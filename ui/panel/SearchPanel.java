package ui.panel;

import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import listener.action.ChooseCategoryListener;
import listener.action.ChooseTypeListener;
import ui.combobox.ComboBoxSearchType;
import utility.Utility;

public final class SearchPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JTextField textField_;
    private final ComboBoxSearchType typeCB_;
    private final JComboBox<String> categoryCB_;
    private final ButtonGroup bg_;
    
    public SearchPanel(final SearchFieldsPanel aFieldPanel) {
        super(new GridBagLayout());
        categoryCB_ = new JComboBox<String>();
        categoryCB_.addActionListener(new ChooseCategoryListener(aFieldPanel, categoryCB_));
        add(new JLabel("Term: "), Utility.setConstraints(0, 0));
        textField_ = new JTextField(15);
        add(textField_, Utility.setConstraints(1, 0));
        add(new JLabel("Type"), Utility.setConstraints(0, 1));
        typeCB_ = new ComboBoxSearchType();
        typeCB_.addActionListener(new ChooseTypeListener(typeCB_, categoryCB_));
        add(typeCB_, Utility.setConstraints(1, 1));
        add(new JLabel("Category"), Utility.setConstraints(0, 2));
        add(categoryCB_, Utility.setConstraints(1, 2));
        final JRadioButton anyRB = new JRadioButton("Any Terms");
        anyRB.setSelected(true);
        add(anyRB, Utility.setConstraints(0, 4));     
        final JRadioButton exactRB = new JRadioButton("Exact Prase");
        add(exactRB, Utility.setConstraints(1, 4));
        bg_ = new ButtonGroup();
        bg_.add(anyRB);
        bg_.add(exactRB);
    }
    
        public final String getTermText () { return textField_.getText().trim(); }
        
        
        public final String getTypeCBText () { return typeCB_.getSelectedItem().toString(); }
        
        
        public final String getCBCategoryText () { return categoryCB_.getSelectedItem().toString(); }
        
        
        public final ButtonGroup getButtonGroup () { return this.bg_; }
}