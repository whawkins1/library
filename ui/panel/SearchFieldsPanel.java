package ui.panel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import utility.Utility;


public final class SearchFieldsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JCheckBox fTitleCB;
    private final JCheckBox fLabelCB;
    private final JCheckBox fStorageMediumCB;
    private final JCheckBox fPublisherCB;
    private final JCheckBox fAuthorCB;
    private final JCheckBox fTypeCB;
    private final JCheckBox fGenreCB;
    private final JCheckBox fDescriptionCB;
    private final JCheckBox fStudioCB;
    private final JCheckBox fLastCB;
    private final JCheckBox fFirstCB;
    private final JCheckBox fDepartmentCB;
    private final JCheckBox fMajorCB;
    private final List<JCheckBox> fCBList;
    
    
    public SearchFieldsPanel() {
        super(new GridLayout(4, 3));
        fCBList = new ArrayList<JCheckBox>(13);
        fTitleCB = createCheckBox("title");
        fLabelCB = createCheckBox("label");
        fStorageMediumCB = createCheckBox("storage medium");
        fPublisherCB = createCheckBox("publisher");
        fAuthorCB = createCheckBox("author");
        fTypeCB = createCheckBox("type");
        fGenreCB = createCheckBox("genre");
        fDescriptionCB = createCheckBox("description");
        fStudioCB = createCheckBox("studio");
        fLastCB = createCheckBox("last");
        fFirstCB = createCheckBox("first");
        fDepartmentCB = createCheckBox("department");
        fMajorCB = createCheckBox("major");
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Check Types"));        
    }
    
    
        private final JCheckBox createCheckBox(final String aTitle) {
            final JCheckBox cb = new JCheckBox(aTitle, false);
            cb.setEnabled(false);
            fCBList.add(cb);
            add(cb);
            return cb;
        }
        
        
        public final void setPersonnelChecked (final boolean aSelected) {
            setLastFirstName(aSelected);
            fDepartmentCB.setSelected(aSelected);
            fDepartmentCB.setEnabled(aSelected);
            fMajorCB.setSelected(aSelected);
            fMajorCB.setEnabled(aSelected);
        }
        
        
        public final void setLastFirstName (final boolean aSelected) {
            fLastCB.setSelected(aSelected);
            fLastCB.setEnabled(aSelected);
            fFirstCB.setSelected(aSelected);
            fFirstCB.setEnabled(aSelected);
        }
        
        
        public final void setStudentsChecked (final boolean aSelected) {
            setLastFirstName(aSelected);
            fMajorCB.setSelected(aSelected);
            fMajorCB.setEnabled(aSelected);
        }
        
        
        public final void setFacultyChecked (final boolean aSelected) {
            setLastFirstName(aSelected);
            fDepartmentCB.setSelected(aSelected);
            fDepartmentCB.setEnabled(aSelected);
        }

        
        public final void setEmployeeChecked (final boolean aSelected) {
            setLastFirstName(aSelected);
        }
        
        
        public final void setInventoryChecked (final boolean aSelected) {
            setTitleDescription(aSelected);
            fLabelCB.setSelected(aSelected);
            fLabelCB.setEnabled(aSelected);
            fStorageMediumCB.setSelected(aSelected);
            fStorageMediumCB.setEnabled(aSelected);
            fStudioCB.setSelected(aSelected);
            fStudioCB.setEnabled(aSelected);
            fAuthorCB.setSelected(aSelected);
            fAuthorCB.setEnabled(aSelected);
            fTypeCB.setSelected(aSelected);
            fTypeCB.setEnabled(aSelected);
            fGenreCB.setSelected(aSelected);
            fGenreCB.setEnabled(aSelected);
            fPublisherCB.setSelected(aSelected);
            fPublisherCB.setEnabled(aSelected);
        }
        
        public final void setTitleDescription (final boolean aSelected) {
            fTitleCB.setSelected(aSelected);
            fTitleCB.setEnabled(aSelected);
            fDescriptionCB.setSelected(aSelected);
            fDescriptionCB.setEnabled(aSelected);
        }
        
        public final void setAudioChecked (final boolean aSelected) {
            setTitleDescription(aSelected);
            fLabelCB.setSelected(aSelected);
            fLabelCB.setEnabled(aSelected);
            fStorageMediumCB.setSelected(aSelected);
            fStorageMediumCB.setEnabled(aSelected);
        }
        
        
        public final void setVideoChecked (final boolean aSelected) {
            setTitleDescription(aSelected);
            fStudioCB.setSelected(aSelected);
            fStudioCB.setEnabled(aSelected);
            fStorageMediumCB.setSelected(aSelected);
            fStorageMediumCB.setEnabled(aSelected);
        }
        
        
        public final void setBooksChecked (final boolean aSelected) {
            setTitleDescription(aSelected);
            fAuthorCB.setSelected(aSelected);
            fAuthorCB.setEnabled(aSelected);
            fTypeCB.setSelected(aSelected);
            fTypeCB.setEnabled(aSelected);
            fGenreCB.setSelected(aSelected);
            fGenreCB.setEnabled(aSelected);
            fPublisherCB.setSelected(aSelected);
            fPublisherCB.setEnabled(aSelected);
        }
        
        
        public final void setChecked (final boolean aIsChecked) {
            for(JCheckBox cb : fCBList) {
                cb.setSelected(aIsChecked);
                cb.setEnabled(aIsChecked);
            }
        }
        
        
        public final String getChecked () {
            final StringBuilder sb = new StringBuilder();
            
            for(JCheckBox cb : fCBList) {
                if(cb.isSelected()) {
                    String title = cb.getText();
                    if (Utility.containsWhiteSpace(title)) {
                        title = title.replace(" ", "_");
                    }
                    sb.append(title + ", ");
                }
            }
            
            
            sb.deleteCharAt(sb.length() - 2);
            final String checkedString = sb.toString().trim();
            return checkedString;
        }
}