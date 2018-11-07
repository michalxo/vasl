package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class StackLeaderTestC implements LeaderTesti{

    private LinkedList<PersUniti> TestStack;
    private ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();

    public StackLeaderTestC (LinkedList<PersUniti> PassStack) {

        // needs to take list as parameters because comes from different places
        TestStack = PassStack;
    }
    public boolean IsLeaderPresent() {
        // calledy by ASLXNA.MovementC.AddToStackAttempt
        // determine if ldr present in stack

        for (PersUniti unittocheck: TestStack) {
            if (unittocheck.getbaseunit().IsUnitALeader()) {
                return true;
            }
        }
        return false;  // if here, no leader
    }
    public boolean IsUnwoundedLeaderPresent() {
        // called by ASLXNA.MOVEC.ProcessPopUp, RedoMovementSTack
        // determine if unwounded ldr present in stack

        for (PersUniti unittocheck: TestStack) {
            if (unittocheck.getbaseunit().IsUnitALeader()) {
                if(unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Wounded || unittocheck.getbaseunit().getFortitudeStatus() ==
                        Constantvalues.FortitudeStatus.Enc_Wnd || unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd ||
                        unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd_Enc) {
                    return true;
                }
            }
        }
        return false;  // if here, no leader
    }
}
