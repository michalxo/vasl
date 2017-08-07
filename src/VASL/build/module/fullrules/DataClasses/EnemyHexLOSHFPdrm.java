package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;

/**
 * Created by dougr_000 on 7/18/2017.
 */
public class EnemyHexLOSHFPdrm {
    // type ussed by EnemyValuesConcreteC to store values to pass to Shadehexes in DFFviewConcreteC.UpdateView
    // held in list EnemyHexList and treturned by EnemyValuesConcreteC.SetupLOSHFPdrmValues

    private Constantvalues.LosStatus pLOSStatus;
    private double pFP;
    private int pdrm;
    private String pHexname;
    private int pHexnum;
    private double pLOCIndex;
    private int pmysolid;

    public EnemyHexLOSHFPdrm(Constantvalues.LosStatus PassLOSStatus, double PassFP, int Passdrm, String PassHexname, int PassHexnum, double PassLOCIndex) {
        pLOSStatus = PassLOSStatus;
        pFP = PassFP;
        pdrm = Passdrm;
        pHexname = PassHexname;
        pHexnum = PassHexnum;
        pLOCIndex = PassLOCIndex;
        pmysolid = 0;  // set to 0 on instance creation, changed later
    }
    public int getSolID() {return pmysolid;}
    public void setSolID(int value) {pmysolid=value;}

    public Constantvalues.LosStatus getLOSStatus() {return pLOSStatus;}
    public void setLOSStatus(Constantvalues.LosStatus value) {pLOSStatus=value;}
    public double getFP() {return pFP;}
    public void setFP(double value) {pFP=value;}
    public int getdrm() {return pdrm;}
    public void setdrm(int value) {pdrm=value;}
    public String getHexname() {return pHexname;}
    public int getHexnum() {return pHexnum;}
    public double getLOCIndex() {return pLOCIndex;}

}
