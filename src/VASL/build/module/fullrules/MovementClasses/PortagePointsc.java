package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;

public class PortagePointsc {
    private ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();
    public PortagePointsc() {

    }
    public int CalcTotalPPUsed() {
        int PPCost =0; int SMCCount = 0;
        for (PersUniti MovingUnit: scencolls.SelMoveUnits) {
            if (!MovingUnit.getMovingunit().IsDummy()) {
                Constantvalues.Utype GetUnitsize = MovingUnit.getbaseunit().getUnittype();
                if (GetUnitsize == Constantvalues.Utype.SMC) {
                    SMCCount += 1;
                    if (SMCCount >= 4) {
                        SMCCount = 0; // reset
                        PPCost += 5;
                    }
                } else {
                    //Dim UtilM = New UtilClassLibrary.ASLXNA.MoveUtil
                    PPCost += 0; //UtilM.GEtPPforUnit(GetUnitsize)
                }
                // add PP for SW
                int SWPPCost = 0;
                for (int x = 1; x < 3; x++) {
                    int OBSWPoss = MovingUnit.getbaseunit().GetSW(x);
                    if (OBSWPoss > 0) {
                        for (SuppWeapi OBSWToCheck : scencolls.SWCol) {
                            if (OBSWToCheck.getbaseSW().getSW_ID() == OBSWPoss) {
                                SWPPCost += 0 ; // CInt(Game.Linqdata.GetLOBSWData(ConstantClassLibrary.ASLXNA.LOBItem.PP, OBSWToCheck))
                            }
                        }
                    }
                }
                PPCost += SWPPCost;
            } else {
                PPCost += 10;
            }
        }
        return PPCost;
    }

}
