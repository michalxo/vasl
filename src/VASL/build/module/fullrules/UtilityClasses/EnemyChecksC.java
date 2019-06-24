package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;

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

    private boolean EnemyFound = false;
    private int HexToCheck = 0;
    private Location LocationtoCheck;
    private Constantvalues.Nationality FriendlySidevalue = Constantvalues.Nationality.None;
    private Constantvalues.Nationality OtherFriendlysidevalue = Constantvalues.Nationality.None;
    private int Locindexvalue = 0;
    private int myScenID;
    //private LinkedList<GameLocation> Mapcol = new LinkedList<GameLocation>();
    private LinkedList<LocationContentc> myLocationContents = new LinkedList<LocationContentc>();

    //   constructor
    public EnemyChecksC(Location LocToCheck, Constantvalues.Nationality FriendlyNat, int PassScenID){
        LocationtoCheck = LocToCheck;
        FriendlySidevalue = FriendlyNat;
        myScenID = PassScenID;

        ScenarioC Scendet = ScenarioC.getInstance();  // retrieves scenario data
        if (FriendlySidevalue == Scendet.getScendet().getATT1()) {
            OtherFriendlysidevalue = Scendet.getScendet().getATT2();
        } else if (FriendlySidevalue == Scendet.getScendet().getATT2()) {
            OtherFriendlysidevalue = Scendet.getScendet().getATT1();
        } else if (FriendlySidevalue == Scendet.getScendet().getDFN1()) {
            OtherFriendlysidevalue = Scendet.getScendet().getDFN2();
        } else if (FriendlySidevalue == Scendet.getScendet().getDFN2()) {
                OtherFriendlysidevalue = Scendet.getScendet().getDFN1();
        }

        for (PersUniti EnemyUnit: GetAllEnemyUnitsInGame()) {
            if( EnemyUnit.getbaseunit().gethexlocation() == LocationtoCheck) {
                EnemyFound = true;
                myLocationContents.add(new LocationContentc(EnemyUnit.getbaseunit().getUnit_ID(), LocationtoCheck, Constantvalues.Typetype.Personnel));
            }
        }
    }

    public LinkedList<LocationContentc> getLocationContents() {return myLocationContents;}

    /*    'properties
    public ReadOnly Property FriendlySide As Integer
    Get
    Return FriendlySidevalue
    End Get
    End Property

        'methods
        */
    public boolean EnemyinLocationTest() {
        // called by MakeADJACENTDM
        // returns true if enemy present, false if not
        return EnemyFound;
    }

    /*
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
    */
    public boolean EnemyInHexTest(Hex hextouse) {
        GetALocationFromMap Getlocs = new GetALocationFromMap();
        for (Locationi HexLocs : Getlocs.RetrieveLocationsinHex(hextouse)){
            GetLocationContents(HexLocs);
            if (EnemyinLocationTest()) {
                return true;
            }
        }
        //LocationContentsToCheck = null;
        return false;
    }

    private void GetLocationContents(Locationi HexLocs) {
        //LocationContentsToCheck = new ContentsofLocation(HexLocs);
        //LocationContentsToCheck.GetContents();
    }

    public LinkedList<PersUniti> GetAllEnemyUnitsInGame() {
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        LinkedList<PersUniti> EnemyInGame = new LinkedList<PersUniti>();
        CommonFunctionsC comfunc = new CommonFunctionsC(myScenID);
        Constantvalues.Nationality[] Enemies = comfunc.SetEnemy(FriendlySidevalue);
        for (PersUniti UnitInGame: Scencolls.Unitcol) {
            if (UnitInGame.getbaseunit().getNationality() == Enemies[0] || UnitInGame.getbaseunit().getNationality() == Enemies[1]) {
                EnemyInGame.add(UnitInGame);
            }
        }

        // do we need to filter for status such as KIAInf, NotinPlay, HIP

        return EnemyInGame;
    }

    /*public Function GetEnemy(ByRef FirstEnemy As Integer, ByRef SecondEnemy As Integer) As Boolean
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

    public Function EnemyUnitsInAdjacentHexes(ByVal PassStartPosition As Integer) As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
            'called by ObjectChange.selectstatuschange.HOBStatusChange
            'returns list of enemy units in adjacent hexes to a friendly unit
    Dim EnemyToCheck = New List(Of ObjectClassLibrary.ASLXNA.PersUniti) : Dim EnemyConToCheck = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim EnemyNat1 As Integer = 0 : Dim EnemyNat2 As Integer = 0
    GetEnemy(EnemyNat1, EnemyNat2)
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
    Dim ADJLocs = New List(Of MapDataClassLibrary.GameLocation) : Dim DirTest As Integer = 0 : Dim ADJLocList As New List(Of Integer)
    Dim Passhexandloc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromHex(LocationtoCheck)
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
