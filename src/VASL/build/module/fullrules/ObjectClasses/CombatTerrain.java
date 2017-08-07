package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

import java.util.ArrayList;
import java.util.LinkedList;

public class CombatTerrain  extends BaseHex {
    // from Terrain table
    private int prHexHindvalue;
    private String prhexdescvalue;  // readonly
    // from passing routine
    private Constantvalues.Hexrole prHexrolevalue;
    private ArrayList prLOSTarget = new ArrayList();  // holds value of eventual target hex - used in CombatFPandDRM
    private boolean prHexLOSHApplies;
    private int prHexsideCrossedTEM;
    private String prHexsideCrossedDesc;
    private int prSolutionID;
    private int prHexTEM;

    // Constructors

    public CombatTerrain(String PassHexname, int PassHexID, int PasshexTerrtype, int PassHexside1, int PassHexside2, int PassHexside3,
                         int PassHexside4, int PassHexside5, int PassHexside6, int PassHexTEM, int PassHexHind, String Passhexdesc,
                         Constantvalues.Hexrole PassHexrole, String Passstaircase, double PassBaseLevel, String Passcontrol, double PassTargetID,
                         LinkedList<SmokeHolder> PassSmokelist, int PassOBA) {
        // called by Linqdata.AddtoCollection
        // creates new CombatTerrain object for any hex involved in combat, passes Staircase and Control to base class constructor but does not use them
        super(PassHexname, PassHexID, PasshexTerrtype,
                PassHexside1, PassHexside2, PassHexside3, PassHexside4,
                PassHexside5, PassHexside6, Passstaircase, PassBaseLevel, Passcontrol, PassSmokelist, PassOBA);
        prHexTEM = PassHexTEM;
        prHexHindvalue = PassHexHind;
        prhexdescvalue = Passhexdesc;
        prHexrolevalue = PassHexrole;
        prHexLOSHApplies = false;
        prHexsideCrossedTEM = 0;
        prHexsideCrossedDesc = "";
        prLOSTarget.add(PassTargetID);
    }

    // thread version
    public CombatTerrain(String PassHexname, int PassHexID, int PasshexTerrtype, int PassHexside1, int PassHexside2, int PassHexside3,
                         int PassHexside4, int PassHexside5, int PassHexside6, int PassHexTEM, int PassHexHind, String Passhexdesc,
                         Constantvalues.Hexrole PassHexrole, String Passstaircase, double PassBaseLevel, String Passcontrol, double PassTargetID,
                         int PassthreadLOCindex, int PassSolid, LinkedList<SmokeHolder> PassSmokelist, int PassOBA) {
        // called by Linqdata.AddtoCollection
        // creates new CombatTerrain object for any hex involved in combat, passes Staircase and Control to base class constructor but does not use them
        super(PassHexname, PassHexID, PasshexTerrtype,
                PassHexside1, PassHexside2, PassHexside3, PassHexside4,
                PassHexside5, PassHexside6, Passstaircase, PassBaseLevel, Passcontrol, PassthreadLOCindex, PassSmokelist, PassOBA);
        prHexTEM = PassHexTEM;
        prHexHindvalue = PassHexHind;
        prhexdescvalue = Passhexdesc;
        prHexrolevalue = PassHexrole;
        prHexLOSHApplies = false;
        prHexsideCrossedTEM = 0;
        prHexsideCrossedDesc = "";
        prLOSTarget.add(PassTargetID);
        prSolutionID = PassSolid;
    }
    public Constantvalues.Hexrole getHexrole() {return prHexrolevalue;}
    public void setHexrole(Constantvalues.Hexrole value) {prHexrolevalue=value;}
    public int getHexTEM() {return prHexTEM;}
    public void setHexTem(int value){prHexTEM=value;}
    public ArrayList getTargetID() {return prLOSTarget;}
    public void setTargetID(ArrayList value) {prLOSTarget=value;}
    public int getHexHind() {return prHexHindvalue;}
    public void setHexHind(int value) {prHexHindvalue=value;}
    public String gethexdesc() {return prhexdescvalue;}
    public boolean getHexLOSHApplies() {return prHexLOSHApplies;}
    public void setHexLOSHApplies(boolean value) {prHexLOSHApplies=value;}
    public int getHexsideCrossedTEM() {return prHexsideCrossedTEM;}
    public String getHexsideCrosseddesc() {return prHexsideCrossedDesc;}
    public int getSolID () {return prSolutionID;}
    public void setSolID(int value) {prSolutionID=value;}

