package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.table.EmployeesTableModel;
import Personnel.Employee;
import ui.dialog.EmployeeDialog;
import ui.panel.HireDatePanel;
import ui.panel.PersonnelInputPanel;
import ui.table.PersonnelTable;
import utility.Utility;


public final class EmployeeListener extends PersonnelInput  
                                            implements ActionListener {
    private static final String INFO_INSERT = "INSERT INTO  employees (email, last_name, "
                                            + " first_name, is_admin, password, date_of_hire)"
                                            + "VALUES(?, ?, ?, ?, ?, ?);";
    
    private static final String ADDRESS_INSERT = "INSERT INTO employee_addresses"
                                               +  "(address, postal_code, city, state, country, email)"
                                               +  "VALUES(?, ?, ?, ?, ?, ?);";
    
    private static final String PHONE_INSERT = "INSERT INTO employee_phone_numbers"
                                              + "(number, type, email)"
                                              + "VALUES(?, ?, ?);";
    
    private static final String INFO_UPDATE = "UPDATE employees SET email = ?, last_name = ?, "
                                             + "first_name = ?, is_admin = ? "
                                             + " WHERE email = ?"; 
    
    private static final String ADDRESS_UPDATE = "UPDATE employee_addresses SET address = ?, postal_code = ?, "
                                               + " city = ?, state = ?, "
                                               + "country = ?, email = ? "
                                               + "WHERE email = ?";
    
    private static final String PHONE_UPDATE = "UPDATE employee_phone_numbers SET"
                                                + " number = ?, type = ?, email = ? "
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
    private EmployeeDialog dialog_;
    private final String fMode;
    private JTable fTable;
    
    public EmployeeListener(final PersonnelInputPanel aInputPanel, 
                            final EmployeeDialog aDialog, 
                            final DataHandler aHandler) {
        super(aInputPanel, aDialog);
        this.handler_ = aHandler;
        this.fMode = aDialog.getMode();
        this.dialog_ = aDialog;
    }
    
    public EmployeeListener(final PersonnelInputPanel aInputPanel, 
            final EmployeeDialog aDialog, 
            final DataHandler aHandler,
            final PersonnelTable aTable) {
            this(aInputPanel, aDialog, aHandler);
            this.fTable = aTable;
    }
         

        @Override public final void actionPerformed(final ActionEvent e) {                
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
                String passHash = "";
                final String email = panel_.getEmail();
                Employee employee = null;
                String originalEmail = "";
                final HashMap<String, Employee> map = handler_.getEmployeeMap();
                EmployeesTableModel model = null;
                try(final Connection conn = Utility.getConnection()) {
                    switch(fMode) {
                        case "ADD":
                            final String password = dialog_.getPassword();
                            final String confirmPassword = dialog_.getConfirmPass();
                            if(password.equals(confirmPassword)) {
                                passHash = Utility.generateHash(password);
                                   final boolean exists = Utility.checkExists(EXISTS, email);
                                    if (exists) {
                                        Utility.showErrorMessage(dialog_, "Email Address is Already in Use, Please Enter Another.");
                                        return;
                                    }
                                    employee = new Employee();                                
                            } else {
                                Utility.showErrorMessage(dialog_, "The Password and Confirm Input Must be the same, Please try again.");  
                            }
                            break;
                        case "EDIT":
                            final int row = fTable.getSelectedRow();
                            model = (EmployeesTableModel)fTable.getModel();
                            employee = model.getEmployee(row);
                            originalEmail = employee.getEmail();
                            break;
                        }
                    
                    boolean success = (setInfo(employee, conn, email, passHash) &&
                                       setAddress(employee, email, conn) &&                        
                                       setPhone(employee, email, conn));    
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
                                 dialog_.setSigninEmail(employee.getEmail());
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
                                     model.removeEmployee(row);
                                 }                                                 
                                 break;
                             }
                         map.put(email, employee);
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
        
    
        private final boolean setInfo(final Employee aEmployee, 
                                   final Connection aConn,
                                   final String aEmail,
                                   final String aPassHash) {
            int count = -1;
            final String last = panel_.getLastName();
            final String first = panel_.getFirstName();
            final int isAdmin = ((EmployeeDialog) dialog_).isAdmin(); 
            final String hireDate = HireDatePanel.getHireDate();
            final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            java.sql.Date sqlDate = null;
            try {
                java.util.Date date = format.parse(hireDate);
                sqlDate = new java.sql.Date(date.getTime());
            } catch(final ParseException e) {
                Utility.showErrorMessage(dialog_, "Error Parsing Hire Date");
                e.printStackTrace();
            }   
            
            String sql = fMode.equals("ADD") ? INFO_INSERT :  INFO_UPDATE;
            try(final PreparedStatement pstmtInfo = aConn.prepareStatement(sql)) {
                pstmtInfo.setString(1, aEmail);
                pstmtInfo.setString(2, last);
                pstmtInfo.setString(3, first);
                pstmtInfo.setInt(4, isAdmin);
                if(fMode.equals("ADD")) {
                    pstmtInfo.setString(5, aPassHash);    
                    pstmtInfo.setDate(6, sqlDate);
                } else {
                    String emailUpdate = aEmail;
                    final String originalEmail = aEmployee.getEmail();
                    if(!originalEmail.equals(aEmail)) {
                        emailUpdate = originalEmail;
                    } 
                    pstmtInfo.setString(5, emailUpdate);
                }
                count = pstmtInfo.executeUpdate();
            } catch(final SQLException e) {
                e.printStackTrace();
            }  
            final boolean success = (count > 0);
            
            if(success) {
                aEmployee.setLastName(last);
                aEmployee.setFirstName(first);
                aEmployee.setEmail(aEmail);
                aEmployee.setIsAdmin(isAdmin);
                aEmployee.setHireDate(hireDate);    
            }
            return success;
        }
        
    
        private final boolean setAddress(final Employee aEmployee, 
                                         final String aEmail,
                                         final Connection aConn) {
            int count = -1;
            final String city = panel_.getCity();
            final String state = panel_.getState();
            final String address = panel_.getAddress();
            final String zip = panel_.getZip();
            final String country = panel_.getCountry();
            
            final String sql = fMode.equals("ADD") ? ADDRESS_INSERT : ADDRESS_UPDATE;
            try(final PreparedStatement pstmtAddress = aConn.prepareStatement(sql)) {
                pstmtAddress.setString(1, address);
                pstmtAddress.setString(2, zip);
                pstmtAddress.setString(3, city);
                pstmtAddress.setString(4, state);
                pstmtAddress.setString(5, country);        
                pstmtAddress.setString(6, aEmail);
                if(fMode.equals("EDIT")) {
                    String emailUpdate = aEmail;
                    final String originalEmail = aEmployee.getEmail();
                    if(!originalEmail.equals(aEmail)) {
                        emailUpdate = originalEmail;
                    } 
                    pstmtAddress.setString(7, emailUpdate);
                }
                count = pstmtAddress.executeUpdate();
            } catch(final SQLException e) {
                e.printStackTrace();
            }
            final boolean success = (count > 0);
            
            if(success) {
                aEmployee.setAddress(address); 
                aEmployee.setZip(zip);
                aEmployee.setCity(city);
                aEmployee.setState(state);
                aEmployee.setCountry(country);
                aEmployee.setEmail(aEmail);
            }
            return success;
        }
        
    
        private final boolean setPhone(final Employee aEmployee, 
                                       final String aEmail,
                                       final Connection aConn) {
            int count = -1;
            final String phone = panel_.getPhone();
            final String typePhone = panel_.getTypePhone();
            
            final String sql = fMode.equals("ADD") ? PHONE_INSERT : PHONE_UPDATE;
            try(final PreparedStatement pstmtPhone = aConn.prepareStatement(sql)) {
                pstmtPhone.setString(1, phone);
                pstmtPhone.setString(2, typePhone);
                pstmtPhone.setString(3, aEmail);
                if(fMode.equals("EDIT")) {
                    String emailUpdate = aEmail;
                    final String originalEmail = aEmployee.getEmail();
                    if(!originalEmail.equals(aEmail)) {
                        emailUpdate = originalEmail;
                    } 
                    pstmtPhone.setString(4, emailUpdate);
                }
                count = pstmtPhone.executeUpdate();
            } catch(final SQLException e) {
                e.printStackTrace();
            }
            final boolean success = (count > 0);
            if(success) {
                aEmployee.setPhone(phone);
                aEmployee.setTypePhone(typePhone);
            }
            return success;
        }
}