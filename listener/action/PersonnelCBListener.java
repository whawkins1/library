package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.StudentsTableModel;

public final class PersonnelCBListener 
                             implements ActionListener {
    private final DataHandler fHandler;
    private final JComboBox<String> fCB;
    private final JTable fTable;
    
    public PersonnelCBListener(final DataHandler aHandler, 
                               final JComboBox<String> aCB, 
                               final JTable aTable )  {
        this.fHandler = aHandler;
        this.fCB = aCB;
        this.fTable = aTable;
    }
    
    public final void actionPerformed(final ActionEvent e) {
        final String selection = (fCB.getSelectedItem().toString());
        AbstractTableModel tableModel = null;
        switch(selection) {
            case "Student":
                tableModel = new StudentsTableModel(fHandler.getStudentMap());
                break;
            case "Faculty":
                tableModel = new FacultyTableModel(fHandler.getFacultyMap());
             break;
            case "Employee":
                tableModel = new EmployeesTableModel(fHandler.getEmployeeMap());
             break;
             default:
                 tableModel = new DefaultTableModel();
        }
        fTable.setModel(tableModel);
    }     
}
