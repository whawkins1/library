package payment;

public final class Payment {
    private String fFirstName;
    private String fLastName;
    private String fEmail;
    private String fDate;
    private String fPayment;
    
    
    public Payment () {}
    
        public final void setFirstName(final String aFirstName) { this.fFirstName = aFirstName; }
        
        
        public final String getFirstName() { return this.fFirstName; }
        
        
        public final void setLastName(final String aLastName) { this.fLastName = aLastName; }
        
        
        public final String getLastName() { return this.fLastName; }
        
        
        public final String getEmail () { return this.fEmail;  }
        
        
        public final void setEmail (final String aEmail) { this.fEmail = aEmail;  }
        
        
        public final void setDate (final String aDate) { this.fDate = aDate; }

        
        public final String getDate () { return this.fDate;  }
        
        
        public final void setPayment(final String aPayment) { this.fPayment = aPayment; }
        
        
        public final String getPayment() { return this.fPayment; }
}