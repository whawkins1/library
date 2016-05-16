package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.panel.LogsReturnCheckoutFilterPanel;
import ui.panel.LogsReturnCheckoutPanel;
import ui.table.LogsReturnCheckoutTable;
import utility.Utility;

public final class LogsReturnCheckoutDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
    public LogsReturnCheckoutDialog(final JFrame aParent, final DataHandler aHandler) {
        super(aParent, "Return/Checkout Logs", false);
        final LogsReturnCheckoutTable table = new LogsReturnCheckoutTable(aHandler);
        add(new LogsReturnCheckoutFilterPanel(table, aHandler),BorderLayout.NORTH);
        add(new LogsReturnCheckoutPanel(table), BorderLayout.CENTER);
        Utility.setCloseOperation(this);  final Toolkit tk = Toolkit.getDefaultToolkit();
        this.setIconImage(Utility.getSmallImage());
        final Dimension d = tk.getScreenSize();
        setPreferredSize(new Dimension((d.width / 3), (d.height / 3)));
        pack();
        setLocationRelativeTo(aParent) ;
        setVisible(true) ;
    }
}