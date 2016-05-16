package ui.dialog;

import handler.DataHandler;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Personnel.Personnel;

import ui.panel.PersonnelInputPanel;

import utility.Utility;

public class PersonnelAccessDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    protected PersonnelInputPanel inputPanel_;
    protected String mode_;
    
    public PersonnelAccessDialog (final DataHandler aHandler,
                                  final String aMode) {
        this.mode_ = aMode;
        inputPanel_ = new PersonnelInputPanel(aHandler, mode_);
    }
    
    public PersonnelAccessDialog (final Personnel aPersonnel, 
                                  final JFrame aParent,
                                  final DataHandler aHandler,
                                  final String aMode) {
        this.mode_ = aMode;
        inputPanel_ = new PersonnelInputPanel(aHandler, mode_);
        inputPanel_.setTFFirstName(aPersonnel.getFirstName());
        inputPanel_.setTFLastName(aPersonnel.getLastName());
        inputPanel_.setAddress(aPersonnel.getAddress());
        inputPanel_.setZip(aPersonnel.getZip());
        inputPanel_.setPhone(aPersonnel.getPhone());
        inputPanel_.setEmail(aPersonnel.getEmail());
        inputPanel_.setGeography(aPersonnel.getCountry(), 
                                 aPersonnel.getCity(), 
                                 aPersonnel.getState());
        if(mode_.equals("VIEW"))
            inputPanel_.setPhoneType(aPersonnel.getTypePhone());
    }
    
        protected final void setDialogProperties(final JFrame aParent, final boolean aModal) {
            setModal(false);
            setDialogProperties(aParent);
        }
    
        protected final void setDialogProperties (final JFrame aParent) {
            setIconImage(Utility.getSmallImage());
            Utility.setCloseOperation(this);
            pack();
            setLocationRelativeTo(aParent);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setVisible(true) ;
        }
        
        public final String getMode() {return this.mode_;};
}