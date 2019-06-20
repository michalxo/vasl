package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.BrkUnWACheckc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.ClaimWallAdvLegalc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.MovingUpdate;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.build.module.fullrules.UtilityClasses.MapCommonFunctionsc;

import java.util.ArrayList;
import java.util.LinkedList;

public class  ClaimWallAdvc implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public ClaimWallAdvc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }

    public boolean MoveAllOK() {
        // Determine cost of move
        Location Movelocvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        // no need to wrap with decorators or calc MFCost as Wall Adv has no cost
        // Determine if move is legal
        PersUniti CheckNat = Scencolls.SelMoveUnits.getFirst();
        Constantvalues.Nationality MovingNationality = CheckNat.getbaseunit().getNationality();
        LegalMovei LegalCheck = new ClaimWallAdvLegalc(hexclickedvalue, Movelocvalue, MovingNationality, CheckNat.getbaseunit().gethexPosition());
        boolean MoveLegal = LegalCheck.IsMovementLegal();
        if (!MoveLegal) {   // movement is not legal, exit move
            // PositionChangedvalue = LegalCheck.getLocationchangevalue();
            //MessageBox.Show("Can't claim wall advantage " & Trim(LegalCheck.Returnstring))
            Moveloc = null;
            return false;
        } else {
            PositionChangedvalue = Constantvalues.AltPos.WallAdv;
        }
        return true;
    }
    public void MoveUpdate() {
        // Triggered by PassToObserver in Game.Update after graphics draw of move completed
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind

        Constantvalues.Nationality MovingNationality;
        String ConLost  = "";
        String ConRevealed = ""; // all used in revealing concealed unit(s)
        String ConLostHex = "";
        LinkedList<PersUniti> RemoveConUnit = new LinkedList<PersUniti>(); // holds any revealed dummies
        ArrayList<Integer> RemoveCon = new ArrayList<Integer>(); // holds any revealed Concealment ID
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Location Temploc = null;
        // update moving stack
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            Temploc = MovingUnit.getbaseunit().gethexlocation();  // needed in BrkWA loop
            MapCommonFunctionsc mapcomfunc = new MapCommonFunctionsc();
            if (mapcomfunc.IsCrestStatus(MovingUnit.getbaseunit().gethexPosition())) {
                ConversionC confrom = new ConversionC();
                PositionChangedvalue = confrom.ConvertCresttoWACrest(MovingUnit.getbaseunit().gethexPosition());
            }
            MovingUnit.getbaseunit().sethexPosition(PositionChangedvalue);
            MovingUnit.getMovingunit().setHexEnteredSideCrossed(0); // within hex move
            Locationi Loctouse = new Locationc(MovingUnit.getbaseunit().gethexlocation(), movementoptionclickedvalue);
            // concealment loss Check
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
        }
        // remove revealed Concealment and Dummies
        String Constring = ConLost + ": Concealment Lost - revealed as " + ConRevealed + " in " + ConLostHex;
        scen.DoMove.ConcreteMove.RemoveRevealedConandDummy(RemoveCon, RemoveConUnit, Constring);
        if (!Scencolls.SelMoveUnits.isEmpty()) {
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                MovingUnit.getMovingunit().UpdateMovementStatus(MovingUnit, MovingUnit.getbaseunit().getMovementStatus());
            }
        }
        // broken and unarmed friendlies in must now claim WA if no in-hex TEM > 0; may claim otherwise
        BrkUnWACheckc BrkUnWA = new BrkUnWACheckc(hexclickedvalue, MovingNationality, Temploc);
        BrkUnWA.BrokenUnarmedWACheck();
        // update Target values
        SetTargetMoveStatus UpdateTarget = new SetTargetMoveStatus();
        UpdateTarget.RemoveRevealedDummies(RemoveConUnit);
        UpdateTarget.UpdateTargetHexLocPos(hexclickedvalue, scen.DoMove.ConcreteMove.getSelectedPieces() );




    }
    public Location EnteringLocation(Hex Samehex, Constantvalues.UMove movementoptionclickedvalue){
        // DONE
        // 'called by Me.MoveAllOK
        // 'determines which specific location is being entered; in this case, same as current as this class handles position change
        // 'if no units yet selected, returns 0; else returns LOCIndex
        if (Scencolls.SelMoveUnits.size() == 0) {return null;}  // no units selected
        PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
        return Movingunit.getbaseunit().gethexlocation();
    }
    public String getmoveresults(){
        return moveresults;
    }
}
