package Personnel;

import java.math.BigDecimal;

public  class Faculty extends Personnel {
	private BigDecimal fFine;
    private String fDept;
	public Faculty() {
		super();
	}
	
	public final void setFine(final BigDecimal aFine) {
	    this.fFine = aFine;
	}
	
	public final BigDecimal getFine() {
	    return this.fFine;
	}
	
	public final void setDept(final String aDept) {
		this.fDept = aDept;
	}
	
	public final String getDept() {
		return this.fDept;
	}
}
