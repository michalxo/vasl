package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.Basepersuniti;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASSAL.command.Command;

import java.util.LinkedList;

public class UpdateBaseunitiCommand extends Command{

    public String pHexname;
    public int pScenario;
    public Hex pHex;  // not encoded - create in decode using pHexname
    public int phexlocation;
    public int phexPosition;
    public double pLevelinHex;
    public boolean pCX;
    public int pELR;
    public int pTurnArrives;
    public int pNationality;
    public int pCon_ID;      // OB id of associated concealment counter; for concealment counters themsleves is always 0  Orderofbattle.con_id
    public int pUnit_ID;     // OB id of the personnel/SW/concealment counter  Orderofbattle.OBUnit_ID/Concealment.con_id/OrderofBattleSW/OBSW_ID
    public int pTypeType_ID;      //  value of Enum.TypeType for all counters
    public int pUnitType; // value of Unittype; stored in LOB and SMC tables;  equals Enum.Utype
    public int pFirstSWLink;
    public int pSecondSWlink;
    public int pSW;
    public int pHexEntSideCrossed;
    public int pSolID;
    public String pUnitName;
    public int pLOBLink;     // ID of LineOfBattle or SupportWeapon of which this object is an instance  OrderofBattle.LOBLink/OrderofBattleSW.Weapontype  for concealment is always 0
    public int pMovementStatus;
    public int pFortitudeStatus;
    public int pOrderStatus;
    public int pVisibilityStatus;
    public int pCombatStatus;
    public int pCharacterStatus;
    public int pRoleStatus;
    public boolean pPinned;
    public int pUnitClass;   // value of Enum.UClass for personnel units; always 0 for concealment and SW counters   LineOfBattle.Class
    public LinkedList<PersUniti> pGuarding;

    public UpdateBaseunitiCommand(PersUniti PassObject) {

        ConversionC confrom = new ConversionC();
        pHexname = PassObject.getbaseunit().getHexName();
        pScenario = PassObject.getbaseunit().getScenario();
        // pHex = PassObject.getbaseunit().getHex();
        Constantvalues.Location locvalue = confrom.ConverttoLocationtypefromVASLLocation(PassObject.getbaseunit().gethexlocation());
        phexlocation = confrom.ConvertLocationTypetoint(locvalue);
        phexPosition = confrom.ConvertAltPosTypetoInt(PassObject.getbaseunit().gethexPosition());
        pLevelinHex = PassObject.getbaseunit().getLevelinHex();
        pCX = PassObject.getbaseunit().getCX();
        pELR = PassObject.getbaseunit().getELR();
        pTurnArrives = PassObject.getbaseunit().getTurnArrives();
        pNationality = confrom.ConvertNationalitytoInt(PassObject.getbaseunit().getNationality());
        pCon_ID = PassObject.getbaseunit().getCon_ID();
        pUnit_ID = PassObject.getbaseunit().getUnit_ID();
        pTypeType_ID = confrom.ConvertTypetypetoint(PassObject.getbaseunit().getTypeType_ID());
        pSW = PassObject.getbaseunit().getnumSW();
        pFirstSWLink = PassObject.getbaseunit().getFirstSWLink();
        pSecondSWlink = PassObject.getbaseunit().getSecondSWLink();
        pHexEntSideCrossed = PassObject.getbaseunit().getHexEntSideCrossed();
        pSolID = PassObject.getbaseunit().getSolID();
        pUnitName = PassObject.getbaseunit().getUnitName();
        pLOBLink = PassObject.getbaseunit().getLOBLink();
        pMovementStatus = confrom.ConvertMovementStatustoInt(PassObject.getbaseunit().getMovementStatus());
        pFortitudeStatus = confrom.ConvertFortitudeStatustoInt(PassObject.getbaseunit().getFortitudeStatus());
        pOrderStatus = confrom.ConvertOrderStatustoInt(PassObject.getbaseunit().getOrderStatus());
        pVisibilityStatus = confrom.ConvertVisibilityStatustoInt(PassObject.getbaseunit().getVisibilityStatus());
        pCombatStatus = confrom.ConvertCombatStatustoInt(PassObject.getbaseunit().getCombatStatus());
        pPinned = PassObject.getbaseunit().getPinned();
        pUnitClass = confrom.ConvertUClasstoint(PassObject.getbaseunit().getUnitClass());
        pCharacterStatus = confrom.ConvertCharacterStatustoInt(PassObject.getbaseunit().getCharacterStatus());
        pUnitType = confrom.ConvertUnitTypetoint(PassObject.getbaseunit().getUnittype());
        pRoleStatus = confrom.ConvertRoleStatustoInt(PassObject.getbaseunit().getRoleStatus());
        //return this;
    }

    public UpdateBaseunitiCommand(String passHexname, int passScenario, int passhexlocation,
        int passhexPosition, int passLevelinHex, String passCX, int passELR, int passTurnArrives, int passNationality,
        int passCon_ID, int passUnit_ID, int passTypeType_ID, int passSW, int passFirstSWLink, int passSecondSWlink,
        int passHexEntSideCrossed, int passSolID, String passUnitName, int passLOBLink, int passMovementStatus,
        int passFortitudeStatus, int passOrderStatus, int passVisibilityStatus, int passCombatStatus,
        String passPinned, int passUnitClass, int passCharacterStatus, int passUnitType, int passRoleStatus){

        ConversionC confrom = new ConversionC();
        pHexname = passHexname;
        pScenario=passScenario;
        phexlocation= passhexlocation;
        phexPosition = passhexPosition;
        pLevelinHex = passLevelinHex;
        pCX = confrom.ConverttoBoolean(passCX);
        pELR = passELR;
        pTurnArrives = passTurnArrives;
        pNationality = passNationality;
        pCon_ID = passCon_ID;
        pUnit_ID = passUnit_ID;
        pTypeType_ID = passTypeType_ID;
        pSW = passSW;
        pFirstSWLink = passFirstSWLink;
        pSecondSWlink = passSecondSWlink;
        pHexEntSideCrossed = passHexEntSideCrossed;
        pSolID = passSolID;
        pUnitName = passUnitName;
        pLOBLink = passLOBLink;
        pMovementStatus = passMovementStatus;
        pFortitudeStatus = passFortitudeStatus;
        pOrderStatus = passOrderStatus;
        pVisibilityStatus = passVisibilityStatus;
        pCombatStatus = passCombatStatus;
        pPinned = confrom.ConverttoBoolean(passPinned);
        pUnitClass = passUnitClass;
        pCharacterStatus = passCharacterStatus;
        pUnitType = passUnitType;
        pRoleStatus = passRoleStatus;

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
