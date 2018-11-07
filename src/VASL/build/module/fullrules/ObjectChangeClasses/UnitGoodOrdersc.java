package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitGoodOrdersc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();

    //Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    public UnitGoodOrdersc() {

    }
    public boolean Takeaction(PersUniti TargParent) {

        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        TargParent.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        TargParent.getbaseunit().setCX(false);
        TargParent.getbaseunit().setPinned(false);
        TargParent.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        //TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() +  " DMs");
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.GoodOrders);
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);

        // HoB
        // does not apply as this is used during HOB results process to return broken units to Good Order
        return true;
    }

    public LinkedList<PersUniti> getNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        // no code required; no new unit
        return null;
    }
}
