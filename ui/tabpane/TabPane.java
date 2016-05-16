package ui.tabpane;


import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import ui.panel.InventoryPanel;
import ui.panel.PersonnelPanel;

public final class TabPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    
    public TabPane(final DataHandler aHandler, final JFrame aFrame)   {
        super(JTabbedPane.TOP);
        add("Inventory", new InventoryPanel(aHandler, aFrame));
        add("Personnel", new PersonnelPanel(aHandler, aFrame));
    }
}