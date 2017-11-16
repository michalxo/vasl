package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.Constantvalues;

public class IFTTableResult {

    private String pname;
    private int pDR;
    private int pFPCol;
    private Constantvalues.IFTResult pIFTResult;

    public String getName(){return pname;}
    public void setName(String value ) {pname = value;}
    public int getDR() {return pDR;}
    public void setDR(int value){pDR = value;}
    public int getFPCol(){return pFPCol;}
    public void setFPCol(int value){pFPCol = value;}
    public Constantvalues.IFTResult getIFTResult(){return pIFTResult;}
    public void setIFTResult(Constantvalues.IFTResult value){pIFTResult = value;}

}
