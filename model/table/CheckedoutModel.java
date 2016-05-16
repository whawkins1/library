package model.table;

import handler.DataHandler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import log.Log;


public final class CheckedoutModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = {"Title", "Date"};
    private static final int CHECKEDOUT_TITLE = 1;
    private static final int CHECKEDOUT_DATE = 2;
    
    private final List<Log> fCheckedoutList;
    
    public CheckedoutModel(final DataHandler aHandler, final String aEmail) {
        final List<Log> tempLogList = new ArrayList<Log>(aHandler.getCheckoutMap().values()); 
        fCheckedoutList = new ArrayList<Log>();
        for(final Log l : tempLogList) {
            final boolean isUserLog = (l.getEmail().equals(aEmail));
            if (isUserLog) 
               fCheckedoutList.add(l);     
        }
    }
    
        @Override public final int getColumnCount() {  return 2;  }
    
        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }    
        
        
        @Override public final int getRowCount() { return fCheckedoutList.size(); }
    
        
        @Override public final Object getValueAt(final int aRow, final int aColumn) {
            final Log l = fCheckedoutList.get(aRow);
            
            switch(aColumn) {
                case CHECKEDOUT_TITLE:
                    return l.getTitle();
                case CHECKEDOUT_DATE:
                    return l.getDate();    
                default:
                    return null;
            }
        }
}