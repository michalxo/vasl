package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class UnitReplacesReducesBreaksc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    //private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    //private boolean myHOBCHeck;

    public UnitReplacesReducesBreaksc() {

    }

    public boolean Takeaction (PersUniti TargParent) {
                /*'Name:       TargetReducesReplacesBreaks()

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
                    '2.*/
        boolean PassHoBCHeck = false; // Hob test done by last unitchange
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Replaces);
        StatusChangei RunFirstChange = new UnitReplacesc(PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        TargParent = (RunFirstChange.getNewTargs()).get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.ReducesBreaks);
        StatusChangei RunnextChange = new UnitReducesBreaksc(myResultstring);
        RunnextChange.Takeaction(TargParent);
        myNewFiring = RunnextChange.getNewFirings();
        myNewTargs = RunnextChange.getNewTargs();
        // No HoB - done in UnitReducesBreaksC
        return true;
    }

    public LinkedList<PersUniti> getNewTargs() {return myNewTargs;}
    public LinkedList<PersUniti> getNewFirings () {return myNewFiring;}

    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
