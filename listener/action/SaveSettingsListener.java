package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import ui.dialog.SettingsDialog;
import ui.panel.SettingsInputPanel;
import utility.Utility;

public final class SaveSettingsListener 
                               implements ActionListener {
    private final SettingsDialog fParent;
    private final SettingsInputPanel fInputPanel;
    
    public SaveSettingsListener(final SettingsDialog aParent, 
                                final SettingsInputPanel aInputPanel) {
        this.fParent = aParent;
        this.fInputPanel = aInputPanel;
    }
    
        @Override public void actionPerformed(final ActionEvent event) { 
            final String url = fInputPanel.getURL();
            final String username = fInputPanel.getUsername();
            final String password = fInputPanel.getPassword();
            final String confirmPassword = fInputPanel.getConfirmPassword();
            
            final boolean validData = ((url.length() > 0) && 
                                       (username.length() > 0) && 
                                       (password.length() > 0) &&
                                       (confirmPassword.length() > 0));
            
            
            if (validData) {
                final boolean passwordsMatch = (password.equals(confirmPassword));
                if (passwordsMatch) {
                    final Preferences prefs = Preferences.userNodeForPackage(Utility.class);
                    prefs.put("URL", url);
                    prefs.put("USERNAME", username);
                    prefs.put("PASSWORD", password);
                    fParent.setClosed(true);
                    fParent.dispose();
                    return;
                }
                Utility.showErrorMessage(fParent, "Passwords Do not Match, Please Try Again.");
                return;
            } 
                Utility.showErrorMessage(fParent, "All Fields Must Be Entered Please Try Again");
        }
}