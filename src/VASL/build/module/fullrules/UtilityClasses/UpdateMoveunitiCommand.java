package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASSAL.command.Command;

public class UpdateMoveunitiCommand extends Command {

    UpdateMoveunitiCommand(PersUniti PassObject){

    }


    protected void executeCommand() {
            /*this.target.setEndPointsandLevels(this.newAnchor, this.newArrow, this.sourceLevel, this.targetLevel);
            this.target.setPersisting(this.newPersisting);
            this.target.setMirroring(this.newMirroring);*/



    }

    protected Command myUndoCommand() {
        //return new VASLThread.VASLLOSCommand(this.target, this.oldAnchor, this.oldArrow, this.oldPersisting, this.oldMirroring, this.target.sourcelevel, this.target.targetlevel);
        return null;
    }
}
