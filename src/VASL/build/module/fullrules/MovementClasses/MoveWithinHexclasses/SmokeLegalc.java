package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.DiceC;

public class SmokeLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private Constantvalues.AltPos positionchangedvalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public SmokeLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
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
        // Test if rolls below smoke exponent
        for (PersUniti MovingUnit : Scencolls.SelMoveUnits) {
            int SmokeExponent = MovingUnit.getMovingunit().getSmokeE();
            if (SmokeExponent == 0) {  // unit can' t throw smoke
                returnresultsvalue = MovingUnit.getbaseunit().getUnitName() + " cannot try to throw smoke";
            } else {
                DiceC SmokeRoll = new DiceC();
                int SmokeDieroll = SmokeRoll.Dieroll();
                returnresultsvalue = MovingUnit.getbaseunit().getUnitName() + " rolls a " + Integer.toString(SmokeDieroll);
                switch (SmokeDieroll) {
                    case 6:
                        returnresultsvalue += ". No smoke placed. Move ends!";
                        positionchangedvalue = Constantvalues.AltPos.FailsandEnds;
                        return false;
                    default:
                        if(SmokeDieroll > SmokeExponent) {
                            returnresultsvalue += ". No smoke placed.";
                            positionchangedvalue = Constantvalues.AltPos.Fails;
                            return false;
                        } else {
                            positionchangedvalue = Constantvalues.AltPos.Smokeplaced;
                            returnresultsvalue += ". Smoke placed!";
                            return true;
                        }
                }
            }
        }
        return false;
    }
}
