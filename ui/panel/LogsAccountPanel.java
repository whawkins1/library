package ui.panel;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.table.LogsAccountTableModel;

import ui.scrollpane.LogsAccountScrollPane;
import ui.table.LogsAccountTable;

public final class LogsAccountPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public LogsAccountPanel(final DataHandler aHandler) {
        setLayout(new BorderLayout());
        final LogsAccountTable table = new LogsAccountTable(aHandler);
        final LogsAccountTableModel model = ((LogsAccountTableModel)table.getModel());
        add(new LogsAccountFilterPanel(model, aHandler), BorderLayout.NORTH);
        add(new LogsAccountScrollPane(table), BorderLayout.CENTER);
    }
}