package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import document.NumbersLettersOnlyDocumentFilter;
import Personnel.Student;
import ui.panel.StudentButtonPanel;
import ui.panel.ViewButtonPanel;
import ui.table.PersonnelTable;
import utility.Utility;


public final class StudentDialog extends PersonnelAccessDialog  {
    private static final long serialVersionUID = 1L;
    private JTextField fClassRankField;
    private JTextField fMajorField;
    
      public StudentDialog ( final JFrame aParent, final DataHandler aHandler) {
          super(aHandler, "ADD");
          setTitle("Add Student");
          buildPanel(null);
          add(new StudentButtonPanel( inputPanel_, this , aHandler), BorderLayout.SOUTH) ;
          setDialogProperties (aParent);
      }
      
      public StudentDialog (final Student aStudent, 
                            final JFrame aParent,
                            final DataHandler aHandler,
                            final PersonnelTable aTable) {
          super(aStudent, aParent, aHandler, "EDIT");
          setTitle("Student Edit");
          buildPanel(aStudent);
          final int rank = aStudent.getClassRank();
          final String rankConv = Integer.toString(rank);
          fClassRankField.setText(rankConv);
          fMajorField.setText(aStudent.getMajor());
          add(new StudentButtonPanel (inputPanel_, this, aHandler, aTable), BorderLayout.SOUTH);
          setDialogProperties(aParent) ;
      }
      
      public StudentDialog(final Student aStudent, 
                           final JFrame aParent, 
                           final DataHandler aHandler) {
          super(aStudent,   aParent, null, "VIEW");
          setTitle("View Student");
          buildPanel(aStudent);
          inputPanel_.setTFNotEditable();
          add(new ViewButtonPanel(aHandler, this, aStudent.getEmail()), BorderLayout.SOUTH);
          setDialogProperties(aParent);
      }
      
      
          public final void buildPanel(final Student aStudent) {
              inputPanel_.add(new JLabel("Class Rank: "), Utility.setConstraints(0, 9));
              fClassRankField = new JTextField(3);
              final PlainDocument doc = (PlainDocument)fClassRankField.getDocument();
              doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(3, true, false));
              final boolean enableTF = mode_.equals("VIEW") ? false : true;
              fClassRankField.setEditable(enableTF);
              fClassRankField.setBackground(Color.WHITE);
              inputPanel_.add(fClassRankField, Utility.setConstraints(1, 9));
              inputPanel_.add(new JLabel("Major: "), Utility.setConstraints(0, 10));
              fMajorField = new JTextField(15);
              final PlainDocument docMajor = (PlainDocument)fMajorField.getDocument();
              docMajor.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(20, false, true));
              if(aStudent != null) {
                  fClassRankField.setText(Integer.toString(aStudent.getClassRank()));
                  fMajorField.setText(aStudent.getMajor());
              }
              inputPanel_.add(fMajorField, Utility.setConstraints(1, 10));
              add(inputPanel_, BorderLayout.NORTH);
          }
                
          
          public final String getClassRank() {return this.fClassRankField.getText().trim();}
          
          
          public final String getMajor() {return this.fMajorField.getText().trim();}
}