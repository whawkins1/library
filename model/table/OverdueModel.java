package model.table;

import handler.DataHandler;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import log.Log;

public final class OverdueModel extends AbstractTableModel{
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = {"Title", "Checkout Date", "Days Overdue"};
    private static final int OVERDUE_TITLE = 1;
    private static final int CHECKOUT_DATE = 2;
    private static final int OVERDUE_DAYS = 3;
    
    private final List<Log> fOverdueList;
    
    public OverdueModel(final DataHandler aHandler, final String aEmail) {
        fOverdueList = aHandler.getOverdueList();
    }
    
        @Override public final int getColumnCount() {  return 3;  }
    
        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }    
        
        
        @Override public final int getRowCount() { return fOverdueList.size(); }
    
        
        @Override public final Object getValueAt(final int aRow, final int aColumn) {
            final Log l = fOverdueList.get(aRow);
            
            switch(aColumn) {
                case OVERDUE_TITLE:
                    return l.getTitle();
                case CHECKOUT_DATE:
                    return l.getDate();
                case OVERDUE_DAYS:
                    return l.getDaysOverdue();
                default:
                    return null;
            }
        }
}