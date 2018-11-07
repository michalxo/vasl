package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;
import VASL.build.module.fullrules.UtilityClasses.RandomSelection;

import java.util.LinkedList;

public class EnterEnemyNotAllConMixedc implements  EnterEnemyi {
    public EnterEnemyNotAllConMixedc() {

    }

    public boolean getOKToEnter(Constantvalues.UMove MovementOptionClicked, EnemyChecksC CheckforEnemy, Locationi UsingLoc) {
        // If some present concealed or Hidden and none or some entering concealed
        // Berserk or Human Wave attackers can enter regardless of Present units
        // Check for above situations, entering cannot be all dummy

        // boolean MovingIsMMC = false; boolean MovingIsDummy  = true;
        boolean OKValue = false;
//        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil :
        EntryTestc CheckEntry = new EntryTestc();
        PresentTestc CheckPresent = new PresentTestc(null);
        HiddenOnBoardc PutHidden = new HiddenOnBoardc();
        RandomSelListc RanSelList = new RandomSelListc();
        Revealingc CheckReveal = new Revealingc();
        ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();
        boolean EntryAllowed = CheckEntry.Entrytest();
        // if dummy or all berserk then let enter
        // If Entering is HumanWave or Banzai then can enter
        if (MovementOptionClicked == Constantvalues.UMove.HumanWave || MovementOptionClicked == Constantvalues.UMove.Banzai) {EntryAllowed = true;}
        if (EntryAllowed) {
            OKValue = true;  // entry not blocked
        } else {
            // check for Present conditions that allow entry: single SMC, unarmed, disrupted
            OKValue = CheckPresent.PresentTest(CheckEntry.getMovingisMMC());
        }
        // Now need to check hidden and concealed units to see if they can block access - only if entry allowed so far
        // at this point we know that there is an unconcealed unit present but it does not block entry
        if (OKValue == true) {
            // Hidden placed onboard concealed - hidden and concealed are not part of VisibleContentsInHex
            PutHidden.PutHiddenOnboard(CheckforEnemy.getLocationContents());
            // use Random Selection to determine revealed defensive unit
            boolean loop = true;
            PersUniti RevealedUnit = null;
            LinkedList<LocationContentc> RanSelEligList = RanSelList.CreateRandSelList(CheckforEnemy.getLocationContents());
            if (RanSelEligList.size() > 1) { // more than one real concealed units exist - do random sel
                RevealedUnit = CheckReveal.RevealConcealUsingRanSel(RanSelEligList);
                if (RevealedUnit != null){
                    AutoDM DMCHeck = new AutoDM(RevealedUnit);
                    OKValue = false;
                    loop = false;
                }
                // no need to delete Concealment counter as at least one concealed unit must remain
                // Entry is now blocked regardless of unit revealed as cannot be either SingleSMC
                // or AllDisruptedOrUnarmed - at least two units and concealed cannot be disrupted or unarmed
            } else if (RanSelEligList.size() == 1) {
                // reveal single concealed item
                RevealedUnit = CheckReveal.RevealSingleItem(RanSelEligList.getFirst().getObjID());
                if (RevealedUnit != null){
                    AutoDM DMCHeck = new AutoDM(RevealedUnit);
                    // at this point, do not need to set OKValue
                }
                // Entry is now blocked regardless of unit revealed as cannot be either SingleSMC
                // or AllDisruptedOrUnarmed - at least two units and concealed cannot be disrupted or unarmed
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
            }
        }
        return OKValue;
    }
}
