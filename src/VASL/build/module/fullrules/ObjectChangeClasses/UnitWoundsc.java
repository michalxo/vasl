package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class UnitWoundsc implements StatusChangei{
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)


    public UnitWoundsc() {

    }

    public boolean Takeaction (PersUniti TargParent) {
              /*'Name:       TargetWounds()
                    'Identifier UC 202

                    '            Preconditions()
                    '1.	SMC is alive and has passed a woundcheck

                    '            Basic Course
                    '1.	Use case begins when a wounded result is obtained from a Woundcheck
                    '2:          .SMC() 's morale and LDRM are lowered
            '3:          .SMC() 's MF are lowered to 3
            '4.	Use case ends when the SMC status is changed to wounded

            'Alternate Course A:
            'Condition:

            'Inheritance:
            'Condition:

            '            Post conditions
            '1.*/
        if (TargParent.getbaseunit().IsUnitALeader()) {
            PersCreation UseObjectFactory = new PersCreation();
            if (TargParent.getTargetunit() == null) {
                CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
                int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
                TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
            }

            TargParent.getTargetunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Wounded);
            TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Wounded);
            TargParent.getbaseunit().setCX(false);
            TargParent.getbaseunit().setPinned(false);
            TargParent.getTargetunit().UpdateTargetStatus(TargParent);
            TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " is wounded");

            // HoB
            if (TargParent.getTargetunit().getHoBFlag()) {   // rolled a 2
                Constantvalues.PersUnitResult HobChange = TargParent.getTargetunit().HOBMC();
                StatusChangei RunStatusChange;
                SelectStatusChangec GetStatusChange = new SelectStatusChangec();
                RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, TargParent);
                if (RunStatusChange != null) {
                    RunStatusChange.Takeaction(TargParent);
                } else {
                    //myPopUpList = GetStatusChange.PopUpItems; temporary while debugging UNDO
                    return false;
                }
                TargParent.getbaseunit().setOrderStatus(TargParent.getTargetunit().getOrderStatus());
                // update Target and Firing lists with new units
                if (RunStatusChange.GetNewTargs != null) {
                    myNewTargs = RunStatusChange.GetNewTargs;
                }
            }
            return true;
        } else {  // is a hero
            // create the new 138 unit
            int WoundsTo = 1101; // 138 hero
            String NewName = null;
            GameModule.getGameModule().getChatter().send("Enter Name of New Unit: " + TargParent.getbaseunit().getUnitName() + " wounds");
            PersCreation UseObjectFactory = new PersCreation();
            PersUniti NewUnit = UseObjectFactory.CreateNewInstance(WoundsTo, NewName, TargParent);
            // update new unit with values of previous unit - Do we need all of this
            UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
            if (TargParent.getTargetunit() != null) {
                // NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
                NewUnit.getTargetunit().setCombatResultsString(TargParent.getbaseunit().getUnitName() + ": " + TargParent.getTargetunit().getCombatResultsString() + " is wounded " + NewUnit.getbaseunit().getUnitName());
                // TargetPersUnit already created by UnitUpdateNewWithOldc
            }

            // change values for former unit
            if (TargParent.getTargetunit() == null) {
                CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
                int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
                TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
            }

            TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.NotInPlay);
            TargParent.getbaseunit().setCX(false);
            TargParent.getbaseunit().setPinned(false);
            TargParent.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
            TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
            TargParent.getbaseunit().setFirstSWLink(0);
            TargParent.getbaseunit().setSecondSWLink(0);
            TargParent.getbaseunit().setnumSW(0);
            TargParent.getTargetunit().UpdateTargetStatus(TargParent);

            // remove old unit from moving list TOO EARLY - DO THIS LATER
            if (TargParent.getMovingunit() != null) {Scencolls.SelMoveUnits.remove(TargParent);}
            // add new unit to Unitcol collection
            Scencolls.Unitcol.add(NewUnit);
            // Store values to update FireGroup and TargetGroup (maybe add movement?)
            if (NewUnit.getTargetunit() != null) {myNewTargs.add(NewUnit);}
            if (NewUnit.getFiringunit() != null) {myNewFiring.add(NewUnit);}

            // update SW values
            ChangeSWOwnerc SWChange = null;
            if(NewUnit.getbaseunit().getFirstSWLink() > 0) {SWChange = new ChangeSWOwnerc(NewUnit.getbaseunit().getFirstSWLink(), NewUnit.getbaseunit().getUnit_ID());}
            if(NewUnit.getbaseunit().getSecondSWLink() > 0) {SWChange = new ChangeSWOwnerc(NewUnit.getbaseunit().getSecondSWLink(), NewUnit.getbaseunit().getUnit_ID());}

            // No HoB as heros not subject to HOB
            return true;
        }
    }

    public LinkedList<PersUniti> GetNewTargs() {return myNewTargs;}
    public LinkedList<PersUniti> GetNewFirings () {return myNewFiring;}

    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
