package map;

import inventory.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

public final class BookMap extends HashMap<String, Book>  {
    private static final long serialVersionUID = 1L;
    private static final String SELECT_BOOKS = ("SELECT call_number AS call_number, date_added, available_copies, "
                                               + "GetDemandFactor(call_number) AS demand_factor, "
                                               + "publisher, author, genre, title, number_copies, year, description, type "
                                               + "FROM books;");
    
    public BookMap(final Connection aConn) {
        try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_BOOKS);
            final ResultSet rs = pstmt.executeQuery()) {
            createBook(rs);  
        } catch(final SQLException e) {
            Utility.showErrorMessage(null, "Error Executing Book Query!");  
            e.printStackTrace();
        }
   }
    
    public BookMap(final ResultSet aRS) {
            createBook(aRS);    
    }
    
        private final void createBook(final ResultSet aRS) {
            try {
                while(aRS.next()) {
                    final Book book = new Book();
                    book.setCallNum(aRS.getString("call_number"));
                    book.setAuthor(aRS.getString("author"));
                    book.setTitle(aRS.getString("title"));
                    book.setNumCopies(aRS.getInt("number_copies"));
                    book.setType(aRS.getString("type"));
                    book.setGenre(aRS.getString("genre"));
                    book.setPublisher(aRS.getString("publisher"));
                    book.setDescription(aRS.getString("description"));
                    book.setYear(aRS.getInt("year"));
                    book.setDemandFactor(aRS.getFloat("demand_factor"));
                    book.setType("Book");
                    book.setAvailCopies(aRS.getInt("available_copies"));
                    book.setDateAdded((aRS.getDate("date_added")).toString());
                    put(book.getCallNumber(), book);
                }
            } catch (final SQLException e) {
                e.printStackTrace();
                Utility.showErrorMessage(null, "Error Loading Book Data!");
            }
        }
}