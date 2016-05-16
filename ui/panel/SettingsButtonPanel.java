package ui.panel;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import ui.button.SaveSettingsButton;
import ui.dialog.SettingsDialog;

public final class SettingsButtonPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    
    public SettingsButtonPanel(final SettingsDialog aDialog,
                               final SettingsInputPanel aInputPanel) {
        super(new FlowLayout(FlowLayout.CENTER));
        final SaveSettingsButton button = new SaveSettingsButton(aDialog, aInputPanel);
        add(button);
    }
}
