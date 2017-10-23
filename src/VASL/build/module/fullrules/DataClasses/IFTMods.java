package VASL.build.module.fullrules.DataClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class IFTMods {
    // holds info about a DRM modifier used in a particular combat
    private int pDRM;
    private Constantvalues.IFTdrm pDRMType;
    // private pDRMName As String
    private int pTargetItem;
    private Constantvalues.Typetype pTargetType;
    private Location pTargetLocation;
    private Location pDRMLocation;

    public IFTMods(int PassDRM, Constantvalues.IFTdrm PassDRMType, int PassTargetItem, Constantvalues.Typetype PassTargettype, Location PassTargetLocation, Location PassDRMLocation) {
        pDRM = PassDRM;
        pDRMType = PassDRMType;
        pTargetItem = PassTargetItem;
        pTargetType = PassTargettype;
        pTargetLocation = PassTargetLocation;
        pDRMLocation = PassDRMLocation;
    }
    public int getDRM() {return pDRM;}
    public void setDRM(int value) {pDRM = value;}
    public Constantvalues.IFTdrm getDRMType() {return pDRMType;}
    public void setDRMType(Constantvalues.IFTdrm value){pDRMType = value;}
    public int getTargetItem() {return pTargetItem;}
    public void setTargetItem(int value) {pTargetItem = value;}
    public Constantvalues.Typetype getTargetType() {return pTargetType;}
    public void setTargetType(Constantvalues.Typetype value) {pTargetType = value;}
    public Location getTargetLocation () {return pTargetLocation;}
    public void setTargetLocation(Location value){ pTargetLocation = value;}
    public Location getDRMLocation() {return pDRMLocation;}
    public void setDRMLocation(Location value){pDRMLocation = value;}

}
