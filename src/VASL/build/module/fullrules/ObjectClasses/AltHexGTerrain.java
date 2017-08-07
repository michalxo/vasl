package VASL.build.module.fullrules.ObjectClasses;

public class AltHexGTerrain {
    // This class holds hex information for hexes where LOS goes along a common hexside as per alternate hex grain hexes
    private int prUpperHexnum; // readonly
    private int prLowerHexnum;  // readonly
    private int prTempSolID;
    private int prIDRange;  // readonly
    private double prSidebaselevel;  // readonly

    // Constructors
    public AltHexGTerrain(int PassUpperHex, int PassLowerHex, int PassTempSolID, int PassRange, double PassLevel) {
        prUpperHexnum = PassUpperHex;
        prLowerHexnum = PassLowerHex;
        prTempSolID = PassTempSolID;
        if (PassRange == 1) {
            prIDRange = 1;
        } else {
            prIDRange = PassRange + 1;
        }
        prSidebaselevel = PassLevel;
    }

    // Properties
    public int getLowerHexnum() {
        return prLowerHexnum;
    }

    public int getUpperHexnum() {
        return prUpperHexnum;
    }

    public int getTempSolID() {
        return prTempSolID;
    }

    public void setTempSolID(int value) {
        prTempSolID = value;
    }

    public int getIDRange() {
        return prIDRange;
    }

    public double getSidebaselevel() {
        return prSidebaselevel;
    }

    // Methods
}
