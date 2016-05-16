package listener.window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import utility.Utility;

public final class SignOffWindowListener extends WindowAdapter {
    private final String fEmail;
    private final JFrame fFrame;
    public SignOffWindowListener(final String aEmail, final JFrame aFrame) {
      this.fEmail = aEmail;
      this.fFrame = aFrame;
    }
    
    public final void WindowClosing(final WindowEvent e) {
        final boolean success = Utility.processSignOff(fEmail);
        if(success) {
            fFrame.dispose();
            System.exit(0);
        }
        Utility.showErrorMessage(fFrame, "Error Signing Off");  
    }
}