package ui.dialog;

import inventory.Video;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utility.Utility;

public final class ViewVideoDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JTextField fTitleField;
    private final JTextField fCallNumTF;
    private final JTextField fStudioLabelTF;
    private final JTextField fYearTF;
    private final JTextField fNumCopiesTF;
    private final JTextArea fDescTA;
    private final List<JTextField> fFieldList;
    private final JComboBox<String> fStorageMedCB;
    
    public ViewVideoDialog ( final Video aVideo) {
        fFieldList = new ArrayList<JTextField>(3);
        setLayout(new GridBagLayout());
        final GridBagConstraints c1 = Utility.setConstraints(0,0);
        final JLabel titleLabel = new JLabel("Name*: ");   
        add(titleLabel, c1);
       final GridBagConstraints c2 = Utility.setConstraints(1,0);
        fTitleField = new JTextField(15);   
        fFieldList.add(fTitleField);
        add(fTitleField, c2);
        final GridBagConstraints c3 = Utility.setConstraints(0,1);
        final JLabel callNumLabel = new JLabel("Call Number*: ");   
        add(callNumLabel, c3);
       final GridBagConstraints c4 = Utility.setConstraints(1,1);
      fCallNumTF = new JTextField(15);  
       fFieldList.add(fCallNumTF);
        add(fCallNumTF, c4);
        final GridBagConstraints c5 = Utility.setConstraints(0,2);
        final JLabel authorLabel = new JLabel("Storage Medium: ");
        add(authorLabel, c5);
        final GridBagConstraints c6 = Utility.setConstraints(1,2);        
        String[] storageMedArr = {"Audio", "Video"};
        fStorageMedCB = new JComboBox<String>(storageMedArr);
        add(fStorageMedCB, c6);
        final GridBagConstraints c7 = Utility.setConstraints(0,3);
        final JLabel publisherLabel = new JLabel("Studio or Label*: ");    
        add(publisherLabel, c7);
      final GridBagConstraints c8 = Utility.setConstraints(1,3);
      fStudioLabelTF = new JTextField(15);
        add(fStudioLabelTF, c8);
        final GridBagConstraints c9 = Utility.setConstraints(0, 4);
        final JLabel yearLabel = new JLabel("Year*: ");     
        add(yearLabel, c9);
      final GridBagConstraints c10 = Utility.setConstraints(1,4);
        fYearTF = new JTextField(15);
        fFieldList.add(fYearTF);
        add(fYearTF, c10);
        final GridBagConstraints c11 = Utility.setConstraints(0,5);
        final JLabel numCopiesLabel = new JLabel("Number of Copies*: ");    
        add(numCopiesLabel, c11);
      final GridBagConstraints c12 = Utility.setConstraints(1,5);
        fNumCopiesTF = new JTextField(3);
        fFieldList.add(fNumCopiesTF);
        add(fNumCopiesTF, c12);
        final GridBagConstraints c15 = Utility.setConstraints(0, 7);
        final JLabel descriptionLabel = new JLabel("Description: ");    
        add(descriptionLabel, c15);
      final GridBagConstraints c16 = Utility.setConstraints(1, 7);
        fDescTA = new JTextArea(200, 100);
        fDescTA.setWrapStyleWord(true);
        fDescTA.setLineWrap(true);
        add(fDescTA, c16);
    }
}
