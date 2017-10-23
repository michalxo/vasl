package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Unpossessed;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;

import java.util.LinkedList;

public class CreateUnpossessedSW {

    private ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();


    public boolean CreateNewUnpossessed(SuppWeapi OBSW, Hex DropHex) {
        Unpossessed DroppedSW = new Unpossessed();
        DroppedSW.setEquipmentID(OBSW.getbaseSW().getUnit_ID());
        DroppedSW.setEquipmenttype(OBSW.getbaseSW().getType_ID());
        DroppedSW.sethex(DropHex);
        if (Scencolls.Unpossesseds == null) {Scencolls.Unpossesseds = new LinkedList<Unpossessed>();}
        Scencolls.Unpossesseds.add(DroppedSW);
        return true;
    }

}
