package VASL.build.module.fullrules.UtilityClasses;

import java.util.Random;

public class DiceC {
    // Implements System.ComponentModel.INotifyPropertyChanged
    // handles all dice and die rolling functions
    // need to ensure instances are disposed of as this class will be called often
    private int pWhite;
    private int pBlue;
    private int pColored;
    private int pRed;
    private int pYellow;
    private int pGreen;
    private int pBlack;

    public DiceC() {
        // ensure that all values are set to 0
        pWhite =0; pBlue =0; pRed =0; pBlack =0; pYellow =0; pGreen =0; pColored =0;
    }


    public int getWhite() {return pWhite;}
    public int getBlue() {return pBlue;}
    public int getColored() {return pColored;}
    public int getRed() {return pRed;}
    public int getYellow() {return pYellow;}
    public int getGreen() {return pGreen;}
    public int getBlack() {return pBlack;}


    public int Dieroll() {  // CODE NEEDS FIXInG
        // called all over; returns single die value
        //  Initialize the random-number generator.
        final Random rand;
        rand = new Random();

        return 1 + rand.nextInt(6);

    }
    public int Diceroll() {
        // called all over; returns value of 2 dice roll and sets Colored and White values
        pColored = Dieroll();
        pWhite = Dieroll();

            // test dode
            //pWhite=pColored;
        return pColored + pWhite;
    }
    public boolean MultiDiceroll(int Howmany) {
        // called by
        // used in situations that require more than 2 dice rolls (ie random selection
            if (Howmany >6) {return false;}
        pWhite = Dieroll();
        pBlack = Dieroll();
        pRed = Dieroll();
        pGreen = Dieroll();
        pYellow = Dieroll();
        pBlue = Dieroll();
        return true;
    }
}
