package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitFanaticsc {
    /*Implements StatusChangei
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function FanaticUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetFanatics()

                    'Identifier UC 221

                    '            Preconditions()
                    '2.	Unbroken MMC Target is alive and gets a Fanatic result on a HOB dice roll

                    '            Basic Course
                    '3.	Use case begins when a Target makes a HOB dice roll [UC109-TargetHOBResult]
                    '4.	Use case ends when the Target changes status to Fanatic

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '2.

    If IsNothing(TargParent.TargetPersUnit) Then
    Dim ComFunc = New UtilWObj.ASLXNA.CommonFunctions(TargParent.BasePersUnit.Scenario)
    Dim FirerSan As Integer = ComFunc.GetEnemySan(TargParent.BasePersUnit.Nationality)
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan)
    End If
    With TargParent
                .TargetPersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.GoodOrder
    If .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Normal Then .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fanatic
    If .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Encircled Then .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Enc
    If .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded Then .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd
    If .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Enc_Wnd Then .BasePersUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd_Enc
                '.BasePersUnit.CX = False
                        '.BasePersUnit.Pinned = False
                        '.BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
                        '.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
                        .SetTexture()
                .TargetPersUnit.UpdateTargetStatus(TargParent)
            .TargetPersUnit.CombatResultString &= " becomes Fanatic"
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
