package ui.panel;

import java.awt.GridBagLayout;
import java.util.prefs.Preferences;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utility.Utility;

public final class SettingsInputPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private final JTextField fURLTF;
    private final JTextField fUsernameTF;
    private final JPasswordField fPasswordField;
    private final JPasswordField fConfirmPasswordField;
    
    public SettingsInputPanel() {
        super(new GridBagLayout());
        final Preferences prefs = Preferences.userNodeForPackage(Utility.class);
        
        fURLTF = new JTextField(20);
        add(new JLabel("URL: "), Utility.setConstraints(0, 0));
        add(fURLTF, Utility.setConstraints(1, 0));
        fUsernameTF = new JTextField(20);
        add(new JLabel("Username: "), Utility.setConstraints(0, 1));
        add(fUsernameTF, Utility.setConstraints(1, 1));
        add(new JLabel("Password: "), Utility.setConstraints(0, 2));
        fPasswordField = new JPasswordField(20);
        add(fPasswordField, Utility.setConstraints(1, 2));
        add(new JLabel("Confirm: "), Utility.setConstraints(0, 3));
        fConfirmPasswordField = new JPasswordField(20);
        add(fConfirmPasswordField, Utility.setConstraints(1, 3));
        
        final boolean settingsExists = (prefs.get("URL", null) != null) && 
                                       (prefs.get("USERNAME", null) != null);
        
        if (settingsExists) {
           final String url = prefs.get("URL", ""); 
           final String username = prefs.get("USERNAME", "");
           fURLTF.setText(url);
           fUsernameTF.setText(username);
        }
    }
    
        public final String getURL() { return fURLTF.getText().trim(); }

        
        public final String getUsername() { return fUsernameTF.getText().trim(); }
        
        
        public final String getPassword() {
            final char[] passwordCharArr = fPasswordField.getPassword();
            final String password = String.valueOf(passwordCharArr);
            
            return password;
        }
        
        
        public final String getConfirmPassword() {
            final char[] passwordCharArr = fConfirmPasswordField.getPassword();
            final String confirmPassword = String.valueOf(passwordCharArr);
            
            return confirmPassword;
        }
}