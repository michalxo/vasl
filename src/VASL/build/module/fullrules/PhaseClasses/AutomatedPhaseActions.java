package VASL.build.module.fullrules.PhaseClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;

public class AutomatedPhaseActions {
    private Constantvalues.WhoCanDo PlayerTurn;
    private int ScenID;

    public AutomatedPhaseActions(Constantvalues.WhoCanDo PassCurrentPlayerTurn, int PassScenID) {
        PlayerTurn = PassCurrentPlayerTurn;
        ScenID = PassScenID;
    }

    public void ShowBroken() {    }

    public void PutHiddenOnBoard() {    }

    public void ShowHiddenInCC() {    }

    public void SaveScenario(boolean Toggle) {    }

    public void SmokeUpdate() {
       /* 'this routine updates status of smoke at various time - called by various phase check routines
        ' it is an automatic background routine not available to the user

        'IMPORTED VB6.0 code to use as a guide - SHOULD THIS CODE BE HERE? AUG 14*/

    }

    public void VehicleDustCHeck() {    }

    public void ClearTempStatus(String StatusToClear) {
        // called by various phase control routines
        // this is an automatic routine not available to the user

        iStatusClear StatusClear;
        if (StatusToClear== "MovementStatus.Moved") {
            StatusClear = new ClearMovement(PlayerTurn, ScenID);
            StatusClear.DoClear();
        } else {
        }
        if (DoDBSave()) {
        }  // MsgBox ("Database save works")
    }

    private boolean DoDBSave() {
        try {
            // table must have primary key otherwise readonly and submit changes will not work
            DataC UseLinq = DataC.GetInstance();
            // UseLinq.db.SubmitChanges()
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}


