package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class UnitReducesPinsc implements StatusChangei{
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)

    public UnitReducesPinsc() {

    }

    public boolean Takeaction (PersUniti TargParent) {
/*
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
        '1.*/
        boolean PassHoBCHeck = false;  // Hob test done by last unitchange
        StatusChangei RunFirstChange = new UnitReducesc(myResultstring, PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        myNewFiring = RunFirstChange.GetNewFirings;
        myNewTargs = RunFirstChange.GetNewTargs;
        TargParent = RunFirstChange.GetNewTargs.get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        StatusChangei RunnextChange = new UnitPinsc();
        RunnextChange.Takeaction(TargParent);
        // No HoB - done by UnitPinsc
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs() {return myNewTargs;}
    public LinkedList<PersUniti> GetNewFirings () {return myNewFiring;}

    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
