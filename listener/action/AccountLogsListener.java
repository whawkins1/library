package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.dialog.LogsAccountDialog;

public final class AccountLogsListener 
                        implements ActionListener  {
    private final DataHandler handler_;
    private final JFrame frame_;
   public AccountLogsListener(final JFrame aFrame, final DataHandler aHandler) {
       this.frame_ = aFrame;
       this.handler_ = aHandler;
   }

    @Override  public final  void actionPerformed(final ActionEvent ae) {
       new LogsAccountDialog(frame_, handler_);        
    }
}