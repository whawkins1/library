package model.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;




import utility.Utility;

public final class GoodStandingTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private static final String GET_OUTSTANDING = "SELECT s.first, s.last "
                                                + "FROM students AS s "
                                                + "INNER JOIN students_good_standing AS sgs "
                                                + "ON sgs.email = s.email;";
    private static final int LAST_NAME = 0;
    private static final int FIRST_NAME = 1;
    private static final String[] COLUMN_NAMES = {"First", "Last"};

    private final List<StudentGoodStanding> fGoodStandingList;
    
       public GoodStandingTableModel() {
           fGoodStandingList = new ArrayList<StudentGoodStanding>();
           try(final Connection conn = Utility.getConnection();
               final PreparedStatement pstmt = conn.prepareStatement(GET_OUTSTANDING);
               final ResultSet rs = pstmt.executeQuery()) {
               
               while(rs.next()) {
                   final String first = rs.getString(1);
                   final String last = rs.getString(2);
                   final StudentGoodStanding student = new StudentGoodStanding(first, last);
                   fGoodStandingList.add(student);
               }                          
           } catch(final SQLException e) {
               e.printStackTrace();
           }
       }
       
        @Override public int getColumnCount() { return 2; }
    
        
        @Override public String getColumnName(final int aColumn) {  return COLUMN_NAMES[aColumn];   }
        
        
        @Override public int getRowCount() { return fGoodStandingList.size(); }
    
        
        @Override public Object getValueAt(int aRow, int aColumn) {
            final StudentGoodStanding student = fGoodStandingList.get(aRow);
            switch(aColumn) {
                case(LAST_NAME):
                    return student.fLast;
                case(FIRST_NAME):
                    return student.fFirst;
                default:
                    return null;
                }
        }
        
        
        class StudentGoodStanding {
            private final String fFirst;
            private final String fLast;
            
            StudentGoodStanding(final String aFirst, final String aLast) {
                this.fFirst = aFirst;
                this.fLast = aLast;
            }
        }
}