package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import ui.dialog.AudioDialog;
import ui.dialog.BookDialog;
import ui.dialog.EmployeeDialog;
import ui.dialog.FacultyDialog;
import ui.dialog.StudentDialog;
import ui.dialog.VideoDialog;

import utility.Utility;

public final class ChooseCategoryBtnListener 
                                   implements ActionListener {
    private final JFrame frame_;
    private final JComboBox<String> fCategoryCB;
    private final JComboBox<String> fTypeCB;
    private final DataHandler handler_;    
    
    public ChooseCategoryBtnListener(final JFrame aFrame,
                                     final JComboBox<String> aCategoryCB, 
                                     final JComboBox<String> aTypeCB,
                                     final DataHandler aHandler) {
        this.frame_ = aFrame;
        this.fCategoryCB = aCategoryCB;
        this.fTypeCB = aTypeCB;
        this.handler_ = aHandler;
    }
    
    @Override public final void actionPerformed(final ActionEvent e) {
            final boolean noTypeSelected = (fTypeCB.getSelectedItem().toString().length() == 0); 
            final boolean categoryModelEmpty = (fCategoryCB.getSelectedIndex() == -1);   
            boolean noCategorySelected = true;
            if(!categoryModelEmpty) 
                noCategorySelected = (fCategoryCB.getSelectedItem().toString().length() == 0);

            if (noTypeSelected && noCategorySelected) {
                Utility.showErrorMessage(frame_, "Please Select Both Type and Category.");
            } else if (noTypeSelected) {
                Utility.showErrorMessage(frame_, "Please Select Type.");
            } else if (noCategorySelected) {
                Utility.showErrorMessage(frame_, "Please Select Category.");
            } else {
                final String category = fCategoryCB.getSelectedItem().toString();
                switch (category) {
                    case "Book" :
                         new BookDialog(frame_, handler_);
                         break;
                    case "Audio" :          
                        new AudioDialog(frame_, handler_);
                        break;
                    case "Video" :
                        new VideoDialog(frame_, handler_);
                        break;
                    case "Student" :
                        new StudentDialog(frame_, handler_);
                        break;
                    case "Faculty" :
                        new FacultyDialog(frame_, handler_);
                        break;
                    case "Employee" :
                        new EmployeeDialog(frame_, handler_, false, false);
                        break;
                }
            } 
    }
}