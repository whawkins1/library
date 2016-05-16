package ui.table;

import java.awt.Dimension;

import javax.swing.JTable;

import model.table.OnHoldTableModel;

public final class OnHoldTable extends JTable {
   
    private static final long serialVersionUID = 1L;

    public OnHoldTable() {
        super(new OnHoldTableModel());
        setShowGrid(false);
        setAutoCreateRowSorter(true);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        setIntercellSpacing(new Dimension(0, 0));
        setPreferredScrollableViewportSize(getPreferredSize());
        setFillsViewportHeight(true);
    }
}