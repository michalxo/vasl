package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class UnitReplacesStepReducesHSc implements StatusChangei{
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)

    public UnitReplacesStepReducesHSc() {

    }

    public boolean Takeaction (PersUniti TargParent) {
                /*'Name:       TargetStepReduces()

                    'Identifier UC 216

                    '            Preconditions()
                    '1.	Non-berserk Japanese Squad Target is alive and has failed an IFT MC

                    '            Basic Course
                    '1.	Use case begins when a MC failure  result is produced [UC115-JapanTargetMCResult]
                    '2.	Determine Reduction (to ReducedStrength or to HS)
                    '3.	Add new Unit [UC217-AddNewUnit]
                    '4.	Target transfers settings to new unit
                    '5.	Change visibility status of Target
                    '6.	Use case ends when the Target changes status to StepReduced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '2.

                    'create the new unit*/
        boolean PassHoBCHeck = false;  // Hob test done by last unitchange
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Replaces);
        StatusChangei RunFirstChange = new UnitReplacesc(PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        LinkedList<PersUniti> myNewUnits = RunFirstChange.getNewTargs();
        TargParent = myNewUnits.get(0);  //.GetNewTargs().get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.StepReducesHS);
        StatusChangei RunnextChange = new UnitStepReducesHSc();
        RunnextChange.Takeaction(TargParent);
        // the following needs to be done to ensure proper display and target management
        myNewTargs.add(TargParent);
        //'If Not IsNothing(RunnextChange.GetNewTargs) AndAlso RunnextChange.GetNewTargs.Count > 0 Then myNewTargs = RunnextChange.GetNewTargs
        myNewFiring = RunnextChange.getNewFirings();
        // No HoB - done in UnitDMs
        return true;

        /*boolean PassHoBCHeck = false; // Hob test done by last unitchange
        StatusChangei RunFirstChange = new UnitReplacesc(PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        TargParent = RunFirstChange.getNewTargs().get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        int ReducesTo = TargParent.getTargetunit().getReducesTo();
        String NewName = "";
        GameModule.getGameModule().getChatter().send("Enter Name of New Unit: " + TargParent.getbaseunit().getUnitName() + " step-reduces");
        PersCreation UseObjectFactory = new PersCreation();
        PersUniti NewUnit = UseObjectFactory.CreateNewInstance(ReducesTo, NewName, TargParent);
        // update new unit with values of previous unit - Do we need all of this
        UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
        if (TargParent.getTargetunit() != null) {
            // NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
            // TargetPersUnit already created by UnitUpdateNewWithOldc
            NewUnit.getTargetunit().setCombatResultsString(TargParent.getbaseunit().getUnitName() + ": " + TargParent.getTargetunit().getCombatResultsString() + " is step-reduced to " + NewUnit.getbaseunit().getUnitName());
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

        // HoB
        if (NewUnit.getTargetunit().getHoBFlag()) {   // rolled a 2
            Constantvalues.PersUnitResult HobChange = NewUnit.getTargetunit().HOBMC();
            StatusChangei RunStatusChange;
            SelectStatusChangec GetStatusChange = new SelectStatusChangec();
            RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, NewUnit);  //VS uses TargParent here?
            if (RunStatusChange != null ) {
                RunStatusChange.Takeaction(NewUnit);   //VS uses TargParent here?
            } else {
                //myPopUpList = GetStatusChange.PopUpItems; temporary while debugging UNDO
                return false;
            }
            NewUnit.getbaseunit().setOrderStatus(TargParent.getTargetunit().getOrderStatus());
            // update Target and Firing lists with new units
            if (RunStatusChange.getNewTargs() != null) {myNewTargs = RunStatusChange.getNewTargs();}
        }
        return true;*/
    }

        public LinkedList<PersUniti> getNewTargs() {return myNewTargs;}
        public LinkedList<PersUniti> getNewFirings () {return myNewFiring;}

    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
