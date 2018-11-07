package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.ScenarioTerrain;

import java.util.LinkedList;

public interface Locationi {
    // Interface to concrete classes to govern access to Hex/Location/Terrain being moved into
    // or in which movement within hex is taking place, used in movement MVC
    // These classes include the VASL Hex, Location and Terrain objects plus other properties and functions

    Terrain getvaslterrain();
    String gethexname();
    double gethexsideentrycost();
    Terrain getvaslotherterrain();
    LinkedList<ScenarioTerrain> getScenTerraininhex();
    double getlocationentrycost(Hex Currenthex);
    Location getvasllocation();
    Hex getvaslhex();
    boolean HasWire();
    int getAPMines();
    void setAPMines(int value);
    boolean IsPillbox();
    Constantvalues.Location getLocationtype();
}
