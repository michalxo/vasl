package VASL.build.module.fullrules.MovementClasses.HexandLocation;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.ScenarioTerrain;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.LinkedList;

public class HexsideImpactc implements HexDecoratori {

    private Locationi basehex;
    private Constantvalues.UMove moveoptionclicked;
    private double hexsideMFcost;
    // constructor for decorator
    public HexsideImpactc (Locationi PassBaseHex, Constantvalues.UMove MovementOptionClicked) {
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
    public void setAPMines (int value){basehex.setAPMines(value);}
    public boolean IsPillbox() {return basehex.IsPillbox();}
    public Constantvalues.Location getLocationtype() {return basehex.getLocationtype();}
    public double getlocationentrycost(Hex Currenthex) {
        // check for hexside entry costs

        ConversionC confrom = new ConversionC();
        // add hexsidecrossed determination
        int hexsidecrossed = 0; // placeholder code
        // now get hexsideterrrain
        Terrain hexsideterrain = basehex.getvaslhex().getHexsideTerrain(hexsidecrossed);
        // road rate
        if (moveoptionclicked == Constantvalues.UMove.Roadrate) {return 1.0;}
        // hexside test (which includes slope)
        if(confrom.ConverttoHexside(hexsideterrain) == Constantvalues.Hexside.Trench  && (moveoptionclicked == Constantvalues.UMove.EnterConnectingTrench || moveoptionclicked == Constantvalues.UMove.EnterConnectingPill)) { //<=ConstantClassLibrary.ASLXNA.Hexside.TrenchWall) AndAlso (OptionClicked >=     ConstantClassLibrary.ASLXNA.UMove.EnterConnectingTrench And     OptionClicked <=ConstantClassLibrary.ASLXNA.UMove.EnterConnectingPill)
            hexsideMFcost = 0.0;
        } else if (confrom.ConverttoHexside(hexsideterrain) != Constantvalues.Hexside.NoTerrain) { // if hexside is not clear
            // cost to get over side
            hexsideMFcost = hexsideterrain.getMF();
        }
        // MessageBox.Show("Hexside costs " & HexsideMFcost.ToString & " MF to cross")
        return basehex.getlocationentrycost(Currenthex) + hexsideMFcost;

    }

}
