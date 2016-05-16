package ui.panel;

import java.awt.FlowLayout;

import handler.DataHandler;

import javax.swing.JDialog;
import javax.swing.JPanel;

import ui.button.CheckedoutDisplayButton;
import ui.button.OverdueDisplayButton;
import ui.button.PaymentsDisplayButton;
import ui.button.ReturnedDisplayButton;

public final class ViewButtonPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    
    public ViewButtonPanel(final DataHandler aHandler, 
                           final JDialog aDialog,
                           final String aEmail) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new CheckedoutDisplayButton(aHandler, aDialog, aEmail));
        add(new ReturnedDisplayButton(aHandler, aDialog, aEmail));
        add(new OverdueDisplayButton(aHandler, aDialog, aEmail));
        add(new PaymentsDisplayButton(aHandler, aDialog, aEmail));
    }
}