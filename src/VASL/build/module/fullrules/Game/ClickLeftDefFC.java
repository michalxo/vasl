package VASL.build.module.fullrules.Game;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;

import java.util.LinkedList;

public class ClickLeftDefFC {

    public final static String DB_COUNTER_TYPE_MARKER_KEY = "DBCounterType";
    public final static String DB_UNIT_TYPE = "unit";

    public void DetermineClickPossibilities(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {

        LinkedList<GamePiece> SelectedUnits = new LinkedList<GamePiece>();
        // clicking on top counter only
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        // Using list of counters for the hex, if Sw chosen, select owner
        // NEED TO HANDLE CONCEALMENT COUNTERS
        for (GamePiece Selitem : SelectedCounters) {
            if (Selitem instanceof Stack) {
                for (PieceIterator pi = new PieceIterator(((Stack) Selitem).getPiecesIterator()); pi.hasMoreElements(); ) {
                    GamePiece p2 = pi.nextPiece();
                    if (isSelected(p2)) {
                        if (isDBUnitCounter(Selitem)) {
                            SelectedUnits.add(Selitem);
                            GameModule.getGameModule().getChatter().send("Have found selected unit in click class");
                        }
                    }
                }
            } else {
                if (isSelected(Selitem)) {
                    if (isDBUnitCounter(Selitem)) {
                        SelectedUnits.add(Selitem);
                        GameModule.getGameModule().getChatter().send("Have found selected unit in click class");
                    }
                }
            }
        }
        // add phase specific actions here
        //ScenarioC scen = ScenarioC.getInstance();
        //scen.IFT.ClickedOnNewParticipants(ClickedHex, SelectedUnits);
    }
    private boolean isSelected(GamePiece p) {
        return Boolean.TRUE.equals(p.getProperty(Properties.SELECTED)) &&
                p.getId() != null &&
                !"".equals(p.getId());
    }
    /**
     * @param piece a game piece
     * @return true if piece has the unit marker set
     */
    public boolean isDBUnitCounter(GamePiece piece) {

        return isPropertySet(piece, DB_COUNTER_TYPE_MARKER_KEY, DB_UNIT_TYPE);
    }
    /**
     * Checks if a given property is set to a given value
     * @param piece the game piece
     * @param key the property key
     * @param value the value
     * @return true if the property is set and equals the value, otherwise false
     */
    public boolean isPropertySet(GamePiece piece, String key, String value) {

        return piece.getProperty(key) != null && piece.getProperty(key).equals(value);
    }
}
