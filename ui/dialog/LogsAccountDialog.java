package ui.dialog;

import java.awt.Dimension;
import java.awt.Toolkit;

import handler.DataHandler;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.panel.LogsAccountPanel;
import utility.Utility;

public final class LogsAccountDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public LogsAccountDialog(final JFrame aParent, final DataHandler aHandler) {
        super(aParent, "Account Logs", false);
        add(new LogsAccountPanel(aHandler));
        Utility.setCloseOperation(this);
        setIconImage(Utility.getSmallImage());
        final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension d = tk.getScreenSize();
        setPreferredSize(new Dimension((d.width / 3), (d.height / 3)));
        pack();
        setLocationRelativeTo(aParent) ;
        setVisible(true) ;
    }
}