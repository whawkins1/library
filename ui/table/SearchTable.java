package ui.table;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import sort.RowSorterToggle;
import Renderer.RemoveFocusRenderer;

public final class SearchTable extends JTable {

    private static final long serialVersionUID = 1L;
    
    public SearchTable () {
        setShowGrid(false);
        setAutoCreateRowSorter(true);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setIntercellSpacing(new Dimension(0, 0));
        setPreferredScrollableViewportSize(getPreferredSize());
        setFillsViewportHeight(true);
        setDefaultRenderer(Object.class, new RemoveFocusRenderer());
        setRowSorter(new RowSorterToggle());
    }
}
