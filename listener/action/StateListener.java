package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JComboBox;

import locations.City;
import locations.State;

public final class StateListener 
                     implements ActionListener {
    private final JComboBox<String> stateCB_;
    private final JComboBox<String> cityCB_;
    private final DataHandler handler_;
    private String currentSelection_;

    public StateListener(final JComboBox<String> aStateCB,
                         final JComboBox<String> aCityCB,
                         final DataHandler aHandler) {
        this.stateCB_ = aStateCB;
        this.cityCB_ = aCityCB;
        this.handler_ = aHandler;
    }

    
        @Override public final void actionPerformed(final ActionEvent e) {
            final String selection = ((String)stateCB_.getSelectedItem());
            if(selection.trim().length() > 0) {
                if(!selection.equals(currentSelection_)) {
                    currentSelection_ = selection.intern();
                    final List<State> stateList = handler_.getStateList();
                    State state = null;
                    for(State s : stateList) {
                        final String name = s.getName();
                        if(name.equals(selection)) {
                            state = s;
                            break;
                        }
                    }
                    final int stateID = state.getID();
                    
                    if(cityCB_.getItemCount() > 0) 
                        cityCB_.removeAllItems();
                    
                        final List<City> cityList = handler_.getCityList();
                        for(City c : cityList) {
                            final int parentID = c.getParent();
                            if(parentID == stateID)
                                cityCB_.addItem(c.getName());
                        }
                        cityCB_.setSelectedIndex(-1);
                    }
             } else  {
                    cityCB_.removeAllItems();
             }
        }
} 

