package ui.spinner;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import listener.change.DayChangeListener;

public final class DaySpinner extends JSpinner {
    private static final long serialVersionUID = 1L;

    public DaySpinner(final SpinnerNumberModel aModel) {
        super(aModel);
        addChangeListener(new DayChangeListener());
    }
}