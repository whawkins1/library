package thread;

import handler.DataHandler;
import inventory.Audio;
import inventory.Book;
import inventory.Inventory;
import inventory.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.SwingWorker;

import log.Log;
import utility.Utility;
import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Personnel;
import Personnel.Student;

public final class Polling extends SwingWorker<Void, Void> {
    
    private final static String STUDENTS_INFO = "SELECT email, last, first, class_rank, major"
                                              + "FROM students AS s"
                                              + "WHERE s.last_edit_date >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private final static String FACULTY_INFO = "SELECT email, last, first, department"
                                             + "FROM faculty AS f"
                                             + "WHERE f.last_edit_date >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private final static String BOOKS_INFO = "SELECT call_number, publisher, title, "
                                            + "author, type, year, date_added"
                                            + "FROM books AS b"
                                            + "WHERE b.last_edit_date >= DATE_SUB(NOW(), "
                                            + "INTERVAL 10 MINUTE;";
    
    private final static String BOOKS_CHECKEDOUT = "SELECT b.call_number, cm.returned_date"
                                                    + "FROM checkout_books AS cb"
                                                    + "INNER JOIN checkout_main AS cm"
                                                    + "ON cb.checkout_id = cm.checkout_id"
                                                    + "INNER JOIN books as b"
                                                    + "ON cb.call_number = b.call_number"
                                                    + "WHERE r.return_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private final static String AUDIO_CHECKEDOUT = "SELECT a.call_number, ca.returned_date"
                                                    + "FROM checkout_audio AS ca"
                                                    + "INNER JOIN checkout_main AS cm"
                                                    + "ON ca.checkout_id = cm.checkout_id"
                                                    + "INNER JOIN audio as a"
                                                    + "ON ca.call_number = a.call_number"
                                                    + "WHERE ca.return_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private final static String VIDEO_CHECKEDOUT = "SELECT v.call_number, cv.returned_date"
                                                    + "FROM checkout_audio AS cv"
                                                    + "INNER JOIN checkout_main AS cm"
                                                    + "ON cv.checkout_id = cm.checkout_id"
                                                    + "INNER JOIN audio as v"
                                                    + "ON cv.call_number = a.call_number"
                                                    + "WHERE cv.return_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private final static String VIDEOS_INFO = "SELECT call_number, title, medium, "
                                            + "studio, year, date_added"
                                            + "FROM video AS v"
                                            + "WHERE v.last_edit_date >= DATE_SUB(NOW(), "
                                            + "INTERVAL 10 MINUTE;";
    
    private final static String AUDIO_INFO = "SELECT call_number, title, medium, "
                                            + "label, year, date_added"
                                            + "FROM audio AS a"
                                            + "WHERE a.last_edit_date >= DATE_SUB(NOW(), "
                                            + "INTERVAL 10 MINUTE;";
    
    private static final String STUDENTS_ADDRESS = "SELECT zip, address, city, state, country, email"
                                                  + "FROM student_addresses AS s"
                                                  + "WHERE s.last_edit_date >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String STUDENTS_DELETED = "SELECT email"
                                                 + "FROM students_deleted AS s"
                                                 + "WHERE s.add_date_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";          
    
    private static final String FACULTY_DELETED = "SELECT email"
                                                + "FROM faculty_deleted AS f"
                                                + "WHERE f.add_date_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String EMPLOYEES_DELETED = "SELECT email"
                                                  + "FROM employees_deleted AS e"
                                                  + "WHERE e.add_date_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String BOOKS_DELETED = "SELECT email"
                                                 + "FROM books_deleted AS b"
                                                 + "WHERE b.add_date_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String VIDEOS_DELETED = "SELECT email"
                                                 + "FROM videos_deleted AS v"
                                                 + "WHERE v.add_date_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String AUDIO_DELETED = "SELECT email"
                                                 + "FROM audio_deleted AS a"
                                                 + "WHERE s.add_date_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String FACULTY_ADDRESS = "SELECT zip, address, city, state, country, email"
                                                + "FROM faculty_addresses AS f"
                                                + "WHERE f.last_edit_date >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String STUDENTS_PHONE = "SELECT number, type"
                                               + "FROM student_phone_numbers AS P"
                                               + "WHERE p.last_edit_date >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
    
    private static final String FACULTY_PHONE = "SELECT number, type"
                                              + "FROM student_phone_numbers AS P"
                                              + "WHERE p.last_edit_date >= DATE_SUB(NOW(), INTERVAL 10 MINUTE;";
     
