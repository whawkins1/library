package ui.panel;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public final class SearchButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public SearchButtonPanel(final JButton aButton) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(aButton);            
    }    
}