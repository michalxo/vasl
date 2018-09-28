package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.command.Command;

import java.util.LinkedList;

public class UpdateFireSWiCommand extends Command {
    public int myCombatStatus;
    public int myLoc;
    public int mySWID;

    public UpdateFireSWiCommand(SuppWeapi PassObject){

        ConversionC confrom = new ConversionC();
        myCombatStatus = confrom.ConvertCombatStatustoInt(PassObject.getbaseSW().getCombatStatus());
        Constantvalues.Location locvalue = confrom.ConverttoLocationtypefromVASLLocation(PassObject.getbaseSW().gethexlocation());
        myLoc = confrom.ConvertLocationTypetoint(locvalue);
        mySWID = PassObject.getbaseSW().getSW_ID();
    }

    public UpdateFireSWiCommand(int passmyCombatStatus, int passMyLoc, int passMyID) {

        //ConversionC confrom = new ConversionC();
        myCombatStatus = passmyCombatStatus;
        myLoc = passMyLoc;
        mySWID = passMyID;
    }

    protected void executeCommand() {
        ScenarioCollectionsc scencol = ScenarioCollectionsc.getInstance();
        scencol.ProcessFireSwUpdate(this);
    }

    protected Command myUndoCommand() {
        //return new VASLThread.VASLLOSCommand(this.target, this.oldAnchor, this.oldArrow, this.oldPersisting, this.oldMirroring, this.target.sourcelevel, this.target.targetlevel);
        return null;
    }

}
