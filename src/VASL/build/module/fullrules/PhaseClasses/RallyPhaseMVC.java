package VASL.build.module.fullrules.PhaseClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;

// concrete class implementing iPhaseMVC
public class RallyPhaseMVC implements iPhaseMVC {
    private AutomatedPhaseActions AutoActions;
    private Constantvalues.Phase CurrentPhasevalue ;
    private int CurrentTurnvalue;
    private Constantvalues.WhoCanDo CurrentPlayerTurnvalue;
    private boolean IsFinishedvalue;
    private PhaseObserverInterface PhaseObserver;

    public RallyPhaseMVC(int ScenID) {
        DataC Linqdata = DataC.GetInstance();    // use null values when sure instance already exists
        Scenario Scendet  = Linqdata.GetScenarioData(ScenID);
        CurrentPlayerTurnvalue = Scendet.getPTURN();
        CurrentTurnvalue = Scendet.getCURRENTTURN();
        IsFinishedvalue = false;
        CurrentPhasevalue = Constantvalues.Phase.Rally;
        AutoActions =new AutomatedPhaseActions(CurrentPlayerTurnvalue, ScenID);

    }

    public boolean JoinPhase() {
        // Called by Me.EnterIntonewPhase and ScenarioC.OpenScenario, ScenarioC.PhaseChangePrevious,
        //  does not run routine called the first time a phase is entered, that is done in EnternewPhase
        AutoActions.ShowBroken();
        return true;
    }

    public void EnterIntoNewPhase() {
        // called by Scenario.Phasechange and Scenario Startup routines
        // when entering for the first time
        // If coming back from previous phase, Phasechange sends to Joinphase instead

        // entering rally so new Playerturn
        if (CurrentPlayerTurnvalue == Constantvalues.WhoCanDo.Attacker) {
            CurrentPlayerTurnvalue = Constantvalues.WhoCanDo.Defender;
        } else {
            CurrentPlayerTurnvalue = Constantvalues.WhoCanDo.Attacker;
        }
        // check for new game turn
        if (CurrentPlayerTurnvalue == Constantvalues.WhoCanDo.Attacker){
            CurrentTurnvalue += 1;
        }
        WeatherCheck();
        JoinPhase();
        NotifyObservers();

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
        if (CurrentPlayerTurnvalue == Constantvalues.WhoCanDo.Attacker) {
            // Is attacker
            CurrentTurnvalue = CurrentTurnvalue - 1;
            CurrentPlayerTurnvalue = Constantvalues.WhoCanDo.Defender;
        } else { // Is Defender
            CurrentPlayerTurnvalue = Constantvalues.WhoCanDo.Attacker;
        }
    }

    public void ExitFromPhaseForward() {

        AutoActions.ClearTempStatus("MovementStatus.Advanced");
        DMCheck();
        LeaveCurrentPhase();
        // ReportEvent.LogPhaseHistory()
    }

    private void DMCheck() {
        /*' called by me.ExitfromPhaseForward
        ' identifies DM units for user to determine keep/remove
        ' this is an automatic routine that is unavailable to the user.*/
    }

    private void WeatherCheck() {
            /*'this routine is called when entering rally phase for the first time only
                    'in each player turn; handles weather, wind, interrogation
                    'it is an automatic routine not available to the user

                    'IMPORTED VB6.0 code for info purposes*/

    }
    public void RegisterObserver(PhaseObserverInterface PhaseObserverC) {
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
