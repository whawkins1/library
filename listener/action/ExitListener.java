package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import utility.Utility;

public final class ExitListener implements ActionListener{
   private final JFrame frame_;
   private final String fEmail;
    
   public ExitListener(final JFrame aFrame, final String aEmail) {
       this.frame_ = aFrame;
       this.fEmail = aEmail;
   }
    
        @Override public final void actionPerformed(final ActionEvent e) {
            final boolean success = Utility.processSignOff(fEmail);
            if(success) {
                frame_.dispose();
                System.exit(0);
            } 
            Utility.showErrorMessage(frame_, "Error Exiting");
        }
}