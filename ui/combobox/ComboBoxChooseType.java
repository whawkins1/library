package ui.combobox;

import javax.swing.JComboBox;

import listener.action.ChooseTypeListener;

public final class ComboBoxChooseType extends JComboBox<String> {
    private static final long serialVersionUID = 1L;
    private final static String[] fTypeArr = new String[]{"Inventory", "Personnel"};
    
    public ComboBoxChooseType(final JComboBox<String> aCB) {
        super(fTypeArr);
        insertItemAt("", 0);
        setSelectedIndex(0);
        addActionListener(new ChooseTypeListener(this, aCB));
    }
}
