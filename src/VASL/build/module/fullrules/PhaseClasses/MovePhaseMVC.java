package VASL.build.module.fullrules.PhaseClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;
// concrete class implementing iPhaseMVC
public class MovePhaseMVC implements iPhaseMVC {


    private AutomatedPhaseActions AutoActions;
    private Constantvalues.Phase CurrentPhasevalue;
    private int CurrentTurnvalue;
    private Constantvalues.WhoCanDo CurrentPlayerTurnvalue;
    private boolean IsFinishedvalue;
    private PhaseObserverInterface PhaseObserver;

    public MovePhaseMVC(int ScenID) {
        DataC Linqdata = DataC.GetInstance();    // use null values when sure instance already exists
        Scenario Scendet = Linqdata.GetScenarioData(ScenID);
        CurrentPlayerTurnvalue = Scendet.getPTURN();
        CurrentTurnvalue = Scendet.getCURRENTTURN();
        IsFinishedvalue = false;
        CurrentPhasevalue = Constantvalues.Phase.Movement;
        AutoActions = new AutomatedPhaseActions(CurrentPlayerTurnvalue, ScenID);
    }

    public boolean JoinPhase() {
            /*' Called by EnterIntonewPhase and PhaseChange, Gameform.buSavScen
                    ' Open Existing Scenario menu item and Open Campaign routine
                    ' does not run routine called the first time a phase is entered, that is done in EnternewPhase

                    'Try
                    '    Game.Scenario.IFT.FirePhasePreparation()
                    'Catch ex As Exception
                    '    Game.Scenario.IFT = new IFTC
                    '    Game.Scenario.IFT.FirePhasePreparation()
                    'End Try
                    'DoMove = new MakeMoveC*/
        return true;
    }

    public void EnterIntoNewPhase() {
        // called by Scenario.Phasechange and Scenario Startup routines
        // when entering for the first time
        // If coming back from previous phase, Phasechange sends to Joinphase instead

        JoinPhase();
        // ReportEvent.LognewPhase()
    }

    public void LeaveCurrentPhase() {
        /*' called by Quit menu item and ExitfromPhase
        ' at quit, leave current phase

        ' Do routines that need to be each time you stop during a phase
        ' could handle some parts of cleanup before Exitfromphase

        ' Do saving and updating routines that are required everytime a phase is left
        'UpdateOBUnitDatabase() : UpdateOBSWDatabase() : UpdateOBGunDatabase()
        'UpdateMapTerrainDatabase: WriteActiveScenariotoDatabase()*/
    }


    public void ExitFromPhaseBack() {

    }

    public void ExitFromPhaseForward() {
        ClearInfantrySmoke();
        AutoActions.ClearTempStatus("MovementStatus.Moved");
        LeaveCurrentPhase();
        // ReportEvent.LogPhaseHistory()
    }

    private void ClearInfantrySmoke() {
        // called by Me.ExitfromPhaseForward
        // removes infantry smoke counters at end of Movement
        // this is an automatic routine that is unavailable to the user.

    }

    public void RegisterObserver(PhaseObserverInterface PhaseObserverC) {
        // called by
        // the Observer is the view as a whole NOT the phase
        PhaseObserver = PhaseObserverC;
    }

    public void RemoveObserver() {
        // no implementation required so far
    }

    private void RemoveResidualFP() {
        // called by Scenario.ExitfromPhase
        // removes infantry RFP counters at end of Movement
        // this is an automatic routine that is unavailable to the user.
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
