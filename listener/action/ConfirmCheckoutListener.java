package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ui.dialog.CheckoutDialog;
import ui.dialog.CheckoutTicketDialog;
import utility.Utility;


public final class ConfirmCheckoutListener
                                implements ActionListener {
    
    private final static String PERSONNEL_EXISTS = "SELECT COUNT(*)  FROM (SELECT email FROM students "
                                                                          + "UNION ALL "
                                                                          + "SELECT email FROM faculty) AS personnel "
                                                    + "WHERE personnel.email = (?);";
    
    private final static String INVENTORY_EXISTS = "SELECT COUNT(*) FROM (SELECT call_number FROM books "
                                                                          + "UNION ALL "
                                                                          + "SELECT call_number FROM audio "
                                                                          + "UNION ALL "
                                                                          + "SELECT call_number FROM video) as inventory "
                                                                          + "WHERE inventory.call_number = (?);";
    
    private final static String INSERT_CHECKEDOUT_EMPLOYEE = "INSERT INTO checkedout_employees (email) "
                                                           + "VALUES (?);";
            
    private final static String INSERT_CHECKOUT_PERSONNEL = "INSERT INTO checkout_personnel "
                                                          + "SET email = (?);";
    
    private final static String INSERT_CHECKOUT_INVENTORY = "INSERT INTO checkout_inventory "
                                                          + "SET call_number = (?);";
    
    private final static String INVENTORY_OVERDUE = "SELECT COUNT(*) AS number_overdue FROM ( SELECT checkout_id "  
                                                    + "FROM checkout_personnel "
                                                    + "WHERE email = (?) "
                                                    + ") AS id_personnel "
                                                    + "INNER JOIN checkout_dates AS cd "
                                                    + "ON cd.checkout_id = id_personnel.checkout_id "
                                                    + "LEFT JOIN returned_dates AS rd "
                                                    + "ON rd.checkout_id = cd.checkout_id "
                                                    + "WHERE rd.checkout_id = NULL AND DATE_ADD(cd.checkout_date, INTERVAL 45 DAY) < CURDATE());";    
    
    private final static String IS_STUDENT = "SELECT EXISTS  "
                                                   + "( SELECT 1 "
                                                   + "FROM students AS s  "
                                                   + "WHERE s.email = (?) "
                                                   + "LIMIT 1 )";
   
    
    private final static String CHECKOUT_LAST_ID = "SELECT MAX(checkout_id) "
                                                 + "FROM checkout_personnel";
    
    private final static String CHECK_AVAILABLE_COPIES = "SELECT GetAvailableCopies(?);";
    
    private final CheckoutDialog fDialog;
    private final String fSigninEmail;
    
    public ConfirmCheckoutListener(final CheckoutDialog aDialog, final String aSigninEmail) {
        this.fDialog= aDialog;
        this.fSigninEmail = aSigninEmail;
    }
    
        private final boolean checkInventoryAvailable(final String callNum) {
           boolean available = false;
           try(final Connection conn = Utility.getConnection(); 
               final PreparedStatement pstmt = conn.prepareStatement(CHECK_AVAILABLE_COPIES)) {
               pstmt.setString(1, callNum);
               final ResultSet rs = pstmt.executeQuery();
               int copies = 0;
               if(rs.next())
                   copies = rs.getInt(1);
               available = (copies != 0);
               rs.close();
           } catch (final SQLException e) {
               e.printStackTrace();
           }
           return available;
        }
    
         @Override public final void actionPerformed(final ActionEvent e) {
            final String callNum = fDialog.getCallNumberTF().getText().trim();
            final String email = fDialog.getEmailTF().getText().trim();
            if(callNum.length() > 0) {  
                if(email.length() > 0) {
                                      final boolean personnelExists = checkPersonnelExists(email);
                                          if(personnelExists) {
                                              final boolean inventoryFound = checkInventoryExists(callNum);
                                            if(inventoryFound) {
                                                   final boolean inventoryAvailable = checkInventoryAvailable(callNum);
                                                   if(inventoryAvailable) {
                                                       performCheckout(callNum, email);
                                                       return;
                                                   }
                                                   Utility.showErrorMessage(fDialog, "No Available Copies, Please Come Back Later.");
                                                   return;
                                             }    
                                                 Utility.showErrorMessage(fDialog, "No Book Matches for the Call Number " + 
                                                         callNum + ", Please Try Again.");
                                                         fDialog.setFocusCallNumber();
                                                 return;                                                 
                                        } 
                                            Utility.showErrorMessage(fDialog, 
                                                    "No Account Matches the Username " +  email + ", "
                                                    + "Please Try Again.");
                                            fDialog.setFocusEmail();
                                            return;
                  } 
               Utility.showErrorMessage(fDialog, "Please Enter Email."  );
               fDialog.setFocusEmail();
               return; 
        } 
            Utility.showErrorMessage(fDialog, "Please Enter Call Number");
              fDialog.setFocusCallNumber();
              return;
    }
        
           private final void performCheckout(final String aCallNumber,
                                              final String aEmail) {
               
               try(final Connection conn = Utility.getConnection()) {
                   final int numberOverdue = getNumberOverdue(conn, aEmail);
                   final String personnelType = getPersonnelType(conn, aEmail);
                   boolean checkoutAllowed = false;
                   if (personnelType.equals("Student")) {
                       if (numberOverdue < 6)
                           checkoutAllowed = true;
                   } else if (personnelType.equals("Faculty")) {
                       if (numberOverdue < 10)
                           checkoutAllowed = true;
                   }
                   if(checkoutAllowed) {
                       conn.setAutoCommit(false);
                       final boolean successPersonnel = insertPersonnel(aEmail, conn);
                          if(successPersonnel) {
                              final boolean successInventory = insertInventory(aCallNumber, conn);
                               if(successInventory)  {
                                       final boolean successEmployee = insertEmployeeCheckedout(conn);
                                       if(successEmployee) {
                                           conn.commit();
                                           final int checkoutID = getLastIDCheckout();
                                           new CheckoutTicketDialog(checkoutID, aCallNumber, conn, fDialog);
                                           return;
                                       }
                                       Utility.showErrorMessage(fDialog, "Error Inserting Checkedout Employee");
                                       return;
                                }
                                   Utility.showErrorMessage(fDialog, "Error Inserting Inventory Checkout");
                                   return;
                           }     
                            Utility.showErrorMessage(fDialog, "Error Inserting Personnel Checkout");
                            return;
                   }
                   Utility.showErrorMessage(fDialog, "Checkout Limit Reached " + "(" + numberOverdue + ")");
               } catch(final SQLException e) {
                   e.printStackTrace();
               }
           }
           
           private final String getPersonnelType(final Connection aConn, final String aEmail) {
               String type = "Faculty";
               try(final PreparedStatement pstmt = aConn.prepareStatement(IS_STUDENT)) {
                   pstmt.setString(1, aEmail);
                   final ResultSet rs = pstmt.executeQuery();
                   final int result = rs.getInt(1);
                   if (result == 1) 
                       type = "Student";
                   rs.close();
               } catch (final SQLException ex) {
                   Utility.showErrorMessage(fDialog, "Error Getting Number Overdue");
                   ex.printStackTrace();
               }
               return type;
           }
           
           
           private final int getNumberOverdue(final Connection aConn, final String aEmail) {
               int overdue = 0;
               try(final PreparedStatement pstmt = aConn.prepareStatement(INVENTORY_OVERDUE)) {
                   pstmt.setString(1, aEmail);
                   final ResultSet rs = pstmt.executeQuery();
                   overdue = rs.getInt("number_overdue");
                   rs.close();
               } catch (final SQLException ex) {
                   Utility.showErrorMessage(fDialog, "Error Getting Number Overdue");
                   ex.printStackTrace();
               }
               return overdue;
           }
           
           
           private final boolean insertEmployeeCheckedout(final Connection aConn) {
               boolean success = false;
               try(final PreparedStatement pstmt = aConn.prepareStatement(INSERT_CHECKEDOUT_EMPLOYEE)) {
                   pstmt.setString(1, fSigninEmail);
                   final int affectedRows = pstmt.executeUpdate();
                   success = (affectedRows != 0);
               } catch(final SQLException e) {
                   e.printStackTrace();
                   try {
                      aConn.rollback();
                   } catch (SQLException e1) {
                      e1.printStackTrace();
                   }
               }
               return success;
           }
           
           private final boolean insertInventory(final String aCallNumber,
                                                 final Connection aConn) {
               boolean success = false;
               try(final PreparedStatement pstmtInventory = aConn.prepareStatement(INSERT_CHECKOUT_INVENTORY)) {   
                   pstmtInventory.setString(1, aCallNumber);
                   final int result = pstmtInventory.executeUpdate();
                   success = (result != 0);
               } catch(SQLException e1) {
                   e1.printStackTrace();
                       try {
                        aConn.rollback();
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   Utility.showErrorMessage(fDialog, "Database Error Inserting");
                }
               return success;
           }
           
           
           private final boolean insertPersonnel(final String aEmail,
                                                 final Connection aConn) {
               boolean success = false;
               try(final PreparedStatement pstmtPersonnel = aConn.prepareStatement(INSERT_CHECKOUT_PERSONNEL)) {
                   pstmtPersonnel.setString(1, aEmail);
                   final int resultPersonnel = pstmtPersonnel.executeUpdate();
                   success = (resultPersonnel != 0);
               } catch(SQLException e1) {
                   e1.printStackTrace();
                   try {
                       aConn.rollback();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
                   Utility.showErrorMessage(fDialog, "Database Error Inserting");
               }
               return success;           
           }
            
           
           private final boolean checkPersonnelExists(final String aEmail) {
                final boolean exists = Utility.checkExists(PERSONNEL_EXISTS, aEmail);
                return exists;
            }
            
           private final boolean checkInventoryExists(final String aCallNumber) {
                final boolean exists = Utility.checkExists(INVENTORY_EXISTS, aCallNumber);
                return exists;
            }
    
            
           
           private final int getLastIDCheckout() {
               int lastID = -1;
               try(final Connection aConn = Utility.getConnection();
                   final PreparedStatement pstmt = aConn.prepareStatement(CHECKOUT_LAST_ID);
                   final ResultSet rs = pstmt.executeQuery()) {
                   if(rs.next())
                       lastID = rs.getInt(1);
               } catch(final SQLException e) {
                   e.printStackTrace();
               }
               return lastID;
           }
 }