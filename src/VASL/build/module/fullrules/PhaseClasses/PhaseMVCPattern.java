package VASL.build.module.fullrules.PhaseClasses;

import VASL.build.module.fullrules.Constantvalues;

public class PhaseMVCPattern {
    // this class creates and uses the Phase MVC pattern which updates and displays the current scenario situation
    private iPhaseMVC PhaseMVC;
    private PhaseControllerInterface PhaseCont;

    public PhaseMVCPattern(Constantvalues.Phase PhaseToUse, int ScenID, Constantvalues.ScenarioAction scenarioaction) {
        // creates the model, then the controller, which creates the view
        // then trigger the model by calling XXX on the controller
        PhaseMVC = CreatePhaseMVC(PhaseToUse, ScenID);
        PhaseCont = new PhaseControllerConcreteC(PhaseMVC);
        // Tells the controller to join phase and update scen values
        PhaseCont.GoToPhase(scenarioaction);
    }

    // these are functions visible to ASLXNA that pass the pattern results back to the main program
    public int GetCurrentTurn() {
        return PhaseMVC.getCurrentTurn();
    }

    public Constantvalues.Phase GetCurrentPhase() {
        return PhaseMVC.getCurrentPhase();
    }

    public Constantvalues.WhoCanDo GetCurrentPlayerTurn() {
        return PhaseMVC.getCurrentPlayerTurn();
    }

    public boolean IsScenarioFinished() {
        return PhaseMVC.IsFinished();
    }

    private iPhaseMVC CreatePhaseMVC(Constantvalues.Phase phasetouse, int ScenID) {
        // called by Me.OpenScenario
        // creates MVC for current phase in the scenario
        switch (phasetouse) {
            case Setup:
                break;
            case Rally:
                PhaseMVC = new RallyPhaseMVC(ScenID);
                break;
            case PrepFire:
                PhaseMVC = new PrepPhaseMVC(ScenID);
                break;
            case Movement:
                PhaseMVC = new MovePhaseMVC(ScenID);
                break;
            case DefensiveFire:
                PhaseMVC = new DefPhaseMVC(ScenID);
                break;
            case AdvancingFire:
                PhaseMVC = new AdvFPhaseMVC(ScenID);
                break;
            case Rout:
                PhaseMVC = new RoutPhaseMVC(ScenID);
                break;
            case Advance:
                PhaseMVC = new AdvPhaseMVC(ScenID);
                break;
            case CloseCombat:
                PhaseMVC = new CCPhaseMVC(ScenID);
                break;
            case Refitphase:
                break;
            default:
                break;
        }
        if (PhaseMVC == null) {
            return null;
        } else {
            return PhaseMVC;
        }
    }

    public void TakeAction(Constantvalues.ScenarioAction ScenAction) {
        PhaseCont.GoToPhase(ScenAction);
    }
}

// MODEL
// interface
interface iPhaseMVC {
    boolean JoinPhase();
    void EnterIntoNewPhase();
    void ExitFromPhaseForward();
    void ExitFromPhaseBack();
    void LeaveCurrentPhase();
    void RegisterObserver(PhaseObserverInterface PhaseObserverC);
    void RemoveObserver();
    int getCurrentTurn();
    Constantvalues.WhoCanDo getCurrentPlayerTurn();
    Constantvalues.Phase getCurrentPhase();
    boolean IsFinished();

}

// CONTROLLER
// interface
interface PhaseControllerInterface {
        void GoToPhase(Constantvalues.ScenarioAction scenarioaction);
}
// concrete class
class PhaseControllerConcreteC implements PhaseControllerInterface {
    iPhaseMVC PhaseModel;
    public PhaseControllerConcreteC(iPhaseMVC newmodel) {
        PhaseModel =newmodel;
        // creates the view
        PhaseViewConcreteC PhaseView  = new PhaseViewConcreteC();
        PhaseView.SetupView(PhaseModel, this);
    }

    public void GoToPhase(Constantvalues.ScenarioAction scenarioaction) {
        // called by PhaseMVCPattern.new
        // this is where external call comes into the pattern and triggers the process
        // asks the model to either enter or join the phase and update scenario values
        switch (scenarioaction) {
            case EnterIntoNewPhase: PhaseModel.EnterIntoNewPhase();
                break;
            case JoinPhase: PhaseModel.JoinPhase();
                break;
            case ExitfromPhaseForward: PhaseModel.ExitFromPhaseForward();
                break;
            case ExitfromPhaseBack: PhaseModel.ExitFromPhaseBack();
                break;
            case LeavePhase: PhaseModel.LeaveCurrentPhase();
                break;
            default: break;
        }
    }
}

// VIEW
// interface
interface PhaseObserverInterface {
    void UpdateView();
}
// concrete class
class PhaseViewConcreteC implements PhaseObserverInterface {
    private iPhaseMVC ViewModel;
    private PhaseControllerInterface ViewController;

    public void SetupView(iPhaseMVC newModel, PhaseControllerInterface newController) {
        // called by PhaseControllerConcreteC.new
        // passes in references to the model and controller and registers as an observer
        ViewModel = newModel;
        ViewController = newController;
        // add observers
        ViewModel.RegisterObserver(this);
    }
    public void UpdateView() {
        // called by .NotifyObservers
        // tells observers that new set of scenario values is availalble, gets them and processes changes to the display
    }
}



