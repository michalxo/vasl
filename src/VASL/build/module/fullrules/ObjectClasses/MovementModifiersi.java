package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public interface MovementModifiersi {
    // MovementModifiers interface provides interface to check various MF determinations

    boolean getDoubleTiming();
    boolean getsLdrBonus();
    boolean getsRoadBonus();
    int getPPAdjust();
    boolean isEncircled();

    public void updateModifiers(PersUniti MovingUnittoCheck, Constantvalues.UMove controlclick, boolean isleaderpresent);
}
