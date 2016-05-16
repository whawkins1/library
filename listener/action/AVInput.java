package listener.action;

import javax.swing.JTextField;

import ui.dialog.InventoryAccessDialog;
import ui.panel.InventoryInputPanel;


public abstract  class AVInput {
    protected final InventoryInputPanel panel_;
    protected final InventoryAccessDialog fDialog;
    protected String title_;
    protected String callNum_;
    protected int year_;
    protected int numCopies_;
    protected String description_;
    protected String storageMedium_;
    protected String studio_;
    
    public AVInput(final InventoryInputPanel aInputPanel, 
                   final InventoryAccessDialog aDialog) {
        this.panel_ = aInputPanel;
        this.fDialog = aDialog;
    }
        
        protected final boolean validInput() {
            title_ = panel_.getTFTitle();
            callNum_ = panel_.getTFCallNum();
            year_ = Integer.parseInt(panel_.getTFYear());
            numCopies_ = panel_.getTFNumCopies();
            description_ = panel_.getTADescription();            
            storageMedium_ = fDialog.getStorageMed();
            studio_ = fDialog.getStudio();
            
            if(storageMedium_.length() == 0) 
                  return false;
          //TODO CHECK WHITESPACE AND NUMERICAL OR ALPHANUMERICAL
            for( JTextField field : panel_.getTFList () ){
                    final String contents = field.getText().trim();
                    if(contents.length() == 0) { 
                        return false;
                    }
             }
             return true;            
        }
}