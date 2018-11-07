package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectChangeClasses.ElimConcealC;
import VASL.build.module.fullrules.ObjectChangeClasses.VisibilityChangei;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;
import VASL.build.module.fullrules.UtilityClasses.RandomSelection;

import javax.swing.*;
import java.util.LinkedList;

public class EnterEnemyAllConAllConc implements EnterEnemyi {
    public EnterEnemyAllConAllConc() {

    }

    public boolean getOKToEnter(Constantvalues.UMove MovementOptionClicked, EnemyChecksC CheckforEnemy, Locationi UsingLoc) {
        // boolean MovingIsMMC = false; boolean MovingIsDummy  = true;
        boolean OKValue = false;
//        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil :
        EntryTestc CheckEntry = new EntryTestc();
        PresentTestc CheckPresent = new PresentTestc(UsingLoc);
        HiddenOnBoardc PutHidden = new HiddenOnBoardc();
        RandomSelListc RanSelList = new RandomSelListc();
        Revealingc CheckReveal = new Revealingc();
        ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();
        boolean EntryAllowed = CheckEntry.Entrytest();
        // if dummy or all berserk then let enter
        // no need to check for Banzai / Human Wave as cannot be concealed and entering are concealed
        // check for Present conditions that allow entry: single SMC, unarmed, disrupted
        OKValue = CheckPresent.PresentTest(CheckEntry.getMovingisMMC());
        // One entering revealed temporarily/ if only Dummies then eliminated
        if (CheckEntry.getMovingIsDummy()) { // all dummies revealed
            OKValue = false;
            for (PersUniti MovingUnit : Scencolls.SelMoveUnits) {
                VisibilityChangei UnittoChange = new ElimConcealC(MovingUnit.getbaseunit().getUnit_ID());
                if (UnittoChange.TakeAction()) {
                    // MessageBox.Show("Dummy revealed, No Entry Allowed!")
                    return false;
                }
            }
        } else { // reveal one item and proceed
            String NewName = "";
            while (NewName == "") {
                NewName = askforNewUnit();
                // verify
                for (PersUniti TestUnit: Scencolls.SelMoveUnits){
                    if (TestUnit.getbaseunit().getUnitName().equals(NewName)) {
                        // MessageBox.Show("Verified. Proceed");
                        OKValue = true;
                    }
                }
            }
        }
        // If entering is not only dummy then Hidden placed onboard concealed and use Random Selection to determine revealed defensive unit – which will block entry unless all concealed present were dummies
        // Now need to check hidden and concealed units to see if they can block access - only if entry allowed so far
        if (OKValue == true) {
            // Hidden placed onboard concealed - hidden and concealed are not part of VisibleContentsInHex
            PutHidden.PutHiddenOnboard(CheckforEnemy.getLocationContents());
            // use Random Selection to determine revealed defensive unit
            boolean loop = true;
            PersUniti RevealedUnit = null;
            LinkedList<LocationContentc> RanSelEligList = RanSelList.CreateRandSelList(CheckforEnemy.getLocationContents());
            while (loop){
                if (RanSelEligList.size() > 1) { // more than one real concealed units exist - do random sel
                    RevealedUnit = CheckReveal.RevealConcealUsingRanSel(RanSelEligList);
                    if (RevealedUnit != null ){
                        AutoDM DMCHeck = new AutoDM(RevealedUnit);
                        OKValue = false;
                        loop = false;
                    }
                } else if (RanSelEligList.size() == 1) {
                    // reveal single concealed item
                    RevealedUnit = CheckReveal.RevealSingleItem(RanSelEligList.getFirst().getObjID());
                    if (RevealedUnit != null){
                        AutoDM DMCHeck = new AutoDM(RevealedUnit);
                        // at this point, do not need to set OKValue
                    }
                } else {
                        // no concealed units found, entry not blocked, remove dummies
                        /*For Each FindCon As ObjectClassLibrary.ASLXNA.LocationContent In CheckforEnemy.PassLocationConents
                            If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, FindCon.TypeID) Then
                                Dim PassCon As ObjectClassLibrary.ASLXNA.PersUniti = (From Selunit As ObjectClassLibrary.ASLXNA.PersUniti In
                                Scencolls.Unitcol Where Selunit.BasePersUnit.Unit_ID = FindCon.ObjID Select Selunit).First
                                Dim RunStatusChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitRevealAllDummyc
                                RunStatusChange.Takeaction(PassCon)
                            End If
                        Next*/
                        OKValue = true;
                }
                // If enemy present then all entering revealed regardless of present type
                if (RevealedUnit == null) { // no enemy present
                        OKValue = true; // no entering revealed
                        loop = false;
                } else { // enemy present - all entering revealed unless AM
                        CheckReveal.RevealEntering(UsingLoc.getvaslhex(), MovementOptionClicked);
                        // now determine if revealed present blocks entry or not

                        if (!RevealedUnit.getbaseunit().IsUnitASMC()) {
                            // Present is MMC (concealed cannot be disrupted or unarmed); entry blocked because Entering cannot be Berserk, Human Wave
                            OKValue = false;
                            loop = false;
                        } else {
                            // Present is single SMC then can choose to not enter or to engage in InfOvr
                            // more concealed must be revealed if possible to prevent InfOvr
                            /*Dim Entrychoice
                            As DialogResult = MessageBox.Show("Do you wish to enter and engage in Infantry Overrun of the SMC?", "SMC revealed", _
                                    MessageBoxButtons.YesNo, MessageBoxIcon.Question)
                            If Entrychoice = DialogResult.Yes Then 'must reveal other concealed present if exist
                            'need to repeat the whole reveal procedure - which is why Do Loop
                            Else
                                    OKValue = False 'entry blocked
                            Exit Do
                            End If*/
                        }
                }

            }

// USE ENEMYCHECKS INSTEAD OF INFANTRYCOMMONFUNCTIONS
            /*InfantryUnitCommonFunctionsc infcomfunc = new InfantryUnitCommonFunctionsc();
            LinkedList<PersUniti> unitsinloc = infcomfunc.FindUnitsInLocation(UsingLoc.getvaslhex(), UsingLoc.getvasllocation(), UseNat);
            if (unitsinloc == null || unitsinloc.isEmpty()) {
                return true;
            }  // no opponents in new location*/



        }
        return OKValue;
    }
        /**
         * Displays the input dialog and returns user input
         */
    public String askforNewUnit() {
            JOptionPane pane = new JOptionPane();
            String newname =  pane.showInputDialog(null,
                    "Enter name of unit temporarily revealed: ",
                    "Concealed Stack trying to enter hex",
                    JOptionPane.QUESTION_MESSAGE
            );
            return newname;
    }
}
