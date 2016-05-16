package map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

import Personnel.Faculty;

public final class FacultyMap extends HashMap<String, Faculty> {
    private static final long serialVersionUID = 1L;
    
    private static final String SELECT_FACULTY = "SELECT f.email, "
                                                /*+ "GetFacultyCheckoutCountBook(f.email) AS number_books_checkout, "*/
                                                //+ "GetFacultyCheckoutCountOther(f.email) AS number_other_checkout, "
                                                + "f.fine, "                                                
                                                + " f.last_name, f.first_name, f.date_added, " 
                                                + "f.department, a.address, a.city, a.state, a.postal_code, " 
                                                + "p.number, p.type, a.country "  
                                                + "FROM faculty AS f " 
                                                + "INNER JOIN faculty_addresses AS a " 
                                                + "  ON a.email = f.email  "
                                                + "INNER JOIN faculty_phone_numbers AS p " 
                                                + "  ON p.email = f.email " 
                                                + "ORDER BY f.last_name;";
    
    public FacultyMap(final Connection aConn) {
        try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_FACULTY);
            final ResultSet rs = pstmt.executeQuery()) {
 
                while(rs.next()) {
                    final Faculty faculty = new Faculty();
                    faculty.setDateAdded((rs.getDate("date_added")).toString());
                    //faculty.setFine(rs.getBigDecimal("fine"));                    
                    faculty.setEmail(rs.getString("email"));
                    faculty.setLastName(rs.getString("last_name"));
                    faculty.setFirstName(rs.getString("first_name"));
                    faculty.setCity(rs.getString("city"));
                    faculty.setState(rs.getString("state"));
                    faculty.setCountry(rs.getString("country"));
                    faculty.setAddress(rs.getString("address"));
                    faculty.setDept(rs.getString("department"));
                    faculty.setZip(rs.getString("postal_code"));
                    faculty.setPhone(rs.getString("number"));                      
                    faculty.setTypePhone(rs.getString("type"));
                    faculty.setAccountType("Faculty");
                    //faculty.setCheckoutCountBook(rs.getInt("number_books_checkout"));
                    //faculty.setCheckoutCountOther(rs.getInt("number_other_checkout"));
                    put(faculty.getEmail(), faculty);
                }
        } catch(final SQLException sqle) {
            Utility.showErrorMessage(null, "Error Loading Faculty Data!");
            sqle.printStackTrace();
        }
    }
}