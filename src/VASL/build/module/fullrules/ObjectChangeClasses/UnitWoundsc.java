package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitWoundsc {
    /*Implements StatusChangei
    Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function WoundUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetWounds()
                    'Identifier UC 202

                    '            Preconditions()
                    '1.	SMC is alive and has passed a woundcheck

                    '            Basic Course
                    '1.	Use case begins when a wounded result is obtained from a Woundcheck
                    '2:          .SMC() 's morale and LDRM are lowered
            '3:          .SMC() 's MF are lowered to 3
            '4.	Use case ends when the SMC status is changed to wounded

            'Alternate Course A:
            'Condition:

            'Inheritance:
            'Condition:

            '            Post conditions
            '1.
    If TargParent.BasePersUnit.IsUnitALeader Then
    If IsNothing(TargParent.TargetPersUnit) Then
    Dim ComFunc = New UtilWObj.ASLXNA.CommonFunctions(TargParent.BasePersUnit.Scenario)
    Dim FirerSan As Integer = ComFunc.GetEnemySan(TargParent.BasePersUnit.Nationality)
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan)
    End If
    With TargParent
                    .TargetPersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded
            .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded
            .BasePersUnit.CX = False
            .BasePersUnit.Pinned = False
                    '.BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
                            '.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
                            .SetTexture()
                    .TargetPersUnit.UpdateTargetStatus(TargParent)
            .TargetPersUnit.CombatResultString &= " is wounded"
    End With
                'HoB
    If TargParent.TargetPersUnit.HoBFlag Then 'rolled a 2
    Dim HobChange As Integer = TargParent.TargetPersUnit.HOBMC()
    Dim RunStatusChange As ObjectChange.ASLXNA.StatusChangei
    Dim GetStatusChange = New ObjectChange.ASLXNA.SelectStatusChangec
            RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, TargParent)
    If Not IsNothing(RunStatusChange) Then
                        RunStatusChange.Takeaction(TargParent)
    Else
            myPopUpList = GetStatusChange.PopUpItems
    End If
    TargParent.BasePersUnit.OrderStatus = TargParent.TargetPersUnit.OrderStatus
                    'update Target and Firing lists with new units
    If Not IsNothing(RunStatusChange.GetNewTargs) Then myNewTargs = RunStatusChange.GetNewTargs
    End If
    Return True
    Else  'is a hero
            'create the new 138 unit
    Dim WoundsTo As Integer = 1101  '138 hero
    Dim NewName As String = Trim(TargParent.BasePersUnit.UnitName)
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
    Dim NewUnit As ObjectClassLibrary.ASLXNA.PersUniti = UseObjectFactory.CreateNewInstance(WoundsTo, NewName, TargParent)
            'update new unit with values of previous unit - Do we need all of this
    Dim UnitUpdateNewWithOld As New UnitUpdateNewOldc(NewUnit, TargParent)
    If Not IsNothing(TargParent.TargetPersUnit) Then
    With NewUnit.TargetPersUnit
            .CombatResultString = Trim(TargParent.BasePersUnit.UnitName) & ": " & TargParent.TargetPersUnit.CombatResultString & " is wounded " & vbCrLf ' & myResultstring & vbCrLf
    End With
    End If
                'change values for former unit
    If IsNothing(TargParent.TargetPersUnit) Then
    Dim ComFunc = New UtilWObj.ASLXNA.CommonFunctions(TargParent.BasePersUnit.Scenario)
    Dim FirerSan As Integer = ComFunc.GetEnemySan(TargParent.BasePersUnit.Nationality)
    TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan)
    End If
    With TargParent
                    .TargetPersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay
            .BasePersUnit.CX = False
            .BasePersUnit.Pinned = False
            .BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
            .BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
            .BasePersUnit.FirstSWLink = 0
            .BasePersUnit.SecondSWlink = 0
            .BasePersUnit.SW = 0
            .SetTexture()
                    .TargetPersUnit.UpdateTargetStatus(TargParent)
    End With
                'remove old unit from moving list TOO EARLY - DO THIS LATER
    If Not IsNothing(TargParent.MovingPersUnit) Then Scencolls.SelMoveUnits.Remove(TargParent)
            'add new unit to Unitcol collection
            Scencolls.Unitcol.Add(NewUnit)
            'Store values to update FireGroup and TargetGroup (maybe add movement?)
    If Not IsNothing(NewUnit.TargetPersUnit) Then myNewTargs.Add(NewUnit)
    If Not IsNothing(NewUnit.FiringPersUnit) Then myNewFiring.Add(NewUnit)

                'update SW values
    With NewUnit
    Dim SWChange As ObjectChange.ASLXNA.ChangeSWOwnerc
    If .BasePersUnit.FirstSWLink > 0 Then SWChange = New ObjectChange.ASLXNA.ChangeSWOwnerc(.BasePersUnit.FirstSWLink, .BasePersUnit.Unit_ID)
    If .BasePersUnit.SecondSWlink > 0 Then SWChange = New ObjectChange.ASLXNA.ChangeSWOwnerc(.BasePersUnit.SecondSWlink, .BasePersUnit.Unit_ID)
    End With
                'No HoB as heros not subject to HOB
    End If


    End Function

    Public ReadOnly Property GetNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti) Implements StatusChangei.GetNewTargs
            Get
    Return myNewTargs
    End Get
    End Property

    Public ReadOnly Property GetNewFirings As List(Of ObjectClassLibrary.ASLXNA.PersUniti) Implements StatusChangei.GetNewFirings
            Get
    Return myNewFiring
    End Get
    End Property

    Public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get
    Return myPopUpList
    End Get
    End Property*/
}
