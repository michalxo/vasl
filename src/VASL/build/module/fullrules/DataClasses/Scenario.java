package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class Scenario {
    private double pGT;
    private int pCURRENTTURN;
    private Constantvalues.WhoCanDo pPTURN;
    private int pYEAR;
    private String pMONTH;
    private Constantvalues.DayNight pDAYNIGHT;
    private String pDATETIME;
    private int pWINDSPEED;
    private String pWINDDIR;
    private int pECDRM;
    private int pATTSAN;
    private int pDFNSAN;
    private int pATTIB;
    private int pDFNIB;
    private int pATTCP;
    private int pDFNCP;
    private int pATTIBL;
    private int pDFNIBL;
    private String pAREINFORCE;
    private String pDREINFORCE;
    private String pFULLNAME;
    private int pCVPATT;
    private int pCVPDEF;
    private int pLVPATT;
    private int pLVPDEF;
    private int pSanattaloc;
    private int pSanattbloc;
    private int pSandfnaloc;
    private int pSandfnbloc;
    private Constantvalues.Nationality pATT1;
    private Constantvalues.Nationality pDFN1;
    private Constantvalues.Phase pPhase;
    private int pScenNum;
    private int pMistDust;
    private Constantvalues.Nationality pATT2;
    private Constantvalues.Nationality pDFN2;
    private int pWeather;
    private Constantvalues.Rules pRules;
    private Constantvalues.Map pMap;
    private int pWindHexGrain;
    private String pASLName;
    private boolean pFinished;
    private boolean pattsanpinned;
    private boolean pdfnsanpinned;
    private boolean pattsanactivated;
    private boolean pdfnsanactivated;
    private boolean pNoQuarter;

    // constructor
    public Scenario(String Passname) {
        // test code
        pGT=5;
        pCURRENTTURN=1;
        pPTURN = Constantvalues.WhoCanDo.Attacker;
        pFULLNAME= Passname;
        pATT1 = Constantvalues.Nationality.Germans;
        pDFN1= Constantvalues.Nationality.Russians;
        pPhase = Constantvalues.Phase.PrepFire;
        pScenNum =1;
    }
    		
	public double getGT() {return pGT;}
    public void setGT(double value) {pGT=value;}
    public int getCURRENTTURN() {return pCURRENTTURN;}
    public void setCURRENTTURN(int value) {pCURRENTTURN=value;}
    public Constantvalues.WhoCanDo getPTURN() {return pPTURN;}
    public void setPTURN(Constantvalues.WhoCanDo value) {pPTURN=value;}
    public int getYEAR() {return pYEAR;}
    public void setYEAR(int value) {pYEAR = value;}
	public String getMONTH() {return pMONTH;}
    public void setMONTH(String value) {pMONTH = value;}
	public Constantvalues.DayNight getDAYNIGHT() {return pDAYNIGHT;}
    public void setDAYNIGHT(Constantvalues.DayNight value) {pDAYNIGHT = value;}
	public String getDATETIME() {return pDATETIME;}
    public void setDATETIME( String value) {pDATETIME = value;}
	public int getWINDSPEED() {return pWINDSPEED;}
    public void setWINDSPEED(int value) {pWINDSPEED = value;}
	public String getWINDDIR() {return pWINDDIR;}
    public void setWINDDIR( String value) {pWINDDIR = value;}
	public int getECDRM() {return pECDRM;}
    public void setECDRM(int value) {pECDRM = value;}
	public int getATTSAN() {return pATTSAN;}
    public void setATTSAN(int value) {pATTSAN = value;}
	public int getDFNSAN() {return pDFNSAN;}
    public void setDFNSAN(int value) {pDFNSAN = value;}
	public int getATTIB() {return pATTIB;}
    public void setATTIB(int value) {pATTIB = value;}
	public int getDFNIB() {return pDFNIB;}
    public void setDFNIB(int value) {pDFNIB = value;}
	public int getATTCP() {return pATTCP;}
    public void setATTCP(int value) {pATTCP = value;}
	public int getDFNCP() {return pDFNCP;}
    public void setDFNCP(int value) {pDFNCP = value;}
	public int getATTIBL() {return pATTIBL;}
    public void setATTIBL(int value) {pATTIBL = value;}
    public int getDFNIBL() {return pDFNIBL;}
    public void setDFNIBL(int value) {pDFNIBL = value;}
	public String getAREINFORCE() {return pAREINFORCE;}
    public void setAREINFORCE( String value) {pAREINFORCE = value;}
	public String getDREINFORCE() {return pDREINFORCE;}
    public void setDREINFORCE(String value) {pDREINFORCE = value;}
	public String getFULLNAME() {return pFULLNAME;}
    public void setFULLNAME(String value) {pFULLNAME = value;}
	public int getCVPATT() {return pCVPATT;}
    public void setCVPATT(int value) {pCVPATT = value;}
	public int getCVPDEF() {return pCVPDEF;}
    public void setCVPDEF(int value) {pCVPDEF = value;}
	public int getLVPATT() {return pLVPATT;}
    public void setLVPATT(int value) {pLVPATT = value;}
	public int getLVPDEF() {return pLVPDEF;}
	public void setLVPDEF(int value) {pLVPDEF=value;}
    public int getSanattaloc() {return pSanattaloc;}
    public void setSanattaloc(int value) {pSanattaloc = value;}
	public int getSanattbloc() {return pSanattbloc;}
    public void setSanattbloc(int value) {pSanattbloc = value;}
	public int getSandfnaloc() {return pSandfnaloc;}
    public void setSandfnaloc(int value) {pSandfnaloc = value;}
	public int getSandfnbloc() {return pSandfnbloc;}
    public void setSandfnbloc(int value) {pSandfnbloc = value;}
	public Constantvalues.Nationality getATT1() {return pATT1;}
    public void setATT1(Constantvalues.Nationality value) {pATT1 = value;}
	public Constantvalues.Nationality getDFN1() {return pDFN1;}
    public void setDFN1(Constantvalues.Nationality value) {pDFN1 = value;}
	public Constantvalues.Phase getPhase() {return pPhase;}
    public void setPhase(Constantvalues.Phase value) {pPhase = value;}
	public int getScenNum() {return pScenNum;}
    public void setScenNum(int value)  {pScenNum = value;}
	public int getMistDust() {return pMistDust;}
    public void setMistDust(int value) {pMistDust = value;}
	public Constantvalues.Nationality getATT2() {return pATT2;}
    public void setATT2(Constantvalues.Nationality value) {pATT2 = value;}
	public Constantvalues.Nationality getDFN2() {return pDFN2;}
    public void setDFN2(Constantvalues.Nationality value) {pDFN2 = value;}
	public int getWeather() {return pWeather;}
    public void setWeather(int value) {pWeather = value;}
	public Constantvalues.Rules getRules() {return pRules;}
    public void setRules(Constantvalues.Rules value) {pRules = value;}
	public Constantvalues.Map getMap() {return pMap;}
    public void setMap(Constantvalues.Map value) {pMap = value;}
	public int WindHexGrain() {return pWindHexGrain;}
    public void setWindHexGrain(int value) {pWindHexGrain = value;}
    public String getASLName() {return pASLName;}
    public void setASLName(String value) {pASLName=value;}
    public boolean getFinished() {return pFinished;}
    public void setFinished(boolean value) {pFinished=value;}
    public  boolean getattsanpinned() {return pattsanpinned;}
    public void setattsanpinned(boolean value) {pattsanpinned=value;}
    public  boolean getdfnsanpinned() {return pdfnsanpinned;}
    public void setdfnsanpinned(boolean value) {pdfnsanpinned=value;}
    public boolean getattsanactivated() {return pattsanactivated;}
    public void setattsanactivated(boolean value){pattsanactivated=value;}
    public boolean getdfnsanactivated() {return pdfnsanactivated;}
    public void setdfnsanactivated(boolean value) {pdfnsanactivated=value;}
    public boolean getNoQuarter() {return pNoQuarter;}
    public void setNoQuarter(boolean value) {pNoQuarter=value;}
}
