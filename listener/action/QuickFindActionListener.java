package listener.action;

import handler.DataHandler;
import inventory.Audio;
import inventory.Video;
import inventory.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import ui.dialog.AudioDialog;
import ui.dialog.BookDialog;
import ui.dialog.EmployeeDialog;
import ui.dialog.FacultyDialog;
import ui.dialog.StudentDialog;
import ui.dialog.VideoDialog;
import utility.Utility;
import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Student;

public final class QuickFindActionListener 
                                       implements ActionListener {
    private final ButtonGroup fBG;
    private final DataHandler fHandler;
    private final JTextField fTF;
    private final int fIsAdmin;
    
    
    public QuickFindActionListener(final ButtonGroup aBG, 
                                      final JTextField aTF, 
                                      final DataHandler aHandler,
                                      final int aIsAdmin) {
        this.fBG = aBG;
        this.fHandler = aHandler;
        this.fTF = aTF;
        this.fIsAdmin = aIsAdmin;
    }
    

        @Override public final void actionPerformed(final ActionEvent evt) {
            final String selected = getRadioButtonSelected();
            final String text = fTF.getText().trim();
            
            final boolean isInput = (text.length() > 0);
            if(isInput) {
                switch(selected) {
                    case "Personnel":
                        final boolean validEmail = Utility.validEmail(text);
                        if (validEmail) {
                            final HashMap<String, Student> studentMap = fHandler.getStudentMap();
                            final HashMap<String, Faculty> facultyMap = fHandler.getFacultyMap();
                        
                            if (studentMap.containsKey(text)) {
                                final Student s = studentMap.get(text);
                                //TODO import parent
                                new StudentDialog(s, null, fHandler);
                            } else if (facultyMap.containsKey(text)) {
                                final Faculty f = facultyMap.get(text);
                                new FacultyDialog(f, null, fHandler);
                            } else {
                                if (fIsAdmin == 1) {
                                    final HashMap<String, Employee> employeeMap = fHandler.getEmployeeMap();    
                                    final Employee e = employeeMap.get(text);
                                    new EmployeeDialog(e, null);
                                } else {
                                    Utility.showErrorMessage(null, text + " Not Found, Please Try Again.");
                                }
                            }
                        }
                      break;
                    case "Inventory":
                        final HashMap<String, Audio> audioMap = fHandler.getAudioMap();
                        final HashMap<String, Book> bookMap = fHandler.getBookMap();
                        final HashMap<String, Video> videoMap = fHandler.getVideoMap();
                        
                        if (audioMap.containsKey(text)) {
                            final Audio a = audioMap.get(text);
                            new AudioDialog(a, null);
                        } else if (bookMap.containsKey(text)) {
                            final Book b = bookMap.get(text);
                            new BookDialog(b, null);
                        } else if (videoMap.containsKey(text)) {
                            final Video v = videoMap.get(text);
                            new VideoDialog(v, null);
                        } else {
                            Utility.showErrorMessage(null, text + " Not Found, Please Try Again.");
                        }
                      break;   
                }
                return;
            } 
            Utility.showErrorMessage(null, "Must Input Key to Process Find.");
        }
        
        
        private final String getRadioButtonSelected () {
            for (Enumeration<AbstractButton> buttons = fBG.getElements(); 
                 buttons.hasMoreElements();) { 
                 final AbstractButton button = buttons.nextElement();
                 
                 if (button.isSelected()) {
                     return button.getText();
                 }
            }
            return null;
        }
}