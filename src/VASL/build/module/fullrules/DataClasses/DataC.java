package VASL.build.module.fullrules.DataClasses;

/**
 * Created by dougr_000 on 7/18/2017.
 */

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASSAL.build.GameModule;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataC {
    // create as singleton class using singleton pattern
            /*called by Scenario.New and Scenario.OPenScenario
            this class holds all of the routines that process requests to retrieve data from the database
            these routines are called from a variety of places in the classes
            once database design is set, these routines should be separated to be found
            in the proper class, such as Status class*/

    private static DataC Datainstance;
    //remmed out while debugging undo

    private String pScenarioName;
    private int pScenID;
    private Connection asldatacon;
    //    'publicTempCombatTerrCol As New Collection
    public LinkedList<OrderofBattle> Unitcol = new LinkedList<OrderofBattle>();
    public LinkedList<OrderofBattleSW> OBSWcol = new LinkedList<OrderofBattleSW>();
    public LinkedList<LineofBattle> LOBTypeCol = new LinkedList<LineofBattle>();
    public LinkedList<SupportWeapon> SWLOBTypeCol = new LinkedList<SupportWeapon>();
    //public VehicleCol As IQueryable(Of AFV)
    private LinkedList<ScenarioTerrain> pScenFeatcol = new LinkedList<ScenarioTerrain>();
    //public Concealcol As IQueryable(Of Concealment)
    //    'publicUnitsInHex As IQueryable(Of OrderofBattle)
    //public VehiclesInhex As IQueryable(Of AFV)
    //public Unposscol As List(Of Unpossessed)
    //public dataTableMap As New DataTable
    private Constantvalues.IFTResult[][] IFTTable = new Constantvalues.IFTResult[16][12];

    // constructors
    private DataC() {
        // called by MapTables.Getinstance as part of singleton pattern

        //pScenarioName = PassScenname;
       // pScenID = PassScenID;
    }

    public String getScenarioName() {
        return pScenarioName;
    }

    public int getScenID() {
        return pScenID;
    }

    public LinkedList<ScenarioTerrain> getScenFeatcol() {
        return pScenFeatcol;
    }

    // Methods
    public static DataC GetInstance() {
        // called by Game.Scenario.Openscenario and NewScenario and Campaign.OpenCampaign, Campaign.SaveCampaign
        // instantiates object as singleton class - using singleton design pattern

        if (Datainstance == null) {
            Datainstance = new DataC();
        }
        return Datainstance;
    }

    /*public static DataC GetInstance(String PassScenname, int PassScenID) {
        // called by Game.Scenario.Openscenario and NewScenario and Campaign.OpenCampaign, Campaign.SaveCampaign
        // instantiates object as singleton class - using singleton design pattern
        // can pass Scenname as "" and ScenID as 0 when know that Datainstance already exists
        if (Datainstance == null) {
            Datainstance = new DataC(PassScenname, PassScenID);
        }
        return Datainstance;
    }*/

    public void InitializeData() {
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:11825;database=ASLData17;integratedSecurity=true";
        // Declare the JDBC objects.
        asldatacon = null;
        //Statement stmt = null;
        //ResultSet rs = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            asldatacon = DriverManager.getConnection(connectionUrl);
            // create collections of static data drawn from db
            CreateLOBTypeCollection();
            CreateSWLOBTypeCollection();
            CreateIFTTable();
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*if (rs != null) try {
                rs.close();
            } catch (Exception e) {
            }
            if (stmt != null) try {
                stmt.close();
            } catch (Exception e) {
            }*/

        }

    }
    public void closeconnection(){
        if (asldatacon != null) try {
            asldatacon.close();
        } catch (Exception e) {
        }
    }
    public void CreateLOBTypeCollection(){

        String PassLOBName = "";
        Constantvalues.Nationality PassNationality = Constantvalues.Nationality.None;
        int PassOBLink = 0;
        int PassFirePower = 0;
        int PassRange = 0;
        int PassMoraleLevel = 0;
        boolean PassAssaultFire = false;
        boolean PassSprayFire = false;
        boolean PassELR5 = false;
        int PassClass = 0;
        int PassSmoke = 0;
        int PassBrokenML = 0;
        int PassBPV = 0;
        String PassRedTo = "";
        String PassSubTo = "";
        String PassHardTo = "";
        boolean PassSelfRally = false;
        int PassReducesTo = 0;
        int PassSubstitutesTo = 0;
        int PassHardensTo = 0;
        Constantvalues.Utype PassUnitType = null;

        Statement stmt = null;
        ResultSet rs = null;
        LineofBattle AddLOBType=null;
        // Create and execute an SQL statement that returns the data.
        if (asldatacon != null) {
            try {
                String SQL = "SELECT * FROM LineofBattle";
                stmt = asldatacon.createStatement();
                rs = stmt.executeQuery(SQL);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            ConversionC DoConversion = new ConversionC();
            while (rs.next()) {
                PassLOBName = rs.getString(1);
                PassNationality = DoConversion.ConverttoNationality(rs.getInt(2));
                PassOBLink = rs.getInt(3);
                PassFirePower = rs.getInt(4);
                PassRange = rs.getInt(5);
                PassMoraleLevel = rs.getInt(6);
                PassAssaultFire = rs.getBoolean(7);
                PassSprayFire = rs.getBoolean(8);
                PassELR5 = rs.getBoolean(9);
                PassClass = rs.getInt(10);
                PassSmoke = rs.getInt(11);
                PassBrokenML = rs.getInt(12);
                PassBPV = rs.getInt(13);
                PassRedTo = rs.getString(14);
                PassSubTo = rs.getString(15);
                PassHardTo = rs.getString(16);
                PassSelfRally= rs.getBoolean(17);
                PassUnitType = DoConversion.ConverttoUnitType(rs.getInt(18));
                PassReducesTo = rs.getInt(19);
                PassSubstitutesTo = rs.getInt(20);
                PassHardensTo = rs.getInt(21);

                AddLOBType = new LineofBattle(PassLOBName, PassNationality,  PassOBLink,  PassFirePower,  PassRange,  PassMoraleLevel,  PassAssaultFire,  PassSprayFire,
                        PassELR5,  PassClass,  PassSmoke,  PassBrokenML,  PassBPV, PassRedTo, PassSubTo, PassHardTo, PassSelfRally, PassUnitType, PassReducesTo,
                        PassSubstitutesTo, PassHardensTo);
                LOBTypeCol.add(AddLOBType);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        }
    }

    public void CreateSWLOBTypeCollection(){

        int PassID = 0;
        String PassWeaponName = "";
        Constantvalues.SWtype PassWeaponType = null;
        //private String _National As String
        int PassFIREPOWER = 0;
        int PassRANGE = 0;
        int PassPORTAGECOST = 0;
        int PassMALFUNCTION = 0;
        int PassREPAIR = 0;
        int PassROF = 0;
        int PassBREAKDOWN = 0;
        boolean PassASSAULTFIRE = false;
        boolean PassSPRAYINGFIRE = false;
        int PassDismantledPP = 0;
        Constantvalues.Nationality PassNationality = null;

        Statement stmt = null;
        ResultSet rs = null;
        SupportWeapon AddSWType=null;
        // Create and execute an SQL statement that returns the data.
        if (asldatacon != null) {
            try {
                String SQL = "SELECT * FROM SupportWeapons";
                stmt = asldatacon.createStatement();
                rs = stmt.executeQuery(SQL);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            ConversionC DoConversion = new ConversionC();
            while (rs.next()) {
                PassID = rs.getInt(1);
                PassWeaponName = rs.getString(2);
                PassWeaponType = DoConversion.ConverttoSWType(rs.getInt(3));
                PassFIREPOWER = rs.getInt(5);
                PassRANGE = rs.getInt(6);
                PassPORTAGECOST = rs.getInt(7);
                PassMALFUNCTION = rs.getInt(8);
                PassREPAIR = rs.getInt(9);
                PassROF = rs.getInt(10);
                PassBREAKDOWN = rs.getInt(11);
                PassASSAULTFIRE = rs.getBoolean(12);
                PassSPRAYINGFIRE = rs.getBoolean(13);
                PassDismantledPP = rs.getInt(14);
                PassNationality = DoConversion.ConverttoNationality(rs.getInt(15));


                AddSWType = new SupportWeapon(PassID, PassWeaponName,  PassWeaponType,  PassFIREPOWER,  PassRANGE,  PassPORTAGECOST,  PassMALFUNCTION,  PassREPAIR,
                        PassROF,  PassBREAKDOWN,  PassASSAULTFIRE,  PassSPRAYINGFIRE,  PassDismantledPP, PassNationality);
                SWLOBTypeCol.add(AddSWType);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        }
    }

    public void CreateIFTTable(){

        Statement stmt = null;
        ResultSet rs = null;
        Constantvalues.IFTResult AddIFTResult=null;
        // Create and execute an SQL statement that returns the data.
        if (asldatacon != null) {
            try {
                String SQL = "SELECT * FROM LookUpIFT";
                stmt = asldatacon.createStatement();
                rs = stmt.executeQuery(SQL);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            int x=0;
            ConversionC DoConversion = new ConversionC();
            while (rs.next()) {
                IFTTable[x][0] = DoConversion.ConverttoIFTResult(rs.getInt(1));
                IFTTable[x][1] = DoConversion.ConverttoIFTResult(rs.getInt(2));
                IFTTable[x][2] = DoConversion.ConverttoIFTResult(rs.getInt(3));
                IFTTable[x][3] = DoConversion.ConverttoIFTResult(rs.getInt(4));
                IFTTable[x][4] = DoConversion.ConverttoIFTResult(rs.getInt(5));
                IFTTable[x][5] = DoConversion.ConverttoIFTResult(rs.getInt(6));
                IFTTable[x][6] = DoConversion.ConverttoIFTResult(rs.getInt(7));
                IFTTable[x][7] = DoConversion.ConverttoIFTResult(rs.getInt(8));
                IFTTable[x][8] = DoConversion.ConverttoIFTResult(rs.getInt(9));
                IFTTable[x][9] = DoConversion.ConverttoIFTResult(rs.getInt(10));
                IFTTable[x][10] = DoConversion.ConverttoIFTResult(rs.getInt(11));
                x += 1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        }
    }
    //remmed out while debugging undo
    /*publicFunction GetConnectionString(ByVal WhichOne As Integer) As String
            'called by routines in Terraindata to create connection string to use in database connection
                    'Value of WhichOne determines which database connection string is returned
    Select Case WhichOne
    Case 1  'ASLSQL_data
    GetConnectionString = "Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\ASLSQL_data.mdf;Integrated Security=True;Connect Timeout=30;User Instance=true"
    Case 2  'ASLMaps
    GetConnectionString =
            "Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\ASLMaps.mdf;Integrated Security=True;Connect Timeout=30;User Instance=true"
    Case Else
    GetConnectionString =
    InputBox("Enter connection string: ", "Parameter failed in Terraindata.GetConnectionString", , , )
    End Select
    End Function
    publicFunction GetNewScenFeaturenum() As Integer
            'called by CreateNewFeature
                    'pulls all Feature records into a temporary collection of type ScenTerrain and identifies highest value of scenter_id
                    'adds 1 to get new scenario number
    Dim Scenfeaturecol As IQueryable(Of ScenarioTerrain)
    Try
                'gets records
    Scenfeaturecol = From QU In db.ScenarioTerrains
    Catch ex As Exception
    MsgBox("Some kind of error", , "RetieveScenario Features Failure")
    Return Nothing
    End Try
    Dim Maxhexnum As Integer = 0
    For Each ScenFeattest As ScenarioTerrain In Scenfeaturecol
                'compares value with largest and selects new largest
    If ScenFeattest.Scenter_id > Maxhexnum Then Maxhexnum = ScenFeattest.Scenter_id
    Next ScenFeattest
    Return (Maxhexnum + 1)  'creates new largest for next scen value
    End Function
    publicFunction GetNewConIDnum() As Integer
            'called by ConcealUnitC.Conceal
                    'pulls all Concealment records into a temporary collection of type Concealment and identifies highest value of con_id
                    'adds 1 to get new conceal number
    Dim Concol As IQueryable(Of Concealment)
    Try
                'gets records
    Concol = From QU In db.Concealments
    Catch ex As Exception
    MsgBox("Some kind of error", , "RetieveScenario Features Failure")
    Return Nothing
    End Try
    Dim Maxconnum As Integer = 0
            'Concol = CType((From Concealment In Concol).OrderByDescending(Function(Concealment) Concealment.Con_ID).ToList, System.Linq.IQueryable(Of Concealment))
                    'Maxconnum = CInt(Concol.First.Con_ID)
    For Each Contest As Concealment In Concol
                'compares value with largest and selects new largest
    If Contest.Con_ID > Maxconnum Then Maxconnum = CInt(Contest.Con_ID)
    Next Contest
    Return (Maxconnum + 1)  'creates new largest for next scen value
    End Function
    publicFunction GetNewScennum() As Integer
            'called by Gameform.Scenario.SaveScenario
                    'pulls all scenario records into a temporary collection of type Scen and identifies highest value of Scen.scenario
                    'adds 1 to get new scenario number
    Dim Scencol As IQueryable(Of scen)
    Try
                'gets records
    Scencol = From QU In db.scens
    Catch ex As Exception
    MsgBox("Some kind of error", , "RetieveScenarioUnits Failure")
    Return Nothing
    End Try
    Dim Maxhexnum As Integer = 0
    For Each Scentest As scen In Scencol
                'compares value with largest and selects new largest
    If Scentest.ScenNum > Maxhexnum Then Maxhexnum = Scentest.ScenNum
    Next Scentest
    Return (Maxhexnum + 1)  'creates new largest for next scen value
    End Function
    publicFunction GetNewUnitID() As Integer
    Dim Unicol As IQueryable(Of OrderofBattle)
    Try
                'gets records
    Unicol = From QU In db.OrderofBattles
    Catch ex As Exception
    MsgBox("Some kind of error", , "RetieveScenarioUnits Failure")
    Return Nothing
    End Try
    Dim Maxhexnum As Integer = 0
    For Each unitest As OrderofBattle In Unicol
                'compares value with largest and selects new largest
    If unitest.OBUnit_ID > Maxhexnum Then Maxhexnum = unitest.OBUnit_ID
    Next unitest
    Return (Maxhexnum + 1)  'creates new largest for next scen value
    End Function
*/
    public Scenario GetScenarioData(String ScenID) {
        // called by Scenario.OpenScenario - is meant to retrieve scenario record from database - via a stored procedure\function call
        String Scenname = ScenID;

        PreparedStatement prestmt = null;
        ResultSet rs = null;
        // Create and execute an SQL statement that returns the data.
        if (asldatacon != null) {
            try {
                String SQL = "SELECT * FROM scen WHERE FULLNAME = ?";   // '" + Scenname + "'";
                prestmt = asldatacon.prepareStatement(SQL);
                prestmt.setString(1, Scenname);
                rs = prestmt.executeQuery();
                Scenario Sceninfo = new Scenario(rs); // temporary while debugging undo (From QU In db.scens Where QU.ScenNum = ScenID Select QU).First
                pScenarioName = Sceninfo.getFULLNAME();
                pScenID = Sceninfo.getScenNum();
                return Sceninfo;
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (rs != null) try { rs.close(); } catch(Exception e) {}
                if (prestmt != null) try { prestmt.close(); } catch(Exception e) {}
            }
        }
        return null;
    }
    /*public Scenario GetScenarioData(int ScenID) {
        // called by Scenario.OpenScenario - is meant to retrieve scenario record from database - via a stored procedure\function call
        String Scenname = Integer.toString(ScenID);
        Statement stmt = null;
        ResultSet rs = null;
        // Create and execute an SQL statement that returns the data.
        if (asldatacon != null) {
            try {
                String SQL = "SELECT * FROM dbo.scen where dbo.scen.scennum =" + Scenname;
                stmt = asldatacon.createStatement();
                rs = stmt.executeQuery(SQL);
                Scenario Sceninfo = new Scenario(rs); // temporary while debugging undo (From QU In db.scens Where QU.ScenNum = ScenID Select QU).First
                pScenarioName = Sceninfo.getFULLNAME();
                pScenID = Sceninfo.getScenNum();
                return Sceninfo;
            }catch (Exception e) {

            }
            finally {
                if (rs != null) try { rs.close(); } catch(Exception e) {}
                if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            }
        }
        return null;
    }

    public Scenario GetScenarioData(int ScenID) {
        // called by Scenario.OpenScenario - is meant to retrieve scenario record from database - via a stored procedure\function call

        Scenario Sceninfo = new Scenario(pScenarioName); // temporary while debugging undo (From QU In db.scens Where QU.ScenNum = ScenID Select QU).First
        //pScenarioName = ScenName;
        pScenID = Sceninfo.getScenNum();
        return Sceninfo;
    }*/
    public List<Scenario> GetScenList() {
        try {
            // Dim Scenlist = From QU In db.scens Where QU.Finished = False Select QU
            return null; //Scenlist;
        } catch (Exception ex) {
            GameModule.getGameModule().getChatter().send("Some kind of error: Retieve Scenario List Failure");
            return null;
        }

    }

    public LinkedList<OrderofBattle> RetrieveScenarioUnits(int ScenarioID) {
        // called by Unitactions.New
        // pulls all OB units for a given scenario it a collection of type OrderofBattle

        // test code
        Unitcol = CreatetestOBunits();
        return Unitcol;


        // LinkedList<OrderofBattle> Unitcol = new LinkedList<OrderofBattle>();
        /*try {
            // Dim Unitcol = From QU In db.OrderofBattles Where QU.Scenario = ScenarioID And Not
            // QU.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay Select QU
            return Unitcol;
        } catch(Exception ex) {
            GameModule.getGameModule().getChatter().send("Some kind of error" + Integer.toString(ScenarioID) +": RetieveScenarioUnits Failure");
            return null;
        }*/

    }

    private LinkedList<OrderofBattle> CreatetestOBunits() {

        // temporary while debugging; creates units linked to counters on board
        LinkedList<OrderofBattle> Testunits = new LinkedList<OrderofBattle>();

        // test unit: German 467
        OrderofBattle Newunit = new OrderofBattle();
        Newunit.setOBUnit_ID(5678);
        Newunit.setCharacterStatus(Constantvalues.CharacterStatus.NONE);
        Newunit.setCombatStatus(Constantvalues.CombatStatus.None);
        Newunit.setCon_ID(0);
        Newunit.setCX(false);
        Newunit.setELR(3);
        Newunit.setFirstSWLink(0);
        Newunit.setFortitudeStatus(Constantvalues.FortitudeStatus.Normal);
        Newunit.setHexEnteredSideCrossedLastMove(0);
        Newunit.sethexlocation(Constantvalues.Location.Woods);
        Newunit.setHexname("L1");
        Newunit.sethexnum(0);
        Newunit.setLevelinHex(0);
        Newunit.setLOBLink(5);
        Newunit.setLocIndex(0);
        Newunit.setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        Newunit.setNationality(Constantvalues.Nationality.Germans);
        Newunit.setOBName("467B");
        Newunit.setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        Newunit.setPinned(false);
        Newunit.setPosition(Constantvalues.AltPos.None);
        Newunit.setRoleStatus(Constantvalues.RoleStatus.None);
        Newunit.setScenario(1);
        Newunit.setSecondSWlink(0);
        Newunit.setSW(0);
        Newunit.setTurnArrives(0);
        Newunit.setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);

        Testunits.add(Newunit);
        // test unit Russian 447
        Newunit = new OrderofBattle();
        Newunit.setOBUnit_ID(789);
        Newunit.setCharacterStatus(Constantvalues.CharacterStatus.NONE);
        Newunit.setCombatStatus(Constantvalues.CombatStatus.None);
        Newunit.setCon_ID(0);
        Newunit.setCX(false);
        Newunit.setELR(3);
        Newunit.setFirstSWLink(0);
        Newunit.setFortitudeStatus(Constantvalues.FortitudeStatus.Normal);
        Newunit.setHexEnteredSideCrossedLastMove(0);
        Newunit.sethexlocation(Constantvalues.Location.OpenGround);
        Newunit.setHexname("L3");
        Newunit.sethexnum(0);
        Newunit.setLevelinHex(0);
        Newunit.setLOBLink(27);
        Newunit.setLocIndex(0);
        Newunit.setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        Newunit.setNationality(Constantvalues.Nationality.Russians);
        Newunit.setOBName("447Z");
        Newunit.setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        Newunit.setPinned(false);
        Newunit.setPosition(Constantvalues.AltPos.None);
        Newunit.setRoleStatus(Constantvalues.RoleStatus.None);
        Newunit.setScenario(1);
        Newunit.setSecondSWlink(0);
        Newunit.setSW(0);
        Newunit.setTurnArrives(0);
        Newunit.setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);

        Testunits.add(Newunit);
        // testunit Russian 447
        Newunit = new OrderofBattle();
        Newunit.setOBUnit_ID(456);
        Newunit.setCharacterStatus(Constantvalues.CharacterStatus.NONE);
        Newunit.setCombatStatus(Constantvalues.CombatStatus.None);
        Newunit.setCon_ID(0);
        Newunit.setCX(false);
        Newunit.setELR(3);
        Newunit.setFirstSWLink(0);
        Newunit.setFortitudeStatus(Constantvalues.FortitudeStatus.Normal);
        Newunit.setHexEnteredSideCrossedLastMove(0);
        Newunit.sethexlocation(Constantvalues.Location.OpenGround);
        Newunit.setHexname("L3");
        Newunit.sethexnum(0);
        Newunit.setLevelinHex(0);
        Newunit.setLOBLink(27);
        Newunit.setLocIndex(0);
        Newunit.setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        Newunit.setNationality(Constantvalues.Nationality.Russians);
        Newunit.setOBName("447Y");
        Newunit.setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        Newunit.setPinned(false);
        Newunit.setPosition(Constantvalues.AltPos.None);
        Newunit.setRoleStatus(Constantvalues.RoleStatus.None);
        Newunit.setScenario(1);
        Newunit.setSecondSWlink(0);
        Newunit.setSW(0);
        Newunit.setTurnArrives(0);
        Newunit.setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);

        Testunits.add(Newunit);
        // testunit German 467
        Newunit = new OrderofBattle();
        Newunit.setOBUnit_ID(1234);
        Newunit.setCharacterStatus(Constantvalues.CharacterStatus.NONE);
        Newunit.setCombatStatus(Constantvalues.CombatStatus.None);
        Newunit.setCon_ID(0);
        Newunit.setCX(false);
        Newunit.setELR(3);
        Newunit.setFirstSWLink(0);
        Newunit.setFortitudeStatus(Constantvalues.FortitudeStatus.Normal);
        Newunit.setHexEnteredSideCrossedLastMove(0);
        Newunit.sethexlocation(Constantvalues.Location.OpenGround);
        Newunit.setHexname("H2");
        Newunit.sethexnum(0);
        Newunit.setLevelinHex(0);
        Newunit.setLOBLink(5);
        Newunit.setLocIndex(0);
        Newunit.setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        Newunit.setNationality(Constantvalues.Nationality.Germans);
        Newunit.setOBName("467A");
        Newunit.setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        Newunit.setPinned(false);
        Newunit.setPosition(Constantvalues.AltPos.None);
        Newunit.setRoleStatus(Constantvalues.RoleStatus.None);
        Newunit.setScenario(1);
        Newunit.setSecondSWlink(0);
        Newunit.setSW(0);
        Newunit.setTurnArrives(0);
        Newunit.setVisibilityStatus(Constantvalues.VisibilityStatus.Visible);

        Testunits.add(Newunit);
        return Testunits;
    }

    public LinkedList<OBVehicles> RetrieveScenarioVehicles(int ScenarioID) {
        // called by VehicleActionsC()
        // pulls all OB vehicles for a given scenario it a collection of type OBVehicles
        try {
            // Dim Vehiclecol = From QU In db.AFVs _Where QU.Scenario = ScenarioID _Select QU
            // Return Vehiclecol
        } catch (Exception e){
            //MsgBox("Some kind of error" & CStr(ScenarioID), , "RetieveScenarioVehicles Failure")
            //Return Nothing
        }
        return null;
    }
    /*public void RetrieveConcealment(ByVal ScenarioID As Integer) As IQueryable(Of Concealment)
            'called by ConcealActions.New
            'pulls all Concealment for a given scenario it a collection of type Concealment
    Try
    Dim Concol = From QU In db.Concealments _
    Where QU.Scenario = ScenarioID _
    Select QU
    Return Concol
    Catch ex As Exception
    MsgBox("Some kind of error" & CStr(ScenarioID), , "RetieveScenarioConcealment Failure")
    Return Nothing
    End Try
    End Function
    publicFunction RetrieveConcealedUnits(ByVal ConID As Integer) As IQueryable(Of OrderofBattle)
            'called by movementvalidation
            'pulls all Concealed units associated with a specific Concealment counter
    Try
    Dim Concol = From QU In Unitcol _
    Where QU.Con_ID = ConID _
    Select QU
    Return Concol
    Catch ex As Exception
    MsgBox("Some kind of error" & CStr(ConID), , "Retieve Concealed Units Failure")
    Return Nothing
    End Try
    End Function*/
    public LinkedList<OrderofBattleSW> RetrieveScenarioOBSW(int ScenarioID) {
        // called by SWactionsC()
        // pulls all OB SW for a given scenario it a collection of type OrderofBattleSW
        try {
            //Dim SWcol = From QU In db.OrderofBattleSWs Where QU.Scenario = ScenarioID Select QU
            //Return SWcol
        } catch (Exception e) {
            //MsgBox("Some kind of error" & CStr(ScenarioID), , "RetieveScenarioOBSW Failure")
            //Return Nothing
        }
        return null;
    }
    /*publicFunction RetrieveUnpossessed() As IQueryable(Of Unpossessed)
            'called by Unpossactions.New
            'pulls all unpossessed SW into a collection of type Unpossessed
    Try
    Dim UnPcol = From QU In db.Unpossesseds _
    Select QU
    Return UnPcol
    Catch ex As Exception
    MsgBox("Some kind of error: RetieveUnpossessed Failure")
    Return Nothing
    End Try
    End Function*/
    /*publicFunction GetUnitsInHex(ByVal hexnum As Integer, ByVal ScenarioID As Integer) As IQueryable(Of OrderofBattle)
            'called by UnitActions.DetermineWhoIsOnTop
            'puts all units in hex chosen into a collection
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ScenarioName, ScenarioID)
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim UsingHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
            'Dim Terrhex As DataRow = Game.Scenario.TerrainActions.GetHexData(hexnum)
    Dim hexname As String = UsingHex.Hexname
    Try
    Dim UnitLoccol As IQueryable(Of OrderofBattle) = From QU In Unitcol _
    Where QU.Hexname = hexname _
    Select QU
    Return UnitLoccol
    Catch ex As Exception
    MsgBox("Some kind of error" & hexname, , "GetUnitsInHex Failure")
    Return Nothing
    End Try
    End Function
    publicFunction GetSWinhex(ByVal hexnum As Integer) As IQueryable(Of OrderofBattleSW)
            'called by VisibleOccupiedhexes.GetAllspritesinhex
            'pulls all unpossessed SW in a hex into a collection of type Unpossessed
    Try
    Dim SWList = From QU In OBSWcol Where QU.Hexnumber = hexnum Select QU
    Return SWList
    Catch ex As Exception
    MsgBox("Some kind of error: RetieveUnpossessed Failure")
    Return Nothing
    End Try
    End Function
    publicFunction GetConcealedInLocation(ByVal LOcIndex As Integer) As IQueryable(Of Concealment)
            'called by
            'puts all units in a location chosen into a collection
    Try
    Dim ConcealLoccol As IQueryable(Of Concealment) = From QU In Concealcol Where QU.LocIndex = LOcIndex Select QU
    Return ConcealLoccol
    Catch ex As Exception
    MsgBox("Some kind of error", , "GetUnitsInHex Failure")
    Return Nothing
    End Try
    End Function
    publicFunction GetConInhex(ByVal hexnum As Integer) As IQueryable(Of Concealment)
            'called by
            'puts all units in a location chosen into a collection
    Try
    Dim ConcealLoccol As IQueryable(Of Concealment) = From QU In Concealcol Where QU.hexnum = hexnum Select QU
    Return ConcealLoccol
    Catch ex As Exception
    MsgBox("Some kind of error", , "GetUnitsInHex Failure")
    Return Nothing
    End Try
    End Function
    publicFunction GetUnitsInLocation(ByVal LOcIndex As Integer) As IQueryable(Of OrderofBattle)
            'called by
            'puts all units in a location chosen into a collection
    Try
    Dim UnitHexcol As IQueryable(Of OrderofBattle) = From QU In Unitcol Where QU.LocIndex = LOcIndex And QU.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible Select QU
    Return UnitHexcol
    Catch ex As Exception
    MsgBox("Some kind of error", , "GetUnitsInHex Failure")
    Return Nothing
    End Try
    End Function
    publicFunction GetVehiclesInHex(ByVal hexnum As Integer) As IQueryable(Of AFV)
            'called by VehicleActions.DetermineWhoIsOnTop
            'puts all vehicles in hex chosen into a collection
    Try
    Dim VehHexcol As IQueryable(Of AFV) = From QU In VehicleCol _
    Where QU.hexnum = hexnum _
    Select QU
    Return VehHexcol
    Catch ex As Exception
    MsgBox("Some kind of error", , "GetVehiclesInHex Failure")
    Return Nothing
    End Try
    End Function
    */
    public OrderofBattle GetUnitfromCol(int IDtopass) {
        // called by Gamecontrol.determineclickpossibilities and Gameform.pbGameMap_MouseMove
        // add Gameform.DetermineWhoIsOnTop
        // returns specific unit

        // error checking
        if (IDtopass == 0) {
            return null;
        }
        // query
         // TEST
        for (OrderofBattle findunit: Unitcol){
            if (findunit.getOBUnit_ID()== IDtopass) {return findunit;}
        }


        OrderofBattle getunit = null; // (From QU In Unitcol Where QU.OBUnit_ID = IDtopass Select QU).First;
        return getunit;
    }

    /*
        publicFunction GetVehiclefromCol(ByVal IDtopass As Integer) As AFV
                'called by ContextBuilder.MenuFilter
                        'returns specific vehilce

                        'error checking
        If IDtopass = 0 Then
                    'contextshowing = True
        Dim InputStr As String = InputBox("Vehicle ID must be > 1. Use -1 to Quit. Enter valid Vehicle ID: ", "VehicleID is zero")
                    'contextshowing = False
        If CInt(InputStr) = -1 Then Return Nothing
                IDtopass = CInt(InputStr)
        End If
                'query
        Dim getveh As AFV = (From QU In VehicleCol Where QU.AFVID = IDtopass Select QU).First
                'MsgBox("you have chosen " & getveh.AFVName)
        Return getveh
        End Function
        publicFunction GetFeaturefromCol(ByVal IDtopass As Integer) As ScenarioTerrain
                'called by SearchAHex.PutHiddenOnBoard
                        'returns specific scenario terrain feature

                        'error checking
        If IDtopass = 0 Then
                    'contextshowing = True
        Dim InputStr As String = InputBox("Feature ID must be > 1. Use -1 to Quit. Enter valid Feature ID: ", "ScenTerr is zero")
                    'contextshowing = False
        If CInt(InputStr) = -1 Then Return Nothing
                IDtopass = CInt(InputStr)
        End If
                'query
        Dim getfeat As ScenarioTerrain = (From QU In ScenFeatcol Where QU.Scenter_id = IDtopass Select QU).First
                'MsgBox("you have chosen " & getveh.AFVName)
        Return getfeat
        End Function
        publicFunction GetScenTerrInHex(ByVal hexname As String) As IQueryable(Of ScenarioTerrain)
                'called by TerrainActions.DetermineWhichIsOnTop
                'puts all scenario terrain in hex chosen into a collection
        hexname = Trim(hexname)
        Try
        Dim ScenTerHexcol As IQueryable(Of ScenarioTerrain) = From QU In ScenFeatcol _
        Where QU.Hexname = hexname _
        Select QU
        Return ScenTerHexcol
        Catch ex As Exception
        MsgBox("Some kind of error " & hexname, , "GetScenTerrInHex Failure")
        Return Nothing
        End Try
        End Function
        publicFunction GetConcealmentfromCol(ByVal IDtopass As Integer) As Concealment
                'called by
                        'returns specific concealment

                        'error checking
        If IDtopass = 0 Then
                    'contextshowing = True
        Dim InputStr As String = InputBox("ConcealmentID must be > 1. Use -1 to Quit. Enter valid ConcealmentID: ", "UnitID is zero")
                    'contextshowing = False
        If CInt(InputStr) = -1 Then Return Nothing
                IDtopass = CInt(InputStr)
        End If
                'query
        Dim getconceal As Concealment = (From QU In Concealcol _
        Where QU.Con_ID = IDtopass Select QU).First
        Return getconceal
        End Function
        */
    public boolean RemoveConFromCol(int ConToRemove) {
        // called by StatusChange.ElimConcealC.Takeaction
        // eliminates specific concealment item in collection

        // error checking
        if (ConToRemove == 0) {
            GameModule.getGameModule().getChatter().send("ConcealmentID must be > 1. No unit found: UnitID is zero");
            return false;
        }
        // delete query
        // temporary while debugging UNDO
        /*Dim RemoveCon = (From Qu In db.Concealments Where  Qu.Con_ID = idtopass Select Qu).First
        Dim ScenarioID As Integer = RemoveCon.Scenario
            db.Concealments.DeleteOnSubmit(RemoveCon)
            db.SubmitChanges()
            'redo the collection
        Concealcol = RetrieveConcealment(ScenarioID)
            'Concealcol = Concealcol.Where(Function(FindCol) FindCol.Con_ID <> idtopass)*/
        return true;
    }

    public String GetNatInfo(Constantvalues.Nationality Playerside, int InfoToGet) {
        // called by Gameform.setphaseenvironment
        // uses database lookup table to get side name

        /*Dim Getname As LookUpNationality = (From QU In db.LookUpNationalities _
        Where QU.Natint = Playerside Select QU).First
        Select Case InfoToGet
        Case 1
        Dim sidestring As String = Getname.NatString
        Return sidestring
        Case 2
        Dim sidestring As String = Getname.NatPrefix
        Return sidestring
        Case Else
        Return Nothing
        End Select*/
        return "German";
    }

           /*
    publicFunction GetPhasename(ByVal Currentphase As Integer) As String
            'called by Gameform.setphaseenvironment
                    'uses database lookup table to get side name
    Dim getname As LookUpPhase = (From QU In db.LookUpPhases _
    Where QU.PhaseInt = Currentphase Select QU).First
    Dim phasestring As String = getname.PhaseString
    Return phasestring
    End Function
    publicFunction GetContextItems(ByVal CurrentPhase As Integer, ByVal WhatwasClicked As Integer, ByVal attordef As Integer, ByVal TypeClicked As Integer) As List(Of Objectholder)
            'Called by Contextbuilder.new
            'queries the database base using Dynamic Linq Query for Linq to SQL to search
            'phase and unittype specific criteria
            'return list of those things that units of the specified type can do in a specified phase
            'list needs additional filtering to handle location/state specific limits
    Dim ContextItems As New List(Of Objectholder)
    Dim ContextItem As Objectholder
    Dim Phasename As String = Me.GetPhasename(CurrentPhase)
    Dim Selectionname As String = Trim(GetSelectionTypeName(WhatwasClicked, TypeClicked)) ' how else to do this, DB query as with phase
    Dim ATTorDefRole As String = If(attordef = ConstantClassLibrary.ASLXNA.WhatClicked.Attacker, "7999", "7998")
    Dim ColToCheck As String = "(" & Trim(Phasename) & " = 7997 or " & Trim(Phasename) & " = " & ATTorDefRole & ") and " & Selectionname & " = true"
    Dim Getquery = db.LookUpContextMenus.Where(ColToCheck).Select("new(Activity, ActivityName)")
            'Dim Getquery = db.LookUpContextMenus.Where(ColToCheck).Select(Of Objectholder)("new(Activity as Activities, ActivityName as ActivityNames)")
    For Each QueryItem In Getquery
            ContextItem = New Objectholder
    ContextItem.Activities = CType(QueryItem.activity, Integer)
    ContextItem.ActivityNames = QueryItem.activityname
                ContextItems.Add(ContextItem)
    Next
    Return ContextItems
    End Function
    publicFunction GetSelectionTypeName(ByVal WhatClicked As Integer, ByVal TypeClicked As Integer) As String
            'this takes an enum value and provide the appropriate name as a string - used in ContextMenu generation to determine which db col to query
                    'called by Linqdata.GetContextItems
    Select Case WhatClicked
    Case ConstantClassLibrary.ASLXNA.Utype.Crew
    Return "Crew"
    Case ConstantClassLibrary.ASLXNA.Utype.HalfSquad
    Return "HalfSquad"
    Case ConstantClassLibrary.ASLXNA.Utype.MMC
    Return "MMC"
    Case ConstantClassLibrary.ASLXNA.Utype.SMC, ConstantClassLibrary.ASLXNA.Utype.Commissar, ConstantClassLibrary.ASLXNA.Utype.Hero, ConstantClassLibrary.ASLXNA.Utype.LdrHero,
    ConstantClassLibrary.ASLXNA.Utype.Leader, ConstantClassLibrary.ASLXNA.Utype.PathFind, ConstantClassLibrary.ASLXNA.Utype.THHero
    Return "SMC"
    Case ConstantClassLibrary.ASLXNA.Utype.Squad
    Return "Squad"
    Case ConstantClassLibrary.ASLXNA.Utype.Units, ConstantClassLibrary.ASLXNA.Utype.Dummy
    Return "Units"
    Case ConstantClassLibrary.ASLXNA.Vtype.Fullytracked
    Return "FullTrack"
    Case ConstantClassLibrary.ASLXNA.Vtype.Halftracked
    Return "HalfTrack"
    Case ConstantClassLibrary.ASLXNA.Vtype.ArmouredCar, ConstantClassLibrary.ASLXNA.Vtype.Truck, ConstantClassLibrary.ASLXNA.Vtype.Motorcycle
    Return "Wheeled"
    End Select
    End Function*/


    public boolean WriteScenarioData(Scenario scendat) {
        // return if (db.TryToUpdateScendata(scendat),True, False)
        return false;
    }

    public int GetUnpossessedHex(int EquipID, int EquipType) {
        // called by OBSW.Ownerhex
        // gets number of hex in which unpossessed SW or Gun is located

        int Hexnumber = 0;
        // tempoarary while debugging undo
    /*        'query
    Try
            Hexnumber = (From
    Qu In
    db.Unpossesseds Where
    Qu.EquipmentID =
    EquipID And
    Qu.Equipmenttype =
    EquipType _
    Select Qu.hexnum).First
    Catch Ex
    As Exception
    Hexnumber =0
    End Try*/
        return Hexnumber;
    }

    public String GetLOBData(Constantvalues.LOBItem PassLOBItem, int PassLobID) {
        // called by IFT.GetUnitRangeFPAssF, FireUnit.new, TargUnit.New, OBSW.CanStillUseInherentFP
        // is meant to retrieve specific item from LOB table in database
        // LobItem identifies which field to retrieve; LobID identifies which row to use
        String LOBInfo = "";
        switch (PassLOBItem) {
            //remmed out while debugging undo
            /*case UNITTYPE: if (PassLobID <1000) {
                    LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.UnitType).First.ToString;
                } else {
                    LOBInfo = (From QU In db.Leaders Where QU.OBLink = LobID Select QU.UnitType).First.ToString;
                }
                break;
            case FIREPOWER: LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.FirePower).First.ToString;
                break;
            case GETRANGE: if (PassLobID <1000) {
                    LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.Range).First.ToString;
                } else {
                    boolean IsHero = (boolean) From QU In db.Leaders Where QU.OBLink = LobID Select QU.IsAHero).First);
                    if (IsHero) {LOBInfo = "4";} else {LOBInfo = "0";}
                }
                break;
            case ASSAULTFIRE: LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.AssaultFire).First.ToString;
                break;
            case LDRGRADE: LOBInfo = (From QU In db.Leaders Where QU.OBLink = LobID Select QU.Grade).First.ToString;
                break;
            case LDRM: LOBInfo = (From QU In db.Leaders Where QU.OBLink = LobID Select QU.LDRM).First.ToString;
                break;
            case IMAGEUNIT: if (PassLobID <1000) {
                    LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.GOImage).First;
                } else {
                    LOBInfo = (From QU In db.Leaders Where QU.OBLink = LobID Select QU.LdrGoImage).First;
                }
                break;
            case IMAGEBRKUNIT:  if (PassLobID <1000) {
                    LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.BrkImage).First;
                } else {
                    LOBInfo = (From QU In db.Leaders Where QU.OBLink = LobID Select QU.LdrBrkImage).First;
                }
                break;
            case UNITCLASS:  if (PassLobID <1000) {
                    LOBInfo = (From QU In db.LineofBattles Where QU.OBLink = LobID Select QU.Class).First.ToString;
                } else {
                    LOBInfo = CStr(ConstantClassLibrary.ASLXNA.UClass.Elite);
                }
                break;
                */
            case SMOKE: LOBInfo = "1"; // test code (From qu In db.LineofBattles Where qu.OBLink = LobID Select qu.Smoke).First.ToString;
                break;
                /*
            case MORALELEVEL: if (PassLobID <1000) {
                    LOBInfo = (From qu In db.LineofBattles Where qu.OBLink = LobID Select qu.MoraleLevel).First.ToString;
                } else {
                    LOBInfo = (From Qu In db.Leaders Where Qu.OBLink = LobID Select Qu.MoraleLevel).First.ToString;
                }
                break;
            case BPV: if (PassLobID <1000) {
                    LOBInfo = (From qu In db.LineofBattles Where qu.OBLink = LobID Select qu.BPV).First.ToString;
                } else {
                    LOBInfo = "0";
                }
                break;*/
            default:
                LOBInfo = null;
        }

        return LOBInfo;
    }

    public LineofBattle GetLOBRecord(int lobitem) {

       for (LineofBattle Newunit: LOBTypeCol) {
            if (Newunit.getOBLink() == lobitem) {
                return Newunit;
            }
       }
       return null;
    }


    public SupportWeapon GetLOBSWRecord(int lobitem) {
        for (SupportWeapon NewWeapon: SWLOBTypeCol) {
            if (NewWeapon.getID() == lobitem) {
                return NewWeapon;
            }
        }
        return null;

    }
    public LinkedList<LineofBattle> getLOBTypeCol() {return LOBTypeCol;}

    public LinkedList<SupportWeapon> getSWLOBTypeCol() {return SWLOBTypeCol;}

    public String GetLOBVehData(int LOBitem, int LobID) {
        // called by ManageTexture.GetTexture
        // is meant to retrieve specific item from AFVDefault table in database
        // LobItem identifies which field to retrieve; LobID identifies which row to use
        /*Dim LOBInfo As String = ""
            'query
        Select Case
        LOBitem
        Case ConstantClassLibrary.ASLXNA.LOBVeh.VehType
            LOBInfo = (From QU In db.AFVDefaults Where QU.AFVDefaultsID = LobID Select QU.Type).First
        Case ConstantClassLibrary.ASLXNA.LOBVeh.Image
            LOBInfo = (From QU In db.AFVDefaults Where QU.AFVDefaultsID = LobID Select QU.Image).First
        Case(ConstantClassLibrary.ASLXNA.LOBVeh.PP)
            LOBInfo =((From QU In db.AFVDefaults Where QU.AFVDefaultsID = LobID  Select QU.PP).First).ToString
        Case Else
                LOBInfo =""
        End Select
        Return LOBInfo*/
        return null;
    }
    //remmed out while debugging undo
    /*publicFunction IsUnitAPassOrRider(ByVal UnitID As Integer, ByVal TypeTest As Integer) As Boolean
            'called by MovementC.Statusprevents
                    'error checking
    If UnitID = 0 Then
                'Game.contextshowing = True
    Dim InputStr As String = InputBox("UnitID must be > 1. Use -1 to Quit. Enter valid UnitID: ", "UnitID is zero")
                'Game.contextshowing = False
    If CInt(InputStr) = -1 Then Return Nothing
            UnitID = CInt(InputStr)
    End If
            'query
    Dim getunit As PassRider
    Try
            getunit = (From QU In db.PassRiders Where QU.PRID = UnitID And QU.PRType = TypeTest Select QU).First
    Return True
    Catch ex As Exception
    Return False  'no match found
    End Try
    End Function
    publicFunction RetrievePassRider(ByVal PRID As Integer, ByVal AFVID As Integer, ByVal TypeTest As Integer) As PassRider
    Return (From QU In db.PassRiders Where QU.PRID = PRID And QU.PRType = TypeTest And QU.VehicleID = AFVID Select QU).First
    End Function
    publicFunction GetTypeOfThing(ByVal SpecificType As Integer) As Integer
    Select Case SpecificType
    Case 1 To 1999
    Return ConstantClassLibrary.ASLXNA.Typetype.Personnel
    Case 2001 To 2999
    Return ConstantClassLibrary.ASLXNA.Typetype.Vehicle
    Case 3001 To 3999
    Return ConstantClassLibrary.ASLXNA.Typetype.Gun
    Case 4001 To 4999
    Return (ConstantClassLibrary.ASLXNA.Typetype.Concealment)
    Case 5001 To 5999
    Return ConstantClassLibrary.ASLXNA.Typetype.SW
    Case 6001 To 6830, Is > 9000
    Return ConstantClassLibrary.ASLXNA.Typetype.Location
                    'Case Is . 900
                            '    Return EnumCon.Typetype.WhiteC
    Case Else
    Return 0
    End Select
    End Function
    publicFunction IsThingATypeOf(ByVal TypetoTest As Integer, ByVal ItemtoTest As Integer) As Boolean
    Select Case TypetoTest
    Case ConstantClassLibrary.ASLXNA.Typetype.Personnel
    If ItemtoTest > 0 And ItemtoTest < 2000 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.Vehicle
    If ItemtoTest > 2000 And ItemtoTest < 3000 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.Concealment
    If ItemtoTest > 4000 And ItemtoTest < 5000 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.SW
    If ItemtoTest > 5000 And ItemtoTest < 6000 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.Gun
    If ItemtoTest > 3000 And ItemtoTest < 4000 Then Return True
                    'Case EnumCon.Typetype.Terrain
                            '    If (ItemtoTest > 6000 And ItemtoTest < 6700) Or (ItemtoTest > 9000 And ItemtoTest < 40000) Then Return True
                            'Case EnumCon.Typetype.WhiteC
                            '    If ItemtoTest > 9000 And ItemtoTest < 10000 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.Location
    If ItemtoTest > 6000 And ItemtoTest < 6830 Or ItemtoTest > 9000 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.Feature
    If ItemtoTest > 6830 And ItemtoTest < 6900 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.AltPos
    If ItemtoTest > 6900 And ItemtoTest < 6980 Then Return True
    Case ConstantClassLibrary.ASLXNA.Typetype.VisHind
    If ItemtoTest > 6980 And ItemtoTest < 7000 Then Return True
    End Select
    Return False
    End Function

    */
    public Constantvalues.IFTResult GetIFTResult(int FPCol, int FDR) {

        int useCol=0;
        if (FDR <0 ) {FDR = 0;}

        switch(FPCol) {
            case 1:
                useCol=0;
                break;
            case 2:
                useCol=1;
                break;
            case 4:
                useCol=2;
                break;
            case 6:
                useCol=3;
                break;
            case 8:
                useCol=4;
                break;
            case 12:
                useCol=5;
                break;
            case 16:
                useCol=6;
                break;
            case 20:
                useCol=7;
                break;
            case 24:
                useCol=8;
                break;
            case 30:
                useCol=9;
                break;
            case 36:
                useCol=10;
                break;
            default:

        }
        return IFTTable[FDR][useCol];
    }


    public Object GetLOBSWData(Constantvalues.LOBItem SWLOBitem, int SWLOBID) {
        // called by  - is meant to retrieve specific item from SWLOB table in database
        // SWLOBItem is the field to be retrieved, SWLOBID is the weapon identifier

        Object SWLOBInfo;
        switch (SWLOBitem) {
            //remmed out while debugging undo
            /*case FIREPOWER: SWLOBInfo = CInt((From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.FIREPOWER).First);
                break;
            case GETRANGE: SWLOBInfo = CInt((From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.RANGE).First);
                break;
            case ROF: SWLOBInfo = CInt((From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.ROF).First);
                break;
            case PP:  SWLOBInfo = CInt((From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.PORTAGECOST).First);
                break;
            case DMPP: SWLOBInfo = CInt((From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.DismantledPP).First);
                break;
            case SWIMAGE: SWLOBInfo = (From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.SWImage).First;
                break;
            case BROKENSWIMAGE: SWLOBInfo = (From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.BrokenImage).First;
                break;
            case DMSIMAGE: SWLOBInfo = (From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.DMImage).First;
                break;
            case BROKENDMSWIMAGE: SWLOBInfo = (From QU In db.SupportWeapons Where QU.WeaponType = SWLOBID Select QU.BrokenDMImage).First;
                break;*/
            default: SWLOBInfo = null;
                break;
        }
        return SWLOBInfo;
    }
    //remmed out while debugging undo
    /*publicFunction GetOBSWData(ByVal SWOBitem As Integer, ByVal SWOBID As Integer) As String
            'called by  - is meant to retrieve specific item from
                    'OBSW table in database
                    'SWOBItem is the field to be retrieved, SWOBID is the weapon identifier

    Dim SWOBInfo As String = "0"
            'query
    Select Case SWOBitem
    Case ConstantClassLibrary.ASLXNA.OBSWitem.Weapontype
            SWOBInfo = ((From QU In db.OrderofBattleSWs Where QU.OBSW_ID = SWOBID _
    Select QU.WeaponType).First).ToString
    Case ConstantClassLibrary.ASLXNA.OBSWitem.Status
            SWOBInfo = (From QU In db.OrderofBattleSWs Where QU.OBSW_ID = SWOBID _
    Select QU.Status).First.ToString
    Case ConstantClassLibrary.ASLXNA.OBSWitem.Owner
            SWOBInfo = (From QU In db.OrderofBattleSWs Where QU.OBSW_ID = SWOBID _
    Select QU.Owner).First.ToString
    Case ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon
            SWOBInfo = (From QU In db.OrderofBattleSWs Where QU.OBSW_ID = SWOBID _
    Select QU.OBWeapon).First
    End Select
    Return SWOBInfo
    End Function
    */
    public OrderofBattleSW GetOBSWRecord(String OBSWname) {
        // called by IFT.AddtoFireGroupandTargetGroup
        // is meant to retrieve record of firing SW from database

        // query
        OrderofBattleSW OBSWInfo  = null; //(From QU In OBSWcol Where QU.OBWeapon = OBSWname Select QU).First;
        return OBSWInfo;
    }


    public OrderofBattleSW GetOBSWRecord(int OBSWID) {
        // 'called by IFT.AddtoFireGroupandTargetGroup
        // is meant to retrieve record of firing SW from database

        // query
        OrderofBattleSW OBSWInfo  = null; //(From QU In OBSWcol Where QU.OBSW_ID = OBSWID Select QU).First;
        return OBSWInfo;
    }

            /*
        'publicFunction GetTerrainData(ByVal Territem As Integer, ByVal TerrID As Integer) As String
                '    'called by Linqdata.addtocollection - is meant to retrieve specific item from
        '    'Terrain table in database
        '    'TerrID is type of terrain - which record to look at
        '    'Territem is which terrain element to return
            '    Dim TerrInfo As String = ""
            '    'query
        '    Select Case Territem
                '        Case EnumCon.TerrFactor.TEM
                '            TerrInfo = (From QU In db.Terrains Where QU.Terraintype = TerrID _
                '                              Select QU.TEM).First.ToString
                '        Case EnumCon.TerrFactor.LOSHind
                '            TerrInfo = (From QU In db.Terrains Where QU.Terraintype = TerrID _
                '                              Select QU.LOSHindDRM).First.ToString
                '        Case EnumCon.TerrFactor.Desc
                '            TerrInfo = (From QU In db.Terrains Where QU.Terraintype = TerrID _
                '                              Select QU.Terraindesc).First.ToString
                '            'Case EnumCon.TerrFactor.ScenFeature
        '            'If TerrID > 6900 Then   'smoke item
            '            '    'TerrInfo = CStr((From QU In db.Terrains Where QU.Terraintype = TerrID _
            '            '    '              Select QU.Terraindesc).First)
            '            'Else                'other scen feature
            '            '    TerrInfo = CStr((From QU In db.Terrains Where QU.Terraintype = TerrID _
            '            '                      Select QU.Terraindesc).First)
            '            'End If
        '        Case EnumCon.TerrFactor.MF
                '            TerrInfo = (From QU In db.Terrains Where QU.Terraintype = TerrID _
                '                              Select QU.mfcot).First.ToString
                '        Case EnumCon.TerrFactor.Image
                '            TerrInfo = (From QU In db.Terrains Where QU.Terraintype = TerrID _
                '                             Select QU.Image).First.ToString
                '        Case EnumCon.TerrFactor.obstHeight
                '            TerrInfo = (From QU In db.Terrains Where QU.Terraintype = TerrID _
                '                             Select QU.ObstHeight).First.ToString
                '    End Select
                '    Return TerrInfo
                'End Function
                'publicFunction GetLocationsData(ByVal hexnumber As Integer) As IQueryable(Of Location)
                '    'called by TerrainActions.GetLocationsInHex
        '    'pulls locations from the database table

        '    Dim Basehexlocvalue As Integer = 0
                '    Dim LocationsFound As IQueryable(Of Location)
                '    Dim MapHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
                '    With MapHex
                '        Basehexlocvalue = CInt(!terraintype)
                '    End With
                '    LocationsFound = From qu In db.Locations Where qu.Hextype = Basehexlocvalue
                '    Return LocationsFound
                'End Function
                'THIS SHOULD NOW BE HANDLED BY MapDataClass April 13
                'publicFunction GetHexSideData(ByVal Sideitem As Integer, ByVal SideID As Integer) As String
                '    'called by Mapactions.DoesHexsideBlockLOS and
        '    'IFT.CalcFP - is meant to retrieve specific item from hexside table in database
        '    'SideID holds type of hexside - which record to retrieve
        '    'Sideitem holds which hexside attribute to return

            '    Dim SideInfo As String = ""
            '    'query
        '    Select Case Sideitem
                '        Case EnumCon.TerrFactor.Hexsidedesc
                '            SideInfo = (From QU In db.Hexsides Where QU.SideValue = SideID _
                '                              Select QU.Sidedesc).First.ToString
                '        Case EnumCon.TerrFactor.HexsideTEM
                '            SideInfo = (From QU In db.Hexsides Where QU.SideValue = SideID _
                '                              Select QU.SideTEM).First.ToString
                '        Case EnumCon.TerrFactor.HexsideMFcost
                '            SideInfo = (From QU In db.Hexsides Where QU.SideValue = SideID _
                '                              Select QU.mfcost).First.ToString
                '        Case EnumCon.TerrFactor.HexsideImage
                '            SideInfo = (From QU In db.Hexsides Where QU.SideValue = SideID _
                '                                                 Select QU.Image).First.ToString
                '    End Select
                '    Return SideInfo
                'End Function
    publicFunction GetScenFeatDatafromDB(ByVal ScenarioID As Integer) As System.Linq.IQueryable(Of ScenarioTerrain)
            'called by TerrainActions.CreateScenFeatureCol
            'returns Scenario Terrain features in the parameter scenario

    Dim TerrainQuery = From scenfeature In db.ScenarioTerrains _
    Where scenfeature.Scenario = ScenarioID _
    Select scenfeature
    Try
    Dim Queryexists = TerrainQuery.First
    Return TerrainQuery
    Catch ex As Exception
                'MsgBox("Major mapping error", , "Linqdata.GetScenFeatDatafromDB")
    Return Nothing
    End Try
    End Function
        'MOVED TO LOSCLASSLIBRARY.THREADEDLOSCHECK
                'publicFunction MistDustmodifier(ByVal range As Integer, ByRef MistIsLOSH As Boolean, ByRef DustLOSh As Integer, ByVal MistDust As Integer) As Integer
                '    'called  by IFT.Combatdrm
        '    'return mist modifier based on range, -1 is returned if mistdrm =6 and LOS is blocked
        '    'return dust modifier based on range, dust LV never blocks LOS
        '    DustLOSh = 0
                '    'Dim Scendet As scen = GetScenarioData(ScenarioID) 'retrieves scenario data
            '    'With Scendet
        '    'selects mist strength
        '    Dim Dice As New UtilClassLibrary.ASLXNA.DiceC
                '    Select Case MistDust
                '        Case Mist.None And Dust.None
                '            MistIsLOSH = False
                '            Return 0
                '        Case Mist.VLight
                '            MistIsLOSH = False
                '            Return If(range > 30, 5, If(range Mod 6 = 0, CInt(range / 6) - 1, CInt(Int(range / 6))))
                '        Case Mist.Light
                '            MistIsLOSH = False
                '            Return If(range > 30, -1, If(range Mod 6 = 0, CInt(range / 6), CInt(Int(range / 6) + 1)))
                '        Case Mist.Moderate
                '            MistIsLOSH = False
                '            Return If(range > 20, -1, If(range Mod 4 = 0, CInt(range / 4), CInt(Int(range / 4) + 1)))
                '        Case Mist.Heavy
                '            MistIsLOSH = False
                '            Return If(range > 15, -1, If(range Mod 3 = 0, CInt(range / 3), CInt(Int(range / 3) + 1)))
                '        Case Mist.VHeavy
                '            MistIsLOSH = True
                '            Return If(range > 10, -1, If(range Mod 2 = 0, CInt(range / 2), CInt(Int(range / 2) + 1)))
                '        Case Mist.EHeavy
                '            MistIsLOSH = True
                '            Return If(range > 5, -1, range)
                '        Case Dust.Light
                '            MistIsLOSH = False
                '            Return CInt(Int(Dice.Dieroll / 2))
                '        Case Dust.Moderate
                '            MistIsLOSH = False
                '            Return CInt(Math.Ceiling(Dice.Dieroll / 2))
                '        Case Dust.Heavy
                '            MistIsLOSH = True
                '            DustLOSh = CInt(Math.Ceiling(range / 2))
                '            Return If(CInt(Math.Ceiling(range / 2)) >= 6, -1, (CInt(Int(Dice.Dieroll / 2)) + CInt(Math.Ceiling(range / 2))))
                '        Case Dust.VHeavy, Dust.EHeavy
                '            MistIsLOSH = True
                '            DustLOSh = range
                '            If range >= 6 Then
                '                Return -1    'LOS bloced by LOSH
        '            Else
                '                Return If(MistDust = Dust.VHeavy, CInt(Int(Dice.Dieroll / 2)) + range, CInt(Int(Dice.Dieroll / 2)) + range)
                '            End If
                '        Case Else
                '            MistIsLOSH = False
                '            Return 0
                '    End Select
                '    'End With

        'End Function
    publicFunction RedoUnitCol(ByVal ScenID As Integer) As Boolean
    Unitcol = RetrieveScenarioUnits(ScenID)
    If Unitcol.Count = 0 Then
    MsgBox("No scenario units found. Exiting")
    Return False
    End If
    Return True
    End Function

    publicFunction CreateNewDBrecord(ByVal ScenarioNum As Integer, ByVal BoardNumber As Integer) As Boolean
            'Called by Scenario.SaveScenario
                    'this needs to be broadened out from Scenario
                    'to any db table - can it be?

                    'This routine adds a new record to a database table

                    'Version using datacontext.
    Dim NewScendet As New scen
    With NewScendet
                .ScenNum = ScenarioNum
            .FULLNAME = Trim("Mapboard test")   '(GameForm.txbScenname.Text)
            .ATT1 = 2001
            .DFN1 = 2003
            .GT = 6
            .Phase = 1202
            .CURRENTTURN = 1
            .PlayerTurn = ConstantClassLibrary.ASLXNA.WhoCanDo.Defender
            .Map = If(BoardNumber > 1100, CShort(BoardNumber), CShort(1100))
    End With

            db.scens.InsertOnSubmit(NewScendet)
    Try
                db.SubmitChanges()
    Return True
    Catch ex As Exception
    Return False
    End Try
    End Function
    publicFunction CreateNewUnitinDB(ByVal PassCharacterStatus As Integer, ByVal PassCombatStatus As Integer, ByVal PassConID As Integer, ByVal PassCX As Boolean, ByVal PassELR As Integer, ByVal PassFirstSWLink As Integer, ByVal PassFortitudeStatus As Integer,
                                      ByVal PassHexEnteredSideCrossedLastMove As Integer, ByVal PassHexlocation As Integer, ByVal Passhexname As String, ByVal Passhexnum As Integer, ByVal PassLevelInHex As Integer, ByVal PassLobLink As Integer,
                                      ByVal PassLocIndex As Integer, ByVal PassMovementStatus As Integer, ByVal Passnationality As Integer, ByVal PassOBname As String, ByVal PassOrderStatus As Integer, ByVal Passpinned As Boolean, ByVal Passhexposition As Integer,
                                      ByVal PassRoleStatus As Integer, ByVal PassScenario As Integer, ByVal passsecondswlink As Integer, ByVal PassSW As Integer, ByVal PassTurnArrives As Integer, ByVal PassVisibilityStatus As Integer) As OrderofBattle
    Dim NewUnit As New OrderofBattle
    NewUnit.OBUnit_ID = GetNewUnitID()
    With NewUnit
                .CharacterStatus = PassCharacterStatus
            .CombatStatus = PassCombatStatus
            .Con_ID = PassConID
            .CX = PassCX
            .ELR = PassELR
            .FirstSWLink = PassFirstSWLink
            .FortitudeStatus = PassFortitudeStatus
            .HexEnteredSideCrossedLastMove = PassHexEnteredSideCrossedLastMove
            .hexlocation = PassHexlocation
            .Hexname = Passhexname
            .hexnum = Passhexnum
            .LevelinHex = PassLevelInHex
            .LOBLink = PassLobLink
            .LocIndex = PassLocIndex
            .MovementStatus = PassMovementStatus
            .Nationality = Passnationality
            .OBName = PassOBname
            .OrderStatus = PassOrderStatus
            .Pinned = Passpinned
            .Position = Passhexposition
            .RoleStatus = PassRoleStatus
            .Scenario = PassScenario
            .SecondSWlink = passsecondswlink
            .SW = PassSW
            .TurnArrives = PassTurnArrives
            .VisibilityStatus = PassVisibilityStatus
    End With

            db.OrderofBattles.InsertOnSubmit(NewUnit)
    Try
                db.SubmitChanges()
    RetrieveScenarioUnits(PassScenario)  'SHOULD THIS STILL BE REQUIRED? July 2014
    Return NewUnit
    Catch ex As Exception
    Return Nothing
    End Try

    End Function
    publicFunction CreateNewConinDB(ByVal PassCX As Boolean, ByVal PassFortitudeStatus As Integer,
                                     ByVal PassHexEnteredSideCrossedLastMove As Integer, ByVal PassHexlocation As Integer, ByVal Passhexname As String, ByVal Passhexnum As Integer, ByVal PassLevelInHex As Integer,
                                     ByVal PassLocIndex As Integer, ByVal PassMovementStatus As Integer, ByVal Passnationality As Integer, ByVal PassOBname As String, ByVal Passhexposition As Integer,
                                     ByVal PassScenario As Integer) As Concealment
    Dim NewUnit As New Concealment
            'NewUnit.OBUnit_ID = GetNewUnitID()
    With NewUnit
                '.CharacterStatus = PassCharacterStatus
                        '.CombatStatus = PassCombatStatus
                        '.Con_ID created by db
                        .CX = PassCX
            .FortitudeStatus = PassFortitudeStatus
            .HexEnteredSideCrossedLastMove = PassHexEnteredSideCrossedLastMove
            .Hexlocation = PassHexlocation
            .Hexname = Passhexname
            .hexnum = Passhexnum
            .LevelinHex = PassLevelInHex
                '.lOBLink = PassLobLink
                        .LocIndex = PassLocIndex
            .MovementStatus = PassMovementStatus
            .Nationality = Passnationality
            .Concounter = PassOBname
                '.OrderStatus = PassOrderStatus
                        '.Pinned = Passpinned
                        .Position = Passhexposition
                '.RoleStatus = PassRoleStatus
                        .Scenario = PassScenario
                '.SecondSWlink = passsecondswlink
                        '.SW = PassSW
                        '.TurnArrives = PassTurnArrives
                        '.visibilityStatus = PassVisibilityStatus
    End With

            db.Concealments.InsertOnSubmit(NewUnit)
    Try
                db.SubmitChanges()
    RetrieveConcealment(PassScenario)  'SHOULD THIS STILL BE REQUIRED? July 2014
            NewUnit.SetTexture()
    Return NewUnit
    Catch ex As Exception
    Return Nothing
    End Try

    End Function
    publicFunction Gethexsidetype(ByVal hexside As Integer, ByVal hexnumber As Integer) As Integer

    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
    Dim Levelchk = New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
    Dim MapHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(hexnumber, 0)  'Game.Scenario.MapTables.RetrieveHexfromDatatable(hexnumber)
    With MapHex
    Select Case hexside
    Case 1
    Gethexsidetype = CInt(.Hexside1)
    Case 2
    Gethexsidetype = CInt(.Hexside2)
    Case 3
    Gethexsidetype = CInt(.Hexside3)
    Case 4
    Gethexsidetype = CInt(.Hexside4)
    Case 5
    Gethexsidetype = CInt(.Hexside5)
    Case 6
    Gethexsidetype = CInt(.Hexside6)
    Case Else
    MsgBox("Hexside failure")
    Gethexsidetype = 0
    End Select
    End With
    End Function
    publicFunction QuickUpdate() As Boolean
    Try
                'table must have primary key otherwise readonly and submit changes will not work
                        db.SubmitChanges()
    Return True
    Catch ex As Exception
    Return False
    End Try
    End Function
    publicFunction UpdateAfterMove() As Boolean    '  ByVal movementoptionclicked As Integer, ByVal SelectedList As System.Collections.Generic.List(Of MovingObjecttypeinterface)) As Boolean
            'called by all .MoveUpdate functions except in Clearance and PlaceDC classes which use an overload
            'Dim UnitMoved As OrderofBattle
            'Dim OBSWitem As OrderofBattleSW
            'Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ScenarioName, ScenarioID)
            'Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
            'Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
            'Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
            'For Each MovingUnit As MovingObjecttypeinterface In SelectedList
            '    If Not (MovingUnit.IsDummy) Then
            '        UnitMoved = GetUnitfromCol(MovingUnit.ItemID)
            '        UnitMoved.hexnum = CShort(MovingUnit.Currenthex)
            '        If MovingUnit.MFUsed > 0 Then UnitMoved.MovementStatus = ASLXNA.DataC.MovementStatus.Moving
            '        If (movementoptionclicked >= ASLXNA.DataC.UMove.ClearRubble And movementoptionclicked <= ASLXNA.DataC.UMove.ClearRdBlk And movementoptionclicked <> ASLXNA.DataC.UMove.ClearRoadATMine) Or
            '            (movementoptionclicked >= ASLXNA.DataC.UMove.ClearEnterRubble1 And movementoptionclicked <= ASLXNA.DataC.UMove.ClearRdBlk6) Then
            '            'doing clearance attempt; no location change but a status change
            '            Dim UnittoChange As UnitChange = New SetasTIc(UnitMoved.OBUnit_ID)
                    '            UnittoChange.TakeAction()
                    '            Dim GetBaseHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(CInt(UnitMoved.hexnum), 0)
                    '            UnitMoved.hexlocation = MovingUnit.CurrentLocationInHex 'always baseloc when doing mine/rubble clearance
            '        ElseIf MovingUnit.CurrentPositionInHex = ASLXNA.DataC.AltPos.WallAdv Or
                    '        (MovingUnit.CurrentPositionInHex >= ASLXNA.DataC.AltPos.WACrestStatus1 And MovingUnit.CurrentPositionInHex <= ASLXNA.DataC.AltPos.WACrestStatus6) Then
                    '            'claiming wall advantage; not location change but a status change - requires texture change
            '            Dim UnittoChange As UnitChange = New SetasWallAdvc(UnitMoved.OBUnit_ID, MovingUnit.CurrentPositionInHex)
                    '            UnittoChange.TakeAction()
                    '            'now part of TakeAction
            '            'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(UnitMoved.hexnum))
            '            'UnitMoved.hexlocation = CInt(GetBaseHex!terraintype) 'always baseloc when doing WA
            '        ElseIf UnitMoved.HasWallAdvantage And UnitMoved.Position <> MovingUnit.CurrentPositionInHex Then
            '            'forfeiting wall advantage; not location change but a status change - requires texture change
            '            Dim UnittoChange As UnitChange = New RemoveWallAdvc(UnitMoved.OBUnit_ID)
                    '            UnittoChange.TakeAction()
                    '            If UnitMoved.Position <> MovingUnit.CurrentPositionInHex Then UnitMoved.Position = MovingUnit.CurrentPositionInHex
                    '            UnitMoved.hexlocation = MovingUnit.CurrentLocationInHex
                    '            'now part of TakeAction
            '            'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(UnitMoved.hexnum))
            '            'If UnitMoved.hexlocation <= EnumCon.AltPos.CrestStatus1 And UnitMoved.hexlocation >= EnumCon.AltPos.CrestStatus6 Then UnitMoved.hexlocation = CInt(GetBaseHex!terraintype) 'always baseloc when doing WA unless in crest status
            '        Else
            '            UnitMoved.hexlocation = MovingUnit.CurrentLocationInHex
            '            UnitMoved.Position = MovingUnit.CurrentPositionInHex
            '        End If
            '        'Dim GetLocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
            '        Dim LocToUse As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(MovingUnit.Currenthex, MovingUnit.CurrentLocationInHex)
            '        UnitMoved.LocIndex = LocToUse.LocIndex
            '        UnitMoved.LevelinHex = CType(LevelChk.GetLocationPositionLevel(MovingUnit.Currenthex, MovingUnit.CurrentLocationInHex, MovingUnit.CurrentPositionInHex), Short?)
            '        UnitMoved.Hexname = GetLocs.GetnamefromdatatableMap(MovingUnit.Currenthex)
            '        If MovingUnit.UsingDT Then UnitMoved.CX = True
            '        'update any SW counters associated with this unit
            '        Dim OBSWPoss As Integer = 0
                    '        For x As Integer = 1 To 2
                    '            If x = 1 Then OBSWPoss = UnitMoved.FirstSWLink Else OBSWPoss = UnitMoved.SecondSWlink
                    '            If OBSWPoss <> 0 Then  '0 value means no SW
            '                'retrieve type of SW
            '                OBSWitem = GetOBSWRecord(OBSWPoss)
                    '                OBSWitem.Hexnumber = UnitMoved.hexnum
                    '                OBSWitem.Hexlocation = CShort(UnitMoved.hexlocation)
                    '                OBSWitem.Position = CShort(UnitMoved.Position)
                    '                OBSWitem.LocIndex = UnitMoved.LocIndex
                    '            End If
                    '        Next
                    '        If MovingUnit.IsConcealed Then
                    '            Dim ConMoved As Concealment = GetConcealmentfromCol(UnitMoved.Con_ID)
                    '            ConMoved.hexnum = UnitMoved.hexnum
                    '            ConMoved.Hexlocation = UnitMoved.hexlocation
                    '            ConMoved.Position = UnitMoved.Position
                    '            ConMoved.Hexname = UnitMoved.Hexname
                    '            ConMoved.LevelinHex = UnitMoved.LevelinHex
                    '            ConMoved.LocIndex = UnitMoved.LocIndex
                    '            ConMoved.CX = UnitMoved.CX
                    '            ConMoved.MovementStatus = UnitMoved.MovementStatus
                    '            ConMoved.SetTexture()
                    '        End If
                    '    Else  'Dummy
            '        Dim ConMoved As Concealment = GetConcealmentfromCol(MovingUnit.ItemID)
                    '        ConMoved.hexnum = MovingUnit.Currenthex
                    '        If MovingUnit.MFUsed > 0 Then ConMoved.MovementStatus = ASLXNA.DataC.MovementStatus.Moving
                    '        ConMoved.Hexlocation = MovingUnit.CurrentLocationInHex
                    '        If MovingUnit.CurrentPositionInHex = ASLXNA.DataC.AltPos.WallAdv Or
                    '        (MovingUnit.CurrentPositionInHex >= ASLXNA.DataC.AltPos.WACrestStatus1 And MovingUnit.CurrentPositionInHex <= ASLXNA.DataC.AltPos.WACrestStatus6) Then
                    '            'claiming wall advantage; not location change but a status change - requires texture change
            '            Dim UnittoChange As UnitChange = New SetConWAc(CInt(ConMoved.Con_ID))
                    '            UnittoChange.TakeAction()
                    '            'now part of TakeAction
            '            'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(MovingCon.hexnum))
            '            'MovingCon.Hexlocation = CInt(GetBaseHex!terraintype) 'always baseloc when doing WA
            '        ElseIf ConMoved.HasWallAdvantage And ConMoved.Position <> MovingUnit.CurrentPositionInHex Then
            '            'forfeiting wall advantage; not location change but a status change - requires texture change
            '            Dim UnittoChange As UnitChange = New RemoveConWAc(ConMoved)
                    '            UnittoChange.TakeAction()
                    '            'now part of TakeAction
            '            'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(ConMoved.hexnum))
            '            'ConMoved.Hexlocation = CInt(GetBaseHex!terraintype) 'always baseloc when doing WA
            '        Else
            '            ConMoved.Hexlocation = MovingUnit.CurrentLocationInHex
            '            ConMoved.Position = MovingUnit.CurrentPositionInHex
            '        End If
            '        Dim LocToUse As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(MovingUnit.Currenthex, MovingUnit.CurrentLocationInHex)
            '        ConMoved.LocIndex = LocToUse.LocIndex
            '        ConMoved.LevelinHex = CType(LevelChk.GetLocationPositionLevel(MovingUnit.Currenthex, MovingUnit.CurrentLocationInHex, MovingUnit.CurrentPositionInHex), Short?)
            '        ConMoved.Hexname = GetLocs.GetnamefromdatatableMap(MovingUnit.Currenthex)
            '        If MovingUnit.UsingDT Then ConMoved.CX = True
            '    End If
            'Next
    Try
                'table must have primary key otherwise readonly and submit changes will not work
                        db.SubmitChanges()
    Return True
    Catch ex As Exception
    Return False
    End Try
    End Function
        'publicFunction UpdateAfterMoveClDC(ByVal movementoptionclicked As Integer, ByVal SelectedList As System.Collections.Generic.List(Of MovingObjecttypeinterface)) As Boolean
                '    'called by Clearance.moveupdate and PlaceDC.moveupdate - overloads
        '    Dim UnitMoved As OrderofBattle : Dim OBSWitem As OrderofBattleSW
                '    For Each MovingUnit As MovingObjecttypeinterface In SelectedList
                '        If Not (MovingUnit.IsDummy) Then
                '            UnitMoved = GetUnitfromCol(MovingUnit.ItemID)
                '            UnitMoved.hexnum = CShort(MovingUnit.Currenthex)
                '            If movementoptionclicked = ASLXNA.DataC.ContextM.PlaceDC Then
                '                'moving units "drops" DC
        '                Dim OBSWPoss As Integer = 0
                '                For x As Integer = 1 To 2
                '                    If x = 1 Then OBSWPoss = UnitMoved.FirstSWLink Else OBSWPoss = UnitMoved.SecondSWlink
                '                    If OBSWPoss <> 0 Then  '0 value means no SW
        '                        'retrieve type of SW
        '                        OBSWitem = GetOBSWRecord(OBSWPoss)
                '                        If OBSWitem.ISATypeOf(ASLXNA.DataC.SWtype.DemoC) Then
                '                            Dim UnittoChange As UnitChange = New PlaceTheDC(UnitMoved.OBUnit_ID, OBSWitem.OBSW_ID)
                '                            UnittoChange.TakeAction()
                '                            Exit For
                '                        End If
                '                    End If
                '                Next
                '            Else
                '                If (movementoptionclicked >= ASLXNA.DataC.UMove.ClearRubble And movementoptionclicked <= ASLXNA.DataC.UMove.ClearRdBlk) Or
                '                    (movementoptionclicked >= ASLXNA.DataC.UMove.ClearEnterRubble1 And movementoptionclicked <= ASLXNA.DataC.UMove.ClearRdBlk6) Then
                '                    'doing clearance attempt; no location change but a status change
        '                    Dim UnittoChange As UnitChange = New SetasTIc(UnitMoved.OBUnit_ID)
                '                    UnittoChange.TakeAction()
                '                    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(ScenarioName, ScenarioID)
                '                    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
                '                    Dim Levelchk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
                '                    Dim GetBaseHex As MapDataClassLibrary.GameLocation = Levelchk.GetLocationatLevelInHex(CInt(UnitMoved.hexnum), 0)
                '                    'Dim GetBaseHex = Game.Scenario.MapTables.RetrieveHexfromDatatable(CInt(UnitMoved.hexnum))
        '                    UnitMoved.hexlocation = MovingUnit.CurrentLocationInHex 'always baseloc when doing mine/rubble clearance
        '                End If
                '                UnitMoved.LevelinHex = 0 'always baseloc when doing mine/rubble clearance
        '                If MovingUnit.UsingDT Then UnitMoved.CX = True
                '                'update any SW counters associated with this unit
        '                Dim OBSWPoss As Integer = 0
                '                For x As Integer = 1 To 2
                '                    If x = 1 Then OBSWPoss = UnitMoved.FirstSWLink Else OBSWPoss = UnitMoved.SecondSWlink
                '                    If OBSWPoss <> 0 Then  '0 value means no SW
        '                        'retrieve type of SW
        '                        OBSWitem = GetOBSWRecord(OBSWPoss)
                '                        OBSWitem.Hexlocation = CShort(UnitMoved.hexlocation)
                '                        OBSWitem.Position = CShort(UnitMoved.Position)
                '                    End If
                '                Next
                '            End If
                '        End If
                '    Next
                '    Try
                '        'table must have primary key otherwise readonly and submit changes will not work
        '        db.SubmitChanges()
                '        Return True
                '    Catch ex As Exception
                '        Return False
                '    End Try
                'End Function
    publicFunction UpdateVehicleAfterMove(ByVal Vehloading As AFV) As Boolean
            MessageBox.Show(Vehloading.AFVName & " is using " & Vehloading.PPUsing.ToString)
            'UnitMoved = GetUnitfromCol(MovingUnit.ItemID)
    Try
                'table must have primary key otherwise readonly and submit changes will not work
                        db.SubmitChanges()
    Return True
    Catch ex As Exception
    Return False
    End Try
    End Function
    publicFunction UpdateFeatureAfterMove(ByVal Featloading As ScenarioTerrain) As Boolean
    Try
                'table must have primary key otherwise readonly and submit changes will not work
                        db.SubmitChanges()
    Return True
    Catch ex As Exception
    Return False
    End Try
    End Function
    publicFunction CreateNewPassRiders(ByVal Passlist As List(Of PassRider)) As Boolean
    For Each NewPR As PassRider In Passlist
                db.PassRiders.InsertOnSubmit(NewPR)
    Next
            Try
                db.SubmitChanges()
                        MessageBox.Show("New PassRider added")
    Return True
    Catch ex As Exception
                MessageBox.Show("Somehow this failed")
    End Try
    End Function
    publicFunction DeletePassRiders(ByVal Vehloading As AFV, ByVal Passlist As List(Of PassRider)) As Boolean
    For Each NewPR As PassRider In Passlist
    Dim UnitToDel As Integer = NewPR.PRID
    Dim DelPassRider As PassRider = (From QU In db.PassRiders _
    Where QU.PRID = UnitToDel And QU.VehicleID = Vehloading.AFVID _
    Select QU).First
                db.PassRiders.DeleteOnSubmit(DelPassRider)
    Next
            Try
                db.SubmitChanges()
                        MessageBox.Show("Former PassRider deleted")
    Return True
    Catch ex As Exception
                MessageBox.Show("Somehow this failed")
    End Try
    End Function
    publicFunction DeleteUnpossessed(ByVal SWtoDel As Integer) As Boolean
            'called by RecoveringSW.RecoverIt
                    'deletes unpossessed SW which has been recovered

    Dim DelUnposs As Unpossessed = (From QU In db.Unpossesseds _
    Where QU.EquipmentID = SWtoDel Select QU).First
            db.Unpossesseds.DeleteOnSubmit(DelUnposs)
    Try
                db.SubmitChanges()
                        MessageBox.Show("Former Unpossessed deleted")
    Return True
    Catch ex As Exception
                MessageBox.Show("Somehow this failed")
    End Try
            'recreate Iqueryable collection
    Unposscol = Nothing
            Unposscol = CType(Me.RetrieveUnpossessed, List(Of Unpossessed))
    End Function
    publicFunction DeleteScenFeature(ByVal ScenFeatureID As Integer) As Boolean
            'called by ClearanceAttempt.MoveUpdate
    Dim DelFeature As ScenarioTerrain = (From QU In db.ScenarioTerrains _
    Where QU.Scenter_id = ScenFeatureID _
    Select QU).First
    Dim ScenarioID = DelFeature.Scenario
            db.ScenarioTerrains.DeleteOnSubmit(DelFeature)
    Try
                db.SubmitChanges()
                        MessageBox.Show("Cleared Feature deleted")
    Return True
    Catch ex As Exception
                MessageBox.Show("Somehow this failed")
    End Try
            'recreate Iqueryable collection
    ScenFeatcol = Nothing
            ScenFeatcol = Me.GetScenFeatDatafromDB(ScenarioID)
    End Function
        'Moved to ObjectChange as a class April 14
                'publicFunction CreateNewUnpossessed(ByVal OBSW As OrderofBattleSW, ByVal hexnumber As Integer) As Boolean
                '    Dim DroppedSW As New Unpossessed
                '    DroppedSW.EquipmentID = OBSW.OBSW_ID
                '    DroppedSW.Equipmenttype = CInt(OBSW.WeaponType)
                '    DroppedSW.hexnum = hexnumber
                '    If IsNothing(Unposscol) Then Unposscol = New List(Of Unpossessed)
                '    Unposscol.Add(DroppedSW)
                '    db.Unpossesseds.InsertOnSubmit(DroppedSW)
                '    db.SubmitChanges()
                '    Return True
                'End Function
                'publicFunction CreateNewFeature(ByVal hexlocation As Integer, ByVal hexnumber As Integer, ByVal Featurestring As String,
                '                                     ByVal Featuretype As Integer) As Integer

                '    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(Trim(ScenarioName), ScenarioID)
                '    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.CreateMapCollection()
                '    Dim ScenFeature As New ScenarioTerrain
                '    ScenFeature.hexlocation = hexlocation
                '    ScenFeature.Hexnumber = hexnumber
                '    ScenFeature.hexposition = 0 'feature needs to be in a location but never a position
        '    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
                '    ScenFeature.Hexname = GetLocs.GetnamefromdatatableMap(hexnumber)
                '    ScenFeature.Feature = Featurestring
                '    ScenFeature.FeatureType = CShort(Featuretype)
                '    ScenFeature.SetTexture()
                '    ScenFeature.Scenter_id = GetNewScenFeaturenum()
                '    Dim ReturnID As Integer = ScenFeature.Scenter_id
                '    ScenFeature.Scenario = ScenarioID
                '    db.ScenarioTerrains.InsertOnSubmit(ScenFeature)
                '    db.SubmitChanges()
                '    'recreate Iqueryable collection
        '    ScenFeatcol = Nothing
                '    ScenFeatcol = Me.GetScenFeatDatafromDB()
                '    Return ReturnID
                'End Function
    publicFunction CreateNewThingsToDo(ByVal PassToDo As Integer, ByVal Passhexnum As Integer, ByVal Passhexloc As Integer, ByVal PassPlayerTurn As Integer, ByVal ScenarioID As Integer) As Boolean
            'called by Movement.ClearanceAttempt.MoveUpdate
    Dim SomethingToDo As New ThingsToDo
    SomethingToDo.scenario = CShort(ScenarioID)
    SomethingToDo.Phase = CShort(SomethingToDo.DoPhase(PassToDo))
    SomethingToDo.PlayerTurn = CShort(PassPlayerTurn)
    SomethingToDo.hexnum = CShort(Passhexnum)
    SomethingToDo.hexlocation = CShort(Passhexloc)
    SomethingToDo.ToDo = CShort(PassToDo)
    SomethingToDo.ItemID = CShort(SomethingToDo.GetNextId)
            db.ThingsToDos.InsertOnSubmit(SomethingToDo)
            db.SubmitChanges()
    Return True
    End Function
        'These are admin functions used to handle large scale data entry of new terrain and location data
                'not available when game running
                'publicFunction CreateNewTerrain() As Boolean
                '    Dim SomethingToDo As Terrain
                '    Dim y As Integer = 15000
                '    For z = 100 To 800 Step 100
                '        For x = 1 To 96
                '            SomethingToDo = New Terrain
                '            SomethingToDo.Terraintype = CShort(x + y + z)
                '            SomethingToDo.TEM = 0
                '            SomethingToDo.LOSHindDRM = 0
                '            SomethingToDo.mfcot = 1
                '            SomethingToDo.mpcot = 1
                '            SomethingToDo.BypassOK = False
                '            SomethingToDo.bogcheck = False
                '            SomethingToDo.ObstHeight = CShort(0)
                '            db.Terrains.InsertOnSubmit(SomethingToDo)
                '        Next x
                '    Next z
                '    db.SubmitChanges()
                '    Return True
                'End Function
                'publicFunction CreateNewlocation() As Boolean
                '    Dim SomethingToDo As Location
                '    Dim DataToSubmit = New List(Of Location)
                '    Dim y As Integer = 30500
                '    Dim z As Integer = 5174
                '    Dim x As Integer = 0
                '    Dim Pillvalue As Integer = 0
                '    For b = 1 To 480
                '        x += 1
                '        'For x = 1 to 8
            '        Select Case x
            '            Case 1, 2, 3
            '                Pillvalue = 6811
            '            Case 4, 5, 6
            '                Pillvalue = 6822
            '            Case 7, 8, 9
            '                Pillvalue = 6813
            '            Case 10, 11, 12
            '                Pillvalue = 6814
            '            Case 13, 14, 15
            '                Pillvalue = 6815
            '            Case 16
            '                Pillvalue = 6816
            '                'Case 7
            '                '    Pillvalue = 6807
            '                'Case 8
            '                '    Pillvalue = 6808
            '        End Select
            '        'BrSt = 6811
            '        'BrWd = 6812
            '        'BrStSL = 6813
            '        'BrWdSL = 6814
            '        'BrPO = 6815
            '        'BrFt = 6816
            '        'For a As Integer = 1 To 980
            '        y += 1
            '        SomethingToDo = New Location
            '        SomethingToDo.Hextype = y
            '        SomethingToDo.ContainsLocation = y
            '        z += 1
            '        SomethingToDo.PrimKIndex = z
            '        DataToSubmit.Add(SomethingToDo)

            '        SomethingToDo = New Location
            '        SomethingToDo.Hextype = y
            '        SomethingToDo.ContainsLocation = Pillvalue
            '        z += 1
            '        SomethingToDo.PrimKIndex = z
            '        DataToSubmit.Add(SomethingToDo)

            '        'SomethingToDo = New Location
        '        'SomethingToDo.Hextype = y
        '        'SomethingToDo.ContainsLocation = EnumCon.Locations.Pillcellar
        '        'z += 1
            '        'SomethingToDo.PrimKIndex = z
        '        'DataToSubmit.Add(SomethingToDo)
            '        'Next a
        '        'Next x
        '        If x = 16 Then x = 0
                '    Next b
                '    db.Locations.InsertAllOnSubmit(DataToSubmit)
                '    db.SubmitChanges()
                '    Return True
                'End Function
                'publicFunction UpdateTerrainEntry() As Boolean
                '    Dim x = 15000 : Dim z As Integer = 0 : Dim terrinfo As String ': Dim TerrainUpdate As Terrain
        '    Dim a = 800
                '    For y = 1 To 96
                '        z = x + y
                '        terrinfo = (From QU In db.Terrains Where QU.Terraintype = z _
                '                             Select QU.Terraindesc).First.ToString
                '        Dim b As Integer = z + a
                '        Dim TerrainUpdate As Terrain = (From QU In db.Terrains Where QU.Terraintype = b Select QU).First
                '        TerrainUpdate.Terraindesc = terrinfo
                '        db.SubmitChanges()
                '    Next
                'End Function
                'publicFunction CreateBuildingTypes() As Boolean
                '    Dim BuildingList = New List(Of LookupBuilding)
                '    For Each Tertest As Terrain In db.Terrains
                '        If Tertest.Building Then
                '            Dim NewBtype = New LookupBuilding
                '            NewBtype.Buildingtype = Tertest.Terraintype
                '            NewBtype.Cellar = False
                '            NewBtype.Factory = False
                '            NewBtype.Fortified = False
                '            NewBtype.Gutted = False
                '            NewBtype.Interior = False
                '            NewBtype.Manhole = False
                '            NewBtype.Roofless = False
                '            NewBtype.Rubble = False
                '            NewBtype.Stair = 0
                '            NewBtype.Stone = False
                '            NewBtype.Tunnel = False
                '            NewBtype.BypassOK = False
                '            NewBtype.desc = Tertest.Terraindesc
                '            BuildingList.Add(NewBtype)
                '        End If
                '    Next
                '    db.LookupBuildings.InsertAllOnSubmit(BuildingList)
                '    db.SubmitChanges()
                'End Function
    publicFunction GetAllConForOneSide(ByVal SideNat1 As Integer, ByVal SideNat2 As Integer) As IQueryable(Of Concealment)
            'called by
            'returns a list of all concealment for one side in the scenario

    Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
    Return (From Qu As Concealment In Concealcol Where (Qu.Nationality = SideNat1 Or Qu.Nationality = SideNat2) And Qu.hexnum > 0) 'And Qu.VisibilityStatus = Visible)

    End Function
    publicFunction GetAllUnitsForOneSide(ByVal SideNat1 As Integer, ByVal SideNat2 As Integer) As IQueryable(Of OrderofBattle)
            'called by EnemyValuesConcreteC.SetLOSFPdrmValues in DFFMVCPattern and by ConcealmentLoss class
            'returns a list of enemy units present in the scenario

    Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
    Return (From Qu As OrderofBattle In Unitcol Where (Qu.Nationality = SideNat1 Or Qu.Nationality = SideNat2) AndAlso Qu.hexnum > 0 AndAlso Qu.VisibilityStatus = Visible AndAlso Qu.OrderStatus <> ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay AndAlso Qu.OrderStatus <> ConstantClassLibrary.ASLXNA.OrderStatus.KIAInf)

    End Function
    publicFunction GetEnemyUnitsInHex(ByVal EnemyNat1 As Integer, ByVal EnemyNat2 As Integer, ByVal Hexnum As Integer) As IQueryable(Of OrderofBattle)
            'called by EnemyValuesConcreteC.SetLOSFPdrmValues in DFFMVCPattern and by ConcealmentLoss class
            'returns a list of enemy units present in the scenario

    Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
    Return (From Qu As OrderofBattle In Unitcol Where (Qu.Nationality = EnemyNat1 Or Qu.Nationality = EnemyNat2) And Qu.hexnum = Hexnum And Qu.VisibilityStatus = Visible)

    End Function
    publicFunction GetEnemyUnitsFromSpecifiedLocationsInHex(ByVal EnemyNat1 As Integer, ByVal EnemyNat2 As Integer, ByVal LOCList As List(Of Integer)) As IQueryable(Of OrderofBattle)
            'called by
                    'returns a list of enemy units present in some but not all locations in a hex
    Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
    Dim Searchstring As String
    For Each Loctouse As Integer In LOCList
    If Trim(Searchstring) <> "" Then Searchstring &= " or "
    Searchstring &= "locindex= " & Loctouse.ToString
            Next
    Dim firstpart As String = "(Nationality=" & EnemyNat1.ToString & " Or " & "Nationality =" & EnemyNat2.ToString & ") And ("
    Dim Finalpart As String = ") and VisibilityStatus =" & Visible.ToString
    Searchstring = firstpart & Searchstring & Finalpart
    Dim UnitsFound As IQueryable(Of OrderofBattle) = Unitcol.Where(Searchstring)   '.Select(Of OrderofBattle)()
    Return UnitsFound

    End Function

    publicFunction GetSideConInHex(ByVal SideNat1 As Integer, ByVal SideNat2 As Integer, ByVal Hexnum As Integer) As IQueryable(Of Concealment)
            'called by
            'returns a list of enemy units present in the hex

    Return (From Qu As Concealment In Concealcol Where (Qu.Nationality = SideNat1 Or Qu.Nationality = SideNat2) And Qu.hexnum = Hexnum)

    End Function
    publicFunction GetSideConFromSpecifiedLocationsInHex(ByVal EnemyNat1 As Integer, ByVal EnemyNat2 As Integer, ByVal LOCList As List(Of Integer)) As IQueryable(Of Concealment)
            'called by
                    'returns a list of enemy concealed present in some but not all locations in a hex
                    'Dim Visible As Integer = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible
    Dim Searchstring As String
    For Each Loctouse As Integer In LOCList
    If Trim(Searchstring) <> "" Then Searchstring &= " or "
    Searchstring &= "locindex= " & Loctouse.ToString
            Next
    Dim firstpart As String = "(Nationality=" & EnemyNat1.ToString & " Or " & "Nationality =" & EnemyNat2.ToString & ") And ("
            'Dim Finalpart As String = ") and VisibilityStatus =" & Visible.ToString
    Searchstring = firstpart & Searchstring '& Finalpart
    Dim ConFound As IQueryable(Of Concealment) = Unitcol.Where(Searchstring)   '.Select(Of OrderofBattle)()
    Return ConFound

    End Function

        'this class is meant to be temporary while I get rid of Feature objects
    publicFunction Getscenterrinhex(ByVal hexnumber As Integer) As ArrayList
            'add any scenario terrain in this hex
    Dim ScenTerr = New ArrayList
    If Not IsNothing(ScenFeatcol) Then
    For Each ScenFeat As ScenarioTerrain In ScenFeatcol
                    'need to check each ScenFeat as more than one can exist per hex
                            'check for hex match
    If ScenFeat.Hexnumber = hexnumber Then
                        ScenTerr.Add(ScenFeat.FeatureType)
    End If
    Next
    End If
    Return ScenTerr
    End Function*/
}
