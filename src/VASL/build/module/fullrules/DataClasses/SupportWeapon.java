package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class SupportWeapon {
    private int pID;
    private String pWeaponName;
    private Constantvalues.SWtype pWeaponType;
    //private String _National As String
    private int pFIREPOWER;
    private int pRANGE;
    private int pPORTAGECOST;
    private int pMALFUNCTION;
    private int pREPAIR;
    private int pROF;
    private int pBREAKDOWN;
    private boolean pASSAULTFIRE;
    private boolean pSPRAYINGFIRE;
    private int pDismantledPP;
    private Constantvalues.Nationality pNationality;

    /*public SupportWeapon(int PassID, String PassWeaponName, Constantvalues.SWtype PassWeaponType, int PassFIREPOWER, int PassRANGE, int PassPORTAGECOST, int  PassMALFUNCTION, int PassREPAIR,
        int PassROF, int PassBREAKDOWN, boolean PassASSAULTFIRE, boolean PassSPRAYINGFIRE, int PassDismantledPP, Constantvalues.Nationality PassNationality) {

        pID = PassID;
        pWeaponName = PassWeaponName;
        pWeaponType = PassWeaponType;
        pFIREPOWER = PassFIREPOWER;
        pRANGE = PassRANGE;
        pPORTAGECOST = PassPORTAGECOST;
        pMALFUNCTION = PassMALFUNCTION;
        pREPAIR = PassREPAIR;
        pROF = PassROF;
        pBREAKDOWN = PassBREAKDOWN;
        pASSAULTFIRE = PassASSAULTFIRE;
        pSPRAYINGFIRE = PassSPRAYINGFIRE;
        pDismantledPP = PassDismantledPP;
        pNationality = PassNationality;
    }*/

    public SupportWeapon(){}

    public int getOBLink() {return pID;}
    public void setOBLink(int value) {pID = value;}
    public String getWeaponName() {return pWeaponName;}
    public void setWeaponName(String value) {pWeaponName = value;}
    public Constantvalues.SWtype getWeaponType() {return pWeaponType;}
    public void setWeaponType(Constantvalues.SWtype value) {pWeaponType = value;}
    public int getFIREPOWER() {return pFIREPOWER;}
    public void setFIREPOWER(int value) {pFIREPOWER = value;}
    public int getRANGE() {return pRANGE;}
    public void setRANGE(int value) {pRANGE = value;}
    public int getPORTAGECOST() {return pPORTAGECOST;}
    public void setPORTAGECOST(int value) {pPORTAGECOST = value;}
    public int getMALFUNCTION() {return pMALFUNCTION;}
    public void setMALFUNCTION(int value) {pMALFUNCTION = value;}
    public int getREPAIR() {return pREPAIR;}
    public void setREPAIR(int value) {pREPAIR = value;}
    public int getROF() {return pROF;}
    public void setROF(int value) {pROF = value;}
    public int getBREAKDOWN() {return pBREAKDOWN;}
    public void setBREAKDOWN(int value) {pBREAKDOWN = value;}
    public boolean getASSAULTFIRE() {return pASSAULTFIRE;}
    public void setASSAULTFIRE(boolean value) {pASSAULTFIRE = value;}
    public boolean getSPRAYINGFIRE() {return pSPRAYINGFIRE;}
    public void setSPRAYINGFIRE(boolean value ) {pSPRAYINGFIRE = value;}
    public int getDismantledPP() {return pDismantledPP;}
    public void setDismantledPP(int value){pDismantledPP = value;}
    public Constantvalues.Nationality getNationality() {return pNationality;}
    public void setNationality(Constantvalues.Nationality value) {pNationality = value;}
    public Constantvalues.Typetype getTypeType() {return Constantvalues.Typetype.SW;}
}
