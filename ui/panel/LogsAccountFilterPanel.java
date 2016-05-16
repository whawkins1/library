package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import log.Log;
import model.table.LogsAccountTableModel;

public final class LogsAccountFilterPanel extends JPanel 
                                                 implements ItemListener  {
    private static final long serialVersionUID = 1L;
    private final LogsAccountTableModel model_;
    private final JComboBox<String> cbFilter_;
    private final List<Log> fLogList;
    private final static String[] ACTIONS = {"All", "Sign In", "Sign Off"};
    
    public LogsAccountFilterPanel(final LogsAccountTableModel aModel, final DataHandler aHandler) {
        super(new FlowLayout(FlowLayout.LEADING));
        this.model_ = aModel;
         fLogList = aHandler.getEmployeeLogList();
        cbFilter_ = new JComboBox<String>(ACTIONS);
        cbFilter_.addItemListener(this);          
        add(new JLabel("Filter By:"));
        add(cbFilter_);
    }
    
    

        @Override public final void itemStateChanged(final ItemEvent ie) {
           final String selectedText = (cbFilter_.getSelectedItem().toString());
           switch(selectedText) {
               case "All":
                   model_.setList(fLogList);
                   break;
               case "Sign In":
                     model_.setList(filterList("SIGN IN"));
                   break;
               case "Sign Off":
                   model_.setList(filterList("SIGN OFF"));
                   break;
               }
       }
        
        private final List<Log> filterList(final String aFilterText) {
            List<Log> list = new ArrayList<Log>();
            for(Log l : fLogList) {
                final String activity = l.getActivity();
                if(activity.equals(aFilterText))
                    list.add(l);
            }
            return list;
        }
}