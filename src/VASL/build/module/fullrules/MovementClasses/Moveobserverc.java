package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.SelectedThing;
import VASSAL.counters.GamePiece;

import java.awt.*;
import java.util.LinkedList;

public class Moveobserverc implements observeri {
    // Observer

    movei Movemodeli;
    movecontrolleri Moveconti;

    public Moveobserverc(movecontrolleri PassController, movei PassModel) {
        Movemodeli = PassModel;
        Moveconti = PassController;
        Movemodeli.RegisterObserver(this);
    }

    public boolean SendClicktoController(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {
        // sends information to controller from user input - by calling controller method
        // in this overload, mouse click on unit(s)
        return (Moveconti.NewAction(ClickedHex, SelectedCounters)  ? true : false);
    }

    public void  SendClicktoController(Constantvalues.MovementStatus ControlClick, Hex HexClicked, String PassSelection) {
        // sends information to controller from user input - by calling controller method
        // in this overlaod, mouse click on Control (dropdown or popup menu, or button)
        // controlclick is value of action selected
        Moveconti.NewAction(ControlClick, HexClicked, PassSelection);
    } 
    public boolean  SendClicktoController(Hex HexClick) {
        // sends information to controller from user input - by calling controller method
        // in this overlaod, mouse click on map
        return Moveconti.NewAction(HexClick);
    } 
    public void  SendClicktoController(LinkedList<SelectedThing> SelectedThings) {
        // sends information to controller from user input - by calling controller method
        // in this overload, right mouse click on item on mapboard
        Moveconti.NewAction(SelectedThings);
    } 
    public void  ControllertoScreen(Point Mappoint) {
        // Show the popup context menu - events handled by Gameform.dropdownEventHandler
        /*Mappoint.x = Mappoint.getX() + Game.Window.ClientBounds.Left + Game.translation.X);
        Mappoint.Y = CInt(Mappoint.Y + Game.Window.ClientBounds.Top + Game.translation.Y);
        Game.Scenario.ContextMenu.Show(Mappoint);
        Game.Scenario.ContextMenu.BringToFront();*/
    } 

    // this overloaded sub allows mouse click info to be transferred to the MVC
    public boolean PasstoObserver(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters){
        // called by Game.DetermineClickPossibilities, Location popup selection
        // comes from click on unit item on mapboard
        return (SendClicktoController(ClickedHex, SelectedCounters) ? true : false);
    }
    public boolean  PasstoObserver(Hex HexClick) {
        // comes from click on mapboard, but not item
         return SendClicktoController(HexClick);
    } 

    public void  PasstoObserver(Constantvalues.MovementStatus ControlClick, Hex HexClicked, String PassSelection) {
        // comes from popup menu click or button click
        SendClicktoController(ControlClick, HexClicked, PassSelection);
    } 
    public void  PasstoObserver(LinkedList<SelectedThing> SelectedThings) {
        // comes from Mainform.ShowContextPopups, part of process for creating popups
        SendClicktoController(SelectedThings);
    } 

    // overloaded
    public void  ShowContextPopup(Hex hexclicked) {
        // controlleri passes list of menu items and asks observeri to show popup
        // since observeri is the "view" allow it to manage on screen activities

    /*Dim ListofMenuThings = New ContextBuilder(menuitems)
            'Now need to filter the list by context of whatever has been clicked
                    'create Context control
                    ListofMenuThings.CreateMenu()
    Game.Scenario.ContextMenu.Tag = CStr(hexclicked)
    Dim popuppoint As New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    popuppoint = MapGeo.SetPoint(hexclicked)
            Game.Scenario.Moveobsi.ControllertoScreen(popuppoint)*/
    } 
    public void  ShowContextPopup(LinkedList<SelectedThing> SelectedThings) {
        // originally driven my Gameform.ShowContextPopups - returns there to show popup - display actions should be in Gameform or observeri
        // controller pass right click information to observer to show contextualized popup

        // Run db query to return list of popup items plus create menu structure using composite pattern
        /*Dim ListofMenuThings = New ContextBuilder(SelectedThings, OH)
            'Now need to filter the list by context of whatever has been clicked - done in ContextBuilder
                    'create Context control and filter menu
                    ListofMenuThings.CreateMenu()
                    'actual display is in Gameform.scenario.ShowContextPopups*/
    } 

    public boolean MovementUpdate() {
        return Moveconti.MovementUpdate();
    }

    /*public void StopMovementdraw() {
        Moveconti.StopMovementDraw();
    } 

    public void StartMovementDraw(Hex hexclicked, Constantvalues.UMove movementoptionclicked, int hexestosearch) {
        // this updates screen as a result of state change by first getting info from the model
        // then showing it - triggers update of graphics to run in Game.Update and Draw
        Moveconti.StartMovementDraw(hexclicked, movementoptionclicked, hexestosearch);
    } */
    
}
