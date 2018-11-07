package VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public interface LegalMovei {
    boolean IsMovementLegal();
    //int hexclicked;
    //Constantvalues.UMove MovementOption;
    //Location LocationChange;
    String getresultsstring(); // used for reporting purposes in some classes; don't implement in others - return null
    Location getLocationchangevalue();
}