    private static final String STUDENTS_RETURNED = "SELECT r.email, r.returned_date, s.last_name, s.first_name"
                                                  + "FROM returned_dates_students AS r"
                                                  + "INNER JOIN students AS s"
                                                  + "ON r.return_date >= DATE_SUB(NOW(), "
                                                  + "INTERVAL 10 MINUTE;";
    
    private static final String FACULTY_RETURNED = "SELECT r.email, r.returned_date, f.last_name, f.first_name"
                                                    + "FROM returned_dates_faculty AS r"
                                                    + "INNER JOIN faculty AS f"
                                                    + "ON r.return_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private static final String STUDENTS_CHECKOUT = "SELECT s.email, c.checkout_date, s.last_name, s.first_name"
                                                    + "FROM checkout_students AS cs"
                                                    + "INNER JOIN students AS s"
                                                    + "ON cs.email = s.email "
                                                    + "INNER JOIN checkout_main AS cm"
                                                    + "ON cs.checkout_id = cm.checkout_id"
                                                    + "WHERE c.checkout_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private static final String FACULTY_CHECKOUT = "SELECT s.email, c.checkout_date, s.last_name, s.first_name"
                                                    + "FROM checkout_faculty AS cf"
                                                    + "INNER JOIN faculty AS f"
                                                    + "ON cf.email = f.email "
                                                    + "INNER JOIN checkout_main AS cm"
                                                    + "ON cf.checkout_id = cm.checkout_id"
                                                    + "WHERE c.checkout_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private static final String EMPLOYEE_LOGS = "SELECT e.email, e.last_name, e.first_name, "
                                                    + "l.last_add_date, l.activity"
                                                    + "FROM employee_logs AS l"
                                                    + "INNER JOIN employees AS e"
                                                    + "ON l.email = e.email"
                                                    + "WHERE l.last_add_date >= DATE_SUB(NOW(), "
                                                    + "INTERVAL 10 MINUTE;";
    
    private static final String EMPLOYEES_CHECKEDOUT = "SELECT e.email, c.returned_date, f.last_name, f.first_name"
                                                        + "FROM checkedout_employees AS ce"
                                                        + "INNER JOIN employees AS e"
                                                        + "ON ce.email = e.email"
                                                        + "INNER JOIN checkout_main as cm"
                                                        + "ON ce.checkout_id = cm.checkout_id"
                                                        + "WHERE c.return_date >= DATE_SUB(NOW(), "
                                                        + "INTERVAL 10 MINUTE;";
    
    private static final String EMPLOYEES_RETURNED = "SELECT e.email, c.returned_date, f.last_name, f.first_name"
                                                        + "FROM returned_employees AS re"
                                                        + "INNER JOIN employees AS e"
                                                        + "ON re.email = e.email"
                                                        + "INNER JOIN checkout_main as cm"
                                                        + "ON re.checkout_id = cm.checkout_id"
                                                        + "WHERE c.return_date >= DATE_SUB(NOW(), "
                                                        + "INTERVAL 10 MINUTE;";
    private final DataHandler handler_;
    private final int isAdmin_;
      
    
    
      public Polling(final DataHandler aHandler, final int aIsAdmin) {
          this.handler_ = aHandler;
          this.isAdmin_ = aIsAdmin;
      }
    
      
      
        @Override protected Void doInBackground() throws Exception {
            try(final Connection conn = Utility.getConnection()) {
                if (isAdmin_ == 1) 
                    performEmployeeChecks(conn);
                    
                  performStudentChecks(conn);
                  performFacultyChecks(conn);
                  performAudioChecks(conn);
                  performVideoChecks(conn);
                  performBookChecks(conn);
            } 
                return null;
        }
        
        
        
        private final void performAudioChecks(final Connection aConn) {
            final HashMap<String, Audio> map = handler_.getAudioMap();
            checkAudioCheckout(aConn, map);
            checkAudioInfo(aConn, map); 
            checkInventoryDeleted(aConn, map, AUDIO_DELETED);
        }
        
        
        
        private final void performVideoChecks(final Connection aConn) {
            final HashMap<String, Video> map = handler_.getVideoMap();
            checkVideoInfo(aConn, map);
            checkVideoCheckout(aConn, map);
            checkInventoryDeleted(aConn, map, VIDEOS_DELETED);
        }
        
        
        
        private final void performBookChecks(final Connection aConn) {
            final HashMap<String, Book> map = handler_.getBookMap();
            checkBooksInfo(aConn, map); 
            checkBooksCheckout(aConn, map);
            checkInventoryDeleted(aConn, map, BOOKS_DELETED);
        }
        
        
        
