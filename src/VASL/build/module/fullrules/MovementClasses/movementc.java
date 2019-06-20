package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.*;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.*;
import VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses.*;
import VASL.build.module.fullrules.ObjectChangeClasses.*;
import VASL.build.module.fullrules.ObjectClasses.*;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;
import VASL.build.module.fullrules.TerrainClasses.TerrainChecks;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.ConcealmentLossc;
import VASL.build.module.fullrules.UtilityClasses.ConcealmentLossi;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.counters.ASLProperties;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;

import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class movementc implements movei {

    // The properties and methods in this class:
    // (a) handle parts of the movement process
    // (b) are common to all movement and used by movement classes

    private ArrayList<observeri> MoveObservers = new ArrayList();  // used as part of MVC
    private MovementModifiersi DetermineModifiers;   // proceeses movement modifiers
    private MoveNewHexi MovingNewHex;    // interface for moving to a new hex
    private MoveWithinHexi MovingWithinHex;   // interface for moving within a hex (including up and down levels)
    private boolean MovementFlag = false;  // toogle used to start and stop draw - PROBABLY WONT NEED
    private boolean CasFlag = false;       // toogle used in search methods
    private int CasDRM = 0;            // used in search methods
    private int hexclickedholder = 0;  // value of Hex in which movement action takes place - PROBABLY WONT NEED
    private Constantvalues.Nationality pMovingSide = Constantvalues.Nationality.None;   // value of moving side as Enum.Nationality
    private ScenarioC scen;
    private LinkedList<GamePiece> pSelectedPieces;

    public Constantvalues.Nationality getMovingSide() {
        return pMovingSide;
    }  // holds value of moving side as member of Enum.Nationality

    public void Initialize(int scenid) {
        // not sure what to use this for; set up various objects based on clicks?
        // sets side which is moving - returns a Nationality value

        CommonFunctionsC PhaseSide = new CommonFunctionsC(scenid);
        pMovingSide = PhaseSide.GetPhaseSide();
        scen = ScenarioC.getInstance();
    }

    public LinkedList<GamePiece> getSelectedPieces() {return pSelectedPieces;}

    public boolean MoveToNewHex(Hex HexClicked, Constantvalues.UMove MovementOptionClicked, String PassSelection) {
        /* called by Processpopup,  or Moveconti.NewAction(MapClick) when no menu options for a hex
        triggered by mapclick in hex outside Scencolls.selmoveunits' s hex
        calls the proper movement class to process the action (movementoptionclicked)
        parallels MoveWithinHex*/

        // clear previous class
        if (MovingWithinHex != null) {
            MovingWithinHex = null;
        }
        switch (MovementOptionClicked) {
            case ClearEnterRubble0:
            case ClearEnterRubble1:
            case ClearEnterRubble2:
            case ClearEnterRubble3:
            case ClearEnterRubble4:
            case ClearEnterRubble5:
            case ClearEnterMines0:
            case ClearEnterMines1:
            case ClearEnterMines2:
            case ClearEnterMines3:
            case ClearEnterMines4:
            case ClearEnterMines5:
                // entering new hex and trying to clear something
                MovingNewHex = new EnterAndClear(HexClicked, MovementOptionClicked);
                break;
            case EnterWA:
                // entering new hex and claiming wall advantage
                MovingNewHex = new EnterAndWA(HexClicked, MovementOptionClicked);
                break;
            case CrestStatusRateWA:
                // entering new hex and claiming wall advantage from creststatus
                MovingNewHex = new EnterCrestWA(HexClicked, MovementOptionClicked);
                break;
            case EnterConnectingCellar:
                case EnterConnectingTrench:
                case EnterConnectingBldg:
                case EnterConnectingPill:
                case EnterViaConnection:
                // entering new hex via connecting terrain (ie tunnel)
                MovingNewHex = new EnteringConnecting(HexClicked, MovementOptionClicked);
                break;
            default:
                // entering a new hex without doing any of the above
                MovingNewHex = new EnterNewHex(HexClicked, MovementOptionClicked);
        }

        if (MovingNewHex.MoveAllOK()) {
            // if here then move affordable, proceed, send to observer for screen update
            String moveresults = MovingNewHex.getmoveresults();
            NotifyMoveObservers(HexClicked, true, moveresults);
            return true;
        } else {
            // cannot enter hex: not enough MF or enemy present
            String moveresults = MovingNewHex.getmoveresults(); //"Hit Clear Movement to restart or carry on with current selected units; Invalid Movement Attempt";
            NotifyMoveObservers(HexClicked, false, moveresults);
            return false;
        }
    }

    public void moveupdate() {
        // called from Observeri when graphics update finished - via STopMovementDraw
        // does data update (including setting up redraw)

        if (MovingNewHex != null) {
            MovingNewHex.MoveUpdate();
        } else if (MovingWithinHex != null) {
            MovingWithinHex.MoveUpdate();
        }

        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        if (!Scencolls.SelMoveUnits.isEmpty()) {  // moving units selected so do FP-drm display
            // REPLACE THIS WITH NEW DRAW CODE
            /*DFFMVCPattern DFFMVC = new DFFMVCPattern();
            For Each ShowString As IFTOppClassLibrary.ASLXNA.storeShadeHex In DFFMVC.StoreStringstoDraw
            Game.StringsToDraw.Add(New ShadeHex(ShowString.Hexname, ShowString.Hexnum, ShowString.LOSStatus, ShowString.LevelClear, ShowString.TextPos, ShowString.ShowColor))
            Next
            For Each ShowShade As IFTOppClassLibrary.ASLXNA.storeShadeHex In DFFMVC.StoreHexestoShade
            Game.HexesToShade.Add(New ShadeHex(ShowShade.Hexname, ShowShade.Hexnum, ShowShade.LOSStatus))
            Next
            Game.Scenario.ShaderToShow = DFFMVC.GetShaderToShow 'Constantvalues.ShadeShow.DFFShade*/
        } else {
            // no moving units so get out of here
            ClearMovement();
        }
    }

    public void QuickMove() {
        // not implemented yet - meant to allow for multi-hex move
    }

    public boolean MoveWithinHex(Hex hexclicked, Constantvalues.UMove MovementOptionClicked, String PassSelection) {
        // called by Processpopup,  triggered by rightclick in hex and then popup selection
        // popup option only shows IF move possible so don't need to recheck but need to confirm move is valid
        // calls the proper movement class to process the action (movementoptionclicked)
        // parallels MoveToNewHex
        // clear previous class

        if (MovingNewHex != null) {
            MovingNewHex = null;
        }
        switch (MovementOptionClicked) {
            case StairsUp:
            case StairsDown:
                MovingWithinHex = new MoveNewLevelc(hexclicked, MovementOptionClicked);
            case EnterFoxhole:
            case EnterWire:
            case EnterTrench:
            case FeatureExit:
            case ExitFoxhole:
            case ExitTrench:
                MovingWithinHex = new MoveIntoFeaturec(hexclicked, MovementOptionClicked);
            case CrestStatus0:
            case CrestStatus1:
            case CrestStatus3:
            case CrestStatus4:
            case CrestStatus5:
            case ExitCrestStatus:
            case INrate:
                MovingWithinHex = new MoveIntoCrestc(hexclicked, MovementOptionClicked);
            case WACrestStatus1:
            case WACrestStatus2:
            case WACrestStatus3:
            case WACrestStatus4:
            case WACrestStatus5:

                MovingWithinHex = new MoveIntoCrestWAc(hexclicked, MovementOptionClicked);
            case EnterVehicle:
                MovingWithinHex = new MoveIntoVehiclec(hexclicked, MovementOptionClicked, PassSelection);
            case ExitVehicle:
                MovingWithinHex = new MoveOutofVehiclec(hexclicked, MovementOptionClicked);
            case ThrowSmokeSame:
            case ThrowSmokeGround:
                MovingWithinHex = new ThrowSmokec(hexclicked, MovementOptionClicked);
            case ClearRubble:
            case ClearEnterRubble:
            case ClearEnterMines:
            case ClearMine:
            case ClearRdBlk:
            case Clearance:
            case ClearFlame:
            case ClearWire:
                MovingWithinHex = new ClearanceAttemptc(hexclicked, MovementOptionClicked);
            case ClearRoadATMine:
                MovingWithinHex = new ClearRdATMAttemptc(hexclicked, MovementOptionClicked);
            case ClaimWallAdv:
                MovingWithinHex = new ClaimWallAdvc(hexclicked, MovementOptionClicked);
            case ForfeitWA:
                MovingWithinHex = new ForfeitWAc(hexclicked, MovementOptionClicked);
            case StairsUpWA:
            case StairsDownWA:
                MovingWithinHex = new MoveNewLevelWAc(hexclicked, MovementOptionClicked);
            case Search:
                MovingWithinHex = new SearchHexesc(hexclicked, MovementOptionClicked);
            case ExitPIllbox:
            case EnterPillbox:
                MovingWithinHex = new MoveNewLocationc(hexclicked, MovementOptionClicked);
            case DropSW:
            case RecoverSW:
            case RecoverSWBrk:
                MovingWithinHex = new SWChangePossc(hexclicked, MovementOptionClicked, PassSelection);
            case PlaceDC:
                MovingWithinHex = new PlaceDCc(hexclicked, MovementOptionClicked);
            case ExitTerrain:
            case EnterTerrain:
                MovingWithinHex = new MoveOtherTerrainc(hexclicked, MovementOptionClicked);
        }
        // Dim XNAGph = XNAGraphicsC.GetInstance
        if (MovingWithinHex.MoveAllOK()) {
            // if here then move affordable, proceed, send to observer for screen update
            if (MovementOptionClicked == Constantvalues.UMove.Search || MovementOptionClicked == Constantvalues.UMove.DoPlaceDC) {
                int HexestoSearch = 0; // = XNAGph.DisplayShade.HexesToSearch;
                NotifyMoveObservers(hexclicked, MovementOptionClicked, HexestoSearch);
                return true;
            } else {
                String moveresults = MovingWithinHex.getmoveresults();
                NotifyMoveObservers(hexclicked, true, moveresults);
                return true;
            }
        } else {
            // cannot enter hex: not enough MF or enemy present
            String moveresults = "Hit Clear Movement to restart or carry on with current selected units; Invalid Movement Attempt";
            NotifyMoveObservers(hexclicked, false, moveresults);
            return false;
        }
    }

    public String GetMovementInfo() {
        // go and get with events info
        return null; // temp code while debugging
    }

    public void RegisterObserver(observeri Observer) {
        MoveObservers.add(Observer);
    }

    public void RemoveObserver(observeri Observer) {
        int i = MoveObservers.indexOf(Observer);
        if (i > 0) {
            MoveObservers.remove(i);
        }
    }

    public void MoveEvent() {
        // NotifyMoveObservers()
    }

    // overloaded to handle special case of Search
    public void NotifyMoveObservers(Hex hexclicked, boolean resultsvalue, String moveresults) {
        //if (resultsvalue) {
            GameModule.getGameModule().getChatter().send(moveresults);
        //} else {
        //    GameModule.getGameModule().getChatter().send("Can't do this Move");
        //}
    }
    public void NotifyMoveObservers(Hex hexclicked, Constantvalues.UMove movementoptionclicked, int HexesToSearch) {
        // scen.Moveobsi.StartMovementDraw(hexclicked, movementoptionclicked, HexesToSearch);
    }
    public boolean IsEligibletoMove(PersUniti MovingUnittoCheck, Hex ClickedHex, LinkedList<PersUniti> TempMovementStack) {
        // called by AddtoMoveStackAttempt
        // checks to see if unit eligible to move - has not already done something else and is in same location as existing selection

        //Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil

        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // NEED TO REPLACE CODE BLOCK WITH MEANS OF RETRIEVING ITEMS CLICKED ON DURING MOVEMENT PHASE - USE SAME LOGIC AS ifT
    /*if (TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, displaysprite.TypeID)) {  // is infantry
        // retrieve unit
        MovingUnittoCheck = (From Seltest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where Seltest.getbaseunit().Unit_ID = displaysprite.ObjID AndAlso Seltest.getbaseunit().TypeType_ID = Constantvalues.Typetype.Personnel).First
    ElseIf TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, displaysprite.TypeID) Then   'is concealment
            'retrieve concealment
    MovingUnittoCheck = (From Seltest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where Seltest.getbaseunit().Unit_ID = displaysprite.ObjID AndAlso Seltest.getbaseunit().TypeType_ID = Constantvalues.Typetype.Concealment).First
    End if*/
    // check for eligiblity
        if (MovingUnittoCheck != null) {
            if(MovingUnittoCheck.getbaseunit().getNationality() == scen.WhoseTurnIsIt()) {
                if (MovingUnittoCheck.getbaseunit().getCombatStatus() == Constantvalues.CombatStatus.PrepFirer ||
                MovingUnittoCheck.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.TI ||
                    (MovingUnittoCheck.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken)  ||  // && AndMovingUnittoCheck.OrderStatus <= Constantvalues.OrderStatus.DisruptedDM)Or
                    (MovingUnittoCheck.getbaseunit().getPinned() == true || MovingUnittoCheck.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Moved)) {
                        return false;  // unit has already taken action or is unable to move
                }
            } else {
                return false; //  unit is not eligible to move - not his turn
            }
        }
        // check for same location
        if (TempMovementStack.size() > 1) { // units must be in same location in the same hex
            TerrainChecks TerrChk = new TerrainChecks();
            for (PersUniti MovingUnit : TempMovementStack) {
                if (MovingUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.HumanWave ||
                        MovingUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.BanzaiCharge) {
                    // needs to be adjacent - STILL TO BE CODED
                } else {
                    if (MovingUnit.getbaseunit().getHex() != ClickedHex || !(TerrChk.AreLocationsSame(MovingUnit.getbaseunit().gethexlocation(), MovingUnittoCheck.getbaseunit().gethexlocation(),
                            MovingUnit.getbaseunit().gethexPosition(), MovingUnittoCheck.getbaseunit().gethexPosition(), ClickedHex, Constantvalues.MovementStatus.Moving))) {
                        return false;  // not in same location for moving purposes so don' t add to Stack
                        //Exit Function
                    }
                }
            }
            return true;  // Displaysprite is in same hex and location as existing stack (or HW/BC eligible)
        } else {
            return true;  // Displaysprite is first item selected for movement
        }
    }

    public void ClearMovement() {
        // called by Moveconti.NewAction and handles Clear Movement button click
        Hex HexToClear = null;
        boolean UpdateRequired = false;
        Constantvalues.MovementStatus movementoption = Constantvalues.MovementStatus.NotMoving;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        for (PersUniti MoveItem: Scencolls.SelMoveUnits) {
            if (MoveItem.getbaseunit().getHex() != HexToClear) {
                //set value of hex where sprites need updating
                HexToClear = MoveItem.getbaseunit().getHex();
            }
            // get visibile counters in hex to update
            // REPLACE WITH LINK TO VASL COUNTERS?
            /*OH = CType(Game.Scenario.HexesWithCounter(HexToClear), VisibleOccupiedhexes)
            For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
            'match movingunit and its sprite
            Dim CompareCheck As New CompareUnitandSpriteC(MoveItem, DisplaySprite)
            if CompareCheck.Compare Then
            'deselect matching sprite
            Updatesprite = DisplaySprite
            Updatesprite.Selected = False
            Exit For
            End if*/

            // update location values
            if (MoveItem.getbaseunit().gethexPosition() == Constantvalues.AltPos.OtherTerrainInHex ||
                    //(MoveItem.getbaseunit().gethexPosition() >= Constantvalues.AltPos.ExitedCrest0 && MoveItem.getbaseunit().gethexPosition() <= Constantvalues.AltPos.ExitedCrest5)
                    MoveItem.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedEntrench) {
                // these are temp locations and units cannot end movement in them; revert to base terrain
                // is this always true to OtherTerrainInHex - CHECK July 12
                // NEED TO REWORK THIS AS IT IS CIRCULAR - WHY NEED TO RESET LOCATION?? OCT 18
                LevelChecks LevelChk = new LevelChecks();
                Location baseloc = LevelChk.GetLocationatLevelInHex(MoveItem.getbaseunit().getHex(), 0);
                //Location baseloc = UsingHex.getvasllocation();
                MoveItem.getbaseunit().sethexlocation(baseloc);
                MoveItem.getbaseunit().sethexPosition(Constantvalues.AltPos.None);
                UpdateRequired = true;
            }
            // set Movement Status to prevent further movement this turn and to trigger Defensive fire drm
            if (MoveItem.getMovingunit().getMFUsed() > 0) {
                UpdateRequired = true;
                movementoption = Constantvalues.MovementStatus.Moved;
            }
        }
        // if triggered, process search casualties
        if (CasFlag) {
            // or defender has booby trap
            //SearchCasualties SearchCas = new SearchCasualites(CasDRM);
            CasFlag = false;
            CasDRM = 0;
        }
        // do write to database (OrderofBattle object)
        if (UpdateRequired) {
            MovingUpdate DoUpdate = new MovingUpdate();
            DoUpdate.UpdateAfterMove(movementoption, Scencolls.SelMoveUnits);
        }
        // stop drawing DO WE NEED THIS?
        if (MovementFlag) {
            // Game.MovingItem.IsMoving = false;
            MovementFlag = false;
        }
        // need to clear the movement property
        for (PersUniti FirstItem: Scencolls.SelMoveUnits) {
            FirstItem.setMovingunit(null);
        }
        Scencolls.SelMoveUnits.clear();
        // clear items related to shading hexes for LOS, Searching
        /*Dim XNAGph = XNAGraphicsC.GetInstance
        if Not IsNothing(XNAGph.DisplayShade) Then
        XNAGph.DisplayShade.HexesToSearch = 0
        XNAGph.DisplayShade.ShaderClear()
        End if
        'clear movement graphics
        Game.MoveStringsToDraw.Clear()
        Game.MoveTrailstoDraw.Clear()
        Game.MoveFactorstoDraw.Clear()*/
        
        // clear DFF
        scen.IFT.ClearCurrentIFT();
        // scen.ShaderToShow = 0;
        // saves any terrain changes or LOS check updates/additions to the database
        String ASLMapLink = "Scen" + scen.getScenID();
        String ASLLOSLink =  "LOS" + scen.getScenID();
        //need to pass string value to create terrain collection
        //Dim Maptables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(Trim(ASLMapLink), Game.Scenario.ScenID)
    }

    public boolean IsPartofStack(PersUniti findunit, LinkedList<PersUniti> TempMovementStack) {
        // called by this.Addtostackattempt
        // check if PersUniti already part of TempMovementStack

        for (PersUniti MovingUnit: TempMovementStack) {
            if (findunit.getbaseunit().getUnit_ID() == MovingUnit.getbaseunit().getUnit_ID() &&
                Constantvalues.Typetype.Personnel == findunit.getbaseunit().getTypeType_ID()) {
                    return true;  // already added
            }
        }
        return false;
    }

        
    public boolean AddtoMoveStackAttempt(Hex ClickedHex, LinkedList<GamePiece> SelectedUnits) {
        // called by DetermineClickResult
        // receives units from Controller and checks if they can be added to Objectclasslibrary.aslxna.Scenariocollectionsc.SelMoveUnits

        // code from IFTC.ClickedOnNewParticipants
        PersUniti Addunit = null;
        LinkedList<PersUniti> TempMovementStack = new LinkedList<PersUniti>();
        Constantvalues.CombatStatus WhichOne = Constantvalues.CombatStatus.None;
        int ObjIDlink = 0;
        // Get list of PersUniti objects for the selected units
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

        //if (ValidSolutions.size() > 0) { // selecting one of possible solutions already checked
            for (GamePiece SelUnit : SelectedUnits) {
                if (SelUnit.getProperty(ASLProperties.LOCATION) == null) {  // is ASLProperties.Location is set then is un-movable piece
                    ObjIDlink = java.lang.Integer.parseInt(SelUnit.getProperty("TextLabel").toString());
                    for (PersUniti findunit : Scencolls.Unitcol) {
                        if (findunit.getbaseunit().getUnit_ID() == ObjIDlink) {
                            if (findunit.getbaseunit().getNationality() == getMovingSide()) {
                                if (!IsPartofStack(findunit, TempMovementStack)) {
                                    TempMovementStack.add(findunit);
                                }
                            }
                        }
                    }
                }
            }
        //}
        pSelectedPieces = SelectedUnits;
        /*// no solution yet in place, so add units and test for solution
        // if selected determine if unit or ? and use nationality to determine if Target or Firer
        for (GamePiece SelUnit : SelectedUnits) {
            ObjIDlink = Integer.parseInt(SelUnit.getProperty("TextLabel").toString());
            for (PersUniti findunit : Scencolls.Unitcol) {
                if (findunit.getbaseunit().getUnit_ID() == ObjIDlink) {
                    if (findunit.getbaseunit().getNationality() == getTargetSide()) {
                        WhichOne = Constantvalues.CombatStatus.None;
                    } else {
                        WhichOne = Constantvalues.CombatStatus.Firing;
                    }
                    Addunit = findunit;
                    break;
                }
            }
            // add unit or ? to Target or Firer (? not added to firer)
            if (WhichOne == Constantvalues.CombatStatus.None && Addunit != null) {  // TargetUniut
                if (AddTargetUnit(Addunit)) {
                    GoCombatSolutionTest = true;
                }
            } else {  // FiringUnit
                if (Addunit.getbaseunit().getVisibilityStatus() != Constantvalues.VisibilityStatus.Visible) {
                    // clicked on concealed unit; don't add
                    GameModule.getGameModule().getChatter().send("Failure to Add Concealed Firer Unit: " + Addunit.getbaseunit().getUnitName() + " in ClickedOnNewParticipants");
                } else {
                    if (AddFirerUnit(Addunit)) {
                        GoCombatSolutionTest = true;
                    }
                }
            }
        }*/

        boolean LeaderInStack = false; boolean RBInSTack = false;

        /*Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
        // check each selected sprite for non-moving side and don't add (return false but don't deselect)
        PersUniti UnitNat;
        for (SpriteOrder displaysprite: OH.VisibleCountersInHex());
            if (TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, displaysprite.TypeID) && displaysprite.Selected) {
                // if clicked on SW, get owner
                Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = displaysprite.ObjID Select GetSW.BaseSW.Owner).First
    if SWOwner > 0 Then
            UnitNat = (From SelTest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where SelTest.getbaseunit().Unit_ID = SWOwner Select SelTest).First
    End if
    ElseIf TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, displaysprite.TypeID) And displaysprite.Selected Then
    UnitNat = (From SelTest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where SelTest.getbaseunit().Unit_ID = displaysprite.ObjID Select SelTest).First
    ElseIf TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, displaysprite.TypeID) And displaysprite.Selected Then
    UnitNat = (From SelTest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where SelTest.getbaseunit().Unit_ID = displaysprite.ObjID AndAlso SelTest.getbaseunit().TypeType_ID = Constantvalues.Typetype.Concealment Select SelTest).First
    ElseIf displaysprite.Selected Then
    displaysprite.Selected = False
    Return False 'if clicks on Gun, Vehicle, just get out of here
    End if
                'if nationality is not movingside then exit
    if UnitNat.getbaseunit().Nationality <> MovingSide Then Return False
            Next
            'put already selected into temp stack and reprocess
    Dim TempMovementStack As New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    For Each Movingunit In Scencolls.SelMoveUnits  'Scencolls.selmoveunits
            TempMovementStack.Add(Movingunit)
    Next*/
        Scencolls.SelMoveUnits.clear();
                    /*'check if unit already selected
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    if DisplaySprite.Selected And (TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, DisplaySprite.TypeID) Or (TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID))) Then  'is infantry or dummy
    if IsPartOfStack(DisplaySprite, TempMovementStack) Then
                        'unit already selected, do nothing further for this unit, keep sprite selected
    Continue For
    End if*/
        // Check if can move
        for (PersUniti checkeligible: TempMovementStack) {
            if (IsEligibletoMove(checkeligible, ClickedHex, TempMovementStack)) {
                // if can move, create MoveUnit property and add to TempMovemementStack
                PersCreation ObjectCreate = new PersCreation();
                if (checkeligible.getMovingunit() == null) {
                    checkeligible = ObjectCreate.CreateMovingUnitandProperty(checkeligible);
                }
            } else {
                String ShowName = checkeligible.getbaseunit().getUnitName();
                GameModule.getGameModule().getChatter().send(ShowName + " is not eligible to join moving stack");
                // keeping firing units selected
                if (checkeligible.getbaseunit().getNationality() == scen.IFT.getTargetSide()) {
                    // unselect the GamePiece
                }
                // reset Movement stack
                for (PersUniti TempMovingunit : TempMovementStack) {
                    Scencolls.SelMoveUnits.add(TempMovingunit);
                }
                return false;  // do nothing further
            }
        }
        // if here, then all units in stack are eligible
        // determine if leader exists; if using road bonus (must apply to all)
        if (!LeaderInStack) {
            LeaderTesti LeaderPresent = new StackLeaderTestC(TempMovementStack);
            LeaderInStack = LeaderPresent.IsLeaderPresent();
        }   
        for (PersUniti movingunittocheck : TempMovementStack) {
            if (movingunittocheck.getMovingunit().getUsingRoadBonus()) {RBInSTack = true;}
        }
        // determine MF modifications (decorators) such as CX, IPC exceeded, road bonus, ldrbonus, encircled
        for (PersUniti MovingUnittoUpdate: TempMovementStack) {
            // all modifiers set by previous selection, now updated
            Constantvalues.UMove Controlclick = null;
            //ConversionC convertfrom = new ConversionC();
            if (RBInSTack) {Controlclick = Constantvalues.UMove.RoadBonus;}
            MovementModifiersc DetermineModifiers = new MovementModifiersc();
            DetermineModifiers.updateModifiers(MovingUnittoUpdate, Controlclick, LeaderInStack);
            // wrap in decorators
            if (DetermineModifiers.getPPAdjust() != 0 && !(MovingUnittoUpdate.getMovingunit().getPPImpact())) {
                MovingUnittoUpdate.setMovingunit(new IPCc(MovingUnittoUpdate, DetermineModifiers));}
            if (DetermineModifiers.getDoubleTiming() && !(MovingUnittoUpdate.getMovingunit().getUsingDT())) {
                MovingUnittoUpdate.setMovingunit(new DoubleTimec(MovingUnittoUpdate));}
            if (DetermineModifiers.isEncircled() && !(MovingUnittoUpdate.getbaseunit().IsUnitEncircled())) {
                MovingUnittoUpdate.setMovingunit(new Encircledc(MovingUnittoUpdate));}
            if (DetermineModifiers.getsLdrBonus() && !(MovingUnittoUpdate.getMovingunit().getHasLdrBonus())) {
                MovingUnittoUpdate.setMovingunit(new LdrBonusc(MovingUnittoUpdate, TempMovementStack));}
            if (DetermineModifiers.getsRoadBonus() && !(MovingUnittoUpdate.getMovingunit().getUsingRoadBonus())) {
                MovingUnittoUpdate.setMovingunit(new RoadBonusc(MovingUnittoUpdate));}
            MovingUnittoUpdate.getMovingunit().setMFAvailable(MovingUnittoUpdate.getMovingunit().CalcMF() - MovingUnittoUpdate.getMovingunit().getMFUsed());
            // add to moving stack
            Scencolls.SelMoveUnits.add(MovingUnittoUpdate);
        }
        return Scencolls.SelMoveUnits.size() > 0 ? true : false;
        //Dim CreateMFtoDraw = New DrawMoveInfo(OH.Hexnum, OH.Hexnum, 0, 0)

    }

    public PersUniti UpdateExistingMovingUnit(PersUniti MovingUnittoUpdate, Constantvalues.UMove controlclick, boolean leaderinStack, LinkedList<PersUniti> MoveStack){
        // called by ProcessPopup and RedoMovementStack
        // updates existing movement stack to reflect changed values for DT, RoadB, LdrB, etc

        // create new modifier object
        MovementModifiersc DetermineModifiers = new MovementModifiersc();
        DetermineModifiers.updateModifiers(MovingUnittoUpdate, controlclick, leaderinStack);
        // wrap in decorators
        if (DetermineModifiers.getPPAdjust() != 0 && !(MovingUnittoUpdate.getMovingunit().getPPImpact())) {
            MovingUnittoUpdate.setMovingunit(new IPCc(MovingUnittoUpdate, DetermineModifiers));}
        if (DetermineModifiers.getDoubleTiming() && !(MovingUnittoUpdate.getMovingunit().getUsingDT())) {
            MovingUnittoUpdate.setMovingunit(new DoubleTimec(MovingUnittoUpdate));}
        if (DetermineModifiers.isEncircled() && !(MovingUnittoUpdate.getbaseunit().IsUnitEncircled())) {
            MovingUnittoUpdate.setMovingunit(new Encircledc(MovingUnittoUpdate));}
        if (DetermineModifiers.getsLdrBonus() && !(MovingUnittoUpdate.getMovingunit().getHasLdrBonus())) {
            MovingUnittoUpdate.setMovingunit(new LdrBonusc(MovingUnittoUpdate, MoveStack));}
        if (DetermineModifiers.getsRoadBonus() && !(MovingUnittoUpdate.getMovingunit().getUsingRoadBonus())) {
            MovingUnittoUpdate.setMovingunit(new RoadBonusc(MovingUnittoUpdate));}
        if (controlclick == Constantvalues.UMove.Assault) {MovingUnittoUpdate.getMovingunit().setAssaultMove(Constantvalues.MovementStatus.AssaultMoving);}
        if (controlclick == Constantvalues.UMove.Dash) {MovingUnittoUpdate.getMovingunit().setDash(Constantvalues.MovementStatus.FirstDash);}
        // calculate MF available adjusted for what has already been used
        MovingUnittoUpdate.getMovingunit().setMFAvailable(MovingUnittoUpdate.getMovingunit().CalcMF() - MovingUnittoUpdate.getMovingunit().getMFUsed());
        return MovingUnittoUpdate;
    }

    public Hex GetStartingHex() {
        // called by controller after mapclick to determine where moving units are starting from
        // if no units yet selected, returns 0; else returns hexnumber
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        if (Scencolls.SelMoveUnits.size() == 0) {return null;} // no units selected
        return Scencolls.SelMoveUnits.getFirst().getbaseunit().getHex();

    }

    /*
    public Sub DetermineMenuforHexMove(ByRef menuitems As List(Of DataClassLibrary.ASLXNA.Objectholder), ByVal Currenthex As Integer, ByVal menuMovehex As Locationi) Implements movei.DetermineMenuforHexMove

            'called from controller to determine which menu items to pass to observer for display
                    'based on one-hex move
    Dim Terrainrate As Boolean = False : Dim Hexsidetype As Integer = 0 : Dim RoadSide As Boolean = False
    Dim MapHex As MapDataClassLibrary.GameLocation : Dim menuItemadd As DataClassLibrary.ASLXNA.Objectholder : Dim ActivityText As String = ""
            'clear menu list
                    menuitems.Clear()
                    'Get data items: current level, hexside crossed, hextype, COT, etc
                    'instantiate map terrain classes
    Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(Game.Scenario.LocationCol)
    Dim Levelchk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(Game.Scenario.LocationCol)
    Dim MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'which hexside crossed(1-6) of new hex (menuMovehex.hexnum)
    Dim HexSideCrossed As Integer = MapGeo.HexSideEntry(Currenthex, menuMovehex.hexnum)
            'get level in hex of moving units - must be at same level-in-hex in new hex unless in split level building -NEED TO ADD MANAGEMENT OF SPLIT LEVELS
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti = CType(Scencolls.SelMoveUnits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
    Dim AtLevel = Levelchk.GetLocationPositionLevel(MovingUnit.getbaseunit().Hexnum, MovingUnit.getbaseunit().hexlocation, MovingUnit.getbaseunit().hexPosition)
            'get terrain type and COT and generate terrain type name for location at same level in hex hex
    if AtLevel = 0 Or (AtLevel = 1 And ((MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.CrestStatus0 And MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.CrestStatus5) Or
    MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.ExitedCrest0 And MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.ExitedCrest5)) }
                'handle special crest/exitedcrest situations - needs a class?
    MapHex = Levelchk.GetLocationatLevelInHex(menuMovehex.hexnum, 0)
    Else
            Try
    MapHex = Levelchk.GetLocationatLevelInHex(menuMovehex.hexnum, AtLevel)
    Catch ex As Exception
    MapHex = Levelchk.GetLocationatLevelInHex(menuMovehex.hexnum, 0)
    End Try
    End if
    Dim TerrID As Integer = CInt(MapHex.Location)
    Dim Terrainname As String = TerrChk.GetLocationData(Constantvalues.TerrFactor.Desc, TerrID, Game.Scenario.Maptables)
    Dim OtherTerrainname As String = TerrChk.GetLocationData(Constantvalues.TerrFactor.Desc, MapHex.OtherTerraininLocation, Game.Scenario.Maptables)
    Dim COT As Single = MapHex.MFCot
    Dim COTOT As Single = MapHex.MFOtherTerrain
            'get hexside type at level
    Hexsidetype = SideTest.Gethexsidetype(MapHex, HexSideCrossed)
    Dim SideCost As Single = CSng(SideTest.GetSideData(Constantvalues.TerrFactor.HexsideMFcost, Hexsidetype, Game.Scenario.Maptables))
    Dim SideDesc As String = ""
    if SideCost > 0 } SideDesc = SideTest.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Hexsidetype, Game.Scenario.Maptables)
            'determine menu options to show based on above context
    if AtLevel <= 0 Or (AtLevel = 1 And ((MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.CrestStatus0 And MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.CrestStatus5) Or
    MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.ExitedCrest0 And MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.ExitedCrest5)) }
    if MapHex.CrestStatusOK } 'moving into depression hex; roadrate cover bridge possibility, must add IN move option
    if IsTerrChk.IsLocationTerrainA(menuMovehex.hexnum, TerrID, Constantvalues.Location.bridgetype) }
    ActivityText = "Enter IN; below Bridge"
    Else
            ActivityText = "Enter IN"
    End if
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.INrate
    menuItemadd.ActivityNames = CreateMenuText(ActivityText, SideDesc, COT, SideCost)
                    menuitems.Add(menuItemadd)
    Terrainrate = True
                    'now add roadrate if bridge
    if (SideTest.IsARoad(HexSideCrossed, MapHex.LocIndex)) }
                        'entering via road hex so show road rate option (which covers bridge)
    RoadSide = True
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.Roadrate
    menuItemadd.ActivityNames = CreateMenuText("Road rate", SideDesc, COT, SideCost)
                        menuitems.Add(menuItemadd)
    End if
                    'Else  'use OT in same level hex
                    '    if MapHex.OtherTerraininLocation > 0 Then 'there are situations where no other terrain - city-square
                    '        'Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(Game.Scenario.LocationCol)
            '        'Dim OtherTerrainname As String = Linqdata.GetTerrainData(constantclasslibrary.aslxna.TerrFactor.Desc, MapHex.OtherTerraininLocation)
                    '        if Not Terrainrate Then AddTerrainRateToMenu(menuitems, Terrainrate, OtherTerrainname, COTOT)
                            '    End if
    End if
                'add OT options
                        'are there other terrain types that should offer OT rate? Do not include feature/position (ie foxhole) here
    if IsTerrChk.IsLocationTerrainA(menuMovehex.hexnum, TerrID, Constantvalues.Location.Shellholetype) Or MapHex.IsPillbox }
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.OTRate
    menuItemadd.ActivityNames = CreateMenuText("OT (" & OtherTerrainname & ")", SideDesc, COTOT, SideCost)
                    menuitems.Add(menuItemadd)
    if Not Terrainrate } AddTerrainRateToMenu(menuitems, Terrainrate, Terrainname, COT, SideDesc, SideCost)
    End if
                'add bypass option
    if IsTerrChk.IsLocationTerrainA(menuMovehex.hexnum, TerrID, Constantvalues.Location.Bypassable) }
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.bypassrate
    menuItemadd.ActivityNames = CreateMenuText("Bypass " & Terrainname, SideDesc, 1, SideCost)
                    menuitems.Add(menuItemadd)
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.extrabypassrate
    menuItemadd.ActivityNames = CreateMenuText("Extra bypass " & Terrainname, SideDesc, 2, SideCost)
                    menuitems.Add(menuItemadd)
    if Not Terrainrate } AddTerrainRateToMenu(menuitems, Terrainrate, Terrainname, COT, SideDesc, SideCost)
    End if
                'add CrestStatus option
    if MapHex.CrestStatusOK And Not (SideTest.IsAGullyStream(Hexsidetype)) And Not RoadSide }  'creststatus is allowed
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.INrate
    menuItemadd.ActivityNames = CreateMenuText("Enter IN (" & Terrainname & ")", SideDesc, COT, SideCost)
                    menuitems.Add(menuItemadd)
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.creststatusrate
    menuItemadd.ActivityNames = CreateMenuText("Enter Crest Status (COT-1)", SideDesc, COT - 1, SideCost)
                    menuitems.Add(menuItemadd)
    if SideTest.IsAWallHedgeRdBlk(HexSideCrossed, MapHex.LocIndex) } 'IsWAAllowed(menuMovehex.hexnum, HexSideCrossed) Then
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.CrestStatusRateWA
    menuItemadd.ActivityNames = CreateMenuText("Enter Crest Status and Claim WA (COT-1)", SideDesc, COT - 1, SideCost)
                        menuitems.Add(menuItemadd)
    End if
    Terrainrate = True
    End if
                'add hexlocations option - connecting cellars, trenchs, tunnels, pillboxes
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.InTrench Or (MovingUnit.getbaseunit().hexlocation >= Constantvalues.Location.Pill1571 And MovingUnit.getbaseunit().hexlocation <= Constantvalues.Location.Bombprf) Or MovingUnit.getbaseunit().hexlocation = Constantvalues.Location.Cellar Or MovingUnit.getbaseunit().hexlocation = Constantvalues.Location.BuildingGroundlevel }
    if (Hexsidetype >= Constantvalues.Hexside.Trench And Hexsidetype <= Constantvalues.Hexside.TrenchWall) AndAlso MapHex.Entrenchment = Constantvalues.Feature.Trench }
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterConnectingTrench
    menuItemadd.ActivityNames = CreateMenuText("Enter Connecting Trench", SideDesc, COT, SideCost)
                        menuitems.Add(menuItemadd)
    End if
    if MapHex.IsBuilding }
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim MapHexLocs As IQueryable(Of MapDataClassLibrary.GameLocation) = GetLocs.RetrieveLocationsinHex(menuMovehex.hexnum, "Hexnum")
    Dim Newhexsidetype As Integer = 0
    For Each NewMapHex As MapDataClassLibrary.GameLocation In MapHexLocs
            Newhexsidetype = SideTest.Gethexsidetype(NewMapHex, HexSideCrossed)
    if (Newhexsidetype >= Constantvalues.Hexside.Trench And Newhexsidetype <= Constantvalues.Hexside.TrenchWall) }
    Dim NewCOT As Single = NewMapHex.MFCot
    Dim NewSideCost As Single = CSng(SideTest.GetSideData(Constantvalues.TerrFactor.HexsideMFcost, Newhexsidetype, Game.Scenario.Maptables))
    Dim NewSideDesc As String = ""
    if NewSideCost > 0 } NewSideDesc = SideTest.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Newhexsidetype, Game.Scenario.Maptables)
    if NewMapHex.LevelInHex = -1 }
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterConnectingCellar
    menuItemadd.ActivityNames = CreateMenuText("Enter Connecting Cellar", NewSideDesc, NewCOT, NewSideCost)
                                    menuitems.Add(menuItemadd)
    ElseIf NewMapHex.LevelInHex = 0 }
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterConnectingBldg
    menuItemadd.ActivityNames = CreateMenuText("Enter Connecting Ground Level", NewSideDesc, NewCOT, NewSideCost)
                                    menuitems.Add(menuItemadd)
    Else
                                    MessageBox.Show("Data Error!")
    End if
    Exit For
    End if
    Next
            Else
    Dim LocHolder As Integer = 0
    Dim TerrTest As New TerrainClassLibrary.ASLXNA.TerrainChecks(Game.Scenario.LocationCol)
    Dim PillPresent As Boolean = TerrTest.IsLocationPresent(menuMovehex.hexnum, Constantvalues.Location.Pillboxtype, LocHolder)
    if PillPresent }
    if (Hexsidetype >= Constantvalues.Hexside.Trench And Hexsidetype <= Constantvalues.Hexside.TrenchWall) }
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim MapHexLocs As IQueryable(Of MapDataClassLibrary.GameLocation) = GetLocs.RetrieveLocationsinHex(menuMovehex.hexnum, "Hexnum")
    For Each NewMapHex As MapDataClassLibrary.GameLocation In MapHexLocs
    Dim NewCOT As Single = NewMapHex.MFCot
    Dim NewSideCost As Single = 0 'IS THIS CORRECT           CSng(SideTest.GetSideData(constantclasslibrary.aslxna.TerrFactor.HexsideMFcost, Newhexsidetype, Game.Scenario.Maptables))
    Dim NewSideDesc As String = ""
    NewSideDesc = SideTest.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Hexsidetype, Game.Scenario.Maptables)
    if NewMapHex.LevelInHex = 0 }
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterConnectingPill
    menuItemadd.ActivityNames = CreateMenuText("Enter Connecting Pillbox", "", NewCOT, NewSideCost)
                                        menuitems.Add(menuItemadd)
    Else
                                        MessageBox.Show("Data Error!")
    End if
    Exit For
    Next
    End if
    End if
    if Hexsidetype = Constantvalues.Hexside.TrenchCrestDown }
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterViaConnection
    menuItemadd.ActivityNames = CreateMenuText("Enter Via Trench Connection ", "", COT, SideCost)
                            menuitems.Add(menuItemadd)
    End if
    End if
    if Not Terrainrate } AddTerrainRateToMenu(menuitems, Terrainrate, Terrainname, COT, SideDesc, SideCost)
                    'Terrainrate = True
    End if
    if Hexsidetype = Constantvalues.Hexside.TrenchCrestUp AndAlso MapHex.Entrenchment = Constantvalues.Feature.Trench }
    Dim Titlestring As String = "B27.6 Moving into Higher-Level Trench"
    Dim Textstring As String = "In order to move into this higher level trench it must be (a) placed by the moving side; or (b) currently controlled by the moving side." & vbCrLf & vbCrLf & "Does the trench meet these requirements?"
    Dim Entrychoice As DialogResult = MessageBox.Show(Textstring, Titlestring, MessageBoxButtons.YesNo, MessageBoxIcon.Question)
    if Entrychoice = DialogResult.Yes }
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterViaConnection
    menuItemadd.ActivityNames = CreateMenuText("Enter Via Trench Connection ", "", COT, SideCost)
                        menuitems.Add(menuItemadd)
    End if
    End if
                'add WA option
    Dim NoWallA As Boolean = True
    For i = 1 To 6
    Dim Otherhexnum As Integer = MapGeo.DirExtent(menuMovehex.hexnum, i, 1)
    if SideTest.IsWAAllowed(menuMovehex.hexnum, i, Otherhexnum) }
                        'check for TEM=0, therefore WA mandatory, don't need to show as option
    if CInt(MapHex.TEM) <> 0 } NoWallA = False
    Exit For 'some form is WA is allowed so can exit loop
    End if
    Next
    if Not (NoWallA) And Not (MapHex.CrestStatusOK) }  'creststatus and WA together already done so don't allow here
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.EnterWA
    menuItemadd.ActivityNames = CreateMenuText("Claim WA in " & menuMovehex.hexname, SideDesc, COT, SideCost)
                    menuitems.Add(menuItemadd)
    if Not Terrainrate } AddTerrainRateToMenu(menuitems, Terrainrate, Terrainname, COT, SideDesc, SideCost)
    End if
                'add clearance options
    Dim StackCheck = New UtilWObj.ASLXNA.StackStatusC
    if TerrID = Constantvalues.Location.WoodRubbleFallen Or TerrID = Constantvalues.Location.StoneRubbleFallen Or
            TerrID = Constantvalues.Location.WoodRubbleFallTB Or TerrID = Constantvalues.Location.StoneRubbleFallTB }  'clearable rubble
    if MovingUnit.getMovingunit().MFUsed = 0 AndAlso MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.GoodOrder AndAlso
    Not (MovingUnit.getbaseunit().Pinned) AndAlso StackCheck.IsMMCPresent(Scencolls.SelMoveUnits) }
                        'if already moved, or not GoodOrder, or Pinned can't do clearance; must be MMC for rubble clearance
    menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.ClearEnterRubble
    menuItemadd.ActivityNames = CreateMenuText("Clear Rubble: ALL MF & Haz. Move", SideDesc, 99, SideCost)
                        menuitems.Add(menuItemadd)
    if Not Terrainrate } AddTerrainRateToMenu(menuitems, Terrainrate, Terrainname, COT, SideDesc, SideCost)
    End if
    Else
    if MapHex.APMinesVisible Or MapHex.ATMinesVisible }
    if MovingUnit.getMovingunit().MFUsed = 0 AndAlso MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.GoodOrder AndAlso
    Not (MovingUnit.getbaseunit().Pinned) }
                            'if already moved, or not GoodOrder, or Pinned can't do clearance
            menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = (Constantvalues.UMove.ClearEnterMines * 10) + HexSideCrossed
    menuItemadd.ActivityNames = CreateMenuText("Clear Mines: ALL MF", SideDesc, 99, SideCost)
                            menuitems.Add(menuItemadd)
    if Not Terrainrate } AddTerrainRateToMenu(menuitems, Terrainrate, Terrainname, COT, SideDesc, SideCost)
    End if
    End if
    End if
    Else  'ATLevel<>0 meaning above or below ground
            'nothing to do here, no options available, when moving at these levels there is only one move possible so no menu required
    End if
    End Sub


    private Function AddTerrainRateToMenu(ByRef menuitems As List(Of DataClassLibrary.ASLXNA.Objectholder), ByRef TerrainRate As Boolean, ByVal Terrainname As String,
    ByVal COT As Single, ByVal SideDesc As String, ByVal SideCost As Single) As Boolean
            'called by Me.DetermineMenuforHexMove
                    'adds COT to location menu item
    Dim menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
    menuItemadd.Activities = Constantvalues.UMove.TerrainRate
    menuItemadd.ActivityNames = CreateMenuText(Terrainname, SideDesc, COT, SideCost)  ' & " Rate: " & COT.ToString & " MF "
            menuitems.Add(menuItemadd)
    TerrainRate = True
    Return True
    End Function
    private Function CreateMenuText(ByVal Terrainstring As String, ByVal Sidestring As String, ByVal TerrainCost As Single, ByVal SideCost As Single) As String
            'called by Me.DetermineMenuforhexmove
                    'creates text for location popup item
    if Trim(Sidestring) <> "" }
            CreateMenuText = Terrainstring & " & " & Sidestring & ": "
    Else
            CreateMenuText = Terrainstring & ": "
    End if
    Dim Coststring As String = ""
    if SideCost > 0 }
            Coststring = (TerrainCost + SideCost).ToString
    Else
            Coststring = TerrainCost.ToString
    End if
    CreateMenuText &= Coststring & " MF"
    End Function


    public Sub ProcessPopup(controlclick As Integer, HexClicked As Integer, ByVal PassSelection As String) Implements movei.ProcessPopup
            'sent here by controller when it receives popup or button click from observeri
                    'process all possibilities here including from hex entry options
    Dim typehexclick As Integer = 0
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
            'test that there are units to process
    if Scencolls.SelMoveUnits.Count = 0 }  'no units selected
            MessageBox.Show("No infantry units selected", "Invalid Menu Selection")
    Exit Sub
    End if
    if HexClicked = Scencolls.SelMoveUnits.Item(0).getbaseunit().Hexnum } typehexclick = Constantvalues.HexClickedType.WithinHex Else typehexclick = Constantvalues.HexClickedType.NewHex
            'Do special cases first
    Select case controlclick
    case Constantvalues.UMove.DoSearch
    Dim HexSearch As locationclickactionsi = New SearchAHex
                    HexSearch.HandleAction(HexClicked, 0)
    if HexSearch.CasualtiesFlag }
    CasFlag = True
    CasDRM += HexSearch.CasualtiesDRM
    End if
    Exit Sub
    case Constantvalues.UMove.DoPlaceDC
    Dim HexPlace As locationclickactionsi = New PlacingDC()
                    HexPlace.HandleAction(HexClicked, CInt(PassSelection))
    Exit Sub
    case Constantvalues.ContextM.LOS
    ShowLOSResults()
    Exit Sub
    End Select
            'now handle either click in new hex or click within hex
                    'handle special case first
    Select case controlclick
    case Constantvalues.ContextM.DoubleTime, Constantvalues.ContextM.RoadBonus, Constantvalues.ContextM.AssaultMove, Constantvalues.ContextM.Dash
    Dim Leadertest As ObjectChange.ASLXNA.LeaderTesti
            Leadertest = New ObjectChange.ASLXNA.StackLeaderTestC(Scencolls.SelMoveUnits)  'PassStack)
    Dim LeaderInStack As Boolean = Leadertest.IsUnwoundedLeaderPresent()
                    'determine MF modifications (decorators) such as CX, IPC exceeded, road bonus, ldrbonus, encircled
    For Each MovingUnittocheck As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.SelMoveUnits
            MovingUnittocheck = UpdateExistingMovingUnit(MovingUnittocheck, controlclick, LeaderInStack, Scencolls.SelMoveUnits)
    Next
    if Scencolls.SelMoveUnits.Count > 0 }
    Dim CreateMFtoDraw = New DrawMoveInfo(Scencolls.SelMoveUnits.Item(0).getbaseunit().Hexnum, Scencolls.SelMoveUnits.Item(0).getbaseunit().Hexnum, 0, 0)
    End if
                    'No actual movement involved, so exit
    Exit Sub
    End Select
    if typehexclick = Constantvalues.HexClickedType.NewHex }
    MoveToNewHex(CInt(HexClicked), controlclick, PassSelection)
    ElseIf typehexclick = Constantvalues.HexClickedType.WithinHex }
    Select case controlclick
    case Constantvalues.ContextM.ExitObst
            controlclick = Constantvalues.UMove.FeatureExit
    case Constantvalues.ContextM.EnterVehicle  'infantry
    controlclick = Constantvalues.UMove.EnterVehicle
    case Constantvalues.ContextM.ExitVehicle
            controlclick = Constantvalues.UMove.ExitVehicle
    case Constantvalues.ContextM.EnterFoxhole
            controlclick = Constantvalues.UMove.EnterFoxhole
    case Constantvalues.ContextM.ExitFoxhole
            controlclick = Constantvalues.UMove.ExitFoxhole
    case Constantvalues.ContextM.EnterPillbox
            controlclick = Constantvalues.UMove.EnterPillbox
    case Constantvalues.ContextM.ExitPillbox
            controlclick = Constantvalues.UMove.ExitPIllbox
    case Constantvalues.ContextM.EnterTrench
            controlclick = Constantvalues.UMove.EnterTrench
    case Constantvalues.ContextM.ExitTrench
            controlclick = Constantvalues.UMove.ExitTrench
    case Constantvalues.ContextM.BelowWire
            controlclick = Constantvalues.UMove.EnterWire
    case Constantvalues.ContextM.MoveIN
            controlclick = Constantvalues.UMove.INrate
    case Constantvalues.ContextM.UpStairs
            controlclick = Constantvalues.UMove.StairsUp
    case Constantvalues.ContextM.Downstairs
            controlclick = Constantvalues.UMove.StairsDown
    End Select
    MoveWithinHex(HexClicked, controlclick, PassSelection)
    End if

    End Sub

    private Sub ShowLOSResults()
            'called by Processpoup when LOS selected on popup
                    'triggers LOS calculation and display
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
            'Determine the 'seeing' location
    Dim FindUnit As ObjectClassLibrary.ASLXNA.PersUniti = Scencolls.SelMoveUnits.Item(0)
    if FindUnit.getMovingunit().IsConcealed }
                'can't show LOS for concealed unit
                MessageBox.Show("Can't do LOS Check for Concealed Unit as range not known ", "Selected LOS from popup")
    Exit Sub
    End if
            'set parameters for LOS checks
    if IsNothing(XNAGph.DisplayShade) } XNAGph.DisplayShade = MapShade.GetInstance()
    XNAGph.DisplayShade.SelectedUnit = FindUnit
    XNAGph.DisplayShade.StartHex = FindUnit.getbaseunit().Hexnum
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.LOSShade
    Game.ShaderDrawStart = True
            'Do the LOS Checks
                    XNAGph.DisplayShade.LOSShade()
    Game.ShaderDrawStart = False
    if Not (Game.HexesToShade.Count > 0) }
                'get the results and put in draw process
    For Each LOSHexToShade As ShadeHex In XNAGph.DisplayShade.HexesToColor
    if LOSHexToShade.LOSStatus <> 0 }
                        Game.HexesToShade.Add(LOSHexToShade)
    End if
    if Not (LOSHexToShade.LevelClear = Nothing) And Trim(LOSHexToShade.LevelClear) <> "" }
                        Game.StringsToDraw.Add(LOSHexToShade)
    End if
    Next LOSHexToShade
    End if
    End Sub

*/
    public boolean RedoMovementStack(Constantvalues.UMove movementoptionclicked) {
        // called by MoveOK method of each movement class
        // this redecorates the Movepersuniti class: recalculates MF and adjusts for any used to date
        Constantvalues.UMove Controlclick = null;  // used when updating
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        // determine if stack should have ldrbonus
        LeaderTesti Leadertest = new StackLeaderTestC(Scencolls.SelMoveUnits);
        boolean LeaderInStack = Leadertest.IsUnwoundedLeaderPresent();
        // no Ldrbonus for units which mount/dismount/ride
        if (LeaderInStack && (movementoptionclicked == Constantvalues.UMove.EnterVehicle || movementoptionclicked == Constantvalues.UMove.ExitVehicle ||
            movementoptionclicked == Constantvalues.UMove.Riding)) {LeaderInStack = false;}
        // update units in stack
        for (PersUniti MovingUnittocheck: Scencolls.SelMoveUnits) {
            // reset movement values
            MovingUnittocheck.getMovingunit().setUsingRoadBonus(false);
            if (MovingUnittocheck.getMovingunit().getUsingDT()) {
                Controlclick = Constantvalues.UMove.DblTime;
            }
            // update the new units
            MovingUnittocheck = UpdateExistingMovingUnit(MovingUnittocheck, Controlclick, LeaderInStack, Scencolls.SelMoveUnits);
        }
        return true;
    }

    // PROBABLY DON'T NEED THIS METHOD ANYMORE
    /*public boolean MovementUpdate() {
        // called by Game.Update
        // checks to see if need to redraw movingunits and calculates the new positions

        if (MovementFlag) { // need to redraw moving units
            ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
            int hexclicked = hexclickedholder;
            Game.MovingItem.MoveItem(Scencolls.SelMoveUnits, hexclicked);
        }
        return true;
    }*/
/*
    public Sub StartMovementDraw(ByVal hexclicked As Integer) Implements movei.StartMovementDraw
            'toogles flag on and off
                    'flag used in MovementUpdate
    MovementFlag = Not MovementFlag
    hexclickedholder = hexclicked
    End Sub
        'overload to handle search and PlaceDC shade display - plus others
    public Sub StartMovementDraw(ByVal hexclicked As Integer, ByVal movementoptionclicked As Integer, ByVal HexesToSearch As Integer) Implements movei.StartMovementDraw
            'called by NotifyMoveObservers
                    'sets game variables to trigger a shade to be shown and calls method to set the shade details
    hexclickedholder = hexclicked
    Dim XNAGph = XNAGraphicsC.GetInstance
    Select case movementoptionclicked
    case Constantvalues.ContextM.Search
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.SearchShade
                    'Game.ShaderStart = False
    Game.ShaderDrawStart = True
                    XNAGph.DisplayShade.SearchShadeShow()
    Game.ShaderDrawStart = False
    case Constantvalues.ContextM.PlaceDC
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.PlaceDCShade
                    'Game.ShaderStart = False
    Game.ShaderDrawStart = True
                    XNAGph.DisplayShade.PlaceDCShadeShow()
    Game.ShaderDrawStart = False
    End Select
    MovementFlag = Not MovementFlag
    End Sub
    Friend Sub StopMovementDraw() Implements movei.StopMovementDraw
            'toogles flag on and off
                    'flag used in MovementUpdate
    MovementFlag = Not MovementFlag
    hexclickedholder = 0
    MoveUpdate()
    End Sub

    public Function StatusPrevents(ActionToTest As Integer) As Boolean 'Implements movei.StatusPrevents
            'called by ContextBuilder.MenuFilter
            'tests is status of moving units prevents other actions from being possible
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim TypeTest As Integer 'Dim UnitCheck As ObjectClassLibrary.ASLXNA.PersUniti : Dim ConCheck As DataClassLibrary.Concealment 'Dim NoGoodOrder As Boolean = True
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(Game.Scenario.LocationCol)
            'not all coded, look for other possibilities FEB 12
    if IsNothing(Scencolls.SelMoveUnits) Or Scencolls.SelMoveUnits.Count = 0 } Return False
    For Each MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.SelMoveUnits
    if MovingUnit.getMovingunit().IsDummy } TypeTest = Constantvalues.Typetype.Concealment Else TypeTest = Constantvalues.Typetype.Personnel
    if Game.Linqdata.IsUnitAPassOrRider(MovingUnit.getbaseunit().Unit_ID, TypeTest) And
    ActionToTest <> Constantvalues.ContextM.ExitVehicle } Return True
    Select case ActionToTest
    case Constantvalues.ContextM.AssaultMove, Constantvalues.UMove.Assault
    if MovingUnit.getMovingunit().UsingDT Or MovingUnit.getMovingunit().AssaultMove = Constantvalues.MovementStatus.AssaultMoved Or
    MovingUnit.getMovingunit().AssaultMove = Constantvalues.MovementStatus.AssaultMoving } Return True
    case Constantvalues.ContextM.DoubleTime, Constantvalues.UMove.DblTime, Constantvalues.UMove.DbT_RB
    if MovingUnit.getMovingunit().UsingDT Or MovingUnit.getMovingunit().AssaultMove = Constantvalues.MovementStatus.AssaultMoved Or
    MovingUnit.getMovingunit().AssaultMove = Constantvalues.MovementStatus.AssaultMoving } Return True
    case Constantvalues.ContextM.EnterVehicle
    if MovingUnit.getMovingunit().UsingDT Or MovingUnit.getMovingunit().AssaultMove = Constantvalues.MovementStatus.AssaultMoving Or
    MovingUnit.getMovingunit().Dash = Constantvalues.MovementStatus.FirstDash Or MovingUnit.getMovingunit().Dash = Constantvalues.MovementStatus.SecondDash } Return True
    case Constantvalues.ContextM.ExitVehicle
    if Not Game.Linqdata.IsUnitAPassOrRider(MovingUnit.getbaseunit().Unit_ID, TypeTest) } Return True 'not a pass or rider
    case Constantvalues.UMove.ClearRubble, Constantvalues.UMove.ClearSetDC, Constantvalues.UMove.ClearWire, Constantvalues.UMove.ClearRdBlk, Constantvalues.UMove.ClearFlame,
    Constantvalues.UMove.ClearEnterRubble0 To Constantvalues.UMove.ClearEnterRubble5
    if MovingUnit.getMovingunit().MFUsed > 0 } Return True 'if already moved can't do clearance
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
                                    'if Not UnitCheck.OrderStatus = Constantvalues.OrderStatus.GoodOrder Then Return True 'unit is not GoodOrder
                            'if UnitCheck.Pinned Then Return True 'unit is pinned
    if Not MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.GoodOrder } Return True 'unit is not Good Order
    if MovingUnit.getbaseunit().Pinned } Return True 'unit is pinned
    End if
    case Constantvalues.UMove.ClearMine, Constantvalues.UMove.ClearRoadATMine, Constantvalues.UMove.ClearEnterMines, Constantvalues.UMove.ClearEnterMines0 To Constantvalues.UMove.ClearEnterMines5
    if ActionToTest <> Constantvalues.UMove.ClearRoadATMine And MovingUnit.getMovingunit().MFUsed > 0 } Return True 'if already moved can't do clearance
    if TypeTest = Constantvalues.Typetype.Personnel }
    if Not MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.GoodOrder } Return True 'unit is not Good Order
    if MovingUnit.getbaseunit().Pinned } Return True 'unit is pinned
    if ActionToTest = Constantvalues.UMove.ClearEnterMines Or (ActionToTest >= Constantvalues.UMove.ClearEnterMines0 And ActionToTest <= Constantvalues.UMove.ClearEnterMines5) }
    if MovingUnit.getMovingunit().MFUsed > 0 } Return True 'enter and clear requires all MF
    End if
    End if
    case Constantvalues.UMove.ClearRoadATMine
    if TypeTest = Constantvalues.Typetype.Personnel }
    if Not MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.GoodOrder } Return True 'unit is not Good Order
    if MovingUnit.getbaseunit().Pinned } Return True 'unit is pinned
    if MovingUnit.getMovingunit().MFAvailable = 0 } Return True 'requires 1 MF
    End if
    case Constantvalues.ContextM.HumanWave
                        'Only Russians and Chinese can Human Wave
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if Not (MovingUnit.getbaseunit().Nationality = Constantvalues.Nationality.Russians Or MovingUnit.getbaseunit().Nationality = Constantvalues.Nationality.Chinese) Then Return True
    Else
    Return True 'concealed unit cannot be part of Human Wave - TRUE?
            'ConCheck = Game.Linqdata.GetConcealmentfromCol(MovingUnit.getbaseunit().Unit_ID)
            'if Not (ConCheck.Nationality = Constantvalues.Nationality.Russians Or ConCheck.Nationality = Constantvalues.Nationality.Chinese) } Return True
    End if
    case Constantvalues.ContextM.Banzai, Constantvalues.ContextM.CreateTHHero
                        'only Japanese can Banzai or Create Tank Hunter Hero
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if Not (MovingUnit.getbaseunit().Nationality = Constantvalues.Nationality.Japanese) } Return True
    Else
    Return True 'concealed unit cannot be part of Banzai charge, or create THH - TRUE?
            'ConCheck = Game.Linqdata.GetConcealmentfromCol(MovingUnit.getbaseunit().Unit_ID)
            'if Not (ConCheck.Nationality = Constantvalues.Nationality.Japanese) Then Return True
    End if
    case Constantvalues.ContextM.BerserktoGO
                        'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if Not MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.Berserk } Return True 'unit is not berserk
    case Constantvalues.ContextM.ClaimWallAdv
                        'can't claim if already has
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.WallAdv Or (MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.WACrestStatus0 And MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.WACrestStatus5) } Return True
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if MovingUnit.getbaseunit().OrderStatus >= Constantvalues.OrderStatus.Broken And MovingUnit.getbaseunit().OrderStatus <= Constantvalues.OrderStatus.DisruptedDM } Return True 'unit is broken
    if IsTerrChk.IsLocationTerrainA(CInt(MovingUnit.getbaseunit().Hexnum), CInt(MovingUnit.getbaseunit().hexlocation), Constantvalues.Location.FortBuildtype) }
                                'need to check for unit being gun crew - can't do yet as Guns not programmed
                                'Return true as default
    Return True  'unit is crewing gun in fortified building
    End if
                            'not on ground level, unless also in creststatus
    if MovingUnit.getbaseunit().LevelinHex <> 0 And Not (MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.CrestStatus0 And
            MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.CrestStatus5) } Return True
                            'Pinned, TI prevent
    if MovingUnit.getbaseunit().Pinned Or MovingUnit.getbaseunit().MovementStatus = Constantvalues.MovementStatus.TI } Return True
                            'In Pillbox, entrenchment, above wire/panji prevent
    if MovingUnit.getbaseunit().hexlocation = Constantvalues.Feature.Foxhole Or (MovingUnit.getbaseunit().hexlocation >= Constantvalues.Location.Pill1571 And MovingUnit.getbaseunit().hexlocation <= Constantvalues.Location.Bombprf) Or MovingUnit.getbaseunit().hexlocation = Constantvalues.Location.BunkUnder Or MovingUnit.getbaseunit().hexlocation = Constantvalues.Feature.Trench Or
    MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.AbovePanji Or MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.AboveWire }
    Return True
    End if
                            'non-prisoner enemy present in location
    Dim CheckforEnemy = New UtilWObj.ASLXNA.EnemyChecksC(CInt(MovingUnit.getbaseunit().LOCIndex), CInt(MovingUnit.getbaseunit().Nationality), Game.Scenario.ScenID)
    if CheckforEnemy.EnemyinLocationTest } Return True
    Else
                            'ConCheck = Game.Linqdata.GetConcealmentfromCol(MovingUnit.getbaseunit().Unit_ID)
                                    'if ConCheck.Position = constantclasslibrary.aslxna.AltPos.WallAdv Then Return True
    if MovingUnit.getbaseunit().LevelinHex <> 0 And Not (MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.CrestStatus0 And
            MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.CrestStatus5) } Return True
    End if
    if MovingUnit.getbaseunit().hexlocation = Constantvalues.Feature.Foxhole Or MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.AboveWire Or
    MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.InFoxhole Or MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.AbovePanji Or
    MovingUnit.getbaseunit().hexlocation = Constantvalues.Feature.Trench Or MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.InTrench } Return True
    if (MovingUnit.getbaseunit().hexlocation >= Constantvalues.Location.StBr14 And MovingUnit.getbaseunit().hexlocation <= Constantvalues.Location.FoBr) }
    For i = 1 To 6
    Dim Sidetest As Integer = Game.Linqdata.Gethexsidetype(i, CInt(MovingUnit.getbaseunit().Hexnum))
    if Not (Sidetest >= Constantvalues.Hexside.Roadblock0 And Sidetest <= Constantvalues.Hexside.Roadblock6) } 'no roadblock
    Return True  'unit is on bridge with no roadblock side
    End if
    Next
    End if
                        'check if creststatus
    if MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.CrestStatus0 And MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.CrestStatus5 }
                            'now need to check that Wall/Hedge exists within crest hexside - not other side of depression
    Dim SideConvert As New UtilClassLibrary.ASLXNA.ConversionC
    Dim crestside As Integer = SideConvert.CrestSideToSide(MovingUnit.getbaseunit().hexPosition)
    Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
    Dim x As Integer = -1
    Do
    Dim testside As Integer = crestside + x
    if testside = 0 } testside = 6
    if testside = 7 } testside = 1
    if SideTest.IsAWallHedgeRdBlk(testside, CInt(MovingUnit.getbaseunit().LOCIndex)) } Return False
    x += 1
    Loop Until x = 2
    Return True
    ElseIf IsTerrChk.IsLocationTerrainA(MovingUnit.getbaseunit().Hexnum, MovingUnit.getbaseunit().hexlocation, Constantvalues.Location.Creststatustype) }
                            'status prevents because requires creststatus and unit is not in creststatus
    Return True
    End if
    Return False  'if here, nothing prevents
    case Constantvalues.ContextM.ForfeitWA
                        'can't forfeit unless already has
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.WallAdv Or (MovingUnit.getbaseunit().hexPosition >= Constantvalues.AltPos.WACrestStatus0 And
            MovingUnit.getbaseunit().hexPosition <= Constantvalues.AltPos.WACrestStatus5) } Return False Else Return True
    case Constantvalues.ContextM.EnterFoxhole
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.InFoxhole } Return True Else Return False
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if MovingUnit.getbaseunit().Pinned Or MovingUnit.getbaseunit().MovementStatus = Constantvalues.MovementStatus.TI } Return True
    End if
    case Constantvalues.ContextM.EnterTrench
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.InTrench } Return True Else Return False
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if MovingUnit.getbaseunit().Pinned Or MovingUnit.getbaseunit().MovementStatus = Constantvalues.MovementStatus.TI } Return True
    End if
    case Constantvalues.ContextM.BelowWire
                        'if TypeTest = Constantvalues.Typetype.Personnel Then
                                'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.AboveWire } Return False Else Return True
    if MovingUnit.getbaseunit().Pinned Or MovingUnit.getbaseunit().MovementStatus = Constantvalues.MovementStatus.TI } Return True
                        'Else
                                'ConCheck = Game.Linqdata.GetConcealmentfromCol(MovingUnit.getbaseunit().Unit_ID)
                                'if ConCheck.Position = Constantvalues.AltPos.AboveWire Then Return False Else Return True
                                'End if
    case Constantvalues.ContextM.ExitPillbox, Constantvalues.ContextM.ExitFoxhole, Constantvalues.ContextM.ExitTrench
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if MovingUnit.getbaseunit().Pinned Or MovingUnit.getbaseunit().MovementStatus = Constantvalues.MovementStatus.TI } Return True
    End if
    case Constantvalues.ContextM.Search
                        'NoGoodOrder = False
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if Not MovingUnit.getbaseunit().IsUnitASMC }
    if MovingUnit.getbaseunit().OrderStatus = Constantvalues.OrderStatus.GoodOrder } Return False
    End if
    End if
    case Constantvalues.ContextM.PlaceDC
    if TypeTest = Constantvalues.Typetype.Personnel }
                            'UnitCheck = Game.Linqdata.GetUnitfromCol(MovingUnit.getbaseunit().Unit_ID)
    if MovingUnit.getbaseunit().Pinned Or MovingUnit.getbaseunit().MovementStatus = Constantvalues.MovementStatus.TI Or MovingUnit.getbaseunit().OrderStatus <>
    Constantvalues.OrderStatus.GoodOrder } Return True
    if MovingUnit.getbaseunit().hexPosition = Constantvalues.AltPos.AboveWire } Return True
    End if
    End Select
    Next
            'if NoGoodOrder Then Return False Else
    Return False
    End Function
*/
    public boolean ProcessValidation(Hex hexclickedvalue, Location locationchange, Constantvalues.UMove movementoptionclickedvalue) {
        //Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
        //Dim HexTest As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromHex(hexclickedvalue, locationchange)
        MovementValidationc MoveValid = new MovementValidationc(locationchange, movementoptionclickedvalue);
        if (!MoveValid.OK()) {
            // movement is blocked, exit move
            // MessageBox.Show("Enemy Units prevent entry of " & GetLocs.GetnamefromdatatableMap(hexclickedvalue))
            // NEED TO REPLACE CODE TO (A) RETURN MOVING UNIT TO PREVIOUS HEX/LOCATION AND (B) SHOW REVEALED UNITS OCT 19
            // MovementValidationC may cause hidden or concealed units to be revealed in hex being entered so need to redraw hex contents
            // if move ok then MoveUpdate will handle redraw in exiting and entering hexes
            //VisibleOccupiedhexes OH = CType(Game.Scenario.HexesWithCounter(hexclickedvalue), VisibleOccupiedhexes)
            //if (OH == null) {
            //    Game.Scenario.HexesWithCounter.Add(hexclickedvalue, new VisibleOccupiedhexes(hexclickedvalue));
            //    OH = CType(Game.Scenario.HexesWithCounter(hexclickedvalue), VisibleOccupiedhexes)
            //}
            //OH.GetAllSpritesInHex()
            //OH.RedoDisplayOrder()
            return false;
        }
        // if here move ok
        return true;
    }
    public boolean Recalculating(Constantvalues.UMove movementoptionclickedvalue, Hex hexclickedvalue, double MFCost, Locationi Moveloc, Hex Currenthex) {
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

        Double HoldMFUsed = 0.0; boolean Recalculate;
        IsSide SideTest = new IsSide();
        do {
            Recalculate = false;
            for (PersUniti MovingUnit: Scencolls.SelMoveUnits) {
                // Now do road bonus check
                if (MovingUnit.getMovingunit().getUsingRoadBonus()) {
                    int HexSideCrossed = getHexSideEntry(Currenthex, hexclickedvalue);
                    if (!SideTest.IsARoad(HexSideCrossed, hexclickedvalue)) {
                        //MessageBox.Show("No longer moving on road. -1 MF ", "Forfeiting Road Bonus")
                        HoldMFUsed = MovingUnit.getMovingunit().getMFUsed();
                        Recalculate = true;
                        break;
                    }
                }
                if (MFCost > MovingUnit.getMovingunit().getMFAvailable()) {
                    // move is not affordable, exit move
                    // MessageBox.Show(Trim(MovingUnit.getbaseunit().UnitName) & " does not have enough MF to enter " & GetLocs.GetnamefromdatatableMap(hexclickedvalue))
                    Moveloc = null;
                    return false;
                } else if (MFCost == MovingUnit.getMovingunit().getMFAvailable() && (movementoptionclickedvalue == Constantvalues.UMove.bypassrate ||
                        movementoptionclickedvalue == Constantvalues.UMove.extrabypassrate)) {
                    //move would end in bypass so is not affordable, exit move
                    // MessageBox.Show(Trim(MovingUnit.getbaseunit().UnitName) & " cannot end move in bypass so cannot enter " & GetLocs.GetnamefromdatatableMap(hexclickedvalue))
                    Moveloc = null;
                    return false;
                } else if (MFCost == MovingUnit.getMovingunit().getMFAvailable() && (MovingUnit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoving ||
                        MovingUnit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoved)) {
                    // unit cannot use all its MF when assault moving
                    // MessageBox.Show(Trim(MovingUnit.getbaseunit().UnitName) & " is assault moving and cannot enter " & GetLocs.GetnamefromdatatableMap(hexclickedvalue) & " as it requires all its remaining MF to enter ")
                    Moveloc = null;
                    return false;
                }
            }
            if (Recalculate) {
                // this resets the decorator process to create a new Stack with revised MF and adjusts for movement so far
                scen.DoMove.ConcreteMove.RedoMovementStack(movementoptionclickedvalue);
            }
        } while (Recalculate == true);
        // if here then move is affordable
        return true;
    }

    public Locationi Decorating(Locationi moveloc, Constantvalues.UMove movementoptionclickedvalue, Location locationchange, Constantvalues.AltPos Positionchange,
                                boolean CurrentPosIsExitedCrest, Hex Currenthex) {
        ConversionC confrom = new ConversionC();
        moveloc = new HexsideImpactc(moveloc, movementoptionclickedvalue);
        moveloc = new ScenTerImpactc(moveloc, movementoptionclickedvalue);
        moveloc = new WeatherImpactc(moveloc, movementoptionclickedvalue);
        if (movementoptionclickedvalue == Constantvalues.UMove.OTRate) {
            moveloc = new UsingOTImpactc(moveloc, movementoptionclickedvalue);
            locationchange = moveloc.getvasllocation();
        } else if (movementoptionclickedvalue == Constantvalues.UMove.BeneathBridge) {
            moveloc = new UsingOTImpactc(moveloc, movementoptionclickedvalue);
            Positionchange = Constantvalues.AltPos.OtherTerrainInHex;
            locationchange = moveloc.getvaslhex().getCenterLocation(); //Constantvalues.Location.BeneathBridge;
        } else if (movementoptionclickedvalue == Constantvalues.UMove.Roadrate) {
            moveloc = new UsingOTImpactc(moveloc, movementoptionclickedvalue);
            locationchange = moveloc.getvasllocation();
        } else if (movementoptionclickedvalue == Constantvalues.UMove.creststatusrate) {
            moveloc = new EnterInCrestStatusc(moveloc, movementoptionclickedvalue);
            int HexSideCrossed = getHexSideEntry(Currenthex, moveloc.getvaslhex());
            Positionchange = SetCrestStatusPosition(HexSideCrossed);
            locationchange = moveloc.getvasllocation();
        } else if (movementoptionclickedvalue == Constantvalues.UMove.bypassrate ||
                movementoptionclickedvalue == Constantvalues.UMove.extrabypassrate) {
            moveloc = new BypassImpactc(moveloc, movementoptionclickedvalue);
            Positionchange = Constantvalues.AltPos.OtherTerrainInHex;
            locationchange = moveloc.getvasllocation();
        } else if (movementoptionclickedvalue == Constantvalues.UMove.EnterConnectingTrench) {
            Positionchange = Constantvalues.AltPos.InTrench;  // only possibility
        } else if (movementoptionclickedvalue == Constantvalues.UMove.EnterViaConnection) {
            int HexSideCrossed = getHexSideEntry(Currenthex, moveloc.getvaslhex());
            Constantvalues.Hexside hexsidetype = confrom.ConverttoHexside(moveloc.getvaslhex().getHexsideTerrain(HexSideCrossed));
            if (hexsidetype == Constantvalues.Hexside.TrenchCrestUp) {
                Positionchange = Constantvalues.AltPos.InTrench;
            }
        }
        // if CurrentPosIsExitedCrest then unit is not at the Depression level and so is not moving uphill when leaving the hex
        moveloc = new MoveUphillc(moveloc, CurrentPosIsExitedCrest);
        return moveloc;
    }

    private Constantvalues.AltPos SetCrestStatusPosition(int HexSideCrossed) {
        ConversionC SideConvert = new ConversionC();
        return SideConvert.SideToCrest(HexSideCrossed);
    }

    public PersUniti UpdateAfterEnter(PersUniti MovingUnit, double MFCost, Hex hexclickedvalue, int hexenteredsidecrossed, Location locationchange,
                                     Constantvalues.AltPos Positionchange) {
        SuppWeapi TempSw;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        //GetALocationFromMap Getlocs = new GetALocationFromMap(Game.Scenario.LocationCol);
        MovingUnit.getMovingunit().setMFAvailable(MovingUnit.getMovingunit().getMFAvailable() - MFCost);
        MovingUnit.getMovingunit().setMFUsed(MovingUnit.getMovingunit().getMFUsed() + MFCost);
        MovingUnit.getbaseunit().setHex(hexclickedvalue);
        MovingUnit.getbaseunit().setHexname(MovingUnit.getbaseunit().getHex().getName());
        MovingUnit.getMovingunit().setHexEnteredSideCrossed(hexenteredsidecrossed) ;
        if (locationchange != null) {MovingUnit.getbaseunit().sethexlocation(locationchange);}
        MovingUnit.getbaseunit().sethexPosition(Positionchange);
        //Dim NewLoc = Getlocs.RetrieveLocationfromHex(MovingUnit.getbaseunit().Hexnum, MovingUnit.getbaseunit().hexlocation)
        //MovingUnit.getbaseunit().LOCIndex = NewLoc.LocIndex
        MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.Moving);
        if (MovingUnit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoving) {
            MovingUnit.getMovingunit().setAssaultMove(Constantvalues.MovementStatus.AssaultMoved);
            MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.AssaultMoved);
        }
        if (MovingUnit.getMovingunit().getDash() == Constantvalues.MovementStatus.FirstDash) {
            MovingUnit.getMovingunit().setDash(Constantvalues.MovementStatus.SecondDash);
        } else if (MovingUnit.getMovingunit().getDash() == Constantvalues.MovementStatus.SecondDash) {
            MovingUnit.getMovingunit().setDash(Constantvalues.MovementStatus.Dashed);
        }
        if (MovingUnit.getbaseunit().getFirstSWLink() > 0) {
            for (SuppWeapi testforsw : Scencolls.SWCol) {
                if (testforsw.getbaseSW().getSW_ID() == MovingUnit.getbaseunit().getFirstSWLink()) {
                    TempSw = testforsw;
                    TempSw.getbaseSW().setHex(MovingUnit.getbaseunit().getHex());
                    TempSw.getbaseSW().sethexlocation(MovingUnit.getbaseunit().gethexlocation());
                    TempSw.getbaseSW().sethexposition(MovingUnit.getbaseunit().gethexPosition());
                    TempSw.getbaseSW().setHexEntSideCrossed(MovingUnit.getbaseunit().getHexEntSideCrossed());
                    //TempSw.getbaseSW().LOCIndex = MovingUnit.getbaseunit().LOCIndex
                }
                break;
            }
        }
        if (MovingUnit.getbaseunit().getSecondSWLink() > 0) {
            for (SuppWeapi testforsw : Scencolls.SWCol) {
                if (testforsw.getbaseSW().getSW_ID() == MovingUnit.getbaseunit().getSecondSWLink()) {
                    TempSw = testforsw;
                    TempSw.getbaseSW().setHex(MovingUnit.getbaseunit().getHex());
                    TempSw.getbaseSW().sethexlocation(MovingUnit.getbaseunit().gethexlocation());
                    TempSw.getbaseSW().sethexposition(MovingUnit.getbaseunit().gethexPosition());
                    TempSw.getbaseSW().setHexEntSideCrossed(MovingUnit.getbaseunit().getHexEntSideCrossed());
                    //TempSw.getbaseSW().LOCIndex = MovingUnit.getbaseunit().LOCIndex
                }
                break;
            }
        }
        return MovingUnit;
    }

    public PersUniti UpdateAfterWithin (PersUniti MovingUnit, double MFCost, Hex hexclickedvalue, int hexenteredsidecrossed, Location locationchange,
                                      Constantvalues.AltPos Positionchange) {
        SuppWeapi TempSw;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        //GetALocationFromMap Getlocs = new GetALocationFromMap(Game.Scenario.LocationCol);
        MovingUnit.getMovingunit().setMFAvailable(MovingUnit.getMovingunit().getMFAvailable() - MFCost);
        MovingUnit.getMovingunit().setMFUsed(MovingUnit.getMovingunit().getMFAvailable() + MFCost);
        MovingUnit.getbaseunit().setHex(hexclickedvalue);
        MovingUnit.getMovingunit().setHexEnteredSideCrossed(hexenteredsidecrossed) ;
        if (locationchange != null) {MovingUnit.getbaseunit().sethexlocation(locationchange);}
        MovingUnit.getbaseunit().sethexPosition(Positionchange);
        //Dim NewLoc = Getlocs.RetrieveLocationfromHex(MovingUnit.getbaseunit().Hexnum, MovingUnit.getbaseunit().hexlocation)
        //MovingUnit.getbaseunit().LOCIndex = NewLoc.LocIndex
        MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.Moving);
        if (MovingUnit.getMovingunit().getAssaultMove() == Constantvalues.MovementStatus.AssaultMoving) {
            MovingUnit.getMovingunit().setAssaultMove(Constantvalues.MovementStatus.AssaultMoved);
            MovingUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.AssaultMoved);
        }
        if (MovingUnit.getbaseunit().getFirstSWLink() > 0) {
            for (SuppWeapi testforsw : Scencolls.SWCol) {
                if (testforsw.getbaseSW().getSW_ID() == MovingUnit.getbaseunit().getFirstSWLink()) {
                    TempSw = testforsw;
                    TempSw.getbaseSW().setHex(MovingUnit.getbaseunit().getHex());
                    TempSw.getbaseSW().sethexlocation(MovingUnit.getbaseunit().gethexlocation());
                    TempSw.getbaseSW().sethexposition(MovingUnit.getbaseunit().gethexPosition());
                    TempSw.getbaseSW().setHexEntSideCrossed(MovingUnit.getbaseunit().getHexEntSideCrossed());
                    //TempSw.getbaseSW().LOCIndex = MovingUnit.getbaseunit().LOCIndex
                }
                break;
            }
        }
        if (MovingUnit.getbaseunit().getSecondSWLink() > 0) {
            for (SuppWeapi testforsw : Scencolls.SWCol) {
                if (testforsw.getbaseSW().getSW_ID() == MovingUnit.getbaseunit().getSecondSWLink()) {
                    TempSw = testforsw;
                    TempSw.getbaseSW().setHex(MovingUnit.getbaseunit().getHex());
                    TempSw.getbaseSW().sethexlocation(MovingUnit.getbaseunit().gethexlocation());
                    TempSw.getbaseSW().sethexposition(MovingUnit.getbaseunit().gethexPosition());
                    TempSw.getbaseSW().setHexEntSideCrossed(MovingUnit.getbaseunit().getHexEntSideCrossed());
                    //TempSw.getbaseSW().LOCIndex = MovingUnit.getbaseunit().LOCIndex
                }
                break;
            }
        }
        return MovingUnit;
    }

    public boolean CheckConcealmentLoss(PersUniti Movingunit, LinkedList<PersUniti> RemoveConUnit, ArrayList<Integer> RemoveCon, String Conlost, String conlosthex,
                                        String Conrevealed, Locationi LoctoUse) {
        // concealment loss check
        VisibilityChangei UnittoChange;
        ConcealmentLossi ConLossCheck = new ConcealmentLossc(scen.getScenID());
        if (ConLossCheck.IsLost(Movingunit, Constantvalues.MovementStatus.NotMoving)) {
            if (Movingunit.getbaseunit().getTypeType_ID() == Constantvalues.Typetype.Concealment) {
                // concealment counter is revealed (removed)
                RemoveCon.add(Movingunit.getbaseunit().getUnit_ID());  // put in list to remove from collection
                RemoveConUnit.add(Movingunit); // put in list to remove from stack
                // process the removals after MovingUnit loop to ensure integrity of lists
                Conlost += " " + Movingunit.getbaseunit().getUnitName();
                conlosthex = LoctoUse.getvaslhex().getName();
            } else if (Movingunit.getMovingunit().IsDummy()) {
                // Dummy unit is removed
                UnittoChange = new RevealDummyc(Movingunit);
                UnittoChange.TakeAction();
                RemoveConUnit.add(Movingunit); // put in list to remove from stack
                Conrevealed += " " + UnittoChange.getActionResult();
            } else {
                // infantry unit is revealed
                if (Movingunit.getMovingunit().getIsConcealed()) { // some moving units may be already known; don' t reveal
                    Movingunit.getMovingunit().setIsConcealed(false);
                    UnittoChange = new RevealUnitC(Movingunit);
                    // also reveals SW possessed by unit
                    AutoDM DMCHeck = new AutoDM(Movingunit); // newly revealed unit can DM adjacent enemy
                    UnittoChange.TakeAction();
                    Conrevealed += " " + UnittoChange.getActionResult();
                }
            }
            return true;
        }
        return false; // if here then problem
    }

    public boolean RemoveRevealedConandDummy(ArrayList<Integer> RemoveCon, LinkedList<PersUniti> removeconunit, String Constring) {

        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        VisibilityChangei UnittoChange;
        if (RemoveCon.size() >0) {
            //MessageBox.Show(Trim(Constring)); // ConLost) & ": Concealment Lost - revealed as " & Trim(ConRevealed) & " in " & Trim(ConLostHex))
            for (int ConRemove : RemoveCon) {
                // delete unit from Concealment - since only concealed unit is now revealed
                UnittoChange = new ElimConcealC(ConRemove);
                UnittoChange.TakeAction();
            }
        }
        // remove revealed dummies
        for (PersUniti RemoveItem: removeconunit) {
            Scencolls.SelMoveUnits.remove(RemoveItem);
            Scencolls.Unitcol.remove(RemoveItem);
        }
        return true;
    }

    public boolean WACleanUp(Hex oldhex, boolean WALoss, Constantvalues.Nationality MovingNationality, boolean EnterToggle) {
        // next items need to be done after database updated due to use of ContentsofLocation class
        // check for WA changes-loss by broken/unarmed in start hex and transfer to adjacent enemy

        if (WALoss) { // at least one exiting unit had WA
            WallAdvantageLossChecki WallAdvLossCheck;
            if (EnterToggle) {
                WallAdvLossCheck = new EnterWALossCheckc();
            } else {
                WallAdvLossCheck = new WithinWALossCheckc(oldhex);
            }
            if (!WallAdvLossCheck.WallAdvantageLossCheck(oldhex)) {
                WALoss = false;
            }
        }
        if (WALoss) {  // additional units losing WA so redraw
                // replace this draw code with a counter action
                /*OH = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
                if (OH != null) {
                    OH.GetAllSpritesInHex();
                    OH.RedoDisplayOrder();
                }*/
                // all units in Oldhex have lost WA and so need to check for switch
                // 'see if other units gain WA in adjacent hexes
                SwitchWallAdvantagec SwitchWA = new SwitchWallAdvantagec(oldhex, MovingNationality);
                SwitchWA.Switch();
        }
        return true;
    }
    public int getHexSideEntry(Hex currenthex, Hex newhex) {
        // returns the hexside by which newhex is entered

        // convert hexsides to lines and check for intersection
        double sourceX = currenthex.getHexCenter().getX();
        double sourceY = currenthex.getHexCenter().getY();
        double targetX = newhex.getHexCenter().getX();
        double targetY = newhex.getHexCenter().getY();
        Line2D.Double losLine = new Line2D.Double(sourceX, sourceY, targetX, targetY);

        // This algorithm courtesy of http://stackoverflow.com/questions/5184815/java-intersection-point-of-a-polygon-and-line

        try {
            final PathIterator polyIt = newhex.getExtendedHexBorder().getPathIterator(null); //Getting an iterator along the polygon path
            final double[] coords = new double[6];      // Double array with length 6 needed by iterator
            final double[] firstCoords = new double[2]; // First point (needed for closing polygon path)
            final double[] lastCoords = new double[2];  // Previously visited point
            polyIt.currentSegment(firstCoords);         // Getting the first coordinate pair
            lastCoords[0] = firstCoords[0];             // Priming the previous coordinate pair
            lastCoords[1] = firstCoords[1];
            polyIt.next();

            HashSet<Integer> hexsides = new HashSet<Integer>();
            int hexside = 0;
            while(!polyIt.isDone()) {
                final int type = polyIt.currentSegment(coords);
                switch(type) {
                    case PathIterator.SEG_LINETO : {
                        final Line2D.Double currentLine = new Line2D.Double(lastCoords[0], lastCoords[1], coords[0], coords[1]);
                        if(currentLine.intersectsLine(losLine)) {
                            //hexsides.add(hexside);
                            return hexside;
                        }
                        lastCoords[0] = coords[0];
                        lastCoords[1] = coords[1];
                        break;
                    }
                    case PathIterator.SEG_CLOSE : {
                        /*final Line2D.Double currentLine = new Line2D.Double(coords[0], coords[1], firstCoords[0], firstCoords[1]);
                        if(currentLine.intersectsLine(losLine)) {
                            hexsides.add(hexside);

                        }
                        break;*/
                    }
                    default : {
                        return 0;
                    }
                }
                polyIt.next();
                hexside++;
            }
            return hexside;
        }
        catch (Exception e){
            return 0;
        }
    }
}
