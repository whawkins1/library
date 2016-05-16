package inventory;

public final class Audio extends Inventory {
    private String label_;
	
    public Audio() {
	    super();
    }
	
	public final void setLabel(final String aLabel) {this.label_ = aLabel;}
	
	
	public final String getLabel() {return this.label_;}
}