        private final void performFacultyChecks(final Connection aConn) {
            final HashMap<String, Faculty> map = handler_.getFacultyMap();
            checkFacultyInfo(aConn, map);
            checkFacultyAddress(aConn, map);
            checkFacultyPhone(aConn, map);
            checkPersonnelDeleted(aConn, map, FACULTY_DELETED);
            final HashMap<Integer, Log> returnedMap = handler_.getReturnedMap();
            checkFacultyReturned(aConn, returnedMap);
            final HashMap<Integer, Log> checkoutMap = handler_.getCheckoutMap();
            checkFacultyCheckout(aConn, checkoutMap);
            
        }
        
        
        
        private final void performEmployeeChecks(final Connection aConn) {
            final HashMap<String, Employee> map = handler_.getEmployeeMap();
            checkEmployeeInfo(aConn, map);
            checkEmployeeAddress(aConn, map);
            checkEmployeePhone(aConn, map);
            checkPersonnelDeleted(aConn, map, EMPLOYEES_DELETED);
            final HashMap<String, Log> activityMap = handler_.getEmployeeActivityMap();
            checkEmployeesCheckout(aConn, activityMap);
            checkEmployeesReturned(aConn, activityMap);
        }
        
        
        
        private final void performStudentChecks(final Connection aConn) {
                final HashMap<String, Student> map = handler_.getStudentMap();
                checkStudentsInfo(aConn, map);
                checkStudentsAddress(aConn, map);
                checkStudentsPhone(aConn, map);
                checkPersonnelDeleted(aConn, map, STUDENTS_DELETED);
                final HashMap<Integer, Log> returnMap = handler_.getReturnedMap();
                checkStudentsReturned(aConn, returnMap);
                final HashMap<Integer, Log> checkoutMap = handler_.getCheckoutMap();
                checkStudentsCheckout(aConn, checkoutMap);
        }
        
        
        
