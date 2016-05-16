package ui.menuitem;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.CategoryAddListener;

public final class AddMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public AddMenuItem(final JFrame aFrame, final DataHandler aHandler) {
        super("Add");
        addActionListener(new CategoryAddListener(aFrame, aHandler));
    }
}