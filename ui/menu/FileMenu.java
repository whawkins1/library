package ui.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;

import ui.menuitem.ExitMenuItem;

public final class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public FileMenu(final JFrame aFrame, final String aEmail) {
           super("File");
           add(new ExitMenuItem(aFrame, aEmail));
       }
}