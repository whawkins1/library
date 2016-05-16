package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import ui.button.BookAddButton;
import ui.dialog.BookDialog;

public final class BookButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public BookButtonPanel(final InventoryInputPanel aInfoPanel, 
                              final BookDialog aDialog,
                              final DataHandler aHandler) {
           super(new FlowLayout(FlowLayout.CENTER));
           
           add(new BookAddButton(aInfoPanel, aDialog, aHandler));                       
       }
    
    public BookButtonPanel(final InventoryInputPanel aInfoPanel, 
            final BookDialog aDialog,
            final DataHandler aHandler,
            final JTable aTable) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new BookAddButton(aInfoPanel, aDialog, aHandler, aTable));                       
     }
}