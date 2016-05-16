package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import listener.action.PaymentConfirmListener;

import ui.panel.PaySummaryPanel;

import utility.Utility;

public final class DisplayPaySummaryDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    public DisplayPaySummaryDialog(final PaymentConfirmListener aPayConfirmListener,
                                   final DataHandler aHandler,
                                   final String aEmployeeEmail,
                                   final JFrame aParent) {
        super(aParent, "Payment Summary", false);
        final PaySummaryPanel panel = new PaySummaryPanel(aPayConfirmListener, aHandler, aEmployeeEmail, aParent); 
        add(panel, BorderLayout.CENTER);
        Utility.setCloseOperation(this);
        setIconImage(Utility.getSmallImage());
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}