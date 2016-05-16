package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public final class  ChooseTypeListener 
                         implements ActionListener {
    private final static String[] fInventCatArr = new String[]{"Audio", "Book", "Video"};
    private final static String[] fPersonCatArr = new String[]{"Employee", "Faculty", "Student"};
    private final JComboBox<String> fCBCategory;
    private final JComboBox<String> fCBType;    
    
    public ChooseTypeListener(final JComboBox<String> aCBType, final JComboBox<String> aCBCategory) {
        this.fCBCategory = aCBCategory;
        this.fCBType = aCBType;
    }
    
        @Override  public final void actionPerformed(final ActionEvent ae) {
            final ComboBoxModel<String> model = fCBType.getModel();
            final int index = fCBType.getSelectedIndex();
            final String chosen = model.getElementAt(index);
            
            DefaultComboBoxModel<String> cbModel = null;
            switch(chosen) {
                case "Inventory":
                    cbModel = new DefaultComboBoxModel<String>(fInventCatArr);
                    break;
                case "Personnel":
                    cbModel = new DefaultComboBoxModel<String>(fPersonCatArr); 
                    break;
                 default:
                     cbModel = new DefaultComboBoxModel<String>();
                }
            fCBCategory.setModel(cbModel);
            fCBCategory.insertItemAt("", 0);
            fCBCategory.setSelectedIndex(0);
            if(index != 0)
                fCBCategory.grabFocus();
        }
}