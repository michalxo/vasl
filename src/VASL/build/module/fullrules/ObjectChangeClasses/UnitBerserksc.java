package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitBerserksc implements StatusChangei {

    ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    public UnitBerserksc(){}

    public boolean Takeaction(PersUniti TargParent) {
        /*Name:       TargetBerserks()

        Identifier UC 212

                    Preconditions()
        1.	MMC Target is alive and has berserked on the HOB dr

                    Basic Course
        1.	Use case begins when a Berserk result is produced [UC109-TargetHOBResult]
        2.	Target drops SW more than 1PP or with total more than IPC [UC209-TargetDropsSW]
        3.	Target changes GO and other settings
        4.	Use case ends when the Target changes status to Beserk

        Alternate Course A:
        Condition:

        Inheritance:
        Condition:

                    Post conditions
        1.	*/

        if (TargParent.getTargetunit() == null ) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan  = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.Berserk);
        TargParent.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.Berserk);
        TargParent.getbaseunit().setCX(false);
        TargParent.getbaseunit().setPinned(false);
        //'.BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
        //'.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " HOB: goes Berserk");
        // No HoB roll as berserk units not subject to HOB
        return true;
    }

    public LinkedList<PersUniti> getNewTargs () {
        // no code required; no new unit
        return null;
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
