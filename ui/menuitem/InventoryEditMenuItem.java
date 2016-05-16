package ui.menuitem;

import handler.DataHandler;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import ui.table.InventoryTable;
import utility.Utility;
import listener.action.InventoryListener;

public final class InventoryEditMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public InventoryEditMenuItem(final InventoryTable aTable,
                                 final JComboBox<String> aCB, 
                                 final JFrame aFrame,
                                 DataHandler aHandler) {
        super("Edit");
        final boolean enable = (Utility.getNumRowsSelected(aTable) > 1) ? false : true;
        
        if(enable)
            addActionListener(new InventoryListener(aTable, aCB, aFrame, "EDIT", aHandler));
        setEnabled(enable);
    }
}