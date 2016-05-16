package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.OnHoldDialog;

public final class OnHoldDisplayListener 
                              implements ActionListener {
    private final JFrame fFrame;
    public OnHoldDisplayListener(final JFrame aParent) {
        this.fFrame = aParent;
    }
    
    @Override public void actionPerformed(ActionEvent e) {
        new OnHoldDialog(fFrame);
    }
}
