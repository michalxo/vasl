package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.ScenarioTerrain;

import java.util.LinkedList;

public class MoveUphillc implements  HexDecoratori {
    // Checking for uphill movememnt

    private Locationi basehex;
    private Constantvalues.UMove moveoptionclicked;
    private Double hexsideMFcost;
    private int intermediatelevels =0; // handles Abrupt Elevation (B10.5)
    boolean currentposisexitedcrest;
    // constructor for decorator
    public MoveUphillc (Locationi PassBaseHex, boolean PassIsExitedCrest) {
        basehex = PassBaseHex;
        //moveoptionclicked = MovementOptionClicked;
        currentposisexitedcrest = PassIsExitedCrest;

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
    public void setAPMines(int value) {;}
    public boolean IsPillbox() {return basehex.IsPillbox();}
    public Constantvalues.Location getLocationtype() {return basehex.getLocationtype();}
    // methods
    public double getlocationentrycost(Hex Currenthex) {
        // Crest test
        return (MovingUphill(Currenthex) ? (intermediatelevels * 2) + (basehex.getlocationentrycost(Currenthex) * 2) : basehex.getlocationentrycost(Currenthex));
        // 'MessageBox.Show("Total entry cost of " & Me.hexname & " is " & TotalMF.ToString & " MF")
    }
    private boolean MovingUphill(Hex Currenthex) {

        int StartBaseLevel = Currenthex.getBaseHeight();
        if (currentposisexitedcrest) {StartBaseLevel += 1;}
        int levelchange = basehex.getvaslhex().getBaseHeight() - StartBaseLevel;
        if (levelchange <=0) {
            return false;
        } else {
            // MessageBox.Show("MovingUphill")
            intermediatelevels = levelchange - 1;
        }
        return true;

    }
}
