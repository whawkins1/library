package listener.action;

import handler.DataHandler;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.dialog.PaymentsViewAllDialog;

public final class PaymentsDisplayAllListener 
                            implements ActionListener {

    private final DataHandler fHandler;
    private final Window fParent;
    
    public PaymentsDisplayAllListener(final Window aParent, 
                                   final DataHandler aHandler) {
        this.fHandler = aHandler;
        this.fParent = aParent;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
            new PaymentsViewAllDialog(fParent, fHandler);
        }

}
