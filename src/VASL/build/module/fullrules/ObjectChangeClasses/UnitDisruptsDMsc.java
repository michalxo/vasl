package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitDisruptsDMsc implements StatusChangei{

    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();

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

        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.DisruptedDM);
        TargParent.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.DisruptedDM);
        TargParent.getbaseunit().setCX(false);
        TargParent.getbaseunit().setPinned(false);
        TargParent.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " is disrupted and DM");

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
            if (RunStatusChange.getNewTargs() != null) {myNewTargs = RunStatusChange.getNewTargs();}
        }
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
