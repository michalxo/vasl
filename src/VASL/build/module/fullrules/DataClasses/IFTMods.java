package VASL.build.module.fullrules.DataClasses;

public class IFTMods {
    // holds info about a DRM modifier used in a particular combat
    private int pDRM;
    private int pDRMType;
    // private pDRMName As String
    private int pTargetItem;
    private int pTargetType;
    private double pTargetLOCindex;
    private double pDRMLOCindex;

    public IFTMods(int PassDRM, int PassDRMType, int PassTargetItem, int PassTargettype, double PassTargetLocIndex, double PassDRMLOCIndex) {
        pDRM = PassDRM;
        pDRMType = PassDRMType;
        pTargetItem = PassTargetItem;
        pTargetType = PassTargettype;
        pTargetLOCindex = PassTargetLocIndex;
        pDRMLOCindex = PassDRMLOCIndex;
    }
    public int getDRM() {return pDRM;}
    public void setDRM(int value) {pDRM = value;}
    public int getDRMType() {return pDRMType;}
    public void setDRMType(int value){pDRMType = value;}
    public int getTargetItem() {return pTargetItem;}
    public void setTargetItem(int value) {pTargetItem = value;}
    public int getTargetType() {return pTargetType;}
    public void setTargetType(int value) {pTargetType = value;}
    public double getTargetLocIndex () {return pTargetLOCindex;}
    public void setTargetLocIndex(double value){ pTargetLOCindex = value;}
    public double getDRMLocIndex() {return pDRMLOCindex;}
    public void setDRMLocIndex(double value){pDRMLOCindex = value;}

}
