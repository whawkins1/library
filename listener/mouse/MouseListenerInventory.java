package listener.mouse;

import handler.DataHandler;
import inventory.Audio;
import inventory.Book;
import inventory.Video;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;

import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.VideoTableModel;
import ui.dialog.AudioDialog;
import ui.dialog.BookDialog;
import ui.dialog.VideoDialog;
import ui.panel.SearchPanel;
import ui.popup.PopupInventory;
import ui.table.InventoryTable;


public final class MouseListenerInventory 
                             implements MouseListener {
    private JComboBox<String> cb_;
    private SearchPanel fSearchPanel;
    private final JFrame frame_;
    private final DataHandler handler_;
    
    public MouseListenerInventory(final JFrame aFrame,
                                  final DataHandler aHandler) {
        this.frame_ = aFrame;
        this.handler_ = aHandler;
    }
    
    public MouseListenerInventory(final JComboBox<String> aCb, 
                                    final JFrame aFrame,
                                    final DataHandler aHandler) {
                this(aFrame, aHandler);
                this.cb_ = aCb;
    }
    
    public MouseListenerInventory(final SearchPanel aSearchPanel, 
                                    final JFrame aFrame,
                                    final DataHandler aHandler) {
                    this(aFrame, aHandler);
                    this.fSearchPanel = aSearchPanel;
}

    
    
    
        @Override  public final void mouseClicked(final MouseEvent me) {
            final JTable table = (JTable)me.getSource();
                if(me.getClickCount() == 2) {
                    if((me.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK); {
                        
                        String category;
                        if(cb_ != null) {
                            category = (cb_.getSelectedItem().toString());
                        } else {
                            category = fSearchPanel.getCBCategoryText();
                        }
                      final int row = table.getSelectedRow();
                      switch(category) {
                          case "Audio":
                              final AudioTableModel audioModel = ((AudioTableModel)table.getModel());
                              final Audio audio = audioModel.getAudio(row);
                              new AudioDialog(audio, frame_);
                              break;
                          case "Video":
                              final VideoTableModel videoModel = ((VideoTableModel)table.getModel());
                              final Video video = videoModel.getVideo(row);
                              new VideoDialog(video, frame_);
                              break;
                          case "Book":
                              final BookTableModel bookModel = ((BookTableModel)table.getModel());
                              final Book book = bookModel.getBook(row);
                              new BookDialog(book, frame_);
                              break;
                      }
                    }
                } 
        }
    
        @Override public final void mouseEntered(MouseEvent me) {}
    
        
        @Override public final void mouseExited(MouseEvent me) {}
    
        
        @Override  public final void mousePressed(MouseEvent me) {}
    
        
        @Override public final void mouseReleased(MouseEvent me) {
            if(me.isPopupTrigger()) {
               final InventoryTable source = (InventoryTable)me.getSource();
               final int row = source.rowAtPoint(me.getPoint());
               final int column = source.columnAtPoint(me.getPoint());
               if(!source.isRowSelected(row))
                   source.changeSelection(row, column, false, false);
            
                final PopupInventory popup = new PopupInventory(source, cb_, frame_, handler_);
                popup.show(me.getComponent(), me.getX(), me.getY());
            }
        }
}