package ui.combobox;

import handler.DataHandler;

import javax.swing.JComboBox;
import javax.swing.JTable;

import listener.action.InventoryCBListener;

public final class ComboBoxInventory extends JComboBox<String> {
    private final static String[] INVENTORY_ARRAY = {"Book", "Audio", "Video"}; 
    private static final long serialVersionUID = 1L;
    final DataHandler handler_;
    final JTable fTable;  
    
    public ComboBoxInventory(final DataHandler aHandler, final JTable aTable){
        super(INVENTORY_ARRAY);
        this.handler_ = aHandler;  
        this.fTable = aTable;
        insertItemAt("", 0);
        setSelectedIndex(0);
        addActionListener(new InventoryCBListener(aHandler, aTable, this));
    }  
}