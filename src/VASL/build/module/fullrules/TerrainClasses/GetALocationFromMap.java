package VASL.build.module.fullrules.TerrainClasses;

import VASL.build.module.fullrules.MapDataClasses.GameLocation;

import java.util.LinkedList;

/**
 * Created by dougr_000 on 7/20/2017.
 */
public class GetALocationFromMap {
    // This class handles various methods to return row(s) from the Map Table that meet parameter criteria (are of a specified location type)
    private LinkedList<GameLocation> MapData;
    private LinkedList<GameLocation> MapCol;

    // overloaded
    // handles standard access via open data reader
    public GetALocationFromMap(LinkedList<GameLocation> LocationCol) {   // As IQueryable(Of MapDataClassLibrary.GameLocation))
        MapData = LocationCol;
    }

    // handles access via new data reader when multithreading
    public GetALocationFromMap() {

    }

    // Methods
    /*Public Sub CreateThreadCollection(ByVal ScenID As Integer)
    Dim ASLMapLink As String = "Scen" & CStr(ScenID)
            'need to pass string value to create terrain collection
                    '' ''THIS DOES NOT WORK AS NOT THREAD SAFE, EACH THREAD WILL ACCESS THE MAPDATACLASSLIBRARY INSTANCE
            ' ''Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(Trim(ASLMapLink), ScenID)
                    ' ''MapData = MapTables.CreateMapCollectionforThread()
    Dim NewDb = New MapDataClassLibrary.MapDataClassesDataContext
            Try
    Dim LOCCol As IQueryable(Of MapDataClassLibrary.GameLocation) = From QU As MapDataClassLibrary.GameLocation In NewDb.GameLocations Select QU
            MapCol = LOCCol

    Catch ex As Exception
    MsgBox("Some kind of error" & ASLMapLink, , "CreateMapCollection Failure")

    End Try

    End Sub
    Public Function GetPillboxLocation(ByVal hexnum As Integer) As Integer
            'returns LocIndex value of pillbox location in a hex
                    '0 means no pillbox exists
    Dim Pillboxloc As Integer = 0
    Try
            Pillboxloc = (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = hexnum And QU.IsPillbox = True Select QU.LocIndex).First
            Catch
    Pillboxloc = 0
    End Try
    Return Pillboxloc
    End Function*/
    // overloaded
    // first function returns record when have LocIndex
    public GameLocation RetrieveLocationfromMaptable(int LocIndex) {
        // called by Mapactions.Puthexdataintocollection, Terrainactions.GetTerrain
        // 'returns the location records matching the location index value
        return null; //(From QU As MapDataClassLibrary.GameLocation In MapData Where QU.LocIndex = LocIndex).First - temporary while debugging delete
    }
    // second overload returns record when have hexnumber and location
    public GameLocation RetrieveLocationfromMaptable(int Hexnumber, int Location) {
        // called by Mapactions.Puthexdataintocollection, Terrainactions.GetTerrain
        // returns the location records matching the location index value
        try {
            //return (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = Hexnumber
            //And(QU.Location = Location Or QU.OtherTerraininLocation = Location)).First
        } catch (Exception e) {
            //MessageBox.Show(e.ToString & vbCrLf & vbCrLf & Hexnumber.ToString & Space(5) & Location.ToString)
            //return null;
        }
        return null; //temporary while debugging, delete
    }
    // thread variant
    public GameLocation RetrieveLocationfromMapcol(int Hexnumber, int Location) {
        // called by Mapactions.Puthexdataintocollection, Terrainactions.GetTerrain
        // returns the location records matching the location index value

        GameLocation LocToGet;
        //remmed out while debugging - delete
        /*try {
            LocToGet = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = Hexnumber
            And(QU.Location = Location Or QU.OtherTerraininLocation = Location)).First
        }catch (Exception e) {
            MessageBox.Show(e.ToString & vbCrLf & vbCrLf & Hexnumber.ToString & Space(5) & Location.ToString)
            LocToGet = null;
        }*/
        MapCol = null;
        return null; //temporary while debugging delete
    }

    //remmed out while debugging - delete
    /*    'overloaded
                'this may need various overloads to handle different filter combinations
                'this overload returns records where location equals a specified terrain type
    Public Function RetrieveLocationsfromMapTable(ByVal Filter As Integer) As IQueryable(Of MapDataClassLibrary.GameLocation)
    Return From Qu As MapDataClassLibrary.GameLocation In MapData Where Qu.Location = Filter
    End Function
        'this overload returns all locations in a specified hex
    Public Function RetrieveLocationsfromMapTable(ByVal Filter As Integer, ByVal LookingFor As String) As IQueryable(Of MapDataClassLibrary.GameLocation)
    Select Case Trim(LookingFor)
    Case "Hexnum"
    Try
    Return From Qu As MapDataClassLibrary.GameLocation In MapData Where Qu.Hexnum = Filter
            Catch
    Return Nothing
    End Try
    End Select
    End Function */
    public String GetnamefromdatatableMap(int hexnumber) {
        // Called by Map.HexbyHexClear, Map.LOSCheck, MapCoord.Gethexname, RangeClassC.DirExtent
        // Returns Hexname from a scenario maptable based on hexnumber passed as parameter
        return null;  // temporary while debugging undo (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = hexnumber Select QU.Hexname).First
    }
    /*Public Function Gethexnumfromhexname(ByVal hexname As String) As Integer
            'Called by
                    'Returns Hexnum from a scenario maptable based on hexname passed as parameter
    hexname = Trim(hexname)
    Return (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexname = hexname Select QU.Hexnum).First
    End Function
        'This sub is overloaded
                'first uses LOCIndex
    Friend Sub GetTerrain(ByVal LocIndexToUse As Integer, ByRef LocBaselevel As Single, ByRef LocObstlevel As Single)
            'called by Map.HexbyHexMoveAlongOK, IFT.CombatDRM, Map.ObstacleFind
                    'Gameform.pbGameMap.MouseMove
                    'provides data on obstacle and height for specific location
    Dim TerrLoc As MapDataClassLibrary.GameLocation = (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.LocIndex = LocIndexToUse).First
            LocBaselevel = TerrLoc.Baselevel
    LocObstlevel = CSng(TerrLoc.ObstacleHeightinhex)
    End Sub
        'second overload uses Hexnum and Location
    Friend Sub GetTerrain(ByVal Hexnumber As Integer, ByVal Locationtouse As Integer,
                          ByRef LocBaselevel As Single, ByRef LocObstlevel As Single)
            'called by Map.HexbyHexClear
                    'provides data on  obstacle and height for specific location

    Dim TerrLoc As MapDataClassLibrary.GameLocation = (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = Hexnumber And QU.Location = Locationtouse).First
            'Dim Terrhex As DataRow = GetHexData(Hexnumber)
                    'Dim hexTerraintype As Integer = CInt(Terrhex("Terraintype"))
    LocBaselevel = TerrLoc.Baselevel
            LocObstlevel = CSng(TerrLoc.ObstacleHeightinhex)

    End Sub*/
}
