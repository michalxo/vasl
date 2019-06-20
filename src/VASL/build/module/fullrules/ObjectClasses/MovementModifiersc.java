package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.SupportWeapon;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;


public class MovementModifiersc implements MovementModifiersi {
    
    private boolean dbltime;
    private boolean ldrbonus;
    private boolean roadbonus;
    private int ppadjustment;
    private boolean isencircled;
        
    public MovementModifiersc() {
        dbltime = false; ldrbonus = false; roadbonus = false;
    }
    public void updateModifiers(PersUniti MovingUnittoCheck, Constantvalues.UMove controlclick, boolean isleaderpresent) {

        isencircled = EncircledCheck(MovingUnittoCheck);
        ppadjustment = CalculateIPCImpact(MovingUnittoCheck);
        if (isleaderpresent && !(MovingUnittoCheck.getbaseunit().getUnittype() == Constantvalues.Utype.LdrHero ||
                MovingUnittoCheck.getbaseunit().getUnittype() == Constantvalues.Utype.Leader)) {
            ldrbonus = true;
        }
        boolean CXTest = false;
        if (!(controlclick == null)) {
            switch (controlclick) { // need to use pattern based on enum values
                case DblTime:
                    CXTest = true;
                    dbltime = true;
                    break;
                case DbT_RB:  // Dbl time and road bonus
                    CXTest = true;
                    dbltime = true;
                    roadbonus = true;
                    break;
                case RoadBonus:
                    roadbonus = true;
                    break;
                default:
            }
        }
        MovingUnittoCheck.getbaseunit().setCX(CXTest);
    }

    public boolean getDoubleTiming() {return dbltime;}
    public boolean getsLdrBonus() {return ldrbonus;}
    public boolean getsRoadBonus() {return roadbonus;}
    public boolean isEncircled() {return isencircled;}
    public int getPPAdjust() {return ppadjustment;}

    private int CalculateIPCImpact(PersUniti MovingUnittoCheck) {
        int SWCarrying  = getPPCarrying(MovingUnittoCheck);
        if (SWCarrying > MovingUnittoCheck.getMovingunit().getIPC()) {
            return MovingUnittoCheck.getMovingunit().getIPC() - SWCarrying;
        } else {
            return 0;
        }
    }
    public int getPPCarrying(PersUniti MovingUnittoCheck) {
        int OBSWPoss = 0; int Carrying = 0;
        Constantvalues.SWStatus SWStatus = Constantvalues.SWStatus.None;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        if (MovingUnittoCheck.getMovingunit().IsDummy()) {
            return 0;
        } else {
            OBSWPoss = MovingUnittoCheck.getbaseunit().getFirstSWLink();
            if (OBSWPoss != 0) { // 0 value means no SW
                //retrieve type of SW
                int LOBSWLink = 0;
                Constantvalues.SWStatus checkswstatus = Constantvalues.SWStatus.None;
                for (SuppWeapi SWtoUse : Scencolls.SWCol) {
                    if (SWtoUse.getbaseSW().getSW_ID() == OBSWPoss) {
                        LOBSWLink = SWtoUse.getbaseSW().getLOBLink();
                        // determine if dismantled
                        checkswstatus = SWtoUse.getbaseSW().getSWStatus();
                    }
                }
                // get LOB SW info
                CommonFunctionsC commonfunc = new CommonFunctionsC(MovingUnittoCheck.getbaseunit().getScenario());
                SupportWeapon lobsw = commonfunc.GetSupportWeapon(String.valueOf(LOBSWLink));
                // get PP cost
                if(checkswstatus == Constantvalues.SWStatus.Dismantled || checkswstatus == Constantvalues.SWStatus.Dis_Broken) {
                    Carrying = lobsw.getDismantledPP();
                } else {
                    Carrying = lobsw.getPORTAGECOST();
                }
            }
            return Carrying;
        }
    }
    public boolean EncircledCheck(PersUniti MovingUnittoCheck) {
        return (MovingUnittoCheck.getbaseunit().IsUnitEncircled() ? true : false);
    }
    
}
