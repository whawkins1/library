package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JTable;

import listener.action.VideoListener;
import ui.dialog.VideoDialog;
import ui.panel.InventoryInputPanel;

public final class VideoButton extends JButton {
    private static final long serialVersionUID = 1L;

        public VideoButton(final InventoryInputPanel aInfoPanel, 
                           final VideoDialog aDialog,
                           final DataHandler aHandler) {
            super(aDialog.getMode());
            
            aDialog.getRootPane().setDefaultButton(this);
            addActionListener(new VideoListener(aInfoPanel, aDialog, aHandler));
    }
        
        public VideoButton(final InventoryInputPanel aInfoPanel, 
                final VideoDialog aDialog,
                final DataHandler aHandler,
                final JTable aTable) {
        super(aDialog.getMode());
        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new VideoListener(aInfoPanel, aDialog, aHandler, aTable));
    }
}