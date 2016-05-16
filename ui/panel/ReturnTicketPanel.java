package ui.panel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JPanel;

import utility.Utility;

public class ReturnTicketPanel extends JPanel {
    private final static String GET_CALL_NUMBER = "SELECT ci.call_number "
                                                + "FROM checkout_inventory AS ci "
                                                + "WHERE ci.checkout_id = (?);";
    
    private final static String GET_DAYS_OVERDUE = "{ ? = call GetDaysOverdue(?) }";
    
    private final static String GET_FINE = "SELECT GetFine(?);";
    
    private static final long serialVersionUID = 1L;

    public ReturnTicketPanel(final int aCheckoutID, final Connection aConn) {
       super(new GridBagLayout());
       setBackground(Color.white);
       add(Utility.createLabelTitle("Checkout ID: "), Utility.setConstraints(0, 0));
       final String checkoutID = ("CHKO" + String.valueOf(aCheckoutID));
       add(Utility.createLabelTitle(checkoutID), Utility.setConstraints(1, 0));
       add(Utility.createLabelTitle("Title: "), Utility.setConstraints(0, 1));
       final String callNumber = getCallNumber(aCheckoutID, aConn);
       add(Utility.createLabelTitle(Utility.getTitle(callNumber, aConn)), Utility.setConstraints(1, 1));
       add(Utility.createLabelTitle("Return Date: "), Utility.setConstraints(0, 2));
       final String returnDate = Utility.joinDates(LocalDate.now());
       add(Utility.createLabelTitle(returnDate), Utility.setConstraints(1, 2));
       add(Utility.createLabelTitle("Days Overdue: "), Utility.setConstraints(0, 3));
       final int daysOverdue = getDaysOverdue(aCheckoutID, aConn);
       add(Utility.createLabelTitle(String.valueOf(daysOverdue)), Utility.setConstraints(1, 3));
       add(Utility.createLabelTitle("Fine Due: "), Utility.setConstraints(0, 4));
       final BigDecimal fine = getFine(daysOverdue, aConn);
       add(Utility.createLabelTitle(fine.toString()), Utility.setConstraints(1, 4));
    }
    
    
        private final String getCallNumber(final int aCheckoutID, final Connection aConn) {
                String call_number = "";
                try(final PreparedStatement pstmt = aConn.prepareStatement(GET_CALL_NUMBER)) {
                    pstmt.setInt(1, aCheckoutID);
                    final ResultSet rs = pstmt.executeQuery();
                    while(rs.next())
                        call_number = rs.getString(1);
                } catch(final SQLException e) {
                    e.printStackTrace();
                }
                return call_number;
            }
        
        
        private final int getDaysOverdue(final int aCheckoutID, final Connection aConn) {
            int daysOverDue = 0;
            try(final CallableStatement stmt = aConn.prepareCall(GET_DAYS_OVERDUE)) {
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setInt(2, aCheckoutID);
                stmt.execute();
                daysOverDue = stmt.getInt(1);
            } catch(final SQLException e) {
                e.printStackTrace();
            }
            return daysOverDue;
        }
        
        private final BigDecimal getFine(final int aDaysOverdue, final Connection aConn) {
           BigDecimal fine = null;
           try(final PreparedStatement stmt = aConn.prepareStatement(GET_FINE)) {
               stmt.setInt(1, aDaysOverdue);
               final ResultSet rs = stmt.executeQuery();
               if(rs.next())
                  fine = rs.getBigDecimal(1);
           } catch(final SQLException e) {
               e.printStackTrace();
           }
           return fine;
        }
}