package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.GoodStandingDialog;

public final class GoodStandingDisplayListener 
                                   implements ActionListener{
    private final JFrame fFrame;
    public GoodStandingDisplayListener(final JFrame aFrame) {
        this.fFrame = aFrame;
    }
    
        @Override public final void actionPerformed(final ActionEvent e) {
            new GoodStandingDialog(fFrame);
        }
}