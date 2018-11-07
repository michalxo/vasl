package VASL.build.module.fullrules.ObjectChangeClasses;

public class RemoveWallAdvc {
   /* Implements OBUnitChange
    Private ActiveUnitOB As DataClassLibrary.OrderofBattle
    Private linqdata As DataClassLibrary.ASLXNA.DataC
    Public Sub New(ByVal LostUnit As Integer)
    Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()
    ActiveUnitOB = Linqdata.GetUnitfromCol(LostUnit)
    End Sub
    Public Function RemoveUnitWallAdv() As Boolean Implements OBUnitChange.TakeAction

    Dim ASLMapLink As String = "Scen" & CStr(CInt(ActiveUnitOB.Scenario))
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ASLMapLink, CInt(ActiveUnitOB.Scenario))
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
    Dim Levelchk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
            'set position and update location to base hex
    If ActiveUnitOB.Position = CShort(ConstantClassLibrary.ASLXNA.AltPos.WallAdv) Then
    ActiveUnitOB.Position = 0
    Dim GetBaseHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(CInt(ActiveUnitOB.hexnum), 0)
    ActiveUnitOB.hexlocation = CInt(GetBaseHex.Location) 'always baseloc when doing WA
    ElseIf ActiveUnitOB.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 AndAlso ActiveUnitOB.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5 Then
                'set position to crest status and update location to base hex
    ActiveUnitOB.Position = WACresttoCrest(CInt(ActiveUnitOB.Position))
    Dim GetBaseHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(CInt(ActiveUnitOB.hexnum), 0)
    ActiveUnitOB.hexlocation = CInt(GetBaseHex.Location) 'always baseloc when doing WA
    End If
    If ActiveUnitOB.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible Then
                MessageBox.Show(ActiveUnitOB.OBName & " loses wall advantage")
    ElseIf ActiveUnitOB.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed Then
                'update concealment counter
    Dim UpdateCon = New RemoveConWAc(ActiveUnitOB.Con_ID, CInt(ActiveUnitOB.Position))
    End If
            'update any SW counters associated with this unit
    Dim OBSWitem As DataClassLibrary.OrderofBattleSW
    If ActiveUnitOB.FirstSWLink > 0 Then
            OBSWitem = linqdata.GetOBSWRecord(CInt(ActiveUnitOB.FirstSWLink))
    OBSWitem.Hexlocation = CShort(ActiveUnitOB.hexlocation)
    OBSWitem.Position = ActiveUnitOB.Position
    End If
    If ActiveUnitOB.SecondSWlink > 0 Then
            OBSWitem = linqdata.GetOBSWRecord(CInt(ActiveUnitOB.SecondSWlink))
    OBSWitem.Hexlocation = CShort(ActiveUnitOB.hexlocation)
    OBSWitem.Position = ActiveUnitOB.Position
    End If
    Return True
    End Function
    Private Function WACresttoCrest(ByVal currentposition As Integer) As Integer
    Select Case currentposition
    Case ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0
    Return ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0
    Case ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1
    Return ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0
    Case ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus2
    Return ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1
    Case ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus3
    Return ConstantClassLibrary.ASLXNA.AltPos.CrestStatus3
    Case ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus4
    Return ConstantClassLibrary.ASLXNA.AltPos.CrestStatus4
    Case ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5
    Return ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5
    Case Else
    Return Nothing
    End Select
    End Function*/
}
