package Personnel;
import java.math.BigDecimal;

public final class Student extends Personnel {
    private int fClassRank;
    private String fMajor;
    private BigDecimal fFine;
	
	public Student() {
    	super();
    }
	
	
	public final void setClassRank(final int aRank) {
		this.fClassRank = aRank;
	}
	
	
	
	public final int getClassRank() {
		return this.fClassRank;
	}
	
	
	
	public final void setMajor(final String aMajor) {
		this.fMajor = aMajor;
	}
	
	
	
	public final String getMajor() {
		return this.fMajor;
	}
	
	
	
	public final void setFine(final BigDecimal aFine) {
	    this.fFine = aFine;
	}
	
	
	
	public final BigDecimal getFine() {
	    return this.fFine;
	}	
 }