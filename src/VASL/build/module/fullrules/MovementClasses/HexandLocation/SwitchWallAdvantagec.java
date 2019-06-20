package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.IsTerrain;
import VASL.build.module.fullrules.UtilityClasses.*;


import java.util.LinkedList;


public class SwitchWallAdvantagec {
    
    private Hex hexclickedvalue;
    private Hex Claimhex;
    private Constantvalues.Nationality MovNat;
    private Locationi claimhexloc;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();
    private InfantryUnitCommonFunctionsc infcommfunc = new InfantryUnitCommonFunctionsc();
    private IsTerrain TerrChks = new IsTerrain();

    public SwitchWallAdvantagec(Hex hexclicked, Constantvalues.Nationality MovingNationality) {
        hexclickedvalue = hexclicked;
        MovNat = MovingNationality;
    }
    public boolean Switch() {
        // called by MoveUpdate methods
        // checks if adjacent units can get WA over hex that has lost WA

        boolean EnemyPresent = false; Location hexloc; Terrain hexOTloc;
        // loop through adjacent hexes
        IsSide Sidechk = new IsSide();
        MapCommonFunctionsc mapcomfunc = new MapCommonFunctionsc();
        Hex [] Adjacenthexes = mapcomfunc.getAdjacentHexArray(hexclickedvalue);
        for (int x = 0; x < 6; x++) {
            // identify the other hex
            Hex hextotest = Adjacenthexes[x];
            if (Sidechk.IsWAAllowedToSwitch(hexclickedvalue, x, hextotest)) {
                // WA status can switch across this hexside
                // Determine if present units (both real and dummy) are enemy to the moving side
                EnemyChecksC CheckforEnemy = new EnemyChecksC(hextotest.getCenterLocation(), MovNat, scen.getScenID());
                EnemyPresent = CheckforEnemy.EnemyInHexTest(hextotest);
                if (EnemyPresent) {
                    // if found
                    // First need to test that WA gain is prevented by friendly WA across other hexsides of the enemy units hex
                    CommonFunctionsC commfunc = new CommonFunctionsC(scen.getScenID());
                    Constantvalues.Nationality Natcheck = commfunc.GetOppositeSide(MovNat);
                    if (DoesWAinAdjacentHexPreventWAGain(hextotest, Natcheck)) {
                        return false;  //MessageBox.Show("WA cannot switch to " & GetLocs.GetnamefromdatatableMap(hexnum) & " due to another hex claiming WA over that hex")
                    }
                    // Second, check if unit's position/location/status in hexclicked prevents it from claiming WA
                    if (!CanClaimWallAdv(hextotest, Natcheck)) {
                        //MessageBox.Show("WA cannot switch to " & GetLocs.GetnamefromdatatableMap(hexnum)
                        return false;
                    }
                }
            }
        }
        return true;

                    
        /*'ElseIf (Checksprite.hexloc = hexloc Or Checksprite.hexloc = hexOTloc Or IsPositionInBaseLocation(Checksprite.hexloc)) And TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.SW, Checksprite.TypeID) Then
        '    Dim ConCheck As ObjectClassLibrary.ASLXNA.SuppWeapi = (From selunit As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where selunit.BaseSW.Unit_ID = Checksprite.ObjID AndAlso selunit.BaseSW.Type_ID = ConstantClassLibrary.ASLXNA.Typetype.SW Select selunit).First
        '    If ConCheck.BaseSW.HasWallAdvantage Then
        '        ' adjacent unit claiming WA -error and can
        't update so just return, this should never happen, but just in case
        '        Return False
        '    Else
        '        ' some hex positions disallow WA(beneath entrenchment, above wire) and so exit
        '        If PosChk.NoWAPosition(Checksprite.hexloc) Then Continue For
        '        ' Now check if Dummy or has units
        '        If ConCheck.MovingPersUnit.IsDummy Then
        '            ' if no TEM then WA shifts automatically else voluntarily
        '            ' Test if Mandatory WA (B9 .323)applies and therefore unit must claim Wall Adv
        '            Dim PassPosition As Integer = 0
        '            Dim Otherhexnum As List(Of Integer) = MapGeo.FillDirExtentArray(Hexnum)
        '            If Sidechk.IsWAMandatory(Hexnum, Otherhexnum, CInt(ConCheck.BasePersUnit.hexlocation), CInt(ConCheck.BasePersUnit.hexPosition)) Then
        '                If ConCheck.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And ConCheck.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6 Then
        '                    PassPosition = GetCrestWAPosition(ConCheck.BasePersUnit.hexPosition)
        '                Else
        '                    PassPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv
        '                End If
        '                ' update texture
        '                Dim TextureName As String = ConCheck.SetTexture()
        '                If Not IsNothing(TextureName) Then
        '                    ConCheck.BasePersUnit.OBTexture = Game.Content.Load(Of Texture2D)(Trim(TextureName))
        '                End If
        '            Else
        '                ' claiming WA is voluntary
        '                ' Display the message box and save the response, Yes or No.
        '                Dim response As Integer = MsgBox("Do you wish to claim Wall Advantage (Y/N)?", MsgBoxStyle.YesNo, "Enemy Unit forfeiting WA")
        '                If response = 6 Then ' Yes
        '                    If ConCheck.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And ConCheck.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6 Then
        '                        PassPosition = GetCrestWAPosition(ConCheck.BasePersUnit.hexPosition)
        '                    Else
        '                        PassPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv
        '                    End If
        '                    ' update texture
        '                    Dim TextureName As String = ConCheck.SetTexture()
        '                    If Not IsNothing(TextureName) Then
        '                        ConCheck.BasePersUnit.OBTexture = Game.Content.Load(Of Texture2D)(Trim(TextureName))
        '                    End If
        '                    ' Dim ContoChange As ObjectChange.ASLXNA.UnitChange = New
        ObjectChange.ASLXNA.SetConWAc(CInt(ConCheck.BasePersUnit.Unit_ID), PassPosition)
        '                    ' ContoChange.TakeAction()
        '                    ' now part of TakeAction
        '                    ' Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(ConCheck.hexnum))
        '                    ' ConCheck.Hexlocation = CInt(GetBaseHex !terraintype)
        'always baseloc when doing mine/rubble clearance
        '                End If
        '            End If
        '            ' Else 'Concealment is not a dummy so get units and then process them which updates Concealment
        '            ' Dim ConGet As IQueryable (Of ObjectClassLibrary.ASLXNA.PersUniti) =
        CType((From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = ConCheck.BasePersUnit.Unit_ID Select selunit), Global.System.Linq.IQueryable(Of Global.ObjectClassLibrary.ASLXNA.PersUniti))
        '            ' If IsNothing (ConGet) Then
        '            ' 'error as should find something
        '            ' Else
        '            ' For Each ConUnit As ObjectClassLibrary.ASLXNA.PersUniti In ConGet
        '            ' ProcessTheSwitch(ConUnit, Hexnum)
        '            ' Next
        '            ' End If
        '        End If
        '    End If*/

    }
    private boolean IsPositionInBaseLocation (Constantvalues.AltPos UnitPosition){
        return true;
                    /*'Checks if unit is in a position considered part of basehex location but with a different value (InFoxhole, UnderWire, CrestStatus)

                    Select Case UnitPosition
                    'this may not be complete, keep adding
                    Case constantclasslibrary.
                    aslxna.Feature.Foxhole, Constantvalues.Feature.Sanger, Constantvalues.Feature.Trench,
                            Constantvalues.AltPos.AboveWire To Constantvalues.AltPos.Passenger
                    Return True
                    Case Else
                    Return False
                    End Select*/
    }
    private boolean ProcessTheSwitch (PersUniti ConUnit, Hex hextoprocess){
                    //if no TEM then WA shifts automatically else voluntarily
                    // Test if Mandatory WA (B9.323) applies and therefore unit must claim Wall Adv

        Constantvalues.AltPos PassPosition;
        if (TerrChks.IsLocationTerrainA(ConUnit.getbaseunit().getHex(), ConUnit.getbaseunit().gethexlocation(), Constantvalues.Location.Creststatustype)) {
            ConversionC CrestConvert = new ConversionC();
            PassPosition = CrestConvert.ConvertCresttoWACrest(ConUnit.getbaseunit().gethexPosition());
        } else {
            PassPosition = Constantvalues.AltPos.WallAdv;
        }
        IsSide SideChk = new IsSide();
        if (SideChk.IsWAMandatory(ConUnit.getbaseunit().getHex(), ConUnit.getbaseunit().gethexlocation(), ConUnit.getbaseunit().gethexPosition())) {

        } else {
            // claiming WA is voluntary
            //  Display the message box and save the response, Yes or No.
            /*Dim response As Integer = MsgBox("Do you wish" & Trim(ConUnit.BasePersUnit.UnitName) & " to claim Wall Advantage (Y/N)?", MsgBoxStyle.YesNo, "Enemy Unit has forfeited WA")
            If response = 6 Then 'Yes
            Else
            Return False
            End If
            End If*/
        }
        ConUnit.getbaseunit().sethexPosition(PassPosition);
        // update SW
        if (ConUnit.getbaseunit().getFirstSWLink() > 0) {UpdateSWWAStatus(ConUnit.getbaseunit().getFirstSWLink(), PassPosition);}
        if (ConUnit.getbaseunit().getFirstSWLink() > 0) {UpdateSWWAStatus(ConUnit.getbaseunit().getFirstSWLink(), PassPosition);}
        return true;
    }

