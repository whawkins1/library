package utility;

import inventory.Inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.math.BigInteger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

import ui.dialog.SettingsDialog;
import ui.dialog.SigninDialog;

import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.EmployeesTableModel;
import model.table.FacultyTableModel;
import model.table.StudentsTableModel;
import model.table.VideoTableModel;

import Personnel.Personnel;

import com.sun.glass.events.KeyEvent;

public final class Utility {
    private final static String GET_TITLE = "SELECT inventory.title "
                                            + "FROM (SELECT a.title, a.call_number "
                                            + "      FROM Audio AS a "
                                            + "      UNION ALL SELECT b.title, b.call_number "
                                            + "      FROM books AS b "
                                            + "      UNION ALL SELECT v.title, v.call_number "
                                            + "      FROM video AS v) AS inventory "
                                            + "WHERE inventory.call_number = (?);";
    
    private final static String INSERT_SIGN_OFF = "INSERT INTO employee_logs (email, activity) "
                                                + "VALUES(?, ?);";
    
    private final static String REG_EXP_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                              + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    
     public Utility() {}
        
        public final static Connection getConnection() {
            
             Connection conn = null;
             final Preferences prefs = Preferences.userNodeForPackage(Utility.class);
            
           /*  try {
                prefs.clear();
            } catch (BackingStoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
             
             
             final boolean settingsExists = (prefs.get("URL", null) != null) && 
                                            (prefs.get("USERNAME", null) != null) && 
                                            (prefs.get("PASSWORD", null) != null);
             boolean notClosed = true;
             
             if (!settingsExists) {
                     final SettingsDialog sd = new SettingsDialog(null);
                     notClosed = sd.showDialog();
             } 
             
          
             if (notClosed) { 
                 try {
                     URL = prefs.get("URL", "");
                     USERNAME = prefs.get("USERNAME", "");
                     PASSWORD = prefs.get("PASSWORD", "");
                     Class.forName("com.mysql.jdbc.Driver");
                     //connection = DriverManager.getConnection( "jdbc:mysql://localhost/library", "root", "chase" );
                     conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 } catch (SQLException ex1) {
                     showErrorMessage(null, "Error Connecting To Database.");
                     ex1.printStackTrace();
                 } catch (ClassNotFoundException ex2) {
                     showErrorMessage(null, "Error Finding com.mysql.jdbc.Driver.");
                     ex2.printStackTrace();
                 }   
             }
             return conn;
         }
         
        
         public final static boolean processSignOff(final String aEmail) {
             boolean success = false;
             try(final Connection conn = Utility.getConnection();
                 final PreparedStatement pstmt = conn.prepareStatement(INSERT_SIGN_OFF)) {
                 pstmt.setString(1, aEmail);
                 pstmt.setString(2, "SIGN OFF");
                 final int rowsAffected = pstmt.executeUpdate();
                 success = (rowsAffected != 0);
             } catch(final SQLException e) {
                 e.printStackTrace();
             }
             return success;
         }
         
         public final static boolean validEmail(final String aEmail) {
                 final Pattern pattern = Pattern.compile(REG_EXP_EMAIL);
                 final Matcher matcher = pattern.matcher(aEmail);
                 return matcher.matches(); 
             }
        
         
         public final static JLabel createLabelAsterick(final String aMode, final String aText) {
             final String textConc =  (aMode.equals("ADD") || aMode.equals("EDIT")) ? (aText + "*: ") :
                                                                                      (aText + ": ");
             return (new JLabel(textConc));
         }
         
         public final static BufferedImage getSmallImage() {
         BufferedImage image = null;
         try {
             image = ImageIO.read(SigninDialog.class.getResource("/images/librarysmall.png"));
            } catch (final IOException e) {
              e.printStackTrace();
            }
         return image;
         }
         
         
         public static boolean checkExists(final String aQuery, final String aCheckString) {
             boolean exists = false; 
             ResultSet rs = null;
             try(final Connection conn = getConnection();
                 final PreparedStatement pstmt = conn.prepareStatement(aQuery);){
                 pstmt.setString(1, aCheckString);
                 rs = pstmt.executeQuery();
                 exists = rs.next();
                 rs.close();
              } catch(final SQLException e) {
                  e.printStackTrace();
              } 
             return exists;
         }
         
         
         
         public static final int getNumRowsSelected(final JTable aTable) {
             final int[] selectedRowsArr = aTable.getSelectedRows(); 
                return selectedRowsArr.length;
         }
         
         
         public final static JLabel createLabelTitle(final String aTitle) {
             final JLabel l = new JLabel(aTitle);
             l.setFont(new Font("Arial", Font.PLAIN, 12));
             l.setBackground(Color.white);
             l.setOpaque(true);
             return l;
         }
         
         
         public final static String getTitle(final String aID, final Connection aConn)  {
             String title = "";
             try(final PreparedStatement pstmt = aConn.prepareStatement(GET_TITLE)) {
                 pstmt.setString(1, aID);
                 final ResultSet rs = pstmt.executeQuery();
                 while(rs.next())
                     title = rs.getString(1);
             } catch(final SQLException e) {
                 e.printStackTrace();
             }
             return title;
         }
         
         
         public final static String joinDates(final LocalDate aLd) {
             final String month = aLd.getMonth().toString();
             final String day = String.valueOf(aLd.getDayOfMonth());
             final String year = String.valueOf(aLd.getYear());
             
             final String dateConc = (month + " " + day + ", " + year);
             return dateConc;
         }
         
         
         public static void setCloseOperation(final Window aWindow) {
             final KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
             final String dispatchClosingAction = "com.spodding.tackline.dispatch:WINDOW_CLOSING";
             final Action dispatch = new AbstractAction() {
                 private static final long serialVersionUID = 1L;
                 public final void actionPerformed(ActionEvent ae) {
                     aWindow.dispatchEvent(new WindowEvent(
                             aWindow, WindowEvent.WINDOW_CLOSING
                     ));
                 }
             };
             
             JRootPane rp = null;
             if(aWindow instanceof JDialog) {
                 final JDialog d = (JDialog)aWindow;
                 rp = d.getRootPane();
             } else if(aWindow instanceof JFrame) {
                 final JFrame f = (JFrame)aWindow;
                 rp = f.getRootPane();
             }
             rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                     escapeStroke, dispatchClosingAction);
             rp.getActionMap().put(dispatchClosingAction, dispatch);
         }
          
         
         
          public final static JButton createButton(final String aTitle, 
                                             final String aCommand, 
                                             final ActionListener aListener) {
             final JButton b = new JButton();
             b.setText(aTitle);
             b.addActionListener(aListener);
             b.setActionCommand(aCommand);
             return b;
         }
         
          
          
         public final static GridBagConstraints setConstraints(final int x, final int y) {
             GridBagConstraints c = new GridBagConstraints();
             c.anchor = GridBagConstraints.EAST;
             c.gridx = x;
             c.gridy = y;
             c.insets = new Insets(2, 0 , 2, 0);
             return c;
           }
         
         
         public final static boolean containsWhiteSpace(final String aWord) {
             final Pattern pattern = Pattern.compile("\\s");
             final Matcher matcher = pattern.matcher(aWord);
             final boolean containsWS = matcher.find();
             return containsWS;
         }
                  
         
         public final static void showErrorMessage(final Component aParent, final String aMessage) {
             JOptionPane.showMessageDialog(aParent, 
                                           aMessage, 
                                           "Error", 
                                           JOptionPane.ERROR_MESSAGE);
         }
         
         
         
         public final static void showInfoMessage(final Component aParent, final String aMessage) {
             JOptionPane.showMessageDialog(aParent, 
                                           aMessage,
                                           "Message",
                                           JOptionPane.INFORMATION_MESSAGE);
         }
         
                  
         
         public final static String getSelectedTextRB(final ButtonGroup aBG) {
             String text = "";
             final Enumeration<AbstractButton> buttons = aBG.getElements();
             while(buttons.hasMoreElements()) {
                 final AbstractButton button = buttons.nextElement();
                 if(button.isSelected()) {
                     text = button.getText();
                     break;
                 }
             }
             return text;
         }
    
         
         
         public final static List<String> createKeyList(final HashMap<String, ?> aMap) {
            final List<String> keyList = new ArrayList<String>();
              for(String key : aMap.keySet()) {
                  keyList.add(key);    
              }       
              return keyList;
         }
         
         
    
         public final static Inventory getInventory(final JTable aTable) {
                 final TableModel model = aTable.getModel();
                 final String modelName = (model.getClass().getName());
                 final int selectedRow = aTable.getSelectedRow();
                 switch(modelName) {
                        case "Book":
                            final BookTableModel bookModel = ((BookTableModel)model);
                            return bookModel.getBook(selectedRow);
                        case "Audio":
                            final AudioTableModel audioModel = ((AudioTableModel)model);
                            return audioModel.getAudio(selectedRow);
                        case "Video":
                            final VideoTableModel videoModel = ((VideoTableModel)model);
                            return videoModel.getVideo(selectedRow);
                        default:
                            return null;
                 }
         }
         
         
         
         public final static Personnel getPersonnel(final JTable aTable) {
            final TableModel model = aTable.getModel();
            final String modelName = (model.getClass().getName());
            final int selectedRow = aTable.getSelectedRow();
             switch(modelName) {
                 case "StudentsTableModel":
                     final StudentsTableModel studentModel = ((StudentsTableModel)model);
                     studentModel.removeStudent(selectedRow);;
                 case "FacultyTableModel":
                     final FacultyTableModel facultyModel = ((FacultyTableModel)model);
                     return facultyModel.getFaculty(selectedRow);
                 case "EmployeeTableModel":
                     final EmployeesTableModel employeeModel = ((EmployeesTableModel)model);
                     return employeeModel.getEmployee(selectedRow);
                    default:
                        return null;
                 }
         }
           
         
         public final static String generateHash(final String aPass) {
             final int iterations = 1000;
             final char[] chars = aPass.toCharArray();
             final byte[] salt = getSalt().getBytes();
             final PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
             String hashStr = "";
             try {
                 SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  
                 final byte[] hash = skf.generateSecret(spec).getEncoded(); 
                 hashStr = iterations + ":" + toHex(salt) + ":" + toHex(hash);
             } catch (NoSuchAlgorithmException e) {
                 e.printStackTrace();
             } catch (InvalidKeySpecException e) {
                 e.printStackTrace();
             }            
                 return hashStr;
         }
         
         public static boolean validatePassword(String originalPassword, String storedPassword)  {
             int diff = 0;
             String[] parts = storedPassword.split(":");
             final int iterations = Integer.parseInt(parts[0]);
             try {
                 byte[] salt = fromHex(parts[1]);
                 byte[] hash = fromHex(parts[2]);
             
              
             final PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
             final SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
             byte[] testHash = skf.generateSecret(spec).getEncoded();
                 diff = hash.length ^ testHash.length;
                 for(int i = 0; i < hash.length && i < testHash.length; i++) {
                     diff |= hash[i] ^ testHash[i];
                 }
             } catch(final InvalidKeySpecException e1) {
                 e1.printStackTrace();
             } catch(final NoSuchAlgorithmException e2) {
                 e2.printStackTrace();
             }
             return (diff == 0);
         }
             
    
         private static String toHex(byte[] aArray) {
             final BigInteger bi = new BigInteger(1, aArray);
             final String hex =  bi.toString(16);
             final int paddingLength = (aArray.length * 2) - hex.length();
             if(paddingLength > 0) {
                 return String.format("%0" + paddingLength + "d", 0) + hex;
             } else {
                 return hex;
             }
         }
         
         private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
         {
             byte[] bytes = new byte[hex.length() / 2];
             for(int i = 0; i<bytes.length ;i++)
             {
                 bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
             }
             return bytes;
         }
         
         
         private static final String getSalt() {
             SecureRandom sr = null;
             try {
                 sr = SecureRandom.getInstance("SHA1PRNG");
             } catch (NoSuchAlgorithmException e) {
                 e.printStackTrace();
             }
                 final byte[] salt = new byte[16];
                 sr.nextBytes(salt);
             return salt.toString();
         }
         
         
         public final static int executeRemove (final String aSqlStatement, final String aId) {
             int success = -1;
             try(final Connection conn = getConnection();
                 final PreparedStatement preparedStatement = conn.prepareStatement(aSqlStatement)) {
                  preparedStatement.setString(1, aId);
                  success = preparedStatement.executeUpdate();                  
             }  catch ( SQLException sqlException ) {
                   sqlException.printStackTrace();
                    System.exit (1);
             } 
             return success;
         }            
         
         
         
         public final static boolean isAllLetters(final String aWord) {
             boolean foundNonLetter = false;
             for(int i = 0; i < aWord.length(); i++) {
                 final char charAt = aWord.charAt(i);
                 if(Character.isLetter(charAt)) {
                     foundNonLetter = true;
                     break;
                 }
             }
             return foundNonLetter;
         }
    
         
         
         public final static boolean isValidInput(final String aInput, final String aRegex) {
             final Pattern pattern = Pattern.compile(aRegex);
             final Matcher matcher = pattern.matcher(aInput);
             return matcher.matches(); 
         }
    }