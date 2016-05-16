package listener.action;

import handler.DataHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.table.AudioTableModel;
import model.table.BookTableModel;
import model.table.VideoTableModel;

public final class InventoryCBListener
                              implements ActionListener {
       private final DataHandler fHandler;
       private final JTable fTable;
       private final JComboBox<String> fCB;
       public InventoryCBListener(final DataHandler aHandler, 
                                  final JTable aTable,
                                  JComboBox<String> aCB) {
           this.fHandler = aHandler;
           this.fTable = aTable;           
           this.fCB = aCB;
       }
    
        @Override public void actionPerformed(final ActionEvent e) {
                final String selection = (fCB.getSelectedItem().toString());
                AbstractTableModel tableModel = null;
                switch(selection) {
                    case "Book":
                        tableModel = new BookTableModel(fHandler.getBookMap());
                       break;
                    case "Audio":
                        tableModel = new AudioTableModel(fHandler.getAudioMap());
                       break;
                    case "Video":
                        tableModel = new VideoTableModel(fHandler.getVideoMap());
                      break;
                    default:
                        tableModel = new DefaultTableModel();
                      break;
                }
                fTable.setModel(tableModel);
            }     
        }