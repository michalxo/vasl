package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.DiceC;

public class SWLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public SWLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
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
        // Test if rolls below 6 when required, and Units exist who are eligible to do action
        if (MovementOptionvalue == Constantvalues.UMove.RecoverSW || MovementOptionvalue == Constantvalues.UMove.RecoverSWBrk) {
            if (Scencolls.SelMoveUnits.size() > 1) {
                returnresultsvalue = "Too many units selected, do recovery one unit at a time: Trying to recover SW";
                return false;
            }
            DiceC SWRoll = new DiceC();
            int SWDieroll = SWRoll.Dieroll();
            if (SWDieroll == 6) {
                returnresultsvalue = "You rolled a 6. No SW recovery possible!";
                return false;
            }
            // already know unpossessed SW (or broken unit with SW) exists or context popup option would not show; now checking for unit
            PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
            if (!Movingunit.getMovingunit().getIsConcealed()) {
                if (Movingunit.getbaseunit().getnumSW() < 2) {
                    returnresultsvalue = "Unit capable of recovering SW found: Recovering SW . . . ";
                    return true;
                }
            }
            // if here then no SW found
            return false;
        } else if (MovementOptionvalue == Constantvalues.UMove.DropSW) {
            for (PersUniti Movingunit : Scencolls.SelMoveUnits) {
                if (!Movingunit.getMovingunit().getIsConcealed()) {
                    if (Movingunit.getbaseunit().getnumSW() > 0) {
                        returnresultsvalue = "Possessed SW found: Dropping SW . . . ";
                        return true;
                    }
                }
            }
            // if here then no SW found
            return false;
        } else {
            return false;  // should never get here
        }
    }
}
