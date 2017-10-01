package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.PersunitDecoratorc;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.counters.GamePiece;

import java.util.LinkedList;

public class SetTargetMoveStatus {
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    ScenarioC scen = ScenarioC.getInstance();
    /*    'Friend Function UpdateTargetHexLocPos() As Boolean
                '    'called movementclasses .moveupdate after all MovingUnit changes are completed
        '    'updates values for hex, location and position of Target unit
        '    Dim MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti
                '    If Game.Scenario.IFT.TempTarget.Count > 0 Then
                '        For Each TempTargUnit As ObjectClassLibrary.ASLXNA.PersUniti In Game.Scenario.IFT.TempTarget
                '            MovingUnit = Nothing
                '        Next
                '        return true
                '    ElseIf Game.Scenario.IFT.TargGroup.Count > 0 Then
                '        return true
                '    Else
                '        'nothing to do here - should always be caught by one of above IFs
        '        return False
                '    End If
                'End Function*/
    public boolean UpdateTargetHexLocPos(Hex Newhex, LinkedList<GamePiece> SelectedUnits) {
        // called movementclasses .moveupdate after all MovingUnit changes are completed
        // updates values for hex, location and position of Target unit

        //'Dim MovingUnit As MovingObjecttypeinterface
        if (scen.IFT.TempTarget.size() > 0 ) {
            scen.IFT.setTargethex(null);
            scen.IFT.TempTarget.clear();
            scen.IFT.ClickedOnNewParticipants(Newhex, SelectedUnits);
            return true;
        } else if (scen.IFT.TargGroup.size() > 0) {
            scen.IFT.setTargethex(null);
            scen.IFT.TargGroup.clear();
            scen.IFT.ClickedOnNewParticipants(Newhex, SelectedUnits);
            return true;
        } else {
            // nothing to do here - should always be caught by one of above IFs
            return false;
        }
    }

    public boolean RemoveRevealedDummies(LinkedList<PersUniti> RemoveDummies) {
        // called movementclasses .moveupdate after all MovingUnit changes are completed
        // removes revealed dummies from Target list before updating values for hex, location and position of Target unit
        if (RemoveDummies.size() ==0) {return true;} // no dummies revealed to remove
        PersUniti ReMovingUnit = null;
        LinkedList<PersUniti> RemoveTempTargetlist = new LinkedList<PersUniti>();
        LinkedList<PersUniti> RemoveTargetlist = new LinkedList<PersUniti>();
        if (scen.IFT.TempTarget.size() >0) {
            for (PersUniti TempTargUnit : scen.IFT.TempTarget) {
                PersUniti TestTarget = TempTargUnit;  // used in linq-sql lambda
                // need to match TargetUnit with its removed revealed dummy
                //'If ConstantClassLibrary.ASLXNA.Typetype.Concealment = TestTarget.BasePersUnit.TypeType_ID Then
                try {
                    //ReMovingUnit = (From DoMatch In RemoveDummies Where DoMatch.BasePersUnit.Unit_ID = TestTarget.BasePersUnit.Unit_ID).First
                } catch (Exception e) {
                }
                //'End If
                // now update TargetUnit
                if (ReMovingUnit != null) { // dummy has been removed from Moving Unit list; need to remove from Target list
                    RemoveTempTargetlist.add(TestTarget);
                }
                ReMovingUnit = null;
            }
            for (PersUniti removeitem : RemoveTempTargetlist) {
                scen.IFT.TempTarget.remove(removeitem);
            }
            return true;
        } else if(scen.IFT.TargGroup.size() >0) {
            for (PersUniti TargetUnit : scen.IFT.TargGroup) {
                PersUniti TestTarget = TargetUnit; // used in linq-sql lambda
                // need to match TargetUnit with its Movingunit - can be one of three types
                //'If ConstantClassLibrary.ASLXNA.Typetype.Concealment = TestTarget.BasePersUnit.TypeType_ID Then
                //ReMovingUnit = (From DoMatch In RemoveDummies Where DoMatch.BasePersUnit.Unit_ID = TestTarget.BasePersUnit.Unit_ID).First
                // End If
                // now update TargetUnit
                if (ReMovingUnit != null) {
                    TargetUnit.getbaseunit().setHexname(ReMovingUnit.getbaseunit().getHexName());
                    RemoveTargetlist.add(TestTarget);
                }
                ReMovingUnit = null;
            }
            for (PersUniti removeitem : RemoveTargetlist) {
                scen.IFT.TargGroup.remove(removeitem);
            }
            return true;
        } else {
            // nothing to do here - should always be caught by one of above IFs
            return false;
        }
    }
}
