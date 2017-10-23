package VASL.build.module.fullrules.LOSClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;

/**
 * Created by dougr_000 on 7/20/2017.
 */
public class BaseSolution {

    private VASL.LOS.Map.Hex prSeehex;
    private double prSeeLevelinHex;
    //private int prSeeLOSIndex;
    private VASL.LOS.Map.Hex prSeenhex;
    private double prSeenLevelinHex;
    //private int prSeenLOSIndex;
    private Constantvalues.AltPos prSeePositionInHex;
    private Constantvalues.AltPos prSeenPositionInHex;
    private VASL.LOS.Map.Map prScenMap;
    boolean prSolworks;
    boolean prSeeUpSlope = false; // set during play
    boolean prSeenUpSlope = false; //  set during play
    double prTotalSeenLevel = 0;  // set during play for Temp Sol and in ValidSol constructor
    double prTotalSeeLevel = 0;   // set during play for Temp Sol and in ValidSol constructor
    Constantvalues.LOS prLOSFollows = Constantvalues.LOS.NoHexGrain;         // set during play for Temp Sol in Map.HexByHexClear

    public BaseSolution(Hex PassSeehex, double PassSeelevelinhex, Constantvalues.AltPos PassSeePosition, Hex PassSeenhex, double PassSeenlevelinhex,
                        Constantvalues.AltPos PassSeenPosition, boolean PassSolWorks, VASL.LOS.Map.Map PassScenMap) {
        prSeehex = PassSeehex;
        prSeeLevelinHex = PassSeelevelinhex;
        //prSeeLOSIndex = PassSeeLOSindex;
        prSeenhex = PassSeenhex;
        prSeenLevelinHex = PassSeenlevelinhex;
        //prSeenLOSIndex = PassSeenLOSIndex;
        prSolworks = PassSolWorks;
        prSeePositionInHex = PassSeePosition;
        prSeenPositionInHex = PassSeenPosition;
        prScenMap = PassScenMap;
    }

    public Hex getSeeHex() {return prSeehex;}
    public double getSeeLevelInHex(){return prSeeLevelinHex;}
    //public int getSeeLOSIndex() {return prSeeLOSIndex;}
    public Constantvalues.AltPos getSeePositionInHex() {return prSeePositionInHex;}
    public Hex getSeenHex() {return prSeenhex;}
    public double getSeenLevelInHex() {return prSeenLevelinHex;}
    //public int getSeenLOSIndex() {return prSeenLOSIndex;}
    public Constantvalues.AltPos getSeenPositionInHex() {return prSeenPositionInHex;}
    public VASL.LOS.Map.Map getScenMap() {return prScenMap;}
    public boolean getSolworks() {return prSolworks;}
    public void setSolworks(boolean value) {prSolworks=value;}
    public boolean getSeeUpSlope() {return prSeeUpSlope;}
    public boolean getSeenUpSlope() {return prSeenUpSlope;}
    public double getTotalSeeLevel() {return prTotalSeeLevel;}
    public void setTotalSeeLevel(double value) {prTotalSeeLevel=value;}
    public double getTotalSeenLevel() {return prTotalSeenLevel;}
    public void setTotalSeenLevel(double value) {prTotalSeenLevel=value;}
    public Constantvalues.LOS getLOSFollows() {return prLOSFollows;}
    public void setLOSFollows(Constantvalues.LOS value ) {prLOSFollows=value;}

}
