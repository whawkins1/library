package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import ui.button.FacultyButton;
import ui.dialog.FacultyDialog;
import ui.table.PersonnelTable;


public final class FacultyButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public FacultyButtonPanel(final PersonnelInputPanel aInputPanel, 
                               final FacultyDialog aDialog,
                               final DataHandler aHandler) {
    super(new FlowLayout(FlowLayout.CENTER));
    add(new FacultyButton(aInputPanel, aDialog, aHandler));           
  }
    
    public FacultyButtonPanel(final PersonnelInputPanel aInputPanel, 
            final FacultyDialog aDialog,
            final DataHandler aHandler,
            final PersonnelTable aTable) {
            super(new FlowLayout(FlowLayout.CENTER));
            add(new FacultyButton (aInputPanel, aDialog, aHandler, aTable));                       
            }
}