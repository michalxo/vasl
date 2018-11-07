package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.UtilityClasses.*;
import VASSAL.command.Command;

import java.util.List;

public class German237Targc implements TargetPersUniti {
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

    public boolean getHoBFlag() {
        return myHOBFlag;
    }

    public void setHoBFlag(boolean value) {
        myHOBFlag = value;
    }

    //constructor
    public German237Targc(Constantvalues.IFTResult PassIFTResult, int TargStackLdrdrm, int PassFirerSan, int PassAttackedbydrm, int PassAttackedbyFP, boolean PassELR5, boolean PassIsConceal, boolean PassIsDummy,
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


    public int getAttackedbydrm() {
        return myAttackedbydrm;
    }

    public void setAttackedbydrm(int value) {
        myAttackedbydrm = value;
    }

    public int getAttackedbyFP() {
        return myAttackedbyFP;
    }

    public void setAttackedbyFP(int value) {
        myAttackedbyFP = value;
    }

    public int getBPV() {
        return 5;
    }

    public int getBrokenML() {
        return 6;
    }

    public int getMCNumber() {
        return myMCNum;
    }

    public void setMCNumber(int value) {
        myMCNum = value;
    }

    public int getTargStackLeaderDRM() {
        return myTargSTackLdrdrm;
    }

    public void setTargStackLeaderDRM(int value) {
        myTargSTackLdrdrm = value;
    }

    public boolean getELR5() {
        return myELR5;
    }

    public void setELR5(boolean value) {
        myELR5 = value;
    }

    public Constantvalues.FortitudeStatus getFortitudeStatus() {
        return myFortitudeStatus;
    }

    public void setFortitudeStatus(Constantvalues.FortitudeStatus value) {
        myFortitudeStatus = value;
    }

    public int getHardensTo() {
        return 12;
    }

    public Constantvalues.IFTResult getIFTResult() {
        return myIFTResult;
    }

    public void setIFTResult(Constantvalues.IFTResult value) {
        myIFTResult = value;
    }

    public boolean getIsConcealed() {
        return myIsConceal;
    }

    public void setIsConcealed(boolean value) {
        myIsConceal = value;
    }

    public boolean getIsDummy() {
        return false;
    }

    public void setIsDummy(boolean value) {
    }

    public boolean getIsLeader() {
        return false;
    }

    public int getMoraleLevel() {
        return 7;
    }

    public Constantvalues.MovementStatus getMovementStatus() {
        return myMovementStatus;
    }

    public void setMovementStatus(Constantvalues.MovementStatus value) {
        myMovementStatus = value;
    }

    public Constantvalues.OrderStatus getOrderStatus() {
        return myOrderStatus;
    }

    public void setOrderStatus(Constantvalues.OrderStatus value) {
        myOrderStatus = value;
    }

    public boolean getPinned() {
        return myPinned;
    }

    public void setPinned(boolean value) {
        myPinned = value;
    }

    public int getQualityStatus() {
        return myQualityStatus;
    }

    public void setQualityStatus(int value) {
        myQualityStatus = value;
    }

    public int getRandomSelected() {
        return myRandomSelected;
    }

    public void setRandomSelected(int value) {
        myRandomSelected = value;
    }

    public int getReducesTo() {
        return 0;
    }

    public int getSmoke() {
        return mySmoke;
    }

    public void setSmoke(int value) {
        mySmoke = value;
    }

    public int getSubstitutesTo() {
        return 14;
    }

    public int getFirerSan() {
        return myFirerSAN;
    }

    public void setFirerSan(int value) {
        myFirerSAN = value;
    }

    public Constantvalues.UClass getUnitClass() {
        return Constantvalues.UClass.SECONDLINE;
    }

    public Constantvalues.VisibilityStatus getVisibilityStatus() {
        return myVisibilityStatus;
    }

    public void setVisibilityStatus(Constantvalues.VisibilityStatus value) {
        myVisibilityStatus = value;
    }

    public Constantvalues.PersUnitResult getPersUnitImpact() {
        return myPersUnitImpact;
    }

    public void setPersUnitImpact(Constantvalues.PersUnitResult value) {
        myPersUnitImpact = value;
    }

    public boolean getSANActivated() {
        return mySanActivated;
    }

    public void setSANActivated(boolean value) {
        mySanActivated = value;
    }

    public boolean getIFTResolved() {
        return myIFTResolved;
    }

    public void setIFTResolved(boolean value) {
        myIFTResolved = value;
    }

    public List<String> getConcealedUnits() {
        return null;
    }

    public void setConcealedUnits(List<String> value) {
        myConcealedList = value;
    }

    public String getCombatResultsString() {
        return myCombatResultsString;
    }

    public void setCombatResultsString(String value) {
        myCombatResultsString = value;
    }

    public boolean CRMC(int KNum, int TargSTackLdrdrm, String Resultstring) {
        myMCNum = KNum;
        myTargSTackLdrdrm = TargSTackLdrdrm;
        myPersUnitImpact = VASL.build.module.fullrules.Constantvalues.PersUnitResult.Dies;
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

        */

        myPersUnitImpact = VASL.build.module.fullrules.Constantvalues.PersUnitResult.Dies;
        myCombatResultsString += myName + " is KIA'd: ";
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
        DiceC Dieclass = new DiceC();
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

        if (ODR == 2) {
            myHOBFlag = true;
        }
        // FDR
        int FDR = ODR + MCNum;
        // 12
        if (ODR == 12) {
            myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
            Resultstring = " and suffers a Casualty MC with " ;
            //return true;
        } else {
            // MC
            if (myOrderStatus == Constantvalues.OrderStatus.Broken || myOrderStatus == Constantvalues.OrderStatus.Broken_DM) {
                if (FDR > (CurrentMoraleLevel - TargStackLdrdrm)) {  // fails MC
                    myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
                    Resultstring = " and is eliminated with " ;
                } else if (FDR <= (CurrentMoraleLevel - TargStackLdrdrm)) {  // passes MC
                    if (myOrderStatus == Constantvalues.OrderStatus.Broken) { // already broken so DMs
                        myPersUnitImpact = Constantvalues.PersUnitResult.DMs;
                        Resultstring = " and is DM'd with " ;
                    } else {                                                  // already DM so no effect
                        myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
                        Resultstring = " and passes a MC with " ;
                    }
                }
            } else {    // unit is good order
                if (FDR == (CurrentMoraleLevel - TargStackLdrdrm)) { // 'pin result
                    myPersUnitImpact = Constantvalues.PersUnitResult.Pins;
                } else if (FDR > (CurrentMoraleLevel - TargStackLdrdrm)) { // fails MC
                    if (FDR > (CurrentMoraleLevel - TargStackLdrdrm + myELR)) {   // ELR failure
                        myPersUnitImpact = Constantvalues.PersUnitResult.ReplacesDMs;
                        Resultstring = " and suffers a MC ELR failure with ";
                    } else {                                                     // no ELR failure
                        myPersUnitImpact = Constantvalues.PersUnitResult.Breaks;
                        Resultstring = " and suffers a MC failure with ";
                    }
                } else if (FDR < (CurrentMoraleLevel - TargStackLdrdrm)) {   // passes MC
                    myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
                    Resultstring = " and passes a MC with ";
                }
            }
        }
        myCombatResultsString += Resultstring + Ldrstring + "SMC drm and a " + Integer.toString(FDR + TargStackLdrdrm) + " modified dice roll:";
        if (myPersUnitImpact != Constantvalues.PersUnitResult.NoEffects) {
            return true;
        } else {
            return false;
        }

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
        String Resultstring = "";
        if (myOrderStatus == Constantvalues.OrderStatus.Broken) {  // DMs - NEED TO ADD TEST FOR SUFFICIENT FP
            myPersUnitImpact = Constantvalues.PersUnitResult.DMs;
            Resultstring = " is DM'd: no effect ";
        } else {
            myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
            Resultstring = "survives: no effect";
        }
        myCombatResultsString += myName +  Resultstring;
        return true;
    }



    public boolean PTC(int TargStackLdrdrm ) {
        /*  Name:       TargetPTCResult()
        Identifier UC 104
        Preconditions()
        1.	An eligible IFT fire solution has produced a result
        Basic Course
        1.	Use case begins when PTC result is obtained on the IFT
        2.	Determine ML, ldr drm, other TC drm
        3:          .Dice Roll & Snipercheck
        4.	Determine Result ( pass, fail)
        5.
        6.	If Passes then no effect unless broken then DMs [Alternate Course of Action: UC208-TargetDMs]
        7.	If Pins then Target Pins [UC206-TargetPins] unless broken then DMs [Alternate Course of Action: UC208-TargetDMs]

        Alternate Course A: UC208-TargetDMs
        Condition:  Target is Broken

        Inheritance:
        Condition:

        Post conditions (List the state(s) the system can be in when this use case ends)
        */
        myTargSTackLdrdrm = TargStackLdrdrm;
        myCombatResultsString += myName;
        String Ldrstring = "";
        if (myTargSTackLdrdrm == 0) {
            Ldrstring = "no ";
        } else {
            Ldrstring = java.lang.Integer.toString(TargStackLdrdrm);
        }

        if (myOrderStatus == Constantvalues.OrderStatus.GoodOrder) {  //  only GoodOrder units can take IFT PTCs
            DiceC Dieclass = new DiceC();
            int ODR = Dieclass.Diceroll();

            myCombatResultsString += " rolls a " + java.lang.Integer.toString(ODR);
            // sniper
            SANCheck(ODR);

            if (ODR > (getMoraleLevel() - TargStackLdrdrm)) {  // fails PTC
                myPersUnitImpact = Constantvalues.PersUnitResult.Pins;
                myCombatResultsString += "and fails PTC with ";
            } else {                                         // passes PTC
                myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
                myCombatResultsString += "and passes PTC with ";
            }
            myCombatResultsString += Ldrstring + "SMC drm and a " + Integer.toString(ODR + TargStackLdrdrm) + " modified dice roll:";
        } else if (myOrderStatus == Constantvalues.OrderStatus.Broken) {  // broken unit is DM'd
            myPersUnitImpact = Constantvalues.PersUnitResult.DMs;
            myCombatResultsString += " DM's due to PTC:";
        } else {
            myPersUnitImpact = Constantvalues.PersUnitResult.NoEffects;
            myCombatResultsString += " is not effected by PTC: ";
        }
        return true;
    }

    public boolean Break() {
             /*Name:       TargetKIABreak()
           Identifier UC 107
           Preconditions()
           1.	An eligible IFT fire solution has produced a result
           Basic Course
           1.	Use case begins when Randon Selection on a #KIA result produces a break result for a unit
           2.	Breaks and is DM?d (UC205-TargetBreaks and UC208-TargetDMs) unless broken then Reduces [Alternate Course of Action: UC203-TargetReduces] or broken and HS/Crew then Dies [Alternate Course of Action: UC201-TargetDies].

           Alternate Course A: UC203-TargetReduces
           Condition: Target is broken
           Alternate Course B: UC201-TargetDies
           Condition: Target is broken HS or Crew

           Inheritance:
           Condition:

           Post conditions (List the state(s) the system can be in when this use case ends)
           */

        if (myOrderStatus == Constantvalues.OrderStatus.Broken || myOrderStatus == Constantvalues.OrderStatus.Broken_DM) {  // unit is already broken so is reduced
            myPersUnitImpact = Constantvalues.PersUnitResult.Dies;
        } else {                                                                                                            // good order unit is broken and DM
            myPersUnitImpact = Constantvalues.PersUnitResult.DMs;
        }
        return true;
    }

    public boolean UpdateTargetStatus(PersUniti PassTarget) {
        // this update triggers the Command to update OrderofBattle values plus remote computer
        // the Command also triggers counter actions

        InfantryUnitCommonFunctionsc UpdateTargCF = new InfantryUnitCommonFunctionsc();
        return UpdateTargCF.UpdateTargetStatus(PassTarget);
    }

    public int getLdrDRM () {return 0;}
    public String getCombatResultString () {
        return myCombatResultsString;
    }

    public Constantvalues.PersUnitResult SniperImpact(int Sniperdr) {
        if (Sniperdr == 1) {
            if (myOrderStatus == Constantvalues.OrderStatus.Broken_DM || myOrderStatus == Constantvalues.OrderStatus.Broken) {
                return Constantvalues.PersUnitResult.Dies;
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
        int HOBdrm = 0;
        if (myOrderStatus == Constantvalues.OrderStatus.Broken || myOrderStatus == Constantvalues.OrderStatus.Broken_DM) {HOBdrm += 1;}  // broken
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
                setPersUnitImpact((Constantvalues.PersUnitResult.NoEffects));
        }
        return getPersUnitImpact();
    }

    private void SANCheck(int ODR) {
        mySanActivated = (ODR == myFirerSAN) ? true: false;
        if (mySanActivated) {myCombatResultsString += " (SAN)";}
    }
}
