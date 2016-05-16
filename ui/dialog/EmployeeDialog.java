package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Personnel.Employee;

import ui.panel.EmployeeButtonPanel;
import ui.panel.HireDatePanel;
import ui.table.PersonnelTable;

import utility.Utility;


public final class EmployeeDialog extends PersonnelAccessDialog {
    private static final long serialVersionUID = 1L;
    private JCheckBox adminCheckBox_; 
    private JCheckBox resetLoginCKB_;
    private JPasswordField passwordField_;
    private JPasswordField confirmPassField_;
    private boolean fIsStartupAccount = false;
    private String fSigninEmail;
    
      public EmployeeDialog (final JFrame aParent, 
                             final DataHandler aHandler,
                             final boolean aModal, 
                             final boolean aStartupAccount) {
          super(aHandler, "ADD");
          this.fIsStartupAccount = aStartupAccount;
          setTitle("Add Employee");
          fSigninEmail = "";
          buildPanel(null);      
          if(aModal) {
              adminCheckBox_.setSelected(true);
              adminCheckBox_.setEnabled(false);
          }
          add(new EmployeeButtonPanel(inputPanel_, this, aHandler), BorderLayout.SOUTH);
          setDialogProperties(aParent, aModal);
      }
      
      public EmployeeDialog(final Employee aEmployee, 
                                 final JFrame aParent,
                                 final DataHandler aHandler,
                                 final PersonnelTable aTable ) {
          super (aEmployee, aParent, aHandler, "EDIT") ;
          setTitle("Edit Faculty");
          buildPanel(aEmployee);
          adminCheckBox_.setSelected(aEmployee.isAdmin() != 0);
          add (new EmployeeButtonPanel(inputPanel_, this, aHandler, aTable), BorderLayout.SOUTH) ; 
          setDialogProperties(aParent);
      }
    
      public EmployeeDialog(final Employee aEmployee, final JFrame aParent) {
          super(aEmployee, aParent, null, "VIEW");
          String title = "View Account ";
          if(aEmployee.isAdmin() == 1)
              title = title + " <ADMIN> ";
          setTitle(title);
          buildPanel(aEmployee);
          inputPanel_.setTFNotEditable();
          setDialogProperties(aParent);
      }
      
      
          public final void buildPanel(final Employee aEmployee) {   
              final GridBagConstraints c19 = Utility.setConstraints(1, 9);
              final JLabel hireDateLabel = Utility.createLabelAsterick(mode_, "Hire Date");
              inputPanel_.add(hireDateLabel, Utility.setConstraints(0, 12));
              final GridBagConstraints c21 = Utility.setConstraints(1, 12);
              final GridBagConstraints c22 = Utility.setConstraints(0, 13);
              final GridBagConstraints c23 = Utility.setConstraints(1, 13);
              
              if(mode_.equals("ADD")) {
                  inputPanel_.add(new HireDatePanel(null), c21);
                  final JLabel passwordLabel = new JLabel("Password: * ");
                  inputPanel_.add(passwordLabel, c22);
                  passwordField_ = new JPasswordField(20);
                  inputPanel_.add(passwordField_, c23);
                  final JLabel confirmPassLabel = new JLabel("Confirm: * ");
                  inputPanel_.add(confirmPassLabel, Utility.setConstraints(0, 14));
                  confirmPassField_ = new JPasswordField(20);
                  inputPanel_.add(confirmPassField_, Utility.setConstraints(1, 14));
              }
              
              if(mode_.equals("ADD") || mode_.equals("EDIT")) {          
                  adminCheckBox_ = new JCheckBox("Admin");
                  if(aEmployee != null) {
                      if(aEmployee.isAdmin() == 1)
                          adminCheckBox_.setSelected(true);
                  }
                  inputPanel_.add(adminCheckBox_, c19);
              } 
              
              
              String date = "";
              if(mode_.equals("EDIT") || mode_.equals("VIEW")) {
                  inputPanel_.setPhoneType(aEmployee.getTypePhone());
                  date = aEmployee.getHireDate();
              }
              
              if(mode_.equals("EDIT")) {
                  inputPanel_.add(new HireDatePanel(date), c21);
                  resetLoginCKB_ = new JCheckBox("Reset Password");
                  inputPanel_.add(resetLoginCKB_, c23);
                  
              }              
              
              if(mode_.equals("VIEW")) {
                  final JTextField hireDateTF = new JTextField(date);
                  hireDateTF.setBackground(Color.WHITE);
                  inputPanel_.addTF(hireDateTF);
                  inputPanel_.add(hireDateTF, c21);
              }              
              add(inputPanel_, BorderLayout.CENTER);
          }
          
          
          public final String showDialog() {return this.fSigninEmail;}
      
          
          public final void setSigninEmail(final String aEmail) {this.fSigninEmail = aEmail;}

          
          public final boolean isStartupAccount() {return this.fIsStartupAccount;}
          
          
          public final int isAdmin() { return this.adminCheckBox_.isSelected() ? 1 : 0; }
          
          
          public final String getPassword() { return convertPassword(this.passwordField_);}
          
          
          public final String getConfirmPass() {return convertPassword(this.confirmPassField_);}
          
          
          private final String convertPassword(final JPasswordField aField) {
              final char[] passChar = aField.getPassword();
              final String passConverted = String.valueOf(passChar);
              Arrays.fill(passChar, '0');
              return passConverted.trim();
          }
          
          public final boolean resetLogin() { return this.resetLoginCKB_.isSelected(); }  
}