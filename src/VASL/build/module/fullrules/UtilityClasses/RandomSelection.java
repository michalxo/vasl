package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

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
        // called by FireResolvle.Resolve
        // performs random selection of X items from Y number of items
        // returns an array of boolean values which indcates which items have been selected
        // can be more than NumberOfItems due to dieroll ties
        DiceC Dice = new DiceC();
        int[] TestArray = new int[NumberofItems - 1]; int[] Test2Array = new int[NumberofItems - 1]; boolean [] Test3Array = new boolean[NumberofItems - 1];
        if (NumberImpacted >= NumberofItems) {
            for (int x = 0; x < NumberofItems; x++) {
                Test3Array[x] = true;
            }
        } else {
            for (int x = 0; x < NumberofItems; x++) {
                //TestArray[x] = Dice.Dieroll; temporary while debugging UNDO
                //'MsgBox("Array item " & x.ToString & " is a " & TestArray(x).ToString)
                Test2Array[x] = TestArray[x];
                Test3Array[x] = false;
            }
            // order array from highest to lowest
            //Array.Sort(TestArray)  temporary while debugging UNDO
            //Array.Reverse(TestArray)
            // determine which ones to switch( - handles dieroll ties
            int Lowestnumbertotake = TestArray[NumberImpacted - 1];
            for (int x = 0; x < NumberofItems; x++) {
                if (Test2Array[x] >= Lowestnumbertotake) {
                    Test3Array[x] = true;
                    //'MsgBox("Array item " & x.ToString & " is a " & Test2Array(x).ToString & " and is true")
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
            case K2:
            case K3:
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
