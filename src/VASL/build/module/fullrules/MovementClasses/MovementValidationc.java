package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;
import VASL.build.module.fullrules.UtilityClasses.LocationVisibilityc;
import VASL.build.module.fullrules.UtilityClasses.UnitsVisibilityc;
import VASL.build.module.fullrules.UtilityClasses.VisibilityStatusi;

public class MovementValidationc {
    private boolean OKvalue;

    public MovementValidationc(Location LocIndexclicked, Constantvalues.UMove MovementOptionClicked) {
        // called by Movementc.IsEntryblocked, MoveNewHex.MoveAllOK, MoveNewLevel.MoveAllOK
        // determines if presence of enemy units blocks movement
        // set OK property to indicate result

        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        ScenarioC scen = ScenarioC.getInstance();
        PersUniti MovingUnit;
        boolean EnemyPresent = false;
        Constantvalues.Nationality MovNat = null;
        Locationc UsingLoc;
        int EnteringVisStatus = 0;
        int PresentVisStatus = 0;
        // use these constants to store visibility status of units in hex
        final int Mixed = 1;
        final int AllConc = 2;
        final int AllVis = 3;
        // Get location info for location being entered
        if (LocIndexclicked != null) { // every location should exist
            GetALocationFromMap GetLocs = new GetALocationFromMap();
            UsingLoc = GetLocs.RetrieveLocationfromHex(LocIndexclicked);
        } else {
            //MessageBox.Show("Error: locindex value not found in Map table", "MovementValidation")
            return;
        }
        // Determine if present units (both real and dummy) are enemy to the entering side
        // get what is present in the hex (visible and non-visible)

        MovNat = Scencolls.SelMoveUnits.getFirst().getbaseunit().getNationality();
        EnemyChecksC CheckforEnemy = new EnemyChecksC(LocIndexclicked, MovNat, scen.getScenID());
        EnemyPresent = CheckforEnemy.EnemyinLocationTest();
        // If same side, ok, if not then determine if move valid
        if (!EnemyPresent) {
            OKvalue = true;
        } else {
            // Determine entering visibility status
            VisibilityStatusi VisibilityStatus = new UnitsVisibilityc(Scencolls.SelMoveUnits);
            EnteringVisStatus = VisibilityStatus.GetStatus();
            // Determine present visibility Status
            VisibilityStatus = new LocationVisibilityc(CheckforEnemy.getLocationContents());
            PresentVisStatus = VisibilityStatus.GetStatus();
            // Now process all the permutations and combination of visible status
            // Determine if movement action is bypass which has different effects
            // NEED TO COME BACK AND REVIEW BYPASS ROUTINE, AND CLASS IT
            if (MovementOptionClicked == Constantvalues.UMove.bypassrate || MovementOptionClicked == Constantvalues.UMove.extrabypassrate) {
                /*'All entering units are revealed/dummies eliminated - IS THIS CORRECT FROM A RULES BASIS -WHAT SECTION?
                'NEED TO REVISE THIS CODE - CALL REVEALENTERING - WHEN IMPLEMENT BYPASS July 2014
                'Dim RemoveList = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
                'For Each MovingUnit In Scencolls.SelMoveUnits
                '    If MovingUnit.MovingPersUnit.IsDummy Then
                '        Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange = New ObjectChange.ASLXNA.RevealDummyC(MovingUnit.BasePersUnit.Unit_ID)
                '        If UnittoChange.TakeAction() Then
                '            RemoveList.Add(MovingUnit) ' put in list to remove from stack
                '        Else
                '            MessageBox.Show(MovingUnit.BasePersUnit.UnitName & " could not be eliminated. Action fails")
                '        End If
                '    Else
                '        Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange = New ObjectChange.ASLXNA.RevealUnitC(MovingUnit.BasePersUnit.Unit_ID)
                '        If UnittoChange.TakeAction() Then
                '            ' change display
                '            Dim DMCHeck = New AutoDM()
                '            ' Dim TargetUpdate = New UpdateTargetUnits
                '        Else
                '            MessageBox.Show(MovingUnit.BasePersUnit.UnitName & " could not be revealed. Action fails")
                '        End If
                '    End If
                '    OKvalue = True ' bypass is not blocked by units in woods/building
                'Next
                '' remove revealed dummies
                'For Each RemoveItem As ObjectClassLibrary.ASLXNA.PersUniti In RemoveList
                '    Scencolls.SelMoveUnits.Remove(RemoveItem)
                'Next
                'need to update screen?*/
            }
        }

        // Handle each visible status case
        if (PresentVisStatus == AllVis) {
            EnterEnemyi ValidEnterAllVis = new EnterEnemyAllVisc();
            OKvalue = ValidEnterAllVis.getOKToEnter(MovementOptionClicked, CheckforEnemy, UsingLoc);
        } else if(EnteringVisStatus != AllConc && PresentVisStatus == Mixed) {
            EnterEnemyi ValidEnterNotAllConMixed = new EnterEnemyNotAllConMixedc();
            OKvalue = ValidEnterNotAllConMixed.getOKToEnter(MovementOptionClicked, CheckforEnemy, UsingLoc);
        } else if(EnteringVisStatus == AllConc && PresentVisStatus == Mixed) {
            // If some but not all present concealed and/or Hidden and all entering concealed
            // Human Wave and Banzai do not apply to concealed units
            // check for Present conditions that allow entry: single SMC, unarmed, disrupted
            EnterEnemyi ValidEnterAllConMixed = new EnterEnemyAllConMixedc();
            OKvalue = ValidEnterAllConMixed.getOKToEnter(MovementOptionClicked, CheckforEnemy, UsingLoc);
        } else if(EnteringVisStatus != AllConc && PresentVisStatus == AllConc) {
            // If all present concealed and/or Hidden and no or some entering concealed
            // Berserk or Human Wave attackers can enter regardless of Present units
            // Check for above situations - may not be required but do first as per other conditions
            EnterEnemyi ValidEnterNotAllConAllCon = new EnterEnemyNotAllConAllConc();
            OKvalue = ValidEnterNotAllConAllCon.getOKToEnter(MovementOptionClicked, CheckforEnemy, UsingLoc);
        } else if(EnteringVisStatus == AllConc && PresentVisStatus == AllConc) {
            // If all present concealed and/or Hidden and all entering concealed then
            // Human Wave and Banzai do not apply to concealed units
            // check for Present conditions that allow entry: single SMC, unarmed, disrupted
            EnterEnemyi ValidEnterAllConAllCon = new EnterEnemyAllConAllConc();
            OKvalue = ValidEnterAllConAllCon.getOKToEnter(MovementOptionClicked, CheckforEnemy, UsingLoc);
        } else {  // next condition of entering and present - there isn' t one

        }
    }
    public boolean OK() {
            return OKvalue;
    }

}
