package ui.dialog;


import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.panel.CheckoutButtonPanel;
import ui.panel.CheckoutInputPanel;

import utility.Utility;


public final class CheckoutDialog  extends JDialog {
    private static final long serialVersionUID = 1L;
    final JTextField fCallNumField;
    final JTextField fEmailField;
    
    public CheckoutDialog(final JFrame aParent, final String aSigninEmail) {
        super(aParent, "Checkout", false);
    	fCallNumField = new JTextField(30);
    	fEmailField = new JTextField(30);
    	add(new CheckoutInputPanel(this), BorderLayout.CENTER);
    	final JPanel buttonPanel = new CheckoutButtonPanel(this, aSigninEmail);
    	add(buttonPanel, BorderLayout.SOUTH);    
    	Utility.setCloseOperation(this);
    	pack();
    	setLocationRelativeTo(aParent);
    	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	setVisible(true);
    }
    
       public final JTextField getCallNumberTF() {
           return this.fCallNumField;
       }
        
        
        public final JTextField getEmailTF() {
            return this.fEmailField;
        }
        
        public final void setFocusCallNumber() {
            this.fCallNumField.selectAll();
            this.fCallNumField.requestFocusInWindow();
        }
        
        
        public final void setFocusEmail() {
            this.fEmailField.selectAll();
            this.fEmailField.requestFocusInWindow();
        }
	}
