package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class LocationVisibilityc implements VisibilityStatusi {
    private LinkedList<LocationContentc> Listtocheck;

    public LocationVisibilityc (LinkedList<LocationContentc> ItemsTocheck) {
        Listtocheck = ItemsTocheck;
    }
    public int GetStatus() {
        boolean SomeVisible = false; boolean SomeConcealed = false;
        int Mixed  = 1;
        int AllConc  = 2;
        int AllVis  = 3;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        for (LocationContentc ItemInHex: Listtocheck) {
            Constantvalues.Typetype typeis  = ItemInHex.getTypeIDvalue();
            switch (typeis) {
                case Personnel:
                    //infantry
                    for (PersUniti hexUnit: Scencolls.Unitcol) {
                        if (hexUnit.getbaseunit().getUnit_ID() == ItemInHex.getObjID()) {
                            switch (hexUnit.getbaseunit().getVisibilityStatus()) {
                                case Visible:
                                    // Enemy MMC blocks entry
                                    SomeVisible = true;
                                    break;
                                case Concealed:
                                    Hidden:
                                    SomeConcealed = true;
                            }
                        }
                    }
                case Vehicle:
                    // vehicle
                    /*for (AFV hexVehicle: Scencolls.Vehcol) {
                        if (hexVehicle.AFVID == ItemInHex.ObjID) {
                            if (hexVehicle.getVehicleStatus() == Constantvalues.VStatus.VKnown) {
                                SomeVisible = true;
                            } else if (hexVehicle.getVehicleStatus() == Constantvalues.VStatus.Concealed || hexVehicle.getVehicleStatus() == Constantvalues.VStatus.Hidden) {
                                SomeConcealed = true;
                            }
                        }
                    }*/
                case Gun: case SW: case Location:
                    // Guns, SW or Terrain - do nothing
                    return 0;
                case Concealment:
                    // Concealment
                    SomeConcealed = true; // If hexConceal.IsDummy Then SomeConcealed = True
                    break;
                case WhiteC:
                // anything needed?
                    return 0;
            }
        }
        if (SomeVisible && SomeConcealed) {
            return Mixed;
        } else if (SomeVisible && !SomeConcealed) {
            return AllVis;
        } else if (!SomeVisible && SomeConcealed) {
            return AllConc;
        } else {
            // error if here
            return 0;
        }
    }
}
