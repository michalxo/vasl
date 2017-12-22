package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASSAL.build.GameModule;
import VASSAL.command.Command;

public class ManageUpdateUnitCommand {
    // this class is called whenever a Persuniti object is changed;
    // it determines which object (Baseunit, Fireunit, Moveunit or TargUnit) has been changed
    // and then calls the correct Command creation method

    public Command CreateCommand (PersUniti PassObject, Constantvalues.UnitCommandtype PassCommandtype) {
        if (PassCommandtype == Constantvalues.UnitCommandtype.baseunit) {
            return new UpdateBaseunitiCommand(PassObject);
        } else if (PassCommandtype == Constantvalues.UnitCommandtype.fireunit) {
            return new UpdateFireunitiCommand(PassObject);
        } else if (PassCommandtype == Constantvalues.UnitCommandtype.moveunit) {
            return new UpdateMoveunitiCommand(PassObject);
        } else if (PassCommandtype == Constantvalues.UnitCommandtype.targunit) {
            return new UpdateTargunitiCommand(PassObject);
        } else {
            return null;
        }
    }

    public void ProcessCommand (Command PassCommand){
        PassCommand.execute();
        GameModule.getGameModule().sendAndLog(PassCommand);
        //map.repaint();
    }
}
