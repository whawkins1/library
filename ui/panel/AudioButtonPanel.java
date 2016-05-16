package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import ui.button.AudioButton;
import ui.dialog.AudioDialog;

public final class AudioButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

        public AudioButtonPanel(final InventoryInputPanel aInfoPanel, 
                                final AudioDialog aDialog, 
                                final DataHandler aHandler) {
        super(new FlowLayout(FlowLayout.CENTER));
        
        add(new AudioButton(aInfoPanel, aDialog, aHandler));
        
    }
        
        public AudioButtonPanel(final InventoryInputPanel aInfoPanel, 
                final AudioDialog aDialog, 
                final DataHandler aHandler, 
                final JTable aTable) {
            super(new FlowLayout(FlowLayout.CENTER));
            add(new AudioButton(aInfoPanel, aDialog, aHandler, aTable));
        }
        
}