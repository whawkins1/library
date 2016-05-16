package ui.dialog;

import java.sql.Connection;

import javax.swing.JDialog;

import ui.panel.ReturnTicketPanel;
import utility.Utility;

public final class ReturnTicketDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    public ReturnTicketDialog(final int aCheckoutID, 
                              final Connection aConn, 
                             final JDialog aParent) {
        super(aParent, "Return Ticket", false);
        Utility.setCloseOperation(this);
        add(new ReturnTicketPanel(aCheckoutID, aConn));
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}