package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.command.Command;

public class UpdateMoveunitiCommand extends Command {

    public boolean concealedvalue;
    public String myOBname;
    //private Hex currhex;
    //private Location currhexloc;
    //private Constantvalues.AltPos currhexpos;
    public int OBID;
    public double MFleft;
    public boolean UsingDTvalue;
    public boolean UsingRBvalue;
    //private boolean usingencircvalue;
    public boolean hasldrbvalue;
    public double mfusedvalue;
    public int AMvalue;
    public int Dashvalue;
    //private int LOCIndexvalue;
    public int HexEntSideCross;

    public UpdateMoveunitiCommand(PersUniti PassObject){

        ConversionC confrom = new ConversionC();
        this.myOBname = PassObject.getbaseunit().getUnitName();
        this.concealedvalue = PassObject.getMovingunit().getIsConcealed();
        this.OBID = PassObject.getMovingunit().getItemID();
        this.MFleft = PassObject.getMovingunit().getMFAvailable();
        this.UsingDTvalue = PassObject.getMovingunit().getUsingDT();
        this.UsingRBvalue = PassObject.getMovingunit().getUsingRoadBonus();
        //this.usingencircvalue = PassObject.getMovingunit().get
        this.hasldrbvalue = PassObject.getMovingunit().getHasLdrBonus();
        this.mfusedvalue = PassObject.getMovingunit().getMFUsed();
        this.AMvalue = confrom.ConvertMovementStatustoInt(PassObject.getMovingunit().getAssaultMove());
        this.Dashvalue = confrom.ConvertMovementStatustoInt(PassObject.getMovingunit().getDash());
        this.HexEntSideCross = PassObject.getMovingunit().getHexEnteredSideCrossed();
    }

public UpdateMoveunitiCommand(String PassOBname, String PassIsConcealed,  int PassOBID, double PassMFleft, String PassUsingDTvalue,
                              String PassUsingRBvalue, String PassHasLdrBonus, double PassMFUsed, int PassAMvalue, int PassDashvalue,
                              int PassHexEntSideCross){

    ConversionC confrom = new ConversionC();
    this.concealedvalue = confrom.ConverttoBoolean(PassIsConcealed);
    this.myOBname = PassOBname;
    this.OBID = PassOBID;
    this.MFleft = PassMFleft;
    this.UsingDTvalue = confrom.ConverttoBoolean(PassUsingDTvalue);
    this.UsingRBvalue = confrom.ConverttoBoolean(PassUsingRBvalue);
    this.hasldrbvalue = confrom.ConverttoBoolean(PassHasLdrBonus);
    this.mfusedvalue = PassMFUsed;
    this.AMvalue = PassAMvalue;
    this.Dashvalue = PassDashvalue;
    this.HexEntSideCross = PassHexEntSideCross;
    setMovingSW(this.OBID);

}
    protected void executeCommand() {

        ScenarioCollectionsc scencol = ScenarioCollectionsc.getInstance();
        scencol.ProcessMoveUnitUpdate(this);

    }

    protected Command myUndoCommand() {
        //return new VASLThread.VASLLOSCommand(this.target, this.oldAnchor, this.oldArrow, this.oldPersisting, this.oldMirroring, this.target.sourcelevel, this.target.targetlevel);
        return null;
    }
    protected void setMovingSW(int MoverID){
        ScenarioCollectionsc scencol = ScenarioCollectionsc.getInstance();
        for(SuppWeapi testOBSWunit: scencol.SWCol ) {
                if (testOBSWunit.getbaseSW().getOwner() == MoverID) {
                    //testOBSWunit.getbaseSW().setMovementStatus();
                    // NEED TO DECIDE IF THIS IS NECESSARY JUNE 19
                }
        }

    }

}
