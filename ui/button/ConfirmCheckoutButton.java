package ui.button;

import javax.swing.JButton;
import javax.swing.JRootPane;

import ui.dialog.CheckoutDialog;
import listener.action.ConfirmCheckoutListener;

public final class ConfirmCheckoutButton extends JButton {
    private static final long serialVersionUID = 1L;

public ConfirmCheckoutButton(final CheckoutDialog aDialog, final String aSigninEmail) {
    super("Checkout");
    final JRootPane rp = aDialog.getRootPane();
    rp.setDefaultButton(this);
    addActionListener(new ConfirmCheckoutListener(aDialog, aSigninEmail));
   }
}