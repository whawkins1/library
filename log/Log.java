package log;

public final class Log {
    private int fCheckoutID;
    private String fLastName;
    private String fFirstName;
    private String fDate;
    private String title_;
    private String fActivity;
    private String fEmail;
    private String fPersonnelType;
    private String fInventoryType;
    private int fDaysOverdue;
    
    public Log() {}
    
        public final void setCheckoutID(final int aCheckoutID) { this.fCheckoutID = aCheckoutID; }

        
        public final int getCheckoutID() { return this.fCheckoutID; }
        
        
        public final void setLastName(final String aLastName) {this.fLastName = aLastName;}
            
        
        public final String getLastName() {return this.fLastName;}
                
        
        public final void setFirstName(final String aFirstName) {this.fFirstName = aFirstName;}
                
        
        public final String getFirstName() {return this.fFirstName;}
                
        
        public final void setDate(final String aDate) {this.fDate = aDate;}
                
        
        public final String getDate() {return this.fDate;}
        
        
        public final void setPersonnelType(final String aType) { this.fPersonnelType = aType; }
        
        
        public final String getPersonnelType() { return this.fPersonnelType; }
        
        
        public final void setInventoryType(final String aType) { this.fInventoryType = aType; }
        
        
        public final String getInventoryType() { return this.fInventoryType; }
        
        
        public final void setActivity(final String aActivity) { this.fActivity = aActivity; }
        
        
        public final String getActivity() { return this.fActivity; }
        
        
        public final void setTitle(final String aTitle) {this.title_ = aTitle;}
        
        
        public final String getTitle() {return this.title_;};
        
        
        public final void setDaysOverdue(final int aDaysOverdue) { this.fDaysOverdue = aDaysOverdue; }
        
        
        public final int getDaysOverdue() { return this.fDaysOverdue; }
        
        
        public final void setEmail(final String aEmail) { this.fEmail = aEmail; }
        
        
        public final String getEmail() { return this.fEmail; }
}