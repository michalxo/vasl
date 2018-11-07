package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.util.ArrayList;

public class ConcealmentLossc implements ConcealmentLossi {
    // this class is used to manage concealment loss tests when unit/vehicle carries out potential loss activity
    private int myScenID;

    public ConcealmentLossc(int PassScenID) {

        myScenID = PassScenID;
    }

    // create overloads for different phases by using a different parameter
    public boolean IsLost(PersUniti MovingUnit, Constantvalues.MovementStatus movementstatus) {
        // called by all movement type classes which involve concealed or dummy units
        // tests if units activity and context cause concealment loss

        // set the variables needed for the routine, including creating TempSol for LOS check
        return true;
        /*PassLOSStatus As Integer ' PassFP As Single :  Passdrm As Integer
        PassHexname As String
        LOSLocNum As Integer = 0 :PassSolWorks As Boolean = False :EnemyLoCList As List(Of Integer)
        Tempsolutions = New List(Of LOSClassLibrary.ASLXNA.TempSolution)
        'first check if unit is concealed
        If Not (MovingUnit.MovingPersUnit.IsConcealed) AndAlso Not(MovingUnit.MovingPersUnit.IsDummy) Then Return False
        'first, set variables about moving unit and its location/position
        Movinghexclicked As Integer = MovingUnit.BasePersUnit.Hexnum
        Movinglocation As Integer = MovingUnit.basepersunit.hexlocation
        MovingPosition As Integer = MovingUnit.BasePersUnit.hexPosition

        GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
        LoCtouse As
        MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(Movinghexclicked, Movinglocation)
        LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Mapcol)
        Movinglevel As Single = LevelChk.GetLocationPositionLevel(Movinghexclicked, Movinglocation, MovingPosition)
        MovingLOSIndex As Single = LoCtouse.LocIndex
        Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(myScenID) 'retrieves scenario data
        scenmap As Integer = CInt(Scendet.Map)
        ' GetNat = New DataClassLibrary.NationalityCheck(myScenID)
        MovingNationality As Integer = MovingUnit.BasePersUnit.Nationality '.GetNationality(MovingUnit)
        'get all enemy units
        EnemyCheck = New EnemyChecksC(LoCtouse.LocIndex, MovingNationality, myScenID)
        EnemytoCheck As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = EnemyCheck.GetAllEnemyUnitsInGame()
        'had to undo this as a classed-out method - in the wrong place; redo
        ' EnemyToCheck As IQueryable(Of DataClassLibrary.OrderofBattle)
        ' EnemyNat1 As Integer = 0 :  EnemyNat2 As Integer = 0
        ' EnemyTest = New EnemyChecksC(LoCtouse.LocIndex, MovingNationality)
        'If EnemyTest.GetEnemy(EnemyNat1, EnemyNat2) Then
        '    EnemyToCheck = Game.linqdata.GetEnemyUnits(EnemyNat1, EnemyNat2)
        'End If
        ' EnemyToCheck = Game.linqdata.GetEnemyUnits(MovingNationality, Movinghexclicked, Movinglocation)

        'loop through and determine LOS and movement status
        For Each EnemyUnit As ObjectClassLibrary.ASLXNA.PersUniti In EnemytoCheck
        ' TempTargetCopy As New List(Of DataClassLibrary.ASLXNA.TempTargetUnit)
        'Get new values for this unit
        'Check if Location already added
        AlreadyAddedTest As Integer:
        AlreadyAdded As Boolean = False
        LOSLocNum = CInt(EnemyUnit.BasePersUnit.LOCIndex)
        PassHexname = Trim(EnemyUnit.BasePersUnit.Hexname)
        Try
                AlreadyAddedTest = (From Qu As Integer In EnemyLoCList Where Qu = LOSLocNum Select Qu).First
                AlreadyAdded = True
        Catch ex As Exception
        AlreadyAddedTest = Nothing
        End Try
        If AlreadyAdded Then Continue For
        'get new values - need to redo los each time because could be level/location difference with alreadyadded
        'get LOS value
        TempSol As LOSClassLibrary.ASLXNA.TempSolution
        'get the required input variables for Tempsolution
        DFFlevel As Single = CSng(EnemyUnit.BasePersUnit.LevelinHex) :DFFlocation As
        Integer = CInt(EnemyUnit.BasePersUnit.hexlocation)
        LoCtouse = GetLocs.RetrieveLocationfromMaptable(LOSLocNum)
        DFFLOSIndex As Single = LoCtouse.LocIndex
        DFFPOsitionInHEx As Integer = CInt(EnemyUnit.BasePersUnit.hexPosition)
        'create TempSolution
        Try
        Tempsolutions.Add(New LOSClassLibrary.ASLXNA.TempSolution(CInt(EnemyUnit.BasePersUnit.Hexnum), DFFlevel, DFFLOSIndex, DFFPOsitionInHEx, Movinghexclicked, Movinglevel, MovingLOSIndex, MovingPosition, PassSolWorks, Tempsolutions.Count - 1, scenmap))
        TempSol = CType(Tempsolutions.Item(Tempsolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        Catch ex As Exception
        MsgBox("Error adding Fire Solution to TempSolutions", , "IFT.AddtoTempSol")
        End Try

        'If Game.Scenario.IFT.AddtoTempSol(CInt(EnemyUnit.hexnum), DFFlevel, DFFLOSIndex, DFFPOsitionInHEx, Movinghexclicked, Movinglevel, MovingLOSIndex, MovingPosition,
        'PassSolWorks, Game.Scenario.IFT.TempSolutions.Count - 1, scenmap) Then
        '    TempSol = CType(Game.Scenario.IFT.TempSolutions.Item(Game.Scenario.IFT.TempSolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        'End If


        'now do LOS check
        If IsNothing (TempSol) Then Return Nothing
        'Linqdata.TempCombatTerrCol.Clear()

        LOSTest = New LOSClassLibrary.ASLXNA.ThreadedLOSCheckC
                NewLOSChecks = New List(Of MapDataClassLibrary.GameLO)
        PassLOSStatus = LOSTest.LOSCheck(TempSol, NewLOSChecks)
        If NewLOSChecks.Count > 0 Then
        ASLMapLink As String = "Scen" & CStr(myScenID) :ASLLOSLink As String = "LOS" & CStr(myScenID)
        'need to pass string value to create terrain collection
        Maptables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(Trim(ASLMapLink), myScenID)
        'Update database table with new LOS results
        For Each LOSCheck As MapDataClassLibrary.GameLO In NewLOSChecks
        'done outside of thread as LINQ to SQL not thread safe
        Maptables.AddNewLOSEntry(LOSCheck)
        Next
        ' TerrCheck As TerrainClassLibrary.ASLXNA.TerrainChecks = New TerrainClassLibrary.ASLXNA.TerrainChecks(Game.Scenario.LocationCol)
        Maptables.UpdateMaptables(ASLMapLink, ASLLOSLink)
        'Then MessageBox.Show("Updated Game and Scenario Location and LOS tables")
        End If
        ' ' ' LOSTest = New LoSCheckClass
        ' ' 'PassLOSStatus = LOSTest.LOSCheck(TempSol, EnemyUnit)

        'use LOS results to detemine concealement impact
        'THIS WORKS BUT NEED TO ADD MORE RULE PRECISION TO THE TESTS BELOW
        If PassLOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.None Then
        ' need to do 16 hex check Or PassLOSStatus = constantclasslibrary.aslxna.LosStatus.BeyondLR Then
        'no LOS so no concealment loss
        Else
        ' need to do 16 hex check Or PassLOSStatus = constantclasslibrary.aslxna.LosStatus.BeyondLR Then
        'start with simple tests: AM and open ground
        MapGeo as
        mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        'can use null values if sure instance already exists
        LOSRange As
        Integer = MapGeo.CalcRange(MovingUnit.BasePersUnit.Hexnum, CInt(EnemyUnit.BasePersUnit.Hexnum), True)
        SideCrossedbyLOS As
        List(Of Integer) = MapGeo.LOSSideEntry(CInt(EnemyUnit.BasePersUnit.Hexnum), MovingUnit.BasePersUnit.Hexnum)
        If LOSRange <=16 Then
        If(MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoved Or MovingUnit.MovingPersUnit.AssaultMove = ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving Or movementstatus = ConstantClassLibrary.ASLXNA.MovementStatus.Connecting)
        And Not (LocationisOG(MovingUnit, SideCrossedbyLOS)) Then
                Else
        Return True
        End If
        Else 'range is greater than 16 hexes so movement in and of itself does not cause concealment loss
        End If
        End If
        Next
        'if gets here then no Enemy LOS strips concealment
        Return False*/
    }
    private boolean LocationisOG(PersUniti MovingUnit, ArrayList<Integer> SideCrossedbyLOS){

        return true;
        /*'called by Me.IsLost
        'checks if unit is in open ground

        'Get the units locatoin
        Getlocs As TerrainClassLibrary.ASLXNA.GetALocationFromMapTable = New
        TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
        UsingLoc As
        MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(MovingUnit.BasePersUnit.LOCIndex)
        'test for open ground
        If(MovingUnit.basepersunit.hexlocation = UsingLoc.Location And UsingLoc.LocIsOG) Or
            (MovingUnit.basepersunit.hexlocation = UsingLoc.OtherTerraininLocation And UsingLoc.OTisOG) Then
        'moving in open ground location; now check position
        If MovingUnit.BasePersUnit.hexPosition = 0 Then Return True 'no position provides cover
        If(MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6)
        Or(MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        Then
        'does LOS cross crestside in use by Unit
        SideConvert As New UtilClassLibrary.ASLXNA.ConversionC :SideInPositionUse As Integer = 0
        If(MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6)
        Then
                SideInPositionUse = SideConvert.CrestSideToSide(MovingUnit.BasePersUnit.hexPosition)
        ElseIf(MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        Then
                SideInPositionUse = SideConvert.WACrestSideToSide(MovingUnit.BasePersUnit.hexPosition)
        End If
        NoCover As Boolean = True
        For Each SideToTest As Integer In SideCrossedbyLOS
        If SideToTest = SideInPositionUse Then
        'position provides cover
        NoCover = False :Exit For
        ElseIf(SideToTest = SideInPositionUse - 1) Or(SideToTest = SideInPositionUse + 1) Then
        Hexsidetype As Integer = Linqdata.Gethexsidetype(SideToTest, MovingUnit.BasePersUnit.Hexnum)
        SideTest = New TerrainClassLibrary.ASLXNA.IsSide(Mapcol)
        If SideTest.IsADepression(Hexsidetype) Then
            NoCover = False :Exit For 'position provides cover
        End If
        End If
        Next
        If NoCover Then Return True 'position provdes no cover
        End If
        If(MovingUnit.BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest1 And MovingUnit.BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest6)
        Or MovingUnit.BasePersUnit.hexPosition = ConstantClassLibrary.ASLXNA.AltPos.ExitedEntrench Or
        MovingUnit.BasePersUnit.hexPosition = ConstantClassLibrary.ASLXNA.AltPos.AboveWire Then Return True
        'position provides no cover
        End If
        'if get here then either location or position provides some cover
        Return False*/
    }
}
