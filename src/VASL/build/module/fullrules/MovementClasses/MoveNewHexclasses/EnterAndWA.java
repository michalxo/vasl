package VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectChangeClasses.VisibilityChangei;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;

import java.util.ArrayList;
import java.util.LinkedList;

public class EnterAndWA  implements MoveNewHexi {
    // when entering other locations within hex, use MOVEC.MoveWithinHex to send to appropriate class
    private Hex newhexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Location LocationChange;
    private Constantvalues.AltPos PositionChange;
    private double MFCost;
    private Hex Currenthex;
    private boolean CurrentPosIsExitedCrest = false;
    private Locationi Moveloc;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public EnterAndWA(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {
        // in this class hexclicked is starting hex as comes from popup click
        movementoptionclickedvalue = Movementoptionclicked;
        newhexclickedvalue = hexclicked;
    }

    public boolean MoveAllOK() {
        //if MovementOptionClicked=0 then no movements options available - just entering hex
        // MovementOptionClicked value comes from constantclasslibrary.aslxna.umove and is set in
        // newhexclickedvalue is number of new hex
        // determines if move doable and returns for drawing

        // Determine if move legal
        LegalMovei MoveNewCheck;
        Currenthex = StartingHex();
        // get new location trying to enter
        Location Movelocvalue = EnteringLocation(newhexclickedvalue, Currenthex);
        Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        // check move legal - there are likely to be a number of possible checks, class out each one to respect SRP, access via LegalMovei interface
        PersUniti LegalCheckunit = (Scencolls.SelMoveUnits.getFirst());
        if (LegalCheckunit.getbaseunit().getLevelinHex() >= 1) {  // ConstantClassLibrary.ASLXNA.Location.Roof to ConstantClassLibrary.ASLXNA.Location.Building3rdLevel Then
            // special case - no need to test
            // MessageBox.Show("Not a legal move at upper level")
            return false;
        } else if (LegalCheckunit.getbaseunit().gethexlocation().getTerrain().getName().equals("Cellar")) {  // = ConstantClassLibrary.ASLXNA.Location.Cellar or ConstantClassLibrary.ASLXNA.Location.BunkUnder Then
            // special case - no need to test
            // MessageBox.Show("Not a legal move at upper level")
            return false;
            // may need to add more elseif to handle other location situations and movementoptions that impact what location clicked in new hex
        } else {
            MoveNewCheck = new MovementNewLegalc(newhexclickedvalue, movementoptionclickedvalue);
            if (!MoveNewCheck.IsMovementLegal()) {
                // movement not legal, end move
                // MessageBox.Show(MoveNewCheck.Returnstring)
                return false;
            } else {
                // if currently at ground level then have clicked ground level of new hex - even if moving uphill or INTO depression
                LocationChange = Moveloc.getvasllocation();
                if (LegalCheckunit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest0) { // LegalCheckunit.BasePersUnit.hexlocation <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest5)
                    CurrentPosIsExitedCrest = true; // if ismovementlegal is true then must be exited crest
                }
            }
        }
        // handle special cases: where bridge is inherent and moving beneath bridge or bridge is ScenFeature and entering bridge via UsingOTImpactc
        // wrap with decorators
        Moveloc = scen.DoMove.ConcreteMove.Decorating(Moveloc, movementoptionclickedvalue, LocationChange, PositionChange, CurrentPosIsExitedCrest, Currenthex);
        // this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(Currenthex) + Moveloc.gethexsideentrycost();  // is this right? should hexside be part of decorating?
        //if here then move is legal
        String stacknames = getMovingStackUnitNames();
        // Determine if move is affordable
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, newhexclickedvalue, MFCost, Moveloc, Currenthex);
        moveresults = "";
        if (!MoveAffordable) {
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                moveresults += MovingUnit.getbaseunit().getUnitName() + "(" + MovingUnit.getMovingunit().getMFUsed() + "/" + MovingUnit.getMovingunit().getMFAvailable() + ")" + " ";
            }
            moveresults += "trying to move to " + newhexclickedvalue.getName() + " which would cost " + MFCost + " MF: Insufficient MF Available";
            return false;
        }
        // Determine if entry blocked  by enemy units
        boolean MoveBlocked = scen.DoMove.ConcreteMove.ProcessValidation(newhexclickedvalue, LocationChange, movementoptionclickedvalue);
        // if movement can proceed; draw will happen and then return to moveupdate to check consequences
        if (MoveBlocked) {
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                moveresults += MovingUnit.getbaseunit().getUnitName() + "(" + MovingUnit.getMovingunit().getMFUsed() + "/" + MovingUnit.getMovingunit().getMFAvailable() + ")" + " ";
            }
            moveresults += "trying to move to " + newhexclickedvalue.getName() + ": Blocked by Enemy Unit(s)";
            Moveloc = null;
            return false;
        }
        // now do Wall Advantage check
        Constantvalues.Nationality MovingNationality = Scencolls.SelMoveUnits.getFirst().getbaseunit().getNationality();
        LegalMovei LegalCheck = new ClaimWallAdvLegalc(newhexclickedvalue, LocationChange, MovingNationality, Scencolls.SelMoveUnits.getFirst().getbaseunit().gethexPosition());
        if (!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            LocationChange = LegalCheck.getLocationchangevalue();
            // MessageBox.Show("Can't claim wall advantage " & Trim(LegalCheck.Returnstring))
            Moveloc = null;
            return false;
        }
        // all conditions met
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            moveresults += MovingUnit.getbaseunit().getUnitName() + "(" + MovingUnit.getMovingunit().getMFUsed() + "/" + MovingUnit.getMovingunit().getMFAvailable() + ")" + " ";
        }
        moveresults += "moving to " + newhexclickedvalue.getName() + " and claim WA which will cost " + Double.toString(MFCost) + " MF";
        Moveloc = null;
        MoveUpdate();
        return true;
    }

    public void MoveUpdate() {
        // checks for consequences
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind

        int hexnum; boolean WALoss = false; boolean ManWAApplies = false;
        LegalMovei legalcheck = null; boolean MoveLegal;
        int AlreadyAdded = 0; LinkedList<Integer> DelConAdded; String ConLost = ""; String ConRevealed = "";  // all used in revealing concealed unit(s)
        String ConLostHex = "";
        LinkedList<PersUniti> RevealedUnits = new LinkedList<PersUniti>();
        LinkedList<PersUniti> RemoveConUnit = new LinkedList<PersUniti>(); // holds any revealed dummies
        ArrayList<Integer> RemoveCon = new ArrayList<Integer>();  // holds any revealed Concealment ID
        SuppWeapi TempSW;
        Constantvalues.Nationality MovingNationality = Scencolls.SelMoveUnits.getFirst().getbaseunit().getNationality();
        Hex Oldhex = Scencolls.SelMoveUnits.getFirst().getbaseunit().getHex();  // used at end to clear unneeded location counters
        int hexenteredsidecrossed  = scen.DoMove.ConcreteMove.getHexSideEntry(Oldhex, newhexclickedvalue);
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            // unit loses WA from previous hex when in new hex
            WARemovec WARemoval = new WARemovec(MovingUnit);
            WALoss = WARemoval.TakeAction();
            // new
            // update moving unit
            //MFCost = MovingUnit.getMovingunit().getMFAvailable();
            PositionChange = Constantvalues.AltPos.WallAdv;
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterEnter(MovingUnit, MFCost, newhexclickedvalue, hexenteredsidecrossed, LocationChange, PositionChange);
            // after this point, MovingUnit is in the new hex - CHECK THIS IS CORRECT AS IT EFFECTS OLDHEX NEWHEX VALUES Oct 18
            Hex newhex = MovingUnit.getbaseunit().getHex();
            // update level if exiting crest
            if (CurrentPosIsExitedCrest) {
                SetLeveltoZero(MovingUnit);
            }
            // check consequences (mines, wire, illuminated, concealment loss due to LOS) - PARTIAL IMPLEMENTATION ONLY AT MAY 2012
            // code block below not necessary as are gaining WA with this class
            /*IsSide SideChk = new IsSide(LocationChange);
            if (SideChk.IsWAMandatory(newhex, MovingUnit.getbaseunit().gethexlocation(), MovingUnit.getbaseunit().gethexPosition())) {
                // must claim, check if possible (no adjacent enemy has)
                if (legalcheck == null) {
                    legalcheck = new ClaimWallAdvLegalc(newhexclickedvalue, LocationChange, MovingNationality, MovingUnit.getbaseunit().gethexPosition());
                }
                MoveLegal = legalcheck.IsMovementLegal();

                if (!MoveLegal) {
                    // movement is not legal, exit move
                    LocationChange = legalcheck.LocationChange;
                    // MessageBox.Show("Can't claim wall advantage " & Trim(LegalCheck.Returnstring))
                    //' due to unit in " & Game.Scenario.MapTables.GetnamefromdatatableMap(hexvalue))
                } else {
                    if (MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.CrestStatus0) { // And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5 Then
                        ConversionC CrestConvert = new ConversionC();
                        MovingUnit.getbaseunit().sethexPosition(CrestConvert.ConvertCresttoWACrest(MovingUnit.getbaseunit().gethexPosition()));
                    } else {
                        MovingUnit.getbaseunit().sethexPosition(Constantvalues.AltPos.WallAdv);
                    }
                    ManWAApplies = true;
                    LocationChange = MovingUnit.getbaseunit().gethexlocation();
                    PositionChange = MovingUnit.getbaseunit().gethexPosition();
                }
            }*/
            Locationi Loctouse = new Locationc(LocationChange, movementoptionclickedvalue );
            //MovingUnit.getbaseunit().sethexlocation(Loctouse.getvasllocation()); // set location entered    DONT THINK THIS IS NEEDED
            // concealment loss check
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
            VisibilityChangei UnittoChange;
            if (Loctouse.HasWire()) {
                MovingUnit.getbaseunit().sethexPosition(Constantvalues.AltPos.AboveWire);
                PositionChange = Constantvalues.AltPos.AboveWire; // needed?
                if (MovingUnit.getbaseunit().gethexlocation() != Loctouse.getvasllocation()) {
                    //MessageBox.Show("I think we have an error here", "EnterNewHex.MoveUpdate")
                    MovingUnit.getbaseunit().sethexlocation(Loctouse.getvasllocation());
                }
            }
            if (Loctouse.getAPMines() > 0) {
                // do minefield attack - TO BE CODED
                //MessageBox.Show("Minefield Attack", "Moving into " & Loctouse.Hexname)
            }
        }
        // remove revealed Concealment and Dummies
        String Constring = ConLost + ": Concealment Lost - revealed as " + ConRevealed + " in " + ConLostHex;
        scen.DoMove.ConcreteMove.RemoveRevealedConandDummy(RemoveCon, RemoveConUnit, Constring);
        // update database
        if (!Scencolls.SelMoveUnits.isEmpty()) {
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                MovingUnit.getMovingunit().UpdateMovementStatus(MovingUnit, MovingUnit.getbaseunit().getMovementStatus());
            }
        }

        // reset this so it does not hold EnterWA value
        for (PersUniti movingunit:  Scencolls.SelMoveUnits) {
            movingunit.getbaseunit().sethexlocation(LocationChange);
        }
        // broken and unarmed friendlies in new hex must now claim WA if no in-hex TEM > 0; may claim otherwise
        BrkUnWACheckc BrkUnWA = new BrkUnWACheckc(newhexclickedvalue, MovingNationality, LocationChange);
        BrkUnWA.BrokenUnarmedWACheck();

        // REPLACE CODE BELOW WITH CALL TO COUNTERACTIONS
        //redo sprite display - before update Target values
        //Game.Scenario.DoMove.ConcreteMove.RedoSpriteDisplay(hexnum, Oldhex)

        // update Target values
        SetTargetMoveStatus UpdateTarget = new SetTargetMoveStatus();
        UpdateTarget.RemoveRevealedDummies(RemoveConUnit);
        UpdateTarget.UpdateTargetHexLocPos(newhexclickedvalue, scen.DoMove.ConcreteMove.getSelectedPieces() );

        // REPLACE THIS CODE WITH VASL DRAW CODE
        //Dim CreateMFtoDraw = New DrawMoveInfo(newhexclickedvalue, Oldhex, MFCost, hexsidecrossed)
        //    Game.XNAGph.DrawHover(hexnum)

        // this needs to be done after database updated due to use of ContentsofLocation class
        scen.DoMove.ConcreteMove.WACleanUp(Oldhex, WALoss, MovingNationality, true);

        // Now check if units in adjacent hexes are made DM by moving unit (which must be Known and armed)
        if (!Scencolls.SelMoveUnits.isEmpty()) {
            AutoDM DMCHeck = new AutoDM(Scencolls.SelMoveUnits);
            for (Hex DMDraw : DMCHeck.getHexesToDM()) {
                // WONT NEED THESE DRAW CALLS BUT WILL NEED TO TRIGGER COUNTERACTIONS
                //OH = CType(Game.Scenario.HexesWithCounter(DMDraw), VisibleOccupiedhexes)
                //OH.GetAllSpritesInHex()
                //OH.RedoDisplayOrder()
            }
        }
        // Game.Linqdata.QuickUpdate();  MAKE SURE THIS IS HANDLED BY THE COMMAND UPDATE
    }

    public Location EnteringLocation(Hex newhex, Hex currenthex){
        // called by Me.MoveAllOK
        // determines which specific location is being entered
        // if no units yet selected, returns null; else returns Location being entered
        if (Scencolls.SelMoveUnits.isEmpty()) {return null;} // no units selected
        Location newenterlocation = null;
        PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
        // get current level
        double currentlevel = Movingunit.getbaseunit().getLevelinHex();
        LevelChecks LevelChk = new LevelChecks();
        // first test: if new hex has only one location that is what is being entered
        // CODE SHOULD BE IN HEX CLASS - MOVE ONCE WORKING
        Location newhexuplocation  = newhex.getCenterLocation().getUpLocation();
        Location newhexdownlocation  = newhex.getCenterLocation().getDownLocation();
        if (newhexuplocation == null && newhexdownlocation == null && !newhex.hasBridge()) {
            return newenterlocation = newhex.getCenterLocation();   // new location is base level location of hex
        }
        // end code to move
        // now test where newhex has multiple locs
        // second test, hexes at same base level
        if (newhex.getBaseHeight() == currenthex.getBaseHeight()) { // hexes are at the same base level therefore entry must be at same level - can't move diagonally
            // get location in new hex at same level
            newenterlocation = LevelChk.GetLocationatLevelInHex(newhex, currentlevel);
            if (newenterlocation != null) {
                return newenterlocation;
            }
        } else if (newhex.getBaseHeight() > currenthex.getBaseHeight()) { // moving to hex with higher base level, either uphill or to lower levelinhex
            // third test, moving to hex with higher base level
            if (currentlevel > 0) { //move horizontally to lower level in new hex - can't move diagonally
                newenterlocation = LevelChk.GetLocationatLevelInHex(newhex, currentlevel - 1);
            } else { // moving uphill at base level of hex
                newenterlocation = newhex.getCenterLocation();
            }
            return newenterlocation;
        } else if (newhex.getBaseHeight() < currenthex.getBaseHeight()) { // moving to hex with lower base level, either downhill or to higher levelinhex
            // fourth test, moving to hex with lower base level
            if (currentlevel > 0) { // move horizontally to higher level in hex
                newenterlocation = LevelChk.GetLocationatLevelInHex(newhex, currentlevel + 1);
                if (newenterlocation == null) {
                    return null;
                } // higher level does not exist in new hex so cannot enter
            } else { //moving downhill at base level of hex or moving horizontally to higher level in hex
                // if moving within building (across building hexside), must move horizontally; otherwise diagonal move downhill

                // HOW TO TELL IF MOVING HORIZONTALLY OR TO BASE LEVEL OF LOWER HEX, BOTH POSSIBLE? NEED TO CODE THIS
                /*Dim hexsidecrossed As Integer = MapGeo.HexSideEntry(currenthex, newhex)
                Dim hexside = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
                If hexside.IsACrossableBuilding(hexside.Gethexsidetype(Currentbase, hexsidecrossed)) Then
                                    'is moving within building
                Try
                        Newloc = LevelChk.GetLocationatLevelInHex(Newbase.Hexnum, Movelevel + 1)
                Catch
                Return 0 'higher level does not exist in new hex so cannot enter
                End Try
                Else
                        Newloc = Newbase
                End If*/
            }
            return newenterlocation;
        }
        return null; // error if reach here

    }

    private Hex StartingHex() {
            // called by MoveAllOK to determine where moving units are starting from
            // if no units yet selected, returns null; else returns Hex
            if (Scencolls.SelMoveUnits.isEmpty()) {
                return null;
            } // no units selected
            PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
            return Movingunit.getbaseunit().getHex();
    }
    private boolean SetLeveltoZero(PersUniti MovingUnit) {
        MovingUnit.getbaseunit().setLevelinHex(0);
        return true;
    }
    public String getmoveresults(){
        return moveresults;
    }
    private String getMovingStackUnitNames() {
        String stackstring ="";
        for (PersUniti movingunit: Scencolls.SelMoveUnits){
            stackstring += movingunit.getbaseunit().getUnitName() + " ";
        }
        return stackstring;
    }
}
