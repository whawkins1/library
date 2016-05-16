package listener.action;

import handler.DataHandler;

import inventory.Audio;
import inventory.Book;
import inventory.Video;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;

import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.VideoTableModel;

import ui.dialog.AudioDialog;
import ui.dialog.BookDialog;
import ui.dialog.VideoDialog;

public final class InventoryListener
                                implements ActionListener{
    private final JTable table_;
    private final JComboBox<String> cb_;
    private final JFrame frame_;
    private final DataHandler handler_;
    private final String mode_;
    
    public InventoryListener(final JTable aTable,
                                 final JComboBox<String> aCB, 
                                 final JFrame aFrame,
                                 final String aMode,
                                 final DataHandler aHandler) {
        this.table_ = aTable;
        this.cb_ = aCB;
        this.frame_ = aFrame;
        this.mode_ = aMode;
        this.handler_ = aHandler;
    }

        @Override public final void actionPerformed(final ActionEvent event) {
            final String type = (cb_.getSelectedItem().toString());
            final int row = table_.getSelectedRow();
            switch(type) {
                case "Audio":
                    displayAudio(row);
                    break;
                case "Video":
                    displayVideo(row);
                    break;
                case "Book":
                    displayBook(row);
                    break;
            }
        }
        
        private final void displayAudio(final int aRow) {
            final AudioTableModel audioModel = ((AudioTableModel)table_.getModel());
            final Audio audio = audioModel.getAudio(aRow);
            switch(mode_) {
                case "ADD":
                    new AudioDialog(frame_, handler_);
                    break;
                case "EDIT":
                    new AudioDialog(audio, frame_, handler_, table_);
                    break;
                case "VIEW":
                    new AudioDialog(audio, frame_);
                    break;
                    }        
        }
        
        private final void displayVideo(final int aRow) {
            final VideoTableModel videoModel = ((VideoTableModel)table_.getModel());
            final Video video = videoModel.getVideo(aRow);
            switch(mode_) {
                case "ADD":
                    new VideoDialog(frame_, handler_);
                    break;
                case "EDIT":
                    new VideoDialog(video, frame_, handler_, table_);
                    break;
                case "VIEW":
                    new VideoDialog(video, frame_);
                    break;
                }
        }
        
        private final void displayBook(final int aRow) {
            final BookTableModel bookModel = ((BookTableModel)table_.getModel());
            final Book book = bookModel.getBook(aRow);
            switch(mode_) {
                case "ADD":
                    new BookDialog(frame_, handler_);
                    break;
                case "EDIT":
                    new BookDialog(book, frame_, handler_, table_);
                    break;
                case "VIEW":
                    new BookDialog(book, frame_);
                    break;
             }
        }
}