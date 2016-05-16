package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;
import log.Log;

public final class ReturnedLogMap extends HashMap<Integer, Log> {
    private static final long serialVersionUID = 1L;
    
    private final static String RETURN_STUDENTS = "SELECT s.last, s.first, s.email, rd.returned_date, rd.checkout_id "
                                                + "FROM returned_dates AS rd "
                                                + "INNER JOIN checkout_personnel AS cp "
                                                + "ON cp.checkout_id = rd.checkout_id "
                                                + "INNER JOIN students AS s "
                                                + "ON s.email = cp.email;";

    private final static String RETURN_FACULTY = "SELECT f.last_name, f.first_name, f.email, rd.returned_date, rd.checkout_id "
                                               + "FROM returned_dates AS rd "
                                               + "INNER JOIN checkout_personnel AS cp "
                                               + "ON cp.checkout_id = rd.checkout_id "
                                               + "INNER JOIN faculty AS f "
                                               + "ON f.email = cp.email;";
    
    private final static String RETURN_AUDIO = "SELECT a.title, rd.checkout_id "
                                             + "FROM returned_dates AS rd "
                                             + "INNER JOIN checkout_inventory AS ci "
                                             + "ON ci.checkout_id = rd.checkout_id "
                                             + "INNER JOIN audio AS a "
                                             + "ON a.call_number = ci.call_number;";
    
    private final static String RETURN_BOOKS = "SELECT b.title, rd.checkout_id "
                                             + "FROM returned_dates AS rd "
                                             + "INNER JOIN checkout_inventory AS ci "
                                             + "ON ci.checkout_id = rd.checkout_id "
                                             + "INNER JOIN books AS b "
                                             + "ON b.call_number = ci.call_number;";
    
    private final static String RETURN_VIDEO = "SELECT v.title, rd.checkout_id "
                                            + "FROM returned_dates AS rd "
                                            + "INNER JOIN checkout_inventory AS ci "
                                            + "ON ci.checkout_id = rd.checkout_id "
                                            + "INNER JOIN video AS v "
                                            + "ON v.call_number = ci.call_number;";
    
    public ReturnedLogMap(final Connection aConn) {
        processStudents(aConn);
        processFaculty(aConn);
        processInventory(aConn, "Audio", RETURN_AUDIO);
        processInventory(aConn, "Book", RETURN_BOOKS);
        processInventory(aConn, "Video", RETURN_VIDEO);
    }
    
        private final void processStudents(final Connection aConn) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(RETURN_STUDENTS);
                    final ResultSet rs = pstmt.executeQuery()) {
                    while(rs.next()) {
                        final Log log = new Log();
                        log.setLastName(rs.getString("last"));
                        log.setFirstName(rs.getString("first"));
                        log.setEmail(rs.getString(("email")));
                        log.setDate(rs.getDate("returned_date").toString());
                        put(rs.getInt("checkout_id"), log);
                    }
                } catch(final SQLException e) {
                    Utility.showErrorMessage(null, "Error Loading Returned Data!");
                    e.printStackTrace();
                }
        }
        
    
        private final void processFaculty(final Connection aConn) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(RETURN_FACULTY);
                    final ResultSet rs = pstmt.executeQuery()) {
                    while(rs.next()) {
                        final Log log = new Log();
                        log.setLastName(rs.getString("last_name"));
                        log.setFirstName(rs.getString("first_name"));
                        log.setEmail(rs.getString("email"));
                        log.setDate(rs.getDate("returned_date").toString());
                        put(rs.getInt("checkout_id"), log);
                    }
                } catch(final SQLException e) {
                    Utility.showErrorMessage(null, "Error Loading Returned Data!");
                    e.printStackTrace();
                }
        }
        
    
        private final void processInventory(final Connection aConn, 
                                            final String aType, 
                                            final String aSQL) {
            try(final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                    final ResultSet rs = pstmt.executeQuery()) {
                       while(rs.next()) {
                           final Log log = get(rs.getInt("checkout_id"));
                           log.setActivity("RETURN");
                           log.setTitle(rs.getString("title"));
                           log.setInventoryType(aType);
                       }
                } catch(final SQLException e) {
                    e.printStackTrace();
                }
        }
}