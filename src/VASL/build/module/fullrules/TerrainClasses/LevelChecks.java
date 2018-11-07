package VASL.build.module.fullrules.TerrainClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;

/**
 * Created by dougr_000 on 7/18/2017.
 */
public class LevelChecks {
    private Location pLocation;

    public LevelChecks() {
        //pLocation = PassLocation;
    }

    // Methods

    public double GetLevelofLocation() {
        // called by MovementWithinLegalc.IsMovementLegal
        // returns the level-in-hex of a specified location

        return pLocation.getBaseHeight();
        // Return CSng((From QU As MapDataClassLibrary.GameLocation In MapData Where QU.LocIndex=LocIndextoTest Select QU.LevelInHex).First)
    }

    // remmed out while debugging undo
    /*
        'Public Function GetBaseLocationofHex(ByVal Hexnumber As Integer) AS MapDataClassLibrary.GameLocation
                '    '
    called by
    HexBesideC.AreLocationsADJACENT
        '    '
    returns the
    base location
    of a
    hex
        '    '
    is the
    query correct;
    can there
    be other
    non-
    base locations
    at level-in-hex 0?caves?
            '    Return (From QU AS MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = Hexnumber And QU.LevelInHex = 0 And QU.IsPillbox = False).First
            'End Function
    */

    public Location GetLocationatLevelInHex(Hex PassHex, double Levelnumber) {
        Location testloc = PassHex.getCenterLocation();
        while (testloc.getBaseHeight() != Levelnumber ){
            if (testloc.getBaseHeight() < Levelnumber) {
                testloc = testloc.getUpLocation();
            } else if (testloc.getBaseHeight() > Levelnumber) {
                testloc = testloc.getDownLocation();
            }
        }

        // called by MapActions.IsSameHexLOSClear
        // returns the location of a non-zero level in a hex
        // is the query correct; can there be more than one locations at level-not-0?
        // If Levelnumber = 0 Then Return Nothing ' only checks non - zero levels

        return testloc; //(From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = Hexnumber And QU.LevelInHex = Levelnumber).First
    }

    // overloaded
    // using LocIndex
    public double GetTotalLocationLevel(int LocIndextoTest) {
        // called by HexBesideC.AreLocationsADJACENT
        // returns the total level of a specified location (base leve of hex plus in-hex level)
    /*Dim loctouse
    As MapDataClassLibrary.GameLocation =(
    From QU
    As MapDataClassLibrary.
    GameLocation In
    MapData Where
    QU.LocIndex =LocIndextoTest).First

    Return CSng(loctouse.Baselevel +loctouse.LevelInHex)*/
    return 0;
    }
    // using Hexnum and Location
    public double GetTotalLocationLevel(int hexnumber,int LoctoTest) {
        // called by HexBesideC.AreLocationsADJACENT
        // returns the total level of a specified location (base leve of hex plus in-hex level)
        /*Dim loctouse
        As MapDataClassLibrary.GameLocation = (
                From QU
        As MapDataClassLibrary.
        GameLocation In
        MapData Where
        QU.Hexnum =
                hexnumber And
        QU.Location = LoctoTest).First

        Return CSng (loctouse.Baselevel + loctouse.LevelInHex)*/
        return 0;
    }

