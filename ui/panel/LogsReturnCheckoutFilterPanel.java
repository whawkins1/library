package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import log.Log;
import model.table.CheckoutReturnLogsTableModel;
import ui.table.LogsReturnCheckoutTable;

public final class LogsReturnCheckoutFilterPanel extends JPanel 
                                                        implements ItemListener        {
    private static final long serialVersionUID = 1L;
    private final CheckoutReturnLogsTableModel model_;
    private final JComboBox<String> cbFilter_;
    private final DataHandler handler_;    
    private final String[] cbArr = {"All", "Checkout", "Return"};
    
    public LogsReturnCheckoutFilterPanel(final LogsReturnCheckoutTable aTable,
                                         final DataHandler aHandler) {
            super(new FlowLayout(FlowLayout.LEADING));
            this.model_ = ((CheckoutReturnLogsTableModel)aTable.getModel());
            this.handler_ = aHandler;
            cbFilter_ = new JComboBox<String>(cbArr);
            cbFilter_.addItemListener(this);
            add(new JLabel("Filter By: "));
            add(cbFilter_);
     }      

    
    
        @Override public final  void itemStateChanged(ItemEvent e) {
            final String selectedText = (cbFilter_.getSelectedItem().toString());
            switch(selectedText) {
                case "Checkout":
                    model_.setList(new ArrayList<Log>(handler_.getCheckoutMap().values()));
                    break;
                case "Return":
                    model_.setList(new ArrayList<Log>(handler_.getReturnedMap().values()));
                    break;
                case "All":
                    model_.setList(new ArrayList<Log>(handler_.getAllLogMap().values()));
                    break;
                }
        }
}