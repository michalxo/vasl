package VASL.build.module.fullrules.Game;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;

import java.util.LinkedList;

public class ClickLeftShiftPrepC implements  Clicki{


    public void DetermineClickPossibilities(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {  // int Hexnumber, Optional ByVal Action As Integer=0)Implements Clicki.DetermineClickPossibilities

        LinkedList<GamePiece> SelectedUnits = new LinkedList<GamePiece>();
        // clicking on top counter only
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        // Using list of counters for the hex, if Sw chosen, select owner
        for (GamePiece Selitem : SelectedCounters) {
            if (Selitem instanceof Stack) {
                for (PieceIterator pi = new PieceIterator(((Stack) Selitem).getPiecesIterator()); pi.hasMoreElements(); ) {
                    GamePiece p2 = pi.nextPiece();
                    if (isSelected(p2)) {

                            SelectedUnits.add(Selitem);
                            GameModule.getGameModule().getChatter().send("Have found selected unit in click class");

                    }
                }
            } else {
                if (isSelected(Selitem)) {

                        SelectedUnits.add(Selitem);
                        GameModule.getGameModule().getChatter().send("Have found selected unit in click class");

                }
            }
        }
        ScenarioC scen = ScenarioC.getInstance();
        scen.IFT.ClickedOnNewParticipants(ClickedHex, SelectedUnits);
        /*Else
        'need a popup to show locations to choose from
                'set point to draw menu
        For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
        hexlocationslist.Add(Passitem)
        Next
        Dim MouseAction As Integer=Constantvalues.MouseAction.LeftShift
        Dim Showpoint=New System.Drawing.Point
        Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc=MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0,0,0,0,0,0,0,0,0,0,0)
        Showpoint=MapGeo.SetPoint(Hexnumber)

        Dim ShowLocPop=New LocationContentPopup(Hexnumber,hexlocationslist,Showpoint,MouseAction,0)
        Game.contextshowing=True
        End If
        End Sub*/
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
