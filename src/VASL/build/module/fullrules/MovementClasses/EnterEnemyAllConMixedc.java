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

public class EnterEnemyAllConMixedc implements EnterEnemyi {

    public boolean getOKToEnter(Constantvalues.UMove MovementOptionClicked, EnemyChecksC CheckforEnemy, Locationi UsingLoc) {
        // If some but not all present concealed and/or Hidden and all entering concealed
        // no need to check for Banzai / Human Wave as cannot be concealed and entering are concealed
        // check for Present conditions that allow entry: single SMC, unarmed, disrupted
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
        // check if entery allowed regardless of Present status
        // if dummy or all berserk then let enter
        // check for Present conditions that allow entry: single SMC, unarmed, disrupted
        OKValue = CheckPresent.PresentTest(CheckEntry.getMovingisMMC());
        // One entering revealed temporarily/ if only Dummies then eliminated
        //if false then at least one visible MMC present
        //One entering revealed temporarily/ if only Dummies then eliminated
        if (CheckEntry.getMovingIsDummy()) {  // all dummies revealed
            OKValue = false;
            for (PersUniti MovingUnit : Scencolls.SelMoveUnits) {
                VisibilityChangei UnittoChange = new ElimConcealC(MovingUnit.getbaseunit().getUnit_ID());
                if (UnittoChange.TakeAction()) {
                    //MessageBox.Show("Dummy revealed, No Entry Allowed!")
                }
            }
        } else { // reveal one item and proceed, moving player decides which one
            String NewName = "";
            while (NewName == "") {
                NewName = askforNewUnit();
                // verify
                for (PersUniti TestUnit : Scencolls.SelMoveUnits) {
                    if (TestUnit.getbaseunit().getUnitName().equals(NewName)) {
                        // MessageBox.Show("Verified. Proceed");
                        OKValue = true;
                    }
                }
            }

            // If entering is not only dummy then Hidden placed onboard concealed and use Random Selection to determine revealed defensive unit – which will block entry unless all concealed were dummies
            // Now need to check hidden and concealed units to see if they can block access - only if entry allowed so far
            if (OKValue == true) {
                // Hidden placed onboard concealed - hidden and concealed are not part of VisibleContentsInHex
                PutHidden.PutHiddenOnboard(CheckforEnemy.getLocationContents());
                // use Random Selection to determine revealed defensive units
                PersUniti RevealedUnit = null;
                LinkedList<LocationContentc> RanSelEligList = RanSelList.CreateRandSelList(CheckforEnemy.getLocationContents());
                if (RanSelEligList.size() > 1) { // more than one real concealed units exist - do random sel
                    RevealedUnit = CheckReveal.RevealConcealUsingRanSel(RanSelEligList);
                    if (RevealedUnit != null){
                        AutoDM DMCHeck = new AutoDM(RevealedUnit);
                        OKValue = false;

                    }
                    // Entry is now blocked regardless of unit revealed as cannot be either SingleSMC
                    // or AllDisruptedOrUnarmed - at least two units and concealed cannot be disrupted or unarmed
                } else if (RanSelEligList.size() == 1) {
                    RevealedUnit =CheckReveal.RevealSingleItem(RanSelEligList.getFirst().getObjID());
                    if (RevealedUnit != null){
                        AutoDM DMCHeck = new AutoDM(RevealedUnit);
                        OKValue = false;
                    }
                    // Entry is now blocked regardless of unit revealed as cannot be either SingleSMC
                    // or AllDisruptedOrUnarmed - at least two units and concealed cannot be disrupted or unarmed
                } else {
                    // no concealed units found, entry not blocked, remove dummies
                    /*for Each FindCon As ObjectClassLibrary.ASLXNA.LocationContent In CheckforEnemy.PassLocationConents
                    If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, FindCon.TypeID) Then
                    Dim PassCon As ObjectClassLibrary.ASLXNA.PersUniti = (From Selunit As ObjectClassLibrary.ASLXNA.PersUniti In
                    Scencolls.Unitcol Where Selunit.BasePersUnit.Unit_ID = FindCon.ObjID Select Selunit).First
                    Dim RunStatusChange As ObjectChange.ASLXNA.StatusChangei = New ObjectChange.ASLXNA.UnitRevealAllDummyc
                    RunStatusChange.Takeaction(PassCon)
                    End If
                    Next*/
                    return OKValue;
                }
            }
            // If Present is more than single SMC, all entering revealed (unless AM from/to concealment terrain) and entry blocked
            CheckReveal.RevealEntering(UsingLoc.getvaslhex(), MovementOptionClicked);
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
