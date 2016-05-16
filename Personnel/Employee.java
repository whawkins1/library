package Personnel;

import java.util.ArrayList;
import java.util.List;

public  class Employee extends Personnel {
     private String fUsername;
     private List<IndivLog> fLogList;
     private String fHireDate;
     private int fIsAdmin;
     
    public Employee() {
        fLogList = new ArrayList<IndivLog>();
    }
	    
	    public final String getUsername() {return this.fUsername;}
	    
	    
	    public final void setUserName(final String aUsername) {this.fUsername = aUsername;}
	    
	    
	    public final void setIsAdmin(final int aIsAdmin) {this.fIsAdmin = aIsAdmin;}
	    
	    
	    public final int isAdmin() {return this.fIsAdmin;}
	    
	    
	    public final void addLog(final String aDate, final String aActivity) {
	        final IndivLog log = new IndivLog(aDate, aActivity);
	        fLogList.add(log);
	    }
	    
	    
	    public final IndivLog getLog(final int aIndex) {return this.fLogList.get(aIndex);}
	    
	    
	    public final void setHireDate(final String aHireDate) {this.fHireDate = aHireDate;}
	    
	    
	    public final String getHireDate() {return this.fHireDate;}
	    
	    //inner class
	    final class IndivLog {
	        final String fDate;
	        final String fActivity;
	        IndivLog(final String aDate,  final String aActivity) {
	            this.fDate = aDate;
	            this.fActivity = aActivity;
	        }	        
	    }
}