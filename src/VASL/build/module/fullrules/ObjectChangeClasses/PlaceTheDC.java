package VASL.build.module.fullrules.ObjectChangeClasses;

public class PlaceTheDC {
    /*Implements UnitChange
    Private ActiveUnitOB As DataClassLibrary.OrderofBattle
    Private DCtoPlace As DataClassLibrary.OrderofBattleSW
    Private linqdata As DataClassLibrary.ASLXNA.DataC
    Friend Sub New(ByVal unitid As Integer, ByVal SWID As Integer)
    Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()
    ActiveUnitOB = linqdata.GetUnitfromCol(unitid)
    DCtoPlace = linqdata.GetOBSWRecord(SWID)
    End Sub
    Public Function PlaceDC() As Boolean Implements UnitChange.TakeAction
            ' ''called by linqdata.updateaftermove (overload)
                    ' ''drops SW and update OB, OBSW
                    ''Dim XNAGph = XNAGraphicsC.GetInstance
            ''Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            ''If ActiveUnitOB.FirstSWLink = DCtoPlace.OBSW_ID Then
            ''    ActiveUnitOB.FirstSWLink = 0
            ''Else
            ''    ActiveUnitOB.SecondSWlink = 0
            ''End If
            ''ActiveUnitOB.SW -= CShort(1)
            ''MessageBox.Show(Trim(ActiveUnitOB.OBName) & " places " & Trim(DCtoPlace.OBWeapon))
            ''Dim SWHexloc As CombatTerrainClassLibrary.ASLXNA.HexAndLocHolder = XNAGph.DisplayShade.SelectedHexloc
            ''DCtoPlace.Hexnumber = SWHexloc.Hexnumber
            ''DCtoPlace.Hexlocation = CShort(SWHexloc.HexLocation)
            ''DCtoPlace.Position = 0
            ''DCtoPlace.Owner = 0
            ''DCtoPlace.Status = CShort(ConstantClassLibrary.ASLXNA.SWStatus.DCPlaced)
            ' ''Manage sprites
                    ''Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(CInt(ActiveUnitOB.hexnum)), VisibleOccupiedhexes)
            ''Dim SWspritetomove As ObjectClassLibrary.ASLXNA.SpriteOrder = (From DoMatch In OH.VisibleCountersInHex Where DoMatch.ObjID = DCtoPlace.OBSW_ID
            ''Select DoMatch).First
            ''SWspritetomove.hexloc = CInt(DCtoPlace.Hexlocation)
            ''SWspritetomove.hexposition = CInt(DCtoPlace.Position)

            ''Dim TargetVector = New Vector2(MapGeo.GetCX(CInt(DCtoPlace.Hexnumber)) - 23, MapGeo.GetCY(CInt(DCtoPlace.Hexnumber)) - 23)
            ''SWspritetomove.Position = TargetVector
            ''SWspritetomove.Updatetexture()
                    ''If ActiveUnitOB.hexnum = DCtoPlace.Hexnumber Then 'placing in own hex; no need to move sprites
            ''    'check for new location counter
            ''    OH.CheckforAssociatedLocationCounter(SWspritetomove)
            ''Else 'placing in new hex
            ''    'delete SWsprite in placement hex
            ''    If SWspritetomove.Ontop = True Then Game.HexToDraw.Remove(SWspritetomove)
            ''    OH.VisibleCountersInHex.Remove(SWspritetomove)
            ''    'add DC sprite in new hex
            ''    If Not (Game.Scenario.HexesWithCounter.ContainsKey(DCtoPlace.Hexnumber)) Then
            ''        'if hex not already added, then add
                    ''        Game.Scenario.HexesWithCounter.Add(DCtoPlace.Hexnumber, New VisibleOccupiedhexes(CInt(DCtoPlace.Hexnumber)))

            ''    End If
            ''    OH = CType(Game.Scenario.HexesWithCounter(CInt(DCtoPlace.Hexnumber)), VisibleOccupiedhexes)
            ''    OH.AddObjecttoDisplay(SWspritetomove)
            ''    If OH.VisibleCountersInHex.Count = 1 Then Game.HexToDraw.Add(SWspritetomove)
            ''    OH.RedoDisplayOrder()
            ''End If
            ' ''create ThingToDo in AdvFPh
                    ''Dim PassPlayerTurn As Integer = Game.Scenario.PlayerTurn
            ''Game.Linqdata.CreateNewThingsToDo(ConstantClassLibrary.ASLXNA.UMove.DoPlaceDC, CInt(DCtoPlace.Hexnumber), CInt(DCtoPlace.Hexlocation), PassPlayerTurn, Game.Scenario.ScenID)
            ''Return True
    End Function*/
}
