package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitSubstitutesc {
    /*Implements StatusChangei
    Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myResultstring As String
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function SubstituteUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetSubstitutes()

                    'Identifier UC 214

                    '            Preconditions()
                    '1.	MMC Target with ELR 5 is alive and has failed a MC by more than its ELR

                    '            Basic Course
                    '1.	Use case begins when an ELR failure  result is produced (several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult;
                    '2.	Add 2 new HS [UC217-AddNewUnit]
                    '3.	Target transfers settings to new unit
                    '4.	Change visibility status of Target
                    '5.	Use case ends when the Target changes status to Replaced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.

                    'create the new units
    For x = 1 To 2
    Dim SubsTo As Integer = TargParent.TargetPersUnit.SubTo
    Dim NewName As String = InputBox("Enter Name of New Unit: ", TargParent.BasePersUnit.UnitName & " substitutes: create two half-squads", )
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
    Dim NewUnit As ObjectClassLibrary.ASLXNA.PersUniti = UseObjectFactory.CreateNewInstance(SubsTo, NewName, TargParent)
            'update new unit with values of previous unit - Do we need all of this
    Dim UnitUpdateNewWithOld As New UnitUpdateNewOldc(NewUnit, TargParent)
    If Not IsNothing(TargParent.TargetPersUnit) Then
                    'NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
    With NewUnit.TargetPersUnit 'TargetPersUnit already created by UnitUpdateNewWithOldc
            .CombatResultString = Trim(TargParent.BasePersUnit.UnitName) & ": " & TargParent.TargetPersUnit.CombatResultString & " is replaced by " & Trim(NewUnit.BasePersUnit.UnitName) & vbCrLf ' & myResultstring & vbCrLf
    End With
                    'update SW values
    With NewUnit
    If TargParent.BasePersUnit.FirstSWLink > 0 Then
    Dim SWItem As Integer = TargParent.BasePersUnit.FirstSWLink
    Dim SWtoChange As ObjectClassLibrary.ASLXNA.SuppWeapi = (From getsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where getsw.BaseSW.Unit_ID = SWItem Select getsw).First
    Dim SWName As String = SWtoChange.BaseSW.UnitName
    Dim Response As System.Windows.Forms.DialogResult = System.Windows.Forms.MessageBox.Show("Does " & NewUnit.BasePersUnit.UnitName & " take possession of " & SWName & "?", "Unit Substitution", Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Question)
    If (Response = System.Windows.Forms.DialogResult.Yes) Then
    Dim ChangeSWPoss As StatusChangei = New UnitPossessesSWc(SWItem)
                                ChangeSWPoss.Takeaction(NewUnit)
    TargParent.BasePersUnit.FirstSWLink = 0
    End If
    End If
    If TargParent.BasePersUnit.SecondSWlink > 0 Then
    Dim SWItem As Integer = TargParent.BasePersUnit.SecondSWlink
    Dim SWtoChange As ObjectClassLibrary.ASLXNA.SuppWeapi = (From getsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where getsw.BaseSW.Unit_ID = SWItem Select getsw).First
    Dim SWName As String = SWtoChange.BaseSW.UnitName
    Dim Response As System.Windows.Forms.DialogResult = System.Windows.Forms.MessageBox.Show("Does " & NewUnit.BasePersUnit.UnitName & " take possession of " & SWName & "?", "Unit Substitution", Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Question)
    If (Response = System.Windows.Forms.DialogResult.Yes) Then
    Dim ChangeSWPoss As StatusChangei = New UnitPossessesSWc(SWItem)
                                ChangeSWPoss.Takeaction(NewUnit)
    TargParent.BasePersUnit.SecondSWlink = 0
    End If
    End If
    End With
    End If

                'change values for former unit
    If x = 2 Then 'do on second pass to ensure current values passed to both new units
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
                                '.BasePersUnit.FirstSWLink = 0
                                '.BasePersUnit.SecondSWlink = 0
                                .BasePersUnit.SW = 0
            .SetTexture()
                        .TargetPersUnit.UpdateTargetStatus(TargParent)
            '.TargetPersUnit.CombatResultString &= "Reduces to " & Trim(NewUnit.BasePersUnit.UnitName)
    End With
    End If
                'remove old unit from moving list TOO EARLY - DO THIS LATER
    If Not IsNothing(TargParent.MovingPersUnit) Then Scencolls.SelMoveUnits.Remove(TargParent)
            'add new unit to Unitcol collection
            Scencolls.Unitcol.Add(NewUnit)
            'Store values to update FireGroup and TargetGroup (maybe add movement?)
    If Not IsNothing(NewUnit.TargetPersUnit) Then myNewTargs.Add(NewUnit)
    If Not IsNothing(NewUnit.FiringPersUnit) Then myNewFiring.Add(NewUnit)
    Next
            'NO HoB as can't produce necessary conditions for ELR5 substitution on a 2 roll
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

    End Get
    End Property*/
}
