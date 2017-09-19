package VASL.build.module.fullrules.MapDataClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by dougr_000 on 7/20/2017.
 */

/*Imports System
        Imports System.Collections
        Imports System.Collections.Generic
        Imports System.ComponentModel
        Imports System.Data
        Imports System.Data.SqlClient
        Imports System.Data.Common
        Imports System.Linq
        'Imports System.Drawing
        Imports System.Text
        Imports System.Diagnostics
        Imports System.Linq.Expressions
        Imports System.Linq.Dynamic*/


public class MapDataC {
    // create as singleton class using singleton pattern
    // called by Scenario.New and Scenario.OPenScenario
    private static MapDataC MapDatainstance;
    //Friend DataTableMapLOS As New DataTable temporary while debugging undo
    private int ScenarioID = 0;
    private String ScenarioName;
    private Connection aslmapdatacon;
    private LinkedList<LocationType> LocationTypeCol = new LinkedList<LocationType>();
    private LinkedList<HexsideType> HexsideTypeCol = new LinkedList<HexsideType>();
    //Public db As MapDataClassesDataContext  temporary while debugging undo

    // constructors
    private MapDataC(String Scenname, int ScenID) {
        // called by MapTables.Getinstance as part of singleton pattern
        // set variables used by various methods
        ScenarioID = ScenID;
        ScenarioName = Scenname;
    }

    public static MapDataC GetInstance(String Scenname, int ScenID) {
        // called by Game.Scenario.Openscenario and NewScenario and Campaign.OpenCampaign, Campaign.SaveCampaign
        // instantiates object as singleton class - using singleton design pattern
        if (MapDatainstance == null) {
            MapDatainstance = new MapDataC(Scenname, ScenID);
        }
        return MapDatainstance;

    }

    public void InitializeData() {
        // called by
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:11825;database=ASLMapData17;integratedSecurity=true";
        // Declare the JDBC objects.
        aslmapdatacon = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            aslmapdatacon = DriverManager.getConnection(connectionUrl);

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {

        }
        CreateLocationTypeCollection();
        CreateHexsideTypeCollection();
        try {
            aslmapdatacon.close();
        } catch (Exception e){

        }
    }

