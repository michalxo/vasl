package VASL.build.module.fullrules.Game;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.DataClasses.OrderofBattleSW;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.ObjectFactoryClasses.SWCreation;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class SWActionsC {
    // called by Me.CreateCounters
    // creates the Scencoll collection of SW in the scenario

    // constructor
    public SWActionsC(DataC Linqdata, ScenarioC Scendet) {
        // get all SWs involved in a scenario
        LinkedList<OrderofBattleSW> OBSWcol = Scendet.getOBSWcol();  // Linqdata.RetrieveScenarioOBSW(Scendet.getScenID());
        if (OBSWcol.size() == 0) {
            GameModule.getGameModule().getChatter().send("No scenario units found. Exiting");
            return;
        }

        // use Object Factory to create the SW objects
        SWCreation UseObjectFactory = new SWCreation();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        SuppWeapi AddNewSW;
        Constantvalues.SWtype PassSWtype = Constantvalues.SWtype.None;
        for (OrderofBattleSW OBSWitem : OBSWcol) {
            PassSWtype = Constantvalues.SWtype.None; //  CInt(Game.Linqdata.GetLOBData(Constantvalues.LOBItem.UnitClass, CInt(unititem.LOBLink)))
            if (OBSWitem.getStatus() == Constantvalues.SWStatus.Used ||
                    OBSWitem.getStatus() == Constantvalues.SWStatus.Eliminated ||
                    OBSWitem.getStatus() == Constantvalues.SWStatus.Malfunctioned) {
                continue;
            }
            AddNewSW = UseObjectFactory.CreateExistingSW(OBSWitem);
            if (AddNewSW == null) {
                continue;
            }
            // temporary while debugging UNDO
            /*if (UseObjectFactory.IsHeroic(AddNewUnit.getbaseunit().getFortitudeStatus())) {
                // need to decorate leader
                Dim DecNewLeader As ObjectClassLibrary.ASLXNA.PersunitDecoratori = New
                ObjectClassLibrary.ASLXNA.PersunitHeroicldrc(AddNewUnit)
                if (AddNewUnit.getbaseunit() != null) {
                    DecNewLeader.BasePersUnit = New ObjectClassLibrary.ASLXNA.BaseHeroicLdrc(AddNewUnit.BasePersUnit);
                }
                AddNewUnit = DecNewLeader;
            }*/
            // add new unit to Unitcol collection
            Scencolls.SWCol.add(AddNewSW);

        }
    }
}
