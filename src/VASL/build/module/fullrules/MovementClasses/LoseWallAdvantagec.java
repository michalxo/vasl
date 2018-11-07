package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class LoseWallAdvantagec {
    public LoseWallAdvantagec(PersUniti passunit) {
        // broken/unarmed in start hex lose WA and transfer to adjacent enemy - this class is only used for this type of WA loss
        ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();
        for (PersUniti testunit : scencolls.Unitcol) {
            if (testunit.equals(passunit)) {
                if (testunit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.GoodOrder && testunit.getbaseunit().getOrderStatus() !=
                Constantvalues.OrderStatus.Berserk) {
                    // all other Orderstatus values lose WA
                    WARemovec UnittoChange = new WARemovec(testunit);
                    UnittoChange.TakeAction();
                    break;
                }
            }
        }
    }
}
