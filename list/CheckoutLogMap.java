package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

import log.Log;

public final class CheckoutLogMap extends HashMap<Integer, Log> {
    private static final long serialVersionUID = 1L;
    
    private final static String CHECKOUT_STUDENTS = "SELECT s.last, s.first, s.email, cd.checkout_date, cp.checkout_id "
                                                  + "FROM students AS s "
                                                  + "INNER JOIN checkout_personnel as cp ON s.email = cp.email "
                                                  + "INNER JOIN checkout_dates AS cd ON cd.checkout_id = cp.checkout_id";
    
    private final static String CHECKOUT_FACULTY = "SELECT f.last_name, f.first_name, cd.checkout_date, f.email, cp.checkout_id "
                                                 + "FROM faculty AS f "
                                                 + "INNER JOIN checkout_personnel as cp ON f.email = cp.email "
                                                 + "INNER JOIN checkout_dates AS cd ON cd.checkout_id = cp.checkout_id";
    
    private final static String CHECKOUT_AUDIO = "SELECT a.title, ci.checkout_id "
                                               + "FROM audio AS a "
                                               + "INNER JOIN checkout_inventory AS ci "
                                               + "ON a.call_number = ci.call_number";
    
    private final static String CHECKOUT_BOOKS = "SELECT b.title, ci.checkout_id "
                                               + "FROM books AS b "
                                               + "INNER JOIN checkout_inventory AS ci "
                                               + "ON b.call_number = ci.call_number"; 
    
    private final static String CHECKOUT_VIDEO = "SELECT v.title, ci.checkout_id "
                                               + "FROM video AS v "
                                               + "INNER JOIN checkout_inventory AS ci "
                                               + "ON v.call_number = ci.call_number";
    
                                                         
    public CheckoutLogMap(final Connection aConn) {
        processStudents(aConn);
        processFaculty(aConn);
        processInventory(aConn, "Audio", CHECKOUT_AUDIO);
        processInventory(aConn, "Book", CHECKOUT_BOOKS);
        processInventory(aConn, "Video", CHECKOUT_VIDEO);
    }

    
        private final void processStudents(final Connection aConn) {
            try(final PreparedStatement pstmt = aConn.prepareStatement(CHECKOUT_STUDENTS);
                    final ResultSet rs = pstmt.executeQuery()) {
                        while(rs.next()) {
                            final Log log = new Log();
                            log.setLastName(rs.getString("last"));
                            log.setFirstName(rs.getString("first"));
                            log.setEmail(rs.getString("email"));
                            log.setPersonnelType("Student");
                            log.setDate(rs.getDate("checkout_date").toString());
                            put(rs.getInt("checkout_id"), log);
                        }
                }  catch(final SQLException e) {
                    Utility.showErrorMessage(null, "Error Loading Checkout Data!");
                    e.printStackTrace();
                }
        }
    
        
        private final void processFaculty(final Connection aConn) {
            try(final PreparedStatement pstmt = aConn.prepareStatement(CHECKOUT_FACULTY);
                    final ResultSet rs = pstmt.executeQuery()) {
                        while(rs.next()) {
                            final Log log = new Log();
                            log.setLastName(rs.getString("last_name"));
                            log.setFirstName(rs.getString("first_name"));
                            log.setEmail(rs.getString("email"));
                            log.setPersonnelType("Faculty");
                            log.setDate(rs.getDate("checkout_date").toString());
                            put(rs.getInt("checkout_id"), log);
                        }
                }  catch(final SQLException e) {
                    Utility.showErrorMessage(null, "Error Loading Checkout Data!");
                    e.printStackTrace();
                }
        }
        
        
        private final void processInventory(final Connection aConn, final String aType, final String aSQL) {
            try(final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                    final ResultSet rs = pstmt.executeQuery()) {
                       while(rs.next()) {
                           final Log log = get(rs.getInt("checkout_id"));
                           log.setActivity("CHECKOUT");
                           log.setTitle(rs.getString("title"));
                           log.setInventoryType(aType);
                       }
                } catch(final SQLException e) {
                    e.printStackTrace();
                }
        }
}