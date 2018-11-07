package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class RandomSelListc {
    public RandomSelListc(){}
    public LinkedList<LocationContentc> CreateRandSelList(LinkedList<LocationContentc> LocationtoCheck) {
        // called by MovementValidation.new
        // creates list of concealed present units subject to randonm selection for reveal (does not include dummies)
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        LinkedList<LocationContentc> RanSelElig = new LinkedList<LocationContentc>();
        for (LocationContentc ItemInHex : LocationtoCheck) {
            Constantvalues.Typetype TypeIs = ItemInHex.getTypeIDvalue();
            switch (TypeIs) {
                case Personnel:
                    // infantry
                    for (PersUniti hexunit : Scencolls.Unitcol) {
                        if (hexunit.getbaseunit().getUnit_ID() == ItemInHex.getObjID()) {
                            //StateChecksc StateTest = new StateChecksc();
                            if (hexunit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed) { //&&
                                    //!(StateTest.IsUnitDummy(hexunit.getbaseunit().getLOBLink()))) {
                                RanSelElig.add(ItemInHex);
                            }
                        }
                    }
                default:
                    // need to add guns and vehicles
            }
        }
        return RanSelElig;
    }
}
