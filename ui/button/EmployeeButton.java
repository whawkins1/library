package ui.button;

import handler.DataHandler;

import javax.swing.JButton;

import listener.action.EmployeeListener;
import ui.dialog.EmployeeDialog;
import ui.panel.PersonnelInputPanel;
import ui.table.PersonnelTable;


public final class EmployeeButton extends JButton {
    private static final long serialVersionUID = 1L;

    public EmployeeButton (final PersonnelInputPanel aInputPanel, 
                           final EmployeeDialog aDialog, 
                           final DataHandler aHandler)   {
        super(aDialog.getMode());

        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new EmployeeListener(aInputPanel, aDialog, aHandler));
    }
    
    public EmployeeButton (final PersonnelInputPanel aInputPanel, 
            final EmployeeDialog aDialog, 
            final DataHandler aHandler,
            final PersonnelTable aTable)   {
           super(aDialog.getMode());

aDialog.getRootPane().setDefaultButton(this);
addActionListener(new EmployeeListener(aInputPanel, aDialog, aHandler, aTable));
}    
}