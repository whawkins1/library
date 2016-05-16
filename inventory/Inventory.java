package inventory;

public abstract class Inventory {
	protected String fTitle;
	protected String fCallNumber;
	protected String fAuthor;
	protected String fCheckoutDate;
	protected String fReturnDate;
	protected int fYear;
    protected String fStorageMedium;
	protected int fNumCopies;
	protected int fNumAvailable;
	protected String fDescription;
	protected String fPublisher;
	protected float fDemandFactor;
	protected String genre_;
    protected String fClassID;
    protected String dateAdded_;
    protected String fType;
	
	public Inventory() {}
	   public final void setType (final String aType) { this.fType = aType; }
	   
	   
	   public final String getType() { return this.fType; }
	
	   
	   public final void setClassType(final String aID) {this.fClassID = aID;}
	   
	   
	   public final String getClassType() {return this.fClassID;}
	
	   
	   public final void setStorageMedium(final String aMedium) {this.fStorageMedium = aMedium;}
	   
	   
	   public final String getStorageMedium() {return this.fStorageMedium;}
	
	   
	   public final void setAvailCopies(final int aCopies) {this.fNumAvailable = aCopies;}
	   
	   
	   public final int getAvailableCopies() {return this.fNumAvailable;}
	
	   
	   public final void setCheckoutDate(String aDate) {this.fCheckoutDate = aDate;}
		
	   
		public final String getCheckoutDate() {return fCheckoutDate;}
		
		
		public final void setNumCopies(final int aNumCopies) {this.fNumCopies = aNumCopies;}
		
		
		public final void setPublisher(final String aPublisher) {this.fPublisher = aPublisher;}
		
		
		public final String getPublisher() {return this.fPublisher;}
		
		
		public final int getNumCopies() {return this.fNumCopies;}
		
		
		public final void setDemandFactor(final float aDemandFactor) {this.fDemandFactor = aDemandFactor;}
		
		
		public final float getDemandFactor() {return this.fDemandFactor;}
		
		
		public final void setDescription(final String aDescription) {this.fDescription = aDescription;}
		
		
		public final String getDescription(){return this.fDescription;}
		
		
		public final void setTitle(String aTitle) {this.fTitle = aTitle;}

		
		public final String getTitle() {return this.fTitle;}
		
		
		public final void setGenre(String aGenre) {this.genre_ = aGenre;}
		
		
		public final String getGenre() {return this.genre_;}
		
		
		public final void setDateAdded(final String aDate) {this.dateAdded_ = aDate;}
		
		
		public final String getDateAdded() {return this.dateAdded_;}
		
		
		public final void setReturnDate(String aDate) {this.fReturnDate = aDate;}
		
		
		public final void setYear(final int aYear) {this.fYear = aYear;}
		
		
		public final int getYear() {return this.fYear;}
		
		
		public final String getReturnDate() {return this.fReturnDate;}
		
		
		public final void setCallNum(String aCallNumber) {this.fCallNumber = aCallNumber;}
		
		
		public final String getCallNumber() {return this.fCallNumber;}		
		
		
		public final void setAuthor(String aAuthor) {this.fAuthor = aAuthor;}
		
		
		public final String getAuthor() {return this.fAuthor;}
}