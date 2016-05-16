package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.panel.QuickFindInputPanel;

import utility.Utility;

public final class QuickFindDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public QuickFindDialog(final JFrame aFrame,
                           final DataHandler aHandler,
                           final int aIsAdmin) {
        
        super(aFrame, "Quick Find", false);         
        add(new QuickFindInputPanel(aHandler, this, aIsAdmin), BorderLayout.NORTH);       
        Utility.setCloseOperation(this);
        pack();
        setLocationRelativeTo(aFrame);
        setVisible(true);
    }
}