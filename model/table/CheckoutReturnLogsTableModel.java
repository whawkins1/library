package model.table;


import java.util.List;

import javax.swing.table.AbstractTableModel;

import log.Log;

public class CheckoutReturnLogsTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Log> fLogList;
    private final static String[]  COLUMN_NAMES = {"Last Name", "First Name", "Date", "Activity"};
    private final static int LAST_NAME = 0;
    private final static int FIRST_NAME = 1;
    private final static int DATE = 2;
    private final static int ACTIVITY = 3;
    
    public CheckoutReturnLogsTableModel(final List<Log> aLogList) {
        this.fLogList = aLogList;
    }
    
        @Override public final int getRowCount() { return fLogList.size(); }
        
        
        @Override  public final int getColumnCount() { return 4; }

        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }
        
        
        @Override public final Object getValueAt(int aRow, int aCol) {
            final Log log = fLogList.get(aRow);
            switch(aCol) {
                case LAST_NAME:
                    return log.getLastName();
                case FIRST_NAME:
                    return log.getFirstName();
                case DATE:
                    return log.getDate();
                case ACTIVITY:
                    return log.getActivity();
                default:
                    return null;
            }
        }
        
        
        public final void setList(final List<Log> aLogList) { 
            this.fLogList = aLogList;  
            this.fireTableDataChanged();
        }
}