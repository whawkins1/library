package ui.panel;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.combobox.ComboBoxInventory;
import ui.scrollpane.ScrollPane;
import ui.table.InventoryTable;

public final class InventoryPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public InventoryPanel(final DataHandler aHandler, final JFrame aFrame) {
        super(new BorderLayout());
        final InventoryTable table = new InventoryTable();
        final ComboBoxInventory cb = new ComboBoxInventory(aHandler, table);
        table.setMouseListener(cb, aFrame, aHandler);
        final InventoryCBPanel inventoryCBPanel = new InventoryCBPanel(cb);
        add(inventoryCBPanel, BorderLayout.NORTH);      
        add(new ScrollPane(table), BorderLayout.CENTER);
    }
}