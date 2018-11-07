package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.DiceC;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;
import VASL.build.module.fullrules.UtilityClasses.ManageUpdateUnitCommand;
import VASSAL.command.Command;

import java.util.List;

public class German149Targc implements TargetPersUniti {

    private int myFirerSAN;
    private int myAttackedbydrm;
    private int myAttackedbyFP;
    private boolean myELR5;
    private Constantvalues.FortitudeStatus myFortitudeStatus;
    private Constantvalues.IFTResult myIFTResult;
    private boolean myIsConceal;
    private Constantvalues.MovementStatus myMovementStatus;
    private Constantvalues.OrderStatus myOrderStatus;
    private boolean myPinned;
    private int myQualityStatus;
    private int myRandomSelected;
    private int mySmoke;
    private Constantvalues.VisibilityStatus myVisibilityStatus;
    private Constantvalues.PersUnitResult myPersUnitImpact;
    private boolean mySanActivated;
    private boolean myIFTResolved;
    private int myELR;
    private String myName;
    private int myMCNum;
    private int myTargSTackLdrdrm;
    private boolean myHOBFlag;
    private List<String> myConcealedList;
    private String myCombatResultsString = " ";
    public boolean getHoBFlag () {return myHOBFlag;}
    public void setHoBFlag(boolean value ) {myHOBFlag = value;}
    private DiceC Dieclass = new DiceC();

    public German149Targc(Constantvalues.IFTResult PassIFTResult, int TargStackLdrdrm, int PassFirerSan, int PassAttackedbydrm, int PassAttackedbyFP, boolean PassELR5, boolean PassIsConceal, boolean PassIsDummy,
                          boolean PassPinned, int PassQualityStatus, int PassRandomSelected, int PassSmoke, PersUniti PassUnit) {
        myFirerSAN = PassFirerSan;
        myAttackedbydrm = PassAttackedbydrm;
        myAttackedbyFP = PassAttackedbyFP;
        myELR5 = PassELR5;
        myFortitudeStatus = PassUnit.getbaseunit().getFortitudeStatus();
        myIFTResult = PassIFTResult;
        myIsConceal = PassIsConceal;
        myMovementStatus = PassUnit.getbaseunit().getMovementStatus();
        myOrderStatus = PassUnit.getbaseunit().getOrderStatus();
        myPinned = PassPinned;
        myQualityStatus = PassQualityStatus;
        myRandomSelected = PassRandomSelected;
        mySmoke = PassSmoke;
        myVisibilityStatus = PassUnit.getbaseunit().getVisibilityStatus();
        myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
        mySanActivated = false;
        myIFTResolved = false;
        myELR = PassUnit.getbaseunit().getELR();
        myName = PassUnit.getbaseunit().getUnitName();
        myHOBFlag = false;
    }

    public int getAttackedbydrm() {return myAttackedbydrm;}
    public void setAttackedbydrm(int value) {myAttackedbydrm = value;}
    public int getAttackedbyFP () {return myAttackedbyFP;}
    public void setAttackedbyFP(int value) {myAttackedbyFP = value;}
    public int getBPV () {return 0;}
    public int getBrokenML () {return 8;}
    public int getMCNumber () {return myMCNum;}
    public void setMCNumber(int value) {myMCNum = value;}
    public int getTargStackLeaderDRM () {return myTargSTackLdrdrm;}
    public void setTargStackLeaderDRM(int value) {myTargSTackLdrdrm = value;}
    public boolean getELR5 () {return myELR5;}
    public void setELR5(boolean value) {myELR5 = value;}
    public Constantvalues.FortitudeStatus getFortitudeStatus () {return myFortitudeStatus;}
    public void setFortitudeStatus(Constantvalues.FortitudeStatus value) {myFortitudeStatus = value;}
    public int getHardensTo () {return -2;}
    public Constantvalues.IFTResult getIFTResult () {return myIFTResult;}
    public void setIFTResult(Constantvalues.IFTResult value) {myIFTResult = value;}
    public boolean getIsConcealed () {return myIsConceal;}
    public void setIsConcealed(boolean value){myIsConceal = value;}
    public boolean getIsDummy () {return false;}
    public void setIsDummy(boolean value) {}
    public boolean getIsLeader () {return false;}
    public int getMoraleLevel () {return 9;}
    public Constantvalues.MovementStatus getMovementStatus () {return myMovementStatus;}
    public void setMovementStatus (Constantvalues.MovementStatus value) {myMovementStatus = value;}
    public Constantvalues.OrderStatus getOrderStatus () {return myOrderStatus;}
    public void setOrderStatus(Constantvalues.OrderStatus value) {myOrderStatus = value;}
    public boolean getPinned () {return myPinned;}
    public void setPinned(boolean value) {myPinned = value;}
    public int getQualityStatus () {return myQualityStatus;}
    public void setQualityStatus(int value) {myQualityStatus = value;}
    public int getRandomSelected () {return myRandomSelected;}
    public void setRandomSelected(int value) {myRandomSelected = value;}
    public int getReducesTo () {return 0;}
    public int getSmoke () {return mySmoke;}
    public void setSmoke (int value) {mySmoke = value;}
    public int getSubstitutesTo () {return 0;}
    public int getFirerSan () {return myFirerSAN;}
    public void setFirerSan(int value) {myFirerSAN = value;}
    public Constantvalues.UClass getUnitClass () {return Constantvalues.UClass.ELITE;}
    public Constantvalues.VisibilityStatus getVisibilityStatus () {return myVisibilityStatus;}
    public void setVisibilityStatus(Constantvalues.VisibilityStatus value) {myVisibilityStatus = value;}
    public Constantvalues.PersUnitResult getPersUnitImpact() {return myPersUnitImpact;}
    public void setPersUnitImpact(Constantvalues.PersUnitResult value) {myPersUnitImpact = value;}
    public boolean getSANActivated () {return mySanActivated;}
    public void setSANActivated(boolean value) {mySanActivated = value;}
    public boolean  getIFTResolved() {return myIFTResolved;}
    public void setIFTResolved(boolean value) {myIFTResolved = value;}
    public List<String> getConcealedUnits () {return null;}
    public void setConcealedUnits(List<String>value) {myConcealedList = value;}
    public String getCombatResultsString() {return myCombatResultsString;}
    public void setCombatResultsString(String value) {myCombatResultsString= value;}

