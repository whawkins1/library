package ui.table;

import handler.DataHandler;

import java.awt.Dimension;

import javax.swing.JTable;

import model.table.CheckedoutModel;
import Renderer.RemoveFocusRenderer;

public final class CheckedoutTable extends JTable {
    private static final long serialVersionUID = 1L;

    public CheckedoutTable(final DataHandler aHandler, final String aEmail) {
        super(new CheckedoutModel(aHandler, aEmail));
        setShowGrid(false);
        setAutoCreateRowSorter(true);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        setIntercellSpacing(new Dimension(0, 0));
        setPreferredScrollableViewportSize(getPreferredSize());
        setDefaultRenderer(Object.class, new RemoveFocusRenderer());
        setFillsViewportHeight(true);
    }
}