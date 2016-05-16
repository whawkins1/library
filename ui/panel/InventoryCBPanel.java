package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class InventoryCBPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public InventoryCBPanel(final JComboBox<String> aCB) {
        super(new FlowLayout(FlowLayout.LEADING));
        add(new JLabel("Type: "));
        add(aCB);
    }
}