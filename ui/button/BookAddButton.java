package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JTable;

import listener.action.BookAddListener;
import ui.dialog.BookDialog;
import ui.panel.InventoryInputPanel;

public final class BookAddButton extends JButton {
    private static final long serialVersionUID = 1L;
    public BookAddButton(final InventoryInputPanel aInfoPanel, 
                                final BookDialog aDialog, 
                                final DataHandler aHandler ) {
        super (aDialog.getMode());
        
        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new BookAddListener(aInfoPanel, aDialog, aHandler));
    }
    
    public BookAddButton(final InventoryInputPanel aInfoPanel, 
            final BookDialog aDialog, 
            final DataHandler aHandler, 
            final JTable aTable) {
          super (aDialog.getMode());

          aDialog.getRootPane().setDefaultButton(this);
          addActionListener(new BookAddListener(aInfoPanel, aDialog, aHandler, aTable));
}
}