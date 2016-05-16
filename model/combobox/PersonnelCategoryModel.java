package model.combobox;

import javax.swing.DefaultComboBoxModel;

public final class PersonnelCategoryModel extends DefaultComboBoxModel<String> {
    private static final long serialVersionUID = 1L;
    private final static String[] CATEGORY_ARRAY = new String[]{"Employee", "Faculty", "Student"};
    
    public PersonnelCategoryModel() {
        super(CATEGORY_ARRAY);
    }
}
