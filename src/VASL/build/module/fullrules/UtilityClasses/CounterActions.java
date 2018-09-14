package VASL.build.module.fullrules.UtilityClasses;

/*This class exists to process counter actions required by a game action, such as:
 - placement of info counters (PrepFire, Pin)
 - changing an existing counter by flipping or invoking a layer (breaking, wounding, breakdown, substituting)
All of these actions are those that are currently handled by key combos in VASL and this class simply
triggers the key combo action*/

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;
import VASSAL.property.Property;

import javax.swing.*;

public class CounterActions {
    ScenarioC scen = ScenarioC.getInstance();
    ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    CommonFunctionsC ToDO = new CommonFunctionsC(scen.getScenID());
    public CounterActions() {

    }
    public GamePiece getcounter (PersUniti usingunit){

        GamePiece touse = ToDO.GetGamePieceFromID(usingunit.getbaseunit().getUnit_ID());
        return touse;
    }
    public GamePiece getcounter (SuppWeapi usingunit){

        GamePiece touse = ToDO.GetGamePieceFromID(usingunit.getbaseSW().getSW_ID());
        return touse;
    }

    public void placefirecounter(PersUniti firingunit){

        ConversionC DoConversion = new ConversionC();
        String InfoName = DoConversion.ConvertCombatStatustoCounterNameString(firingunit.getFiringunit().getCombatStatus());

        // add fire counter - this needs to be tied to current phase
        // NEED TO FIX FOR FINAL AND ADV FIRE
        GamePiece usecounter = getcounter(firingunit);
        if (usecounter != null && !ToDO.CheckIfInfoCounterExistsFromID(firingunit.getbaseunit().getUnit_ID(), InfoName)) {
            if (InfoName.equals("Prep Fire")) {
                usecounter.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("First Fire")) {
                usecounter.keyEvent(KeyStroke.getKeyStroke('J', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Final Fire")) {
                usecounter.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Adv Fire")){
                usecounter.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            }
        }
    }

    public void placefirecounter(SuppWeapi firingunit){

        ConversionC DoConversion = new ConversionC();
        String InfoName = DoConversion.ConvertCombatStatustoCounterNameString(firingunit.getbaseSW().getCombatStatus());

        // add fire counter - this needs to be tied to current phase
        // NEED TO FIX FOR FINAL AND ADV FIRE
        GamePiece usecounter = getcounter(firingunit);
        if (usecounter != null && !ToDO.CheckIfInfoCounterExistsFromID(firingunit.getbaseSW().getSW_ID(), InfoName)) {
            if (InfoName.equals("Prep Fire")) {
                usecounter.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("First Fire")) {
                usecounter.keyEvent(KeyStroke.getKeyStroke('J', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Final Fire")) {
                usecounter.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Adv Fire")){
                usecounter.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            }
        }
    }
    public void flipcounter(PersUniti countertoflip){
        GamePiece usecounter = getcounter(countertoflip);
        if (usecounter != null) {
            usecounter.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
        }
    }
    public void flipcounter (SuppWeapi countertoflip){
        GamePiece usecounter = getcounter(countertoflip);
        if (usecounter != null) {
            usecounter.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
        }
    }
    public void processcounter (Constantvalues.PersUnitResult actiontotake, PersUniti countertouse){
        GamePiece usecounter = getcounter(countertouse);
        if(actiontotake == Constantvalues.PersUnitResult.Breaks) {
            // flip counter and add DM
            usecounter.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
        } else if(actiontotake == Constantvalues.PersUnitResult.Pins) {
            // add Pin counter
            usecounter.keyEvent(KeyStroke.getKeyStroke('P', java.awt.event.InputEvent.CTRL_MASK));
        } else if(actiontotake == Constantvalues.PersUnitResult.Wounds) {
            // flip counter
            usecounter.keyEvent(KeyStroke.getKeyStroke('W', java.awt.event.InputEvent.CTRL_MASK));
        } else if(actiontotake == Constantvalues.PersUnitResult.Dies) {
            // delete counter
            usecounter.keyEvent(KeyStroke.getKeyStroke('D', java.awt.event.InputEvent.CTRL_MASK));
        } else if(actiontotake == Constantvalues.PersUnitResult.DMs) {
            //  add DM
            usecounter.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
            usecounter.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
        } else if(actiontotake == Constantvalues.PersUnitResult.Replaces){
            // replace unit counter
            usecounter.keyEvent(KeyStroke.getKeyStroke('E', java.awt.event.InputEvent.CTRL_MASK));
        } else if(actiontotake == Constantvalues.PersUnitResult.Reduces){
            // reduce unit counter
            usecounter.keyEvent(KeyStroke.getKeyStroke('V', java.awt.event.InputEvent.CTRL_MASK));

            // need to add
        } else if(actiontotake == Constantvalues.PersUnitResult.Disrupts){
            // disrupt unit counter

        } else if(actiontotake == Constantvalues.PersUnitResult.StepReduces){
            // reduce unit counter

        } else if(actiontotake == Constantvalues.PersUnitResult.StepReducesHS){
            // reduce unit counter

        }
    }
    public void updatecounterID (int newID, PersUniti countertouse){
        String passID = String.valueOf(newID);
        GamePiece usecounter = getcounter(countertouse);
        //usecounter.setProperty(Properties., passID);
        usecounter.keyEvent(KeyStroke.getKeyStroke('L', java.awt.event.InputEvent.CTRL_MASK));
    }
}
