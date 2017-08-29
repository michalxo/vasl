package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public class SmokeHolder {
    private Constantvalues.VisHind psmokevalue;
    private int psmokebase;

    public SmokeHolder(Constantvalues.VisHind PassSmoke, int PassBaselevel) {
        psmokevalue = PassSmoke;
        psmokebase = PassBaselevel;
    }
    public Constantvalues.VisHind getSmoke(){return psmokevalue;}
    public int getSmokeBaseLevel() {return psmokebase;}
}
