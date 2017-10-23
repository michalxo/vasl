package VASL.build.module.fullrules.LOSClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.LOS.VASLGameInterface;
import VASL.LOS.counters.OBA;
import VASL.LOS.counters.Smoke;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;
import VASL.build.module.fullrules.MapDataClasses.MapDataC;
import VASL.build.module.fullrules.ObjectClasses.AltHexGTerrain;
import VASL.build.module.fullrules.ObjectClasses.CombatTerrain;
import VASL.build.module.fullrules.ObjectClasses.SmokeHolder;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.TerrainClasses.TerrainChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.build.module.fullrules.UtilityClasses.DiceC;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class ThreadedLOSCheckCommonc {
    
    public LinkedList<CombatTerrain> TempCombatTerrColCommon = new LinkedList<CombatTerrain>();
    public LinkedList<AltHexGTerrain> TempAltHexLOSGroupCommon = new LinkedList<AltHexGTerrain>();   // holds Althexgrain instances before los confirmed
    public LinkedList<AltHexGTerrain> AltHexLOSGroupCommon = new LinkedList<AltHexGTerrain>();   // holds CombatTerrain.AltHexGTerrain instances
    private TerrainChecks terrchk;
    private boolean pMistIsLOSH= false;
    private boolean pDustIsLOSH= false;
    public ThreadedLOSCheckCommonc(TerrainChecks PassTerrChk) {
        terrchk = PassTerrChk;
    }
    
    public boolean IsMistLOSH() {return pMistIsLOSH;}
    public boolean IsDustLOSH() {return pDustIsLOSH;}
    /* 'Private Function AddtoCollection(ByVal Maphex As MapDataClassLibrary.GameLocation, ByVal Hexrole As Integer, ByVal PassSmokeList As List(Of CombatTerrainClassLibrary.ASLXNA.SmokeHolder)) As Boolean
                '    'called by Me.
        '    'from Mapgeo
        '    Dim UsingTarget As Boolean = false
                '    'no hex found by RetrieveData - exit
        '    If CStr(Maphex.Hexname) = "" Then return false
                '    'check if hex already present
        '    If TempCombatTerrColCommon.Count <> 0 Then
                '        'other hexes exist in collection
        '        For Each TempCT As objectclasslibrary.aslxna.combatterrain In TempCombatTerrColCommon
                '            If CStr(Maphex.Hexname) = TempCT.HexName Then
                '                If TempCT.IsTarget Then UsingTarget = true
                '                'REM out Aug 13 - not using TargetID in fire routines so no point in setting
        '                ' ''For i = 0 To 99
            '                ' ''    If TempCT.TargetID(i) = Maphex.LocIndex Then Exit For
        '                ' ''    If TempCT.TargetID(i) <> 0 Then Continue For
        '                ' ''    TempCT.TargetID(i) = Maphex.LocIndex
        '                ' ''    Exit For
        '                ' ''Next
        '                return false
                '            End If
                '        Next
                '    End If
                '    'determine if Target is in location or otherterraininlocation
        '    Dim PasshexTerrtype As Integer = 0 : Dim Passhextem As Integer = 0
                '    PasshexTerrtype = CInt(Maphex.Location)
                '    Passhextem = CInt(Maphex.TEM)
                '    Dim Passhexname As String = Trim(CStr(Maphex.Hexname))
                '    Dim PassHexID As Integer = CInt(Maphex.Hexnum)
                '    Dim PassHexside1 As Integer = CInt(Maphex.Hexside1)
                '    Dim PassHexside2 As Integer = CInt(Maphex.Hexside2)
                '    Dim PassHexside3 As Integer = CInt(Maphex.Hexside3)
                '    Dim PassHexside4 As Integer = CInt(Maphex.Hexside4)
                '    Dim PassHexside5 As Integer = CInt(Maphex.Hexside5)
                '    Dim PassHexside6 As Integer = CInt(Maphex.Hexside6)
                '    Dim PassBaseLevel As Single = CSng(Maphex.Baselevel)
                '    Dim PassStaircase As String = CStr(Maphex.HasStair)
                '    Dim Passcontrol As String = ""  'CStr(Maphex.Control)
        '    Dim PassOBA As Integer = CInt(Maphex.OBA)
                '    Dim PassHexHind As Integer = CInt(Maphex.LOSHdrm) 'GetTerrainData(EnumCon.TerrFactor.LOSHind, PasshexTerrtype))
            '    'Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
            '    Dim MapTableInstance = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)  'use null values when sure instance exists
        '    Dim PassHexdesc As String = terrchk.GetLocationData(constantclasslibrary.aslxna.terrfactor.Desc, PasshexTerrtype, MapTableInstance)
                '    'Dim PassHexdesc As String = GetTerrainData(EnumCon.TerrFactor.Desc, PasshexTerrtype)
        '    Dim Passhexrole As Integer = Hexrole
                '    Dim PassTargetID As Integer = Maphex.LocIndex   'this doesn't feel right - CHECK IT OUT
            '    Dim FireTerrain As New objectclasslibrary.aslxna.combatterrain(Passhexname, _
            '    PassHexID, PasshexTerrtype, PassHexside1, PassHexside2, _
            '    PassHexside3, PassHexside4, PassHexside5, PassHexside6, _
            '    Passhextem, PassHexHind, PassHexdesc, Passhexrole,
            '    PassStaircase, PassBaseLevel, Passcontrol, PassTargetID, PassSmokeList, PassOBA)
            '    If Not (IsNothing(FireTerrain)) Then TempCombatTerrColCommon.Add(FireTerrain)
            '    'if get this far then Terrain addeded
        '    return true
                'End Function*/
    public boolean AddtoCollectionThread(Location LOSLoc, Constantvalues.Hexrole Hexrole, int TempsolID, LinkedList<SmokeHolder> PassSmokeList) {
        //called by Me.DoSightcheck
        // adds a new CombatTerrain to a collection
        boolean UsingTarget = false;
        // no hex found - exit
        if (LOSLoc.getHex().getName() == "") {return false;}
        //check if hex already present
        if (TempCombatTerrColCommon.size() != 0) {
            // other hexes exist in collection
            for (CombatTerrain TempCT : TempCombatTerrColCommon) {
                if (LOSLoc.getHex().getName() == TempCT.getHexName()) {
                    if (TempCT.IsTarget()) {
                        UsingTarget = true;
                    }
                    if (TempCT.getHexBaseLevel() == LOSLoc.getBaseHeight()) {   // this may be wrong CombatTerrain needs to be location specific - may not be; may be hex specific
                        return false;
                    }
                }
            }
        }
        // determine if Target is in location or otherterraininlocation
        int Passhextem = 0; boolean PassHexLOSHApplies = false;
        Constantvalues.Feature Passoba = null;
        Constantvalues.Location PasshexTerrtype = getLocationtypefromVASLLocation(LOSLoc);
        //Passhextem = (LOSLoc.getTerrain().getTEM());
        int PassHexHind  = LOSLoc.getTerrain().getLOSHindDRM();
        if (PassHexHind >0) {PassHexLOSHApplies=true;}
        //String PassHexname  = (LOSLoc.getHex().getName());

        // int PassHexID = 0;  //= Maphex.getHexnum();
        // may be able to delete all of the hexside stuff and just use VASL hexside info
        ConversionC convert = new ConversionC();
        Constantvalues.Hexside PassHexside1  = convert.ConverttoHexside((LOSLoc.getHex().getHexsideLocation(1).getTerrain()));
        Constantvalues.Hexside PassHexside2  = convert.ConverttoHexside((LOSLoc.getHex().getHexsideLocation(2).getTerrain()));
        Constantvalues.Hexside PassHexside3  = convert.ConverttoHexside((LOSLoc.getHex().getHexsideLocation(3).getTerrain()));
        Constantvalues.Hexside PassHexside4  = convert.ConverttoHexside((LOSLoc.getHex().getHexsideLocation(4).getTerrain()));
        Constantvalues.Hexside PassHexside5  = convert.ConverttoHexside((LOSLoc.getHex().getHexsideLocation(5).getTerrain()));
        Constantvalues.Hexside PassHexside6  = convert.ConverttoHexside((LOSLoc.getHex().getHexsideLocation(0).getTerrain()));
        //int PassBaseLevel = (LOSLoc.getBaseHeight());
        //boolean PassStaircase  = LOSLoc.getHex().hasStairway();
        String Passcontrol  = "";  //Maphex.getControl)
        if (OBAPresentinHex(LOSLoc.getHex())) {
            Passoba = Constantvalues.Feature.FFE1;  // this is wrong needs to be generalized OBA value - change OBAPresentinHex
        } else {
            Passoba = Constantvalues.Feature.None;
        }
        //Constantvalues.Hexrole Passhexrole  = Hexrole;
        int PassTargetID  = 0;  // Maphex.getLocIndex();   // this doesn' t feel right - CHECK IT OUT
        CombatTerrain FireTerrain = new CombatTerrain(PasshexTerrtype, PassHexside1, PassHexside2,
                PassHexside3, PassHexside4, PassHexside5, PassHexside6,
                Passhextem, PassHexHind, Hexrole, Passcontrol, PassTargetID, PassSmokeList, Passoba, TempsolID, LOSLoc, PassHexLOSHApplies);
        if (FireTerrain != null) {TempCombatTerrColCommon.add(FireTerrain);}
        // if get this far then Terrain addeded
        return true;
    }

    /*
    Friend Function IsLocationAlreadyInTempCombatTerrColThread(ByVal LOCIndexToTest As Integer) As Boolean
    Dim LocationIsIn As Boolean = false 'flags if Location is already added to TempCombatTerrCol
    For Each TestComTer As objectclasslibrary.aslxna.combatterrain In TempCombatTerrColCommon
    If TestComTer.LocIndex = CInt(LOCIndexToTest) Then
    LocationIsIn = true  'Location is already in TempCombatTerrcol
    End If
    Next
    return LocationIsIn
    End Function
    */
    public boolean AddCombatTerrainHexThread(Location SeeLOSLoc, int UsingSol, Constantvalues.Hexrole Hexrole) {

        LinkedList<SmokeHolder> PassSmokelist; // = new LinkedList<SmokeHolder>();
        try {
            PassSmokelist = terrchk.SmokePresentinHex(SeeLOSLoc.getHex());
            AddtoCollectionThread(SeeLOSLoc, Hexrole, UsingSol, PassSmokelist);
            return true;
        } catch (Exception e) {
            // no hex found by RetrieveData - exit
            return false;
        }
    }
    private Constantvalues.Location getLocationtypefromVASLLocation(Location SeeLOSLoc){
        ConversionC DoConversion = new ConversionC();
         return DoConversion.getLocationtypefromVASLLocation(SeeLOSLoc);
        // this routine turns a VASL Location (VASL.LOS.Map.Location) into a Constantvalues.Location value
        // eventually can be eliminated as SharedBoardMetadata.xml is expanded
    }
            /*
        'Upslopecheck from Mapgeo
    Friend Sub UpSlopeCheckThread(ByRef TempSolItem As LOSClassLibrary.ASLXNA.TempSolution, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC, ByVal SideCheck As TerrainClassLibrary.ASLXNA.IsSide, ByVal hexsidecrossed() As Integer)
            ' called by Me.DoSightCheck
            ' determines if a hex is upslope as part of LOS process and sets values in TempSolItem
            ' ''Dim ChangeX As Single : Dim ChangeY As Single
            ' ''Dim lineslope As Single ': Dim hexsidecrossed(1) As Integer
            '' ''initialize array
            ' ''hexsidecrossed(0) = 0 : hexsidecrossed(1) = 0
                    '' ''get point data
            '' ''Dim MapGeo = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0)  ' use null value if sure instance exists   Mapgeo.MapBtype, Mapgeo.Xoffset, Mapgeo.Yoffset, Mapgeo.MaxCols, Mapgeo.MaxRows)
            ' ''With TempSolItem
            ' ''    Dim Targpoint As System.Drawing.PointF = MapGeo.SetPoint(.SeenHexNum)
            ' ''    Dim Firerpoint As System.Drawing.PointF = MapGeo.SetPoint(.SeeHexNum)
            ' ''    'determine lineslope
            ' ''    ChangeX = Targpoint.X - Firerpoint.X
                    ' ''    ChangeY = Targpoint.Y - Firerpoint.Y
                    ' ''    If ChangeX = 0 Then ' vertical change, vertex of hexsides 2 and 3 or 5 and 6
            ' ''        If Targpoint.Y > Firerpoint.Y Then  'Firer above target - Firer 2 and 3, Target 5 and 6
            ' ''            hexsidecrossed(0) = 5 : hexsidecrossed(1) = 6
            ' ''        Else  'Target above Firer
            ' ''            hexsidecrossed(0) = 2 : hexsidecrossed(1) = 3
                    ' ''        End If
                    ' ''    ElseIf .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.AltHexGrain Then  'Los follows hexside; need to check 2 hexsides
            ' ''        lineslope = (ChangeY) / (ChangeX)
                    ' ''        If Targpoint.X < Firerpoint.X Then
                    ' ''            If lineslope > 0 Then
                    ' ''                hexsidecrossed(0) = 1
                    ' ''                hexsidecrossed(1) = 2
                    ' ''            ElseIf lineslope < 0 Then
                    ' ''                hexsidecrossed(0) = 6
                    ' ''                hexsidecrossed(1) = 1
                    ' ''            End If
                    ' ''        ElseIf Targpoint.X > Firerpoint.X Then
                    ' ''            If lineslope > 0 Then ' hexside 5
            ' ''                hexsidecrossed(0) = 4
            ' ''                hexsidecrossed(1) = 5
            ' ''            ElseIf lineslope < 0 Then
            ' ''                hexsidecrossed(0) = 3
            ' ''                hexsidecrossed(1) = 4
            ' ''            End If
            ' ''        End If
            ' ''    Else
            ' ''        lineslope = (ChangeY) / (ChangeX)
            ' ''        'determine hexside entry for Targethex
            ' ''        'hexside for FirerHex is ALWAYS the opposite 1/4, 2/5, 3/6, 4/1, 5/2, 6/3

            ' ''        If Targpoint.X < Firerpoint.X Then   'hexside 6, 1, or 2
            ' ''            If lineslope > 0.667 Then ' hexside 2
            ' ''                hexsidecrossed(0) = 2
            ' ''            ElseIf lineslope < -0.667 Then 'hexside 6
            ' ''                hexsidecrossed(0) = 6
            ' ''            Else
            ' ''                hexsidecrossed(0) = 1
            ' ''            End If
            ' ''        ElseIf Targpoint.X > Firerpoint.X Then 'hexside 3, 4, or 5
            ' ''            If lineslope > 0.667 Then ' hexside 5
            ' ''                hexsidecrossed(0) = 5
            ' ''            ElseIf lineslope < -0.667 Then 'hexside 3
            ' ''                hexsidecrossed(0) = 3
            ' ''            Else
            ' ''                hexsidecrossed(0) = 4
            ' ''            End If
            ' ''        End If
            ' ''    End If
            'determine if Firer/Target hex is upslope
    With TempSolItem
    For i As Integer = 0 To 1
    If hexsidecrossed(i) > 0 Then
            .SeenUpSlope = false
    Dim MapHex As MapDataClassLibrary.GameLocation = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = .SeenHexNum And QU.LevelInHex = 0).First
                        'MsgBox("Target hexside crossed for slope calculation purposes: " & CStr(hexsidecrossed(i)))
    Dim hexsideterrain As Integer = SideCheck.Gethexsidetype(MapHex, hexsidecrossed(i))
    If hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUp Or hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpHedge Or _
    hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpPavedRoad Or hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpUnpavedRoad Or _
            hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpWall Or hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpWire Or
            hexsideterrain = constantclasslibrary.aslxna.hexside.CrestUpSlope Or hexsideterrain = constantclasslibrary.aslxna.hexside.CrestUpSlopeHedge Or
            hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpPO Or hexsideterrain = constantclasslibrary.aslxna.hexside.CLUpHedgeSlope Or
            hexsideterrain = constantclasslibrary.aslxna.hexside.CLUpWallSlopePO Or hexsideterrain = constantclasslibrary.aslxna.hexside.CLUpWallSlope Or
            hexsideterrain = constantclasslibrary.aslxna.hexside.CrestUpSlopeWall Then
            .SeenUpSlope = true
                            'only has to be upslope across one of the hexsides; see Peiper rules
    End If
                        'determine if Firer hex is upslope
    hexsidecrossed(i) = If(hexsidecrossed(i) > 3, hexsidecrossed(i) - 3, hexsidecrossed(i) + 3)
            'MsgBox("Firer hexside crossed for slope calculation purposes: " & CStr(hexsidecrossed(i)))
            .SeeUpSlope = false
            MapHex = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = .SeeHexNum And QU.LevelInHex = 0).First
            hexsideterrain = SideCheck.Gethexsidetype(MapHex, hexsidecrossed(i))
    If hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUp Or hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpHedge Or _
    hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpPavedRoad Or hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpUnpavedRoad Or _
            hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpWall Or hexsideterrain = constantclasslibrary.aslxna.hexside.SlopeUpWire Then
            .SeeUpSlope = true
                            'only has to be upslope across one of the hexsides; see Peiper rules
    End If
    End If
    Next i
    End With 'TempSolItem
    End Sub
        'Gethexsidetype from Linqdata
    Friend Function Gethexsidetype(ByVal hexside As Integer, ByVal hexnumber As Integer) As Integer

    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)  'use null values if sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
            'Dim Levelchk = New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim MapHex As MapDataClassLibrary.GameLocation = (From QU As MapDataClassLibrary.GameLocation In LocationCol Where QU.Hexnum = hexnumber And QU.LevelInHex = 0).First 'Levelchk.GetLocationatLevelInHex(hexnumber, 0)  'Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
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
    End With
    End Function
    public Function HexbyHexMoveAlongOKThread(ByVal xpix As Single, ByVal ypix As Single, ByVal TempSol As LOSClassLibrary.ASLXNA.TempSolution, ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC,
                                              ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal IsTerrChk As TerrainClassLibrary.ASLXNA.IsTerrain, ByVal LevelChk As TerrainClassLibrary.ASLXNA.LevelChecks, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC,
                                              ByVal Sidecheck As TerrainClassLibrary.ASLXNA.IsSide) As Boolean
            'called by DoClearSightCheck and DoNewSightCheck in two places; simply avoids code repitition, so should never be called from elsewhere

    Dim hexbyhexmovealongok As Boolean = true
    Dim HexTest As Integer = 0  'holds value of hex in which the selected point(xpix,ypix) is found
    Dim IsNewHex As Boolean = false  'toogle value for point text - set in HexCrossedCheck
    Dim hexObstlevel As Single = 0   'holds value of obstacle height if any
    Dim HexestoCheck As Integer = 1  'used only when LOS moving along vertical hexside
    Dim TestRange As Integer = 0  'used when LOS goes along hexside
    Dim LOSRange As Integer = 0  'holds range from see to seen
    Dim hexBaselevel As Single = 0   'holds base value of intervening hex
    Dim hexTerraintype As Integer = 0  'holds terrain type of intervening hex
    Dim Hexname As String = ""     'holds name of hex - used in test code; delete if code deleted 
    Dim PassUpperhexnum As Integer = 0 : Dim PassLowerhexnum As Integer = 0
    Dim nexthexsidecrossed As Integer : Dim hexsideterrain As Integer : Dim HexsideLevel As Single = 0
    Static Dim FirerHexspine As Boolean = false 'holds value of whether firer hexspine is wall, hedge, etc when los follows hexside
    Static Dim TargetHexspineVertex As Integer = 0 'holds value of number of exit hexsides at range of two from target that are wall, hedge, etc when los follows hexside (0, 1 or 2)
    Static Dim Lasthex As Integer = 0 ' holds value of last hex entered

    With TempSol
                'determines if new hex entered 
    HexTest = HexCrossedCheck(xpix, ypix, Lasthex, IsNewHex, TempSol, MapGeo, MapTableInstance)
                'checks hex is not firer/target
    If HexTest <> .SeeHexNum And HexTest <> .SeenHexNum Then
                    'set variables and get terrain, obstacle and height data
                            'use Hextest to retrieve hex specific data from map collection
                            'use terrraintype to retrieve terrain specific data from Terrain collection
    TestRange = MapGeo.CalcRange(.SeeHexNum, HexTest, true)
    LOSRange = MapGeo.CalcRange(.SeeHexNum, .SeenHexNum, true)
    If IsNewHex Then  'first time in this hex
            'if not new hex then clear because this routine does not check pixel-by-pixel; 
            'just inherent terrain, scenario terrain and hexsides that block LOS
    Dim Secondhextest As Integer = 0 : Dim FirstHextest As Integer = 0
    Dim LOSFollows As ThreadedLosFollowsi
    If .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.AltHexGrain Then
    LOSFollows = New ThreadedLosFollowsAltGrainc(MapTableInstance, MapCol, terrchk, Me, Sidecheck)
    ElseIf .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.VertHexGrain Then
    LOSFollows = New ThreadedLosFollowsVerticalc(MapTableInstance, MapCol, terrchk, Me, Sidecheck)
    ElseIf .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.NoHexGrain Or .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.HexGrain Then
    LOSFollows = New ThreadedLOSFollowsNonec(MapTableInstance, MapCol, terrchk, Me, Sidecheck)
    End If
                        LOSFollows.DoVariableSetup(TempSol, TestRange, HexestoCheck, xpix, HexTest, PassUpperhexnum, PassLowerhexnum, HexsideLevel, Secondhextest)
            ' ''If .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.AltHexGrain Then
            ' ''    If TestRange = 1 Then
            ' ''        DetermineAltHexes(.LOSFollows, .SeeHexNum, .SeenHexNum, .SeeHexNum, PassUpperhexnum, PassLowerhexnum)
            ' ''    ElseIf TestRange Mod 2 = 0 Then  'when range is even, find althexes at next range - do it this way to avoid determining which side of hexside the point is on
                        ' ''        DetermineAltHexes(.LOSFollows, .SeeHexNum, .SeenHexNum, HexTest, PassUpperhexnum, PassLowerhexnum)
                                ' ''    End If
                                ' ''    If TestRange Mod 2 = 0 Or TestRange = 1 Then
                                ' ''        If Not AltHexAlreadyAdded(PassUpperhexnum, PassLowerhexnum) Then
                                ' ''            Dim Upperlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(PassUpperhexnum, 0)
                                ' ''            Dim Lowerlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(PassLowerhexnum, 0)
                                ' ''            If Upperlocation.Baselevel > Lowerlocation.Baselevel Then
                                ' ''                HexsideLevel = Lowerlocation.Baselevel
                                ' ''            Else
                                ' ''                HexsideLevel = Upperlocation.Baselevel
                                ' ''            End If
                                ' ''            TempAltHexLOSGroupCommon.Add(New CombatTerrainClassLibrary.ASLXNA.AltHexGTerrain(PassUpperhexnum, PassLowerhexnum, .ID, TestRange, HexsideLevel))
                                ' ''        End If
                                ' ''    End If
                                ' ''ElseIf .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.VertHexGrain Then
                                ' ''    'TestRange = MapGeo.CalcRange(.SeeHexNum, HexTest, true)
            ' ''    If TestRange Mod 2 <> 0 Then  'when range is odd, LOS is along hexside
                        ' ''        HexestoCheck = 2
                                ' ''        'force check on right hand side hex first
                        ' ''        If xpix > MapGeo.GetCX(HexTest) Then HexTest += 1
                                ' ''        PassUpperhexnum = HexTest
                                ' ''        Secondhextest = HexTest - 1
                                ' ''        PassLowerhexnum = Secondhextest
                                ' ''        If Not AltHexAlreadyAdded(PassUpperhexnum, PassLowerhexnum) Then
                                ' ''            Dim Upperlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(PassUpperhexnum, 0)
                                ' ''            Dim Lowerlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(PassLowerhexnum, 0)
                                ' ''            If Upperlocation.Baselevel > Lowerlocation.Baselevel Then
                                ' ''                HexsideLevel = Lowerlocation.Baselevel
                                ' ''            Else
                                ' ''                HexsideLevel = Upperlocation.Baselevel
                                ' ''            End If
                                ' ''            TempAltHexLOSGroupCommon.Add(New CombatTerrainClassLibrary.ASLXNA.AltHexGTerrain(PassUpperhexnum, PassLowerhexnum, .ID, TestRange, HexsideLevel))
                                ' ''        End If
                                ' ''    End If
                                ' ''End If
                                ' ''If (TestRange Mod 2 = 1 Or TestRange = 1) And .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.AltHexGrain Then
                                ' ''    HexestoCheck = 2
                                ' ''    For Each AHGHex As CombatTerrainClassLibrary.ASLXNA.AltHexGTerrain In TempAltHexLOSGroupCommon
                                ' ''        If HexTest = AHGHex.UpperHexnum Then
                                ' ''            Secondhextest = AHGHex.LowerHexnum
                                ' ''            Exit For
                                ' ''        ElseIf HexTest = AHGHex.LowerHexnum Then
                                ' ''            Secondhextest = AHGHex.UpperHexnum
                                ' ''            Exit For
                                ' ''        End If
                                ' ''    Next
                                ' ''End If
    For k As Integer = 1 To HexestoCheck
    hexbyhexmovealongok = true
    If k = 2 Then
            FirstHextest = HexTest
    HexTest = Secondhextest   '-= 1
    PassLowerhexnum = HexTest
                                'TempAltHexLOSGroupCommon.Add(New CombatTerrainClassLibrary.ASLXNA.AltHexGTerrain(PassUpperhexnum, PassLowerhexnum, .ID, TestRange, HexsideLevel))
    End If
                            'Get basic hex information
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(HexTest, 0)
    hexTerraintype = Baselocation.Location
            hexBaselevel = Baselocation.Baselevel
    Dim Althextocheck As Integer = 0
    If (TestRange Mod 2 = 1 Or TestRange = 1) And .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.AltHexGrain Then
    For Each AHGHex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In TempAltHexLOSGroupCommon
    If HexTest = AHGHex.UpperHexnum Or HexTest = AHGHex.LowerHexnum Then
            HexsideLevel = AHGHex.Sidebaselevel : Exit For
    End If
    Next
    If HexsideLevel < hexBaselevel Then hexBaselevel = HexsideLevel
    End If
    hexObstlevel = Baselocation.ObstacleHeightinhex
                            'adjust results for terrain exception - Partially collapsed buildings in VotG
                                    'if doing hexbyhex, treat as rubble so adjust
                                    'if doing LOSCheck then treat as PartCol
    If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PartCol1 Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PartCol15 Then
            hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StoneRubble : hexObstlevel = 0.5
    End If
                            'are there any other terrain types which needed to be treated in this way?

                                    'test for crestline LOS block; slope is factored into Totallevel calculation see and seen
    hexbyhexmovealongok = Not (Crestlineblockcheck(LevelChk, GetLocs, hexBaselevel, TempSol, HexTest))
            'inherent terrain check
    If hexbyhexmovealongok = true Then
    Dim TerrTest As New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
    Baselocation = LevelChk.GetLocationatLevelInHex(HexTest, 0)
    Dim LocationToTest As Integer = Baselocation.LocIndex
    If TerrTest.IsTerrainInherent(LocationToTest) Then
    If DoesObstacleBlockLosThread(TempSol, hexObstlevel, hexTerraintype, hexBaselevel, HexTest, MapTableInstance, GetLocs, IsTerrChk, LevelChk, MapGeo) Then
    hexbyhexmovealongok = false : Exit For
    End If
    End If
    End If
                            ' check if hexside crossed blocks LOS


    If hexbyhexmovealongok Then
            hexbyhexmovealongok = Not (LOSFollows.Doeshexsideblocktest(HexTest, Lasthex, TempSol, TestRange, LOSRange, hexBaselevel, FirerHexspine, TargetHexspineVertex, k, Secondhextest, FirstHextest))
                                ' ''    If HexTest <> Lasthex And Lasthex <> .SeeHexNum And
                                        ' ''        HexTest <> .SeenHexNum Then
                                        ' ''        'hexsides involving See and Seen hexes don't block LOS
            ' ''        If .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.AltHexGrain Or .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.VertHexGrain Then  'LOS exact from centre to centre
                                ' ''            'Dim LOSTestRange As Integer = MapGeo.CalcRange(HexTest, .SeeHexNum, true)
            ' ''            If TestRange Mod 2 = 0 Then 'range is even
                                ' ''                'test 2 entry and 2 exit hexsides for this hex
                                ' ''                Dim SidesToCheck(5) As Integer
                                        ' ''                MapGeo.DetermineSidesToCheck(.SeeHexNum, .SeenHexNum, SidesToCheck)

                                        ' ''                If TestRange = 2 And TestRange + 2 = LOSRange Then
                                        ' ''                    'first check entry sides
                                ' ''                    If SpecialSpineTestFirer(SidesToCheck, TempSol, HexTest, hexBaselevel, FirerHexspine, Sidecheck, GetLocs) Then
                                        ' ''                        'LOS still ok, now need to change exit hexsides
                                ' ''                        TargetHexspineVertex = SpecialSpineTestTarget(SidesToCheck, TempSol, HexTest, hexBaselevel, Sidecheck, GetLocs)
                                        ' ''                    End If
                                        ' ''                ElseIf TestRange = 2 And TestRange + 2 <> LOSRange Then 'check if firer hexspine has LOS effect
                                ' ''                    'first check entry sides
                                ' ''                    If SpecialSpineTestFirer(SidesToCheck, TempSol, HexTest, hexBaselevel, FirerHexspine, Sidecheck, GetLocs) Then
                                        ' ''                        'LOS still ok, now need to change exit hexsides
                                ' ''                        For x As Integer = 3 To 4
                                        ' ''                            'get hexside type for side crossed
                                ' ''                            'hexsideterrain = Sidecheck.Gethexsidetype(Baselocation, SidesToCheck(x))
            ' ''                            'MsgBox("You are checking hexside " & SidesToCheck(x).ToString & " in Hex " & Hexname & " Hex side type: " & CStr(hexsideterrain))
            ' ''                            'check block if hexside terrain exists
                                ' ''                            'If hexsideterrain <> constantclasslibrary.aslxna.hexside.NoTerrain Then  '6500= hexside clear
            ' ''                            If HexSideBlockCheckThread(MapCol, Sidecheck, GetLocs, TempSol, SidesToCheck(x), hexBaselevel, HexTest) Then return false
            ' ''                            'hexside blocks so Hex...MoveAlongOK is false
            ' ''                            'End If
                                ' ''                        Next x
                                        ' ''                    Else
                                        ' ''                        return false
                                        ' ''                    End If
                                        ' ''                ElseIf TestRange + 2 = LOSRange And TestRange <> 2 Then ' check if target hexspine has LOS effect
                                ' ''                    'first check entry hexsides
                                ' ''                    For x As Integer = 1 To 2
                                        ' ''                        'get hexside type for  side crossed
                                ' ''                        'hexsideterrain = Sidecheck.Gethexsidetype(Baselocation, SidesToCheck(x))
            ' ''                        'MsgBox("You are checking hexside " & SidesToCheck(x).ToString & " in Hex " & Hexname & " Hex side type: " & CStr(hexsideterrain))
            ' ''                        'check block if hexside terrain exists
                                ' ''                        'If hexsideterrain <> constantclasslibrary.aslxna.hexside.NoTerrain Then  '6500= hexside clear
            ' ''                        If HexSideBlockCheckThread(MapCol, Sidecheck, GetLocs, TempSol, SidesToCheck(x), hexBaselevel, HexTest) Then return false
            ' ''                        'hexside blocks so Hex...MoveAlongOK is false
            ' ''                        'End If
                                ' ''                    Next x
                                        ' ''                    'now do exit sides
                                ' ''                    TargetHexspineVertex = SpecialSpineTestTarget(SidesToCheck, TempSol, HexTest, hexBaselevel, Sidecheck, GetLocs)
                                        ' ''                ElseIf TestRange <> 2 And TestRange + 2 <> LOSRange Then  'no hex spine impact
                                ' ''                    For x As Integer = 1 To 4
                                        ' ''                        'get hexside type for side crossed
                                ' ''                        'hexsideterrain = Sidecheck.Gethexsidetype(Baselocation, SidesToCheck(x))
            ' ''                        'MsgBox("You are checking hexside " & SidesToCheck(x).ToString & " in Hex " & Hexname & " Hex side type: " & CStr(hexsideterrain))
            ' ''                        'check block if hexside terrain exists
                                ' ''                        'If hexsideterrain <> constantclasslibrary.aslxna.hexside.NoTerrain Then  '6500= hexside clear
            ' ''                        If HexSideBlockCheckThread(MapCol, Sidecheck, GetLocs, TempSol, SidesToCheck(x), hexBaselevel, HexTest) Then return false
            ' ''                        'hexside blocks so Hex...MoveAlongOK is false
            ' ''                        'End If
                                ' ''                    Next x
                                        ' ''                End If

                                        ' ''                '' ''test 2 entry and 2 exit hexsides for this hex
                                        ' ''                ' ''Dim SidesToCheck(5) As Integer
                                ' ''                ' ''MapGeo.DetermineSidesToCheck(.SeeHexNum, .SeenHexNum, SidesToCheck)
            ' ''                ' ''For x As Integer = 1 To 4
            ' ''                ' ''    hexbyhexmovealongok = Not (HexSideBlockCheckThread(MapCol, Sidecheck, GetLocs, TempSol, x, hexBaselevel, HexTest))
            ' ''                ' ''    If hexbyhexmovealongok = false Then Exit For
                                ' ''                ' ''Next x
                                ' ''                ElseIf TestRange > 1 And Not (TestRange + 1 = LOSRange) Then  'need to check the hexside followed
                                ' ''                    'determine which hexside crossed (1-6)
                                ' ''                    If k = 1 Then
                                        ' ''                        nexthexsidecrossed = MapGeo.HexSideEntry(Secondhextest, HexTest)
                                        ' ''                    Else
                                        ' ''                        nexthexsidecrossed = MapGeo.HexSideEntry(FirstHextest, HexTest)
                                        ' ''                    End If
                                        ' ''                    hexbyhexmovealongok = Not (HexSideBlockCheckThread(MapCol, Sidecheck, GetLocs, TempSol, nexthexsidecrossed, hexBaselevel, HexTest))
                                        ' ''                ElseIf TestRange + 1 = LOSRange And k = 1 Then  'check for TargetHexspine, only need to do once
                                ' ''                    If TargetHexspineVertex = 2 Then hexbyhexmovealongok = false 'two hexside obstacles in previous hex block LOS regardless
                                ' ''                    If TargetHexspineVertex = 1 Then ' then need to test for Targethexspine; if nothing present then single side in previous hex blocks los
                                ' ''                        Dim SpineSide As Integer = MapGeo.HexSideEntry(Secondhextest, HexTest)
                                        ' ''                        Dim Spinesideterrain As Integer = Sidecheck.Gethexsidetype(Baselocation, SpineSide)
                                        ' ''                        Dim SpineLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(HexTest)
                                        ' ''                        If Sidecheck.IsAWallHedgeRdBlk(SpineSide, SpineLoc.LocIndex) Then
                                        ' ''                        If Not (CInt(Sidecheck.GetSideData(TerrFactor.HexsideTEM, Spinesideterrain, MapTableInstance))) > 0 Then hexbyhexmovealongok = false 'single side unconnected to target hex blocks LOS
                                ' ''                            'MessageBox.Show("hexspine " & SpineSide.ToString & " has a drm of " & HexSpineDRM.ToString)
            ' ''                        End If
            ' ''                    End If
            ' ''                End If
            ' ''        Else 'check for hexside crossed
                                ' ''            'determine which hexside crossed (1-6)
                                ' ''            nexthexsidecrossed = MapGeo.HexSideEntry(Lasthex, HexTest)
                                        ' ''            hexbyhexmovealongok = Not (HexSideBlockCheckThread(MapCol, Sidecheck, GetLocs, TempSol, nexthexsidecrossed, hexBaselevel, HexTest))
                                        ' ''        End If
                                        ' ''    ElseIf HexTest <> Lasthex And Lasthex = .SeeHexNum And .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.AltHexGrain Or .LOSFollows = MapGeoClassLibrary.ASLXNA.LOS.VertHexGrain Then
                                        ' ''        'range is 1
            ' ''        If TestRange = 1 And k = 1 Then  'check for firerHexspine, only need to do once
                                ' ''            'hexspine connects to Firerhex vertex
                                ' ''            Dim SpineSide As Integer = MapGeo.HexSideEntry(Secondhextest, HexTest)
                                        ' ''            Dim Spinesideterrain As Integer = Sidecheck.Gethexsidetype(Baselocation, SpineSide)
                                        ' ''            'Dim SpineLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(HexTest)
            ' ''            'Dim IsSideCheck = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
            ' ''            If Sidecheck.IsAWallHedgeRdBlk(SpineSide, Baselocation.LocIndex) Then
            ' ''                If CInt(Sidecheck.GetSideData(TerrFactor.HexsideTEM, Spinesideterrain, MapTableInstance)) > 0 Then FirerHexspine = true
            ' ''                'MessageBox.Show("hexspine " & SpineSide.ToString & " has a drm of " & HexSpineDRM.ToString)
            ' ''            End If
            ' ''        End If

            ' ''    End If
    End If
                            ' check if scenario terrain blocks LOS
    If hexbyhexmovealongok Then
    If DoesScenarioterrainBlockLOSThread(TempSol, hexObstlevel, hexBaselevel, HexTest, MapTableInstance, GetLocs, IsTerrChk, LevelChk, MapGeo) Then hexbyhexmovealongok = false
    End If
    If k = 2 Then
            HexTest = FirstHextest
    ElseIf Not (hexbyhexmovealongok) Then
    Exit For
    End If
    Next k
    End If
    Else
                    'hex is either See or Seen; either way, normally LOS is good; need to handle Seen hex exceptions
    hexbyhexmovealongok = true
                    'if still in See hex, do nothing
    Dim UsingPositionNoResultSave As Boolean = false
    If HexTest = .SeenHexNum And HexTest <> Lasthex Then   'in Seen hex and for first time or HexTest would = Lasthex
            'need to add some code here to cover bypass or wall advantage in target hex where target obstacle can block LOS
    If .TotalSeeLevel <= .TotalSeenLevel Then
    Dim IsCrossingWHR As Boolean = false
    Dim SeenLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
            'entrenched or crestatus behind a wall (blocks LOS but don't have results)
    If (.SeenPositionInHex >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And .SeenPositionInHex <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6) Or .SeenPositionInHex = ConstantClassLibrary.ASLXNA.AltPos.InFoxhole Or .SeenPositionInHex = ConstantClassLibrary.ASLXNA.AltPos.InSanger Or .SeenPositionInHex = ConstantClassLibrary.ASLXNA.AltPos.InTrench Then
                                'need to check for Wall/hedge/roadblock
    UsingPositionNoResultSave = true
            nexthexsidecrossed = MapGeo.HexSideEntry(Lasthex, HexTest)
                                'Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    IsCrossingWHR = Sidecheck.IsAWallHedgeRdBlk(nexthexsidecrossed, CInt(.SeenLOSIndex))
    If IsCrossingWHR Then hexbyhexmovealongok = false
    End If
                            'cellar
    Dim Hexsidelist As List(Of Integer) = MapGeo.LOSSideEntry(.SeenHexNum, .SeeHexNum)  'these are reversed to get hexside crossed exiting Seehex
    Dim SeeLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeeLOSIndex))
    Dim SeebaseLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeeHexNum))
    For Each nexthexside As Integer In Hexsidelist
                                'Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    IsCrossingWHR = Sidecheck.IsAWallHedgeRdBlk(nexthexside, CInt(.SeeHexNum))
    If IsCrossingWHR Then Exit For
            Next
    If SeeLoc.IsCellar And IsCrossingWHR Then hexbyhexmovealongok = false
                            'sewer
    If SeeLoc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
    If SeenLoc.IsManhole And SeeLoc.Hexnum = SeenLoc.Hexnum Then
                                    'possible los if discovered - how to program?

    Else
            hexbyhexmovealongok = false
    End If
    End If
    End If

                        'copied to here
    If hexbyhexmovealongok = true And .TotalSeeLevel >= .TotalSeenLevel Then
    Dim IsCrossingWHR As Boolean = false
    Dim Hexsidelist As List(Of Integer) = MapGeo.LOSSideEntry(.SeenHexNum, .SeeHexNum)  'these are reversed to get hexside crossed exiting Seehex
    Dim SeeLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeeLOSIndex))
            'entrenched or crestatus behind a wall (blocks LOS but don't have results)
    If (.SeePositionInHex >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And .SeePositionInHex <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6) Or .SeePositionInHex = ConstantClassLibrary.ASLXNA.AltPos.InFoxhole Or .SeePositionInHex = ConstantClassLibrary.ASLXNA.AltPos.InSanger Or .SeePositionInHex = ConstantClassLibrary.ASLXNA.AltPos.InTrench Then
                                'need to check for Wall/hedge/roadblock
    UsingPositionNoResultSave = true
    For Each nexthexside As Integer In Hexsidelist
                                    'Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    IsCrossingWHR = Sidecheck.IsAWallHedgeRdBlk(nexthexside, CInt(.SeeLOSIndex))
    If IsCrossingWHR Then hexbyhexmovealongok = false
    Next
    End If
                            'cellar
    Hexsidelist = MapGeo.LOSSideEntry(.SeeHexNum, .SeenHexNum)  'these are reversed to get hexside crossed exiting Seehex
    Dim SeenLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
    Dim SeenBaseLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenHexNum))
            'Dim IsCrossingWHR As Boolean = false
    For Each nexthexside As Integer In Hexsidelist
                                'Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    IsCrossingWHR = Sidecheck.IsAWallHedgeRdBlk(nexthexside, CInt(.SeenHexNum))
    If IsCrossingWHR Then Exit For
            Next
    If SeenLoc.IsCellar And IsCrossingWHR Then hexbyhexmovealongok = false
                            'sewer
    If SeenLoc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
    If SeeLoc.IsManhole And SeeLoc.Hexnum = SeenLoc.Hexnum Then
                                    'possible los if discovered - how to program?

    Else
            hexbyhexmovealongok = false
    End If
    End If
    End If
    If hexbyhexmovealongok = true And .TotalSeeLevel = .TotalSeenLevel Then
                            'below ground (cellar, sewer)
    Dim SeenLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
    If .TotalSeeLevel < SeenLoc.Baselevel Then
    Dim BelowGrdRange As Integer = MapGeo.CalcRange(.SeenHexNum, .SeeHexNum, true)
    If BelowGrdRange = 1 Then
            nexthexsidecrossed = MapGeo.HexSideEntry(.SeeHexNum, .SeenHexNum)
            'Dim sidecheck As TerrainClassLibrary.ASLXNA.IsSide = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    hexsideterrain = Sidecheck.Gethexsidetype(SeenLoc, nexthexsidecrossed)
    If Not Sidecheck.IsABuilding(hexsideterrain) AndAlso Not Sidecheck.IsACrossableBuilding(hexsideterrain) Then hexbyhexmovealongok = false 'all conditions exist to block LOS
    Else
            hexbyhexmovealongok = false  'no los between non-adjacent below ground (cellar, sewer)
    End If
    End If
    End If
    If hexbyhexmovealongok = true And .TotalSeeLevel <> .TotalSeenLevel Then
                            'Dim Getlocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim SeeLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeeLOSIndex))
    Dim SeenLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
    Dim ADJTest As New CombatTerrainClassLibrary.ASLXNA.HexBesideC(SeeLoc, SeenLoc, 0)
            'check both hexes for buidling side (handle roof and cellar situations)
    If ADJTest.IshexAdjacent Then
    nexthexsidecrossed = MapGeo.HexSideEntry(Lasthex, HexTest)
            'Dim sidecheck As TerrainClassLibrary.ASLXNA.IsSide = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
            'hexsideterrain = sidecheck.Gethexsidetype(SeenLoc, nexthexsidecrossed)
            'code from GEThexsidetype
            'Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)  'use null values if sure instance exists
                                'Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
                                        'Dim Levelchk = New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim MapHex As MapDataClassLibrary.GameLocation = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = SeenLoc.Hexnum And QU.LevelInHex = 0).First 'Levelchk.GetLocationatLevelInHex(hexnumber, 0)  'Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
    hexsideterrain = Sidecheck.Gethexsidetype(MapHex, nexthexsidecrossed)
    If Sidecheck.IsABuilding(hexsideterrain) Then
    hexbyhexmovealongok = false
                                    'all conditions exist to block LOS
    Else
            nexthexsidecrossed = MapGeo.HexSideEntry(HexTest, Lasthex)
                                    'hexsideterrain = sidecheck.Gethexsidetype(SeeLoc, nexthexsidecrossed)
                                            'code from GEThexsidetype
                                            'Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)  'use null values if sure instance exists
                                    'Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
                                            'Dim Levelchk = New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    MapHex = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = SeeLoc.Hexnum And QU.LevelInHex = 0).First 'Levelchk.GetLocationatLevelInHex(hexnumber, 0)  'Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
    hexsideterrain = Sidecheck.Gethexsidetype(MapHex, nexthexsidecrossed)
    If Sidecheck.IsABuilding(hexsideterrain) Then
    hexbyhexmovealongok = false
                                        'all conditions exist to block LOS
    End If
    End If
    End If
    End If
                        'If Not (Game.Scenario.ShaderToShow = EnumCon.ShadeShow.LOSShade) Then
    If hexbyhexmovealongok Then
                            'pull data from database to create TempCombatTerrCol collection
                                    'First check to see if hex already added
    Dim LocationIsIn As Boolean = IsLocationAlreadyInTempCombatTerrColThread(CInt(.SeenLOSIndex)) 'flags if hex is already part of TempCombatTerrCol
    If Not (LocationIsIn) Then
    AddCombatTerrainHexThread(CInt(.SeenLOSIndex), .ID, MapTableInstance, ConstantClassLibrary.ASLXNA.Hexrole.Target)
    End If
    End If
    End If
    End If
    Lasthex = HexTest
    End With
    return hexbyhexmovealongok
    End Function
    Friend Function HexCrossedCheck(ByVal xpix As Single, ByVal ypix As Single, _
            ByVal LastHex As Integer, ByRef IsNewHex As Boolean, ByVal tempsolitem As LOSClassLibrary.ASLXNA.TempSolution, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC, ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC) As Integer
            'called by Mapactions.ObstacleFind, Mapactions.HexByHexMoveAlongOK
                    'determines if Los check routine has entered a new hex 
    Dim Testpoint As PointF = New PointF() With {.X = xpix, .Y = ypix}
    Dim Testrange As Integer = 0 : Dim NoAdd As Boolean = false
    Dim Hexnumber As Integer = MapGeo.GethexnumfromCoord(Testpoint)
            'handle error in finding hex
    If Hexnumber = -999 Then Hexnumber = LastHex 'error in GethexnumFromCoord
    With tempsolitem
    If Hexnumber <> .SeeHexNum And Hexnumber <> .SeenHexNum _
    And Hexnumber <> LastHex Then
    If .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.VertHexGrain Then   'NEED TO HANDLE ALTHEXGRAIN
    Testrange = MapGeo.CalcRange(.SeeHexNum, Hexnumber, true)
    If Testrange Mod 2 <> 0 Then
    If Hexnumber + 1 = LastHex Then NoAdd = true
    End If
    End If
    If Not (NoAdd) Then
                        'new hex found
    Dim LocationIsIn As Boolean = IsLocationAlreadyInTempCombatTerrColThread(Hexnumber) 'flags if hex is already part of TempCombatTerrCol
    If Not (LocationIsIn) Then AddCombatTerrainHexThread(Hexnumber, .ID, MapTableInstance, constantclasslibrary.aslxna.hexrole.Intervening)
    IsNewHex = true
    End If
    End If
    HexCrossedCheck = Hexnumber
    End With  'TempSolitem
    End Function
    Friend Function DetermineAltHexes(ByVal LOSFollows As Integer, ByVal FirerHexNum As Integer, ByVal TargetHexNum As Integer, ByVal HexTest As Integer, ByRef PassUpperhexnum As Integer, ByRef PassLowerhexnum As Integer) As Boolean
            'called by MapActions.HexbyHexMoveAlongOK
                    'determines which two hexes form part of Alternate hexgrain LOS
    Dim Firerrow, FirerCol, TargetRow, TargetCol, AltDirU, AltDirL As Integer
    Dim HexPairTest As Integer = 0
    If LOSFollows = ConstantClassLibrary.ASLXNA.LOS.AltHexGrain Then  'sloped LOS
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)  'use null values when sure instance already exists
            MapGeo.GetRowandCol(FirerHexNum, Firerrow, FirerCol)
            MapGeo.GetRowandCol(TargetHexNum, TargetRow, TargetCol)
    If Firerrow < TargetRow Then   'Firer is above
    If FirerCol < TargetCol Then  'LOS is down to the right
    AltDirU = 1 : AltDirL = 2
    Else  'LOS is down to the left
    AltDirU = 4 : AltDirL = 3
    End If
    Else  'Firer is below
    If FirerCol < TargetCol Then  'LOS is up to the right
    AltDirU = 6 : AltDirL = 1
    Else  'Los is up to the left
    AltDirU = 5 : AltDirL = 4
    End If
    End If
    PassUpperhexnum = MapGeo.DirExtent(HexTest, AltDirU, 1)
    PassLowerhexnum = MapGeo.DirExtent(HexTest, AltDirL, 1)
    Else  ' follows vertical LOS

    End If
    return true
    End Function
    Friend Function AddHextoLOSH(ByVal Hexnumber As Integer) As Boolean
            'Called by Me.DoesObstacleBlockLOS, Map.HexbyHexMoveAlongOK 
    AddHextoLOSH = false
    For Each TempCombatTerr As objectClassLibrary.ASLXNA.CombatTerrain In TempCombatTerrColCommon
    If TempCombatTerr.HexID = Hexnumber Then
                    'LOSH applies
    TempCombatTerr.HexLOSHApplies = true
            AddHextoLOSH = true
    End If
    Next
    End Function
    Friend Function AltHexAlreadyAdded(ByVal PassUpperhexnum As Integer, ByVal PassLowerhexnum As Integer) As Boolean
    AltHexAlreadyAdded = false
    If TempAltHexLOSGroupCommon.Count > 0 Then
    For Each TempAltHex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In TempAltHexLOSGroupCommon
    If PassUpperhexnum = TempAltHex.UpperHexnum And PassLowerhexnum =
            TempAltHex.LowerHexnum Then
            AltHexAlreadyAdded = true
    Exit For
    End If
    Next
    End If
    End Function
    Friend Function DoesObstacleBlockLosThread(ByVal TempSolItem As LOSClassLibrary.ASLXNA.TempSolution, ByVal hexObstlevel As Single, _
            ByVal hexTerraintype As Integer, ByVal hexBaselevel As Single, ByVal Hexnumber As Integer, ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC,
                                               ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal IsTerrChk As TerrainClassLibrary.ASLXNA.IsTerrain, ByVal LevelChk As TerrainClassLibrary.ASLXNA.LevelChecks, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC) As Boolean
            'called by Me.DoSightCheck
                    'determines if obstacle found in hex actually blocks LOS
    DoesObstacleBlockLosThread = false
            'get name of obstacle type and hex for reporting purposes
    Dim TerrainName As String = terrchk.GetLocationData(constantclasslibrary.aslxna.terrfactor.Desc, hexTerraintype, MapTableInstance)
    Dim Hexhit As String = GetLocs.GetnamefromdatatableMap(Hexnumber)
    Dim msgstr As String 'holds text used in reporting
    DoesObstacleBlockLosThread = false  'default value; holding code
            'check if there is a LOS obstacle in hex
    With TempSolItem
    If hexObstlevel >= 1 Then
            msgstr = " over "
                    'calc height of obstacle
    hexObstlevel += hexBaselevel  'total hex height
    If .TotalSeenLevel >= hexObstlevel And .TotalSeeLevel >= hexObstlevel Then
                        'both are at or above obstacle: LOS is clear
    ElseIf (.TotalSeenLevel < hexObstlevel And .TotalSeeLevel < hexObstlevel) Or _
                    (.TotalSeenLevel = hexObstlevel And .TotalSeeLevel < hexObstlevel) Or _
            (.TotalSeenLevel < hexObstlevel And .TotalSeeLevel = hexObstlevel) Then
                        'both are below or one is below and one is at obstacle level: LOS is blocked unless obstacle is hindrance
    If (CInt(Math.Abs(.TotalSeenLevel - .TotalSeeLevel)) = 0) And hexBaselevel <= .TotalSeenLevel Then
                            'check if full-level obstance is Hindrance
                                    'Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, 0)
    Dim LocationToUse As Integer = Baselocation.LocIndex
    If IsTerrChk.IsLocationTerrainA(LocationToUse, ConstantClassLibrary.ASLXNA.Location.HindranceHex) Then
                                'do I have all of these terrain types - missing olive groves & some palm tree types
                                        'firer can see through same level fire of orchards, etc: LOS is clear
                                        'LOS Hindrance DRM applies
    If Not AddHextoLOSH(Hexnumber) Then
                                    'MsgBox("Error trying to set CombatTerrain.HexLOSHApplies property", , "Map.DoesObstacleBlockLOS")
    End If
    msgstr = " through "
    Else
                                'MsgBox("Your LOS is blocked by a " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check")
    DoesObstacleBlockLosThread = true
    End If
    Else
                            'MsgBox("Your LOS is blocked by a " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check")
    DoesObstacleBlockLosThread = true
    End If
    Else 'one is above and one is below
            'checking for blind hexes
            'determine range and height differences
            'Dim MapGeo = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Dim ObsTargRange As Integer = MapGeo.CalcRange(Hexnumber, .SeenHexNum, true)
    Dim FirerObsRange As Integer = MapGeo.CalcRange(Hexnumber, .SeeHexNum, true)
    Dim ObsTargElev As Single = hexObstlevel - .TotalSeenLevel
    Dim FirerObsElev As Single = .TotalSeeLevel - Int(hexObstlevel)
    If (ObsTargRange = 1 And ObsTargElev >= 1) Or _
                        (FirerObsRange = 1 And FirerObsElev <= -1) Then
                            'target is adjacent and below obstacle: automatic blind or
                                    'firer is adjacent and below obstacle: automatic blind
                                    'MsgBox("Your LOS is blocked by an adjacent " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check: Automatic Blind Hex")
    DoesObstacleBlockLosThread = true : Exit Function
    ElseIf (FirerObsRange% = 1 And FirerObsElev > 0) Or _
                        (ObsTargRange = 1 And ObsTargElev < 0) Then
                            'firer is adjacent and above obstacle: LOS clear
                                    'target is adjacent and above obstacle: LOS clear
    Else
                            'neither firer nor target is adjacent
    If FirerObsElev > 0 Then
                                'firer is above obstacle
    Dim FirerBlindHexes As Integer = CInt(Int(FirerObsRange / 5) - (FirerObsElev - 1))
    Dim TargetBlindHexes As Integer = CInt(hexBaselevel) - CInt(Math.Round(.TotalSeenLevel, 0, MidpointRounding.AwayFromZero))
    Dim BlindHexes As Integer = 1 + FirerBlindHexes + TargetBlindHexes
    If ObsTargRange <= BlindHexes Then
                                    'MsgBox("Your LOS is blocked by blind hexes created by " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check: Automatic Blind Hex")
    DoesObstacleBlockLosThread = true
    End If
    Else ' target is above obstacle
    Dim FirerBlindHexes As Integer = CInt((Int(ObsTargRange / 5)) - (Math.Abs(ObsTargElev) - 1))
    Dim TargetBlindHexes As Integer = CInt(hexBaselevel - Math.Abs(.TotalSeeLevel))
    Dim BlindHexes As Integer = FirerBlindHexes + TargetBlindHexes
    If FirerObsRange <= BlindHexes Then
                                    'MsgBox("Your LOS is blocked by blind hexes created by " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check: Automatic Blind Hex")
    DoesObstacleBlockLosThread = true
    End If
    End If
    End If
    End If
                    'obstacle has not blocked LOS
                            'MsgBox("Your LOS goes" & msgstr & "the " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check")
    ElseIf hexObstlevel = 0.5 Then
                    'check for LOS Hindrance
                            'calc height of obstacle
    hexObstlevel += hexBaselevel  'total hex height
    If .TotalSeenLevel = .TotalSeeLevel Then
    If hexObstlevel > .TotalSeeLevel Then
                            'some half-level obstacles block LOS others hinder
    If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodRubble Or
            hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StoneRubble Then
                                'LOS Blocked
                                        'MsgBox("Your LOS is blocked by " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check: Half-level LOS Obstacle")
    DoesObstacleBlockLosThread = true
            Else
                                'LOS Hindrance DRM applies
    If .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.AltHexGrain Or .LOSFollows = ConstantClassLibrary.ASLXNA.LOS.VertHexGrain Then
    Dim FirerObsRange As Integer = MapGeo.CalcRange(Hexnumber, .SeeHexNum, true)
    If FirerObsRange Mod 2 <> 0 Then 'do nothing as hex is part of alt pair
    Else
    AddHextoLOSH(Hexnumber)
    End If
    Else
    If Not (AddHextoLOSH(Hexnumber)) Then
    MsgBox("Error trying to set CombatTerrain.HexLOSHApplies property", , "ThreadedLOSCheckC.DoesObstacleBlockLOSThread")
    End If
    End If
    End If
    End If
                        'ElseIf .TotalSeeLevel > .TotalSeenLevel And hexObstlevel > .TotalSeenLevel Or _
                                '    .TotalSeeLevel < .TotalSeenLevel And hexObstlevel < .TotalSeenLevel Then
                                'LOSH DRM does not apply
                                'MsgBox("Your LOS goes over the " & Trim(TerrainName) & " Hindrance in " & Trim(Hexhit) & ": no DRM added due to height advantage", , "LOS Check. . .Hindrance Found")
    ElseIf .TotalSeeLevel > .TotalSeenLevel Or .TotalSeeLevel < .TotalSeenLevel Then
                        'handle exceptions: cellar
    Dim SeenLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
    If SeenLoc.IsCellar Then
                            'some half-level obstacles block LOS others hinder
    If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodRubble Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StoneRubble Then
                                'LOS Blocked
                                        'MsgBox("Your LOS is blocked by " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check: Half-level LOS Obstacle")
    DoesObstacleBlockLosThread = true
            Else
                                'LOS Hindrance DRM applies
    AddHextoLOSH(Hexnumber)
    End If
    End If
    Dim SeeLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeeLOSIndex))
    If SeeLoc.IsCellar Then
                            'some half-level obstacles block LOS others hinder
    If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodRubble Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StoneRubble Then
                                'LOS Blocked
                                        'MsgBox("Your LOS is blocked by " & Trim(TerrainName) & " in hex " & Trim(Hexhit), , "LOS Check: Half-level LOS Obstacle")
    DoesObstacleBlockLosThread = true
            Else
                                'LOS Hindrance DRM applies
    AddHextoLOSH(Hexnumber)
    End If
    End If
    End If
    End If
    End With  'Tempsolitem
    return DoesObstacleBlockLosThread
    End Function
    Friend Function HexSideBlockCheckThread(ByVal MapCol As IQueryable(Of MapDataClassLibrary.GameLocation), ByVal SideTest As TerrainClassLibrary.ASLXNA.IsSide, ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable,
    ByVal Tempsol As LOSClassLibrary.ASLXNA.TempSolution, ByVal hexsidecrossed As Integer, ByVal hexbaselevel As Single, ByVal hextest As Integer) As Boolean
            'get hexside type for side crossed
    HexSideBlockCheckThread = false
    Dim MapHex As MapDataClassLibrary.GameLocation = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = HexTest And QU.LevelInHex = 0).First 'Levelchk.GetLocationatLevelInHex(hexnumber, 0)  'Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
    Dim hexsideterrain As Integer = SideTest.Gethexsidetype(MapHex, hexsidecrossed)
    Dim hexname As String = MapHex.Hexname
            'MsgBox("You are checking hexside " & hexsidecrossed.ToString & " in Hex " & hexname & " Hex side type: " & CStr(hexsideterrain))
                    'check block if hexside terrain exists
    With Tempsol
    If hexsideterrain <> constantclasslibrary.aslxna.hexside.NoTerrain Then  '6500= hexside clear
            'cellar fix
    Dim TestSeeLevel As Single = 0 : Dim TestSeenLevel As Single = 0
    TestSeeLevel = .TotalSeeLevel : TestSeenLevel = .TotalSeenLevel
    Dim SeeLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeeLOSIndex))
    If SeeLoc.IsCellar Then TestSeeLevel += 1
    Dim SeenLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
    If SeenLoc.IsCellar Then TestSeenLevel += 1
    If DoesHexSideBlockLOSThread(TestSeeLevel, TestSeenLevel, hexbaselevel, MapHex.LocIndex, hexsideterrain, hexsidecrossed, SideTest) Then
    HexSideBlockCheckThread = true
            Else
    HexSideBlockCheckThread = false
    End If
    End If
    End With
    return HexSideBlockCheckThread
    End Function
    Friend Function DoesHexSideBlockLOSThread(ByVal TestSeeLevel As Single, ByVal TestSeenLevel As Single, ByVal hexBaselevel As Single, ByVal LOCIndexToUse As Integer, ByVal hexsideterrain As Integer, ByVal hexsidenumber As Integer, ByVal SideTest As TerrainClassLibrary.ASLXNA.IsSide) As Boolean
            'called by Me.DoSightCheck
                    'determines if hexside obstacle found actually blocks LOS
                    'get name of obstacle type for reporting purposes
    Dim hexsideObstlevel As Single = 0
            'Dim MapTableInstance = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)  'use null values when sure instance exists
            'Dim HexSideName As String = SideTest.GetSideData(constantclasslibrary.aslxna.terrfactor.Hexsidedesc, hexsideterrain, MapTableInstance)
    If SideTest.IsAWallHedgeRdBlk(hexsidenumber, LOCIndexToUse) Then hexsideObstlevel = hexBaselevel + CSng(0.5) 'total hex height
            'If hexsideterrain = constantclasslibrary.aslxna.hexside.Wall Or hexsideterrain = constantclasslibrary.aslxna.hexside.Hedge _
            'Or hexsideterrain = constantclasslibrary.aslxna.hexside.RailwayEmb Then
    If hexsideterrain = constantclasslibrary.aslxna.hexside.RailCar Then hexsideObstlevel = hexBaselevel + 1
    If TestSeenLevel = TestSeeLevel And hexsideObstlevel >= TestSeeLevel And hexsideObstlevel > 0 Then
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)
            'Dim Hexhit As String = GetLocs.GetnamefromdatatableMap(Hexnumber)
            'MsgBox("Your LOS   blocked by a " & HexSideName & " hexside in hex " & Trim$(Hexhit), , "LOS Check")
    DoesHexSideBlockLOSThread = true
            Else
                'MsgBox("Your LOS goes over the " & HexSideName & " hexside in hex " & Trim$(Hexhit), , "LOS Check")
    DoesHexSideBlockLOSThread = false
    End If
    End Function
    Friend Function DoesScenarioterrainBlockLOSThread(ByVal TempSolItem As LOSClassLibrary.ASLXNA.TempSolution, ByVal hexObstlevel As Single, _
            ByVal hexBaselevel As Single, ByVal Hexnumber As Integer, ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC,
                                                      ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal IsTerrChk As TerrainClassLibrary.ASLXNA.IsTerrain, ByVal LevelChk As TerrainClassLibrary.ASLXNA.LevelChecks, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC) As Boolean
            'called by Mapactions.HexbyHexMoveAlongOK
                    'determines if scenario terrain found in hex actually blocks LOS
    DoesScenarioterrainBlockLOSThread = false
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
    If Not IsNothing(Linqdata.ScenFeatcol) Then
                'If Not IsNothing(Game.linqdata.ScenFeatcol) Then
    For Each ScenFeat As DataClassLibrary.ScenarioTerrain In Linqdata.ScenFeatcol
                    'need to check each ScenFeat as more than one can exist per hex
                            'this routine only needs to check for LOS blocks; TEM impacts are dealt with in IFT.Combatdrm
                            'check for hex match
    If ScenFeat.Hexnumber = Hexnumber Then
                        'get type of terrain found
    If ScenFeat.CanBlockLOS(hexObstlevel) = true AndAlso DoesObstacleBlockLosThread(TempSolItem,
                                                                                    hexObstlevel, CInt(ScenFeat.FeatureType), hexBaselevel, Hexnumber, MapTableInstance, GetLocs, IsTerrChk, LevelChk, MapGeo) Then
            DoesScenarioterrainBlockLOSThread = true
    Exit For
    End If
    End If
    Next
            Else
                ' no scenario features currently exist in this scenario
                        'MsgBox("No Scenario Terrain currently exists in the game", , "Mapactions.DoesScenarioTerrainBlockLOS")
    End If
            'need to check which Scenario terrain can block LOS (pillboxes?)
                    'Just Rubble
    End Function*/
    public int MistDustmodifier(int range, boolean MistIsLOSH, int DustLOSh, Constantvalues.Mist Mistvalue, Constantvalues.Dust Dustvalue) {
        // called  by IFT.Combatdrm
        // return mist modifier based on range, -1 is returned if mistdrm =6 and LOS is blocked
        // return dust modifier based on range, dust LV never blocks LOS
        DustLOSh = 0;
        // selects mist strength
        DiceC Dice = new DiceC();
        switch (Mistvalue) {
            case None:    // And ConstantClassLibrary.ASLXNA.Dust.None
                MistIsLOSH = false;
                return 0;
            case VLight:
                pMistIsLOSH = false;
                return (range > 30 ? 5 : (range % 6 == 0 ? (int) (range / 6) - 1 : (int) (range / 6)));
            case Light:
                pMistIsLOSH = false;
                return (range > 30 ? -1 : (range % 6 == 0 ? (int) (range / 6) : (int) (range / 6) + 1));
            case Moderate:
                pMistIsLOSH = false;
                return (range > 20 ? -1 : (range % 4 == 0 ? (int) (range / 4) : (int) (range / 4) + 1));
            case Heavy:
                pMistIsLOSH = false;
                return (range > 15 ? -1 : (range % 3 == 0 ? (int) (range / 3) : (int) (range / 3) + 1));
            case VHeavy:
                pMistIsLOSH = true;
                return (range > 10 ? -1 : (range % 2 == 0 ? (int) (range / 2) : (int) (range / 2) + 1));
            case EHeavy:
                pMistIsLOSH = true;
                return (range > 5 ? -1 : range);
            default:
                pMistIsLOSH = false;
                // no break so will continue to switch (Dustvalue)
        }
        switch (Dustvalue) {
            case None:
                pDustIsLOSH = false;
                return 0;
            case Light:
                pDustIsLOSH = false;
                return (int) (Dice.Dieroll() / 2);
            case Moderate:
                pDustIsLOSH = false;
                //return (int) (Math.Ceiling(Dice.Dieroll() / 2));
            case Heavy:
                pDustIsLOSH = true;
                //DustLOSh = CInt(Math.Ceiling(range / 2));
                //return (int)(Math.Ceiling(range / 2)) >= 6, -1, (int)((int)(Dice.Dieroll() / 2)) + (int)(Math.Ceiling(range / 2)));
            case VHeavy:
            case EHeavy:
                pDustIsLOSH = true;
                DustLOSh = range;
                if (range >= 6) {
                    return -1; // LOS bloced by LOSH
                } else {
                    return (Dustvalue == Constantvalues.Dust.VHeavy ? (int)((int)(Dice.Dieroll() / 2)) + range: (int)((int)(Dice.Dieroll() / 2)) + range);
                }
            default:
                pDustIsLOSH = false;
                return 0;
        }
    }
    // moved to TerrainChecks
    /*public LinkedList<SmokeHolder> SmokePresentinHex(Hex Hextocheck) {
        // called by Me.DoSightCheck
        // returns list of smoke present in the hex - searches all locations in the hex
        LinkedList<SmokeHolder>  Smokelist = new LinkedList<SmokeHolder>();
        Smoke nextSmoke=null;
        ScenarioC scendet = ScenarioC.getInstance();
        VASLGameInterface vaslgameinterface = new VASLGameInterface(scendet.getASLMap(), scendet.getGameMap());
        HashSet<Smoke> LookforSmoke= vaslgameinterface.getSmoke(Hextocheck);
        // test for smoke, if found then set values and return
        Iterator<Smoke> itr=LookforSmoke.iterator();
        while(itr.hasNext()){
            nextSmoke = itr.next();
            Constantvalues.VisHind  Passvishind = ConverttoVisHind(nextSmoke.getName());
            int Passsmokebaselevel = nextSmoke.getLocation().getBaseHeight();
            SmokeHolder NewSmoke = new SmokeHolder(Passvishind, Passsmokebaselevel);
            Smokelist.add(NewSmoke);
        }
        return Smokelist;
    }*/
    public boolean OBAPresentinHex(Hex Hextocheck) {
        // need to change this to return specific OBA value (SR, FFE1, etc)
        ScenarioC scendet = ScenarioC.getInstance();
        VASLGameInterface vaslgameinterface = new VASLGameInterface(scendet.getASLMap(), scendet.getGameMap());
        HashSet<OBA> LookforOBA= vaslgameinterface.getOBA(Hextocheck);
        // test for OBA, if found then return
        Iterator<OBA> itr=LookforOBA.iterator();
        while(itr.hasNext()){
            return true;
        }
        return false;
    }

    /*Friend Function PixelLOSCheckThread(ByVal TempSolItem As LOSClassLibrary.ASLXNA.TempSolution, ByVal ScenID As Integer,
                                        ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC, ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal IsTerrChk As TerrainClassLibrary.ASLXNA.IsTerrain) As Boolean
            'Called by LOSCheckclass.DoSightCheck, returns LOS Yes/No 
                    'it searches for obstacles on a pixel by pixel basis as hex-by-hex already done

    Dim Hexnum As Integer = 0 : Dim HexCrossed = "" ': Dim Lasthex As String = ""
    Dim Endpoint As PointF

            'check for LOS blockages due to obstacles 
    If PixelLOSBlockCheckThread(TempSolItem, Endpoint, ScenID, MapTableInstance, GetLocs, IsTerrChk) Then
                'get name of hex where blockage occured
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Hexnum = MapGeo.GethexnumfromCoord(Endpoint)
            'Targethex can't block LOS (need to handle exceptions - bypass)
    If TempSolItem.SeenHexNum <> Hexnum Then
                    'MsgBox("LOS is blocked in " & Trim(Linqdata.GetnamefromdatatableMap(Hexnum)), , "LOS Check")
    return false
                    'Else
                            '    LOSCheck = true
                            '    PuthexdataintoComTercoll(TempSolItem.TargetHexNum, EnumCon.Hexrole.Target, TempSolItem)
    End If
    End If
            'If Not (Game.Scenario.ShaderToShow = EnumCon.ShadeShow.LOSShade) Then PuthexdataintoComTercoll(CInt(TempSolItem.SeenLOSIndex), EnumCon.Hexrole.Target, TempSolItem)
                    'if here then LOS good
    return true
            'End If
                    'For Each Tempterr As CombatTerrain In TempCombatTerrCol
                    '    HexCrossed &= Tempterr.HexName & Space(2)
                    'Next
                    'MsgBox("Hexes in LOS chain: " & HexCrossed, , "LOSCheck")
    End Function
    Private Function PixelLOSBlockCheckThread(ByVal Tempsolitem As LOSClassLibrary.ASLXNA.TempSolution, ByRef XYPoint As PointF, ByVal ScenID As Integer,
                                              ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC,
                                              ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal IsTerrChk As TerrainClassLibrary.ASLXNA.IsTerrain) As Boolean
            'called by MapActions.LOSCheck
                    'this routine only needs to focus on obstacles as hex-by-hex already done

    Dim BoardBMP As Bitmap = Nothing  'holds mask bmp
    Dim i As Single : Dim j As Integer = 0   'i holds point on line; j holds increment value
    Dim xpix As Single, ypix As Single, bcon As Single
    Dim Targetpoint As PointF : Dim Firerpoint As PointF  'holds centre points of firer and target hex
    Dim lineslope As Single = 0   'holds value of slope of line between target and firer centre points
    PixelLOSBlockCheckThread = false
            'get hex centre data for Firing and Target Hex
                    'Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)   ' use null values when sure instance exists  (Mapgeo.MapBtype, Mapgeo.Xoffset, Mapgeo.Yoffset, Mapgeo.MaxCols, Mapgeo.MaxRows)
    With Tempsolitem
    Targetpoint = MapGeo.SetPointF(.SeenHexNum)
    Firerpoint = MapGeo.SetPointF(.SeeHexNum)
    End With
            'calculates lineslope between centre points
    lineslope = MapGeo.LOSLineslope(Firerpoint, Targetpoint)
            'open b&w mask file
    Dim imagename As String = \dougr_000\documents\ASL\Images\Maps\"    'Game.Scenario.FilePath & "Images\Maps\"
            'Dim Scendet As DataClassLibrary.scen = Game.linqdata.GetScenarioData(Game.Scenario.ScenID) 'retrieves scenario data

            'use scenario data
    imagename &= GetBWMapName(Tempsolitem.ScenMap)
    BoardBMP = CType(Image.FromFile(imagename), Bitmap)
            'move along the line between the two points testing for pixel being white
            'if y = ax+b (ie straight line)and you have two pairs of x,y coords x1,y1 and x2,y2
            'then slope a =(y2-y1)/(x2-x1), and given that value for a
            'Dim LastHex As String = Nothing
    bcon = Firerpoint.Y - (lineslope * Firerpoint.X)
    If lineslope > 1 Or lineslope < -1 Then         ' move along Y, calculate x
    j = If(Firerpoint.Y < Targetpoint.Y, 2, -2) ' based on which point is "above" 
    For i = Firerpoint.Y + 1 To Targetpoint.Y Step j
            ypix = i : xpix = (ypix - bcon) / lineslope
            PixelLOSBlockCheckThread = ObstacleFindThread(Tempsolitem, xpix, ypix, BoardBMP, MapGeo, MapTableInstance, GetLocs, IsTerrChk)
    If PixelLOSBlockCheckThread Then Exit For
            Next
    Else                                              ' move along x, calculate y
    j = If(Firerpoint.X < Targetpoint.X, 2, -2) ' based on which point is "left"
    For i = Firerpoint.X + 1 To Targetpoint.X Step j
            xpix = i : ypix = lineslope * i + bcon
            PixelLOSBlockCheckThread = ObstacleFindThread(Tempsolitem, xpix, ypix, BoardBMP, MapGeo, MapTableInstance, GetLocs, IsTerrChk)
    If PixelLOSBlockCheckThread Then Exit For
            Next
    End If
            '' displays LOS as redline
            'Dim FinishPoint As New PointF() With {.X = xpix, .Y = ypix}
                    'Game.XNAGph.SetLOSLine(Firerpoint, FinishPoint)
                    ''send back to LOSCheck, point at which LOS ended (either blocked or clear)
            'XYPoint.X = xpix : XYPoint.Y = ypix
    End Function
    Friend Function GetBWMapName(ByVal Maptype As Integer) As String
            'called by Mapactions.LOSBlockCheck and Mapactions.HexbyHexClear
    If Maptype > 1100 Then 'historical board
    Select case Maptype
    case ConstantClassLibrary.ASLXNA.Map.BloodReef
    return "bdTarawaB&W.gif"
    case ConstantClassLibrary.ASLXNA.Map.PegasusBridge
    return "bdPBB&W.gif"
    case ConstantClassLibrary.ASLXNA.Map.Stoumont
    return "bdSTB&W.gif"
    case ConstantClassLibrary.ASLXNA.Map.RedB
    return "bdRBB&W.gif"
    case ConstantClassLibrary.ASLXNA.Map.LaGleize
    return "bdLGB&W.gif"
    case ConstantClassLibrary.ASLXNA.Map.Cheneux
    return "bdCHB&W.gif"
    case ConstantClassLibrary.ASLXNA.Map.VotG
    return "bdVotGB&W.bmp"
    case Else
    return Nothing
    End Select
    Else
                'Needs to be fixed; only works for Board 1
    return "bd01B&W.gif"
    End If

    End Function
    Private Function ObstacleFindThread(ByVal TempSolItem As LOSClassLibrary.ASLXNA.TempSolution, ByVal xpix As Single, ByVal ypix As Single, ByVal BoardBMp As Bitmap,
                                        ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC, ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC,
                                        ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal IsTerrChk As TerrainClassLibrary.ASLXNA.IsTerrain) As Boolean
            'called by MapActions.LOSBlockCheck
                    'checks new point for los obstruction on pixel-by-pixel basis

                    'the Static variables are only changed when a new hex is found 
    Static Dim Hexnumber As Integer
    Static Dim hexBaselevel As Single
    Static Dim hexTerraintype As Integer
    Dim Baselocation As MapDataClassLibrary.GameLocation
    Static Dim Lastnumber As Integer
    Static Dim hexObstlevel As Single
    Dim testcolour As Color : Dim IsNewHex As Boolean = false
            'Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
    ObstacleFindThread = false
            'determines if new hex entered
    With TempSolItem
    Hexnumber = HexCrossedCheck(xpix, ypix, Lastnumber, IsNewHex, TempSolItem, MapGeo, MapTableInstance)
                'checks hex is not firer/target
    If Hexnumber <> .SeeHexNum And Hexnumber <> .SeenHexNum Then
                    'set variables and get terrain, obstacle and height data
                            'use Hexnumber to retrieve hex specific data from map collection
                            'use terrraintype to retrieve terrain specific data from Terrain collection
    If IsNewHex Then
                        'reset variables to new hex info
    Baselocation = LevelChk.GetLocationatLevelInHex(Hexnumber, 0)
    hexTerraintype = Baselocation.Location
            hexBaselevel = Baselocation.Baselevel : hexObstlevel = Baselocation.ObstacleHeightinhex
    End If
                    'each step along LOS, check if terrain blocks LOS
    Do
            Try
    testcolour = BoardBMp.GetPixel(CInt(xpix), CInt(ypix))
    Exit Do
    Catch ex As Exception
    If ypix > BoardBMp.Width Then ypix = BoardBMp.Width
    If ypix < 0 Then ypix = 0
    If xpix > BoardBMp.Height Then xpix = BoardBMp.Height
    If xpix < 0 Then xpix = 0
    End Try
    Loop
                    'Black=0,0,0: White =255,255, 255
    If testcolour.R = 0 And testcolour.G = 0 And testcolour.B = 0 Then
                        'MsgBox("You have hit a Black pixel at " & CStr(CInt(xpix)) & " , " & CStr(CInt(ypix)))
    If DoesObstacleBlockLosThread(TempSolItem, hexObstlevel, hexTerraintype, hexBaselevel, _
            Hexnumber, MapTableInstance, GetLocs, IsTerrChk, LevelChk, MapGeo) Then
    ObstacleFindThread = true : Exit Function
    End If
    End If
    Else
    If Hexnumber = .SeenHexNum Then  'And HexTest <> LastHex Then   'in target hex
                        'need to add some code here to cover bypass or wall advantage
                                'in target hex where target obstacle can block LOS
    End If
    End If
    Lastnumber = Hexnumber
    End With
    End Function
    Friend Function Crestlineblockcheck(ByVal Levelchk As TerrainClassLibrary.ASLXNA.LevelChecks, ByVal getlocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal hexbaselevel As Single,
                                        ByVal Tempsol As LOSClassLibrary.ASLXNA.TempSolution, ByVal Hextest As Integer) As Boolean
    Dim CrestlineBlocksLOS As Boolean = false
    Dim Baselocation As MapDataClassLibrary.GameLocation
    With Tempsol
    If hexbaselevel = .TotalSeeLevel And .TotalSeeLevel > .TotalSeenLevel Then
                    'LOSBlocked by crestline in intervening hex
                            'if on Tarawa, could be hinterland situation
    CrestlineBlocksLOS = true
                    'now handle exceptions which allow LOS: if on Tarawa, could be hinterland situation; cellar
    If CInt(Tempsol.ScenMap) = ConstantClassLibrary.ASLXNA.Map.BloodReef Then
                        '        MsgBox("Blood Reef hinterland situation)")
    Baselocation = Levelchk.GetLocationatLevelInHex(Hextest, 0)
    If Baselocation.Location = ConstantClassLibrary.ASLXNA.Location.Ocean Then
                            'HinterlandHind = true
    CrestlineBlocksLOS = false
    End If
    End If
                    'cellar
    Dim SeenLoc As MapDataClassLibrary.GameLocation = getlocs.RetrieveLocationfromMaptable(CInt(.SeenLOSIndex))
    If SeenLoc.IsCellar Then CrestlineBlocksLOS = false
    ElseIf hexbaselevel = .TotalSeenLevel And .TotalSeeLevel < .TotalSeenLevel Then
                    'LOSBlocked by crestline in intervening hex
    CrestlineBlocksLOS = true
                    'now handle exceptions which allow LOS: if on Tarawa, could be hinterland situation; cellar
    If CInt(Tempsol.ScenMap) = ConstantClassLibrary.ASLXNA.Map.BloodReef Then
                        '        MsgBox("Blood Reef hinterland situation)")
    Baselocation = Levelchk.GetLocationatLevelInHex(Hextest, 0)
    If Baselocation.Location = ConstantClassLibrary.ASLXNA.Location.Ocean Then
                            'HinterlandHind = true
    CrestlineBlocksLOS = false
    End If
    End If
                    'cellar
    Dim SeeLoc As MapDataClassLibrary.GameLocation = getlocs.RetrieveLocationfromMaptable(CInt(.SeeLOSIndex))
    If SeeLoc.IsCellar Then CrestlineBlocksLOS = false
    ElseIf hexbaselevel > .TotalSeenLevel And hexbaselevel > .TotalSeeLevel Then   'intervening above see and seen
            'MsgBox("LOS Blocked- Intervening hex is higher than Seer and Seen!!", , "LOS Check in " & Trim(Getlocs.GetnamefromdatatableMap(.SeenHexNum)))
    CrestlineBlocksLOS = true ': return LosStatus.None
    End If
    End With
    return CrestlineBlocksLOS
    End Function
    Friend Function DoPillboxCheck(ByVal Tempsol As LOSClassLibrary.ASLXNA.TempSolution, ByVal Terrget As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable, ByVal Maptables As MapDataClassLibrary.ASLXNA.MapDataC, ByVal Linqdata As DataClassLibrary.ASLXNA.DataC, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC) As Boolean
            'called by
    Dim hexbyhexclear As Boolean
    With Tempsol
    Dim HexLocIndex As Integer = Terrget.GetPillboxLocation(.SeeHexNum)
    Dim UsingHex As MapDataClassLibrary.GameLocation
    If HexLocIndex > 0 Then
    Dim PillboxLOS As Boolean = false
                    'get Pillbox location
    UsingHex = Terrget.RetrieveLocationfromMaptable(HexLocIndex)
            'Now determine Pillbox covered arc
    Dim Imagename As String = terrchk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.Image, CInt(UsingHex.Location), Maptables)
    Dim TestCA As DataClassLibrary.LookupCA = (From Qu In Linqdata.db.LookupCAs Where Qu.Terraindesc = Imagename).First
                    'NEED TO DO CA DETERMINATION - CURRENT CODE IS WRONG - THIS SHOULD BE OUTSIDE LOS AS WILL BE NEEDED FOR MANY PURPOSES but here for now
    PillboxLOS = IsSeenWithinSeeCA(CInt(.SeeHexNum), CInt(.SeenHexNum), TestCA, MapGeo)
    If Not PillboxLOS Then hexbyhexclear = false Else hexbyhexclear = true
    Else 'no pillbox
    hexbyhexclear = true
    End If
    End With
    return hexbyhexclear
    End Function
    Friend Function IsSeenWithinSeeCA(ByVal Seehexnum As Integer, ByVal Seenhexnum As Integer, ByVal TestCA As DataClassLibrary.LookupCA, ByVal MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC) As Boolean
    Dim CAMatch As CADeterminationi
    Select case TestCA.FirstCA
    case 1
    CAMatch = New CADetermination1c
    case 2
    CAMatch = New CADetermination2c
    case 3
    CAMatch = New CADetermination3c
    case 4
    CAMatch = New CADetermination4c
    case 5
    CAMatch = New CADetermination5c
    case 6
    CAMatch = New CADetermination6c
    case Else
    return false
    End Select
    return CAMatch.IsSeenInCA(Seehexnum, Seenhexnum, MapGeo)
    End Function
    Friend Function DoSameHexCheck(ByVal TempSol As LOSClassLibrary.ASLXNA.TempSolution, ByVal MapTableInstance As MapDataClassLibrary.ASLXNA.MapDataC) As Boolean
            'same hex LOS
    Dim HexbyhexClear As Boolean = false
    With TempSol
    If IsSameHexLOSClear(TempSol) Then
                    'locations can see each other
                            'add firer then target location to CombatTerrain collection - TempCombatTerrCol
                            'First check to see if hex already added
    Dim LocationIsIn As Boolean = IsLocationAlreadyInTempCombatTerrColThread(CInt(.SeeLOSIndex)) 'flags if hex is already part of TempCombatTerrCol
            'add firer
    If Not (LocationIsIn) Then 'need to add to TempCombatTerrCol
    AddCombatTerrainHexThread(CInt(.SeeLOSIndex), .ID, MapTableInstance, constantclasslibrary.aslxna.hexrole.Firer)
    End If
                    'now add target; First check to see if hex already added
    LocationIsIn = IsLocationAlreadyInTempCombatTerrColThread(CInt(.SeenLOSIndex)) 'flags if hex is already part of TempCombatTerrCol
    If Not (LocationIsIn) Then
    AddCombatTerrainHexThread(CInt(.SeenLOSIndex), .ID, MapTableInstance, constantclasslibrary.aslxna.hexrole.Target)
    End If
    HexbyhexClear = true
            Else
                    'LOS blocked
    HexbyhexClear = false
    End If
    End With
    return HexbyhexClear
    End Function
    Friend Function IsSameHexLOSClear(ByVal Tempsolitem As LOSClassLibrary.ASLXNA.TempSolution) As Boolean
            'called by Me.DoSightCheck
                    'determines if fire within hex has an LOS
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
    Dim baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Tempsolitem.SeeHexNum, 0)

            'How to handle bypass?

    With Tempsolitem
    If System.Math.Abs(.SeeLevelInHex - .SeenLevelInHex) > 1 Then
                    'Units are more than one level apart
                            'Only units in towers or Factory hexes have los
    If baselocation.IsFactory Or IsTerrChk.IsLocationTerrainA(baselocation.LocIndex, ConstantClassLibrary.ASLXNA.Location.towertype) Then
            IsSameHexLOSClear = true
    Else
            IsSameHexLOSClear = false
    End If
                    'need to check if TargetUnits are in bypass
    ElseIf System.Math.Abs(.SeeLevelInHex - .SeenLevelInHex) = 1 Then
                    'units are adjacent levels in same hex
                            'units in Sewers and Cellars are ADJACENT and have LOS (other conditions govern fire eligibility)
    Dim Firerlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(.SeeHexNum, CInt(.SeeLevelInHex))
    Dim Targetlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(.SeenHexNum, CInt(.SeenLevelInHex))
    If IsTerrChk.IsLocationTerrainA(Firerlocation.LocIndex, ConstantClassLibrary.ASLXNA.Location.bridgetype) Or
                    IsTerrChk.IsLocationTerrainA(Firerlocation.LocIndex, ConstantClassLibrary.ASLXNA.Feature.Tunnel) Or
                    IsTerrChk.IsLocationTerrainA(Targetlocation.LocIndex, ConstantClassLibrary.ASLXNA.Location.bridgetype) Or
                    IsTerrChk.IsLocationTerrainA(Targetlocation.LocIndex, ConstantClassLibrary.ASLXNA.Feature.Tunnel) Then
                        'units on/below bridge do not have LOS to each other; units in tunnel have no LOS
    IsSameHexLOSClear = false
    ElseIf IsTerrChk.IsLocationTerrainA(baselocation.LocIndex, ConstantClassLibrary.ASLXNA.Location.NonStairBldg) Then
                        'if any location is non-stairbldg then ground level MUST BE; non-stair bldg locations do not have LOS
    IsSameHexLOSClear = false
            Else
    IsSameHexLOSClear = true
    End If
    Else
                    'Units are in same level (but not necessarily the same location) - TPBF
    IsSameHexLOSClear = true
                    'pillbox fire is ok, I think
                            'handle cave as an exception to this
    End If
    End With

    End Function*/

    // moved to TerrainChecks
    /*private Constantvalues.VisHind ConverttoVisHind(String smoketype){
        if (smoketype == "White +3 Smoke") {
            return Constantvalues.VisHind.GunSmoke;
        } else {
            return Constantvalues.VisHind.None;
        }
    }*/

}
