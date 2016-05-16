package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AbstractDocument;

import listener.action.SigninListener;
import listener.document.SigninDocumentFilter;
import listener.focus.CursorAtStartFocusListener;

import ui.frame.SplashScreen;

import utility.Utility;

public final class SigninDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final static String EMPLOYEE_TABLE_EXISTS = "SELECT * FROM EMPLOYEES;";
    private JTextField fSigninField;
    private JPasswordField fPasswordField;
    private JButton fSigninButton;
    
    public SigninDialog(final DataHandler aHandler) {
       super();
       setTitle("Library Sign In");
       add(buildSigninInfoPanel(), BorderLayout.WEST);
       add(buildButtonPanel(aHandler), BorderLayout.SOUTH);
       getRootPane().setDefaultButton(fSigninButton);
       setIconImage(Utility.getSmallImage());
       Utility.setCloseOperation(this);
       setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
    }
    
        private final JPanel buildSigninInfoPanel() {
            final JPanel p = new JPanel();
            p.setLayout(new GridBagLayout());
            final GridBagConstraints c1 = Utility.setConstraints(0, 0);
            p.add(buildEmailField(), c1);
            final GridBagConstraints c2 = Utility.setConstraints(0, 1);
            p.add(buildPasswordField(), c2);
            return p;
        }
    
        
        private final JTextField buildEmailField() {
            fSigninField = new JTextField(24);
            fSigninField.addFocusListener(new CursorAtStartFocusListener(fSigninField));
            final AbstractDocument emailDocument = ((AbstractDocument)fSigninField.getDocument());
            emailDocument.setDocumentFilter(new SigninDocumentFilter(fSigninField, "Email"));
            return fSigninField;
        }
        
        
        private final JTextField buildPasswordField() {
            fPasswordField = new JPasswordField(24);
            fPasswordField.addFocusListener(new CursorAtStartFocusListener(fPasswordField));
            final AbstractDocument passwordDocument = (AbstractDocument)fPasswordField.getDocument();
            passwordDocument.setDocumentFilter(new SigninDocumentFilter(fPasswordField, "Password"));
            return fPasswordField;
        }
        
        
        private final JPanel buildButtonPanel(final DataHandler aHandler) {
            final JPanel p = new JPanel();
            final SigninListener listener = new SigninListener(this, aHandler); 
            fSigninButton = Utility.createButton("OK", "OK", listener);
            p.add(fSigninButton);
            return p;
        }
    
        public final String getEmailInput() { return this.fSigninField.getText().trim(); }
        
            
        public final char[] getPasswordInput() { return this.fPasswordField.getPassword(); }

        
        public final static void main(final String args[])    {
            try     {
                  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                }   catch(UnsupportedLookAndFeelException e1)  {
                    e1.printStackTrace();
                    System.out.println("Unsupported Look And Feel");                
                }   catch(IllegalAccessException e2)   {
                    e2.printStackTrace();
                    System.out.println("Unsupported Look And Feel");
                }   catch(InstantiationException e3)    {
                    e3.printStackTrace();
                    System.out.println("Unsupported Look And Feel");
                }   catch(ClassNotFoundException e4)  {
                    e4.printStackTrace();
                    System.out.println("Unsupported Look And Feel");
                }            
            try(final Connection conn = Utility.getConnection();
                final PreparedStatement pstmt = conn.prepareStatement(EMPLOYEE_TABLE_EXISTS);
                final ResultSet rs = pstmt.executeQuery()) {
                final DataHandler handler = new DataHandler();
                final boolean notEmpty = rs.next();
                if(notEmpty) {
                    final Runnable thread = ( new Runnable() {
                        @Override public void run() {
                            new SigninDialog(handler); 
                        }           
                    });
                    javax.swing.SwingUtilities.invokeLater(thread);        
                } else {
                    Utility.showInfoMessage(null, "Please Create an Administrator Account.");
                    handler.setEmployeeMapStartup();
                    handler.setCountryList(conn);
                    handler.setStateList(conn);
                    handler.setCityList(conn);
                    final EmployeeDialog d = new EmployeeDialog(null, handler, true, true);
                    final String signinEmail = d.showDialog();
                    if(signinEmail.length() > 0)
                        new SplashScreen(handler, 1, signinEmail);                    
                }
            } catch(final SQLException e) {
                Utility.showErrorMessage(null, "Signin: Error Accessing Database.");
                e.printStackTrace();
            }
         }
}