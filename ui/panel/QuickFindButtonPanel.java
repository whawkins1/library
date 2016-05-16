package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public final class QuickFindButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public QuickFindButtonPanel(final JButton aButton) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(aButton);
    }
}