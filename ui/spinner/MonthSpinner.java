package ui.spinner;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import listener.change.MonthChangeListener;

public final class MonthSpinner extends JSpinner {
    private static final long serialVersionUID = 1L;
    
    public MonthSpinner(final SpinnerNumberModel aModel) {
        super(aModel);      
        addChangeListener(new MonthChangeListener());
    }
}