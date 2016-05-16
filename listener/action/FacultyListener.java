package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;



import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.table.FacultyTableModel;
import Personnel.Faculty;
import ui.dialog.FacultyDialog;
import ui.panel.PersonnelInputPanel;
import ui.table.PersonnelTable;
import utility.Utility;


public final class FacultyListener extends PersonnelInput
                                            implements ActionListener {    
        private static final String INFO_QUERY = ("INSERT INTO  faculty  (email, last_name, "
                                                + "first_name, department) "
                                                + "VALUES(?, ?, ?, ?);");
        
        private static final String ADDRESS_QUERY = ("INSERT INTO faculty_addresses (address, postal_code, "
                                                   + "country, email, city, state) "
                                                   + "VALUES(?, ?, ?, ?, ?, ?);");
       
        private static final String PHONE_QUERY = ("INSERT INTO faculty_phone_numbers (number, type, email) "
                                                 + "VALUES(?, ?, ?);");
        
        private static final String INFO_UPDATE = ("UPDATE faculty SET email = ?, last_name = ?, "
                                                 + "first_name = ?, department = ? "
                                                 + "WHERE email = ?");
        
        private static final String ADDRESS_UPDATE = "UPDATE faculty_addresses SET address = ?, postal_code = ?, "
                                                    + "country = ?, email = ?, city = ?, state = ? "
                                                    + "WHERE email = ?";
        
        private static final String PHONE_UPDATE = "UPDATE faculty_phone_numbers SET number = ?, type = ?, email = ? "
                                                  + "WHERE email = ?";
        
        private static final String EXISTS = "SELECT * "
                                           + "FROM (SELECT s.email "
                                           + "     FROM students AS s "
                                           + "     UNION ALL "
                                           + "     SELECT f.email "
                                           + "     FROM faculty AS f "
                                           + "     UNION ALL "
                                           + "     SELECT e.email "
                                           + "     FROM employees AS e) AS personnel "
                                           + "     WHERE personnel.email = ?;";
        
        private DataHandler handler_;
        private FacultyDialog fDialog;
        private String fMode;
        private PersonnelTable fTable;
        
   public FacultyListener(final PersonnelInputPanel aInputPanel, 
                         final FacultyDialog aDialog, 
                         final DataHandler aHandler) {
       super(aInputPanel, aDialog);
       this.fDialog = aDialog;
       this.handler_ = aHandler;
       this.fMode = aDialog.getMode();
    }
   
   public FacultyListener(final PersonnelInputPanel aInputPanel, 
                           final FacultyDialog aDialog, 
                           final DataHandler aHandler,
                           final PersonnelTable aTable) {
            this(aInputPanel, aDialog, aHandler);
            this.fTable = aTable;
            if(aTable == null)
                System.out.println("null");
}


        @Override public void actionPerformed(final ActionEvent ae) {
            boolean fieldsNotEmpty = true; 
            for(JTextField field : panel_.getTFList()) {
                final String contents = field.getText();
                if(contents.length() == 0) {
                    fieldsNotEmpty = false;
                    break;
                }               
            }
            
            final boolean valid = (fieldsNotEmpty && validInput());
            if(valid) {
                final String email = panel_.getEmail();
                Faculty faculty = null;
                String originalEmail = "";
                final HashMap<String, Faculty> map = handler_.getFacultyMap();
                FacultyTableModel model = null;
                try(final Connection conn = Utility.getConnection()) {
                    switch(fMode) {
                        case "ADD":
                                   final boolean exists = Utility.checkExists(EXISTS, email);
                                    if (exists) {
                                        Utility.showErrorMessage(dialog_, "Email Address is Already in Use, Please Enter Another.");
                                        return;
                                    } else {
                                        faculty = new Faculty();    
                                    }                                                                    
                            break;
                        case "EDIT":
                            final int row = fTable.getSelectedRow();
                            model = (FacultyTableModel)fTable.getModel();
                            faculty = model.getFaculty(row);
                            originalEmail = faculty.getEmail();
                            break;
                        }
                    
                    boolean success = (setInfo(faculty, conn, email) &&
                                       setPhone(faculty, email, conn) &&
                                       setAddress(faculty, email, conn));    
                     int reply = -1;
                     String modeConc = "";
                     switch(fMode) {
                         case "ADD":
                             modeConc = "Add";
                             break;
                         case "EDIT":
                             modeConc = "Edit";
                             break;
                         }
                     
                     if(success) {
                         Utility.showInfoMessage(dialog_, "Success " + modeConc + "ing");
                         switch(fMode) {
                             case "ADD":
                                 reply = JOptionPane.showConfirmDialog(dialog_, 
                                                                       "Success " + modeConc + " Another Employee?", 
                                                                       "Add", 
                                                                        JOptionPane.YES_NO_OPTION,
                                                                        JOptionPane.PLAIN_MESSAGE
                                                                       );
                                 break;
                             case "EDIT":
                                 if(!originalEmail.equals(email)) {
                                     map.remove(originalEmail);
                                     model.addKey(email);
                                     final int row = fTable.getSelectedRow();
                                     model.removeFaculty(row);
                                 }                                                 
                                 break;
                             }
                         map.put(email, faculty);
                         if(fMode.equals("EDIT"))
                              model.fireTableDataChanged();
                    } else {
                        reply = JOptionPane.showConfirmDialog(dialog_, 
                                "Error " + modeConc + "ing, Try Again?", 
                                "Error", 
                                 JOptionPane.YES_NO_OPTION,
                                 JOptionPane.ERROR_MESSAGE
                                 );
                     }                              
                     if(reply == JOptionPane.NO_OPTION || reply == -1)
                         dialog_.dispose();
                     return;
            } catch(final SQLException e1) {
                e1.printStackTrace();
                return;
            }
            } else {   
            Utility.showErrorMessage(dialog_, "All Fields Must be Entered");
            }
         }
       
      
        
        private final boolean setInfo(final Faculty aFaculty, 
                                   final Connection aConn,
                                   final String aEmail) {
            int count = -1;
            final String last = panel_.getLastName();
            final String first = panel_.getFirstName();
            final String department = fDialog.getDepartment();
            
            String sql = fMode.equals("ADD") ? INFO_QUERY :  INFO_UPDATE;
            try(final PreparedStatement pstmtInfo = aConn.prepareStatement(sql)) {
                pstmtInfo.setString(1, aEmail);
                pstmtInfo.setString(2, last);
                pstmtInfo.setString(3, first);
                pstmtInfo.setString(4, department);
                if(fMode.equals("EDIT")) {
                    String emailUpdate = aEmail;
                    final String originalEmail = aFaculty.getEmail();
                    if(!originalEmail.equals(aEmail)) 
                        emailUpdate = originalEmail;
                    pstmtInfo.setString(5, emailUpdate);
                }
                count = pstmtInfo.executeUpdate();
            } catch(final SQLException e) {
                e.printStackTrace();
            }  
            final boolean success = (count > 0);
            
            if(success) {
                aFaculty.setLastName(last);
                aFaculty.setFirstName(first);
                aFaculty.setEmail(aEmail);
                aFaculty.setDept(department);    
            }
            return success;
        }
        
        
        
        private final boolean setAddress(final Faculty aFaculty,
                                         final String aEmail,
                                         final Connection aConn) {
            int count = -1;
            final String city = panel_.getCity();
            final String state = panel_.getState();
            final String address = panel_.getAddress();
            final String zip = panel_.getZip();
            final String country = panel_.getCountry();
            
            final String sql = fMode.equals("ADD") ? ADDRESS_QUERY : ADDRESS_UPDATE;
            try(final PreparedStatement pstmtAddress = aConn.prepareStatement(sql)) {
                pstmtAddress.setString(1, address);
                pstmtAddress.setString(2, zip);
                pstmtAddress.setString(3, country);
                pstmtAddress.setString(4, aEmail);
                pstmtAddress.setString(5, city);
                pstmtAddress.setString(6, state);                        
                
                if(fMode.equals("EDIT")) {
                    String emailUpdate = aEmail;
                    final String originalEmail = aFaculty.getEmail();
                    if(!originalEmail.equals(aEmail)) 
                        emailUpdate = originalEmail;
                    pstmtAddress.setString(7, emailUpdate);
                }
                count = pstmtAddress.executeUpdate();
            } catch(final SQLException e) {
                e.printStackTrace();
            }
            final boolean success = (count > 0);
            
            if(success) {
                aFaculty.setAddress(address); 
                aFaculty.setZip(zip);
                aFaculty.setCity(city);
                aFaculty.setState(state);
                aFaculty.setCountry(country);
                aFaculty.setEmail(aEmail);
            }
            return success;
        }
                
        
        private final boolean setPhone(final Faculty aFaculty, 
                                       final String aEmail,
                                       final Connection aConn) {
            int count = -1;
            final String phone = panel_.getPhone();
            final String typePhone = panel_.getTypePhone();
            
            final String sql = fMode.equals("ADD") ? PHONE_QUERY : PHONE_UPDATE;
            try(final PreparedStatement pstmtPhone = aConn.prepareStatement(sql)) {
                pstmtPhone.setString(1, phone);
                pstmtPhone.setString(2, typePhone);
                pstmtPhone.setString(3, aEmail);
                if(fMode.equals("EDIT")) {
                    String emailUpdate = aEmail;
                    final String originalEmail = aFaculty.getEmail();
                    if(!originalEmail.equals(aEmail)) 
                        emailUpdate = originalEmail;
                    pstmtPhone.setString(4, emailUpdate);
                }
                count = pstmtPhone.executeUpdate();
            } catch(final SQLException e) {
                e.printStackTrace();
            }
            final boolean success = (count > 0);
            if(success) {
                aFaculty.setPhone(phone);
                aFaculty.setTypePhone(typePhone);
            }
            return success;
        }
}