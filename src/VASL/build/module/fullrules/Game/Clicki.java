package VASL.build.module.fullrules.Game;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Stack;

import java.util.LinkedList;

public interface Clicki {
    void DetermineClickPossibilities(Hex ClickedHex, LinkedList<GamePiece> SelectedCounters);  // , Optional ByVal Action As Integer=0)  'action used for hover clicks
}
/*    Friend Class ClickLeftRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

    End Sub
    End Class
*/

/*
    Friend Class ClickLeftMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.Moveobsi.PasstoObserver(OH)
    If Scencolls.SelMoveUnits.Count > 0 Then 'moving units exist - could have clicked on moving or defending units
            'only do this if moving units selected - can't start MPh combat unless have moving units
                Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    End If

    End Sub
    End Class
    Friend Class ClickLeftDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

            Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)

    End Sub
    End Class
    Friend Class ClickLeftAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

            Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)

    End Sub
    End Class
    Friend Class ClickLeftRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on top counter only
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'use click on any unit to clear shade
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Try
    Dim displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where DoMatch.Ontop = True).First
    displaysprite.Selected = True
    Catch ex As Exception
    End Try
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next

    End Sub
    End Class

    Friend Class ClickLeftShiftSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location


                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    */

        /*
    Friend Class ClickLeftShiftMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                Game.Scenario.Moveobsi.PasstoObserver(OH)
    If Scencolls.SelMoveUnits.Count > 0 Then 'moving units exist - could have clicked on moving or defending units
            'only do this if moving units selected - can't start MPh combat unless have moving units
                    Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    End If
    Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickLeftShiftRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'selecting all units in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 1 Then
                'only one location so select everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

            Else
                'need a popup to show locations to choose from
                        'set point to draw menu
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class

    Friend Class ClickLeftCtlSetUpC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCtlRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCtlPrepC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 5000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
            Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    End Sub
    End Class
    Friend Class ClickLeftCtlMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
            Game.Scenario.Moveobsi.PasstoObserver(OH)
    If Scencolls.SelMoveUnits.Count > 0 Then 'moving units exist - could have clicked on moving or defending units
            'only do this if moving units selected - can't start MPh combat unless have moving units
                Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    End If
    End Sub
    End Class
    Friend Class ClickLeftCtlDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
            Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    End Sub
    End Class
    Friend Class ClickLeftCtlAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCtlRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCtlAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCtlCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class
    Friend Class ClickLeftCtlRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'select all units in hex

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID <= 6000 Then 'infantry, vehicle, gun or concealment, not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next

    End Sub
    End Class

    Friend Class ClickLeftAltSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickLeftAltRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickLeftAltPrepC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Triggers display of Combat possibilities using IFTMVC
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'add firers to tempfiregroup
    Dim AddFirers As Boolean = False
    Dim CommUtil = New UtilWObj.ASLXNA.CommonFunctions(Game.Scenario.ScenID)
            'Which player's phase is it
    Dim PlayerPhase As Integer = CommUtil.GetPhaseSide
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, displaysprite.TypeID) Then
    Dim SelUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From getunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where getunit.BasePersUnit.Unit_ID = displaysprite.ObjID Select getunit).First
                    'only select units capable of firing in this phase
    If SelUnit.BasePersUnit.Nationality = PlayerPhase Then
    displaysprite.Selected = True
            AddFirers = True
    End If
    End If
    Next
            'add all units in the hex
    If AddFirers Then Game.Scenario.IFT.AddFirerUnit(OH) Else Exit Sub

            'trigger IFTMVC
    If Not Game.Scenario.IFT.TempFireGroup.Count = 0 Then
    Dim IFTShow = New IFTOppClassLibrary.ASLXNA.IFTMVCPattern(Game.Scenario.IFT.TempFireGroup)
    For Each ShowString As IFTOppClassLibrary.ASLXNA.storeShadeHex In IFTShow.StoreStringstoDraw
                    Game.StringsToDraw.Add(New ShadeHex(ShowString.Hexname, ShowString.Hexnum, ShowString.LOSStatus, ShowString.LevelClear, ShowString.TextPos, ShowString.ShowColor))
    Next
    For Each ShowShade As IFTOppClassLibrary.ASLXNA.storeShadeHex In IFTShow.StoreHexestoShade
                    Game.HexesToShade.Add(New ShadeHex(ShowShade.Hexname, ShowShade.Hexnum, ShowShade.LOSStatus))
    Next
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.IFTShade
    End If
    End Sub
    End Class
    Friend Class ClickLeftAltMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickLeftAltDefensiveC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Triggers display of Combat possibilities using IFTMVC
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'add firers to tempfiregroup
    Dim AddFirers As Boolean = False
    Dim CommUtil = New UtilWObj.ASLXNA.CommonFunctions(Game.Scenario.ScenID)
            'Which player's phase is it
    Dim PlayerPhase As Integer = CommUtil.GetPhaseSide
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, displaysprite.TypeID) Then
    Dim SelUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From getunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where getunit.BasePersUnit.Unit_ID = displaysprite.ObjID Select getunit).First
                    'only select units capable of firing in this phase
    If SelUnit.BasePersUnit.Nationality = PlayerPhase Then
    displaysprite.Selected = True
            AddFirers = True
    End If
    End If
    Next
            'add all units in the hex
    If AddFirers Then Game.Scenario.IFT.AddFirerUnit(OH) Else Exit Sub

            'trigger IFTMVC
    If Not Game.Scenario.IFT.TempFireGroup.Count = 0 Then
    Dim IFTShow = New IFTOppClassLibrary.ASLXNA.IFTMVCPattern(Game.Scenario.IFT.TempFireGroup)
    For Each ShowString As IFTOppClassLibrary.ASLXNA.storeShadeHex In IFTShow.StoreStringstoDraw
                    Game.StringsToDraw.Add(New ShadeHex(ShowString.Hexname, ShowString.Hexnum, ShowString.LOSStatus, ShowString.LevelClear, ShowString.TextPos, ShowString.ShowColor))
    Next
    For Each ShowShade As IFTOppClassLibrary.ASLXNA.storeShadeHex In IFTShow.StoreHexestoShade
                    Game.HexesToShade.Add(New ShadeHex(ShowShade.Hexname, ShowShade.Hexnum, ShowShade.LOSStatus))
    Next
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.IFTShade
    End If
    End Sub
    End Class
    Friend Class ClickLeftAltAdvancingC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Triggers display of Combat possibilities using IFTMVC
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'add firers to tempfiregroup
    Dim AddFirers As Boolean = False
    Dim CommUtil = New UtilWObj.ASLXNA.CommonFunctions(Game.Scenario.ScenID)
            'Which player's phase is it
    Dim PlayerPhase As Integer = CommUtil.GetPhaseSide
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, displaysprite.TypeID) Then
    Dim SelUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From getunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where getunit.BasePersUnit.Unit_ID = displaysprite.ObjID Select getunit).First
                    'only select units capable of firing in this phase
    If SelUnit.BasePersUnit.Nationality = PlayerPhase Then
    displaysprite.Selected = True
            AddFirers = True
    End If
    End If
    Next
            'add all units in the hex
    If AddFirers Then Game.Scenario.IFT.AddFirerUnit(OH) Else Exit Sub

            'trigger IFTMVC
    If Not Game.Scenario.IFT.TempFireGroup.Count = 0 Then
    Dim IFTShow = New IFTOppClassLibrary.ASLXNA.IFTMVCPattern(Game.Scenario.IFT.TempFireGroup)
    For Each ShowString As IFTOppClassLibrary.ASLXNA.storeShadeHex In IFTShow.StoreStringstoDraw
                    Game.StringsToDraw.Add(New ShadeHex(ShowString.Hexname, ShowString.Hexnum, ShowString.LOSStatus, ShowString.LevelClear, ShowString.TextPos, ShowString.ShowColor))
    Next
    For Each ShowShade As IFTOppClassLibrary.ASLXNA.storeShadeHex In IFTShow.StoreHexestoShade
                    Game.HexesToShade.Add(New ShadeHex(ShowShade.Hexname, ShowShade.Hexnum, ShowShade.LOSStatus))
    Next
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.IFTShade
    End If
    End Sub
    End Class
    Friend Class ClickLeftAltRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickLeftAltAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickLeftAltCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickLeftAltRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'not in use as of March 12

    End Sub
    End Class
    Friend Class ClickRightSetupC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightRallyC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightPrepC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True

    End Sub
    End Class
    Friend Class ClickRightMovementC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil


    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
    Exit For
    End If
    Next
            'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    End If
    Next
            'if Concealment chosen, select all associated ? counters
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get stack units and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConSWlist = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Con_ID = DisplaySprite.ObjID Select GetSW)
    Dim ConUnitlist = (From GetUnit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetUnit.BasePersUnit.Con_ID = DisplaySprite.ObjID Select GetUnit)
    For Each ConSW In ConSWlist
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConSW.BaseSW.Unit_ID).First
    DisplayUnit.Selected = True
            Next
    For Each ConUnit In ConUnitlist
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConUnit.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
            Next
    End If
    End If
    Next
    If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
    If Scencolls.SelMoveUnits.Count > 0 Then 'moving units exist
            'only do this if moving units selected - can't start MPh combat unless have moving units
                    Game.Scenario.IFT.ClearCurrentIFT()
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
    Exit For
    End If
    Next
                    'if Sw chosen, select owner
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                            'use selected items only
                                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                                'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                                    'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                                    'SW is unpossessed
    End If
    End If
    End If
    Next
                    'if Concealment chosen, select all associated ? counters
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                            'use selected items only
                                    'If concealment clicked, get stack units and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConSWlist = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Con_ID = DisplaySprite.ObjID Select GetSW)
    Dim ConUnitlist = (From GetUnit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetUnit.BasePersUnit.Con_ID = DisplaySprite.ObjID Select GetUnit)
    For Each ConSW In ConSWlist
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConSW.BaseSW.Unit_ID).First
    DisplayUnit.Selected = True
            Next
    For Each ConUnit In ConUnitlist
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConUnit.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
            Next
    End If
    End If
    Next
                    Game.Scenario.IFT.ClickedOnNewParticipants(Hexnumber)
    End If
                Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End If

    End Sub
    End Class
    Friend Class ClickRightDefensiveC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True

            '' ''all this is a temp fix to test LOS
            ' ''Dim LevelinHex As Integer = 0
                    '' ''NEED TO Select other Levels - use popup
            ' ''Mapgeo.LOSCheckSetup(Hexnumber, LevelinHex)

    End Sub
    End Class
    Friend Class ClickRightAdvancingC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightRoutC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightAdvanceC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCloseCombatC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightRefitC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show Context popup for one counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'set point to draw menu
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
    DisplaySprite.Selected = True
                    'If SW clicked, get owner and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.SW, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim SWOwner As Integer = (From GetSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where GetSW.BaseSW.Unit_ID = DisplaySprite.ObjID Select GetSW.BaseSW.Owner).First
                        'Dim SWOwner As Integer = CInt(Game.Linqdata.GetOBSWData(Constantvalues.OBSWitem.Owner, DisplaySprite.ObjID))
    If SWOwner > 0 Then
                            'SW is possessed
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = SWOwner).First
    DisplayUnit.Selected = True
            Else
                            'SW is unpossessed
    End If
    End If
    Exit For
    End If
    Next
            'if Concealment chosen, select contents
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Selected Then
                    'use selected items only
                            'If concealment clicked, get contents and set selected to true
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, DisplaySprite.TypeID) And DisplaySprite.Ontop = True Then
    Dim ConContains As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From GetCon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where GetCon.BasePersUnit.Con_ID = DisplaySprite.ObjID).ToList
    For Each ConItem As ObjectClassLibrary.ASLXNA.PersUniti In ConContains
                            'select con item sprite
    Dim DisplayUnit As ObjectClassLibrary.ASLXNA.SpriteOrder = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.Unit_ID).First
    DisplayUnit.Selected = True
    If ConItem.BasePersUnit.SW > 0 Then
                                'need to select concealed SW
    If ConItem.BasePersUnit.FirstSWLink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.FirstSWLink).First
    DisplayUnit.Selected = True
    End If
    If ConItem.BasePersUnit.SecondSWlink > 0 Then
            DisplayUnit = OH.VisibleCountersInHex.Where(Function(Findsprite) Findsprite.ObjID = ConItem.BasePersUnit.SecondSWlink).First
    DisplayUnit.Selected = True
    End If
    End If
    Next ConItem
    End If
    End If
    Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightShiftSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftPrepC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
    If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                    Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If

    End Sub
    End Class
    Friend Class ClickRightShiftDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightShiftRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'show context popup for all items in hex location

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim hexlocationsvalue As IQueryable(Of MapDataClassLibrary.GameLocation) = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    Dim hexlocationslist As New List(Of MapDataClassLibrary.GameLocation)
    If hexlocationsvalue.Count = 0 Then
                'only one location so show everything in hex
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.TypeID < 5000 Then 'is infantry, vehicle, gun or concealment in selected location, , not SW or Terrain
    DisplaySprite.Selected = True
    End If
    Next
                'If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                        Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
                'End If
    Else
    For Each Passitem As MapDataClassLibrary.GameLocation In hexlocationsvalue
                    hexlocationslist.Add(Passitem)
    Next
                'need a popup to show locations to choose from
    Dim MouseAction As Integer = Constantvalues.MouseAction.RightShift
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocationslist, Showpoint, MouseAction, 0)
    Game.contextshowing = True
    End If
    End Sub
    End Class
    Friend Class ClickRightAltSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltPrepC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))
    End Sub
    End Class
    Friend Class ClickRightAltMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))
    Dim CreateMFtoDraw = New DrawMoveInfo(OH.Hexnum, OH.Hexnum, 0, 0)
    End Sub
    End Class
    Friend Class ClickRightAltDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class
    Friend Class ClickRightAltRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGrp.CheckforMouseClick
                    'processes click - in all phases this click iterates through the hex stack, top to bottom
                    'Right - Alt: changing ontop (visible) sprite to next one in collection

                    'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
            'if only one counter in hex no change required
    If OH.VisibleCountersInHex.Count = 1 Then Exit Sub
    Dim NewTop As Integer = 0
            'identify next counter in stack and make it visible while hiding previous, if at bottom return to top
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If DisplaySprite.Ontop = True Then
                    Game.HexToDraw.Remove(DisplaySprite)
    DisplaySprite.Ontop = False
    Exit For
    End If
    Next
    Dim TempSpriteList = New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    For DisplayLoop As Integer = 1 To OH.VisibleCountersInHex.Count - 1
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(DisplayLoop))
    Next
            TempSpriteList.Add(OH.VisibleCountersInHex.Item(0))  'add previous top to bottom
    OH.VisibleCountersInHex = TempSpriteList
            'Set Ontop counter
    With OH.VisibleCountersInHex.Item(0)
            .Ontop = True
    End With
            Game.HexToDraw.Add(OH.VisibleCountersInHex.Item(0))

    End Sub
    End Class

    Friend Class ClickRightCtlSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlPrepC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)

            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
    If Game.Scenario.Moveobsi.PasstoObserver(OH) Then
                Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End If

    End Sub
    End Class
    Friend Class ClickRightCtlDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class
    Friend Class ClickRightCtlRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Right-Ctrl: Show Context Popup for all counters in hex

    Dim Showpoint = New System.Drawing.Point
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
            'Get list of counters for the hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(Hexnumber), VisibleOccupiedhexes)
    For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    DisplaySprite.Selected = True
            Next
            Game.Scenario.ShowContextPopups(OH, Showpoint)
    Game.contextshowing = True
    End Sub
    End Class

    Friend Class ClickRightNothingSetupC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingRallyC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingPrepC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)
    End Sub
    End Class
    Friend Class ClickRightNothingMovementC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingDefensiveC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingAdvancingC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingRoutC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingAdvanceC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingCloseCombatC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class
    Friend Class ClickRightNothingRefitC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'trigger drag-and-drop LOSCheck
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            'all this is a temp fix to test LOS
    Dim LevelinHex As Integer = 0
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim LocsToTest As IQueryable(Of MapDataClassLibrary.GameLocation) : Dim PassTestLOSIndex As Single = 0
    LocsToTest = Getlocs.RetrieveLocationsfromMapTable(Hexnumber, "Hexnum")
    If LocsToTest.Count > 1 Then
            LevelinHex = CInt(InputBox("Enter Level to Test:", "Setting Up LOS Check"))
    End If
    Dim LOSHexToMap = New LOSClassLibrary.ASLXNA.LOSCheckHexToMap()
            LOSHexToMap.LOSCheckSetup(Hexnumber, LevelinHex, Game.Scenario.ScenID)

    End Sub
    End Class

    Friend Class ClickLeftShiftNothingSetupC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingRallyC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\He lpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingPrepC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Helpfiles\ASLRuleBook2ndEditionLatest.htm"
            Help.ShowHelp(Game.Toolbox, helpfile, HelpNavigator.KeywordIndex)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingMovementC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingDefensiveC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingAdvancingC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingRoutC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingAdvanceC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingCloseCombatC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftShiftNothingRefitC
    Implements Clicki

    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Left - Shift on map: opens terrain help
    Dim Test As Integer
    Test = 0
    Dim ShowHelp = New HelpC
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim Baselocation As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Hexnumber, Test)
    Dim Helplink As String = ShowHelp.GetHelpLink(Baselocation.Location)
    Dim helpfile As String = Game.Scenario.GetPath & "Documents\ASL Documents\Helpfiles\HelpASL.chm"
            Help.ShowHelp(Game.gameform, helpfile, Helplink)   '"A4Move.htm#IPC")   'HelpNavigator.KeywordIndex, "MF")
    End Sub
    End Class
    Friend Class ClickLeftNothingSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'use click on map to clear shde
    Dim XNAGph = XNAGraphicsC.GetInstance
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingPrepC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingMovementC
    Implements Clicki
    private scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

            'Left on Map: triggers move
                    Game.Scenario.Moveobsi.PasstoObserver(Hexnumber)

    End Sub
    End Class
    Friend Class ClickLeftNothingDefensiveC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingAdvancingC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If
    End Sub
    End Class
    Friend Class ClickLeftNothingRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.DisplayShade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftNothingRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
    Dim XNAGph = XNAGraphicsC.GetInstance
            'use click on map to clear shde
    If Game.HexesToShade.Count > 0 Or XNAGph.displayshade.HexesToColor.Count > 0 Then
                XNAGph.DisplayShade.ShaderClear()
                        'Game.Scenario.IFT.TempFireGroup.Clear()  ' this is not part of shader but allows for firingunits to be selected for combat.
    Exit Sub 'by exiting sub, just clear shade, must click again for action
    End If

    End Sub
    End Class
    Friend Class ClickLeftShadeC
    Implements Clicki
    public Sub DetermineClickPossibilities(ByVal Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'clicking on shade triggers action appropriate to the shade - value held in Scenario.ShaderToShow
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Dim XNAGph = XNAGraphicsC.GetInstance
    If Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.SniperShade Then
                'trigger sniper action - choosing target hex
    Dim SniperAction As locationclickactionsi = New SniperLocationC
                SniperAction.HandleAction(Hexnumber, Action)
    If Game.Scenario.IFT.NeedToResumeResolution Then Game.Scenario.IFT.ResumeCombatResolution()
    Exit Sub
    End If
    Select caseGame.Scenario.Phase
    caseConstantvalues.Phase.PrepFire

    caseConstantvalues.Phase.Movement
    Select caseGame.Scenario.ShaderToShow
    caseConstantvalues.ShadeShow.SearchShade
                            'Left on shaded hex: triggers search resolution
                                    Game.Scenario.Moveobsi.PasstoObserver(Constantvalues.UMove.DoSearch, Hexnumber, "")
    caseConstantvalues.ShadeShow.PlaceDCShade
                            'triggers PlaceDC
    Dim hexlocations = New List(Of MapDataClassLibrary.GameLocation)
    Dim hexlocationslist = From DoMatch As MapDataClassLibrary.GameLocation In XNAGph.DisplayShade.ADJACENTLocs
    Where DoMatch.Hexnum = Hexnumber Select DoMatch
    If hexlocationslist.Count = 1 Then
                                'only one location so use it for placement
                                        Game.Scenario.Moveobsi.PasstoObserver(Constantvalues.UMove.DoPlaceDC, Hexnumber, CStr(hexlocationslist.First.Location))
    Else
                                'need a popup to show locations to choose from
                                        'set point to draw menu
    Dim MouseAction As Integer = Constantvalues.MouseAction.LeftShift
    Dim Showpoint = New System.Drawing.Point
                                'Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Showpoint = MapGeo.SetPoint(Hexnumber)
    For Each test As MapDataClassLibrary.GameLocation In hexlocationslist
                                    hexlocations.Add(test)
    Next
    Dim ShowLocPop = New LocationContentPopup(Hexnumber, hexlocations, Showpoint, MouseAction, Constantvalues.UMove.DoPlaceDC)
    Game.contextshowing = True
    End If
    caseConstantvalues.ShadeShow.DFFShade
                            MessageBox.Show("clicked on DFFShade")


    End Select
    End Select

    End Sub
    End Class

    Friend Class ClickLeftSelectHoverC
    Implements Clicki
    public Sub DetermineClickPossibilities(ItemID As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'clicked on hover image (snipercounter, sniperlocation, sniper target)
    Select caseAction
    caseConstantvalues.HoverAction.SelectChoice
                    'processes click on sniper hover counter to trigger sniper resolution
    Dim SelectHover As HoverActionsi = New selectchoicec
                    SelectHover.HandleAction(ItemID)
    If Game.Scenario.IFT.NeedToResumeResolution Then Game.Scenario.IFT.ResumeCombatResolution()
    caseConstantvalues.HoverAction.ContentsDisplay
    caseConstantvalues.HoverAction.SniperAvail
                    'click on sniper counter, starts sniper resolution
    Dim Sniperside As Integer = Game.SniperToDraw.Item(0).ItemID
    Dim SniperHover As HoverActionsi = New SniperActionsc
                    SniperHover.HandleAction(ItemID)
    End Select
    End Sub
    End Class
    Friend Class ClickRightAltNothingSetupC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick

                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltnothingRallyC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick


                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltnothingPrepC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingMovementC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick


                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingDefensiveC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingAdvancingC
    Implements Clicki
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick
                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingRoutC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick


                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingAdvanceC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick


                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingCloseCombatC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick


                    'Game.TriggerZoom()
    End Sub
    End Class
    Friend Class ClickRightAltNothingRefitC
    Implements Clicki
    public Sub DetermineClickPossibilities(Hexnumber As Integer, Optional ByVal Action As Integer = 0) Implements Clicki.DetermineClickPossibilities
            'called by XNAGph.CheckforMouseClick


                    'Game.TriggerZoom()
    End Sub
    End Class*/

