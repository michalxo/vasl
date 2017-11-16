package VASL.build.module.fullrules.TerrainClasses;

import VASL.build.module.fullrules.Constantvalues;

import java.util.LinkedList;

public class ManageScenarioTerrain {
    public ManageScenarioTerrain(){}

    /*public LinkedList<GameLocation> ShowScenarioTerrain(LinkedList<GameLocation> LocationCol) {
        // called by Terrainactions.new
        // takes records from the map data table where counters are necessary
        try {

            *//*Return From QU As MapDataClassLibrary.GameLocation In LocationCol Where QU.ShowCounter = True
            Or(QU.IsWire And QU.WireVisible) Or(QU.APMines > 0And QU.APMinesVisible)
            Or(QU.ATMines > 0And QU.ATMinesVisible) Or QU.Smoke > 0
            Catch
            MessageBox.Show("No Scenario Terrain counters to show")
            Return Nothing
            End Try
            End Function
            'overloaded, first uses locindex second uses hex and loc
            Public Function DoesLocationContain(ByVal LookingFor As Integer, ByVal Locationnumber As Integer,
                    ByVal Mapdata As IQueryable(Of MapDataClassLibrary.GameLocation)) As Boolean
            '
            'determines if a location contains a particular feature
            Dim GetLocs = New GetALocationFromMapTable(Mapdata)
            'PARTIAL IMPLEMENTATION ONLY - EXPAND ON THIS FEB 12
            Dim LocFound As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(Locationnumber)
            Select Case LookingFor
            Case constantclasslibrary.aslxna.Feature.Wire
            If LocFound.IsWire Then Return True
            Case ConstantClassLibrary.ASLXNA.Feature.APMines
            If LocFound.APMines > 0 Then Return True
            Case ConstantClassLibrary.ASLXNA.Location.Smoketype
            If LocFound.Smoke > 0 Then Return True
            End Select
            Return False*//*
        } catch (Exception e) {

        }
        return null;
    }*/
    public boolean IsFeatureVisHind(int Featuretype) {
        /*'called by various terrain and LOSH functions

        'needs to add building blaze types
        If Featuretype >=ConstantClassLibrary.ASLXNA.VisHind.VehDust And
        Featuretype <= ConstantClassLibrary.ASLXNA.VisHind.GreyWPDisp Then Return True Else*/
        return false;
    }
        /*'Public Function DoesLocationContain(ByVal LookingFor As Integer, ByVal Hexnumber As Integer, ByVal Locationnumber As Integer,
                '    ByVal Mapdata As IQueryable(Of MapDataClassLibrary.GameLocation)) As Boolean
                '    'called by EnterAndClear.moveupdate
        '    'determines if a location contains a particular feature
        '    Dim GetLocs = New GetALocationFromMapTable(Mapdata)
                '    'PARTIAL IMPLEMENTATION ONLY - EXPAND ON THIS FEB 12
            '    Dim LocFound As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(Hexnumber, Locationnumber)
            '    Select Case LookingFor
            '        Case Feature.Wire
            '            If LocFound.IsWire Then Return True
            '        Case Feature.APMines
            '            If LocFound.APMines > 0 Then Return True
            '        Case Location.Smoketype
            '            If LocFound.Smoke > 0 Then Return True
            '    End Select
            '    Return False
            'End Function*/
    /*public GameLocation UpdateLocation(int LocIndexChange, int ChangetoMake, LinkedList<GameLocation> Mapdata) {
        GameLocation LocFound=null;
        *//*Dim GetLocs = New GetALocationFromMapTable(Mapdata) :Dim nextlocfound As MapDataClassLibrary.GameLocation
        Dim LocFound As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(LocIndexChange)
        Select Case ChangetoMake
        Case ConstantClassLibrary.ASLXNA.VisHind.VehDust To ConstantClassLibrary.ASLXNA.VisHind.GreyWPDisp
        LocFound.Smoke = ChangetoMake
        LocFound.SmokeBaseLevel = CInt(LocFound.LevelInHex)
        'need to add smoke to higher levels
        'determine Smoke height
        Dim SmokeHeight As Integer = GetSmokeHeight(LocFound.Smoke)
        If SmokeHeight <=0 Then Return Nothing
        Dim CurrentLevelHexnum As Integer = LocFound.Hexnum
        Dim CurrentLevelLocation As Integer = LocFound.Location
        For NextLocUp = 1 To SmokeHeight
        Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Mapdata)
        Dim NextUp As Integer = LevelChk.GetNextLevelUp(CurrentLevelHexnum, CurrentLevelLocation, 0)
        'check level exists and get it
        If NextUp = 99 Then Exit For 'no more levels exist
        nextlocfound = LevelChk.GetLocationatLevelInHex(LocFound.Hexnum, NextUp)
        nextlocfound.Smoke = ChangetoMake
        nextlocfound.SmokeBaseLevel = CInt(LocFound.LevelInHex)
        CurrentLevelHexnum = nextlocfound.Hexnum :CurrentLevelLocation = nextlocfound.Location
        Next NextLocUp
        End Select
        'Dim UpdatedMapTable As IQueryable(Of MapDataClassLibrary.GameLocation) = Mapdata*//*
        return LocFound;  // UpdatedMapTable
    }*/
    public int GetSmokeHeight(Constantvalues.VisHind Smoketype) {
        /*'called by Me.UpdateLocation
        'determines height of smoke added
        Select Case Smoketype
        Case ConstantClassLibrary.
        ASLXNA.VisHind.BlazeStone, ConstantClassLibrary.ASLXNA.VisHind.BlazeWood, ConstantClassLibrary.ASLXNA.VisHind.GreyWP, ConstantClassLibrary.ASLXNA.VisHind.GreyWPDisp, ConstantClassLibrary.ASLXNA.VisHind.GunWP, ConstantClassLibrary.ASLXNA.VisHind.GunWPDisp, ConstantClassLibrary.ASLXNA.VisHind.InfWP
        Return 4
        Case ConstantClassLibrary.
        ASLXNA.VisHind.GreyDisp, ConstantClassLibrary.ASLXNA.VisHind.GunSmoke, ConstantClassLibrary.ASLXNA.VisHind.GunSmokeDisp, ConstantClassLibrary.ASLXNA.VisHind.InfSmoke, ConstantClassLibrary.ASLXNA.VisHind.OBASmoke, ConstantClassLibrary.ASLXNA.VisHind.OBASmokeDisp, ConstantClassLibrary.ASLXNA.VisHind.VehDust
        Return 2
        Case ConstantClassLibrary.ASLXNA.VisHind.Flame, ConstantClassLibrary.ASLXNA.VisHind.HamperedFlame
        Return 0
        Case Else
        Return 0
        End Select*/
        return 0;
    }
}
