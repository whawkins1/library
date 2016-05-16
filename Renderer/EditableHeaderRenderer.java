package Renderer;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import adapter.mouse.MouseEventReposter;

public final class EditableHeaderRenderer 
                         implements TableCellRenderer {

    private JTable table_ = null;
    private MouseEventReposter reporter_ = null;
    private JComponent editor_;
    
    public EditableHeaderRenderer(final JComponent aEditor) {
        this.editor_ = aEditor;
        this.editor_.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    }
    
    @Override public Component getTableCellRendererComponent(final JTable aTable, final Object value,
                                                              final boolean isSelected, final boolean hasFocus, 
                                                              final int row, final int column) {
        final boolean validTable = ((aTable != null) && (this.table_ != aTable));
        if (validTable) {
            this.table_ = aTable;
            final JTableHeader header = aTable.getTableHeader();
            if (header != null) {
                this.editor_.setForeground(header.getForeground());
                this.editor_.setBackground(header.getBackground());
                this.editor_.setFont(header.getFont());
                reporter_ = new MouseEventReposter(header, column, this.editor_);
                header.addMouseListener(reporter_);
            }
            if (reporter_ != null)
                reporter_.setColumn(column);
        }
        return this.editor_;
    }
}