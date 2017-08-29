package VASL.build.module.fullrules.LOSClasses;

import VASL.build.module.fullrules.Constantvalues;

/**
 * Created by dougr_000 on 7/20/2017.
 */
public class TempSolution extends BaseSolution {
    private int TempSolIDvalue;

    // constructor
    public TempSolution(int PassSeehexnum, double PassSeelevelinhex, int PassSeeLOSindex, Constantvalues.AltPos PassSeePosition, int PassSeenhexnum,
                        double PassSeenlevelinhex, int PassSeenLOSIndex, Constantvalues.AltPos PassSeenPosition, boolean PassSolWorks, int PassTempSolID, VASL.LOS.Map.Map PassScenMap) {
        super(PassSeehexnum,PassSeelevelinhex,PassSeeLOSindex,PassSeePosition,PassSeenhexnum,PassSeenlevelinhex,PassSeenLOSIndex,PassSeenPosition,PassSolWorks,PassScenMap);
        TempSolIDvalue =PassTempSolID +1;
    }

    // methods
    public int getID() {
        return TempSolIDvalue;
    }
    public void setID(int value) {
        TempSolIDvalue = value;
    }

}
