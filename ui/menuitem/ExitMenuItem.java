package ui.menuitem;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.ExitListener;

public final class ExitMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public ExitMenuItem(final JFrame aFrame, final String aEmail) {
        super("Exit");
        addActionListener(new ExitListener(aFrame, aEmail));
    }
}
