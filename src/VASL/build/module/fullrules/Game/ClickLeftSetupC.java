package VASL.build.module.fullrules.Game;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Stack;

import java.util.LinkedList;

public class ClickLeftSetupC implements Clicki {  //, GameComponent {
    public void DetermineClickPossibilities(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {  // As Integer, Optional ByVal Action As Integer=0)
        // called by XNAGph.CheckforMouseClick
        // clicking on top counter only
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        // Using list of counters for the hex, if Sw chosen, select owner
        for (GamePiece Selitem : SelectedCounters) {
            if (Selitem instanceof Stack) {
                for (PieceIterator pi = new PieceIterator(((Stack) Selitem).getPiecesIterator()); pi.hasMoreElements(); ) {
                    GamePiece p2 = pi.nextPiece();
                    if(p2.getProperty("overlay") != null){
                        // draggableOverlays.add(p2);
                    }
                }
            } else {
                if(Selitem.getProperty("overlay") != null){
                    // draggableOverlays.add(aP);
                }
            }
        }
        /*'use selected items only
        'If SW clicked, get owner and set selected to true
        If TypeCheck.

        IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop =
                True Then
        Dim SWOwner
        As Integer = (From
        GetSW As
        ObjectClassLibrary.ASLXNA.SuppWeapi In
        Scencolls.SWCol Where
        GetSW.BaseSW.Unit_ID =
                DisplaySprite.ObjID Select
        GetSW.BaseSW.Owner).First
        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
        If SWOwner >0 Then
        'SW is possessed
        Dim DisplayUnit
        As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(

                Function(Findsprite)Findsprite.ObjID = SWOwner).First
        DisplayUnit.Selected =
                True
        Else
        'SW is unpossessed
        End If
        End If
        End If
        Next
        'if Concealment chosen, select contents
        For Each
        DisplaySprite As
        ObjectClassLibrary.ASLXNA.SpriteOrder In
        OH.VisibleCountersInHex
        If DisplaySprite.
        Selected Then
        'use selected items only
        'If concealment clicked, get contents and set selected to true
        If TypeCheck.

        IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop =
                True Then
        Dim ConContains

        As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =(
                From GetCon
        As ObjectClassLibrary.
        ASLXNA.PersUniti In
        Scencolls.Unitcol Where
        GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
        For Each
        ConItem As
        ObjectClassLibrary.ASLXNA.PersUniti In
        ConContains
        'select con item sprite
        Dim DisplayUnit
        As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(

                Function(Findsprite)Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
        DisplayUnit.Selected = True
        If ConItem.BasePersUnit.SW > 0 Then
        'need to select concealed SW
        If ConItem.BasePersUnit.FirstSWLink > 0
        Then
                DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite)Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
        DisplayUnit.Selected = True
        End If
        If ConItem.BasePersUnit.SecondSWlink > 0
        Then
                DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite)Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
        DisplayUnit.Selected = True
        End If
        End If
        Next ConItem
        End If
        End If
        Next*/
    }

}
