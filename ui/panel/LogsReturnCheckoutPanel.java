package ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import ui.scrollpane.LogsReturnCheckoutScrollPane;

public class LogsReturnCheckoutPanel extends JPanel  {
    private static final long serialVersionUID = 1L;

    public LogsReturnCheckoutPanel(final JTable aTable) {
       super(new BorderLayout());   
       add(new LogsReturnCheckoutScrollPane(aTable), BorderLayout.CENTER);
    }
}