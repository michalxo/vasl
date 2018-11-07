package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.*;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.IFTMods;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.CombatCalcC;
import VASL.build.module.fullrules.TerrainClasses.*;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASSAL.build.GameModule;

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
    private Constantvalues.Hexside prHexsideCrossedtype;
    private String prHexsideCrossedDesc;
    private int prSolutionID;
    private int prHexTEM;
    //private MapDataC Maptables = MapDataC.GetInstance("", 0);  // use null values for parameters when sure instance exists
    //private LinkedList<GameLocation> LocationCol = new LinkedList<GameLocation>();
    private ScenarioC prscen;
    private Scenario prScendet;
    private Location prLocation;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private Hex prHex;
    private String prVisLOSHName="";
    private LinkedList<HindInLOS> prhindInLOS;
    // Constructors

    public CombatTerrain(Constantvalues.Location PasshexTerrtype, Constantvalues.Hexside PassHexside1, Constantvalues.Hexside PassHexside2, Constantvalues.Hexside PassHexside3,
                         Constantvalues.Hexside PassHexside4, Constantvalues.Hexside PassHexside5, Constantvalues.Hexside PassHexside6, int PassHexHind,
                         Constantvalues.Hexrole PassHexrole, String Passcontrol, int PassTargetID,
                         LinkedList<SmokeHolder> PassSmokelist, LinkedList<HindInLOS> PassHindInLOS, int PassSolID, Location PassLocation, boolean PassHexLOSHApplies) {
        // called by
        // creates new CombatTerrain object for any hex involved in combat, passes Staircase and Control to base class constructor but does not use them
        super(PasshexTerrtype,
                PassHexside1, PassHexside2, PassHexside3, PassHexside4,
                PassHexside5, PassHexside6, Passcontrol, PassSmokelist, PassLocation);
        prHexTEM = PassLocation.getTerrain().getTEM();
        prHexHindvalue = PassHexHind;
        prhexdescvalue = PassLocation.getTerrain().getName();
        prHexrolevalue = PassHexrole;
        prHexLOSHApplies = PassHexLOSHApplies;
        prHexsideCrossedTEM = 0;
        prHexsideCrossedDesc = "";
        prLOSTarget.add(PassTargetID);
        prSolutionID= PassSolID;
        prscen  = ScenarioC.getInstance();
        prScendet = prscen.getScendet();
        prLocation = PassLocation;
        prHex = PassLocation.getHex();
        prhindInLOS = PassHindInLOS;
    }

    // thread version
    public CombatTerrain(Constantvalues.Location PasshexTerrtype, Constantvalues.Hexside PassHexside1, Constantvalues.Hexside PassHexside2, Constantvalues.Hexside PassHexside3,
                         Constantvalues.Hexside PassHexside4, Constantvalues.Hexside PassHexside5, Constantvalues.Hexside PassHexside6, int PassHexHind,
                         Constantvalues.Hexrole PassHexrole, String Passcontrol, double PassTargetID,
                         int PassSolid, LinkedList<SmokeHolder> PassSmokelist, LinkedList<HindInLOS> PassHindInLOS, int PassScenID, Location PassLocation, boolean PassHexLOSHApplies) {
        // called by Linqdata.AddtoCollection
        // creates new CombatTerrain object for any hex involved in combat, passes Staircase and Control to base class constructor but does not use them
        super(PasshexTerrtype,
                PassHexside1, PassHexside2, PassHexside3, PassHexside4,
                PassHexside5, PassHexside6, Passcontrol, PassSmokelist, PassLocation);
        prHexTEM = PassLocation.getTerrain().getTEM();
        prHexHindvalue = PassHexHind;
        prhexdescvalue = PassLocation.getTerrain().getName();
        prHexrolevalue = PassHexrole;
        prHexLOSHApplies = PassHexLOSHApplies;
        prHexsideCrossedTEM = 0;
        prHexsideCrossedDesc = "";
        prLOSTarget.add(PassTargetID);
        prSolutionID = PassSolid;
        prscen  = ScenarioC.getInstance();
        prScendet = prscen.getScendet();
        prLocation = PassLocation;
        prHex = PassLocation.getHex();
        prhindInLOS = PassHindInLOS;
    }

    public Constantvalues.Hexrole getHexrole() {
        return prHexrolevalue;
    }

    public void setHexrole(Constantvalues.Hexrole value) {
        prHexrolevalue = value;
    }

    public int getHexTEM() {
        return prHexTEM;
    }

    public void setHexTem(int value) {
        prHexTEM = value;
    }

    public ArrayList getTargetID() {
        return prLOSTarget;
    }

    public void setTargetID(ArrayList value) {
        prLOSTarget = value;
    }

    public int getHexHind() {
        return prHexHindvalue;
    }

    public void setHexHind(int value) {
        prHexHindvalue = value;
    }

    public String gethexdesc() {
        return prhexdescvalue;
    }

    public boolean getHexLOSHApplies() {
        return prHexLOSHApplies;
    }

    public void setHexLOSHApplies(boolean value) {
        prHexLOSHApplies = value;
    }

    public int getHexsideCrossedTEM() {
        return prHexsideCrossedTEM;
    }

    public String getHexsideCrosseddesc() {
        return prHexsideCrossedDesc;
    }

    public int getSolID() {
        return prSolutionID;
    }

    public void setSolID(int value) {
        prSolutionID = value;
    }

    public String getVisLOSHName(){return prVisLOSHName; }
    public void setVisLOSHName(String value){prVisLOSHName = value;}
    public LinkedList<HindInLOS> gethindInLOS() {return prhindInLOS;}

    public Location getLocation() {return prLocation;}
    public void setLocation(Location value){prLocation=value;}

    public boolean IsFirer() {
        boolean CheckIsFirer = (prHexrolevalue == Constantvalues.Hexrole.Firer ||
                prHexrolevalue == Constantvalues.Hexrole.FirerInt ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTarget ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTargetInt) ? true : false;
        return CheckIsFirer;
    }

    public boolean IsIntervening() {
        return (prHexrolevalue == Constantvalues.Hexrole.Intervening ||
                prHexrolevalue == Constantvalues.Hexrole.FirerInt ||
                prHexrolevalue == Constantvalues.Hexrole.TargetInt ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTargetInt ? true : false);
    }

    public boolean IsTarget() {
        return (prHexrolevalue == Constantvalues.Hexrole.Target ||
                prHexrolevalue == Constantvalues.Hexrole.TargetInt ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTarget ||
                prHexrolevalue == Constantvalues.Hexrole.FirerTargetInt ? true : false);
    }

    public Constantvalues.Hexside TargetHexSideCrossed(String lasthexname, String Firsthexname) {
        // Called by Combat.CalcCombatFPandDRM
        // returns value of hexside terrain type: 6500 if none
        // and if hexside terrain exists, sets values of
        // HexsideCrossedTEM and HexsideCrossedDesc properties

        // determine which hexside crossed (0-5)
        int  hexsidecrossedvalue= SharedhexsideAdjacentHexes(Firsthexname, lasthexname);
        IsSide SideTest = new IsSide();
        Terrain hexsideterrain = SideTest.GethexsideTerrain(hexsidecrossedvalue);
        ConversionC DoConversion = new ConversionC();
        Constantvalues.Hexside hexsidecrossedtype= DoConversion.ConverttoHexside(hexsideterrain);
        // determine hexside type of hexside crossed
        //VASL.LOS.Map.Terrain Terrainofhexsidecrossed = lasthex.getHexsideTerrain(hexsidecrossedvalue);
        /*switch (hexsidecrossedvalue) {
            case 1:
                if (this.getHexside1() != Constantvalues.Hexside.NoTerrain) {
                    hexsidecrossedtype = this.getHexside1();
                }
                break;
            case 2:
                if (this.getHexside2() != Constantvalues.Hexside.NoTerrain) {
                    hexsidecrossedtype = this.getHexside2();
                }
                break;
            case 3:
                if (this.getHexside3() != Constantvalues.Hexside.NoTerrain) {
                    hexsidecrossedtype = this.getHexside3();
                }
                break;
            case 4:
                if (this.getHexside4() != Constantvalues.Hexside.NoTerrain) {
                    hexsidecrossedtype = this.getHexside4();
                }
                break;
            case 5:
                if (this.getHexside5() != Constantvalues.Hexside.NoTerrain) {
                    hexsidecrossedtype = this.getHexside5();
                }
                break;
            case 6:
                if (this.getHexside6() != Constantvalues.Hexside.NoTerrain) {
                    hexsidecrossedtype = this.getHexside6();
                }
                break;
            default:
                hexsidecrossedtype = Constantvalues.Hexside.NoTerrain;
        }*/
        // if not No Terrain then determine TEM and Desc of hexside crossed
        if (!(hexsideterrain == null)) {
            // set property values
            prHexsideCrossedTEM = hexsideterrain.getTEM();
            prHexsideCrossedDesc = hexsideterrain.getName();
        }
        return hexsidecrossedtype;
    }

    public int SharedhexsideAdjacentHexes(String Firsthexname, String Secondhexname){
        // returns the value of the commmon hexside between two adjacent hexes
        // returns the hexside of the Firsthex
        Hex firsthex = prscen.getGameMap().getHex(Firsthexname);
        Hex secondhex = prscen.getGameMap().getHex(Secondhexname);

        for(int x = 0; x < 6; x++) {
            Hex adjacentHex = prscen.getGameMap().getAdjacentHex(secondhex, x);
            if (adjacentHex == firsthex) {
                return x;
            }
        }
        return -1;
    }

    public int GetScenFeatTEM(String FeatureName) {
        // called by IFT.Combatdrm
        // returns TEM a of cumulative Scenario Terrain in a hex
        // along with description
        // THIS NEEDS TO BE REWORKED AS MORE SCEN FEATURES GET MOVED INTO MAP TABLE (Smoke etc)

        IsTerrain IsTerrChk = new IsTerrain();
        int TempScenFeatTEM = 0;  // temp variables used to store deta
        String TempFeatureName = "";
        boolean FirerHex = false;
        int ScenFeatTEM = 0;
        /*if (getScenFeatcol().size() == 0) {
            // no scenario features currently exist in this scenario
            ScenFeatTEM = 0;
            FeatureName = "";
            //GameModule.getGameModule().getChatter().send("No Scenario Terrain currently exists in the game: CombatTerrain.GetScenFeatTEM");
        } else {
            for (ScenarioTerrain ScenFeat: getScenFeatcol()){
                // need to check each ScenFeat as more than one can
                // exist per hex (ie foxhole and wire) - BUT TEM NOT CUMULATIVE
                // reset temp variables
                TempScenFeatTEM = 0; TempFeatureName = "";
                // check for hex match
                if (ScenFeat.getHexname() == getHexName()) {
                    // get type of terrain found
                    if (IsTarget()) {
                        ConversionC DoConversion = new ConversionC();
                        Constantvalues.Location hexterrtype = DoConversion.getLocationtypefromVASLLocation(ScenFeat.gethexlocation());
                        if (!IsTerrChk.IsLocationTerrainA(hexterrtype, Constantvalues.Location.HindranceFeature))
                            ;
                        TempScenFeatTEM = ScenFeat.GetTEM(TempFeatureName);
                    } else {
                        //'TempScenFeatTEMLOSH = ScenFeat.GetLOSH(false, true, TempFeatureName, OBALOSH)
                        //'TargetLOSH = TempScenFeatTEMLOSH
                        //'TargLOSHName = TempFeatureName & Space(1) & TargLOSHName
                    }
                }else {
                    if (IsFirer()) {
                        FirerHex = true;
                        // 'TempScenFeatTEMLOSH = ScenFeat.GetLOSH(FirerHex, false, TempFeatureName, OBALOSH)
                    }
                }
                if (TempScenFeatTEM > 0) {
                        ScenFeatTEM += TempScenFeatTEM;
                        FeatureName += TempFeatureName + " ";
                
                }
            }
            *//*'' check for Smoke
            ' Getlocs = New Terrainvalues.GetALocationFromMapTable(Game.Scenario.LocationCol)
            ' LocToUse As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromHex(this.LocIndex)
            'if LocToUse.Smoke > 0 Then '
            Smoke in
            location
            '    '
            determine TEM /
            LOSH drm
            '    Select case LocToUse.Smoke
            '        case EnumCon.VisHind.BlazeStone, EnumCon.VisHind.BlazeWood, EnumCon.VisHind.GunSmoke, EnumCon.VisHind.OBASmoke
            '            GetScenFeatTEMLOSH += 3
            '        case EnumCon.VisHind.GreyDisp, EnumCon.VisHind.GunSmokeDisp, EnumCon.VisHind.GreyWP, EnumCon.VisHind.GunSmokeDisp,
            '            EnumCon.VisHind.GunWP, EnumCon.VisHind.InfSmoke, EnumCon.VisHind.OBASmokeDisp, EnumCon.VisHind.VehDust
            '            GetScenFeatTEMLOSH += 2
            '        case EnumCon.VisHind.GreyWPDisp, EnumCon.VisHind.GunWPDisp, EnumCon.VisHind.InfWP
            '            GetScenFeatTEMLOSH += 1
            '        case Else
            '            GetScenFeatTEMLOSH += 0
            '    End Select
            'End if
            'if OBALOSH Then
            '    GetScenFeatTEMLOSH += 1
            '    FeatureName &= "OBA LOSH"
            'End if
            'Trim(FeatureName)*//*
        }*/
        return ScenFeatTEM;
    }

    public int TargetHexdrm(CombatCalcC.TargetVariables targetvar, LinkedList<IFTMods> DRMList, PersUniti TargetUnit, int hexspinedrm,
                             boolean alreadymoved, LinkedList<PersUniti> FireGroup_Thread) {

        boolean terraintest = targetvar.getTerraintest();
        boolean hexsidetest = targetvar.getHexSideTest();
        boolean ScenFeatureTest = targetvar.getScenFeatureTest();
        TerrainChecks TerrChk = new TerrainChecks();
        IFTMods NewDRM;
        boolean PositiveDRM = false;
        int FinalTargetHexdrm = 0;
        //int Featuredrm = targetvar.getFeaturedrm();
        int Hexsidedrm = targetvar.getHexsidedrm();

        if (targetvar.getFeaturedrm() > 0) {ScenFeatureTest = true;}

        // LOSH does not apply except for smoke/OBAFFE
        // determine if LOS blocked by LOSH

        // determine any TEM
        if (hexspinedrm > Hexsidedrm) {
            Hexsidedrm = hexspinedrm;
            hexsidetest = true;
        }
        if (terraintest && hexsidetest && ScenFeatureTest) {
            // hex is not open ground, hexside obstacle, scenario feature present
            if (targetvar.getTEMdrm() >= targetvar.getHexsidedrm() && targetvar.getTEMdrm() >= targetvar.getFeaturedrm()) {
                // use hex TEM
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                    NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += targetvar.getTEMdrm();
                } else {
                    targetvar.setTEMdrm(0);
                    //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                }
            } else if (Hexsidedrm > targetvar.getTEMdrm() && Hexsidedrm >= targetvar.getFeaturedrm()) {
                // use hexside
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Hexside)) {
                    NewDRM = new IFTMods(Hexsidedrm, Constantvalues.IFTdrm.Hexside, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getHexsidedesc());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += Hexsidedrm;
                }
                // check for Runway/Boulevard/City Square -1
                if (targetvar.getTEMdrm() == -1) {
                    if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                        NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                        DRMList.add(NewDRM);
                        FinalTargetHexdrm += targetvar.getTEMdrm();
                    } else {
                        targetvar.setTEMdrm(0);
                        //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                    }
                }
                //'MsgBox("Using Wall Advantage", , "Calculating Terrain DRM")
                targetvar.setTEMdrm(0);
            } else if (targetvar.getFeaturedrm() > targetvar.getTEMdrm() && targetvar.getFeaturedrm() > Hexsidedrm) {
                // use feature
                targetvar.setTEMdrm(0);
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Feature)) {
                    NewDRM = new IFTMods(targetvar.getFeaturedrm(), Constantvalues.IFTdrm.Feature, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getFeatureName());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += targetvar.getFeaturedrm();
                } else {
                    //'MessageBox.Show(FeatureName & " in " & this.HexName & " already added to DRM")
                }
                // check for Runway/Boulevard/City Square -1
                if (targetvar.getTEMdrm() == -1) {
                    if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                        NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                        DRMList.add(NewDRM);
                        FinalTargetHexdrm += targetvar.getTEMdrm();
                    } else {
                        targetvar.setTEMdrm(0);
                        //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                    }
                }
            } else {
                //'MsgBox("Need to check what conditions brought us here in CALCFP")
            }
        } else if (terraintest && hexsidetest  && !ScenFeatureTest) {
            // hex is not open ground, hexside obstacle, no scenario feature
            if (targetvar.getTEMdrm() >= Hexsidedrm) {
                // use hex TEM
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                    NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += targetvar.getTEMdrm();
                } else {
                    targetvar.setTEMdrm(0);
                    //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                }
            } else {
                // use hexside
                //'MsgBox("Using Wall Advantage", , "Calculating Terrain DRM")
                targetvar.setTEMdrm(0);
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Hexside)) {
                    NewDRM = new IFTMods(Hexsidedrm, Constantvalues.IFTdrm.Hexside, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getHexsidedesc());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += Hexsidedrm;
                } else {
                    Hexsidedrm = 0;
                    //'MessageBox.Show(SideTerrainName & " in " & this.HexName & " already added to DRM")
                }
                // check for Runway/Boulevard/City Square -1
                if (targetvar.getTEMdrm() == -1) {
                    if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                        NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                        DRMList.add(NewDRM);
                        FinalTargetHexdrm += targetvar.getTEMdrm();
                    } else {
                        targetvar.setTEMdrm(0);
                        //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                    }
                }
            }

        } else if (terraintest && !hexsidetest && ScenFeatureTest) {
            // hex is not open ground, scenario feature present, hexside clear
            if (targetvar.getTEMdrm() >= targetvar.getFeaturedrm()) {
                // use hex TEM
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                    NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += targetvar.getTEMdrm();
                } else {
                    targetvar.setTEMdrm(0);
                    //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                }
            } else {
                // use FeatureTEM
                targetvar.setTEMdrm(0);
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Feature)) {
                    NewDRM = new IFTMods(targetvar.getFeaturedrm(), Constantvalues.IFTdrm.Feature, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getFeatureName());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += targetvar.getFeaturedrm();
                } else {
                    //'MessageBox.Show(FeatureName & " in " & this.HexName & " already added to DRM")
                }
                // check for Runway/Boulevard/City Square -1
                if (targetvar.getTEMdrm() == -1) {
                    if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                        NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                        DRMList.add(NewDRM);
                        FinalTargetHexdrm += targetvar.getTEMdrm();
                    } else {
                        targetvar.setTEMdrm(0);
                        //'MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
                    }
                }
            }
        } else if(!(terraintest) && hexsidetest && ScenFeatureTest) {
            // hexside obstacle with scenario feature and open ground hex
            if (Hexsidedrm >= targetvar.getFeaturedrm()) {
                // use hexside TEM
                //'MsgBox("Using Wall Advantage", , "Calculating Terrain DRM")
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Hexside)) {
                    NewDRM = new IFTMods(Hexsidedrm, Constantvalues.IFTdrm.Hexside, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getHexsidedesc());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += Hexsidedrm;
                } else {
                    Hexsidedrm = 0;
                    //'MessageBox.Show(SideTerrainName & " in " & this.HexName & " already added to DRM")
                }
            } else {
                // use Feature TEM
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Feature)) {
                    NewDRM = new IFTMods(targetvar.getFeaturedrm(), Constantvalues.IFTdrm.Feature, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getFeatureName());
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += targetvar.getFeaturedrm();
                } else {
                    //'MessageBox.Show(FeatureName & " in " & this.HexName & " already added to DRM")
                }
            }
            targetvar.setTEMdrm(0);

        } else if (terraintest && !hexsidetest && !ScenFeatureTest) {
            // hex is not open ground and hexside is clear, no scenario feature
            // Use TEM
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Terrain)) {
                NewDRM = new IFTMods(targetvar.getTEMdrm(), Constantvalues.IFTdrm.Terrain, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getTerrainName());
                DRMList.add(NewDRM);
                FinalTargetHexdrm += targetvar.getTEMdrm();
            } else {
                targetvar.setTEMdrm(0);
                // MessageBox.Show(TerrainName & " in " & this.HexName & " already added to DRM")
            }
        } else if (!terraintest && hexsidetest && !ScenFeatureTest) {
            // hexside obstacle and open ground hex, no scenario feature
            targetvar.setTEMdrm(0);
            // use hexside
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Hexside)) {
                NewDRM = new IFTMods(Hexsidedrm, Constantvalues.IFTdrm.Hexside, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getHexsidedesc());
                DRMList.add(NewDRM);
                FinalTargetHexdrm += Hexsidedrm;
            } else {
                Hexsidedrm = 0;
                //'MessageBox.Show(SideTerrainName & " in " & this.HexName & " already added to DRM")
            }
        } else if (!terraintest && !hexsidetest && ScenFeatureTest) {
            // scenario feature and hexside clear, hex is open ground
            targetvar.setTEMdrm(0);
            // use feature
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.Feature)) {
                NewDRM = new IFTMods(targetvar.getFeaturedrm(), Constantvalues.IFTdrm.Feature, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getFeatureName());
                DRMList.add(NewDRM);
                FinalTargetHexdrm += targetvar.getFeaturedrm();
            } else {
                // MessageBox.Show(FeatureName & " in " & this.HexName & " already added to DRM")
            }
        } else if (!terraintest && !hexsidetest && !ScenFeatureTest) {
            // hex is open ground, hexside clear, no scenario feature
            targetvar.setTEMdrm(0);
            // use none
            int Targterraintype = 0;
            double Targbaselevel = getHexBaseLevel();
            boolean TempFG = false;
            double TempInhex = 0; double TotalFirerlevel =0; double Firerinhexlevel =0; double FirerBaseLevel = 0;
            for (PersUniti FiringUnit : FireGroup_Thread) {   //NOT SURE THIS IS CALCULATING HEIGHT PROPERLY
                TempInhex = FiringUnit.getbaseunit().getLevelinHex();
                if (TempInhex > Firerinhexlevel) {
                    Firerinhexlevel = TempInhex;
                }
                FirerBaseLevel = FiringUnit.getbaseunit().getHex().getBaseHeight();
            }
            TotalFirerlevel = FirerBaseLevel + Firerinhexlevel;
            if (TotalFirerlevel < (Targbaselevel + TargetUnit.getbaseunit().getLevelinHex())) {
                    // HA applies when Target above Firer and no other TEM
                    targetvar.setTEMdrm(1);
                    if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.HA)) {
                        NewDRM = new IFTMods(1, Constantvalues.IFTdrm.HA, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), "Height Advantage");
                        DRMList.add(NewDRM);
                        FinalTargetHexdrm += 1;
                    } else {
                        targetvar.setTEMdrm(0);
                        //'MessageBox.Show("HA in " & this.HexName & " already added to DRM")
                    }
            }

        } else {
            //'MsgBox("How in heck did we end up here?", , "Calculating which Terrain drm to use")
        }
        // Vehicle TEM
        targetvar.setTEMdrm(targetvar.getTEMdrm() + VehicleTEMCheck(DRMList, TargetUnit));
        // FFMO check
        for (IFTMods IFTdrmTest: DRMList) {
            if (IFTdrmTest.getDRM() > 0) {
                PositiveDRM = true;
                break;
            }
        }
        if (!PositiveDRM && (TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Moving || 
                            TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.AssaultMoving || 
                            TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Wading ||
                            TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.TI &&
                            alreadymoved == false)) {
            if (TerrChk.IsLocationOGforFFMO(TargetUnit.getbaseunit().gethexlocation(), TargetUnit.getbaseunit().gethexPosition())) {
                // all FFMO conditions exist
                targetvar.setTEMdrm(targetvar.getTEMdrm() + -1);
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.FFMO)) {
                    NewDRM = new IFTMods(-1, Constantvalues.IFTdrm.FFMO, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), "FFMO");
                    DRMList.add(NewDRM);
                    FinalTargetHexdrm += -1;
                } else {
                    targetvar.setTEMdrm(targetvar.getTEMdrm() + 1);
                    //'MessageBox.Show("FFMO in " & ComTer.HexName & " already added to DRM")
                }
            }
        }
        // Now do FFNAM
        if ((TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Moving || 
                TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Wading || 
                TargetUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.TI) &&
                alreadymoved == false){
            targetvar.setTEMdrm(targetvar.getTEMdrm() + -1);
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.FFNAM)) {
                NewDRM = new IFTMods(-1, Constantvalues.IFTdrm.FFNAM, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), "FFNAM");
                DRMList.add(NewDRM);
                FinalTargetHexdrm += -1;
            } else {
                targetvar.setTEMdrm(targetvar.getTEMdrm() + 1);
                //'MessageBox.Show("FFNAM in " & this.HexName & " already added to DRM")
            }
        }
        // Now do Target has a FT    - THIS HAS TO BE APPLIED TO A UNIT NOT A los  DELETE FROM HERE
        if (TargetUnit.getTargetunit().HasFT()) {
            targetvar.setTEMdrm(targetvar.getTEMdrm() + -1);
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.TargHasFT)) {
                NewDRM = new IFTMods(-1, Constantvalues.IFTdrm.TargHasFT, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), "FT");
                DRMList.add(NewDRM);
                FinalTargetHexdrm += -1;
            } else {
                targetvar.setTEMdrm(targetvar.getTEMdrm() + 1);
                //'MessageBox.Show("FT in " & this.HexName & " already added to DRM")
            }
        }
        // Now do LOSH in target hex
        if (targetvar.getLOSHdrm() > 0) {
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.TargLOSH)) {
                NewDRM = new IFTMods(targetvar.getLOSHdrm(), Constantvalues.IFTdrm.TargLOSH, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), getLocation(), targetvar.getLOSHName());
                DRMList.add(NewDRM);
                FinalTargetHexdrm += targetvar.getLOSHdrm();
            } else {
                targetvar.setLOSHdrm(0);
                //'MessageBox.Show(TargLOSHName & " in " & this.HexName & " already added to DRM")
            }
        }
        return FinalTargetHexdrm;
    }

    public int getTargetLOSHdrm(LinkedList<IFTMods> DRMList, PersUniti TargetUnit){
        // first OBA
        int OBALOSH = OBACheck();
        IFTMods NEWDrm = null;
        String Smokestring = "";
        // now add visibility losh to drm list
        if (OBALOSH > 0) {
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.OBA)) {
                NEWDrm = new IFTMods(OBALOSH, Constantvalues.IFTdrm.OBA, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), "OBA");
                DRMList.add(NEWDrm);
            } else {
                OBALOSH = 0;
                GameModule.getGameModule().getChatter().send(this.getVisLOSHName() + " in " + this.getHexName() + " already added to DRM");
            }
        }
        // then Smoke
        int Smokedrm=0;
        if (prhindInLOS != null) {
            for (HindInLOS testhind : prhindInLOS) {
                //check for OBA hind
                if (testhind.getHindtype() == Constantvalues.Feature.Smoke) {
                    Smokedrm= testhind.getHindvalue() ;
                }
            }
        }

        if (Smokedrm > 0) {
            if (this.NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.VisLoSH)) {
                NEWDrm = new IFTMods(Smokedrm, Constantvalues.IFTdrm.VisLoSH, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), Smokestring);
                DRMList.add(NEWDrm);
            } else {
                Smokedrm = 0;
                GameModule.getGameModule().getChatter().send(this.getVisLOSHName() + " in " + this.getHexName() + " already added to DRM");
            }
        }
        return OBALOSH + Smokedrm;

    }
    public boolean NotAlreadyAddedToDRMList(LinkedList<IFTMods> ListtoCheck, PersUniti targetUnit, Constantvalues.IFTdrm Testdrmtype) {
        // called by CombatDRM
        // determines if LOSH or Terrain DRM already added by a unit in the same target hex

        if (ListtoCheck == null) {return true;} // if nothing in list then nothing added so true
        if (this.IsIntervening()) {
            for (IFTMods DRMTest : ListtoCheck) {
                if (targetUnit.getbaseunit().gethexlocation().equals(DRMTest.getDRMLocation()) && DRMTest.getDRMType() == Testdrmtype && DRMTest.getDRMLocation().equals(this.getLocation())) {
                    return false;
                }
            }
            for (IFTMods DRMTest : ListtoCheck) {
                if (targetUnit.getbaseunit().gethexlocation().equals(DRMTest.getDRMLocation()) && this.getLocation().equals(targetUnit.getbaseunit().gethexlocation()) && DRMTest.getDRMType() == Testdrmtype) {
                    return false;
                }
            }
            // special test for OBA which can only be added once
            for (IFTMods DRMTest: ListtoCheck){
                if (DRMTest.getDRMType() == Constantvalues.IFTdrm.OBA && Testdrmtype == Constantvalues.IFTdrm.OBA) {
                    return false;
                }
            }
        }
        // if this far then not already added
        return true;
    }

    public void SetTargetVariables(CombatCalcC.TargetVariables targetvar, PersUniti TargetUnit, Hex lasthex, String Terrainname,
            Constantvalues.Hexside Passhexsidetype, Constantvalues.LOS LOSFollows, Hex Seehex) {
        // retrieve LOS status and LOS result values
        TerrainChecks TerrChk = new TerrainChecks();
        Map.LOSStatus losstatus = Seehex.getMap().getLOSStatus();
        LOSResult losresult = prscen.getIFT().getLOSResult();
        // working with target hex so need to determine where Target is
        Location TargetLoc = losresult.getTargetLocation();
        int HexTEM = TargetLoc.getTerrain().getTEM();
        // check hexside of entry into target hex
        int [] targetenterhexsides = losstatus.targetEnterHexsides;
        // no need to check other hexes as LOS has already cleared hexsides of intervening hexes
        boolean IsWallSide = false;
        boolean TargUsingWA = false; // whether target is WA
        int Hexsidetem = 0; // value of hexside TEM
        prHexsideCrossedtype = Constantvalues.Hexside.NoTerrain;
        Constantvalues.Hexside hexside = Constantvalues.Hexside.NoTerrain; // temp hexside type value
        Terrain hexsideterrain = null;

        // First set hexside variables: hexsidetype, _HexsidecrossedTEM, _HexSideCrossedDesc
        if (losresult.isLOSis60Degree() || losresult.isLOSisHorizontal()) {
            int sidetocheck=0;
            for (int x = 0; x < 2; x++) { // test entry sides  and then entry hexspine
                // determine hexside type of hexside crossed
                sidetocheck = targetenterhexsides[x];
                hexsideterrain = TargetLoc.getHex().getHexsideTerrain(sidetocheck);
                // if not No Terrain then determine TEM and Desc of hexside crossed
                if (hexsideterrain != null) {
                    // set property values
                    Hexsidetem = hexsideterrain.getTEM();
                    if (Hexsidetem !=0 && prHexsideCrossedTEM < Hexsidetem) {  // select the hexside with the highest TEM
                        Sethexsidevalues(Hexsidetem, hexsideterrain, targetvar);
                        IsWallSide=true;
                    }
                }
            }
            // now check incoming hexspine
            sidetocheck = losresult.getTargetEnterHexspine();
            Hex adjhex = prscen.getGameMap().getAdjacentHex(TargetLoc.getHex(), sidetocheck);
            int adjhexsidetocheck= gethexspineside(sidetocheck);
            if (adjhexsidetocheck > -1) {
                hexsideterrain = adjhex.getHexsideTerrain(adjhexsidetocheck);
                // if not No Terrain then determine TEM and Desc of hexspine
                if (hexsideterrain != null) {
                    // set property values
                    Hexsidetem = hexsideterrain.getTEM();
                    if (Hexsidetem != 0 && prHexsideCrossedTEM < Hexsidetem) {  // select the hexside with the highest TEM
                        Sethexsidevalues(Hexsidetem, hexsideterrain, targetvar);
                        IsWallSide=true;
                    }
                }
            }
        } else {  // LOS crosses one specific hexside only
            hexsideterrain = TargetLoc.getHex().getHexsideTerrain(targetenterhexsides[0]);
            // if not No Terrain then determine TEM and Desc of hexside crossed
            if (hexsideterrain != null) {
                // set property values
                Hexsidetem = hexsideterrain.getTEM();
                if (Hexsidetem !=0 && prHexsideCrossedTEM < Hexsidetem) {  // select the hexside with the highest TEM
                    Sethexsidevalues(Hexsidetem, hexsideterrain, targetvar);
                    IsWallSide=true;
                }
            }
        }

        // Now deal with hex itself: position is more specfic so takes precedence over Location if >0
        if (TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WallAdv ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WallAdv ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WACrestStatus0 ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WACrestStatus1 ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WACrestStatus2 ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WACrestStatus3 ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WACrestStatus4 ||
                TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WACrestStatus5) {
            if (losstatus.sourceHex != losstatus.targetHex) {
                TargUsingWA = true;
                if (hexsideterrain != null && hexsideterrain.isHexsideTerrain() ) {
                    Hexsidetem= hexsideterrain.getTEM();
                    Sethexsidevalues(Hexsidetem, hexsideterrain, targetvar);
                    IsWallSide=true;
                } else { //'use OG even if in obstacle hex
                   if (HexTEM < 0) {
                        targetvar.setTEMdrm(HexTEM);
                   } else {
                        targetvar.setTEMdrm(0);
                   }
                }
            }
        } else if (TargetUnit.getbaseunit().gethexPosition() != null && TargetUnit.getbaseunit().gethexPosition() != Constantvalues.AltPos.None) {
            // use position TEM
            if (TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest0 ||
                    TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest1 ||
                    TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest2 ||
                    TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest3 ||
                    TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest4 ||
                    TargetUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.ExitedCrest5) {
                // if exitedcrest then use location TEM
                targetvar.setTEMdrm(HexTEM);
            } else {
                targetvar.setTEMdrm(TerrChk.GetPositionTEM(TargetUnit.getbaseunit().gethexPosition()));
                targetvar.setTerrainName(TerrChk.GetPositionDesc(TargetUnit.getbaseunit().gethexPosition()));
                targetvar.setTerraintest(true);
            }
        } else {
            // Use location tem (if shoudl be otherterrain will be set by position check above
            if (TargetUnit.getbaseunit().IsLocationAMatch(prLocation)) {
                targetvar.setTEMdrm(HexTEM);
                targetvar.setTerrainName(TargetLoc.getTerrain().getName());
            }
        }
        if (targetvar.getTEMdrm() != 0) {targetvar.setTerraintest(true);}
        // special case; any others?
        if (targetvar.getTEMdrm() == 0 && this.getHextertype() == Constantvalues.Location.CitySquareShellhole) {targetvar.setTerraintest(true);}
        // now check hexside - set value of hexsidetest
        // need to deal with adjacent to firer hex special case
        if (losresult.getRange() == 1 & TargetLoc.getHex().getMap().isAdjacentHexside(TargetLoc.getHex(), TargetLoc.getHex().getHexsideLocation(targetenterhexsides[0]))) {  //IS THIS RIGHT ?? 11/17
            if (IsWallSide && !TargUsingWA) {
                // firer/target on either side of wall, etc and target does not have WA
                // target still gets hexside tem unless firer has WA
                // get all enemy units
                Constantvalues.Nationality enemyside1 = SetEnemy1(TargetUnit.getbaseunit().getNationality());
                Constantvalues.Nationality enemyside2 = SetEnemy2(TargetUnit.getbaseunit().getNationality());
                LinkedList<PersUniti> EnemyToCheck = new LinkedList<PersUniti>();
                for (PersUniti testforenemy : Scencolls.Unitcol) {
                    if ((testforenemy.getbaseunit().getNationality() == enemyside1 || testforenemy.getbaseunit().getNationality() == enemyside2) &&
                            (testforenemy.getbaseunit().getHexName().equals(Seehex.getName()) && testforenemy.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible &&
                                    testforenemy.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.KIAInf &&
                                    testforenemy.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.NotInPlay &&
                                    testforenemy.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Prisoner &&
                                    testforenemy.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Unarmed)) {
                        EnemyToCheck.add(testforenemy);
                    }
                }

                for (PersUniti EnemyPresent : EnemyToCheck) {
                    if (EnemyPresent.getbaseunit().HasWallAdvantage()) {
                        // no hexside tem for Target
                        prHexsideCrossedtype = Constantvalues.Hexside.NoTerrain;
                        targetvar.setHexSideTest(false);
                        prHexsideCrossedDesc = "";
                        prHexsideCrossedTEM = 0;
                    }
                }
            }
        } else {
            // check hexside of entry into target hex
            if (prHexsideCrossedtype != Constantvalues.Hexside.NoTerrain) {
                targetvar.setHexSideTest(true);
            }
        }
    }

    private void Sethexsidevalues(int Hexsidetem, Terrain hexsideterrain, CombatCalcC.TargetVariables targetvar) {
        prHexsideCrossedTEM = Hexsidetem;
        ConversionC DoConversion = new ConversionC();
        prHexsideCrossedtype = DoConversion.ConverttoHexside(hexsideterrain);
        prHexsideCrossedDesc = hexsideterrain.getName();
        targetvar.setHexsidedesc(prHexsideCrossedDesc);
        targetvar.setHexsidedrm(prHexsideCrossedTEM);
        targetvar.setHexSideTest(true);
    }
    private int gethexspineside(int sidetocheck) {
        int adjhexsidetocheck = 0;
        switch (sidetocheck) {
            case 0:
                return adjhexsidetocheck = 4;
            case 1:
                return adjhexsidetocheck = 5;
            case 2:
                return adjhexsidetocheck = 0;
            case 3:
                return adjhexsidetocheck = 1;
            case 4:
                return adjhexsidetocheck = 2;
            case 5:
                return adjhexsidetocheck = 3;
            default:
                return adjhexsidetocheck = -1;
        }

    }
    public int InterveningDRM(LinkedList<IFTMods> DRMList, PersUniti TargetUnit) {
        int VisLOSH = 0;
        int OBAHind =0;
        int Featuredrm =0;
        int LOSHdrm = 0;
        int FinalLOSHDRM = 0;
        String VisLOSHName = "";
        String OBAHindName = "";
        String Featurename = "";
        String LOSHName = "";
        IFTMods NEWDrm;

        // existence of hex Hindrance set in LOS routine; check for value now
        if (this.getHexLOSHApplies()) {
            LOSHdrm = this.getHexHind();
            // temporary while debugging UNDO
            /*if (Map == Constantvalues.Map.BloodReef) {
                // MsgBox("Blood Reef hinterland situation)")
                // LOSHAppliesTo will be true but HexHind should be 0 for open ground - both values set in Map.HexbyHexMoveAlongOK
                LOSHdrm = +1;
                // This assumes that all such cases will be Hinterland and not just a plain error
                // Could add an error checking routine - confirm that one of firer/target hex is ocean but have not done so at this stage
                // need to change LOSHname
                LOSHName = "Hinterland";
            } else {
                LOSHName = this.gethexdesc();
            }*/
            LOSHName = this.gethexdesc();
        }

        // Don't need to check alternate hexes if LOS is along hexside as VASL LOSCheck has already done so and determined where hindrances apply. TEST THIS
        // check for non-terrain LOSH such as vehicles, OBA, Smoke
        if (prhindInLOS != null) {
            for (HindInLOS testhind : prhindInLOS) {
                // need to check each item as more than one can exist per hex
                // this routine only needs to check for LOSH; LOS blocks already done;

                if (testhind.getHindtype() == Constantvalues.Feature.Smoke) {
                    VisLOSH = testhind.getHindvalue();
                    VisLOSHName = testhind.getHinddesc();
                } else if (testhind.getHindtype() == Constantvalues.Feature.FFE1 ||
                        testhind.getHindtype() == Constantvalues.Feature.FFE2 ||
                        testhind.getHindtype() == Constantvalues.Feature.FFEC) {
                    OBAHind = testhind.getHindvalue();
                    OBAHindName = testhind.getHinddesc();
                } else if (testhind.getHindtype() == Constantvalues.Feature.Wreck ||
                    testhind.getHindtype() == Constantvalues.Feature.BOWreck ||
                        testhind.getHindtype() == Constantvalues.Feature.Vehicle) {
                    Featuredrm = testhind.getHindvalue();
                    Featurename = testhind.getHinddesc();
                }
            }
        }
        /*// now see if althexgrain needs to be checked
        Constantvalues.Location hexterrtype; int GetAltLOSH; boolean AHGAlreadyDone = false;
        String AltHextoCheck=""; int AltFeatLOSH=0; String AltFeatName = ""; boolean OBALOSH = false;
        int WhichLOSHToUse = 0; int currenthexdrm = 0; int alternatehexdrm = 0;
        int AltVisLOSH = 0; String AltVisName = ""; boolean AltOBAAlreadyFound = false;

        if (LOSAlongHexside) {
                for (AltHexGTerrain AHGHex: AltHexesInLoSLIst) {
                    if (this.getHexName() == AHGHex.getUpperHexname()) {
                        AltHextoCheck = AHGHex.getLowerHexname();
                        break;
                    } else if (this.getHexName() == AHGHex.getLowerHexname()) {
                        AltHextoCheck = AHGHex.getUpperHexname();
                        break;
                    }
                }
                if (AltHextoCheck !="" ) {
                    double althexobstaclelevel = 0;
                    double altHexBAselevel = 0;
                    GetAltLOSH = 0;
                    LevelChecks LevelChk = new LevelChecks(prLocation);
                    Location Baselocation = LevelChk.GetLocationatLevelInHex(AltHextoCheck, 0);
                    ConversionC DoConversion = new ConversionC();
                    hexterrtype = DoConversion.ConverttoLocationtypefromVASLLocation(Baselocation);
                    if (IsTerrChk.IsLocationTerrainA(hexterrtype, Constantvalues.Location.HindranceHex)) {
                        altHexBAselevel = Baselocation.getBaseHeight();
                        althexobstaclelevel = Baselocation.getAbsoluteHeight();
                        GetAltLOSH = Baselocation.getTerrain().getLOSHindDRM();
                    } else {
                        GetAltLOSH = 0;
                        hexterrtype = Constantvalues.Location.NA;
                    }


                    if (Featuredrm > 0) {
                        if (DoesScenLOSHApplytothisLOS(Featuredrm, Featurename, TotalFirerlevel, TargetTotalLevel)) {
                            TerrainName = "";
                        } else {
                            Featuredrm = 0;
                            Featurename = "";
                        }
                    }
                    boolean TempFG = false;
                    // revised Feb 13
                    double hexobstaclelevel = 0;
                    double hexsidelevel = 0;
                    Location ComTerlocation = LevelChk.GetLocationatLevelInHex(this.getHexName(), 0);
                    hexterrtype = DoConversion.ConverttoLocationtypefromVASLLocation(ComTerlocation);
                    altHexBAselevel = ComTerlocation.getBaseHeight();
                    hexobstaclelevel = ComTerlocation.getAbsoluteHeight();
                    Location Altlocation = LevelChk.GetLocationatLevelInHex(AltHextoCheck, 0);
                    if (ComTerlocation.getBaseHeight() > Altlocation.getBaseHeight()) {
                        hexsidelevel = Altlocation.getBaseHeight();
                    } else {
                        hexsidelevel = ComTerlocation.getBaseHeight();
                    }
                    double LOSHTotalLevel = hexsidelevel;
                    if (TotalFirerlevel > LOSHTotalLevel || TargetTotalLevel > LOSHTotalLevel) {
                        this.setHexHind(0);
                        LOSHName = "";
                        GetAltLOSH = 0;
                        // 'MsgBox("LOSH does not apply as LOS goes over Hindrance", , "CombatDRM")
                    }
                    if (AltFeatLOSH > 0) {
                        for (CombatTerrain TempComTer : CombatTerrCol) {
                            if (TempComTer.getHexName() == AltHextoCheck) {
                                if (DoesScenLOSHApplytothisLOS(AltFeatLOSH, AltFeatName, TotalFirerlevel, TargetTotalLevel)) {
                                    TerrainName = "";
                                } else {
                                    AltFeatLOSH = 0;
                                    AltFeatName = "";
                                }
                            }
                        }
                    }
                    //why create this instance of CombatTerrain ???
                    Location PassNewLoc = null; boolean PassHexLOSHApplies = false; //  need to fix this and make it a real location and variable value
                    //GetALocationFromMap GetLocs = new GetALocationFromMap(LocationCol);
                    //String PassHexdesc = TerrChk.GetLocationData(Constantvalues.TerrFactor.Desc, hexterrtype);
                    LinkedList<SmokeHolder> Smokelist = TerrChk.SmokePresentinHex(Baselocation.getHex());
                    // may be able to delete all of the hexside stuff and just use VASL hexside info
                    ConversionC convert = new ConversionC();
                    Constantvalues.Hexside PassHexside1  = convert.ConverttoHexside((Baselocation.getHex().getHexsideLocation(1).getTerrain()));
                    Constantvalues.Hexside PassHexside2  = convert.ConverttoHexside((Baselocation.getHex().getHexsideLocation(2).getTerrain()));
                    Constantvalues.Hexside PassHexside3  = convert.ConverttoHexside((Baselocation.getHex().getHexsideLocation(3).getTerrain()));
                    Constantvalues.Hexside PassHexside4  = convert.ConverttoHexside((Baselocation.getHex().getHexsideLocation(4).getTerrain()));
                    Constantvalues.Hexside PassHexside5  = convert.ConverttoHexside((Baselocation.getHex().getHexsideLocation(5).getTerrain()));
                    Constantvalues.Hexside PassHexside6  = convert.ConverttoHexside((Baselocation.getHex().getHexsideLocation(0).getTerrain()));
                    CombatTerrain NextTempComTer = new CombatTerrain(hexterrtype, PassHexside1, PassHexside2, PassHexside3, PassHexside4, PassHexside5, PassHexside6, Baselocation.getTerrain().getTEM(),
                        Baselocation.getTerrain().getLOSHindDRM(), Constantvalues.Hexrole.Intervening, "", SolID, Smokelist, prhindInLOS, prscen.getScenID(), PassNewLoc, PassHexLOSHApplies);
                    if (NextTempComTer.getHexName() == AltHextoCheck) {
                        AltVisLOSH = CalcVisLOSH(AltVisName, AltOBAAlreadyFound, NextTempComTer, TotalFirerlevel, TargetTotalLevel, SeeHex, SeenHex);
                        if (AltVisLOSH == 0) {
                            AltVisName = "";
                        }
                    }

                    if (Featuredrm != 0 || AltFeatLOSH != 0 || this.getHexHind() != 0 || GetAltLOSH != 0 || VisLOSH != 0 || AltVisLOSH != 0) {
                        // there is a LOSH modifier somewhere
                        currenthexdrm = this.getHexHind() + Featuredrm + VisLOSH;
                        alternatehexdrm = GetAltLOSH + AltFeatLOSH + AltVisLOSH;
                        WhichLOSHToUse = (currenthexdrm >= alternatehexdrm ? 1 : 2);  // this may need debugging
                        String Testname = "";
                        if (WhichLOSHToUse == 1) {
                            // using LOSH in current hex
                            FinalLOSHName = (this.getHexHind() > 0 ? this.gethexdesc() : ""); // works because LOSH due to inherent
                            if (Featuredrm == 0) {
                                Featurename = "";
                            }
                            // 'Featuredrm = 0
                            LOSHdrm = this.getHexHind();
                            FinalLOSHDRM = currenthexdrm;
                            FinalVisLOSH = VisLOSH;
                            //FinalVisLOSHName = VisLOSHname;
                            Testname = this.getHexName(); // for test purposes only
                        } else {
                            // using LOSH in alternate hex
                            if (GetAltLOSH > 0) {
                                LOSHName = NextTempComTer.gethexdesc();
                            } else {
                                LOSHName = ""; // ' if(GetAltLOSH > 0, Linqdata.GetTerrainData(EnumCon.TerrFactor.Desc, hexterrtype), "")
                            }
                            if (AltFeatLOSH > 0) {
                                Featurename = AltFeatName;
                            } else {
                                Featurename = "";
                                Featuredrm = 0;
                            }
                            if (AltVisLOSH > 0) {
                                setVisLOSHName(AltVisName);
                                FinalVisLOSH = AltVisLOSH;
                            } else {
                                FinalVisLOSH = 0;
                                setVisLOSHName("");
                            }
                            FinalLOSHDRM = alternatehexdrm;
                            FinalLOSHName = (LOSHName + " " + Featurename + " " + getVisLOSHName());
                            //'Featuredrm = 0
                            UseAltName = AltHextoCheck;
                            UsingComTer = NextTempComTer;
                            LOSHdrm = GetAltLOSH;
                            Featuredrm = AltFeatLOSH;
                            VisLOSH = AltVisLOSH;
                        }
                        // 'MsgBox("Using " & FinalLOSHName & Space(1) & FeatureName & FinalVisLOSHName & " with " & FinalLOSHDrm.ToString & " drm from " & Testname)
                    } else {
                        // 'no LOSH modifiers
                        Featurename = "";
                        AltFeatName = "";
                        LOSHName = "";
                        WhichLOSHToUse = 0;
                    }
                } else {
                    // current ComTer hex is not an alternate hex grain hex; no action required
                    FinalLOSHDRM = LOSHdrm + Featuredrm + VisLOSH;
                    FinalFeatureDRM = Featuredrm;
                    FinalVisLOSH = VisLOSH;
                    //'msgbox for test purposes only - delete when working
                    //'MsgBox(ComTer.HexName & " is not an alternate hex grain hex; no action required")
                }
                TerrainName = "";
                if (Featuredrm >0) {
                    if (DoesScenLOSHApplytothisLOS(Featuredrm, Featurename, TotalFirerlevel, TargetTotalLevel)) {
                        TerrainName = "";
                    } else {
                        Featuredrm = 0;
                        Featurename = "";
                    }
                }
                // see if a hexspine drm needs to be calculated - and then store for use in ComTer.IsTarget section
                String Seenhexname= SeenHex.getName(); String Seehexname = SeeHex.getName();
                int LOSRange = prscen.getGameMap().range(prscen.getGameMap().getHex(Seehexname), prscen.getGameMap().getHex(Seenhexname), prscen.getGameMap().getMapConfiguration());
                int TestRange = prscen.getGameMap().range(prscen.getGameMap().getHex(Seehexname), prscen.getGameMap().getHex(this.getHexName()), prscen.getGameMap().getMapConfiguration());

                if (TestRange + 1 == LOSRange) {
                // hexspine connects to Targethex vertex
                *//*String AltHexName = "";
                for (GameLocation testlocation: LocationCol) {
                    if (testlocation.getHexnum() == AltHextoCheck) {
                        AltHexName = testlocation.getHexname();
                    }
                    break;
                }*//*
                    int SpineSide = SharedhexsideAdjacentHexes(AltHextoCheck, this.getHexName());
                    IsSide IsSideCheck = new IsSide(prLocation);
                    //GetALocationFromMap Getlocs = new GetALocationFromMap(LocationCol);
                    //GameLocation hexsidehex = Getlocs.RetrieveLocationfromHex(this.getLocIndex());
                    ConversionC DoConversion = new ConversionC();
                    Constantvalues.Hexside hexsideterrain  = DoConversion.ConverttoHexside(IsSideCheck.GethexsideTerrain(SpineSide));

                // temporary while debugging UNDO
                *//*if (IsSideCheck.IsAWallHedgeRdBlk(SpineSide, this.getLocIndex())) {
                    hexspinedrm =Integer.parseInt((IsSideCheck.GetSideData(Constantvalues.TerrFactor.HexsideTEM, hexsideterrain)));
                 // MessageBox.Show("hexspine " & SpineSide.ToString & " has a drm of " & hexspinedrm.ToString)
                }*//*
                }
                //'Else
                //'    Featuredrm = 0 : LOSHdrm = 0
                //'End if
        } else {*/
        // don't need this check as VASL Loscheck has already assured LOSH applies TEST THIS
        /*if (Featuredrm > 0) {
                    if (DoesScenLOSHApplytothisLOS(Featuredrm, Featurename, TotalFirerlevel, TargetTotalLevel)) {
                        TerrainName = "";
                    } else {
                        Featuredrm = 0;
                        Featurename = "";
                    }
        }*/
        FinalLOSHDRM = LOSHdrm + Featuredrm + VisLOSH +OBAHind;

        // having calculated all possible drm and decided which ones to use, now add them to the DRMList
        boolean AddIsGood = true;
        // need to do range test (A6.7) - two hexes of same range, add only one NO ALREADY DONE by VASL LOS check TEST
        /*if (lasthex != null) {
            if (RangeIsEqual(this.getLocation().getHex(), lasthex, SeeHex)) {
                if (Lasthexloshdrm >= FinalLOSHDRM) {
                    // don't add new mods
                    //'MessageBox.Show("Range equal to previous hex; no additional LOSH drm added (A6.7)")
                    AddIsGood = false;
                } else {
                    // get rid of previous mods
                    LinkedList<IFTMods> Removeitems = new LinkedList<IFTMods>();
                    for (IFTMods checkmod: DRMList) {
                        if (checkmod.getDRMLocation().getHex().equals(lasthex)) {
                            Removeitems.add(checkmod);
                        }
                    }
                    for (IFTMods RemoveMod : Removeitems) {
                        DRMList.remove(RemoveMod);
                    }
                }
            }
        }*/
        // NEED TO HANDLE situations with multiple of same type, which smoke to use - think vasl los has aready dealt with?
        // first hexhind and feature losh
        if (AddIsGood) {
            if (LOSHdrm != 0) {
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.HexHind)) {
                    NEWDrm = new IFTMods(LOSHdrm, Constantvalues.IFTdrm.HexHind, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), LOSHName);
                    DRMList.add(NEWDrm);
                } else {
                    LOSHdrm = 0;
                }
            }
            // at this point veh/wrk are only feature losh - should there be more?
            if (Featuredrm != 0) {

                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.VehWrkLOSH)) {
                    NEWDrm = new IFTMods(Featuredrm, Constantvalues.IFTdrm.VehWrkLOSH, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), Featurename);
                    DRMList.add(NEWDrm);
                } else {
                    Featuredrm = 0;
                    //'MessageBox.Show(FeatureName & " in " & UsingComTer.HexName & " already added to DRM")
                }
            }
            // now add OBA hindrance
            if (OBAHind != 0) {
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.OBA)) {
                        NEWDrm = new IFTMods(1, Constantvalues.IFTdrm.OBA, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), OBAHindName);
                        DRMList.add(NEWDrm);
                        //FinalLOSHDRM += 1;
                    }

            }
            // now add visibility losh
            if (VisLOSH > 0) {
                if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.VisLoSH)) {
                    NEWDrm = new IFTMods(VisLOSH, Constantvalues.IFTdrm.VisLoSH, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), VisLOSHName);
                    DRMList.add(NEWDrm);
                } else {
                    //FinalVisLOSH = 0;
                    // MessageBox.Show(FinalVisLOSHName & " in " & UsingComTer.HexName & " already added to DRM")
                }
            }
        }
        return FinalLOSHDRM;

    }
    private int VehicleTEMCheck(LinkedList<IFTMods> DRMList, PersUniti TargetUnit) {
        boolean PositiveDRM = false;
        IFTMods NewDRM;
        for (IFTMods IFTdrmTest: DRMList) {
            if (IFTdrmTest.getDRM() > 0 && IFTdrmTest.getDRMLocation().equals(this.getLocation())) {
                PositiveDRM = true;
                continue;
            }
        }
        if (!PositiveDRM && AFVIsPresent()) {
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.VehWrkTEM)) {
                NewDRM = new IFTMods(1, Constantvalues.IFTdrm.VehWrkTEM, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), "VehWrkTEM");
            }
            return 1;
        }
        return 0;
    }

    private boolean AFVIsPresent() {

        // temporary whiile debugging UNDO
        /*LinkedList<AFV> VehLIst = new LinkedList<AFV>();
        // Get Vehicle if exists
        try {
            VehLIst = (Linqdata.GetVehiclesInHex(this.getHexID())).ToList;
        } catch (Exception e) {
            return false; // no vehicles found in hex
        }
        if (VehLIst.size() == 0) {
            return false;
        } // no vehicles found
        // check veh type
        for (AFV Selvehicle : VehLIst) {
            // is afv
            if (!Selvehicle.ISAFV) {
                continue;
            }
            // veh status
            if (Selvehicle.getVehicleStatus == Constantvalues.Vmove.Motion || Selvehicle.getVehicleStatus == Constantvalues.Vmove.Moving || Selvehicle.getVehicleStatus == Constantvalues.Vmove.Moved) {
                continue;
            }
            if (Selvehicle.getVehicleStatus == Constantvalues.VStatus.Burning) {
                continue;
            }
            //'if Selvehicle.levelinhex <> ComTer.HexBaseLevel then Continue for
            //    'if Selvheicle entrenched or dug in - NEED TO PROGRAM

            // if find one valid vehicle then return true
            return true;
        }*/
        return false;  // no valid vehicle present
    }

    private boolean AFVInLOS() {
        // NEEDS TO BE PROGRAMMED
        return true;
    }

    /*public int CalcVisLOSH() { //String VisLOSHName, boolean OBAAlreadyFound, CombatTerrain NextComTer, double SeeLevelinHex, double SeenLevelinhex, Hex Seehex, Hex seenhex) {
        // called by IFTC.Combatdrm
        //        'determines if any visibility-LOSH exists in hex (only from OBA and/or Smoke coded so far ) - NEED TO add MIST AND DUST
        //        'NEED TO add CODE TO HANDLE DIFFERENT LEVEL TESTS
        //        'BOTH ABOVE AND BELOW PLUS ONE UP AND ONE DOWN (USE BLIND HEX CHECK TO RESOLVE)
        Constantvalues.VisHind Smoketype = Constantvalues.VisHind.None; double SmokeBaselevel = 0; // used when checking presence of smoke elsewhere in hex
        int VisLOSH = 0; // return value
        String VisLOSHName = ""; boolean AddSmoke = false; boolean SmokeLosCheck = false;
        // check for Smoke
        //' Getlocs = New Terrainvalues.GetALocationFromMapTable(LocationCol)
        //        ' LocToUse As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromHex(ComTer.LocIndex)
        double FirerLevel = SeeLevelinHex + NextComTer.getHexBaseLevel();
        double Targetlevel = SeenLevelinhex + NextComTer.getHexBaseLevel();
        if (NextComTer.getSmokeList().size() > 0) {  // Smoke in hex
            SmokeLosCheck = true;
        }
        if (NextComTer.IsFirer()) {
            ManageScenarioTerrain GetSmoke = new ManageScenarioTerrain();
            double Smokelevel = 0;
            for (SmokeHolder Smokeinstance : NextComTer.getSmokeList()) {
                SmokeBaselevel = Smokeinstance.getSmokeBaseLevel();
                Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.getSmoke()) + SmokeBaselevel + NextComTer.getHexBaseLevel();
                if (FirerLevel <= Smokelevel && (FirerLevel >= (SmokeBaselevel + NextComTer.getHexBaseLevel()))) {   //'  Targetlevel < Smokelevel) Or (FirerLevel = SmokeBaselevel - 1 And Targetlevel > SmokeBaselevel) Then
                    // determine TEM / LOSH drm
                    AddSmoke = true;
                    Smoketype = Smokeinstance.getSmoke();
                    break;
                }
            }
        } else if (SmokeLosCheck) {
            ManageScenarioTerrain GetSmoke = new ManageScenarioTerrain();
                double Smokelevel = 0;
        for (SmokeHolder Smokeinstance: NextComTer.getSmokeList()) {
                SmokeBaselevel = Smokeinstance.getSmokeBaseLevel();
                Smokelevel = GetSmoke.GetSmokeHeight(Smokeinstance.getSmoke()) + SmokeBaselevel + NextComTer.getHexBaseLevel();
                if (FirerLevel >= Smokelevel && Targetlevel >= Smokelevel) {
                    VisLOSH = 0; // Firer and Targer are above Smoke (Unit at same level as Smoke height is not effected by Smoke see A24.4 example)
                } else if (FirerLevel < SmokeBaselevel && Targetlevel < SmokeBaselevel) {
                    VisLOSH = 0; // LOS passes below smoke
                } else if ((Targetlevel == Smokelevel && FirerLevel < Smokelevel) ||
                    (Targetlevel < Smokelevel && FirerLevel == Smokelevel) ||
                    (Targetlevel == SmokeBaselevel - 1) ||
                    (FirerLevel >= SmokeBaselevel) ||
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
                    //  AddSmoke As Boolean = false
                    String Seenhexname= seenhex.getName(); String Seehexname = Seehex.getName();
                    for (GameLocation testlocation: LocationCol){
                        if (testlocation.getHexnum()== seenhexnum ){Seenhexname=testlocation.getHexname();}
                        break;
                    }
                    for (GameLocation testlocation: LocationCol){
                        if (testlocation.getHexnum()== Seehexnum ){Seehexname=testlocation.getHexname();}
                        break;
                    }
                    int ObsTargRange = prscen.getGameMap().range(prscen.getGameMap().getHex(NextComTer.getHexName()), prscen.getGameMap().getHex(Seenhexname), prscen.getGameMap().getMapConfiguration());
                    int FirerObsRange = prscen.getGameMap().range(prscen.getGameMap().getHex(NextComTer.getHexName()), prscen.getGameMap().getHex(Seehexname), prscen.getGameMap().getMapConfiguration());
                    double ObsTargElev = Smokelevel - Targetlevel;
                    double FirerObsElev = FirerLevel - Smokelevel;
                    if ((ObsTargRange == 1 && ObsTargElev >= 1) || (FirerObsRange == 1 && FirerObsElev <= -1)) {
                        // target is adjacent and below smoke: automatic blind or firer is adjacent and below smoke: automatic blind
                        // MessageBox.Show("Your LOS passes through adjacent smoke in " & Trim(NextComTer.HexName), "LOSH Check in Combat DRM")
                        AddSmoke = true;
                        Smoketype = Smokeinstance.getSmoke();
                    } else if ((FirerObsRange == 1 && FirerObsElev > 0) || (ObsTargRange == 1 && ObsTargElev < 0)) {
                        // firer is adjacent and above smoke, target is adjacent and above smoke: LOS clear
                    } else {
                        // neither firer nor target is adjacent to smoke
                        if (FirerObsElev > 0) {
                            // firer is above smoke
                            int FirerBlindHexes = (int)((FirerObsRange / 5) - (FirerObsElev - 1));
                            int TargetBlindHexes = (int)(NextComTer.getHexBaseLevel()) - (int)(Math.round(Targetlevel));  //, MidpointRounding.AwayFromZero))
                            int BlindHexes = 1 + FirerBlindHexes + TargetBlindHexes;
                            if (ObsTargRange <= BlindHexes) {
                                //MessageBox.Show("Your LOS passes through smoke in " & Trim(NextComTer.HexName), "LOSH Check in Combat DRM")
                                AddSmoke = true;
                                Smoketype = Smokeinstance.getSmoke();
                            }
                        } else {
                            // target is above obstacle
                            int FirerBlindHexes = (int) ((int) (ObsTargRange / 5) - (Math.abs(ObsTargElev) - 1));
                            int TargetBlindHexes = (int) (NextComTer.getHexBaseLevel() - Math.abs(FirerLevel));
                            int BlindHexes = FirerBlindHexes + TargetBlindHexes;
                            if (FirerObsRange <= BlindHexes) {
                             //MessageBox.Show("Your LOS passes through smoke in " & Trim(NextComTer.HexName), "LOSH Check in Combat DRM")
                                AddSmoke = true;
                                Smoketype = Smokeinstance.getSmoke();
                            }
                        }
                    }
                }
            }
        }
        if (AddSmoke) {
            switch (Smoketype) {
                case BlazeStone:
                case BlazeWood:
                case GunSmoke:
                case OBASmoke:
                    VisLOSH += 3;
                    break;
                case GreyDisp:
                case GunSmokeDisp:
                case GreyWP:
                case GunWP:
                case InfSmoke:
                case OBASmokeDisp:
                case VehDust:
                    VisLOSH += 2;
                    break;
                case GreyWPDisp:
                case GunWPDisp:
                case InfWP:
                    VisLOSH += 1;
                    break;
                default:
                    VisLOSH += 0;
            }
            if (NextComTer.IsFirer()) {
                VisLOSH += 1;
            } // Smoke in firer's location
            //'COME BACK AND FIX SMOKE NAMING LATER
            //    ' TerrChk = New Terrainvalues.TerrainChecks(LocationCol)
            VisLOSHName = "Smoke"; // 'TerrChk.GetPositionData(Datavalues.DataC.TerrFactor.Desc, Smoketype, Maptables)
        }
        // check for OBA
        if (!OBAAlreadyFound) { // OBA +1 only applies once no matter how many hexes crossed so if already added, don't add again
            if (NextComTer.getOBA() == Constantvalues.Feature.FFE1 || NextComTer.getOBA() == Constantvalues.Feature.FFE2 || NextComTer.getOBA() == Constantvalues.Feature.FFEC) {
                VisLOSH += 1;
                VisLOSHName += " OBA LOSH";
                OBAAlreadyFound = true;
            }
        }
        return VisLOSH;
    }*/

    /*private boolean DoesScenLOSHApplytothisLOS(int FeatdrmAdj, String FeatureName, double TotalSeeLevel, double totalseenlevel) {
        // called by IFT.CombatDRM
        // determines if a Scenario Terrain feature effects LOS or if
        // height difference prevents it
        //  TempValidSol As LOSvalues.LOSSolution
        double ScenFeatureTotalHeight = 0;
        double FeatureHeight = 0;
        int TempAdj = 0;
        String TempName = "";
        FeatureName = "";

//        if (getScenFeatcol() == null) {
//            // no scenario features currently exist in this scenario
//            FeatdrmAdj = 0;
//            return false;
//        }
//        for (ScenarioTerrain ScenFeat : Linqdata.getScenFeatcol()) {
//            // need to check each ScenFeat as more than one can exist per hex (ie smoke and wire)
//            FeatureHeight = 0;
//            // check for hex match
//            if (ScenFeat.getHexname() == this.getHexName()) {
//                // get height of feature found
//                // NEED A ROUTINE TO DO THIS
//                ScenFeatureTotalHeight = this.getHexBaseLevel() + FeatureHeight;
//                if (TotalSeeLevel >= ScenFeatureTotalHeight && totalseenlevel >= ScenFeatureTotalHeight) {
//                    // LOS goes over LOSH feature; no drm applies
//                } else if (((TotalSeeLevel < ScenFeatureTotalHeight && totalseenlevel <= ScenFeatureTotalHeight) || (TotalSeeLevel <= ScenFeatureTotalHeight && totalseenlevel < ScenFeatureTotalHeight)) ||
//                        (((TotalSeeLevel < ScenFeatureTotalHeight && totalseenlevel > ScenFeatureTotalHeight) || (TotalSeeLevel > ScenFeatureTotalHeight && totalseenlevel < ScenFeatureTotalHeight)) && (FeatureHeight > 0.5))) {
//                    // Tests for two conditions: (a)one is below and other is equal to or below and
//                    // (b) one is above and one is below and LOSH is not a half-level (ie grain)
//                    // in both cases LOS goes through LOSH feature; drm applies
//                    TempAdj += ScenFeat.GetLOSH(false, false, TempName, false);
//                    FeatureName += " " + TempName;
//                } else {
//                    // Once checked this can be simplified to just the elseif case once I know that it works
//                }
//            }
//        }
        FeatdrmAdj = TempAdj;
        if (FeatdrmAdj > 0) {
            return true;
        } else {
            return false;
        }
    }*/
    // moved to TerrainChecks
    /*private LinkedList<SmokeHolder> SmokePresentinHex(Hex hextocheck) {


        LinkedList<SmokeHolder> Smokelist = new LinkedList<SmokeHolder>();
        LinkedList<GameLocation> AllLOCs  = GetLocs.RetrieveLocationsinHex(hexnum, "Hexnum");
        for (GameLocation LookforSmoke: AllLOCs) {
            // test for smoke, if found then set values and return
            if (LookforSmoke.getSmoke() != Constantvalues.VisHind.None) {
                SmokeHolder NewSmoke = new SmokeHolder(LookforSmoke.getSmoke(), LookforSmoke.getSmokeBaseLevel());
                Smokelist.add(NewSmoke);
            }
        }
        return Smokelist;
    }*/

    private boolean RangeIsEqual(Hex Currenthex, Hex lasthex, Hex Starthex) {
        /*String Currenthexname=""; String lasthexname = ""; //String Starthexname = "";
        for (GameLocation testlocation: LocationCol){
            if (testlocation.getHexnum()== Currenthex ){Currenthexname=testlocation.getHexname();}
            break;
        }
        for (GameLocation testlocation: LocationCol){
            if (testlocation.getHexnum()== lasthex ){lasthexname=testlocation.getHexname();}
            break;
        }*/
        /*for (GameLocation testlocation: LocationCol){
            if (testlocation.getHexnum()== Starthex ){Starthexname=testlocation.getHexname();}
            break;
        }*/
        int FirstRange = prscen.getGameMap().range(Starthex, Currenthex, prscen.getGameMap().getMapConfiguration());
        int SecondRange = prscen.getGameMap().range(lasthex, Currenthex, prscen.getGameMap().getMapConfiguration());
        if (FirstRange == SecondRange) {
            return true;
        } else {
            return false;
        }
    }
    private Constantvalues.Nationality SetEnemy1(Constantvalues.Nationality FirstFriendly) {
        // called by
        // set the nationality values of the "enemy" side
        if (FirstFriendly == prScendet.getATT1() || FirstFriendly == prScendet.getATT2()) {
            return prScendet.getDFN1();
        } else if (FirstFriendly == prScendet.getDFN1() || FirstFriendly == prScendet.getDFN2()) {
            return prScendet.getATT1();
        }
        return Constantvalues.Nationality.None;
    }
    private Constantvalues.Nationality SetEnemy2(Constantvalues.Nationality FirstFriendly) {
        // called by
        // set the nationality values of the "enemy" side
        if (FirstFriendly == prScendet.getATT1() || FirstFriendly == prScendet.getATT2()) {
            return prScendet.getDFN2();
        } else if (FirstFriendly == prScendet.getDFN1() || FirstFriendly == prScendet.getDFN2()) {
            return prScendet.getATT2();
        }
        return Constantvalues.Nationality.None;
    }
    private int OBACheck () {
        if (prhindInLOS != null) {
            for (HindInLOS testhind : prhindInLOS) {
                //check for OBA hind
                if (testhind.getHindtype() == Constantvalues.Feature.FFE1 ||
                        testhind.getHindtype() == Constantvalues.Feature.FFE2 ||
                        testhind.getHindtype() == Constantvalues.Feature.FFEC) {
                    return 1;
                }
            }
        }
        // no hindrances
        return 0;
    }
    public int FiringDRM(LinkedList<IFTMods> DRMList, PersUniti TargetUnit){
        // first OBA
        int OBALOSH = OBACheck();
        IFTMods NEWDrm = null;
        // now add visibility losh to drm list
        if (OBALOSH > 0) {
            if (NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.OBA)) {
                NEWDrm = new IFTMods(OBALOSH, Constantvalues.IFTdrm.OBA, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), "OBA");
                DRMList.add(NEWDrm);
            } else {
                OBALOSH = 0;
                GameModule.getGameModule().getChatter().send(this.getVisLOSHName() + " in " + this.getHexName() + " already added to DRM");
            }
        }
        // then Smoke
        int Smokedrm=0;
        if (prhindInLOS != null) {
            for (HindInLOS testhind : prhindInLOS) {
                //check for OBA hind
                if (testhind.getHindtype() == Constantvalues.Feature.Smoke) {
                    Smokedrm= testhind.getHindvalue() +1; // +1 due to firing from hex
                }
            }
        }

        if (Smokedrm > 0) {
            if (this.NotAlreadyAddedToDRMList(DRMList, TargetUnit, Constantvalues.IFTdrm.VisLoSH)) {
                NEWDrm = new IFTMods(Smokedrm, Constantvalues.IFTdrm.VisLoSH, TargetUnit.getbaseunit().getUnit_ID(), TargetUnit.getbaseunit().getTypeType_ID(), TargetUnit.getbaseunit().gethexlocation(), this.getLocation(), "VisLOSH");
                DRMList.add(NEWDrm);
            } else {
                Smokedrm = 0;
                GameModule.getGameModule().getChatter().send(this.getVisLOSHName() + " in " + this.getHexName() + " already added to DRM");
            }
        }
        return OBALOSH + Smokedrm;
    }
}