    public void CreateLocationTypeCollection(){

        Constantvalues.Location PassLocationvalue=null;
        int PassTEM=0;
        int PassLOSHindDRM=0;
        double Passmfcot=0;
        double Passmpcot=0;
        boolean PassBypassOK=false;
        boolean Passbogcheck=false;
        String PassTerrainDesc="";
        int PassObstHeight=0;
        boolean PassBuilding=false;
        boolean PassRoad=false;
        boolean PassBridge=false;
        boolean PassPillbox=false;
        boolean PassRemoveWhenUnoccupied=false;
        Statement stmt = null;
        ResultSet rs = null;
        LocationType AddLocType=null;
        // Create and execute an SQL statement that returns the data.
        if (aslmapdatacon != null) {
            try {
                String SQL = "SELECT * FROM Location";
                stmt = aslmapdatacon.createStatement();
                rs = stmt.executeQuery(SQL);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            while (rs.next()) {
                PassLocationvalue = ConverttoLocationType(rs.getInt(1));
                PassTEM = rs.getInt(2);
                PassLOSHindDRM = rs.getInt(3);
                Passmfcot = rs.getDouble(4);
                Passmpcot = rs.getDouble(5);
                PassBypassOK = rs.getBoolean(6);
                Passbogcheck = rs.getBoolean(7);
                PassTerrainDesc = rs.getString(8);
                PassObstHeight = rs.getInt(10);
                PassBuilding = rs.getBoolean(11);
                PassRoad = rs.getBoolean(12);
                PassBridge = rs.getBoolean(13);
                PassPillbox = rs.getBoolean(14);
                PassRemoveWhenUnoccupied = rs.getBoolean(15);
                AddLocType = new LocationType(PassLocationvalue, PassTEM,  PassLOSHindDRM,  Passmfcot,  Passmpcot,  PassBypassOK,  Passbogcheck,  PassTerrainDesc,
                 PassObstHeight,  PassBuilding,  PassRoad,  PassBridge,  PassPillbox,  PassRemoveWhenUnoccupied);
                LocationTypeCol.add(AddLocType);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        }
    }
    public void CreateHexsideTypeCollection(){

        Constantvalues.Hexside PassHexsidevalue = null;
        int PassTEM = 0;
        boolean PassSideHalfObstacle = false;
        boolean PassSideBypassOK = false;
        String PassSidedesc = "";
        double Passmfcot = 0;
        double Passmpcot = 0;
        boolean PassIsRoad = false;
        boolean PassIsWallHdRdbk = false;

        Statement stmt = null;
        ResultSet rs = null;
        HexsideType AddHexSideType=null;
        // Create and execute an SQL statement that returns the data.
        if (aslmapdatacon != null) {
            try {
                String SQL = "SELECT * FROM Hexside";
                stmt = aslmapdatacon.createStatement();
                rs = stmt.executeQuery(SQL);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            while (rs.next()) {
                PassHexsidevalue = ConverttoHexsideType(rs.getInt(1));
                PassTEM = rs.getInt(2);
                PassSideHalfObstacle = rs.getBoolean(3);
                PassSideBypassOK = rs.getBoolean(4);
                PassSidedesc = rs.getString(5);
                Passmfcot = rs.getInt(6);
                Passmpcot = rs.getInt(7);
                PassIsRoad = rs.getBoolean(9);
                PassIsWallHdRdbk = rs.getBoolean(10);

                AddHexSideType = new HexsideType(PassHexsidevalue, PassTEM,  PassSideHalfObstacle,  PassSideBypassOK,  PassSidedesc,  Passmfcot,
                        Passmpcot,  PassIsRoad,  PassIsWallHdRdbk);
                HexsideTypeCol.add(AddHexSideType);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        }
    }
    public Constantvalues.Location ConverttoLocationType(int Locvalue){

        ConversionC DoConversion = new ConversionC();
        return DoConversion.ConverttoLocationType(Locvalue);

    }

    public Constantvalues.Hexside ConverttoHexsideType( int databasevalue){

        ConversionC DoConversion = new ConversionC();
        return DoConversion.ConverttoHexsideType(databasevalue);

    }
    public LinkedList<LocationType> getLocationTypeCol() {return LocationTypeCol;}

    public LinkedList<HexsideType> getHexsideTypeCol() {return HexsideTypeCol;}

    /*
        Public Function UpdateMaptables(ByVal ASLMapLink As String, ByVal ASLLOSLink As String) As Boolean
        Try
                    'table must have primary key otherwise readonly and submit changes will not work
                            'first save changes to GameLocation and GameLOS tables
                            'MessageBox.Show(db.GameLOs.Count.ToString)
                            db.SubmitChanges()
                            'Now copy from those tables to the Map and LOS Table
                            'MessageBox.Show(db.GameLOs.Count.ToString)
                            'db.Connection.Close()
        UpdateScenMapTable(ASLMapLink)
        UpdateScenLOSTable(ASLLOSLink)
                    'db.Connection.Open()
                            'MessageBox.Show(db.GameLOs.Count.ToString)
        Return True

        Catch ex As Exception
        Return False
        End Try
        End Function
        Private Function UpdateScenMapTable(ByVal AslLink As String) As Boolean
                'called by MapDataC.UpdateMapTable
                        'copies hex data from Gamelocation table to Scenario location table after change to terrain (rubble creation, smoke added, etc)
        Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
        Dim connection As New SqlConnection(connectionstring)
        Dim cmd As SqlCommand
        Dim strsql As String   'hold sql text
        Dim result As Boolean

        Using connection
                    connection.Open()
        Try
                strsql = "delete from dbo." & Trim(AslLink)
        cmd = New SqlCommand(strsql, connection)
                        cmd.ExecuteNonQuery()
                                'MessageBox.Show("Table " & AslLink & " successfully deletes old data", "Table Update Status", _
                                'MessageBoxButtons.OK, MessageBoxIcon.Information)
        result = True
        Catch sqlExc As SqlException
                        MessageBox.Show(sqlExc.ToString, "SQL Exception Error! in MapDataC.UpdateScenMapTable", _
        MessageBoxButtons.OK, MessageBoxIcon.Error)
        result = False
        End Try
        Try
                strsql = "INSERT INTO dbo." & AslLink & vbCrLf & _
                        " SELECT * FROM dbo.GameLocation"
        cmd = New SqlCommand(strsql, connection)
                        cmd.ExecuteNonQuery()
                                'MessageBox.Show("Table GameLocation successfully copies data to " & AslLink, "Table Update Status", _
                                'MessageBoxButtons.OK, MessageBoxIcon.Information)
        result = True
        Catch sqlExc As SqlException
                        MessageBox.Show(sqlExc.ToString, "SQL Exception Error! in MapDataC.UpdateScenMapTable", _
        MessageBoxButtons.OK, MessageBoxIcon.Error)
        result = False
        End Try

        End Using
        Return result
        End Function
        Private Function UpdateScenLOSTable(ByVal AslLink As String) As Boolean
                'called by MapDataC.UpdateMapTable
                        'copies hex data from GameLOS table to Scenario LOS table after change to update/add LOS results
        Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
        Dim connection As New SqlConnection(connectionstring)
        Dim cmd As SqlCommand
        Dim strsql As String   'hold sql text
        Dim result As Boolean

        Using connection
                    connection.Open()
        Try
                strsql = "delete from dbo." & Trim(AslLink)
        cmd = New SqlCommand(strsql, connection)
                        cmd.ExecuteNonQuery()
                                'MessageBox.Show("Table " & AslLink & " successfully deletes old data", "Table Update Status", _
                                'MessageBoxButtons.OK, MessageBoxIcon.Information)
        result = True
        Catch sqlExc As SqlException
                        MessageBox.Show(sqlExc.ToString, "SQL Exception Error! in MapDataC.UpdateScenLOSTable", _
        MessageBoxButtons.OK, MessageBoxIcon.Error)
        result = False
        End Try
        Try
                strsql = "INSERT INTO dbo." & AslLink & vbCrLf & _
                        " SELECT * FROM dbo.GameLOS"
        cmd = New SqlCommand(strsql, connection)
                        cmd.ExecuteNonQuery()
                                'MessageBox.Show("Table GameLOS successfully copies data to " & AslLink, "Table Update Status", _
                                'MessageBoxButtons.OK, MessageBoxIcon.Information)
        result = True
        Catch sqlExc As SqlException
                        MessageBox.Show(sqlExc.ToString, "SQL Exception Error! in MapDataC.UpdateScenLOSTable", _
        MessageBoxButtons.OK, MessageBoxIcon.Error)
        result = False
        End Try

        End Using
        Return result
        End Function*/
    public LinkedList<GameLocation> CreateMapCollection() {
        // called by Game.Scenario.OpenScenario and NewScenario
        // pulls all Location records for current scenario from GameLocation into LocationCol
        // which is passed into other methods, never created within this class library

        PopulateGameLocationTable(ScenarioName);
        InitializeData();
        try {
            LinkedList<GameLocation> Loccol = null; // NEED TO FIXFrom QU As MapDataClassLibrary.GameLocation In db.GameLocations Select QU;
            //LocationCol = Loccol;
            return Loccol;
        } catch(Exception ex) {
            //MsgBox("Some kind of error" & CStr(ScenarioID), , "CreateMapCollection Failure")
            return null;
        }
    }

    /*
    Public Function CreateMapCollectionforThread() As IQueryable(Of MapDataClassLibrary.GameLocation)
            'called by
            'pulls all Location records for current scenario from GameLocation into LocationCol
            'which is passed into other methods, never created within this class library
            'PopulateGameLocationTable(ScenarioName)
            'Dim LOSName As String = "LOS" & CStr(ScenarioID)
            'PopulateGameLOSTable(LOSName)
    InitializeData()
    Try
    Dim Loccol = From QU As MapDataClassLibrary.GameLocation In db.GameLocations Select QU
    Return Loccol
    Catch ex As Exception
    MsgBox("Some kind of error" & CStr(ScenarioID), , "CreateMapCollection Failure")
    Return Nothing
    End Try
    End Function
    Public Function GetBoardArray(ByVal ScenID As Integer) As String(,)
    Dim ScenIDstr As String = CStr(ScenID) : Dim MapRowStr As String
    Dim BoardArray(5, 5) As String
    For MapRow = 0 To 5
    MapRowStr = CStr(MapRow)
    For MapCol = 0 To 5
    Dim ColToCheck As String = "new(Col" & CStr(MapCol) & ")"
    Dim QuerytoRun As String = "ScenID =" & ScenIDstr & " And Row = " & MapRowStr
                    'Dim ColToCheck As String = "(" & Trim(Phasename) & " = 7997 or " & Trim(Phasename) & " = " & ATTorDefRole & ") and " & Selectionname & " = true"
    Dim GetQuery = db.MapArrays.Where(QuerytoRun).Select(ColToCheck)
                    'BoardArray(MapRow, MapCol) = CStr((From QU In GetQuery).First)
    For Each QueryItem In GetQuery
    BoardArray(MapRow, MapCol) = CStr(QueryItem.GetType().GetProperty("Col" & CStr(MapCol)).GetValue(QueryItem, Nothing))
    Next
    If CInt(BoardArray(MapRow, MapCol)) = -999 Then Exit For
    Next MapCol
                'If CInt(currentRow(MapCounter)) = -999 Then Exit For
    Next MapRow
    Return BoardArray
    End Function
    Public Function CreateNewMapDbTables(ByVal BoardNum(,) As String) As Boolean
            'NOT NEEDED AS A RESULT OF USING LINQ TO SQL DATA CONTEXT OCT 12
                    'called by Scenario.SaveScenario
                    'routine for historical and geomorphic  boards
                    'This routine creates all of the tables necessary for a new scenario:
                    'the scenario specific tables for both hexes and LOS and
                    'the board specific tables for hexes and LOS if they don't already exist
            'then it populates the tables; either with data copied from the board files or
                    'with placeholder data

    Dim maprow As Integer = 0 : Dim mapcol As Integer = 0  'hold row and col values of boards in map
            'Dim Boardwidth As Integer  'board width is number of boards in overall maps or columns for historical boards
            'Dim Boardheight As Integer 'board height is number of boards in overall maps or rows for historical boards
    Dim boardname As Integer = 0  'holds the identifying number of a board used in scenario
    Dim basetable As String    'holds name of base hex table; scenario hex table name is passed in as parameter
    Dim LOSScenname As String  'holds name of scenario LOS table
    Dim LOSbasetable As String 'holds name of base LOS table

    LOSScenname = "LOS" & ScenarioID.ToString 'Game.Scenario.ScenID.ToString
            'create new scenario specific tables with standard structure
    If Not GetNewTable(ScenarioName, ConstantClassLibrary.ASLXNA.table.hex) Then Return False
    If Not GetNewTable(LOSScenname, ConstantClassLibrary.ASLXNA.table.LOSScen) Then Return False
            'determine if board table(s) exist and if not create them and populate with empty data
    boardname = Math.Abs(CInt(BoardNum(0, 0)))
    If boardname > ConstantClassLibrary.ASLXNA.Map.CreateMap Then    'historical board
            'start with hex table; only one board table to check
            'stored in BoardNum(0,0); Get table name
    basetable = GetHistMapTableName(boardname)
                'check if table exists, create and populate
    If Not TestCreatePopulateBaseTables(boardname, basetable) Then Return False 'something failed
            'all good, now do LOS base table; Get table name
    LOSbasetable = "LOS" & Trim(basetable)
                'check if table exists
    If Not TestCreateLOSTables(LOSbasetable) Then Return False 'something failed
    Else          'geomorphic boards
            'Need to cycle through BoardNum(x,x) to identify boards in use
            'Boardwidth = CInt(Int(GameForm.pbGameMap.Image.Width / 600))      'identifies max number of boards wide
                'Boardheight = CInt(Int(GameForm.pbGameMap.Image.Height))   'and high
                'Maprow and mapcolumn hold current board position being used in loop
    For maprow = 0 To 5   'Boardheight
    For mapcol = 0 To 5   'Boardwidth
    boardname = Math.Abs(CInt(BoardNum(maprow, mapcol)))
    If CInt(BoardNum(maprow, mapcol)) = -999 Then Exit For
    If boardname > 0 Then   'board exists in this position
            'Get table name
    basetable = "board" & CStr(boardname)
    LOSbasetable = "LOS" & Trim(basetable)
                            'start with hex table
                                    'check if table exists
    If Not TestCreatePopulateBaseTables(boardname, basetable) Then Return False
                            'all good
                                    'now do LOS table
    If Not TestCreateLOSTables(LOSbasetable) Then Return False 'something failed
    End If 'no board exists in this position so loop
    Next mapcol
    If boardname = -999 Then Exit For
    Next maprow
    End If
            'At this point, Scenario hex and LOS tables and all Board hex and LOS tables created and
                    'Board hex tables have data (real or empty)

                    'Now get data from the board table(s) and put it into the scenario hex table
    If boardname > 1100 Then
                'historical map
    basetable = GetHistMapTableName(boardname)
    LOSbasetable = "LOS" & Trim(basetable)
    Else
            boardname = 0
    basetable = "Board"
    LOSbasetable = "LOSBoard"
    End If
    If Not PutDatafromBoardIntoScenarioHexTable(boardname, basetable, ScenarioName, BoardNum) Then Return False
            'Now get data (if any) from the Board LOS tables  and put it into the scenario LOS table
    If Not PutDatafromBoardIntoScenarioLOSTable(boardname, LOSbasetable, LOSScenname, BoardNum) Then Return False
            'if here then all above has worked
    CreateNewMapDbTables = True
    End Function
    Public Function LOSAlreadyValid(ByVal FirerLocIndex As Integer, ByVal TargetLocIndex As Integer, ByRef Checkdone As Boolean) As Boolean
            'called by IFT.DetermineFireSolution
                    'checks to see if a LOSCheck result exists in GameLOS table; if exists, returns IsClear value
    Dim LOSValidCheck As GameLO
    Try
                'need to check both combinations as LOS is reciprocal
    LOSValidCheck = (From QU As GameLO In db.GameLOs Where (QU.FirerLocIndex = FirerLocIndex And QU.TargetLocIndex = TargetLocIndex) Or
                (QU.FirerLocIndex = TargetLocIndex And QU.TargetLocIndex = FirerLocIndex)).First
    Catch ex As Exception
    Checkdone = False
    Return False
    End Try
    Dim IsClear As Boolean = LOSValidCheck.IsClear
            'Dim FirerHex As String = (From QU As MapDataClassLibrary.GameLocation In db.GameLocations Where QU.LocIndex = FirerLocIndex Select QU.Hexname).First.ToString
                    'Dim TargetHex As String = (From QU As MapDataClassLibrary.GameLocation In db.GameLocations Where QU.LocIndex = TargetLocIndex Select QU.Hexname).First.ToString
                    'test code to show result
    If IsClear Then
                ' MsgBox(FirerHex & " - " & TargetHex & " LOS previously checked: it's clear!", , "MapData.LOSAlreadyValid")
    Else
                '    MsgBox(FirerHex & " - " & TargetHex & " LOS previously checked: it's blocked!", , "MapData.LOSAlreadyValid")
    End If
            'set flag to confirm that result found in database
    Checkdone = True
            'return result found
    Return IsClear
    End Function
        'Public Function LOSAlreadyValid(ByVal MapLOSTabletoGet As String, ByVal FirerHexnum As Integer, ByVal TargetHexNum As Integer, ByVal FirerLevelinhex As Single,
                '                                ByVal TargetLevelInHex As Single, ByRef Checkdone As Boolean) As Boolean
                '    'called by IFT.DetermineFireSolution
        '    'checks to see if a LOSCheck result exists in each of the Mapboard-specific and Scenario-specific tables
        '    Dim Firerhextocheck As Integer = FirerHexnum         'all four variables used just for clarity
        '    Dim Targethextocheck As Integer = TargetHexNum
                '    Dim FirerLeveltoCheck As Single = FirerLevelinhex
                '    Dim TargetLeveltoCheck As Single = TargetLevelInHex
                '    Dim Testsalldone As Boolean = False
                '    LOSAlreadyValid = False
                '    Try
                '        'connect
        '        'value of GetConnectionString parameter determines which
        '        'to return: 1 for ASLSQL_data; 2 for ASLMaps
        '        Dim connectionstring As String = GetConnectionString(2)
                '        Using connection As New SqlConnection(connectionstring)
                '            connection.Open()
                '            ' Create a SqlDataAdapter for the MapLOS table.
        '            Dim adapter As New SqlDataAdapter()
                '            ' A table mapping names the DataTable.
            '            adapter.TableMappings.Add("Table", MapLOSTabletoGet)
            '            ' Create a SqlCommand to retrieve map data.
        '            Do
                '                Dim MaptoGettext As String = "Select * from dbo." & MapLOSTabletoGet &
                '                    " where FirerLOSHex = '" & Firerhextocheck & "'" &
                '                    " and TargetLOSHex= '" & Targethextocheck & "'" &
                '                    " and FirerLOSLevel= '" & FirerLeveltoCheck & "'" &
                '                    " and TargetLOSLevel= '" & TargetLeveltoCheck & "'"
                '                Dim MapCommand As SqlCommand = New SqlCommand(MaptoGettext, connection) With
                '                    {.CommandType = CommandType.Text}
                '                ' Set the SqlDataAdapter's SelectCommand.
            '                adapter.SelectCommand = MapCommand
            '                ' Create a SqlDataReader for use with the Load Method.
        '                Dim Mapreader As SqlDataReader = MapCommand.ExecuteReader
                '                ' Fill the DataTable with data by calling Load and passing the SqlDataReader.
            '                DataTableMapLOS.Clear()
            '                DataTableMapLOS.Load(Mapreader, LoadOption.PreserveChanges)
            '                Mapreader.Close()
            '                If Not Testsalldone Then
            '                    'see if anything found
        '                    If DataTableMapLOS.Rows.Count = 0 Then
                '                        'reverse the check and test again
        '                        Firerhextocheck = TargetHexNum
                '                        Targethextocheck = FirerHexnum
                '                        FirerLeveltoCheck = TargetLevelInHex
                '                        TargetLeveltoCheck = FirerLevelinhex
                '                        Testsalldone = True
                '                    Else
                '                        'no need to repeat: LOSCheck found
        '                        Exit Do
                '                    End If
                '                Else
                '                    'both tests done; live with the results
        '                    Exit Do
                '                End If
                '            Loop
                '            adapter.Dispose()
                '            If DataTableMapLOS.Rows.Count = 0 Then
                '                'no match found
        '                Checkdone = False
                '                'MsgBox("This LOS has not yet been checked", , "Linqdata.LOSAlreadyValid")
        '                Return False
                '            Else
                '                'LOSCheck found
        '                Checkdone = True
                '                For Each LOSCheckDone As DataRow In DataTableMapLOS.Rows
                '                    If CBool(LOSCheckDone("Clear")) = True Then
                '                        'MsgBox("LOS previously checked: it's clear!", , "Linqdata.LOSAlreadyValid")
        '                        LOSAlreadyValid = True
                '                    Else
                '                        'MsgBox("LOS previously checked: it's blocked!", , "Linqdata.LOSAlreadyValid")
        '                        LOSAlreadyValid = False
                '                    End If
                '                Next
                '            End If
                '        End Using
                '    Catch ex As Exception
                '        MsgBox("No LOS Checks have been performed on this MapBoard so no match can be found", , "Linqdata.LOSAlreadyValid")
                '        Checkdone = False
                '        Return False
                '    End Try
                'End Function
    Private Function IsSingleBoardLOS(ByVal MapInUse As Integer, ByVal FirerHexnum As Integer,
                                      ByVal TargetHexnum As Integer, ByRef LOSonBoard As Integer, ByVal MapData As IQueryable(Of MapDataClassLibrary.GameLocation)) As Boolean
            'called by Linqdata.AddNewLOSEntry
                    'checks if LOS is within one board and so can be written to Board file
                    'as well as Scenario file; return true/false and if true which board
    Dim GetFirerBoard As String = "" : Dim GetTargetBoard As String = ""  'holds board number for these hexes
    Dim Chartest As Integer : Dim NameToTest As String : Dim NumToTest As Integer = 0  'hold value of fields being tested
    Dim FirstNum As Integer = 0

    If MapInUse > ConstantClassLibrary.ASLXNA.Map.CreateMap Then   'Historical mapboard; no Board number
    LOSonBoard = 0
    Return True
    Else  'geopmorphic; need to check if hexes on same board
            'Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    GetFirerBoard = GetnamefromdatatableMap(FirerHexnum, MapData)
    GetTargetBoard = GetnamefromdatatableMap(TargetHexnum, MapData)
    For i As Integer = 1 To 2
    NameToTest = If(i = 1, GetFirerBoard, GetTargetBoard)
    Chartest = Asc(GetChar(NameToTest, 2))
            'check ascii code for the 2nd char in hexname string
            'if num then board is a two-digit number; if not then it is a one-digit number
    NumToTest = If(Chartest >= 48 And Chartest <= 57, CInt(Left(NameToTest, 2)), CInt(Left(NameToTest, 1)))
            'get the board number
    If i = 1 Then
            FirstNum = NumToTest
    Else
    If FirstNum = NumToTest Then   'from the same board
    LOSonBoard = FirstNum
    Return True
    End If
    End If
    Next i
    LOSonBoard = 0
    Return False
    End If
    End Function
    Private Function GetnamefromdatatableMap(ByVal hexnumber As Integer, ByVal MapData As IQueryable(Of MapDataClassLibrary.GameLocation)) As String
            'Called by MapData.IsSingleBoardLOS
                    'Returns Hexname from a scenario maptable based on hexnumber passed as parameter
                    'private methods for this class library; public version is in TerrainClassLibrary.GetALocationfromMapTable
    Return (From QU As MapDataClassLibrary.GameLocation In MapData Where QU.Hexnum = hexnumber Select QU.Hexname).First
    End Function
    public boolean AddNewLOSEntry(int FirerLOCIndex, int TargetLOCIndex, boolean IsClear) {
        // called by IFT.DetermineFireSolution when pixel-by-pixel determines if LOS is blocked or clear
        // only use pixel-by-pixel for location-pairs not already in GameLOS table
        // enters result in GameLOS table for Scenario LOS which later copies to Scenario LOS table and Map LOS table

        GameLO NewLOS = new GameLO;
        NewLOS.FirerLocIndex = FirerLOCIndex
        NewLOS.TargetLocIndex = TargetLOCIndex
        NewLOS.IsClear = IsClear
        NewLOS.PKvalue = db.GameLOs.Count + 1
        Dim FirerHex As String = (From QU As MapDataClassLibrary.GameLocation In db.GameLocations Where
        QU.LocIndex = FirerLOCIndex Select QU.Hexname).First.ToString
        Dim TargetHex As String = (From QU As MapDataClassLibrary.GameLocation In db.GameLocations Where
        QU.LocIndex = TargetLOCIndex Select QU.Hexname).First.ToString
        Dim ClearState As String = vbCrLf & vbCrLf
        If IsClear Then ClearState = " Clear" Else ClearState = " Blocked"
        Try
        db.GameLOS.InsertOnSubmit(NewLOS)
        db.SubmitChanges()
        MsgBox("GameLOS table is updated for new LOS check result: " & FirerHex & " - " & TargetHex & ClearState, , "Mapdata.AddNewLOSEntry")
        Catch
        MsgBox("GameLOS table has Ungodly error adding new LOS check: " & FirerHex & " - " & TargetHex & ClearState, , "Mapdata.AddNewLOSEntry")
        Return False
        End Try
        Return True
    }

    // threaded version
    public boolean AddNewLOSEntry(GameLO NewLOS) {
        // called by DFFMVCPPattern as part of SetLOSFPdrmValues
        // enters result in GameLOS table for Scenario LOS which later copies to Scenario LOS table and Map LOS table

        NewLOS.PKvalue = db.GameLOs.Count + 1
        Dim FirerHex As String = (From QU As MapDataClassLibrary.GameLocation In db.GameLocations Where
        QU.LocIndex = NewLOS.FirerLocIndex Select QU.Hexname).First.ToString
        Dim TargetHex As String = (From QU As MapDataClassLibrary.GameLocation In db.GameLocations Where
        QU.LocIndex = NewLOS.TargetLocIndex Select QU.Hexname).First.ToString
        Dim ClearState As String = vbCrLf & vbCrLf
        If NewLOS.IsClear Then ClearState = " Clear" Else ClearState = " Blocked"
        Try
        db.GameLOs.InsertOnSubmit(NewLOS)
        db.SubmitChanges()
        'MsgBox("GameLOS table is updated for new LOS check result: " & FirerHex & " - " & TargetHex & ClearState, , "Mapdata.AddNewLOSEntry")
        Catch
        'MsgBox("GameLOS table has Ungodly error adding new LOS check: " & FirerHex & " - " & TargetHex & ClearState, , "Mapdata.AddNewLOSEntry")
        Return False
        End Try
        Return True
    }
        'Public Function AddNewLOSEntry(ByVal MaploStabletoget As String, ByVal CheckResult As Boolean,
                '                               ByVal MapInUse As Integer, ByVal FirerHexNum As Integer, ByVal TargetHexnum As Integer,
                '                               ByVal FirerLevelINhex As Single, ByVal TargetLevelInHex As Single, ByVal MapData As IQueryable(Of GameLocation)) As Boolean
                '    'Called by IFT.DetermineFireSolution when pixel-by-pixel determines if LOS is blocked or clear
        '    'only use pixel-by-pixel for hexes not already in table
        '    'enters result in a Map table for Scenario LOS; which is checked for each Solution
        '    'Dim Testsalldone As Boolean = False
        '    Dim ClearValue As Integer : Dim ResultString As String
                '    Dim LOSOnBoard As Integer = 0 : Dim Boardname As String
                '    Dim BoardFirerHex As Integer : Dim BoardTargetHex As Integer
                '    If CheckResult Then
                '        ClearValue = 1
                '        ResultString = "Clear"
                '    Else
                '        ClearValue = 0
                '        ResultString = "Blocked"
                '    End If
                '    'connect
        '    'value of GetConnectionString parameter determines which
        '    'to return: 1 for ASLSQL_data; 2 for ASLMaps
        '    Dim connectionstring As String = GetConnectionString(2)
                '    Using connection As New SqlConnection(connectionstring)
                '        connection.Open()
                '        If IsSingleBoardLOS(MapInUse, FirerHexNum, TargetHexnum, LOSOnBoard, MapData) Then   'add to Boardfile as well
        '            If LOSOnBoard = 0 Then    'historical map
        '                Boardname = GetHistMapTableName(MapInUse)
                '                'check if table exists, create and populate
        '                Boardname = "LOS" & Trim(Boardname)
                '                If Not TestCreateLOSTables(Boardname) Then Return False 'something failed
        '                BoardFirerHex = FirerHexNum
                '                BoardTargetHex = TargetHexnum
                '            Else   'geopmorphic
        '                Boardname = "LOSBoard" & LOSOnBoard.ToString
                '                If Not TestCreateLOSTables(Boardname) Then Return False 'something failed
        '                'need to translate hexnum
        '                BoardFirerHex = TranslateScenHexNumtoBoard(FirerHexNum, MapData)
                '                BoardTargetHex = TranslateScenHexNumtoBoard(TargetHexnum, MapData)
                '            End If
                '            Try
                '                Dim nstrsql As String = "INSERT INTO " & Boardname & vbCrLf & _
                '                    "(FirerLOSHex, FirerLOSLevel, TargetLOSHex, TargetLOSLevel, Clear)" & _
                '                    " Values (" & BoardFirerHex & ", " & FirerLevelINhex & _
                '                    ", " & BoardTargetHex & ", " & TargetLevelInHex & ", " & ClearValue & ")"
                '                Dim cmd = New SqlCommand(nstrsql, connection)
                '                cmd.ExecuteNonQuery()
                '                'MsgBox(Boardname & " is updated with new LOS check result", , "Linqdata.AddNewLOSEntry")
        '            Catch
                '                MsgBox("Ungodly error")
                '                Return False
                '            End Try
                '        End If
                '        'now update scenario board regardless of whether single board los or not
        '        Try
                '            If Not DoesTableExist(MaploStabletoget) Then
                '                If Not GetNewTable(MaploStabletoget, table.LOSScen) Then Return False
                '            End If
                '            Dim nstrsql As String = "INSERT INTO " & MaploStabletoget & vbCrLf & _
                '                "(FirerLOSBoard, FirerLOSHex, FirerLOSLevel, TargetLOSBoard, TargetLOSHex, TargetLOSLevel, Clear)" & _
                '                " Values (" & LOSOnBoard & ", " & FirerHexNum & ", " & FirerLevelINhex & _
                '                ", " & LOSOnBoard & ", " & TargetHexnum & ", " & TargetLevelInHex & ", " & ClearValue & ")"
                '            Dim cmd = New SqlCommand(nstrsql, connection)
                '            cmd.ExecuteNonQuery()
                '            'MsgBox(MaploStabletoget & " is updated for new LOS check result", , "Linqdata.AddNewLOSEntry")
        '        Catch
                '            MsgBox(MaploStabletoget & " has Ungodly error adding new LOS check")
                '            Return False
                '        End Try
                '    End Using
                '    'only get this far if all has worked
        '    Return True
                'End Function
    Private Function TranslateScenHexNumtoBoard(ByVal HexNumToTranslate As Integer, ByVal MapData As IQueryable(Of MapDataClassLibrary.GameLocation)) As Integer
            'Called by Linqdata.AddNewLOSEntry
                    'Takes scenario map hexnumber and translates it into a geomorphic hex number via hexname
                    'board number doesn't matter as all geomorphic boards have same name-number relation
    Dim HexNametoMatch As String : Dim Result As Integer = 0
    HexNametoMatch = GetnamefromdatatableMap(HexNumToTranslate, MapData)
            'retrieve data from database
                    'connect
                    'value of GetConnectionString parameter determines which
                    'to return: 1 for ASLSQL_data; 2 for ASLMaps
    Dim connectionstring As String = GetConnectionString(2)
    Using connection As New SqlConnection(connectionstring)
                connection.Open()
                        ' Create a SqlDataAdapter for the Map table.
    Dim adapter As New SqlDataAdapter()
                ' A table mapping names the DataTable.
                        adapter.TableMappings.Add("Table", "board1")
                        ' Create a SqlCommand to retrieve map data.
    Dim MaptoGettext As String = "Select * from dbo.board1"
    Dim MapCommand As SqlCommand = New SqlCommand(MaptoGettext, connection) With
    {.CommandType = CommandType.Text}
                ' Set the SqlDataAdapter's SelectCommand.
    adapter.SelectCommand = MapCommand
    Dim Mapreader As SqlDataReader = MapCommand.ExecuteReader
                ' Fill the DataTable with data by calling Load and
                        ' passing the SqlDataReader.
    Dim DatatableBoard As New DataTable
                DatatableBoard.Load(Mapreader, LoadOption.PreserveChanges)
            'Set columns having unique values so they can be primary keys
            DatatableBoard.Columns("Hexname").Unique = True
                'DatatableBoard.Columns("hexnum").Unique = True
                        Mapreader.Close()
    Dim pKey(1) As DataColumn
    pKey(0) = DatatableBoard.Columns("Hexname")
    DatatableBoard.PrimaryKey = pKey
    Dim MapHex As DataRow = DatatableBoard.Rows.Find(HexNametoMatch)
    Result = CInt(MapHex("Hexnum"))
    MapHex = Nothing
            DatatableBoard = Nothing
                adapter.Dispose()
    End Using
    Return Result
    End Function
        'Public Function CreateDataTableMap(ByVal MapFiletoGet As String) As Boolean
                '    'called by Scenario.Openscenario

        '    'REPLACED BY CREATEMAPCOLLECTION
        '    'This routine allows any scenario map table to be opened and data passed to a collection
        '    Try
                '        'retrieve data from database
        '        'connect
        '        'value of GetConnectionString parameter determines which
        '        'to return: 1 for ASLSQL_data; 2 for ASLMaps
        '        Dim connectionstring As String = GetConnectionString(2)
                '        Using connection As New SqlConnection(connectionstring)
                '            connection.Open()
                '            ' Create a SqlDataAdapter for the Map table.
        '            Dim adapter As New SqlDataAdapter()
                '            ' A table mapping names the DataTable.
            '            adapter.TableMappings.Add("Table", MapFiletoGet)
            '            ' Create a SqlCommand to retrieve map data.
        '            Dim MaptoGettext As String = "Select * from dbo." & MapFiletoGet & " Order by " & MapFiletoGet & ".hexnum;"
                '            Dim MapCommand As SqlCommand = New SqlCommand(MaptoGettext, connection) With
                '                {.CommandType = CommandType.Text}
                '            ' Set the SqlDataAdapter's SelectCommand.
            '            adapter.SelectCommand = MapCommand
            '            ' Create a SqlDataReader for use with the Load Method.
        '            Dim Mapreader As SqlDataReader = MapCommand.ExecuteReader
                '            ' Fill the DataTable with data by calling Load and passing the SqlDataReader.
            '            MapdataTable.Load(Mapreader, LoadOption.PreserveChanges)
            '            'Set columns having unique values so they can be primary keys
        '            MapdataTable.Columns("Hexname").Unique = True
                '            MapdataTable.Columns("hexnum").Unique = True
                '            Mapreader.Close()
                '            adapter.Dispose()
                '        End Using
                '        CreateDataTableMap = True
                '    Catch
                '        CreateDataTableMap = False
                '    End Try

                'End Function

    Private Function TestCreatePopulateBaseTables(ByVal boardname As Integer, ByVal basetable As String) As Boolean
            'called by Linqdata.CreateNewMapDbTables
                    'checks if a specified base table exists and in not creates it
                    'then it populates that table with tombstone data

    If Not DoesTableExist(basetable) Then  'board table does not exist
            MessageBox.Show("DoesBoardTableExist = False", "Need to create Board table")
            'create a new board table with standard structure
    If Not GetNewTable(basetable, ConstantClassLibrary.ASLXNA.table.hex) Then Return False 'if table not created, exit
            'populate new table

    If Not PopulateNewBoardtable(basetable, boardname) Then
                    'if populate fails then exit
                            MessageBox.Show("PopulateNewBoardTable = False", "Need to add data to new Board table")
    Return False
    Else
                    'if populate works then continue
                            MessageBox.Show("PopulateNewBoardTable = True")
    End If
    Else
                'Table exists
                        MessageBox.Show("DoesBoardTableExist = true")
                        'check data exists
    If Not DoesTableDataExist(basetable) Then  'table is empty
            'populate table
    If Not PopulateNewBoardtable(basetable, boardname) Then
                        'if populate fails then exit
                                MessageBox.Show("Populate BoardTable = False", "Need to add data to Board table")
    Return False
    Else
                        MessageBox.Show("Populate BoardTable = True")
                                'if populate works then continue
    End If
    Else
                    MessageBox.Show("Data exists in existing Board Table")
                            'if hex data is there then continue
    End If
    End If
    Return True 'all false cases have already returned
    End Function
    Private Function TestCreateLOSTables(ByVal LOSbasetable As String) As Boolean
            'called by Linqdata.createnewmapdbtables
                    'checks for existence of base LOS tables and creates them if necessary
                    'no data population

    If Not DoesTableExist(LOSbasetable) Then  'LOS table does not exist
            MessageBox.Show("DoesLOSTableExist = False", "Need to create LOS table")
            'create a new LOS table with standard structure
    If Not GetNewTable(LOSbasetable, ConstantClassLibrary.ASLXNA.table.LOSBoard) Then Return False 'if table not created, exit
            'don't populate new LOS bae table

    Else               'Table exists
            'MessageBox.Show("Does " & LOSbasetable & " Table Exist = true")
            'don't check if data exists in new LOS base table
    End If
    Return True   'all false cases have already returned
    End Function

    Friend Function GetSQLString(ByVal Scentable As String, ByVal Basetable As String, ByVal hextoget As String) As String
            'called by PutDatafromBoardIntoScenarioHexTable
                    'sets value of command string used in SQL command
    Dim nstrsql As String = "INSERT INTO " & Scentable & vbCrLf & _
                    "(Hexnum, Terraintype, Baselevel, " & _
                    "Hexside1, Hexside2, Hexside3, " & _
                    "Hexside4, Hexside5, Hexside6, " & _
                    "Control, Staircase, Hexname)" & _
                    "SELECT Hexnum, Terraintype, Baselevel, " & _
                    "Hexside1, Hexside2, Hexside3, " & _
                    "Hexside4, Hexside5, Hexside6, " & _
                    "Control, Staircase, Hexname " & _
                    "FROM dbo." & Basetable & " Where dbo." & _
    Basetable & ".hexname = '" & hextoget & "'"
    Return nstrsql
    End Function
    Private Function GetNewTable(ByVal NewTableName As String, ByVal Tabletype As Integer) As Boolean
            'called by CreateNewMapDBTables
                    'creates new hex and tables; both scenario and  board
    Dim TstrSQL As String  'holds value of SQL string
    Dim cmd As SqlCommand   'an sqlcommand is used to execute database command
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Dim connection As New SqlConnection(connectionstring)
            'value of GetConnectionString parameter determines which to return: 1 for ASLSQL_data; 2 for ASLMaps
    Dim result As Boolean
            'set string to create empty table with scenario or board specific name
    If Tabletype = ConstantClassLibrary.ASLXNA.table.hex Then
            TstrSQL = "CREATE TABLE " & NewTableName & " (" & _
                "Hexnum Int  NOT NULL," & _
                "Terraintype Int NOT NULL," & _
                "Baselevel Int NOT NULL," & _
                "Hexside1 Int NOT NULL," & _
                "Hexside2 Int NOT NULL," & _
                "Hexside3 Int NOT NULL," & _
                "Hexside4 Int NOT NULL," & _
                "Hexside5 Int NOT NULL," & _
                "Hexside6 Int NOT NULL," & _
                "Control NVarChar(1) NOT NULL," & _
                "Staircase NVarChar(1) NOT NULL," & _
                "Hexname NVarChar(8) NOT NULL," & ")"
    ElseIf Tabletype = ConstantClassLibrary.ASLXNA.table.LOSScen Then
            TstrSQL = "CREATE TABLE " & NewTableName & " (" & _
                "FirerLOSBoard Int, " & _
                "TargetLOSBoard Int, " & _
                "FirerLOSHex Int NOT NULL, " & _
                "TargetLOSHex Int NOT NULL, " & _
                "FirerLOSLevel Decimal(4,2) NOT NULL, " & _
                "TargetLOSLevel Decimal(4,2) NOT NULL, " & _
                "Clear bit NOT NULL," & ")"
    ElseIf Tabletype = ConstantClassLibrary.ASLXNA.table.LOSBoard Then
            TstrSQL = "CREATE TABLE " & NewTableName & " (" & _
                "FirerLOSHex Int  NOT NULL," & _
                "TargetLOSHex Int NOT NULL," & _
                "FirerLOSLevel decimal(4,2)  NOT NULL," & _
                "TargetLOSLevel decimal(4,2) NOT NULL," & _
                "Clear bit NOT NULL," & ")"
    Else
    Return False
    End If
            ' Open the connection
    Using connection
                connection.Open()
    Try
                    ' A SqlCommand object is used to execute the SQL commands.
    cmd = New SqlCommand(TstrSQL, connection)
                    ' execute the command
                            ' It is more efficient to ExecuteNonQuery when data is not being returned.
                            cmd.ExecuteNonQuery()
                            'MessageBox.Show("Table " & NewTableName & " successfully created.", "Table Creation Status", _
                            '    MessageBoxButtons.OK, MessageBoxIcon.Information)
    result = True
    Catch sqlExc As SqlException
                    MessageBox.Show(sqlExc.ToString, "SQL Exception Error!", _
    MessageBoxButtons.OK, MessageBoxIcon.Error)
    result = False
    End Try
                'empty scenario hex or LOS table created
    End Using
    Return result
    End Function
    Private Function DoesTableExist(ByVal basetable As String) As Boolean
            'called by CreateNewMapDBTables, AddNewLOSEntry
                    'tests if table (hex or LOS) exists and returns result
    Dim restrictions(3) As String
    Dim result As Boolean
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Using connection As New SqlConnection(connectionstring)
                'value of GetConnectionString parameter determines which to return: 1 for ASLSQL_data; 2 for ASLMaps
                        connection.Open()
    restrictions(2) = basetable
    Dim dbTbl As DataTable = connection.GetSchema("Tables", restrictions)
    result = If(dbTbl.Rows.Count = 0, False, True)
                dbTbl.Dispose()
    End Using
    Return result
    End Function
    Private Function DoesTableDataExist(ByVal basetable As String) As Boolean
            'called by CreateNewMapDbTables
                    'Determines if a table already has data; returns result
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Dim connection As New SqlConnection(connectionstring)
    Dim MaptoGettext As String = "Select * from dbo." & basetable
    Dim result As Boolean

    Using connection
                connection.Open()
    Dim MapCommand As SqlCommand = New SqlCommand(MaptoGettext, connection)
    Dim Mapreader As SqlDataReader = MapCommand.ExecuteReader
                'if HasRows is true then data exists
    result = If(Mapreader.HasRows = True, True, False)
                Mapreader.Close()
                        'see if anything found and return result
    End Using
    Return result
    End Function
    Private Function PopulateNewBoardtable(ByVal basetable As String, ByVal boardname As Integer) As Boolean
            'called by CreateNewMapDBTables
                    'adds empty data to new Board table created earlier in CreateNewMapDBTables
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Dim connection As New SqlConnection(connectionstring)
    Dim cmd As SqlCommand
    Dim strsql As String  'holds sql text
    Dim hexrow As Integer : Dim hexcol As Integer     'loop variables
    Dim Hexletter As String : Dim hexname As String
    Dim i As Integer
    Dim hexcounter As Integer = 0           'index variable
    Dim result As Boolean
    Dim MaxRows As Integer : Dim MaxCols As Integer : Dim MapXoffset As Integer : Dim MapYOffset As Integer
    Dim Mapheight As Single : Dim Mapwidth As Single : Dim Boardtype As Integer
    If boardname <= ConstantClassLibrary.ASLXNA.Map.CreateMap Then
    Boardtype = 0 : Mapheight = 1799 : Mapwidth = 645  'standard geomorphic board
    Else  'historical map
    Boardtype = GetBoardtype(boardname)
    Mapheight = 0 : Mapwidth = 0
            'actual values assigned in MakeAdjustmentsToHexes routine
    End If
    MakeAdjustmentsToHexes(boardname, Boardtype, Mapheight, Mapwidth, MaxRows, MaxCols, MapXoffset, MapYOffset)
    Using connection
                connection.Open()
    If boardname <= 1100 Then  'geomorphic board
            'iterates through rows and column, adding data hex by hex
    For hexrow = 1 To 33
    i = If(hexrow Mod 2 = 0, 0, 1)
    Hexletter = If(hexrow < 27, Chr(64 + hexrow), Chr(64 + hexrow - 26) & Chr(64 + hexrow - 26))
    For hexcol = 10 To i Step -1
    hexcounter += 1
    hexname = CStr(boardname) & Hexletter & CStr(hexcol)
    strsql = "INSERT INTO " & basetable & vbCrLf & _
                            "(Hexnum, Terraintype, Baselevel, " & _
                            "Hexside1, Hexside2, Hexside3, " & _
                            "Hexside4, Hexside5, Hexside6, " & _
                            "Control, Staircase, Hexname)" & _
                            " Values (" & CStr(hexcounter) & ", " & "6001, 0, " & _
                            "6500, 6500, 6500, 6500, 6500, 6500, " & _
                            "0, 'N', '" & hexname & "')"
    Try
            cmd = New SqlCommand(strsql, connection)
                                cmd.ExecuteNonQuery()
    result = True
            Catch
    result = False
    End Try
    Next hexcol
    Next hexrow
    Else
                    'historical map
    Dim Noextra As Boolean = False : Dim Oddtrue As Boolean = True
    Dim finishnum As Integer = 0 : Dim StartNum As Integer = 0 : Dim StepNum As Integer = -1
    Dim rowstart As Integer = 0
                    'Dim bitwidth As Single = 3225  'GameForm.pbGameMap.Image.Width
                    'Dim bitheight As Single = 2304  'GameForm.pbGameMap.Image.Height
                    'Dim Boardwidth As Integer = CInt((bitwidth / 64.5))   'number of columns
                    'Dim Boardheight As Integer = CInt(Int(bitheight / 56)) + 1 'number of rows
    Select Case Boardtype
    Case 1, 2   'EnumCon.Map.BloodReef, EnumCon.Map.PegasusBridge, EnumCon.Map.Stoumont
    StartNum = MaxCols
    If Boardtype = 2 Then Noextra = True 'PB/ST
            'Noextra is true for BRT but don't set as it is used to adjust col number
                            'which BRT does not require
    Case 3  'EnumCon.Map.RedB, EnumCon.Map.LaGleize
    StartNum = MaxCols - 1
            'Case EnumCon.Map.Primosole
    Case 4  'EnumCon.Map.Cheneux
    Noextra = True
                            'StartNum = MaxCols - 1
                                    'MaxRows -= 1   'this map has fullheight hexes in first and last rows
    Case 5  'EnumCon.Map.VotG
    StartNum = MaxCols - 1
    End Select
                    'writes from top left to bottom right of map
                            'all of this routine is to generate hexname and hexnumber
    For hexrow = 1 To MaxRows   'Boardheight
    Hexletter = ""
    If Not (Oddtrue) Then finishnum = 0 Else finishnum = 1 'even rows finish with zero on all boards except BRT
    If Boardtype = 1 Then finishnum = 1 'always true for BRT
    If Noextra Then
    If (hexrow Mod 2 = 0) Then StartNum = MaxCols - 1 Else StartNum = MaxCols
    End If
                        ' figures out which letter to use for row
    If hexrow < 27 Then
            rowstart = 64 + hexrow
    Else
            rowstart = 64 + hexrow - 26
    Hexletter = Chr(rowstart)
    End If
    For hexcolumn = StartNum To finishnum Step StepNum
    hexname = Trim(Hexletter) & Chr(rowstart) & CStr(hexcolumn)
    hexcounter += 1
            'from strsql to end try is duplicate code: can we methodize?
    strsql = "INSERT INTO " & basetable & vbCrLf & _
                            "(Hexnum, Terraintype, Baselevel, " & _
                            "Hexside1, Hexside2, Hexside3, " & _
                            "Hexside4, Hexside5, Hexside6, " & _
                            "Control, Staircase, Hexname)" & _
                            " Values (" & CStr(hexcounter) & ", " & "6001, 0, " & _
                            "6500, 6500, 6500, 6500, 6500, 6500, " & _
                            "0, 'N', '" & hexname & "')"
    Try
            cmd = New SqlCommand(strsql, connection)
                                cmd.ExecuteNonQuery()
    result = True
            Catch
    result = False
    End Try
    Next hexcolumn
    Oddtrue = Not (Oddtrue)
    Next hexrow
    End If
    End Using
    Return result
    End Function
    Private Function PutDatafromBoardIntoScenarioHexTable(ByVal boardname As Integer,
                                                          ByVal basetable As String, ByVal Scenname As String, ByVal Boardnum(,) As String) As Boolean
            'called by MapDataC.CreateNewMapDBTables
                    'copies hex data from Board table to scenario table
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Dim connection As New SqlConnection(connectionstring)
    Dim cmd As SqlCommand
    Dim strsql As String   'hold sql text
    Dim result As Boolean
    Dim maprow As Integer = 0 : Dim Mapcol As Integer = 0  ' hold row and column values in boards
    Dim hexrow As Integer : Dim hexcolumn As Integer ' hold row and column values in hexes
    Dim Oddtrue As Boolean ' holds odd/even value of current row being used
    Dim Startnum As Integer 'used with finishhum to move across columns in a row
    Dim hexname As String = ""   'name of current hex
    Dim hexcounter As Integer = 0 'number of hex on map being processed
    Dim finishnum As Integer
    Dim Stepnum As Integer 'holds increment value for loops
    Dim Rowstart As Integer 'holds number value for starting row
    Dim Hexletter As String 'hex letter (A, AA, etc) determined by row number
    Dim boardnumstr As String 'holds string value of board number
    Dim Boardwidth As Integer  'board width is number of boards in overall map or columns for historical boards
    Dim Boardheight As Integer 'board height is number of boards in overall map or rows for historical boards
            'Dim bitwidth As Integer : Dim bitheight As Integer 'overall map dimensions in pixels

    Using connection
                connection.Open()
    If boardname > 1100 Then   'historical map
            'single bulk insertion works because map config is always the same
            'VARIATION ON THIS WOULD BE TO ALLOW FOR CROPPED MAPS
    Try
            strsql = "INSERT INTO " & Scenname & vbCrLf & _
                        "(Hexnum, Terraintype, Baselevel, " & _
                        "Hexside1, Hexside2, Hexside3, " & _
                        "Hexside4, Hexside5, Hexside6, " & _
                        "Control, Staircase, Hexname)" & _
                        "SELECT Hexnum, Terraintype, Baselevel, " & _
                        "Hexside1, Hexside2, Hexside3, " & _
                        "Hexside4, Hexside5, Hexside6, " & _
                        "Control, Staircase, Hexname " & _
                        "FROM dbo." & basetable
            cmd = New SqlCommand(strsql, connection)
                        cmd.ExecuteNonQuery()
                                'MessageBox.Show("Table " & Scenname & " successfully imports data from " & basetable, "Table Creation Status", _
                                'MessageBoxButtons.OK, MessageBoxIcon.Information)
    result = True
    Catch sqlExc As SqlException
                        MessageBox.Show(sqlExc.ToString, "SQL Exception Error!", _
    MessageBoxButtons.OK, MessageBoxIcon.Error)
    result = False
    End Try
    Else   'geomorphic board
            'each of which has 33 rows (two of which, A and GG are half-hexes)
            'and 10 columns (even numbered rows have 11 columns including 2 half-hexes, 0 and 10
            '  for one board map total hexes is  (10 * 33)+ (16 *1) = 346
            'bitwidth = GameForm.pbGameMap.Image.Width
            'bitheight = GameForm.pbGameMap.Image.Height
    For maprow = 0 To 5
    For Mapcol = 0 To 5
    If CInt(Boardnum(maprow, Mapcol)) = -999 Then Exit For
    If Math.Abs(CInt(Boardnum(maprow, Mapcol))) > 0 Then
    If Boardwidth < Mapcol Then Boardwidth = Mapcol
    If Boardheight < maprow Then Boardheight = maprow
    End If
    Next
            Next
                    'Boardwidth = CInt(Int(bitwidth / 645))      'identifies max number of boards wide
                    'Boardheight = CInt(Int(bitheight / 1799))   'and high
                    'Maprow and mapcolumn hold current board position being used in loop
    Dim firstrow As Integer = 1 'firstrow is either 1 for top row of boards, 2 of subsequent rows due to GG/A merge
    For maprow = 0 To Boardheight
                        'boardname = CInt(Gameform.Scenario.BoardNum(maprow, Mapcol))
    Oddtrue = True ' determines if a row with 2 half-hexes - Oddtrue=false (B, D, F . . . )
    If maprow > 0 Then                  'does not create "A" row for subsequent boards, merges
    firstrow = 2 : Oddtrue = False  'then with GG
    End If
    For hexrow = firstrow To 33
    For Mapcol = 0 To Boardwidth
                                'deals with situations where there is a missing board (L-shaped configuration)
    If (hexrow <> 33 And Math.Abs(CInt(Boardnum(maprow, Mapcol))) < 1) Or
            (hexrow = 33 And Math.Abs(CInt(Boardnum(maprow, Mapcol))) < 1 And
            maprow = Boardheight) Then
                                    'need to add dummy hexes
    For hexcolumn = 1 To 10
    hexcounter += 1
    strsql = "INSERT INTO " & Scenname & vbCrLf & _
                                        "(Hexnum, Terraintype, Baselevel, " & _
                                        "Hexside1, Hexside2, Hexside3, " & _
                                        "Hexside4, Hexside5, Hexside6, " & _
                                        "Control, Staircase, Hexname)" & _
                                        " Values (" & CStr(hexcounter) & ", " & "0, 0, " & _
                                        "0, 0, 0, 0, 0, 0, " & _
                                        "0, 'N', 'OffB')"
    Try
            cmd = New SqlCommand(strsql, connection)
                                            cmd.ExecuteNonQuery()
    result = True
            Catch
    result = False
    End Try
    Next hexcolumn
    ElseIf hexrow = 33 And Math.Abs(CInt(Boardnum(maprow, Mapcol))) < 1 Then
                                    'deals with upside-down L shaped map
    If Math.Abs(CInt(Boardnum(maprow + 1, Mapcol))) > 0 Then
            boardname = Math.Abs(CInt(Boardnum(maprow + 1, Mapcol)))
    boardnumstr = CStr(boardname)
    basetable = "board" & boardname
    If CInt(Boardnum(maprow + 1, Mapcol)) > 0 Then   'Board up
    Hexletter = "A"
    Startnum = 10 : Stepnum = -1 : finishnum = 1
    Else      ' Board down
    Hexletter = "GG"
    Startnum = 1 : Stepnum = 1 : finishnum = 10
    End If
    Else
            boardnumstr = "" : Hexletter = "a" : Startnum = 10 : Stepnum = -1 : finishnum = 1
    End If
    For hexcolumn = Startnum To finishnum Step Stepnum
    hexname = boardnumstr & Trim$(Hexletter) & CStr(hexcolumn)
    hexcounter += 1
    strsql = GetSQLString(Scenname, basetable, hexname)
    cmd = New SqlCommand(strsql, connection)
                                        cmd.ExecuteNonQuery()
    strsql = "Update " & Scenname & " set hexnum= " & CStr(hexcounter) & _
                                            " where hexname = '" & hexname & "'"
    Try
            cmd = New SqlCommand(strsql, connection)
                                            cmd.ExecuteNonQuery()
    result = True
            Catch
    result = False
    End Try
    Next hexcolumn
    Else  'normal rectangular configuration
    boardname = Math.Abs(CInt(Boardnum(maprow, Mapcol)))
    boardnumstr = CStr(boardname)
    basetable = "board" & boardname
            Hexletter = ""
                                    ' if boardup then count columns down from 10 to 1 or zero
                                            ' if not boardup then count columns up form 0 or 1 to 10
    If CInt(Boardnum(maprow, Mapcol)) > 0 Then 'Board is up - A is top row
    finishnum = If(Oddtrue, 1, 0)
    Startnum = 10 : Stepnum = -1
            ' figures out which letter to use for first row and then
            ' works up or down accordingly
    If hexrow < 27 Then
            Rowstart = 64 + hexrow
    Else
            Rowstart = 64 + hexrow - 26
    Hexletter = Chr(Rowstart)
    End If
    Else   'Board is down - GG is top row
    Startnum = If(Oddtrue, 1, 0)
    finishnum = 10 : Stepnum = 1
    If hexrow < 8 Then
            Rowstart = 91 - hexrow - 19
    Hexletter = Chr(Rowstart)
    Else
            Rowstart = 91 - hexrow + 7
    End If
    End If
    For hexcolumn = Startnum To finishnum Step Stepnum
    If Not (Oddtrue) And hexcolumn = 0 And Mapcol < (Boardwidth) And Math.Abs(CInt(Boardnum(maprow, Mapcol + 1))) > 0 Then
                                            'if halfhex in board which has a board to
                                                    'the right of it, add nothing, the next halfhex will
                                                    'cover the full hex
    Else
    If Trim(CStr(boardname)) = "" And hexcolumn = Startnum Then
    Dim Checkhexstring As String = hexname
    Dim Sublen As Integer = Len(Checkhexstring)
    hexname = Left(Checkhexstring, Sublen - 1)
    hexcounter += 1
    If Right(Checkhexstring, 1) = "9" Then
    hexname &= "10"
    Else
    hexname &= "0"
    End If
    strsql = GetSQLString(Scenname, basetable, hexname)
    Else
            hexname = Trim(CStr(boardname)) & Trim$(Hexletter) & Chr(Rowstart) & CStr(hexcolumn)
    hexcounter += 1
    strsql = GetSQLString(Scenname, basetable, hexname)
    End If
    cmd = New SqlCommand(strsql, connection)
                                            cmd.ExecuteNonQuery()
    strsql = "Update " & Scenname & " set hexnum= " & CStr(hexcounter) & _
                                            " where hexname = '" & hexname & "'"
    Try
            cmd = New SqlCommand(strsql, connection)
                                                cmd.ExecuteNonQuery()
    result = True
            Catch
    result = False
    End Try
    End If
    Next hexcolumn
    End If
    Next Mapcol
    Oddtrue = Not (Oddtrue)
    Next hexrow
    Next maprow
    End If
    End Using
    Return result
    End Function
    Private Function PutDatafromBoardIntoScenarioLOSTable(ByVal boardname As Integer, ByVal LOSbasetable As String,
                                                          ByVal LOSScenname As String, ByVal Boardnum(,) As String) As Boolean
    Dim result As Boolean
    Dim maprow As Integer = 0 : Dim Mapcol As Integer = 0  ' hold row and column values in boards
            'Dim hexname As String = ""   'name of current hex
            'Dim hexcounter As Integer = 0 'number of hex on map being processed
    Dim Boardwidth As Integer  'board width is number of boards in overall map or columns for historical boards
    Dim Boardheight As Integer 'board height is number of boards in overall map or rows for historical boards

    If boardname > ConstantClassLibrary.ASLXNA.Map.CreateMap Then   'historical map
    result = If(CopyLOSBoardtoLoScenData(boardname, LOSbasetable, LOSScenname) = True, True, False)
    Else  'geomorphic map
            'Boardwidth = CInt(Int(GameForm.pbGameMap.Image.Width / 600))      'identifies max number of boards wide
                'Boardheight = CInt(Int(GameForm.pbGameMap.Image.Height / 1700))
    For maprow = 0 To 5
    For Mapcol = 0 To 5
    If CInt(Boardnum(maprow, Mapcol)) = -999 Then Exit For
    If Math.Abs(CInt(Boardnum(maprow, Mapcol))) > 0 Then
    If Boardwidth < Mapcol Then Boardwidth = Mapcol
    If Boardheight < maprow Then Boardheight = maprow
    End If
    Next
            Next
    For maprow = 0 To Boardheight
    For Mapcol = 0 To Boardwidth
    boardname = Math.Abs(CInt(Boardnum(maprow, Mapcol)))
    If boardname <> 0 Then
            LOSbasetable = "LOSBoard" & boardname.ToString
    result = If(CopyLOSBoardtoLoScenData(boardname, LOSbasetable, LOSScenname) = True, True, False)
    End If
    Next Mapcol
    Next maprow
    End If
    Return result
    End Function
    Private Function CopyLOSBoardtoLoScenData(ByVal boardname As Integer, ByVal LOSbasetable As String,
                                              ByVal LOSScenname As String) As Boolean
            'called by Linqdata.PutDatafromBoardIntoScenarioLOSTable
                    'moves data (if any exists) from Board LOS table(s) into scenario LOS table
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Dim connection As New SqlConnection(connectionstring)
    Dim cmd As SqlCommand
    Dim strsql As String   'hold sql text
    Dim Result As Boolean
    If Not DoesTableDataExist(LOSbasetable) Then 'table is empty
            'MessageBox.Show("Table " & LOSbasetable & " does not contain records. No data import required.", "Table Creation Status", _
            '        MessageBoxButtons.OK, MessageBoxIcon.Information)
    Return True  'Can still proceed; an empty LOS table is not an error
    End If
            'table has data
    Using connection
                connection.Open()
    For i As Integer = 1 To 2
    If i = 1 Then
            strsql = "INSERT INTO " & LOSScenname & vbCrLf &
            "(FirerLOSHex, FirerLOSLevel, TargetLOSHex, TargetLOSLevel, Clear) " &
            "SELECT FirerLOSHex, FirerLOSLevel, TargetLOSHex, TargetLOSLevel, Clear " &
            "FROM dbo." & LOSbasetable
    Else
            strsql = "Update " & LOSScenname & " set FirerLOSBoard= '" & boardname & _
                        "', " & "TargetLOSBoard='" & boardname & "'"
    End If
    Try
            cmd = New SqlCommand(strsql, connection)
                        cmd.ExecuteNonQuery()
                                'MessageBox.Show("Table " & LOSScenname & " successfully imports data from " & LOSbasetable, "Table Creation Status", _
                                'MessageBoxButtons.OK, MessageBoxIcon.Information)
    Result = True
    Catch ex As Exception
                        MessageBox.Show("Table " & LOSScenname & " did not accept LOSBoard records from " & LOSbasetable, "Houston, we have a problem")
    Result = False
    End Try
    Next i
    End Using
    Return If(Result = True, True, False)
    End Function
    Friend Function GetHistMapTableName(ByVal Maptype As Integer) As String
            'called by Linqdata.CreateNewMapDbTables
    Select Case Maptype
    Case ConstantClassLibrary.ASLXNA.Map.BloodReef
    Return "BloodReefTarawaMap"
    Case ConstantClassLibrary.ASLXNA.Map.PegasusBridge
    Return "PegasusBridgeMap"
    Case ConstantClassLibrary.ASLXNA.Map.Stoumont
    Return "StoumontMap"    '"KGPMap"
    Case ConstantClassLibrary.ASLXNA.Map.RedB
    Return "RedBarricadesMap"
    Case ConstantClassLibrary.ASLXNA.Map.LaGleize
    Return "LaGleizeMap"
    Case ConstantClassLibrary.ASLXNA.Map.Cheneux
    Return "CheneuxMap"
    Case ConstantClassLibrary.ASLXNA.Map.VotG
    Return "VotGMap"
    Case Else
    Return Nothing
    End Select
    End Function

    Friend Function GetConnectionString(ByVal WhichOne As Integer) As String
            'called by routines in Terraindata to create connection string to use in database connection
                    'Value of WhichOne determines which database connection string is returned
    Select Case WhichOne
    Case 1  'ASLSQL_data
    GetConnectionString = "Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\ASLSQL_data.mdf;Integrated Security=True;Connect Timeout=30;User Instance=true"
    Case 2  'ASLMaps
    GetConnectionString =
    My.Settings.ASLMapsConnectionString
                    '"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\ASLMaps.mdf;Integrated Security=True;Connect Timeout=30;User Instance=true"
    Case Else
    GetConnectionString =
    InputBox("Enter connection string: ", "Parameter failed in Terraindata.GetConnectionString", , , )
    End Select
    End Function
    Private Sub MakeAdjustmentsToHexes(ByVal boardname As Integer, ByVal Boardtype As Integer, ByVal bitmapheight As Single, ByVal bitmapwidth As Single,
                                       ByRef Maprows As Integer, ByRef MapCols As Integer, ByRef MapXoffset As Integer, ByRef MapYOffset As Integer)
            'called by PopulateNewBoardTable; copied from Scenario class
                    'makes adjustment to row and columns count and to xoffset and yoffset to reflect different
                    'board layouts and confugurations
                    'Calculate number of cols and rows for geomorphic; use defaults for historical
                    'use map type property to adjust offsets
    Select Case Boardtype
    Case 0  'geopmorphic
    Maprows = CInt(bitmapheight / 56) + 1
    MapCols = CInt(bitmapwidth / 645) * 11
    MapXoffset = 0
    MapYOffset = -37
    Case 1  'BRT
            'all rows have same number of hexes and one half hex which alternates from right side
            'to left side
    MapCols = 63 : Maprows = 26
    MapYOffset = -37
    Case 2  'PB/ST
            'all rows have same number of hexes and one half hex which alternates from left side
            'to right side and thus needs XOffset adjustment
            'use scenario data to set MapBtype
    If boardname = ConstantClassLibrary.ASLXNA.Map.Stoumont Then
            MapCols = 57 : Maprows = 46
    Else  'PB
    MapCols = 29 : Maprows = 46
    End If
    MapXoffset = -32
    MapYOffset = -37
            'MapCols += 1
    Case 3  'RB/LG
            'same configuration as geomorphic except left hand side even row half hexes
            'don't exist in LG - but program has to treat them as if they do
    MapXoffset = 0
            'use scenario data to set MapBtype
    If boardname = ConstantClassLibrary.ASLXNA.Map.LaGleize Then
            MapCols = 58 : Maprows = 46
    Else
            MapCols = 46 : Maprows = 36
    End If
    MapYOffset = -37
    Case 4  'CH
            'first and last row are full height so need to adjust Maprows
            'columns have same configuration as Case 2 - top row left side has half width hex so
            'need Xoffset adjustment
    MapCols = 29 : Maprows = 46
    MapXoffset = -32

    Case 5  'VotG
            'same column configuration as geomorphic except A is full height so no YOffset adjustment
            'same row configuration as geomorphic so no XOffset adjustment
            'Last row is halfheight row but has no impact
    MapCols = 51 : Maprows = 41
    End Select

    End Sub
    Private Function GetBoardtype(ByVal MapinUse As Integer) As Integer
            'called by lots of Mapactions routines
    Select Case MapinUse
    Case ConstantClassLibrary.ASLXNA.Map.BloodReef
    Return 1
    Case ConstantClassLibrary.ASLXNA.Map.PegasusBridge, ConstantClassLibrary.ASLXNA.Map.Stoumont
    Return 2
    Case ConstantClassLibrary.ASLXNA.Map.RedB, ConstantClassLibrary.ASLXNA.Map.LaGleize
    Return 3
    Case ConstantClassLibrary.ASLXNA.Map.Cheneux
    Return 4
    Case ConstantClassLibrary.ASLXNA.Map.VotG
    Return 5
    Case Else  'geomorphic
    Return 0
    End Select
    End Function
    */
    private boolean PopulateGameLocationTable(String Scenname) {
        // called by MapDataC.CreateNewMapDBTables
        // copies hex data from Scenario Map table to GameLocation table

        /*
        Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
        Dim connection As New SqlConnection(connectionstring)
        Dim cmd As SqlCommand
        Dim strsql As String 'hold sql text
        Dim result As Boolean

        Using connection
        connection.Open()
        Try
                strsql = "delete from dbo.GameLocation "
        cmd = New SqlCommand(strsql, connection)
        cmd.ExecuteNonQuery()
        'MessageBox.Show("Table GameLocation successfully deletes old data", "Table Creation Status", _
        'MessageBoxButtons.OK, MessageBoxIcon.Information)
        result = True
        Catch sqlExc As SqlException
        MessageBox.Show(sqlExc.ToString, "SQL Exception Error!", _
                MessageBoxButtons.OK, MessageBoxIcon.Error)
        result = False
        End Try
        Try
                strsql = "INSERT INTO dbo.GameLocation " & vbCrLf & _
        " SELECT * FROM dbo." & Scenname
        cmd = New SqlCommand(strsql, connection)
        cmd.ExecuteNonQuery()
        'MessageBox.Show("Table GameLocation successfully imports data from " & Scenname, "Table Creation Status", _
        'MessageBoxButtons.OK, MessageBoxIcon.Information)
        result = True
        Catch sqlExc As SqlException
        MessageBox.Show(sqlExc.ToString, "SQL Exception Error!", _
                MessageBoxButtons.OK, MessageBoxIcon.Error)
        result = False
        End Try

        End Using
        */
        return false; //result
    }

            /*
    Private Function PopulateGameLOSTable(ByVal LOSname As String) As Boolean
            'NOT implemented yet
                    'called by MapDataC.CreateNewMapDBTables
                    'copies hex data from ScenarioLOS table to  GameLOS table
    Dim connectionstring As String = GetConnectionString(2) 'standard variables for connecting to database
    Dim connection As New SqlConnection(connectionstring)
    Dim cmd As SqlCommand
    Dim strsql As String   'hold sql text
    Dim result As Boolean

    Using connection
                connection.Open()
    Try
            strsql = "delete from dbo.GameLOS "
    cmd = New SqlCommand(strsql, connection)
                    cmd.ExecuteNonQuery()
                            'MessageBox.Show("Table GameLOS successfully deletes old data", "Table Creation Status", _
                            'MessageBoxButtons.OK, MessageBoxIcon.Information)
    result = True
    Catch sqlExc As SqlException
                    MessageBox.Show(sqlExc.ToString, "SQL Exception Error!", _
    MessageBoxButtons.OK, MessageBoxIcon.Error)
    result = False
    End Try
    Try
            strsql = "INSERT INTO dbo.GameLOS " & vbCrLf & _
                    " SELECT * FROM dbo." & LOSname
            cmd = New SqlCommand(strsql, connection)
                    cmd.ExecuteNonQuery()
                            'MessageBox.Show("Table GameLOS successfully imports data from " & LOSname, "Table Creation Status", _
                            'MessageBoxButtons.OK, MessageBoxIcon.Information)
    result = True
    Catch sqlExc As SqlException
                    MessageBox.Show(sqlExc.ToString, "SQL Exception Error!", _
    MessageBoxButtons.OK, MessageBoxIcon.Error)
    result = False
    End Try

    End Using
    Return result
    End Function
        'admin function - one time use but could be repurposed for other uses
    Public Function GetMapDatabase() As MapDataClassesDataContext
    Dim db2 As MapDataClassesDataContext = New MapDataClassesDataContext
    Return db2
    End Function
#End Region*/
}
