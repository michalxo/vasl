package VASL.build.module.fullrules.IFTCombatClasses;

public class TargetType {

    private int myAttackedbyFP;
    private int myAttackedbyDRM;

    public TargetType(int PassAttackedbyFP, int Passattackedbydrm) {

        myAttackedbyFP = PassAttackedbyFP;
        myAttackedbyDRM = Passattackedbydrm;
    }

    public int getAttackedbyFP() {
        return myAttackedbyFP;
    }

    public int getattackedbydrm() {
        return myAttackedbyDRM;
    }
}