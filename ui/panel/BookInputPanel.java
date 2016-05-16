package ui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utility.Utility;

public final class BookInputPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JTextField fTitleField;
    private final JTextField fCallNumTF;
    private final JTextField fAuthorField;
    private final JTextField fPublisherTF;
    private final JTextField fYearTF;
    private final JTextField fNumCopiesTF;
    private final JTextField fGenreTF;
    private final JTextArea fDescTA;
    private final List<JTextField> fFieldList;
    
    public BookInputPanel() {
        fFieldList = new ArrayList<JTextField>(3);
        setLayout(new GridBagLayout());
        final GridBagConstraints c1 = Utility.setConstraints(0,0);
        final JLabel titleLabel = new JLabel("Title*: ");   
        add(titleLabel, c1);
       final GridBagConstraints c2 = Utility.setConstraints(1,0);
        fTitleField = new JTextField();   
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
        final JLabel authorLabel = new JLabel("Author*: ");
        add(authorLabel, c5);
        final GridBagConstraints c6 = Utility.setConstraints(1, 2);
        fAuthorField = new JTextField(15);
        add(fAuthorField, c6);
        fFieldList.add(fAuthorField);
        final GridBagConstraints c7 = Utility.setConstraints(0,3);
        final JLabel publisherLabel = new JLabel("Publisher: ");    
        add(publisherLabel, c7);
      final GridBagConstraints c8 = Utility.setConstraints(1,3);
        fPublisherTF = new JTextField(15);
        add(fPublisherTF, c8);
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
        final GridBagConstraints c13 = Utility.setConstraints(0,6);
        final JLabel genreLabel = new JLabel("Genre: ");    
        add(genreLabel, c13);
      final GridBagConstraints c14 = Utility.setConstraints(1,6);
        fGenreTF = new JTextField(15);
        add(fGenreTF, c14);       
        final GridBagConstraints c15 = Utility.setConstraints(0, 7);
        final JLabel descriptionLabel = new JLabel("Description: ");    
        add(descriptionLabel, c15);
      final GridBagConstraints c16 = Utility.setConstraints(1, 7);
        fDescTA = new JTextArea(200, 100);
        fDescTA.setWrapStyleWord(true);
        fDescTA.setLineWrap(true);
        add(fDescTA, c16);
    }
    
    public final String getTitle ( ) {
        return this.fTitleField.getText();
    }
    
    public final String getCallNum ( ) {
        return this.fCallNumTF.toString();
    }

    public final String getAuthor ( ) {
        return this.fAuthorField.getText().toString();
    }

    public final String getPublisher ( ) {
        return this.fPublisherTF.getText().toString();
    }
    
    public final String getYear ( ) {
        return this.fYearTF.toString();
    }

    public final String getNumCopies ( ) {
        return this.fNumCopiesTF.toString();
    }

    public final String getGenre ( ) {
        return this.fGenreTF.toString();
    }

    public final String getDescription ( ) {
        return this.fDescTA.getText();
    }
    
    public final List<JTextField> getTFList ( ) {
        return this.fFieldList;
    }
}