package model.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import utility.Utility;

public final class OnHoldTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private static final int FIRST_NAME = 0;
    private static final int LAST_NAME = 1;
    
    private static final String GET_ON_HOLD = "SELECT s.first, s.last "
                                            + "FROM students AS s "
                                            + "INNER JOIN students_hold AS sh "
                                            + "ON sh.email = s.email;";
    
    private static final String[] COLUMN_NAMES = {"Last", "First"};
    private List<OnHoldStudent> fOnHoldList;

    public OnHoldTableModel() {
        fOnHoldList = new ArrayList<OnHoldStudent>();
        try(final Connection conn = Utility.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(GET_ON_HOLD);
            final ResultSet rs = pstmt.executeQuery()) {
              
              while(rs.next()) {
                  final String first = rs.getString(1);
                  final String last = rs.getString(2);
                  final OnHoldStudent student = new OnHoldStudent(last, first);
                  fOnHoldList.add(student);
              }
        } catch(final SQLException e) {
            e.printStackTrace();
            Utility.showErrorMessage(null, "Error Getting Students On Hold.");
        }
    }
    
        @Override public final int getColumnCount() { return 2; }
    
        
        @Override public final int getRowCount() { return fOnHoldList.size(); }
    
        
        @Override public Object getValueAt(final int aRow, final int aColumn) { 
              final OnHoldStudent s = fOnHoldList.get(aRow);
              switch(aColumn) {
                  case(FIRST_NAME) :
                      return s.fFirst;
                  case(LAST_NAME):
                      return s.fLast;
                  default:
                      return null;
              }
        }
    
        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }

        
        //INNER CLASS
        class OnHoldStudent  {
            private final String fLast;
            private final String fFirst;
            OnHoldStudent(final String aLast, final String aFirst) {
                this.fLast = aLast;
                this.fFirst = aFirst;
            }
        }
}