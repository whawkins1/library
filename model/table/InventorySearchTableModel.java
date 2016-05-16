package model.table;

import inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Utility;

public final class InventorySearchTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Title", "Author", "Type"};
    private static final long serialVersionUID = 1L;
    private static final int TITLE = 0;
    private static final int AUTHOR = 1;
    private static final int TYPE = 2;
    private List<String> fKeyList;
    private final HashMap<String, Inventory> fInventoryMap;
    
    public InventorySearchTableModel(final HashMap<String, Inventory> aMap) {
        this.fInventoryMap = aMap;    
        this.fKeyList = Utility.createKeyList(fInventoryMap);
        Collections.sort(fKeyList);
    }   
    
        @Override public final int getColumnCount() { return 3; }
        
    
        @Override public final int getRowCount() { return fKeyList.size(); }

        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }
        
    
        @Override public final Object getValueAt(final int aRow, final int aColumn) {
            final String key = fKeyList.get(aRow);
            final Inventory i = fInventoryMap.get(key);
            switch(aColumn) {
                case(TITLE):
                    return i.getTitle();
                case(AUTHOR):
                    return i.getAuthor();
                case(TYPE):
                    return i.getType();
                default:
                    return null;            
                }
       }
        
        
            public final Inventory getInventory(final int aRow) {
                final String key = fKeyList.get(aRow);
                return fInventoryMap.get(key); 
            }

            
            public final void setKeyList(final List<String> aList) { this.fKeyList = new ArrayList<String>(aList); }

            
            public final void addKey(final String aKey) { this.fKeyList.add(aKey); }
}
