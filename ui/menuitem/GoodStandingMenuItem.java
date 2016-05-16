package ui.menuitem;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.GoodStandingDisplayListener;

public final class GoodStandingMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public GoodStandingMenuItem(final JFrame aFrame) {
        super("Good Standing");
        addActionListener(new GoodStandingDisplayListener(aFrame));
    }
}