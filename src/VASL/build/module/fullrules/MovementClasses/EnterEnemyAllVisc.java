package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;

public class EnterEnemyAllVisc implements EnterEnemyi {

    public boolean getOKToEnter(Constantvalues.UMove MovementOptionClicked, EnemyChecksC CheckforEnemy, Locationi UsingLoc) {
        // If No present concealed or Hidden and none, some or all entering concealed - handles all entering cases
        // Only Berserk or Human Wave attackers can enter regardless of Present units
        // Check for above situations,
        boolean MovingIsMMC= false; boolean MovingIsDummy = true;
        EntryTestc CheckEntry = new EntryTestc();
        PresentTestc CheckPresent = new PresentTestc(null);
        boolean EntryAllowed = CheckEntry.Entrytest();
        // if dummy or all berserk then let enter
        // if Entering is HumanWave or Banzai then can enter
        if (MovementOptionClicked == Constantvalues.UMove.HumanWave ||
            MovementOptionClicked == Constantvalues.UMove.Banzai) {EntryAllowed = true;}
        if (EntryAllowed) {
            return true; // entry not blocked by entering units: Human Wave, Banzai, All Berserk, All Dummy
        } else {
            // check for Present conditions that allow entry: single SMC, unarmed, disrupted
            return CheckPresent.PresentTest(CheckEntry.getMovingisMMC());
        }
    }

}
