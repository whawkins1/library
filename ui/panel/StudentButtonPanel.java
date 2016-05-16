package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import ui.button.StudentButton;
import ui.dialog.StudentDialog;
import ui.table.PersonnelTable;

public final class StudentButtonPanel extends JPanel  {
    private static final long serialVersionUID = 1L;
    public StudentButtonPanel(final PersonnelInputPanel aInputPanel, 
                               final StudentDialog aDialog,
                               final DataHandler aHandler) {
           super(new FlowLayout(FlowLayout.CENTER));
           add( new StudentButton(aInputPanel, aDialog, aHandler));                       
       }
    
    public StudentButtonPanel(final PersonnelInputPanel aInputPanel, 
            final StudentDialog aDialog,
            final DataHandler aHandler,
            final PersonnelTable aTable) {
super(new FlowLayout(FlowLayout.CENTER));
add( new StudentButton(aInputPanel, aDialog, aHandler, aTable));                       
}
}