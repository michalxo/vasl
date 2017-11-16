package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;

import java.util.LinkedList;

public class EnemyChecksC {
    /*'This class meets the SRP - it does one thing: provides information about the enemy
            ' "enemy" is defined as not the specified friendly side
            ' it includes a number separate methods that check for 
            ' a) enemy units in a hex
            ' b) enemy units in a location
            ' c) enemy unit compared to friendly side
            ' d) return nationality values of enemy
            ' e) all enemy units currently in the game
            ' f) enemy units in adjacent hexes
            ' g) opponent is Russian 
            'these methods can be called externally and internally*/
    //private ContentsofLocation LocationContentsToCheck;
    private int HexToCheck = 0;
    private int LocationtoCheck = 0;
    private Constantvalues.Nationality FriendlySidevalue = Constantvalues.Nationality.None;
    private Constantvalues.Nationality OtherFriendlysidevalue = Constantvalues.Nationality.None;
    private int Locindexvalue = 0;
    private int myScenID;
    //private NewMap As UtilWObj.ASLXNA.NewMapDB
    //private LinkedList<GameLocation> Mapcol = new LinkedList<GameLocation>();
    private DataC Linqdata  = DataC.GetInstance();
   // private LinkedList<LocationContent> myPassLocationContents = new LinkedList<LocationContent>();

