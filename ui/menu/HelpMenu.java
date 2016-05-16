package ui.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;

import ui.menuitem.AboutMenuItem;

public final class HelpMenu extends JMenu {
    private static final long serialVersionUID = 1L;
    
    public HelpMenu(final JFrame aFrame) {
        super("Help");
        final AboutMenuItem aboutMenuItem = new AboutMenuItem(aFrame);
        add(aboutMenuItem);
    }
}