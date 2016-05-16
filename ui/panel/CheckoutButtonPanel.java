package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import ui.button.ConfirmCheckoutButton;
import ui.dialog.CheckoutDialog;

public final class CheckoutButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public CheckoutButtonPanel(final CheckoutDialog aDialog, final String aSigninEmail) {
        super(new FlowLayout(FlowLayout.CENTER));        
        add(new ConfirmCheckoutButton(aDialog, aSigninEmail));
    }
}