package listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import ui.panel.SearchFieldsPanel;

public final class ChooseCategoryListener 
                            implements ActionListener {
    private final SearchFieldsPanel fFieldsPanel;
    private final JComboBox<String> fCategoryCB;
    
    
    public ChooseCategoryListener(final SearchFieldsPanel aFieldsPanel, 
                                  final JComboBox<String> aCategoryCB) {
        this.fFieldsPanel = aFieldsPanel;
        this.fCategoryCB = aCategoryCB;
    }
    
        @Override public final void actionPerformed(final ActionEvent evt) {
               final ComboBoxModel<String> model = fCategoryCB.getModel();
               final int selectedIndex = fCategoryCB.getSelectedIndex();
               final String selected = model.getElementAt(selectedIndex);
               fFieldsPanel.setChecked(false); // Disable CheckBoxes
               
               switch(selected) {
                   case "Audio":
                       fFieldsPanel.setAudioChecked(true);
                       break;
                   case "Video":
                       fFieldsPanel.setVideoChecked(true);
                       break;
                   case "Book":
                       fFieldsPanel.setBooksChecked(true);
                       break;
                   case "Student":
                       fFieldsPanel.setStudentsChecked(true);
                       break;
                   case "Faculty":
                       fFieldsPanel.setFacultyChecked(true);
                       break;
                   case "Employee":
                       fFieldsPanel.setEmployeeChecked(true);
                       break;
                   case "All Personnel":
                       fFieldsPanel.setPersonnelChecked(true);
                       break;
                   case "ALL Inventory":
                       fFieldsPanel.setInventoryChecked(true);
                       break;
               }
        }
}