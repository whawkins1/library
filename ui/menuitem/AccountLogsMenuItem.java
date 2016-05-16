package ui.menuitem;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.AccountLogsListener;

public final class AccountLogsMenuItem extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public AccountLogsMenuItem(final JFrame aFrame, final DataHandler aHandler) {
        super("Account Access");
        addActionListener(new AccountLogsListener(aFrame, aHandler));
    }
}