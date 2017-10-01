package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitStepReducesHSc {
    /*Implements StatusChangei
    Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function StepReduceUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetStepReduces()

                    'Identifier UC 216

                    '            Preconditions()
                    '1.	Non-berserk Japanese Squad Target is alive and has failed an IFT MC

                    '            Basic Course
                    '1.	Use case begins when a MC failure  result is produced [UC115-JapanTargetMCResult]
                    '2.	Determine Reduction (to ReducedStrength or to HS)
                    '3.	Add new Unit [UC217-AddNewUnit]
                    '4.	Target transfers settings to new unit
                    '5.	Change visibility status of Target
                    '6.	Use case ends when the Target changes status to StepReduced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '2.

                    'create the new unit
    Dim ReducesTo As Integer = TargParent.TargetPersUnit.RedTo
    Dim NewName As String = InputBox("Enter Name of New Unit: ", TargParent.BasePersUnit.UnitName & " step-reduces", )
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
    Dim NewUnit As ObjectClassLibrary.ASLXNA.PersUniti = UseObjectFactory.CreateNewInstance(ReducesTo, NewName, TargParent)
            'update new unit with values of previous unit - Do we need all of this
    Dim UnitUpdateNewWithOld As New UnitUpdateNewOldc(NewUnit, TargParent)
    If Not IsNothing(TargParent.TargetPersUnit) Then
                'NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
    With NewUnit.TargetPersUnit 'TargetPersUnit already created by UnitUpdateNewWithOldc
            .CombatResultString = Trim(TargParent.BasePersUnit.UnitName) & ": " & TargParent.TargetPersUnit.CombatResultString & " is step-reduced to " & Trim(NewUnit.BasePersUnit.UnitName) & vbCrLf ' & myResultstring & vbCrLf
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
