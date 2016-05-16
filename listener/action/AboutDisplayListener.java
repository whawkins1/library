package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.AboutDialog;

public final class AboutDisplayListener 
                           implements ActionListener {
    private final JFrame fFrame;
    
    public AboutDisplayListener(final JFrame aFrame) {
        this.fFrame = aFrame;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
            new AboutDialog(fFrame);
        }
}