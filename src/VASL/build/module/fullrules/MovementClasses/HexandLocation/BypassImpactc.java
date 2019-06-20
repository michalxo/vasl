package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.ScenarioTerrain;

import java.util.LinkedList;

public class BypassImpactc implements HexDecoratori {
    // Checking for Using bypass impacts

    private Locationi basehex;
    private Constantvalues.UMove moveoptionclicked;
    private Double hexsideMFcost;
    // constructor for decorator
    public BypassImpactc (Locationi PassBaseHex, Constantvalues.UMove MovementOptionClicked) {
        basehex = PassBaseHex;
        moveoptionclicked = MovementOptionClicked;
    }

    public double gethexsideentrycost() {return hexsideMFcost;}
    public VASL.LOS.Map.Location getvasllocation() {return basehex.getvasllocation();}
    public Hex getvaslhex() {return basehex.getvaslhex();}
    public Terrain getvaslterrain () {return basehex.getvaslterrain();}
    public String gethexname() {return basehex.gethexname();}
    public Terrain getvaslotherterrain() {return basehex.getvaslotherterrain();}
    public LinkedList<ScenarioTerrain> getScenTerraininhex() {return basehex.getScenTerraininhex();}
    public boolean HasWire() {return basehex.HasWire();}
    public int getAPMines() {return basehex.getAPMines();}
    public void setAPMines (int value){basehex.setAPMines(value);}
    public boolean IsPillbox() {return basehex.IsPillbox();}
    public Constantvalues.Location getLocationtype() {return basehex.getLocationtype();}
    // methods
    public double getlocationentrycost(Hex Currenthex) {
        if (moveoptionclicked == Constantvalues.UMove.bypassrate) {
            return 1.0;
        } else if (moveoptionclicked == Constantvalues.UMove.extrabypassrate) {
            return 2.0;
        } else {
            return basehex.getlocationentrycost(Currenthex);
        }
    }
}