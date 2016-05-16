package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.button.ReturnDialogButton;

public final class ReturnButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public ReturnButtonPanel(final JTextField aCheckoutTF,
                             final JTextField aCallNumberTF,
                             final JDialog aDialog) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new ReturnDialogButton(aCheckoutTF, aCallNumberTF, aDialog));    
    }
}
