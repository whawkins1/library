package ui.dialog;

import handler.DataHandler;
import inventory.Audio;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import ui.panel.AudioButtonPanel;
import utility.Utility;


public final class AudioDialog extends  InventoryAccessDialog {
    private static final long serialVersionUID = 1L;
    private final static String[] AUDIO_STORAGE = {"CD", "Vinyl", "Cassette", "File"};
    
        public AudioDialog( final JFrame aParent, final DataHandler aHandler) {
            super("ADD");
            buildPanel(null);
            add(inputPanel_, BorderLayout.NORTH);
            add(new AudioButtonPanel(inputPanel_, this, aHandler));
            setDialogProperties(aParent, "Add Audio");
        }
        
        public AudioDialog(final Audio aAudio,
                             final JFrame aParent,
                             final DataHandler aHandler,
                             final JTable aTable) {
            super(aAudio, "EDIT", aParent) ;
            buildPanel(aAudio);
            add(inputPanel_, BorderLayout.NORTH);
            add(new AudioButtonPanel(inputPanel_, this, aHandler, aTable));
            setDialogProperties(aParent, "Edit Audio");
        }
        
        public AudioDialog(final Audio aAudio, final JFrame aParent) {
            super(aAudio, "VIEW", aParent);
            buildPanel(aAudio);
            inputPanel_.setTFNotEditable();
            setDialogProperties(aParent, "View Audio");
        }
            
            
            private final void buildPanel(final Audio aAudio) {
                inputPanel_.add(new JLabel("Storage Medium: "), Utility.setConstraints(0,2));
                inputPanel_.add(new JLabel("Label: "), Utility.setConstraints(0, 3));
                fLabelTF = new JTextField();
                
                String medium = "";
                if(aAudio != null) 
                    medium = aAudio.getStorageMedium();    
                
                if(mode_.equals("VIEW")) {
                    medium = aAudio.getStorageMedium();
                    final JTextField mediumTF = new JTextField(medium, medium.length());
                    mediumTF.setText(medium);
                    mediumTF.setBackground(Color.WHITE);
                    final String label = aAudio.getPublisher();
                    fLabelTF.setColumns(label.length());
                    fLabelTF.setText(label);
                    inputPanel_.addTF(fLabelTF);
                    inputPanel_.setTFNotEditable();
                    inputPanel_.add(mediumTF, Utility.setConstraints(1, 2));
                    
                }  
                
                if(mode_.equals("EDIT") || mode_.equals("ADD")) {
                    fStorageMedCB = new JComboBox<String>(AUDIO_STORAGE);
                    inputPanel_.add(fStorageMedCB, Utility.setConstraints(1,2));
                    fLabelTF.setColumns(14);
                }                
                
                if(mode_.equals("ADD")) {
                    fStorageMedCB.insertItemAt("", 0);
                    fStorageMedCB.setSelectedIndex(0);
                }                
                
                if(mode_.equals("EDIT")) {
                    fStorageMedCB.setSelectedItem(medium);
                    fLabelTF.setText(aAudio.getPublisher());  
                }                

                inputPanel_.add(fLabelTF, Utility.setConstraints(1, 3));
                add(inputPanel_, BorderLayout.CENTER);
            }
}