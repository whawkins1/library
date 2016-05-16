package ui.table;

import handler.DataHandler;

import java.awt.Dimension;

import javax.swing.JTable;

import model.table.PaymentsAllModel;
import Renderer.RemoveFocusRenderer;

public final class PaymentsAllTable extends JTable{
    private static final long serialVersionUID = 1L;
    
    public PaymentsAllTable(final DataHandler aHandler) {
        super(new PaymentsAllModel(aHandler));
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
