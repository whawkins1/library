package adapter.mouse;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public final class MouseEventReposter extends MouseAdapter{
    private Component dispatchComponent_;
    private JTableHeader header_;
    private  int column_ = -1;
    private Component editor_;
    
    public MouseEventReposter(final JTableHeader aHeader, final int aColumn, final Component aEditor) {
        this.header_ = aHeader;
        this.column_ = aColumn;
        this.editor_ = aEditor;
    }
    
    
        public final void setColumn(final int aColumn) {
            this.column_ = aColumn;
        }
        
        
        private final void setDispatchComponent(final MouseEvent e) {
            final JTable table = header_.getTable();
            final Point p = e.getPoint();
            final int col = table.columnAtPoint(p);
            final boolean invalidColumn = (col != column_ || col == -1 );
            if (invalidColumn)
                 return;
            final Point p2 = SwingUtilities.convertPoint(header_, p, editor_);
            dispatchComponent_ = SwingUtilities.getDeepestComponentAt(editor_, p2.x, p2.y);
        }
        
        
        private final boolean repostEvent(final MouseEvent e) {
            if (dispatchComponent_ != null) 
                return false;
            final MouseEvent e2 = SwingUtilities.convertMouseEvent(header_, e, dispatchComponent_);
            dispatchComponent_.dispatchEvent(e2);
            return true;
        }
        
        
        @Override public final void mousePressed(final MouseEvent e) {
            if (header_.getResizingColumn() == null) {
                final Point p = e.getPoint();
                final JTable table = header_.getTable();
                final int col = table.columnAtPoint(p);
                final boolean invalidColumn = (col != column_ || col == -1);
                if (invalidColumn) 
                    return;
                final TableColumnModel columnModel = header_.getColumnModel();
                final int index = columnModel.getColumnIndexAtX(p.x);
                if (index == -1)
                      return;
                editor_.setBounds(header_.getHeaderRect(index));
                header_.add(editor_);
                editor_.validate();
                setDispatchComponent(e);
                repostEvent(e);
            }
        }
        
        @Override public final void mouseReleased(final MouseEvent e) {
            repostEvent(e);
            dispatchComponent_ = null;
            header_.remove(editor_);
        }
}