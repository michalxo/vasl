package VASL.build.module.fullrules.LOSClasses;

import VASL.build.module.fullrules.Constantvalues;

/**
 * Created by dougr_000 on 7/20/2017.
 */
public class BaseSolution {

    private int prSeehexnum;
    private double prSeeLevelinHex;
    private double prSeeLOSIndex;
    private int prSeenhexnum;
    private double prSeenLevelinHex;
    private double prSeenLOSIndex;
    private Constantvalues.AltPos prSeePositionInHex;
    private Constantvalues.AltPos prSeenPositionInHex;
    private Constantvalues.Map prScenMap;
    boolean prSolworks;
    boolean prSeeUpSlope = false; // set during play
    boolean prSeenUpSlope = false; //  set during play
    double prTotalSeenLevel = 0;  // set during play for Temp Sol and in ValidSol constructor
    double prTotalSeeLevel = 0;   // set during play for Temp Sol and in ValidSol constructor
    int prLOSFollows = 0;         // set during play for Temp Sol in Map.HexByHexClear

    public BaseSolution(int PassSeehexnum, double PassSeelevelinhex, double PassSeeLOSindex, Constantvalues.AltPos PassSeePosition, int PassSeenhexnum, double PassSeenlevelinhex,
                        double PassSeenLOSIndex, Constantvalues.AltPos PassSeenPosition, boolean PassSolWorks, Constantvalues.Map PassScenMap) {
        prSeehexnum = PassSeehexnum;
        prSeeLevelinHex = PassSeelevelinhex;
        prSeeLOSIndex = PassSeeLOSindex;
        prSeenhexnum = PassSeenhexnum;
        prSeenLevelinHex = PassSeenlevelinhex;
        prSeenLOSIndex = PassSeenLOSIndex;
        prSolworks = PassSolWorks;
        prSeePositionInHex = PassSeePosition;
        prSeenPositionInHex = PassSeenPosition;
        prScenMap = PassScenMap;
    }

    public int getSeeHexNum() {return prSeehexnum;}
    public double getSeeLevelInHex(){return prSeeLevelinHex;}
    public double getSeeLOSIndex() {return prSeeLOSIndex;}
    public Constantvalues.AltPos getSeePositionInHex() {return prSeePositionInHex;}
    public int getSeenHexNum() {return prSeenhexnum;}
    public double getSeenLevelInHex() {return prSeenLevelinHex;}
    public double getSeenLOSIndex() {return prSeenLOSIndex;}
    public Constantvalues.AltPos getSeenPositionInHex() {return prSeenPositionInHex;}
    public Constantvalues.Map getScenMap() {return prScenMap;}
    public boolean getSolworks() {return prSolworks;}
    public void setSolworks(boolean value) {prSolworks=value;}
    public boolean getSeeUpSlope() {return prSeeUpSlope;}
    public boolean getSeenUpSlope() {return prSeenUpSlope;}
    public double getTotalSeeLevel() {return prTotalSeeLevel;}
    public void setTotalSeeLevel(double value) {prTotalSeeLevel=value;}
    public double getTotalSeenLevel() {return prTotalSeenLevel;}
    public void setTotalSeenLevel(double value) {prTotalSeenLevel=value;}
    public int getLOSFollows() {return prLOSFollows;}
    public void setLOSFollows(int value ) {prLOSFollows=value;}

}
