package ui.menuitem;

import java.awt.Window;

import handler.DataHandler;

import javax.swing.JMenuItem;

import listener.action.PaymentsDisplayAllListener;


public final class PaymentMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public PaymentMenuItem(final Window aWindow, 
                           final String aSigninEmail, 
                           final DataHandler aHandler) {
        super("Payments");
        addActionListener(new PaymentsDisplayAllListener(aWindow, aHandler));        
    }
}