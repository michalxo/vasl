package VASL.build.module.fullrules.MapDataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class GameLocation {
    
    private int pHexnum;
    private String pHexname;
    private int pLocIndex;
    private Constantvalues.Location pLocation;
    private Constantvalues.Location pOtherTerraininLocation;
    private int pBaselevel;
    private double pLevelInHex;
    private double pObstacleHeightinhex;
    private Constantvalues.Hexside pHexside1;
    private Constantvalues.Hexside pHexside2;
    private Constantvalues.Hexside pHexside3;
    private Constantvalues.Hexside pHexside4;
    private Constantvalues.Hexside pHexside5;
    private Constantvalues.Hexside pHexside6;
    private int pTEM;
    private int pLOSHdrm;
    private double pMFCot;
    private int pTEMOtherTerrain;
    private double pMFOtherTerrain;
    private boolean pBypassOK;
    private boolean pBogCheckRequired;
    private boolean pIsBuilding;
    private boolean pIsBridge;
    private boolean pHasRoad;
    private boolean pIsPillbox;
    private boolean pIsStone;
    private boolean pIsFortified;
    private boolean pIsFactory;
    private boolean pIsCellar;
    private boolean pIsGutted;
    private boolean pIsRoofless;
    private boolean pIsTunnel;
    private boolean pIsRubble;
    private boolean pHasStair;
    private boolean pIsManhole;
    private boolean pIsInterior;
    private boolean pIsInherent;
    private boolean pIsInherentOtherTerrain;
    private boolean pIsWire;
    private Constantvalues.VisHind pSmoke;
    private int pAPMines;
    private int pATMines;
    private int pSmokeBaseLevel;
    private boolean pShowCounter;
    private boolean pWireVisible;
    private boolean pAPMinesVisible;
    private boolean pATMinesVisible;
    private boolean pCrestStatusOK;
    private boolean pSide1IsRoad;
    private boolean pSide1IsWHR;
    private boolean pSide2IsRoad;
    private boolean pSide2IsWHR;
    private boolean pSide3IsRoad;
    private boolean pSide3IsWHR;
    private boolean pSide4IsRoad;
    private boolean pSide4IsWHR;
    private boolean pSide5IsRoad;
    private boolean pSide5IsWHR;
    private boolean pSide6IsRoad;
    private boolean pSide6IsWHR;
    private boolean pLocIsOG;
    private boolean pOTisOG;
    private int pEntrenchment;
    private Constantvalues.Feature pOBA;
    private boolean pIsHardSurface;
    private boolean pOTisHardSurface;
   
    // Constructor 
    public GameLocation() {
        
    }
    
    public int getHexnum() {return pHexnum;}
    public void setHexnum(int value) {pHexnum = value;}
    public String getHexname() {return pHexname;}
    public void setHexnum(String value) {pHexname = value;}
	public int getLocIndex() {return pLocIndex;}
    public void setLocIndex(int value){pLocIndex = value;}
	public Constantvalues.Location getLocation() {return pLocation;}
	public void setLocation(Constantvalues.Location value) {pLocation = value;}
    public Constantvalues.Location getOtherTerraininLocation() {return pOtherTerraininLocation;}
    public void setOtherTerraininLocation(Constantvalues.Location value) {pOtherTerraininLocation = value;}
    public int getBaselevel(){return pBaselevel;}
    public void setBaselevel(int value) {pBaselevel = value;}
    public double getLevelInHex() {return pLevelInHex;}
    public void setLevelInHex(double value) {pLevelInHex = value;}
    public double getObstacleHeightinhex() {return pObstacleHeightinhex;}
    public void setObstacleHeightinhex(double value) {pObstacleHeightinhex = value;}
    public Constantvalues.Hexside getHexside1() {return pHexside1;}
    public void setHexside1(Constantvalues.Hexside value) {pHexside1 = value;}
    public Constantvalues.Hexside getHexside2() {return pHexside2;}
    public void setHexside2(Constantvalues.Hexside value) {pHexside2 = value;}
    public Constantvalues.Hexside getHexside3() {return pHexside3;}
    public void setHexside3(Constantvalues.Hexside value) {pHexside3 = value;}
    public Constantvalues.Hexside getHexside4() {return pHexside4;}
    public void setHexside4(Constantvalues.Hexside value) {pHexside4 = value;}
    public Constantvalues.Hexside getHexside5() {return pHexside5;}
    public void setHexside5(Constantvalues.Hexside value) {pHexside5 = value;}
    public Constantvalues.Hexside getHexside6() {return pHexside6;}
    public void setHexside6(Constantvalues.Hexside value) {pHexside6 = value;}
    public int getTEM() {return pTEM;}
    public void setTEM(int value){pTEM = value;}
    public int getLOSHdrm(){return pLOSHdrm;}
    public void setLOSHdrm(int value){pLOSHdrm = value;}
    public double getMFCot() {return pMFCot;}
    public void setMFCot(double value) {pMFCot = value;}
    public int getTEMOtherTerrain(){return pTEMOtherTerrain;}
    public void setTEMOtherTerrain(int value ){pTEMOtherTerrain = value;}
    public double getMFOtherTerrain() {return pMFOtherTerrain;}
    public void setMFOtherTerrain(double value){pMFOtherTerrain = value;}
    public boolean getBypassOK(){return pBypassOK;}
    public void setBypassOK(boolean value){pBypassOK = value;}
    public boolean getBogCheckRequired() {return pBogCheckRequired;}
    public void setBogCheckRequired(boolean value) {pBogCheckRequired = value;}
    public boolean getIsBuilding() {return pIsBuilding;}
    public void setIsBuilding(boolean value){pIsBuilding = value;}
    public boolean getIsBridge() {return pIsBridge;}
    public void setIsBridge(boolean value){pIsBridge = value;}
    public boolean getHasRoad() {return pHasRoad;}
    public void setHasRoad(boolean value){pHasRoad = value;}
    public boolean getIsPillbox() {return pIsPillbox;}
    public void setIsPillbox(boolean value){pIsPillbox = value;}
    public boolean getIsStone() {return pIsStone;}
    public void setIsStone(boolean value){pIsStone = value;}
    public boolean getIsFortified() {return pIsFortified;}
    public void setIsFortified(boolean value){pIsFortified = value;}
    public boolean getIsFactory(){return pIsFactory;}
    public void setIsFactory(boolean value) {pIsFactory = value;}
    public boolean getIsCellar() {return pIsCellar;}
    public void setIsCellar (boolean value){pIsCellar = value;}
    public boolean getIsGutted() {return pIsGutted;}
    public void setIsGutted(boolean value){pIsGutted = value;}
    public boolean getIsRoofless() {return pIsRoofless;}
    public void setIsRoofless(boolean value ){pIsRoofless = value;}
    public boolean getIsTunnel() {return pIsTunnel;}
    public void setIsTunnel(boolean value){pIsTunnel = value;}
    public boolean getIsRubble() {return pIsRubble;}
    public void setIsRubble(boolean value) {pIsRubble = value;}
    public boolean getHasStair() {return pHasStair;}
    public void setHasStair(boolean value){pHasStair = value;}
    public boolean getIsManhole() {return pIsManhole;}
    public void setisManhole(boolean value){pIsManhole = value;}
    public boolean getIsInterior() {return pIsInterior;}
    public void setIsInterior(boolean value){pIsInterior = value;}
    public boolean getIsInherent() {return pIsInherent;}
    public void setIsInherent(boolean value){pIsInherent = value;}
    public boolean getIsInherentOtherTerrain() {return pIsInherentOtherTerrain;}
    public void setIsInherentOtherTerrain(boolean value){pIsInherentOtherTerrain = value;}
    public boolean getIsWire(){return pIsWire;}
    public void setIsWire(boolean value){pIsWire = value;}
    public Constantvalues.VisHind getSmoke() {return pSmoke;}
    public void setSmoke(Constantvalues.VisHind value){pSmoke = value;}
    public int getAPMines() {return pAPMines;}
    public void setAPMines(int value){pAPMines = value;}
    public int getATMines() {return pATMines;}
    public void setATMines(int value){pATMines = value;}
    public int getSmokeBaseLevel() {return pSmokeBaseLevel;}
    public void setSmokeBaseLevel(int value){pSmokeBaseLevel = value;}
    public boolean getShowCounter() {return pShowCounter;}
    public void setShowCounter(boolean value){pShowCounter = value;}
    public boolean getWireVisible(){return pWireVisible;}
    public void setWireVisible(boolean value){pWireVisible = value;}
    public boolean getAPMinesVisible() {return pAPMinesVisible;}
    public void setAPMinesVisible(boolean value){pAPMinesVisible = value;}
    public boolean geyATMinesVisible() {return pATMinesVisible;}
    public void setATMinesVisible(boolean value){pATMinesVisible = value;}
    public boolean getCrestStatusOK() {return pCrestStatusOK;}
    public void setCrestStatusOK(boolean value){pCrestStatusOK = value;}
    public boolean getSide1IsRoad() {return pSide1IsRoad;}
    public void setSide1IsRoad(boolean value){pSide1IsRoad = value;}
    public boolean getSide1IsWHR() {return pSide1IsWHR;}
    public void setSide1IsWHR(boolean value){pSide1IsWHR = value;}
    public boolean getSide2IsRoad() {return pSide2IsRoad;}
    public void setSide2IsRoad(boolean value){pSide2IsRoad = value;}
    public boolean getSide2IsWHR() {return pSide2IsWHR;}
    public void setSide2IsWHR(boolean value){pSide2IsWHR = value;}
    public boolean getSide3IsRoad() {return pSide3IsRoad;}
    public void setSide3IsRoad(boolean value){pSide3IsRoad = value;}
    public boolean getSide3IsWHR() {return pSide3IsWHR;}
    public void setSide3IsWHR(boolean value){pSide3IsWHR = value;}
    public boolean getSide4IsRoad() {return pSide4IsRoad;}
    public void setShowCounterSide4IsRoad(boolean value){pSide4IsRoad = value;}
    public boolean getSide4IsWHR() {return pSide4IsWHR;}
    public void setSide4IsWHR(boolean value){pSide4IsWHR = value;}
    public boolean getSide5IsRoad() {return pSide5IsRoad;}
    public void setSide5IsRoad(boolean value){pSide5IsRoad = value;}
    public boolean getSide5IsWHR() {return pSide5IsWHR;}
    public void setSide5IsWHR(boolean value){pSide5IsWHR = value;}
    public boolean getSide6IsRoad() {return pSide6IsRoad;}
    public void setSide6IsRoad(boolean value){pSide6IsRoad = value;}
    public boolean getSide6IsWHR() {return pSide6IsWHR;}
    public void setSide6IsWHR(boolean value){pSide6IsWHR = value;}
    public boolean getLocIsOG() {return pLocIsOG;}
    public void setLocIsOG(boolean value){pLocIsOG = value;}
    public boolean getOTisOG() {return pOTisOG;}
    public void setOTisOG(boolean value){pOTisOG = value;}
    public int getEntrenchment() {return pEntrenchment;}
    public void setEntrenchment(int value){pEntrenchment = value;}
    public Constantvalues.Feature getOBA() {return pOBA;}
    public void setOBA(Constantvalues.Feature value){pOBA = value;}
    public boolean getIsHardSurface() { return pIsHardSurface;}
    public void setIsHardSurface(boolean value){pIsHardSurface = value;}
    public boolean  getOTisHardSurface() {return pOTisHardSurface;}
    public void setOTisHardSurface(boolean value){pOTisHardSurface = value;}

}
