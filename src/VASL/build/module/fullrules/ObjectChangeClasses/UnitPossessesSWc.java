package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitPossessesSWc {
    /*Implements StatusChangei
    Private mySWtoPossess As Integer
    Private Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance
    Public Sub New(ByVal SWToPossess As Integer)
    mySWtoPossess = SWToPossess
    End Sub
    Public Function PossessSW(ByRef GettingUnit As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'called by SWChangePoss.MoveOK and others
                    'possesses SW and update OB, OBSW, and Unpossessed
    Dim Scencolls = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim SWtoCheck As ObjectClassLibrary.ASLXNA.SuppWeapi : Dim SWChange As ObjectChange.ASLXNA.ChangeSWOwnerc
    If mySWtoPossess = 0 Then Return False 'no SW found
    SWtoCheck = (From getsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where getsw.BaseSW.Unit_ID = mySWtoPossess Select getsw).First
    If IsNothing(SWtoCheck) Then Return False
    If GettingUnit.BasePersUnit.SW = 2 Then Return False 'cannot possess an additional sw
    Dim OBSWname As String = SWtoCheck.BaseSW.UnitName
    GettingUnit.BasePersUnit.SW += CShort(1)
    If GettingUnit.BasePersUnit.FirstSWLink = 0 Then
    GettingUnit.BasePersUnit.FirstSWLink = mySWtoPossess
            SWChange = New ObjectChange.ASLXNA.ChangeSWOwnerc(GettingUnit.BasePersUnit.FirstSWLink, GettingUnit.BasePersUnit.Unit_ID)
    Else
    GettingUnit.BasePersUnit.SecondSWlink = mySWtoPossess
            SWChange = New ObjectChange.ASLXNA.ChangeSWOwnerc(GettingUnit.BasePersUnit.SecondSWlink, GettingUnit.BasePersUnit.Unit_ID)
    End If
    MsgBox(Trim(GettingUnit.BasePersUnit.UnitName) & " gains " & Trim(SWtoCheck.BaseSW.UnitName))
    Dim SWHexloc = New CombatTerrainClassLibrary.ASLXNA.HexAndLocHolder(CInt(SWtoCheck.BaseSW.Hexnum), CInt(SWtoCheck.BaseSW.hexlocation))
    Return True
            'Delete Unpossessed
    Try
    Dim DelUnpossessed As DataClassLibrary.Unpossessed = (From GetUnposs As DataClassLibrary.Unpossessed In Scencolls.Unpossesseds Where GetUnposs.EquipmentID = mySWtoPossess Select GetUnposs).First
                Scencolls.Unpossesseds.Remove(DelUnpossessed)
    Catch
                'do nothing, no unpossessed exists
    End Try

    End Function

    Public ReadOnly Property GetNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti) Implements StatusChangei.GetNewTargs
            Get

    End Get
    End Property

    Public ReadOnly Property GetNewFirings As List(Of ObjectClassLibrary.ASLXNA.PersUniti) Implements StatusChangei.GetNewFirings
            Get

    End Get
    End Property

    Public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
