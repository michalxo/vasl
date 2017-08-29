package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class IFTMods {
    // holds info about a DRM modifier used in a particular combat
    private int pDRM;
    private Constantvalues.IFTdrm pDRMType;
    // private pDRMName As String
    private int pTargetItem;
    private Constantvalues.Typetype pTargetType;
    private double pTargetLOCindex;
    private double pDRMLOCindex;

    public IFTMods(int PassDRM, Constantvalues.IFTdrm PassDRMType, int PassTargetItem, Constantvalues.Typetype PassTargettype, double PassTargetLocIndex, double PassDRMLOCIndex) {
        pDRM = PassDRM;
        pDRMType = PassDRMType;
        pTargetItem = PassTargetItem;
        pTargetType = PassTargettype;
        pTargetLOCindex = PassTargetLocIndex;
        pDRMLOCindex = PassDRMLOCIndex;
    }
    public int getDRM() {return pDRM;}
    public void setDRM(int value) {pDRM = value;}
    public Constantvalues.IFTdrm getDRMType() {return pDRMType;}
    public void setDRMType(Constantvalues.IFTdrm value){pDRMType = value;}
    public int getTargetItem() {return pTargetItem;}
    public void setTargetItem(int value) {pTargetItem = value;}
    public Constantvalues.Typetype getTargetType() {return pTargetType;}
    public void setTargetType(Constantvalues.Typetype value) {pTargetType = value;}
    public double getTargetLocIndex () {return pTargetLOCindex;}
    public void setTargetLocIndex(double value){ pTargetLOCindex = value;}
    public double getDRMLocIndex() {return pDRMLOCindex;}
    public void setDRMLocIndex(double value){pDRMLOCindex = value;}

}
