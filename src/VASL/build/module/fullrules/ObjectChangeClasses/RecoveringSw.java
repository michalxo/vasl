package VASL.build.module.fullrules.ObjectChangeClasses;

public class RecoveringSw {
    /*Implements OBUnitChange
    Private ActiveUnitOB As DataClassLibrary.OrderofBattle
    Private UnpossSWID As Integer
    Private linqdata As DataClassLibrary.ASLXNA.DataC
    Public Sub New(ByVal unitid As Integer, ByVal SWId As Integer)
    linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance
            ActiveUnitOB = linqdata.GetUnitfromCol(unitid)
    UnpossSWID = SWId
    End Sub
    Public Function RecoverSW() As Boolean Implements OBUnitChange.TakeAction
            'called by
                    'adds SW to OBUnit and deletes from Unpossessed
    If ActiveUnitOB.FirstSWLink = 0 Then
    ActiveUnitOB.FirstSWLink = CType(UnpossSWID, Short?)
    ActiveUnitOB.SW += CShort(1)
    ElseIf ActiveUnitOB.SecondSWlink = 0 Then
    ActiveUnitOB.SecondSWlink = CType(UnpossSWID, Short?)
    ActiveUnitOB.SW += CShort(1)
    Else
    Return False
    End If
            linqdata.DeleteUnpossessed(UnpossSWID)
    Dim OBSW As DataClassLibrary.OrderofBattleSW = linqdata.GetOBSWRecord(UnpossSWID)
            MessageBox.Show(Trim(ActiveUnitOB.OBName) & " recovers " & Trim(OBSW.OBWeapon))
    OBSW.Owner = ActiveUnitOB.OBUnit_ID
    OBSW.Hexlocation = CShort(ActiveUnitOB.hexlocation)
    OBSW.Hexnumber = ActiveUnitOB.hexnum
    OBSW.Position = ActiveUnitOB.Position
    If OBSW.Nationality = ActiveUnitOB.Nationality Then OBSW.Captured = False Else OBSW.Captured = True
            'OBSW.SWTexture = Game.Content.Load(Of Texture2D)(Trim(OBSW.SetTexture()))
    Return True
    End Function*/
}
