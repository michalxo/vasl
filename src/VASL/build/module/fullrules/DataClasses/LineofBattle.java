package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class LineofBattle {
    private String pLOBName;
    private Constantvalues.Nationality pNationality;
    private int pOBLink;
    private int pFirePower;
    private int pRange;
    private int pMoraleLevel;
    private boolean pAssaultFire;
    private boolean pSprayFire;
    private boolean pELR5;
    private int pClass;
    private int pSmoke;
    private int pBrokenML;
    private int pBPV;
    private String pRedTo;
    private String pSubTo;
    private String pHardTo;
    private boolean pSelfRally;
    //private Constantvalues.Typetype pTypeType;
    private int pReducesTo;
    private int pSubstitutesTo;
    private int pHardensTo;
    private Constantvalues.Utype pUnitType;

   public LineofBattle(String PassLOBName, Constantvalues.Nationality PassNationality, int PassOBLink, int PassFirePower, int  PassRange, int PassMoraleLevel, boolean PassAssaultFire, boolean PassSprayFire,
        boolean PassELR5, int PassClass, int PassSmoke, int PassBrokenML, int PassBPV, String PassRedTo, String PassSubTo, String PassHardTo, boolean PassSelfRally, Constantvalues.Utype PassUnitType,
        int PassReducesTo, int PassSubstitutesTo, int PassHardensTo){

       pLOBName = PassLOBName;
       pNationality = PassNationality;
       pOBLink = PassOBLink;
       pFirePower = PassFirePower;
       pRange = PassRange;
       pMoraleLevel = PassMoraleLevel;
       pAssaultFire = PassAssaultFire;
       pSprayFire= PassSprayFire;
       pELR5 = PassELR5;
       pClass = PassClass;
       pSmoke = PassSmoke;
       pBrokenML = PassBrokenML;
       pBPV = PassBPV;
       pRedTo = PassRedTo;
       pSubTo = PassSubTo;
       pHardTo = PassHardTo;
       pSelfRally = PassSelfRally;
       pReducesTo = PassReducesTo;
       pSubstitutesTo = PassSubstitutesTo;
       pHardensTo = PassHardensTo;
       pUnitType = PassUnitType;

   }
   public LineofBattle(){}

   public String getLOBName(){return pLOBName;}
   public void setLOBName(String value){pLOBName = value;}
   public Constantvalues.Nationality getNationality() {return pNationality;}
   public void setNationality(Constantvalues.Nationality value) {pNationality = value;}
   public int getOBLink() {return pOBLink;}
   public void setOBLink(int value) {pOBLink = value;}
   public int getFirePower() {return pFirePower;}
   public void setFirePower( int value) {pFirePower = value;}
   public int getRange() {return pRange;}
   public void setRange(int value) {pRange = value;}
   public int getMoraleLevel(){return pMoraleLevel;}
   public void setMoraleLevel(int value) {pMoraleLevel = value;}
   public boolean getAssaultFire() {return pAssaultFire;}
   public void setAssaultFire(boolean value) {pAssaultFire = value;}
   public boolean getSprayFire() {return pSprayFire;}
   public void setSprayFire(boolean value) {pSprayFire = value;}
   public boolean getELR5(){return pELR5;}
   public void setELR5(boolean  value) {pELR5 = value;}
   public int getUClass() {return pClass;}
   public void setUClass (int value) {pClass = value;}
   public int getSmoke() {return pSmoke;}
   public void setSmoke(int value) {pSmoke = value;}
   public int getBrokenML() {return pBrokenML;}
   public void setBrokenML(int value) {pBrokenML = value;}
   public int getBPV() {return pBPV;}
   public void setBPV(int value) {pBPV = value;}
   public String getRedTo() { return pRedTo;}
   public void setRedTo(String value) {pRedTo = value;}
   public String getSubTo() {return pSubTo;}
   public void setSubTo(String value) {pSubTo = value;}
   public String getHardTo() {return pHardTo;}
   public void setHardTo(String value) {pHardTo = value;}
   public boolean getSelfRally() {return pSelfRally;}
   public void setSelfRally(boolean value)  {pSelfRally = value;}
   public Constantvalues.Utype getUnitType() {return pUnitType;}
   public void setUnitType(Constantvalues.Utype value) {pUnitType = value;}
   public Constantvalues.Typetype getTypeType() {return Constantvalues.Typetype.Personnel;}
    //public void setTypeType(Constantvalues.Typetype value) {pTypeType = value;}
   public int getReducesTo() {return pReducesTo;}
   public void setReducesTo(int value) {pReducesTo = value;}
   public int getSubstitutesTo() {return pSubstitutesTo;}
   public void setSubstitutesTo(int value) {pSubstitutesTo = value;}
   public int getHardensTo() {return pHardensTo;}
   public void setHardensTo(int value) {pHardensTo = value;}

}
