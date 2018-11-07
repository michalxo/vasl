package VASL.build.module.fullrules.TerrainClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

public class IsSide {
    // This class handles various methods to return value of boolean test accesssing one or more hexsides for specified location(s)
    Location pLocation;

    public IsSide() {
        //pLocation = PassLocation;
    }

    public boolean IsARoad(int hexsidetouse, Hex enteringhex) {
        // called by movement.Enternewhex.MoveAllOK
        // checks if side crossed is some kind of road hexside

        Terrain hexsideTerrain = enteringhex.getHexsideTerrain(hexsidetouse);
        return hexsideTerrain.isRoad();
    }


    public boolean IsADepression(Constantvalues.Hexside hexsidetype) {
        //called by movement.DetermineMenuItems
        // checks if side is a depression, meaning crest status allowed
        switch (hexsidetype) {
            case GullyUp: case GullyDown: case GullyUpSlope: case GullyDownSlope: case GullyUpWire: case GullyDownWire: case AttWoodsGullyUp: case AttWoodsGullyDown:
            case AttPWdsGullyUp: case AttPWdsGullyDn: case GullyUpHedge: case GullyDownHedge: case GullyUpWall: case GullyDownWall: case GullyUpTrail: case GullyDownTrail:
            case AttPwdsCrestUpGully: case AttPWdsCrestDnGully: case CrestUpGullyDown: case CrestDownGullyUp: case AttPWdsCrestUpGullyDn: case AttPWdsCrestDnGullyUp:
            case GullyUpSlopeWire: case GullyDownSlopeWire: case GullyUpUnPvRd: case GullyDnUnPvRd: case GullyUpSlopeHedge: case GullyDnSlopeHedge: case GullyUpGullyDown:
            case GullyUpGullyDownHedge: case CrestUpGullyHedge: case CrestDnGullyHedge: case GullyDnCLUpHedge: case GullyUpCLDnHedge: case CLUpGullySlopehedge: case CLDnGullySlopeHedge:
            case AttWdsCrestUpGullyDn: case AttWdsCrestDnGullyUp: case CrestUpGullyWire: case CrestDnGullyWire: case AttWoodsCrestUpGully: case AttWoodsCrestDnGully:
            case CrestUpGully: case CrestDnGully:
                return true;
            default:
                return false;
        }
    }

    public boolean IsATrenchside(Constantvalues.Hexside hexsidetype){
        return (hexsidetype.equals(Constantvalues.Hexside.Trench) ||
                hexsidetype.equals(Constantvalues.Hexside.TrenchCrestDown) ||
                hexsidetype.equals(Constantvalues.Hexside.TrenchCrestUp) ||
                hexsidetype.equals(Constantvalues.Hexside.TrenchHedge) ||
                hexsidetype.equals(Constantvalues.Hexside.TrenchWall));
    }
    public boolean IsACliff(int hexsidetype) {
        // called by Terractions.AllADJACENTLocations

        /*Select case hexsidetype
        case Cliff,CliffDoubleDownHedge,
                CliffDoubleUPHedge, CliffDownHedge,
                CliffUpHedge
        Return True
        case Else
        Return False
        End Select*/
        return false;
    }

    public boolean IsAGullyStream(int hexsidetype) {
        //'called by movement.DetermineMenuItems
        //        'checks if side is a gully or stream side, meaning creststatus NA
        /*Select case hexsidetype
        case Gully,
        CrestUpStream, CrestDownStream,
                AttWdsCrestDnStream, AttWdsCrestUpStream,
                Stream, WoodsStream, River, PineWStream
        Return True
        case Else
        Return False
        End Select*/
        return false;
    }

