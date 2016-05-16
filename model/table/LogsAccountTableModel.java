package model.table;

import handler.DataHandler;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import log.Log;


public final class LogsAccountTableModel extends AbstractTableModel {
    private final static long serialVersionUID = 1L;
    private final static int LAST_NAME = 0;
    private static final int FIRST_NAME = 1;
    private static final int DATE = 2;
    private static final int ACTIVITY = 3;
    private static final String[] COLUMN_NAMES = {"Last", "First Name", "Date", "Activity"};
    private List<Log> logList_;
    
    public LogsAccountTableModel(final DataHandler aHandler) {
        logList_ = aHandler.getEmployeeLogList();
    }
        
        @Override public int getColumnCount() { return 4; }
        
        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }
        
        
        @Override public int getRowCount() { return logList_.size();   }
        
        
        @Override public Object getValueAt(final int aRow, final int aCol) {
            final Log log = logList_.get(aRow);
            switch(aCol) {
                case(LAST_NAME) :
                    return log.getLastName();
                case(FIRST_NAME):
                    return log.getFirstName();
                case(DATE):
                    return log.getDate();
                case(ACTIVITY):
                    return log.getActivity();
                default:
                    return null;
                }
        }

        public final void setList (final List<Log> aList) { 
            this.logList_ = aList;
            fireTableDataChanged();
        }
        
       public final List<Log> getList() { return this.logList_; }
}