package VASL.build.module.fullrules.UtilityClasses;

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
        //randomize();
        // Generate random value between 1 and 6.
        int mydr = 0;  //(int)((int)(6 * Rnd()) + 1);
        return mydr;
    }
    public int Diceroll() {
        // called all over; returns value of 2 dice roll and sets Colored and White values
        pColored = Dieroll();
        //Thread.Sleep(1500)
        pWhite = Dieroll();

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
