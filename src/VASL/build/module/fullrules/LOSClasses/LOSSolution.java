package VASL.build.module.fullrules.LOSClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.AltHexGTerrain;
import VASL.build.module.fullrules.ObjectClasses.CombatTerrain;
import VASL.build.module.fullrules.ObjectClasses.SmokeHolder;
import VASL.build.module.fullrules.TerrainClasses.ManageScenarioTerrain;


import java.util.LinkedList;

public class LOSSolution extends BaseSolution {
    private int prSolIDvalue;
    private LinkedList<CombatTerrain> prHexesinLOS = new LinkedList<CombatTerrain>();
    private LinkedList<AltHexGTerrain> prAltHexesinLOS = new LinkedList<AltHexGTerrain>();

    // constructor called by
    public LOSSolution(Hex PassSeehex, double PassSeelevelinhex, double PassTotalSeeLevel, Constantvalues.AltPos PassSeePositionInHex, Hex PassSeenhex,
                       double PassSeenlevelinhex, double PassTotalSeenLevel, Constantvalues.AltPos PassSeenPositionInHex, boolean PassSolWorks,
                       Constantvalues.LOS PassLOSFollows, int PassID, VASL.LOS.Map.Map PassScenMap, LinkedList<CombatTerrain> PassHexesinLOS) {
        super(PassSeehex, PassSeelevelinhex, PassSeePositionInHex, PassSeenhex, PassSeenlevelinhex,
                PassSeenPositionInHex, PassSolWorks, PassScenMap);
        prSolIDvalue = PassID;
        setTotalSeeLevel(PassTotalSeeLevel);
        setTotalSeenLevel(PassTotalSeenLevel);
        setLOSFollows(PassLOSFollows);
        for (CombatTerrain PassComTerr: PassHexesinLOS){
            prHexesinLOS.add(PassComTerr);
        }
    }

    public int getID() {
        return prSolIDvalue;
    }

    public LinkedList<CombatTerrain> getHexesInLOS() {
        return prHexesinLOS;
    }

    public LinkedList<AltHexGTerrain> getAltHexesInLOS() {
        return prAltHexesinLOS;
    }

    public void AddtoLOSList(CombatTerrain Usehex) {
        Usehex.setSolID(prSolIDvalue);
        prHexesinLOS.add(Usehex);
    }

    public void AddtoAltHexList(AltHexGTerrain UseAlthex) {
        UseAlthex.setTempSolID(prSolIDvalue);
        prAltHexesinLOS.add(UseAlthex);
    }

