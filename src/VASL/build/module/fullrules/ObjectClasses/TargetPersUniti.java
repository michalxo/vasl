package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

import java.util.List;

public interface TargetPersUniti {
        int getBPV();
        int  getBrokenML();
        boolean getELR5();
        void setELR5(boolean value);
        Constantvalues.FortitudeStatus getFortitudeStatus();
        void setFortitudeStatus(Constantvalues.FortitudeStatus value);
        int getHardensTo();
        int getMoraleLevel();
        Constantvalues.MovementStatus getMovementStatus();
        void setMovementStatus(Constantvalues.MovementStatus value);
        Constantvalues.OrderStatus getOrderStatus();
        void setOrderStatus(Constantvalues.OrderStatus value);
        boolean getPinned();
        void setPinned(boolean value);
        Constantvalues.PersUnitResult  getPersUnitImpact();
        void setPersUnitImpact(Constantvalues.PersUnitResult value);
        int getQualityStatus();
        void setQualityStatus(int value);
        int getReducesTo();
        boolean getSANActivated();
        void setSANActivated(boolean value);
        int getSmoke();
        void setSmoke(int value);
        int getSubstitutesTo();
        int getFirerSan();
        void setFirerSan(int value);
        Constantvalues.UClass getUnitClass();
        Constantvalues.VisibilityStatus getVisibilityStatus();
        void setVisibilityStatus(Constantvalues.VisibilityStatus value);
        int getAttackedbydrm();
        void setAttackedbydrm(int value);
        int  getAttackedbyFP();
        void setAttackedbyFP(int value);
        Constantvalues.IFTResult getIFTResult();
        void setIFTResult(Constantvalues.IFTResult value);
        int getRandomSelected();
        void setRandomSelected(int value);
        boolean getIsLeader();
        boolean getIsConcealed();
        void setIsConcealed(boolean value);
        boolean getIsDummy();
        void setIsDummy(boolean value);
        boolean getIFTResolved();
        void setIFTResolved(boolean value);
        List<String> getConcealedUnits();
        void setConcealedUnits(List<String> value);
        int getLdrDRM();
        String getCombatResultsString();
        void setCombatResultsString(String value);
        int getMCNumber();
        void setMCNumber(int value);
        int getTargStackLeaderDRM();
        void setTargStackLeaderDRM(int value);
        boolean getHoBFlag();
        void setHoBFlag(boolean value);


        boolean HasFT();
        boolean HasWallAdvantage();
        //int SetTexture();
        boolean CRMC(int KNum, int TargSTackLdrdrm, String Resultstring);
        boolean KIA();
        boolean MC(int MCNum, int TargSTackLdrdrm);
        boolean NR();
        boolean PTC(int TargSTackLdrdrm);
        boolean Break();
        boolean UpdateTargetStatus(PersUniti PassTarget);
        Constantvalues.PersUnitResult SniperImpact(int Sniperdr);
        Constantvalues.PersUnitResult HOBMC();
}
