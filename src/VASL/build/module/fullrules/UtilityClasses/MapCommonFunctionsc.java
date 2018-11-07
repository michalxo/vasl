package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;

public class MapCommonFunctionsc {

    public MapCommonFunctionsc(){

    }

    // this method returns the 6 hexes that are adjacent to a starting hex; null values if adjacent hex is "off-borad"
    public Hex[] getAdjacentHexArray(Hex StartHex) {
        Hex[] Adjacenthexes = new Hex[5];

        for(int x = 0; x < 6; x++) {
            Adjacenthexes[x] = StartHex.getMap().getAdjacentHex(StartHex, x);
        }
        return Adjacenthexes;
    }
    public boolean IsCrestStatus(Constantvalues.AltPos hexposition){
        return (hexposition == Constantvalues.AltPos.CrestStatus0 ||
                hexposition == Constantvalues.AltPos.CrestStatus1 ||
                hexposition == Constantvalues.AltPos.CrestStatus2 ||
                hexposition == Constantvalues.AltPos.CrestStatus3 ||
                hexposition == Constantvalues.AltPos.CrestStatus4 ||
                hexposition == Constantvalues.AltPos.CrestStatus5);

    }
}
