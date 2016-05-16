package listener.action;

import handler.DataHandler;
import inventory.Audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.table.AudioTableModel;
import ui.dialog.AudioDialog;
import ui.dialog.InventoryAccessDialog;
import ui.panel.InventoryInputPanel;
import utility.Utility;

public final class AudioListener extends AVInput
                              implements ActionListener {
    
    
    private final static String INSERT_NEW = "INSERT INTO audio (call_number, title, "
                                           + "storage_medium, label, year, "
                                           + "num_copies, description, available_copies) "
                                           + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    
    private final static String UPDATE = "UPDATE audio SET call_number = ?, title = ?, "
                                               + "storage_medium = ?, label = ?, year = ?, "
                                               + "num_copies = ?, description = ? "
                                               + "WHERE call_number = ?";
    
    private final static String CHECK_EXISTS = "SELECT * FROM (SELECT a.call_number FROM audio AS a "
                                             + "UNION ALL "
                                             + "SELECT b.call_number FROM books AS b "
                                             + "UNION ALL "
                                             + "SELECT v.call_number FROM video AS v) AS i "
                                             + "WHERE call_number = ?;";
    
    private String mode_;
     private final DataHandler handler_;
     private final InventoryAccessDialog fDialog;
     private JTable fTable;
     
     public AudioListener(final InventoryInputPanel aInputPanel, 
                           final AudioDialog aDialog,
                           final DataHandler aHandler) {
     
         super(aInputPanel, aDialog);
         this.mode_ = aDialog.getMode();
         this.handler_ = aHandler;
         this.fDialog = aDialog;         
     }
     
     public AudioListener(final InventoryInputPanel aInputPanel, 
                          final AudioDialog aDialog,
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
                                    success = executeQuery(INSERT_NEW);
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
                                    success = executeQuery(UPDATE);
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
         
         private final boolean executeQuery(final String aQuery) {
             int count = -1;
             String origCallNumber = "";
             Audio audio;
             AudioTableModel model = null;
             
             if(mode_.equals("ADD")) {
                 audio = new Audio();
             } else {
                 model = (AudioTableModel)fTable.getModel();
                 final int row = fTable.getSelectedRow();
                 audio = model.getAudio((row));
                 origCallNumber = audio.getCallNumber();
             }
                                                     
             audio.setTitle(title_);
             audio.setCallNum(callNum_);
             audio.setYear(year_);
             audio.setLabel( fDialog.getStudio());
             audio.setNumCopies(numCopies_);
             audio.setDescription(description_);
             audio.setStorageMedium(storageMedium_);
             final HashMap<String, Audio> audioMap = handler_.getAudioMap();
             if(mode_.equals("EDIT") && (!origCallNumber.equals(callNum_))) {
                 audioMap.remove(origCallNumber);
                 model.addKey(callNum_);
                 final int row = fTable.getSelectedRow();
                 model.removeAudio(row);
             }                 
             audioMap.put(callNum_, audio);
             
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
                 if(mode_.equals("EDIT"))
                     pstmt.setString(9, origCallNumber);
                 count = pstmt.executeUpdate();
             } catch(final SQLException e) {
                 e.printStackTrace();
             }
             return (count > 0);
         }
}