package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JTable;

import listener.action.AudioListener;
import ui.dialog.AudioDialog;
import ui.panel.InventoryInputPanel;

public class AudioButton extends JButton {
    private static final long serialVersionUID = 1L;

    public AudioButton(final InventoryInputPanel aInfoPanel, 
                       final AudioDialog aDialog,
                       final DataHandler aHandler) {
      super(aDialog.getMode());
      aDialog.getRootPane().setDefaultButton(this);
      addActionListener(new AudioListener(aInfoPanel, aDialog, aHandler));
  }
    
    public AudioButton(final InventoryInputPanel aInfoPanel, 
            final AudioDialog aDialog,
            final DataHandler aHandler, 
            final JTable aTable) {
        super(aDialog.getMode());
        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new AudioListener(aInfoPanel, aDialog, aHandler, aTable));
        
    }
}