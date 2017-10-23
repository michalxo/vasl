package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;

public class GermanDummyc implements PersUniti{
    // encapsulation variables
    private Basepersuniti myBasePU;
    private MovingPersuniti myMovingPU;
    private FiringPersUniti myFiringPU;
    private TargetPersUniti myTargetPU;

    public GermanDummyc (String PassHexname, int PassScenario, Hex PassHex, Location Passhexlocation, Constantvalues.AltPos PasshexPosition, double PassLevelinHex, int PassLOCIndex, boolean PassCX,
                         int PassELR , int PassTurnArrives, Constantvalues.Nationality PassNationality, int PassCon_ID, int PassUnit_ID, Constantvalues.Typetype PassTypeType_ID, int PassFirstSWLink, int PassSecondSWlink,
                         int PassHexEntSideCrossed, int PassSolID, String PassUnitName, int PassLOBLink, Constantvalues.CombatStatus PassCombatStatus, Constantvalues.VisibilityStatus PassVisibilityStatus,
                         Constantvalues.FortitudeStatus PassFortitudeStatus, Constantvalues.OrderStatus PassOrderStatus, Constantvalues.MovementStatus PassMovementStatus, boolean PassPinned, int PassSW,
                         Constantvalues.UClass PassUnitClass, Constantvalues.CharacterStatus PassCharacterStatus, Constantvalues.Utype PassUtype, Constantvalues.RoleStatus PassRoleStatus) {
        // myBasePU must be created by constructor - it has to exist, so readonly to prevent disposal
        myBasePU = new BasePersunitc(PassHexname, PassScenario, PassHex, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassELR, PassTurnArrives, PassNationality,
                PassCon_ID, PassUnit_ID, PassTypeType_ID, PassFirstSWLink, PassSecondSWlink, PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus,
                PassVisibilityStatus, PassFortitudeStatus, PassOrderStatus, PassMovementStatus, PassPinned, PassSW, PassUnitClass, PassCharacterStatus, PassUtype, PassRoleStatus);
        DataC Linqdata = DataC.GetInstance();  // use empty variables when know that instance already exist
        OrderofBattle NewOBUnit;
        if (PassUnit_ID == 0) {  // 'new unit, save to database and create unit ID
            // temporary while debugging UNDO
            /*NewOBUnit = Linqdata.CreateNewUnitinDB(PassCharacterStatus, PassCombatStatus, PassCon_ID, PassCX, PassELR, PassFirstSWLink, PassFortitudeStatus,
                    PassHexEntSideCrossed, Passhexlocation, PassHexname, PassHexnum, CInt(PassLevelinHex), PassLOBLink,
                    PassLOCIndex, PassMovementStatus, PassNationality, PassUnitName, PassOrderStatus, PassPinned, PasshexPosition,
                    PassRoleStatus, PassScenario, PassSecondSWlink, PassSW, PassTurnArrives, PassVisibilityStatus)
            myBasePU.SetID(NewOBUnit.OBUnit_ID)*/
        }


    }

    public Basepersuniti getbaseunit() {return myBasePU;}

    // these properties are populated as required by game actions
    public FiringPersUniti getFiringunit() {return myFiringPU;}
    public MovingPersuniti getMovingunit() {return myMovingPU;}
    public TargetPersUniti getTargetunit() {return myTargetPU;}
    public void setFiringunit(FiringPersUniti value) {myFiringPU = value;}
    public void setMovingunit(MovingPersuniti value) {myMovingPU = value;}
    public void setTargetunit(TargetPersUniti value) {myTargetPU = value;}
}
