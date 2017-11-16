package VASL.build.module.fullrules.Game;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OBVehicles;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.Vehiclesi;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.ObjectFactoryClasses.VehCreation;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class VehicleActionsC {
    // called by Me.CreateCounters
    // Creates the collection of vehicles in the scenario

    // constructor
    public VehicleActionsC(DataC Linqdata, ScenarioC Scendet) {
        // get all vehicles involved in a scenario
        LinkedList<OBVehicles> OBVehcol =  new LinkedList<OBVehicles>(); //Linqdata.RetrieveScenarioVehicles(Scendet.getScenID());
        if (OBVehcol.size() == 0) {
            GameModule.getGameModule().getChatter().send("No scenario vehicles found. Exiting");
            return;
        }

        // use Object Factory to create the vehicle objects
        VehCreation UseObjectFactory = new VehCreation();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        Vehiclesi AddNewVeh;
        Constantvalues.VClass PassClass = Constantvalues.VClass.None;
        for (OBVehicles vehitem : OBVehcol) {
            PassClass = Constantvalues.VClass.None; //  CInt(Game.Linqdata.GetLOBData(Constantvalues.LOBItem.UnitClass, CInt(unititem.LOBLink)))
            // temporary while debugging UNDO
            /*if (vehitem.getOrderStatus() == Constantvalues.OrderStatus.KIAInf ||
                    unititem.getOrderStatus() == Constantvalues.OrderStatus.NotInPlay) {
                continue;
            }
            AddNewUnit = UseObjectFactory.CreateExistingInstance(unititem);
            if (AddNewUnit == null) {
                continue;
            }
            if (UseObjectFactory.IsHeroic(AddNewUnit.getbaseunit().getFortitudeStatus())) {
                // need to decorate leader
                Dim DecNewLeader As ObjectClassLibrary.ASLXNA.PersunitDecoratori = New
                ObjectClassLibrary.ASLXNA.PersunitHeroicldrc(AddNewUnit)
                if (AddNewUnit.getbaseunit() != null) {
                    DecNewLeader.BasePersUnit = New ObjectClassLibrary.ASLXNA.BaseHeroicLdrc(AddNewUnit.BasePersUnit);
                }
                AddNewUnit = DecNewLeader;
            }*/
            // add new unit to Unitcol collection
            //Scencolls.Vehcol.add(AddNewVeh);

        }
    }
        //'Methods
}
