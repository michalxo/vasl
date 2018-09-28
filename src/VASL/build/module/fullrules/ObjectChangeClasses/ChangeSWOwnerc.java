package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;

public class ChangeSWOwnerc {

    public ChangeSWOwnerc(int SWItem, int NewOwner) {
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        for (SuppWeapi SWtoChange : Scencolls.SWCol) {
            if (SWtoChange.getbaseSW().getSW_ID() == SWItem) {
                SWtoChange.getbaseSW().setOwner(NewOwner);
                SWtoChange.getbaseSW().UpdateSWStatus(SWtoChange);
            }
        }
    }

}
