package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import document.NumbersLettersOnlyDocumentFilter;
import Personnel.Faculty;
import ui.panel.FacultyButtonPanel;
import ui.panel.ViewButtonPanel;
import ui.table.PersonnelTable;
import utility.Utility;

public final class FacultyDialog extends PersonnelAccessDialog  {
    private static final long serialVersionUID = 1L;
    private  JTextField departmentTF_;
  
      public FacultyDialog (final JFrame aParent, final DataHandler aHandler) {
          super(aHandler, "ADD");
          setTitle("Add Faculty");
          buildPanel(null);
          this.mode_ = "ADD";
          add(new FacultyButtonPanel(inputPanel_, this, aHandler), BorderLayout.SOUTH);
          setDialogProperties(aParent);
      }
      
      public FacultyDialog ( final Faculty aFaculty, 
                             final JFrame aParent,
                             final DataHandler aHandler,
                             final PersonnelTable aTable) {
          super(aFaculty, aParent, aHandler, "EDIT") ;
          buildPanel(aFaculty);   
          setTitle("Edit Faculty");
          this.mode_ = "EDIT";
          inputPanel_.setTFNotEditable();
          add(new FacultyButtonPanel(inputPanel_, this, aHandler, aTable), BorderLayout.SOUTH) ;
          setDialogProperties(aParent);
      }
      
      public FacultyDialog(final Faculty aFaculty, 
                           final JFrame aParent,
                           final DataHandler aHandler) {
          super(aFaculty, aParent, null, "VIEW");
          setTitle("View Faculty");
          buildPanel(aFaculty);          
          add(new ViewButtonPanel(aHandler, this, aFaculty.getEmail()), BorderLayout.SOUTH);
          inputPanel_.setTFNotEditable();
          setDialogProperties(aParent);
      }
  
  
          public final void buildPanel(final Faculty aFaculty) {
              inputPanel_.add(new JLabel("Department: "), Utility.setConstraints(0, 9));
              departmentTF_ = new JTextField(15);
              departmentTF_.setBackground(Color.WHITE);
              inputPanel_.addTF(departmentTF_);
              if(aFaculty != null)
                  departmentTF_.setText(aFaculty.getDept());
              final PlainDocument doc = (PlainDocument)departmentTF_.getDocument();
              doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(20, false, true));
              inputPanel_.add(departmentTF_, Utility.setConstraints(1, 9));
              add(inputPanel_, BorderLayout.CENTER);
              
              
              
          }
          
          
          public final String getDepartment() {return this.departmentTF_.getText().trim();} 
}