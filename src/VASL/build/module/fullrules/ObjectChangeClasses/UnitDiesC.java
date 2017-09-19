package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitDiesC implements StatusChangei {

    public UnitDiesC(){}

    public boolean Takeaction(PersUniti TargParent) {
        /*Name:       TargetDies()

        Identifier UC 201

                    Preconditions()
        1.	Target is alive

                    Basic Course
        1.	Use case begins when Target dies
        2.	Target is not Goodorder nor Visible
        3.	Use case ends when the Target’s status is changed to KIA

        Alternate Course A:
        Condition:

        Inheritance:
        Condition:

                    Post conditions
        1.	*/

        if (TargParent.getTargetunit() != null) {
        } else {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan  = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        if (TargParent.getTargetunit() != null) {TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.KIAInf);}
        TargParent.getbaseunit().setCX(false);
        TargParent.getbaseunit().setPinned(false);
        TargParent.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        TargParent.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.KIAInf);
            //'.BasePersUnit.Hexnum = 0
            //'.BasePersUnit.LOCIndex = 0
            //'.BasePersUnit.hexlocation = 0
            //'.BasePersUnit.hexPosition = 0
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " Dies");
        if (TargParent.getbaseunit().getnumSW() > 0) {
            if (TargParent.getbaseunit().getFirstSWLink() > 0) {
                StatusChangei Dropit = new UnitDropsFirstSWc();
                Dropit.Takeaction(TargParent);
            }
            if (TargParent.getbaseunit().getSecondSWLink() > 0) {
                StatusChangei Dropit2 = new UnitDropsSecondSWc();
                Dropit2.Takeaction(TargParent);
            }
        }
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        // No HoB roll even if 2 rolled as unit dead
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs () {
        // no code required; no new unit
        return null;
    }
    public LinkedList<PersUniti> GetNewFirings () {
        // no code required; no new unit
        return null;
    }
    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/

}
