package model.table;

import inventory.Audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Utility;

public final class AudioTableModel extends AbstractTableModel{
	 private static final long serialVersionUID = 1L;
	 private static final int CALL_NUMBER = 0;
	 private static final int TITLE = 1;
	 private static final int NUMBER_AVAILABLE = 2;
	 private List<String> fKeyList; 
	 private HashMap<String, Audio> fMap;
	 private final static String[] COLUMN_NAMES = {"Call Number", "Title", "Number Available"}; 	
	 
     public AudioTableModel(final HashMap<String, Audio> aMap)  {
	   	this.fMap = aMap; 
	    this.fKeyList = Utility.createKeyList(fMap);
	 }
		
          
			@Override public final int getColumnCount() { return 3; }
			
			
			@Override public final int getRowCount() {  return fKeyList.size();  }
			
			
			@Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn];	}
			
			
			@Override public final Object getValueAt(final int aRow, final int aCol) {
			    final String key = fKeyList.get(aRow);
			    final Audio a = fMap.get(key);
			    switch(aCol) {
    			    case(CALL_NUMBER) :
    			    	return a.getCallNumber();
    			    case(TITLE):
    			        return a.getTitle();
    			    case(NUMBER_AVAILABLE):
    			        return a.getAvailableCopies();
    			    default:
    			    	return null;
    			    }
			}
			
			
			
			public final Audio getAudio(final int aRow) {
			    final String key = fKeyList.get(aRow);
			    return fMap.get(key); 
			}
			
			
			
			public final void setKeyList(final List<String> aList) {
			    this.fKeyList = new ArrayList<String>(aList);    
			}
			
			
			
			public final void removeAudio(final int aRow) {
			    final String key = fKeyList.get(aRow);
			    fKeyList.remove(aRow);
			    fMap.remove(key);
			    fireTableRowsDeleted(aRow, aRow);
			}
			
			
			
			public final void addKey(final String aKey) {
			    this.fKeyList.add(aKey);
			}
}