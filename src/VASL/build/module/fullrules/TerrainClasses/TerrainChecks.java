package VASL.build.module.fullrules.TerrainClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.VASLGameInterface;
import VASL.LOS.counters.Smoke;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;
//import VASL.build.module.fullrules.MapDataClasses.LocationType;
import VASL.build.module.fullrules.MapDataClasses.MapDataC;
import VASL.build.module.fullrules.ObjectClasses.SmokeHolder;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class TerrainChecks {

    private LinkedList<GameLocation> MapData = new LinkedList<GameLocation>();

    public TerrainChecks() {

    }


    public boolean IsSmokeAllowed(int Hexnumber) {
        /*'called by ContextBuilder.MenuFilter
        'determines if smoke can be placed in the hex
        'gets all locations in the hex
        Dim GetLocs
        As New

        GetALocationFromMapTable(MapData)

        Dim MapHexLocs

        As IQueryable (Of MapDataClassLibrary.GameLocation) =GetLocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
        'checks if any prevent smoke placement (water obstacles or wet terrain)
        For Each
        LocationtoTest As
        MapDataClassLibrary.GameLocation In
        MapHexLocs
        If LocationtoTest.Location =
                ConstantClassLibrary.ASLXNA.Location.Pond Or
        LocationtoTest.Location =
                ConstantClassLibrary.ASLXNA.Location.River Or
        LocationtoTest.Location =
                ConstantClassLibrary.ASLXNA.Location.Ocean Or
        LocationtoTest.Location =
                ConstantClassLibrary.ASLXNA.Location.Canal Or
        LocationtoTest.Location =
                ConstantClassLibrary.ASLXNA.Location.Marsh Or
        LocationtoTest.Location =
                ConstantClassLibrary.ASLXNA.Location.Swamp Then
        return False
        End If
        Next*/
        return true;
    }

    public boolean AdjSmokeOK(int StartHexnumber, int SideCheck, int FinishHexNumber, int WindSpeed, int WindHexGrain) {
        /*'called by ContextBuilder.MenuFilter
        'determines if smoke can be placed on adjacent hex/location by doing various tests

        'get data
        Dim Startlevel
        As Single :
        Dim FinishLevel
        As Single
        Dim Levelchk
        As New

        LevelChecks(MapData)

        Dim StartMapHex
        As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(StartHexnumber, 0)
        Startlevel =

                CSng(StartMapHex.Baselevel)
        'FinishHexNumber = Mapgeo.RangeC.DirExtent(StartHexnumber, SideCheck, 1)
        Dim FinishMapHex
        As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(FinishHexNumber, 0)
        FinishLevel =

                CInt(FinishMapHex.Baselevel)
        'check the hex type
        If Not

        IsSmokeAllowed(FinishHexNumber) Then return False
        'level difference
        If FinishLevel -Startlevel >= 2 Then return False
        'mild breeze impacts - heavy winds and gusts checked in MenuFilter
        If WindSpeed = ConstantClassLibrary.ASLXNA.Wind.MildBreeze
        Then
        If SideCheck = WindHexGrain
        Or SideCheck = WindHexGrain - 1
        Or
                SideCheck = WindHexGrain + 1
        Then return False
        End If*/
        return true;
    }

    public boolean AreLocationsSame(int Firstlocation, int Secondlocation, int Firstposition, int secondposition, int hexnum, int Activity) {
        /*'called by Movementc.IsEligibleToMove
        'determines if two locations in a hex are considered to be the same - answer varies by activity being done and position of objects

        'if locations are different then not the same
        If Firstlocation = Secondlocation
        Then return True
        'retrieve hex data
        Dim hexTerrainType
        As Integer
        Dim LevelChk = New

        LevelChecks(MapData)

        Dim Terrhex
        As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        hexTerrainType = Terrhex.Location
        'now check cases where actually the same even if not literally
        Select Case
        Activity
        Case ConstantClassLibrary.ASLXNA.MovementStatus.Moving, ConstantClassLibrary.ASLXNA.MovementStatus.Connecting
        If Firstlocation = ConstantClassLibrary.ASLXNA.Feature.Foxhole
        And Secondlocation = hexTerrainType
        Then return True
        If Firstlocation = hexTerrainType
        And Secondlocation = ConstantClassLibrary.ASLXNA.Feature.Foxhole
        Then return True
        'If Firstlocation = AltPos.UnderWire And Secondlocation = hexTerrainType Then return True
        If Firstlocation = hexTerrainType
        And Secondlocation = ConstantClassLibrary.ASLXNA.AltPos.AboveWire
        Then return True
        If Firstlocation = ConstantClassLibrary.ASLXNA.AltPos.AboveWire
        And Secondlocation = hexTerrainType
        Then return True
        If Firstlocation = ConstantClassLibrary.ASLXNA.Feature.Trench
        And Secondlocation = hexTerrainType
        Then return True
        If Firstlocation = hexTerrainType
        And Secondlocation = ConstantClassLibrary.ASLXNA.Feature.Trench
        Then return True
        If Firstlocation = hexTerrainType
        And Secondlocation = Terrhex.OtherTerraininLocation
        Then return True
         If Secondlocation = hexTerrainType
        And Firstlocation = Terrhex.OtherTerraininLocation
        Then return True
        return False 'all other cases involve separate locations.
        End Select*/
        return true;
    }

    public Constantvalues.Location getLocationtypefromVASLLocation(Location SeeLOSLoc){
        ConversionC DoConversion = new ConversionC();
        return DoConversion.getLocationtypefromVASLLocation(SeeLOSLoc);
        /*if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OpenGround;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Grain")) {
            return Constantvalues.Location.Grainfield;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Woods")) {
            return Constantvalues.Location.Woods;
        } else {
            return Constantvalues.Location.NA;
        }*/
        // this routine turns the terrain type and base level of a VASL Location (VASL.LOS.Map.Location) into a Constantvalues.Location value - which can then be searched in the Locations collection
    }

    /*public String GetLocationData(Constantvalues.TerrFactor Territem, Constantvalues.Location TerrID) {
        // called by Linqdata.addtocollection - is meant to retrieve specific item from Location type table
        // TerrID is type of terrain - which record to look at
        // Territem is which terrain element to return
        MapDataC MapData = MapDataC.GetInstance("", 0);
        LinkedList<LocationType> LocationTypeCol = MapData.getLocationTypeCol();

        switch (Territem) {
            case TEM:
                for (LocationType loctypetoget : LocationTypeCol) {
                    if (loctypetoget.getLocationvalue() == TerrID) {
                        return Integer.toString(loctypetoget.getTEM());
                    }
                }
        }
        return "";
    *//*Case ConstantClassLibrary.
    ASLXNA.TerrFactor.Desc
            TerrInfo = (From
    QU In
    Loctab.Locations Where
    QU.Locationtype =
    TerrID _
    Select QU.Terraindesc).First.ToString
                    'Case LocFactor.ScenFeature
                            'If TerrID > 6900 Then   '
    smoke item
                    '    'TerrInfo =

    CStr((From QU In db.Locations Where QU.Terraintype=TerrID _
            '    'Select QU.Terraindesc).First)
            'Else                '
    other scen
    feature
                    '    TerrInfo = CStr((From QU In db.Locations Where QU.Terraintype = TerrID _
                            '                      Select QU.Terraindesc).First)
                            'End If
                            'Case LocFactor.MF
                            '    TerrInfo = (From QU In db.Locations Where QU.Terraintype = TerrID _
                            '                      Select QU.mfcot).First.ToString
    Case ConstantClassLibrary.
    ASLXNA.TerrFactor.Image
            TerrInfo = (From
    QU In
    Loctab.Locations Where
    QU.Locationtype =
    TerrID _
    Select QU.Image).First.ToString
                    'Case LocFactor.ObstHeight
                            '    TerrInfo = (From QU In db.Locations Where QU.Terraintype = TerrID _
                            '                     Select QU.ObstHeight).First.ToString
    End Select
        String TerrInfo = "";
        return TerrInfo;*//*
    }*/

    public boolean IsLocationCounterRemoveable(int Territem, int TerrID) {
    /*
            'called by
                    'retrieves value of RemoveWhenUnoccupied from Location type table
    Dim Loctab
    As MapDataClassLibrary.MapDataClassesDataContext =Maptables.GetMapDatabase
    Dim RemoveTest
    As Boolean = CBool((From QU In Loctab.Locations Where QU.Locationtype = TerrID _
            Select QU.RemoveWhenUnoccupied).First)*/
            return false;
    }
    public String GetPositionData(Constantvalues.TerrFactor Territem, int TerrID) {
        //called by
        // is meant to retrieve specific item from Position table in Map database
        // TerrID is type of terrain - which record to look at
        // Territem is which terrain element to return

        //Dim Postab As MapDataClassLibrary.MapDataClassesDataContext = Maptables.GetMapDatabase
        String TerrInfo = "";
       /* 'query
        If TerrID = 0 Then return TerrInfo
        Select Case Territem
        Case ConstantClassLibrary.ASLXNA.TerrFactor.TEM
                TerrInfo = (From QU In Postab.Positions Where QU.Locationtype = TerrID _
        Select QU.TEM).First.ToString
        If TerrInfo = "" Then TerrInfo = "0"
        'Case LocFactor.LOSHind
        '    TerrInfo = (From QU In Loctab.Locations Where QU.Locationtype = TerrID _
        '                      Select QU.LOSHindDRM).First.ToString
        Case ConstantClassLibrary.ASLXNA.TerrFactor.Desc
                TerrInfo = (From QU In Postab.Positions Where QU.Locationtype = TerrID _
        Select QU.Terraindesc).First.ToString
        'Case LocFactor.ScenFeature
        'If TerrID > 6900 Then   ' smoke item
        '    ' TerrInfo = CStr((From QU In db.Locations Where QU.Terraintype = TerrID _
        '    ' Select QU.Terraindesc).First)
        'Else                ' other scen feature
        '    TerrInfo = CStr((From QU In db.Locations Where QU.Terraintype = TerrID _
        '                      Select QU.Terraindesc).First)
        'End If
        'Case LocFactor.MF
        '    TerrInfo = (From QU In db.Locations Where QU.Terraintype = TerrID _
        '                      Select QU.mfcot).First.ToString
        Case ConstantClassLibrary.ASLXNA.TerrFactor.Image
                TerrInfo = (From QU In Postab.Positions Where QU.Locationtype = TerrID _
        Select QU.Image).First.ToString
        'Case LocFactor.ObstHeight
        '    TerrInfo = (From QU In db.Locations Where QU.Terraintype = TerrID _
        '                     Select QU.ObstHeight).First.ToString
        End Select*/
        return TerrInfo;
    }
    public boolean IsLocationOGforFFMO(Location TestHexlocation, Constantvalues.AltPos TestHexPosition) {

        /*Dim LoctoUse = (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.LocIndex = TestLocIndex).First
        If TestHexPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv Then
        If LoctoUse.IsInherent And CInt(LoctoUse.TEM) > 0 Then return False Else return True
        'If LoctoUse.IsInherent = False And LoctoUse.OtherTerraininLocation > 0 Then
        '    return CBool(LoctoUse.OTisOG)
        'Else
        '    return CBool(LoctoUse.LocIsOG)
        'End If
        End If
        If LoctoUse.Location = TestHexlocation Then return CBool(LoctoUse.LocIsOG)
        If LoctoUse.OtherTerraininLocation = TestHexlocation Then return CBool(LoctoUse.OTisOG)
        'if here then error*/
        return false;
    }
    public boolean IsLocationPresent(int HextoTest, int LocationtoFind, int LocToUse) {
        /*'check if there is a type of location in a hex
        Dim locationPresent = False
        Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapData)
        Dim Temploclist As IQueryable (Of MapDataClassLibrary.GameLocation) =
        Getlocs.RetrieveLocationsfromMapTable(HextoTest, "Hexnum")
        If LocationtoFind >=ConstantClassLibrary.ASLXNA.Location.Pillboxtype Then
        For Each TempHexloc As MapDataClassLibrary.GameLocation In Temploclist
        If TempHexloc.Location >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And TempHexloc.
        Location <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
                locationPresent = True :LocToUse = TempHexloc.LocIndex :Exit For
        End If
        Next
                Else
        For Each TempHexloc As MapDataClassLibrary.GameLocation In Temploclist
        If TempHexloc.Location = LocationtoFind Then
                locationPresent = True :LocToUse = TempHexloc.LocIndex :Exit For
        End If
        Next
        End If
        IsLocationPresent = locationPresent*/
        return false;
    }
    public LinkedList<SmokeHolder> SmokePresentinHex(Hex Hextocheck) {
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
    }
    private Constantvalues.VisHind ConverttoVisHind(String smoketype){
        if (smoketype == "White +3 Smoke") {
            return Constantvalues.VisHind.GunSmoke;
        } else {
            return Constantvalues.VisHind.None;
        }
    }
}
