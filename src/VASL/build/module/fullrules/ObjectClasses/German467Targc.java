package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.UtilityClasses.DiceC;
import VASL.build.module.fullrules.UtilityClasses.HOBCheckC;
import java.util.List;

public class German467Targc implements TargetPersUniti {
    private int myFirerSAN;
    private int myAttackedbydrm;
    private int myAttackedbyFP;
    // private myCharacterStatus As Integer
    private boolean myELR5;
    private Constantvalues.FortitudeStatus myFortitudeStatus;
    private Constantvalues.IFTResult myIFTResult;
    private boolean myIsConceal;
    // private myIsDummy As Boolean
    private Constantvalues.MovementStatus myMovementStatus;
    private Constantvalues.OrderStatus myOrderStatus;
    private boolean myPinned;
    private int myQualityStatus;
    private int myRandomSelected;
    // private myRoleStatus As Integer
    private int mySmoke;
    // private mySw As Integer
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
        
    public German467Targc(Constantvalues.IFTResult PassIFTResult, int TargStackLdrdrm, int PassFirerSan, int PassAttackedbydrm, int PassAttackedbyFP, boolean PassELR5, boolean PassIsConceal, boolean PassIsDummy, 
            boolean PassPinned, int PassQualityStatus, int PassRandomSelected, int PassSmoke, PersUniti PassUnit) {
        myFirerSAN = PassFirerSan;
        myAttackedbydrm = PassAttackedbydrm;
        myAttackedbyFP = PassAttackedbyFP;
        // myCharacterStatus = PassCharacterStatus
        myELR5 = PassELR5;
        myFortitudeStatus = PassUnit.getbaseunit().getFortitudeStatus();
        myIFTResult = PassIFTResult;
        myIsConceal = PassIsConceal;
        // myIsDummy = PassIsDummy
        myMovementStatus = PassUnit.getbaseunit().getMovementStatus();
        myOrderStatus = PassUnit.getbaseunit().getOrderStatus();
        myPinned = PassPinned;
        myQualityStatus = PassQualityStatus;
        myRandomSelected = PassRandomSelected;
        // myRoleStatus = PassRoleStatus
        mySmoke = PassSmoke;
        // mySw = PassSW
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
    public int getBPV () {return 10;}
    public int getBrokenML () {return 7;}
    public int getMCNumber () {return myMCNum;}
    public void setMCNumber(int value) {myMCNum = value;}
    public int getTargStackLeaderDRM () {return myTargSTackLdrdrm;}
    public void setTargStackLeaderDRM(int value) {myTargSTackLdrdrm = value;}
    public boolean getELR5 () {return myELR5;}
    public void setELR5(boolean value) {myELR5 = value;}
    public Constantvalues.FortitudeStatus getFortitudeStatus () {return myFortitudeStatus;}
    public void setFortitudeStatus(Constantvalues.FortitudeStatus value) {myFortitudeStatus = value;}
    public int getHardensTo () {return 3;}
    public Constantvalues.IFTResult getIFTResult () {return myIFTResult;}
    public void setIFTResult(Constantvalues.IFTResult value) {myIFTResult = value;}
    public boolean getIsConcealed () {return myIsConceal;}
    public void setIsConcealed(boolean value){myIsConceal = value;}
    public boolean getIsDummy () {return false;}
    public void setIsDummy(boolean value) {}
    public boolean getIsLeader () {return false;}
    public int getMoraleLevel () {return 7;}
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
    public int getReducesTo () {return 12;}
    public int getSmoke () {return mySmoke;}
    public void setSmoke (int value) {mySmoke = value;}
    public int getSubstitutesTo () {return 6;}
    public int getFirerSan () {return myFirerSAN;}
    public void setFirerSan(int value) {myFirerSAN = value;}
    public Constantvalues.UClass getUnitClass () {return VASL.build.module.fullrules.Constantvalues.UClass.FIRSTLINE;}
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
        myMCNum = KNum;
        myTargSTackLdrdrm = TargSTackLdrdrm;
        return true;
    }

    public boolean HasFT() {
        return false;  // to be coded
    }

    public boolean HasWallAdvantage() {
        return false; // to be coded
    }

    public boolean KIA() {
        return false; // to be coded
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


            // MISSING CODE SEE RUSSIAN447TARGC
        myMCNum = MCNum;
        myTargSTackLdrdrm = TargStackLdrdrm;
        DiceC Dieclass = new DiceC();
        int ODR = Dieclass.Diceroll();
        // sniper
        setSANActivated(ODR == myFirerSAN ? true: false);
        // 2
        if (ODR == 2) {
            int HOBdrm = 0; //firstline
            HOBCheckC HOB = new HOBCheckC();
            Constantvalues.HOBResult HOBRes = HOB.GetHOBOutcome(HOBdrm);
            switch (HOBRes) {
                case HardensAndHero:
                        setPersUnitImpact(Constantvalues.PersUnitResult.Fanatics);
                        break;
                case HeroCreation:
                        break;
                case Hardens:
                        setPersUnitImpact(Constantvalues.PersUnitResult.Fanatics);
                        break;
                case Berserk:
                        setPersUnitImpact(Constantvalues.PersUnitResult.Berserks);
                        break;
                case Surrenders:
                        setPersUnitImpact(Constantvalues.PersUnitResult.Surrenders);
                        break;
                default:

            }

        }
        int FDR = ODR + MCNum;
        if (FDR == (getMoraleLevel() - getTargStackLeaderDRM())) { // pin result
            setPersUnitImpact(Constantvalues.PersUnitResult.Pins);
        } else if (FDR > (getMoraleLevel() - getTargStackLeaderDRM())) { // breaks
            setPersUnitImpact(Constantvalues.PersUnitResult.Breaks);
        } else if (FDR < (getMoraleLevel() - getTargStackLeaderDRM())) {
            setPersUnitImpact(Constantvalues.PersUnitResult.NoEffects);
        }
        return true;
    }

    public boolean NR() {
        return false; // to be coded
    }



    public boolean PTC(int TargSTackLdrdrm ) {
        return false; // to be coded
    }

    public boolean Break() {
        return false; // to be coded
    }

    public boolean UpdateTargetStatus(PersUniti PassTarget) {
        return false; // to be coded
    }

    public int getLdrDRM () {return 0;}
    public String getCombatResultString () {
        return " "; // to be coded
    }

    public Constantvalues.PersUnitResult SniperImpact(int Sniperdr) {
        if (Sniperdr == 1) {
            if (myOrderStatus == Constantvalues.OrderStatus.Broken_DM || myOrderStatus == Constantvalues.OrderStatus.Broken) {
                return Constantvalues.PersUnitResult.Reduces;
            } else {
                return Constantvalues.PersUnitResult.Breaks;
            }
        } else if (Sniperdr == 2) {
            if (myOrderStatus == Constantvalues.OrderStatus.Broken_DM || myOrderStatus == Constantvalues.OrderStatus.Broken) {
                return Constantvalues.PersUnitResult.DMs;
            } else {
                return Constantvalues.PersUnitResult.Pins;
            }
        }
        return Constantvalues.PersUnitResult.NoEffects;
    }

    public Constantvalues.PersUnitResult HOBMC() {
        return Constantvalues.PersUnitResult.NoEffects; // to be coded
    }


    public boolean getHoBFlag () {return myHOBFlag;}
    public void setHoBFlag(boolean value ) {myHOBFlag = value;}

}
