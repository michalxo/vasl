package VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.*;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.LinkedList;

public class MovementNewLegalc implements LegalMovei {

    // determines if can legally move to new hex (or must exit obstacle, creststatus first, etc)
    private Hex newhexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private Hex currenthexvalue;
    private String returnresultsvalue;

    // constructor
    public MovementNewLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
        newhexclickedvalue = passnewhexclicked;
        MovementOptionvalue = passmovementoption;
        returnresultsvalue = "";
    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // called by MoveNewHexi.MoveAllOK
        // determines if legal to move from one hex to another based on location in current hex
        // can't be in crest status or in feature such as foxhole
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        ScenarioC scen = ScenarioC.getInstance();
        // get current hex
        PersUniti LegalCheckUnit = Scencolls.SelMoveUnits.getFirst();
        currenthexvalue = LegalCheckUnit.getbaseunit().getHex();
        locationchangevalue = getnewlocationtoenter(newhexclickedvalue, MovementOptionvalue);
        Locationi TempMoveloc = new Locationc(locationchangevalue, MovementOptionvalue);
        IsTerrain IsTerrChk = new IsTerrain();
        LevelChecks LevelChk = new LevelChecks();
        Locationi DepartLocation = null;
        // set value of departing location
        if (MovementOptionvalue == Constantvalues.UMove.EnterConnectingPill) {
            GetALocationFromMap GetLocs = new GetALocationFromMap();
            LinkedList<Locationi> HexLocs = GetLocs.RetrieveLocationsinHex(newhexclickedvalue);
            for (Locationi newlocationi : HexLocs) {
                if (newlocationi.IsPillbox()) {
                    DepartLocation = newlocationi;
                    break;
                }
            }
        } else if (MovementOptionvalue == Constantvalues.UMove.EnterConnectingCellar)  {
            DepartLocation = new Locationc(LevelChk.GetLocationatLevelInHex(newhexclickedvalue, -1), MovementOptionvalue);
        } else {
            DepartLocation = new Locationc(LevelChk.GetLocationatLevelInHex(newhexclickedvalue, 0), MovementOptionvalue);
        }
        Terrain baseterrainclicked  = DepartLocation.getvaslterrain();
        // handle particular cases
        IsSide SideTest = new IsSide();
        // PUSH THIS OUT INTO SRP CLASSES VIA INTERFACE AS PER MOVEMENT VALIDATION
        if(isUnitCurrentlyinCrestStatus(LegalCheckUnit.getbaseunit().gethexPosition())) {
            // in crest status so can' t move to new hex
            returnresultsvalue = "Unit is in Crest Status. Must exit crest status before leaving hex";
            return false;
        } else if(hasUnitExitedCrest(LegalCheckUnit.getbaseunit().gethexPosition())) {
            // move must be across crest status hexside else have to moveIN and the up and out
            int hexsidecrossed  = scen.DoMove.ConcreteMove.getHexSideEntry(currenthexvalue, newhexclickedvalue);
            ConversionC confrom = new ConversionC();
            Constantvalues.Hexside  Hexsidetype = confrom.ConverttoHexside(LegalCheckUnit.getbaseunit().getHex().getHexsideTerrain(hexsidecrossed));
            if (hexsidecrossed == confrom.ConvertAltPosSidetohexsideInt(LegalCheckUnit.getbaseunit().gethexPosition())) {
                // middle position of Crest so must be valid hexside
                locationchangevalue = TempMoveloc.getvasllocation(); // set new location value if move legal
            } else if ((hexsidecrossed == confrom.ConvertAltPosSidetohexsideInt(LegalCheckUnit.getbaseunit().gethexPosition())+1 ||
                    hexsidecrossed == confrom.ConvertAltPosSidetohexsideInt(LegalCheckUnit.getbaseunit().gethexPosition())-1) &&
                    SideTest.IsADepression(Hexsidetype)){
                // using one of outer hexsides of crest, could be a depression
                locationchangevalue = TempMoveloc.getvasllocation(); // set new location value if move legal
            } else {
                    returnresultsvalue = "Not a legal move: Must Move IN hex first and then up and out, not using Crest Status hexside";
                    return false;
            }
        } else if (LegalCheckUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.InFoxhole ||  LegalCheckUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.InSanger) {
            // in feature so must exit before move to new hex
            String Featurestring = "";
            if (LegalCheckUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.InFoxhole) {
                Featurestring = "Foxhole";
            } else {
                Featurestring = "Sanger";
            }
            returnresultsvalue = "Unit is in " + Featurestring + ". Must exit before leaving hex";
            return false;
        } else if (LegalCheckUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.InTrench) {
            int hexsidecrossed  = scen.DoMove.ConcreteMove.getHexSideEntry(currenthexvalue, newhexclickedvalue);
            Constantvalues.Hexside Hexsidetype = SideTest.Gethexsidetype(DepartLocation, hexsidecrossed);
            if (MovementOptionvalue == Constantvalues.UMove.EnterConnectingTrench && SideTest.IsATrenchside(Hexsidetype)) {
                MovementOptionvalue = Constantvalues.UMove.EnterTrench;
                locationchangevalue = DepartLocation.getvasllocation(); // set new location value if move legal
                return true;
            } else if (MovementOptionvalue == Constantvalues.UMove.EnterViaConnection) {
                locationchangevalue = DepartLocation.getvasllocation();  // set new location value if move legal
                return true;
            } else if (DepartLocation.getvaslterrain().isBuilding() && (MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldg ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingCellar)) {
                GetALocationFromMap GetLocs = new GetALocationFromMap();
                LinkedList<Locationi> HexLocs = GetLocs.RetrieveLocationsinHex(currenthexvalue);
                for (Locationi hexloc: HexLocs) {
                    Constantvalues.Hexside Newhexsidetype = SideTest.Gethexsidetype(hexloc, hexsidecrossed) ;
                    if (SideTest.IsATrenchside(Newhexsidetype)) {
                        locationchangevalue = hexloc.getvasllocation(); // set new location value if move legal
                        return true;
                    }
                }
            } else if (DepartLocation.IsPillbox() && Hexsidetype == Constantvalues.Hexside.Trench) {
                locationchangevalue = DepartLocation.getvasllocation(); // set new location value if move legal
                return true;
            } else {
                TerrainChecks TerrChk = new TerrainChecks();
                String Featurestring = TerrChk.GetPositionDesc(LegalCheckUnit.getbaseunit().gethexPosition());
                returnresultsvalue = "Unit is in " + Featurestring + ". Must exit before leaving hex";
                return false;
            }
        }else if(DepartLocation.IsPillbox()) {
            int hexsidecrossed  = scen.DoMove.ConcreteMove.getHexSideEntry(currenthexvalue, newhexclickedvalue);
            Constantvalues.Hexside Hexsidetype = SideTest.Gethexsidetype(DepartLocation, hexsidecrossed);
            if (SideTest.IsATrenchside(Hexsidetype) && (MovementOptionvalue == Constantvalues.UMove.EnterConnectingTrench ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldg ||
                    MovementOptionvalue == Constantvalues.UMove.EnterViaConnection ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingCellar ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingPill ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel1 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel2 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel3 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel4 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevelm1 ) ) {

                if (MovementOptionvalue == Constantvalues.UMove.EnterConnectingTrench) {
                    MovementOptionvalue = Constantvalues.UMove.EnterTrench;
                    locationchangevalue = DepartLocation.getvasllocation(); // set new location value if move legal
                    return true;
                } else if (DepartLocation.getvaslterrain().isBuilding() && (MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldg ||
                        MovementOptionvalue == Constantvalues.UMove.EnterConnectingCellar)) {
                    GetALocationFromMap GetLocs = new GetALocationFromMap();
                    LinkedList<Locationi> HexLocs = GetLocs.RetrieveLocationsinHex(currenthexvalue);
                    for (Locationi hexloc: HexLocs) {
                        Constantvalues.Hexside Newhexsidetype = SideTest.Gethexsidetype(hexloc, hexsidecrossed) ;
                        if (SideTest.IsATrenchside(Newhexsidetype)) {
                            locationchangevalue = hexloc.getvasllocation(); // set new location value if move legal
                            return true;
                        }
                    }
                } else if (LegalCheckUnit.getbaseunit().gethexPosition() != Constantvalues.AltPos.None) {
                    locationchangevalue = DepartLocation.getvasllocation(); // set new location value if move legal
                    return true;
                } else {
                    TerrainChecks TerrChk = new TerrainChecks();
                    String Featurestring = TerrChk.GetPositionDesc(LegalCheckUnit.getbaseunit().gethexPosition());
                    returnresultsvalue = "Unit is in " + Featurestring + ". Must exit before leaving hex";
                    return false;
                }
            }
        }else if (IsUnitCurrentlyinGroundLevelBldg(LegalCheckUnit.getbaseunit().gethexlocation())) {
            int hexsidecrossed  = scen.DoMove.ConcreteMove.getHexSideEntry(currenthexvalue, newhexclickedvalue);
            Constantvalues.Hexside Hexsidetype = SideTest.Gethexsidetype(DepartLocation, hexsidecrossed);
            if ((SideTest.IsATrenchside(Hexsidetype)) &&
                    (MovementOptionvalue == Constantvalues.UMove.EnterConnectingTrench ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldg ||
                    MovementOptionvalue == Constantvalues.UMove.EnterViaConnection ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingCellar ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingPill ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel1 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel2 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel3 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel4 ||
                    MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevelm1)) {
                if (DepartLocation.getLocationtype().equals(Constantvalues.Feature.Trench) && MovementOptionvalue == Constantvalues.UMove.EnterConnectingTrench) {
                    MovementOptionvalue = Constantvalues.UMove.EnterTrench;
                    locationchangevalue = DepartLocation.getvasllocation(); // set new location value if move legal
                    return true;
                } else if (DepartLocation.getvaslterrain().isBuilding() && (MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldg ||
                        MovementOptionvalue == Constantvalues.UMove.EnterConnectingCellar)) {
                    GetALocationFromMap GetLocs = new GetALocationFromMap();
                    LinkedList<Locationi> HexLocs = GetLocs.RetrieveLocationsinHex(currenthexvalue);
                    for (Locationi hexloc: HexLocs) {
                        Constantvalues.Hexside Newhexsidetype = SideTest.Gethexsidetype(hexloc, hexsidecrossed) ;
                        if (SideTest.IsATrenchside(Newhexsidetype)) {
                            locationchangevalue = hexloc.getvasllocation(); // set new location value if move legal
                            return true;
                        }
                    }
                } else if (DepartLocation.IsPillbox()) {
                    locationchangevalue = DepartLocation.getvasllocation(); // set new location value if move legal
                    return true;
                }
            }
        } else if (LegalCheckUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.AboveWire) {
            returnresultsvalue = "Unit is above wire. Must move below (enter) wire before leaving hex";
            return false;
        }else if (LegalCheckUnit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoved) {
            returnresultsvalue = "Unit has already Assault Moved and cannot enter a new hex";
            return false;
        } else if (LegalCheckUnit.getMovingunit().getDash() == Constantvalues.MovementStatus.Dashed) {
            returnresultsvalue = "Unit has already Dashed and can move no further";
            return false;
        }else if (LegalCheckUnit.getMovingunit().getDash() == Constantvalues.MovementStatus.FirstDash && 
                !(IsTerrChk.IsLocationTerrainA(newhexclickedvalue, newhexclickedvalue.getCenterLocation(), Constantvalues.Location.Roadtype))) {
            returnresultsvalue = "Unit not trying to enter road hex so can't qualify for dash";
            LegalCheckUnit.getMovingunit().setDash(Constantvalues.MovementStatus.NotMoving);
            return true;
        } else {
            locationchangevalue = DepartLocation.getvasllocation();
            // set new location value if move legal  IS THIS RIGHT - OK FOR TRENCH MOVE BUT WHAT ABOUT NORMAL MOVES?
        }
        return true;
    }
    private Location getnewlocationtoenter(Hex newhexclickedvalue, Constantvalues.UMove MovementOptionvalue){
        // this is new hex entry so will normally be base location, unless special case
        Location newlocation = newhexclickedvalue.getCenterLocation();
        //special case building levels
        if (MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel1) {
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
        } else if (MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel2) {
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newlocation.getUpLocation();
        } else if(MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel3) {
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newlocation.getUpLocation();
            newlocation = newlocation.getUpLocation();
        } else if(MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevel4) {
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newlocation.getUpLocation();
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newhexclickedvalue.getCenterLocation().getUpLocation();
            newlocation = newlocation.getUpLocation();
        } else if(MovementOptionvalue == Constantvalues.UMove.EnterConnectingBldgLevelm1 ){
            newlocation = newhexclickedvalue.getCenterLocation().getDownLocation();
        }
        // other special cases - to add
        
        return newlocation;
    }
    // MOVE THESE TO COMMON FUNCTIONS
    private boolean isUnitCurrentlyinCrestStatus(Constantvalues.AltPos passposition){
        return (passposition == Constantvalues.AltPos.CrestStatus0 ||
        passposition == Constantvalues.AltPos.CrestStatus1 ||
        passposition == Constantvalues.AltPos.CrestStatus2 ||
        passposition == Constantvalues.AltPos.CrestStatus3 ||
        passposition == Constantvalues.AltPos.CrestStatus4 ||
        passposition == Constantvalues.AltPos.CrestStatus5 ||
        passposition == Constantvalues.AltPos.WACrestStatus0 ||
        passposition == Constantvalues.AltPos.WACrestStatus1 ||
        passposition == Constantvalues.AltPos.WACrestStatus2 ||
        passposition == Constantvalues.AltPos.WACrestStatus3 ||
        passposition == Constantvalues.AltPos.WACrestStatus4 ||
        passposition == Constantvalues.AltPos.WACrestStatus5);
    }

    private boolean IsUnitCurrentlyinGroundLevelBldg(Location checklocation) {
        ConversionC confrom = new ConversionC();
        Constantvalues.Location checkloc = confrom.ConverttoLocationtypefromVASLLocation(checklocation);
        return (checkloc == Constantvalues.Location.StoneBuildingGroundlevel ||
                checkloc == Constantvalues.Location.WoodBuildingGroundlevel);
    }
    private boolean hasUnitExitedCrest(Constantvalues.AltPos passposition){
        return (passposition==Constantvalues.AltPos.ExitedCrest0 ||
        passposition==Constantvalues.AltPos.ExitedCrest1 ||
        passposition==Constantvalues.AltPos.ExitedCrest2 ||
        passposition==Constantvalues.AltPos.ExitedCrest3 ||
        passposition==Constantvalues.AltPos.ExitedCrest4 ||
        passposition==Constantvalues.AltPos.ExitedCrest5);
    }
}
