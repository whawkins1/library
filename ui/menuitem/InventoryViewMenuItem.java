package ui.menuitem;

import handler.DataHandler;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import listener.action.InventoryListener;

import utility.Utility;


public final class InventoryViewMenuItem extends JMenuItem  {
    private static final long serialVersionUID = 1L;
    
    public InventoryViewMenuItem(final JTable aTable, 
                                 final JComboBox<String> aCb, 
                                 final JFrame aFrame,                                 
                                 final DataHandler aHandler) {
        super("View");
        final boolean enable = (Utility.getNumRowsSelected(aTable) > 1) ? false : true;
        if(enable) 
            addActionListener(new InventoryListener( aTable, aCb, aFrame, "VIEW", aHandler));
        setEnabled(enable);
    }
}