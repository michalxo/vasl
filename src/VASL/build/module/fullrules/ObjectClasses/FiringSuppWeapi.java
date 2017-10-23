package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;

public interface FiringSuppWeapi {
    Constantvalues.CombatStatus getCombatStatus();
    void setCombatStatus(Constantvalues.CombatStatus value);
    boolean getIsCX();
    boolean  getIsEncirc();
    int getBaseFP();
    int getBaseRange();
    boolean getAssF();
    double getCombatFP();
    int getLdrDRM();
    Constantvalues.Utype getUseHeroOrLeader();
    void setUseHeroOrLeader(int value);
    boolean getIsPinned();
    void setIsPinned(boolean value);
    int getHeroDRM();
    int getMalfunction();
    void setMalfunction(int value);
    int getROF();
    int getBreakdown();
    void setBreakdown(int value);
    boolean getSprayFire();
    void setSprayFire(boolean value);
    boolean getIsMG();

    void RangeModification(double range, double LevelDifference, PersUniti TargetU);
    void PinnedModification();
    void AdvancingFireModification(Constantvalues.Phase CurrentPhase);
    void FireVsConcealedModification(PersUniti TargetUnit);
    void FirstFireModification();
    void SprayFireModification(boolean UsingSprayFire);
    void AssaultFireModification(Constantvalues.Phase CurrentPhase);
    void AreaFireModification(int FGSize, Location targloc);
        //'Sub MGModification()
    void CrestStatusModification(int Targethexnum);
    void ResetCombatFP();
    void UpdateCombatStatus(Constantvalues.CombatStatus NewCombatStatus, int ROFdr);
        //'Function CanStillUseInherentFP(ByVal MGCount As Integer) As Boolean

}
