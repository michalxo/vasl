package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class MoveIntoVehiclec implements MoveWithinHexi {

    // called by Movement.movewithinhex,
    // triggered by popup option click

    private Hex hexclickedvalue;
    private Constantvalues.UMove movementoptionclickedvalue;
    private Constantvalues.AltPos PositionChangedvalue;
    private double MFCost;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String AFVName;
    //private AFV Vehloading;

    public MoveIntoVehiclec(Hex hexclicked, Constantvalues.UMove Movementoptionclicked, String PassAFVName) {

        hexclickedvalue = hexclicked;
        movementoptionclickedvalue = Movementoptionclicked;
        // PositionChangedvalue = Constantvalues.AltPos.None; // default value
        AFVName = PassAFVName;

    }

    public boolean MoveAllOK() {

        return false;

        // NOT UPDATED UNTIL AFV CLASSES ARE CREATED
        /*'Determine cost of move
        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
        Dim Movelocvalue As Integer = EnteringLocation(hexclickedvalue, movementoptionclickedvalue)
        Dim Moveloc As Locationi = New Locationc(Movelocvalue, movementoptionclickedvalue)
        'wrap with decorators
        Moveloc = New WeatherImpactc(Moveloc)
        'this will cascade down and back up the wrappers
        MFCost = Moveloc.EntryCost(hexclickedvalue)
        Dim OH As VisibleOccupiedhexes :Dim vehname As String
        OH = CType(Game.Scenario.HexesWithCounter(hexclickedvalue), VisibleOccupiedhexes)
        For Each Displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Vehicle, Displaysprite.TypeID) AndAlso Trim
        (Displaysprite.Objname) = Trim(AFVName) Then
                Vehloading = Game.Linqdata.GetVehiclefromCol(Displaysprite.ObjID)
        vehname = Vehloading.AFVName
        Exit For
        End If
        Next
        If Not IsNothing(vehname) Then
        MessageBox.Show("Trying to move onboard " & Trim(vehname) & " which will cost " & MFCost.ToString & " MF")
        Else
        MessageBox.Show("Vehicle not Found. Exiting routine")
        Return False
        End If
        'Determine if move is affordable and nullify road bonus
        Dim recalculate As Boolean :Dim HoldMFUsed As Single = 0
        Do
                recalculate = False
        For Each MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.selmoveunits
        'Now do road bonus check
        If MovingUnit.MovingPersUnit.UsingRoadBonus Then
        MessageBox.Show("No longer moving on road. -1 MF ", "Forfeiting Road Bonus")
        HoldMFUsed = MovingUnit.MovingPersUnit.MFUsed
        recalculate = True
        Exit For
        End If
        If MFCost >MovingUnit.MovingPersUnit.MFAvailable Then
        'move is not affordable, exit move
        MessageBox.Show(Trim(MovingUnit.BasePersUnit.UnitName) & " does not have enough MF to enter " & Trim(vehname))
        ElseIf MFCost = MovingUnit.MovingPersUnit.MFAvailable And MovingUnit.
        MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving Or
        MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoved Then
        'unit cannot use all its MF when assault moving
        MessageBox.Show(Trim(MovingUnit.BasePersUnit.UnitName) & " is assault moving and cannot use all its remaining MF to move")
        Moveloc = Nothing
        Return False
        ElseIf MovingUnit.MovingPersUnit.HasLdrBonus Then
        recalculate = True ' can' t use while loading / unloading / riding
        End If
        Next MovingUnit
        If Recalculate Then
        'this resets the decorator process to create a new Stack with revised MF and adjusts for movement so far
        Dim Scencolls As ObjectClassLibrary.
        ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
        Game.Scenario.DoMove.ConcreteMove.RedoMovementStack(movementoptionclickedvalue)
        End If
        Loop Until recalculate = False

        Dim LegalCheck As LegalMovei
        LegalCheck = New VehicleLegalC(hexclickedvalue, movementoptionclickedvalue, PositionChangedvalue)
        Dim MoveLegal As Boolean = LegalCheck.IsMovementLegal()
        If Not MoveLegal Then
        'movement is not legal, exit move
        MessageBox.Show("Desired move is not legal ")
        Moveloc = Nothing
        Return False
        Else
                PositionChangedvalue = LegalCheck.LocationChange
        End If
        'Determine if entry blocked  by enemy units
        Dim MoveBlocked
        As Boolean = Game.Scenario.DoMove.ConcreteMove.ProcessValidation(hexclickedvalue, Moveloc.Location, movementoptionclickedvalue)
        'if movement can proceed; draw will happen and then return to moveupdate to check consequences
        Moveloc = Nothing
        Return If (MoveBlocked, False, True)*/
    }
    public void MoveUpdate() {

        // NOT UPDATED UNTIL AFV CLASSES ARE IMPLEMENTED
        /*'Triggered by PassToObserver in Game.Update after graphics draw of move completed
        'update data collections - movement array and dataclasslibrary.orderofbattle
        'update display arrays - including what is left behind

        Dim OH As VisibleOccupiedhexes ': Dim Displaycheck As Boolean
        Dim MovingUnitPPCost As Single = 0 :Dim SMCCount As Integer = 0 :Dim WALoss As Boolean = False
        Dim OldLoc As Integer :Dim Savesprite As ObjectClassLibrary.ASLXNA.SpriteOrder
        Dim AlreadyAdded As Integer = 0 :Dim DelConAdded As New List(Of Integer) :Dim ConLost As String = "" :
        Dim ConRevealed As String = "" 'all used in revealing concealed unit(s)
        Dim ConLostHex As String = ""
        Dim RemoveConUnit = New List(Of ObjectClassLibrary.ASLXNA.PersUniti) 'holds any revealed dummies
        Dim RemoveCon = New List(Of Integer) 'holds any revealed Concealment ID
        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
        Dim LevelChk As TerrainClassLibrary.ASLXNA.LevelChecks = New
        TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
        Dim MovingUnit As ObjectClassLibrary.
        ASLXNA.PersUniti = CType(Scencolls.selmoveunits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
        Dim CheckNat As ObjectClassLibrary.
        ASLXNA.PersUniti = CType(Scencolls.selmoveunits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
        'Dim GetNat = New DataClassLibrary.NationalityCheck(Game.Scenario.ScenID)
        Dim MovingNationality As Integer = CheckNat.BasePersUnit.Nationality 'GetNat.GetNationality(CheckNat)
        Dim hexnum As Integer = MovingUnit.basepersunit.hexnum
        OH = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
        'update moving stack
        For Each MovingUnit In Scencolls.SelMoveUnits
        'unit loses WA (if any) from previous location
        Dim WARemoval As New ObjectChange.ASLXNA.WARemovec(MovingUnit)
        WALoss = WARemoval.IsRemoved
        'update moving unit
        MovingUnit = Game.Scenario.DoMove.ConcreteMove.UpdateAfterWithin(MovingUnit, MFCost, hexclickedvalue, 0, MovingUnit.BasePersUnit.hexlocation, PositionChangedvalue)


        Dim CreateMFtoDraw = New DrawMoveInfo(hexnum, hexnum, MFCost, 0)
        'redraw hex to show movei
        OH = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        'redo hover as this hex will be displaying
        Game.XNAGph.DrawHover(hexnum)
        ' do WA check
        Game.Scenario.DoMove.ConcreteMove.WACleanUp(hexnum, WALoss, MovingNationality, False)
        'If WALoss Then ' at least one exiting unit had WA
        '    ' check for WA changes -loss by broken/unarmed in start location and transfer to adjacent enemy
        '    Dim WallAdvLossCheck As WallAdvantageLossChecki = New WithinWALossCheckC(hexclickedvalue)
        '    If Not WallAdvLossCheck.WallAdvantageLossCheck(OldLoc) Then WALoss = False
        'End If
        'If WALoss Then ' all units in Oldhex have lost WA and so need to check for switch
        '    ' see if other units gain WA in adjacent hexes
        '    Dim SwitchWA As New SwitchWallAdvantageC(hexnum, MovNat)
        '    SwitchWA.Switch()
        'End If
        Game.Linqdata.QuickUpdate()*/
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
    /*private boolean ChangeLocationTest(ByVal MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti) {
        If PositionChangedvalue = constantclasslibrary.aslxna.AltPos.Passenger
        Or PositionChangedvalue = constantclasslibrary.aslxna.AltPos.Rider Then
        Return True
        Else
        Return False
        End If
    }*/


}
