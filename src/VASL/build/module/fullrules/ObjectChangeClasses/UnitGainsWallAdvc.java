package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASL.build.module.fullrules.UtilityClasses.CounterCreationC;

public class UnitGainsWallAdvc implements UnitChangei {
    private PersUniti unittochange;
    public UnitGainsWallAdvc(PersUniti ChangeUnit) {
        unittochange = ChangeUnit;
    }

    public boolean TakeAction(){
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        // set position
        unittochange.getbaseunit().sethexPosition(Constantvalues.AltPos.WallAdv);
        //if concealed update concealment
        if (unittochange.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed) {
            //Dim UpdateCon = New SetConWAc(ActiveUnitOB.Con_ID, CInt(ActiveUnitOB.Position))
        }
        //update any SW counters associated with this unit
        if (unittochange.getbaseunit().getFirstSWLink() > 0) {
            for (SuppWeapi findSW : Scencolls.SWCol) {
                if (findSW.getbaseSW().getSW_ID() == unittochange.getbaseunit().getFirstSWLink()) {
                    findSW.getbaseSW().sethexposition(unittochange.getbaseunit().gethexPosition());
                }
            }
        }
        if (unittochange.getbaseunit().getSecondSWLink() > 0) {
            for (SuppWeapi findSW : Scencolls.SWCol) {
                if (findSW.getbaseSW().getSW_ID() == unittochange.getbaseunit().getSecondSWLink()) {
                    findSW.getbaseSW().sethexposition(unittochange.getbaseunit().gethexPosition());
                }
            }
        }
        // create a VASL counter
        createcounter();
        return true;
    }
    public void createcounter() {
        CounterCreationC createcounter = new CounterCreationC();
        String countername = "VASSAL.build.module.PieceWindow:Inf/VASSAL.build.widget.ListWidget:Inf/VASSAL.build.widget.PieceSlot:WallAdv";
        createcounter.createCounter(countername);
    }
}
