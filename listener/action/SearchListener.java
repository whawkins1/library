package listener.action;

import handler.DataHandler;
import inventory.Audio;
import inventory.Book;
import inventory.Inventory;
import inventory.Video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Personnel.Personnel;
import Personnel.Student;
import Personnel.Faculty;
import Personnel.Employee;
import listener.mouse.MouseListenerInventory;
import listener.mouse.MouseListenerPersonnel;
import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.InventorySearchTableModel;
import model.table.PersonnelSearchTableModel;
import model.table.StudentsTableModel;
import model.table.VideoTableModel;
import ui.panel.SearchFieldsPanel;
import ui.panel.SearchPanel;
import ui.scrollpane.ScrollPane;
import ui.table.InventoryTable;
import ui.table.PersonnelTable;
import ui.table.SearchTable;
import utility.Utility;


public final class SearchListener 
                          implements ActionListener  {
   private final JDialog dialog_;
   private final DataHandler handler_;
   private final SearchPanel fSearchPanel;
   private final SearchFieldsPanel fFieldsPanel;
   private final JTabbedPane fTabPane;
   private JFrame fParent;
    
    public SearchListener(final SearchPanel aSearchPanel,
                         final JDialog aDialog,
                         final DataHandler aHandler,
                         final SearchFieldsPanel aFieldsPanel, 
                         final JTabbedPane aTabPane) {
        this.fSearchPanel = aSearchPanel;
        this.fFieldsPanel = aFieldsPanel;
        this.dialog_ = aDialog;
        this.handler_ = aHandler;
        this.fTabPane = aTabPane;
   }
    
    
    
        @Override public final void actionPerformed(final ActionEvent event) {
                String term = (fSearchPanel.getTermText());
                final String category = (fSearchPanel.getCBCategoryText());
                final String checkedFields = fFieldsPanel.getChecked();
                final String type = (fSearchPanel.getTypeCBText());
                
                boolean complete = ((term.length() > 0) 
                                   && (category.length() > 0)
                                   && (checkedFields.length() > 0)
                                   && (checkedFields.length() > 0));
                
                if(complete) {
                    final String searchType = Utility.getSelectedTextRB(fSearchPanel.getButtonGroup());
                    String termAppended = "";
                    
                    switch(searchType) {
                        case "Any Terms":
                            termAppended = ("'" + term + "'");
                            break;
                        case "Exact Phrase":
                            termAppended = ("'\"" + term + "\"'");
                            break;
                    }
                    
                    String sql = "";
                    // Create sql string delineated by |
                    if (category.equals("All Personnel")) {
                        final String[] personnelArr = new String[]{"Student", "Employee", "Faculty"};
                        sql = createAllSQL(termAppended, checkedFields, personnelArr);
                    } else if (category.equals("All Inventory")) {
                        final String[] personnelArr = new String[]{"Audio", "Book", "Video"};
                        sql = createAllSQL(termAppended, checkedFields, personnelArr);
                    } else {
                        sql = buildSQL(termAppended, category, type, checkedFields);
                    }
                    
                    getMatchesSetModel(sql, category);
                    return;
                }
                    Utility.showErrorMessage(dialog_, "All Fields Must be Complete, PLease Try Again.");
        }
        
        
        private final String createAllSQL (final String aTerm, 
                                           final String aFields, 
                                           final String[] aPersonnelArr) {
            String sql = "";
            final String[] personnelArr = new String[]{"Student", "Faculty", "Employee"};
            final StringBuilder sb = new StringBuilder();
            for(String p : personnelArr) {
                sql = buildSQL(aTerm, p, "Personnel", aFields);
                sb.append(sql);
                sb.append(" | ");
                sql = "";
            }
            return sql;
        }
        
        
        private final String buildSQL (final String aTermFormatted, 
                                              final String aCategory, 
                                              final String aType,
                                              final String aFields) {
            final String selectValue = aType.equals("Personnel") ? "email" : "call_number";
            
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append(selectValue);
            sb.append(" FROM ");
            sb.append(aCategory);
            sb.append(" WHERE");
            sb.append(" MATCH ( " );
            sb.append(aFields);
            sb.append(" )");
            sb.append(" AGAINST ( " );
            sb.append(aTermFormatted);
            sb.append(" IN BOOLEAN MODE)");
            sb.append(";");
            
            return sb.toString();
        }
        
        private final void getMatchesSetModel(final String aSQL, 
                                              final String aCategory) {
            try(final Connection conn = Utility.getConnection()) {
                if (aCategory.equals("All Personnel")) {
                     final String[] personnelArr = aSQL.split("\\|");
                     final HashMap<String, Student> studentMap = getStudentResults(personnelArr[0], conn);
                     final HashMap<String, Faculty> facultyMap = getFacultyResults(personnelArr[1], conn);
                     final HashMap<String, Employee> employeeMap = getEmployeeResults(personnelArr[2], conn);
                     final HashMap<String, Personnel> personnelMap = new HashMap<String, Personnel>();
                     
                     personnelMap.putAll(studentMap);
                     personnelMap.putAll(facultyMap);
                     personnelMap.putAll(employeeMap);
                     final PersonnelSearchTableModel personnelModel = new PersonnelSearchTableModel(personnelMap);
                     final PersonnelTable personnelTable = new PersonnelTable();
                     personnelTable.addMouseListener(new MouseListenerPersonnel(personnelTable, fSearchPanel, fParent, handler_));
                     personnelTable.setModel(personnelModel);
                     fTabPane.add("Search Personnel", new ScrollPane(personnelTable));
                } else if (aCategory.equals("All Inventory")) {
                    final String[] inventoryArr = aSQL.split("\\|");
                    final HashMap<String, Audio> audioMap = getAudioResults(inventoryArr[0], conn);
                    final HashMap<String, Book> bookMap = getBookResults(inventoryArr[1], conn);
                    final HashMap<String, Video> videoMap = getVideoResults(inventoryArr[2], conn);
                    final HashMap<String, Inventory> inventoryMap = new HashMap<String, Inventory>();
                    
                    inventoryMap.putAll(audioMap);
                    inventoryMap.putAll(bookMap);
                    inventoryMap.putAll(videoMap);
                    final InventorySearchTableModel inventoryModel = new InventorySearchTableModel(inventoryMap);
                    final InventoryTable inventoryTable = new InventoryTable();
                    inventoryTable.addMouseListener(new MouseListenerInventory(fSearchPanel, fParent, handler_));
                    inventoryTable.setModel(inventoryModel);
                    fTabPane.add("Search Inventory", new ScrollPane(inventoryTable));
                } else {
                        switch(aCategory) {
                            case "Book":
                                 final HashMap<String, Book> bookMap = getBookResults(aSQL, conn);
                                 final BookTableModel bookModel = new BookTableModel(bookMap);
                                 final InventoryTable bookTable = new InventoryTable();
                                 bookTable.setModel(bookModel);
                                 bookTable.addMouseListener(new MouseListenerInventory(fSearchPanel, fParent, handler_));
                                 fTabPane.add("Search Books", new ScrollPane(bookTable));
                                 break;
                            case "Audio":
                                final HashMap<String, Audio> audioMap = getAudioResults(aSQL, conn);
                                final AudioTableModel audioModel = new AudioTableModel(audioMap);
                                final SearchTable audioTable = new SearchTable();
                                audioTable.setModel(audioModel);
                                audioTable.addMouseListener(new MouseListenerInventory(fSearchPanel, fParent, handler_));
                                fTabPane.add("Search Audio", new ScrollPane(audioTable));
                                break;
                            case "Video":
                                final HashMap<String, Video> videoMap = getVideoResults(aSQL, conn);
                                final VideoTableModel videoModel = new VideoTableModel(videoMap);
                                final SearchTable videoTable = new SearchTable();
                                videoTable.setModel(videoModel);
                                videoTable.addMouseListener(new MouseListenerInventory(fSearchPanel, fParent, handler_));
                                fTabPane.add("Search Videos", new ScrollPane(videoTable));
                                break;
                            case "Student":
                                final HashMap<String, Student> studentMap = getStudentResults(aSQL, conn);
                                final StudentsTableModel studentModel = new StudentsTableModel(studentMap);
                                final PersonnelTable studentTable = new PersonnelTable();
                                studentTable.setModel(studentModel);
                                studentTable.addMouseListener(new MouseListenerPersonnel(studentTable, fSearchPanel, fParent, handler_));
                                fTabPane.add("Search Students", new ScrollPane(studentTable));
                                break;
                            case "Employee":
                                final HashMap<String, Employee> employeeMap = getEmployeeResults(aSQL, conn);
                                final EmployeesTableModel employeeModel = new EmployeesTableModel(employeeMap);
                                final PersonnelTable employeeTable = new PersonnelTable();
                                employeeTable.setModel(employeeModel);
                                employeeTable.addMouseListener(new MouseListenerPersonnel(employeeTable, fSearchPanel, fParent, handler_));
                                fTabPane.add("Search Employees", new ScrollPane(employeeTable));
                                break;
                            case "Faculty":
                                final HashMap<String, Faculty> facultyMap = getFacultyResults(aSQL, conn);
                                final FacultyTableModel facultyModel = new FacultyTableModel(facultyMap);
                                final PersonnelTable facultyTable = new PersonnelTable();
                                facultyTable.setModel(facultyModel);
                                facultyTable.addMouseListener(new MouseListenerPersonnel(facultyTable, fSearchPanel, fParent, handler_));
                                fTabPane.add("Search Faculty", new ScrollPane(facultyTable));
                                break;
                        } 
                        fTabPane.setSelectedIndex(2);
                        dialog_.dispose();
                        return;  
                     }
                        Utility.showErrorMessage(null, "Matches Found, Please Try Again.");
               } catch(final SQLException e) {
                   e.printStackTrace();
                   Utility.showErrorMessage(dialog_, "Error Connection To Database.");
               }
            }
       

        
       private final HashMap<String, Book> getBookResults (final String aSQL, 
                                                           final Connection aConn) {
           
           final HashMap<String, Book> bookMap = new HashMap<String, Book>(); 
           try (final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                final ResultSet rs = pstmt.executeQuery()) {   
               
               final HashMap<String, Book> tempBookMap = handler_.getBookMap();
               while (rs.next()) {
                   final String callNumber = rs.getString("call_number");
                   final Book book = tempBookMap.get(callNumber);
                   bookMap.put(callNumber, book);                             
               }
            } catch (final SQLException e) {
                e.printStackTrace();
                Utility.showErrorMessage(dialog_, "Error Retrieving Book Results");
            }
           return bookMap;           
       }
       
       
       private final HashMap<String, Audio> getAudioResults (final String aSQL,
                                                             final Connection aConn) {
           
           final HashMap<String, Audio> audioMap = new HashMap<String, Audio>();
           try (final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                final ResultSet rs = pstmt.executeQuery()) {
               
               final HashMap<String, Audio> tempAudioMap = handler_.getAudioMap();
               while (rs.next()) {
                   final String callNumber = rs.getString("call_number");
                   final Audio audio = tempAudioMap.get(callNumber);
                   audioMap.put(callNumber, audio);
               }
           } catch (final SQLException e) {
               e.printStackTrace();
               Utility.showErrorMessage(dialog_, "Error Retrieving Audio Results");
           }
               return audioMap;
       }
       
       
       private final HashMap<String, Video> getVideoResults (final String aSQL,
                                                             final Connection aConn) {
           
           final HashMap<String, Video> videoMap = new HashMap<String, Video>();
           try (final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                final ResultSet rs = pstmt.executeQuery()) {

               final HashMap<String, Video> tempVideoMap = handler_.getVideoMap();
               while (rs.next()) {
                   final String callNumber = rs.getString("call_number");
                   final Video video = tempVideoMap.get(callNumber);
                   videoMap.put(callNumber, video);
               }
           } catch (final SQLException e) {
               e.printStackTrace();
               Utility.showErrorMessage(dialog_, "Error Retrieving Video Results");
           }
               return videoMap;
       }
       
       
       private final HashMap<String, Student> getStudentResults (final String aSQL,
                                                                 final Connection aConn) {
           
           final HashMap<String, Student> studentMap = new HashMap<String, Student>();
           try (   final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                   final ResultSet rs = pstmt.executeQuery()) {
               
               final HashMap<String, Student> tempStudentMap = handler_.getStudentMap();
               while (rs.next()) {
                   final String email = rs.getString("email");
                   final Student student = tempStudentMap.get(email);
                   studentMap.put(email, student);
               }
           } catch (final SQLException e) {
               e.printStackTrace();
               Utility.showErrorMessage(dialog_, "Error Retrieving Student Results");
           }
           return studentMap;
       }
        
        
       private final HashMap<String, Employee> getEmployeeResults (final String aSQL,
                                                                   final Connection aConn) {
           
           final HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();

           try (final Connection conn = Utility.getConnection();
                final PreparedStatement pstmt = conn.prepareStatement(aSQL);
                final ResultSet rs = pstmt.executeQuery()){
               
               final HashMap<String, Employee> tempEmployeeMap = handler_.getEmployeeMap();
               while (rs.next()) {
                   final String email = rs.getString("email");
                   final Employee employee = tempEmployeeMap.get(email);
                   employeeMap.put(email, employee);
               }
           } catch (final SQLException e) {
               e.printStackTrace();
               Utility.showErrorMessage(dialog_, "Error Retrieving Employee Results");
           }
               return employeeMap;
       }
       
       
       private final HashMap<String, Faculty> getFacultyResults (final String aSQL,
                                                                 final Connection aConn) {
           final HashMap<String, Faculty> facultyMap = new HashMap<String, Faculty>();
           try (final PreparedStatement pstmt = aConn.prepareStatement(aSQL);
                final ResultSet rs = pstmt.executeQuery()){

               final HashMap<String, Faculty> tempFacultyMap = handler_.getFacultyMap();    
               while (rs.next()) {
                   final String email = rs.getString("email");
                   final Faculty faculty = tempFacultyMap.get(email);
                   facultyMap.put(email, faculty);
               }
           } catch (final SQLException e) {
               e.printStackTrace();
               Utility.showErrorMessage(dialog_, "Error Retrieving Faculty Results");
           }
           return facultyMap;
       }
       
       public final void setParent(final JFrame aParent) { this.fParent = aParent; }
       
       
}