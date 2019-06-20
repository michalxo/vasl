package VASL.build.module.fullrules.MovementClasses;

// This class called whenever movement phase entered; creates the MVC pattern which awaits user input

import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;

public class MoveMVCPattern {
    public movei ConcreteMove;
    protected movecontrolleri ConcreteController;
    private ScenarioC scen;

    public void Initialize() {
        scen = ScenarioC.getInstance();
        ConcreteMove = new movementc();
        ConcreteMove.Initialize(scen.getScenID());
        movecontrollerc ConcreteController = new movecontrollerc(ConcreteMove);

    }
}
