package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;

public class RevealUnitC implements VisibilityChangei {
    private PersUniti RevealUnit=null;
    private String myRevealResults = "";
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    public RevealUnitC(PersUniti PassRevealed) {
        RevealUnit = PassRevealed;
    }
    public boolean TakeAction () {
        if (RevealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible) {return true;} // no action required, unit is already revealed
        if (RevealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed ||
            RevealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
            RevealUnit.getbaseunit().setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);
            RevealUnit.getbaseunit().setCon_ID(0);
            myRevealResults = (RevealUnit.getbaseunit().getUnitName());
            // Need to add any SW associated with this unit
            if (RevealUnit.getbaseunit().getFirstSWLink() != 0) {   // 0 value means no SW
                // retrieve SW and change visibility status
                for (SuppWeapi RevealSW : Scencolls.SWCol) {
                    if (RevealSW.getbaseSW().getSW_ID() == RevealUnit.getbaseunit().getFirstSWLink()) {
                        if (RevealSW.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed ||
                                RevealSW.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
                            RevealSW.getbaseSW().setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);
                            myRevealResults += " & " + RevealSW.getbaseSW().getUnitName();
                        }
                    }
                }
            }
            if (RevealUnit.getbaseunit().getSecondSWLink() != 0) {   // 0 value means no SW
                // retrieve SW and change visibility status
                for (SuppWeapi RevealSW : Scencolls.SWCol) {
                    if (RevealSW.getbaseSW().getSW_ID() == RevealUnit.getbaseunit().getSecondSWLink()) {
                        if (RevealSW.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed ||
                                RevealSW.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
                            RevealSW.getbaseSW().setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);
                            myRevealResults += " + " + RevealSW.getbaseSW().getUnitName();
                        }
                    }
                }
            }
            myRevealResults += " revealed in " + RevealUnit.getbaseunit().getHexName();
            RevealUnit.getbaseunit().UpdateBaseStatus(RevealUnit);
            return true;   // unit is revealed
        }
        // if here then something wrong
        GameModule.getGameModule().getChatter().send(RevealUnit.getbaseunit().getUnitName() + " could not be revealed. Action fails");
        return false;
    }
    public String getActionResult() {return myRevealResults;}

}
