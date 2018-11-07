package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationc;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.SelectedThing;
import VASSAL.counters.GamePiece;

import java.util.LinkedList;

public class movecontrollerc implements movecontrolleri {

    private movei Movemodeli;
    ScenarioC scen = ScenarioC.getInstance();

    public movecontrollerc (movei PassModel){

        Movemodeli = PassModel;

        scen.Moveobsi = new Moveobserverc(this, Movemodeli);
        // normally these next two calls set up the view and the model
        // but don't need to do much here because view already exists (map)
        Movemodeli.Initialize(scen.getScenID()) ;  // controller directs set up of initial objects*/
    }
    
    // controller subs should call methods in model and observer
    // no processing in controller
    public void  Jumphexes() {

    }

    public void  MovetoNewLocation() {

    }

    // 4 overloads: first handles map click, second handles button or right-click popup selection; third handles, fourth handles right-click on item - shows popup
    // popup selected following mapclick
    public void NewAction(Hex HexClicked) {
        // called by observeri to pass mouse event (mapclick) to controller for action
        // controller either sends to model or asks observer for more info (show popup)
        /*Dim menuitems As New List(Of DataClassLibrary.ASLXNA.Objectholder)
        // Determine which hex was clicked and retrive current hex
        Hex Currenthex = Movemodeli.GetStartingHex();
        if (Currenthex == null) {return;}  // no units selected yet
        // Determine range
        int  Moverange  = MapGeo.CalcRange(Currenthex, HexClicked, false);
        // Pass action to model or back to observer
        if (Moverange == 1) {
            // ask model to determine which menu items should be shown
            Locationi menuMovehex = new Locationc(HexClicked.getCenterLocation(), Constantvalues.UMove.HexNew);
            Movemodeli.DetermineMenuforHexMove(menuitems, Currenthex, menuMovehex);
            // pass menu items to observer and ask it to show context popup
            if (menuitems.size())== 0){  // no options to show; proceed with move
                Movemodeli.MoveToNewHex(HexClicked, 0, ""); // 2nd variable is movement options and is none; 3rd parameter is selection text and is none
            } else{
                scen.Moveobsi.ShowContextPopup(menuitems, HexClicked);
                //Game.contextshowing = True
            }
        } else if (Moverange > 1) {
            Movemodeli.QuickMove();
        }*/
    }

    public void NewAction(Constantvalues.MovementStatus controlclick, Hex HexClicked, String PassSelection) {
        // called by popup selection or button click; observer and passes to model to manage
        // sends to model to act on
        if (controlclick == Constantvalues.MovementStatus.Moved) {
            Movemodeli.ClearMovement();
        } else {
            //Movemodeli.ProcessPopup(controlclick, HexClicked, PassSelection);
        }
    }
    public void NewAction(LinkedList<SelectedThing> SelectedThings) {
        // passed from Mainform.ShowContextPopups by observeri as part of process to show contextualized popup
        scen.Moveobsi.ShowContextPopup(SelectedThings);
        //Game.contextshowing = true;
    }
    public boolean NewAction(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {
        // called by Moveobsi.SendClicktoController
        // Receives new units from the observer and asks model to see if they can join stack or Def FireGroup
        return Movemodeli.AddtoStackAttempt(ClickedHex, SelectedCounters) ? true : false;
    }
    public void MoveUpdate() {
            Movemodeli.moveupdate();
    }

    public boolean MovementUpdate() {
        return false;
        //Movemodeli.MovementUpdate();
    }

    public void  StartMovementDraw(Hex hexclicked) {
            //Movemodeli.StartMovementDraw(hexclicked);
    }

    public void  StopMovementDraw() {
            //Movemodeli.StopMovementDraw();
    }

    public void  StartMovementDraw1(Hex hexclicked, Constantvalues.UMove movementoptionclicked, int hexestosearch) {
            //Movemodeli.StartMovementDraw(hexclicked, movementoptionclicked, hexestosearch);
    }
            
}
