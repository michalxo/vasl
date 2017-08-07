package VASL.build.module.fullrules.PhaseClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;

public interface iStatusClear {
        boolean DoClear();
}
class ClearMovement implements iStatusClear {
    private Constantvalues.WhoCanDo UsingPlayer;
    private Constantvalues.Nationality player1;
    private Constantvalues.Nationality player2;
    private int ScenID;

    public ClearMovement(Constantvalues.WhoCanDo PassPlayerTurn, int PassScenID) {
        UsingPlayer = PassPlayerTurn;
        ScenID = PassScenID;
    }
    public boolean DoClear() {
        DataC Linqdata = DataC.GetInstance(); // use null values when sure instance already exists
        Scenario Scendet = Linqdata.GetScenarioData(ScenID);
        if (UsingPlayer == Constantvalues.WhoCanDo.Attacker) {
            player1 = Scendet.getATT1();
            player2 = Scendet.getATT2();
        } else {
            player1 = Scendet.getDFN1();
            player2 = Scendet.getDFN2();

        }
        // need to debug
        /*Dim AllPlayers As IQueryable (Of DataClassLibrary.OrderofBattle) =Linqdata.GetAllUnitsForOneSide(player1, player2)
        Dim AllMoved As IQueryable (Of DataClassLibrary.OrderofBattle)
        AllMoved = From QU As DataClassLibrary.OrderofBattle In AllPlayers Where
        QU.MovementStatus = Constantvalues.MovementStatus.Moved Or
        QU.MovementStatus = Constantvalues.MovementStatus.AssaultMoved Or
        QU.MovementStatus = Constantvalues.MovementStatus.Moving Or
        QU.MovementStatus = Constantvalues.MovementStatus.AssaultMoving Select QU

        For Each MovedUnit As DataClassLibrary.OrderofBattle In AllMoved
                MovedUnit.MovementStatus = Constantvalues.MovementStatus.NotMoving
        Next
        Dim AllConceal As IQueryable (Of DataClassLibrary.Concealment) =Linqdata.GetAllConForOneSide(player1, player2)
        Dim AllMovedCon As IQueryable (Of DataClassLibrary.Concealment)
        AllMovedCon = From QU As DataClassLibrary.Concealment In AllConceal Where
        QU.MovementStatus = Constantvalues.MovementStatus.Moved Or
        QU.MovementStatus = Constantvalues.MovementStatus.AssaultMoved Or
        QU.MovementStatus = Constantvalues.MovementStatus.Moving Or
        QU.MovementStatus = Constantvalues.MovementStatus.AssaultMoving Select QU

        For Each MovedCon As DataClassLibrary.Concealment In AllMovedCon
                MovedCon.MovementStatus = Constantvalues.MovementStatus.NotMoving
        Next*/
        return true;
    }
}