    //   constructor
    public EnemyChecksC(int LocIndexclicked, Constantvalues.Nationality FriendlyNat, int PassScenID){
        /*NewMap = New UtilWObj.ASLXNA.NewMapDB
        Mapcol = NewMap.GetMapCol
        myScenID = PassScenID;
        if (LocIndexclicked > 0) {  // every location have an index value so PassLocationIndex must be greater than zero
            Dim GetLocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
            Dim UsingLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(LocIndexclicked)
            HexToCheck = UsingLoc.Hexnum
            Locindexvalue = LocIndexclicked
            LocationtoCheck = UsingLoc.LocIndex
        } else {
            GameModule.getGameModule().getChatter().send("Error: locindex value not found in Map table: ContentsOfLocation");
            return;
        }
        FriendlySidevalue = FriendlyNat;
        Scenario Scendet = Linqdata.GetScenarioData(myScenID);  // retrieves scenario data
        if (FriendlySidevalue == Scendet.getATT1()) {
            OtherFriendlysidevalue = Scendet.getATT2();
        } else if (FriendlySidevalue == Scendet.getATT2()) {
            OtherFriendlysidevalue = Scendet.getATT1();
        } else if (FriendlySidevalue == Scendet.getDFN1()) {
            OtherFriendlysidevalue = Scendet.getDFN2();
        } else if (FriendlySidevalue == Scendet.getDFN2()) {
                OtherFriendlysidevalue = Scendet.getDFN1();
        }*/
    }
    /*    'properties
    public ReadOnly Property FriendlySide As Integer
    Get
    Return FriendlySidevalue
    End Get
    End Property
    public ReadOnly Property PassLocationConents As List(Of ObjectClassLibrary.ASLXNA.LocationContent)
    Get
    Return myPassLocationContents
    End Get
    End Property
        'methods
    public Function EnemyinLocationTest() As Boolean
            'called by MovementValidation.New 
                    'returns true if enemy present, false if not
    if IsNothing(LocationContentsToCheck) Then GetLocationContents(LocationtoCheck)
    myPassLocationContents = LocationContentsToCheck.ContentsInLocation
    For Each ItemInHex As ObjectClassLibrary.ASLXNA.LocationContent In LocationContentsToCheck.ContentsInLocation
    if IsUnitEnemy(ItemInHex.ObjID, ItemInHex.TypeID) Then return true;
    Next
    return false; 'if gets here no enemy present
    End Function
    public Function IsUnitEnemy(ByVal UnitID As Integer, ByVal TypeID As Integer) As Boolean
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim Prisoner As Boolean = True : Dim hexunit As ObjectClassLibrary.ASLXNA.PersUniti
    Dim hexVehicle As DataClassLibrary.AFV : Dim hexConceal As ObjectClassLibrary.ASLXNA.PersUniti
    Dim typeIs As Integer = Linqdata.GetTypeOfThing(TypeID)
    Select Case typeIs
    Case ConstantClassLibrary.ASLXNA.Typetype.Personnel, ConstantClassLibrary.ASLXNA.Typetype.Concealment
                    'infantry including concealment/dummies
    hexunit = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = UnitID Select selunit).First
    if Prisoner Then
    if hexunit.BasePersUnit.OrderStatus <> ConstantClassLibrary.ASLXNA.OrderStatus.Prisoner Then Prisoner = False 'once false, stays false - means enemy present
    End if
    if CInt(hexunit.BasePersUnit.Nationality) <> FriendlySide And CInt(hexunit.BasePersUnit.Nationality) <> OtherFriendlysidevalue And Not Prisoner Then
                        'enemy present
    return true;
    End if
    Case ConstantClassLibrary.ASLXNA.Typetype.Vehicle
                    'vehicle
    hexVehicle = Linqdata.VehicleCol.Where(Function(FindVeh) FindVeh.AFVID = UnitID).First
                    'VehTypeClicked = ItemInHex.TypeID
    if (CInt(hexVehicle.OwnerNationality) <> FriendlySide And CInt(hexVehicle.OwnerNationality) <> OtherFriendlysidevalue And Not hexVehicle.Captured) Or
            ((CInt(hexVehicle.OwnerNationality) = FriendlySide Or CInt(hexVehicle.OwnerNationality) = OtherFriendlysidevalue) And hexVehicle.Captured) Then
                        'enemy present
    return true;
    End if
    Prisoner = False
    Case ConstantClassLibrary.ASLXNA.Typetype.Gun
                    'Guns - still to code
    Case ConstantClassLibrary.ASLXNA.Typetype.SW, ConstantClassLibrary.ASLXNA.Typetype.Location
                    'SW or Terrain - do nothing
                            'Case ConstantClassLibrary.ASLXNA.Typetype.Concealment
                            '    'Concealment
                    '    hexConceal = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Concol Where selunit.BasePersUnit.Unit_ID = UnitID Select selunit).First
                            '    Prisoner = False
                            '    if CInt(hexConceal.BasePersUnit.Nationality) <> FriendlySide And CInt(hexConceal.BasePersUnit.Nationality) <> OtherFriendlysidevalue And Not Prisoner Then
                            '        'enemy present
                    '        return true;
                            '    End if
    Case ConstantClassLibrary.ASLXNA.Typetype.WhiteC
                    'anything needed?
    End Select
    return false;  'if get to here then nothing found that is Enemy
    End Function
    public Function EnemyInHexTest() As Boolean
            '
    Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
    For Each HexLocs As MapDataClassLibrary.GameLocation In Getlocs.RetrieveLocationsfromMapTable(HexToCheck, "Hexnum") ' Game.Scenario.TerrainActions.GetLocationsInHex(HexToCheck)
    GetLocationContents(HexLocs.LocIndex)
    if EnemyinLocationTest() Then return true;
    Next
            LocationContentsToCheck = Nothing
    return false;
    End Function
    private Sub GetLocationContents(ByVal locationtoget As Integer)
    LocationContentsToCheck = New UtilWObj.ASLXNA.ContentsofLocation(locationtoget)
            LocationContentsToCheck.GetContents()
    End Sub
    public Function GetEnemy(ByRef FirstEnemy As Integer, ByRef SecondEnemy As Integer) As Boolean
            'called by 
                    'returns the nationality values of the "enemy" side
    Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(myScenID) 'retrieves scenario data
    if FriendlySidevalue = Scendet.ATT1 Or FriendlySidevalue = Scendet.ATT2 Then
            FirstEnemy = CInt(Scendet.DFN1) : SecondEnemy = CInt(Scendet.DFN2)
    } else if FriendlySidevalue = Scendet.DFN1 Or FriendlySidevalue = Scendet.DFN2 Then
            FirstEnemy = CInt(Scendet.ATT1) : SecondEnemy = CInt(Scendet.ATT2)
    } else {
    return false; 'no nationality values set
    End if
    return true;
    End Function
    public Function GetEnemyUnitsFromSpecifiedLocations(ByVal EnemyNat1 As Integer, ByVal EnemyNat2 As Integer, ByVal LOCList As List(Of Integer)) As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
            'called by 
                    'returns a list of enemy units present in selected locations (all or some in a hex, multiple hexes) 
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim Testcol As IQueryable(Of ObjectClassLibrary.ASLXNA.PersUniti) = Scencolls.Unitcol.AsQueryable()
            'Dim nexttestcol As IQueryable(Of ObjectClassLibrary.ASLXNA.PersUniti) = From getnext As ObjectClassLibrary.ASLXNA.PersUniti In Testcol Select getnext
    Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
    Dim Searchstring As String
    For Each Loctouse As Integer In LOCList
    if Trim(Searchstring) <> "" Then Searchstring &= " or "
    Searchstring &= "basepersunit.locindex= " & Loctouse.ToString
            Next
    Dim firstpart As String = "(basepersunit.Nationality=" & EnemyNat1.ToString & " Or " & "basepersunit.Nationality =" & EnemyNat2.ToString & ") And ("
    Dim Finalpart As String = ") and basepersunit.VisibilityStatus =" & Visible.ToString
    Searchstring = firstpart & Searchstring & Finalpart
    Dim UnitsFound As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (Testcol.Where(Searchstring)).ToList
    Return UnitsFound

    End Function
    public Function GetEnemyConFromSpecifiedLocations(ByVal EnemyNat1 As Integer, ByVal EnemyNat2 As Integer, ByVal LOCList As List(Of Integer)) As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
            'called by 
                    'returns a list of enemy ? and contents(units/dummies) present in selected locations ( all or some in a hex, multiple hexes) 
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim Testcol As IQueryable(Of ObjectClassLibrary.ASLXNA.PersUniti) = Scencolls.Unitcol.AsQueryable()
    Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed
    Dim Searchstring As String = ""
    For Each Loctouse As Integer In LOCList
    if Trim(Searchstring) <> "" Then Searchstring &= " or "
    Searchstring &= "basepersunit.locindex= " & Loctouse.ToString
            Next
    Dim firstpart As String = "(basepersunit.Nationality=" & EnemyNat1.ToString & " Or " & "basepersunit.Nationality =" & EnemyNat2.ToString & ") And ("
    Dim Finalpart As String = ") and basepersunit.VisibilityStatus =" & Visible.ToString
    Searchstring = firstpart & Searchstring & Finalpart
    Dim ConFound As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (Testcol.Where(Searchstring)).ToList
    Return ConFound

    End Function
    public Function GetAllEnemyUnitsInGame() As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Dim EnemyToCheck As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim EnemyNat1 As Integer = 0 : Dim EnemyNat2 As Integer = 0
    Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
            'Dim EnemyTest = New EnemyChecksC(UsingLocationIndex, Friendlynationality, myScenID)
    if GetEnemy(EnemyNat1, EnemyNat2) Then
                'EnemyToCheck = Linqdata.GetAllUnitsForOneSide(EnemyNat1, EnemyNat2)
    EnemyToCheck = (From Qu As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where (Qu.BasePersUnit.Nationality = EnemyNat1 Or Qu.BasePersUnit.Nationality = EnemyNat2) AndAlso Qu.BasePersUnit.Hexnum > 0 AndAlso
    Qu.BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible AndAlso Qu.BasePersUnit.OrderStatus <> ConstantClassLibrary.ASLXNA.OrderStatus.KIAInf AndAlso
    Qu.BasePersUnit.OrderStatus <> ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay).ToList
    End if
            'Dim Returnlist As IQueryable(Of ObjectClassLibrary.ASLXNA.PersUniti) = Queryable.AsQueryable(EnemyToCheck)
    if Not IsNothing(EnemyToCheck) Then Return EnemyToCheck } else { Return Nothing
    End Function
    public Function EnemyUnitsInAdjacentHexes(ByVal PassStartPosition As Integer) As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
            'called by ObjectChange.selectstatuschange.HOBStatusChange
            'returns list of enemy units in adjacent hexes to a friendly unit
    Dim EnemyToCheck = New List(Of ObjectClassLibrary.ASLXNA.PersUniti) : Dim EnemyConToCheck = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim EnemyNat1 As Integer = 0 : Dim EnemyNat2 As Integer = 0
    GetEnemy(EnemyNat1, EnemyNat2)
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
    Dim ADJLocs = New List(Of MapDataClassLibrary.GameLocation) : Dim DirTest As Integer = 0 : Dim ADJLocList As New List(Of Integer)
    Dim Passhexandloc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(LocationtoCheck)
    Dim ADJTest As New CombatTerrainClassLibrary.ASLXNA.HexBesideC(Passhexandloc, Nothing, CInt(PassStartPosition))
    ADJLocs = ADJTest.AllADJACENTLocations()
    if Not IsNothing(ADJLocs) Then
    For Each ADJLocation As MapDataClassLibrary.GameLocation In ADJLocs
                    ADJLocList.Add(ADJLocation.LocIndex)
    Next
            EnemyToCheck = GetEnemyUnitsFromSpecifiedLocations(EnemyNat1, EnemyNat2, ADJLocList)
    EnemyConToCheck = GetEnemyConFromSpecifiedLocations(EnemyNat1, EnemyNat2, ADJLocList)
                EnemyToCheck.AddRange(EnemyConToCheck)
    Return EnemyToCheck
    End if

    End Function
    public Function VersusRussians() As Boolean
    Dim Enemy1 As Integer = 0 : Dim Enemy2 As Integer = 0
    GetEnemy(Enemy1, Enemy2)
    if Enemy1 = ConstantClassLibrary.ASLXNA.Nationality.Russians Then return true;
    if Enemy2 = ConstantClassLibrary.ASLXNA.Nationality.Russians Then return true;
    return false;
    End Function*/
}
