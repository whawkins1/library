package listener.change;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.panel.HireDatePanel;

public final class MonthChangeListener 
                        implements ChangeListener {

    public MonthChangeListener() {};
    
        @Override public final void stateChanged(final ChangeEvent e) {
            HireDatePanel.updateDate();        
        }
}