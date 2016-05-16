package ui.menu;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JMenu;

import ui.menuitem.AddMenuItem;

public final class AccountsMenu extends JMenu {
    private static final long serialVersionUID = 1L;
    
    public AccountsMenu(final JFrame aFrame, final DataHandler aHandler) {
        super("Accounts");
        add(new AddMenuItem(aFrame, aHandler));
    }
}