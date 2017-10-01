package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitReplacesc {
    /*Implements StatusChangei
    Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myHOBcheck As Boolean
    Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    Public Sub New(ByVal HOBcheck As Boolean)
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    myHOBcheck = HOBcheck
    End Sub
    Public Function ReplaceUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetReplaces()

                    'Identifier UC 204

                    '            Preconditions()
                    '1.	MMC Target is alive and fails a MC by more than its ELR

                    '            Basic Course
                    '1.	Use case begins when a Target fails a MC [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult]
                    '2.	Add new unit of lower quality [UC218-AddNewUnit]
                    '3.	Target transfers settings to new unit
                    '4.	Change visibility status of Target
                    '5.	Use case ends when the Target changes status to Replaced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.


                    'create the new unit
    Dim ReplacesTo As Integer = TargParent.TargetPersUnit.SubTo
    Dim NewName As String = InputBox("Enter Name of New Unit: ", TargParent.BasePersUnit.UnitName & " replaces", )
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
    Dim NewUnit As ObjectClassLibrary.ASLXNA.PersUniti = UseObjectFactory.CreateNewInstance(ReplacesTo, NewName, TargParent)
            'update new unit with values of previous unit - Do we need all of this
    Dim UnitUpdateNewWithOld As New UnitUpdateNewOldc(NewUnit, TargParent)
    If Not IsNothing(TargParent.TargetPersUnit) Then
                'NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
    With NewUnit.TargetPersUnit 'TargetPersUnit already created by UnitUpdateNewWithOldc
            .CombatResultString = Trim(TargParent.BasePersUnit.UnitName) & ": " & TargParent.TargetPersUnit.CombatResultString & " is replaced by " & Trim(NewUnit.BasePersUnit.UnitName) & vbCrLf ' & myResultstring & vbCrLf
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
                '.BasePersUnit.Hexnum = 0
                        '.BasePersUnit.LOCIndex = 0
                        '.BasePersUnit.hexlocation = 0
                        '.BasePersUnit.hexPosition = 0
                        .BasePersUnit.FirstSWLink = 0
            .BasePersUnit.SecondSWlink = 0
            .BasePersUnit.SW = 0
            .SetTexture()
                .TargetPersUnit.UpdateTargetStatus(TargParent)
            '.TargetPersUnit.CombatResultString &= "Reduces to " & Trim(NewUnit.BasePersUnit.UnitName)
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
            'HoB
    If NewUnit.TargetPersUnit.HoBFlag Then 'rolled a 2
    Dim HobChange As Integer = NewUnit.TargetPersUnit.HOBMC()
    Dim RunStatusChange As ObjectChange.ASLXNA.StatusChangei
    Dim GetStatusChange = New ObjectChange.ASLXNA.SelectStatusChangec
            RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, TargParent)
    If Not IsNothing(RunStatusChange) Then
                    RunStatusChange.Takeaction(TargParent)
    Else
            myPopUpList = GetStatusChange.PopUpItems
    Return False
    End If
    NewUnit.BasePersUnit.OrderStatus = NewUnit.TargetPersUnit.OrderStatus
                'update Target and Firing lists with new units
    If Not IsNothing(RunStatusChange.GetNewTargs) Then myNewTargs = RunStatusChange.GetNewTargs
    End If
    Return True
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