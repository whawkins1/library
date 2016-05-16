package listener.action;

import handler.DataHandler;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.dialog.PaymentsViewDialog;


public final class PaymentsDisplayListener 
                            implements ActionListener {
    private final DataHandler fHandler;
    private final Window fParent;
    private final String fEmail;
   
    public PaymentsDisplayListener(final Window aParent, 
                                   final DataHandler aHandler,
                                   final String aEmail) {
        this.fHandler = aHandler;
        this.fParent = aParent;
        this.fEmail = aEmail;
    }
    
        @Override public final void actionPerformed(final ActionEvent event) {
            new PaymentsViewDialog(fParent, fHandler, fEmail);
        }
}