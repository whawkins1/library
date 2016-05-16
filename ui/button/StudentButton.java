package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JRootPane;

import listener.action.StudentListener;
import ui.dialog.StudentDialog;
import ui.panel.PersonnelInputPanel;
import ui.table.PersonnelTable;


public final class StudentButton extends JButton {
    private static final long serialVersionUID = 1L;
    public StudentButton(final PersonnelInputPanel aInfoPanel, 
                         final StudentDialog aDialog,
                         final DataHandler aHandler) {
        super(aDialog.getMode());
        final JRootPane rp = aDialog.getRootPane();
        rp.setDefaultButton(this);
        addActionListener(new StudentListener(aInfoPanel, aDialog, aHandler));
    }
    
    public StudentButton(final PersonnelInputPanel aInfoPanel, 
            final StudentDialog aDialog,
            final DataHandler aHandler,
            final PersonnelTable aTable) {
        super(aDialog.getMode());
        final JRootPane rp = aDialog.getRootPane();
        rp.setDefaultButton(this);
addActionListener(new StudentListener(aInfoPanel, aDialog, aHandler, aTable));
}
}