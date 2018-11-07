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
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;

import java.util.ArrayList;
import java.util.LinkedList;

public class ThrowSmokec implements MoveWithinHexi {

    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();

    public ThrowSmokec(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }

    public boolean MoveAllOK() {
        // Determine cost of move
        Location LocationChangedvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi Moveloc = new Locationc(LocationChangedvalue, movementoptionclickedvalue);
        // wrap with decorators
        Moveloc = new ScenTerImpactc(Moveloc, movementoptionclickedvalue);
        Moveloc = new WeatherImpactc(Moveloc, movementoptionclickedvalue);
        // this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);
        //MessageBox.Show("Trying to Throw Smoke from . . . " + hexclickedvalue.getName() + " . . . which will cost " + Double.toString(MFCost) + " MF");
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {
            return false;
        }
        // Determine if move is legal
        LegalMovei LegalCheck = new SmokeLegalc(hexclickedvalue, movementoptionclickedvalue);
        if (!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            /*//PositionChangedValue = LegalCheck.LocationChange
            If PositionChangedValue = constantclasslibrary.aslxna.UMove.ThrowSmokeUp
            Or(PositionChangedValue >= constantclasslibrary.aslxna.UMove.ThrowSmoke1 And
                    PositionChangedValue <= constantclasslibrary.aslxna.UMove.ThrowSmoke6) Then
            If PositionChangedValue = constantclasslibrary.aslxna.UMove.ThrowSmokeUp Then
            Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
            Dim DestHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexclickedvalue, 0)
            Dim DestinationLevel As Integer = CInt(DestHex.Baselevel)
            Dim MoveUTest As ObjectClassLibrary.
            ASLXNA.PersUniti = CType(Scencolls.selmoveunits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
            Dim Curhex As MapDataClassLibrary.
            GameLocation = LevelChk.GetLocationatLevelInHex(MoveUTest.basepersunit.hexnum, 0)
            Dim CurrentLevel As Integer = CInt(Curhex.baselevel)
            If DestinationLevel >CurrentLevel Then
            'need dieroll to determine final placement
            Dim Smokelocdie As New UtilClassLibrary.ASLXNA.DiceC
            Dim Finalloc As Integer = Smokelocdie.Dieroll
            If Finalloc >3 Then 'smoke placed in thrower' s location
            PositionChangedValue = constantclasslibrary.aslxna.UMove.ThrowSmokeSame
            End If
            End If
            End If
            End If*/
        }
        LocationChangedvalue = LegalCheck.getLocationchangevalue();
        return true;
    }
    public void MoveUpdate() {
        // Triggered by PassToObserver in Game.Update after graphics draw of move completed
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind
        // no WA updates as clearance attempt does not cause WA claim or forfeit
        ; Location LocationChangedvalue = null;
        String ConLost  = ""; boolean WALoss = false;
        String ConRevealed = ""; // all used in revealing concealed unit(s)
        String ConLostHex = "";
        LinkedList<PersUniti> RemoveConUnit = new LinkedList<PersUniti>(); // holds any revealed dummies
        ArrayList<Integer> RemoveCon = new ArrayList<Integer>(); // holds any revealed Concealment ID
        LevelChecks LevelChk = new LevelChecks();
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        //MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Hex Oldhex = MovingNatUnit.getbaseunit().getHex();
        Location Temploc = null;
        // update moving stack
        for (PersUniti MovingUnit:Scencolls.SelMoveUnits) {
            if (PositionChangedvalue == Constantvalues.AltPos.FailsandEnds) {
                MFCost = MovingUnit.getMovingunit().getMFAvailable();
            }else {
                MovingUnit.getMovingunit().setMFAvailable(MovingUnit.getMovingunit().getMFAvailable() - MFCost);
                MovingUnit.getMovingunit().setMFUsed(MovingUnit.getMovingunit().getMFAvailable() + MFCost);
            }
            MovingUnit.getMovingunit().setHexEnteredSideCrossed(0); // within hex move  -IS THIS RIGHT FOR SMOKE PLACEMENT? July 13
            MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.Moving);
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, null);

            /*LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, MovingUnit.getbaseunit().getLevelinHex());
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);*/
        }

        if (Scencolls.SelMoveUnits.isEmpty()) {
            // update data collections
            //MovingUpdate DoUpdate = new MovingUpdate();
            //DoUpdate.UpdateAfterMove(movementoptionclickedvalue, Scencolls.SelMoveUnits);
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
