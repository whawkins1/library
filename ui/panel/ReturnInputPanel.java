package ui.panel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utility.Utility;

public final class ReturnInputPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    public ReturnInputPanel(final JTextField aCheckoutIDTF) {
           setLayout(new GridBagLayout());
           add(new JLabel("Checkout ID: "), Utility.setConstraints(0, 0));
           add(aCheckoutIDTF, Utility.setConstraints(1, 0));
       }
}