        private final void checkPersonnelDeleted(final Connection aConn, 
                                                 final HashMap<String, ? extends Personnel> aMap, 
                                                 final String aQuery) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(aQuery);
                 final ResultSet rs = pstmt.executeQuery()) {
                   while (rs.next()) {
                       aMap.remove(rs.getString("email"));
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        
        
        private final void checkInventoryDeleted(final Connection aConn, 
                                                 final HashMap<String, ? extends Inventory> aMap, 
                                                 final String aQuery) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(aQuery);
                 final ResultSet rs = pstmt.executeQuery()) {
                   while (rs.next()) {
                       aMap.remove(rs.getString("call_number"));
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        
        
        private final void checkStudentsInfo(final Connection aConn, final HashMap<String, Student> aMap) {
              try (final PreparedStatement pstmt = aConn.prepareStatement(STUDENTS_INFO);
                   final ResultSet rs = pstmt.executeQuery()) {
                  
                  while (rs.next()) {
                      final String email = rs.getString("email");
                      final Student s = aMap.get(email);
                      s.setEmail(email);
                      s.setLastName(rs.getString("last"));
                      s.setFirstName(rs.getString("first"));
                      s.setClassRank(rs.getInt("class_rank"));
                      s.setMajor(rs.getString("Major"));
                  }
              } catch (final SQLException e) {
                  e.printStackTrace();
              }            
        }
        
        
        
        private final void checkStudentsAddress(final Connection aConn, final HashMap<String, Student> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(STUDENTS_ADDRESS);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Student s = aMap.get(rs.getString("email"));
                    s.setZip(rs.getString("zip"));
                    s.setAddress(rs.getString("address"));
                    s.setState(rs.getString("state"));
                    s.setCountry(rs.getString("country"));
                }                   
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        
        
        private final void checkStudentsPhone(final Connection aConn, final HashMap<String, Student> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(STUDENTS_PHONE);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Student s = aMap.get(rs.getString("email"));
                    s.setPhone(rs.getString("phone_id"));;
                    s.setTypePhone(rs.getString("type"));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkStudentsReturned(final Connection aConn, final HashMap<Integer, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(STUDENTS_RETURNED);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                   while (rs.next()) {
                       final Log l = new Log();
                       l.setActivity("RETURN");
                       l.setLastName(rs.getString("last_name"));
                       l.setFirstName(rs.getString("first_name"));
                       l.setDate(rs.getDate("returned_date").toString());
                       aMap.put(rs.getInt("checkout_id"), l);
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }      
        }
        
        
        
        private final void checkStudentsCheckout(final Connection aConn, final HashMap<Integer, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(STUDENTS_CHECKOUT);
                 final ResultSet rs = pstmt.executeQuery()) {
                    
                   while (rs.next()) {
                       final Log l = new Log();
                       l.setActivity("CHECKOUT");
                       l.setLastName(rs.getString("last_name"));
                       l.setFirstName(rs.getString("first_name"));
                       l.setDate(rs.getDate("checkout_date").toString());
                       aMap.put(rs.getInt("checkout_id"), l);
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }

        
        
        private final void checkFacultyInfo(final Connection aConn, final HashMap<String, Faculty> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_INFO);
                 final ResultSet rs = pstmt.executeQuery()) {
                    
                   while (rs.next()) {
                       final String email = rs.getString("email");
                       final Faculty f = aMap.get(email);
                       f.setEmail(email);
                       f.setLastName(rs.getString("last"));
                       f.setFirstName(rs.getString("first"));
                       f.setDept(rs.getString("department"));
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        

        
        private final void checkFacultyAddress(final Connection aConn, final HashMap<String, Faculty> aMap) {
                try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_ADDRESS);
                     final ResultSet rs = pstmt.executeQuery()) {
                    
                    while (rs.next()) {
                        final Faculty f = aMap.get(rs.getString("email"));
                        f.setZip(rs.getString("zip"));
                        f.setAddress(rs.getString("address"));
                        f.setState(rs.getString("state"));
                        f.setCountry(rs.getString("country"));
                    }                   
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }   
        
        
        
        private final void checkFacultyPhone(final Connection aConn, final HashMap<String, Faculty> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_PHONE);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Faculty f = aMap.get(rs.getString("email"));
                    f.setPhone(rs.getString("phone_id"));;
                    f.setTypePhone(rs.getString("type"));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkFacultyReturned(final Connection aConn, final HashMap<Integer, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_RETURNED);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                   while (rs.next()) {
                       final Log l = new Log();
                       l.setActivity("RETURN");
                       l.setLastName(rs.getString("last_name"));
                       l.setFirstName(rs.getString("first_name"));
                       l.setDate(rs.getDate("returned_date").toString());
                       aMap.put(rs.getInt("checkout_id"), l);
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        
        
        private final void checkFacultyCheckout(final Connection aConn, final HashMap<Integer, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_CHECKOUT);
                 final ResultSet rs = pstmt.executeQuery()) {
                    
                   while (rs.next()) {
                       final Log l = new Log();
                       l.setActivity("CHECKOUT");
                       l.setLastName(rs.getString("last_name"));
                       l.setFirstName(rs.getString("first_name"));
                       l.setDate(rs.getDate("checkout_date").toString());
                       aMap.put(rs.getInt("checkout_id"), l);
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        

        private final void checkEmployeeInfo(final Connection aConn, final HashMap<String, Employee> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(STUDENTS_INFO);
                 final ResultSet rs = pstmt.executeQuery()) {
                    
                   while (rs.next()) {
                       final String email = rs.getString("email");
                       final Employee e = aMap.get(email);
                       e.setEmail(email);
                       e.setLastName(rs.getString("last"));
                       e.setFirstName(rs.getString("first"));
                       e.setIsAdmin(rs.getInt("isAdmin"));
                       e.setHireDate((rs.getDate("date_of_hire")).toString());
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }            
        }
        
        
        
        private final void checkEmployeeAddress(final Connection aConn, final HashMap<String, Employee> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_ADDRESS);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Employee e = aMap.get(rs.getString("email"));
                    e.setZip(rs.getString("zip"));
                    e.setAddress(rs.getString("address"));
                    e.setState(rs.getString("state"));
                    e.setCountry(rs.getString("country"));
                }                   
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        
        private final void checkEmployeePhone(final Connection aConn, final HashMap<String, Employee> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(FACULTY_PHONE);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Employee e = aMap.get(rs.getString("email"));
                    e.setPhone(rs.getString("phone_id"));;
                    e.setTypePhone(rs.getString("type"));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkEmployeeLogs(final Connection aConn, final HashMap<String, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(EMPLOYEE_LOGS);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Log l = new Log();
                    l.setActivity(rs.getString("activity"));
                    final java.sql.Date sqlDate = rs.getDate("last_add_date");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    l.setDate(df.format(sqlDateConverted));
                    l.setLastName(rs.getString("last_name"));
                    l.setFirstName(rs.getString("first_name"));
                    aMap.put(rs.getString("email"), l);
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            } 
        }
        
        
        
        private final void checkEmployeesCheckout(final Connection aConn, final HashMap<String, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(EMPLOYEES_CHECKEDOUT);
                 final ResultSet rs = pstmt.executeQuery()) {
                    
                   while (rs.next()) {
                       final Log l = new Log();
                       l.setActivity("CHECKOUT");
                       l.setLastName(rs.getString("last_name"));
                       l.setFirstName(rs.getString("first_name"));
                       l.setDate(rs.getDate("checkout_date").toString());
                       aMap.put(rs.getString("email"), l);
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        
        
        private final void checkEmployeesReturned(final Connection aConn, final HashMap<String, Log> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(EMPLOYEES_RETURNED);
                 final ResultSet rs = pstmt.executeQuery()) {
                    
                   while (rs.next()) {
                       final Log l = new Log();
                       l.setActivity("RETURNED");
                       l.setLastName(rs.getString("last_name"));
                       l.setFirstName(rs.getString("first_name"));
                       l.setDate(rs.getDate("checkout_date").toString());
                       aMap.put(rs.getString("email"), l);
                   }
               } catch (final SQLException e) {
                   e.printStackTrace();
               }
        }
        
        
        
        private final void checkBooksInfo(final Connection aConn, final HashMap<String, Book> aMap) {
            try (   final PreparedStatement pstmt = aConn.prepareStatement(BOOKS_INFO);
                    final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Book b = aMap.get(rs.getString("call_number"));
                    b.setPublisher(rs.getString("publisher"));
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setType(rs.getString("type"));
                    b.setYear(rs.getInt("year"));
                    final java.sql.Date sqlDate = rs.getDate("date_added");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    b.setDateAdded(df.format(sqlDateConverted));
                    b.setNumCopies(rs.getInt("num_copies"));
                    b.setGenre(rs.getString("genre"));
                    b.setDescription(rs.getString("Description"));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            } 
        }
        
        
        
        private final void checkBooksCheckout(final Connection aConn,final HashMap<String, Book> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(BOOKS_CHECKEDOUT);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Book b = aMap.get(rs.getString("call_number"));
                    final java.sql.Date sqlDate = rs.getDate("checkout_date");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    b.setCheckoutDate(df.format(sqlDateConverted));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkVideoInfo(final Connection aConn, final HashMap<String, Video> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(VIDEOS_INFO);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Video v = aMap.get(rs.getString("call_number"));
                    v.setTitle(rs.getString("title"));
                    v.setStorageMedium(rs.getString("medium"));
                    v.setStudio(rs.getString("studio"));
                    v.setYear(rs.getInt("year"));
                    final java.sql.Date sqlDate = rs.getDate("date_added");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    v.setDateAdded(df.format(sqlDateConverted));
                    v.setNumCopies(rs.getInt("num_copies"));
                    v.setDescription(rs.getString("Description"));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkVideoCheckout(final Connection aConn, final HashMap<String, Video> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(VIDEO_CHECKEDOUT);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Video v = aMap.get(rs.getString("call_number"));
                    final java.sql.Date sqlDate = rs.getDate("checkout_date");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    v.setCheckoutDate(df.format(sqlDateConverted));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkAudioCheckout(final Connection aConn, final HashMap<String, Audio> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(AUDIO_CHECKEDOUT);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Audio a = aMap.get(rs.getString("call_number"));
                    final java.sql.Date sqlDate = rs.getDate("checkout_date");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    a.setCheckoutDate(df.format(sqlDateConverted));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
        
        
        
        private final void checkAudioInfo(final Connection aConn, final HashMap<String, Audio> aMap) {
            try (final PreparedStatement pstmt = aConn.prepareStatement(AUDIO_INFO);
                 final ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    final Audio a = aMap.get(rs.getString("call_number"));
                    a.setTitle(rs.getString("title"));
                    a.setStorageMedium(rs.getString("medium"));
                    a.setLabel(rs.getString("label"));
                    a.setYear(rs.getInt("year"));
                    final java.sql.Date sqlDate = rs.getDate("date_added");
                    final java.util.Date sqlDateConverted = new java.util.Date(sqlDate.getTime());
                    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    a.setDateAdded(df.format(sqlDateConverted));
                    a.setNumCopies(rs.getInt("num_copies"));
                    a.setDescription(rs.getString("Description"));
                }                   
            } catch (final SQLException e) {
                 e.printStackTrace();
            }
        }
}