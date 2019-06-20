package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.SelectedThing;
import VASSAL.counters.GamePiece;

import java.util.LinkedList;

public interface movecontrolleri {

        void MovetoNewLocation();
        //void Jumphexes();
        boolean NewAction(Hex HexClick);
        void NewAction(Constantvalues.MovementStatus ControlClick, Hex HexClicked, String PassSelection);
        void NewAction(LinkedList<SelectedThing> SelectedThings);

        boolean NewAction(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters);
        void MoveUpdate();
        boolean MovementUpdate();

        //Sub StartMovementDraw(ByVal hexclicked As Integer)
        //Sub StartMovementDraw(ByVal hexclicked As Integer, ByVal movementoptionclicked As Integer, ByVal hexestosearch As Integer)
        //Sub StopMovementDraw()

}