    public boolean IsFirer() {
        boolean CheckIsFirer = (prHexrolevalue == Constantvalues.Hexrole.Firer ||
                prHexrolevalue == Constantvalues.Hexrole.FirerInt ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTarget ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTargetInt) ? true: false;
                return CheckIsFirer;
    }
    //remmed out while debugging undo
    /*Public Function IsIntervening() As Boolean
    IsIntervening = If(_Hexrolevalue = constantclasslibrary.aslxna.hexrole.Intervening Or _Hexrolevalue =
            constantclasslibrary.aslxna.hexrole.FirerInt Or _Hexrolevalue = constantclasslibrary.aslxna.hexrole.TargetInt Or
            _Hexrolevalue = constantclasslibrary.aslxna.hexrole.FirerTargetInt, True, False)
    End Function
    Public Function IsTarget() As Boolean
    IsTarget = If(_Hexrolevalue = constantclasslibrary.aslxna.hexrole.Target Or _Hexrolevalue =
            constantclasslibrary.aslxna.hexrole.TargetInt Or _Hexrolevalue = constantclasslibrary.aslxna.hexrole.FirerTarget Or
            _Hexrolevalue = constantclasslibrary.aslxna.hexrole.FirerTargetInt, True, False)
    End Function
    Public Function TargetHexSideCrossed(ByVal lasthexnum As Integer, ByVal Firsthexnum As Integer) As Integer
        'Called by Combat.CalcCombatFPandDRM
                'returns value of hexside terrain type: 6500 if none
                'and if hexside terrain exists, sets values of
                'HexsideCrossedTEM and HexsideCrossedDesc properties
                'determine which hexside crossed (1-6)
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists    Mapgeo.MapBtype, Mapgeo.Xoffset, Mapgeo.Yoffset, Mapgeo.MaxCols, Mapgeo.MaxRows)
    Dim hexvalue As Integer = MapGeo.HexSideEntry(lasthexnum, Firsthexnum)
    TargetHexSideCrossed = ConstantClassLibrary.ASLXNA.Hexside.NoTerrain  'default value - 6500
            'determine hexside type of hexside crossed
    Select Case hexvalue
    Case 1
    If Me.Hexside1 <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then TargetHexSideCrossed = Me.Hexside1
    Case 2
    If Me.Hexside2 <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then TargetHexSideCrossed = Me.Hexside2
    Case 3
    If Me.Hexside3 <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then TargetHexSideCrossed = Me.Hexside3
    Case 4
    If Me.Hexside4 <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then TargetHexSideCrossed = Me.Hexside4
    Case 5
    If Me.Hexside5 <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then TargetHexSideCrossed = Me.Hexside5
    Case 6
    If Me.Hexside6 <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then TargetHexSideCrossed = Me.Hexside6
    Case Else
    TargetHexSideCrossed = ConstantClassLibrary.ASLXNA.Hexside.NoTerrain  'default value - 6500
    End Select
        'if not No Terrain then determine TEM and Desc of hexside crossed
    If TargetHexSideCrossed <> ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then
        'set property values
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(LocationCol)
    _HexsideCrossedTEM = CInt(SideTest.GetSideData(ConstantClassLibrary.ASLXNA.TerrFactor.HexsideTEM, TargetHexSideCrossed, Maptables))
    _HexsideCrossedDesc = SideTest.GetSideData(ConstantClassLibrary.ASLXNA.TerrFactor.Hexsidedesc, TargetHexSideCrossed, Maptables)
    End If
    End Function
    Public Function GetScenFeatTEM(ByRef FeatureName As String) As Integer
        'called by IFT.Combatdrm
                'returns TEM a of cumulative Scenario Terrain in a hex
                'along with description
                'THIS NEEDS TO BE REWORKED AS MORE SCEN FEATURES GET MOVED INTO MAP TABLE (Smoke etc)
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(LocationCol)
    Dim TempScenFeatTEM As Integer = 0  'temp variables used to store deta
    Dim TempFeatureName As String = ""
    Dim FirerHex As Boolean = False
    GetScenFeatTEM = 0
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
    If IsNothing(Linqdata.ScenFeatcol) Then '.Count = 0 Then
            ' no scenario features currently exist in this scenario
    GetScenFeatTEM = 0 : FeatureName = ""
            'MsgBox("No Scenario Terrain currently exists in the game", , "TerrainActions.GetScenFeatTEMLOSH")
    Else
    For Each ScenFeat In Linqdata.ScenFeatcol
        'need to check each ScenFeat as more than one can
                'exist per hex (ie foxhole and wire) - BUT TEM NOT CUMULATIVE
                'reset temp variables
    TempScenFeatTEM = 0 : TempFeatureName = ""
            'check for hex match
    If ScenFeat.Hexnumber = Me.HexID Then
        'get type of terrain found
    If Me.IsTarget Then
    If Not IsTerrChk.IsLocationTerrainA(CInt(ScenFeat.LocIndex), ConstantClassLibrary.ASLXNA.Location.HindranceFeature) Then
            TempScenFeatTEM = ScenFeat.GetTEM(TempFeatureName)
    Else
        'TempScenFeatTEMLOSH = ScenFeat.GetLOSH(False, True, TempFeatureName, OBALOSH)
                'TargetLOSH = TempScenFeatTEMLOSH
                'TargLOSHName = TempFeatureName & Space(1) & TargLOSHName
    End If
    Else
    If Me.IsFirer Then FirerHex = True
        'TempScenFeatTEMLOSH = ScenFeat.GetLOSH(FirerHex, False, TempFeatureName, OBALOSH)
    End If
    If TempScenFeatTEM > 0 Then
    GetScenFeatTEM += TempScenFeatTEM
    FeatureName += TempFeatureName & Space(1)
    End If
    End If
    Next
        ''check for Smoke
        'Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
                'Dim LocToUse As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(Me.LocIndex)
                'If LocToUse.Smoke > 0 Then 'Smoke in location
        '    'determine TEM / LOSH drm
        '    Select Case LocToUse.Smoke
                '        Case EnumCon.VisHind.BlazeStone, EnumCon.VisHind.BlazeWood, EnumCon.VisHind.GunSmoke, EnumCon.VisHind.OBASmoke
                '            GetScenFeatTEMLOSH += 3
                '        Case EnumCon.VisHind.GreyDisp, EnumCon.VisHind.GunSmokeDisp, EnumCon.VisHind.GreyWP, EnumCon.VisHind.GunSmokeDisp,
                '            EnumCon.VisHind.GunWP, EnumCon.VisHind.InfSmoke, EnumCon.VisHind.OBASmokeDisp, EnumCon.VisHind.VehDust
                '            GetScenFeatTEMLOSH += 2
                '        Case EnumCon.VisHind.GreyWPDisp, EnumCon.VisHind.GunWPDisp, EnumCon.VisHind.InfWP
                '            GetScenFeatTEMLOSH += 1
                '        Case Else
                '            GetScenFeatTEMLOSH += 0
                '    End Select
                'End If
                'If OBALOSH Then
                '    GetScenFeatTEMLOSH += 1
                '    FeatureName &= "OBA LOSH"
                'End If
                'Trim(FeatureName)
    End If
    End Function
    Public Sub TargetHexdrm(ByRef TEMdrm As Integer, ByRef TotalLocationLOSHdrm As Integer, ByRef Hexsidedrm As Integer, ByRef Featuredrm As Integer, ByRef DRMList As List(Of DataClassLibrary.ASLXNA.IFTMods), ByVal TargetUnit As PersUniti, ByVal hexspinedrm As Integer,
    ByVal Terrainname As String, ByVal SideTerrainname As String, ByVal Featurename As String, ByRef TotalLOSLOSHdrm As Integer, ByVal alreadymoved As Boolean, ByVal terraintest As Boolean,
    ByRef hexsidetest As Boolean, ByVal FirerBaseLevel As Single, ByVal Firerinhexlevel As Single, ByRef TargLOSHName As String, ByVal TargetLOSH As Integer, ByVal FireGroup_Thread As List(Of ObjectClassLibrary.ASLXNA.PersUniti))

    Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation)
    Dim NewDb As MapDataClassLibrary.MapDataClassesDataContext = New MapDataClassLibrary.MapDataClassesDataContext
            Try
    Dim LOCCol As IQueryable(Of MapDataClassLibrary.GameLocation) = From QU As MapDataClassLibrary.GameLocation In NewDb.GameLocations Select QU
            MapCol = LOCCol
    Catch ex As Exception
        ' MsgBox("Some kind of error" & ASLMapLink, , "CreateMapCollection Failure")
    End Try
    Dim TerrChk As TerrainClassLibrary.ASLXNA.TerrainChecks = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
    Dim NewDRM As DataClassLibrary.ASLXNA.IFTMods

    Dim PositiveDRM As Boolean = False
    Dim ScenFeatureTest As Boolean = False
    If Featuredrm > 0 Then ScenFeatureTest = True

        'LOSH does not apply except for smoke/OBAFFE
                'determine if LOS blocked by LOSH

                'MOVE THESE NEXT TWO LINES BACK TO COMBATCALC.COMBATDRM? AUG 14
    TotalLocationLOSHdrm += TargetLOSH
    TotalLOSLOSHdrm += TotalLocationLOSHdrm
        'determine any TEM
    If hexspinedrm > Hexsidedrm Then
    Hexsidedrm = hexspinedrm
            hexsidetest = True
    End If




    If terraintest And hexsidetest And ScenFeatureTest Then 'hex is not open ground, hexside obstacle, scenario feature present
    If TEMdrm >= Hexsidedrm And TEMdrm >= Featuredrm Then 'use hex TEM
    Featurename = "" : Featuredrm = 0 : SideTerrainname = "" : Hexsidedrm = 0
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    ElseIf Hexsidedrm > TEMdrm And Hexsidedrm >= Featuredrm Then  'use hexside
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Hexsidedrm, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Hexsidedrm = 0
        'MessageBox.Show(SideTerrainName & " in " & me.HexName & " already added to DRM")
    End If
        'check for Runway/Boulevard/City Square -1
    If TEMdrm = -1 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    End If
        'MsgBox("Using Wall Advantage", , "Calculating Terrain DRM")
    Featurename = "" : Featuredrm = 0 : Terrainname = "" : TEMdrm = 0
    ElseIf Featuredrm > TEMdrm And Featuredrm > Hexsidedrm Then 'use feature
    Terrainname = "" : TEMdrm = 0 : SideTerrainname = "" : Hexsidedrm = 0
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Feature) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Featuredrm, ConstantClassLibrary.ASLXNA.IFTdrm.Feature, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Featuredrm = 0
        'MessageBox.Show(FeatureName & " in " & me.HexName & " already added to DRM")
    End If
        'check for Runway/Boulevard/City Square -1
    If TEMdrm = -1 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    End If
    Else
        'MsgBox("Need to check what conditions brought us here in CALCFP")
    End If
    ElseIf terraintest And hexsidetest And Not (ScenFeatureTest) Then 'hex is not open ground, hexside obstacle, no scenario feature
    If TEMdrm >= Hexsidedrm Then 'use hex TEM
    SideTerrainname = "" : Hexsidedrm = 0
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    Else  'use hexside
            'MsgBox("Using Wall Advantage", , "Calculating Terrain DRM")
    Terrainname = "" : TEMdrm = 0
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Hexsidedrm, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Hexsidedrm = 0
        'MessageBox.Show(SideTerrainName & " in " & me.HexName & " already added to DRM")
    End If
        'check for Runway/Boulevard/City Square -1
    If TEMdrm = -1 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    End If
    End If
    Featurename = "" : Featuredrm = 0
    ElseIf terraintest And Not (hexsidetest) And ScenFeatureTest Then 'hex is not open ground, scenario feature present, hexside clear
    If TEMdrm >= Featuredrm Then 'use hex TEM
    Featurename = "" : Featuredrm = 0
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    Else  'use FeatureTEM
    Terrainname = "" : TEMdrm = 0
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Feature) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Featuredrm, ConstantClassLibrary.ASLXNA.IFTdrm.Feature, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Featuredrm = 0
        'MessageBox.Show(FeatureName & " in " & me.HexName & " already added to DRM")
    End If
        'check for Runway/Boulevard/City Square -1
    If TEMdrm = -1 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    End If
    End If
    SideTerrainname = "" : Hexsidedrm = 0
    ElseIf Not (terraintest) And hexsidetest And ScenFeatureTest Then 'hexside obstacle with scenario feature and open ground hex
    If Hexsidedrm >= Featuredrm Then  'use hexside TEM
            'MsgBox("Using Wall Advantage", , "Calculating Terrain DRM")
    Featuredrm = 0 : Featurename = ""
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Hexsidedrm, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Hexsidedrm = 0
        'MessageBox.Show(SideTerrainName & " in " & me.HexName & " already added to DRM")
    End If
    Else  'use Feature TEM
    Hexsidedrm = 0 : SideTerrainname = ""
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Feature) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Featuredrm, ConstantClassLibrary.ASLXNA.IFTdrm.Feature, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Featuredrm = 0
        'MessageBox.Show(FeatureName & " in " & me.HexName & " already added to DRM")
    End If
    End If
    TEMdrm = 0 : Terrainname = ""
    ElseIf terraintest And Not (hexsidetest) And Not (ScenFeatureTest) Then 'hex is not open ground and hexside is clear, no scenario feature
            'Use TEM
    Hexsidedrm = 0 : SideTerrainname = "" : Featuredrm = 0 : Featurename = ""
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TEMdrm, ConstantClassLibrary.ASLXNA.IFTdrm.Terrain, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show(TerrainName & " in " & me.HexName & " already added to DRM")
    End If
    ElseIf Not (terraintest) And hexsidetest And Not (ScenFeatureTest) Then 'hexside obstacle and open ground hex, no scenario feature
    TEMdrm = 0 : Terrainname = "" : Featuredrm = 0 : Featurename = "" 'use hexside
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Hexsidedrm, ConstantClassLibrary.ASLXNA.IFTdrm.Hexside, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Hexsidedrm = 0
        'MessageBox.Show(SideTerrainName & " in " & me.HexName & " already added to DRM")
    End If
    ElseIf Not (terraintest) And Not (hexsidetest) And ScenFeatureTest Then  'scenario feature and hexside clear, hex is open ground
    TEMdrm = 0 : Terrainname = "" : Hexsidedrm = 0 : SideTerrainname = "" 'use feature
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.Feature) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(Featuredrm, ConstantClassLibrary.ASLXNA.IFTdrm.Feature, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            Featuredrm = 0
        'MessageBox.Show(FeatureName & " in " & me.HexName & " already added to DRM")
    End If
    ElseIf Not (terraintest) And Not (hexsidetest) And Not (ScenFeatureTest) Then  'hex is open ground, hexside clear, no scenario feature
    TEMdrm = 0 : Terrainname = "" : Hexsidedrm = 0 : SideTerrainname = "" : Featuredrm = 0 : Featurename = "" 'use none
    Dim Targterraintype = 0 : Dim Targbaselevel As Single = Me.HexBaseLevel
    Dim TempFG As Boolean = False : Dim TempInhex As Single = 0
    For Each FiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup_Thread
            TempInhex = FiringUnit.BasePersUnit.LevelinHex
    If TempInhex > Firerinhexlevel Then Firerinhexlevel = TempInhex
            Next
    Dim TotalFirerlevel As Single = FirerBaseLevel + Firerinhexlevel
    If TotalFirerlevel < (Targbaselevel + TargetUnit.BasePersUnit.LevelinHex) Then
        'HA applies when Target above Firer and no other TEM
    TEMdrm = 1 : Terrainname = "Height Advantage"
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.HA) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(1, ConstantClassLibrary.ASLXNA.IFTdrm.HA, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TEMdrm = 0
        'MessageBox.Show("HA in " & me.HexName & " already added to DRM")
    End If
    End If
    Else
        'MsgBox("How in heck did we end up here?", , "Calculating which Terrain drm to use")
    End If
        'Vehicle TEM
    TEMdrm += VehicleTEMCheck(DRMList, TargetUnit)
        'FFMO check
    For Each IFTdrmTest As DataClassLibrary.ASLXNA.IFTMods In DRMList
    If IFTdrmTest.DRM > 0 Then PositiveDRM = True : Exit For
    Next
    If Not (PositiveDRM) AndAlso (TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moving Or TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving Or TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Wading Or TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.TI And alreadymoved = False) Then
    If TerrChk.IsLocationOGforFFMO(TargetUnit.BasePersUnit.hexlocation, TargetUnit.BasePersUnit.LOCIndex, TargetUnit.BasePersUnit.hexPosition) Then
        'all FFMO conditions exist
    TEMdrm += -1
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.FFMO) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(-1, ConstantClassLibrary.ASLXNA.IFTdrm.FFMO, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
    TEMdrm += 1
            'MessageBox.Show("FFMO in " & ComTer.HexName & " already added to DRM")
    End If
    End If
    End If
        'Now do FFNAM
    If TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moving Or TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Wading Or TargetUnit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.TI And
    alreadymoved = False Then
    TEMdrm += -1
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.FFNAM) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(-1, ConstantClassLibrary.ASLXNA.IFTdrm.FFNAM, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
    TEMdrm += 1
            'MessageBox.Show("FFNAM in " & me.HexName & " already added to DRM")
    End If
    End If
        'Now do Target has a FT
    If TargetUnit.TargetPersUnit.HasFT Then
    TEMdrm -= 1
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.TargHasFT) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(-1, ConstantClassLibrary.ASLXNA.IFTdrm.TargHasFT, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
    TEMdrm += 1
            'MessageBox.Show("FT in " & me.HexName & " already added to DRM")
    End If
    End If
        'End If
                'Now do LOSH in target hex
    If TargetLOSH > 0 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.TargLOSH) Then
    NewDRM = New DataClassLibrary.ASLXNA.IFTMods(TargetLOSH, ConstantClassLibrary.ASLXNA.IFTdrm.TargLOSH, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NewDRM)
    Else
            TargetLOSH = 0
        'MessageBox.Show(TargLOSHName & " in " & me.HexName & " already added to DRM")
    End If
    End If
    End Sub
    Public Function NotAlreadyAddedToDRMList(ByVal ListtoCheck As List(Of DataClassLibrary.ASLXNA.IFTMods), ByVal targetUnit As ObjectClassLibrary.ASLXNA.PersUniti, ByVal Testdrmtype As Integer) As Boolean
        'called by CombatDRM
                'determines if LOSH or Terrain DRM already added by a unit in the same target hex
    If IsNothing(ListtoCheck) Then Return True 'if nothing in list then nothing added so true
    If Me.IsIntervening() Then
    For Each DRMTest As DataClassLibrary.ASLXNA.IFTMods In ListtoCheck
    If targetUnit.BasePersUnit.LOCIndex = DRMTest.TargetLocIndex And DRMTest.DRMType = Testdrmtype And DRMTest.DRMLocIndex = Me.LocIndex Then Return False
    Next
    ElseIf Me.IsTarget Then
    For Each DRMTest As DataClassLibrary.ASLXNA.IFTMods In ListtoCheck
    If targetUnit.BasePersUnit.LOCIndex = DRMTest.DRMLocIndex And Me.Hextertype = targetUnit.BasePersUnit.hexlocation And DRMTest.DRMType = Testdrmtype Then Return False
    Next
    End If
        'if this far then not already added
    Return True
    End Function
    Public Sub SetTargetVariables(ByVal TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti, ByVal lasthexnum As Integer, ByRef hexsidetest As Boolean, ByRef TEMdrm As Integer, ByVal Maptableinstance As MapDataClassLibrary.ASLXNA.MapDataC, ByRef Terrainname As String,
                                  ByRef Terraintest As Boolean, ByRef hexsidetype As Integer, ByVal LOSFollows As Integer, ByVal Seehexnum As Integer)
        'working with target hex so need to determine where Target is
    Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation)
    Dim NewDb As MapDataClassLibrary.MapDataClassesDataContext = New MapDataClassLibrary.MapDataClassesDataContext
            Try
    Dim LOCCol As IQueryable(Of MapDataClassLibrary.GameLocation) = From QU As MapDataClassLibrary.GameLocation In NewDb.GameLocations Select QU
            MapCol = LOCCol
    Catch ex As Exception
        ' MsgBox("Some kind of error" & ASLMapLink, , "CreateMapCollection Failure")
    End Try
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)  'Game.Scenario.LocationCol)
    Dim Maphex As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(Me.LocIndex)  'HexID, ComTer.Hextertype)
    Dim TerrChk As TerrainClassLibrary.ASLXNA.TerrainChecks = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
            'position is more specfic so takes precedence over Location if >0
    If TargetUnit.BasePersUnit.hexPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv Or (TargetUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And TargetUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6) Then
    If lasthexnum <> Me.HexID Then
        'check hexside of entry into target hex
                'no need to check other hexes as LOS has already cleared hexsides of intervening hexes
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Dim SideChk = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    Dim hexsidevalue As Integer = 0 : Dim IsWallSide As Boolean = False
    If LOSFollows = ConstantClassLibrary.ASLXNA.LOS.AltHexGrain Or LOSFollows = ConstantClassLibrary.ASLXNA.LOS.VertHexGrain Then
    Dim SidesToCheck(5) As Integer
        MapGeo.DetermineSidesToCheck(Seehexnum, Me.HexID, SidesToCheck)
    For x = 1 To 2  'test entry sides only
    IsWallSide = SideChk.IsAWallHedgeRdBlk(SidesToCheck(x), Me.LocIndex)
    If IsWallSide Then Exit For
            Next
    Else
            hexsidevalue = MapGeo.HexSideEntry(lasthexnum, Me.HexID)
    IsWallSide = SideChk.IsAWallHedgeRdBlk(hexsidevalue, Me.LocIndex)
    End If
    If IsWallSide Then
            hexsidetest = True 'use hexside TEM; actual value set in later
            'Hexsidetype = ComTer.TargetHexSideCrossed(lasthexnum, ComTer.HexID)
            'Hexsidedrm = ComTer.HexsideCrossedTEM
            ''TEMdrm = CInt(TerrChk.GetPositionData(EnumCon.TerrFactor.TEM, CInt(TargetUnit.hexposition), Game.Scenario.Maptables))
            'TerrainName = TerrChk.GetPositionData(EnumCon.TerrFactor.Desc, CInt(TargetUnit.hexposition), Game.Scenario.Maptables)
    Else  'use OG even if in obstacle hex
    TEMdrm = CInt(Maphex.TEM)
    If Maphex.TEM < 0 Then
            TEMdrm = CInt(Maphex.TEM)
    Else
            TEMdrm = 0
    End If
    End If
    End If
    ElseIf TargetUnit.BasePersUnit.hexPosition > 0 Then
        'use position TEM
    If TargetUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest1 And TargetUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest6 Then
        'if exitedcrest then use location TEM
    TEMdrm = CInt(Maphex.TEM)
    Else
            TEMdrm = CInt(TerrChk.GetPositionData(ConstantClassLibrary.ASLXNA.TerrFactor.TEM, CInt(TargetUnit.BasePersUnit.hexPosition), Maptableinstance))
    Terrainname = TerrChk.GetPositionData(ConstantClassLibrary.ASLXNA.TerrFactor.Desc, CInt(TargetUnit.BasePersUnit.hexPosition), Maptableinstance)
    End If
    Else
        'Use location tem or otherterrain tem
    If TargetUnit.BasePersUnit.hexlocation = Maphex.Location Then
    TEMdrm = CInt(Maphex.TEM)
    ElseIf TargetUnit.BasePersUnit.hexlocation = Maphex.OtherTerraininLocation Then
    Terrainname = TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.Desc, CInt(Maphex.OtherTerraininLocation), Maptableinstance)
    TEMdrm = CInt(Maphex.TEMOtherTerrain)
    End If
    End If
    If TEMdrm <> 0 Then Terraintest = True
        'special case; any others?
    If TEMdrm = 0 And Me.Hextertype = ConstantClassLibrary.ASLXNA.Location.CitySquareShellhole Then Terraintest = True
        'now check hexside
                'need to deal with adjacent to firer hex special case
    If lasthexnum = 0 Then
        'lasthex is firerhex
                'firer/target on either side of wall, etc
                'now need to determine who has wall advantage
                'need to come back to this.
    MsgBox("Routine now needs to determine Wall Advantage. Not yet programmed.", , "CalcFP: Adjacent Units")
    ElseIf lasthexnum <> 0 And lasthexnum <> Me.HexID Then
        'check hexside of entry into target hex
                'no need to check other hexes as LOS has already cleared hexsides of intervening hexes
    hexsidetype = Me.TargetHexSideCrossed(lasthexnum, Me.HexID)
    If hexsidetype > ConstantClassLibrary.ASLXNA.Hexside.NoTerrain Then hexsidetest = True
        'hexvalue = Mainform.Scenario.Map.HexSideEntry(lasthex, ComTer.HexName)
                'MsgBox(lasthex & Space(2) & ComTer.HexName & Space(2) & CStr(hexvalue))
    End If
    End Sub
    Public Sub InterveningDRM(ByVal VisLOSH As Integer, ByRef FinalLOSHDRM As Integer, ByRef LOSHdrm As Integer, ByVal Map As Integer, ByRef LOSHName As String, ByRef TerrainName As String, ByRef Featuredrm As Integer, ByRef Featurename As String,
                              ByRef FinalLOSHName As String, ByVal LOSAlongHexside As Boolean, ByVal FirerBaseLevel As Single, ByVal Firerinhexlevel As Single, ByRef FinalVisLOSHName As String, ByRef VisLOSHname As String, ByRef FinalVisLOSH As Integer,
                              ByRef hexspinedrm As Integer, ByVal FireGroupThread As List(Of ObjectClassLibrary.ASLXNA.PersUniti), ByRef DRMList As List(Of DataClassLibrary.ASLXNA.IFTMods), ByVal TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti,
    ByRef TotalLocationLOSHdrm As Integer, ByRef TotalLOSLOSHdrm As Integer, ByVal TargetTotalLevel As Single, ByRef Lasthexloshdrm As Integer, ByVal lasthexnum As Integer, ByRef UseAltName As String,
    ByVal AltHexesInLoSLIst As List(Of ObjectClassLibrary.ASLXNA.AltHexGTerrain), ByVal SolID As Integer, ByVal CombatTerrCol As List(Of CombatTerrain), ByVal SeeHexNum As Integer, ByVal SeenHexnum As Integer)
    Dim UsingComTer As CombatTerrain
    Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation)
    Dim NewDb As MapDataClassLibrary.MapDataClassesDataContext = New MapDataClassLibrary.MapDataClassesDataContext
            Try
    Dim LOCCol As IQueryable(Of MapDataClassLibrary.GameLocation) = From QU As MapDataClassLibrary.GameLocation In NewDb.GameLocations Select QU
            MapCol = LOCCol
    Catch ex As Exception
        ' MsgBox("Some kind of error" & ASLMapLink, , "CreateMapCollection Failure")
    End Try
    Dim TerrChk As TerrainClassLibrary.ASLXNA.TerrainChecks = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
    Dim IsTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
    Dim Maptableinstance As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    Dim NEWDrm As DataClassLibrary.ASLXNA.IFTMods
    Dim FinalFeatureDRM As Integer = 0
    Dim Lastlocindex As Integer = 0 'holds value of last hex checked LocIndex
            'existence of hex Hindrance set in LOS routine; check for value now
    FinalLOSHDRM = 0
    If Me.HexLOSHApplies Then
        'will not apply to Alternate Hex Grain hexes as .HexLOSHApplies is not set by LOS routine for such hexes
    LOSHdrm = Me.HexHind
    If Map = ConstantClassLibrary.ASLXNA.Map.BloodReef Then
    MsgBox("Blood Reef hinterland situation)")
        'LOSHAppliesTo will be true but HexHind should be 0 for open ground - both values set in Map.HexbyHexMoveAlongOK
    LOSHdrm = +1
            'This assumes that all such cases will be Hinterland and not just a plain error
            'Could add an error checking routine - confirm that one of firer/target hex is ocean but have not done so at this stage
            'need to change LOSHname
    LOSHName = "Hinterland"
    Else
            LOSHName = Me.hexdesc
    End If
    TerrainName = ""
    Else
            LOSHName = ""
    End If

    Firerinhexlevel = 99 : Dim TempInhex As Single = 0
    For Each FiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroupThread
            TempInhex = FiringUnit.BasePersUnit.LevelinHex
    If TempInhex <= Firerinhexlevel Then Firerinhexlevel = TempInhex
            Next
    Dim TotalFirerlevel As Single = FirerBaseLevel + Firerinhexlevel
        'now see if althexgrain needs to be checked
    Dim hexterrtype As Integer : Dim GetAltLOSH As Integer : Dim AHGAlreadyDone As Boolean = False
    Dim AltHextoCheck As Integer = 0 : Dim AltFeatLOSH As Integer : Dim AltFeatName As String = "" : Dim OBALOSH As Boolean = False
    Dim WhichLOSHToUse As Integer = 0 : Dim currenthexdrm As Integer = 0 : Dim alternatehexdrm As Integer = 0
    Dim AltVisLOSH As Integer = 0 : Dim AltVisName As String = "" : Dim AltOBAAlreadyFound As Boolean = False
    UsingComTer = Me
    If LOSAlongHexside Then
    For Each AHGHex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In AltHexesInLoSLIst  'ValidSol.AltHexesInLOS   'Game.Scenario.IFT.AltHexLOSGroup
    If Me.HexID = AHGHex.UpperHexnum Then
    AltHextoCheck = AHGHex.LowerHexnum : Exit For
    ElseIf Me.HexID = AHGHex.LowerHexnum Then
    AltHextoCheck = AHGHex.UpperHexnum : Exit For
    End If
    Next
    If AltHextoCheck > 0 Then
    Dim althexobstaclelevel As Single = 0 : Dim altHexBAselevel As Single = 0
    GetAltLOSH = 0
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(AltHextoCheck, 0)
    If IsTerrChk.IsLocationTerrainA(Baselocation.LocIndex, ConstantClassLibrary.ASLXNA.Location.HindranceHex) Then
    hexterrtype = Baselocation.Location
            altHexBAselevel = Baselocation.Baselevel
    althexobstaclelevel = Baselocation.ObstacleHeightinhex
            GetAltLOSH = Baselocation.LOSHdrm
    Else
            GetAltLOSH = 0
    End If
        'code adapted from Map.DoesScenarioTerrainBlockLoS
    If Not IsNothing(Linqdata.ScenFeatcol) Then
    For Each ScenFeat As DataClassLibrary.ScenarioTerrain In Linqdata.ScenFeatcol
        'need to check each ScenFeat as more than one can exist per hex
                'this routine only needs to check for LOSH; LOS blocks already done;
                'check for hex match
    If ScenFeat.Hexnumber <> AltHextoCheck Then Continue For
        'get type of terrain found; last two are passed by reference
    AltFeatLOSH = ScenFeat.GetLOSH(False, False, AltFeatName, OBALOSH)
    If OBALOSH Then AltFeatLOSH += 1
    Next
            Else
        ' no scenario features currently exist in this scenario
                'MsgBox("No Scenario Terrain currently exists in the game", , "IFT.CombatDRM")
    End If

    If Featuredrm > 0 Then
    If DoesScenLOSHApplytothisLOS(Featuredrm, Featurename, TotalFirerlevel, TargetTotalLevel) Then
    TerrainName = ""
    Else
            Featuredrm = 0 : Featurename = ""
    End If
    End If
    Dim TempFG As Boolean = False
        'revised Feb 13
    Dim hexobstaclelevel As Single = 0 : Dim hexsidelevel As Single = 0
    Dim ComTerlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Me.HexID, 0)
    hexterrtype = ComTerlocation.Location
            altHexBAselevel = ComTerlocation.Baselevel
    hexobstaclelevel = ComTerlocation.ObstacleHeightinhex
    Dim Altlocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(AltHextoCheck, 0)
    If ComTerlocation.Baselevel > Altlocation.Baselevel Then
    hexsidelevel = Altlocation.Baselevel
            Else
    hexsidelevel = ComTerlocation.Baselevel
    End If
    Dim LOSHTotalLevel As Single = hexsidelevel
    If TotalFirerlevel > LOSHTotalLevel Or TargetTotalLevel > LOSHTotalLevel Then
    Me.HexHind = 0 : LOSHName = "" : GetAltLOSH = 0
            'MsgBox("LOSH does not apply as LOS goes over Hindrance", , "CombatDRM")
    End If
    If AltFeatLOSH > 0 Then
    For Each TempComTer As CombatTerrain In CombatTerrCol
    If TempComTer.HexID = AltHextoCheck Then
    If DoesScenLOSHApplytothisLOS(AltFeatLOSH, AltFeatName, TotalFirerlevel, TargetTotalLevel) Then
    TerrainName = ""
    Else
            AltFeatLOSH = 0 : AltFeatName = ""
    End If
    End If
    Next
    End If
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)
    Dim PassHexdesc As String = TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.Desc, hexterrtype, Maptableinstance)
    Dim Smokelist As List(Of ObjectClassLibrary.ASLXNA.SmokeHolder) = SmokePresentinHex(Baselocation.Hexnum, GetLocs)
    Dim NextTempComTer = New CombatTerrain(UseAltName, AltHextoCheck, Baselocation.Location, Baselocation.Hexside1, Baselocation.Hexside2,
                                           Baselocation.Hexside3, Baselocation.Hexside4, Baselocation.Hexside5, Baselocation.Hexside6, Baselocation.TEM, Baselocation.LOSHdrm,
                                           UseAltName, ConstantClassLibrary.ASLXNA.Hexrole.Intervening, CStr(Baselocation.HasStair), Baselocation.Baselevel, "", TargetUnit.BasePersUnit.LOCIndex, Baselocation.LocIndex, SolID, Smokelist, Baselocation.OBA)
    If NextTempComTer.HexID = AltHextoCheck Then
    AltVisLOSH = CalcVisLOSH(AltVisName, AltOBAAlreadyFound, NextTempComTer, TotalFirerlevel, TargetTotalLevel, SeeHexNum, SeenHexnum)
    If AltVisLOSH = 0 Then AltVisName = ""
    End If

    If Featuredrm <> 0 Or AltFeatLOSH <> 0 Or Me.HexHind <> 0 Or GetAltLOSH <> 0 Or VisLOSH <> 0 Or AltVisLOSH <> 0 Then
        'there is a LOSH modifier somewhere
    currenthexdrm = Me.HexHind + Featuredrm + VisLOSH
            alternatehexdrm = GetAltLOSH + AltFeatLOSH + AltVisLOSH
    WhichLOSHToUse = If(currenthexdrm >= alternatehexdrm, 1, 2)
    Dim Testname As String = ""
    If WhichLOSHToUse = 1 Then
        'using LOSH in current hex
    FinalLOSHName = If(Me.HexHind > 0, Me.hexdesc, "") 'works because LOSH due to inherent
    If Featuredrm = 0 Then Featurename = ""
        'Featuredrm = 0
    LOSHdrm = Me.HexHind
            FinalLOSHDRM = currenthexdrm
    FinalVisLOSH = VisLOSH : FinalVisLOSHName = VisLOSHname
            Testname = Me.HexName  'for test purposes only
    Else
        ' using LOSH in alternate hex
    TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
    If GetAltLOSH > 0 Then LOSHName = TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.Desc, hexterrtype, Maptableinstance) Else LOSHName = "" ' If(GetAltLOSH > 0, Linqdata.GetTerrainData(EnumCon.TerrFactor.Desc, hexterrtype), "")
    If AltFeatLOSH > 0 Then Featurename = AltFeatName Else Featurename = "" : Featuredrm = 0
    If AltVisLOSH > 0 Then VisLOSHname = AltVisName : FinalVisLOSH = AltVisLOSH Else FinalVisLOSH = 0 : VisLOSHname = ""
    FinalLOSHDRM = alternatehexdrm
            FinalLOSHName = Trim(LOSHName) & Space(1) & Trim(Featurename) & Space(1) & Trim(VisLOSHname)
        'Featuredrm = 0
                'Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(mapcol)
    UseAltName = GetLocs.GetnamefromdatatableMap(AltHextoCheck)
    Testname = UseAltName
            UsingComTer = NextTempComTer
    LOSHdrm = GetAltLOSH : Featuredrm = AltFeatLOSH : VisLOSH = AltVisLOSH
    End If
        'MsgBox("Using " & FinalLOSHName & Space(1) & FeatureName & FinalVisLOSHName & " with " & FinalLOSHDrm.ToString & " drm from " & Testname)
    Else
        'no LOSH modifiers
    Featurename = "" : AltFeatName = "" : LOSHName = "" : WhichLOSHToUse = 0
    End If
    Else
        'current ComTer hex is not an alternate hex grain hex; no action required
    FinalLOSHDRM = LOSHdrm + Featuredrm + VisLOSH : FinalFeatureDRM = Featuredrm : FinalVisLOSH = VisLOSH
        'msgbox for test purposes only - delete when working
                'MsgBox(ComTer.HexName & " is not an alternate hex grain hex; no action required")
    End If
        'Next
    TerrainName = ""
    If Featuredrm > 0 Then
    If DoesScenLOSHApplytothisLOS(Featuredrm, Featurename, TotalFirerlevel, TargetTotalLevel) Then
    TerrainName = ""
    Else
            Featuredrm = 0 : Featurename = ""
    End If
    End If
        'see if a hexspine drm needs to be calculated - and then store for use in ComTer.IsTarget section
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Dim LOSRange As Integer = MapGeo.CalcRange(SeeHexNum, SeenHexnum, True)
    Dim TestRange As Integer = MapGeo.CalcRange(SeeHexNum, Me.HexID, True)
    If TestRange + 1 = LOSRange Then
        'hexspine connects to Targethex vertex
    Dim SpineSide As Integer = MapGeo.HexSideEntry(AltHextoCheck, Me.HexID)
    Dim IsSideCheck = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)  'Game.Scenario.LocationCol)
    Dim hexsidehex As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(Me.LocIndex)
    Dim hexsideterrain As Integer = IsSideCheck.Gethexsidetype(hexsidehex, SpineSide)
    If IsSideCheck.IsAWallHedgeRdBlk(SpineSide, Me.LocIndex) Then
    hexspinedrm = CInt(IsSideCheck.GetSideData(ConstantClassLibrary.ASLXNA.TerrFactor.HexsideTEM, hexsideterrain, Maptableinstance))
            MessageBox.Show("hexspine " & SpineSide.ToString & " has a drm of " & hexspinedrm.ToString)
    End If
    End If
        'Else
                '    Featuredrm = 0 : LOSHdrm = 0
                'End If
    Else  'Los not along hexside
    If Featuredrm > 0 Then
    If DoesScenLOSHApplytothisLOS(Featuredrm, Featurename, TotalFirerlevel, TargetTotalLevel) Then
    TerrainName = ""
    Else
            Featuredrm = 0 : Featurename = ""
    End If
    End If
    FinalLOSHDRM = LOSHdrm + Featuredrm + VisLOSH : FinalFeatureDRM = Featuredrm : FinalVisLOSH = VisLOSH
    End If

        'having calculated all possible drm and decided which ones to use, now add them to the DRMList
    Dim AddIsGood As Boolean = True
        'need to do range test (A6.7) - two hexes of same range, add only one
    If lasthexnum > 0 Then
    If RangeIsEqual(Me.HexID, lasthexnum, SeeHexNum) Then
    If Lasthexloshdrm >= FinalLOSHDRM Then
        'don't add new mods
        'MessageBox.Show("Range equal to previous hex; no additional LOSH drm added (A6.7)")
    AddIsGood = False
            Else
        'get rid of previous mods
    Dim Removeitems As IEnumerable(Of DataClassLibrary.ASLXNA.IFTMods) = From QU As DataClassLibrary.ASLXNA.IFTMods In DRMList Where QU.DRMLocIndex = Lastlocindex
    For Each RemoveMod As DataClassLibrary.ASLXNA.IFTMods In Removeitems
        DRMList.Remove(RemoveMod)
    Next
    End If
    End If
    End If
        'first hexhind and feature losh
    If AddIsGood Then
    If LOSHdrm <> 0 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.HexHind) Then
    NEWDrm = New DataClassLibrary.ASLXNA.IFTMods(LOSHdrm, ConstantClassLibrary.ASLXNA.IFTdrm.HexHind, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, UsingComTer.LocIndex)
            DRMList.Add(NEWDrm)
    Else
            LOSHdrm = 0
        'MessageBox.Show(LOSHName & " in " & UsingComTer.HexName & " already added to DRM")
    End If
    End If
    If Featuredrm <> 0 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.LOSH) Then
    NEWDrm = New DataClassLibrary.ASLXNA.IFTMods(Featuredrm, ConstantClassLibrary.ASLXNA.IFTdrm.LOSH, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, UsingComTer.LocIndex)
            DRMList.Add(NEWDrm)
    Else
            Featuredrm = 0
        'MessageBox.Show(FeatureName & " in " & UsingComTer.HexName & " already added to DRM")
    End If
    End If
        'now do Veh/Wreck LOSh
    If LOSHdrm <= 0 And Featuredrm <= 0 Then
    If AFVIsPresent() AndAlso AFVInLOS() Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.VehWrkLOSH) Then
    NEWDrm = New DataClassLibrary.ASLXNA.IFTMods(1, ConstantClassLibrary.ASLXNA.IFTdrm.VehWrkLOSH, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
            DRMList.Add(NEWDrm)
    FinalLOSHDRM += 1
    End If
    End If
    End If
        'now add visibility losh
    If FinalVisLOSH > 0 Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.VisLoSH) Then
    NEWDrm = New DataClassLibrary.ASLXNA.IFTMods(FinalVisLOSH, ConstantClassLibrary.ASLXNA.IFTdrm.VisLoSH, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, UsingComTer.LocIndex)
            DRMList.Add(NEWDrm)
    Else
            FinalVisLOSH = 0
        'MessageBox.Show(FinalVisLOSHName & " in " & UsingComTer.HexName & " already added to DRM")
    End If
    End If
    TotalLocationLOSHdrm += FinalLOSHDRM
        'MessageBox.Show("Total LOSH drm in " & UsingComTer.HexName & " is " & TotalLocationLOSHdrm.ToString)
    TotalLOSLOSHdrm += TotalLocationLOSHdrm
    End If
    Lasthexloshdrm = FinalLOSHDRM

    End Sub
    Private Function VehicleTEMCheck(ByRef DRMList As List(Of DataClassLibrary.ASLXNA.IFTMods), ByVal TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti) As Integer
    Dim PositiveDRM As Boolean = False
    For Each IFTdrmTest As DataClassLibrary.ASLXNA.IFTMods In DRMList
    If IFTdrmTest.DRM > 0 AndAlso IFTdrmTest.DRMLocIndex = Me.LocIndex Then PositiveDRM = True : Exit For
    Next
    If Not (PositiveDRM) AndAlso AFVIsPresent() Then
    If NotAlreadyAddedToDRMList(DRMList, TargetUnit, ConstantClassLibrary.ASLXNA.IFTdrm.VehWrkTEM) Then
    Dim NewDRM = New DataClassLibrary.ASLXNA.IFTMods(1, ConstantClassLibrary.ASLXNA.IFTdrm.VehWrkTEM, TargetUnit.BasePersUnit.Unit_ID, TargetUnit.BasePersUnit.TypeType_ID, TargetUnit.BasePersUnit.LOCIndex, Me.LocIndex)
    End If
    Return 1
    End If
    Return 0
    End Function

    Private Function AFVIsPresent() As Boolean
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    Dim VehLIst As New List(Of DataClassLibrary.AFV)
        'Get Vehicle if exists
    Try
            VehLIst = (Linqdata.GetVehiclesInHex(Me.HexID)).ToList
    Catch
    Return False 'no vehicles found in hex
    End Try
    If VehLIst.Count = 0 Then Return False ' no vehicles found
            'check veh type
    For Each Selvehicle As DataClassLibrary.AFV In VehLIst
        'is afv
    If Not Selvehicle.ISAFV Then Continue For
        'veh status
    If Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.Vmove.Motion Or Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.Vmove.Moving Or Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.Vmove.Moved Then Continue For
    If Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.Burning Then Continue For
        'If Selvehicle.levelinhex <> ComTer.HexBaseLevel then Continue for
                'If Selvheicle entrenched or dug in - NEED TO PROGRAM

                'if find one valid vehicle then return true
    Return True
    Next
    Return False 'no valid vehicle present
    End Function
    Private Function AFVInLOS() As Boolean
        'NEEDS TO BE PROGRAMMED
    Return True
    End Function
    Private Function CalcVisLOSH(ByRef VisLOSHName As String, ByRef OBAAlreadyFound As Boolean, ByVal NextComTer As CombatTerrain, ByVal SeeLevelinHex As Single, ByVal SeenLevelinhex As Single, ByVal Seehexnum As Integer, ByVal seenhexnum As Integer) As Integer
        'called by IFTC.Combatdrm
                'determines if any visibility-LOSH exists in hex (only from OBA and/or Smoke coded so far ) - NEED TO ADD MIST AND DUST
                'NEED TO ADD CODE TO HANDLE DIFFERENT LEVEL TESTS
                'BOTH ABOVE AND BELOW PLUS ONE UP AND ONE DOWN (USE BLIND HEX CHECK TO RESOLVE)
    Dim Smoketype As Integer = 0 : Dim SmokeBaselevel As Single = 0 'used when checking presence of smoke elsewhere in hex
    CalcVisLOSH = 0 : VisLOSHName = "" : Dim AddSmoke As Boolean = False : Dim SmokeLosCheck As Boolean = False
        'check for Smoke
                'Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
        'Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
                'Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
                'Dim LocToUse As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(ComTer.LocIndex)
    Dim FirerLevel As Single = SeeLevelinHex + NextComTer.HexBaseLevel
    Dim Targetlevel As Single = SeenLevelinhex + NextComTer.HexBaseLevel
    If NextComTer.SmokeList.Count > 0 Then 'Smoke in hex
    SmokeLosCheck = True
    End If

    If NextComTer.IsFirer Then
    Dim GetSmoke = New TerrainClassLibrary.ASLXNA.ManageScenarioTerrain
    Dim Smokelevel As Single = 0
    For Each Smokeinstance In NextComTer.SmokeList
            SmokeBaselevel = Smokeinstance.SmokeBaseLevel
    Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.Smoke) + SmokeBaselevel + NextComTer.HexBaseLevel
    If (FirerLevel <= Smokelevel) And (FirerLevel >= (SmokeBaselevel + NextComTer.HexBaseLevel)) Then   '  Targetlevel < Smokelevel) Or (FirerLevel = SmokeBaselevel - 1 And Targetlevel > SmokeBaselevel) Then
            'determine TEM / LOSH drm
    AddSmoke = True : Smoketype = Smokeinstance.Smoke : Exit For
    End If
    Next
    ElseIf SmokeLosCheck Then
    Dim GetSmoke = New TerrainClassLibrary.ASLXNA.ManageScenarioTerrain
    Dim Smokelevel As Single = 0
    For Each Smokeinstance In NextComTer.SmokeList
            SmokeBaselevel = Smokeinstance.SmokeBaseLevel
    Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.Smoke) + SmokeBaselevel + NextComTer.HexBaseLevel
    If FirerLevel >= Smokelevel And Targetlevel >= Smokelevel Then
    CalcVisLOSH = 0 'Firer and Targer are above Smoke (Unit at same level as Smoke height is not effected by Smoke see A24.4 example)
    ElseIf FirerLevel < SmokeBaselevel And Targetlevel < SmokeBaselevel Then
            CalcVisLOSH = 0 'LOS passes below smoke
    ElseIf (Targetlevel = Smokelevel And FirerLevel < Smokelevel) Or (Targetlevel < Smokelevel And FirerLevel = Smokelevel) Or
            (Targetlevel = SmokeBaselevel - 1 And FirerLevel >= SmokeBaselevel) Or (Targetlevel >= SmokeBaselevel And FirerLevel = SmokeBaselevel - 1) Or
            (Targetlevel < Smokelevel And Targetlevel >= SmokeBaselevel And FirerLevel < Smokelevel And FirerLevel >= SmokeBaselevel) Then
        'one is below and one is at obstacle level OR one is above base and other is just below base OR both are within smoke height: LOSH applies - LOSH never goes over or under
                'determine TEM / LOSH drm
    AddSmoke = True : Smoketype = Smokeinstance.Smoke
            Else
        'one is in/above and one in/below (but not both in): LOS potentially passes through the smoke, need to use Obstacle and Blind hex logic to determine
                'if blind hex would block then goes through smoke
                'determine range and height differences
                'Dim AddSmoke As Boolean = False
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Dim ObsTargRange As Integer = MapGeo.CalcRange(NextComTer.HexID, seenhexnum, True)
    Dim FirerObsRange As Integer = MapGeo.CalcRange(NextComTer.HexID, Seehexnum, True)
    Dim ObsTargElev As Single = Smokelevel - Targetlevel
    Dim FirerObsElev As Single = FirerLevel - Smokelevel
    If (ObsTargRange = 1 And ObsTargElev >= 1) Or (FirerObsRange = 1 And FirerObsElev <= -1) Then
        'target is adjacent and below smoke: automatic blind or firer is adjacent and below smoke: automatic blind
                MessageBox.Show("Your LOS passes through adjacent smoke in " & Trim(NextComTer.HexName), "LOSH Check in Combat DRM")
    AddSmoke = True : Smoketype = Smokeinstance.Smoke
    ElseIf (FirerObsRange = 1 And FirerObsElev > 0) Or (ObsTargRange = 1 And ObsTargElev < 0) Then
        'firer is adjacent and above smoke, target is adjacent and above smoke: LOS clear
    Else
        'neither firer nor target is adjacent to smoke
    If FirerObsElev > 0 Then
        'firer is above smoke
    Dim FirerBlindHexes As Integer = CInt(Int(FirerObsRange / 5) - (FirerObsElev - 1))
    Dim TargetBlindHexes As Integer = CInt(NextComTer.HexBaseLevel) - CInt(Math.Round(Targetlevel, 0, MidpointRounding.AwayFromZero))
    Dim BlindHexes As Integer = 1 + FirerBlindHexes + TargetBlindHexes
    If ObsTargRange <= BlindHexes Then
        MessageBox.Show("Your LOS passes through smoke in " & Trim(NextComTer.HexName), "LOSH Check in Combat DRM")
    AddSmoke = True : Smoketype = Smokeinstance.Smoke
    End If
    Else ' target is above obstacle
    Dim FirerBlindHexes As Integer = CInt((Int(ObsTargRange / 5)) - (Math.Abs(ObsTargElev) - 1))
    Dim TargetBlindHexes As Integer = CInt(NextComTer.HexBaseLevel - Math.Abs(FirerLevel))
    Dim BlindHexes As Integer = FirerBlindHexes + TargetBlindHexes
    If FirerObsRange <= BlindHexes Then
        MessageBox.Show("Your LOS passes through smoke in " & Trim(NextComTer.HexName), "LOSH Check in Combat DRM")
    AddSmoke = True : Smoketype = Smokeinstance.Smoke
    End If
    End If
    End If
    End If
    Next
    End If
    If AddSmoke Then
    Select Case Smoketype
    Case ConstantClassLibrary.ASLXNA.VisHind.BlazeStone, ConstantClassLibrary.ASLXNA.VisHind.BlazeWood, ConstantClassLibrary.ASLXNA.VisHind.GunSmoke, ConstantClassLibrary.ASLXNA.VisHind.OBASmoke
    CalcVisLOSH += 3
    Case ConstantClassLibrary.ASLXNA.VisHind.GreyDisp, ConstantClassLibrary.ASLXNA.VisHind.GunSmokeDisp, ConstantClassLibrary.ASLXNA.VisHind.GreyWP, ConstantClassLibrary.ASLXNA.VisHind.GunSmokeDisp,
    ConstantClassLibrary.ASLXNA.VisHind.GunWP, ConstantClassLibrary.ASLXNA.VisHind.InfSmoke, ConstantClassLibrary.ASLXNA.VisHind.OBASmokeDisp, ConstantClassLibrary.ASLXNA.VisHind.VehDust
    CalcVisLOSH += 2
    Case ConstantClassLibrary.ASLXNA.VisHind.GreyWPDisp, ConstantClassLibrary.ASLXNA.VisHind.GunWPDisp, ConstantClassLibrary.ASLXNA.VisHind.InfWP
    CalcVisLOSH += 1
    Case Else
    CalcVisLOSH += 0
    End Select
    If NextComTer.IsFirer Then CalcVisLOSH += 1 'Smoke in firer's location
        'COME BACK AND FIX SMOKE NAMING LATER
                'Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(LocationCol)
    VisLOSHName = "Smoke" 'TerrChk.GetPositionData(DataClassLibrary.ASLXNA.DataC.TerrFactor.Desc, Smoketype, Maptables)
    End If
        ' check for OBA
    If Not (OBAAlreadyFound) Then 'OBA +1 only applies once no matter how many hexes crossed so if already added, don't add again
    If NextComTer.OBA = ConstantClassLibrary.ASLXNA.Feature.FFE1 Or NextComTer.OBA = ConstantClassLibrary.ASLXNA.Feature.FFE2 Or NextComTer.OBA = ConstantClassLibrary.ASLXNA.Feature.FFEC Then
    CalcVisLOSH += 1
    VisLOSHName &= " OBA LOSH"
    OBAAlreadyFound = True
    End If
    End If
    Trim(VisLOSHName)
    Return CalcVisLOSH
    End Function
    Private Function DoesScenLOSHApplytothisLOS(ByRef FeatdrmAdj As Integer, ByRef FeatureName As String, ByVal TotalSeeLevel As Single, ByVal totalseenlevel As Single) As Boolean
        'called by IFT.CombatDRM
                'determines if a Scenario Terrain feature effects LOS or if
                'height difference prevents it
                'Dim TempValidSol As LOSClassLibrary.ASLXNA.LOSSolution
    Dim ScenFeatureTotalHeight As Single = 0 : Dim FeatureHeight As Single = 0
    Dim TempAdj As Integer = 0 : Dim TempName As String = ""

    FeatureName = ""
    DoesScenLOSHApplytothisLOS = False
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
    If IsNothing(Linqdata.ScenFeatcol) Then
        ' no scenario features currently exist in this scenario
    FeatdrmAdj = 0
    Return False
    End If
    For Each ScenFeat In Linqdata.ScenFeatcol
        'need to check each ScenFeat as more than one can exist per hex (ie smoke and wire)
    FeatureHeight = 0
            'check for hex match
    If ScenFeat.Hexnumber = Me.HexID Then
        'get height of feature found
                'NEED A ROUTINE TO DO THIS
    ScenFeatureTotalHeight = Me.HexBaseLevel + FeatureHeight
    If TotalSeeLevel >= ScenFeatureTotalHeight And totalseenlevel >= ScenFeatureTotalHeight Then
        'LOS goes over LOSH feature; no drm applies
    ElseIf ((TotalSeeLevel < ScenFeatureTotalHeight And totalseenlevel <= ScenFeatureTotalHeight) Or
        (TotalSeeLevel <= ScenFeatureTotalHeight And totalseenlevel < ScenFeatureTotalHeight)) Or
            (((TotalSeeLevel < ScenFeatureTotalHeight And totalseenlevel > ScenFeatureTotalHeight) Or
        (TotalSeeLevel > ScenFeatureTotalHeight And totalseenlevel < ScenFeatureTotalHeight)) And
            (FeatureHeight > 0.5)) Then
        'Tests for two conditions: (a)one is below and other is equal to or below and
                '(b) one is above and one is below and LOSH is not a half-level (ie grain)
                'in both cases LOS goes through LOSH feature; drm applies
    TempAdj += ScenFeat.GetLOSH(False, False, TempName, False)
    FeatureName += Space(1) & Trim(TempName)
    Else
        'Once checked this can be simplified to just the elseif case once I know that it works
    End If
    End If
    Next
            FeatdrmAdj = TempAdj
    Trim(FeatureName)
    If FeatdrmAdj > 0 Then DoesScenLOSHApplytothisLOS = True
    End Function
    Private Function SmokePresentinHex(ByVal hexnum As Integer, ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable) As List(Of ObjectClassLibrary.ASLXNA.SmokeHolder)
            'called by Me.DoSightCheck
            'returns list of smoke present in the hex - searches all locations in the hex
    Dim Smokelist = New List(Of ObjectClassLibrary.ASLXNA.SmokeHolder)
    Dim AllLOCs As IQueryable(Of MapDataClassLibrary.GameLocation) = GetLocs.RetrieveLocationsfromMapTable(hexnum, "Hexnum")
    For Each LookforSmoke As MapDataClassLibrary.GameLocation In AllLOCs
        'test for smoke, if found then set values and return
    If LookforSmoke.Smoke > 0 Then
    Dim NewSmoke = New ObjectClassLibrary.ASLXNA.SmokeHolder(LookforSmoke.Smoke, LookforSmoke.SmokeBaseLevel)
            Smokelist.Add(NewSmoke)
    End If
    Next
    Return Smokelist
    End Function
    Private Function RangeIsEqual(ByVal Currenthex As Integer, ByVal lasthex As Integer, ByVal Starthex As Integer) As Boolean
    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) 'can use null values if sure instance already exists
    Dim Firstrange As Integer = MapGeo.CalcRange(Starthex, Currenthex, True)
    Dim Secondrange As Integer = MapGeo.CalcRange(lasthex, Currenthex, True)
    If Firstrange = Secondrange Then Return True Else Return False
    End Function
        'following 4 methods moved to LOSClassLibrary.LOSSolution April 13
                'Public Function DoesScenLOSHApplytothisLOS(ByVal UsingValidSol As Integer, ByRef FeatdrmAdj As Integer, ByRef FeatureName As String) As Boolean
                '    'called by IFT.CombatDRM
        '    'determines if a Scenario Terrain feature effects LOS or if
            '    'height difference prevents it
        '    Dim TempValidSol As LOSClassLibrary.aslxna.LOSSolution
                '    Dim ScenFeatureTotalHeight As Single = 0 : Dim FeatureHeight As Single = 0
                '    Dim TempAdj As Integer = 0 : Dim TempName As String = ""

                '    FeatureName = ""
                '    DoesScenLOSHApplytothisLOS = False
                '    If IsNothing(Game.linqdata.ScenFeatcol) Then
                '        ' no scenario features currently exist in this scenario
        '        FeatdrmAdj = 0
                '        Return False
                '    End If
                '    TempValidSol = CType(Game.Scenario.IFT.ValidSolutions.Item(UsingValidSol - 1), LOSClassLibrary.aslxna.LOSSolution) 'item is zero-based so -1
            '    With TempValidSol
            '        For Each ScenFeat In Game.linqdata.ScenFeatcol
            '            'need to check each ScenFeat as more than one can exist per hex (ie smoke and wire)
        '            FeatureHeight = 0
                '            'check for hex match
        '            If ScenFeat.Hexnumber = Me.HexID Then
                '                'get height of feature found
        '                'NEED A ROUTINE TO DO THIS
        '                ScenFeatureTotalHeight = Me.HexBaseLevel + FeatureHeight
                '                If .TotalSeeLevel >= ScenFeatureTotalHeight And .TotalSeenLevel >= ScenFeatureTotalHeight Then
                '                    'LOS goes over LOSH feature; no drm applies
        '                ElseIf ((.TotalSeeLevel < ScenFeatureTotalHeight And .TotalSeenLevel <= ScenFeatureTotalHeight) Or
                '                       (.TotalSeeLevel <= ScenFeatureTotalHeight And .TotalSeenLevel < ScenFeatureTotalHeight)) Or
                '                   (((.TotalSeeLevel < ScenFeatureTotalHeight And .TotalSeenLevel > ScenFeatureTotalHeight) Or
                '                       (.TotalSeeLevel > ScenFeatureTotalHeight And .TotalSeenLevel < ScenFeatureTotalHeight)) And
                '                        (FeatureHeight > 0.5)) Then
                '                    'Tests for two conditions: (a)one is below and other is equal to or below and
        '                    '(b) one is above and one is below and LOSH is not a half-level (ie grain)
        '                    'in both cases LOS goes through LOSH feature; drm applies
        '                    TempAdj += ScenFeat.GetLOSH(False, False, TempName, False)
                '                    FeatureName += Space(1) & Trim(TempName)
                '                Else
                '                    'Once checked this can be simplified to just the elseif case once I know that it works
        '                End If
                '            End If
                '        Next
                '        FeatdrmAdj = TempAdj
                '        Trim(FeatureName)
                '    End With
                '    If FeatdrmAdj > 0 Then DoesScenLOSHApplytothisLOS = True
                'End Function
                'Public Function CalcVisLOSH(ByRef VisLOSHName As String, ByRef OBAAlreadyFound As Boolean, ByVal ValidSol As LOSClassLibrary.aslxna.LOSSolution) As Integer
                '    'called by IFTC.Combatdrm
        '    'determines if any visibility-LOSH exists in hex (only from OBA and/or Smoke coded so far ) - NEED TO ADD MIST AND DUST
        '    'NEED TO ADD CODE TO HANDLE DIFFERENT LEVEL TESTS
        '    'BOTH ABOVE AND BELOW PLUS ONE UP AND ONE DOWN (USE BLIND HEX CHECK TO RESOLVE)
        '    Dim Smoketype As Integer = 0 : Dim SmokeBaselevel As Single = 0 'used when checking presence of smoke elsewhere in hex
        '    CalcVisLOSH = 0 : VisLOSHName = "" : Dim AddSmoke As Boolean = False : Dim SmokeLosCheck As Boolean = False
                '    'check for Smoke
        '    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
        '    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
                '    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
                '    Dim LocToUse As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(Me.LocIndex)
                '    If LocToUse.Smoke > 0 Then 'Smoke in location
        '        Smoketype = LocToUse.Smoke
                '        SmokeLosCheck = True
                '    ElseIf Me.IsFirer Then
                '        If SmokePresentElsewhereinHex(LocToUse, ValidSol, Smoketype, SmokeBaselevel) Then
                '            ' if smoke present elsewhere in hex then determine totals levels of firer, target and smoke
        '            Dim FirerLevel As Single = ValidSol.SeeLevelInHex + HexBaseLevel
                '            Dim Targetlevel As Single = ValidSol.SeenLevelInHex + HexBaseLevel
                '            Dim GetSmoke = New TerrainClassLibrary.ASLXNA.ManageScenarioTerrain
                '            Dim Smokelevel As Single = GetSmoke.GetSmokeHeight(Smoketype) + SmokeBaselevel + HexBaseLevel
                '            If (FirerLevel = Smokelevel And Targetlevel < Smokelevel) Or (FirerLevel = SmokeBaselevel - 1 And Targetlevel > SmokeBaselevel) Then
                '                'determine TEM / LOSH drm
        '                AddSmoke = True
                '            End If
                '        End If
                '    ElseIf SmokePresentElsewhereinHex(LocToUse, ValidSol, Smoketype, SmokeBaselevel) Then
                '        ' if smoke present elsewhere in hex then determine totals levels of firer, target and smoke
        '        SmokeLosCheck = True
                '    End If
                '    If SmokeLosCheck Then
                '        Dim FirerLevel As Single = ValidSol.SeeLevelInHex + HexBaseLevel
                '        Dim Targetlevel As Single = ValidSol.SeenLevelInHex + HexBaseLevel
                '        Dim GetSmoke = New TerrainClassLibrary.ASLXNA.ManageScenarioTerrain
                '        Dim Smokelevel As Single = GetSmoke.GetSmokeHeight(Smoketype) + SmokeBaselevel
                '        If FirerLevel >= Smokelevel And Targetlevel >= Smokelevel Then
                '            CalcVisLOSH = 0 'Firer and Targer are above Smoke (Unit at same level as Smoke height is not effected by Smoke see A24.4 example)
        '        ElseIf FirerLevel < SmokeBaselevel And Targetlevel < SmokeBaselevel Then
                '            CalcVisLOSH = 0 'LOS passes below smoke
        '        ElseIf (Targetlevel = Smokelevel And FirerLevel < Smokelevel) Or (Targetlevel < Smokelevel And FirerLevel = Smokelevel) Or
                '        (Targetlevel = SmokeBaselevel - 1 And FirerLevel >= SmokeBaselevel) Or (Targetlevel >= SmokeBaselevel And FirerLevel = SmokeBaselevel - 1) Or
                '        (Targetlevel < Smokelevel And Targetlevel >= SmokeBaselevel And FirerLevel < Smokelevel And FirerLevel >= SmokeBaselevel) Then
                '            'one is below and one is at obstacle level OR one is above base and other is just below base OR both are within smoke height: LOSH applies - LOSH never goes over or under
        '            'determine TEM / LOSH drm
        '            AddSmoke = True
                '        Else
                '            'one is in/above and one in/below (but not both in): LOS potentially passes through the smoke, need to use Obstacle and Blind hex logic to determine
        '            'if blind hex would block then goes through smoke
        '            'determine range and height differences
        '            'Dim AddSmoke As Boolean = False
        '            Dim ObsTargRange As Integer = Mapgeo.RangeC.CalcRange(Me.HexID, ValidSol.SeenHexNum, True)
                '            Dim FirerObsRange As Integer = Mapgeo.RangeC.CalcRange(Me.HexID, ValidSol.SeeHexNum, True)
                '            Dim ObsTargElev As Single = Smokelevel - Targetlevel
                '            Dim FirerObsElev As Single = FirerLevel - Smokelevel
                '            If (ObsTargRange = 1 And ObsTargElev >= 1) Or (FirerObsRange = 1 And FirerObsElev <= -1) Then
                '                'target is adjacent and below smoke: automatic blind or firer is adjacent and below smoke: automatic blind
        '                MessageBox.Show("Your LOS passes through adjacent smoke in " & Trim(Me.HexName), "LOSH Check in Combat DRM")
                '                AddSmoke = True
                '            ElseIf (FirerObsRange = 1 And FirerObsElev > 0) Or (ObsTargRange = 1 And ObsTargElev < 0) Then
                '                'firer is adjacent and above smoke, target is adjacent and above smoke: LOS clear
        '            Else
                '                'neither firer nor target is adjacent to smoke
        '                If FirerObsElev > 0 Then
                '                    'firer is above smoke
        '                    Dim FirerBlindHexes As Integer = CInt(Int(FirerObsRange / 5) - (FirerObsElev - 1))
                '                    Dim TargetBlindHexes As Integer = CInt(HexBaseLevel) - CInt(Math.Round(Targetlevel, 0, MidpointRounding.AwayFromZero))
                '                    Dim BlindHexes As Integer = 1 + FirerBlindHexes + TargetBlindHexes
                '                    If ObsTargRange <= BlindHexes Then
                '                        MessageBox.Show("Your LOS passes through smoke in " & Trim(Me.HexName), "LOSH Check in Combat DRM")
                '                        AddSmoke = True
                '                    End If
                '                Else ' target is above obstacle
        '                    Dim FirerBlindHexes As Integer = CInt((Int(ObsTargRange / 5)) - (Math.Abs(ObsTargElev) - 1))
                '                    Dim TargetBlindHexes As Integer = CInt(HexBaseLevel - Math.Abs(FirerLevel))
                '                    Dim BlindHexes As Integer = FirerBlindHexes + TargetBlindHexes
                '                    If FirerObsRange <= BlindHexes Then
                '                        MessageBox.Show("Your LOS passes through smoke in " & Trim(Me.HexName), "LOSH Check in Combat DRM")
                '                        AddSmoke = True
                '                    End If
                '                End If
                '            End If

                '        End If

                '    End If
                '    If AddSmoke Then
                '        Select Case Smoketype
                '            Case VisHind.BlazeStone, VisHind.BlazeWood, VisHind.GunSmoke, VisHind.OBASmoke
                '                CalcVisLOSH += 3
                '            Case VisHind.GreyDisp, VisHind.GunSmokeDisp, VisHind.GreyWP, VisHind.GunSmokeDisp,
                '                VisHind.GunWP, VisHind.InfSmoke, VisHind.OBASmokeDisp, VisHind.VehDust
                '                CalcVisLOSH += 2
                '            Case VisHind.GreyWPDisp, VisHind.GunWPDisp, VisHind.InfWP
                '                CalcVisLOSH += 1
                '            Case Else
                '                CalcVisLOSH += 0
                '        End Select
                '        If Me.IsFirer And LocToUse.Smoke > 0 Then CalcVisLOSH += 1 'Smoke in firer's location
            '        Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(LocationCol)
            '        VisLOSHName = TerrChk.GetPositionData(TerrFactor.Desc, Smoketype, Maptables)
            '    End If

            '    ' check for OBA
        '    If Not (OBAAlreadyFound) Then 'OBA +1 only applies once no matter how many hexes crossed so if already added, don't add again
            '        If LocToUse.OBA = Feature.FFE1 Or LocToUse.OBA = Feature.FFE2 Or LocToUse.OBA = Feature.FFEC Then
            '            CalcVisLOSH += 1
            '            VisLOSHName &= "OBA LOSH"
            '            OBAAlreadyFound = True
            '        End If
            '    End If
            '    Trim(VisLOSHName)
            '    Return CalcVisLOSH
            'End Function
            'Private Function SmokePresentElsewhereinHex(ByVal Loctouse As MapDataClassLibrary.GameLocation, ByVal ValidSolution As LOSClassLibrary.aslxna.LOSSolution, ByRef Smoketype As Integer, ByRef SmokeBaselevel As Single) As Boolean
            '    'called by Me.CalcVisLOSH
        '    'returns boolean of smoke present in the hex - searches all locations in the hex
        '    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)    'use null values for parameters when sure instance exists
        '    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
                '    Dim GetLocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
                '    Dim AllLOCs As IQueryable(Of MapDataClassLibrary.GameLocation) = GetLocs.RetrieveLocationsfromMapTable(Loctouse.Hexnum, "Hexnum")
                '    For Each LookforSmoke As MapDataClassLibrary.GameLocation In AllLOCs
                '        'test for smoke, if found then set values and return
            '        If LookforSmoke.Smoke > 0 Then
            '            Smoketype = LookforSmoke.Smoke
            '            SmokeBaselevel = LookforSmoke.SmokeBaseLevel
            '            Return True
            '        End If
            '    Next
            '    'if get here then no smoke found; set values to 0
            '    Smoketype = 0
            '    SmokeBaselevel = 0
            '    Return False
            'End Function
            'Public Sub ResetPropertyValues()
            '    'resets values of properties set during code processing
        '    'to default values
        '    _HexLOSHApplies = False
                '    _HexsideCrossedTEM = 0
                '    _HexsideCrossedDesc = ""
                'End Sub*/


}
