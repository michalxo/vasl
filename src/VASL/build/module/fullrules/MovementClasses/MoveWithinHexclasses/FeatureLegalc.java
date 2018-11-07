package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class FeatureLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private String returnresultsvalue;
    private Constantvalues.AltPos positionchangedvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public FeatureLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
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
        // don' t need legal test for enter Feature
        // just need to set final location
        locationchangevalue = hexclickedvalue.getCenterLocation();
        switch (MovementOptionvalue) {
            case EnterFoxhole:
                positionchangedvalue = Constantvalues.AltPos.InFoxhole;
            case EnterTrench:
                positionchangedvalue = Constantvalues.AltPos.InTrench;
            case EnterWire:
                positionchangedvalue = Constantvalues.AltPos.None;
            case FeatureExit: case ExitFoxhole: case ExitTrench:
                positionchangedvalue = Constantvalues.AltPos.None;
            default:
                positionchangedvalue = Constantvalues.AltPos.None;
        }
        return true;
    }

}
