package ui.table;

import handler.DataHandler;

import java.awt.Dimension;

import javax.swing.JTable;

import model.table.PaymentsModel;
import Renderer.RemoveFocusRenderer;

public final class PaymentsTable extends JTable {
    private static final long serialVersionUID = 1L;

    public PaymentsTable(final DataHandler aHandler, final String aEmail) {
        super(new PaymentsModel(aHandler, aEmail));
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