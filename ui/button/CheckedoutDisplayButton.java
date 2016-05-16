package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;

import listener.action.CheckedoutDisplayListener;

public final class CheckedoutDisplayButton extends JButton {
    private static final long serialVersionUID = 1L;
    
    public CheckedoutDisplayButton(final DataHandler aHandler, 
                                   final JDialog aDialog,
                                   final String aEmail) {
        super("Checkedout");
        addActionListener(new CheckedoutDisplayListener(aHandler, aDialog, aEmail));
        final JRootPane rp = aDialog.getRootPane();
        rp.setDefaultButton(this);
    }
}