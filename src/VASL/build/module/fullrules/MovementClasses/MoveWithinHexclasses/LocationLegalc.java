package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;

public class LocationLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private Constantvalues.AltPos positionchangedvalue = Constantvalues.AltPos.None;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public LocationLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
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
        // don't need legal test for enter/exit location
        // just need to set final location

        switch (MovementOptionvalue) {
            case EnterPillbox:
                // Location change requires AM test
                PersUniti movingunit = Scencolls.SelMoveUnits.getFirst();
                if (movingunit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoved) {
                    returnresultsvalue = "You have already changed locations and cannot do so again while using assault movement";
                    return false;
                } else {
                    GetALocationFromMap TerrGet = new GetALocationFromMap();
                    Location HexLoc = TerrGet.GetPillboxLocation(hexclickedvalue);
                    locationchangevalue = HexLoc;
                }
            case ExitPIllbox:
                locationchangevalue = hexclickedvalue.getCenterLocation();

            default:

        }
        return true;
    }
}
