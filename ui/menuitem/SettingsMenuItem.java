package ui.menuitem;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.SettingsDisplayListener;

public final class SettingsMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public SettingsMenuItem(final JFrame aFrame) {
         super("Settings");
         addActionListener(new SettingsDisplayListener(aFrame));
     }
}