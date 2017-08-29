package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;

public class CombatUtil {

    public int FPRoundingDown(double BasicFP) {
        // called by CalcCombatFPandDRM
        // rounds down excess FP
        if (BasicFP <=1) {return 0;}
        if (BasicFP >=1 && BasicFP <2) {return 1;}
        if (BasicFP >=2 && BasicFP <4) {return 2;}
        if (BasicFP >=4 && BasicFP <6) {return 4;}
        if (BasicFP >=6 && BasicFP <8) {return 6;}
        if (BasicFP >=8 && BasicFP <12) {return 8;}
        if (BasicFP >=12 && BasicFP <16) {return 12;}
        if (BasicFP >=16 && BasicFP <20) {return 16;}
        if (BasicFP >=20 && BasicFP <24) {return 20;}
        if (BasicFP >=24 && BasicFP <30) {return 24;}
        if (BasicFP >=30 && BasicFP <36) {return 30;}
        if (BasicFP >=36 ) {
            return 36;
        } else {
            return 0;
        }
    }
    
    public String IFTResultstring(Constantvalues.IFTResult iftresult ) {
        switch (iftresult) {
            case KIA: case KIA1: case KIA2: case KIA3: case KIA4: case KIA5: case KIA6: case KIA7:
                return "KIA";
            case K4:
                return "K/4";
            case K3:
                return "K/3";
            case K2:
                return "K/2";
            case K1:
                return "K/1";
            case MC4:
                return "MC4";
            case MC3:
                return "MC3";
            case MC2:
                return "MC2";
            case MC1:
                return "MC1";
            case NMC:
                return "NMC";
            case Broken:
                return "Break";
            case PTC:
                return "PTC";
            case NR:
                return "NR";
            default:
                return "NR";
        }
    }
}
