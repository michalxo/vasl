package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;
import VASSAL.command.Command;

public class ManageUpdateSWCommand {
    // this class is called whenever a SuppWeapi object is changed;
    // it determines which object (BaseSW or FiringSW) has been changed
    // and then calls the correct Command creation method

    public Command CreateCommand (SuppWeapi PassObject, Constantvalues.UnitCommandtype PassCommandtype) {
        if (PassCommandtype == Constantvalues.UnitCommandtype.basesw) {
            return new UpdateBaseSWiCommand(PassObject);
        } else if (PassCommandtype == Constantvalues.UnitCommandtype.firesw) {
            return new UpdateFireSWiCommand(PassObject);
        } else {
            return null;
        }
    }

    public void ProcessCommand (Command PassCommand) {
        PassCommand.execute();
        GameModule.getGameModule().sendAndLog(PassCommand);
        //map.repaint();
    }
}
