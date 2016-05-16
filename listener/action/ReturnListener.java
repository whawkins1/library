package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.ReturnDialog;

public final class ReturnListener implements ActionListener {
   private final JFrame fFrame;
    public ReturnListener(final JFrame aFrame) {
       this.fFrame = aFrame;
       
   }

@Override
public void actionPerformed(final ActionEvent ae) {
    new ReturnDialog(fFrame);
    
}
}
