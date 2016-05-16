package ui.spinner;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import listener.change.YearChangeListener;

public final class YearSpinner extends JSpinner {
    private static final long serialVersionUID = 1L;

    public YearSpinner(final SpinnerNumberModel aModel) {
        super(aModel);
        final NumberEditor yearEditor = new NumberEditor(this, "####");
        setEditor(yearEditor);   
        addChangeListener(new YearChangeListener());
    }
}