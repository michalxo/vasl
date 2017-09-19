package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Unpossessed;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;

import java.util.LinkedList;

public class CreateUnpossessedSW {

    private ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();
    private DataC Linqdata  = DataC.GetInstance();

    public boolean CreateNewUnpossessed(SuppWeapi OBSW, int hexnumber) {
        Unpossessed DroppedSW = new Unpossessed();
        DroppedSW.setEquipmentID(OBSW.getbaseSW().getUnit_ID());
        DroppedSW.setEquipmenttype(OBSW.getbaseSW().getType_ID());
        DroppedSW.sethexnum(hexnumber);
        if (Scencolls.Unpossesseds == null) {Scencolls.Unpossesseds = new LinkedList<Unpossessed>();}
        Scencolls.Unpossesseds.add(DroppedSW);
//        Linqdata.db.Unpossesseds.InsertOnSubmit(DroppedSW)
//        Linqdata.db.SubmitChanges()
        return true;
    }

}
