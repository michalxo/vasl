package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.*;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.BrkUnWACheckc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.ClaimWallAdvLegalc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.ArrayList;
import java.util.LinkedList;

public class MoveIntoCrestWAc implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click

    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Location LocationChangedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();

    public MoveIntoCrestWAc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }

    public boolean MoveAllOK() {
        // Determine cost of move
        ConversionC confrom = new ConversionC();
        Location Movelocvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        // wrap with decorators
        Moveloc = new ScenTerImpactc(Moveloc, movementoptionclickedvalue);
        Moveloc = new WeatherImpactc(Moveloc, movementoptionclickedvalue);
        if (movementoptionclickedvalue == Constantvalues.UMove.WACrestStatus1 ||
                movementoptionclickedvalue == Constantvalues.UMove.WACrestStatus2 ||
                movementoptionclickedvalue == Constantvalues.UMove.WACrestStatus3 ||
                movementoptionclickedvalue == Constantvalues.UMove.WACrestStatus4 ||
                movementoptionclickedvalue == Constantvalues.UMove.WACrestStatus5 ||
                movementoptionclickedvalue == Constantvalues.UMove.WACrestStatus0) {
            Moveloc = new EnterInCrestStatusc(Moveloc, movementoptionclickedvalue);
        }
        //'this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);
        //MessageBox.Show("Trying to change Crest status within . . . " + hexclickedvalue.getName() + " . . . which will cost " + Double.toString(MFCost) + " MF");
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}

        // Determine if move is legal
        LegalMovei LegalCheck = new CrestMoveLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            PositionChangedvalue = confrom.ConvertUMovetoAltPos(movementoptionclickedvalue);
        }

        // Determine if entry blocked  by enemy units
        boolean MoveBlocked = scen.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, hexclickedvalue.getCenterLocation(), movementoptionclickedvalue);
        // if movement can proceed; draw will happen and then return to moveupdate to check consequences
        Moveloc = null;
        if (MoveBlocked) {
            return false;
        }

        // Now do WA check
        LegalCheck = null;
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            // need to update creststatus before proceeding to WallAdv
            MovingUnit.getbaseunit().sethexPosition(PositionChangedvalue);
            if (LegalCheck == null) {
                LegalCheck = new ClaimWallAdvLegalc(hexclickedvalue, LocationChangedvalue, MovingUnit.getbaseunit().getNationality(), MovingUnit.getbaseunit().gethexPosition());
                if (!LegalCheck.IsMovementLegal()) {
                    // movement is not legal, exit move
                    // MessageBox.Show("Can't claim wall advantage " & Trim(LegalCheck.Returnstring))
                } else {
                    PositionChangedvalue = confrom.ConvertCresttoWACrest(PositionChangedvalue);
                }
            }
        }
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
        LevelChecks LevelChk = new LevelChecks();
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Hex Oldhex = MovingNatUnit.getbaseunit().getHex();
        Location Temploc = null;
        // update moving stack
        for (PersUniti MovingUnit:Scencolls.SelMoveUnits) {
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, PositionChangedvalue);
            LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, 0);
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
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

        // broken and unarmed friendlies in new hex must now claim WA if no in-hex TEM > 0; may claim otherwise
        BrkUnWACheckc BrkUnWA = new BrkUnWACheckc(hexclickedvalue, MovingNationality, LocationChangedvalue);
        BrkUnWA.BrokenUnarmedWACheck();

        // update Target values
        SetTargetMoveStatus UpdateTarget = new SetTargetMoveStatus();
        UpdateTarget.RemoveRevealedDummies(RemoveConUnit);
        UpdateTarget.UpdateTargetHexLocPos(hexclickedvalue, scen.DoMove.ConcreteMove.getSelectedPieces() );

        if (!Scencolls.SelMoveUnits.isEmpty()) {
            AutoDM DMCHeck = new AutoDM(Scencolls.SelMoveUnits);
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
