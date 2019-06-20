package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public interface MoveWithinHexi {
    boolean MoveAllOK();
    void MoveUpdate();
    Location EnteringLocation(Hex newhex, Constantvalues.UMove movementoptionclickedvalue);
    String getmoveresults();
}
