package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectChangeClasses.UnitChangei;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.IsTerrain;
import VASL.build.module.fullrules.UtilityClasses.*;

import java.util.LinkedList;

public class WithinWALossCheckc implements WallAdvantageLossChecki {
    private InfantryUnitCommonFunctionsc infcommfunc = new InfantryUnitCommonFunctionsc();
    private Hex hexclickedvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private IsTerrain TerrChks = new IsTerrain();
    ScenarioC scen ;

    public WithinWALossCheckc(Hex hexclicked) {
        scen = ScenarioC.getInstance();
        hexclickedvalue = hexclicked;
    }
    public boolean WallAdvantageLossCheck(Hex usinghex) {
        // determines if broken/unarmed friendlies lose Wall Adv and
        // if Concealed WA switches (meaning friendly ? is a dummy)

        // THIS IS SAME CODE AS EnterWALossCheck     - ARE SEPARATE CLASES NEEDED?

        LinkedList<PersUniti> unitsinhex = infcommfunc.FindUnitsInHex(usinghex);
        for (PersUniti WAUnit: unitsinhex) {
            if ((WAUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.GoodOrder || WAUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Berserk) &&
                    WAUnit.getbaseunit().HasWallAdvantage() && WAUnit.getbaseunit().getVisibilityStatus().equals(Constantvalues.VisibilityStatus.Visible)) {
                // all other Orderstatus values lose WA
                return false;  // a remaining unit has valid WA
            } else if (WAUnit.getbaseunit().getVisibilityStatus().equals(Constantvalues.VisibilityStatus.Concealed)){
                if (!EnemyForcesWALoss(usinghex)) {return false;} //concealed units prevents WA loss
            }
        }
        // if here then nothing prevents possible WA loss and possible switch
        // do loss

        for (PersUniti StatusTest: unitsinhex) {
            if (TerrChks.IsPositionWA(StatusTest.getbaseunit().gethexPosition())) {
                UnitChangei UnittoChange = new WARemovec(StatusTest);
                UnittoChange.TakeAction();
                return true;
            }

        }
        return true;

    }
    private boolean EnemyForcesWALoss(Hex usinghex) {
        // called by WallAdvantageLossCheck
        // determines if enemy forces WA to switch - actual switch is done later

        // THIS IS SAME CODE AS EnterWALossCheck     - ARE SEPARATE CLASES NEEDED?

        Location hexloc; boolean EnemyPresent = false; Hex hextocheck;
        // set friendly side
        Constantvalues.Nationality MoveNat = Scencolls.SelMoveUnits.getFirst().getbaseunit().getNationality();
        // loop through each adjacent hex
        MapCommonFunctionsc mapcomfunc = new MapCommonFunctionsc();
        Hex [] Adjacenthexes = mapcomfunc.getAdjacentHexArray(usinghex);
        for (int x = 0; x < 6; x++) {
            // identify the other hex
            Hex hextotest = Adjacenthexes[x];
            IsSide Sidechk = new IsSide();
            if (Sidechk.IsWAAllowedToSwitch(usinghex, x, hextotest)) {
                // WA status can switch across this hexside so get the hex and check for enemy
                // Determine if present units (both real and dummy) are enemy to the moving side
                EnemyChecksC CheckforEnemy = new EnemyChecksC(hextotest.getCenterLocation(), MoveNat, scen.getScenID());
                EnemyPresent = CheckforEnemy.EnemyInHexTest(hextotest);
                if (EnemyPresent) {
                    // if found
                    // First need to test if WA gain by the enemy is prevented by WA across other hexsides of the enemy unit' s hex
                    CommonFunctionsC commfunc = new CommonFunctionsC(scen.getScenID());
                    Constantvalues.Nationality Natcheck = commfunc.GetOppositeSide(MoveNat);
                    if (!CanClaimWallAdv(hextotest, Natcheck)) {
                        //MessageBox.Show("WA cannot switch to " & GetLocs.GetnamefromdatatableMap(hexnum) & " due to another hex claiming WA over that hex")
                    } else {
                        // WA can switch to enemy
                        return true;
                    }
                }
            }
        }
        return false; // if here then no hexes where switch allowed
    }
    private boolean IsPositionInBaseLocation(Constantvalues.AltPos UnitPosition) {
        /*'Checks if unit is in a position considered part of basehex location but with a different value (InFoxhole, UnderWire, CrestStatus)
        Select Case UnitPosition
        'this may not be complete, keep adding
        Case constantclasslibrary.
        aslxna.Feature.Foxhole, constantclasslibrary.aslxna.Feature.Sanger, constantclasslibrary.aslxna.Feature.Trench,
                constantclasslibrary.aslxna.AltPos.AboveWire To constantclasslibrary.aslxna.AltPos.Passenger
        Return True
        Case Else
        Return False
        End Select*/
        return false;
    }
    public boolean CanClaimWallAdv(Hex usinghex, Constantvalues.Nationality NatTest) {
        // Test if location/position of Defending unit in hex prevents WA gain (ie above wire) or if enemy units in hex adjacent to Defending Unit block gain
        // does NOT change WA; returns bool value of if gain allowed/blocked

        // THIS IS SAME CODE AS EnterWALossCheck     - ARE SEPARATE CLASES NEEDED?

        Locationi hexloc; boolean EnemyPresent = false; int hexBlocktest = 0;
        LinkedList<PersUniti> ConList = new LinkedList<PersUniti>(); boolean CanClaim = false;

        // check if status(location, position, condition, etc) of Defending unit prevents it from gaining WA
        // retrieve defending units
        LinkedList<PersUniti> unitsinhex = infcommfunc.FindUnitsInHex(usinghex, NatTest);
        for (PersUniti EnemyUnit: unitsinhex) {
            IsSide Sidechk = new IsSide();
            // check status - already has WA
            if (EnemyUnit.getbaseunit().HasWallAdvantage() &&
                    (EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.GoodOrder ||
                            EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Berserk)) {
                return false;
                // Defending unit already claiming WA so no need to switch
            }
            if (EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken ||
                    EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.DisruptedDM ||
                    EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken_DM ||
                    EnemyUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Disrupted ) {
                continue; // this unit can' t claim but others could
            }
            if (EnemyUnit.getbaseunit().getVisibilityStatus().equals(Constantvalues.VisibilityStatus.Visible)) {CanClaim = true;} // one Defender is not broken so WA gain possible

            if (usinghex.getCenterLocation().getTerrain().isBuildingTerrain() && infcommfunc.IsUnitACrew(EnemyUnit.getbaseunit().getUnitClass())) {
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
                }
            } else if (TerrChks.IsLocationTerrainA(EnemyUnit.getbaseunit().getHex(), EnemyUnit.getbaseunit().gethexlocation(), Constantvalues.Location.Creststatustype)) {
                // WA requires creststatus and Defender is not in creststatus
                CanClaim = false;
                continue;
            }
        }

