package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public class German467Movec implements MovingPersuniti {
    
    private boolean concealedvalue;
    private String myOBname;
    private int currhex;
    private int currhexloc;
    private int currhexpos;
    private int OBID;
    private double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private boolean usingencircvalue;
    private boolean hasldrbvalue;
    private double mfusedvalue;
    private int AMvalue;
    private int Dashvalue;
    private int LOCIndexvalue;
    private int HexEntSideCross;
    // private mysmokee As Integer

    public German467Movec(String PassOBname, int Passhexnum, int Passhexlocation, int PassPosition, int PassLocIndex,
                          int PassOBUnitID, Constantvalues.VisibilityStatus PassVisibilityStatus, int PassConID, int PasshexEnteredSideCrossedLastMove) {
        myOBname = PassOBname;
        currhex = Passhexnum;
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
    public int getAssaultMove () {return AMvalue;}
    public void setAssaultMove(int value) {AMvalue = value;}
    public int getDash () {return Dashvalue;}
    public void setDash(int value) {Dashvalue = value;}
    public int getHexEnteredSideCrossed () {return HexEntSideCross;}
    public void setHexEnteredSideCrossed(int value) {HexEntSideCross = value;}
    public int getSmokeE () {return 1;}
    public boolean getusingEncirc () {return usingencircvalue;}
    public void setusingEncirc(boolean value) {usingencircvalue = value;}

}
