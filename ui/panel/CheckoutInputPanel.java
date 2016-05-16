package ui.panel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.dialog.CheckoutDialog;

import utility.Utility;

public final class CheckoutInputPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public CheckoutInputPanel(final CheckoutDialog aDialog) {
        setLayout(new GridBagLayout());
        add(new JLabel("Call Number:  "), Utility.setConstraints(0, 0));
        add(aDialog.getCallNumberTF(), Utility.setConstraints(1, 0));
        add(new JLabel("Email:  "), Utility.setConstraints(0, 1));
        add(aDialog.getEmailTF(), Utility.setConstraints(1, 1));
    }
}