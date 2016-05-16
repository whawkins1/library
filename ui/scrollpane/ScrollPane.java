package ui.scrollpane;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

public final class ScrollPane extends JScrollPane {
    private static final long serialVersionUID = 1L;

    public ScrollPane(final JTable aTable) {
        super(aTable);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        final JViewport vp = getViewport();
        vp.setBackground(Color.WHITE);        
    }
}