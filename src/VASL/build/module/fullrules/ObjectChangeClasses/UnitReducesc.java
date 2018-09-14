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

public class UnitReducesc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring;
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    private boolean myHOBCHeck;

    public UnitReducesc(String Resultstring, boolean PassHOBcheck) {

        myResultstring = Resultstring;
    }

    public boolean Takeaction (PersUniti TargParent) {
            /*'Name:       TargetReduces()

                    'Identifier UC 203

                    '            Preconditions()
                    '1.	MMC Target is alive and has suffered a CR result

                    '            Basic Course
                    '1.	Use case begins when a CR result is produced  [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult]
                    '2.	Add new HS [UC217-AddNewUnit]
                    '3.	Target transfers settings to new unit
                    '4.	Change visibility status of Target
                    '5.	Use case ends when the Target status changes to NotInPlay

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.

                    'create the new HS*/
        int ReducesTo = TargParent.getTargetunit().getReducesTo();
        String NewName ="";
        while (NewName =="") {
            NewName = askforNewUnit(TargParent.getbaseunit().getUnitName());
        }
        PersCreation UseObjectFactory = new PersCreation();
        PersUniti NewUnit = UseObjectFactory.CreateNewInstance(ReducesTo, NewName, TargParent);
        // add new unit to Unitcol collection
        Scencolls.Unitcol.add(NewUnit);
        // update ID value of counter to match new unit
        setcounterID(NewUnit.getbaseunit().getUnit_ID(), TargParent);
        // update new HS with values of previous unit - Do we need all of this
        UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
        if (TargParent.getTargetunit() != null) {  // TargetPersUnit already created by UnitUpdateNewWithOldc
            //NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
            NewUnit.getTargetunit().setCombatResultsString(": " + TargParent.getTargetunit().getCombatResultsString() + " Reduces to " + NewUnit.getbaseunit().getUnitName());
            if (NewUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken) {NewUnit.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.Broken_DM);}
        }

        // change values for former unit
        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            UseObjectFactory = new PersCreation();
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
        TargParent.getbaseunit().setHex(null);
        TargParent.getbaseunit().sethexlocation(null);
        TargParent.getbaseunit().sethexPosition(Constantvalues.AltPos.None);
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        //'remove old unit from moving list TOO EARLY - DO THIS LATER
        if (TargParent.getMovingunit() != null) {Scencolls.SelMoveUnits.remove(TargParent);}

        //'Store values to update FireGroup and TargetGroup (maybe add movement?)
        if (NewUnit.getTargetunit() !=null) {myNewTargs.add(NewUnit);}
        if (NewUnit.getFiringunit() != null) {myNewFiring.add(NewUnit);}
        // remove oldunit from Unitcol - same id value is causing problems. May undo this once a new id scheme is in place
        Scencolls.Unitcol.remove(TargParent);
        //update SW values
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
                Oldname + " is reduced to a Half-Squad",
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
