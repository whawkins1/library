package ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.scrollpane.GoodStandingScrollPane;
import ui.table.GoodStandingTable;
import utility.Utility;

public final class GoodStandingDialog extends JDialog{
    private static final long serialVersionUID = 1L;
    
    public GoodStandingDialog(final JFrame aParent) {
        super(aParent, "Students Good Standing", false);
        final GoodStandingTable table = new GoodStandingTable();
        add(new GoodStandingScrollPane(table), BorderLayout.CENTER);
        setIconImage(Utility.getSmallImage());
        Utility.setCloseOperation(this);  final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension d = tk.getScreenSize();
        setPreferredSize(new Dimension((d.width / 3), (d.height / 3)));
        pack();
        setLocationRelativeTo(aParent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);  
    }
}