package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.SelectedThing;
import VASSAL.counters.GamePiece;

import java.awt.*;
import java.util.LinkedList;

public interface observeri {

    // WILL WE NEED ANY OF THIS GIVEN VASL EXISTS?

    //void StartMovementDraw(int hexclicked);  // updating screen after state change
    // void StartMovementDraw(int hexclicked, int movementoptionclicked, int  hexestosearch); // overloaded to handle search
    boolean SendClicktoController(Hex HexClick);  // sending use input to contoller to drive state change
    boolean SendClicktoController(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters);
    void SendClicktoController(Constantvalues.MovementStatus ControlClick, Hex HexClicked, String PassSelection);
    void SendClicktoController(LinkedList<SelectedThing> SelectedThings);
    void ControllertoScreen(Point Mappoint); // allowing the controller to update screen options (menus, popups)
    boolean PasstoObserver(Hex hexClick);
    boolean PasstoObserver(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters);
    void PasstoObserver(Constantvalues.MovementStatus ControlClick, Hex HexClicked, String PassSelection);
    void PasstoObserver(LinkedList<SelectedThing> SelectedThings);
    void ShowContextPopup(Hex hexclicked);
    void ShowContextPopup(LinkedList<SelectedThing> SelectedThings);
    boolean MovementUpdate();
    // void StopMovementdraw();
}
