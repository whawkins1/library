package ui.table;

import handler.DataHandler;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JTable;

import log.Log;

import model.table.CheckoutReturnLogsTableModel;


public final class LogsReturnCheckoutTable extends JTable {
    private static final long serialVersionUID = 1L;
    
    public LogsReturnCheckoutTable(final DataHandler aHandler) {
        super(new CheckoutReturnLogsTableModel(new ArrayList<Log>(aHandler.getAllLogMap().values())));
        setShowGrid(false);
        setAutoCreateRowSorter(true);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        setIntercellSpacing(new Dimension(0, 0));
        setPreferredScrollableViewportSize(getPreferredSize());
        setFillsViewportHeight(true);
    }
}