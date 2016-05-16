package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.SettingsDialog;

public final class SettingsDisplayListener 
                                  implements ActionListener{
    private final JFrame fParent;
    
    public SettingsDisplayListener (final JFrame aParent) {
        this.fParent = aParent;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
             new SettingsDialog (fParent);    
        }
}