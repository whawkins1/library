package ui.menuitem;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import listener.action.InventoryRemoveListener;

public final class InventoryRemoveMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;
    
    public InventoryRemoveMenuItem(final JTable aTable, final JComboBox<String> aCB) {
        super("Remove");
        addActionListener(new InventoryRemoveListener(aTable, aCB));
    }
}