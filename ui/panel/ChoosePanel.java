package ui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.Utility;

public final class ChoosePanel extends JPanel {
    private static final long serialVersionUID = 1L;

public ChoosePanel(final JComboBox<String> aTypeCB, final JComboBox<String> aCategoryCB) {
    setLayout(new GridBagLayout());
    final GridBagConstraints c1 = Utility.setConstraints(0,0);
    final JLabel typeLabel = new JLabel("Type: ");   
    add(typeLabel, c1);
    final GridBagConstraints c2 = Utility.setConstraints(1,0);
    add(aTypeCB, c2);
    final GridBagConstraints c3 = Utility.setConstraints(0,1);
    final JLabel categoryLabel = new JLabel("Category: ");   
    add(categoryLabel, c3);
    final GridBagConstraints c4 = Utility.setConstraints(1,1);
    add(aCategoryCB, c4);
   }
}
