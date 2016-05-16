package ui.combobox;

import javax.swing.JComboBox;


public final class ComboBoxSearchType extends JComboBox<String> {
    private static final long serialVersionUID = 1L;
    private final static String[] TYPE_ARRAY = new String[]{"Inventory", "Personnel"};
    
    public ComboBoxSearchType() {
        super (TYPE_ARRAY);      
        setSelectedIndex(-1);
    }
}