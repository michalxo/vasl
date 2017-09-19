package VASL.build.module.fullrules.PhaseClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.IFTC;
import VASSAL.build.GameModule;

// concrete class implementing iPhaseMVC
public class PrepPhaseMVC implements iPhaseMVC {
    private AutomatedPhaseActions AutoActions;
    private Constantvalues.Phase CurrentPhasevalue;
    private int CurrentTurnvalue;
    private Constantvalues.WhoCanDo CurrentPlayerTurnvalue;
    private boolean IsFinishedvalue;
    private PhaseObserverInterface PhaseObserver;

    public PrepPhaseMVC(int ScenID) {
        ScenarioC scen = ScenarioC.getInstance();
        CurrentPlayerTurnvalue =  scen.getPlayerTurn();
        CurrentTurnvalue =  scen.getCurrentTurn();
        IsFinishedvalue = false;
        CurrentPhasevalue = Constantvalues.Phase.PrepFire;
        AutoActions = new AutomatedPhaseActions(CurrentPlayerTurnvalue, ScenID);
    }

    public boolean JoinPhase() {
        // Called by EnterIntonewPhase and PhaseChange, Open Existing Scenario and Open Campaign routine
        // does not run routine called the first time a phase is entered, that is done in EnternewPhase
        ScenarioC scen = ScenarioC.getInstance();
        try {
            scen.IFT.FirePhasePreparation();
        } catch (Exception ex) {
            scen.IFT = new IFTC(scen.getScenID());
            scen.IFT.FirePhasePreparation();
        }
        return true;
    }

    public void EnterIntoNewPhase() {
        // called by Scenario.Phasechange and Scenario Startup routines
        // when entering for the first time
        // If coming back from previous phase, Phasechange sends to Joinphase instead

        AutoActions.SmokeUpdate();
        AutoActions.VehicleDustCHeck();
        JoinPhase();
        // ReportEvent.LognewPhase()
        GameModule.getGameModule().getChatter().send("Now in Prep Fire Phase");
    }

    public void LeaveCurrentPhase() {
        // called by Quit menu item and ExitfromPhase
        // at quit, leave current phase

        // Do routines that need to be each time you stop during a phase
        // could handle some parts of cleanup before Exitfromphase

        // Do saving and updating routines that are required everytime a phase is left
        //UpdateOBUnitDatabase() : UpdateOBSWDatabase() : UpdateOBGunDatabase()
        //UpdateMapTerrainDatabase: WriteActiveScenariotoDatabase()
    }


    public void ExitFromPhaseBack() {

    }



    public void ExitFromPhaseForward() {
        LeaveCurrentPhase();
        // ReportEvent.LogPhaseHistory()
    }
    public void RegisterObserver(PhaseObserverInterface PhaseObserverC) {
        // called by
        // the Observer is the view as a whole NOT the phase
        PhaseObserver = PhaseObserverC;
    }

    public void RemoveObserver() {
        // no implementation required so far
    }

    private void NotifyObservers() {
        // called by SetLOSFPdrmValues
        // calls UpdateView on all observers
        PhaseObserver.UpdateView();
    }

    public Constantvalues.Phase getCurrentPhase () {return CurrentPhasevalue;}
    public Constantvalues.WhoCanDo getCurrentPlayerTurn() {return CurrentPlayerTurnvalue;}
    public int getCurrentTurn() {return CurrentTurnvalue;}
    public boolean IsFinished() {return IsFinishedvalue;}
}
