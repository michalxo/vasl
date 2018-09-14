package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.CounterActions;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;

import javax.swing.*;
import java.util.LinkedList;

public class UnitSubstitutesc implements StatusChangei{
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)


    public UnitSubstitutesc() {

    }

    public boolean Takeaction (PersUniti TargParent) {
            /*'Name:       TargetSubstitutes()

                    'Identifier UC 214

                    '            Preconditions()
                    '1.	MMC Target with ELR 5 is alive and has failed a MC by more than its ELR

                    '            Basic Course
                    '1.	Use case begins when an ELR failure  result is produced (several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult;
                    '2.	Add 2 new HS [UC217-AddNewUnit]
                    '3.	Target transfers settings to new unit
                    '4.	Change visibility status of Target
                    '5.	Use case ends when the Target changes status to Replaced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.

                    'create the new units*/
        for (int x = 1; x < 3; x++) {
            int SubsTo = TargParent.getTargetunit().getSubstitutesTo();
            String NewName = "";
            while (NewName =="") {
                NewName = askforNewUnit(TargParent.getbaseunit().getUnitName());
            }
            PersCreation UseObjectFactory = new PersCreation();
            PersUniti NewUnit = UseObjectFactory.CreateNewInstance(SubsTo, NewName, TargParent);
            // add new unit to Unitcol collection
            Scencolls.Unitcol.add(NewUnit);
            // update ID value of counter to match new unit
            setcounterID(NewUnit.getbaseunit().getUnit_ID(), TargParent);
            // update new HS with values of previous unit - Do we need all of this
            UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
            if (TargParent.getTargetunit() != null) {
                // NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
                NewUnit.getTargetunit().setCombatResultsString(TargParent.getbaseunit().getUnitName() + ": " + TargParent.getTargetunit().getCombatResultsString() + " is replaced by " + NewUnit.getbaseunit().getUnitName());
                // TargetPersUnit already created by UnitUpdateNewWithOldc
            }

            // update SW values
            if(TargParent.getbaseunit().getFirstSWLink() > 0) {
                int SWItem = TargParent.getbaseunit().getFirstSWLink();
                SuppWeapi SWtoChange = null;
                for (SuppWeapi TestSW: Scencolls.SWCol){
                    if (TestSW.getbaseSW().getSW_ID() == SWItem) {
                        SWtoChange = TestSW;
                        break;
                    }
                }
                String SWName = SWtoChange.getbaseSW().getUnitName();
                GameModule.getGameModule().getChatter().send("Does " + NewUnit.getbaseunit().getUnitName() + " take possession of " + SWName + "?" + "Unit Substitution");
                //If(Response = System.Windows.Forms.DialogResult.Yes) Then
                StatusChangei ChangeSWPoss = new UnitPossessesSWc(SWItem);
                ChangeSWPoss.Takeaction(NewUnit);
                TargParent.getbaseunit().setFirstSWLink(0);
            }
            if(TargParent.getbaseunit().getSecondSWLink() > 0) {
                int SWItem = TargParent.getbaseunit().getSecondSWLink();
                SuppWeapi SWtoChange = null;
                for (SuppWeapi TestSW: Scencolls.SWCol){
                    if (TestSW.getbaseSW().getSW_ID() == SWItem) {
                        SWtoChange = TestSW;
                        break;
                    }
                }
                String SWName = SWtoChange.getbaseSW().getUnitName();
                GameModule.getGameModule().getChatter().send("Does " + NewUnit.getbaseunit().getUnitName() + " take possession of " + SWName + "?" + "Unit Substitution");
                //If(Response = System.Windows.Forms.DialogResult.Yes) Then
                StatusChangei ChangeSWPoss = new UnitPossessesSWc(SWItem);
                ChangeSWPoss.Takeaction(NewUnit);
                TargParent.getbaseunit().setSecondSWLink(0);
            }

            // change values for former unit
            if (x == 2) { // do on second pass to ensure current values passed to both new units
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
            }

            // remove old unit from moving list TOO EARLY - DO THIS LATER
            if (TargParent.getMovingunit() != null) {Scencolls.SelMoveUnits.remove(TargParent);}
            // Store values to update FireGroup and TargetGroup (maybe add movement?)
            if (NewUnit.getTargetunit() != null) {myNewTargs.add(NewUnit);}
            if (NewUnit.getFiringunit() != null) {myNewFiring.add(NewUnit);}
        }
        // NO HoB as can' t produce necessary conditions for ELR5 substitution on a 2 roll
        return true;
    }

        public LinkedList<PersUniti> getNewTargs() {return myNewTargs;}
        public LinkedList<PersUniti> getNewFirings () {return myNewFiring;}

    /**
     * Displays the input dialog and returns user input
     */
    public String askforNewUnit(String Oldname) {
        JOptionPane pane = new JOptionPane();
        String newname =  pane.showInputDialog(null,
                "Enter Name of New Half-Squad: ",
                Oldname + " suffers ELR5 Substitution",
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
