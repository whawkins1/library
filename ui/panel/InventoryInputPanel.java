package ui.panel;

import java.awt.Color;
import java.awt.GridBagLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.PlainDocument;

import document.NumbersLettersOnlyDocumentFilter;

import utility.Utility;

public final class InventoryInputPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField fTitleField;
    private JTextField fCallNumTF;
    private JTextField fYearTF;
    private JTextField fNumCopiesTF;
    private JTextArea fDescTA;
    private final List<JTextField> tFList_;
    
    public InventoryInputPanel(final String aMode) {
        tFList_ = new ArrayList<JTextField>();
        setLayout(new GridBagLayout());
        add(Utility.createLabelAsterick(aMode, "Title"), Utility.setConstraints(0,0));
        fTitleField = new JTextField();   
        tFList_.add( fTitleField );
        add ( fTitleField, Utility.setConstraints(1,0));
        add(Utility.createLabelAsterick(aMode, "Call Number"), Utility.setConstraints(0,1));
        fCallNumTF = new JTextField();  
        tFList_.add( fCallNumTF );
        add(fCallNumTF, Utility.setConstraints(1,1));
        add(Utility.createLabelAsterick(aMode, "Year"), Utility.setConstraints(0, 4));
        fYearTF = new JTextField(4);
        final PlainDocument docYear = (PlainDocument)fYearTF.getDocument();
        docYear.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(4, true, false));
        tFList_.add( fYearTF );
        add(fYearTF, Utility.setConstraints(1, 4));
        add(Utility.createLabelAsterick(aMode, "Number Of Copies"), Utility.setConstraints(0,5));
        fNumCopiesTF = new JTextField();
        final PlainDocument docCopies = (PlainDocument)fNumCopiesTF.getDocument();
        docCopies.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(2, true, false));
        tFList_.add( fNumCopiesTF );
        add(fNumCopiesTF, Utility.setConstraints(1,5));
        add(Utility.createLabelAsterick(aMode, "Description"), Utility.setConstraints(0, 10));
        fDescTA = new JTextArea(6, 25);
        fDescTA.setBorder(new BevelBorder(BevelBorder.LOWERED));
        fDescTA.setWrapStyleWord(true);
        fDescTA.setLineWrap(true);
        add(fDescTA, Utility.setConstraints(1, 10));
        if(aMode.equals("EDIT") || aMode.equals("ADD")) {
            setEditSizeTF();
            add(new JLabel("* Must Be Completed"), Utility.setConstraints(1, 11));
        }
    }
    
        private final void setEditSizeTF() {
            fTitleField.setColumns(15);
            fCallNumTF.setColumns(8);
            fYearTF.setColumns(4);
            fNumCopiesTF.setColumns(3);
        }
    
        public final void setTFNotEditable() {
            for(JTextField tf : tFList_) {
                tf.setEditable(false);
                tf.setBackground(Color.WHITE);
            }
            fDescTA.setEditable(false);
        }
        
        public final void resetFields() {
            for(JTextField tf : tFList_) 
                tf.setText("");
            fTitleField.grabFocus();            
        }

        
        public final String getCallNumber() { return fCallNumTF.getText().trim(); }
        
        
        public final List<JTextField> getTFList () { return this.tFList_; }
        
        
        public final void addTF (final JTextField aTF) { tFList_.add (aTF); }
        
        
        public final void setTFTitle(final String aTitle) { 
                     fTitleField.setColumns(aTitle.length());
                     fTitleField.setText( aTitle ); 
       }
        
        
        public final String getTFTitle() { return fTitleField.getText().trim(); }
        
        
        public final void setTFCallNum(final String aCallNum) { 
               fCallNumTF.setColumns(aCallNum.length());
               fCallNumTF.setText( aCallNum ); 
            }
        
        
        public final String getTFCallNum() { return fCallNumTF.getText().trim(); }
        
        
        public final void setTFYear(final int aYear) { fYearTF.setText( Integer.toString(aYear) ); }
        
        
        public final String getTFYear() { return fYearTF.getText().trim(); }
        
        
        public final int getTFNumCopies() { return Integer.parseInt( fNumCopiesTF.getText().trim() ); }
        
        
        public final void setTFNumCopies(final int aNumCopies) {
            final String numCopies = Integer.toString(aNumCopies);
            fNumCopiesTF.setColumns(numCopies.length());
            fNumCopiesTF.setText(numCopies); 
            }
        
        
        public final void setTADescription(final String aDescription) { fDescTA.setText( aDescription ); }
        
        
        public final String getTADescription() { return fDescTA.getText().trim(); }
}