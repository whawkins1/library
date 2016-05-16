package ui.button;

import handler.DataHandler;

import javax.swing.JButton;

import listener.action.FacultyListener;

import ui.dialog.FacultyDialog;
import ui.panel.PersonnelInputPanel;
import ui.table.PersonnelTable;


public final class FacultyButton extends JButton {
    private static final long serialVersionUID = 1L;

    public FacultyButton (final PersonnelInputPanel aInputPanel, 
            final FacultyDialog aDialog, 
            final DataHandler aHandler)   {
         super(aDialog.getMode());

        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new FacultyListener(aInputPanel, aDialog, aHandler));
    }
    
    public FacultyButton ( final PersonnelInputPanel aInputPanel, 
                           final FacultyDialog aDialog,
                           final DataHandler aHandler,
                           final PersonnelTable aTable) {        
        super(aDialog.getMode());
        
        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new FacultyListener(aInputPanel, aDialog, aHandler, aTable));
    }
}