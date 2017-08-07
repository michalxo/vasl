package VASL.build.module.fullrules.LOSClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.AltHexGTerrain;
import VASL.build.module.fullrules.ObjectClasses.CombatTerrain;


import java.util.LinkedList;

public class LOSSolution extends BaseSolution {
    private int prSolIDvalue;
    private LinkedList<CombatTerrain> prHexesinLOS = new  LinkedList<CombatTerrain>();
    private LinkedList<AltHexGTerrain> prAltHexesinLOS = new LinkedList<AltHexGTerrain>();

    // constructor called by
    public LOSSolution(int PassSeehexnum, double PassSeelevelinhex, double PassTotalSeeLevel, double PassSeeLOSindex, Constantvalues.AltPos PassSeePositionInHex, int PassSeenhexnum,
                       double PassSeenlevelinhex, double PassTotalSeenLevel, double PassSeenLOSIndex, Constantvalues.AltPos PassSeenPositionInHex, boolean PassSolWorks,
                       int PassLOSFollows, int PassID, Constantvalues.Map PassScenMap) {
        super(PassSeehexnum, PassSeelevelinhex, PassSeeLOSindex, PassSeePositionInHex, PassSeenhexnum, PassSeenlevelinhex,
                PassSeenLOSIndex, PassSeenPositionInHex, PassSolWorks, PassScenMap);
        prSolIDvalue = PassID;
        setTotalSeeLevel(PassTotalSeeLevel);
        setTotalSeenLevel(PassTotalSeenLevel);
        setLOSFollows(PassLOSFollows);
        //pHexesInLOS = New List(Of objectclasslibrary.aslxna.combatterrain)
        // pAltHexesinLOS = New List(Of ObjectClassLibrary.ASLXNA.AltHexGTerrain)
    }

    public int getID() {return prSolIDvalue;}
    public LinkedList<CombatTerrain> getHexesInLOS() {return prHexesinLOS;}
    public LinkedList<AltHexGTerrain> getAltHexesInLOS() {return prAltHexesinLOS;}

    public void AddtoLOSList(CombatTerrain Usehex) {
        Usehex.setSolID(prSolIDvalue);
        prHexesinLOS.add(Usehex);
    }
     public void AddtoAltHexList(AltHexGTerrain UseAlthex) {
        UseAlthex.setTempSolID(prSolIDvalue);
        prAltHexesinLOS.add(UseAlthex);
    }

