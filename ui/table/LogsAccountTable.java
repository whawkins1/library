package ui.table;

import handler.DataHandler;

import java.awt.Dimension;

import javax.swing.JTable;

import model.table.LogsAccountTableModel;


public final class LogsAccountTable extends JTable {
    private static final long serialVersionUID = 1L;
    public LogsAccountTable(final DataHandler aHandler) {
        super(new LogsAccountTableModel(aHandler));
        setShowGrid(false);
        setAutoCreateRowSorter(true);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        setIntercellSpacing(new Dimension(0, 0));
        setPreferredScrollableViewportSize(getPreferredSize());
        setFillsViewportHeight(true);
    }
}