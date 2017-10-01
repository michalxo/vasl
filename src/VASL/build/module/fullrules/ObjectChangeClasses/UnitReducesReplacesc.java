package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitReducesReplacesc {
    /*Implements StatusChangei
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myResultstring As String
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function ReduceReplaceUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
            'Name:       TargetReduces()

                    'Identifier UC 203

                    '            Preconditions()
                    '1.	MMC Target is alive and has suffered a CR result

                    '            Basic Course
                    '1.	Use case begins when a CR result is produced [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult]
                    '2.	Add new HS [UC217-AddNewUnit]
                    '3.	Target transfers settings to new unit
                    '4.	Change visibility status of Target
                    '5.	Use case ends when the Target status changes to Reduced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.
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
    Dim PassHoBCHeck As Boolean = False 'Hob test done by last unitchange
    Dim RunFirstChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitReplacesc(PassHoBCHeck)
            RunFirstChange.Takeaction(TargParent)
            'myNewTargs = RunFirstChange.GetNewTargs
    TargParent = RunFirstChange.GetNewTargs.Item(0)
    myResultstring = TargParent.TargetPersUnit.CombatResultString
            PassHoBCHeck = True 'HOB test done by last unitchange
    Dim RunnextChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitReducesc(myResultstring, PassHoBCHeck)
            RunnextChange.Takeaction(TargParent)
    myNewFiring = RunnextChange.GetNewFirings
            myNewTargs = RunnextChange.GetNewTargs
            'No HoB - done by UnitReducesc
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
