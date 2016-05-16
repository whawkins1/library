package ui.dialog;


import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.panel.PaymentButtonPanel;
import ui.panel.PaymentInputPanel;
import utility.Utility;

public final class PaymentDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    public PaymentDialog(final JFrame aParent,
                         final DataHandler aHandler,
                         final String aSigninEmail) {
        super(aParent, "Payment", false);
        final PaymentInputPanel inputPanel = new PaymentInputPanel();
        final PaymentButtonPanel buttonPanel = new PaymentButtonPanel(inputPanel, aHandler, aSigninEmail, aParent);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        Utility.setCloseOperation(this);
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}