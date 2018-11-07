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

public class EnterEnemyNotAllConAllConc implements EnterEnemyi{
    public EnterEnemyNotAllConAllConc() {}

    public boolean getOKToEnter(Constantvalues.UMove MovementOptionClicked, EnemyChecksC CheckforEnemy, Locationi UsingLoc) {
        // If all present concealed and/or Hidden and no or some entering concealed
        // Berserk or Human Wave attackers can enter regardless of Present units
        // Check for above situations - may not be required but do first as per other conditions


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
        // if Entering is HumanWave or Banzai then can enter
        if (MovementOptionClicked == Constantvalues.UMove.HumanWave || MovementOptionClicked == Constantvalues.UMove.Banzai) {EntryAllowed = true;}
        if (EntryAllowed) {return true;} // entry not blocked
        // Hidden placed onboard concealed - hidden and concealed are not part of VisibleContentsInHex
        PutHidden.PutHiddenOnboard(CheckforEnemy.getLocationContents());
        // use Random Selection to determine revealed defensive unit
        boolean loop = true;
        PersUniti RevealedUnit = null;
        LinkedList<LocationContentc> RanSelEligList = RanSelList.CreateRandSelList(CheckforEnemy.getLocationContents());
        while (loop){
            if (RanSelEligList.size() > 1) { // more than one real concealed units exist - do random sel
                 RevealedUnit = CheckReveal.RevealConcealUsingRanSel(RanSelEligList);
                 if (RevealedUnit != null){
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
                return true;
            }
            // If enemy present then all entering revealed regardless of present type
            if (RevealedUnit == null) { // no enemy present
                return true; // no entering revealed
                //loop = false;
            } else { // enemy present - all entering revealed
                CheckReveal.RevealEntering(UsingLoc.getvaslhex(), MovementOptionClicked);
                // now determine if revealed present blocks entry or not

                if (!RevealedUnit.getbaseunit().IsUnitASMC()) {
                    // Present is MMC (concealed cannot be disrupted or unarmed); entry blocked because Entering is Berserk, Human Wave - then entry is allowed regardless
                    return EntryAllowed;
                    //loop = false;
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
                            return false;
                }
            }

        }
        return false;
    }

}
