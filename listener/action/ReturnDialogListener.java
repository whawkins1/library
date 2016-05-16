package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JTextField;

import ui.dialog.ReturnTicketDialog;
import utility.Utility;

public final class ReturnDialogListener
                            implements ActionListener {
       private final static String RETURN_EXISTS = "SELECT COUNT(*) "
                                                 + "FROM (SELECT checkout_id "
                                                 + "      FROM checkout_personnel WHERE checkout_id "
                                                 + "      NOT IN (SELECT checkout_id FROM returned_dates)) AS not_returned "
                                                 + "WHERE not_returned.checkout_id  = (?);";
       
       private final static String INSERT_DATE = "INSERT INTO returned_dates SET checkout_id = (?);"; 
       
       final JTextField fCheckoutIDTF;
       final JTextField fCallNumberTF;
       final JDialog fParent;

   public ReturnDialogListener(final JTextField aCheckoutIDTF, 
                               final JTextField aCallNumberTF, 
                               final JDialog aParent) {
       this.fCheckoutIDTF = aCheckoutIDTF;
       this.fCallNumberTF = aCallNumberTF;
       this.fParent = aParent;
   }
        

        @Override public final void actionPerformed(ActionEvent ae) {
                final String checkoutID = fCheckoutIDTF.getText().trim();
                    if(checkoutID.length() > 0) { //&& callNumber.length() > 0) {
                        final String validPrefixLC = checkoutID.toLowerCase();
                        final boolean validPrefix = validPrefixLC.startsWith("chko"); 
                        if(validPrefix) {
                            final String returnID = checkoutID.substring(4);
                            if(isNumeric(returnID)) {
                                final int returnIDConverted = Integer.parseInt(returnID);
                                try(final Connection conn = Utility.getConnection()) {
                                    final boolean returnExists = entryExists(RETURN_EXISTS, returnIDConverted, conn);
                                    if(returnExists) {
                                         final boolean successInsertReturn = insertReturnDate(INSERT_DATE, returnIDConverted, conn);
                                         if(successInsertReturn) {
                                                  new ReturnTicketDialog(Integer.parseInt(returnID), conn, fParent);
                                                  return;
                                         }
                                         Utility.showErrorMessage(fParent, "Error Inserting Item, Please Try Again.");
                                         return;
                                   }
                                      Utility.showErrorMessage(fParent, "Error Returning Item Doesn't Exists, Please Try Again.");
                                      return;
                                } catch(final SQLException e) {
                                    e.printStackTrace();
                                }
                                Utility.showErrorMessage(fParent, "Error Inserting Please Try Again?");
                                fCheckoutIDTF.requestFocusInWindow();
                                return;
                            }
                            Utility.showErrorMessage(fParent, "Error Input Must Be a Number");
                            fCheckoutIDTF.requestFocusInWindow();
                            return;
                        }
                        Utility.showErrorMessage(fParent, "Error ID Must start with CHKO");
                        fCheckoutIDTF.requestFocusInWindow();
                        return;                            
               }   Utility.showErrorMessage(fParent, "Both Fields Must Be Completed, Please Try Again");
               fCheckoutIDTF.requestFocusInWindow();
        }
        
        
        private final boolean insertReturnDate(final String aQuery, 
                                               final int aCheckoutID,
                                               final Connection aConn)  {
        int insertSuccess = -1; 
            try(final PreparedStatement pstmt = aConn.prepareStatement(aQuery)    ){    
                pstmt.setInt(1, aCheckoutID);
                insertSuccess = pstmt.executeUpdate();
            } catch(final SQLException e) {
              e.printStackTrace();
            } 
            return (insertSuccess != 0);
        }
        
        private final boolean entryExists(final String aQuery, 
                                          final int aCheckoutID,
                                          final Connection aConn)   {
            int emailFound = -1;
            try (final PreparedStatement pstmt = aConn.prepareStatement(aQuery)   ){
                    pstmt.setInt(1, aCheckoutID);
                    final ResultSet rs = pstmt.executeQuery();
                    if(rs.next())
                        emailFound = rs.getInt(1);
            } catch(final SQLException e) {
                 e.printStackTrace();
            }  
          return (emailFound != 0);
        }
        
        private final boolean isNumeric(final String aInput) {
            return aInput.matches("[-+]?\\d+(\\.\\d+)?");
        }
}