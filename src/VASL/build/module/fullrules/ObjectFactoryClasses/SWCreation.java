package VASL.build.module.fullrules.ObjectFactoryClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.DataClasses.OrderofBattleSW;
import VASL.build.module.fullrules.DataClasses.SupportWeapon;
import VASL.build.module.fullrules.ObjectClasses.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class SWCreation {
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private DataC Linqdata = DataC.GetInstance() ; // use empty variables when know that instance already exists

    public SuppWeapi CreateExistingSW(OrderofBattleSW unititem) {
        // called by ASLXNA.Actions.SWActionsC which creates new suppweapi objects at startup

        // takes information from database objects OrderofBattleSW and SupportWeapon and creates object of type suppweapi which is returned to calling method
        SupportWeapon LOBSW = Linqdata.GetLOBSWRecord(unititem.getWeaponType());

        int PassScenario = unititem.getScenario();
        int PassHexnum = unititem.getHexnumber();
        Constantvalues.Location Passhexlocation = unititem.getHexlocation();
        Constantvalues.AltPos PasshexPosition = unititem.getPosition();
        int PassLOCIndex = unititem.getLocIndex();
        double PassLevelinHex;
        boolean PassCX;
        int PassTurnArrives;
        Constantvalues.Nationality PassNationality = unititem.getNationality();
        int PassCon_ID;
        int PassUnit_ID = unititem.getOBSW_ID();
        Constantvalues.Typetype PassTypeType_ID = Constantvalues.Typetype.SW;
        int PassPP = LOBSW.getPORTAGECOST();
        int PassRepair = LOBSW.getREPAIR();
        int PassDisPP = LOBSW.getDismantledPP();
        int PassHexEntSideCrossed;
        int PassSolID;
        String PassUnitName = unititem.getOBWeapon();
        int PassLOBLink = unititem.getWeaponType();
        Constantvalues.CombatStatus PassCombatStatus = unititem.getCombatStatus();
        Constantvalues.VisibilityStatus PassVisibilityStatus = unititem.getVisibilityStatus();
        Constantvalues.FortitudeStatus PassFortitudeStatus;;
        Constantvalues.SWStatus PassSWStatus = unititem.getStatus();
        Constantvalues.MovementStatus PassMovementStatus;
        boolean PassPinned;
        int PassSW;
        Constantvalues.CharacterStatus PassCharacterStatus;
        boolean PassCaptured = unititem.getCaptured();
        PersUniti SWOwner = null;
        int PassOwner = unititem.getOwner();
        if (PassOwner != 0){
            for (PersUniti OwnerUnit:  Scencolls.Unitcol) {
                if (OwnerUnit.getbaseunit().getUnit_ID() == PassOwner) {
                    SWOwner=OwnerUnit;
                    break;
                }
            }
            if (SWOwner !=null ) {
                PassLevelinHex = SWOwner.getbaseunit().getLevelinHex();
                PassCX = SWOwner.getFiringunit().getIsCX();
                PassTurnArrives = SWOwner.getbaseunit().getTurnArrives();
                PassCon_ID = SWOwner.getbaseunit().getCon_ID();
                PassHexEntSideCrossed = SWOwner.getbaseunit().getHexEntSideCrossed();
                PassSolID = SWOwner.getFiringunit().getSolID();
                PassMovementStatus = SWOwner.getbaseunit().getMovementStatus();
                PassPinned = SWOwner.getFiringunit().getIsPinned();
                PassSW = 0;  // pretty sure not neeeded and can delete
                PassCharacterStatus = SWOwner.getbaseunit().getCharacterStatus();
                PassFortitudeStatus = SWOwner.getbaseunit().getFortitudeStatus();
            } else {
                PassLevelinHex=0;
                PassCX =false;
                PassTurnArrives = 0;
                PassCon_ID = 0;
                PassHexEntSideCrossed = 0;
                PassSolID = 0;
                PassMovementStatus = Constantvalues.MovementStatus.NotMoving;
                PassPinned = false;
                PassSW = 0;
                PassCharacterStatus = Constantvalues.CharacterStatus.NONE;
                PassFortitudeStatus = Constantvalues.FortitudeStatus.Normal;
            }
        } else {

            PassLevelinHex=0;
            PassCX =false;
            PassTurnArrives = 0;
            PassCon_ID = 0;
            PassHexEntSideCrossed = 0;
            PassSolID = 0;
            PassMovementStatus = Constantvalues.MovementStatus.NotMoving;
            PassPinned = false;
            PassSW = 0;
            PassCharacterStatus = Constantvalues.CharacterStatus.NONE;
            PassFortitudeStatus = Constantvalues.FortitudeStatus.Normal;
        }
        switch (unititem.getWeaponType()) {
            case 5017:
                return new GermanLMGc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            /*case 5018:
                return new GermanMMGc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5019:
                return new GermanHMGc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5020:
                return new GermanFTc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5021:
                return new GermanDCHc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5022:
                return new GermanRADc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5023:
                return new GermanPHNc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5024:
                return new GermanATRc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5025:
                return new GermanPSKc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5026:
                return new GermanM50c(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5043:
                return new RussianLMGc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5044:
                return new RussianMMGc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5045:
                return new RussianHMGc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5049:
                return new RussianFTc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5048:
                return new RussianDCHc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5052:
                return new RussianRADc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5053:
                return new RussianPHNc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5047:
                return new RussianATRc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5050:
                return new RussianMolPc(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5051:
                return new RussianM50c(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
            case 5046:
                return new RussianHMG50c(PassScenario, PassHexnum, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID, PassTypeType_ID, PassPP, PassRepair, PassDisPP,
                    PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassSWStatus, PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);*/
            default:
                return null;
        }

    }

    // Firing SW property
    // MAY NOT NEED THIS FUNCTION; KEEP IT FOR A WHILE AND THEN DELETE IF NOT USED
    public SuppWeapi CreatefiringSwandProperty (SuppWeapi SWitem) {
        // called by

        // adds firing property to selected SuppWeapi and returns updated object
        if (Constantvalues.Typetype.SW == SWitem.getbaseSW().getType_ID() ) { // item is sw
            OrderofBattleSW PassSW = Linqdata.GetOBSWRecord(SWitem.getbaseSW().getUnit_ID());
            SWitem.setFiringSW(createfiringswproperty(SWitem));
        }
        return SWitem;
    }
    public FiringSuppWeapi createfiringswproperty(SuppWeapi switem) {
        // called by ASLXNA.Combat.IFTC.AddtoTempFG

        // creates and returns to calling routine a Firing SW property to be added to a Suppweapi object
        switch (switem.getbaseSW().getLOBLink()) {
            case 1:
                return null;
            case 5017:
                return new GermanLMGFiringc(switem);
            /*case 5018:
                return new GermanMMGfiringc(switem);
            case 5019:
                return new GermanHMGFiringc(switem);
            case 5020:
                return new GermanFTFiringc();
            case 5021:
                return new GermanDCHFiringc();
            case 5022:
                return new GermanRadFiringc();
            case 5023:
                return new GermanPHNFiringc();
            case 5024:
                return new GermanATRFiringc();
            case 5025:
                return new GermanPSKFiringc();
            case 5026:
                return new GermanM50Firingc();
            case 5043:
                return new RussianLMGFiringc(switem);
            case 5044:
                return new RussianMMGfiringc(switem);
            case 5045:
                return new RussianHMGFiringc(switem);
            case 5049:
                return new RussianFTFiringc();
            case 5048:
                return new RussianDCHFiringc();
            case 5052:
                return new RussianRadFiringc();
            case 5053:
                return new RussianPHNFiringc();
            case 5047:
                return new RussianATRFiringc();
            case 5050:
                return new RussianMolPFiringc();
            case 5051:
                return new RussianM50Firingc();
            case 5046:
                return new RussianHMG50Firingc(switem);*/
            default:
                return null;
        }
    }
    
}
