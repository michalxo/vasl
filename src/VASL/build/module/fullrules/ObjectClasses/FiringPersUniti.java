package VASL.build.module.fullrules.ObjectClasses;


import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

import java.util.LinkedList;

// Second level interface implemented by personnel unit instances
public  interface FiringPersUniti{

    int getSolID();
    Constantvalues.Utype getType();
    int getLOBLink();
    Constantvalues.CombatStatus getCombatStatus();
    void setCombatStatus(Constantvalues.CombatStatus value);
        boolean getIsCX();
        boolean getIsEncirc();
        int getBaseFP();
        int getBaseRange();
        boolean getAssF();
        double getCombatFP();
        int getLdrDRM();
        Constantvalues.Utype getUseHeroOrLeader();
        void setUseHeroOrLeader(Constantvalues.Utype value);
        boolean getIsPinned();
        void setIsPinned(boolean value);
        int getHeroDRM();
        boolean getHasMG();
        boolean getUsingInherentFP();
        void setUsingInherentFP(boolean value);
        boolean getUsingfirstMG();
        void setUsingfirstMG(boolean value);
        boolean getUsingsecondMG();
        void setUsingsecondMG(boolean value);
        LinkedList<SuppWeapi> FiringMGs = new LinkedList<SuppWeapi>();

        void RangeModification(double range, double LevelDifference, PersUniti TargetU);
        void PinnedModification();
        void AdvancingFireModification(Constantvalues.Phase phase);
        void FireVsConcealedModification(PersUniti TargetUnit);
        void FirstFireModification();
        void SprayFireModification(boolean UsingSprayFire);
        void AssaultFireModification(Constantvalues.Phase phase);
        void AreaFireModification(int FGSize, Location targloc);
        void MGModification();
        void CrestStatusModification(int Targethexnum);
        void ResetCombatFP();
        void UpdateCombatStatus(Constantvalues.CombatStatus NewCombatStatus, int ROFdr);
}
