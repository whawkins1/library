package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import ui.button.VideoButton;
import ui.dialog.VideoDialog;

public final class VideoButtonPanel extends JPanel{
        private static final long serialVersionUID = 1L;
    
        public VideoButtonPanel( final InventoryInputPanel aInfoPanel, 
                                 final VideoDialog aDialog, 
                                 final DataHandler aHandler) {
        super(new FlowLayout(FlowLayout.CENTER));

        add(new VideoButton(aInfoPanel, aDialog, aHandler));                       
  }
        
        public VideoButtonPanel( final InventoryInputPanel aInfoPanel, 
                final VideoDialog aDialog, 
                final DataHandler aHandler, 
                final JTable aTable) {
           super(new FlowLayout(FlowLayout.CENTER));

          add(new VideoButton(aInfoPanel, aDialog, aHandler, aTable));                       
       } 

}