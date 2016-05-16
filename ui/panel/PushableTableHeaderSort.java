package ui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public final class PushableTableHeaderSort extends JPanel {
    private static final long serialVersionUID = 1L;
    TableRowSorter<AbstractTableModel> fSorter;
   
    public PushableTableHeaderSort(final TableColumn aColumn,
                                  final JTableHeader aHeader) {
       fSorter = null;
       setLayout(new BorderLayout());
       final ButtonHeaderRenderer renderer = new ButtonHeaderRenderer();
       aColumn.setHeaderRenderer(renderer);
       final HeaderListener listener = new HeaderListener(aHeader, renderer);
       aHeader.addMouseListener(listener);
   }
   
   public final class HeaderListener extends MouseAdapter {
       private final JTableHeader fHeader;
       private ButtonHeaderRenderer fRenderer;
       int fColumn = -1;
       private SortOrder fCurrentOrder = SortOrder.UNSORTED;
       
       
       HeaderListener(final JTableHeader aHeader, final ButtonHeaderRenderer aRenderer) {
            this.fHeader = aHeader;
            this.fRenderer = aRenderer;
       }
       
       @Override
       public final void mousePressed(final MouseEvent e) {
           fColumn = fHeader.columnAtPoint(e.getPoint());
           fRenderer.setPressedColumn(fColumn);
           fHeader.repaint();
       }
       
       @Override
       public final void mouseReleased(final MouseEvent e) {
           fColumn = fHeader.columnAtPoint(e.getPoint());
           List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
           switch(fCurrentOrder) {
           case UNSORTED:
               sortKeys.add(new RowSorter.SortKey(fColumn, fCurrentOrder = SortOrder.ASCENDING));
               break;
           case ASCENDING:
               sortKeys.add(new RowSorter.SortKey(fColumn, fCurrentOrder = SortOrder.DESCENDING));
               break;
           case DESCENDING:
               sortKeys.add(new RowSorter.SortKey(fColumn,  fCurrentOrder = SortOrder.UNSORTED));
               break;
           }
           fSorter.setSortKeys(sortKeys);
           fRenderer.setPressedColumn(fColumn);
           fHeader.repaint();
       }
   }
   
   final class ButtonHeaderRenderer implements TableCellRenderer {
       private int pushedColumn;
       private final JButton fBtn;
       private ButtonModel fModel;
       
       ButtonHeaderRenderer() {
           pushedColumn = -1;
           fBtn = new JButton();
           fBtn.setMargin(new Insets(0,0,0,0));
           fModel = null;
       }
       
       @Override
       public final Component getTableCellRendererComponent(JTable table,
                                                            Object value,
                                                            boolean isSelected,
                                                            boolean hasFocused,
                                                            int row,
                                                            int column) {
           final String btnTxt = ((value == null) ? "" : value.toString());
           fBtn.setText(btnTxt);
           final boolean isPressed = (column == pushedColumn);
           fModel = fBtn.getModel();
           fModel.setPressed(isPressed);
           fModel.setArmed(isPressed);
           return fBtn;
       }
       
       public final void setPressedColumn(final int aColumn) {
           pushedColumn = aColumn;
       }
   }
   
   public final void setRowSorter(final TableRowSorter<AbstractTableModel> aSorter) {
       this.fSorter = aSorter;   
   }
}
