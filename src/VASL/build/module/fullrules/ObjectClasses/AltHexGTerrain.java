package VASL.build.module.fullrules.ObjectClasses;

public class AltHexGTerrain {
    // This class holds hex information for hexes where LOS goes along a common hexside as per alternate hex grain hexes
    private String prUpperHexname; // readonly
    private String prLowerHexname;  // readonly
    private int prTempSolID;
    private int prIDRange;  // readonly
    private double prSidebaselevel;  // readonly

    // Constructors
    public AltHexGTerrain(String PassUpperHexname, String PassLowerHexname, int PassTempSolID, int PassRange, double PassLevel) {
        prUpperHexname = PassUpperHexname;
        prLowerHexname = PassLowerHexname;
        prTempSolID = PassTempSolID;
        if (PassRange == 1) {
            prIDRange = 1;
        } else {
            prIDRange = PassRange + 1;
        }
        prSidebaselevel = PassLevel;
    }

    // Properties
    public String getLowerHexname() {
        return prLowerHexname;
    }

    public String getUpperHexname() {
        return prUpperHexname;
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
