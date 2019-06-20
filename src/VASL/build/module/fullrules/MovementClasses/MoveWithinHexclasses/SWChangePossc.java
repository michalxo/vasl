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
import VASL.build.module.fullrules.ObjectChangeClasses.StatusChangei;
import VASL.build.module.fullrules.ObjectChangeClasses.UnitDropsFirstSWc;
import VASL.build.module.fullrules.ObjectChangeClasses.UnitDropsSecondSWc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;

import java.util.ArrayList;
import java.util.LinkedList;

public class SWChangePossc implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private double MFCost;
    private String SWSelected;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public SWChangePossc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked, String PassSelection) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        SWSelected = PassSelection;
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
        //MessageBox.Show("Changing SW possession in . . . " + hexclickedvalue.getName() + " . . . which will cost " + Double.toString(MFCost) + " MF");
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}
        // Determine if move is legal
        LegalMovei LegalCheck = new SWLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            // Perform the action
            if (movementoptionclickedvalue == Constantvalues.UMove.DropSW) {
                for (PersUniti movingunit : Scencolls.SelMoveUnits) {
                    if (movingunit.getbaseunit().getnumSW() > 0) {
                        if (movingunit.getbaseunit().getFirstSWLink() > 0) {
                            StatusChangei Dropit = new UnitDropsFirstSWc();
                            Dropit.Takeaction(movingunit);
                        }
                        if (movingunit.getbaseunit().getSecondSWLink() > 0) {
                            StatusChangei Dropit = new UnitDropsSecondSWc();
                            Dropit.Takeaction(movingunit);
                        }
                    }
                }
            } else if (movementoptionclickedvalue == Constantvalues.UMove.RecoverSW) {
                PersUniti MovingUnit = Scencolls.SelMoveUnits.getFirst();
                /*For Each SWSprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
                if SWSprite.Selected And (TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, SWSprite.TypeID)) Then
                'selected sw
                Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
                GetSW.BaseSW.Unit_ID = SWSprite.ObjID Select GetSW.BaseSW.Owner).First
                if SWOwner = 0 Then
                Dim PossessIt As ObjectChange.ASLXNA.StatusChangei = New
                ObjectChange.ASLXNA.UnitPossessesSWc(SWSprite.ObjID)
                PossessIt.Takeaction(MovingUnit)
                Exit For 'can only possess one at a time
                End if
                End if
                Next*/
            } else if (movementoptionclickedvalue == Constantvalues.UMove.RecoverSWBrk) {
                // NEED TO FIX SEPT 14
                // Dim Movingunit As ObjectClassLibrary.ASLXNA.PersUniti = Scencolls.SelMoveUnits.Item(0)
                // Dim TransferIt As ObjectChange.ASLXNA.UnitChange = New ObjectChange.ASLXNA.TransferSW(Movingunit, )
                // TransferIt.TakeAction()
            }

        }
        LocationChangedvalue = LegalCheck.getLocationchangevalue();

        return true;
    }
    public void MoveUpdate() {
        // Triggered by PassToObserver in Game.Update after graphics draw of move completed
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind
        // no WA updates as clearance attempt does not cause WA claim or forfeit
        Constantvalues.Nationality MovingNationality; Location LocationChangedvalue = null;
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
            MovingUnit.getMovingunit().setMFAvailable(MovingUnit.getMovingunit().getMFAvailable() - MFCost);
            MovingUnit.getMovingunit().setMFUsed(MovingUnit.getMovingunit().getMFAvailable() + MFCost);
            MovingUnit.getMovingunit().setHexEnteredSideCrossed(0); //within hex move
            MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.Moving);
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, null);

/*
            LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, MovingUnit.getbaseunit().getLevelinHex());
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);*/
        }

        if (!Scencolls.SelMoveUnits.isEmpty()) {
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                MovingUnit.getMovingunit().UpdateMovementStatus(MovingUnit, MovingUnit.getbaseunit().getMovementStatus());
            }
        }
    }
    public Location EnteringLocation(Hex Samehex, Constantvalues.UMove movementoptionclickedvalue) {
        // DONE
        // called by Me.MoveAllOK
        // determines which specific location is being entered; in this case, same as current as this class handles position change
        // if no units yet selected, returns 0; else returns LOCIndex
        if (Scencolls.SelMoveUnits.isEmpty()) {return null;} //no units selected
        PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
        return Movingunit.getbaseunit().gethexlocation();  // this may not be right CHECK CODE IN VB movement.vb file

    }
    public String getmoveresults(){
        return moveresults;
    }
}
