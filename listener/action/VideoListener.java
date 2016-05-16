package listener.action;

import handler.DataHandler;
import inventory.Video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.table.VideoTableModel;
import ui.dialog.VideoDialog;
import ui.panel.InventoryInputPanel;
import utility.Utility;

public final class VideoListener extends AVInput
                                        implements ActionListener {
    
    private final static String INSERT_NEW = "INSERT INTO video (call_number, title, "
                                           + "storage_medium, studio, year, "
                                           + "number_copies, description, available_copies) "
                                           + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    
    private final static String UPDATE = "UPDATE video SET call_number = ?, title = ?, "
                                              + "storage_medium = ?, studio = ?, year = ?, "
                                              + "number_copies = ?, description = ? "
                                              + "WHERE call_number = ?";
    
    private final static String CHECK_EXISTS = "SELECT COUNT * FROM (SELECT a.call_number FROM audio AS a "
                                             + "UNION ALL "
                                             + "SELECT b.call_number FROM books AS b "
                                             + "UNION ALL "
                                             + "SELECT v.call_number FROM video AS v) AS i "
                                             + "WHERE call_number = ?;";
    
    private final DataHandler handler_;
    private final String mode_;
    private final VideoDialog fDialog;
    private JTable fTable;
    
    public VideoListener(final InventoryInputPanel aInputPanel, 
                         final VideoDialog aDialog,
                         final DataHandler aHandler) {
        super(aInputPanel, aDialog);
        this.mode_ = aDialog.getMode();
        this.handler_ = aHandler;     
        this.fDialog = aDialog;
    }
    
    public VideoListener(final InventoryInputPanel aInputPanel, 
                        final VideoDialog aDialog,
                        final DataHandler aHandler,
                        final JTable aTable) {
        this(aInputPanel, aDialog, aHandler);
        this.fTable = aTable;
}
        
    

        @Override public final void actionPerformed(final ActionEvent event) {
            if(validInput()) { 
                boolean success = false;
                int reply = -1;
                switch(mode_) {
                    case "ADD":
                        final boolean audioExists = Utility.checkExists(CHECK_EXISTS, callNum_);
                        if (!audioExists) {
                            success = setValuesAdd(INSERT_NEW);
                            if(success) {
                                reply = JOptionPane.showConfirmDialog(fDialog, 
                                                                                "Success, Add Another Audio?", 
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
                            break;
                        } else {
                            Utility.showErrorMessage(fDialog, "Invalid Entry(s), Please Try Again.");
                        }
                    case "EDIT":
                            success = setValuesAdd(UPDATE);
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
              Utility.showErrorMessage(fDialog, 
                                       "The Call Number" + callNum_ + 
                                       " Already Exists, Please Try Again.");
          }                
        }
        
           
           private final boolean setValuesAdd(final String aQuery) {
               int count = -1;
               boolean success = false;
               VideoTableModel model;
               Video video = null;
               String originalCallNumber;
               final HashMap<String, Video> videoMap = handler_.getVideoMap();
               try(final Connection conn = Utility.getConnection();
                   final PreparedStatement pstmt = conn.prepareStatement(aQuery)) {
                   pstmt.setString(1, callNum_);
                   pstmt.setString(2, title_);
                   pstmt.setString(3, storageMedium_);
                   pstmt.setString(4, studio_);
                   pstmt.setInt(5, year_);
                   pstmt.setInt(6, numCopies_);
                   pstmt.setString(7, description_);
                   pstmt.setInt(8, numCopies_);
                   if (mode_.equals ("EDIT")) {
                       final int row = fTable.getSelectedRow();
                       model = (VideoTableModel)fTable.getModel();
                       video = model.getVideo(row);
                       originalCallNumber = video.getCallNumber();
                       String updateCallNumber = callNum_;
                       if(!originalCallNumber.equals(callNum_)) {
                           videoMap.remove(originalCallNumber);
                           updateCallNumber = originalCallNumber;
                           model.addKey(callNum_);
                           model.removeVideo(row);                           
                       }
                           pstmt.setString(9, updateCallNumber);
                   }
                             
                   count = pstmt.executeUpdate();
                   success = (count > 0);
                   if(success) {
                       if(mode_.equals("ADD")) 
                           video = new Video();
                       video.setTitle(title_);
                       video.setStorageMedium(storageMedium_);
                       video.setCallNum(callNum_);
                       video.setYear(year_) ;
                       video.setStudio(studio_);
                       video.setNumCopies(numCopies_);
                       video.setDescription(description_);
                       videoMap.put(callNum_, video);
                   }                   
               } catch(final SQLException e) {
                   e.printStackTrace();
               }
               return (success);
           }
      }