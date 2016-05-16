package ui.panel;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listener.action.QuickFindActionListener;
import listener.item.QuickFindItemListener;
import radiobutton.QuickFindRB;
import ui.dialog.QuickFindDialog;

public final class QuickFindInputPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private boolean fSelected;
    
    public QuickFindInputPanel(final DataHandler aHandler, 
                               final QuickFindDialog aDialog,
                               final int aIsAdmin) {
        super(new BorderLayout());
        fSelected = false;
        final ButtonGroup bg = new ButtonGroup();
        final JTextField tf = new JTextField(18);
        final JLabel label = new JLabel();
        final QuickFindActionListener actionListener = new QuickFindActionListener(bg, tf, aHandler, aIsAdmin);
        final QuickFindItemListener personnelListener = new QuickFindItemListener(this, aDialog, actionListener, tf, label, "Email: " );
        final QuickFindRB personnelRB = new QuickFindRB("Personnel", personnelListener); 
        bg.add(personnelRB);
        final QuickFindItemListener inventoryListener = new QuickFindItemListener(this, aDialog, actionListener, tf, label, "Call Number: ");
        final QuickFindRB inventoryRB = new QuickFindRB("Inventory", inventoryListener); 
        bg.add(inventoryRB);
        final JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.add(personnelRB); 
        radioButtonPanel.add(inventoryRB);
        add(radioButtonPanel, BorderLayout.NORTH);
        radioButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select Category"));
    }
    
        public final void setIsSelected () { this.fSelected = true; }
        
        
        public final boolean isSelected () { return this.fSelected; }
}