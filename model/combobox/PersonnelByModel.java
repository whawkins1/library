package model.combobox;

import javax.swing.DefaultComboBoxModel;

public final class PersonnelByModel extends DefaultComboBoxModel<String> {
    private static final long serialVersionUID = 1L;
    private final static String[] BY_ARRAY = new String[]{"First Name", "Last Name", "City"};
    public PersonnelByModel() {
        super(BY_ARRAY);
    }
}
