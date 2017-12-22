package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASSAL.command.Command;

import java.util.List;

public class UpdateTargunitiCommand extends Command{

    public int myFirerSAN;
    public int myAttackedbydrm;
    public int myAttackedbyFP;
    public boolean myELR5;
    public int myFortitudeStatus;
    public int myIFTResult;
    public boolean myIsConceal;
    public int myMovementStatus;
    public int myOrderStatus;
    public boolean myPinned;
    public int myQualityStatus;
    public int myRandomSelected;
    public int mySmoke;
    public int myVisibilityStatus;
    public int myPersUnitImpact;
    public boolean mySanActivated;
    public boolean myIFTResolved;
    public int myELR;
    public String myName;
    public int myMCNum;
    public int myTargSTackLdrdrm;
    public boolean myHOBFlag;
    public List<String> myConcealedList;
    public String myCombatResultsString = " ";
    public boolean getHoBFlag () {return myHOBFlag;}

    UpdateTargunitiCommand(PersUniti PassObject){
        ConversionC confrom = new ConversionC();

        myFirerSAN = PassObject.getTargetunit().getFirerSan();
        myAttackedbydrm = PassObject.getTargetunit().getAttackedbydrm();
        myAttackedbyFP = PassObject.getTargetunit().getAttackedbyFP();
        myELR5 = PassObject.getTargetunit().getELR5();
        myFortitudeStatus = confrom.ConvertFortitudeStatustoInt(PassObject.getbaseunit().getFortitudeStatus());
        myIFTResult = confrom.ConvertIFTResulttoInt(PassObject.getTargetunit().getIFTResult());
        myIsConceal = PassObject.getTargetunit().getIsConcealed();
        myMovementStatus = confrom.ConvertMovementStatustoInt(PassObject.getbaseunit().getMovementStatus());
        myOrderStatus = confrom.ConvertOrderStatustoInt(PassObject.getbaseunit().getOrderStatus());
        myPinned = PassObject.getTargetunit().getPinned();
        myQualityStatus = PassObject.getTargetunit().getQualityStatus();
        myRandomSelected = PassObject.getTargetunit().getRandomSelected();
        mySmoke = PassObject.getTargetunit().getSmoke();
        myVisibilityStatus = confrom.ConvertVisibilityStatustoInt(PassObject.getbaseunit().getVisibilityStatus());
        myPersUnitImpact = confrom.ConvertPersUnitResulttoint(PassObject.getTargetunit().getPersUnitImpact());
        mySanActivated = PassObject.getTargetunit().getSANActivated();
        myIFTResolved = PassObject.getTargetunit().getIFTResolved();
        myELR = PassObject.getbaseunit().getELR();
        myName = PassObject.getbaseunit().getUnitName();
        myHOBFlag = PassObject.getTargetunit().getHoBFlag();
        myCombatResultsString = PassObject.getTargetunit().getCombatResultsString();
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
