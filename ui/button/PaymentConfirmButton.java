package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JFrame;

import listener.action.PaymentConfirmListener;
import ui.panel.PaymentInputPanel;


public final class PaymentConfirmButton extends JButton{
    private static final long serialVersionUID = 1L;
       
    public PaymentConfirmButton(final PaymentInputPanel aInputPanel, 
                                final DataHandler aHandler, 
                                final String aSigninEmail,
                                final JFrame aFrame) {
        super("Pay");
        addActionListener(new PaymentConfirmListener(aInputPanel, aHandler, aSigninEmail, aFrame));
    }
}