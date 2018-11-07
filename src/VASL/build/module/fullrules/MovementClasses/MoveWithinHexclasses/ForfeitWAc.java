package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.*;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.ArrayList;
import java.util.LinkedList;

public class ForfeitWAc implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();

    public ForfeitWAc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
    }

    public boolean MoveAllOK() {
        // Determine cost of move
        Location Movelocvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        //no need to wrap with decorators or calc MFCost as claiming/forfeiting Wall Adv has no cost
        // Determine if move is legal
        LegalMovei LegalCheck = new LocationLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Can't forfeit wall advantage: no In-Hex TEM (B9.323)")
            Moveloc = null;
            return false;
        } else {
            Constantvalues.AltPos CurrentPosition = Scencolls.SelMoveUnits.getFirst().getbaseunit().gethexPosition();
            if (CurrentPosition == Constantvalues.AltPos.WACrestStatus1 ||
                    CurrentPosition == Constantvalues.AltPos.WACrestStatus2 ||
                    CurrentPosition == Constantvalues.AltPos.WACrestStatus3 ||
                    CurrentPosition == Constantvalues.AltPos.WACrestStatus4 ||
                    CurrentPosition == Constantvalues.AltPos.WACrestStatus5 ||
                    CurrentPosition == Constantvalues.AltPos.WACrestStatus0 ) {
                ConversionC confrom = new ConversionC();
                PositionChangedvalue = confrom.WACresttoJustCrest(CurrentPosition);
            } else {
                PositionChangedvalue = Constantvalues.AltPos.None;  // unit is not in a position
            }
        }

        for (PersUniti MovingUnit : Scencolls.SelMoveUnits) {
            // check if has already moved
            if (MovingUnit.getMovingunit().getMFUsed() > 0) {
                // MessageBox.Show("You have already spent MF and therefore cannot attempt Clearance", "B24.7 Clearance violation")
                Moveloc = null;
                return false;
            }
        }

        // Determine if entry blocked  by enemy units - no need in this class
        Moveloc = null;
        return true;
    }

    public void MoveUpdate() {
        // Triggered by PassToObserver in Game.Update after graphics draw of move completed
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind

        Constantvalues.Nationality MovingNationality;
        String ConLost  = ""; boolean WALoss = false;
        String ConRevealed = ""; // all used in revealing concealed unit(s)
        String ConLostHex = "";
        LinkedList<PersUniti> RemoveConUnit = new LinkedList<PersUniti>(); // holds any revealed dummies
        ArrayList<Integer> RemoveCon = new ArrayList<Integer>(); // holds any revealed Concealment ID
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Hex Oldhex = MovingNatUnit.getbaseunit().getHex();
        LevelChecks LevelChk = new LevelChecks();
        Location WAHex = LevelChk.GetLocationatLevelInHex(hexclickedvalue, 0);
        // update moving stack
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            MovingUnit.getMovingunit().setMFAvailable(MovingUnit.getMovingunit().getMFAvailable() - MFCost);
            //if (MovingUnit.getMovingunit().getMFAvailable() < 0) {MovingUnit.getMovingunit().setMFAvailable(0);}
            MovingUnit.getMovingunit().setMFUsed(MovingUnit.getMovingunit().getMFUsed() + MFCost);
            MovingUnit.getMovingunit().setHexEnteredSideCrossed(0); // within hex move
            MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.Moving);
            MovingUnit.getbaseunit().sethexPosition(PositionChangedvalue);
            /*if (WAHex.getTerrain().getTEM() > 0) {   NEED TO RETHINK THIS
                MovingUnit.getbaseunit().sethexlocation(WAHex);
            } else if (WAHex.TEMOtherTerrain > 0) {
                MovingUnit.getbaseunit().hexlocation = WAHex.OtherTerraininLocation
            }*/

            Locationi Loctouse = new Locationc(WAHex, null );
            // concealment loss Check
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
        }

        // remove revealed Concealment and Dummies
        String Constring = ConLost + ": Concealment Lost - revealed as " + ConRevealed + " in " + ConLostHex;
        scen.DoMove.ConcreteMove.RemoveRevealedConandDummy(RemoveCon, RemoveConUnit, Constring);

        if (Scencolls.SelMoveUnits.isEmpty()) {
            // update data collections
            //MovingUpdate DoUpdate = new MovingUpdate();
            //DoUpdate.UpdateAfterMove(movementoptionclickedvalue, Scencolls.SelMoveUnits);
            // REPLACE ABOVE 2 LIINES WITH CALL TO UPDATEMOVEUNITICOMMAND AS PER TARGETSTATUSUPDATE IN TARGETUNIT CLASSES
        }

        WallAdvantageLossChecki WallAdvLossCheck = new WithinWALossCheckc(hexclickedvalue);
        if (!WallAdvLossCheck.WallAdvantageLossCheck(hexclickedvalue)) {WALoss = false;}

        // update Target values
        SetTargetMoveStatus UpdateTarget = new SetTargetMoveStatus();
        UpdateTarget.RemoveRevealedDummies(RemoveConUnit);
        UpdateTarget.UpdateTargetHexLocPos(hexclickedvalue, scen.DoMove.ConcreteMove.getSelectedPieces() );

        // now check and see if WA can switch to Adjacent enemy
        if (WALoss) { // all units in Oldhex have lost WA and so need to check for switch
            // see if other units gain WA in adjacent hexes
            SwitchWallAdvantagec SwitchWA = new SwitchWallAdvantagec(hexclickedvalue, MovingNationality);
            SwitchWA.Switch();
        }

    }

    public Location EnteringLocation(Hex Samehex, Constantvalues.UMove movementoptionclickedvalue) {
        // DONE
        // called by Me.MoveAllOK
        // determines which specific location is being entered; in this case, same as current as this class handles position change
        // if no units yet selected, returns 0; else returns LOCIndex
        if (Scencolls.SelMoveUnits.isEmpty()) {return null;} //no units selected
        PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
        return Movingunit.getbaseunit().gethexlocation();

    }
}
