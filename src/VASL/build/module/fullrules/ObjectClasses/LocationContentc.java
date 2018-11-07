package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class LocationContentc {
    // this class sets structure for description of objects that are in a given location in a hex
    // this includes OB units (visible, concealed and hidden), vehicles (V, C, and H), Guns (V, C, H), OBSW (V, C, H), Terrain (Visible or Hidden), and Concealment (dummies only)
    private int ObjIDvalue;   // the unique instance id from the database table
    private Location locvalue;  //  which location in the hex; using vaslocations
    private Constantvalues.Typetype TypeIDvalue;  // what type of object (which LOB type of unit, of gun, or vehicle or sw)
    private boolean selectedvalue; // may be useful to allow identification of which items being acted upon.

    // constructor
    public LocationContentc (int ObjectID, Location hexlocation, Constantvalues.Typetype ObjectTypeID) {
        // called by Action classes New after creating collections
        // may need overloads
        ObjIDvalue = ObjectID;
        locvalue = hexlocation;
        TypeIDvalue = ObjectTypeID;
        selectedvalue = false;  // default value
    }

    public int getObjID() {return ObjIDvalue;}

    public Location getLocation() {return locvalue;}
    public void setLocation(Location value) {locvalue = value;}
    public Constantvalues.Typetype getTypeIDvalue() {return TypeIDvalue;}
    public void setTypeIDvalue(Constantvalues.Typetype value) {TypeIDvalue = value;}
    public boolean IsSelected() {return selectedvalue;}
    public void setIsSelected(boolean value){selectedvalue = value;}

}
