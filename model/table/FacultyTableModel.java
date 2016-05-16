package model.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Personnel.Faculty;

import utility.Utility;

public final class FacultyTableModel extends AbstractTableModel{
    private static final String[] COLUMN_NAMES = {"Last Name", "First Name", "Number Checkedout"};
    private static final long serialVersionUID = 1L;
	private static final int LAST_NAME = 0;
	private static final int FIRST_NAME = 1;
	private static final int NUMBER_CHECKEDOUT = 2;
	private List<String> fKeyList;
    private HashMap<String, Faculty> fMap;
    
	public FacultyTableModel(final HashMap<String, Faculty> aMap) {
	    this.fMap = aMap;    
	    this.fKeyList = Utility.createKeyList(fMap);
    }	
	
		@Override public final int getColumnCount() {
			return 3;
		}
		
	
		@Override public final int getRowCount() {
		  return fKeyList.size();	
		}

		
		@Override public final String getColumnName(final int aColumn) {
		    return COLUMN_NAMES[aColumn];
		}
		
		
	
		@Override public final Object getValueAt(final int aRow, final int aColumn) {
			final String key = fKeyList.get(aRow);
			final Faculty f = fMap.get(key);
			switch(aColumn) {
			case(LAST_NAME):
				return f.getLastName();
			case(FIRST_NAME):
				return f.getFirstName();
			case(NUMBER_CHECKEDOUT) :
			    return (f.getBookCheckkOutCount() + f.getCheckoutCountOther());
			default:
				return null;			
			}
       }
		
		
		
    		public final Faculty getFaculty(final int aRow) {
    		    final String key = fKeyList.get(aRow);
                return fMap.get(key); 
    		}
    		
    		
    		
    		public final void setKeyList(final List<String> aList) {
    		    this.fKeyList = new ArrayList<String>(aList);
    		}
    		
    		
    		
    		public final void removeFaculty(final int aRow) {
    		    final String key = fKeyList.get(aRow);
    		    fKeyList.remove(aRow);
    		    fMap.remove(key);
    		    this.fireTableRowsDeleted(aRow, aRow);
    		}
    		
    		
    		
    		public final void addKey(final String aKey) {
    		    this.fKeyList.add(aKey);
    		}
}