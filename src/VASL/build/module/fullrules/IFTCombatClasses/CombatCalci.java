package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.DataClasses.IFTMods;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.util.LinkedList;

public interface CombatCalci {
    int CalcCombatFPandDRM(LinkedList<PersUniti> PassFireGroupToUse, LinkedList<PersUniti> PassTargetGroupToUse, Scenario Scendet, int Usingsol);
    LinkedList<IFTMods> getFinalDRMList();
}
