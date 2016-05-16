package model.table;

import inventory.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Utility;

public final class BookTableModel extends AbstractTableModel {
 	private static final long serialVersionUID = 1L;
 	private static final String[] COLUMN_NAMES = {"Call Number", "Title", "Number Available"};
 	private static final int CALL_NUMBER = 0;
 	private static final int TITLE = 1;
 	private static final int NUMBER_AVAILABLE = 2;
 	private List<String> fKeyList;
 	private HashMap<String, Book> fMap;
	 
 	public BookTableModel(final HashMap<String, Book> aMap) {
    	 this.fMap = aMap;
	     this.fKeyList = Utility.createKeyList(fMap);
     }
	
 	
 	
		@Override public int getColumnCount() {
			return 3;		
		}
		
		
		@Override public final String getColumnName(final int aColumn) {
		    return COLUMN_NAMES[aColumn];
		}
		
		
		@Override public int getRowCount() {
			return fKeyList.size();
		}
	
		
		
		@Override public Object getValueAt(int aRow, int aColumn) {
			final String key = fKeyList.get(aRow);
		    final Book book = fMap.get(key);
		    
			switch(aColumn) {
    			case(CALL_NUMBER):
    				return book.getCallNumber();
    			case(TITLE):
    				return book.getTitle();
    			case(NUMBER_AVAILABLE): 
    				return book.getNumCopies();
    			default:
    				return null;
    			}		
		}
		
		
		
		public final void filterPublic() {
		    //TODO
		}
		
		
		
		public final void filterReference() {
		    //TODO
		}
		
		
		
		public final Book getBook(final int aRow) {
		    final String key = fKeyList.get(aRow);
            return fMap.get(key); 
		}
		
		
		
		public final void addKey(final String aKey) {
		    this.fKeyList.add(aKey);
		}
		
		
		public final void setKeyList(final List<String> aList) {
		    fKeyList = new ArrayList<String>(aList); 
		}
		
		
		public final void removeBook(final int aRow) {
		    final String key = fKeyList.get(aRow);
		    fKeyList.remove(aRow);
		    fMap.remove(key);
		}
		
		public final void showTopTen() {
			
		}
}