package ui.menu;

import javax.swing.JMenu;

import ui.menuitem.FacultyMenuItem;
import ui.menuitem.StudentMenuItem;

public final class PersonnelMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public PersonnelMenu() {
         super("Personnel");
         add(new StudentMenuItem());
         add(new FacultyMenuItem());
     }
}