package ui.table;

import java.awt.Dimension;

import javax.swing.JTable;

import model.table.GoodStandingTableModel;

public final class GoodStandingTable extends JTable {
    private static final long serialVersionUID = 1L;

        public GoodStandingTable() {
            super(new GoodStandingTableModel());
            setShowGrid(false);
            setAutoCreateRowSorter(true);
            setColumnSelectionAllowed(false);
            setRowSelectionAllowed(false);
            setIntercellSpacing(new Dimension(0, 0));
            setPreferredScrollableViewportSize(getPreferredSize());
            setFillsViewportHeight(true);
        }
}
