package ui.menuitem;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.CheckoutReturnsListener;

public final class CheckoutReturnsLogsMenuItem extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public CheckoutReturnsLogsMenuItem(final JFrame aFrame, final DataHandler aHandler) {
        super("checkouts/returns");
        addActionListener(new CheckoutReturnsListener(aFrame, aHandler));
    }    
}