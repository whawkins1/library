package ui.panel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.Utility;

public final class AboutInfoPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    
    public AboutInfoPanel() {
        super(new GridBagLayout()); 
        add(new JLabel("Year: 2016"), Utility.setConstraints(0, 2));
    }
}
