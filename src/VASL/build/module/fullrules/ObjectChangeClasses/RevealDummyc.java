package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class RevealDummyc implements VisibilityChangei {
    private PersUniti RevealUnit;
    private String myRevealResults;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    public RevealDummyc(PersUniti PassRevealedUnit) {
    
    RevealUnit = PassRevealedUnit;
    }
    
    public boolean TakeAction() {
        if (RevealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible) {return true;} // no action required, unit is already revealed
        if (RevealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed ||
        RevealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
            RevealUnit.getbaseunit().setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);
            RevealUnit.getbaseunit().setCon_ID(0);
            RevealUnit.getbaseunit().setOrderStatus(Constantvalues.OrderStatus.NotInPlay);
            RevealUnit.getbaseunit().setCX(false);
            RevealUnit.getbaseunit().setPinned(false);
            RevealUnit.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
            RevealUnit.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
            myRevealResults = RevealUnit.getbaseunit().getUnitName() + " is revealed as a dummy";
            //Linqdata.MakeNotInPlay(RevealUnit.getbaseunit().Unit_ID);
            return true; // unit is revealed
        }
        // if here then something wrong
        return false;
    }

    public String getActionResult() {
        return myRevealResults;
    }
}
