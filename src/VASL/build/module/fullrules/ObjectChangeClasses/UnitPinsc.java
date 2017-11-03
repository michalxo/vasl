package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Stack;

import javax.swing.*;
import java.util.LinkedList;

public class UnitPinsc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();

    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    public UnitPinsc() {

    }
    public boolean Takeaction(PersUniti TargParent) {
            /*'Name:       TargetPins()

                    'Identifier UC 206

                    '            Preconditions()
                    '1.	Unbroken MMC Target is alive and fails a PTC or passes a MC by highest possible number

                    '            Basic Course
                    '1.	Use case begins when a Target fails a PTC or passes MC by highest number [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult; UC104-TargetPTCResult]
                    '2.	Use case ends when the Target changes status to Pinned

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '1.*/
        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }

                /*'If Not IsNothing(.TargetPersUnit) Then .TargetPersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.
                        '.BasePersUnit.CX = False
                        .BasePersUnit.Pinned = True
                '.BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None*/
        TargParent.getTargetunit().setPinned(true);
        TargParent.getbaseunit().setPinned(true);
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " Pins");

        // add counter
        ScenarioC scen = ScenarioC.getInstance();
        CommonFunctionsC ToDO = new CommonFunctionsC(scen.getScenID());
        GamePiece ToPin = ToDO.GetGamePieceFromID(TargParent.getbaseunit().getUnit_ID());
        if (ToPin != null) {ToPin.keyEvent(KeyStroke.getKeyStroke('P', java.awt.event.InputEvent.CTRL_MASK));}

        // HoB
        if (TargParent.getTargetunit().getHoBFlag()) {   // rolled a 2
            Constantvalues.PersUnitResult HobChange = TargParent.getTargetunit().HOBMC();
            StatusChangei RunStatusChange;
            SelectStatusChangec GetStatusChange = new SelectStatusChangec();
            RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, TargParent);
            if (RunStatusChange != null ) {
                RunStatusChange.Takeaction(TargParent);
            } else {
                //myPopUpList = GetStatusChange.PopUpItems; temporary while debugging UNDO
                return false;
            }
            TargParent.getbaseunit().setOrderStatus(TargParent.getTargetunit().getOrderStatus());
            // update Target and Firing lists with new units
            if (RunStatusChange.GetNewTargs != null) {myNewTargs = RunStatusChange.GetNewTargs;}
        }
        return true;
    }


    public LinkedList<PersUniti> GetNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> GetNewFirings () {
        // no code required; no new unit
        return null;
    }
    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/

}
