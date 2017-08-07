package VASL.build.module.fullrules.ObjectClasses;


import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;

// Second level interface implemented by personnel unit instances
public  interface FiringPersUniti{

    int getSolID();
    int getType();
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
        /*LinkedList<SuppWeapi> getFiringMGs();    temporary while debugging undo
        void setFiringMGs(LinkedList<SuppWeapi> value);*/
        void RangeModification(double range, double LevelDifference, PersUniti TargetU);
        void PinnedModification();
        void AdvancingFireModification(int Phase);
        void FireVsConcealedModification(PersUniti TargetUnit);
        void FirstFireModification();
        void SprayFireModification(boolean UsingSprayFire);
        void AssaultFireModification(int Phase);
        void AreaFireModification(int FGSize, GameLocation targloc);
        void MGModification();
        void CrestStatusModification(int Targethexnum);
        void ResetCombatFP();
        void UpdateCombatStatus(int NewCombatStatus, int ROFdr);
}
