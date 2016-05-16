package ui.dialog;

import handler.DataHandler;
import inventory.Book;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import document.NumbersLettersOnlyDocumentFilter;
import ui.panel.BookButtonPanel;
import utility.Utility;


public final class BookDialog extends InventoryAccessDialog  {
    private static final long serialVersionUID = 1L;
    private final static String[] BOOK_TYPES = {"Public", "Reference"};    
    private JTextField fAuthorField;
    private JTextField fPublisherTF;
    private JTextField fGenreTF;
    
    public BookDialog (final JFrame aParent, 
                       final DataHandler aHandler) {
             super("ADD");
             buildPanel(null);           
             add( new BookButtonPanel(inputPanel_, this, aHandler));
             setDialogProperties( aParent, "Add Book" );
    }
    
    public BookDialog(final Book aBook,
                      final JFrame aParent,                 
                      final DataHandler aHandler,
                      final JTable aTable) {
        super ( aBook, "EDIT", aParent) ;
        buildPanel(aBook);
        this.mode_ = "EDIT";
        add( new BookButtonPanel ( inputPanel_, this, aHandler, aTable) );
        setDialogProperties ( aParent, "Edit Book" ) ;
    }
    
    public BookDialog(final Book aBook, final JFrame aParent) {
        super(aBook, "VIEW", aParent);
        buildPanel(aBook);
        inputPanel_.setTFNotEditable();
        setDialogProperties(aParent, "View Book");
    }
    
        private final void buildPanel(final Book aBook) {
            fAuthorField = new JTextField();
            fPublisherTF = new JTextField();
            fGenreTF = new JTextField();
            inputPanel_.add(Utility.createLabelAsterick(mode_, "Type"), Utility.setConstraints(0, 6));
            
            if(mode_.equals("ADD")) {
                fAuthorField.setColumns(15);
                fPublisherTF.setColumns(15);
                fGenreTF.setColumns(15);
            }
            
            String type = "";
            if(mode_.equals("EDIT") || mode_.equals("VIEW")) { 
                type = aBook.getType();
                final String author = aBook.getAuthor();
                int length = author.length();
                fAuthorField.setText(author);
                fAuthorField.setColumns(length);                
                final String publisher = aBook.getPublisher();
                length = publisher.length();
                fPublisherTF.setColumns(length);
                fPublisherTF.setText(publisher);
                inputPanel_.addTF(fPublisherTF);
                final String genre = aBook.getGenre();
                length = genre.length();
                fGenreTF.setColumns(length);
                fGenreTF.setText(genre);
                inputPanel_.addTF(fGenreTF);
                
            } 
            
            if(mode_.equals("VIEW")) {
                final JTextField typeTF = new JTextField();
                final int length = type.length();
                typeTF.setColumns(length);
                typeTF.setText(type);
                inputPanel_.addTF(typeTF);
                inputPanel_.add(typeTF, Utility.setConstraints(1, 6));
            }
            
            if(mode_.equals("EDIT") || mode_.equals("ADD")) {
                final PlainDocument doc = (PlainDocument)fAuthorField.getDocument();
                doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(20, false, true));    
                final PlainDocument docPublisher = (PlainDocument)fPublisherTF.getDocument();
                docPublisher.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(20, false, true));
                final PlainDocument docGenre = (PlainDocument)fGenreTF.getDocument();            
                docGenre.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(20, false, true));
            }
            
            if(mode_.equals("ADD") || mode_.equals("EDIT")) {
                fBookTypeCB = new JComboBox<String>(BOOK_TYPES);
                fBookTypeCB.insertItemAt("", 0);
                inputPanel_.add(fBookTypeCB, Utility.setConstraints(1, 6));
            }
            
            if(mode_.equals("ADD"))
                fBookTypeCB.setSelectedIndex(0);
            
            if(mode_.equals("EDIT")) 
                fBookTypeCB.setSelectedItem(type);
            
            inputPanel_.add(Utility.createLabelAsterick(mode_, "Author"), Utility.setConstraints(0,2));
            inputPanel_.add(fAuthorField, Utility.setConstraints(1, 2));        
            inputPanel_.add(Utility.createLabelAsterick(mode_, "Publisher"), Utility.setConstraints(0, 7));
            inputPanel_.add(fPublisherTF, Utility.setConstraints(1,7));
            inputPanel_.add(Utility.createLabelAsterick(mode_, "Genre"), Utility.setConstraints(0, 8));
            inputPanel_.add(fGenreTF, Utility.setConstraints(1, 8));
            add(inputPanel_, BorderLayout.NORTH);
        }
        
        
        public final String getTFAuthor() { return fAuthorField.getText().trim(); }
        
        public final String getTFPublisher() { return fPublisherTF.getText().trim(); }
        
        public final String getTFGenre() { return fGenreTF.getText().trim(); }
}