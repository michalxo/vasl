package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.TerrainClasses.IsSide;

import java.util.LinkedList;

public class MapCommonFunctionsc {

    public MapCommonFunctionsc(){

    }

    // this method returns the 6 hexes that are adjacent to a starting hex; null values if adjacent hex is "off-borad"
    public Hex[] getAdjacentHexArray(Hex StartHex) {
        Hex[] Adjacenthexes = new Hex[6];

        for(int x = 0; x < 6; x++) {
            Adjacenthexes[x] = StartHex.getMap().getAdjacentHex(StartHex, x);
        }
        return Adjacenthexes;
    }
    public boolean IsCrestStatus(Constantvalues.AltPos hexposition){
        return (hexposition == Constantvalues.AltPos.CrestStatus0 ||
                hexposition == Constantvalues.AltPos.CrestStatus1 ||
                hexposition == Constantvalues.AltPos.CrestStatus2 ||
                hexposition == Constantvalues.AltPos.CrestStatus3 ||
                hexposition == Constantvalues.AltPos.CrestStatus4 ||
                hexposition == Constantvalues.AltPos.CrestStatus5);

    }

    // this method returns all locations ADJACENT to a starting location
    public LinkedList<Locationi> getAllADJACENTLocations(Location StartLocation) {
        //this function returns a list of all locations ADJACENT to a specificied location
        //don't need to check entrenchments as the are not sparate locations for ADJACENCY purposes

        LinkedList<Locationi> ADJList = new LinkedList<Locationi>();

        // look for locations in hex - UpLocation and DownLocation checks will cover Pillboxes, etc which are added as up or down locations
        if (StartLocation.getDownLocation() != null) {
            ADJList.add(new Locationc(StartLocation.getDownLocation(), null));
        }
        if (StartLocation.getUpLocation() != null) {
            ADJList.add(new Locationc(StartLocation.getUpLocation(), null));
        }

        // check for Pillbox (and other terrain) that can only be ADJACENT to same hex Locations
        Locationi Startlocationc = new Locationc(StartLocation, null);
        // Pillbox
        if (Startlocationc.IsPillbox()) {
            return ADJList;
        }
        // pillbox cellar

        // bridge


        // look for ADJACENT locations in other hexes
        Hex[] AdjacentHexes = getAdjacentHexArray(StartLocation.getHex());
        int Testlevel = StartLocation.getAbsoluteHeight();
        for (int x = 0; x < 6; x++) {
            if (AdjacentHexes[x] != null){
                if (AdjacentHexes[x].getCenterLocation().getAbsoluteHeight() == Testlevel) {
                    ADJList.add(new Locationc(AdjacentHexes[x].getCenterLocation(), null));
                } else {
                    // center, up locations
                    Location temp1 = AdjacentHexes[x].getCenterLocation();
                    while (temp1.getUpLocation() != null) {
                        if (Testlevel == (temp1.getUpLocation().getAbsoluteHeight())) {
                            ADJList.add(new Locationc(temp1.getUpLocation(), null));
                        }
                        temp1 = temp1.getUpLocation();
                    }

                    // down locations
                    temp1 = AdjacentHexes[x].getCenterLocation();
                    while (temp1.getDownLocation() != null) {
                        if (Testlevel == (temp1.getDownLocation().getAbsoluteHeight())) {
                            ADJList.add(new Locationc(temp1.getDownLocation(), null));
                        }
                        temp1 = temp1.getDownLocation();
                    }
                    // now check for crestlines
                    temp1 = AdjacentHexes[x].getCenterLocation();
                    if (temp1.getAbsoluteHeight() == Testlevel + 1 || temp1.getAbsoluteHeight() == Testlevel - 1) {
                        // add hexsidecrossed determination
                        IsSide SideTest = new IsSide();
                        int hexsidecrossed = SideTest.SharedhexsideAdjacentHexes(StartLocation.getHex().getName(), temp1.getHex().getName());
                        // now get hexsideterrrain
                        Terrain hexsideterrain = StartLocation.getHex().getHexsideTerrain(hexsidecrossed);
                        if ((hexsideterrain == null) || (!hexsideterrain.isCliff() && !hexsideterrain.isBuildingTerrain() && !hexsideterrain.isWaterTerrain())) {
                            ADJList.add(new Locationc(temp1, null));
                        }
                    }
                }
            }
        }


       /* 'ElseIf hexlocation = EnumCon.Locations.Pillcellar Then ' only one possibility(tunnel ?)
        '    TempLocList = Game.Scenario.TerrainActions.GetLocationsInHex(hexnumber)
        '    For Each TempHexloc As HexAndLocHolder In TempLocList
        '        If TempHexloc.HexLocation >= EnumCon.Locations.Pill157 And TempHexloc.HexLocation <= EnumCon.Locations.Pill146 Then
        '            HexLocToAdd = New HexAndLocHolder(TempHexloc.Hexnumber, TempHexloc.HexLocation)
        '            ADJList.Add(HexLocToAdd)
        '        End If
        '    Next
        'End If
        '' need to check for trench or tunnel

        '' locations within a hex with access to other hexes, a bridge
        'If (hexlocation >= EnumCon.Locations.BrSt And hexlocation <= EnumCon.Locations.BrFt) Or hexlocation = constantclasslibrary.aslxna.location.Pier Or hexlocation = constantclasslibrary.aslxna.location.PierNoLoc Then
        '    ' only road/pier hexsides are adjacent (beneath bridge / pier is not)
        '    For i = 1 To 6
        '        Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, hexnumber)
        '        If Game.Scenario.TerrainActions.IsSideARoad(Hexsidetest) Then
        '            Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
        '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, hexlocation)
        '            ADJList.Add(HexLocToAdd)
        '        End If
        '    Next
        '    Return ADJList
        'End If
        '' lower levels -sewer / tunnel / cave
        'If hexlocation = EnumCon.Locations.Sewer Then
        '    For i = 1 To 6
        '        Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
        '        If Game.Scenario.TerrainActions.IsHexTerrainA(Adjhexnum, constantclasslibrary.aslxna.location.Manholetype) Then
        '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, EnumCon.Locations.Sewer)
        '            ADJList.Add(HexLocToAdd)
        '        End If
        '    Next
        '    Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
        '    With GetBaseHex
        '        If (CInt(!terraintype) <> constantclasslibrary.aslxna.location.RubbledWoodMan And CInt(!terraintype) <> constantclasslibrary.aslxna.location.RubbledStoneMan) Or
        '            (CInt(!terraintype) = constantclasslibrary.aslxna.location.StoneRubbleTB Or CInt(!terraintype) = constantclasslibrary.aslxna.location.WoodRubbleTB) Then
        '            If Game.Scenario.TerrainActions.IsHexTerrainA(hexnumber, constantclasslibrary.aslxna.location.Cellartype) Then
        '                Adjloc = EnumCon.Locations.Cellar
        '            Else
        '                Adjloc = CInt(!terraintype)
        '            End If
        '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, EnumCon.Locations.Sewer)
        '            ADJList.Add(HexLocToAdd)
        '        End If
        '    End With
        '    Return ADJList
        'ElseIf hexlocation = EnumCon.Locations.InCave Then

        'ElseIf hexlocation = EnumCon.Feature.Tunnel Then

        'End If
        '' building levels -upper, roof and cellar, within the hex
        'Dim Hexloclist As List(Of HexAndLocHolder) = Game.Scenario.TerrainActions.GetLocationsInHex(hexnumber)
        'If Hexloclist.Count > 1 Then  ' multiple locations exist;
        identify adjacent
        '    For Each LocToTest As HexAndLocHolder In Hexloclist
        '        If LocToTest.HexLocation = hexlocation Then ' do nothing as same location
        '        Else
        '            If Not Game.Scenario.TerrainActions.IsHexTerrainA(hexnumber, constantclasslibrary.aslxna.location.NonStairBldg) Then
        '                If IsSameHexLocationADJACENT(hexlocation, LocToTest.HexLocation) Then
        '                    HexLocToAdd = New HexAndLocHolder(hexnumber, LocToTest.HexLocation)
        '                    ' HexLocToAdd.Hexnumber = hexnumber
        '                    ' HexLocToAdd.HexLocation = LocToTest.HexLocation
        '                    ADJList.Add(HexLocToAdd)
        '                End If
        '            Else
        '                MessageBox.Show("No stairs so not ADJACENT")
        '            End If
        '        End If
        '    Next
        'End If
        '' upper and lower levels adjacent to same in other hexes
        '' pass position as zero because checking location only;
        factor in unit position later
        'If Game.Scenario.TerrainActions.GetLocationPositionLevel(hexlocation, 0) <> 0 Then
        '    For i = 1 To 6
        '        Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, hexnumber)
        '        If Hexsidetest = EnumCon.Hexside.AttachedBldg Then
        '            Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
        '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, hexlocation)
        '            ADJList.Add(HexLocToAdd)
        '        End If
        '    Next
        'End If
        'base level and terrain of hex
        'THERE SITUATIONS WHERE HEX WOULD BE NON-ADJACENT: No LOS or can' t Advance
        'pass position as zero because checking location only; factor in unit position later  - CHECK bypass
        'If Game.Scenario.TerrainActions.GetLocationPositionLevel(hexlocation, 0) = 0 Then  ' level in hex not level of
        hex
        '    For i = 1 To 6
        '        Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
        '        Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, hexnumber)
        '        ' depression check
        '        If Game.Scenario.TerrainActions.IsHexTerrainA(hexnumber, constantclasslibrary.aslxna.location.Creststatustype) And
        '        Game.Scenario.TerrainActions.IsHexTerrainA(Adjhexnum, constantclasslibrary.aslxna.location.Creststatustype) Then  '
        meaning both are depressions
        '            ' must have LOS to be ADJACENT so hexside must be depression
        '            If Not Game.Scenario.TerrainActions.IsSideADepression(Hexsidetest) Then Continue For
        '        End If
        '        ' cliff
        '        If Game.Scenario.TerrainActions.IsSideACliff(Hexsidetest) Then Continue For ' not ADJACENT across cliff
        hexsides
        '        Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(Adjhexnum)
        '        With GetBaseHex
        '            Adjloc = CInt(!terraintype)
        '        End With
        '        HexLocToAdd = New HexAndLocHolder(Adjhexnum, Adjloc)
        '        ADJList.Add(HexLocToAdd)
        '    Next
        'End If*/

        //return all locations
        return ADJList;
    }
    // FROM COMBATTERRAIN.VB - DEALS WITH ADJACENCY
    /*Public Class HexBesideC
    Private pStartHexLoc As New MapDataClassLibrary.GameLocation
    Private pTestHexloc As New MapDataClassLibrary.GameLocation
    Private pStarthexposition As Integer = 0

    Public Sub New(ByVal StartHexLoc As MapDataClassLibrary.GameLocation, ByVal TestHexLoc As MapDataClassLibrary.GameLocation,
                   ByVal starthexposition As Integer)
    pStartHexLoc = StartHexLoc
    If Not IsNothing(TestHexLoc) Then pTestHexloc = TestHexLoc
            pStarthexposition = starthexposition
    End Sub
#Region "Methods"
            'adjacent
    Public Function IshexAdjacent() As Boolean
            'determines if two hexes are adjacent - share common hexside
    Dim HexCheck As Integer = 0
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    For i = 1 To 6
    HexCheck = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    If pTestHexloc.Hexnum = HexCheck Then Return True
    Next
            'no match found so not adjacent
    Return False

    End Function
        'ADJACENT
    Public Function AllADJACENTLocations() As List(Of MapDataClassLibrary.GameLocation)
            'this function returns a list of all locations ADJACENT to a specificied location
            'don't need to check entrenchments as the are not sparate locations for ADJACENCY purposes

    Dim hexnumber As Integer = pStartHexLoc.Hexnum : Dim hexlocation As Integer = pStartHexLoc.Location
    Dim ADJList As New List(Of MapDataClassLibrary.GameLocation) ': Dim HexLocToAdd As MapDataClassLibrary.GameLocation
    Dim Adjhex As Integer = 0 : Dim Adjloc As Integer = 0 : Dim Adjhexnum As Integer = 0
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
            'look for locations in hex
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
    Dim TempLocList As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(hexnumber, "Hexnum")
    If TempLocList.Count > 1 Then
                'test if each location in the same hex is ADJACENT to the starting location
    For Each TempHexloc As MapDataClassLibrary.GameLocation In TempLocList
            pTestHexloc = TempHexloc
    If IsSameHexLocationADJACENT() Then ADJList.Add(TempHexloc)
    Next
    End If
            'look in adjacent locations in other hexes
    For i = 1 To 6
    Adjhexnum = MapGeo.DirExtent(hexnumber, i, 1)
    TempLocList = Getlocs.RetrieveLocationsfromMapTable(Adjhexnum, "Hexnum")
    If TempLocList.Count > 0 Then
    For Each TempHexloc As MapDataClassLibrary.GameLocation In TempLocList
            pTestHexloc = TempHexloc
    If AreLocationsADJACENT() Then ADJList.Add(TempHexloc)
    Next
    End If
    Next
            MessageBox.Show(ADJList.Count.ToString & " ADJACENT Locations found ")


            ''locations within a hex such as pillboxes (check for other types: bunkers) with no access to other hexes
            ''returns only locations in hex
            'If hexlocation >= EnumCon.Locations.Pill157 And hexlocation <= EnumCon.Locations.Pill146 Then
                    '    TempLocList = Game.Scenario.TerrainActions.GetLocationsInHex(hexnumber)
                    '    For Each TempHexloc As HexAndLocHolder In TempLocList
                    '        If TempHexloc.HexLocation <= EnumCon.Locations.Pill157 Or TempHexloc.HexLocation >= EnumCon.Locations.Pill146 Then 'no need to include current location
            '            HexLocToAdd = New HexAndLocHolder(TempHexloc.Hexnumber, TempHexloc.HexLocation)
                    '            ADJList.Add(HexLocToAdd)
                    '        End If
                    '    Next
                    'ElseIf hexlocation = EnumCon.Locations.Pillcellar Then 'only one possibility (tunnel?)
            '    TempLocList = Game.Scenario.TerrainActions.GetLocationsInHex(hexnumber)
                    '    For Each TempHexloc As HexAndLocHolder In TempLocList
                    '        If TempHexloc.HexLocation >= EnumCon.Locations.Pill157 And TempHexloc.HexLocation <= EnumCon.Locations.Pill146 Then
                    '            HexLocToAdd = New HexAndLocHolder(TempHexloc.Hexnumber, TempHexloc.HexLocation)
                    '            ADJList.Add(HexLocToAdd)
                    '        End If
                    '    Next
                    'End If
                    ''need to check for trench or tunnel

            ''locations within a hex with access to other hexes, a bridge
            'If (hexlocation >= EnumCon.Locations.BrSt And hexlocation <= EnumCon.Locations.BrFt) Or hexlocation = constantclasslibrary.aslxna.location.Pier Or hexlocation = constantclasslibrary.aslxna.location.PierNoLoc Then
                    '    'only road/pier hexsides are adjacent (beneath bridge/pier is not)
            '    For i = 1 To 6
                    '        Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, hexnumber)
                    '        If Game.Scenario.TerrainActions.IsSideARoad(Hexsidetest) Then
                    '            Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
                    '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, hexlocation)
                    '            ADJList.Add(HexLocToAdd)
                    '        End If
                    '    Next
                    '    Return ADJList
                    'End If
                    ''lower levels - sewer/tunnel/cave
            'If hexlocation = EnumCon.Locations.Sewer Then
                    '    For i = 1 To 6
                    '        Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
                    '        If Game.Scenario.TerrainActions.IsHexTerrainA(Adjhexnum, constantclasslibrary.aslxna.location.Manholetype) Then
                    '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, EnumCon.Locations.Sewer)
                    '            ADJList.Add(HexLocToAdd)
                    '        End If
                    '    Next
                    '    Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
                    '    With GetBaseHex
                    '        If (CInt(!terraintype) <> constantclasslibrary.aslxna.location.RubbledWoodMan And CInt(!terraintype) <> constantclasslibrary.aslxna.location.RubbledStoneMan) Or
                    '            (CInt(!terraintype) = constantclasslibrary.aslxna.location.StoneRubbleTB Or CInt(!terraintype) = constantclasslibrary.aslxna.location.WoodRubbleTB) Then
                    '            If Game.Scenario.TerrainActions.IsHexTerrainA(hexnumber, constantclasslibrary.aslxna.location.Cellartype) Then
                    '                Adjloc = EnumCon.Locations.Cellar
                    '            Else
                    '                Adjloc = CInt(!terraintype)
                    '            End If
                    '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, EnumCon.Locations.Sewer)
                    '            ADJList.Add(HexLocToAdd)
                    '        End If
                    '    End With
                    '    Return ADJList
                    'ElseIf hexlocation = EnumCon.Locations.InCave Then

                    'ElseIf hexlocation = EnumCon.Feature.Tunnel Then

                    'End If
                    ''building levels - upper, roof and cellar, within the hex
            'Dim Hexloclist As List(Of HexAndLocHolder) = Game.Scenario.TerrainActions.GetLocationsInHex(hexnumber)
                    'If Hexloclist.Count > 1 Then  'multiple locations exist; identify adjacent
            '    For Each LocToTest As HexAndLocHolder In Hexloclist
                    '        If LocToTest.HexLocation = hexlocation Then 'do nothing as same location
            '        Else
                    '            If Not Game.Scenario.TerrainActions.IsHexTerrainA(hexnumber, constantclasslibrary.aslxna.location.NonStairBldg) Then
                    '                If IsSameHexLocationADJACENT(hexlocation, LocToTest.HexLocation) Then
                    '                    HexLocToAdd = New HexAndLocHolder(hexnumber, LocToTest.HexLocation)
                    '                    'HexLocToAdd.Hexnumber = hexnumber
            '                    'HexLocToAdd.HexLocation = LocToTest.HexLocation
            '                    ADJList.Add(HexLocToAdd)
                    '                End If
                    '            Else
                    '                MessageBox.Show("No stairs so not ADJACENT")
                    '            End If
                    '        End If
                    '    Next
                    'End If
                    ''upper and lower levels adjacent to same in other hexes
            ''pass position as zero because checking location only; factor in unit position later
            'If Game.Scenario.TerrainActions.GetLocationPositionLevel(hexlocation, 0) <> 0 Then
                    '    For i = 1 To 6
                    '        Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, hexnumber)
                    '        If Hexsidetest = EnumCon.Hexside.AttachedBldg Then
                    '            Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
                    '            HexLocToAdd = New HexAndLocHolder(Adjhexnum, hexlocation)
                    '            ADJList.Add(HexLocToAdd)
                    '        End If
                    '    Next
                    'End If
                    'base level and terrain of hex
                    'THERE SITUATIONS WHERE HEX WOULD BE NON-ADJACENT: No LOS or can't Advance
            'pass position as zero because checking location only; factor in unit position later  - CHECK bypass
                    'If Game.Scenario.TerrainActions.GetLocationPositionLevel(hexlocation, 0) = 0 Then  'level in hex not level of hex
            '    For i = 1 To 6
                    '        Adjhexnum = Mapgeo.RangeC.DirExtent(hexnumber, i, 1)
                    '        Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, hexnumber)
                    '        'depression check
            '        If Game.Scenario.TerrainActions.IsHexTerrainA(hexnumber, constantclasslibrary.aslxna.location.Creststatustype) And
                    '        Game.Scenario.TerrainActions.IsHexTerrainA(Adjhexnum, constantclasslibrary.aslxna.location.Creststatustype) Then  'meaning both are depressions
            '            'must have LOS to be ADJACENT so hexside must be depression
            '            If Not Game.Scenario.TerrainActions.IsSideADepression(Hexsidetest) Then Continue For
                    '        End If
                    '        'cliff
            '        If Game.Scenario.TerrainActions.IsSideACliff(Hexsidetest) Then Continue For 'not ADJACENT across cliff hexsides
            '        Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(Adjhexnum)
                    '        With GetBaseHex
                    '            Adjloc = CInt(!terraintype)
                    '        End With
                    '        HexLocToAdd = New HexAndLocHolder(Adjhexnum, Adjloc)
                    '        ADJList.Add(HexLocToAdd)
                    '    Next
                    'End If
                    'return all locations
    Return ADJList
    End Function
    Public Function IsSameHexLocationADJACENT() As Boolean
            'called by AreLocationsADJACENT
                    'checks if two location in same hex are ADJACENT
    If pStartHexLoc.LocIndex = pTestHexloc.LocIndex Then Return False 'same location and therefore NOT ADJACENT - is this right?
            'Dim Getbasehex As DataRow = Game.Scenario.MapTables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(LocationCol)
            'if one location is pillbox, other location must be baseterrain, cellar for ADJACENCY
    If pStartHexLoc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pStartHexLoc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then 'location is the pillbox
            'match with other location in hex, pillbox cellar both of which are ADJACENT
    If pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.BunkUnder Then Return True
                'Dim Testloc As DataRow = Game.Scenario.MapTables.RetrieveHexfromDatatable(pTestHexLoc.Hexnum)
    If LevelChk.GetLevelofLocation(pTestHexloc.LocIndex) = 0 Then Return True
                'If pTestHexLoc.Location = CInt(Testloc!terraintype) Then Return True
    ElseIf pTestHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pTestHexloc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then 'location is the pillbox
            'match with other location in hex, pillbox cellar both of which are ADJACENT
    If pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.BunkUnder Then Return True
    If LevelChk.GetLevelofLocation(pStartHexLoc.LocIndex) = 0 Then Return True
                'Dim Testloc As DataRow = Game.Scenario.MapTables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
                        'If pStartHexLoc.Location = CInt(Testloc!terraintype) Then Return True
    ElseIf pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.BunkUnder Then 'only one possibility; other must be the pillbox
    If pTestHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pTestHexloc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then Return True Else Return False
    ElseIf pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.BunkUnder Then 'only one possibility; other must be the pillbox
    If pStartHexLoc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pStartHexLoc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then Return True Else Return False
    End If
            'lower levels - sewer/tunnel/cave
    If pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
                'only cellar or baselevel can be ADJACENT
    If IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Manholetype) Then
    If pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.Cellar Then Return True 'other is cellar
    If Not IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Cellartype) Then
                        'other is non-cellar man - must be ground level (0) in hex
    If LevelChk.GetLevelofLocation(pTestHexloc.LocIndex) = 0 Then Return True
                        'If pTestHexLoc.Location = CInt(Getbasehex!terraintype) Then Return True
    End If
    Return False 'no match so not ADJACENT
    ElseIf pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.InCave Then


    Return False 'no match so not ADJACENT
    ElseIf pStartHexLoc.Location = Feature.Tunnel Then


    Return False 'no match so not ADJACENT
    End If
    ElseIf pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
                'only cellar or baselevel can be ADJACENT
    If IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Manholetype) Then
    If pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.Cellar Then Return True 'other is cellar
    If Not IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Cellartype) Then
                        'other is non-cellar man so must be at ground level (0) in hex
    If LevelChk.GetLevelofLocation(pStartHexLoc.LocIndex) = 0 Then Return True
                        'If pStartHexLoc.Location = CInt(Getbasehex!terraintype) Then Return True
    End If
    End If
    Return False 'no match so not ADJACENT
    ElseIf pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.InCave Then


    Return False 'no match so not ADJACENT
    ElseIf pTestHexloc.Location = Feature.Tunnel Then


    Return False 'no match so not ADJACENT

    End If
            'bridge locations are never ADJACENT within a hex as there is no LOS to beneath the bridge

                    'building levels - upper, roof and cellar, within the hex
                    'confirm is building with stairs, otherwise no ADJACENCY possible
    If IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Buildingtype) And
    Not IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.NonStairBldg) Then
                'passes position as "0" because position does not impact ADJACENCY
    Dim Startlevel As Single = LevelChk.GetLevelofLocation(pStartHexLoc.LocIndex) 'Game.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0)
    Dim LocChecklevel As Single = LevelChk.GetLevelofLocation(pTestHexloc.LocIndex) 'Game.Scenario.TerrainActions.GetLocationPositionLevel(pTestHexLoc.Location, 0)
            'if one level apart then are ADJACENT in staircase hex
    If Math.Abs(Startlevel - LocChecklevel) <= 1 Then Return True Else Return False 'incorporates half-level difference for roof
    End If
            'should never get here as above covers all location combinations
    Return False
    End Function
    Public Function AreLocationsADJACENT() As Boolean
            'called by
                    'determines if two Locations are ADJACENT - meaning that they have an LOS to each other and can move from one to another in AdPh
                    'do position impact outside of this routine (ie in a foxhole behind a wall/hedge, above wire, passenger/rider, crest status which gives LOS)
    Dim TestHexNum As Integer = 0 ': Dim GetBasehex As DataRow
    Dim Testloc As MapDataClassLibrary.GameLocation
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(LocationCol)
            'Make sure they are not the same location; if so not ADJACENT
    If pStartHexLoc.Hexnum = pTestHexloc.Hexnum And pStartHexLoc.Location = pTestHexloc.Location Then Return False
            'both locations within the same hex such as pillboxes or levels
    If pStartHexLoc.Hexnum = pTestHexloc.Hexnum Then
    If IsSameHexLocationADJACENT() Then Return True Else Return False
    End If
    Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(LocationCol)
            'locations in separate hexes
            'locations within a hex with access to other hexes, a bridge
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    If (pStartHexLoc.Location >= ConstantClassLibrary.ASLXNA.Location.StBr14 And pStartHexLoc.Location <= ConstantClassLibrary.ASLXNA.Location.FoBr) Or
    pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.Pier Or pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.PierNoLoc Then
                'only road/pier hexsides are ADJACENT (beneath bridge/pier is not)
    For i = 1 To 6
            'get hexside
            'Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pStartHexLoc.Hexnum)
            'if hexside is a road
    If Not (SideTest.IsARoad(i, pStartHexLoc.LocIndex)) Then '(hexsidetype, Game.Scenario.Maptables)) Then
            'get test hex
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
            'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
    Testloc = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
            'if testhex location is base hex terrain then ADJACENT
    If pTestHexloc.Hexnum = TestHexNum And pTestHexloc.Location = CInt(Testloc.Location) Then Return True
    End If
    Next
    Return False 'no match so not ADJACENT
    ElseIf (pTestHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.StBr14 And pTestHexloc.Location <= ConstantClassLibrary.ASLXNA.Location.FoBr) Or
    pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.Pier Or pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.PierNoLoc Then
                'only road/pier hexsides are ADJACENT (beneath bridge/pier is not)
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'get hexside
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pTestHexloc.Hexnum)
                    'if hexside is a road
    If SideTest.IsARoad(i, pTestHexloc.LocIndex) Then
                        'get test hex
    TestHexNum = MapGeo.DirExtent(pTestHexloc.Hexnum, i, 1)
    Testloc = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
            'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
            'if testhex location is base hex terrain then ADJACENT
    If pStartHexLoc.Hexnum = TestHexNum And pStartHexLoc.Location = CInt(Testloc.Location) Then Return True
    End If
    Next
    Return False 'no match so not ADJACENT
    End If
            'lower levels - sewer/tunnel/cave
    If pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
    For i = 1 To 6
            'ADJACENT sewer test; must check for enemy in calling routine
            'get adjacent hex
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
            'if it is a manhole
    Dim LocFound As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
    If IsTerrChk.IsLocationTerrainA(TestHexNum, LocFound.Location, ConstantClassLibrary.ASLXNA.Location.Manholetype) Then
                        'is location a sewer
    If pTestHexloc.Hexnum = TestHexNum And pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then Return True
    End If
    Next
    Return False 'no match so not ADJACENT
            'don't need to start with Testloc as Sewer because only ADJACENT if start is sewer so already done
    ElseIf pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.InCave Then


    Return False 'no match so not ADJACENT
    ElseIf pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Feature.Tunnel Then


    Return False 'no match so not ADJACENT
    End If
            'Pillbox
    Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(LocationCol)
    If pStartHexLoc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pStartHexLoc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
                'get pillbox location in start hex
    Dim TerrGet = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
    Testloc = TerrGet.RetrieveLocationfromMaptable(pStartHexLoc.LocIndex)
    Dim GetBasehex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(pStartHexLoc.Hexnum, 0)
    Dim Imagename As String = TerrChk.GetLocationData(TerrFactor.Image, CInt(Testloc.Location), Maptables)
                'get adjacent covered arc hexes
    Dim CAHexes As List(Of HexAndLocHolder) = GetCoveredArcAdjacentHexes(Imagename)
    For Each CAHexloc As HexAndLocHolder In CAHexes
                    'if covered arc hex matches test hex then test hex is ADJACENT - unless across cliff hexside or other barriers
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    Dim Hexsidecrossed As Integer = MapGeo.HexSideEntry(CAHexloc.Hexnumber, pStartHexLoc.Hexnum)
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(Hexsidecrossed, pStartHexLoc.Hexnum)
                    'depression check
    If IsTerrChk.IsLocationTerrainA(GetBasehex.Hexnum, GetBasehex.Location, ConstantClassLibrary.ASLXNA.Location.Creststatustype) And
                    IsTerrChk.IsLocationTerrainA(CAHexloc.Hexnumber, CAHexloc.HexLocation, ConstantClassLibrary.ASLXNA.Location.Creststatustype) Then
                        'both are depressions, must have LOS to be ADJACENT so hexside must be depression OR hexposition must be crest
    If Not (SideTest.IsADepression(Hexsidetest)) And Not ((pStarthexposition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And
            pStarthexposition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6) Or (pStarthexposition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And
    pStarthexposition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)) Then Continue For
    End If
                    'cliff check
    If SideTest.IsACliff(Hexsidetest) Then Continue For 'not ADJACENT across cliff hexsides
            'building wall check
    If Hexsidetest = Hexside.IntBldgWall Or Hexsidetest = Hexside.IntFactside Or
            Hexsidetest = Hexside.Rowhouseside Then Continue For 'not ADJACENT across building wall hexsides
            'waterobstaclecheck -not ADJACENT because can't advance into
    If IsTerrChk.IsLocationTerrainA(CAHexloc.Hexnumber, CAHexloc.HexLocation, ConstantClassLibrary.ASLXNA.Location.WaterObstacletype) And
    Not IsTerrChk.IsLocationTerrainA(CAHexloc.Hexnumber, CAHexloc.HexLocation, ConstantClassLibrary.ASLXNA.Location.bridgetype) Then
    Return False
    End If
    If pTestHexloc.Hexnum = CAHexloc.Hexnumber And pTestHexloc.Location = CAHexloc.HexLocation Then Return True
    Next
                'Bunker - accessible not ADJACENT
                        'Dim TestFeature As ScenarioTerrain = (From qu In ScenFeatcol Where qu.FeatureType = EnumCon.Feature.Trench And qu.Hexnumber = pTestHexLoc.Hexnum).First
                        'If Not IsNothing(TestFeature) Then Return True
    ElseIf pTestHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pTestHexloc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
                'get pillbox location in target hex
    Dim TerrGet = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
    Testloc = TerrGet.RetrieveLocationfromMaptable(pTestHexloc.LocIndex)
            'Dim TestLoc As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(pTestHexloc.Hexnum, 0)
            ''GetBasehex = Game.Scenario.MapTables.RetrieveHexfromDatatable(pTestHexLoc.Hexnum)
    Dim Imagename As String = TerrChk.GetLocationData(TerrFactor.Image, CInt(Testloc.Location), Maptables)
                'get adjacent covered arc hexes
    Dim CAHexes As List(Of HexAndLocHolder) = GetCoveredArcAdjacentHexes(Imagename)
    For Each CAHexloc As HexAndLocHolder In CAHexes
                    'if covered arc hex matches test hex then test hex is ADJACENT
    If pStartHexLoc.Hexnum = CAHexloc.Hexnumber And pStartHexLoc.Location = CAHexloc.HexLocation Then Return True
    Next
    End If
            'building levels - upper, roof and cellar,
    If IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Buildingtype) Then
                'upper and lower levels adjacent to same in other hexes
                        'pass position as zero because checking location only; factor in unit position later
    If LevelChk.GetLevelofLocation(pStartHexLoc.LocIndex) <> 0 Then 'Game.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0) <> 0 Then
            'comparing non-ground levels
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'get hexside
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pStartHexLoc.Hexnum)
                        'if attached building
    If Hexsidetest = Hexside.AttachedBldg Then
                            'get test hex
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    If pTestHexloc.Hexnum = TestHexNum Then
                                'if matches other hex then proceed
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
    Dim Testloclist As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(TestHexNum, "Hexnum")
            'loop through location in test hex
    For Each TestLocToTest As MapDataClassLibrary.GameLocation In Testloclist
                                    'if same total level in each hex then ADJACENT
    If TestLocToTest.Location = pTestHexloc.Location Then
                                        'test this location
                                                'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
                                                'Dim StartBasehex As DataRow = Game.Scenario.Maptables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
    Dim TotalStartLevel As Single = LevelChk.GetTotalLocationLevel(pStartHexLoc.Hexnum, pStartHexLoc.Location) 'Game.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0) + CInt(StartBasehex!baselevel)
    Dim TotalOtherlevel As Single = LevelChk.GetTotalLocationLevel(TestHexNum, TestLocToTest.Location) '+ CInt(GetBasehex!baselevel)
    If TotalStartLevel = TotalOtherlevel Then Return True
                                        'can be different building types (fortified and not) so need to use level test
                                                'total level allows for split level buidlings
    End If
    Next
    End If
    End If
    Next
    End If

    End If
            'base level and terrain of hex
                    'pass position as zero because checking location only; factor in unit position later
    If LevelChk.GetLevelofLocation(pStartHexLoc.LocIndex) = 0 Then '.gameform.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0) = 0 Then  'level in hex not level of hex
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'loop through adjacent hexes
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    If TestHexNum = 0 Then Continue For 'handles cases where no hex found, such as board-edge
    Testloc = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
    If pTestHexloc.Hexnum = TestHexNum Then
                        'if matches then other hex is adjacent and can proceed
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pStartHexLoc.Hexnum)
                        'depression check
    If IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Creststatustype) And
                        IsTerrChk.IsLocationTerrainA(TestHexNum, Testloc.Location, ConstantClassLibrary.ASLXNA.Location.Creststatustype) Then
                            'both are depressions, must have LOS to be ADJACENT so hexside must be depression OR hexposition must be crest
    If ((SideTest.IsADepression(Hexsidetest)) And Not ((pStarthexposition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And
            pStarthexposition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6)) Or (Not SideTest.IsADepression(Hexsidetest) AndAlso (pStarthexposition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And
    pStarthexposition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6))) Then Continue For
    End If
                        'cliff check
    If SideTest.IsACliff(Hexsidetest) Then Continue For 'not ADJACENT across cliff hexsides
            'building wall check
    If Hexsidetest = Hexside.IntBldgWall Or Hexsidetest = Hexside.IntFactside Or
            Hexsidetest = Hexside.Rowhouseside Then Continue For 'not ADJACENT across building wall hexsides
            'waterobstaclecheck -not ADJACENT because can't advance into
    If IsTerrChk.IsLocationTerrainA(TestHexNum, Testloc.Location, ConstantClassLibrary.ASLXNA.Location.WaterObstacletype) And
    Not IsTerrChk.IsLocationTerrainA(TestHexNum, Testloc.Location, ConstantClassLibrary.ASLXNA.Location.bridgetype) Then
                            'can't place DC as NOT ADJACENT
    Return False
    End If
                        'get base terrain for both hexes
    Dim BaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
    Dim StartBaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(pStartHexLoc.Hexnum, 0)
            'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
            'Dim StartBasehex As DataRow = Game.Scenario.Maptables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
            'base hexes in adjacent hexes = ADJACENT
    If pStartHexLoc.Location = CInt(StartBaseTest.Location) And pTestHexloc.Location = CInt(BaseTest.Location) Then Return True
    End If
    Next
    Return False 'if here, no match and not ADJACENT
    End If
            'CHECK bypass
                    'ANYTHING ELSE?
    Return False 'no match so not ADJACENT
    End Function
        'accessible
    Public Function AreLocationsAccessible() As Boolean
            'called by
                    'determines if two Locations are Accessible - meaning that units can move from one to another in AdPh (no LOS required)
                    'do position impact outside of this routine (ie above wire, passenger/rider)
    Dim TestHexNum As Integer = 0 ': Dim GetBasehex As DataRow
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
            'Make sure they are not the same location; if so not Accessible
    If pStartHexLoc.Hexnum = pTestHexloc.Hexnum And pStartHexLoc.Location = pTestHexloc.Location Then Return False
            'both locations within the same hex such as pillboxes or levels
    If pStartHexLoc.Hexnum = pTestHexloc.Hexnum Then
    If IsSameHexLocationADJACENT() Then Return True Else Return False
    End If
    Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(LocationCol)
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(LocationCol)
            'locations in separate hexes
            'locations within a hex with access to other hexes, a bridge
    If (pStartHexLoc.Location >= ConstantClassLibrary.ASLXNA.Location.StBr14 And pStartHexLoc.Location <= ConstantClassLibrary.ASLXNA.Location.FoBr) Or
    pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.Pier Or pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.PierNoLoc Then
                'only road/pier hexsides are accessible (beneath bridge/pier is not)
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'get hexside
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pStartHexLoc.Hexnum)
                    'if hexside is a road
    If SideTest.IsARoad(i, pStartHexLoc.LocIndex) Then
                        'get test hex
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    Dim BaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
            'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
            'if testhex location is base hex terrain then accessible
    If pTestHexloc.Hexnum = TestHexNum And pTestHexloc.Location = CInt(BaseTest.Location) Then Return True
    End If
    Next
    Return False 'no match so not Accessible
    ElseIf (pTestHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.StBr14 And pTestHexloc.Location <= ConstantClassLibrary.ASLXNA.Location.FoBr) Or
    pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.Pier Or pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.PierNoLoc Then
                'only road/pier hexsides are accessible (beneath bridge/pier is not)
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'get hexside
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pTestHexloc.Hexnum)
                    'if hexside is a road
    If SideTest.IsARoad(i, pTestHexloc.LocIndex) Then
                        'get test hex
    TestHexNum = MapGeo.DirExtent(pTestHexloc.Hexnum, i, 1)
    Dim BaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
            'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
            'if testhex location is base hex terrain then accessible
    If pStartHexLoc.Hexnum = TestHexNum And pStartHexLoc.Location = CInt(BaseTest.Location) Then Return True
    End If
    Next
    Return False 'no match so not accessible
    End If
            'lower levels - sewer/tunnel/cave
    If pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
    For i = 1 To 6
            'accessible sewer test; must check for enemy in calling routine
            'get adjacent hex
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    Dim BaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
            'if it is a manhole
    If IsTerrChk.IsLocationTerrainA(TestHexNum, BaseTest.Location, ConstantClassLibrary.ASLXNA.Location.Manholetype) Then
                        'is location a sewer
    If pTestHexloc.Hexnum = TestHexNum And pTestHexloc.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then Return True
    End If
    Next
    Return False 'no match so not Accessible
            'don't need to start with Testloc as Sewer because only accessible if start is sewer so already done
    ElseIf pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Location.InCave Then


    Return False 'no match so not Accessible
    ElseIf pStartHexLoc.Location = ConstantClassLibrary.ASLXNA.Feature.Tunnel Then


    Return False 'no match so not Accessible
    End If
            'Pillbox
    If pStartHexLoc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pStartHexLoc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
                'don't need to do this here as adjacenet hexes ONLY accessible if Bunker
                ''get hex
                'GetBasehex = Game.Scenario.MapTables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
                        ''get adjacent covered arc hexes
                'Dim CAHexes As List(Of HexAndLocHolder) = GetCoveredArcAdjacentHexes(CStr(GetBasehex!image))
                        'For Each CAHexloc As HexAndLocHolder In CAHexes
                        '    'if covered arc hex matches test hex then test hex is accessible
                '    If pTestHexLoc.Hexnum = CAHexloc.Hexnumber And pTestHexLoc.Location = CAHexloc.HexLocation Then Return True
                        'Next
                        'Bunker test - accessible
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    Dim TestFeature As DataClassLibrary.ScenarioTerrain = (From qu In Linqdata.ScenFeatcol Where qu.FeatureType = ConstantClassLibrary.ASLXNA.Feature.Trench And qu.Hexnumber = pTestHexloc.Hexnum).First
    If Not IsNothing(TestFeature) Then Return True
    ElseIf pTestHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And pTestHexloc.Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
                'Bunker test - accessible
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    Dim TestFeature As DataClassLibrary.ScenarioTerrain = (From qu In Linqdata.ScenFeatcol Where qu.FeatureType = ConstantClassLibrary.ASLXNA.Feature.Trench And qu.Hexnumber = pStartHexLoc.Hexnum).First
    If Not IsNothing(TestFeature) Then Return True
    End If
            'building levels - upper, roof and cellar,
    If IsTerrChk.IsLocationTerrainA(pStartHexLoc.Hexnum, pStartHexLoc.Location, ConstantClassLibrary.ASLXNA.Location.Buildingtype) Then
                'upper and lower levels adjacent to same in other hexes
                        'pass position as zero because checking location only; factor in unit position later
    If LevelChk.GetLevelofLocation(pStartHexLoc.Hexnum) <> 0 Then 'Game.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0) <> 0 Then
            'comparing non-ground levels
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'get hexside
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pStartHexLoc.Hexnum)
                        'if attached building
    If Hexsidetest = Hexside.AttachedBldg Then
                            'get test hex
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    If pTestHexloc.Hexnum = TestHexNum Then
                                'if matches other hex then proceed
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
    Dim Testloclist As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(TestHexNum, "Hexnum")
            'Dim Testloclist As List(Of HexAndLocHolder) = Game.Scenario.TerrainActions.GetLocationsInHex(TestHexNum)
            'loop through location in test hex
    For Each TestLocToTest As MapDataClassLibrary.GameLocation In Testloclist
                                    'if same total level in each hex then accessible
    If TestLocToTest.Location = pTestHexloc.Location Then
                                        'test this location
                                                'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
                                                'Dim StartBasehex As DataRow = Game.Scenario.Maptables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
                                                'pass position as zero because checking location only; factor in unit position later
                                                'Dim TotalStartLevel As Single = Game.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0) + CInt(StartBasehex!baselevel)
                                                'Dim TotalOtherlevel As Single = Game.Scenario.TerrainActions.GetLocationPositionLevel(TestLocToTest.HexLocation, 0) + CInt(GetBasehex!baselevel)
    Dim TotalStartLevel As Single = LevelChk.GetTotalLocationLevel(pStartHexLoc.Hexnum, pStartHexLoc.Location)
    Dim TotalOtherlevel As Single = LevelChk.GetTotalLocationLevel(TestHexNum, TestLocToTest.Location)
    If TotalStartLevel = TotalOtherlevel Then Return True
                                        'can be different building types (fortified and not) so need to use level test
                                                'total level allows for split level buidlings
    End If
    Next
    End If
    End If
    Next
    End If

    End If
            'base level and terrain of hex
                    'pass position as zero because checking location only; factor in unit position outside of this routine
    If LevelChk.GetLevelofLocation(pStartHexLoc.Hexnum) = 0 Then 'Game.Scenario.TerrainActions.GetLocationPositionLevel(pStartHexLoc.Location, 0) = 0 Then  'level in hex not level of hex
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
    For i = 1 To 6
            'loop through adjacent hexes
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, i, 1)
    If pTestHexloc.Hexnum = TestHexNum Then
                        'if matches then other hex is adjacent and can proceed
    Dim Hexsidetest As Integer = Linqdata.Gethexsidetype(i, pStartHexLoc.Hexnum)
                        'depression check - not needed for accessible
                                'If Game.Scenario.TerrainActions.IsHexTerrainA(pStartHexLoc.Hexnum, constantclasslibrary.aslxna.location.Creststatustype) And
                                'Game.Scenario.TerrainActions.IsHexTerrainA(TestHexNum, constantclasslibrary.aslxna.location.Creststatustype) Then
                                '    'both are depressions, must have LOS to be ADJACENT so hexside must be depression
                        '    If Not Game.Scenario.TerrainActions.IsSideADepression(Hexsidetest) Then Continue For
                                'End If
                                'cliff check
    If SideTest.IsACliff(Hexsidetest) Then Continue For 'not ADJACENT across cliff hexsides
            'building wall check
    If Hexsidetest = Hexside.IntBldgWall Or Hexsidetest = Hexside.IntFactside Or
            Hexsidetest = Hexside.Rowhouseside Then Continue For 'not ADJACENT across building wall hexsides
            'get base terrain for both hexes
            'GetBasehex = Game.Scenario.Maptables.RetrieveHexfromDatatable(TestHexNum)
            'Dim StartBasehex As DataRow = Game.Scenario.Maptables.RetrieveHexfromDatatable(pStartHexLoc.Hexnum)
    Dim BaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
    Dim StartBaseTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(pStartHexLoc.Hexnum, 0)
            'base hexes in adjacent hexes = ADJACENT
    If pStartHexLoc.Location = CInt(StartBaseTest.Location) And pTestHexloc.Location = CInt(BaseTest.Location) Then Return True
    Exit For
    End If
    Next
    Return False 'if here, no match and not ADJACENT
    End If
            'CHECK bypass
                    'ANYTHING ELSE?
    Return False 'no match so not ADJACENT
    End Function
    Private Function GetCoveredArcAdjacentHexes(ByVal Terrainimage As String) As List(Of HexAndLocHolder)
    Dim Hexloclist As New List(Of HexAndLocHolder)
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim Basehex As MapDataClassLibrary.GameLocation
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
    Dim TestCA As DataClassLibrary.LookupCA = (From Qu In Linqdata.db.LookupCAs Where Qu.Terraindesc = Terrainimage).First
    Dim TestHexNum As Integer = MapGeo.DirExtent(pStartHexLoc.Hexnum, TestCA.FirstCA, 1)
    Basehex = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
    Dim AddHexandLoc As New HexAndLocHolder(TestHexNum, CInt(Basehex.Location))
            Hexloclist.Add(AddHexandLoc)
    TestHexNum = MapGeo.DirExtent(pStartHexLoc.Hexnum, TestCA.SecondCA, 1)
    Basehex = LevelChk.GetLocationatLevelInHex(TestHexNum, 0)
    AddHexandLoc = New HexAndLocHolder(TestHexNum, CInt(Basehex.Location))
            Hexloclist.Add(AddHexandLoc)
    Return Hexloclist
    End Function
#End Region
    End Class*/
}
