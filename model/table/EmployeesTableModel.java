package model.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Personnel.Employee;
import utility.Utility;

public final class EmployeesTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Last Name", "First Name"};
    private static final long serialVersionUID = 1L;
	private static final int LAST_NAME = 0;
	private static final int FIRST_NAME = 1;
	private List<String> fKeyList;
	private HashMap<String, Employee> fMap;
	
	public EmployeesTableModel(final HashMap<String, Employee> aMap) {
        this.fMap = aMap;	
        
	    this.fKeyList = Utility.createKeyList(fMap);
    }    
	
    	@Override public final int getColumnCount() {
    		return 2;
    	}
    	
    
    	@Override public final int getRowCount() {
    	    return fKeyList.size();		
    	}
    	
    	
    	@Override public final String getColumnName(final int aColumn) {
    	    return COLUMN_NAMES[aColumn];
    	}    	
    	
    
    	@Override public Object getValueAt(int aRow, int aColumn) {
    		final String key = fKeyList.get(aRow);
    		final Employee e = fMap.get(key);
    		switch(aColumn) {
        		case(LAST_NAME):
        			return e.getLastName();
        		case(FIRST_NAME):
        			return e.getFirstName();
        		default:
        			return null;
        		}
    	}
    	
    	

    	public final Employee getEmployee(final int aRow) {
    	    final String key = fKeyList.get(aRow);
            return fMap.get(key); 
    	}
    	
        
    	public final void setKeyList(final List<String> aList) {
    	    this.fKeyList = new ArrayList<String>(aList);
    	}
    	
    	
    	
    	public final void removeEmployee(final int aRow) {
    	    final String key = fKeyList.get(aRow);
    	    fKeyList.remove(aRow);
    	    fMap.remove(key);
    	    fireTableRowsDeleted(aRow, aRow);
    	}
    	
    	
    	
    	public final void addKey(final String aKey) {
    	    this.fKeyList.add(aKey);
    	}
}