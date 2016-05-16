package map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

import Personnel.Employee;

public final class EmployeeMap extends HashMap<String, Employee>{
    private static final long serialVersionUID = 1L;
    private static final String SELECT_EMPLOYEE = ("SELECT e.email, e.last_name, e.first_name, a.country, " 
                                                 + "a.city, a.address, a.state, a.postal_code, p.number, " 
                                                 + "e.date_of_hire, e.is_admin, p.type "  
                                                 + "FROM employees AS e "
                                                 + "INNER JOIN employee_addresses AS a "
                                                 + " ON a.email = e.email " 
                                                 + "INNER JOIN employee_phone_numbers AS p "
                                                 + " ON p.email = e.email "
                                                 + "ORDER BY e.last_name;");
    
    public EmployeeMap() {}
    
    public EmployeeMap(final Connection aConn) {
        try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_EMPLOYEE);
            final ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                final Employee employee = new Employee();
                employee.setCountry(rs.getString("country"));
                employee.setIsAdmin(rs.getInt("is_admin"));
                employee.setEmail(rs.getString("email"));
                employee.setLastName(rs.getString("last_name"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setCity(rs.getString("city"));
                employee.setState(rs.getString("state"));
                employee.setAddress(rs.getString("address"));
                employee.setZip(rs.getString("postal_code"));
                employee.setPhone(rs.getString("number"));
                employee.setTypePhone(rs.getString("type"));
                employee.setAccountType("Employee");
                employee.setEmail(rs.getString("email"));
                employee.setHireDate(rs.getDate("date_of_hire").toString());
                put(employee.getEmail(), employee);    
            }
       } catch(final SQLException e) {
           Utility.showErrorMessage(null, "Error Loading Employee Data!");
           e.printStackTrace();
       }
    }
}