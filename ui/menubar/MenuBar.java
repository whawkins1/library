package ui.menubar;

import handler.DataHandler;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import ui.menu.AccountsMenu;
import ui.menu.EditMenu;
import ui.menu.FileMenu;
import ui.menu.HelpMenu;
import ui.menu.ViewMenu;

public final class MenuBar extends JMenuBar{
    private static final long serialVersionUID = 1L;
      public MenuBar(final JFrame aFrame, 
                     final String aEmail,
                     final DataHandler aHandler,
                     final boolean aIsAdmin) {
          add(new FileMenu(aFrame, aEmail));
          if (aIsAdmin) {
              add(new ViewMenu(aFrame, aEmail, aHandler));
              add(new EditMenu(aFrame));
              add(new AccountsMenu(aFrame, aHandler));
          }
          add(new HelpMenu(aFrame));
      }    
}