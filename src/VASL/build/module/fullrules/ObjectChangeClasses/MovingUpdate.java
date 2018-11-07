package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.util.LinkedList;

public class MovingUpdate {
    public MovingUpdate (){

    }

    public boolean UpdateAfterMove(Constantvalues.MovementStatus movementoptionclicked, LinkedList<PersUniti> SelectedList) {
        // called by all .MoveUpdate functions except in Clearance and PlaceDC classes which use an overload
        return true;
        /*Dim UnitMoved As DataClassLibrary.OrderofBattle
        Dim OBSWitem As DataClassLibrary.OrderofBattleSW
        Dim linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance
        Dim ASLMapLink As String = "Scen" & CStr(CInt(SelectedList.Item(0).BasePersUnit.Scenario))
        Dim Maptables As MapDataClassLibrary.
        ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ASLMapLink, CInt(SelectedList.Item(0).BasePersUnit.Scenario))
        Dim LocationCol As IQueryable (Of MapDataClassLibrary.GameLocation) =Maptables.CreateMapCollection()
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
        For Each MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti In SelectedList
        If Not (MovingUnit.BasePersUnit.LOBLink > 4000) Then 'not concealment counter - is either unit or dummy
        UnitMoved = linqdata.GetUnitfromCol(MovingUnit.BasePersUnit.Unit_ID)
        UnitMoved.hexnum = CShort(MovingUnit.BasePersUnit.Hexnum)
        Dim LocToUse As MapDataClassLibrary.
        GameLocation = GetLocs.RetrieveLocationfromMaptable(MovingUnit.BasePersUnit.Hexnum, MovingUnit.BasePersUnit.hexlocation)
        UnitMoved.LocIndex = LocToUse.LocIndex
        UnitMoved.LevelinHex = CType(LevelChk.GetLocationPositionLevel(MovingUnit.BasePersUnit.Hexnum, MovingUnit.BasePersUnit.hexlocation, MovingUnit.BasePersUnit.hexPosition), Short ?)
        UnitMoved.Hexname = GetLocs.GetnamefromdatatableMap(MovingUnit.BasePersUnit.Hexnum)
        UnitMoved.HexEnteredSideCrossedLastMove = MovingUnit.MovingPersUnit.HexEnteredSideCrossed
        UnitMoved.VisibilityStatus = MovingUnit.BasePersUnit.VisibilityStatus
        UnitMoved.Con_ID = MovingUnit.BasePersUnit.Con_ID
        If MovingUnit.MovingPersUnit.UsingDT Then UnitMoved.CX = True
        If movementoptionclicked = ConstantClassLibrary.ASLXNA.MovementStatus.Moved Then
        UnitMoved.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moved
        Else
        If MovingUnit.MovingPersUnit.MFUsed > 0 Then UnitMoved.
        MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moving
        If MovingUnit.MovingPersUnit.MFUsed > 0
        AndAlso(MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving Or MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoved)
        Then UnitMoved.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving
        If MovingUnit.MovingPersUnit.MFUsed > 0
        AndAlso(movementoptionclicked >= ConstantClassLibrary.ASLXNA.UMove.EnterConnectingTrench And movementoptionclicked <= ConstantClassLibrary.ASLXNA.UMove.EnterConnectingPill)
        Then UnitMoved.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Connecting
        End If
        If(movementoptionclicked >= ConstantClassLibrary.ASLXNA.UMove.ClearRubble And movementoptionclicked <= ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk And movementoptionclicked < > ConstantClassLibrary.ASLXNA.UMove.ClearRoadATMine)
        Or
                (movementoptionclicked >= ConstantClassLibrary.ASLXNA.UMove.ClearEnterRubble1 And movementoptionclicked <= ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk6)
        Then
        'doing clearance attempt; no location change but a status change
        Dim UnittoChange As OBUnitChange = New SetasTIc(UnitMoved.OBUnit_ID)
        UnittoChange.TakeAction()
        Dim GetBaseHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(CInt(UnitMoved.hexnum), 0)
        UnitMoved.hexlocation = MovingUnit.BasePersUnit.hexlocation 'always baseloc when doing mine/rubble clearance
        ElseIf MovingUnit.BasePersUnit.hexPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv Or
            (MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        Then
        'claiming wall advantage; not location change but a status change - requires texture change
        Dim UnittoChange As OBUnitChange = New SetasWallAdvc(UnitMoved.OBUnit_ID, MovingUnit.BasePersUnit.hexPosition)
        UnittoChange.TakeAction()
        'now part of TakeAction
        'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(UnitMoved.hexnum))
        'UnitMoved.hexlocation = CInt(GetBaseHex!terraintype) ' always baseloc when doing WA
        ElseIf UnitMoved.HasWallAdvantage And UnitMoved.Position<> MovingUnit.BasePersUnit.hexPosition Then
        'forfeiting wall advantage; not location change but a status change - requires texture change
        Dim UnittoChange As OBUnitChange = New RemoveWallAdvc(UnitMoved.OBUnit_ID)
        UnittoChange.TakeAction()
        If UnitMoved.Position<> MovingUnit.BasePersUnit.hexPosition Then
        UnitMoved.Position = MovingUnit.BasePersUnit.hexPosition
        UnitMoved.hexlocation = MovingUnit.BasePersUnit.hexlocation
        'now part of TakeAction
        'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(UnitMoved.hexnum))
        'If UnitMoved.hexlocation <= constantclasslibrary.aslxna.AltPos.CrestStatus1 And UnitMoved.hexlocation >= constantclasslibrary.aslxna.AltPos.CrestStatus6 Then UnitMoved.hexlocation = CInt(GetBaseHex!terraintype) '
        always baseloc when doing WA unless in crest status
            Else
        UnitMoved.hexlocation = MovingUnit.BasePersUnit.hexlocation
        UnitMoved.Position = MovingUnit.BasePersUnit.hexPosition
        End If
        UnitMoved.SetTexture()
        'update any SW counters associated with this unit
        Dim OBSWPoss As Integer = 0
        For x As Integer = 1 To 2
        If x = 1 Then OBSWPoss = CInt(UnitMoved.FirstSWLink) Else OBSWPoss = CInt(UnitMoved.SecondSWlink)
        If OBSWPoss <>0 Then '0 value means no SW
        'retrieve type of SW
        OBSWitem = linqdata.GetOBSWRecord(OBSWPoss)
        OBSWitem.Hexnumber = UnitMoved.hexnum
        OBSWitem.Hexlocation = CShort(UnitMoved.hexlocation)
        OBSWitem.Position = CShort(UnitMoved.Position)
        OBSWitem.LocIndex = UnitMoved.LocIndex
        OBSWitem.VisibilityStatus = UnitMoved.VisibilityStatus
        End If
        Next
        If MovingUnit.MovingPersUnit.IsConcealed Then
        Dim ConMoved As DataClassLibrary.Concealment = linqdata.GetConcealmentfromCol(UnitMoved.Con_ID)
        ConMoved.hexnum = CInt(UnitMoved.hexnum)
        ConMoved.Hexlocation = CInt(UnitMoved.hexlocation)
        ConMoved.Position = CInt(UnitMoved.Position)
        ConMoved.Hexname = UnitMoved.Hexname
        ConMoved.LevelinHex = CSng(UnitMoved.LevelinHex)
        ConMoved.LocIndex = CInt(UnitMoved.LocIndex)
        ConMoved.CX = UnitMoved.CX
        ConMoved.MovementStatus = UnitMoved.MovementStatus
        'ConMoved.ConTexture = Game.Content.Load(Of Texture2D)(Trim(ConMoved.SetTexture()))
        ConMoved.HexEnteredSideCrossedLastMove = UnitMoved.HexEnteredSideCrossedLastMove
        End If
        Else 'Concealment counter
        Dim ConMoved As DataClassLibrary.Concealment = linqdata.GetConcealmentfromCol(MovingUnit.BasePersUnit.Unit_ID)
        ConMoved.hexnum = MovingUnit.BasePersUnit.Hexnum
        If MovingUnit.MovingPersUnit.MFUsed > 0 Then ConMoved.
        MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moving
        If MovingUnit.MovingPersUnit.MFUsed > 0
        AndAlso(MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving Or MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoved)
        Then ConMoved.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving
        If MovingUnit.MovingPersUnit.MFUsed > 0
        AndAlso(movementoptionclicked >= ConstantClassLibrary.ASLXNA.UMove.EnterConnectingTrench And movementoptionclicked <= ConstantClassLibrary.ASLXNA.UMove.EnterConnectingPill)
        Then ConMoved.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Connecting
        ConMoved.Hexlocation = MovingUnit.BasePersUnit.hexlocation
        If MovingUnit.BasePersUnit.hexPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv Or
            (MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        Then
        'claiming wall advantage; not location change but a status change - requires texture change
        Dim UnittoChange As OBUnitChange = New SetConWAc(CInt(ConMoved.Con_ID), MovingUnit.BasePersUnit.hexPosition)
        UnittoChange.TakeAction()
        'now part of TakeAction
        'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(MovingCon.hexnum))
        'MovingCon.Hexlocation = CInt(GetBaseHex!terraintype) ' always baseloc when doing WA
        ElseIf ConMoved.HasWallAdvantage And ConMoved.Position<> MovingUnit.BasePersUnit.hexPosition Then
        'forfeiting wall advantage; not location change but a status change - requires texture change
        'Dim UnittoChange As UnitChange = New RemoveConWAc(ConMoved)
        Dim UnittoChange As OBUnitChange = New RemoveConWAc(CInt(ConMoved.Con_ID), MovingUnit.BasePersUnit.hexPosition)
        UnittoChange.TakeAction()
        'now part of TakeAction
        'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(ConMoved.hexnum))
        'ConMoved.Hexlocation = CInt(GetBaseHex!terraintype) ' always baseloc when doing WA
            Else
        ConMoved.Hexlocation = MovingUnit.BasePersUnit.hexlocation
        ConMoved.Position = MovingUnit.BasePersUnit.hexPosition
        End If
        Dim LocToUse As MapDataClassLibrary.
        GameLocation = GetLocs.RetrieveLocationfromMaptable(MovingUnit.BasePersUnit.Hexnum, MovingUnit.BasePersUnit.hexlocation)
        ConMoved.LocIndex = LocToUse.LocIndex
        ConMoved.LevelinHex = CSng(CType(LevelChk.GetLocationPositionLevel(MovingUnit.BasePersUnit.Hexnum, MovingUnit.BasePersUnit.hexlocation, MovingUnit.BasePersUnit.hexPosition), Short ?))
        ConMoved.Hexname = GetLocs.GetnamefromdatatableMap(MovingUnit.BasePersUnit.Hexnum)
        ConMoved.HexEnteredSideCrossedLastMove = MovingUnit.MovingPersUnit.HexEnteredSideCrossed
        If MovingUnit.MovingPersUnit.UsingDT Then ConMoved.CX = True
        ConMoved.SetTexture()
        End If
        Next
        linqdata.UpdateAfterMove()
        Return True*/
    }
    public boolean UpdateAfterMoveClDC(Constantvalues.UMove movementoptionclicked, LinkedList<PersUniti> SelectedList){

        return true;
        /*'called by Clearance.moveupdate and PlaceDC.moveupdate - overloads
        Dim linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance
        Dim UnitMoved As DataClassLibrary.OrderofBattle:
    Dim OBSWitem As DataClassLibrary.OrderofBattleSW
        Dim ASLMapLink As String = "Scen" & CStr(CInt(SelectedList.Item(0).BasePersUnit.Scenario))
        Dim Maptables As MapDataClassLibrary.
        ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ASLMapLink, CInt(SelectedList.Item(0).BasePersUnit.Scenario))
        Dim LocationCol As IQueryable (Of MapDataClassLibrary.GameLocation) =Maptables.CreateMapCollection()
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
        For Each MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti In SelectedList
        If Not (MovingUnit.MovingPersUnit.IsDummy) Then
        UnitMoved = linqdata.GetUnitfromCol(MovingUnit.BasePersUnit.Unit_ID)
        UnitMoved.hexnum = CShort(MovingUnit.basepersunit.hexnum)
        UnitMoved.LocIndex = CShort(MovingUnit.BasePersUnit.LOCIndex)
        UnitMoved.Hexname = GetLocs.GetnamefromdatatableMap(MovingUnit.basepersunit.hexnum)
        UnitMoved.HexEnteredSideCrossedLastMove = 0
        If movementoptionclicked = ConstantClassLibrary.ASLXNA.ContextM.PlaceDC Then
        'moving units "drops" DC
        Dim OBSWPoss As Integer = 0
        For x As Integer = 1 To 2
        If x = 1 Then OBSWPoss = CInt(UnitMoved.FirstSWLink) Else OBSWPoss = CInt(UnitMoved.SecondSWlink)
        If OBSWPoss <>0 Then '0 value means no SW
        'retrieve type of SW
        OBSWitem = linqdata.GetOBSWRecord(OBSWPoss)
        If OBSWitem.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.DemoC) Then
        Dim UnittoChange As UnitChange = New PlaceTheDC(UnitMoved.OBUnit_ID, OBSWitem.OBSW_ID)
        UnittoChange.TakeAction()
        Exit For
        End If
        End If
        Next
                Else
        If(movementoptionclicked >= ConstantClassLibrary.ASLXNA.UMove.ClearRubble And movementoptionclicked <= ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk)
        Or
                (movementoptionclicked >= ConstantClassLibrary.ASLXNA.UMove.ClearEnterRubble1 And movementoptionclicked <= ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk6)
        Then
        'doing clearance attempt; no location change but a status change
        Dim UnittoChange As OBUnitChange = New SetasTIc(UnitMoved.OBUnit_ID)
        UnittoChange.TakeAction()
        'Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ScenarioName, ScenarioID)
        'Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
        Dim Levelchk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
        Dim GetBaseHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(CInt(UnitMoved.hexnum), 0)
        'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(UnitMoved.hexnum))
        UnitMoved.hexlocation = MovingUnit.basepersunit.hexlocation 'always baseloc when doing mine/rubble clearance
        End If
        UnitMoved.LevelinHex = 0 'always baseloc when doing mine/rubble clearance
        UnitMoved.HexEnteredSideCrossedLastMove = 0
        If MovingUnit.MovingPersUnit.UsingDT Then UnitMoved.CX = True
        'update any SW counters associated with this unit
        Dim OBSWPoss As Integer = 0
        For x As Integer = 1 To 2
        If x = 1 Then OBSWPoss = CInt(UnitMoved.FirstSWLink) Else OBSWPoss = CInt(UnitMoved.SecondSWlink)
        If OBSWPoss <>0 Then '0 value means no SW
        'retrieve type of SW
        OBSWitem = linqdata.GetOBSWRecord(OBSWPoss)
        OBSWitem.Hexlocation = CShort(UnitMoved.hexlocation)
        OBSWitem.Position = CShort(UnitMoved.Position)
        End If
        Next
        End If
        End If
        Next
        linqdata.UpdateAfterMove()
        Return True*/
    }
}
