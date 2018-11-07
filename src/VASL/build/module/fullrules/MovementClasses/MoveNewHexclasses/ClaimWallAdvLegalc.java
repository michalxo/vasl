package VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.IsTerrain;
import VASL.build.module.fullrules.TerrainClasses.TerrainChecks;
import VASL.build.module.fullrules.UtilityClasses.*;

import java.util.LinkedList;

public class ClaimWallAdvLegalc implements LegalMovei {

    // determines if can legally move to new hex (or must exit obstacle, creststatus first, etc)
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private int currenthexvalue;
    private String returnresultsvalue;
    private boolean usemoving;
    private Constantvalues.Nationality initialnationality;
    private Constantvalues.AltPos positionvalue;
    // Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc =ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    private ScenarioC scen = ScenarioC.getInstance();
    InfantryUnitCommonFunctionsc infcommfunc = new InfantryUnitCommonFunctionsc();

    // constructor
    public ClaimWallAdvLegalc(Hex passhexclicked, Location passhexlocationclicked, Constantvalues.Nationality InitialNat, Constantvalues.AltPos PassPosition)  {
        hexclickedvalue = passhexclicked;
        //MovementOptionvalue = passmovementoption;
        usemoving = false;
        locationchangevalue = passhexlocationclicked;
        initialnationality = InitialNat;
        positionvalue = PassPosition;
        returnresultsvalue = "";

    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // Test if units in adjacent hexes have Wall Adv and so block gain by moving unit in starthex AND
        // test if location/position in hex prevents (ie above wire) - this is done already in context popup so never get here but needed
        // for ManWA checking
        // does NOT change WA; returns bool value of if gain allowed/blocked
        // does NOT check if adjacent units are themselves prevented from gaining WA by units adjacent to them - see ForfeitWA.CanClaimWallAdv

        boolean EnemyPresent = false;
        Hex hexBlocktest;
        Constantvalues.Location hexOTLoc = Constantvalues.Location.NA;
        LinkedList<PersUniti> Conlist = new LinkedList<PersUniti>();
        // set Nationality to friendly
        CommonFunctionsC comfunc = new CommonFunctionsC(scen.getScenID());
        Constantvalues.Nationality NatTest = comfunc.GetFriendlySide(initialnationality);
        // various tests of movingunit position

        // ADJACENT WA enemy check
        // loop through adjacent hexes
        IsSide Sidechk = new IsSide();
        if (DoesWAinAdjacentHexPreventWAGain(hexclickedvalue, NatTest)) {
            returnresultsvalue = "due to Enemy unit with WA in adjacent hex";
            return false;
        }
        // Second, check if unit's position/location/status in hexclicked prevents it from claiming WA
        if (!CanClaimWallAdv(hexclickedvalue, NatTest)) {  // replaces StatusPrevents used in VB
                returnresultsvalue = "due to location, position or condition of moving unit";
                return false;
        }

        return true; // nothing above stops WA Gain

        // SORT THIS OUT ONCE ABOVE CODE WORKING
        /*'now check for concealed enemy claiming WA
        For Each
        Checksprite As
        ObjectClassLibrary.ASLXNA.SpriteOrder In

        OH.VisibleCountersInHex
        If(Checksprite.hexloc = hexloc Or IsPositionInBaseLocation(Checksprite.hexloc))
        And TypeCheck.

        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, Checksprite.TypeID) Then

        Dim ConCheck
        As ObjectClassLibrary.ASLXNA.PersUniti = (
                From selunit
        As ObjectClassLibrary.
        ASLXNA.PersUniti In
        Scencolls.Unitcol Where
        selunit.BasePersUnit.Unit_ID =
                Checksprite.ObjID AndAlso
        selunit.BasePersUnit.Con_ID > 0
        Select selunit).First
        If ConCheck.
        BasePersUnit.HasWallAdvantage Then ConList.Add(ConCheck)
        End If
        Next
        End If
        End If
        Next
        If ConList.Count > 0 Then 'if only enemy claiming WA is concealed then need to test

        If DoesConcealmentBlock (ConList) Then

        returnstringvalue = "due to concealed enemy unit with WA"
        Return False 'adjacent unit claiming WA
        End If
        End If
        Return True 'nothing in other adjacent hexes blocks WA Gain by starthex*/
    }
    private boolean IsPositionInBaseLocation(Constantvalues.AltPos UnitPosition) {
        /*'Checks if unit is in a position considered part of basehex location but with a different value (InFoxhole, UnderWire, CrestStatus)
        Select Case
        UnitPosition
        'this may not be complete, keep adding
        Case ConstantClassLibrary.
        ASLXNA.Feature.Foxhole, ConstantClassLibrary.ASLXNA.Feature.Sanger, ConstantClassLibrary.ASLXNA.Feature.Trench,
                ConstantClassLibrary.ASLXNA.AltPos.AboveWire To
        ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6
        Return True
        Case Else
        Return False
        End Select*/
        return false;
    }
    private boolean DoesConcealmentBlock(LinkedList<PersUniti> Conlist) {
       return false;
        /* As Boolean
        'Sorts out role of Con counters in preventing WA claim
        Const AllConc
        As Integer = 2 :
        Dim ClaimReal
        As Boolean = False
        Dim TypeCheck = New
        UtilClassLibrary.ASLXNA.TypeUtil
        'since ALL units with WA in test hex are concealed, unit(s) in starthex must be non-concealed or temp reveal
        'determine visibility status in starthex
        Dim VisibilityStatus
        As UtilWObj.ASLXNA.VisibilityStatusi :
        Dim ContentsofLocationClaiming
        As UtilWObj.ASLXNA.ContentsofLocation
        If UseMoving
        Then 'need to use Moving Units
        VisibilityStatus =
                New UtilWObj.ASLXNA.UnitsVisibilityC(Scencolls.SelMoveUnits)
        Else 'use contents of location
        Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
        Dim HexTest
        As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(hexclickedvalue, locationchangevalue)
        ContentsofLocationClaiming =
                New UtilWObj.ASLXNA.ContentsofLocation(HexTest.LocIndex)
        ContentsofLocationClaiming.GetContents()
        VisibilityStatus =
                New UtilWObj.ASLXNA.LocationVisibilityC(ContentsofLocationClaiming.ContentsInLocation)
        End If
        Dim ClaimingVisStatus = VisibilityStatus.GetStatus
        If ClaimingVisStatus = AllConc
        Then
        'claiming stack must reveal one unit temporarily to show that it contains real units
        Dim GetLocs
        As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
        Dim claimhexname
        As String = GetLocs.GetnamefromdatatableMap(hexclickedvalue)
        Do
        Game.contextshowing = True
        Dim UnitName
        As String = InputBox("Enter name of unit temporarily revealed in " & claimhexname & ": ", "Concealed Stack trying to claim WA")
        'verify
        If UseMoving
        Then 'need to use Moving Units
        Try
        Dim MatchUnit
        As ObjectClassLibrary.ASLXNA.PersUniti = (Scencolls.SelMoveUnits).

                Where(Function(DoMatch)DoMatch.BasePersUnit.UnitName =

                        Trim(UnitName)).First
        MessageBox.Show("Verified. Proceed")
        ClaimReal = True :
        Exit Do
        Catch ex
        As Exception

        If Trim (UnitName) = ""
        Then
                ClaimReal = False :
        Exit Do
        End If
        End Try
        Else 'use contents of location
        For Each
        LocCont In
        ContentsofLocationClaiming.ContentsInLocation
        If TypeCheck.

        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, LocCont.TypeID) Then

        Dim ClaimingUnit
        As ObjectClassLibrary.ASLXNA.PersUniti = (Scencolls.SelMoveUnits).

                Where(Function(DoMatch)DoMatch.BasePersUnit.Unit_ID = LocCont.ObjID).First

        If Trim (ClaimingUnit.BasePersUnit.UnitName) =

                Trim(UnitName) Then
        MessageBox.Show("Verified. Proceed")
        ClaimReal = True :
        Exit Do

        ElseIf Trim (UnitName) = ""
        Then
                ClaimReal = False :
        Exit Do
        End If
        End If
        Next
        End If
        MessageBox.Show("Not Found. Enter valid name")
        Loop
        Game.contextshowing = False
        Else 'not all claiming stack are concealed therefore no need to reveal; can claim
        ClaimReal = True
        End If
        If ClaimReal
        Then
        'claiming side has real units, WA con units in test hex must momentarily reveal in order to block claim
        ' if all Concealed in test hex are dummies then forfeit WA
        Dim AllDummy
        As Boolean = True :
        Dim MandatoryTest
        As Boolean :
        Dim Firstime
        As Boolean = True :
        Dim MixedMan
        As Boolean = False :
        Dim MustReveal
        As Boolean = False
        For Each
        ConItem As
        DataClassLibrary.Concealment In
        Conlist
        Dim ConUnits

        As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =(
                From ConFind
        As ObjectClassLibrary.
        ASLXNA.PersUniti In
        Scencolls.Unitcol Where
        ConFind.BasePersUnit.Con_ID =
                ConItem.Con_ID Select
        ConFind).ToList
        For Each
        Conunit As
        ObjectClassLibrary.ASLXNA.PersUniti In
        ConUnits

        If Not (Conunit.BasePersUnit.LOBLink >= 201 And Conunit.BasePersUnit.LOBLink <= 215)Then ' Not Dummy
        AllDummy = False :
        Exit For
        End If

        'Dim MapGeo As MapGeoClassLibrary.ASLXNA.MapGeoC = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        'Dim Otherhexnum As List(Of Integer) = MapGeo.FillDirExtentArray(CInt(ConItem.hexnum))
        'Dim SideChk = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
        'If SideChk.WAMandatory(CInt(ConItem.hexnum), Otherhexnum, CInt(ConItem.Hexlocation), CInt(ConItem.Position)) Then
        '    '
        If Game.Scenario.TerrainActions.WAMandatory(

                CInt(ConItem.hexnum)) Then
        '    If Firstime Then
        '        MandatoryTest = True : Firstime = False
        '    Else
        '        If MandatoryTest = False Then MixedMan = True Else MandatoryTest = True
        '    End If
        '    If Not (Conunit.BasePersUnit.LOBLink >= 201 And Conunit.BasePersUnit.LOBLink <= 215) Then MustReveal = True '
        ConItem.IsDummy Then
        MustReveal = True
        'Else
        '    If Firstime Then
        '        MandatoryTest = False : Firstime = False
        '    Else
        '        If MandatoryTest = True Then MixedMan = True Else MandatoryTest = False
        '    End If
        'End If
        Next
                Next
        Dim RevealedUnit
        As Integer = 0 :
        Dim Conremove
        As Integer = 0 :
        Dim Prompt
        As String = ""
        'If MixedMan And Not MustReveal Then
        '    Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "Use cancel to forfeit ALL WA without revealing. For ? in Mandatory terrain, confirms that they are Dummies."
        'ElseIf MixedMan And MustReveal Then
        '    Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "At least one unit exists in Mandatory Terrain. MUST reveal a unit."
        'ElseIf MandatoryTest Then
        '    Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "Since all terrain requires Mandatory WA can only use cancel to forfeit ALL WA if all Dummies. MUST reveal if exists to maintain WA"
        'ElseIf Not (MandatoryTest) Then
        Prompt = "Enter name of unit revealed: " &

                Chr(13) &

                Chr(10) &

                Chr(13) &

                Chr(10) & "Use cancel to voluntarily forfeit ALL WA without revealing."
        'End If
        Do
        Game.contextshowing = True
        Dim UnitName
        As String = InputBox(Prompt, "Concealed Stack must have non-dummy to keep WA")
        'verify
        'If AllDummy Then
        '    RevealedUnit = 0 : Exit Do
        'Else
        For Each
        ConItem As
        ObjectClassLibrary.ASLXNA.PersUniti In
        Conlist
        Dim ConGet

        As IEnumerable (Of ObjectClassLibrary.ASLXNA.PersUniti) =(Scencolls.Unitcol).

                Where(Function(Getcon)Getcon.BasePersUnit.Unit_ID = ConItem.BasePersUnit.Unit_ID)
        Try
        Dim MatchUnit
        As ObjectClassLibrary.ASLXNA.PersUniti = (ConGet).

                Where(Function(DoMatch)DoMatch.BasePersUnit.UnitName =

                        Trim(UnitName)).First
        MessageBox.Show("Verified. Proceed")
        RevealedUnit = MatchUnit.BasePersUnit.Unit_ID
        'RevealItem(MatchUnit.BasePersUnit.Unit_ID, RevealedUnit)
        returnstringvalue = MatchUnit.BasePersUnit.Hexnum.ToString
        Exit Do
        Catch ex
        As Exception

        If Trim (UnitName) = "" Then
        'If Not MustReveal Then
        RevealedUnit = 0:
        Exit Do
        'End If
        End If
        End Try


        'For Each ConUnit As dataclasslibrary.orderofbattle In ConGet
        '    If Trim(ConUnit.OBName) = Trim(UnitName) Then
        '        MessageBox.Show("Verified. Proceed")
        '        RevealedUnit = ConUnit.OBUnit_ID
        '        RevealItem(ConUnit.OBUnit_ID, RevealedUnit)
        '        returnstringvalue = ConUnit.hexnum.ToString
        '        '
        If ConGet.Count = 1
        Then Conremove = CInt(ConItem.Con_ID)
        '        Exit Do
        '    ElseIf Trim(UnitName) = "" Then
        '        If Not MustReveal Then
        '            RevealedUnit = 0 : Exit Do
        '        End If
        '    End If
        'Next
        Next
        MessageBox.Show("Not Found. Enter valid name")
        'End If
        Loop
        Game.contextshowing = False
        'If Conremove > 0 Then '
        concounter to
        eliminate
        '    '
        delete Con
        from Concealmentcol -
                since only
        concealed unit
        is now
        revealed
        '    Dim UnittoChange As UnitChange = New ElimConcealC(Conremove)
        '    UnittoChange.TakeAction()
        'End If
        'revealed unit prevents units in starthex claiming WA
        If RevealedUnit >0 Then
        Return True
        'Else
        '    '
        no real
        units revealed
        in test
        hex;
        all Con
        units in
        test hexes
        lose WA
        and WA
        is allowed
        in starthex
        '    For Each Conitem As ObjectClassLibrary.ASLXNA.PersUniti In Conlist
        '        Dim RemoveIt = New ObjectChange.ASLXNA.WARemovec(Conitem)
        '        RemoveIt.IsRemoved()
        '    Next
        '    Return False
        End If
        Else
        Return True 'no real units in starthex to claim WA so "blocked"
        End If*/
    }

