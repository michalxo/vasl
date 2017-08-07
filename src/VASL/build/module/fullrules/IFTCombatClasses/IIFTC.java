package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.IFTMods;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.LOSClasses.LOSSolution;
import VASL.build.module.fullrules.LOSClasses.TempSolution;
import VASL.build.module.fullrules.LOSClasses.ThreadedLOSCheckC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.counters.GamePiece;

import java.util.LinkedList;
import java.util.List;

public interface IIFTC {
    Constantvalues.Nationality getFirerSide();
    Constantvalues.Nationality getTargetSide();
    int getFirerSan();
    int getTargetSan();
    int getFirerhex();
    void setFirerhex(int value);
    int getFirerloc();
    void setFirerloc(int value);
    Constantvalues.AltPos getFirerpos();
    void setFirerpos(Constantvalues.AltPos value);
    int getTargethex();
    void setTargethex(int value);
    int getTargetloc();
    void setTargetloc(int value);
    boolean getNeedtoResumeResolution();

    boolean InitializeIFTCombat();
    void FirePhasePreparation();
    void ClickedOnNewParticipants(Hex ClickedHex, LinkedList<GamePiece> SelectedUnits);
}

