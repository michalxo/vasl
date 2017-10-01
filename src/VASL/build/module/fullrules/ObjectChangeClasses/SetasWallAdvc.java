package VASL.build.module.fullrules.ObjectChangeClasses;

public class SetasWallAdvc {
    /*Implements OBUnitChange
    Private ActiveUnitOB As DataClassLibrary.OrderofBattle
    Private WallAdvPosition As Integer
    Private Linqdata As DataClassLibrary.ASLXNA.DataC
    Public Sub New(ByVal unitid As Integer, ByVal PassWAPosition As Integer)
    Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()
    ActiveUnitOB = Linqdata.GetUnitfromCol(unitid)
    WallAdvPosition = PassWAPosition
    End Sub
    Public Function MakeUnitWallAdv() As Boolean Implements OBUnitChange.TakeAction
            'set position
    ActiveUnitOB.Position = WallAdvPosition
            'update location
    Dim ASLMapLink As String = "Scen" & CStr(CInt(ActiveUnitOB.Scenario))
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ASLMapLink, CInt(ActiveUnitOB.Scenario))
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
    Dim Levelchk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim BaseHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(CInt(ActiveUnitOB.hexnum), 0)
    ActiveUnitOB.hexlocation = CInt(BaseHex.Location)
            MessageBox.Show(ActiveUnitOB.OBName & " claims wall advantage")
            'if concealed update concealment
    If ActiveUnitOB.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed Then
    Dim UpdateCon = New SetConWAc(ActiveUnitOB.Con_ID, CInt(ActiveUnitOB.Position))
    End If
            'update any SW counters associated with this unit
    Dim OBSWitem As DataClassLibrary.OrderofBattleSW
    If ActiveUnitOB.FirstSWLink > 0 Then
            OBSWitem = Linqdata.GetOBSWRecord(CInt(ActiveUnitOB.FirstSWLink))
    OBSWitem.Hexlocation = CShort(ActiveUnitOB.hexlocation)
    OBSWitem.Position = ActiveUnitOB.Position
    End If
    If ActiveUnitOB.SecondSWlink > 0 Then
            OBSWitem = Linqdata.GetOBSWRecord(CInt(ActiveUnitOB.SecondSWlink))
    OBSWitem.Hexlocation = CShort(ActiveUnitOB.hexlocation)
    OBSWitem.Position = ActiveUnitOB.Position
    End If
    Return True
    End Function*/
}
