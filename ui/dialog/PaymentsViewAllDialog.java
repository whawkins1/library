package ui.dialog;

import handler.DataHandler;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JDialog;

import ui.scrollpane.ScrollPane;
import ui.table.PaymentsAllTable;

import utility.Utility;

public final class PaymentsViewAllDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
    public PaymentsViewAllDialog(final Window aParent,
                                 final DataHandler aHandler) {
            super(aParent, "Payment History", Dialog.ModalityType.DOCUMENT_MODAL);
            add(new ScrollPane(new PaymentsAllTable(aHandler)));
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
