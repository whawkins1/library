package ui.table;

import handler.DataHandler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.CellRendererPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import Renderer.RemoveFocusRenderer;
import sort.RowSorterToggle;
import listener.mouse.MouseListenerInventory;

public final class InventoryTable extends JTable {
    private static final long serialVersionUID = 1L;
    private DefaultTableCellRenderer fNoDataRenderer;
    private JComboBox<String> fCB;
    
    public InventoryTable() {        
        setShowGrid(false);
        setAutoCreateRowSorter(true);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setIntercellSpacing(new Dimension(0, 0));
        setPreferredScrollableViewportSize(getPreferredSize());
        setFillsViewportHeight(true);
        setDefaultRenderer(Object.class, new RemoveFocusRenderer());
        setRowSorter(new RowSorterToggle());
    }
    
        public final void setMouseListener(final JComboBox<String> aCB, 
                                           final JFrame aFrame, 
                                           final DataHandler aHandler) {
            this.fCB = aCB;
            addMouseListener(new MouseListenerInventory(aCB, aFrame, aHandler));
        }
        
        public final boolean getScrollableTracksViewportHeight() {
            return getPreferredSize().height < getParent().getHeight();
        }
        
        
        @Override public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final String selection = fCB.getSelectedItem().toString();
            final boolean noSelection = selection.length() == 0;
            if(getRowCount() > 0 || noSelection)
                return;
            
            final Component comp = getNoDataRenderer();
            final CellRendererPane rendererPane  = getRendererPane();
            
            final Dimension size = getSize();
            final Dimension prefSize = comp.getPreferredSize();
            
            final Rectangle rect = new Rectangle((size.width = prefSize.width) / 2,
                                                  size.height / 4, prefSize.width, prefSize.height);
            rendererPane.paintComponent(g, comp, this, rect);
        }
        
        
        private final CellRendererPane  getRendererPane() {
            for (int i = 0; i < getComponentCount(); i++) {
                if (getComponent(i) instanceof CellRendererPane) {
                    return (CellRendererPane) getComponent(i);
                }
            }
            return null;
        }
        
        private final Component getNoDataRenderer() {
         
            if(fNoDataRenderer == null)
                fNoDataRenderer = createNoDataRenderer();
            
            final String localizedNoData  = UIManager.getString("No Data Available");
            
            final Component comp = fNoDataRenderer.getTableCellRendererComponent(this, 
                                                                                 localizedNoData != null ? localizedNoData : "No Data Available", 
                                                                                 false, 
                                                                                 false, 
                                                                                 -1, 
                                                                                 -1);
            final Font currentFont = getFont();
            final Font bigger = currentFont.deriveFont(Font.BOLD | Font.ITALIC, getFont().getSize2D() * 3);
            comp.setFont(bigger);
            return comp;
        }
        
        
        private final DefaultTableCellRenderer createNoDataRenderer() {
            final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setForeground(Color.GRAY);
            return renderer;
        }
}