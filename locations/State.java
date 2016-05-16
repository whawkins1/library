package locations;

public final class State {
    private String name_;
    private int id_;
    private int parentID_;
    
    public State() {}
    
       public final void setName(final String aName) {this.name_ = aName;};
    
       
       public final String getName() {return this.name_;}
    
       
       public final void setID(final int aID) {this.id_ = aID;};
       
       
       public final void setParentID(final int aParentID) {this.parentID_ = aParentID;}
       
       
       public final int getID() {return this.id_;}
       
       
       public final int getParent() {return this.parentID_;}
}