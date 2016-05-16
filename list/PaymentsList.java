package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import payment.Payment;
import utility.Utility;

public final class PaymentsList extends ArrayList<Payment> {
    private static final long serialVersionUID = 1L;
    
    private static final String SELECT_PAYMENTS = "SELECT personnel.last_name, personnel.first_name, payments.email, payments.payment_date, payments.payment "
                                                + "FROM "
                                                + "(SELECT last AS last_name, first AS first_name, email "
                                                + "   FROM students "
                                                + " UNION ALL SELECT last_name, first_name, email "
                                                + "  FROM faculty) as personnel "
                                                + "INNER JOIN payments ON payments.email = personnel.email;";
                                                    
    public PaymentsList(final Connection aConn) {
            try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_PAYMENTS);
                final ResultSet rs = pstmt.executeQuery() ) {
                while(rs.next()) {
                    final Payment payment = new Payment();
                    payment.setFirstName("first_name");
                    payment.setLastName("last_name");
                    payment.setPayment((rs.getBigDecimal("payment")).toString());
                    payment.setEmail(rs.getString("email"));
                    payment.setDate((rs.getDate("date_added").toString()));
                    add(payment);
               }
            } catch(final SQLException e) {
                Utility.showErrorMessage(null, "Error Loading Payments Data!");
                e.printStackTrace();
            }
       }
}