package ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.panel.SettingsButtonPanel;
import ui.panel.SettingsInputPanel;
import utility.Utility;

public final class SettingsDialog extends JDialog{
    private static final long serialVersionUID = 1L;
    private boolean fClosed;
    
    public SettingsDialog(final JFrame aParent) {
        super(aParent, "Settings", true);
        final SettingsInputPanel inputPanel = new SettingsInputPanel();
        add(inputPanel, BorderLayout.CENTER);
        add(new SettingsButtonPanel(this, inputPanel), BorderLayout.SOUTH);
        Utility.setCloseOperation(this);
        setIconImage(Utility.getSmallImage());  
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);  
    }
    
        public final boolean showDialog() {
            return fClosed;
        }
        
        
        public final void setClosed (final boolean aClosed) {
            this.fClosed = aClosed;
        }
}