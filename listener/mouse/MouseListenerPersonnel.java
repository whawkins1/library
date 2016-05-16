package listener.mouse;


import handler.DataHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;

import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.StudentsTableModel;

import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Student;

import ui.dialog.EmployeeDialog;
import ui.dialog.FacultyDialog;
import ui.dialog.StudentDialog;
import ui.panel.SearchPanel;
import ui.popup.PopupPersonnel;
import ui.table.PersonnelTable;


public final class MouseListenerPersonnel implements MouseListener {
    private final PersonnelTable table_;
    private JComboBox<String> cb_;
    private final JFrame frame_;
    private final DataHandler handler_;
    private SearchPanel fSearchPanel;
    
    public MouseListenerPersonnel(final PersonnelTable aTable, 
                                  final JFrame aFrame, 
                                  final DataHandler aHandler) {
      
      this.table_ = aTable;
      this.frame_ = aFrame;
      this.handler_ = aHandler;
    }
    
    public MouseListenerPersonnel(final PersonnelTable aTable, 
                                  final JComboBox<String> aCB, 
                                  final JFrame aFrame, 
                                  final DataHandler aHandler) {
        this(aTable, aFrame, aHandler);
        this.cb_ = aCB;
    }
    
    public MouseListenerPersonnel(final PersonnelTable aTable, 
                                  final SearchPanel aSearchPanel, 
                                  final JFrame aFrame, 
                                  final DataHandler aHandler) {
                    
                    this(aTable, aFrame, aHandler);
                    this.fSearchPanel = aSearchPanel;
}
    
    
     
    
        @Override public void mouseClicked(final MouseEvent e) {
            final JTable table = (JTable)e.getSource();
                if(e.getClickCount() == 2) {
                    
                    String category = "";
                    if(cb_ != null) {
                        category = cb_.getSelectedItem().toString();
                    } else {
                        category = fSearchPanel.getCBCategoryText();     
                    }
                    
                   
                   final int row = table.getSelectedRow();
                   switch(category) {
                       case "Student":
                           displayStudent((StudentsTableModel)table.getModel(), row);
                           break;
                       case "Faculty":
                           displayFaculty((FacultyTableModel)table.getModel(), row);
                           break;
                       case "Employee":
                           displayEmployee((EmployeesTableModel)table.getModel(), row);
                           break;
                       default:
                              
                   }
                } else if(e.isPopupTrigger()) {
                    final PopupPersonnel popup = new PopupPersonnel(table_,  cb_, frame_, handler_);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
        }

        
        private final void displayStudent(final StudentsTableModel aModel, final int row) {
            final Student s = aModel.getStudent(row);
            new StudentDialog(s, frame_, handler_);
        }
        
        
        private final void displayFaculty(final FacultyTableModel aModel, final int row) {
            final Faculty f = aModel.getFaculty(row);
            new FacultyDialog(f, frame_, handler_);
        } 
        
        private final void displayEmployee(final EmployeesTableModel aModel, final int row) {
            final Employee employee = aModel.getEmployee(row);
            new EmployeeDialog(employee, frame_);
        }

        @Override public final void mouseEntered(final MouseEvent e) {}

        @Override public final void mouseExited(final MouseEvent e) {}

        @Override public final void mousePressed(final MouseEvent e) {}

        @Override public final void mouseReleased(final MouseEvent e) {
            if(e.isPopupTrigger()) {
                final int row = table_.rowAtPoint(e.getPoint());
                final int column = table_.columnAtPoint(e.getPoint());
                if(!table_.isRowSelected(row)) 
                   table_.changeSelection(row,  column, false, false);
                   
                final PopupPersonnel popup = new PopupPersonnel(table_, cb_, frame_, handler_); 
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
            
        }
   }