    public boolean CRMC(int KNum, int TargSTackLdrdrm, String Resultstring) {

        myTargSTackLdrdrm = TargSTackLdrdrm;
        // good order hero wounds
        myCombatResultsString += myName;
        int wdsevdr = Dieclass.Dieroll(); // takes wound severity dr
        if (wdsevdr >= 5) {  // 5,6 so dies
            myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
            Resultstring += " wounds, then rolls a " + Integer.toString(wdsevdr) + ", fails its Wound Severity dr and ";
        } else {
            myPersUnitImpact = Constantvalues.PersUnitResult.Wounds;
            Resultstring += " wounds, then rolls a " + Integer.toString(wdsevdr) + ", passes its Wound Severity dr and is wounded";
        }
        myCombatResultsString += Resultstring ;
        return true;
    }

    public boolean HasFT() {
        return false;  // to be coded
    }

    public boolean HasWallAdvantage() {
        return false; // to be coded
    }

    public boolean KIA() {
         /*Name:       TargetKIAResult()
        Identifier UC 101
        Preconditions()
        1.	An eligible IFT fire solution has produced a result
        Basic Course
        1.	Use case begins when a KIA result is obtained from the IFT
        2.	The Target drops its support weapons [UC209-TargetDropsSW]
        3.	The Target adds CVP/CP to scenario totals [UC210-TargetAddsCVP]
        4.	Use case ends when the Target dies [UC201-TargetDies]

        Alternate Course A:
        Condition:

        Inheritance: UC 111 - SMCTargetKIAResult
        Condition: Target is an SMC

                    Post conditions
        */
        myCombatResultsString += myName + " is KIA'd: ";
        myPersUnitImpact = VASL.build.module.fullrules.Constantvalues.PersUnitResult.Dies;
        return true;
    }

