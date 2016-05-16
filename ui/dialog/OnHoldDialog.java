package ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.scrollpane.OnHoldScrollPane;
import ui.table.OnHoldTable;
import utility.Utility;


public final class OnHoldDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
    public OnHoldDialog(final JFrame aParent) {
        super(aParent, "Students On Hold", false);
        final OnHoldTable table = new OnHoldTable();     
        add(new OnHoldScrollPane(table), BorderLayout.CENTER);
        Utility.setCloseOperation(this);
        setIconImage(Utility.getSmallImage());  
        final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension d = tk.getScreenSize();
        setPreferredSize(new Dimension((d.width / 3), (d.height / 3)));
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);  
    }
}