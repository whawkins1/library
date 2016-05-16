package ui.button;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import listener.action.ReturnDialogListener;

public final class ReturnDialogButton  extends JButton {
    private static final long serialVersionUID = 1L;

public ReturnDialogButton(final JTextField aCheckoutIDTF, 
                          final JTextField aCallNumberTF, 
                          final JDialog aDialog) {
       super("CHECKOUT");
       final JRootPane rp = aDialog.getRootPane();
       rp.setDefaultButton(this);
       aDialog.getRootPane().setDefaultButton(this);
       addActionListener(new ReturnDialogListener(aCheckoutIDTF, aCallNumberTF, aDialog));
   }
}