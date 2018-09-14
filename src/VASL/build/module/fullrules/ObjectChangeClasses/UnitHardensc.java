package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.CounterActions;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Scanner;

public class UnitHardensc  implements  StatusChangei {
    
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    
    public UnitHardensc () {
        
    }
    public boolean Takeaction(PersUniti TargParent) {
        /*Name:       TargetHardens()

        Identifier UC 211

                    Preconditions()
        1.	MMC Target is alive and has hardened on the HOB dr

                    Basic Course
        1.	Use case begins when a Harden result is produced [UC109-TargetHOBResult]
        2.	Check if Unit goes Fanatic; if so, exit to that UC [UC-??]
        3:          .Add New Unit
        4.	Target transfers settings to new unit
        5.	Change visibility status of Target
        6.	Use case ends when the Target changes status to Hardened 

        Alternate Course A: 
        Condition:

        Inheritance:
        Condition:

                    Post conditions
        1.	*/
        
        // create the new unit
        int HardensTo = TargParent.getTargetunit().getHardensTo();
        String NewName ="";
        while (NewName =="") {
            NewName = askforNewUnit(TargParent.getbaseunit().getUnitName());
        }
        PersCreation UseObjectFactory = new PersCreation();
        PersUniti NewUnit = UseObjectFactory.CreateNewInstance(HardensTo, NewName, TargParent);
        // add new unit to Unitcol collection
        Scencolls.Unitcol.add(NewUnit);
        // update ID value of counter to match new unit
        setcounterID(NewUnit.getbaseunit().getUnit_ID(), TargParent);
        // update new unit with values of previous unit - Do we need all of this
        UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
        NewUnit.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        // BH restores unit to GO A15.3
        NewUnit.getTargetunit().UpdateTargetStatus(NewUnit);
        if (TargParent.getTargetunit() != null) {
                // NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
                // TargetPersUnit already created by UnitUpdateNewWithOldc
           TargParent.getTargetunit().setCombatResultsString(TargParent.getbaseunit().getUnitName() + ": " + TargParent.getTargetunit().getCombatResultsString() + "HOB: Hardens to " + NewUnit.getbaseunit().getUnitName());
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
        //'.TargetPersUnit.CombatResultString &= "Reduces to " & Trim(NewUnit.BasePersUnit.UnitName)

        //'remove old unit from moving list TOO EARLY - DO THIS LATER
        //If Not IsNothing(TargParent.MovingPersUnit) Then Scencolls.SelMoveUnits.Remove(TargParent)

        // Store values to update FireGroup and TargetGroup (maybe add movement?)
        if (NewUnit.getTargetunit() != null ) {myNewTargs.add(NewUnit);}
        if (NewUnit.getFiringunit() != null) {myNewFiring.add(NewUnit);}

        // update SW values
        ChangeSWOwnerc SWChange;
        if (NewUnit.getbaseunit().getFirstSWLink() > 0) {SWChange = new ChangeSWOwnerc(NewUnit.getbaseunit().getFirstSWLink(), NewUnit.getbaseunit().getUnit_ID());}
        if (NewUnit.getbaseunit().getSecondSWLink() > 0) {SWChange = new ChangeSWOwnerc(NewUnit.getbaseunit().getSecondSWLink(), NewUnit.getbaseunit().getUnit_ID());}
        // No HoB - Hardening is caused by HOB
        return true;

    }

    public LinkedList<PersUniti> getNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        // no code required; no new unit
        return null;
    }
    /**
     * Displays the input dialog and returns user input
     */
    public String askforNewUnit(String Oldname) {
        JOptionPane pane = new JOptionPane();
        String newname =  pane.showInputDialog(null,
                "Enter Name of New Squad: ",
                "Battle Hardens: " + Oldname + " hardens",
                JOptionPane.QUESTION_MESSAGE
        );
        return newname;
    }
    // move this out to a common function as it will be the same in all classes
    private void setcounterID(int newunitID, PersUniti FormerUnit){
        CommonFunctionsC ToDO = new CommonFunctionsC(FormerUnit.getbaseunit().getScenario());
        GamePiece CounterToUse = ToDO.GetGamePieceFromID(FormerUnit.getbaseunit().getUnit_ID());

        if (CounterToUse != null) {
            // trigger counter action
            CounterActions counteractions = new CounterActions();
            counteractions.updatecounterID(newunitID, FormerUnit);
        }
    }
}
