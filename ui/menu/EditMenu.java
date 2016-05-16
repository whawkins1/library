package ui.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;

import ui.menuitem.SettingsMenuItem;

public final class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public EditMenu(final JFrame aFrame) {
        super("Edit");
        add(new SettingsMenuItem(aFrame));
    }
}