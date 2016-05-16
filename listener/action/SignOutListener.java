package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.SigninDialog;
import utility.Utility;

public final class SignOutListener 
                      implements ActionListener {
    private final JFrame fFrame;
    @SuppressWarnings("unused")
    private DataHandler handler_;
    private final String fEmail;
    
    public SignOutListener(final JFrame aFrame, 
                           final String aEmail, 
                           final DataHandler aHandler) {
        this.fFrame = aFrame;
        this.fEmail = aEmail;
        this.handler_ = aHandler;        
    }
    
        @Override public final void actionPerformed(final ActionEvent e) {
            final boolean success = Utility.processSignOff(fEmail);
            if(success) {
                fFrame.dispose();
                handler_ = null;
                new SigninDialog(new DataHandler());
                return;
            } 
            Utility.showErrorMessage(fFrame, "Error Signing Off");
        }
}