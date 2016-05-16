package sort;

import java.util.List;

import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public final class RowSorterToggle extends TableRowSorter<AbstractTableModel> {
    public RowSorterToggle() {}
    
    @Override public final void toggleSortOrder(final int aColumn) {
        final List<? extends SortKey> sortKeys = getSortKeys();
        if (sortKeys.size() > 0) {
            final SortKey currentKey = sortKeys.get(0);
            final SortOrder currentOrder = currentKey.getSortOrder();
            if (currentOrder == SortOrder.DESCENDING) {
                setSortKeys(null);
                return;
            }            
        }
        super.toggleSortOrder(aColumn);
    }
}