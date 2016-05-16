package ui.menu;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JMenu;

import ui.menuitem.AccountLogsMenuItem;
import ui.menuitem.CheckoutReturnsLogsMenuItem;
import ui.menuitem.GoodStandingMenuItem;
import ui.menuitem.OnHoldMenuItem;
import ui.menuitem.PaymentMenuItem;

public final class ViewMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public ViewMenu(final JFrame aFrame, 
                    final String aSigninEmail,
                    final DataHandler aHandler) {
         super("View");
         add(new CheckoutReturnsLogsMenuItem(aFrame, aHandler));
         add(new AccountLogsMenuItem(aFrame, aHandler));
         add(new OnHoldMenuItem(aFrame));
         add(new GoodStandingMenuItem(aFrame));
         add(new PaymentMenuItem(aFrame, aSigninEmail, aHandler));
     }
}