package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.WeatherImpactc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.BrkUnWACheckc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.ClaimWallAdvLegalc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectChangeClasses.AutoDM;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.ArrayList;
import java.util.LinkedList;

public class MoveOutofVehiclec implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click

    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private int VehUsingID;
    ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public MoveOutofVehiclec(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        // PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }


    public boolean MoveAllOK() {
        // ORDER IS DIFFERENT THAT FOR ENTER CLASSES - LEGAL MOVE TEST IS AT END RATHER THAN BEGINNING. WHY? IMPACT?

        // Determine cost of move
        Location LocationChangedvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(LocationChangedvalue, movementoptionclickedvalue);
        // wrap with decorators
        //Moveloc = new ScenTerImpactc(Moveloc, movementoptionclickedvalue);
        Moveloc = new WeatherImpactc(Moveloc, movementoptionclickedvalue);
        // this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);

        // change this code when AFV created
       /* 'check vehicle exists
        Dim UnitID As Integer = Scencolls.SelMoveUnits.Item(0).BasePersUnit.Unit_ID
        Dim PassridUsing As DataClassLibrary.PassRider = Game.Linqdata.GetPassRider(UnitID)
        VehUsingID = PassridUsing.VehicleID
        Dim VehUnloading As DataClassLibrary.AFV = Game.Linqdata.GetVehiclefromCol(VehUsingID)
        Dim vehname As String = VehUnloading.AFVName
        If Not IsNothing(vehname) Then
        MessageBox.Show("Trying to unload from " & Trim(vehname) & " which will cost " & MFCost.ToString & " MF")
        Else
        MessageBox.Show("Vehicle not Found. Exiting routine")
        Return False
        End If*/

        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}
        // Determine if move is legal
        VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei LegalCheck = new LocationLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            ConversionC confrom = new ConversionC();
            PositionChangedvalue = confrom.ConvertUMovetoAltPos(movementoptionclickedvalue);
        }
        LocationChangedvalue = LegalCheck.getLocationchangevalue();

        // Determine if entry blocked  by enemy units
        return scen.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, hexclickedvalue.getCenterLocation(), movementoptionclickedvalue);

    }
    public void MoveUpdate() {
        // Triggered by PassToObserver in Game.Update after graphics draw of move completed
        // update data collections - movement array and dataclasslibrary.orderofbattle
        // update display arrays - including what is left behind

        Constantvalues.Nationality MovingNationality; Location LocationChangedvalue = null;
        double MovingUnitPPCost = 0.0; LegalMovei LegalCheck = null; boolean MoveLegal = false;
        boolean ManWAApplies = false;
        String ConLost  = ""; boolean WALoss = false;
        String ConRevealed = ""; // all used in revealing concealed unit(s)
        String ConLostHex = "";
        LinkedList<PersUniti> RemoveConUnit = new LinkedList<PersUniti>(); // holds any revealed dummies
        ArrayList<Integer> RemoveCon = new ArrayList<Integer>(); // holds any revealed Concealment ID
        LevelChecks LevelChk = new LevelChecks();
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Hex newhex = MovingNatUnit.getbaseunit().getHex();
        Location Temploc = null;
        // update moving stack
        for (PersUniti MovingUnit:Scencolls.SelMoveUnits) {
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, PositionChangedvalue);

            LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, MovingUnit.getbaseunit().getLevelinHex());
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
            scen.DoMove.ConcreteMove.CheckConcealmentLoss(MovingUnit, RemoveConUnit, RemoveCon, ConLost, ConLostHex, ConRevealed, Loctouse);
            if (Loctouse.HasWire()) {
                MovingUnit.getbaseunit().sethexPosition(Constantvalues.AltPos.AboveWire);
                PositionChangedvalue = Constantvalues.AltPos.AboveWire;  // needed?
                if (MovingUnit.getbaseunit().gethexlocation() != Loctouse.getvasllocation()) {
                    //MessageBox.Show("I think we have an error here", "EnterNewHex.MoveUpdate")
                    MovingUnit.getbaseunit().sethexlocation(Loctouse.getvasllocation());
                }
            }
            if (Loctouse.getAPMines() > 0) {
                // do minefield attack
                // essageBox.Show("Minefield Attack", "Moving into " & Loctouse.Hexname)
            }
        }
        // change this code when AFV created
       /* // need to do this before deleting revealed dummies
        Dim PPCost As New PortagePointsC()
        MovingUnitPPCost = PPCost.CalcTotalPPUsed
        'update vehicle
        Dim Vehloading As DataClassLibrary.AFV = Game.Linqdata.GetVehiclefromCol(VehUsingID)
        Vehloading.PPUsing = CType(Vehloading.PPUsing - MovingUnitPPCost, Short ?)

        'update PassRiders
        Dim ChangePassRiders = New ObjectChange.ASLXNA.PassRiders
        ChangePassRiders.deletePassRiders(Vehloading, Scencolls.SelMoveUnits)*/

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
        // change this code when AFV created
        /*'update vehicle
        If Not IsNothing(Vehloading) Then Game.linqdata.UpdateVehicleAfterMove(Vehloading)*/

        // now must check for mandatory WA gain in new hex
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            IsSide SideChk = new IsSide();
            if (SideChk.IsWAMandatory(newhex, MovingUnit.getbaseunit().gethexlocation(), MovingUnit.getbaseunit().gethexPosition())) {
                // must claim, check if possible (no adjacent enemy has)
                if (LegalCheck == null) {
                    LegalCheck = new VehicleLegalc(newhex, movementoptionclickedvalue, MovingUnit.getbaseunit().gethexPosition());
                }
                MoveLegal = LegalCheck.IsMovementLegal();

                if (!MoveLegal) {
                    // movement is not legal, exit move
                    //PositionChangedvalue = LegalCheck.gLocationchangevalue();
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
                    LocationChangedvalue = MovingUnit.getbaseunit().gethexlocation();
                    PositionChangedvalue = MovingUnit.getbaseunit().gethexPosition();
                }
            }
        }

        if (ManWAApplies) {
            // broken and unarmed friendlies in new hex must now claim WA if no in-hex TEM > 0; may claim otherwise
            BrkUnWACheckc BrkUnWA = new BrkUnWACheckc(hexclickedvalue, MovingNationality, LocationChangedvalue);
            BrkUnWA.BrokenUnarmedWACheck();
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
    private boolean ChangeLocationTest(PersUniti MovingUnit) {
        return (PositionChangedvalue == Constantvalues.AltPos.Passenger || PositionChangedvalue == Constantvalues.AltPos.Rider);
    }
    public String getmoveresults(){
        return moveresults;
    }
}
