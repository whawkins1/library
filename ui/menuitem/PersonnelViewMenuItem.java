package ui.menuitem;

import handler.DataHandler;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import ui.table.PersonnelTable;

import listener.action.PersonnelListener;

public final class PersonnelViewMenuItem extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public PersonnelViewMenuItem(final PersonnelTable aTable,
                                 final JComboBox<String> aCB, 
                                 final JFrame aFrame, 
                                 final DataHandler aHandler) {
        super("View");
        final boolean enabled = aTable.getSelectedRowCount() == 1;
        if(enabled) 
            addActionListener(new PersonnelListener(aTable, aCB, aFrame, "VIEW", aHandler));    
        setEnabled(enabled);        
    }
}