package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitDisruptsc implements StatusChangei {

    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();

    //Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    public UnitDisruptsc() {

    }
    public boolean Takeaction(PersUniti TargParent) {
        /*'Name:       TargetDisrupts()

        'Identifier UC 218

        '            Preconditions()
        '1.	MMC Target of lowest quality is alive and fails a MC by more than its ELR

        '            Basic Course
        '1.	Use case begins when a Target fails a MC => ELR (several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult;
        '2.	Use case ends when the Target changes status to Disupted (Alternate Course A: many units do not disrupt and are broken)

        'Alternate Course A:
        '	Use case ends when the Target changes status to Broken
        'Condition: Target is not subject to disruption

        'Inheritance:
        'Condition:

        '            Post conditions
        '1.*/

        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.Disrupted);
        TargParent.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.Disrupted);
        TargParent.getbaseunit().setCX(false);
        TargParent.getbaseunit().setPinned(false);
        TargParent.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " is disrupted");

        //'HoB
        if (TargParent.getTargetunit().getHoBFlag()) { // rolled a 2
            Constantvalues.PersUnitResult HobChange = TargParent.getTargetunit().HOBMC();
            StatusChangei RunStatusChange;
            SelectStatusChangec GetStatusChange = new SelectStatusChangec();
            RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, TargParent);
            if (RunStatusChange != null ) {
                RunStatusChange.Takeaction(TargParent);
            } else {
                //myPopUpList = GetStatusChange.PopUpItems; temporary while debugging UNDO
                return false;
            }
            TargParent.getbaseunit().setOrderStatus(TargParent.getTargetunit().getOrderStatus());
            // update Target and Firing lists with new units
            if (RunStatusChange.GetNewTargs != null) {myNewTargs = RunStatusChange.GetNewTargs;}
        }
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> GetNewFirings () {
        // no code required; no new unit
        return null;
    }

    /*Public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get
    Return myPopUpList
    End Get
    End Property*/
}
