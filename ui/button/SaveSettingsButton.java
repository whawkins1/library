package ui.button;

import javax.swing.JButton;
import javax.swing.JRootPane;

import listener.action.SaveSettingsListener;
import ui.dialog.SettingsDialog;
import ui.panel.SettingsInputPanel;

public final class SaveSettingsButton extends JButton {
    private static final long serialVersionUID = 1L;

    public SaveSettingsButton(final SettingsDialog aDialog, 
                              final SettingsInputPanel aInputPanel) {
        super("Save");
        addActionListener(new SaveSettingsListener(aDialog, aInputPanel));
        final JRootPane rp = aDialog.getRootPane();
        rp.setDefaultButton(this);
    }
}
