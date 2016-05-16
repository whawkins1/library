package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.combobox.ComboBoxChooseType;
import ui.panel.ChooseButtonPanel;
import ui.panel.ChoosePanel;
import utility.Utility;


public final class ChooseCategoryDialog extends JDialog  {
    private static final long serialVersionUID = 1L;
	
    public ChooseCategoryDialog( final JFrame aParent, final DataHandler aHandler) {
        super(aParent, "Select Add", false);
        final JComboBox<String> categoryCB = new JComboBox<String>();
        final ComboBoxChooseType typeCB = new ComboBoxChooseType(categoryCB);
        Utility.setCloseOperation(this);
        add(new ChooseButtonPanel(this, aParent, categoryCB, typeCB, aHandler), BorderLayout.SOUTH);
        add(new ChoosePanel(typeCB, categoryCB ), BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	pack();
    	setLocationRelativeTo(aParent);
    	setVisible(true);
    }  
}