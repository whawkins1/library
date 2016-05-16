package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import ui.dialog.SearchDialog;

public final class SearchDisplayListener 
                        implements ActionListener{
   private final JFrame fParent;
   private final JTabbedPane fTabPane;
   private final DataHandler handler_;
    
   public SearchDisplayListener(final JFrame aParent, 
                                final JTabbedPane aTabPane,
                                final DataHandler aHandler) {
       this.fParent = aParent;
       this.fTabPane = aTabPane;
       this.handler_ = aHandler;
   }
   
   
        @Override public final  void actionPerformed(final ActionEvent event) {
            new SearchDialog(fParent, fTabPane, handler_);            
        }
}