    private boolean StatusPrevents() {
            /*'called by
                    'tests if status of moving units prevents claiming WA
    Dim TypeTest
    As Integer = ConstantClassLibrary.ASLXNA.Typetype.Personnel
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(Game.Scenario.LocationCol)
            'if no moving units then status prevents

    If IsNothing(Scencolls.SelMoveUnits) Or Scencolls.SelMoveUnits.Count =0
    Then Return
    True
            'loop through each unit to see in any can'
    t claim
    WA
    For Each
    MovingUnit As
    ObjectClassLibrary.ASLXNA.PersUniti In
    Scencolls.SelMoveUnits
                'can'
    t claim if
    already has

    If(MovingUnit.BasePersUnit.hexPosition =ConstantClassLibrary.ASLXNA.AltPos.WallAdv Or(MovingUnit.BasePersUnit.hexPosition>=ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And MovingUnit.BasePersUnit.hexPosition<=ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6))And
    MovingUnit.BasePersUnit.Hexnum =
    hexclickedvalue Then
    Return True
                'broken units can'
    t claim
    If MovingUnit.BasePersUnit.OrderStatus >=
    ConstantClassLibrary.ASLXNA.OrderStatus.Broken And
    MovingUnit.BasePersUnit.OrderStatus <=
    ConstantClassLibrary.ASLXNA.OrderStatus.DisruptedDM Then
    Return True
                'unit crewing gun in fortified building can'
    t claim
    If IsTerrChk.

    IsLocationTerrainA(CInt(MovingUnit.BasePersUnit.Hexnum),CInt(MovingUnit.BasePersUnit.hexlocation),ConstantClassLibrary.ASLXNA.Location.FortBuildtype)Then
                    'need to check for unit being gun crew - can' t do
    yet as
    Guns not
    programmed
                    'Return true as default
    Return True  'unit is crewing gun in fortified building
    End If
                'not on ground level, unless also in creststatus
    If MovingUnit.BasePersUnit.LevelinHex<> 0

    And Not(MovingUnit.BasePersUnit.hexPosition >=ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And
            MovingUnit.BasePersUnit.hexPosition<=ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6) Then Return True
                'Pinned, TI prevent
    If MovingUnit.
    BasePersUnit.Pinned Or
    MovingUnit.BasePersUnit.MovementStatus =
    ConstantClassLibrary.ASLXNA.MovementStatus.TI Then
    Return True
                'non-prisoner enemy present in location
    Dim CheckforEnemy = New UtilWObj.ASLXNA.EnemyChecksC(

    CInt(MovingUnit.BasePersUnit.LOCIndex),CInt(MovingUnit.BasePersUnit.Nationality),Game.Scenario.ScenID)
    If CheckforEnemy.
    EnemyinLocationTest Then
    Return True
                'In Pillbox, entrenchment, above wire/panji, on bridge, etc prevent
    If MovingUnit.BasePersUnit.hexlocation =
    ConstantClassLibrary.ASLXNA.Feature.Foxhole Or
    MovingUnit.BasePersUnit.hexPosition =
    ConstantClassLibrary.ASLXNA.AltPos.AboveWire Or
    MovingUnit.BasePersUnit.hexPosition =
    ConstantClassLibrary.ASLXNA.AltPos.InFoxhole Or
    MovingUnit.BasePersUnit.hexPosition =

    ConstantClassLibrary.ASLXNA.AltPos.AbovePanji Or
            (MovingUnit.BasePersUnit.hexlocation >=ConstantClassLibrary.ASLXNA.Location.Pill1571 And MovingUnit.BasePersUnit.hexlocation<=ConstantClassLibrary.ASLXNA.Location.Bombprf) Or MovingUnit.BasePersUnit.hexlocation =
    ConstantClassLibrary.ASLXNA.Location.BunkUnder Or
    MovingUnit.BasePersUnit.hexlocation =
    ConstantClassLibrary.ASLXNA.Feature.Trench Or
    MovingUnit.BasePersUnit.hexPosition =
    ConstantClassLibrary.ASLXNA.AltPos.InTrench Then
    Return True

    If(MovingUnit.BasePersUnit.hexlocation >=ConstantClassLibrary.ASLXNA.Location.StBr14 And MovingUnit.BasePersUnit.hexlocation<=ConstantClassLibrary.ASLXNA.Location.FoBr) Then

    For i = 1
    To 6
    Dim Sidetest
    As Integer = Game.Linqdata.Gethexsidetype(i, CInt(MovingUnit.BasePersUnit.Hexnum))

    If Not(Sidetest >=ConstantClassLibrary.ASLXNA.Hexside.Roadblock1 And Sidetest<=ConstantClassLibrary.ASLXNA.Hexside.Roadblock6) Then 'no roadblock
    Return True  'unit is on bridge with no roadblock side
    End If
    Next
    End If
                'check if creststatus and wall/hedge on crest side
    If MovingUnit.BasePersUnit.hexPosition >=
    ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And
    MovingUnit.BasePersUnit.hexPosition <=
    ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6 Then
                    'now need to check that Wall/Hedge exists within crest hexside - not other side of depression
    Dim SideConvert
    As New
    UtilClassLibrary.ASLXNA.ConversionC
    Dim crestside
    As Integer = SideConvert.CrestSideToSide(MovingUnit.BasePersUnit.hexPosition)
    Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
    Dim x
    As Integer = -1
    Do
    Dim testside
    As Integer = crestside + x
    If testside = 0
    Then testside = 6
    If testside = 7
    Then testside = 1
    If SideTest.

    IsAWallHedgeRdBlk(testside, CInt(MovingUnit.BasePersUnit.LOCIndex))
    Then Return
    False
    x +=1
    Loop Until
    x =2
    Return True
    ElseIf IsTerrChk.

    IsLocationTerrainA(MovingUnit.BasePersUnit.Hexnum, MovingUnit.BasePersUnit.hexlocation, ConstantClassLibrary.ASLXNA.Location.Creststatustype) Then
                    'status prevents because requires creststatus and unit is not in creststatus
    Return True
    End If
    Next
            'default value: if nothing prevents action then return false*/
        return false;
    }
    private boolean DoesWAinAdjacentHexPreventWAGain(Hex hextotest, Constantvalues.Nationality Natcheck ){
        // checks if any adjacent hexes have enemy WA units that prevent Natcheck from gaining WA in hextotest

        MapCommonFunctionsc mapcomfunc = new MapCommonFunctionsc();
        Hex [] Adjacenthexes = mapcomfunc.getAdjacentHexArray(hexclickedvalue);
        for (int x = 0; x < 6; x++) {
            // identify the other hex
            Hex hextotestforWA = Adjacenthexes[x];
            EnemyChecksC CheckforEnemy = new EnemyChecksC(hextotestforWA.getCenterLocation(), Natcheck, scen.getScenID());
            boolean EnemyPresent = CheckforEnemy.EnemyInHexTest(hextotest);
            if (EnemyPresent) {
                // if found, see if any have WA
                LinkedList<PersUniti> unitsinhex = infcommfunc.FindUnitsInHex(hextotestforWA, Natcheck);
                for (PersUniti WAcheckunit : unitsinhex) {
                    if (WAcheckunit.getbaseunit().HasWallAdvantage()) {
                        return true;
                    }
                }
            }
        }
        return false; // if here then nothing in adjacent hexes prevents hextotest from gaining WA
    }
    public boolean CanClaimWallAdv (Hex hextocheck, Constantvalues.Nationality NatTest){
        // Test if location/position of existingunit in hextocheck prevents WA being claimed in hextocheck adjacent to hex which has just lost WA
        // or if enemy units in hex adjacent to existingunit block gain
        // does NOT change WA; returns bool value of if gain allowed/blocked

        Locationi hexloc; boolean EnemyPresent = false; int hexBlocktest = 0;
        LinkedList<PersUniti> ConList = new LinkedList<PersUniti>(); boolean CanClaim = false;
        IsTerrain TerrChks = new IsTerrain();
        // check if status(location, position, condition, etc) of existingunit prevents it from gaining WA
        // retrieve existingunits
        LinkedList<PersUniti> unitsinhex = infcommfunc.FindUnitsInHex(hextocheck, NatTest);
        for (PersUniti EnemyUnit: unitsinhex) {
            IsSide Sidechk = new IsSide();
            // check status - already has WA
            if (EnemyUnit.getbaseunit().HasWallAdvantage() &&
                    (EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.GoodOrder ||
                            EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Berserk)) {
                return false;
                // existingunit already claiming WA so no need to switch
            }
            if (EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken ||
                    EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.DisruptedDM ||
                    EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken_DM ||
                    EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Disrupted) {
                continue; // this existingunit can' t claim but others could
            }
            if (EnemyUnit.getbaseunit().getVisibilityStatus().equals(Constantvalues.VisibilityStatus.Visible)) {
                CanClaim = true;
            } // one existingunit is not broken so WA gain possible

            if (hextocheck.getCenterLocation().getTerrain().isBuildingTerrain() && infcommfunc.IsUnitACrew(EnemyUnit.getbaseunit().getUnitClass())) {
                // building check must be for Fort Bldg and crew must possess a gun -ADD THESE TESTS
                CanClaim = false;
                continue;
            }
            if (EnemyUnit.getbaseunit().getLevelinHex() != 0) {
                // not on ground level; this unit can't claim but others could
                CanClaim = false;
                continue;
            }
            if (EnemyUnit.getbaseunit().getPinned() || EnemyUnit.getbaseunit().getMovementStatus().equals(Constantvalues.MovementStatus.TI)){
                // Pinned, TI prevent; this unit can't claim but others could
                CanClaim = false;
                continue;
            }
            if (TerrChks.SpecialTerrainPreventsWAGain(EnemyUnit)){
                // In Pillbox, entrenchment, above wire/panji prevent; this unit can' t claim but others could
                CanClaim = false;
                continue;
            }
            if (EnemyUnit.getbaseunit().gethexlocation().getTerrain().isBridge()){
                for (int x =0; x <6; x++){
                    hexloc = new Locationc(EnemyUnit.getbaseunit().getHex().getCenterLocation(), null);
                    if(!Sidechk.IsARdBlk(Sidechk.Gethexsidetype(hexloc, x))) {
                        // unit is on bridge with no roadblock side
                        CanClaim = false;
                        continue;
                    }
                }
            }
            // non-prisoner enemy (of the Enemy) present in location
            EnemyChecksC CheckforEnemy = new EnemyChecksC(EnemyUnit.getbaseunit().gethexlocation(), EnemyUnit.getbaseunit().getNationality(), scen.getScenID());
            if (CheckforEnemy.EnemyinLocationTest()) {
                CanClaim = false;
                continue;
            }
            // check if creststatus
            MapCommonFunctionsc mapcommonfunc = new MapCommonFunctionsc();
            if (mapcommonfunc.IsCrestStatus(EnemyUnit.getbaseunit().gethexPosition())) {
                // now need to check that Wall/Hedge exists within crest hexside - not other side of depression
                ConversionC SideConvert = new ConversionC();
                int crestside = SideConvert.CrestSideToSide(EnemyUnit.getbaseunit().gethexPosition());
                int x = -1;
                boolean Cando = false;
                while (x < 2) {
                    int testside = crestside + x;
                    if (testside == -1) {
                        testside = 5;
                    }
                    if (testside > 6) {
                        testside = 0;
                    }
                    if (Sidechk.IsAWallHedgeRdBlk(SideConvert.ConverttoHexside(EnemyUnit.getbaseunit().getHex().getHexsideTerrain(testside)))) {
                        Cando = true;
                    }
                    x += 1;
                }
                if (CanClaim == false || Cando == false) {
                    CanClaim = false;
                } else if (Cando) {
                    CanClaim=true;
                }
            } else if (TerrChks.IsLocationTerrainA(EnemyUnit.getbaseunit().getHex(), EnemyUnit.getbaseunit().gethexlocation(), Constantvalues.Location.Creststatustype)) {
                // WA requires creststatus and Defender is not in creststatus
                CanClaim = false;
                continue;
            }

        }
        return CanClaim;

        // NEED TO DEAL WITH CASE OF CONCEALED UNITS AND DUMMIES BUT WAIT UNTIL HAVE OTHER THINGS WORKING
                 /*ElseIf TypeCheck.
                        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, Defendersprite.TypeID) Then
                            CanClaim = True
                        Dim DefenderCheck As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As
                        ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where
                        selunit.BasePersUnit.Unit_ID = Defendersprite.ObjID AndAlso selunit.
                        BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment Select selunit).First
                        If DefenderCheck.BasePersUnit.HasWallAdvantage Then Return False 'already has no so gain
                        If DefenderCheck.BasePersUnit.LevelinHex<> 0 Then CanClaim = False :Continue For
                        'not at ground level
                        'In Pillbox, entrenchment, above wire/panji prevent
                        If DefenderCheck.basepersunit.hexlocation = ConstantClassLibrary.ASLXNA.Feature.Foxhole
                        Or DefenderCheck.basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.AboveWire Or
                            (DefenderCheck.basepersunit.hexlocation >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And DefenderCheck.basepersunit.hexlocation <= ConstantClassLibrary.ASLXNA.Location.Bombprf)
                        Or
                        DefenderCheck.basepersunit.hexlocation = ConstantClassLibrary.ASLXNA.Location.BunkUnder Or
                        DefenderCheck.basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.InFoxhole
                        Or DefenderCheck.basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.AbovePanji Or
                        DefenderCheck.basepersunit.hexlocation = ConstantClassLibrary.ASLXNA.Feature.Trench Or DefenderCheck.
                        basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.InTrench Then CanClaim = False :
                        Continue For
                        If(DefenderCheck.basepersunit.hexlocation >= ConstantClassLibrary.ASLXNA.Location.StBr14 And DefenderCheck.basepersunit.hexlocation <= ConstantClassLibrary.ASLXNA.Location.FoBr)
                        Then
                        For i = 1 To 6
                        Dim Sidetest As Integer = Game.Linqdata.Gethexsidetype(i, CInt(DefenderCheck.basepersunit.hexnum))
                        If Not (Sidetest >= ConstantClassLibrary.ASLXNA.Hexside.Roadblock1 And Sidetest <=
                        ConstantClassLibrary.ASLXNA.Hexside.Roadblock6)Then 'no roadblock
                        CanClaim = False :Continue For 'unit is on bridge with no roadblock side
                        End If
                        Next
                        End If
                        'check if creststatus
                        If DefenderCheck.basepersunit.hexposition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1
                        And DefenderCheck.basepersunit.hexposition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6 Then
                        'now need to check that Wall/Hedge exists within crest hexside - not other side of depression
                        Dim SideConvert As New UtilClassLibrary.ASLXNA.ConversionC
                        Dim crestside As Integer = SideConvert.CrestSideToSide(CInt(DefenderCheck.basepersunit.hexposition))
                        Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
                        Dim x As Integer = -1 :Dim Cando As Boolean = False
                        Do
                        Dim testside As Integer = crestside + x
                        If testside = 0 Then testside = 6
                        If testside = 7 Then testside = 1
                        'Dim Sidetotest As Integer = Linqdata.Gethexsidetype(testside, CInt(DefenderCheck.hexnum))
                        If SideTest.IsAWallHedgeRdBlk(testside, CInt(DefenderCheck.BasePersUnit.LOCIndex)) Then ConList.
                        Add(DefenderCheck) :Cando = True :Exit Do
                        x += 1
                        Loop Until x = 2
                        If CanClaim = False Or Cando = False Then CanClaim = False
                        ElseIf IsTerrChk.
                        IsLocationTerrainA(CInt(DefenderCheck.basepersunit.hexnum), CInt(DefenderCheck.basepersunit.hexlocation), ConstantClassLibrary.ASLXNA.Location.Creststatustype)
                        Then
                        'WA requires creststatus and defender is not in creststatus
                        CanClaim = False :Continue For
                        End If
                        End If
                        If CanClaim = True Then Exit For
                            Next
                        If CanClaim = False Then Return False 'no Defender can claim WA
                        'check if ADJACENT enemy with WA over one hexside of Defender' s hex prevents gain
                        Dim Sidechk As New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
                        Dim MapGeo as mapgeoclasslibrary.
                        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                        For i = 1 To 6
                        'identify the other hex
                        Dim hexBlocktestnum As Integer = MapGeo.DirExtent(hexnum, i, 1)
                        If Sidechk.IsWAAllowedToSwitch(hexnum, i, hexBlocktestnum) Then
                        'If Game.Scenario.TerrainActions.IsWAAllowedToSwitch(hexnum, i) Then
                        'WA could exist across hexside and prevent gain by defending unit
                        'get test hex info
                        'hexBlocktest = Mapgeo.RangeC.DirExtent(hexnum, i, 1)
                        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
                        Dim HexTest As MapDataClassLibrary.
                        GameLocation = LevelChk.GetLocationatLevelInHex(hexBlocktestnum, 0)
                        hexloc = CInt(HexTest.Location)
                        'Determine if units (both real and dummy) in test hex are enemy to the defender
                        Dim CheckforEnemy = New
                        UtilWObj.ASLXNA.EnemyChecksC(HexTest.LocIndex, NatTest, Game.Scenario.ScenID)
                        EnemyPresent = CheckforEnemy.EnemyinLocationTest
                        If EnemyPresent Then
                        'if enemy exists, any units claiming WA (will Block WA gain by Defender unit)
                        OH = CType(Game.Scenario.HexesWithCounter(hexBlocktestnum), VisibleOccupiedhexes)
                        'check visible units first
                        For Each Checksprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
                        If(Checksprite.hexloc = hexloc Or IsPositionInBaseLocation(Checksprite.hexloc)) And TypeCheck.
                        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, Checksprite.TypeID) Then
                        Dim UnitCheck As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As
                        ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where
                        selunit.BasePersUnit.Unit_ID = Checksprite.ObjID Select selunit).First
                        'check if claiming WA
                        If UnitCheck.BasePersUnit.HasWallAdvantage And
                        (UnitCheck.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.GoodOrder Or UnitCheck.
                        BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Berserk)Then
                        Return False
                        'adjacent unit claiming WA across another hexside - so blocks WA in by Defender
                        'all other OrderStatus values can' t hold WA from switching(broken, prisoner)
                        End If
                        End If
                        Next
                        'now check for concealed enemy claiming WA
                        For Each Checksprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
                        If(Checksprite.hexloc = hexloc Or IsPositionInBaseLocation(Checksprite.hexloc)) And TypeCheck.
                        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, Checksprite.TypeID) Then
                        Dim ConCheck As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As
                        ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where
                        selunit.BasePersUnit.Unit_ID = Checksprite.ObjID AndAlso selunit.
                        BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment Select selunit).First
                        If ConCheck.BasePersUnit.HasWallAdvantage Then ConList.Add(ConCheck)
                        End If
                        Next
                        End If
                        End If
                        'End If
                        Next
                        If ConList.Count > 0 Then 'if only enemy claiming WA is concealed then need to test
                        If DoesConcealmentBlock (ConList, hexnum)Then

                        Return False 'adjacent unit claiming WA
                        End If
                        End If
                        Return CanClaim*/
    }
}
