package ui.scrollpane;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import ui.table.OnHoldTable;

public final class OnHoldScrollPane extends JScrollPane {
    private static final long serialVersionUID = 1L;
    
    public OnHoldScrollPane(final OnHoldTable aTable) {
        super(aTable);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        final JViewport vp = getViewport();
        vp.setBackground(Color.WHITE);      
    }
}