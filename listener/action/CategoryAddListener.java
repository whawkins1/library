package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.ChooseCategoryDialog;


public final class CategoryAddListener implements ActionListener {
    private final JFrame fFrame;
    private final DataHandler handler_;
    
    public CategoryAddListener(final JFrame aFrame, final DataHandler aHandler) {
        this.fFrame = aFrame;
        this.handler_ = aHandler;
    }

        @Override public final void actionPerformed(final ActionEvent ae) {
            new ChooseCategoryDialog(fFrame, handler_);           
        }
}