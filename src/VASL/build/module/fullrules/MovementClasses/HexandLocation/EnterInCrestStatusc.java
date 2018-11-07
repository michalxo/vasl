package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.ScenarioTerrain;

import java.util.LinkedList;

public class EnterInCrestStatusc implements HexDecoratori {
    private Locationi basehex;
    private Constantvalues.UMove moveoptionclicked;
    private Double hexsideMFcost;
    // constructor for decorator
    public EnterInCrestStatusc (Locationi PassBaseHex, Constantvalues.UMove MovementOptionClicked) {
        basehex = PassBaseHex;
        moveoptionclicked = MovementOptionClicked;
    }

    public double gethexsideentrycost() {return hexsideMFcost;}
    public Location getvasllocation() {return basehex.getvasllocation();}
    public Hex getvaslhex() {return basehex.getvaslhex();}
    public Terrain getvaslterrain () {return basehex.getvaslterrain();}
    public String gethexname() {return basehex.gethexname();}
    public Terrain getvaslotherterrain() {return basehex.getvaslotherterrain();}
    public LinkedList<ScenarioTerrain> getScenTerraininhex() {return basehex.getScenTerraininhex();}
    public boolean HasWire() {return basehex.HasWire();}
    public int getAPMines() {return basehex.getAPMines();}
    public void setAPMines(int value){;}
    public Constantvalues.Location getLocationtype () {return basehex.getLocationtype();}
    public boolean IsPillbox() {return basehex.IsPillbox();}


    public double getlocationentrycost(Hex Currenthex) {

        if (basehex.equals(Currenthex)) {
            // moving to crest status within hex;
            return 2;
        } else {
            return basehex.getlocationentrycost(Currenthex)-1;
        }

    }
}
