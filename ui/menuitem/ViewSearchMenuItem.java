package ui.menuitem;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public final class ViewSearchMenuItem  extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public ViewSearchMenuItem(final JTable aTable, final boolean aEnable) {
        super("View");
        setEnabled(aEnable);
    }
}