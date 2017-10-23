package VASL.build.module.fullrules.DataClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;

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
    private Hex pHex;
    private int pmysolid;

    public EnemyHexLOSHFPdrm(Constantvalues.LosStatus PassLOSStatus, double PassFP, int Passdrm, String PassHexname) {
        ScenarioC scen = ScenarioC.getInstance();

        pLOSStatus = PassLOSStatus;
        pFP = PassFP;
        pdrm = Passdrm;
        pHexname = PassHexname;
        pHex = scen.getGameMap().getHex(PassHexname);
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
    public Hex getHexnum() {return pHex;}


}
