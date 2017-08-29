package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.util.LinkedList;

public interface CombatResi {
    boolean getFirerSANActivated();
    void setFirerSANActivated(boolean value);
    boolean NeedToResume();
    boolean NeedAPopup();
    LinkedList<PersUniti> getNewTargets();
    LinkedList<PersUniti> getNewFirings();
    // LinkedList<MenuItemObjectholderinterface> PopupItems;    temporary while debugging UNDO
    void ResolveCombat(LinkedList<PersUniti> TargGroup, LinkedList<TargetType> FPdrmCombos, int FirerSAN, int ScenID);
    void ResumeResolution();
    void ResumeSurrenderResolution(int GuardSelected, int TargID);
        /*'called by IFT.ProcessIFTCombat
                'at this stage, each unit has been assigned its indvidual IFT result - random selection for KIA and K is done
                'still need to handle LLMC/LLTC*/
}
