package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JDialog;

import listener.action.PaymentsDisplayListener;

public final class PaymentsDisplayButton extends JButton{
    private static final long serialVersionUID = 1L;

    public PaymentsDisplayButton(final DataHandler aHandler, 
                                 final JDialog aDialog,
                                 final String aEmail) {
        super("Payments");
        addActionListener(new PaymentsDisplayListener(aDialog, aHandler, aEmail));
    }
}