package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import locations.Country;
import locations.State;

public final class CountryListener
                           implements ActionListener {
    private final JComboBox<String> countryCB_;
    private final JComboBox<String> stateCB_;
    private final JComboBox<String> cityCB_;
    private final DataHandler handler_;
    private String currentSelection;

    public CountryListener(final JComboBox<String> aCountryCB,
                           final JComboBox<String> aStateCB,
                           final JComboBox<String> aCityCB,
                           final DataHandler aHandler)  {
        this.countryCB_ = aCountryCB;
        this.stateCB_ = aStateCB;
        this.cityCB_ = aCityCB;
        this.handler_ = aHandler;
    }
    
        @Override  public final void actionPerformed(final ActionEvent e) {
           final String selection = ((String)countryCB_.getSelectedItem());
           if(!(selection.equals(currentSelection))) {
               currentSelection = selection.intern();
               final List<Country> countryList = handler_.getCountryList();
               Country country = null;
               for(Country c : countryList) {
                   final String name = c.getName();
                   if(name.equals(selection)) {
                       country = c;
                       break;
                   }
               }
               final int countryID = country.getID();
               if(stateCB_.getItemCount() > 0) {
                   removeListenersItems();
               }
               stateCB_.insertItemAt("", 0);
               final List<State> stateList = handler_.getStateList();
               for(State s : stateList) {
                   final int parentID = s.getParent();
                   if(parentID == countryID)
                      stateCB_.addItem(s.getName());
               }           
               stateCB_.addActionListener(new StateListener(stateCB_, cityCB_, handler_));   
               cityCB_.removeAllItems();               
            } else if(selection.trim().length() == 0) {
                removeListenersItems();
            }
        }
        
        private final void removeListenersItems() {
            for(ActionListener al : stateCB_.getActionListeners()) {
                stateCB_.removeActionListener(al);
            }
            stateCB_.removeAllItems();
        }
}