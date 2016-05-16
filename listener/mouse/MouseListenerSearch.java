package listener.mouse;

import handler.DataHandler;
import inventory.Audio;
import inventory.Book;
import inventory.Inventory;
import inventory.Video;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTable;

import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Personnel;
import Personnel.Student;
import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.InventorySearchTableModel;
import model.table.PersonnelSearchTableModel;
import model.table.StudentsTableModel;
import model.table.VideoTableModel;
import ui.dialog.AudioDialog;
import ui.dialog.BookDialog;
import ui.dialog.EmployeeDialog;
import ui.dialog.FacultyDialog;
import ui.dialog.StudentDialog;
import ui.dialog.VideoDialog;

public final class MouseListenerSearch                            
                             implements MouseListener {
    private final JFrame frame_;
    private final String fCategory;
    private final JTable fTable;
    private final DataHandler fHandler;
    
    public MouseListenerSearch(final JFrame aFrame,
                               final String aCategory,
                               final JTable aTable,
                               final DataHandler aHandler) {
        this.frame_ = aFrame;
        this.fCategory = aCategory;
        this.fTable = aTable;
        this.fHandler = aHandler;
    }
    
        @Override  public final void mouseClicked(final MouseEvent me) {
                if(me.getClickCount() == 2) {
                    if((me.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK); {
                         final int row = fTable.getSelectedRow();
                      switch(fCategory) {
                          case "All Inventory":
                              final InventorySearchTableModel inventoryModel = ((InventorySearchTableModel)fTable.getModel());
                              final Inventory i = inventoryModel.getInventory(row);
                              final String inventoryCategory = i.getType();
                                  switch(inventoryCategory) {
                                      case "Audio":
                                          new AudioDialog(((Audio)i), frame_);
                                          break;
                                      case "Book":
                                          new BookDialog(((Book)i), frame_);
                                          break;
                                      case "Video":
                                          new VideoDialog(((Video)i), frame_);
                                          break;
                                  }
                          case "Audio":
                              final AudioTableModel audioModel = ((AudioTableModel)fTable.getModel());
                              final Audio audio = audioModel.getAudio(row);
                              new AudioDialog(audio, frame_);
                              break;
                          case "Book":
                              final BookTableModel bookModel = ((BookTableModel)fTable.getModel());
                              final Book book = bookModel.getBook(row);
                              new BookDialog(book, frame_);
                              break;
                          case "Video":
                              final VideoTableModel videoModel = ((VideoTableModel)fTable.getModel());
                              final Video video = videoModel.getVideo(row);
                              new VideoDialog(video, frame_);
                              break;
                          case "All Personnel":
                              final PersonnelSearchTableModel personnelModel = ((PersonnelSearchTableModel)fTable.getModel());
                              final Personnel p = personnelModel.getPersonnel(row);
                              final String personnelCategory = p.getAccountType();
                                  switch(personnelCategory) {
                                      case "Student":
                                          new StudentDialog(((Student)p), frame_, fHandler);
                                          break;
                                      case "Faculty":
                                          new FacultyDialog(((Faculty)p), frame_, fHandler);
                                          break;
                                      case "Employee":
                                          new EmployeeDialog(((Employee)p), frame_);
                                          break;
                                  }
                          case "Student":
                              final StudentsTableModel studentModel = ((StudentsTableModel)fTable.getModel());
                              final Student s = studentModel.getStudent(row);
                              new StudentDialog(s, frame_, fHandler);
                              break;
                          case "Faculty":
                              final FacultyTableModel facultyModel = ((FacultyTableModel)fTable.getModel());
                              final Faculty f = facultyModel.getFaculty(row);
                              new FacultyDialog(f, frame_, fHandler);
                              break;
                          case "Employee":
                              final EmployeesTableModel EmployeesModel = ((EmployeesTableModel)fTable.getModel());
                              final Employee employee = EmployeesModel.getEmployee(row);
                              new EmployeeDialog(employee, frame_);
                              break;
                          }
                        }
                } 
        }
    
        @Override public final void mouseEntered(MouseEvent me) {}
    
        
        @Override public final void mouseExited(MouseEvent me) {}
    
        
        @Override  public final void mousePressed(MouseEvent me) {}
    
        
        @Override public final void mouseReleased(MouseEvent me) {}
}