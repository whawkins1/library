package ui.panel;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.combobox.ComboBoxPersonnel;
import ui.scrollpane.ScrollPane;
import ui.table.PersonnelTable;

public final class PersonnelPanel extends JPanel   {
    private static final long serialVersionUID = 1L;
    public PersonnelPanel(final DataHandler aHandler, final JFrame aFrame) {
        super(new BorderLayout());
        final PersonnelTable table = new PersonnelTable();
        final ComboBoxPersonnel cb = new ComboBoxPersonnel(aHandler, table);
        table.setMouseListener(table, cb, aFrame, aHandler);
        final PersonnelCBPanel personnelCBPanel = new PersonnelCBPanel(cb);
        add(personnelCBPanel, BorderLayout.NORTH);
        add(new ScrollPane(table), BorderLayout.CENTER);
     }
}