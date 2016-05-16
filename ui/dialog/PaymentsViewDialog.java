package ui.dialog;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import handler.DataHandler;

import javax.swing.JDialog;

import ui.scrollpane.ScrollPane;
import ui.table.PaymentsTable;
import utility.Utility;

public final class PaymentsViewDialog extends JDialog {
    private static final long serialVersionUID = 1L;
        
    public PaymentsViewDialog(final Window aParent,
                              final DataHandler aHandler,
                              final String aEmail) {
        super(aParent, "Payment History", Dialog.ModalityType.DOCUMENT_MODAL);
        final PaymentsTable table = new PaymentsTable(aHandler, aEmail);
        add(new ScrollPane(table));
        setIconImage(Utility.getSmallImage());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Utility.setCloseOperation(this);  final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension d = tk.getScreenSize();
        setPreferredSize(new Dimension((d.width / 3), (d.height / 3)));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);  
    }
}