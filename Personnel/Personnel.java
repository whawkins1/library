package Personnel;

import inventory.Inventory;

import java.util.List;

public abstract class Personnel {
	protected String fID;
	protected String fFirstName;
	protected String fLastName;
	protected String fCity;
	protected String fState;
	protected String country_;
	protected String fAddress;
	protected List<Inventory> fOverdueBooks;
	protected boolean fOnHold;
	protected String fZip;
	protected String fPhone;
	protected String fEmail;
	protected int fNumBooksCheckedout;
	protected int fNumOtherCheckedout;
	protected String fClassID;
	protected String phoneType_;
	protected String dateAdded_;
	protected String fAccountType;
	
    public Personnel() {
    	fOverdueBooks = null;
    	fOnHold = false;
    }
    
        public final void setAccountType (final String aType) { this.fAccountType = aType; }
        
        
        public final String getAccountType () { return this.fAccountType; }
    
        
        public final void setDateAdded(final String aDate) {this.dateAdded_ = aDate;};
        
        
        public final String getDateAdded() {return this.dateAdded_;}
    
        
        public final void setClassType(final String aID) {this.fClassID = aID;}
        
        
        public final String getClassType() {return this.fClassID;}
        
        
        public final void setEmail(final String aEmail) {this.fEmail = aEmail;}
            
        
        public final String getEmail() {return this.fEmail;}
        
        
        public final void setZip(final String aZip) {this.fZip = aZip;}
            
        
        public final String getZip() {return this.fZip;}
            
        
        public final void setPhone(final String aPhone) {this.fPhone = aPhone;}
            
        
        public final String getPhone() {return this.fPhone;}
        
        
        public final void setTypePhone(final String aType) {this.phoneType_ = aType;}
    
        
        public final String getTypePhone() {return this.phoneType_;}
            
        
        public final void setCheckoutCountBook(final int aCount) {this.fNumBooksCheckedout = aCount;}
            
        
        public final int getBookCheckkOutCount() {return this.fNumBooksCheckedout;}
    
        
        public final void setCheckoutCountOther(final int aCount) {this.fNumOtherCheckedout = aCount;}
            
        
        public final int getCheckoutCountOther() {return this.fNumOtherCheckedout;}
    	
        
        public final void setFirstName(String aFirstName) {this.fFirstName = aFirstName;}
    		   
        
        public String getFirstName() {return fFirstName;}
    		   
        
        public final void setLastName(String aLastName) {this.fLastName = aLastName;}
    		   
        
        public final String getLastName() {return this.fLastName;}
    		   
        
        public final void setCity(String aCity) {this.fCity = aCity;}
    		   
        
        public final String getCity() {return this.fCity;}
    		   
        
        public final void setState(String aState) {this.fState = aState;}
    		   
        
        public final String getState() {return this.fState;}
        
        
        public final void setCountry(final String aCountry) {this.country_ = aCountry;}
        
        
        public final String getCountry() {return this.country_;}
    		   
        
        public final void setAddress(String aAddress) {this.fAddress = aAddress;}
    		   
        
        public final String getAddress() {return this.fAddress;}
    		   
        
        public final void setOverdueBooks(List<Inventory> aOverdueBooks) {this.fOverdueBooks = aOverdueBooks;}
    		   
        
        public final List<Inventory> getOverdueBooks() {return this.fOverdueBooks;}
    		   
        
        public final void setHold(boolean aHold) {this.fOnHold = aHold;}
    		   
        
        public final boolean isOnHold() {return fOnHold;}
}