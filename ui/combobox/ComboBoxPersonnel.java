package ui.combobox;

import handler.DataHandler;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;

import listener.action.PersonnelCBListener;

public final class ComboBoxPersonnel extends JComboBox<String> implements ActionListener{
        private final static String[] PERSONNEL_ARRAY = {"Student", "Faculty", "Employee"}; 
        private static final long serialVersionUID = 1L;
        final DataHandler handler_;
        final JTable fTable;
              
        public ComboBoxPersonnel(final DataHandler aHandler, final JTable aTable){
            super(PERSONNEL_ARRAY);
            this.handler_ = aHandler;  
            this.fTable = aTable;
            insertItemAt("", 0);
            setSelectedIndex(0);
            addActionListener(new PersonnelCBListener(aHandler, this, aTable));
        }
}