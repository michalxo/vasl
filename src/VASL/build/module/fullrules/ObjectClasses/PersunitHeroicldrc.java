package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public class PersunitHeroicldrc extends PersunitDecoratorac {

    public PersunitHeroicldrc(PersUniti PassParentUnit) {
        super(PassParentUnit);
    }

    private boolean IsWounded(Constantvalues.FortitudeStatus FortitudeStatus) {
        if (FortitudeStatus == Constantvalues.FortitudeStatus.Wounded) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.HeroicWounded) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.Fan_Wnd) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.Fan_Wnd_Enc) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.HeroicEnc_Wnd) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.Enc_Wnd) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.HeroicFan_Wnd) {return true;}
        if (FortitudeStatus == Constantvalues.FortitudeStatus.HeroicFan_Wnd_Enc) {return true;}
        return false;

    }
}
