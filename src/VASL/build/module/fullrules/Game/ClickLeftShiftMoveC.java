package VASL.build.module.fullrules.Game;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;

import java.util.LinkedList;

public class ClickLeftShiftMoveC implements Clicki {

    public void DetermineClickPossibilities(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {

        LinkedList<GamePiece> SelectedUnits = new LinkedList<GamePiece>();
        // clicking on top counter only
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        // Using list of counters for the hex, if Sw chosen, select owner
        // NEED TO HANDLE CONCEALMENT COUNTERS
        // NEED TO MOVE THIS CODE TO COMMON FUNCTION AS IT WILL BE REPEATED IN EVERY CLICKCLASS
        for (GamePiece Selitem : SelectedCounters) {
            if (Selitem instanceof Stack) {
                for (PieceIterator pi = new PieceIterator(((Stack) Selitem).getPiecesIterator()); pi.hasMoreElements(); ) {
                    GamePiece p2 = pi.nextPiece();
                    if (isSelected(p2)) {

                        SelectedUnits.add(Selitem);
                        //GameModule.getGameModule().getChatter().send("Have found selected unit in click class");

                    }
                }
            } else {
                if (isSelected(Selitem)) {

                    SelectedUnits.add(Selitem);
                    //GameModule.getGameModule().getChatter().send("Have found selected unit in click class");

                }
            }
        }
        // phase specific actions here
        ScenarioC scen = ScenarioC.getInstance();
        scen.Moveobsi.PasstoObserver(ClickedHex, SelectedUnits);

        if (Scencolls.SelMoveUnits.size() > 0) { // moving units exist - could have clicked on moving or defending units
            // only do this if moving units selected - can't start MPh combat unless have moving units
            scen.IFT.ClickedOnNewParticipants(ClickedHex, SelectedUnits);
        }
    }
    private boolean isSelected(GamePiece p) {
        return Boolean.TRUE.equals(p.getProperty(Properties.SELECTED)) &&
                p.getId() != null &&
                !"".equals(p.getId());
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
