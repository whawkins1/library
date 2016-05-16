package Renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public final class RemoveFocusRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    @Override public Component getTableCellRendererComponent(final JTable table, 
                                                              final Object value, 
                                                              final boolean isSelected, 
                                                              final boolean hasFocus, 
                                                              final int row, 
                                                              final int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBorder(noFocusBorder);
        return this;        
    }
}
