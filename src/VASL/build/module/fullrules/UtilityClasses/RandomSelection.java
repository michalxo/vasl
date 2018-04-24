package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;

public class RandomSelection {
    // called by FireResolve.Resolve
    // determines if random selection is required (more units than # impacted)
    // and conducts random selection

    public RandomSelection (){}

    public boolean CheckRndSel(int NumberImpacted, int NumberofItems) {
        // called by FireResolve.Resolve
        // determines if total items  > items impacted and
        // therefore random selection is required
        return (NumberofItems > NumberImpacted ? true: false);
    }
    public boolean[] RandomSel(int NumberImpacted, int NumberofItems) {
        // called by
        // performs random selection of X items from Y number of items - for SWbreakdown and similar, NumberImpacted should be 1
        // returns an array of boolean values which indicates which items have been selected
        // can be more than NumberImpacted due to dieroll ties
        DiceC Dice = new DiceC();
        int[] TestArray = new int[NumberofItems];  boolean [] Test3Array = new boolean[NumberofItems];
        if (NumberImpacted >= NumberofItems) {
            for (int x = 0; x < NumberofItems; x++) {
                Test3Array[x] = true;
            }
        } else {
            for (int x = 0; x < NumberofItems; x++) {
                TestArray[x] = Dice.Dieroll();
                Test3Array[x] = false;
            }
            // determine which ones to select - handles dieroll ties
            int Lowestnumbertotake = 0;
            for (int x = 0; x < NumberofItems; x++) {
                if (TestArray[x] > Lowestnumbertotake){Lowestnumbertotake = TestArray[x];}
            }
            for (int x = 0; x < NumberofItems; x++) {
                if (TestArray[x] == Lowestnumbertotake) {
                    Test3Array[x] = true;
                }
            }
        }
        return Test3Array;
    }
    public int DetermineImpact(Constantvalues.IFTResult iftresult) {
        // called by FireResolve.Resolve        // Determines number of items impacted

        // is there a better way?
        switch(iftresult) {
            case KIA7:
                return 7;
            case KIA6:
                return 6;
            case KIA5:
                return 5;
            case KIA4:
                return 4;
            case KIA3:
                return 3;
            case KIA2:
                return 2;
            case KIA1: case K1: case K2: case K3: case K4:
                return 1;
            default:
                return 0;
        }
    }
    public Constantvalues.IFTResult DeterminePrimResult(Constantvalues.IFTResult ifttableresult) {
        switch(ifttableresult) {
            case KIA7:
            case KIA6:
            case KIA5:
            case KIA4:
            case KIA3:
            case KIA2:
            case KIA1:
                return Constantvalues.IFTResult.KIA;
            case K4:
                return ifttableresult;
            case K2:
                return ifttableresult;
            case K3:
                return ifttableresult;
            case K1:
                return ifttableresult;
            default: // should never be called as no results below K1 shoudl be passed in
                return Constantvalues.IFTResult.NR;
        }
    }
    public Constantvalues.IFTResult DetermineSecondaryResult(Constantvalues.IFTResult ifttableresult) {
        switch(ifttableresult){
            case KIA7: case KIA6: case KIA5: case KIA4: case KIA3: case KIA2: case KIA1:
                return Constantvalues.IFTResult.Broken;
            case K4:
                return Constantvalues.IFTResult.MC4;

            case K3:
                return Constantvalues.IFTResult.MC3;

            case K2:
                return Constantvalues.IFTResult.MC2;

            case K1:
                return Constantvalues.IFTResult.MC1;

            default:
                return Constantvalues.IFTResult.NR;
        }
    }
}
