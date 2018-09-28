package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.command.Command;

import java.util.LinkedList;

public class UpdateBaseSWiCommand extends Command {

    public int myScenario;
    public String myHexName;
    public int myhexlocation;
    public int myhexPosition;
    public double myLevelinHex;
    public String myCX;
    public int myTurnArrives;
    public int myNationality;
    public int myCon_ID;
    public int myUnit_ID;
    public int myTypeType_ID;
    public int myHexEntSideCrossed;
    public int mySolID;
    public String myUnitName;
    public int myLOBLink;
    public int myMovementStatus;
    public int myFortitudeStatus;
    public int mySWStatus;
    public int myVisibilityStatus;
    public int myCombatStatus;
    public int myCharacterStatus;
    public String myPinned;
    public int myPP;
    public int myRepair;
    public int myDisPP;
    public String myCaptured;
    public int myOwner;
    public String myIsMG;
    public String myIsFT;
    public String myIsDC;

    public UpdateBaseSWiCommand(SuppWeapi PassObject) {

        ConversionC confrom = new ConversionC();
        myScenario = PassObject.getbaseSW().getScenario();
        myHexName = PassObject.getbaseSW().getHex().getName();
        Constantvalues.Location locvalue = confrom.ConverttoLocationtypefromVASLLocation(PassObject.getbaseSW().gethexlocation());
        myhexlocation = confrom.ConvertLocationTypetoint(locvalue);
        myhexPosition = confrom.ConvertAltPosTypetoInt(PassObject.getbaseSW().gethexPosition());
        myLevelinHex = PassObject.getbaseSW().getLevelinHex();
        myCX = confrom.ConvertBooleantoString(PassObject.getbaseSW().getCX());
        myTurnArrives = PassObject.getbaseSW().getTurnArrives();
        myNationality = confrom.ConvertNationalitytoInt(PassObject.getbaseSW().getNationality());
        myCon_ID = PassObject.getbaseSW().getCon_ID();
        myUnit_ID = PassObject.getbaseSW().getSW_ID();
        myTypeType_ID = confrom.ConvertTypetypetoint(PassObject.getbaseSW().getType_ID());
        myHexEntSideCrossed = PassObject.getbaseSW().getHexEntSideCrossed();
        mySolID = PassObject.getbaseSW().getSolID();
        myUnitName = PassObject.getbaseSW().getUnitName();
        myLOBLink = PassObject.getbaseSW().getLOBLink();
        myMovementStatus = confrom.ConvertMovementStatustoInt(PassObject.getbaseSW().getMovementStatus());
        myFortitudeStatus = confrom.ConvertFortitudeStatustoInt(PassObject.getbaseSW().getFortitudeStatus());
        mySWStatus = confrom.ConvertSWStatustoInt(PassObject.getbaseSW().getSWStatus());
        myVisibilityStatus = confrom.ConvertVisibilityStatustoInt(PassObject.getbaseSW().getVisibilityStatus());
        myCombatStatus = confrom.ConvertCombatStatustoInt(PassObject.getbaseSW().getCombatStatus());
        myPinned = confrom.ConvertBooleantoString(PassObject.getbaseSW().getPinned());
        myPP = PassObject.getbaseSW().getPP();
        myCharacterStatus = confrom.ConvertCharacterStatustoInt(PassObject.getbaseSW().getCharacterStatus());
        myRepair = PassObject.getbaseSW().getRepair();
        myDisPP = PassObject.getbaseSW().getDisPP();
        myCaptured = confrom.ConvertBooleantoString(PassObject.getbaseSW().isCaptured());
        myOwner = PassObject.getbaseSW().getOwner();
        myIsDC = confrom.ConvertBooleantoString(PassObject.getbaseSW().IsDC());
        myIsFT = confrom.ConvertBooleantoString(PassObject.getbaseSW().IsFT());
        myIsMG = confrom.ConvertBooleantoString(PassObject.getbaseSW().IsMG());
        //return this;
    }

    public UpdateBaseSWiCommand(int passScenario, String passHexname, int passhexlocation,
                                  int passhexPosition, double passLevelinHex, String passCX, int passTurnArrives, int passNationality,
                                  int passCon_ID, int passUnit_ID, int passTypeType_ID,
                                  int passHexEntSideCrossed, int passSolID, String passUnitName, int passLOBLink, int passMovementStatus,
                                  int passFortitudeStatus, int passSWStatus, int passVisibilityStatus, int passCombatStatus,
                                  String passPinned, int passPP, int passCharacterStatus, int passRepair, int passDisPP, String passCaptured,
                                int passOwner, String passIsDC, String passIsFT, String passIsMG){

        ConversionC confrom = new ConversionC();
        myHexName = passHexname;
        myScenario=passScenario;
        myhexlocation= passhexlocation;
        myhexPosition = passhexPosition;
        myLevelinHex = passLevelinHex;
        myCX = passCX;
        myTurnArrives = passTurnArrives;
        myNationality = passNationality;
        myCon_ID = passCon_ID;
        myUnit_ID = passUnit_ID;
        myTypeType_ID = passTypeType_ID;
        myHexEntSideCrossed = passHexEntSideCrossed;
        mySolID = passSolID;
        myUnitName = passUnitName;
        myLOBLink = passLOBLink;
        myMovementStatus = passMovementStatus;
        myFortitudeStatus = passFortitudeStatus;
        mySWStatus = passSWStatus;
        myVisibilityStatus = passVisibilityStatus;
        myCombatStatus = passCombatStatus;
        myPinned = passPinned;
        myPP = passPP;
        myCharacterStatus = passCharacterStatus;
        myRepair = passRepair;
        myDisPP = passDisPP;
        myCaptured = passCaptured;
        myOwner = passOwner;
        myIsDC = passIsDC;
        myIsFT = passIsFT;
        myIsMG = passIsMG;

    }
    protected void executeCommand() {
        ScenarioCollectionsc scencol = ScenarioCollectionsc.getInstance();
        scencol.ProcessBaseSWUpdate(this);



    }

    protected Command myUndoCommand() {
        //return new VASLThread.VASLLOSCommand(this.target, this.oldAnchor, this.oldArrow, this.oldPersisting, this.oldMirroring, this.target.sourcelevel, this.target.targetlevel);
        return null;
    }


}
