package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.LinkedList;

public class MovementWithinLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public MovementWithinLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption, Location passhexlocationclicked)  {
        hexclickedvalue = passnewhexclicked;
        MovementOptionvalue = passmovementoption;
        returnresultsvalue = "";
        locationchangevalue = passhexlocationclicked;
    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // called by MoveWithinHex.MoveAllOK
        // determines if moves up or down within a hex are logically possible and legal (ie moving up from roof level)

        ScenarioC scen = ScenarioC.getInstance();
        // Get base terrain for hex
        Location baselocation = hexclickedvalue.getCenterLocation();
        PersUniti MovingUnit = Scencolls.SelMoveUnits.getFirst();
        GetALocationFromMap Getlocs = new GetALocationFromMap();
        LinkedList<Locationi> HexLocs = Getlocs.RetrieveLocationsinHex(hexclickedvalue);
        // if at top and up or bottom and down then not legal
        if (Attop(HexLocs, MovingUnit) && MovementOptionvalue == Constantvalues.UMove.StairsUp) {
            return false;
        } else if (Atbottom(HexLocs, MovingUnit) && MovementOptionvalue == Constantvalues.UMove.StairsDown) {
            return false;
        }
        // if not in stair hex then not legal
        if (NoStairs(hexclickedvalue) && (MovementOptionvalue == Constantvalues.UMove.StairsUp || MovementOptionvalue == Constantvalues.UMove.StairsDown ||
                MovementOptionvalue == Constantvalues.UMove.StairsUpWA || MovementOptionvalue == Constantvalues.UMove.StairsDownWA)) {
            return false;
        }
        // this tests for location change so must do Assault move check
        if (MovingUnit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoved) {
            returnresultsvalue = "You have already changed locations so can't do so again while assault moving";
            return false;
        }
        // movement is now legal, need to determine final level
        double NewLevel = 0.0;

        if (MovementOptionvalue == Constantvalues.UMove.StairsUp || MovementOptionvalue == Constantvalues.UMove.StairsUpWA) {
            locationchangevalue = MovingUnit.getbaseunit().gethexlocation().getUpLocation();  // set new location value if move legal
        } else if (MovementOptionvalue == Constantvalues.UMove.StairsDown || MovementOptionvalue == Constantvalues.UMove.StairsDownWA) {
            locationchangevalue = MovingUnit.getbaseunit().gethexlocation().getDownLocation();  // set new location value if move legal
        }
        return true;
    }
    private boolean Attop(LinkedList<Locationi> HexLocations, PersUniti MovingUnit ) {
        return MovingUnit.getbaseunit().gethexlocation().getUpLocation().equals(null);
    }
    private boolean Atbottom(LinkedList<Locationi> hexlocations, PersUniti MovingUnit ) {
        return MovingUnit.getbaseunit().gethexlocation().getDownLocation().equals(null);
    }
    private boolean NoStairs(Hex Usinghex) {
        // If no stairs, can' t go up or down
        return Usinghex.hasStairway();
    }

}
