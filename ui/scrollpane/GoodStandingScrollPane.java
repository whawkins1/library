package ui.scrollpane;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import ui.table.GoodStandingTable;

public final class GoodStandingScrollPane extends JScrollPane{
    private static final long serialVersionUID = 1L;
    
    public GoodStandingScrollPane(final GoodStandingTable aTable) {
         super(aTable);
         setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
         setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         final JViewport vp = getViewport();
         vp.setBackground(Color.WHITE);      
    }
}