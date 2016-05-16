package ui.dialog;

import inventory.Inventory;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ui.panel.InventoryInputPanel;
import utility.Utility;

public  class InventoryAccessDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    protected InventoryInputPanel inputPanel_;
    protected  JTextField fLabelTF;
    protected  JComboBox<String> fStorageMedCB;
    protected JComboBox<String> fBookTypeCB;
    protected String mode_;

    
    public InventoryAccessDialog(final String aMode) {
        this.mode_ = aMode;
        inputPanel_ = new InventoryInputPanel(aMode);
    }
    
    public InventoryAccessDialog(final Inventory aInventory,
                                 final String aMode,
                                 final JFrame aParent) {
        
        this(aMode);
        inputPanel_.setTFTitle(aInventory.getTitle());
        inputPanel_.setTFCallNum(aInventory.getCallNumber());
        inputPanel_.setTFYear(aInventory.getYear());
        inputPanel_.setTFNumCopies(aInventory.getNumCopies());
        inputPanel_.setTADescription(aInventory.getDescription());
    }
    
        protected final void setDialogProperties(final JFrame aParent, final String aTitle) {
            Utility.setCloseOperation(this);
            setIconImage(Utility.getSmallImage());
            setTitle(aTitle);
            pack();
            setLocationRelativeTo(aParent);
            setVisible(true);
        }
        
        
        protected final void disableTF() {
            for(JTextField tf : inputPanel_.getTFList()) 
                tf.setEnabled(false);            
        }
        
        
        public final String getStudio() { return this.fLabelTF.getText().toString().trim(); }
        
        
        public final String getStorageMed() { return fStorageMedCB.getSelectedItem().toString(); }
        
        
        public final String getMode() { return this.mode_; }
        
        
        public final String getCBBookType() { return fBookTypeCB.getSelectedItem().toString(); }
}