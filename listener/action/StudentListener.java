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

import model.table.StudentsTableModel;

import Personnel.Student;

import ui.dialog.StudentDialog;
import ui.panel.PersonnelInputPanel;
import ui.table.PersonnelTable;

import utility.Utility;

public final class StudentListener extends PersonnelInput
                                           implements ActionListener {

    private static final String INFO_QUERY = ("INSERT INTO  students "
                                            + "(email, last, first, class_rank, major)"
                                            + "VALUES(?, ?, ?, ?, ?);");
    
    private static final String ADDRESS_QUERY = ("INSERT INTO student_addresses "
                                               + "(address, postal_code, city, state, country, email) "
                                               + "VALUES(?, ?, ?, ?, ?, ?);");
    
    private static final String PHONE_QUERY = ("INSERT INTO student_phone_numbers "
                                             + "(number, type, email)"
                                             + "VALUES(?, ?, ?);");
    
    private static final String INFO_UPDATE = "UPDATE students SET "
                                                + "email = ?, last = ?, "
                                                + "first = ?, "
                                                + "class_rank = ?, major = ? "
                                                + "WHERE email = ?";
    
    private static final String ADDRESS_UPDATE = "UPDATE student_addresses SET "
                                                + "address = ?, postal_code = ?, "
                                                + "city = ?, state = ?, "
                                                + "country = ?, email = ? "
                                                + "WHERE email = ?";
    
    private static final String PHONE_UPDATE = "UPDATE student_phone_numbers SET "
                                                + "number = ?, type = ?, email = ? "
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
    private String fMode;
    private PersonnelTable fTable;
    
    public StudentListener(final PersonnelInputPanel aInputPanel, 
                              final StudentDialog aDialog,
                              final DataHandler aHandler) {
        super(aInputPanel, aDialog);
        this.fMode = aDialog.getMode();
        this.handler_ = aHandler;
    }
    
    public StudentListener(final PersonnelInputPanel aInputPanel, 
                            final StudentDialog aDialog, 
                            final DataHandler aHandler,
                            final PersonnelTable aTable) {
            this(aInputPanel, aDialog, aHandler);
            this.fTable = aTable;
    }
    
    
    
        @Override public final void actionPerformed(final ActionEvent ae) {
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
                Student student = null;
                String originalEmail = "";
                final HashMap<String, Student> map = handler_.getStudentMap();
                StudentsTableModel model = null;
                try(final Connection conn = Utility.getConnection()) {
                    switch(fMode) {
                        case "ADD":
                                   final boolean exists = Utility.checkExists(EXISTS, email);
                                    if (exists) {
                                        Utility.showErrorMessage(dialog_, "Email Address is Already in Use, Please Enter Another.");
                                    } else {
                                        student = new Student();
                                    }
                            break;
                        case "EDIT":
                            final int row = fTable.getSelectedRow();
                            model = (StudentsTableModel)fTable.getModel();
                            student = model.getStudent(row);
                            originalEmail = student.getEmail();
                            break;
                        }
                    
                    boolean success = (setInfo(student, conn, email) &&
                                       setAddress(student, email, conn) &&                        
                                       setPhone(student, email, conn));    
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
                                                                       "Success " + modeConc + " Another Student?", 
                                                                       "Add", 
                                                                        JOptionPane.YES_NO_OPTION,
                                                                        JOptionPane.PLAIN_MESSAGE
                                                                       );
                                 break;
                             case "EDIT":
                                 if(!originalEmail.equals(email)) {
                                     map.remove(originalEmail);
                                     model.setKey(email);
                                     final int row = fTable.getSelectedRow();
                                     model.removeStudent(row);
                                 }                                                 
                                 break;
                             }
                         map.put(email, student);
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
                } catch(final SQLException e1) {
                    e1.printStackTrace();
                    return;
                }
            } else {   
            Utility.showErrorMessage(dialog_, "All Fields Must be Entered");
            }
     }
         
         private final boolean setInfo(final Student aStudent, 
                                    final Connection aConn,
                                    final String aEmail) {
             int count = -1;
             final String last = panel_.getLastName();
             final String first = panel_.getFirstName();
             final StudentDialog d = (StudentDialog)dialog_;
             final String classRank = d.getClassRank();
             final String major = d.getMajor();
             final
             
             String sql = fMode.equals("ADD") ? INFO_QUERY :  INFO_UPDATE;
             try(final PreparedStatement pstmtInfo = aConn.prepareStatement(sql)) {
                 pstmtInfo.setString(1, aEmail);
                 pstmtInfo.setString(2, last);
                 pstmtInfo.setString(3, first);
                 pstmtInfo.setInt(4, Integer.parseInt(classRank));
                 pstmtInfo.setString(5, major);
                 
                 if(fMode.equals("EDIT")) {
                     String emailUpdate = aEmail;
                     final String originalEmail = aStudent.getEmail();
                     if(!originalEmail.equals(aEmail)) 
                         emailUpdate = originalEmail;
                     pstmtInfo.setString(6, emailUpdate);
                 }
                 count = pstmtInfo.executeUpdate();
             } catch(final SQLException e) {
                 e.printStackTrace();
             }  
             final boolean success = (count > 0);
             
             if(success) {
                 aStudent.setLastName(last);
                 aStudent.setFirstName(first);
                 aStudent.setEmail(aEmail);
                 aStudent.setMajor(major);
                 aStudent.setClassRank(Integer.parseInt(classRank));
             }
             return success;
         }
        
         
         
        private final boolean setAddress(final Student aStudent, 
                                         final String aEmail,
                                         final Connection aConn) {
            int count = -1;
            final String city = panel_.getCity();
            final String state = panel_.getState();
            final String address = panel_.getAddress();
            final String zip = panel_.getZip();
            final String country = panel_.getCountry();
            final String email = panel_.getEmail();
            
            final String sql = fMode.equals("ADD") ? ADDRESS_QUERY :  ADDRESS_UPDATE;
            try(final PreparedStatement pstmtAddress = aConn.prepareStatement(sql)) {
                  pstmtAddress.setString(1, address);
                  pstmtAddress.setString(2, zip);
                  pstmtAddress.setString(3, city);
                  pstmtAddress.setString(4, state);
                  pstmtAddress.setString(5, country);
                  pstmtAddress.setString(6, email);
                  if(fMode.equals("EDIT")) {
                      String emailUpdate = aEmail;
                      final String originalEmail = aStudent.getEmail();
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
                aStudent.setAddress(address); 
                aStudent.setZip(zip);
                aStudent.setCity(city);
                aStudent.setState(state);
                aStudent.setCountry(country);
            }            
            return success;
         }

        
        
        private final boolean setPhone(final Student aStudent, 
                                       final String aEmail,
                                       final Connection aConn) {
             int count = 0;
             final String phone = panel_.getPhone();
             final String typePhone = panel_.getTypePhone();
             final String email = panel_.getEmail();
             final String sql = fMode.equals("ADD") ? PHONE_QUERY :  PHONE_UPDATE;
            try(final PreparedStatement pstmtPhone = aConn.prepareStatement(sql)) {
                pstmtPhone.setString(1, phone);
                pstmtPhone.setString(2, typePhone);
                pstmtPhone.setString(3, email);
                if(fMode.equals("EDIT")) {
                    String emailUpdate = aEmail;
                    final String originalEmail = aStudent.getEmail();
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
                aStudent.setPhone(phone);
                aStudent.setTypePhone(typePhone);
                aStudent.setEmail(aEmail);
            }
            return success;
        }
}