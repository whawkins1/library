package listener.action;

import inventory.Audio;
import inventory.Book;
import inventory.Video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import utility.Utility;

import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.VideoTableModel;

public final class InventoryRemoveListener 
                                  implements ActionListener {    
    private final static String  REMOVE_BOOK = "DELETE FROM book WHERE  call_number = (?);";
    private final static String  REMOVE_AUDIO = "DELETE FROM audio WHERE  call_number = (?);";
    private final static String  REMOVE_VIDEO = "DELETE FROM video WHERE  call_number = (?);";
    
    private final JTable fTable;
    private final JComboBox<String> cb_;
    
    public InventoryRemoveListener(final JTable aTable, final JComboBox<String> aCB) {
        this.fTable = aTable;
        this.cb_ = aCB;
    }

    
    @Override public void actionPerformed(final ActionEvent event) {
            final int[] rowArr = fTable.getSelectedRows();
            final int result = JOptionPane.showConfirmDialog(null, 
                                                             "Remove " + rowArr.length + " Selection(s)?",
                                                             "Remove",
                                                             JOptionPane.YES_NO_OPTION,
                                                             JOptionPane.PLAIN_MESSAGE);
            
            if(result == JOptionPane.YES_OPTION) {
                final String type = cb_.getSelectedItem().toString();
                int count = 0;
                int rowsEffected = 0;
                switch(type) {
                    case "Book":
                        final BookTableModel bookModel = ((BookTableModel)fTable.getModel());
                        for(int i : rowArr) {
                            final Book book = bookModel.getBook(i);
                            rowsEffected = Utility.executeRemove(REMOVE_BOOK, book.getCallNumber());
                            if(rowsEffected == 1) {
                                bookModel.removeBook(i);
                                count += rowsEffected;
                            }
                        }
                        if(count > 0) {
                            Utility.showInfoMessage(null, "Success Deleting " + count + " Entry(s).");
                        } else {
                            Utility.showErrorMessage(null, "Error Removing Entry(s)");
                        }
                        break;
                    case "Audio":
                        final AudioTableModel audioModel = ((AudioTableModel)fTable.getModel());
                        for(int i : rowArr) {
                            final Audio audio = audioModel.getAudio(i);
                            rowsEffected = Utility.executeRemove(REMOVE_AUDIO, audio.getCallNumber());
                            if(rowsEffected == 1) {
                                audioModel.removeAudio(i);
                                count += rowsEffected;
                            }                            
                            if(count > 0) {
                                Utility.showInfoMessage(null, "Success Deleting " + count + " Entry(s).");
                            } else {
                                Utility.showErrorMessage(null, "Error Removing Entry(s)");
                            }
                        }
                        break;                        
                    case "Video":
                        final VideoTableModel videoModel = ((VideoTableModel)fTable.getModel());
                        for(int i : rowArr) {
                            final Video video = videoModel.getVideo(i);
                           rowsEffected = Utility.executeRemove(REMOVE_VIDEO, video.getCallNumber());
                           if(rowsEffected == 1) {
                               videoModel.removeVideo(i);
                               count += rowsEffected;
                           }
                           if(count > 0) {
                               Utility.showInfoMessage(null, "Success Deleting " + count + " Entry(s).");
                           } else {
                               Utility.showErrorMessage(null, "Error Removing Entry(s)");
                           }
                        }     
                        break;
                }
            }
        }
}