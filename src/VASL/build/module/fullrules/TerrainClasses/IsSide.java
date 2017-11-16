package VASL.build.module.fullrules.TerrainClasses;

import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;

public class IsSide {
    // This class handles various methods to return value of boolean test accesssing one or more hexsides for specified location(s)
    Location pLocation;

    public IsSide(Location PassLocation) {
        pLocation = PassLocation;
    }

    public boolean IsARoad(int hexsidetouse, int LOCIndextouse) {
        // called by movement.Enternewhex.MoveAllOK
        // checks if side crossed is some kind of road hexside

        /*'Create reference to required hexside attribute field
        Dim Hexside As String = "Side" & hexsidetouse.ToString & "IsRoad"
        'query using Dynamic Linq
        Dim SideisRoad = MapData.Where("locindex=" & LOCIndextouse.ToString).Select(Hexside)
        'SideisRoad is an Iqyuerable so need to select first instance
        For Each RoadCheck As Boolean In SideisRoad
        If RoadCheck Then Return True Else Return False
        Next
        End Function
        Public Function IsADepression(ByVal hexsidetype As Integer) As Boolean
        'called by movement.DetermineMenuItems
        'checks if side is a depression, meaning crest status allowed
        Select case hexsidetype
        case ConstantClassLibrary.ASLXNA.Hexside.GullyUp,
        ConstantClassLibrary.ASLXNA.Hexside.GullyDown, ConstantClassLibrary.ASLXNA.Hexside.GullyUpSlope, ConstantClassLibrary.ASLXNA.Hexside.GullyDownSlope,
                ConstantClassLibrary.ASLXNA.Hexside.GullyUpWire, ConstantClassLibrary.ASLXNA.Hexside.GullyDownWire, ConstantClassLibrary.ASLXNA.Hexside.AttWoodsGullyUp, ConstantClassLibrary.ASLXNA.Hexside.AttWoodsGullyDown,
                ConstantClassLibrary.ASLXNA.Hexside.AttPWdsGullyUp, ConstantClassLibrary.ASLXNA.Hexside.AttPWdsGullyDn, ConstantClassLibrary.ASLXNA.Hexside.GullyUpHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.GullyUpWall, ConstantClassLibrary.ASLXNA.Hexside.GullyDownWall, ConstantClassLibrary.ASLXNA.Hexside.GullyUpTrail, ConstantClassLibrary.ASLXNA.Hexside.GullyDownTrail,
                ConstantClassLibrary.ASLXNA.Hexside.AttPwdsCrestUpGully, ConstantClassLibrary.ASLXNA.Hexside.AttPWdsCrestDnGully, ConstantClassLibrary.ASLXNA.Hexside.CrestUpGullyDown, ConstantClassLibrary.ASLXNA.Hexside.CrestDownGullyUp,
                ConstantClassLibrary.ASLXNA.Hexside.AttPWdsCrestUpGullyDn, ConstantClassLibrary.ASLXNA.Hexside.AttPWdsCrestDnGullyUp, ConstantClassLibrary.ASLXNA.Hexside.GullyUpSlopeWire, ConstantClassLibrary.ASLXNA.Hexside.GullyDownSlopeWire,
                ConstantClassLibrary.ASLXNA.Hexside.GullyUpUnPvRd, ConstantClassLibrary.ASLXNA.Hexside.GullyDnUnPvRd, ConstantClassLibrary.ASLXNA.Hexside.GullyUpSlopeHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyDnSlopeHedge,
                ConstantClassLibrary.ASLXNA.Hexside.GullyUpGullyDown, ConstantClassLibrary.ASLXNA.Hexside.GullyUpGullyDownHedge, ConstantClassLibrary.ASLXNA.Hexside.CrestUpGullyHedge, ConstantClassLibrary.ASLXNA.Hexside.CrestDnGullyHedge,
                ConstantClassLibrary.ASLXNA.Hexside.GullyDnCLUpHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyUpCLDnHedge, ConstantClassLibrary.ASLXNA.Hexside.CLUpGullySlopehedge, ConstantClassLibrary.ASLXNA.Hexside.CLDnGullySlopeHedge,
                ConstantClassLibrary.ASLXNA.Hexside.AttWdsCrestUpGullyDn, ConstantClassLibrary.ASLXNA.Hexside.AttWdsCrestDnGullyUp, ConstantClassLibrary.ASLXNA.Hexside.CrestUpGullyWire, ConstantClassLibrary.ASLXNA.Hexside.CrestDnGullyWire,
                ConstantClassLibrary.ASLXNA.Hexside.AttWoodsCrestUpGully, ConstantClassLibrary.ASLXNA.Hexside.AttWoodsCrestDnGully, ConstantClassLibrary.ASLXNA.Hexside.CrestUpGully, ConstantClassLibrary.ASLXNA.Hexside.CrestDnGully
        IsADepression = True
        case Else
        IsADepression = False
        End Select*/
        return false;
    }

