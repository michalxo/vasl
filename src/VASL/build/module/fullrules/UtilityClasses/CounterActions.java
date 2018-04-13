package VASL.build.module.fullrules.UtilityClasses;

/*This class exists to process counter actions required by a game action, such as:
 - placement of info counters (PrepFire, Pin)
 - changing an existing counter by flipping or invoking a layer (breaking, wounding, breakdown, substituting)
All of these actions are those that are currently handled by key combos in VASL and this class simply
triggers the key combo action*/

import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.counters.GamePiece;

import javax.swing.*;

public class CounterActions {

    public CounterActions() {

    }

    public void placefirecounter(PersUniti firingunit){

        ConversionC DoConversion = new ConversionC();
        String InfoName = DoConversion.ConvertCombatStatustoCounterNameString(firingunit.getFiringunit().getCombatStatus());

        // add fire counter - this needs to be tied to current phase
        // NEED TO FIX FOR FINAL AND ADV FIRE
        ScenarioC scen = ScenarioC.getInstance();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        CommonFunctionsC ToDO = new CommonFunctionsC(scen.getScenID());
        GamePiece ToPrep = ToDO.GetGamePieceFromID(firingunit.getbaseunit().getUnit_ID());
        if (ToPrep != null && !ToDO.CheckIfInfoCounterExistsFromID(firingunit.getbaseunit().getUnit_ID(), InfoName)) {
            if (InfoName.equals("Prep Fire")) {
                ToPrep.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("First Fire")) {
                ToPrep.keyEvent(KeyStroke.getKeyStroke('J', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Final Fire")) {
                ToPrep.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Adv Fire")){
                ToPrep.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            }
        }
    }

    public void placefirecounter(SuppWeapi firingunit){

        ConversionC DoConversion = new ConversionC();
        String InfoName = DoConversion.ConvertCombatStatustoCounterNameString(firingunit.getbaseSW().getCombatStatus());

        // add fire counter - this needs to be tied to current phase
        // NEED TO FIX FOR FINAL AND ADV FIRE
        ScenarioC scen = ScenarioC.getInstance();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        CommonFunctionsC ToDO = new CommonFunctionsC(scen.getScenID());
        GamePiece ToPrep = ToDO.GetGamePieceFromID(firingunit.getbaseSW().getSW_ID());
        if (ToPrep != null && !ToDO.CheckIfInfoCounterExistsFromID(firingunit.getbaseSW().getSW_ID(), InfoName)) {
            if (InfoName.equals("Prep Fire")) {
                ToPrep.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("First Fire")) {
                ToPrep.keyEvent(KeyStroke.getKeyStroke('J', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Final Fire")) {
                ToPrep.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            } else if (InfoName.equals("Adv Fire")){
                ToPrep.keyEvent(KeyStroke.getKeyStroke('G', java.awt.event.InputEvent.CTRL_MASK));
            }
        }
    }
    public void flipcounter(PersUniti countertoflip){

    }
    public void flipcounter (SuppWeapi countertoflip){
        ScenarioC scen = ScenarioC.getInstance();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        CommonFunctionsC ToDO = new CommonFunctionsC(scen.getScenID());
        GamePiece toflip = ToDO.GetGamePieceFromID(countertoflip.getbaseSW().getSW_ID());
        if (toflip != null) {
            toflip.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
        }
    }
}
