package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.ScenTerImpactc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.WeatherImpactc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlaceDCc implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private double MFCost;
    private boolean SearchFound;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    ScenarioC scen = ScenarioC.getInstance();

    public PlaceDCc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;

    }


    public boolean MoveAllOK() {
        // Determine cost of move
        Location LocationChangedvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(LocationChangedvalue, movementoptionclickedvalue);
        // wrap with decorators
        Moveloc = new ScenTerImpactc(Moveloc, movementoptionclickedvalue);
        Moveloc = new WeatherImpactc(Moveloc, movementoptionclickedvalue);
        // this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);
        //MessageBox.Show("Trying to move within . . . " + hexclickedvalue.getName() + " . . . which will cost " + Double.toString(MFCost) + " MF");
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}
        // Determine if move is legal
        LegalMovei LegalCheck = new PlaceDCLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            //ConversionC confrom = new ConversionC();
            //PositionChangedvalue = confrom.ConvertUMovetoAltPos(movementoptionclickedvalue);
        }
        LocationChangedvalue = LegalCheck.getLocationchangevalue();

        return true;

    }
    public void MoveUpdate() {
        // Triggered by PassToObserver in Game.Update after graphics draw of move completed
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind
        // no WA updates as clearance attempt does not cause WA claim or forfeit
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
        /*for (PersUniti MovingUnit:Scencolls.SelMoveUnits) {
            // unit loses WA from previous location when in new location - NEEDED HERE??
            WARemovec WARemoval = new WARemovec(MovingUnit);
            WALoss = WARemoval.TakeAction();
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, PositionChangedvalue);
            // set new Level
            if (movementoptionclickedvalue == Constantvalues.UMove.StairsUp || movementoptionclickedvalue == Constantvalues.UMove.StairsUpWA) {
                MovingUnit.getbaseunit().setLevelinHex(MovingUnit.getbaseunit().getLevelinHex() + 1);
            } else if (movementoptionclickedvalue == Constantvalues.UMove.StairsDown || movementoptionclickedvalue == Constantvalues.UMove.StairsDownWA) {
                MovingUnit.getbaseunit().setLevelinHex(MovingUnit.getbaseunit().getLevelinHex() - 1);
            }
            LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, MovingUnit.getbaseunit().getLevelinHex());
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
        }*/

        if (Scencolls.SelMoveUnits.isEmpty()) {
            // update data collections
            //MovingUpdate DoUpdate = new MovingUpdate();
            //DoUpdate.UpdateAfterMoveCLDC(movementoptionclickedvalue, Scencolls.SelMoveUnits);
            // REPLACE ABOVE 2 LIINES WITH CALL TO UPDATEMOVEUNITICOMMAND AS PER TARGETSTATUSUPDATE IN TARGETUNIT CLASSES
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
