package map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;
import Personnel.Student;

public final class StudentMap extends HashMap<String, Student> {
    private static final long serialVersionUID = 1L;
    
    private static final String SELECT_STUDENTS = "SELECT s.email, "
                                                  /*+ "GetStudentCheckoutCountBook(s.email) AS number_books_checkout, "*/
                                                  //+ "GetStudentCheckoutCountOther(s.email) AS number_other_checkout, "
                                                  + "s.fine, "
                                                  + "s.last, s.first, "  
                                                  + "s.class_rank, s.major, a.country, a.address, " 
                                                  + "a.city, a.state, a.postal_code, p.number, p.type "  
                                                  + "FROM students AS s " 
                                                  + "INNER JOIN student_addresses AS a "
                                                  + " ON a.email = s.email " 
                                                  + "INNER JOIN student_phone_numbers AS p " 
                                                  + " ON p.email = s.email " 
                                                  + "ORDER BY s.last;";
    

    
  public StudentMap(final Connection aConn)  { 
    try(final PreparedStatement pstmt =  aConn.prepareStatement(SELECT_STUDENTS);
        final ResultSet rs = pstmt.executeQuery()) {
                 while(rs.next()) {
                     final Student student = new Student();
                     student.setFine(rs.getBigDecimal("fine"));
                     student.setEmail(rs.getString("email"));
                     student.setLastName(rs.getString("last"));
                     student.setFirstName(rs.getString("first"));
                     student.setCountry(rs.getString("country"));
                     student.setCity(rs.getString("city"));
                     student.setState(rs.getString("state"));
                     student.setZip(rs.getString("postal_code"));
                     student.setPhone(rs.getString("number"));
                     student.setTypePhone(rs.getString("type"));
                     student.setAddress(rs.getString("address"));         
                     student.setClassRank(Integer.parseInt(rs.getString("class_rank")));
                     student.setMajor(rs.getString("major"));
                     student.setAccountType("Student");
                     //student.setCheckoutCountBook(rs.getInt("number_books_checkout"));
                     //student.setCheckoutCountOther(rs.getInt("number_other_checkout"));
                     put(student.getEmail(), student); 
                 }
         } catch(final SQLException e) {
             Utility.showErrorMessage(null, "Error Loading Student Data!");
             e.printStackTrace();
         }
  }
}