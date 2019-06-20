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
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.ArrayList;
import java.util.LinkedList;

public class MoveIntoFeaturec implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click
    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Location LocationChangedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi Moveloc;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private String moveresults;

    public MoveIntoFeaturec(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        //LocationChangedvalue = movementoptionclickedvalue;
        PositionChangedvalue = Constantvalues.AltPos.None; // default value
    }
    public boolean MoveAllOK() {
        // Determine cost of move
        Location Movelocvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        // wrap with decorators
        Moveloc = new ScenTerImpactc(Moveloc, movementoptionclickedvalue);  // handles many "feature" options
        Moveloc = new WeatherImpactc(Moveloc, movementoptionclickedvalue);
        // this will cascade down and back up the wrappers
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);
        //MessageBox.Show("Trying to move within . . . " + Moveloc.gethexname() + " . . . which will cost " + Double.toString(MFCost) + " MF";
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}

        // Determine if move is legal
        LegalMovei LegalCheck = new FeatureLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            ConversionC confrom = new ConversionC();
            PositionChangedvalue = confrom.ConvertUMovetoAltPos(movementoptionclickedvalue);
            LocationChangedvalue = LegalCheck.getLocationchangevalue();  // is this needed? should not be location change
        }

        for (PersUniti MovingUnit : Scencolls.SelMoveUnits) {
            // check if has already moved
            if (MovingUnit.getMovingunit().getMFUsed() > 0) {
                // MessageBox.Show("You have already spent MF and therefore cannot attempt Clearance", "B24.7 Clearance violation")
                Moveloc = null;
                return false;
            }
        }

        // Determine if entry blocked  by enemy units
        return scen.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, hexclickedvalue.getCenterLocation(), movementoptionclickedvalue);

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
        LegalMovei legalcheck = null;
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Hex Currenthex = MovingNatUnit.getbaseunit().getHex();
        Location Temploc = null; boolean ManWAApplies = false;
        // update moving stack
        for (PersUniti MovingUnit:Scencolls.SelMoveUnits) {
            // unit loses WA from previous location when in new location - NEEDED HERE??
            WARemovec WARemoval = new WARemovec(MovingUnit);
            WALoss = WARemoval.TakeAction();
            // update moving unit
            MovingUnit = scen.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, LocationChangedvalue, PositionChangedvalue);
            // now must check for mandatory WA gain in hex  - should this be here or in MOveAllOK
            IsSide SideChk = new IsSide();
            if (SideChk.IsWAMandatory(Currenthex, MovingUnit.getbaseunit().gethexlocation(), MovingUnit.getbaseunit().gethexPosition())) {
                // must claim, check if possible (no adjacent enemy has)
                if (legalcheck == null) {
                    legalcheck = new ClaimWallAdvLegalc(Currenthex, MovingUnit.getbaseunit().gethexlocation(), MovingNationality, MovingUnit.getbaseunit().gethexPosition());
                }
                boolean MoveLegal = legalcheck.IsMovementLegal();

                if (!MoveLegal) {
                    // movement is not legal, exit move
                    Location LocationChange = legalcheck.getLocationchangevalue();
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
                    Location LocationChange = MovingUnit.getbaseunit().gethexlocation();
                    Constantvalues.AltPos PositionChange = MovingUnit.getbaseunit().gethexPosition();
                }

            }
            LocationChangedvalue = LevelChk.GetLocationatLevelInHex(hexclickedvalue, 0);
            // concealment loss Check
            Locationi Loctouse = new Locationc(LocationChangedvalue, null);
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
        if (ManWAApplies) {
            // broken and unarmed friendlies in new hex must now claim WA if no in-hex TEM > 0; may claim otherwise
            BrkUnWACheckc BrkUnWA = new BrkUnWACheckc(Currenthex, MovingNationality, Currenthex.getCenterLocation());
            BrkUnWA.BrokenUnarmedWACheck();
        }

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
        if (Scencolls.SelMoveUnits.isEmpty()) {
            return null;
        } //no units selected
        PersUniti Movingunit = Scencolls.SelMoveUnits.getFirst();
        return Movingunit.getbaseunit().gethexlocation();
    }
    /*private Function Wiretest(ByRef movingunit As ObjectClassLibrary.ASLXNA.PersUniti) As Boolean
        'return true allows recalculate and retry movement
        'Dim UnittoCheck As DataClassLibrary.OrderofBattle
        'only applies to moving beneath wire so if not then exit
        If Not movementoptionclickedvalue = constantclasslibrary.aslxna.UMove.EnterWire Then Return False
        'wire attempt uses all MFAvailable
        movingunit.MovingPersUnit.MFUsed += movingunit.MovingPersUnit.MFAvailable
        movingunit.MovingPersUnit.MFAvailable = 0
        'dummy has no SW to drop
        If movingunit.MovingPersUnit.IsDummy Then Return False
        'If movingunit.MovingPersUnit.IsDummy Then
        'Else
        '    UnittoCheck = Game.Linqdata.GetUnitfromCol(movingunit.BasePersUnit.Unit_ID)
        'End If
        If movingunit.BasePersUnit.SW = 0 Then Return False 'no PP to drop
        Dim TestModel As New ObjectClassLibrary.ASLXNA.MovementModifiersc :Dim Entrychoice As DialogResult :
        Dim dropchoice As Boolean = False
        Dim SWCarrying As Integer = TestModel.PPCarrying(movingunit)
        If SWCarrying >movingunit.MovingPersUnit.IPC Then
        Dim SWtoCheck As ObjectClassLibrary.ASLXNA.SuppWeapi
        Dim FindSw As Integer = 0
        For i = 1 To 2
        If i = 1 And movingunit.BasePersUnit.FirstSWLink<> 0 Then FindSw = movingunit.BasePersUnit.FirstSWLink
        If i = 2 And movingunit.BasePersUnit.SecondSWlink<> 0 Then FindSw = movingunit.BasePersUnit.SecondSWlink
        If FindSw = 0 Then Return False 'no SW found
        SWtoCheck = (From getsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
        getsw.BaseSW.Unit_ID = FindSw Select getsw).First
        If IsNothing (SWtoCheck) Then Return False
        'If i = 1 And movingunit.BasePersUnit.FirstSWLink <> 0 Then
        '    SWtoCheck = Game.Linqdata.GetOBSWRecord(CInt(movingunit.BasePersUnit.FirstSWLink))
        'ElseIf i = 2 And movingunit.BasePersUnit.SecondSWlink <> 0 Then
        '    SWtoCheck = Game.Linqdata.GetOBSWRecord(CInt(movingunit.BasePersUnit.SecondSWlink))
        'Else
        '    Continue For
        'End If
        Dim OBSWname As String = SWtoCheck.BaseSW.UnitName
        Entrychoice = MessageBox.Show("Do you wish to drop " & Trim(OBSWname) & "?", "Trying to move beneath wire", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
        If Entrychoice = DialogResult.Yes Then
        Dim Dropit As ObjectChange.ASLXNA.StatusChangei
        If i = 1 Then
                Dropit = New ObjectChange.ASLXNA.UnitDropsFirstSWc

        ElseIf i = 2 Then
                Dropit = New ObjectChange.ASLXNA.UnitDropsSecondSWc
        End If
        Dropit.Takeaction(movingunit)
        'Dim UnpossSWcreate = New ObjectChange.ASLXNA.CreateUnpossessedSW
        'UnpossSWcreate.CreateNewUnpossessed(SWtoCheck, CInt(movingunit.BasePersUnit.Hexnum))
        'SWtoCheck.BaseSW.Owner = 0
        '' Game.Linqdata.CreateNewUnpossessed(SWtoCheck, MovingUnit.basepersunit.hexnum)
        '' UnittoCheck would not work with db.submitchanges - no idea why
        '' UnittoCheck.DropSW(SWtoCheck.OBSW_ID)
        'movingunit.BasePersUnit.SW -= CShort(1)
        'If movingunit.BasePersUnit.FirstSWLink = SWtoCheck.BaseSW.Unit_ID Then movingunit.BasePersUnit.FirstSWLink = 0
        'If movingunit.BasePersUnit.SecondSWlink = SWtoCheck.BaseSW.Unit_ID Then movingunit.BasePersUnit.SecondSWlink = 0
        'MsgBox(Trim(movingunit.BasePersUnit.UnitName) & " drops " & Trim(SWtoCheck.BaseSW.UnitName))
        dropchoice = True
        End If

        Next
        If Not dropchoice Then Return False
        Else
        Return False 'can' t get move mF by dropping PP
        End If
        'have dropped one or both SW so can retry move
        Return True
    }
    private boolean ChangeLocationTest(ByVal MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti) {
        If(LocationChangedvalue >= constantclasslibrary.aslxna.Location.Pill1571 And LocationChangedvalue <= constantclasslibrary.aslxna.Location.Bombprf)
        Or LocationChangedvalue = constantclasslibrary.aslxna.Location.BunkUnder Then
        Return True
        Else
        Return False
        End If
    }*/
    public String getmoveresults(){
        return moveresults;
    }
}
