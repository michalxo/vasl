package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class German467Movec implements MovingPersuniti {
    
    private boolean concealedvalue;
    private String myOBname;
    private Hex currhex;
    private Location currhexloc;
    private Constantvalues.AltPos currhexpos;
    private int OBID;
    private double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private boolean usingencircvalue;
    private boolean hasldrbvalue;
    private double mfusedvalue;
    private Constantvalues.MovementStatus AMvalue;
    private Constantvalues.MovementStatus Dashvalue;
    private int LOCIndexvalue;
    private int HexEntSideCross;
    // private mysmokee As Integer

    public German467Movec(String PassOBname, Hex Passhex, Location Passhexlocation, Constantvalues.AltPos PassPosition, int PassLocIndex,
                          int PassOBUnitID, Constantvalues.VisibilityStatus PassVisibilityStatus, int PassConID, int PasshexEnteredSideCrossedLastMove) {
        myOBname = PassOBname;
        currhex = Passhex;
        currhexloc = Passhexlocation;
        currhexpos = PassPosition;
        LOCIndexvalue = PassLocIndex;
        OBID = PassOBUnitID;
        if (PassVisibilityStatus == Constantvalues.VisibilityStatus.Concealed && PassConID > 0) {concealedvalue=true;}
        UsingDTvalue = false; UsingRBvalue = false;
        HexEntSideCross = PasshexEnteredSideCrossedLastMove;
    }
    public double CalcMF() {return 4;}
    public double getMFAvailable() {return MFleft;}
    public void setMFAvailable(double value){MFleft=value;}
    public String getItemName () {return myOBname;}
    public int getItemID() {return OBID;}
    public int getIPC() {return 3;}
    public boolean IsDummy() {return false;}
    public boolean getIsConcealed () {return concealedvalue;}
    public void setIsConcealed(boolean value) {concealedvalue = value;}
    public boolean getUsingDT () {return UsingDTvalue;}
    public void setUsingDT(boolean value) {UsingDTvalue = value;}
    public boolean getUsingRoadBonus() {return UsingRBvalue;}
    public void setUsingRoadBonus(boolean value) {UsingRBvalue = value;}
    public boolean getHasLdrBonus () {return hasldrbvalue;}
    public void setHasLdrBonus(boolean value) {hasldrbvalue = value;}
    public double getMFUsed () {return mfusedvalue;}
    public void setMFUsed(double value) {mfusedvalue = value;}
    public boolean getPPImpact () {return false;}
    public Constantvalues.MovementStatus getAssaultMove () {return AMvalue;}
    public void setAssaultMove(Constantvalues.MovementStatus value) {AMvalue = value;}
    public Constantvalues.MovementStatus getDash () {return Dashvalue;}
    public void setDash(Constantvalues.MovementStatus value) {Dashvalue = value;}
    public int getHexEnteredSideCrossed () {return HexEntSideCross;}
    public void setHexEnteredSideCrossed(int value) {HexEntSideCross = value;}
    public int getSmokeE () {return 1;}
    public boolean getusingEncirc () {return usingencircvalue;}
    public void setusingEncirc(boolean value) {usingencircvalue = value;}

}
