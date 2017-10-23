package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.LOSResult;
import VASL.LOS.Map.Location;
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
    Hex getFirerhex();
    void setFirerhex(Hex value);
    Location getFirerloc();
    void setFirerloc(Location value);
    Constantvalues.AltPos getFirerpos();
    void setFirerpos(Constantvalues.AltPos value);
    Hex getTargethex();
    void setTargethex(Hex value);
    Location getTargetloc();
    void setTargetloc(Location value);
    boolean getNeedtoResumeResolution();
    LOSResult getLOSResult();

    LinkedList<PersUniti> TempTarget = new LinkedList<PersUniti>();
    LinkedList<PersUniti> TargGroup = new LinkedList<PersUniti>();

    boolean InitializeIFTCombat();
    void FirePhasePreparation();
    void ClickedOnNewParticipants(Hex ClickedHex, LinkedList<GamePiece> SelectedUnits);
    void ProcessIFTCombat();
    void ClearCurrentIFT();
}

