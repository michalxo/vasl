package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.*;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.BrkUnWACheckc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.build.module.fullrules.UtilityClasses.MapCommonFunctionsc;

import java.util.ArrayList;
import java.util.LinkedList;

public class MoveWithinLocationc implements MoveWithinHexi {
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    Location locationchangevalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi Moveloc;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public MoveWithinLocationc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {
        // called by Movement.MoveWithinHex, handles location changes (in and out) such as pillbox but not up and down (actual change determined by movementoptionclickedvalue)
        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        //locationchangevalue = movementoptionclickedvalue;
        PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }

    public boolean MoveAllOK() {
        // Determine cost of move
        Location Movelocvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        // wrap with decorators
        Moveloc = new WeatherImpactc(Moveloc, movementoptionclickedvalue);
        // this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);
        //MessageBox.Show("Trying to move within . . . " & GetLocs.GetnamefromdatatableMap(hexclickedvalue) & " . . . which will cost " & MFCost.ToString & " MF")
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}
        // Determine if move is legal
        LegalMovei LegalCheck = new LocationLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        }
        locationchangevalue = LegalCheck.getLocationchangevalue();
        ConversionC confrom = new ConversionC();
        PositionChangedvalue = confrom.ConverttoAltPosType(Integer.getInteger(LegalCheck.getresultsstring()));
        // Determine if entry blocked  by enemy units
        boolean MoveBlocked = scen.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, locationchangevalue, movementoptionclickedvalue);
        // if movement can proceed; draw will happen and then return to moveupdate to check consequences
        Moveloc = null;
        return (MoveBlocked ? false: true);
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
        Location Temploc = null;
        // update moving stack
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            // unit loses WA from previous location when in new location
            WARemovec waremove = new WARemovec(MovingUnit);
            WALoss = waremove.TakeAction();
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, locationchangevalue, PositionChangedvalue);
            //MovingUnit.getbaseunit().sethexPosition(PositionChangedvalue);
            //MovingUnit.getMovingunit().setHexEnteredSideCrossed(0); // within hex move
            Locationi Loctouse = new Locationc(MovingUnit.getbaseunit().gethexlocation(), movementoptionclickedvalue);
            // concealment loss Check
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
            if (Loctouse.HasWire()) {
                MovingUnit.getbaseunit().sethexPosition(Constantvalues.AltPos.AboveWire);
                PositionChangedvalue = Constantvalues.AltPos.AboveWire; // needed?
                if (MovingUnit.getbaseunit().gethexlocation() != Loctouse.getvasllocation()) {
                    //MessageBox.Show("I think we have an error here", "EnterNewHex.MoveUpdate")
                    MovingUnit.getbaseunit().sethexlocation(Loctouse.getvasllocation());
                }
            }
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

        // update Target values
        SetTargetMoveStatus UpdateTarget = new SetTargetMoveStatus();
        UpdateTarget.RemoveRevealedDummies(RemoveConUnit);
        UpdateTarget.UpdateTargetHexLocPos(hexclickedvalue, scen.DoMove.ConcreteMove.getSelectedPieces() );

        if (WALoss) { // at least one unit had WA
            // check for WA changes-loss by broken/unarmed in start location and transfer to adjacent enemy
            WallAdvantageLossChecki WallAdvLossCheck = new WithinWALossCheckc(hexclickedvalue);
            if (!WallAdvLossCheck.WallAdvantageLossCheck(Oldhex)) {
                WALoss = false;
            }
        }

        if (WALoss) { // all units in Oldhex have lost WA and so need to check for switch
            // see if other units gain WA in adjacent hexes
            SwitchWallAdvantagec SwitchWA = new SwitchWallAdvantagec(hexclickedvalue, MovingNationality);
            SwitchWA.Switch();
        }
        // Now check if units in adjacent hexes are made DM by moving unit (which must be Known and armed)
        if (!Scencolls.SelMoveUnits.isEmpty()) {
            AutoDM DMCHeck = new AutoDM(Scencolls.SelMoveUnits);
            // need counter routine
        }

    }
    public Location EnteringLocation(Hex Samehex, Constantvalues.UMove movementoptionclickedvalue){
        // DONE
        // 'called by Me.MoveAllOK
        // 'determines which specific location is being entered; in this case, same as current as this class handles position change
        // 'if no units yet selected, returns 0; else returns LOCIndex
        if (Scencolls.SelMoveUnits.size() == 0) {return null;}  // no units selected
        PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
        if (movementoptionclickedvalue == Constantvalues.UMove.EnterPillbox) {
            GetALocationFromMap Getlocs = new GetALocationFromMap();
            return Getlocs.GetPillboxLocation(Samehex);
        } else if (movementoptionclickedvalue == Constantvalues.UMove.ExitPIllbox) {  // IS THIS NEEDED?
            return Movingunit.getbaseunit().gethexlocation();
        } else {
            return Movingunit.getbaseunit().gethexlocation();
        }
    }
    public String getmoveresults(){
        return moveresults;
    }
}
