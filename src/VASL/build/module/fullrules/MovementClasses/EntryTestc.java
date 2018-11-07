package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class EntryTestc {
    private boolean MovingisMMC = false;
    private boolean MovingIsDummy = true;
    public EntryTestc() {

    }
    public boolean getMovingisMMC() {return MovingisMMC;}
    public boolean getMovingIsDummy() {return MovingIsDummy;}
    public boolean Entrytest() {
        // called by MovementValidation.New
        boolean AllBerserk = true;
        ScenarioCollectionsc scencolls  = ScenarioCollectionsc.getInstance();
        for (PersUniti MovingUnit: scencolls.SelMoveUnits) {
            if (!MovingUnit.getMovingunit().IsDummy()) {
                // reset dummy to false when unit found
                MovingIsDummy = false;
                // reset MovingIsMMC to true when MMC found
                if (!MovingUnit.getbaseunit().IsUnitASMC()) {
                    MovingisMMC = true;
                }
                // reset AllBerserk to false if non-berserker found
                if (MovingUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Berserk) {
                    AllBerserk = false;
                } else { //if dummy
                    // dummies will be revealed later but need to allow as this point
                }
            }
        }
        return (MovingIsDummy || AllBerserk);
    }
}
