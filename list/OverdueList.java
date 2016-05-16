package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import utility.Utility;
import log.Log;

public final class OverdueList extends ArrayList<Log> {
    private static final long serialVersionUID = 1L;
    
    private static final String GET_OVERDUE = "SELECT cp.email, GetDaysOverdue(cp.email) AS days_overdue, cd.checkout_date "
                                            + "FROM checkout_personnel AS cp "
                                            + "INNER JOIN returned_dates AS rd "
                                            + "ON rd.checkout_id = cp.checkout_id "
                                            + "INNER JOIN checkout_dates AS cd "
                                            + "ON cd.checkout_id = rd.checkout_id "
                                            + "INNER JOIN checkout_inventory AS ci "
                                            + "ON ci.checkout_id = rd.checkout_id "
                                            + "WHERE rd.checkout_id = null AND DATE_ADD(cd.checkout_date, INTERVAL 45 DAY) < CURDATE();";

    public OverdueList(final Connection aConn) {
        
            try(final PreparedStatement pstmt = aConn.prepareStatement(GET_OVERDUE);
                    final ResultSet rs = pstmt.executeQuery()) {
                        while(rs.next()) {
                            final Log log = new Log();
                            log.setEmail(rs.getString("email"));
                            log.setDaysOverdue(rs.getInt("days_overdue"));
                            log.setDate(rs.getDate("checkout_date").toString());
                            add(log);
                        }
                }  catch(final SQLException e) {
                    Utility.showErrorMessage(null, "Error Loading Checkout Data!");
                    e.printStackTrace();
                }
    }
}
