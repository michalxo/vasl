package VASL.build.module.fullrules.ObjectChangeClasses;

public class UnitReducesDies {
    /*Implements StatusChangei
        'Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Private myNewTargs As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myNewFiring As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Private myResultstring As String = ""
    Public Sub New()
    myNewTargs = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    End Sub
    Public Function ReduceDieUnit(ByRef TargParent As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean Implements StatusChangei.Takeaction
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
                    'Name:       TargetDies()

                    'Identifier UC 201

                    '            Preconditions()
                    '1.	Target is alive

                    '            Basic Course
                    '1.	Use case begins when Target dies
                    '2.	Target is not Goodorder nor Visible
                    '3.	Use case ends when the Target’s status is changed to KIA

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.
    Dim PassHoBCHeck As Boolean = False 'Hob test done by last unitchange
    Dim RunFirstChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitReducesc(myResultstring, PassHoBCHeck)
            RunFirstChange.Takeaction(TargParent)
    myNewFiring = RunFirstChange.GetNewFirings
            myNewTargs = RunFirstChange.GetNewTargs
    TargParent = RunFirstChange.GetNewTargs.Item(0)
    myResultstring = TargParent.TargetPersUnit.CombatResultString
    Dim RunnextChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitDiesc
            RunnextChange.Takeaction(TargParent)
            'No HoB as unit dead
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
