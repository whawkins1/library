package ui.popup;

import handler.DataHandler;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import ui.menuitem.InventoryEditMenuItem;
import ui.menuitem.InventoryViewMenuItem;
import ui.menuitem.InventoryRemoveMenuItem;
import ui.table.InventoryTable;

public final class PopupInventory extends JPopupMenu {
    private static final long serialVersionUID = 1L;
       public PopupInventory(final InventoryTable aTable, 
                             final JComboBox<String> aCb,
                             final JFrame aFrame,
                             final DataHandler aHandler) {
           
           add( new InventoryViewMenuItem(aTable, aCb, aFrame, aHandler));
           add( new InventoryEditMenuItem(aTable, aCb, aFrame, aHandler));
           add( new InventoryRemoveMenuItem(aTable, aCb));
       }
}