package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitDisruptsDMsc implements StatusChangei{

    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private String myResultstring;

    //Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    public UnitDisruptsDMsc() {

    }
    public boolean Takeaction(PersUniti TargParent) {
        /*'Name:       TargetDisruptDMs()

        'Identifier UC 222

        '            Preconditions()
        '2.	MMC Target of lowest quality is alive and fails a MC by more than its ELR

        '            Basic Course
        '3.	Use case begins when a Target fails a MC => ELR (several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult;
        '4.	Use case ends when the Target changes status to Disupted (Alternate Course A: many units do not disrupt and are broken)

        'Alternate Course A:
        '	Use case ends when the Target changes status to DisruptDM
        'Condition: Target is not subject to disruption

        'Inheritance:
        'Condition:

        '            Post conditions
        '2.*/

        boolean PassHoBCHeck = false;  // Hob test done by last unitchange
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Disrupts);
        StatusChangei RunFirstChange = new UnitDisruptsc();
        RunFirstChange.Takeaction(TargParent);
        LinkedList<PersUniti> myNewUnits = RunFirstChange.getNewTargs();
        TargParent = myNewUnits.get(0);  //.GetNewTargs().get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Breaks);
        StatusChangei RunnextChange = new UnitBreaksc();
        RunnextChange.Takeaction(TargParent);
        // the following needs to be done to ensure proper display and target management
        myNewTargs.add(TargParent);
        //'If Not IsNothing(RunnextChange.GetNewTargs) AndAlso RunnextChange.GetNewTargs.Count > 0 Then myNewTargs = RunnextChange.GetNewTargs
        myNewFiring = RunnextChange.getNewFirings();
        // No HoB - done in UnitBreaks
        return true;
    }

    public LinkedList<PersUniti> getNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        // no code required; no new unit
        return null;
    }

    /*Public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get
    Return myPopUpList
    End Get
    End Property*/
}
