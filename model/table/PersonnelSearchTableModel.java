package model.table;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Utility;
import Personnel.Personnel;

public final class PersonnelSearchTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Last Name", "First Name", "Account Type"};
    private static final long serialVersionUID = 1L;
    private static final int LAST_NAME = 0;
    private static final int FIRST_NAME = 1;
    private static final int ACCOUNT_TYPE = 2;
    private List<String> fKeyList;
    private final HashMap<String, Personnel> fPersonnelMap;
    
    public PersonnelSearchTableModel(final HashMap<String, Personnel> aMap) {
        this.fPersonnelMap = aMap;    
        this.fKeyList = Utility.createKeyList(fPersonnelMap);
        Collections.sort(fKeyList);
    }   
    
            @Override public final int getColumnCount () { return 3; }
            
        
            @Override public final int getRowCount() { return fKeyList.size(); }
    
            
            @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }
            
        
            @Override public final Object getValueAt(final int aRow, final int aColumn) {
                final String key = fKeyList.get(aRow);
                final Personnel p = fPersonnelMap.get(key);
                switch(aColumn) {
                    case(LAST_NAME):
                        return p.getLastName();
                    case(FIRST_NAME):
                        return p.getFirstName();
                    case(ACCOUNT_TYPE):
                        return p.getAccountType();
                    default:
                        return null;            
                    }
           }

            
            public final Personnel getPersonnel(final int aRow) {
                final String key = fKeyList.get(aRow);
                return fPersonnelMap.get(key); 
            }
            
            
            public final void setKeyList(final List<String> aList) { this.fKeyList = new ArrayList<String>(aList); }
            
            
            public final void addKey(final String aKey) { this.fKeyList.add(aKey); }
}