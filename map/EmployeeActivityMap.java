package map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

import log.Log;

public final class EmployeeActivityMap extends HashMap<String, Log> {
    private static final long serialVersionUID = 1L;
    private static final String EMPLOYEES_CHECKEDOUT =    "SELECT e.email, e.last_name, e.first_name, b.title "
                                                        + "FROM checkedout_employees AS ce "
                                                        + "INNER JOIN employees AS e " 
                                                        + " ON e.email = ce.email " 
                                                        + "INNER JOIN checkout_main AS cm " 
                                                        + " ON cm.checkout_id = ce.checkout_id " 
                                                        + "INNER JOIN checkout_books AS cb " 
                                                        + " ON cb.checkout_id = ce.checkout_id " 
                                                        + "INNER JOIN books AS b " 
                                                        + " ON b.call_number = cb.call_number;";
                                                      /*+ "WHERE cm.return_date >= DATE_SUB(NOW(), "
                                                        + "INTERVAL 10 MINUTE;";*/
    
    public EmployeeActivityMap(final Connection aConn)  {
        try(final PreparedStatement pstmt = aConn.prepareStatement(EMPLOYEES_CHECKEDOUT);
        final ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                final Log l = new Log();
                l.setActivity("CHECKOUT");
                l.setLastName(rs.getString("last_name"));
                l.setFirstName(rs.getString("first_name"));
                l.setDate(rs.getDate("checkout_date").toString());
                l.setTitle(rs.getString("title"));
                put(rs.getString("email"), l);
                
            }     
        } catch(final SQLException e) {
            Utility.showErrorMessage(null, "Error Loading Employee Activity Data!");
            e.printStackTrace();
        }
    }
}