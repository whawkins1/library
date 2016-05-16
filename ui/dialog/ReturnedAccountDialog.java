package ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import ui.scrollpane.ScrollPane;
import ui.table.ReturnedTable;

import utility.Utility;

import handler.DataHandler;

public final class ReturnedAccountDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public ReturnedAccountDialog(final JDialog aParent, 
                                 final DataHandler aHandler,
                                 final String aEmail) {
        super(aParent, "Returned", true);
        final ReturnedTable table = new ReturnedTable(aHandler, aEmail);
        
        add(new ScrollPane(table), BorderLayout.CENTER);
        Utility.setCloseOperation(this);
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
