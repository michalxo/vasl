package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitReplacesDMsc {
    /*Implements StatusChangei
        'Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myResultstring As String
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function RRBUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetReducesReplacesBreaks()

                    'Identifier UC 204.5

                    '            Preconditions()
                    '2.	MMC Target is alive and fails a MC by more than its ELR on rolling a 12

                    '            Basic Course
                    '6.	Use case begins when a Target fails a MC [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult] due to rolling a 12
                    '7.	Add new HS of lower quality [UC218-AddNewUnit]
                    '8.	Target transfers settings to new unit and change its order status to Broken-DM
                    '9.	Change visibility status of Target
                    '10.	Use case ends when the Target changes status to Replaced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '2.
    Dim PassHoBCHeck As Boolean = False 'Hob test done by last unitchange
    Dim RunFirstChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitReplacesc(PassHoBCHeck)
            RunFirstChange.Takeaction(TargParent)
            'myNewTargs = RunFirstChange.GetNewTargs
    TargParent = RunFirstChange.GetNewTargs.Item(0)
    myResultstring = TargParent.TargetPersUnit.CombatResultString
    Dim RunnextChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitDMsc
            RunnextChange.Takeaction(TargParent)
            'the following needs to be done to ensure proper display and target management
            myNewTargs.Add(TargParent)
            'If Not IsNothing(RunnextChange.GetNewTargs) AndAlso RunnextChange.GetNewTargs.Count > 0 Then myNewTargs = RunnextChange.GetNewTargs
    myNewFiring = RunnextChange.GetNewFirings
            'No HoB - done in UnitDMs

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
