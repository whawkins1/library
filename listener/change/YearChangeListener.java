package listener.change;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.panel.HireDatePanel;

public final class YearChangeListener 
                          implements ChangeListener {

    public YearChangeListener() {}  
     
        @Override public void stateChanged(final ChangeEvent e) {
            HireDatePanel.updateDate();        
        }
}