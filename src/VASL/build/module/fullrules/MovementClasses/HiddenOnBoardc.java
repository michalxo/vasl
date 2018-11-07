package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectChangeClasses.ConcealUnitC;
import VASL.build.module.fullrules.ObjectChangeClasses.VisibilityChangei;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class HiddenOnBoardc {
    public HiddenOnBoardc() {
    }

    public void PutHiddenOnboard(LinkedList<LocationContentc> LocationtoCheck) {
        //called by
        // puts hidden units on board under a new concealment counter
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        for (LocationContentc ItemInHex: LocationtoCheck) {
            Constantvalues.Typetype TypeIs = ItemInHex.getTypeIDvalue();
            switch (TypeIs) {
                case Personnel:
                    // infantry
                    for (PersUniti hexunit : Scencolls.Unitcol) {
                        if (hexunit.getbaseunit().getUnit_ID() == ItemInHex.getObjID()) {
                            if (hexunit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
                                VisibilityChangei UnittoChange = new ConcealUnitC(hexunit.getbaseunit().getUnit_ID());
                                UnittoChange.TakeAction();
                                break;
                            }
                        }
                    }
                default:
                // need to add guns, vehicles, terrain
            }
        }
    }
}

