package locations;

public final class Country {
    private String name_;
    private int id_;
   
   public Country() {}
   
      public final void setName(final String aName) { this.name_ = aName;}
   
      
      public final String getName() {return this.name_;}
      
      
      public final void setID(final int aID) {this.id_ = aID;}
      
      
      public final int getID() {return this.id_;}
}