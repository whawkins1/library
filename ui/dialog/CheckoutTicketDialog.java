package ui.dialog;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JDialog;

import ui.panel.CheckoutTicketPanel;
import utility.Utility;

public final class CheckoutTicketDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public CheckoutTicketDialog(final int aCheckoutID, 
                                final String aCallNumber,
                                final Connection aConn,
                                final JDialog aParent) {
         super(aParent, "Checkout Ticket", true);
         add(new CheckoutTicketPanel(aCheckoutID, aCallNumber, aConn), BorderLayout.CENTER);
         Utility.setCloseOperation(this);
         pack();
         setLocationRelativeTo(aParent);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         setVisible(true);
     }
}
