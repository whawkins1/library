package ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ui.scrollpane.ScrollPane;
import ui.table.InventoryTable;

public final class SearchTabPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public SearchTabPanel(final InventoryTable aTable) {
        super(new BorderLayout());
        //table.setMouseListener(cb, aFrame, aHandler);
        add(new ScrollPane(aTable), BorderLayout.CENTER);
    }        
}