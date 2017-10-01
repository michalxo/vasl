package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitPinsc {

    /*Implements StatusChangei
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function PinUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetPins()

                    'Identifier UC 206

                    '            Preconditions()
                    '1.	Unbroken MMC Target is alive and fails a PTC or passes a MC by highest possible number

                    '            Basic Course
                    '1.	Use case begins when a Target fails a PTC or passes MC by highest number [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult; UC104-TargetPTCResult]
                    '2.	Use case ends when the Target changes status to Pinned

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.
    If IsNothing(TargParent.TargetPersUnit) Then
    Dim ComFunc = New UtilWObj.ASLXNA.CommonFunctions(TargParent.BasePersUnit.Scenario)
    Dim FirerSan As Integer = ComFunc.GetEnemySan(TargParent.BasePersUnit.Nationality)
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan)
    End If
    With TargParent
                'If Not IsNothing(.TargetPersUnit) Then .TargetPersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.
                        '.BasePersUnit.CX = False
                        .BasePersUnit.Pinned = True
                '.BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
                        .BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
            .SetTexture()
            .TargetPersUnit.UpdateTargetStatus(TargParent)
            .TargetPersUnit.CombatResultString &= " Pins"
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
    Return False
    End If
    TargParent.BasePersUnit.OrderStatus = TargParent.TargetPersUnit.OrderStatus
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
                'no code required; no new unit
    End Get
    End Property

    Public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get
    Return myPopUpList
    End Get
    End Property*/

}
