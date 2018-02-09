package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.map.StartGame;
import VASSAL.command.Command;

public class UpdateFireunitiCommand extends Command{

    public boolean myIsEncirc = false;
    public boolean myCX = false;
    public int myCombatStatus;
    public boolean myIsPinned;
    public double myCombatFP;
    public int mySolID;
    public boolean myHasMG;
    public boolean myUsingInherentFP;
    public boolean myUsingfirstMG;
    public boolean myUsingsecondMG;
    public boolean myIsInCrestStatus;
    public int myhexposition;
    public int myUseHeroOrLeader;
    public int myOBLink;
    public int MyLoc;
    public PersUniti myUnit;

    public UpdateFireunitiCommand(PersUniti PassObject){

        ConversionC confrom = new ConversionC();
        if (PassObject.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Encircled ||
                PassObject.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Enc_Wnd ||
                PassObject.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Fan_Enc ||
                PassObject.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Fan_Wnd_Enc) {
            this.myIsEncirc = true;
        }
        this.myCombatStatus = confrom.ConvertCombatStatustoInt(PassObject.getFiringunit().getCombatStatus());
        this.myIsPinned = PassObject.getFiringunit().getIsPinned();
        this.mySolID = PassObject.getFiringunit().getSolID();
        this.myCombatFP = PassObject.getFiringunit().getCombatFP();
        this.myCX = PassObject.getFiringunit().getIsCX();
        this.myHasMG = PassObject.getFiringunit().getHasMG();
        this.myUsingInherentFP = PassObject.getFiringunit().getUsingInherentFP();
        this.myUsingfirstMG = PassObject.getFiringunit().getUsingfirstMG();
        this.myUsingsecondMG = PassObject.getFiringunit().getUsingsecondMG();
        Constantvalues.Location getLoc = confrom.ConverttoLocationtypefromVASLLocation(PassObject.getbaseunit().gethexlocation());
        MyLoc = confrom.ConvertLocationTypetoint(getLoc);
        myIsInCrestStatus = PassObject.getbaseunit().IsInCrestStatus();
        myhexposition = confrom.ConvertAltPosTypetoInt(PassObject.getbaseunit().gethexPosition());
        myOBLink = PassObject.getbaseunit().getUnit_ID();
        myUseHeroOrLeader =  0; // need to add convert PassObject.getFiringunit().getUseHeroOrLeader()
        myUnit = PassObject;
    }

    public UpdateFireunitiCommand(int passmyCombatStatus, String passmyIsEncirc, String passmyCX, String passmyIsPinned,
        int passmyCombatFP, int passmySolID, String passmyHasMG, String passmyUsingInherentFP,
        String passmyUsingfirstMG, String passmyUsingsecondMG, String passmyIsInCrestStatus,
        int passmyhexposition, int passmyUseHeroOrLeader, int passmyOBLink, int passMyLoc) {

        ConversionC confrom = new ConversionC();
        myCombatStatus = passmyCombatStatus;
        myIsEncirc = confrom.ConverttoBoolean(passmyIsEncirc);
        myCX = confrom.ConverttoBoolean(passmyCX);
        myIsPinned = confrom.ConverttoBoolean(passmyIsPinned);
        myCombatFP = passmyCombatFP;
        mySolID = passmySolID;
        myHasMG = confrom.ConverttoBoolean(passmyHasMG);
        myUsingInherentFP = confrom.ConverttoBoolean(passmyUsingInherentFP);
        myUsingfirstMG = confrom.ConverttoBoolean(passmyUsingfirstMG);
        myUsingsecondMG = confrom.ConverttoBoolean(passmyUsingsecondMG);
        myIsInCrestStatus = confrom.ConverttoBoolean(passmyIsInCrestStatus);
        myhexposition = passmyhexposition;
        myUseHeroOrLeader = passmyUseHeroOrLeader;
        myOBLink = passmyOBLink;
        MyLoc = passMyLoc;
        

    }

    protected void executeCommand() {
            /*this.target.setEndPointsandLevels(this.newAnchor, this.newArrow, this.sourceLevel, this.targetLevel);
            this.target.setPersisting(this.newPersisting);
            this.target.setMirroring(this.newMirroring);*/
        ScenarioCollectionsc scencol = ScenarioCollectionsc.getInstance();
        scencol.ProcessFireUnitUpdate(this);


    }

    protected Command myUndoCommand() {
        //return new VASLThread.VASLLOSCommand(this.target, this.oldAnchor, this.oldArrow, this.oldPersisting, this.oldMirroring, this.target.sourcelevel, this.target.targetlevel);
        return null;
    }
}
