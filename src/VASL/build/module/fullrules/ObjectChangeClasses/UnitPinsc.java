package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
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
    public final static String DB_COUNTER_TYPE_MARKER_KEY = "DBCounterType";
    public final static String DB_UNIT_TYPE = "unit";
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
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " Pins");

        GamePiece ToPin = null;
        try {

            final PieceIterator pi = new PieceIterator(GameModule.getGameModule().getGameState().getAllPieces().iterator());

            while (pi.hasMoreElements()) {
                final GamePiece piece = pi.nextPiece();
                if (piece instanceof Stack) {
                    for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                        GamePiece p2 = pi2.nextPiece();
                        if (isDBUnitCounter(p2) && p2.getProperty("TextLabel").toString() != null) {
                            int ObjIDlink = Integer.parseInt(p2.getProperty("TextLabel").toString());
                            if (ObjIDlink == TargParent.getbaseunit().getUnit_ID()) {
                                //GameModule.getGameModule().getChatter().send("Have found Gamepiece to DM: " + TargParent.getbaseunit().getUnitName());
                                ToPin = p2;
                                //p2.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
                            }
                        }
                    }
                } else {
                    /*if (isDBUnitCounter(piece) && piece.getProperty("TextLabel").toString() != null) {
                        int ObjIDlink = Integer.parseInt(piece.getProperty("TextLabel").toString());
                        if (ObjIDlink == TargParent.getbaseunit().getUnit_ID()) {
                            GameModule.getGameModule().getChatter().send("Have found Gamepiece to DM: " + TargParent.getbaseunit().getUnitName());
                        }
                    }*/
                }

            }

        } catch (Exception e) {
            GameModule.getGameModule().getChatter().send("Error finding Gamepiece to Pin: " + TargParent.getbaseunit().getUnitName());
        }
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
    public boolean isDBUnitCounter(GamePiece piece) {

        return isPropertySet(piece, DB_COUNTER_TYPE_MARKER_KEY, DB_UNIT_TYPE);
    }
    public boolean isPropertySet(GamePiece piece, String key, String value) {

        return piece.getProperty(key) != null && piece.getProperty(key).equals(value);
    }
}