    public boolean IsACliff(int hexsidetype) {
        // called by Terractions.AllADJACENTLocations

        /*Select case hexsidetype
        case ConstantClassLibrary.ASLXNA.Hexside.Cliff,ConstantClassLibrary.ASLXNA.Hexside.CliffDoubleDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CliffDoubleUPHedge, ConstantClassLibrary.ASLXNA.Hexside.CliffDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CliffUpHedge
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
        case ConstantClassLibrary.ASLXNA.Hexside.Gully,
        ConstantClassLibrary.ASLXNA.Hexside.CrestUpStream, ConstantClassLibrary.ASLXNA.Hexside.CrestDownStream,
                ConstantClassLibrary.ASLXNA.Hexside.AttWdsCrestDnStream, ConstantClassLibrary.ASLXNA.Hexside.AttWdsCrestUpStream,
                ConstantClassLibrary.ASLXNA.Hexside.Stream, ConstantClassLibrary.ASLXNA.Hexside.WoodsStream, ConstantClassLibrary.ASLXNA.Hexside.River, ConstantClassLibrary.ASLXNA.Hexside.PineWStream
        Return True
        case Else
        Return False
        End Select*/
        return false;
    }

    public boolean IsACrossableBuilding(int hexsidetype) {

            //'called by UpperLevelLegalC.IsMovementLegal, EnterNewHex.enteringlocation

    /*Select case hexsidetype
    case ConstantClassLibrary.ASLXNA.Hexside.AttachedBldg,ConstantClassLibrary.ASLXNA.Hexside.IntFactside
    Return True
    case Else
    Return False
    End Select*/
    return false;
    }
    public boolean IsABuilding(int hexsidetype) {
        //'called by MapActions.HexbyhexMoveAlongOK

        /*Select case hexsidetype
        case ConstantClassLibrary.ASLXNA.Hexside.AttachedBldg,
        ConstantClassLibrary.ASLXNA.Hexside.IntFactside, ConstantClassLibrary.ASLXNA.Hexside.Rowhouseside, ConstantClassLibrary.ASLXNA.Hexside.IntBldgWall
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
            '    '        Hexside.CliffDoubleUPHedge, Hexside.CliffDoubleDownHedge, Hexside.Roadblock1 To Hexside.Roadblock6,
            '    '        Hexside.crestdndblslopehedge, Hexside.crestupdblslopehedge, Hexside.CLDownDblslopehedge, Hexside.CLUpDblSlopehedge
        '    '        Return True
        '    '    case Else
        '    '        Return False
        '    'End Select
        'End Function*/
    public boolean IsAWallHedgeRdBlk(Constantvalues.Hexside hexsidetype) {
        // called by
        // checks if side crossed is some kind of wall Hedge, rdblk

        if (hexsidetype == Constantvalues.Hexside.Hedge ||
                hexsidetype == Constantvalues.Hexside.HedgePO ||
                hexsidetype == Constantvalues.Hexside.HedgeUnpavedRoad ||
                hexsidetype == Constantvalues.Hexside.Roadblock ||
                hexsidetype == Constantvalues.Hexside.Roadblock1 ||
                hexsidetype == Constantvalues.Hexside.Roadblock2 ||
                hexsidetype == Constantvalues.Hexside.Roadblock3 ||
                hexsidetype == Constantvalues.Hexside.Roadblock4 ||
                hexsidetype == Constantvalues.Hexside.Roadblock5 ||
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
                hexsidetype == Constantvalues.Hexside.CLUpWallSlopePO
            ) {
            return true;
        } else {
            return false;
        }

    }
    public boolean IsWAAllowed(int hexnum, int hexsidetouse, int Otherhexnum) {
        //'called by ContextMenu.MenuFilter
        //'determines if Terrain allows WA; using a specific hex and hexside

        /*'get info for both hexes
        Dim Otherhex As MapDataClassLibrary.GameLocation
        Dim LevelChk As New LevelChecks(MapData)
        Dim Starthex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        If Otherhexnum >0 Then Otherhex = LevelChk.GetLocationatLevelInHex(Otherhexnum, 0)
        'Get hexside type
        Dim hexsidetype As Integer = Gethexsidetype(Starthex, hexsidetouse)
        Select case hexsidetype
        case ConstantClassLibrary.ASLXNA.Hexside.CLUpHedgeSlope,
        ConstantClassLibrary.ASLXNA.Hexside.Hedge, ConstantClassLibrary.ASLXNA.Hexside.Wall, ConstantClassLibrary.ASLXNA.Hexside.SlopeDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.SlopeDownWall, ConstantClassLibrary.ASLXNA.Hexside.CrestDownSlopeWall, ConstantClassLibrary.ASLXNA.Hexside.SunkenNonroadHedge,
                ConstantClassLibrary.ASLXNA.Hexside.HedgeUnpavedRoad, ConstantClassLibrary.ASLXNA.Hexside.WallUnpavedRoad, ConstantClassLibrary.ASLXNA.Hexside.CLUpHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CLUpWall, ConstantClassLibrary.ASLXNA.Hexside.CLUpWallSlope, ConstantClassLibrary.ASLXNA.Hexside.CLUpWallSlopePO,
                ConstantClassLibrary.ASLXNA.Hexside.WallPO, ConstantClassLibrary.ASLXNA.Hexside.HedgePO, ConstantClassLibrary.ASLXNA.Hexside.Bocage,
                ConstantClassLibrary.ASLXNA.Hexside.CrestDownHedge, ConstantClassLibrary.ASLXNA.Hexside.CrestDownWall, ConstantClassLibrary.ASLXNA.Hexside.CrestDownSlopeHedge,
                ConstantClassLibrary.ASLXNA.Hexside.SlopeUpHedge, ConstantClassLibrary.ASLXNA.Hexside.SlopeUpWall, ConstantClassLibrary.ASLXNA.Hexside.GullyUpHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyDownHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyUpWall, ConstantClassLibrary.ASLXNA.Hexside.GullyDownWall,
                ConstantClassLibrary.ASLXNA.Hexside.GullyUpSlopeHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyDnSlopeHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyUpGullyDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CrestUpGullyHedge, ConstantClassLibrary.ASLXNA.Hexside.CrestDnGullyHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyDnCLUpHedge, ConstantClassLibrary.ASLXNA.Hexside.GullyUpCLDnHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CLUpGullySlopehedge, ConstantClassLibrary.ASLXNA.Hexside.CLDnGullySlopeHedge
        Return True
        case ConstantClassLibrary.ASLXNA.Hexside.CrestUpHedge,
        ConstantClassLibrary.ASLXNA.Hexside.CrestUpWall, ConstantClassLibrary.ASLXNA.Hexside.CrestUpSlopeHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CrestUpSlopeWall
        Return False
        case ConstantClassLibrary.ASLXNA.Hexside.CLDnHedgeSlope,ConstantClassLibrary.ASLXNA.Hexside.CLDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CLDownWall, ConstantClassLibrary.ASLXNA.Hexside.CLDnWallSlope, ConstantClassLibrary.ASLXNA.Hexside.CLDnWallSlopePO
        Return True
        'If CrestSt Then Return True Else Return False
        'only certain CL terrain will permit creststatus: depression; other CL hexes will not and so WA will not be allowed
        'MOVED THIS OUT OF HERE AS IT RELATES TO UNIT STATUS NOT TERRAIN - INTEGRATED INTO MOVEMENT CLASSES
        'Return If(Game.Scenario.Moveobsi.StatusPrevents(ContextM.ClaimWallAdv), False, True)
        case ConstantClassLibrary.ASLXNA.Hexside.Roadblock1
        To ConstantClassLibrary.ASLXNA.Hexside.Roadblock6, ConstantClassLibrary.ASLXNA.Hexside.Roadblock
        'roadblock need special test
        If Otherhexnum >0 Then
        Return If (CInt(Starthex.Baselevel) > CInt(Otherhex.Baselevel), False, True)
        Else 'then otherhexnum is outside board
        Return True
        End If
        case Else
        Return False
        End Select*/
        return false;
    }
    public boolean IsWAAllowedToSwitch(int hexnum, int hexsidetouse, int Otherhexnum) {
        //'called by SwitchWallAdvantage.Switch and .WABlocked
        //'determines if Terrain allows WA; using a specific hex and hexside
        //'same as ISWAAllowed except for treatment of depression hexes - switch allowed; no statusprevents check

        //'get info for both hexes
        /*Dim LevelChk As New LevelChecks(MapData)
        Dim Starthex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        Dim Otherhex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Otherhexnum, 0)
        'Get hexside type
        Dim hexsidetype As Integer = Gethexsidetype(Starthex, hexsidetouse)
        Select case hexsidetype
        case ConstantClassLibrary.ASLXNA.Hexside.CLUpHedgeSlope,
        ConstantClassLibrary.ASLXNA.Hexside.Hedge, ConstantClassLibrary.ASLXNA.Hexside.Wall, ConstantClassLibrary.ASLXNA.Hexside.SlopeDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.SlopeDownWall, ConstantClassLibrary.ASLXNA.Hexside.CrestDownSlopeWall, ConstantClassLibrary.ASLXNA.Hexside.SunkenNonroadHedge,
                ConstantClassLibrary.ASLXNA.Hexside.HedgeUnpavedRoad, ConstantClassLibrary.ASLXNA.Hexside.WallUnpavedRoad, ConstantClassLibrary.ASLXNA.Hexside.CLUpHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CLUpWall, ConstantClassLibrary.ASLXNA.Hexside.CLUpWallSlope, ConstantClassLibrary.ASLXNA.Hexside.CLUpWallSlopePO,
                ConstantClassLibrary.ASLXNA.Hexside.WallPO, ConstantClassLibrary.ASLXNA.Hexside.HedgePO, ConstantClassLibrary.ASLXNA.Hexside.Bocage,
                ConstantClassLibrary.ASLXNA.Hexside.CrestDownHedge, ConstantClassLibrary.ASLXNA.Hexside.CrestDownWall, ConstantClassLibrary.ASLXNA.Hexside.CrestDownSlopeHedge,
                ConstantClassLibrary.ASLXNA.Hexside.SlopeUpHedge, ConstantClassLibrary.ASLXNA.Hexside.SlopeUpWall
        Return True
        case ConstantClassLibrary.ASLXNA.Hexside.CrestUpHedge,
        ConstantClassLibrary.ASLXNA.Hexside.CrestUpWall, ConstantClassLibrary.ASLXNA.Hexside.CrestUpSlopeHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CrestUpSlopeWall
        Return False
        case ConstantClassLibrary.ASLXNA.Hexside.CLDnHedgeSlope,ConstantClassLibrary.ASLXNA.Hexside.CLDownHedge,
                ConstantClassLibrary.ASLXNA.Hexside.CLDownWall, ConstantClassLibrary.ASLXNA.Hexside.CLDnWallSlope, ConstantClassLibrary.ASLXNA.Hexside.CLDnWallSlopePO
        'for switch purposes, WA can move to otherside of hexside
        Return True
        '' If CrestSt Then Return True Else Return False
        '' only certain CL terrain will permit creststatus:
        depression;
        other CL hexes will not and so WA will not be allowed
        'If Game.Scenario.Moveobsi.StatusPrevents(ContextM.ClaimWallAdv) Then Return False Else Return True
        case ConstantClassLibrary.ASLXNA.Hexside.Roadblock1
        To ConstantClassLibrary.ASLXNA.Hexside.Roadblock6, ConstantClassLibrary.ASLXNA.Hexside.Roadblock
        'roadblock need special test
        If CInt (Starthex.Baselevel) > CInt(Otherhex.Baselevel) Then Return False Else Return True
        case Else
        Return False
        End Select*/
        return false;
    }
    public boolean WAMandatory(int hexnum, int[] Otherhexnum , int LoctoTest, int PostoTest) {
        //'called by WallAdv.MoveUpdate, ForfeitWA.SwitchWA, Newhex.Moveupdate(where must check for adjacent enemy with WA or in-hex friendly with WA)
        //'Test if Mandatory WA (B9.323) applies and therefore unit must claim Wall Adv
        /*Dim HexTEM As Integer = 0
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(MapData)
        Dim WAHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        If PostoTest >0 And PostoTest <>ConstantClassLibrary.ASLXNA.AltPos.WallAdv Then
        'determine TEM of position being occupied
        Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim TerrCheck As TerrainClassLibrary.ASLXNA.TerrainChecks = New
        TerrainClassLibrary.ASLXNA.TerrainChecks(MapData)
        HexTEM = CInt(TerrCheck.GetPositionData(ConstantClassLibrary.ASLXNA.TerrFactor.TEM, PostoTest, Maptables))
        ElseIf WAHex.Location = LoctoTest Then
                HexTEM = WAHex.TEM
        ElseIf WAHex.OtherTerraininLocation = LoctoTest Then
                HexTEM = CInt(WAHex.TEMOtherTerrain)
        End If
        'if no in-hex TEM then must claim
        If HexTEM <=0 Then 'And WAHex.TEM <= 0 And CInt(WAHex.TEMOtherTerrain) < 0 Then
        'check for wall/hedge/rdblk
        Dim NoWallA As Boolean = True
        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapData)
        For i = 1 To 6
        'Dim Otherhexnum As Integer = Mapgeo.RangeC.DirExtent(hexnum, i, 1)
        If SideChk.IsWAAllowed(hexnum, i, Otherhexnum.Item(i - 1)) Then
                NoWallA = False :Exit For
        End If
        Next
        Return If (NoWallA, False, True)
        Else
        Return False
        End If*/
        return false;
    }
        //'overloaded
          //      'first uses hexside and hexnum; second uses hexside of Gamelocation
    public int Gethexsidetype(int hexside, int hexnumber) {
        //'called by IsSide.IsWAAllowed
        //'returns hexside value of specificied side of specified hex
        /*Dim Levelchk As New TerrainClassLibrary.ASLXNA.LevelChecks(MapData)
        Dim MapHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(hexnumber, 0)
        'Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
        With MapHex
        Select case hexside
        case 1
        Gethexsidetype = CInt(.Hexside1)
        case 2
        Gethexsidetype = CInt(.Hexside2)
        case 3
        Gethexsidetype = CInt(.Hexside3)
        case 4
        Gethexsidetype = CInt(.Hexside4)
        case 5
        Gethexsidetype = CInt(.Hexside5)
        case 6
        Gethexsidetype = CInt(.Hexside6)
        case Else
        MsgBox("Hexside failure")
        Gethexsidetype = 0
        End Select
        End With*/
        return 0;
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
