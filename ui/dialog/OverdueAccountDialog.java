package ui.dialog;

import java.awt.BorderLayout;

import handler.DataHandler;

import javax.swing.JDialog;

import ui.scrollpane.ScrollPane;
import ui.table.OverdueTable;

import utility.Utility;

public final class OverdueAccountDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public OverdueAccountDialog(final JDialog aParent, 
                                final DataHandler aHandler,
                                final String aEmail) {
        super(aParent, "Overdue", true);
        final OverdueTable table = new OverdueTable(aHandler, aEmail);
        add(new ScrollPane(table), BorderLayout.CENTER);
        Utility.setCloseOperation(this);
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