    public boolean MC(int MCNum, int TargStackLdrdrm) {
            /*'UC
                    '1.	Use case begins when MC result is obtained on the IFT
                    '2.	Determine ML, ldr drm, MC# drm
                    '3. Dice Roll & Snipercheck
                    '4.	Determine Result (2or12, pass, pin, broken, broken > ELR)
                    '5.	If  rolls 2or12 then target takes TargetHOBResult [UC 109]
                    '6.	If Passes then no effect
                    '7.	If Pins then Target Pins [UC206-TargetPins]
                    '8.	If Breaks by <= ELR then Target Breaks [UC205-TargetBreaks] then DMs [UC208-TargetDMs]
                    '9.	If Breaks by > ELR then
                    'Target replaces [UC204-TargetReplaces] (Alternate Course A: UC218-TargetDisrupts, Alternate Course B: UC214-TargetSubstitutes)
                    '	Target DMs [UC208-TargetDMs]*/

        String Resultstring = "";
        myMCNum = MCNum;
        myTargSTackLdrdrm = TargStackLdrdrm;
        String Ldrstring = "";
        if (myTargSTackLdrdrm == 0) {
            Ldrstring = "no ";
        } else {
            Ldrstring = java.lang.Integer.toString(TargStackLdrdrm);
        }

        int ODR = Dieclass.Diceroll();

        myCombatResultsString += myName + " rolls an original " + java.lang.Integer.toString(ODR);
        // sniper
        SANCheck(ODR);
        myCombatResultsString += ": ";
        int CurrentMoraleLevel = 0;
        if (myOrderStatus == Constantvalues.OrderStatus.Broken || myOrderStatus == Constantvalues.OrderStatus.Broken_DM) {
            CurrentMoraleLevel = getBrokenML();
        } else {
            CurrentMoraleLevel = getMoraleLevel();
        }

        if (ODR == 2) {myHOBFlag = false;}  // heros dont take hob
        // FDR
        int FDR = ODR + MCNum;
        // 12
        if (ODR == 12) {    // hero is wounded; takes wound severity DR in place of MC result;
            int wdsevdr = Dieclass.Dieroll(); // takes wound severity dr
            if (wdsevdr >= 5) {  // 5,6 so dies
                myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
                Resultstring = " rolls a 12, then rolls a " + Integer.toString(wdsevdr) + ", fails its Wound Severity dr and ";
            } else {
                myPersUnitImpact = Constantvalues.PersUnitResult.Wounds;
                Resultstring = " rolls a 12, then rolls a " + Integer.toString(wdsevdr) + ", passes its Wound Severity dr and is wounded";
            }
        }
        // MC - hero wounds not breaks
        String drmstring = Ldrstring + " SMC drm and a " + Integer.toString(FDR + TargStackLdrdrm) + " modified dice roll:";
        if (FDR > (CurrentMoraleLevel - TargStackLdrdrm)) { // fails MC
            int wdsevdr = Dieclass.Dieroll(); // takes wound severity dr
            if (wdsevdr >= 5) {  // 5,6 so dies
                myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
                Resultstring = " fails its MC, then rolls a " + Integer.toString(wdsevdr) + ", fails its Wound Severity dr and";
            } else {
                myPersUnitImpact = Constantvalues.PersUnitResult.Wounds;
            Resultstring = " fails its MC, then rolls a " + Integer.toString(wdsevdr) + ", passes its Wound Severity dr and is wounded";
            }
        } else {   // passes MC
            myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
            Resultstring = " passes its MC" ;
            }

        myCombatResultsString += drmstring + Resultstring ;

        return true;

    }




    public boolean NR() {
        /*Name:       TargetNoEResult()
        Identifier UC 105
        Preconditions()
        1.	An eligible IFT fire solution has produced a result
        Basic Course
        1.	Use case begins when NoEffect result is obtained on the IFT
        2.	no effect unless broken then DMs [Alternate Course of Action: UC208-TargetDMs]

        Alternate Course A: UC208-TargetDMs
        Condition: Target is broken

        Inheritance:
        Condition:

        Post conditions (List the state(s) the system can be in when this use case ends)
        */

        myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
        String Resultstring = "survives: no effect";
        myCombatResultsString += myName +  Resultstring;
        return true;
    }

    public boolean PTC(int TargSTackLdrdrm ) {

        myCombatResultsString += myName + " is not subject to PTC: ";
        myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;

        return true;
    }



    public boolean Break() {
        // good order hero wounds
        String Resultstring = null;
        int wdsevdr = Dieclass.Dieroll(); // takes wound severity dr
        if (wdsevdr >= 5) {  // 5,6 so dies
            myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
            Resultstring = " wounds, then rolls a " + Integer.toString(wdsevdr) + ", fails its Wound Severity dr and ";
        } else {
            myPersUnitImpact = Constantvalues.PersUnitResult.Wounds;
            Resultstring = " wounds, then rolls a " + Integer.toString(wdsevdr) + ", passes its Wound Severity dr and is wounded";
        }
        myCombatResultsString += Resultstring ;
        return true;
    }
    public boolean UpdateTargetStatus(PersUniti PassTarget) {
        // this update triggers the Command to update OrderofBattle values plus remote computer
        // the Command also triggers counter actions

        InfantryUnitCommonFunctionsc UpdateTargCF = new InfantryUnitCommonFunctionsc();
        return UpdateTargCF.UpdateTargetStatus(PassTarget);
    }

    public int getLdrDRM () {return -1;}
    public String getCombatResultString () {
        return " "; // to be coded
    }



    public Constantvalues.PersUnitResult SniperImpact(int Sniperdr) {
        if (Sniperdr == 1) {
            // DIES
            return Constantvalues.PersUnitResult.Dies;
        } else if (Sniperdr == 2) {
            // WOUNDS
            return Constantvalues.PersUnitResult.Wounds;
        }
        return Constantvalues.PersUnitResult.NoEffects;
    }


    public Constantvalues.PersUnitResult HOBMC() {return null;}

    private void SANCheck(int ODR) {
        mySanActivated = (ODR == myFirerSAN) ? true: false;
        if (mySanActivated) {myCombatResultsString += " (SAN)";}
    }
}
