package ui.popup;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import ui.menuitem.AccountLogsMenuItem;
import ui.menuitem.CheckoutReturnsLogsMenuItem;

public final class LogsPopup extends JPopupMenu  {
    private static final long serialVersionUID = 1L;

    public LogsPopup(final JFrame aFrame, final DataHandler aHandler) {
        add(new AccountLogsMenuItem(aFrame, aHandler));
        add(new CheckoutReturnsLogsMenuItem(aFrame, aHandler));
    }
}