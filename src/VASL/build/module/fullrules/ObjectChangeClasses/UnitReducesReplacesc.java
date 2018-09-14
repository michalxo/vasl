package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class UnitReducesReplacesc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)

    public UnitReducesReplacesc() {

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
                    '1.*/
        boolean PassHoBCHeck = false; // Hob test done by last unitchange
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Replaces);
        StatusChangei RunFirstChange = new UnitReplacesc(PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        // myNewTargs = RunFirstChange.GetNewTargs
        TargParent =RunFirstChange.getNewTargs().get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        PassHoBCHeck = true; // HOB test done by last unitchange
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Reduces);
        StatusChangei RunnextChange = new UnitReducesc(myResultstring,PassHoBCHeck);
        RunnextChange.Takeaction(TargParent);
        myNewFiring = RunnextChange.getNewFirings();
        myNewTargs = RunnextChange.getNewTargs();
        // No HoB - done by UnitReducesc
        return true;
    }

    public LinkedList<PersUniti> getNewTargs() {return myNewTargs;}
    public LinkedList<PersUniti> getNewFirings () {return myNewFiring;}

    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
