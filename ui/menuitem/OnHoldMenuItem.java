package ui.menuitem;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.OnHoldDisplayListener;

public final class OnHoldMenuItem extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public OnHoldMenuItem(final JFrame aFrame) {
        super("On Hold");
        addActionListener(new OnHoldDisplayListener(aFrame));
    }
}