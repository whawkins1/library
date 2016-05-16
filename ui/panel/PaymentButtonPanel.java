package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.button.PaymentConfirmButton;

public final class PaymentButtonPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    public PaymentButtonPanel(final PaymentInputPanel aInputPanel, 
                              final DataHandler aHandler,
                              final String aSigninEmail,
                              final JFrame aFrame) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new PaymentConfirmButton(aInputPanel, aHandler, aSigninEmail, aFrame));
    }
}