    //remmed out while debugging undo
    /*Public Function DoesScenLOSHApplytothisLOS(ByRef FeatdrmAdj As Integer, ByRef FeatureName As String, ByVal ComTer As objectclasslibrary.aslxna.combatterrain) As Boolean
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
    If ScenFeat.Hexnumber = ComTer.HexID Then
                    'get height of feature found
                            'NEED A ROUTINE TO DO THIS
    ScenFeatureTotalHeight = ComTer.HexBaseLevel + FeatureHeight
    If Me.TotalSeeLevel >= ScenFeatureTotalHeight And Me.TotalSeenLevel >= ScenFeatureTotalHeight Then
                        'LOS goes over LOSH feature; no drm applies
    ElseIf ((Me.TotalSeeLevel < ScenFeatureTotalHeight And Me.TotalSeenLevel <= ScenFeatureTotalHeight) Or
                           (Me.TotalSeeLevel <= ScenFeatureTotalHeight And Me.TotalSeenLevel < ScenFeatureTotalHeight)) Or
            (((Me.TotalSeeLevel < ScenFeatureTotalHeight And Me.TotalSeenLevel > ScenFeatureTotalHeight) Or
                           (Me.TotalSeeLevel > ScenFeatureTotalHeight And Me.TotalSeenLevel < ScenFeatureTotalHeight)) And
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
    Public Function CalcVisLOSH(ByRef VisLOSHName As String, ByRef OBAAlreadyFound As Boolean, ByVal ComTer As objectclasslibrary.aslxna.combatterrain) As Integer
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
    Dim FirerLevel As Single = Me.SeeLevelInHex + ComTer.HexBaseLevel
    Dim Targetlevel As Single = Me.SeenLevelInHex + ComTer.HexBaseLevel
    If ComTer.SmokeList.Count > 0 Then 'Smoke in hex
    SmokeLosCheck = True
    End If

    If ComTer.IsFirer Then
    Dim GetSmoke = New TerrainClassLibrary.ASLXNA.ManageScenarioTerrain
    Dim Smokelevel As Single = 0
    For Each Smokeinstance In ComTer.SmokeList
            SmokeBaselevel = Smokeinstance.SmokeBaseLevel
    Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.Smoke) + SmokeBaselevel + ComTer.HexBaseLevel
    If (FirerLevel <= Smokelevel) And (FirerLevel >= (SmokeBaselevel + ComTer.HexBaseLevel)) Then   '  Targetlevel < Smokelevel) Or (FirerLevel = SmokeBaselevel - 1 And Targetlevel > SmokeBaselevel) Then
            'determine TEM / LOSH drm
    AddSmoke = True : Smoketype = Smokeinstance.Smoke : Exit For
    End If
    Next
    ElseIf SmokeLosCheck Then
    Dim GetSmoke = New TerrainClassLibrary.ASLXNA.ManageScenarioTerrain
    Dim Smokelevel As Single = 0
    For Each Smokeinstance In ComTer.SmokeList
            SmokeBaselevel = Smokeinstance.SmokeBaseLevel
    Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.Smoke) + SmokeBaselevel + ComTer.HexBaseLevel
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
    Dim ObsTargRange As Integer = MapGeo.CalcRange(ComTer.HexID, Me.SeenHexNum, True)
    Dim FirerObsRange As Integer = MapGeo.CalcRange(ComTer.HexID, Me.SeeHexNum, True)
    Dim ObsTargElev As Single = Smokelevel - Targetlevel
    Dim FirerObsElev As Single = FirerLevel - Smokelevel
    If (ObsTargRange = 1 And ObsTargElev >= 1) Or (FirerObsRange = 1 And FirerObsElev <= -1) Then
                            'target is adjacent and below smoke: automatic blind or firer is adjacent and below smoke: automatic blind
                                    MessageBox.Show("Your LOS passes through adjacent smoke in " & Trim(ComTer.HexName), "LOSH Check in Combat DRM")
    AddSmoke = True : Smoketype = Smokeinstance.Smoke
    ElseIf (FirerObsRange = 1 And FirerObsElev > 0) Or (ObsTargRange = 1 And ObsTargElev < 0) Then
                            'firer is adjacent and above smoke, target is adjacent and above smoke: LOS clear
    Else
                            'neither firer nor target is adjacent to smoke
    If FirerObsElev > 0 Then
                                'firer is above smoke
    Dim FirerBlindHexes As Integer = CInt(Int(FirerObsRange / 5) - (FirerObsElev - 1))
    Dim TargetBlindHexes As Integer = CInt(ComTer.HexBaseLevel) - CInt(Math.Round(Targetlevel, 0, MidpointRounding.AwayFromZero))
    Dim BlindHexes As Integer = 1 + FirerBlindHexes + TargetBlindHexes
    If ObsTargRange <= BlindHexes Then
                                    MessageBox.Show("Your LOS passes through smoke in " & Trim(ComTer.HexName), "LOSH Check in Combat DRM")
    AddSmoke = True : Smoketype = Smokeinstance.Smoke
    End If
    Else ' target is above obstacle
    Dim FirerBlindHexes As Integer = CInt((Int(ObsTargRange / 5)) - (Math.Abs(ObsTargElev) - 1))
    Dim TargetBlindHexes As Integer = CInt(ComTer.HexBaseLevel - Math.Abs(FirerLevel))
    Dim BlindHexes As Integer = FirerBlindHexes + TargetBlindHexes
    If FirerObsRange <= BlindHexes Then
                                    MessageBox.Show("Your LOS passes through smoke in " & Trim(ComTer.HexName), "LOSH Check in Combat DRM")
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
    If ComTer.IsFirer Then CalcVisLOSH += 1 'Smoke in firer's location
                'COME BACK AND FIX SMOKE NAMING LATER
                        'Dim TerrChk = New TerrainClassLibrary.ASLXNA.TerrainChecks(LocationCol)
    VisLOSHName = "Smoke" 'TerrChk.GetPositionData(constantclasslibrary.ASLXNA.TerrFactor.Desc, Smoketype, Maptables)
    End If
            ' check for OBA
    If Not (OBAAlreadyFound) Then 'OBA +1 only applies once no matter how many hexes crossed so if already added, don't add again
    If ComTer.OBA = ConstantClassLibrary.ASLXNA.Feature.FFE1 Or ComTer.OBA = ConstantClassLibrary.ASLXNA.Feature.FFE2 Or ComTer.OBA = ConstantClassLibrary.ASLXNA.Feature.FFEC Then
    CalcVisLOSH += 1
    VisLOSHName &= " OBA LOSH"
    OBAAlreadyFound = True
    End If
    End If
    Trim(VisLOSHName)
    Return CalcVisLOSH
    End Function
        'Private Function SmokePresentelsewhereinHex(ByVal hexnum As Integer, ByVal GetLocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable) As List(Of CombatTerrainClassLibrary.ASLXNA.SmokeHolder)
                '    'called by Me.DoSightCheck
        '    'returns list of smoke present in the hex - searches all locations in the hex
        '    Dim Smokelist = New List(Of CombatTerrainClassLibrary.ASLXNA.SmokeHolder)
                '    Dim AllLOCs As IQueryable(Of MapDataClassLibrary.GameLocation) = GetLocs.RetrieveLocationsfromMapTable(hexnum, "Hexnum")
                '    For Each LookforSmoke As MapDataClassLibrary.GameLocation In AllLOCs
                '        'test for smoke, if found then set values and return
            '        If LookforSmoke.Smoke > 0 Then
            '            Dim NewSmoke = New CombatTerrainClassLibrary.ASLXNA.SmokeHolder(LookforSmoke.Smoke, LookforSmoke.SmokeBaseLevel)
            '            Smokelist.Add(NewSmoke)
            '        End If
            '    Next
            '    Return Smokelist
            'End Function
    Public Sub ResetPropertyValues()
            'NOT CALLED SO CAN DELETE APRIL 13
                    'resets values of properties set during code processing
                    'to default values
                    '_HexLOSHApplies = False
                    '_HexsideCrossedTEM = 0
                    '_HexsideCrossedDesc = ""
    End Sub*/

}
