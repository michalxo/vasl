package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;

public class HOBCheckC {
    public HOBCheckC() {

    }

    public Constantvalues.HOBResult GetHOBOutcome(int HOBdrm) {
        // called by any TargetPersUniti class MC that rolls a 2

        DiceC Dice = new DiceC();
        int HOBODR = Dice.Diceroll();
        int FinalHOBDR = HOBODR + HOBdrm;
        // test code
        FinalHOBDR =3;
        if (FinalHOBDR == 5 || FinalHOBDR == 6) {
            return Constantvalues.HOBResult.HardensAndHero;
        } else if (FinalHOBDR < 5) {
            return Constantvalues.HOBResult.HeroCreation;
        } else if (FinalHOBDR == 7 || FinalHOBDR == 8) {
            return Constantvalues.HOBResult.Hardens;
        } else if (FinalHOBDR == 9 || FinalHOBDR == 10 || FinalHOBDR == 11) {
            return Constantvalues.HOBResult.Berserk;
        } else if (FinalHOBDR > 11) {
            return Constantvalues.HOBResult.Surrenders;
        }
        return null;
    }
}
