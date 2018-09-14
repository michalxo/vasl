package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitNoEffectsc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    public UnitNoEffectsc() {

    }
    public boolean Takeaction(PersUniti TargParent) {
        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        if (TargParent.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken) {
            TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " is already broken; becomes DM");
            TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.Broken_DM);
            TargParent.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.Broken_DM);
        } else if (TargParent.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken_DM) {
            TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " is already DM: no effect");
        } else {
            TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " survives: no effect");
        }
        // HoB
        if (TargParent.getTargetunit().getHoBFlag()) {   // rolled a 2
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
    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/

}
