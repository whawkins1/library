package model.combobox;

import javax.swing.DefaultComboBoxModel;

public final class InventoryByModel extends DefaultComboBoxModel<String> {
    private static final long serialVersionUID = 1L;
    private final static String[] BY_ARRAY = new String[]{"Title", "Description"};
    public InventoryByModel() {
        super(BY_ARRAY);
    }
}