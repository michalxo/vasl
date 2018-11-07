package VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.IsTerrain;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

public class UpperLevelLegalc implements LegalMovei {

    // determines if can legally move to new hex from a level above ground level
    private Hex newhexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private Hex currenthexvalue;
    private String returnresultsvalue;

    // constructor
    public UpperLevelLegalc(Hex passhexclicked, Constantvalues.UMove passmovementoption)  {
        newhexclickedvalue = passhexclicked;
        MovementOptionvalue = passmovementoption;
        //usemoving = false;
        //locationchangevalue = passhexlocationclicked;
        //initialnationality = InitialNat;
        //positionvalue = PassPosition;
        returnresultsvalue = "";
    }

    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean  IsMovementLegal() {
        // called by MoveNewHexi.MoveAllOK
        // determines if legal to move from one hex to another at upper level
        // buildings must be adjoining, not blocked by interior wall, both have required level (may not be same in each hex - split level buildings)
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        ScenarioC scen = ScenarioC.getInstance();

        // get current hex
        PersUniti LegalCheckUnit = Scencolls.SelMoveUnits.getFirst();
        currenthexvalue = LegalCheckUnit.getbaseunit().getHex();
        int hexsidecrossed  = scen.DoMove.ConcreteMove.getHexSideEntry(currenthexvalue, newhexclickedvalue);
        ConversionC confrom = new ConversionC();
        Constantvalues.Hexside  Hexsidetype = confrom.ConverttoHexside(LegalCheckUnit.getbaseunit().getHex().getHexsideTerrain(hexsidecrossed));
        IsSide SideTest = new IsSide();
        if (!SideTest.IsACrossableBuilding(Hexsidetype)) {return false;} // can't cross hexside
        IsTerrain IsTerrChk = new IsTerrain();
        LevelChecks LevelChk = new LevelChecks();
        //matching level in hex
        if (IsTerrChk.IsLocationTerrainA(currenthexvalue, LegalCheckUnit.getbaseunit().gethexlocation(), Constantvalues.Location.SplitLeveltype)) {

            /*'still to program
            'Dim TotalStartLevel As Single = Game.Scenario.TerrainActions.GetLocationPositionLevel(TempCheckunit.CurrentLocationInHex, 0) + CInt(StartBasehex!baselevel)
            'Dim TotalOtherlevel As Single = Game.Scenario.TerrainActions.GetLocationPositionLevel(TestLocToTest.HexLocation, 0) + CInt(GetBasehex!baselevel)
            'If TotalStartLevel = TotalOtherlevel Then Return True*/
            return true;
        } else {
            // connecting trench test
            boolean Movelegal = false;
            GetALocationFromMap Getlocs = new GetALocationFromMap();
            Locationi Matchhexloc = Getlocs.RetrieveLocationfromHex(newhexclickedvalue, confrom.ConverttoLocationtypefromVASLLocation(LegalCheckUnit.getbaseunit().gethexlocation()));
            // if Matchhexloc is not null then same upper level exists in entry hex
            if (Matchhexloc == null) {
                Movelegal = false;
            } else {
                locationchangevalue = Matchhexloc.getvasllocation();
                 Movelegal = true;
            }
            return Movelegal;
        }

    }

}
