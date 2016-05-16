package inventory;

public final class Video extends Inventory {
    private String studio_;
    
    public Video() {
        super();
    }
        
        public final void setStudio(final String aStudio) {this.studio_ = aStudio;}
        
        
        public final String getStudio() {return this.studio_;}
}