package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JDialog;

import listener.action.ReturnedDisplayListener;

public final class ReturnedDisplayButton extends JButton {
    private static final long serialVersionUID = 1L;
    
    public ReturnedDisplayButton(final DataHandler aHandler, 
                                 final JDialog aDialog,
                                 final String aEmail) {
        super("Returned");
        addActionListener(new ReturnedDisplayListener(aHandler, aDialog, aEmail));
    }
}