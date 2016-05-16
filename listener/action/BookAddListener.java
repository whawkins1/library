package listener.action;

import handler.DataHandler;
import inventory.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.table.BookTableModel;
import ui.dialog.BookDialog;
import ui.panel.InventoryInputPanel;
import utility.Utility;


public final class BookAddListener 
                         implements ActionListener {
    
        private final static String ADD_QUERY = "INSERT INTO books (call_number, publisher, "
                                          + "title, author, type, year, number_copies, "
                                          + "genre, description, available_copies) "
                                          + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private final static String UPDATE = "UPDATE books SET call_number = ?, publisher = ?, "
                                           + "title = ?, author = ?, type = ?, year = ?, "
                                           + "number_copies = ?, genre = ?, description = ? "
                                           + "WHERE call_number = ?";
    
    private final static String  EXISTS = "SELECT * FROM (SELECT a.call_number FROM audio AS a "
                                          + "UNION ALL "
                                          + "SELECT b.call_number FROM books AS b "
                                          + "UNION ALL "
                                          + "SELECT v.call_number FROM video AS v) AS i "
                                          + "WHERE call_number = ?;";
    
    private final BookDialog fParent;
    private final InventoryInputPanel inputPanel_;
    private String type_;
    private final DataHandler handler_;
    private final String mode_;
    private String title_; 
    private String callNum_;
    private String author_;
    private String publisher_;
    private int year_;
    private int numCopies_;
    private String genre_;
    private String description_;
    private BookDialog fDialog;
    private JTable fTable;

    public BookAddListener(final InventoryInputPanel aInfoPanel, 
                           final BookDialog aDialog, 
                           final DataHandler aHandler) {
        this.fParent = aDialog;
        this.mode_ = aDialog.getMode();
        this.inputPanel_ = aInfoPanel;
        this.handler_ = aHandler;
        this.fDialog = aDialog;
    }
    
    public BookAddListener(final InventoryInputPanel aInfoPanel, 
            final BookDialog aDialog, 
            final DataHandler aHandler,
            final JTable aTable) {
          this(aInfoPanel, aDialog, aHandler);
          this.fTable = aTable;
    }
    
    
    
        @Override public void actionPerformed(final ActionEvent ae) {
                boolean fieldsNotEmpty = true;
                    List<JTextField> tfList = inputPanel_.getTFList();
                    for(JTextField tf : tfList) {
                        final String text = (tf.getText().trim());     
                        if(text.length() == 0) 
                           fieldsNotEmpty =  false;  
                    }
                
                if(fieldsNotEmpty) {
                    boolean success = false;
                    int reply = -1;
                    Book book;
                    type_ = fParent.getCBBookType(); 
                    switch(mode_) {
                        case "ADD":
                            final boolean exists = Utility.checkExists(EXISTS, callNum_);
                              if(!exists) {  
                                    book = new Book();
                                    if (type_.equals("public")) {
                                        book.setType(type_);
                                        setValuesAddBook (book);
                                        return;
                                    }
                                    book.setType(type_);
                                    success = setValuesAddBook (book);
                                    if(success) {
                                        reply = JOptionPane.showConfirmDialog(fDialog, 
                                                "Add Another Book?", 
                                                "Add", 
                                                JOptionPane.YES_NO_OPTION,
                                                JOptionPane.PLAIN_MESSAGE
                                                );
                                     } else {
                                     reply = JOptionPane.showConfirmDialog(fDialog, 
                                                                    "Error Adding, Try Again?", 
                                                                    "Error", 
                                                                    JOptionPane.YES_NO_OPTION,
                                                                    JOptionPane.ERROR_MESSAGE
                                                                   );
                                     }
                              } else {
                                  Utility.showErrorMessage(fParent, 
                                          "The Call Number" + callNum_ + 
                                          " Already Exists, Please Try Again.");
                              }
                              break;
                        case "EDIT":
                            final int row = fTable.getSelectedRow();
                            final BookTableModel model = (BookTableModel)fTable.getModel();
                            book = model.getBook(row);                           
                            
                            success = setValuesAddBook(book);
                            if(success) {
                                
                                Utility.showInfoMessage(fDialog, "Success Editing"); 
                                fDialog.dispose();
                            } else {
                                reply = JOptionPane.showConfirmDialog(fDialog, 
                                                                      "Error Editing, Try Again?", 
                                                                       "Error", 
                                                                       JOptionPane.YES_NO_OPTION,
                                                                       JOptionPane.ERROR_MESSAGE
                                                                      );
                            }
                            break;
                    }
                    if(reply == JOptionPane.NO_OPTION)
                        fDialog.dispose();
                } else {
                    Utility.showErrorMessage(fDialog, "Invalid Entry(s), Please Try Again.");
                }
        }
         
        
        
        private final boolean setValuesAddBook(final Book aBook) {
                  boolean success =  false;
                  title_ = inputPanel_.getTFTitle().trim();
                  callNum_ = inputPanel_.getCallNumber();
                  author_ = fParent.getTFAuthor().trim();
                  publisher_ = fParent.getTFPublisher().trim();
                  year_ = Integer.parseInt(inputPanel_.getTFYear());
                  numCopies_ = inputPanel_.getTFNumCopies();
                  genre_ = fParent.getTFGenre().trim();
                  description_ = inputPanel_.getTADescription().trim();       
                  final String originalCallNumber = aBook.getCallNumber();
                    switch(mode_) {
                      case "ADD":
                         success = executeQuery(ADD_QUERY, callNum_);
                         break;
                      case "EDIT":
                         success = executeQuery(UPDATE, originalCallNumber);
                         break;
                  }
                    if(success) {
                        aBook.setTitle(title_);
                        aBook.setCallNum(callNum_);
                        aBook.setAuthor(author_);
                        aBook.setGenre(genre_);
                        aBook.setPublisher(publisher_);
                        aBook.setYear(year_);
                        aBook.setNumCopies(numCopies_);
                        aBook.setGenre(genre_);
                        aBook.setDescription(description_);
                        aBook.setType(type_);
                        final HashMap<String, Book> map = handler_.getBookMap();
                        if(mode_.equals("EDIT") && !originalCallNumber.equals(callNum_)) {
                            final BookTableModel model = (BookTableModel)fTable.getModel();
                            model.removeBook(fTable.getSelectedRow());
                            map.remove(aBook.getCallNumber());
                            model.addKey(callNum_);
                        }                            
                        map.put(callNum_, aBook);
                    }
                return success;
          }    
                
                private final boolean executeQuery(final String aQuery, 
                                                   final String aCallNumber) {
                    int count = 0;
                    try (final Connection conn = Utility.getConnection();
                         final PreparedStatement pstmt = conn.prepareStatement(aQuery)) {
                            pstmt.setString(1, callNum_);
                            pstmt.setString(2, publisher_);
                            pstmt.setString(3, title_);
                            pstmt.setString(4, author_);
                            pstmt.setString(5, type_);
                            pstmt.setInt(6, year_);
                            pstmt.setInt(7, numCopies_);
                            pstmt.setString(8, genre_);
                            pstmt.setString(9, description_);
                            pstmt.setInt(10, numCopies_);
                            if(mode_.equals("EDIT")) 
                                pstmt.setString(11, aCallNumber);
                            count = pstmt.executeUpdate();
                    } catch(final SQLException e) {
                        e.printStackTrace();
                    }
                    return (count > 0);
        }       
}