    public boolean IsACrossableBuilding(Constantvalues.Hexside hexsidetype) {
        return(hexsidetype.equals(Constantvalues.Hexside.AttachedBldg));
    }
    public boolean IsABuilding(int hexsidetype) {
        //'called by MapActions.HexbyhexMoveAlongOK

        /*Select case hexsidetype
        case AttachedBldg,
        IntFactside, Rowhouseside, IntBldgWall
        Return True
        case Else
        Return False
        End Select*/
        return false;
    }
    /*    'Public Function IsAWallHedgeRdBlk(ByVal hexsidetype As Integer, ByVal Maptables As DataClassLibrary.ASLXNA.MapDataC) As Boolean
                '    'called by Contextpopup.MenuFilter
        '    Dim Mapdbo As MapDataClassesDataContext = Maptables.GetMapDatabase
                '    Dim SideisWHR As Boolean
                '    'query
        '    SideisWHR = CBool((From QU In Mapdbo.Hexsides Where QU.SideValue = hexsidetype Select QU.IsWallHdRdbk).First)
                '    Return SideisWHR

                '    'Select case hexsidetype
        '    '    case Hexside.Hedge, Hexside.Wall, Hexside.CrestDownHedge To Hexside.CrestUpWall,
            '    '        Hexside.CrestDownSlopeHedge, Hexside.CrestUpSlopeHedge, Hexside.SlopeDownHedge To Hexside.SlopeUpWall,
            '    '        Hexside.SunkenNonroadHedge, Hexside.CrestDownSlopeWall, Hexside.CrestUpSlopeWall,
            '    '        Hexside.HedgeUnpavedRoad To Hexside.CLDownHedge, Hexside.CLUpWallSlope To Hexside.CLDnWallSlopePO,
            '    '        Hexside.CLDnHedgeSlope, Hexside.CLUpHedgeSlope, Hexside.Roadblock To Hexside.HedgePO, Hexside.Bocage,
            '    '        Hexside.CLupdblhedge, Hexside.CLDowndblHedge, Hexside.CliffDownHedge, Hexside.CliffUpHedge,
            '    '        Hexside.CliffDoubleUPHedge, Hexside.CliffDoubleDownHedge, Hexside.Roadblock0 To Hexside.Roadblock6,
            '    '        Hexside.crestdndblslopehedge, Hexside.crestupdblslopehedge, Hexside.CLDownDblslopehedge, Hexside.CLUpDblSlopehedge
        '    '        Return True
        '    '    case Else
        '    '        Return False
        '    'End Select
        'End Function*/
    public boolean IsAWallHedgeRdBlk(Constantvalues.Hexside hexsidetype) {
        // called by
        // checks if side crossed is some kind of wall Hedge, rdblk

        return (hexsidetype == Constantvalues.Hexside.Hedge ||
                hexsidetype == Constantvalues.Hexside.HedgePO ||
                hexsidetype == Constantvalues.Hexside.HedgeUnpavedRoad ||
                hexsidetype == Constantvalues.Hexside.Roadblock ||
                hexsidetype == Constantvalues.Hexside.Roadblock0 ||
                hexsidetype == Constantvalues.Hexside.Roadblock1 ||
                hexsidetype == Constantvalues.Hexside.Roadblock2 ||
                hexsidetype == Constantvalues.Hexside.Roadblock3 ||
                hexsidetype == Constantvalues.Hexside.Roadblock4 ||
                hexsidetype == Constantvalues.Hexside.Roadblock6 ||
                hexsidetype == Constantvalues.Hexside.Wall ||
                hexsidetype == Constantvalues.Hexside.WallPO ||
                hexsidetype == Constantvalues.Hexside.WallUnpavedRoad ||
                hexsidetype == Constantvalues.Hexside.CLDnWallSlope ||
                hexsidetype == Constantvalues.Hexside.CLDnWallSlopePO ||
                hexsidetype == Constantvalues.Hexside.CLDownWall ||
                hexsidetype == Constantvalues.Hexside.CLUpWall ||
                hexsidetype == Constantvalues.Hexside.CrestDownSlopeWall ||
                hexsidetype == Constantvalues.Hexside.CrestDownWall ||
                hexsidetype == Constantvalues.Hexside.CrestUpSlopeWall ||
                hexsidetype == Constantvalues.Hexside.CrestUpWall ||
                hexsidetype == Constantvalues.Hexside.GullyDownWall ||
                hexsidetype == Constantvalues.Hexside.GullyUpWall ||
                hexsidetype == Constantvalues.Hexside.SlopeDownWall ||
                hexsidetype == Constantvalues.Hexside.SlopeUpWall ||
                hexsidetype == Constantvalues.Hexside.CLUpWallSlope ||
                hexsidetype == Constantvalues.Hexside.CLUpWallSlopePO);

    }
    public boolean IsARdBlk(Constantvalues.Hexside hexsidetype) {
        // called by
        // checks if side crossed is some kind of wall Hedge, rdblk

        return (hexsidetype == Constantvalues.Hexside.Roadblock ||
                hexsidetype == Constantvalues.Hexside.Roadblock0 ||
                hexsidetype == Constantvalues.Hexside.Roadblock1 ||
                hexsidetype == Constantvalues.Hexside.Roadblock2 ||
                hexsidetype == Constantvalues.Hexside.Roadblock3 ||
                hexsidetype == Constantvalues.Hexside.Roadblock4 ||
                hexsidetype == Constantvalues.Hexside.Roadblock6 );

    }
    public boolean IsWAAllowed(Hex newhex, int hexsidetouse, Hex Otherhex) {
        //called by ContextMenu.MenuFilter
        //determines if Terrain allows WA; using a specific hex and hexside

        // 'get info for both hexes
        Locationi Otherhexloc  = new Locationc (Otherhex.getCenterLocation(), null); 
        Locationi Starthexloc = new Locationc(newhex.getCenterLocation(), null);
        // Get hexside type
        Constantvalues.Hexside hexsidetype = Gethexsidetype(Starthexloc, hexsidetouse);
        switch (hexsidetype) {
            case CLUpHedgeSlope:
            case Hedge: case Wall: case SlopeDownHedge:
            case SlopeDownWall: case CrestDownSlopeWall: case SunkenNonroadHedge:
            case HedgeUnpavedRoad: case WallUnpavedRoad: case CLUpHedge:
            case CLUpWall: case CLUpWallSlope: case CLUpWallSlopePO:
            case WallPO: case HedgePO: case Bocage:
            case CrestDownHedge: case CrestDownWall: case CrestDownSlopeHedge:
            case SlopeUpHedge: case SlopeUpWall: case GullyUpHedge: case GullyDownHedge: case GullyUpWall: case GullyDownWall:
            case GullyUpSlopeHedge: case GullyDnSlopeHedge: case GullyUpGullyDownHedge:
            case CrestUpGullyHedge: case CrestDnGullyHedge: case GullyDnCLUpHedge: case GullyUpCLDnHedge:
            case CLUpGullySlopehedge: case CLDnGullySlopeHedge:
                return true;
            case CrestUpHedge:
            case CrestUpWall: case CrestUpSlopeHedge: case CrestUpSlopeWall:
                return false;
            case CLDnHedgeSlope: case CLDownHedge: case CLDownWall: case CLDnWallSlope: case CLDnWallSlopePO:
                return true;
            case Roadblock0: case Roadblock1: case Roadblock2: case Roadblock3: case Roadblock4: case Roadblock6: case Roadblock:
                // roadblock need special test -IS THIS TEST CORRECT?
                if (Otherhex != null) {
                    return !(Starthexloc.getvasllocation().getBaseHeight() > Otherhexloc.getvasllocation().getBaseHeight());
                }
            default:
                return false;
        }

    }
    public boolean IsWAAllowedToSwitch(Hex Starthex, int hexsidetouse, Hex Otherhex) {
        // called by SwitchWallAdvantage.Switch and .WABlocked
        // determines if Terrain allows WA; using a specific hex and hexside
        // same as ISWAAllowed except for treatment of depression hexes - switch allowed; no statusprevents check

        // Get hexside type
        Locationi loctouse = new Locationc(Starthex.getCenterLocation(), null);
        Constantvalues.Hexside hexsidetype = Gethexsidetype(loctouse, hexsidetouse);
        switch (hexsidetype) {
            case CLUpHedgeSlope: case Hedge: case Wall: case SlopeDownHedge: case SlopeDownWall: case CrestDownSlopeWall: case SunkenNonroadHedge:
            case HedgeUnpavedRoad: case WallUnpavedRoad: case CLUpHedge: case CLUpWall: case CLUpWallSlope: case CLUpWallSlopePO:
            case WallPO: case HedgePO: case Bocage: case CrestDownHedge: case CrestDownWall: case CrestDownSlopeHedge: case SlopeUpHedge: case SlopeUpWall:
                return true;
            case CrestUpHedge: case CrestUpWall: case CrestUpSlopeHedge: case CrestUpSlopeWall:
                return false;
            case CLDnHedgeSlope: case CLDownHedge: case CLDownWall: case CLDnWallSlope: case CLDnWallSlopePO:
                // for switch purposes, WA can move to otherside of hexside
                return true;
            case Roadblock0: case Roadblock1: case Roadblock2: case Roadblock3: case Roadblock4: case Roadblock6: case Roadblock:
                // roadblock need special test
                return (Starthex.getBaseHeight() > Otherhex.getBaseHeight() ? false : true);
            default:
                return false;
        }

    }
    public boolean IsWAMandatory(Hex newhex, Location LoctoTest, Constantvalues.AltPos PostoTest) {
        // called by WallAdv.MoveUpdate, ForfeitWA.SwitchWA, Newhex.Moveupdate(where must check for adjacent enemy with WA or in-hex friendly with WA)
        // Test if Mandatory WA (B9.323) applies and therefore unit must claim Wall Adv
        
        int HexTEM= 0;
        Locationi WALocation = new Locationc(LoctoTest, null);
        if (PostoTest != Constantvalues.AltPos.None && PostoTest != Constantvalues.AltPos.WallAdv) {
            // determine TEM of position being occupied
            HexTEM = WALocation.getvaslterrain().getTEM();  // CHECK THAT THIS WORKS
        } else if (WALocation.getvasllocation().equals(LoctoTest)) {
            HexTEM = WALocation.getvaslterrain().getTEM();
        } else if (WALocation.getvaslotherterrain().equals(LoctoTest.getTerrain())) {
            HexTEM = WALocation.getvaslotherterrain().getTEM();
        }
        // if no in-hex TEM then must claim
        if (HexTEM <= 0) { // 
            // check for wall/hedge/rdblk
            IsSide SideChk = new IsSide();
            for (int i=0; i < 6; i++) {
                Hex ADJhex = newhex.getMap().getAdjacentHex(newhex, i);
                if (SideChk.IsWAAllowed(newhex, i, ADJhex)) {
                    return true;
                }
            }    
        }
        return false;
    }