    //remmed out while debugging undo
    /*Public Function DoesScenLOSHApplytothisLOS(ByRef FeatdrmAdj As Integer, ByRef FeatureName As String, ByVal ComTer As objectvalues.combatterrain) As Boolean
            'called by IFT.CombatDRM
                    'determines if a Scenario Terrain feature effects LOS or if
                    'height difference prevents it
                    'Dim TempValidSol As LOSvalues.LOSSolution
    Dim ScenFeatureTotalHeight As Single = 0 : Dim FeatureHeight As Single = 0
    Dim TempAdj As Integer = 0 : Dim TempName As String = ""

    FeatureName = ""
    DoesScenLOSHApplytothisLOS = false
    Dim Linqdata = Datavalues.DataC.getInstance()  'use null values when sure instance exists
    if IsNothing(Linqdata.ScenFeatcol) Then
                ' no scenario features currently exist in this scenario
    FeatdrmAdj = 0
    Return false
    End if
    for ( ScenFeat In Linqdata.ScenFeatcol
                'need to check each ScenFeat as more than one can exist per hex (ie smoke and wire)
    FeatureHeight = 0
            'check for hex match
    if ScenFeat.Hexnumber = ComTer.HexID Then
                    'get height of feature found
                            'NEED A ROUTINE TO DO THIS
    ScenFeatureTotalHeight = ComTer.HexBaseLevel + FeatureHeight
    if Me.TotalSeeLevel >= ScenFeatureTotalHeight And Me.TotalSeenLevel >= ScenFeatureTotalHeight Then
                        'LOS goes over LOSH feature; no drm applies
    ElseIf ((Me.TotalSeeLevel < ScenFeatureTotalHeight And Me.TotalSeenLevel <= ScenFeatureTotalHeight) Or
                           (Me.TotalSeeLevel <= ScenFeatureTotalHeight And Me.TotalSeenLevel < ScenFeatureTotalHeight)) Or
            (((Me.TotalSeeLevel < ScenFeatureTotalHeight And Me.TotalSeenLevel > ScenFeatureTotalHeight) Or
                           (Me.TotalSeeLevel > ScenFeatureTotalHeight And Me.TotalSeenLevel < ScenFeatureTotalHeight)) And
            (FeatureHeight > 0.5)) Then
                        'Tests for two conditions: (a)one is below and other is equal to or below and
                                '(b) one is above and one is below and LOSH is not a half-level (ie grain)
                                'in both cases LOS goes through LOSH feature; drm applies
    TempAdj += ScenFeat.GetLOSH(false, false, TempName, false)
    FeatureName += Space(1) & Trim(TempName)
    Else
                        'Once checked this can be simplified to just the elseif case once I know that it works
    End if
    End if
    Next
            FeatdrmAdj = TempAdj
    Trim(FeatureName)
    if FeatdrmAdj > 0 Then DoesScenLOSHApplytothisLOS = true
    End Function*/
    /*public int CalcVisLOSH(boolean OBAAlreadyFound, CombatTerrain ComTer) {
        // called by IFTC.Combatdrm
        // determines if any visibility-LOSH exists in hex (only from OBA and/or Smoke coded so far ) - NEED TO ADD MIST AND DUST
        // NEED TO ADD CODE TO HANDLE DIFFERENT LEVEL TESTS
        // BOTH ABOVE AND BELOW PLUS ONE UP AND ONE DOWN (USE BLIND HEX CHECK TO RESOLVE)
        Constantvalues.VisHind Smoketype = Constantvalues.VisHind.None;
        double SmokeBaselevel = 0; // used when checking presence of smoke elsewhere in hex
        int pCalcVisLOSH =0;
        boolean AddSmoke = false;
        boolean SmokeLosCheck = false;
        String VisLOSHName = "";
        // check for Smoke
        double FirerLevel = getSeeLevelInHex() + ComTer.getHexBaseLevel();
        double Targetlevel = getSeeLevelInHex() + ComTer.getHexBaseLevel();
        ManageScenarioTerrain GetSmoke = new ManageScenarioTerrain();
        if (ComTer.getSmokeList().size() > 0) {   // Smoke in hex
            SmokeLosCheck = true;
        }
        if (ComTer.IsFirer()) {
            double Smokelevel = 0;
            for (SmokeHolder Smokeinstance : ComTer.getSmokeList()) {
                SmokeBaselevel = Smokeinstance.getSmokeBaseLevel();
                Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.getSmoke()) + SmokeBaselevel + ComTer.getHexBaseLevel();
                if ((FirerLevel <= Smokelevel) && (FirerLevel >= (SmokeBaselevel + ComTer.getHexBaseLevel()))) {   //  Targetlevel < Smokelevel) Or (FirerLevel = SmokeBaselevel - 1 And Targetlevel > SmokeBaselevel) Then
                    // determine TEM / LOSH drm
                    AddSmoke = true;
                    Smoketype = Smokeinstance.getSmoke();
                    break;
                }
            }
        }else if (SmokeLosCheck) {
            double Smokelevel = 0;
            for (SmokeHolder Smokeinstance : ComTer.getSmokeList()) {
                SmokeBaselevel = Smokeinstance.getSmokeBaseLevel();
                Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.getSmoke()) + SmokeBaselevel + ComTer.getHexBaseLevel();
                if (FirerLevel >= Smokelevel && Targetlevel >= Smokelevel) {
                    pCalcVisLOSH = 0;  // Firer and Targer are above Smoke (Unit at same level as Smoke height is not effected by Smoke see A24.4 example)
                } else if (FirerLevel < SmokeBaselevel && Targetlevel < SmokeBaselevel) {
                    pCalcVisLOSH = 0; // LOS passes below smoke
                } else if ((Targetlevel == Smokelevel && FirerLevel < Smokelevel) ||
                        (Targetlevel < Smokelevel && FirerLevel == Smokelevel) ||
                        (Targetlevel == SmokeBaselevel - 1 && FirerLevel >= SmokeBaselevel) ||
                        (Targetlevel >= SmokeBaselevel && FirerLevel == SmokeBaselevel - 1) ||
                        (Targetlevel < Smokelevel && Targetlevel >= SmokeBaselevel && FirerLevel < Smokelevel && FirerLevel >= SmokeBaselevel)) {
                    // one is below and one is at obstacle level OR one is above base and other is just below base OR both are within smoke height: LOSH applies - LOSH never goes over or under
                    // determine TEM / LOSH drm
                    AddSmoke = true;
                    Smoketype = Smokeinstance.getSmoke();
                } else {
                    // one is in/above and one in/below (but not both in): LOS potentially passes through the smoke, need to use Obstacle and Blind hex logic to determine
                    // if blind hex would block then goes through smoke
                    // determine range and height differences

                    // NEED TO IMPORT VASL.LOS LOGIC FOR BLIND HEXES IN HERE
                    //'Dim AddSmoke As Boolean = false
                    //Hex FiringHex = scen.getGameMap().getHex(Firingunit.getbaseunit().getHexName());
                    *//*Hex TargetHex = scen.getGameMap().getHex(TargetUnit.getbaseunit().getHexName());
                    range = scen.getGameMap().range(FiringHex, TargetHex, scen.getGameMap().getMapConfiguration());

                    Dim ObsTargRange As Integer = MapGeo.CalcRange(ComTer.HexID, Me.SeenHexNum, true)
                    Dim FirerObsRange As Integer = MapGeo.CalcRange(ComTer.HexID, Me.SeeHexNum, true)
                    Dim ObsTargElev  As Single = Smokelevel - Targetlevel
                    Dim FirerObsElev As Single = FirerLevel - Smokelevel

                    if(ObsTargRange =1And ObsTargElev>=1) Or (FirerObsRange =1  And FirerObsElev <=-1)Then
                            'target is adjacent and below smoke: automatic blind or firer is adjacent and below smoke: automatic blind
                                    MessageBox.Show("Your LOS passes through adjacent smoke in "&

                        Trim(ComTer.HexName), "LOSH Check in Combat DRM")
                        AddSmoke =true :Smoketype =

                        Smokeinstance.Smoke
                    ElseIf(FirerObsRange =1And FirerObsElev>0) Or (ObsTargRange =1 And ObsTargElev < 0)Then
                            'firer is adjacent and above smoke, target is adjacent and above smoke: LOS clear
                    Else
                            'neither firer nor target is adjacent to smoke
                        if FirerObsElev >0Then
                                'firer is above smoke
                            Dim FirerBlindHexes As Integer = CInt(Int(FirerObsRange / 5) - (FirerObsElev - 1))
                            Dim TargetBlindHexes As Integer = CInt(ComTer.HexBaseLevel) - CInt(Math.Round(Targetlevel, 0, MidpointRounding.AwayFromZero))
                            Dim BlindHexes As Integer = 1 + FirerBlindHexes + TargetBlindHexes
                            if ObsTargRange <= BlindHexes Then
                                    MessageBox.Show("Your LOS passes through smoke in "&

                                Trim(ComTer.HexName), "LOSH Check in Combat DRM")
                                AddSmoke =true :Smoketype =Smokeinstance.Smoke
                            End if
                        Else ' target is above obstacle
                            Dim FirerBlindHexes As Integer = CInt((Int(ObsTargRange / 5)) - (Math.Abs(ObsTargElev) - 1))
                            Dim TargetBlindHexes As Integer = CInt(ComTer.HexBaseLevel - Math.Abs(FirerLevel))
                            Dim BlindHexes As Integer = FirerBlindHexes + TargetBlindHexes
                            if FirerObsRange <=  BlindHexes Then
                                    MessageBox.Show("Your LOS passes through smoke in "&

                                Trim(ComTer.HexName), "LOSH Check in Combat DRM")
                                 AddSmoke =true :Smoketype =Smokeinstance.Smoke
                            End if
                        End if
                    End if*//*
                }
            }
        }
        if (AddSmoke) {
            switch (Smoketype) {
                case BlazeStone:
                case BlazeWood:
                case GunSmoke:
                case OBASmoke:
                    pCalcVisLOSH += 3;
                case GreyDisp:
                case GunSmokeDisp:
                case GreyWP:
                case GunWP:
                case InfSmoke:
                case OBASmokeDisp:
                case VehDust:
                    pCalcVisLOSH += 2;
                case GreyWPDisp:
                case GunWPDisp:
                case InfWP:
                    pCalcVisLOSH += 1;
                default:
                    pCalcVisLOSH += 0;
            }
            if (ComTer.IsFirer()) {
                pCalcVisLOSH += 1; // Smoke in firer's location
                // COME BACK AND FIX SMOKE NAMING LATER
                // Dim TerrChk = New Terrainvalues.TerrainChecks(LocationCol)
                VisLOSHName = "Smoke";
                // 'TerrChk.GetPositionData(constantvalues.TerrFactor.Desc, Smoketype, Maptables)
            }
        }
        //  check for OBA
        if (!OBAAlreadyFound) { // OBA +1 only applies once no matter how many hexes crossed so if already added, don't add again
            *//*if (ComTer.getOBA() == Constantvalues.Feature.FFE1 || ComTer.getOBA() == Constantvalues.Feature.FFE2 || ComTer.getOBA() == Constantvalues.Feature.FFEC) {
                pCalcVisLOSH += 1;
                VisLOSHName += " OBA LOSH";
                OBAAlreadyFound = true;
            }*//*
        }
        ComTer.setVisLOSHName(VisLOSHName);
        return pCalcVisLOSH;
    }*/
}
