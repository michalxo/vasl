package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;
import VASL.build.module.fullrules.MapDataClasses.MapDataC;
import VASL.build.module.fullrules.TerrainClasses.TerrainChecks;

import java.util.LinkedList;

public class ScenarioTerrain {
    private String pFeature;
    private String pHexname;
    private Constantvalues.Feature pFeatureType;
    private int pScenario;
    private int pScenter_id;
    private int pHexnumber;
    private int phexlocation;
    private Constantvalues.AltPos phexposition;
    private Constantvalues.VisibilityStatus pVisibilityStatus;
    private int pLocIndex;
    private MapDataC Maptables = MapDataC.GetInstance("", 0); //{Trim("Scen" & Me.Scenario.ToString), CInt(Me.Scenario))
    private LinkedList<GameLocation> LocationCol = new LinkedList<GameLocation>();

    public ScenarioTerrain() {
        
    }

    public String getFeature() {return pFeature;}
    public void setFeature(String value) {pFeature = value;}
	public String getHexname() {return pHexname;}
	public void setHexname(String value) {pHexname = value;}
	public Constantvalues.Feature getFeatureType() {return pFeatureType;}
	public void setFeaturetype(Constantvalues.Feature value) {pFeatureType = value;}
	public int getScenario() {return pScenario;}
	public void setScenario(int value){pScenario = value;}
	public int getScenter_id() {return pScenter_id;}
	public void setScenter_id(int value){pScenter_id = value;}
	public int getHexnumber() {return pHexnumber;}
	public void setHexnumber(int value){pHexnumber = value;}
	public int gethexlocation() {return phexlocation;}
	public void sethexlocation(int value){phexlocation = value;}
	public Constantvalues.AltPos gethexposition() {return phexposition;}
	public void sethexposition(Constantvalues.AltPos value) {phexposition = value;}
	public Constantvalues.VisibilityStatus getVisibilityStatus() {return pVisibilityStatus;}
	public void setVisibilityStatus(Constantvalues.VisibilityStatus value) {pVisibilityStatus = value;}
	public int getLocIndex() {return pLocIndex;}
	public void setLocIndex(int value) {pLocIndex = value;}

    public int GetTEM(String TEmpFeatureName) {
        // called by CombatTerrain class .GetScenFeatTEM
        // returns TEM for specified Scenario Terrain
        int TEM = 0;
        // get TEM and name of terrain found
        TerrainChecks TerrChk = new TerrainChecks(LocationCol);
        // temporary while debugging REDO - think about with AltPos
        /*TEM = (TerrChk.GetLocationData(Constantvalues.TerrFactor.TEM, pFeatureType);
        'GetTEM = CInt(GetTerrainData(ConstantClassLibrary.ASLXNA.TerrFactor.TEM, Featuretype))
        'If Featuretype >= ConstantClassLibrary.ASLXNA.Feature.RFE And Featuretype <= ConstantClassLibrary.ASLXNA.Feature.BFE Then OBALOSH = True
        'set variable to true - only add +1 no matter how many FFE hexes crossed
        TEmpFeatureName = TerrChk.GetLocationData(Constantvalues.TerrFactor.Desc, pFeatureType);*/
                return TEM;
    }
    public int GetLOSH(boolean IsFirerHex, boolean IsTargetHex, String TEmpFeatureName, boolean OBALOSH) {
        // called by CombatTerrain class .GetScenFeatTEM
        // returns LOSH for specified Scenario Terrain
        int LOSH = 0;
        // 'get LOSH and name of terrain found
        TerrainChecks TerrChk = new TerrainChecks(LocationCol);
        switch (pFeatureType) {
            // temporary while debuggin REDO
            /*Case ConstantClassLibrary.ASLXNA.VisHind.BlazeWood To ConstantClassLibrary.ASLXNA.VisHind.GreyWPDisp
            'treat smoke specially
            TEmpFeatureName = If(Featuretype < ConstantClassLibrary.ASLXNA.VisHind.GunWP, "Smoke", "WP")
            GetLOSH = CInt(TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.LOSHind, Featuretype, Maptables))
            'smoke effect is greater in firer hex
            If IsFirerHex Then GetLOSH +=1
            Case ConstantClassLibrary.ASLXNA.Feature.FFE1 To ConstantClassLibrary.ASLXNA.Feature.FFEC
            'set variable to true - only add +1 no matter
            'how many FFE hexes crossed
            OBALOSH = True
            Case ConstantClassLibrary.ASLXNA.Feature.BOWreck, ConstantClassLibrary.ASLXNA.Feature.Wreck
            'TEM not LOSH applies in target hex
            If Not IsTargetHex Then
            GetLOSH = CInt(TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.LOSHind, Featuretype, Maptables))
            TEmpFeatureName = TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.Desc, Featuretype, Maptables)
            End If
            Case ConstantClassLibrary.ASLXNA.VisHind.VehDust
                    GetLOSH = CInt(TerrChk.GetLocationData(ConstantClassLibrary.ASLXNA.TerrFactor.LOSHind, Featuretype, Maptables))
            TEmpFeatureName = "Veh Dust"*/
        }
        return LOSH;
    }

    public boolean CanBlockLOS(double hexobstlevel) {
        // called by Mapactions.DoesScenarioTerrainBlockLOS
        // checks for scenario terrain types the block LOS through
        // the hex and returns boolean value (T/F)
        boolean BlockLOS = false;
        /*if (pFeatureType = ConstantClassLibrary.ASLXNA.Location.WoodRubble Or
                _FeatureType = ConstantClassLibrary.ASLXNA.Location.StoneRubble Or
                _FeatureType = ConstantClassLibrary.ASLXNA.Location.Hillock Or
                _FeatureType = ConstantClassLibrary.ASLXNA.Location.SandDuneH Or
                _FeatureType = ConstantClassLibrary.ASLXNA.Location.CactusPatch Then
        'not certain that Hillock, SandDuneH and CactusPatch will be handled as scenario terrain
        'they may be normal map terrain but just in case . . .
        CanBlockLOS = True
        hexobstlevel = 0.5
        End If*/
        return BlockLOS;
    }
}
