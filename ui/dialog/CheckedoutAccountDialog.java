package ui.dialog;

import java.awt.BorderLayout;

import handler.DataHandler;

import javax.swing.JDialog;

import ui.scrollpane.ScrollPane;
import ui.table.CheckedoutTable;
import utility.Utility;

public final class CheckedoutAccountDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public CheckedoutAccountDialog(final JDialog aParent, 
                                   final DataHandler aHandler,
                                   final String aEmail) {
          super(aParent, "Checkedout", true);
          final CheckedoutTable table = new CheckedoutTable(aHandler, aEmail);
          add(new ScrollPane(table), BorderLayout.CENTER);
          Utility.setCloseOperation(this);
          pack();
          setLocationRelativeTo(aParent);
          setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
          setVisible(true);
      }
}
