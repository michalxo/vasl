package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;

public interface EnterEnemyi {
    boolean getOKToEnter(Constantvalues.UMove MovementOptionClicked, EnemyChecksC CheckforEnemy, Locationi UsingLoc);
}
