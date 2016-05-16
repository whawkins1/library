package ui.panel;

import java.awt.FlowLayout;

import handler.DataHandler;

import javax.swing.JPanel;

import ui.button.EmployeeButton;
import ui.dialog.EmployeeDialog;
import ui.table.PersonnelTable;

public final class EmployeeButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public EmployeeButtonPanel(final PersonnelInputPanel aInputPanel, 
                              final EmployeeDialog aDialog,
                              final DataHandler aHandler) {
           super(new FlowLayout(FlowLayout.CENTER));
           add(new EmployeeButton (aInputPanel, aDialog, aHandler));                       
       }
    
    public EmployeeButtonPanel(final PersonnelInputPanel aInputPanel, 
            final EmployeeDialog aDialog,
            final DataHandler aHandler,
            final PersonnelTable aTable) {
            super(new FlowLayout(FlowLayout.CENTER));
            add(new EmployeeButton (aInputPanel, aDialog, aHandler, aTable));                       
            }
}