    public double GetLocationPositionLevel(Location Locationtoget, Constantvalues.AltPos Positiontoget) {
        // called by IsSameHexLocationADJACENT, Linqdata.UpdateAfterMove, MoveWithinLegal.IsMovementLegal plus many other methods
        // gets level within hex of specific location and position

        // remmed out while debugging undo
        /*Dim loctouse
        As MapDataClassLibrary.GameLocation = (
                From QU
        As MapDataClassLibrary.
        GameLocation In
        MapData Where
        QU.Hexnum =

                hexnumber And(QU.Location = Locationtoget Or QU.OtherTerraininLocation = Locationtoget)).First
        Dim LocPosLevel
        As Single = CSng(loctouse.LevelInHex)
        'if in crest status or exited crest then one level above terrain in hex

        If(Positiontoget >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Positiontoget <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest5)
        Or
                (Positiontoget >=
                                ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And
                        Positiontoget <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Then
        Return 1
        Else
        Return LocPosLevel
        End If
    }
            'Roof
                    'InCave
                    'BeneathBridge
                    'Sewer
                    'BeneathPier
                    'BlazeRoofS = 6755
                    'BlazeRoofW = 6756
*/
        return 0;
    }
    /*Public Function GetNextLevelUp(ByVal hexnumber As Integer, ByVal currentlocation As Integer, ByVal CurrentPosition As Integer) As Integer
            'called by ThrowSmoke.MoveUpdate
                    'Gets the next higher level-in-hex (if one exists)
    Dim LocationsFound As IQueryable(Of MapDataClassLibrary.GameLocation)
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapData)
    LocationsFound = GetLocs.RetrieveLocationsinHex(hexnumber, "Hexnum")
    Dim Currentlevel As Single = GetLocationPositionLevel(hexnumber, currentlocation, CurrentPosition)
    For Each LocsFound As MapDataClassLibrary.GameLocation In LocationsFound
                'check if location is next level up (Levelinhex+1)
    If LocsFound.LevelInHex - Currentlevel = 1 Then Return CInt(LocsFound.LevelInHex) 'match
    Next
    Return 99  ' if no higher level found
    End Function
    Public Function GetNextLevelDown(ByVal hexnumber As Integer, ByVal currentlocation As Integer, ByVal currentposition As Integer) As Integer
            'called by ThrowSmoke.MoveUpdate
                    'gets the next lower level-in-hex (if one exists)
    Dim LocationsFound As IQueryable(Of MapDataClassLibrary.GameLocation)
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapData)
    LocationsFound = GetLocs.RetrieveLocationsinHex(hexnumber, "Hexnum")
    Dim Currentlevel As Single = GetLocationPositionLevel(hexnumber, currentlocation, currentposition)
    For Each LocsFound As MapDataClassLibrary.GameLocation In LocationsFound
                'check if location is next level up (Levelinhex+1)
    If Currentlevel - LocsFound.LevelInHex = 1 Then Return CInt(LocsFound.LevelInHex) 'match
    Next
    Return 99  ' if no lower level found
    End Function
    Public Function IsLocationUpperLevel(ByVal hexnumber As Integer, ByVal hexlocation As Integer) As Boolean
    Try
    Dim LocLevel As Single = CSng((From QU In MapData Where QU.Hexnum = hexnumber And QU.Location = hexlocation Select QU.LevelInHex).First)
    If LocLevel > 0 Then Return True Else Return False
    Catch ex As Exception
    Return False
    End Try
    End Function
    Public Function GetNumberofLevelsInHex(ByVal hexnumtotest As Integer) As Integer
            'called by DisplayShade.GetLevels
                    'determines number of levels in hex - not including base level of hex
    Dim NumofLevels As Integer = 1 'every hex has a base level
            'get all the locations in the hex
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapData)
    Dim Getlocations As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsinHex(hexnumtotest)
            'rotate through looking non-ground levels
    For Each LocFound As MapDataClassLibrary.GameLocation In Getlocations
    If LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Building1stLevel Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Building2ndLevel Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Building3rdLevel Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Cellar Or LocFound.Location = ConstantClassLibrary.ASLXNA.Location.BunkUnder Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Sewer Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.InCave Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Roof Then  '1 Or LocFound.Location = Location.Roof25 Or LocFound.Location = Location.Roof35 Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location = ConstantClassLibrary.ASLXNA.Location.Pier Then
    NumofLevels += 1
    Continue For
    ElseIf LocFound.Location >= ConstantClassLibrary.ASLXNA.Location.StBr14 And LocFound.Location <= ConstantClassLibrary.ASLXNA.Location.SWdBr36 Then
    NumofLevels += 1
    Exit For
    End If
    Next
    Return NumofLevels
    End Function
    Public Function Attop(ByVal hexnum As Integer, ByVal currentlocindex As Integer) As Boolean
    Dim NewLevel As Single = 0
            'get all the locations in the hex
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapData)
    Dim Getlocations As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsinHex(hexnum, "Hexnum")
            'get current level
    Dim Currentlevel = GetLevelofLocation(currentlocindex)
    For Each HexLoc As MapDataClassLibrary.GameLocation In Getlocations
            NewLevel = GetLevelofLocation(HexLoc.LocIndex)
    If NewLevel = Currentlevel + 1 Or NewLevel = Currentlevel + 0.5 Then Return False
            Next
    Return True
    End Function
    Public Function Atbottom(ByVal hexnum As Integer, ByVal Currentlocindex As Integer) As Boolean
    Dim NewLevel As Single = 0
            'get all the locations in the hex
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapData)
    Dim Getlocations As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsinHex(hexnum, "Hexnum")
            'get current level
    Dim Currentlevel = GetLevelofLocation(Currentlocindex)
    For Each HexLoc As MapDataClassLibrary.GameLocation In Getlocations
            NewLevel = GetLevelofLocation(HexLoc.LocIndex)
    If NewLevel = Currentlevel - 1 Or NewLevel = Currentlevel - 0.5 Then Return False
            Next
    Return True
    End Function*/

}
