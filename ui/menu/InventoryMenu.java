package ui.menu;

import javax.swing.JMenu;

import ui.menuitem.AudioMenuItem;
import ui.menuitem.BookMenuItem;
import ui.menuitem.VideoMenuItem;

public final class InventoryMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public InventoryMenu() {
        super("Inventory");
        add(new AudioMenuItem());
        add(new BookMenuItem());
        add(new VideoMenuItem());
    }
}