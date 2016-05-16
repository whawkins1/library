package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import ui.dialog.DisplayPaySummaryDialog;

import ui.panel.PaymentInputPanel;

import utility.Utility;

public final class PaymentConfirmListener 
                             implements ActionListener {
    private final static String CHECK_FINE_DUE = "SELECT p.fine FROM "
                                               + " ( SELECT s.fine, s.email "
                                               + "   FROM students AS s "
                                               + " UNION ALL "
                                               + "SELECT f.fine, f.email "
                                               + "FROM faculty AS f) AS p "
                                               + "WHERE p.email = (?);";
    
    private final static String PAY_FINE_PROC = "{CALL PayFine(?,?,?,?,?)}";
    
    private final PaymentInputPanel fInputPanel;
    private final String fSigninEmail;
    private final DataHandler fHandler;
    private final JFrame fParent;
    private BigDecimal fPayment;
    private BigDecimal fBalance;
    private BigDecimal fChange;
    private String fStudentEmail;
    
    public PaymentConfirmListener(final PaymentInputPanel aInputPanel, 
                                  final DataHandler aHandler, 
                                  final String aSigninEmail,
                                  final JFrame aParent) {
        this.fInputPanel = aInputPanel;
        this.fSigninEmail = aSigninEmail;
        this.fHandler = aHandler;
        this.fParent = aParent;
    }
    
        @Override public final void actionPerformed(final ActionEvent evt) {
            fStudentEmail = (fInputPanel.getEmailInput());
            fPayment = (fInputPanel.getPaymentInput());
            if(Utility.validEmail(fStudentEmail)) {
                final Pattern patternCurrency = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
                final Matcher matcher = patternCurrency.matcher(fPayment.toString());
                final boolean validCurrency = matcher.matches();
                if(validCurrency) {
                    try(final Connection conn = Utility.getConnection()) {
                        final boolean fineDue = isFineDue(conn);
                        if(fineDue) {
                            insertPayment(conn);
                            return;   
                        }
                        Utility.showInfoMessage(null, "No Fine Due, Have a Nice Day.");
                        return;
                    } catch(final SQLException ex) {
                        ex.printStackTrace();
                    }
                    
                }
                Utility.showErrorMessage(null, "Invalid Currency Format Must Have One or Two Numbers Right Of The Decimal");
                return;
            } 
            Utility.showErrorMessage(null, "Invalid Email Address, Please Try Again.");
        }
        
        
        private final boolean isFineDue(final Connection aConn) {
            BigDecimal fine = new BigDecimal(0.00);
            try(final PreparedStatement pstmt = aConn.prepareStatement(CHECK_FINE_DUE)) {
                pstmt.setString(1, fStudentEmail);
                final ResultSet rs = pstmt.executeQuery();
                if(rs.next())
                    fine = rs.getBigDecimal(1);
            } catch(final SQLException e) {
                e.printStackTrace();
                Utility.showErrorMessage(null, "Error Checking Fine is Due");
            }
            final boolean isDue = (fine.compareTo(new BigDecimal(0.00))) == 1 ? true : false; 
            return isDue;
        }
        
        
        private final void insertPayment(final Connection aConn) {
            try {
                final CallableStatement callableStatement = aConn.prepareCall(PAY_FINE_PROC);     
                callableStatement.setString(1, fStudentEmail);
                callableStatement.setString(2, fSigninEmail);
                callableStatement.setBigDecimal(3, fPayment);
                callableStatement.registerOutParameter(4, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(5, java.sql.Types.DECIMAL);
                
                final int nRowsAffected = callableStatement.executeUpdate();
                
                final boolean success = (nRowsAffected != 0);
                if(success) {
                    fBalance = callableStatement.getBigDecimal(3);
                    fChange = callableStatement.getBigDecimal(4);
                    new DisplayPaySummaryDialog(this, fHandler, fSigninEmail, fParent);
                }
            } catch(final SQLException e) {
                e.printStackTrace();
                Utility.showErrorMessage(null, "Error Calling Procedure PayFine.");
            }
        }
        
        
        public final String getStudentEmail() {
            return this.fStudentEmail;
        }
        
        
        public final String getPayment() {
            return this.fPayment.toString();
        }
        
        
        public final String getBalance() {
            return this.fBalance.toString();
        }

        
        public final String getChange() {
            return this.fChange.toString();
        }
}