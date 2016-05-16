package ui.menuitem;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import listener.action.PersonnelRemoveListener;

public final class PersonnelRemoveMenuItem extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public PersonnelRemoveMenuItem(final JTable aTable, final JComboBox<String> aCB) {
        super("Remove");
        addActionListener(new PersonnelRemoveListener(aTable, aCB));
    }
}