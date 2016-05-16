package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.PaymentDialog;

public final class PaymentDisplayInputListener 
                          implements ActionListener {
    private final JFrame fFrame;
    private final DataHandler fHandler;
    private final String fSigninEmail;
    
    public PaymentDisplayInputListener(final JFrame aFrame,
                                       final String aSigninEmail,
                                  final DataHandler aHandler) {
        this.fFrame = aFrame;
        this.fHandler = aHandler;
        this.fSigninEmail = aSigninEmail;
    }
        
        @Override public final void actionPerformed(final ActionEvent e) {
            new PaymentDialog(fFrame, fHandler, fSigninEmail);
        }
}