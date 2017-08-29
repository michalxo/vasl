package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class MGandInherentFPSelection {
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    public Constantvalues.CombatSel ShowChoices(PersUniti Unititem) {
        /*'called by IFT.AddFireUnit when Unititem has SW
        'determines if MG present and if so what combination of Inherent FP and MG is to be added to the FG

        'Const AddInf As Integer = 1 ':Const AddMG As Integer = 2 'variables used to determine which type
        Dim OBSWPoss As Integer = 0 'holds ID value of SW being checked
        Dim OBSW As ObjectClassLibrary.ASLXNA.SuppWeapi 'holds weapon values of SW being checked
        Dim MGCount As Integer = 0 'holds number of MG possessed
        Dim WhichtoUse As Integer = 0 'holds user input value for units to add
        Dim UsingMG As Integer = 0 'holds number of MG being fired: determines if inh FG can be used
        Dim InherentOK As Boolean = True 'determines if unit can use Inherent FP - depends on MG used
        Dim menuitems As New List(Of DataClassLibrary.ASLXNA.Objectholder)
        Dim menuItemadd As DataClassLibrary.ASLXNA.Objectholder
        Dim FirstMG As String = "" :Dim SecondMG As String = ""

        OBSWPoss = Unititem.BasePersUnit.FirstSWLink
        If OBSWPoss <>0 Then '0 value means no SW
        'retrieve  of SW
        OBSW = (From SelSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
        SelSW.BaseSW.Unit_ID = OBSWPoss Select SelSW).First 'Game.Linqdata.GetOBSWRecord(OBSWPoss)
        'check if Sw type is a MG
        If OBSW.IsMG Then
        MGCount += 1 'SW is a MG
        FirstMG = Trim(OBSW.BaseSW.UnitName)
        'Game.Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon, OBSWPoss)) Else SecondMG = Trim(Game.Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon, OBSWPoss))
        End If
        End If
        OBSWPoss = Unititem.BasePersUnit.SecondSWlink
        If OBSWPoss <>0 Then '0 value means no SW
        'retrieve  of SW
        OBSW = (From SelSW As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where
        SelSW.BaseSW.Unit_ID = OBSWPoss Select SelSW).First 'Game.Linqdata.GetOBSWRecord(OBSWPoss)
        'check if Sw type is a MG
        If OBSW.IsMG Then
        MGCount += 1 'SW is a MG
        SecondMG = Trim(OBSW.BaseSW.UnitName)
        'Game.Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon, OBSWPoss)) Else SecondMG = Trim(Game.Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon, OBSWPoss))
        End If
        End If
        InherentOK = CanStillUseInherentFP(Unititem, MGCount)
        If MGCount = 0 Then ' no MGs, only Inherent FP can be used, no selection to make
        If InherentOK Then Return ConstantClassLibrary.ASLXNA.CombatSel.InhOnly Else Return ConstantClassLibrary.
        ASLXNA.CombatSel.None
        End If

        'add context popup items
        'fire Inh and MG
        If InherentOK Then
        If FirstMG <>"" Then
                menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.CombatSel.InhandFirstMG
        menuItemadd.ActivityNames = Trim(Unititem.BasePersUnit.UnitName) & Space(1) & FirstMG
        menuitems.Add(menuItemadd)
        End If
        If SecondMG <>"" Then
                menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.CombatSel.InhandSecondMG
        menuItemadd.ActivityNames = Trim(Unititem.BasePersUnit.UnitName) & Space(1) & SecondMG
        menuitems.Add(menuItemadd)
        End If
        End If
        'MG only
        If FirstMG <>"" And SecondMG <>"" Then
                menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.CombatSel.BothMG
        menuItemadd.ActivityNames = FirstMG & Space(1) & SecondMG
        menuitems.Add(menuItemadd)
        End If
        If FirstMG <>"" Then
                menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.CombatSel.FirstMG
        menuItemadd.ActivityNames = FirstMG
        menuitems.Add(menuItemadd)
        End If
        If SecondMG <>"" Then
                menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.CombatSel.SecondMG
        menuItemadd.ActivityNames = SecondMG
        menuitems.Add(menuItemadd)
        End If
        'inherent only
        If InherentOK Then
                menuItemadd = New DataClassLibrary.ASLXNA.Objectholder
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.CombatSel.InhOnly
        menuItemadd.ActivityNames = Trim(Unititem.BasePersUnit.UnitName)
        menuitems.Add(menuItemadd)
        End If
        If menuitems.Count = 1 Then Return menuitems.Item(0).Activities
        'if only one option possible no need to show menu
        Dim ListofMenuThings = New ContextBuilder(menuitems)
        'create Context control
        ListofMenuThings.CreateMenu()
        Game.Scenario.ContextMenu.Text = "Firer"
        Game.Scenario.ContextMenu.Tag = Unititem.BasePersUnit.Unit_ID.ToString
        Dim MapGeo as mapgeoclasslibrary.
        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Dim popuppoint As New System.Drawing.Point
                popuppoint = MapGeo.SetPoint(CInt(Unititem.BasePersUnit.Hexnum))
        'Show the popup context menu - events handled by Gameform.dropdownEventHandler
        popuppoint.X = CInt(popuppoint.X + Game.Window.ClientBounds.Left + Game.translation.X)
        popuppoint.Y = CInt(popuppoint.Y + Game.Window.ClientBounds.Top + Game.translation.Y)
        'Game.Scenario.ContextMenu.AutoClose = False
        Game.Scenario.ContextMenu.BringToFront()
        Game.Scenario.ContextMenu.Show(popuppoint)
        Game.contextshowing = True
        */
        return Constantvalues.CombatSel.ShowMenu;
    }
    public void ProcessChoice(int SelectionMade, int PassUnitUsed) {
        /*'called by Gameform.dropdownEventHandler
        'processes result of popup selection of which items to add to TempFiregroup
        Dim FirstMG As DataClassLibrary.OrderofBattleSW:
        Dim SecondMG As DataClassLibrary.OrderofBattleSW
        Dim Unititem As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In
        Scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = PassUnitUsed Select selunit).First
        'DataClassLibrary.OrderofBattle = Game.linqdata.GetUnitfromCol(PassUnitUsed)
        Dim GoCombatSolutionTest As Boolean = False
        If Not Unititem.BasePersUnit.FirstSWLink = 0
        Then FirstMG = Game.Linqdata.GetOBSWRecord(CInt(Unititem.BasePersUnit.FirstSWLink))
        If Not Unititem.BasePersUnit.SecondSWlink = 0
        Then SecondMG = Game.Linqdata.GetOBSWRecord(CInt(Unititem.BasePersUnit.SecondSWlink))
        'process selection
        GoCombatSolutionTest = Game.Scenario.IFT.ProcessAddFirer(Unititem, SelectionMade)
        If SelectionMade = ConstantClassLibrary.ASLXNA.CombatSel.InhandFirstMG Or
                SelectionMade = ConstantClassLibrary.ASLXNA.CombatSel.BothMG
        Or SelectionMade = ConstantClassLibrary.ASLXNA.CombatSel.FirstMG Then
        'using FirstSw
        Else 'deselect sprites of those not chosen - not using FirstSW
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(CInt(Unititem.BasePersUnit.Hexnum)), VisibleOccupiedhexes)
        'loop through counters
        For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If DisplaySprite.Selected Then
        If DisplaySprite.ObjID = Unititem.BasePersUnit.FirstSWLink Then
        DisplaySprite.Selected = False :Exit For
        End If
        End If
        Next
        End If
        If SelectionMade = ConstantClassLibrary.ASLXNA.CombatSel.InhandSecondMG Or
                SelectionMade = ConstantClassLibrary.ASLXNA.CombatSel.BothMG
        Or SelectionMade = ConstantClassLibrary.ASLXNA.CombatSel.SecondMG Then
        'using SecondSW
        Else 'deselect sprites of those not chosen - not using SecondSW
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(CInt(Unititem.BasePersUnit.Hexnum)), VisibleOccupiedhexes)
        'loop through counters
        For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If DisplaySprite.Selected Then
        If DisplaySprite.ObjID = Unititem.BasePersUnit.SecondSWlink Then
        DisplaySprite.Selected = False :Exit For
        End If
        End If
        Next
        End If
        'SHOULD THIS ALL BE HERE OR CAN i MOVE IT INTO IFTC - it completes ClickedOnNewParticipants when using context selection
        'See if a possible solution exists
        If GoCombatSolutionTest Then
        'if possible solution then decide to test it
        If Game.Scenario.IFT.IsThereASolutiontoTest(ConstantClassLibrary.ASLXNA.CombatStatus.Firing) Then
        'next is Los check as both a firer and target chosen
        Game.Scenario.IFT.DetermineFireSolution()
        'call overarching method to manage LOS, FP and DRM calculations based on Firer/Target selections and display Fire button
        Game.Scenario.IFT.ManageCombatSolutionDetermination()
        End If
        Else
        Exit Sub
        'Do we need to do anything else here?
        End If*/
    }
    private boolean CanStillUseInherentFP(PersUniti Unititem, int MGCount) {
        /*'called by Me.ShowChoices
        'handles both combatstatus check and MG count check

        'combat status
        If Unititem.BasePersUnit.CombatStatus >= ConstantClassLibrary.ASLXNA.CombatStatus.PrepFirer And Unititem.
        BasePersUnit.CombatStatus <= ConstantClassLibrary.ASLXNA.CombatStatus.AdvFirer Then Return False
        'MG count check - depends on unit size
        Dim Unititemtype As Integer = Unititem.BasePersUnit.Unittype
        ' CInt(Game.Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType, CInt(Unititem.BasePersUnit.LOBLink)))
        If Unititemtype = ConstantClassLibrary.ASLXNA.Utype.Squad Then
        ElseIf Unititemtype = ConstantClassLibrary.ASLXNA.Utype.HalfSquad
        Or Unititemtype = ConstantClassLibrary.ASLXNA.Utype.Crew
        Or Unititemtype = ConstantClassLibrary.ASLXNA.Utype.Hero
        Or Unititemtype = ConstantClassLibrary.ASLXNA.Utype.LdrHero Then
        If MGCount >=1 Then Return False
        ElseIf Unititemtype >=ConstantClassLibrary.ASLXNA.Utype.Leader And
        Unititemtype <= ConstantClassLibrary.ASLXNA.Utype.Commissar Then
        Return False
        End If*/
        return true;
    }
}
