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
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.BrkUnWACheckc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.ClaimWallAdvLegalc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.ArrayList;
import java.util.LinkedList;

public class MoveNewLevelWAc implements MoveWithinHexi {

    // called by Movement.MoveWithinHex, can handle both up and down (determined by movementoptionclickedvalue) to ground level and into WA position
    // triggered by popup option click
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Location LocationChangedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public MoveNewLevelWAc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        //LocationChangedvalue = movementoptionclickedvalue;
        PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }

    public boolean MoveAllOK() {
        // ORDER IS DIFFERENT THAT FOR ENTER CLASSES - LEGAL MOVE TEST IS AT END RATHER THAN BEGINNING. WHY? IMPACT?

        // Determine cost of move
        LocationChangedvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
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
        LegalMovei LegalCheck = new MovementWithinLegalc(hexclickedvalue, movementoptionclickedvalue, LocationChangedvalue);
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

        // Determine if entry blocked  by enemy units
        boolean MoveBlocked = scen.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, hexclickedvalue.getCenterLocation(), movementoptionclickedvalue);
        // if movement can proceed; draw will happen and then return to moveupdate to check consequences
        Moveloc = null;
        return (LegalCheck.getresultsstring() == "WALoss" ? true : false);

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
            // unit loses WA from previous location when in new location - NEEDED HERE??
            //WARemovec WARemoval = new WARemovec(MovingUnit);
            //WALoss = WARemoval.TakeAction();
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, PositionChangedvalue);
            // set new Level
            if (movementoptionclickedvalue == Constantvalues.UMove.StairsUp || movementoptionclickedvalue == Constantvalues.UMove.StairsUpWA) {
                MovingUnit.getbaseunit().setLevelinHex(MovingUnit.getbaseunit().getLevelinHex() + 1);
            } else if (movementoptionclickedvalue == Constantvalues.UMove.StairsDown || movementoptionclickedvalue == Constantvalues.UMove.StairsDownWA) {
                MovingUnit.getbaseunit().setLevelinHex(MovingUnit.getbaseunit().getLevelinHex() - 1);
            }
            LegalMovei LegalCheck = null;
            if (LegalCheck == null) {
                    LegalCheck = new ClaimWallAdvLegalc(hexclickedvalue, LocationChangedvalue, MovingUnit.getbaseunit().getNationality(), MovingUnit.getbaseunit().gethexPosition());
                    if (!LegalCheck.IsMovementLegal()) {
                        // movement is not legal, exit move
                        // MessageBox.Show("Can't claim wall advantage " & Trim(LegalCheck.Returnstring))
                    } else {
                        if (MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus1 ||
                                MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus2 ||
                                MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus3 ||
                                MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus4 ||
                                MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus5 ||
                                MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus0 ) {
                            ConversionC confrom = new ConversionC();
                            MovingUnit.getbaseunit().sethexPosition(confrom.ConvertCresttoWACrest(MovingUnit.getbaseunit().gethexPosition()));
                            PositionChangedvalue = confrom.ConvertCresttoWACrest(PositionChangedvalue);
                        } else {
                            MovingUnit.getbaseunit().sethexPosition(Constantvalues.AltPos.WallAdv);
                        }

                    }
            }

            LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, MovingUnit.getbaseunit().getLevelinHex());
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
            // no wire check required
            if (Loctouse.getAPMines() > 0) {
                // do minefield attack
                // MessageBox.Show("Minefield Attack", "Moving into " & Loctouse.Hexname)
            }
        }

        // remove revealed Concealment and Dummies
        String Constring = ConLost + ": Concealment Lost - revealed as " + ConRevealed + " in " + ConLostHex;
        scen.DoMove.ConcreteMove.RemoveRevealedConandDummy(RemoveCon, RemoveConUnit, Constring);

        if (!Scencolls.SelMoveUnits.isEmpty()) {
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                MovingUnit.getMovingunit().UpdateMovementStatus(MovingUnit, MovingUnit.getbaseunit().getMovementStatus());
            }
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

        if (movementoptionclickedvalue == Constantvalues.UMove.StairsUp || movementoptionclickedvalue == Constantvalues.UMove.StairsUpWA) {
            return Movingunit.getbaseunit().gethexlocation().getUpLocation();
        } else if (movementoptionclickedvalue == Constantvalues.UMove.StairsDown || movementoptionclickedvalue == Constantvalues.UMove.StairsDownWA) {
            return Movingunit.getbaseunit().gethexlocation().getDownLocation();
        }
        return null;
    }
    public String getmoveresults(){
        return moveresults;
    }
}