    public Constantvalues.Hexside Gethexsidetype(Locationi hexloc, int hexsidetouse) {
        // called by IsSide.IsWAAllowed
        // returns hexside value of specificied side of specified hex

        ConversionC confrom = new ConversionC();
        return confrom.ConverttoHexside(hexloc.getvaslhex().getHexsideTerrain(hexsidetouse));

    }
    public Terrain GethexsideTerrain(int hexside) {
        // called by IsSide.IsWAAllowed, movement.determinemenuforhexmove
        // returns hexside value of specificied side of specified hex
        return pLocation.getHex().getHexsideTerrain(hexside);
    }
    public int GethexsideTEM (Constantvalues.Hexside hexsidetype){
        switch (hexsidetype){
            case NoTerrain:
                return 0;
            case Wall:
            case WallPO:
            case WallUnpavedRoad:
                return 2;
            case Hedge:
            case HedgePO:
            case HedgeUnpavedRoad:
                return 1;
            default:
                return 0;
        }
    }
    public String GetSideData(Constantvalues.TerrFactor Sideitem, Constantvalues.Hexside SideID) {
        // called by
        // retrieve specific item from HexSide table in Map database
        // SideID is type of Side - which record to look at
        // Sideitem is which element to return

        //Dim Loctab As MapDataClassLibrary.MapDataClassesDataContext = Maptables.GetMapDatabase
        String SideInfo  = "";
        // query
        if (SideID == Constantvalues.Hexside.NoTerrain) {return SideInfo;}
        switch (Sideitem) {
            case HexsideTEM:
                SideInfo = ""; //(From QU In Loctab.Hexsides Where QU.SideValue = SideID Select QU.SideTEM).First.ToString temporary while debugging UNDO
                break;
            /*'case LocFactor.LOSHind
            '    TerrInfo = (From QU In Loctab.Locations Where QU.Locationtype = TerrID _
            '                      Select QU.LOSHindDRM).First.ToString
            case ConstantClassLibrary.ASLXNA.TerrFactor.Hexsidedesc
                    SideInfo = (From QU In Loctab.Hexsides Where QU.SideValue = SideID _
            Select QU.Sidedesc).First.ToString
            'case LocFactor.ScenFeature
            'If TerrID > 6900 Then   ' smoke item
            '    ' TerrInfo = CStr((From QU In db.Locations Where QU.Terraintype = TerrID _
            '    ' Select QU.Terraindesc).First)
            'Else                ' other scen feature
            '    TerrInfo = CStr((From QU In db.Locations Where QU.Terraintype = TerrID _
            '                      Select QU.Terraindesc).First)
            'End If
            'case LocFactor.MF
            '    TerrInfo = (From QU In db.Locations Where QU.Terraintype = TerrID _
            '                      Select QU.mfcot).First.ToString
            case ConstantClassLibrary.ASLXNA.TerrFactor.Image
                    SideInfo = (From QU In Loctab.Hexsides Where QU.SideValue = SideID _
            Select QU.Image).First.ToString
            'case LocFactor.ObstHeight
            '    TerrInfo = (From QU In db.Locations Where QU.Terraintype = TerrID _
            '                     Select QU.ObstHeight).First.ToString*/
            case HexsideMFcost:
                SideInfo = ""; //(From QU In Loctab.Hexsides Where QU.SideValue = SideID Select QU.mfcost).First.ToString  temporary while debugging UNDO
                break;
        }
        return SideInfo;
    }
}
