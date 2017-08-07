package VASL.build.module.fullrules.LOSClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.AltHexGTerrain;
import VASL.build.module.fullrules.ObjectClasses.CombatTerrain;

import java.util.LinkedList;

public class ThreadedLOSCheckC {
    //'handles LOSCheck in thread - uses thread safe version of ASLXNA.LOSCheckClass.LOSCheck
    public LinkedList<CombatTerrain> TempCombatTerrCol = new LinkedList<CombatTerrain>();  //  'holds CombatTerrain instances before LOS confirmed
    public LinkedList<AltHexGTerrain> TempAltHexLOSGroup = new LinkedList<AltHexGTerrain>(); //     'holds Althexgrain instances before los confirmed

    // temporary while debugging undo
    /*Private MapCol As IQueryable(Of MapDataClassLibrary.GameLocation)   'holds collection of all gamelocations in the current map
    Private NewDb As MapDataClassLibrary.MapDataClassesDataContext*/
    public ThreadedLOSCheckC() {

// temporary while deguggging undo
    /*NewDb =
    New MapDataClassLibrary.
    MapDataClassesDataContext
            Try
    Dim LOCCol

    As IQueryable(Of MapDataClassLibrary.GameLocation) =
    From QU
    As MapDataClassLibrary.
    GameLocation In
    NewDb.GameLocations Select
    QU
            MapCol = LOCCol
    Catch ex
    As Exception
                ' MsgBox("Some kind of error" & ASLMapLink, , "CreateMapCollection Failure")
    End Try*/
}
    public Constantvalues.LosStatus LOSCheck(TempSolution TempSol) {
        // temporary while debugging undo
        /*'called by EnemyValuesConcreteC.SetLOSFPdrmValues
        'calls the sightcheck  to determine if LOS exists
        Return DoSightCheck (TempSol, NewLOSChecks)
        'Tempsol is the LOS solution to be tested, ScenMap is the map in use, NewLOSChecks are los check results to be sent to the ASLMap LOS tables*/
        return null;
    }
    // temporary while debugging undo
    /*Private Function DoSightCheck(ByVal Tempsol As LOSClassLibrary.ASLXNA.TempSolution, ByRef NewLOSResults As List(Of MapDataClassLibrary.GameLO)) As Integer
            'called by Me.LOSCheck
                    'processes a LOS Check based on a fire solution; returns value of LOS: 0=yes, enumcon.LOSSTatus.none=NO
    Dim Checkdone As Boolean = False 'holds value of whether LOS result already stored in database
    Dim IsClear As Boolean = False  'holds value of LOS result already stored in database
    Dim PassHexname As String = "" : Dim PassLOSResult As Integer = 0
            'Dim HexbyHexClear As Boolean = False
    Dim MapTableInstance = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)  'use null values when sure instance exists
    Dim PassSmokelist = New List(Of ObjectClassLibrary.ASLXNA.SmokeHolder)
    Static Dim Lasthex As Integer  'holds value of hex most recently checked
    Dim ScenID As Integer = 0   'holds value of current scenario
    Dim ThreadedCommonMethods As ThreadedLOSCheckCommonc 'class that holds LOS routines common to all ThreadedSightChecki classes
            'first check LOS database table for existing result
    With Tempsol
                'checks to see if a LOSCheck result exists in GameLOS table; if exists, returns IsClear value
    Dim LOSValidCheck As MapDataClassLibrary.GameLO
            Try
                    'need to check both combinations as LOS is reciprocal
    LOSValidCheck = (From QU As MapDataClassLibrary.GameLO In NewDb.GameLOs Where (QU.FirerLocIndex = CInt(Tempsol.SeeLOSIndex) And QU.TargetLocIndex = CInt(Tempsol.SeenLOSIndex)) Or
            (QU.FirerLocIndex = CInt(Tempsol.SeenLOSIndex) And QU.TargetLocIndex = CInt(Tempsol.SeeLOSIndex))).First
            Checkdone = True
    IsClear = LOSValidCheck.IsClear
    Catch ex As Exception
    Checkdone = False  'no LOS result in GameLOS table
    IsClear = False
    End Try
    Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)   'class for various data-based terrain checks
    ThreadedCommonMethods = New ThreadedLOSCheckCommonc(TerrChk, MapCol)
    If IsClear Then  'database says LOS clear but need to do some checks
    Dim ClearSightCheck As ThreadedSightChecki = New ThreadedClearSightCheck(MapTableInstance, Lasthex, Tempsol, MapCol, TerrChk, ScenID, ThreadedCommonMethods)
    PassLOSResult = ClearSightCheck.ThreadedDoSightCheck()
    Else  'no clear result in database; either blocked or check not done before
    Dim DoLOSTest As Boolean = True : Dim UsingPositionNoResultSave As Boolean = False
                    'determine if see or seen is in crest status; if so, LOS results are not stored
    Dim TerrTest As New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
    If (TerrTest.IsLocationTerrainA(CInt(.SeenLOSIndex), ConstantClassLibrary.ASLXNA.Location.Creststatustype) And .SeenLevelInHex = 1) Or
            (TerrTest.IsLocationTerrainA(CInt(.SeeLOSIndex), ConstantClassLibrary.ASLXNA.Location.Creststatustype) And .SeeLevelInHex = 1) Then
            UsingPositionNoResultSave = True  'don't store loc-loc result as using a position such as crest which could have different result
    End If
    If Checkdone Then
                        'Check exists in database and LOS is blocked
                                'if unit is in crest status then need to do new check; results are NOT stored in the LOS file
    If Not UsingPositionNoResultSave Then
    PassHexname = ""
    PassLOSResult = ConstantClassLibrary.ASLXNA.LosStatus.None
            DoLOSTest = False
    End If
    End If
    If DoLOSTest Then   'need to do a full LOS check including pixel by pixel to check for obstacle block
    Dim NewSightCheck As ThreadedSightChecki = New ThreadedNewSightCheck(MapTableInstance, Lasthex, Tempsol, MapCol, TerrChk, ScenID, ThreadedCommonMethods, UsingPositionNoResultSave, TerrTest, NewLOSResults)
    PassLOSResult = NewSightCheck.ThreadedDoSightCheck()
    End If
    End If
    End With
            'transfer combat terrain  and Althexlos involved in each thread to common collection (TempCombatTerrCol)
    For Each TempComTerr As objectclasslibrary.aslxna.combatterrain In ThreadedCommonMethods.TempCombatTerrColCommon
                TempCombatTerrCol.Add(TempComTerr)
    Next
    If ThreadedCommonMethods.TempAltHexLOSGroupCommon.Count > 0 Then
    For Each TempAltHex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In ThreadedCommonMethods.TempAltHexLOSGroupCommon
                    TempAltHexLOSGroup.Add(TempAltHex)
    Next
    End If
            'return LOS Result; 0=yes, PassLOSResult=enumcon.losstatus.none = no
    Return PassLOSResult
    End Function*/
}
