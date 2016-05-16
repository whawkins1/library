package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Arrays;

import ui.dialog.SigninDialog;
import ui.frame.SplashScreen;

import utility.Utility;

public final class SigninListener 
                          implements ActionListener {
    private final static String GET_EMPLOYEE = "SELECT is_admin, password "
                                               + "FROM employees "
                                               + "WHERE email = (?);";
    
    private final static String INSERT_EMPLOYEE_LOGS = "INSERT INTO employee_logs (email, activity) "
                                                     + "VALUES(?, ?);";
    
    private final SigninDialog dialog_;
    private final DataHandler handler_;
    
    public SigninListener(final SigninDialog aDialog, 
                          final DataHandler aHandler) {
        this.dialog_ = aDialog;
        this.handler_ = aHandler;
    }
    

    @Override public void actionPerformed(final ActionEvent e) {
            ResultSet rs = null; 
            try(final Connection conn = Utility.getConnection(); 
                final PreparedStatement pstmt = conn.prepareStatement(GET_EMPLOYEE)) {
                final String email = dialog_.getEmailInput();
                final char[] passCharArr = dialog_.getPasswordInput();
                final boolean validEmail = ((email.length() > 0) &&  
                                              (!(Utility.containsWhiteSpace(email))) &&
                                              (!(email.equals("Email"))));
                final String password = String.valueOf(passCharArr);
                Arrays.fill(passCharArr, '0');
                final boolean validPassword = ((password.length()) > 0 && 
                                              (!(password.equals("Password"))));
                
                if(validEmail && validPassword) {                    
                    pstmt.setString(1, email);
                    rs = pstmt.executeQuery();
                    if(rs.next()) {
                        final String passStored = rs.getString("password");
                        final boolean validPass = Utility.validatePassword(password, passStored);
                        if(validPass) {
                            final int isAdmin = rs.getInt(1);
                            try(final PreparedStatement pstmt2 = conn.prepareStatement(INSERT_EMPLOYEE_LOGS)) {
                                pstmt2.setString(1, email);
                                pstmt2.setString(2, "SIGN IN");
                                pstmt2.executeUpdate();
                            }
                            Utility.showInfoMessage(dialog_, "You are Signed in as " + email);
                            if(rs != null)
                                rs.close();
                            dialog_.dispose();                            
                            new SplashScreen(handler_, isAdmin, email);                      
                            return;
                        }
                    } 
                    Utility.showErrorMessage(dialog_, "Invalid Signin, Please Try Again.");
                    return;
                } 
                Utility.showErrorMessage(dialog_, "Username and Password must be entered!");
            } catch(final SQLException e1) {
                    Utility.showErrorMessage(dialog_, "Error Signing in, Please Try Again.");
                    e1.printStackTrace();
            } 
        }
 }