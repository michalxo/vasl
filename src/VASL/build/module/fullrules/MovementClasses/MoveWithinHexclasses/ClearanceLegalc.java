package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.Clearancec;

public class ClearanceLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private String returnresultsvalue;
    private String ClearanceStatusvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    // constructor
    public ClearanceLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
        hexclickedvalue = passnewhexclicked;
        MovementOptionvalue = passmovementoption;
        returnresultsvalue = "";
        //locationchangevalue = passhexlocationclicked;
    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // Test if can clear terrain
        //  first test if unit available to attempt
        boolean NoUnit = true;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        for (PersUniti ClearingUnit: Scencolls.SelMoveUnits) {
            if (!ClearingUnit.getMovingunit().IsDummy()) {
                NoUnit = false;
                break;
            }
        }
        if (NoUnit) {return false;}  // can' t attempt as no unit present
        Clearancec DoClear = new Clearancec();
        switch (MovementOptionvalue) {
            // These two are actually resovled in MPh; others deferred to CC
            case ClearFlame:
                int FinalDR = DoClear.ClearanceRollIs2(MovementOptionvalue);
                if (FinalDR == 2) {
                    ClearanceStatusvalue = "Extinguished";
                } else if (FinalDR > 2 && FinalDR < 7) {  // hampered
                    ClearanceStatusvalue = "Hampered";
                }
            case ClearRoadATMine:
                ClearanceStatusvalue = "Cleared";
            default:
                ClearanceStatusvalue = "Resolved in CC";
        }
        // everything returns true as nothing prevents
        return true;
    }

}
