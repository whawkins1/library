package model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import log.Log;

public final class EmployeeLogsTableModel extends AbstractTableModel{
    private static final long serialVersionUID = 1L;
    private List<Log> fLogList;
    private static final int LAST_NAME = 0;
    private static final int FIRST_NAME = 1;
    private static final int DATE = 2;
    private static final int ACTIVITY = 3;
    
    public EmployeeLogsTableModel(final List<Log> aLogList) {
            this.fLogList = aLogList;
    }
    
        @Override public final int getColumnCount() { return 4; }
    
        
        @Override public final int getRowCount() { return fLogList.size(); }
    
        
        @Override public final Object getValueAt(int aRow, int aColumn) {
            final Log log = fLogList.get(aRow);
            switch(aColumn) {
            case(LAST_NAME):
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
        
        public final void setList(final List<Log> aLogList) {
            this.fLogList = aLogList;
        }
}
