package listener.action;

import javax.swing.JDialog;

import ui.panel.PersonnelInputPanel;

import utility.Utility;

public abstract class PersonnelInput  {
    private final static String REG_EXP_ZIP = "^[0-9]{5}(?:-[0-9]{4})?$";
    private final static String REG_EXP_PHONE = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
    private final static String ERR_MSG_PHONE = "Invalid Phone Number Must Be in the Format of: "
                                              + "\"(xxx)xxx-xxxx\", \"xxx-xxx-xxxx\", "
                                              + "\"xxxxxxxxxx\", \"(xxx)-xxx-xxxx\","
                                              + " Please Try Again!";
    private final static String ERR_MSG_ZIP = "Invalid Zip Code Must Be in the Format of: "
                                            + "\"xxxxx\" or \"xxxxx-xxxx\""; 
    private final static String ERR_MSG_LAST_NAME = "Last Name Must Be All Letters, Please Try Again!";
    private final static String ERR_MSG_FIRST_NAME = "First Name Must Be All Letters, Please Try Again!";
    private final static String ERR_MSG_EMAIL = "Invalid E-Mail Must Be in the Format of: "
                                              + "abc@xyz.net or ab.c@tx.gov, Please Try Again!"; 
    protected final PersonnelInputPanel panel_;
    protected final JDialog dialog_;
    
   public PersonnelInput(final PersonnelInputPanel aPanel, final JDialog aDialog) {
       this.panel_ = aPanel;
       this.dialog_ = aDialog;
   }
   
       protected final boolean validInput() {
           final String firstName = panel_.getFirstName();
           final boolean isAllLettersFirst = Utility.isAllLetters(firstName);
           
           final String email = panel_.getEmail();
           final boolean validEmail = Utility.validEmail(email);
            if (validEmail) {
               if(isAllLettersFirst) {
                   final String lastName = panel_.getLastName();
                   final boolean isAllLettersLast = Utility.isAllLetters(lastName);
                   
                   if(isAllLettersLast) {
                   final String zip = panel_.getZip();
                   final boolean validZip = Utility.isValidInput(zip, REG_EXP_ZIP);
                       if(validZip) {
                           final String phone = panel_.getPhone();
                           final boolean validPhone = Utility.isValidInput(phone, REG_EXP_PHONE);           
                           if(validPhone) 
                               return true;
                               Utility.showErrorMessage(dialog_, ERR_MSG_PHONE);
                               panel_.setFocus("PHONE");
                       } else {
                       Utility.showErrorMessage(dialog_, ERR_MSG_ZIP);
                       panel_.setFocus("ZIP");
                   }
                   } else {
                       Utility.showErrorMessage(dialog_, ERR_MSG_LAST_NAME);
                       panel_.setFocus("LAST_NAME");
                   }    
               } else {
                   Utility.showErrorMessage(dialog_, ERR_MSG_FIRST_NAME); 
                   panel_.setFocus("FIRST_NAME");
               }
            } else {
                 Utility.showErrorMessage(dialog_, ERR_MSG_EMAIL);
                 panel_.setFocus("EMAIL");
            }
            return false;
        }
}