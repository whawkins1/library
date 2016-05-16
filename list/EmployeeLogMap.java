package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.Utility;
import log.Log;

public final class EmployeeLogMap extends ArrayList<Log> {
    private static final long serialVersionUID = 1L;
    private final static String SELECT_LOGS = ("SELECT e.last_name, e.first_name, l.activity, l.date "
                                             + "FROM employees AS e "
                                             + "INNER JOIN employee_logs AS l  "
                                             + "WHERE l.email = e.email;");
    
    public EmployeeLogMap(final Connection aConn) {
        try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_LOGS);
            final ResultSet rs = pstmt.executeQuery() ) {
            while(rs.next()) {
                final Log log = new Log();
                log.setLastName(rs.getString("last_name"));
                log.setFirstName(rs.getString("first_name"));
                log.setDate((rs.getDate("date").toString()));
                log.setActivity(rs.getString("activity"));
                add(log);
           }
        } catch(final SQLException e) {
            Utility.showErrorMessage(null, "Error Loading Employees Logs Data!");
            e.printStackTrace();
        }
   }
}