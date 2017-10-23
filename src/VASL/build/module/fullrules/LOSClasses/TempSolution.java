package VASL.build.module.fullrules.LOSClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;

/**
 * Created by dougr_000 on 7/20/2017.
 */
public class TempSolution extends BaseSolution {
    private int TempSolIDvalue;

    // constructor
    public TempSolution(Hex PassSeehex, double PassSeelevelinhex, Constantvalues.AltPos PassSeePosition, Hex PassSeenhex,
                        double PassSeenlevelinhex, Constantvalues.AltPos PassSeenPosition, boolean PassSolWorks, int PassTempSolID, VASL.LOS.Map.Map PassScenMap) {
        super(PassSeehex,PassSeelevelinhex,PassSeePosition,PassSeenhex,PassSeenlevelinhex,PassSeenPosition,PassSolWorks,PassScenMap);
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
