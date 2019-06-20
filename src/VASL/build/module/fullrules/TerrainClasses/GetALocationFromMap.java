package VASL.build.module.fullrules.TerrainClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;

import java.util.LinkedList;

/**
 * Created by dougr_000 on 7/20/2017.
 */
public class GetALocationFromMap {
    // This class handles various methods to return row(s) from the Map Table that meet parameter criteria (are of a specified location type)
    //private LinkedList<GameLocation> MapData;
    //private LinkedList<GameLocation> MapCol;

    // overloaded
    // handles standard access via open data reader
    //public GetALocationFromMap(LinkedList<GameLocation> LocationCol) {   // As IQueryable(Of MapDataClassLibrary.GameLocation))
       //MapData = LocationCol;
    //}

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
    */
    public Location GetPillboxLocation(Hex hextouse) {
        // returns Location  of pillbox location in a hex
        // None means no pillbox exists
        LinkedList<Locationi> HexLocs = RetrieveLocationsinHex(hextouse);
        for (Locationi newlocationi : HexLocs) {
            if (newlocationi.IsPillbox()) {
                return newlocationi.getvasllocation();
            }
        }
        return null;
    }
    // overloaded
    // first function returns record when have Location
    public Locationc RetrieveLocationfromHex(Location LocIndex) {
        // called by Mapactions.Puthexdataintocollection, Terrainactions.GetTerrain
        // 'returns the location records matching the location index value
        return null; //(From QU As MapDataClassLibrary.GameLocation In MapData Where QU.LocIndex = LocIndex).First - temporary while debugging delete
    }
    // second overload returns record when have hex and location
    public Locationi RetrieveLocationfromHex(Hex hextouse, Constantvalues.Location Locationtoget) {
        // called by Mapactions.Puthexdataintocollection, Terrainactions.GetTerrain
        // returns the specified location in a specified hex

        LinkedList<Locationi> HexLocs = RetrieveLocationsinHex(hextouse);
        for (Locationi newlocationi : HexLocs) {
            if (newlocationi.getLocationtype().equals(Locationtoget)) {
                return newlocationi;

            }
        }

        return null; //error if here; location sought does not exist in hextouse
    }
    // thread variant
    public Location RetrieveLocationfromMapcol(Hex Hextoget, Location Locationtoget) {
        // called by Mapactions.Puthexdataintocollection, Terrainactions.GetTerrain
        // returns the location records matching the location index value

        Location LocToGet;
        //remmed out while debugging - delete
        /*try {
            LocToGet = (From QU As MapDataClassLibrary.GameLocation In MapCol Where QU.Hexnum = Hexnumber
            And(QU.Location = Location Or QU.OtherTerraininLocation = Location)).First
        }catch (Exception e) {
            MessageBox.Show(e.ToString & vbCrLf & vbCrLf & Hexnumber.ToString & Space(5) & Location.ToString)
            LocToGet = null;
        }*/
        //MapCol = null;
        return null; //temporary while debugging delete
    }

    // overloaded
    // this may need various overloads to handle different filter combinations
    // this overload returns records where location equals a specified terrain type
    public LinkedList<Locationi> RetrieveLocationsfromMapTable(int Filter) {
        return null; // From Qu As MapDataClassLibrary.GameLocation In MapData Where Qu.Location = Filter
    }
    // this overload returns all locations in a specified hex
    public LinkedList<Locationi> RetrieveLocationsinHex(Hex hexforlocations) {

        LinkedList<Locationi> locationsinhex = new LinkedList<Locationi>();
        locationsinhex.add(new Locationc((hexforlocations.getCenterLocation()), null));
        Location currentlocation = hexforlocations.getCenterLocation();
        while (currentlocation.getUpLocation() != null) {
            locationsinhex.add(new Locationc((currentlocation.getUpLocation()), null));
            currentlocation = currentlocation.getUpLocation();
        }
        currentlocation = hexforlocations.getCenterLocation();
        while (currentlocation.getDownLocation() != null) {
            locationsinhex.add(new Locationc((currentlocation.getDownLocation()), null));
            currentlocation = currentlocation.getDownLocation();
        }
        return locationsinhex;
    }
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
