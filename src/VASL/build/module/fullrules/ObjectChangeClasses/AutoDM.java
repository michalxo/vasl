package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class AutoDM {
    // handles Adjacent DM check and change
    private int hexnum;
    private int hexlocation;
    private int hexposition;
    private int Stacknat;
    private int ScenID;
    private ScenarioCollectionsc scencolls  = ScenarioCollectionsc.getInstance();
    private LinkedList<Integer> myHexesToDm = new LinkedList<java.lang.Integer>();

    /*public AutoDM(LinkedList<PersUniti> PassUnitList ) {
        // called by various methods where action is sufficient to DM enemy units, mainly in Movement and ObjectChange
        // automatically DMs ADJACENT broken enemy
        // PassUnit is the stack of units that cause DM, need to find enemy brokies ADJACENT to it
        int AllConc = 2;
        // Determine stack visibility status - need to be known
        VisibilityC VisibilityStatus = new VisibilityC();
        int EnteringVisStatus = VisibilityStatus.GetStackStatus(PassUnitList);
        if (EnteringVisStatus != AllConc) {
            for (PersUniti StackUnit: PassUnitList) {
                if (StackUnit.getbaseunit().getVisibilityStatus() != Constantvalues.VisibilityStatus.Visible) {
                    continue; // concealed units do not cause DM on adjacent units
                }
                if (StackUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Unarmed) {
                    // DM any ADJACENT broken enemy
                    hexnum = StackUnit.getbaseunit().getHexnum();
                    hexlocation = StackUnit.getbaseunit().gethexlocation();
                    hexposition = StackUnit.getbaseunit().gethexPosition();
                    Stacknat = StackUnit.getbaseunit().getNationality();
                    ScenID = StackUnit.getbaseunit().getScenario;
                    MakeAdjacentDM();
                    break; // only need to do once
                }
            }
        }
    }
    public AutoDM(int PassUnitID ) {
        // called by various methods where action is sufficient to DM enemy units, mainly in Movement and ObjectChange
        // automatically DMs ADJACENT broken enemy
        // PassUnitID is the unit that can cause DM, need to find enemy brokies ADJACENT to it
        int AllConc = 2;
        Dim PassUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From GetUnit As ObjectClassLibrary.ASLXNA.PersUniti In
        scencolls.Unitcol Where GetUnit.BasePersUnit.Unit_ID = PassUnitID Select GetUnit).First
        Dim PassUnitList As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =New List (Of
        ObjectClassLibrary.ASLXNA.PersUniti)
        PassUnitList.Add(PassUnit)
        'Determine stack visibility status - need to be known
        Dim VisibilityStatus = New VisibilityC()
        Dim EnteringVisStatus = VisibilityStatus.GetStackStatus(PassUnitList)
        If EnteringVisStatus <>AllConc Then
        For Each StackUnit As ObjectClassLibrary.ASLXNA.PersUniti In PassUnitList
        If StackUnit.BasePersUnit.VisibilityStatus<> ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible Then
        Continue For 'concealed units do not cause DM on adjacent units
        If StackUnit.BasePersUnit.OrderStatus<> ConstantClassLibrary.ASLXNA.OrderStatus.Unarmed Then
        'DM any ADJACENT broken enemy
        hexnum = StackUnit.BasePersUnit.Hexnum
        hexlocation = StackUnit.BasePersUnit.hexlocation
        hexposition = StackUnit.BasePersUnit.hexPosition
        Stacknat = StackUnit.BasePersUnit.Nationality
        ScenID = StackUnit.BasePersUnit.Scenario
        MakeAdjacentDM()
        Exit For 'only need to do once
        End If
        Next
        End If
    }
    Public ReadOnly Property HexesToDM As List(Of Integer)
    Get
    return myHexesToDM
    End Get

    End Property
    private Sub MakeAdjacentDM()
    Dim EnemyPresent As Boolean = False : Dim ADJACENTLocs As List(Of MapDataClassLibrary.GameLocation)
    Dim Mapcol As IQueryable(Of MapDataClassLibrary.GameLocation)
    Dim NewMap = New UtilWObj.ASLXNA.NewMapDB
            Mapcol = NewMap.GetMapCol
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()
            'first get list of adjacent locations
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
    Dim PassHexloc = Getlocs.RetrieveLocationfromMaptable(hexnum, hexlocation)
    Dim ADJTest As New CombatTerrainClassLibrary.ASLXNA.HexBesideC(PassHexloc, Nothing, hexposition)
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'get ADJACENT locations
    ADJACENTLocs = ADJTest.AllADJACENTLocations()
            'check for enemy present in each ADJACENT location
    For Each LocToCheck As MapDataClassLibrary.GameLocation In ADJACENTLocs
                'Determine if present units (both real and dummy) are enemy to the moving side
    Dim CheckforEnemy = New UtilWObj.ASLXNA.EnemyChecksC(LocToCheck.LocIndex, Stacknat, ScenID)
    EnemyPresent = CheckforEnemy.EnemyInHexTest
    If EnemyPresent Then
                    'if found then get hex contents
    Dim ContentsofLocationToBeSearched = New UtilWObj.ASLXNA.ContentsofLocation(LocToCheck.LocIndex)
            ContentsofLocationToBeSearched.GetContents()
    If Not IsNothing(ContentsofLocationToBeSearched.ContentsInLocation) Then
                        'look for broken infantry of other side
    For Each ItemInHex As ObjectClassLibrary.ASLXNA.LocationContent In ContentsofLocationToBeSearched.ContentsInLocation
    If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ItemInHex.TypeID) Then
                                'infantry
    Dim hexUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = ItemInHex.ObjID Select selunit).First
                                'side and visibility check
    If Not (hexUnit.BasePersUnit.Nationality = Stacknat) AndAlso hexUnit.BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible Then
                                    'order check - must be broken or disrupted
    If hexUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken Then
    Dim SetDM As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitDMsc
                                        SetDM.Takeaction(hexUnit)
            myHexesToDm.Add(hexUnit.BasePersUnit.Hexnum)
    ElseIf hexUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Disrupted Then
    Dim SetDM As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitDisruptDMsc
                                        SetDM.Takeaction(hexUnit)
            myHexesToDm.Add(hexUnit.BasePersUnit.Hexnum)
    End If
    End If
    End If
    Next
    End If
    End If
    Next
    End Sub*/
}
