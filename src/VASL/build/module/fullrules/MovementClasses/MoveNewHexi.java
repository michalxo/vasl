package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;

public interface MoveNewHexi {
    boolean MoveAllOK();
    void MoveUpdate();
    Location EnteringLocation(Hex newhex, Hex currenthex);
}
