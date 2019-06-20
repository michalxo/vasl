package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASSAL.counters.GamePiece;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public interface movei {

    void Initialize(int scenid);
    
    boolean MoveToNewHex(Hex HexClicked, Constantvalues.UMove MovementOptionClicked, String PassSelection);
    void QuickMove();
    boolean MoveWithinHex(Hex HexClicked, Constantvalues.UMove MovementOptionClicked, String PassSelection);
    String GetMovementInfo();
    boolean IsEligibletoMove(PersUniti MovingUnittoCheck, Hex clickedhex, LinkedList<PersUniti> TempMovementStack);
    void RegisterObserver(observeri Observer);
    void RemoveObserver(observeri Observer);
    void ClearMovement();
    boolean IsPartofStack(PersUniti MovingUnittoCheck, LinkedList<PersUniti> TempMovementStack);
    boolean AddtoMoveStackAttempt(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters);
    Hex GetStartingHex();
    //void DetermineMenuforHexMove(LinkedList<ObjectHolder> menuitems, int currenthex, Locationi menuMoveHex);
    //void ProcessPopup(int controlclick, int HexClicked, String PassSelection);
    void moveupdate();
    PersUniti UpdateExistingMovingUnit(PersUniti MovingUnittoUpdate, Constantvalues.UMove controlclick, boolean leaderinStack, LinkedList<PersUniti> TempSTack);
    boolean RedoMovementStack(Constantvalues.UMove movementoptionclicked);
    //boolean MovementUpdate();
    //void StartMovementDraw(int hexclicked);
    //void StartMovementDraw(int hexclicked, int movementoptionclicked, int hexestosearch);
    //void StopMovementDraw();
    boolean ProcessValidation(Hex hexclickedvalue, Location locationchange, Constantvalues.UMove movementoptionclickedvalue);
    boolean Recalculating(Constantvalues.UMove movementoptionclickedvalue, Hex hexclickedvalue, double MFCost, Locationi Moveloc, Hex Currenthex);
    Locationi Decorating(Locationi moveloc, Constantvalues.UMove movementoptionclickedvalue, Location locationchange, Constantvalues.AltPos Positionchange, boolean CurrentPosIsExitedCrest, Hex Currenthex);
    PersUniti UpdateAfterEnter(PersUniti MovingUnit, double MFCost, Hex hexclickedvalue, int hexenteredsidecrossed, Location locationchange, Constantvalues.AltPos Positionchange);
    PersUniti UpdateAfterWithin(PersUniti MovingUnit, double MFCost, Hex hexclickedvalue, int hexenteredsidecrossed, Location locationchange, Constantvalues.AltPos Positionchange);
    boolean CheckConcealmentLoss(PersUniti Movingunit, LinkedList<PersUniti> RemoveConUnit, ArrayList<Integer> RemoveCon, String Conlost, String conlosthex, String Conrevealed, Locationi LoctoUse);
    boolean RemoveRevealedConandDummy(ArrayList<Integer> RemoveCon, LinkedList<PersUniti> removeconunit, String Constring);
    //boolean RedoSpriteDisplay(int Newhex, Optional int Oldhex = 0);
    boolean WACleanUp(Hex Oldhex, boolean WALoss, Constantvalues.Nationality MovingNationality, boolean EnterToggle);
    int MovingSide = 0;
    int getHexSideEntry(Hex Currenthex, Hex newhex);
    LinkedList<GamePiece> getSelectedPieces();
}
