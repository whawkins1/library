package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import utility.Utility;

import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Student;

import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.StudentsTableModel;

public final class PersonnelRemoveListener 
                                implements ActionListener{
    private final static String REMOVE_STUDENT = "DELETE FROM students WHERE  email = ?;"; 
    private final static String REMOVE_FACULTY = "DELETE FROM faculty WHERE  email = ?;";
    private final static String REMOVE_EMPLOYEE = "DELETE FROM employee WHERE  username = ?;";
    private final JTable fTable;
    private final JComboBox<String> cb_;
    
    public PersonnelRemoveListener(final JTable aTable, final JComboBox<String> aCB) {
        this.fTable = aTable;
        this.cb_ = aCB;
    }

    
    @Override public void actionPerformed(final ActionEvent event) {
        final int[] rowArr = fTable.getSelectedRows();
        int count = 0;
        int rowEffected = 0;
        final int result = JOptionPane.showConfirmDialog(null,  
                                                         "Remove " + rowArr.length + " Selection(s)?", 
                                                         "Add", 
                                                         JOptionPane.YES_NO_OPTION,
                                                         JOptionPane.PLAIN_MESSAGE
                                                       );
           if(result == JOptionPane.YES_OPTION) {
                final String type = cb_.getSelectedItem().toString();
                switch(type) {
                    case "Student":
                        final StudentsTableModel studentModel = ((StudentsTableModel)fTable.getModel());
                        for(int i : rowArr) {
                            final Student s = studentModel.getStudent(i);
                            rowEffected = Utility.executeRemove(REMOVE_STUDENT, s.getEmail());
                            if(rowEffected == 1) {
                                 studentModel.removeStudent(i);
                                 count += rowEffected;
                            }
                        }
                        if(count > 0) {
                            Utility.showInfoMessage(null, "Success Deleting " + count + " Entry(s).");
                        } else {
                            Utility.showErrorMessage(null, "Error Removing Entry(s)");
                        }
                        break;
                    case "Faculty":
                        final FacultyTableModel facultyModel = ((FacultyTableModel)fTable.getModel());
                        for(int i : rowArr) {
                            final Faculty f = facultyModel.getFaculty(i);
                            rowEffected = Utility.executeRemove(REMOVE_FACULTY, f.getEmail());
                            if(rowEffected == 1) {
                                 facultyModel.removeFaculty(i);
                                 count += rowEffected;
                            }
                        }
                        if(count > 0) {
                            Utility.showInfoMessage(null, "Success Deleting " + count + " Entry(s).");
                            facultyModel.fireTableDataChanged();
                        } else {
                            Utility.showErrorMessage(null, "Error Removing Entry(s)");
                        }
                        break;
                    case "Employee":
                        final EmployeesTableModel employeeModel = ((EmployeesTableModel)fTable.getModel());
                        for(int i : rowArr) {
                           final Employee e = employeeModel.getEmployee(i);
                           rowEffected = Utility.executeRemove(REMOVE_EMPLOYEE, e.getUsername());
                           if(rowEffected == 1) { 
                                employeeModel.removeEmployee(i);
                                count += rowEffected;
                           }
                        }
                        if(count > 0) {
                            Utility.showInfoMessage(null, "Success Deleting " + count + " Entry(s).");
                            employeeModel.fireTableDataChanged();
                        } else {
                            Utility.showErrorMessage(null, "Error Removing Entry(s)");
                        }
                        break;
                }
            }
    }
}