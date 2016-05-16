package model.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Personnel.Student;

import utility.Utility;

public final class StudentsTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Last Name", "First Name", "Number Checkedout"};
    private static final long serialVersionUID = 1L;
    private static final int LAST_NAME = 0;
    private static final int FIRST_NAME = 1;
    private static final int NUMBER_CHECKEDOUT = 2;
	private List<String> fKeyList; 
	private HashMap<String, Student> fMap;
	
		public StudentsTableModel(final HashMap<String, Student> aMap) {
	    	this.fMap = aMap;
		    this.fKeyList = Utility.createKeyList(fMap);
	     }
		
		
		
		@Override public int getColumnCount() {
			return 3;
		}
		
		
	
		@Override public int getRowCount() {
			return fKeyList.size();		
		}
		
		@Override public final String getColumnName(final int aColumn) {
		    return COLUMN_NAMES[aColumn];
		}
		
		
	
		@Override public Object getValueAt(int aRow, int aCol) {
		    final String key = fKeyList.get(aRow);
		    final Student s  = fMap.get(key);
			switch(aCol) {
    			case(LAST_NAME):
    				return s.getLastName();
    			case(FIRST_NAME):			
    				return s.getFirstName();
    			case(NUMBER_CHECKEDOUT):
    			    return (s.getBookCheckkOutCount() + s.getCheckoutCountOther());
    			default:
    				return null;
    			} 
		}
		
		
		
		public final Student getStudent(final int aRow) {
		    final String key = fKeyList.get(aRow);
            return fMap.get(key); 
		}
		
		
		
		public final void setKeyList(final List<String> aList) {
		   this.fKeyList = new ArrayList<String>(aList);    
		}
		
		
		
		public final void removeStudent(final int aRow) {
		    final String key = fKeyList.get(aRow);
		    fKeyList.remove(aRow);
		    fMap.remove(key);
		    fireTableRowsDeleted(aRow, aRow);
		}
		
		
		
		public final void setKey(final String aKey) {
		    this.fKeyList.add(aKey);
		}
}