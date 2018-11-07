package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;

public class OtherTerrainLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private Constantvalues.AltPos positionchangevalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public OtherTerrainLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
        hexclickedvalue = passnewhexclicked;
        MovementOptionvalue = passmovementoption;
        returnresultsvalue = "";
        // locationchangevalue = passhexlocationclicked;
    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // don't need legal test for change from Location to OtherTerraininLocation or reverse within same Location
        // just need to set final location

        switch (MovementOptionvalue) {
            case EnterTerrain:
                locationchangevalue = hexclickedvalue.getCenterLocation();
                positionchangevalue = Constantvalues.AltPos.None;
            case ExitTerrain:
                locationchangevalue = hexclickedvalue.getCenterLocation();
                positionchangevalue = Constantvalues.AltPos.OtherTerrainInHex;
            default:

        }
        return true;
    }
}
