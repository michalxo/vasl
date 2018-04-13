package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.UtilityClasses.DiceC;

import java.util.LinkedList;

public interface IFTResulti {
    LinkedList<PersUniti> GetIFTResult(LinkedList<PersUniti> TargGroup, DiceC DR, LinkedList<PersUniti> FireGroup) ;
        //Function SWBrkDwnCowerAdj(ByVal TargGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti), ByVal DR As UtilClassLibrary.ASLXNA.DiceC, ByVal FireGroup As List(Of objectclasslibrary.aslxna.persuniti)) As List(Of objectclasslibrary.aslxna.persuniti)
    int getROFdr();
    void setROFdr(int value);
    boolean getBreakdown12();
    void setBreakdown12(boolean value);
        //Property SniperDR As Integer
        Constantvalues.HitLocation getHitLocation();
    void setHitLocation(Constantvalues.HitLocation value);
    boolean getCowers();
    void setCowers(boolean value);
    LinkedList<TargetType> getFPdrmCombos();

    LinkedList<PersUniti> getSWBrkDwn(LinkedList<PersUniti> TargGroup, DiceC DR, LinkedList<PersUniti> FireGroup) ;
    LinkedList<PersUniti> getCoweringAdj(LinkedList<PersUniti> TargGroup, DiceC DR, LinkedList<PersUniti> FireGroup) ;
}
