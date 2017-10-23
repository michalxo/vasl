package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class GermanLMGc implements SuppWeapi {
    
    //encapsulation variables
    private BaseSuppWeapc myBaseSW; 
    // private myMovingSW As MovingSuppWeapi
    private FiringSuppWeapi myFiringSW;
    

    public GermanLMGc(int PassScenario, Hex PassHex, Location Passhexlocation, Constantvalues.AltPos PasshexPosition, double  PassLevelinHex, int PassLOCIndex, boolean PassCX,
                      int PassTurnArrives, Constantvalues.Nationality PassNationality, int PassCon_ID, int PassUnit_ID, Constantvalues.Typetype PassTypeType_ID, int PassPP, int PassRepair, int PassDisPP,
                      int PassHexEntSideCrossed, int PassSolID, String PassUnitName, int PassLOBLink, Constantvalues.CombatStatus PassCombatStatus, Constantvalues.VisibilityStatus PassVisibilityStatus,
                      Constantvalues.FortitudeStatus PassFortitudeStatus, Constantvalues.SWStatus PassOrderStatus, Constantvalues.MovementStatus PassMovementStatus, boolean PassPinned, int PassSW,
                      Constantvalues.CharacterStatus PassCharacterStatus, boolean PassCaptured, int PassOwner) {
            // myBasePU must be created by constructor - it has to exist, so readonly to prevent disposal 
            myBaseSW = new BaseSuppWeapc(PassScenario, PassHex, Passhexlocation, PasshexPosition, PassLevelinHex, PassLOCIndex, PassCX, PassTurnArrives, PassNationality, PassCon_ID, PassUnit_ID,
            PassTypeType_ID, PassPP, PassRepair, PassDisPP, PassHexEntSideCrossed, PassSolID, PassUnitName, PassLOBLink, PassCombatStatus, PassVisibilityStatus, PassFortitudeStatus, PassOrderStatus, 
            PassMovementStatus, PassPinned, PassSW, PassCharacterStatus, PassCaptured, PassOwner);
    }

    public BaseSuppWeapc getbaseSW() {return myBaseSW;}
    
    public FiringSuppWeapi getFiringSW () {return myFiringSW;}
    public void setFiringSW(FiringSuppWeapi value ){myFiringSW = value;}
    
    //public MovingSuppWeapi getMovingSW () {return myMovingSW;} 
    //public void setMovingSW(MovingSuppWeapi value) {myMovingSW = value;}
    public boolean IsDC () {return false;}
    public boolean IsFT () {return false;}
    public boolean IsMG () {return true;}
        
}
