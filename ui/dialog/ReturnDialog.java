package ui.dialog;


import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ui.panel.ReturnButtonPanel;
import ui.panel.ReturnInputPanel;

import utility.Utility;

public class ReturnDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public ReturnDialog(final JFrame aParent) {
         super(aParent, "Return", false);
         final JTextField checkoutIDTF = new JTextField(15);
         final JTextField callNumberTF = new JTextField(15);
         add(new ReturnInputPanel(checkoutIDTF), BorderLayout.CENTER);
         add(new ReturnButtonPanel(checkoutIDTF, callNumberTF, this), BorderLayout.SOUTH);
         pack();
         Utility.setCloseOperation(this);
         setLocationRelativeTo(aParent);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         setVisible(true);     
     }
}