        // NEED TO DEAL WITH CASE OF CONCEALED UNITS AND DUMMIES BUT WAIT UNTIL HAVE OTHER THINGS WORKING

        /*ElseIf TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, Defendersprite.TypeID) Then
                CanClaim = True
        Dim DefenderCheck As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As
        ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where
        selunit.BasePersUnit.Unit_ID = Defendersprite.ObjID AndAlso selunit.
        BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment).First
        If DefenderCheck.BasePersUnit.HasWallAdvantage Then Return False 'already has no so gain
        If DefenderCheck.BasePersUnit.LevelinHex<> 0 Then CanClaim = False :Continue For 'not at ground level
        'In Pillbox, entrenchment, above wire/panji prevent
        If DefenderCheck.basepersunit.hexlocation = ConstantClassLibrary.ASLXNA.Feature.Foxhole Or DefenderCheck.
        basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.AboveWire Or
                (DefenderCheck.basepersunit.hexlocation >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And DefenderCheck.basepersunit.hexlocation <= ConstantClassLibrary.ASLXNA.Location.Bombprf)
        Or
        DefenderCheck.basepersunit.hexlocation = ConstantClassLibrary.ASLXNA.Location.BunkUnder Or
        DefenderCheck.basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.InFoxhole Or DefenderCheck.
        basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.AbovePanji Or
        DefenderCheck.basepersunit.hexlocation = ConstantClassLibrary.ASLXNA.Feature.Trench Or DefenderCheck.
        basepersunit.hexposition = ConstantClassLibrary.ASLXNA.AltPos.InTrench Then CanClaim = False :Continue For
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
        If DefenderCheck.basepersunit.hexposition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And DefenderCheck.
        basepersunit.hexposition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6 Then
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
        hexBlocktest = MapGeo.DirExtent(hexnum, i, 1)
        If Sidechk.IsWAAllowedToSwitch(hexnum, i, hexblocktest) Then
        'If Game.Scenario.TerrainActions.IsWAAllowedToSwitch(hexnum, i) Then
        'WA could exist across hexside and prevent gain by defending unit
        'get test hex info
        'hexBlocktest = Mapgeo.RangeC.DirExtent(hexnum, i, 1)
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
        Dim HexTest As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexBlocktest, 0)
        hexloc = CInt(HexTest.Location)
        'Determine if units (both real and dummy) in test hex are enemy to the side in starthex
        Dim CheckforEnemy = New UtilWObj.ASLXNA.EnemyChecksC(HexTest.LocIndex, NatTest, Game.Scenario.ScenID)
        EnemyPresent = CheckforEnemy.EnemyinLocationTest
        If EnemyPresent Then
        'if enemy exists, any units claiming WA (will Block WA gain by Defender unit)
        OH = CType(Game.Scenario.HexesWithCounter(hexBlocktest), VisibleOccupiedhexes)
        'check visible units first
        For Each Checksprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If(Checksprite.hexloc = hexloc Or IsPositionInBaseLocation(Checksprite.hexloc)) And TypeCheck.
        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, Checksprite.TypeID) Then
        Dim UnitCheck As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In
        scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = Checksprite.ObjID).First
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
        Dim ConCheck As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In
        scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = Checksprite.ObjID AndAlso selunit.
        BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment).First
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
        End If*/
        return CanClaim;
    }

    private boolean DoesConcealmentBlock(LinkedList<PersUniti> Conlist, Hex usinghex) {
        /*'Sorts out role of Con counters in preventing WA claim
        Const AllConc As Integer = 2 :Dim ClaimReal As Boolean = False :Dim DefenderVisStatus As Integer = AllConc
        'since ALL units with WA in test hex are concealed, Defender unit(s) must be non-concealed or temp reveal
        'determine visibility status in Defender hex
        Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
        For Each Defendersprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, Defendersprite.TypeID) Then
                DefenderVisStatus = 0 :Exit For
        End If
        Next
        If DefenderVisStatus = AllConc Then
        'Defender stack must reveal one unit temporarily to show that it contains real units
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
        Dim Defenderhexname As String = GetLocs.GetnamefromdatatableMap(hexnum)
        Do
        Game.contextshowing = True
        Dim DefenderName
        As String = InputBox("Enter name of unit temporarily revealed in " & Defenderhexname & ": ", "Testing if Concealed Stack can claim WA")
        'verify
        For Each Defendersprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, Defendersprite.TypeID) Then
        If Trim (DefenderName) = Trim(Defendersprite.Objname) Then
        MessageBox.Show("Verified. Proceed")
        ClaimReal = True :Exit Do
        ElseIf Trim (DefenderName) = "" Then
                ClaimReal = False :Exit Do
        End If
        ElseIf TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, Defendersprite.TypeID) Then
        Dim ConGet As IQueryable (Of ObjectClassLibrary.ASLXNA.PersUniti) =
        CType((From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = Defendersprite.ObjID Select selunit), Global.System.Linq.IQueryable(Of Global.ObjectClassLibrary.ASLXNA.PersUniti))
        Try
        Dim MatchUnit As ObjectClassLibrary.
        ASLXNA.PersUniti = (ConGet).Where(Function(DoMatch)Trim(DoMatch.BasePersUnit.UnitName) = Trim(DefenderName)).First
        MessageBox.Show("Verified. Proceed")
        ClaimReal = True :Exit Do
        Catch ex As Exception
        If Trim (DefenderName) = "" Then
                ClaimReal = False :Exit Do
        End If
        End Try
        End If
        Next
        MessageBox.Show("Not Found. Enter valid name")
        Loop
        Game.contextshowing = False
        Else
                ClaimReal = True
        End If
        If ClaimReal Then
        'claiming side has real units, WA con units in test hex must momentarily reveal in order to block claim
        ' if all Concealed in test hex are dummies then forfeit WA
        Dim AllDummy As Boolean = True :Dim MandatoryTest As Boolean :Dim Firstime As Boolean = True :Dim MixedMan
        As Boolean = False :Dim MustReveal As Boolean = False
        For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In Conlist
        If Not ConItem.MovingPersUnit.IsDummy Then
        AllDummy = False
        End If
        Dim MapGeo as mapgeoclasslibrary.
        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Dim Otherhexnum As List (Of Integer) =MapGeo.FillDirExtentArray(CInt(ConItem.basepersunit.hexnum))
        Dim SideChk = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
        If SideChk.
        WAMandatory(CInt(ConItem.basepersunit.hexnum), Otherhexnum, CInt(ConItem.basepersunit.hexlocation), CInt(ConItem.basepersunit.hexposition))
        Then
        If Firstime Then
                MandatoryTest = True :Firstime = False
        Else
        If MandatoryTest = False Then MixedMan = True Else MandatoryTest = True
        End If
        If Not ConItem.MovingPersUnit.IsDummy Then MustReveal = True
        Else
        If Firstime Then
                MandatoryTest = False :Firstime = False
        Else
        If MandatoryTest = True Then MixedMan = True Else MandatoryTest = False
        End If
        End If
        Next
        Dim RevealedUnit As Integer = 0 :Dim Conremove As Integer = 0 :Dim Prompt As String = ""
        If MixedMan And Not MustReveal Then
        Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "Use cancel to forfeit ALL WA without revealing. For ? in Mandatory terrain, confirms that they are Dummies."
        ElseIf MixedMan And MustReveal Then
                Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "At least one unit exists in Mandatory Terrain. MUST reveal a unit."
        ElseIf MandatoryTest Then
                Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "Since all terrain requires Mandatory WA can only use cancel to forfeit ALL WA if all Dummies. MUST reveal if exists to maintain WA"
        ElseIf Not (MandatoryTest) Then
        Prompt = "Enter name of unit revealed: " & Chr(13) & Chr(10) & Chr(13) & Chr(10) & "Use cancel to voluntarily forfeit ALL WA without revealing."
        End If
        Do
        Game.contextshowing = True
        Dim UnitName As String = InputBox(Prompt, "Concealed Stack must have non-dummy to keep WA")
        'verify
        If AllDummy Then
                RevealedUnit = 0 :Exit Do
        Else
        For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In Conlist
        Dim ConGet As IQueryable (Of ObjectClassLibrary.ASLXNA.PersUniti) =
        CType((From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = ConItem.BasePersUnit.Unit_ID Select selunit), Global.System.Linq.IQueryable(Of Global.ObjectClassLibrary.ASLXNA.PersUniti))
        Try
        Dim MatchUnit As ObjectClassLibrary.
        ASLXNA.PersUniti = (ConGet).Where(Function(DoMatch)Trim(DoMatch.BasePersUnit.UnitName) = Trim(UnitName)).First
        MessageBox.Show("Verified. Proceed")
        RevealedUnit = MatchUnit.BasePersUnit.Unit_ID
        RevealItem(MatchUnit.BasePersUnit.Unit_ID, RevealedUnit)
        'returnstringvalue = MatchUnit.hexnum.ToString
        Exit Do
        Catch ex As Exception
        If Trim (UnitName) = "" Then
        If Not MustReveal Then
        RevealedUnit = 0 :Exit Do
        End If
        End If
        End Try
        Next
        MessageBox.Show("Not Found. Enter valid name")
        End If
        Loop
        Game.contextshowing = False
        'revealed unit prevents Defender units from claiming WA
        If RevealedUnit >0 Then
        Return True
        Else
        'no real units revealed in test hex; all Con units in test hexes lose WA and defender is allowed to claim WA
        For Each Conitem As ObjectClassLibrary.ASLXNA.PersUniti In Conlist
        Dim RemoveIt As ObjectChange.ASLXNA.UnitChange = New ObjectChange.ASLXNA.WARemovec(Conitem)
        RemoveIt.TakeAction()
        Next
        Return False
        End If
        Else
        Return True 'no real Defender units so claiming WA is "blocked"
        End If*/
        return true;
    }
    private boolean RevealItem(PersUniti ItemtoGet, PersUniti RevealedUnit) {
        /*'called by DoesConcealmentBlock
        'reveal single concealed item
        RevealedUnit = 0
        Dim hexUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In
        scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = ItemtoGet).First
        Dim conidtoget As Integer = CInt(hexUnit.BasePersUnit.Con_ID)
        Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange = New ObjectChange.ASLXNA.RevealUnitC(ItemtoGet)
        UnittoChange.TakeAction()
        RevealedUnit = hexUnit.BasePersUnit.Unit_ID*/
        return true;
    }

}
