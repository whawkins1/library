package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.CheckoutDialog;

public final class CheckoutListener implements  ActionListener{
    private final JFrame fFrame;
    private final String fSigninEmail;
    public CheckoutListener(final JFrame aFrame, final String aSigninEmail) {
        this.fFrame = aFrame;
        this.fSigninEmail = aSigninEmail;
    }
    
        @Override public void actionPerformed(final ActionEvent ae) {
              new CheckoutDialog(fFrame, fSigninEmail);
        }
}