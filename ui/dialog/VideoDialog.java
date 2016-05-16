package ui.dialog;

import handler.DataHandler;
import inventory.Video;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import document.NumbersLettersOnlyDocumentFilter;
import ui.panel.VideoButtonPanel;
import utility.Utility;

public final class VideoDialog extends InventoryAccessDialog {
    private static final long serialVersionUID = 1L;
    private final static String[] VIDEO_STORAGE = {"DVD", "Blu-ray", "VHS", "File"};

        
        public VideoDialog(final JFrame aParent, final DataHandler aHandler) {
            super("ADD");
            add(inputPanel_, BorderLayout.NORTH);
            add(new VideoButtonPanel( inputPanel_,  this, aHandler), BorderLayout.SOUTH ) ;
            buildPanel(null);
            setDialogProperties(aParent, "Add Video");
        }
        
        public VideoDialog ( final Video aVideo,
                                 final JFrame aParent,                                 
                                 final DataHandler aHandler,
                                 final JTable aTable) {
            
            super ( aVideo, "EDIT",  aParent) ;
            buildPanel(aVideo);
            add(inputPanel_, BorderLayout.NORTH);
            add( new VideoButtonPanel(inputPanel_,  this, aHandler, aTable), BorderLayout.SOUTH);
            setDialogProperties(aParent, "Edit Video");
        }
        
        public VideoDialog(final Video aVideo, final JFrame aParent) {
            super(aVideo, "VIEW", aParent);
            buildPanel(aVideo);
            inputPanel_.setTFNotEditable();
            setDialogProperties(aParent, "Add Video");
        }
        
        private final void buildPanel(final Video aVideo) {
            inputPanel_.add(new JLabel("Storage Medium: "), Utility.setConstraints(0,2));
            fLabelTF = new JTextField(15);
            
            String medium = "";
            if(mode_.equals("VIEW") || mode_.equals("EDIT")) {
                 medium = aVideo.getStorageMedium();
                 final String label = aVideo.getPublisher();
                 fLabelTF.setColumns(label.length());
                 fLabelTF.setText(label);
                 inputPanel_.addTF(fLabelTF);
                 fLabelTF.setBackground(Color.WHITE);
            }
            
            if(mode_.equals("VIEW")) {
                final JTextField mediumTF = new JTextField(medium.length());
                mediumTF.setText(medium);
                mediumTF.setBackground(Color.WHITE);     
                inputPanel_.addTF(mediumTF);
                inputPanel_.add(mediumTF,Utility.setConstraints(1,2));
            }
                
            
            if(mode_.equals("EDIT") || mode_.equals("ADD")) {
                fStorageMedCB = new JComboBox<String>(VIDEO_STORAGE);
                fStorageMedCB.insertItemAt("", 0);
                inputPanel_.add(fStorageMedCB, Utility.setConstraints(1,2));    
                final PlainDocument doc = (PlainDocument)fLabelTF.getDocument();
                doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(20, false, true));
            }   
            
            if(mode_.equals("ADD"))
                fStorageMedCB.setSelectedIndex(0);
            
            if(mode_.equals("EDIT")) 
                fStorageMedCB.setSelectedItem(medium);
            
            inputPanel_.add(new JLabel("Studio: "), Utility.setConstraints(0, 3));
            inputPanel_.add(fLabelTF, Utility.setConstraints(1, 3));
            add(inputPanel_, BorderLayout.CENTER);
        }
        
}