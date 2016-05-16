package ui.panel;

import handler.DataHandler;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import document.NumbersLettersOnlyDocumentFilter;
import listener.action.CountryListener;
import listener.action.StateListener;
import locations.Country;
import locations.City;
import utility.Utility;


public final class PersonnelInputPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String[] typePhoneArr = { "Home", "Mobile" };
    private JComboBox<String> countryCB_;
    private JComboBox<String> fStateCB;
    private JComboBox<String> fCityCB;
    private JTextField fFirstNameField;
    private JTextField fLastNameField;
    private JTextField fAddressField;
    private JTextField fZipField;
    private JTextField fViewCountryTF;
    private JTextField fViewStateTF;
    private JTextField fViewCityTF;
    private JFormattedTextField fPhoneField;
    private JTextField fEmailField;
    private JTextField fViewTypeTF;
    private final String fMode;
    private final DataHandler fHandler;
    private JRadioButton adminRadButton_;
    private JComboBox<String> typePhoneCB_;
    private final List<JComboBox<String>> fCBList;
    private final List<JTextField> fTextFieldList;
        
    
    public PersonnelInputPanel(final DataHandler aHandler,
                               final String aMode) {
        super(new GridBagLayout());
        this.fMode = aMode;
        this.fHandler = aHandler;
        this.fTextFieldList = new ArrayList<JTextField>(4);
        fCBList = new ArrayList<JComboBox<String>>(2);
        createFirstLabelText();
        createLastLabelText();
        createCityLabelCB();
        createStateLabelCB();  
        createCountryLabelCB();
        createAddressLabelText(); 
        createZipLabelText(); 
        createPhoneLabelText(); 
        createPhoneTypeLabelText(aMode); 
        createEmailLabelText();
        createMustCompleteLabel();
     }
    
        public final void setTFNotEditable() {
            for(JTextField tf : fTextFieldList) {
                tf.setEditable(false);
                tf.setBackground(Color.WHITE);
            }
           
        }
        
            public final void resetFields() {
                for(JTextField tf : fTextFieldList) 
                    tf.setText("");
                countryCB_.setSelectedIndex(0);
                fStateCB.setSelectedIndex(0);
                fCityCB.setSelectedIndex(0);
                fFirstNameField.grabFocus();
            }
        
        
          private final void createMustCompleteLabel() {
              if(fMode.equals("ADD") || fMode.equals("EDIT"))
                   add(new JLabel("* Must Be Completed"), Utility.setConstraints(1, 15));
          }
    
          
            private final void createFirstLabelText() {
                add(Utility.createLabelAsterick(fMode, "First Name"), Utility.setConstraints(0,0));
                fFirstNameField = new JTextField(20);
                final PlainDocument doc = (PlainDocument)fFirstNameField.getDocument();
                doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(25, false, true));
                fTextFieldList.add(fFirstNameField);
                add(fFirstNameField, Utility.setConstraints(1, 0));
            }
            
            
            private final void createLastLabelText() {
                final JLabel lastNameLabel = Utility.createLabelAsterick(fMode, "Last Name");
                add(lastNameLabel, Utility.setConstraints(0, 1));
                fLastNameField = new JTextField(20);
                final PlainDocument doc = (PlainDocument)fLastNameField.getDocument();
                doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(25, false, true));
                fTextFieldList.add(fLastNameField);
                add(fLastNameField, Utility.setConstraints(1, 1));
            }
            
            
            private final void createCityLabelCB() {
                add(Utility.createLabelAsterick(fMode, "City"), Utility.setConstraints(0, 4));
                final GridBagConstraints c10 = Utility.setConstraints(1, 4);
                if(fMode.equals("VIEW")) {    
                    fViewCityTF = new JTextField();
                    fTextFieldList.add(fViewCityTF);
                    add(fViewCityTF, c10);
                } else { 
                       final List<City> cities = fHandler.getCityList();
                       String[] cityArr = new String[cities.size()];
                       int index = 0;
                       for(City c : cities) {
                           cityArr[index] = c.getName();
                           index++;
                       }
                        fCityCB = new JComboBox<String>();  
                        fCityCB.insertItemAt("", 0);
                        fCityCB.setSelectedIndex(0);
                        fCBList.add(fCityCB);
                        add(fCityCB, c10);
                }
                    
            }
            
            
            private final void createStateLabelCB() {
                add(Utility.createLabelAsterick(fMode, "State"), Utility.setConstraints(0, 3));
                final GridBagConstraints c8 = Utility.setConstraints(1, 3);
                    if(fMode.equals("VIEW")) {
                        fViewStateTF = new JTextField();
                        fTextFieldList.add(fViewStateTF);
                        add(fViewStateTF, c8);
                    } else {
                        fStateCB = new JComboBox<String>();
                        fStateCB.addActionListener(new StateListener(fStateCB, 
                                fCityCB, 
                                fHandler));
                        fCBList.add(fStateCB);
                        add(fStateCB, c8);
                    }
            }
            
            
            
            private final void createCountryLabelCB() {
                add(Utility.createLabelAsterick(fMode, "Country"), Utility.setConstraints(0, 2));
                final GridBagConstraints c6 = Utility.setConstraints(1, 2);    
                if(fMode.equals("VIEW")) {
                    fViewCountryTF = new JTextField();
                    fTextFieldList.add(fViewCountryTF);
                    add(fViewCountryTF, c6);
                } else {
                    
                        final List<Country> countries = fHandler.getCountryList();
                        String[] countriesArr = new String[countries.size()];
                        int index = 0;
                        for(Country c : countries) {
                            countriesArr[index] = c.getName();
                            index++;
                        }
                        countryCB_ = new JComboBox<String>(countriesArr);
                        countryCB_.insertItemAt("", 0);
                        countryCB_.setSelectedIndex(0);
                        countryCB_.addActionListener(new CountryListener(countryCB_, 
                                                                         fStateCB, 
                                                                         fCityCB, 
                                                                         fHandler));
                        fCBList.add(countryCB_);
                        add(countryCB_, c6);
                    } 
                    
               
            }
            
            
            private final void createAddressLabelText() {
                add(Utility.createLabelAsterick(fMode, "Address"), Utility.setConstraints(0, 5));
                fAddressField = new JTextField(16);
                fTextFieldList.add(fAddressField);
                add(fAddressField, Utility.setConstraints(1, 5));
            }
            
            
            private final void createZipLabelText() {
                add(Utility.createLabelAsterick(fMode, "Zip"), Utility.setConstraints(0, 6));
                fZipField = new JTextField(5);
                final PlainDocument doc = ((PlainDocument)fZipField.getDocument());
                doc.setDocumentFilter(new NumbersLettersOnlyDocumentFilter(5, true, false));
                fTextFieldList.add(fZipField);
                add(fZipField, Utility.setConstraints(1, 6));
            }
            
            
            private final void createPhoneLabelText() {
                add(Utility.createLabelAsterick(fMode, "Phone"), Utility.setConstraints(0, 7));
                MaskFormatter mf = null;
                try {
                    mf = new MaskFormatter("(###) ###-####");   
                    mf.setPlaceholderCharacter('_');            
                } catch(final ParseException e) {
                    e.printStackTrace();
                }           
                fPhoneField = new JFormattedTextField(mf); 
                fTextFieldList.add(fPhoneField);
                add(fPhoneField, Utility.setConstraints(1, 7));
            }
            
            
            private final void createPhoneTypeLabelText(final String aMode) {
                add(Utility.createLabelAsterick(fMode, "Phone Type"), Utility.setConstraints(0, 8));
                final GridBagConstraints c18 = Utility.setConstraints(1, 8);
                if(aMode.equals("VIEW")) {
                    fViewTypeTF = new JTextField(6);
                    fTextFieldList.add(fViewCityTF);
                    add(fViewTypeTF, c18);
                } else {
                    typePhoneCB_ = new JComboBox<String>(typePhoneArr);
                    typePhoneCB_.insertItemAt("", 0);
                    if(aMode.equals("ADD"))
                        typePhoneCB_.setSelectedIndex(0);
                    add(typePhoneCB_, c18);    
                }
                
            }
            
            
            private final void createEmailLabelText() {
                add(Utility.createLabelAsterick(fMode, "Email"), Utility.setConstraints(0, 11));
                fEmailField = new JTextField(14);
                add(fEmailField, Utility.setConstraints(1, 11));
            }
    
            
            public final void setFocus(final String aFieldName) {
                JTextField tf = null;
                switch(aFieldName) {
                    case "EMAIL":
                        tf = fEmailField;
                        break;
                    case "FIRST_NAME":
                        tf = fFirstNameField;
                        break;
                    case "LAST_NAME":
                        tf = fLastNameField;
                        break;
                    case "PHONE":    
                        tf = fPhoneField;
                        break;
                    case "ZIP":
                        tf = fZipField;
                        break;
                    }        
                tf.selectAll();
                tf.grabFocus();
            }
            
            
            public final void setPhoneType(final String aType) { 
                if(fMode.equals("EDIT")) {
                    typePhoneCB_.setSelectedItem(aType);
                } else { // View
                    this.fViewTypeTF.setText(aType);
                }
            }
            
            
            public final void setGeography(final String aCountry,
                                           final String aCity,
                                           final String aState) { 
                if(fMode.equals("EDIT")) {
                  countryCB_.setSelectedItem(aCountry);
                  fStateCB.setSelectedItem(aState);
                  fCityCB.setSelectedItem(aCity);
                } else { // View
                    fViewCountryTF.setText(aCountry);
                    fViewCityTF.setText(aCity);
                    fViewStateTF.setText(aState);
                }
            }
            
            
            public final String getCountry() { return this.countryCB_.getSelectedItem().toString();}
            
            
            public final String getState() {return this.fStateCB.getSelectedItem().toString(); }
                    
            
            public final String getFirstName() { return this.fFirstNameField.getText().trim(); }
            
            
            public final void setTFFirstName(final String aFirst) {this.fFirstNameField.setText( aFirst );}
            
            
            public final String getLastName() { return this.fLastNameField.getText().trim(); }
            
            
            public final void setTFLastName(final String aLastName) { this.fLastNameField.setText( aLastName );}
        
            
            public final String getCity() {return this.fCityCB.getSelectedItem().toString();}
            
            
            public final String getAddress() {return this.fAddressField.getText().trim();}
            
            
            public final void setAddress (final String aAddress) {this.fAddressField.setText( aAddress );}
        
            
            public final String getZip () {return this.fZipField.getText().trim();}
            
            
            public final void setZip (final String aZip) {this.fZipField.setText(aZip);}
            
            
            public final String getPhone() {return this.fPhoneField.getText().trim();}
            
            
            public final void setPhone(final String aPhone) {this.fPhoneField.setText(aPhone);}
            
            
            public final void setTypePhoneCB(final String aType) {typePhoneCB_.setSelectedItem(aType);}
        
            
            public final String getTypePhone() {return this.typePhoneCB_.getSelectedItem().toString();}
        
            
            public final String getEmail() {return this.fEmailField.getText().trim();}
            
            
            public final void setEmail (final String aEmail) {this.fEmailField.setText(aEmail);}
            
            
            public final void setRadButton(final JRadioButton aRadButton) {this.adminRadButton_ = aRadButton;}
            
            
            public final boolean getRadSelected() {return this.adminRadButton_.isSelected();}
            
            
            public final void addTF (final JTextField aTF) {this.fTextFieldList.add(aTF);}
        
            
            public final List<JTextField> getTFList() {return this.fTextFieldList;}
            
            
            public final List<JComboBox<String>> getCBList () {return this.fCBList;}
}