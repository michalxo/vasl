package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.SetTargetMoveStatus;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.WeatherImpactc;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.ArrayList;
import java.util.LinkedList;

public class ClearanceAttemptc implements MoveWithinHexi {

    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private double MFCost;
    private String ClearanceResultvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();

    public ClearanceAttemptc(Hex hexclicked, Constantvalues.UMove Movementoptionclicked) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;

    }

    public boolean MoveAllOK() {
        // Determine cost of move
        Location Movelocvalue = EnteringLocation(hexclickedvalue, movementoptionclickedvalue);
        Locationi Moveloc = new Locationc(Movelocvalue, movementoptionclickedvalue);
        // wrap with decorators - not needed for clearance
        MFCost = Moveloc.getlocationentrycost(hexclickedvalue);
        // Determine if move is affordable and nullify road bonus
        boolean MoveAffordable = scen.DoMove.ConcreteMove.Recalculating(movementoptionclickedvalue, hexclickedvalue, MFCost, Moveloc, hexclickedvalue);
        if (!MoveAffordable) {return false;}

        // Determine if move is legal
        LegalMovei LegalCheck = new ClearanceLegalc(hexclickedvalue, movementoptionclickedvalue);
        if(!LegalCheck.IsMovementLegal()) {
            // movement is not legal, exit move
            // MessageBox.Show("Desired move is not legal ")
            Moveloc = null;
            return false;
        } else {
            ClearanceResultvalue= LegalCheck.getresultsstring();
        }
        ClearanceResultvalue += "Trying to clear terrain in  . . . " + hexclickedvalue.getName() + " . . . which makes unit TI";

        for (PersUniti MovingUnit : Scencolls.SelMoveUnits) {
            // check if has already moved
            if (MovingUnit.getMovingunit().getMFUsed() > 0) {
                // MessageBox.Show("You have already spent MF and therefore cannot attempt Clearance", "B24.7 Clearance violation")
                Moveloc = null;
                return false;
            }
        }

        // Determine if entry blocked  by enemy units
        boolean MoveBlocked = scen.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, hexclickedvalue.getCenterLocation(), movementoptionclickedvalue);
        // if movement can proceed; draw will happen and then return to moveupdate to check consequences
        Moveloc = null;
        return (MoveBlocked ? false: true);

        // SOME OF THIS IS NOT IN RECALCULATING - NEED TO DECIDE IF IT SHOULD BE - OR MAYBE SPECIFIC TO CLEARANCE OR WRONG?
        /*MovingUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moving
        If MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving
        Or MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoved Then
        MessageBox.Show("You are using all of your MF and are no longer using Assault Movement so are subject to -2 Hazardous Move DRM")
        MovingUnit.MovingPersUnit.AssaultMove = 0
        ElseIf MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoved Then
        'unit cannot use all its MF when assault moving
        MessageBox.Show(Trim(MovingUnit.BasePersUnit.UnitName) & " is assault moving and cannot use all its remaining MF to move")
        Moveloc = Nothing
        Return False
        End If*/

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
        PersUniti MovingNatUnit = Scencolls.SelMoveUnits.getFirst();
        // set Nationality to friendly
        MovingNationality = MovingNatUnit.getbaseunit().getNationality();
        Hex Oldhex = MovingNatUnit.getbaseunit().getHex();
        Location Temploc = null;
        // update moving stack
        for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
            MFCost = MovingUnit.getMovingunit().getMFAvailable();
            MovingUnit.getMovingunit().setMFUsed(MFCost);
            MovingUnit.getMovingunit().setHexEnteredSideCrossed(0); // within hex move
            MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.TI);
        }

        if (Scencolls.SelMoveUnits.isEmpty()) {
            // update data collections
            //MovingUpdate DoUpdate = new MovingUpdate();
            //DoUpdate.UpdateAfterMoveClDC(movementoptionclickedvalue, Scencolls.SelMoveUnits);
            // REPLACE ABOVE 2 LIINES WITH CALL TO UPDATEMOVEUNITICOMMAND AS PER TARGETSTATUSUPDATE IN TARGETUNIT CLASSES
        }

        // create ThingToDo in Close Combat
        Constantvalues.WhoCanDo PassPlayerTurn  = scen.getPlayerTurn();
        CommonFunctionsC comfunc = new CommonFunctionsC(scen.getScenID());
        ConversionC confrom = new ConversionC();
        Constantvalues.ToDo whattodo = confrom.ConvertUMovetoToDo(movementoptionclickedvalue);
        comfunc.CreateNewThingsToDo(whattodo, MovingNatUnit.getbaseunit().getHex(), MovingNatUnit.getbaseunit().gethexlocation(), PassPlayerTurn );
        // reset this so it does not hold clearance value
        for (PersUniti movingunit : Scencolls.SelMoveUnits) {
            movingunit.getbaseunit().sethexlocation(movingunit.getbaseunit().getHex().getCenterLocation());
        }
        // process ClearFlame and ClearATMine results
        if (ClearanceResultvalue == "Extinguished") {
            /*For Each Displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
            If Displaysprite.TypeID = ConstantClassLibrary.ASLXNA.VisHind.Flame Or Displaysprite.
            TypeID = ConstantClassLibrary.ASLXNA.VisHind.HamperedFlame Then*/
            GetALocationFromMap GetLocs = new GetALocationFromMap();
            Locationi LOCtouse = GetLocs.RetrieveLocationfromHex(MovingNatUnit.getbaseunit().gethexlocation());
            // LOCtouse.Smoke = 0; need to manage
        } else if (ClearanceResultvalue == "Hampered") {
            GetALocationFromMap GetLocs = new GetALocationFromMap();
            Locationi LOCtouse = GetLocs.RetrieveLocationfromHex(MovingNatUnit.getbaseunit().gethexlocation());
            // LOCtouse.Smoke = Constantvalues.VisHind.HamperedFlame  - NEED TO MANAGE
        } else if (ClearanceResultvalue == "Cleared") {
            GetALocationFromMap GetLocs = new GetALocationFromMap();
            Locationi LOCtouse = GetLocs.RetrieveLocationfromHex(MovingNatUnit.getbaseunit().gethexlocation());
            // LOCtouse.Smoke = 0; need to manage
            LOCtouse.setAPMines(0); // LOCtouse.ATMinesVisible = False - need to manage
        }
        // update Target values
        SetTargetMoveStatus UpdateTarget = new SetTargetMoveStatus();
        UpdateTarget.RemoveRevealedDummies(RemoveConUnit);
        UpdateTarget.UpdateTargetHexLocPos(hexclickedvalue, scen.DoMove.ConcreteMove.getSelectedPieces() );

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
    /*private boolean Wiretest(ByRef movingunit As ObjectClassLibrary.ASLXNA.PersUniti) {
        'return true allows recalculate and retry movement
        'Dim UnittoCheck As DataClassLibrary.OrderofBattle
        'only applies to moving beneath wire so if not then exit
        If Not movementoptionclickedvalue = constantclasslibrary.aslxna.UMove.EnterWire Then Return False
        'wire attempt uses all MFAvailable
        movingunit.MovingPersUnit.MFUsed += movingunit.MovingPersUnit.MFAvailable
        movingunit.MovingPersUnit.MFAvailable = 0
        'dummy has no SW to drop
        If movingunit.MovingPersUnit.IsDummy Then Return False

        If movingunit.BasePersUnit.SW = 0 Then Return False 'no PP to drop
        Dim TestModel As New ObjectClassLibrary.ASLXNA.MovementModifiersc :Dim Entrychoice As DialogResult :
        Dim dropchoice As Boolean = False
        Dim SWCarrying As Integer = TestModel.PPCarrying(movingunit)
        If SWCarrying >movingunit.MovingPersUnit.IPC Then
        Dim SWtoCheck As ObjectClassLibrary.ASLXNA.SuppWeapi :Dim FindSW As Integer = 0
        For i = 1 To 2
        If i = 1 And movingunit.BasePersUnit.FirstSWLink<> 0 Then FindSW = movingunit.BasePersUnit.FirstSWLink
        If i = 2 And movingunit.BasePersUnit.SecondSWlink<> 0 Then FindSW = movingunit.BasePersUnit.SecondSWlink
        If FindSW = 0 Then Return False 'no SW found
        SWtoCheck = (From getsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
        getsw.BaseSW.Unit_ID = FindSW Select getsw).First

        If IsNothing (SWtoCheck) Then Return False
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

        dropchoice = True
        End If

        Next
        If Not dropchoice Then Return False
        Else
        Return False 'can' t get move mF by dropping PP
        End If
        'have dropped one or both SW so can retry move
        Return True
    }*/
}
