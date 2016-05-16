package model.table;

import inventory.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Utility;

public final class VideoTableModel extends AbstractTableModel{
     private static final long serialVersionUID = 1L;
     private static final int CALL_NUMBER = 0;
     private static final int TITLE = 1;
     private static final int NUMBER_AVAILABLE = 2;
     private List<String> fKeyList; 
     private HashMap<String, Video> fMap;
     private final String[] columnNames_  = {"Call Number", "Title", "Number Available"};

     public VideoTableModel(final HashMap<String, Video> aMap) {
                this.fMap = aMap; 
                this.fKeyList = Utility.createKeyList(fMap);
     }
     
     
     
         @Override public int getColumnCount() {
                        return 3;
                   }
         
         
         
         @Override public int getRowCount() {
                        return fKeyList.size();       
                   }
         
         
    
         @Override public String getColumnName(final int aColumn) {
                         return columnNames_[aColumn]; 
                  }
         
         
         
         @Override public Object getValueAt(final int aRow, final int aCol) {
                    final String key = fKeyList.get(aRow);
                    final Video v = fMap.get(key);
                    switch(aCol) {
                        case(CALL_NUMBER) :
                            return v.getCallNumber();
                        case(TITLE):
                            return v.getTitle();
                        case(NUMBER_AVAILABLE): 
                            return v.getAvailableCopies();
                       default:
                            return null;
                    }
         }
     
     
     
            public final Video getVideo(final int aRow) {
                final String key = fKeyList.get(aRow);
                return fMap.get(key); 
            }
            
            
            public final void setKeyList(final List<String> aList) {
                this.fKeyList = new ArrayList<String>(aList);
            }
     
            
            
            public final void removeVideo(final int aRow) {
                final String key = fKeyList.get(aRow);
                fKeyList.remove(aRow);
                fMap.remove(key);
                this.fireTableRowsDeleted(aRow, aRow);
            }
            
            
            
            public final void addKey(final String aKey) {
                this.fKeyList.add(aKey);
            }
}