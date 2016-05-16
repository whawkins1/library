package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JDialog;

import listener.action.OverdueDisplayListener;


public final class OverdueDisplayButton extends JButton {
    private static final long serialVersionUID = 1L;
    
    public OverdueDisplayButton(final DataHandler aHandler, 
                                final JDialog aDialog,
                                final String aEmail) {
        super("Overdue");
        addActionListener(new OverdueDisplayListener(aHandler, aDialog, aEmail));
    }
}