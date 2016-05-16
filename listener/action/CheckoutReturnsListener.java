package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.LogsReturnCheckoutDialog;

public final class CheckoutReturnsListener implements ActionListener {
    private final JFrame frame_;
    private final DataHandler handler_;
    
    public CheckoutReturnsListener(final JFrame aFrame, final DataHandler aHandler) {
        this.frame_ = aFrame;
        this.handler_ = aHandler;
    }

    @Override public final void actionPerformed(final ActionEvent ae) {
        new LogsReturnCheckoutDialog(frame_, handler_);
    }
}
