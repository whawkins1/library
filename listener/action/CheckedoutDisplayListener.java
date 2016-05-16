package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.dialog.CheckedoutAccountDialog;

public final class CheckedoutDisplayListener 
                                 implements ActionListener {

    private final DataHandler fHandler;
    private final JDialog fDialog;
    private final String fEmail;
    
    public CheckedoutDisplayListener(final DataHandler aHandler, 
                                     final JDialog aDialog,
                                     final String aEmail) {
        this.fHandler = aHandler;
        this.fDialog = aDialog;
        this.fEmail = aEmail;
    }
    
    
    @Override public final void actionPerformed(final ActionEvent event) {
        new CheckedoutAccountDialog(fDialog, fHandler, fEmail);
    }
}