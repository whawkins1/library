package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Student;

import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.StudentsTableModel;

import ui.dialog.EmployeeDialog;
import ui.dialog.FacultyDialog;
import ui.dialog.StudentDialog;
import ui.table.PersonnelTable;


public final class PersonnelListener 
                           implements ActionListener{
    private final PersonnelTable table_;
    private final JComboBox<String> cb_;
    private final JFrame frame_;
    private final DataHandler handler_;
    private final String mode_;
    
    public PersonnelListener(final PersonnelTable aTable,
                                 final JComboBox<String> aCB, 
                                 final JFrame aFrame,
                                 final String aMode,
                                 final DataHandler aHandler) {
        this.table_ = aTable;
        this.cb_ = aCB;
        this.frame_ = aFrame;
        this.mode_ = aMode;
        this.handler_ = aHandler;
    }
    
    
        @Override public final void actionPerformed(final ActionEvent event) {
            final String type = (cb_.getSelectedItem().toString());
            final int row = table_.getSelectedRow();
            switch(type) {
                case "Student":
                    displayStudent(row);
                    break;
                case "Faculty":
                    displayFaculty(row);
                    break;
                case "Employee":
                    displayEmployee(row);
                    break;
            }
        }
        
        
        private final void displayStudent(final int aRow) {
            final StudentsTableModel studentModel = ((StudentsTableModel)table_.getModel());
            final Student student = studentModel.getStudent(aRow);
            switch(mode_) {
                case "ADD":
                    new StudentDialog(frame_, handler_);
                    break;
                case "EDIT":
                    new StudentDialog(student, frame_, handler_, table_);
                    break;
                case "VIEW":
                    new StudentDialog(student, frame_, handler_);
                    break;
               }        
        }
                
        
        private final void displayFaculty(final int aRow) {
            final FacultyTableModel facultyModel = ((FacultyTableModel)table_.getModel());
            final Faculty faculty = facultyModel.getFaculty(aRow);
            switch(mode_) {
                case "ADD":
                    new FacultyDialog(frame_, handler_);
                    break;
                case "EDIT":
                    new FacultyDialog(faculty, frame_, handler_, table_);
                    break;
                case "VIEW":
                    new FacultyDialog(faculty, frame_, handler_);
                    break;
             }
        }
        
        
        private final void displayEmployee(final int aRow) {
            final EmployeesTableModel employeeModel = ((EmployeesTableModel)table_.getModel());
            final Employee employee = employeeModel.getEmployee(aRow);
            switch(mode_) {
                case "ADD":
                    new EmployeeDialog(frame_, handler_, false, false);
                    break;
                case "EDIT":
                    new EmployeeDialog(employee, frame_, handler_, table_);
                    break;
                case "VIEW":
                    new EmployeeDialog(employee, frame_);
                    break;
             }
        }
}