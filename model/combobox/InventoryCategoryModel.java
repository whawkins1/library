package model.combobox;

import javax.swing.DefaultComboBoxModel;

public final class InventoryCategoryModel extends DefaultComboBoxModel<String> {
    private static final long serialVersionUID = 1L;
    private final static String[] CATEGORY_ARRAY = new String[]{"Audio", "Book", "Video"};
    public InventoryCategoryModel() {
        super(CATEGORY_ARRAY);            
    }
}
