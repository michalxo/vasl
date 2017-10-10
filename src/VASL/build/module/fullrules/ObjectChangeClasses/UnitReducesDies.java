package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class UnitReducesDies implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    //private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring ="";
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)

    public UnitReducesDies() {

    }

    public boolean Takeaction (PersUniti TargParent) {
        /*'Name:       TargetReduces()

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
        '1.*/
        boolean PassHoBCHeck  = false; // Hob test done by last unitchange
        StatusChangei RunFirstChange = new UnitReducesc(myResultstring, PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        myNewFiring = RunFirstChange.GetNewFirings;
        myNewTargs = RunFirstChange.GetNewTargs;
        TargParent = RunFirstChange.GetNewTargs.get(0);  // not sure this is right??
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        StatusChangei RunnextChange = new UnitDiesC();
        RunnextChange.Takeaction(TargParent);
        // No HoB as unit dead
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs() {return myNewTargs;}
    public LinkedList<PersUniti> GetNewFirings () {return myNewFiring;}

    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
