package ui.combobox;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public final class QuickFindComboBox extends JComboBox<String> {
    private static final long serialVersionUID = 1L;

    
    public QuickFindComboBox(final int aIsAdmin) {
        super();
        List<String> cbList = Arrays.asList("Audio", "Video", "Student", "Faculty", 
                                            "Employee", "Book");
        if( aIsAdmin != 1) 
            cbList.remove("Employee");    
        setSelectedIndex(-2);
        final String[] cbArr = cbList.toArray(new String[cbList.size()]);
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(cbArr);
        setModel(model);
    }
    
    
        @Override public final String toString ( ) { return "Choose Category"; }
}