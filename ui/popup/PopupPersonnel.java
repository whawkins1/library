package ui.popup;

import handler.DataHandler;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import ui.menuitem.PersonnelEditMenuItem;
import ui.menuitem.PersonnelRemoveMenuItem;
import ui.menuitem.PersonnelViewMenuItem;
import ui.table.PersonnelTable;


public final class PopupPersonnel extends JPopupMenu {
    private static final long serialVersionUID = 1L;
       public PopupPersonnel(final PersonnelTable aTable,  
                             final JComboBox<String> aCB, 
                             final JFrame aFrame, 
                             final DataHandler aHandler) {
           
           add(new PersonnelViewMenuItem(aTable, aCB, aFrame, aHandler));
           add(new PersonnelEditMenuItem(aTable, aCB, aFrame, aHandler));
           add(new PersonnelRemoveMenuItem(aTable, aCB));
       }
}