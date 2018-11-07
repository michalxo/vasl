package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.IsSide;

public class ForfeitWallAdvLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public ForfeitWallAdvLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
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
        // Test if Mandatory WA (B9.323) applies and therefore unit cannot forfeit Wall Adv

        IsSide SideChk = new IsSide();
        for (PersUniti Movingunit : Scencolls.SelMoveUnits) {
            // if in mandatory wa terrain, can' t forfeit unless in crest status (which gives TEM)
            if (SideChk.IsWAMandatory(hexclickedvalue, Movingunit.getbaseunit().gethexlocation(), Movingunit.getbaseunit().gethexPosition())) {
                if (!Movingunit.getbaseunit().HasWallAdvantage()) {return false;}
            }
        }
        // if here then all selected units can forfeit so return true
        return true;
    }

}
