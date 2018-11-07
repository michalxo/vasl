package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class WARemovec implements UnitChangei {

    private PersUniti ActiveUnit;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    public WARemovec(PersUniti MovingUnit){
        ActiveUnit = MovingUnit;
    }
    public boolean TakeAction() {
        return false;
        /*Dim ASLMapLink
        As String = "Scen" & CStr(CInt(ActiveUnit.BasePersUnit.Scenario))
        Dim Maptables As MapDataClassLibrary.
        ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ASLMapLink, CInt(ActiveUnit.BasePersUnit.Scenario))
        Dim LocationCol As IQueryable (Of MapDataClassLibrary.GameLocation) =Maptables.CreateMapCollection()
        'Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
        Dim TempSW As ObjectClassLibrary.ASLXNA.SuppWeapi :Dim WALost As Boolean = False
        'only remove where exists; if not in WA, do nothing
        If ActiveUnit.BasePersUnit.hexPosition = CShort(ConstantClassLibrary.ASLXNA.AltPos.WallAdv) Then
        ActiveUnit.BasePersUnit.hexPosition = 0
        WALost = True
        Dim GetBaseHex As MapDataClassLibrary.
        GameLocation = LevelChk.GetLocationatLevelInHex(CInt(ActiveUnit.BasePersUnit.Hexnum), 0)
        ActiveUnit.BasePersUnit.hexlocation = CInt(GetBaseHex.Location) 'always baseloc when doing WA
        ElseIf ActiveUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0
        AndAlso ActiveUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5 Then
        ActiveUnit.BasePersUnit.hexPosition = WACresttoCrest(CInt(ActiveUnit.BasePersUnit.hexPosition))
        WALost = True
        Dim GetBaseHex As MapDataClassLibrary.
        GameLocation = LevelChk.GetLocationatLevelInHex(CInt(ActiveUnit.BasePersUnit.Hexnum), 0)
        ActiveUnit.BasePersUnit.hexlocation = CInt(GetBaseHex.Location) 'always baseloc when doing WA
        Else
        'Return False ' nothing to remove
        End If
        Dim NewTexture As String = ActiveUnit.SetTexture()
        If Not IsNothing(NewTexture) Then
        If ActiveUnit.BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
        AndAlso WALost Then
        MessageBox.Show(ActiveUnit.BasePersUnit.UnitName & " loses wall advantage")
        Else
        If ActiveUnit.BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment AndAlso WALost
        Then MessageBox.Show(ActiveUnit.BasePersUnit.UnitName & " loses wall advantage")
        End If
        'update any SW counters associated with this unit
        If ActiveUnit.BasePersUnit.FirstSWLink > 0 AndAlso WALost Then
                TempSW = (From GetSw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
        GetSw.BaseSW.Unit_ID = ActiveUnit.BasePersUnit.FirstSWLink Select GetSw).First
        TempSW.BaseSW.hexlocation = ActiveUnit.BasePersUnit.hexlocation
        TempSW.BaseSW.hexPosition = ActiveUnit.BasePersUnit.hexPosition
        TempSW.SetTexture()
        End If
        If ActiveUnit.BasePersUnit.SecondSWlink > 0 AndAlso WALost Then
                TempSW = (From GetSw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
        GetSw.BaseSW.Unit_ID = ActiveUnit.BasePersUnit.SecondSWlink Select GetSw).First
        TempSW.BaseSW.hexlocation = ActiveUnit.BasePersUnit.hexlocation
        TempSW.BaseSW.hexPosition = ActiveUnit.BasePersUnit.hexPosition
        TempSW.SetTexture()
        End If
        End If
        Return WALost*/
    }
    /*Private Function WACresttoCrest(ByVal currentposition As Integer) As Integer
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