    public boolean CanClaimWallAdv (Hex hextocheck, Constantvalues.Nationality NatTest){
        // Test if location/position of existingunit in hextocheck prevents WA being claimed in hextocheck adjacent to hex which has just lost WA
        // or if enemy units in hex adjacent to existingunit block gain
        // does NOT change WA; returns bool value of if gain allowed/blocked

        Locationi hexloc; boolean EnemyPresent = false; int hexBlocktest = 0;
        LinkedList<PersUniti> ConList = new LinkedList<PersUniti>(); boolean CanClaim = false;

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

    private boolean DoesConcealmentBlock (LinkedList<PersUniti> Conlist, Hex hexclickedvalue) {

        return true;
        /*'Sorts out role of Con counters in preventing WA claim
                Const AllConc As Integer = 2 :Dim ClaimReal As Boolean = False :Dim DefenderVisStatus
                As Integer = AllConc
                Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
                'since ALL units with WA in test hex are concealed, Defender unit(s) must be non-concealed or temp reveal
                'determine visibility status in Defender hex
                Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
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
                ElseIf TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, Defendersprite.TypeID)
                Then
                Dim ConGet As IQueryable (Of ObjectClassLibrary.ASLXNA.PersUniti) =
                CType((From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = Defendersprite.ObjID Select selunit), Global.System.Linq.IQueryable(Of Global.ObjectClassLibrary.ASLXNA.PersUniti))
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
                Dim AllDummy As Boolean = True :Dim MandatoryTest As Boolean :Dim Firstime As Boolean = True :
                Dim MixedMan As Boolean = False :Dim MustReveal As Boolean = False
                For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In Conlist
                If Not ConItem.MovingPersUnit.IsDummy Then
                AllDummy = False
                End If
                Dim MapGeo as mapgeoclasslibrary.
                aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                Dim Otherhexnum As List (Of Integer) =MapGeo.FillDirExtentArray(CInt(ConItem.basepersunit.hexnum))
                Dim SideChk = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
                If SideChk.
                IsWAMandatory(CInt(ConItem.basepersunit.hexnum), Otherhexnum, CInt(ConItem.basepersunit.hexlocation), CInt(ConItem.basepersunit.hexposition))
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
                CType((From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = ConItem.BasePersUnit.Unit_ID Select selunit), Global.System.Linq.IQueryable(Of Global.ObjectClassLibrary.ASLXNA.PersUniti))
                Try
                'Dim MatchUnit As DataClassLibrary.OrderofBattle = (ConGet).Where(Function(DoMatch) DoMatch.OBName = Trim(UnitName)).First
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
                End If
                        */
    }
    /*private Function RevealItem(ByVal ItemtoGet As Integer, ByRef RevealedUnit As Integer) As Boolean
            'called by DoesConcealmentBlock
                    'reveal single concealed item
    RevealedUnit = 0
            'Dim hexUnit As DataClassLibrary.OrderofBattle = Game.Linqdata.GetUnitfromCol(ItemtoGet)
    Dim hexUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = ItemtoGet Select selunit).First
    Dim conidtoget As Integer = CInt(hexUnit.BasePersUnit.Con_ID)
    Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange = New ObjectChange.ASLXNA.RevealUnitC(ItemtoGet)
            UnittoChange.TakeAction()
    RevealedUnit = hexUnit.BasePersUnit.Unit_ID
    Return True
    End Function*/
    private Constantvalues.AltPos GetCrestWAPosition(Constantvalues.AltPos crestpos){
        switch (crestpos) {
            case CrestStatus1:
            return Constantvalues.AltPos.WACrestStatus1;
            case CrestStatus2:
            return Constantvalues.AltPos.WACrestStatus2;
            case CrestStatus3:
            return Constantvalues.AltPos.WACrestStatus3;
            case CrestStatus4:
            return Constantvalues.AltPos.WACrestStatus4;
            case CrestStatus5:
            return Constantvalues.AltPos.WACrestStatus5;
            case CrestStatus0:
            return Constantvalues.AltPos.WACrestStatus0;
            default:
                return Constantvalues.AltPos.None;
        }
    }
    private boolean UpdateSWWAStatus(int SWToUse, Constantvalues.AltPos PassPosition) {
        /*Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
        Dim UpdateSW As ObjectClassLibrary.ASLXNA.SuppWeapi = (From Selsw As ObjectClassLibrary.ASLXNA.SuppWeapi In
        Scencolls.SWCol Where Selsw.BaseSW.Unit_ID = SWToUse Select Selsw).First
        UpdateSW.BaseSW.hexPosition = PassPosition
        Dim GEtBAseHex As MapDataClassLibrary.
        GameLocation = LevelChk.GetLocationatLevelInHex(CInt(UpdateSW.BaseSW.Hexnum), 0)
        UpdateSW.BaseSW.hexlocation = CInt(GEtBAseHex.Location) 'always baseloc when doing WA*/
        return true;
    }
    private boolean DoesWAinAdjacentHexPreventWAGain(Hex hextotest, Constantvalues.Nationality Natcheck ){
        // checks if any adjacent hexes have enemy WA units that prevent Natcheck from gaining WA in hextotest
        MapCommonFunctionsc mapcomfunc = new MapCommonFunctionsc();
        Hex [] Adjacenthexes = mapcomfunc.getAdjacentHexArray(hexclickedvalue);
        for (int x = 0; x < 6; x++) {
            // identify the other hex
            Hex hextotestforWA = Adjacenthexes[x];
            EnemyChecksC CheckforEnemy = new EnemyChecksC(hextotestforWA.getCenterLocation(), Natcheck, scen.getScenID());
            boolean EnemyPresent = CheckforEnemy.EnemyInHexTest(hextotestforWA);
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
}
