package ui.menuitem;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import listener.action.AboutDisplayListener;


public final class AboutMenuItem extends JMenuItem{
    private static final long serialVersionUID = 1L;

    public AboutMenuItem(final JFrame aFrame) {
       super("About");
       addActionListener(new AboutDisplayListener(aFrame));
    }
}