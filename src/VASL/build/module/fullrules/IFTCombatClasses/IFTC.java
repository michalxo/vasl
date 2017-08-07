package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.IFTMods;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.LOSClasses.LOSSolution;
import VASL.build.module.fullrules.LOSClasses.TempSolution;
import VASL.build.module.fullrules.LOSClasses.ThreadedLOSCheckC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.Properties;

import java.util.LinkedList;
import java.util.List;

public class IFTC implements IIFTC {
    // Handles determination of combat parameters and combat results
    // Does NOT process combat result; that is done in CombatResolution.vb

    protected LinkedList<PersUniti> TempFireGroup = new LinkedList<PersUniti>(); // holds selected items before LOS confirmed
    protected LinkedList<PersUniti> FireGroup = new LinkedList<PersUniti>();     // holds selected items with LOS
    protected LinkedList<PersUniti> TempTarget = new LinkedList<PersUniti>();   // holds selected items before LOS confirmed
    protected LinkedList<PersUniti> TargGroup = new LinkedList<PersUniti>();   // holds selected items with LOS
    protected LinkedList<TempSolution> TempSolutions = new LinkedList<TempSolution>();  // holds LOScheck before LOS confirmed
    protected LinkedList<LOSSolution> ValidSolutions = new LinkedList<LOSSolution>(); // holds LOSCheck with LOS
    private Constantvalues.Nationality pFirerSide;      // holds value of firing side (Enum Nationality)
    private Constantvalues.Nationality pTargetSide;     // holds value of target side (Enum Nationality)
    private int pFirerSan;  // holds value of firer san (Scenario.attsan or scenario.dfnsan)
    private int pTargetSan;  //holds value of target san
    // private CombatReport As New ReportingEvents
    protected LinkedList<IFTMods> FinalDRMLIst = new LinkedList<IFTMods>();  // holds info about drm for current combat
    private int pTargethex;  // first selected target hex - used to determine if other hexes eligible
    private int pFirerhex;   // first selected Firer hex - used to determine if other hexes eligible
    private int pTargetloc;  // first selected target loc - used to determine if other hexes eligible
    private int pFirerloc; // first selected Firer loc - used to determine if other hexes eligible
    private Constantvalues.AltPos pFirerpos; // first selected Firer pos - used to determine if other hexes eligible
    //public ThreadManagerC ThreadManager = new ThreadManagerC;  // class with combat management methods
    public ThreadedLOSCheckC LOSTest = new ThreadedLOSCheckC();   // loscheckclass
    // public WithEvents DR As UtilClassLibrary.ASLXNA.DiceC        'utility class that handles all variations of DR, dr
    public String DRstring;    // holds info on DR for reporting
    public String FirerSanString;
    public String TargSanString;  // holds info for reporting
    //private CombatResi CombatRes;   // class that processes combat resolution
    private boolean myNeedToResumeResolution;  // toogle for interruptions due to user choice popups (sniper, surrender)
    private int FirerHexes[];       // holds list of firer hexes to be redrawn after combat
    private int TargetHexes[];      // holds list of target hexes to be redrawn after combat
    //private IFTResulti IFTRes;   // class with methods for combat resolution
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();  // class that holds game object collections
    private int pScenID; // holds value of scenario index

    // constructor
    public IFTC(int PassScenID) {
        // called by Scenario.Joinphase
        pFirerSide = Constantvalues.Nationality.None;
        pTargetSide = Constantvalues.Nationality.None;
        pFirerSan = 0;
        pTargetSan = 0;
        pFirerhex = 0;
        pTargethex = 0;
        pFirerloc = 0;
        pTargetloc = 0;
        pFirerpos = Constantvalues.AltPos.None;
        myNeedToResumeResolution = false;
        pScenID=PassScenID;
    }

    public Constantvalues.Nationality getFirerSide() {
        return pFirerSide;
    }

    public Constantvalues.Nationality getTargetSide() {
        return pTargetSide;
    }

    public int getFirerSan() {
        return pFirerSan;
    }

    public int getTargetSan() {
        return pTargetSan;
    }

    public int getTargethex() {
        return pTargethex;
    }

    public void setTargethex(int value) {
        pTargethex = value;
    }

    public int getFirerhex() {
        return pFirerhex;
    }

    public void setFirerhex(int value) {
        pFirerhex = value;
    }

    public int getTargetloc() {
        return pTargetloc;
    }

    public void setTargetloc(int value) {
        pTargetloc = value;
    }

    public int getFirerloc() {
        return pFirerloc;
    }

    public void setFirerloc(int value) {
        pFirerloc = value;
    }

    public Constantvalues.AltPos getFirerpos() {
        return pFirerpos;
    }

    public void setFirerpos(Constantvalues.AltPos value) {
        pFirerpos = value;
    }

    public boolean getNeedtoResumeResolution() {
        return myNeedToResumeResolution;
    }

    // methods
    public boolean InitializeIFTCombat() {
        // called by IFT.FirephasePreparation
        // 'uses playerturn and phase parametets to identify which side is firing and which is target, sets san values

        // elsewhere in program need to handle reverse fire issues - AFV combat
        // INTEGRATE THIS INTO CLASS GETSIDEFORPHASE IN ASLGAME
        boolean test1; boolean test2;
        DataC Linqdata = DataC.GetInstance();
        Scenario Scendet = Linqdata.GetScenarioData(pScenID);
        // temporary while debugging UNDO
        Constantvalues.Phase test3 =  Constantvalues.Phase.PrepFire;       // Scendet.getPhase();
        test1 = (test3 == Constantvalues.Phase.PrepFire);
        test2 = (test3 == Constantvalues.Phase.AdvancingFire);
        Constantvalues.WhoCanDo test4 =    Constantvalues.WhoCanDo.Attacker;        // Scendet.getPTURN();
        if((test4 == Constantvalues.WhoCanDo.Attacker && (test1 || test2)) ||
                (test4 != Constantvalues.WhoCanDo.Attacker && (!test1 && !test2))) {
            // ATTACKER
            pFirerSide = Constantvalues.Nationality.Germans;   // Scendet.getATT1();
            pTargetSide =  Constantvalues.Nationality.British; //Scendet.getDFN1();
            pFirerSan = 2;  // Scendet.getATTSAN();
            pTargetSan = 2;    //Scendet.getDFNSAN();
        } else {
            // Defender
            pFirerSide = Scendet.getDFN1();
            pTargetSide = Scendet.getATT1();
            pFirerSan = Scendet.getDFNSAN();
            pTargetSan = Scendet.getATTSAN();
        }
        return true;

    }

    public void FirePhasePreparation() {
        // called by ASLGame.Scenario.CreatePhaseMVC
        // if new phase is a fire phase then moves game into IFT combat context if not already there

        // needs routine to check if already in combat mode and ask user if wishes to cancel

        //  set combat controls and visual effects
        //SetIFTEnv SetIFTEnvironment = new SetIFTEnv();
        // SetIFTEnvironment.SetToolbox();
        //  set combat context: attacker, defender, SAN values
        InitializeIFTCombat();
    }

    /*public boolean ValidSolutionMatch(TempSolution Tempsolitem, int BringBackid) {
        'called by IFT.DetermineFireSolution
        'compares TempSol to all current Valid Solutions

        ValidSolutionMatch = False 'default value
        For Each ValidSol As LOSClassLibrary.ASLXNA.LOSSolution In Game.Scenario.IFT.ValidSolutions
        If Tempsolitem.SeeLOSIndex = ValidSol.SeeLOSIndex And Tempsolitem.SeenLOSIndex = ValidSol.SeenLOSIndex
        And ValidSol.Solworks = True Then
        'if match the LOS is good; no further check needed
        BringBackid = ValidSol.ID
        Return True
        End If
        Next
    }

    protected void DetermineFireSolution() {
        'called by IFT.ClickedOnNewParticipants and MGandInherentFPSelection.ProcessChoice
        'either add units to existing Fire Solution or create new solution if LOS exists
        'then return to ClickedOnNewParticipants and move to ManageCombatSolutionDetermination for Calc(FP And DRM)
        Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()
        Dim CheckResult As Boolean = False
        Dim MapLOSTabletoGet As String = "LOS" & CStr(Game.Scenario.ScenID)
        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(Game.Scenario.ScenID) 'retrieves scenario data
        Dim MapInUse As Integer = CInt(Scendet.Map)
        Dim SetCleartoTrue As Boolean = False
        Dim BringBackID As Integer = 0

        If TempSolutions.Count > 0 Then
        'new temp solutions exists to test
        'check for existing LOS determination
        For Each TempSolitem As LOSClassLibrary.ASLXNA.TempSolution In TempSolutions
        If ValidSolutions.Count > 0 Then
        'adding to existing solution
        If ValidSolutionMatch (TempSolitem, BringBackID)Then
        'solution already exists
        AddtoFireGroupandTargetGroup(BringBackID)
        'clear TempSol and other Temps
        TempSolitem.ID = BringBackID
        TempSolitem.Solworks = True
        SetCleartoTrue = True
        Continue For
        Else
        If TempSolitem.ID = 0 Then TempSolitem.ID = ValidSolutions.Count
        End If
        End If
        'no match in ValidSolution
        'so check LOS database table
        'need to clear TempCombatTerrcol at this point to ensure create valid list for each Fire solution (unique LOS)
        LOSTest.TempCombatTerrCol.Clear() 'Linqdata.TempCombatTerrCol.Clear()

        Dim NewChecks = New List(Of MapDataClassLibrary.GameLO)
        Dim PassLOSStatus As Integer = LOSTest.LOSCheck(TempSolitem, NewChecks)
        If PassLOSStatus <>constantclasslibrary.aslxna.LosStatus.None Then
        TempSolitem.Solworks = True
        Else
        TempSolitem.Solworks = False
        SetCleartoTrue = True
        End If
        Next TempSolitem
        For Each TempSolitem As LOSClassLibrary.ASLXNA.TempSolution In TempSolutions
        If TempSolitem.Solworks = False Then
        'if one LOS is invalid, don' t add any Tempsolutions or units or terrain to valid solutions
        'trigger reporting event
        CombatReport.ShowAddFailure(TempFireGroup, TempTarget)
        'clear Temp variables
        If SetCleartoTrue Then ClearTempCombat ()
        'if no valid solutions clear combat
        If ValidSolutions.Count = 0 Then ClearCurrentIFT ()
        'end process
        Exit Sub
        End If
        Next TempSolitem
        'at this point all Temp Solutions are valid so add TempSolutions, TempUnuits and TempCombatTerr to valid solutions
        For Each TempSolitem As LOSClassLibrary.ASLXNA.TempSolution In TempSolutions
        'TempSol to ValidSol
        AddtoValidSolutions(TempSolitem)
        'add firer(s) to FireGroup, target(s) to Target Group
        AddtoFireGroupandTargetGroup(TempSolitem.ID)
        'clear Temps
        SetCleartoTrue = True
        Next TempSolitem
        For Each Usealthex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In LOSTest.TempAltHexLOSGroup
        ThreadManager.TempAlthexcol.Add(Usealthex)
        Next
        ThreadManager.AddtoAltHexLOSGroup()
        Else
        'no new temp solution to test - here due to error in IFT.IsThereASolutionToTest
        'clear temps and preserve existing solutions
        SetCleartoTrue = True
        End If
        If SetCleartoTrue Then ClearTempCombat ()

    }

    protected boolean ConfirmValidFG(List<PersUniti> FireGroup) {
        'called by IFT.ManageCombatSolutionDetermination
        'used check valid FG configuration
        Dim ldrpresent As Boolean = False :Dim Unitpresent As Boolean = False
        'First test: no leaders with just leaders
        For Each TempCombathex As ObjectClassLibrary.ASLXNA.CombatTerrain In LOSTest.TempCombatTerrCol
        'Game.Linqdata.TempCombatTerrCol
        ldrpresent = False :Unitpresent = False
        If TempCombathex.Hexrole = ConstantClassLibrary.ASLXNA.Hexrole.Firer Or
        TempCombathex.Hexrole = ConstantClassLibrary.ASLXNA.Hexrole.FirerInt Or
        TempCombathex.Hexrole = ConstantClassLibrary.ASLXNA.Hexrole.FirerTargetInt Or
        TempCombathex.Hexrole = ConstantClassLibrary.ASLXNA.Hexrole.FirerTarget Then
        'check each hex in Combat terrain collection; if a firer
        'hex, need more than a ldr to be part of FG
        For Each FiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        If(FiringUnit.BasePersUnit.Unittype = ConstantClassLibrary.ASLXNA.Utype.Leader And
                TempCombathex.HexID = FiringUnit.BasePersUnit.Hexnum) Or
                (FiringUnit.BasePersUnit.Unittype = ConstantClassLibrary.ASLXNA.Utype.LdrHero And
                        FiringUnit.FiringPersUnit.UseHeroOrLeader = ConstantClassLibrary.ASLXNA.Utype.Leader And
                        TempCombathex.HexID = FiringUnit.BasePersUnit.Hexnum) Then
                ldrpresent = True
        ElseIf TempCombathex.HexID = FiringUnit.BasePersUnit.Hexnum Then
                Unitpresent = True
        End If
        Next FiringUnit
        If ldrpresent = True And Unitpresent = False Then
        'only ldr present - can' t be part of FG
        MsgBox(String.Format("Only leader present in hex {0}. Can't be part of FG", TempCombathex.HexName))
        ConfirmValidFG = False
        Exit Function
        ElseIf ldrpresent = False And Unitpresent = False Then
        'no units in hex!!
        MsgBox(String.Format("No units present in hex {0}. Can't be part of FG", TempCombathex.HexName))
        ConfirmValidFG = False
        Exit Function
        End If
        End If
        Next TempCombathex
        ConfirmValidFG = True
    }

    protected boolean AddTargetUnit(PersUniti Selunit) {
        'called by IFT.clickedonnewparticipants
        'confirms that unit is eligible to join target group and then adds to TempTarget

        Dim MapGeo as mapgeoclasslibrary.
        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        'can use null values if sure instance already exists
        Dim GroupAddOk As Boolean = False 'toggle to signal if unit can be part of Target group
        If TargetHex = Nothing Or TargetHex = 0 Then
        'first target in group, set target hex and add to TempTarget
        TargetHex = CInt(Selunit.BasePersUnit.Hexnum)
        Targetloc = CInt(Selunit.BasePersUnit.hexlocation)
        If AddtoTempTarget (Selunit) Then Return True Else Return False 'true if add ok; false if add fails
        Else
        'determine if eligible to join existing selection
        'not already added; to add additional units, must be adjacent so check range
        Dim NewTarget As Integer = CInt(Selunit.BasePersUnit.Hexnum) :Dim TestList As List (Of
        ObjectClassLibrary.ASLXNA.PersUniti)
        If TempTarget.Count > 0 Then 'have not yet clicked on attacker or resolved first LOS
        TestList = TempTarget
        ElseIf TargGroup.Count > 0 Then 'defender clicked and one LOS confirmed already
        TestList = TargGroup
        Else
        'error because not first Target but no other units in targetgroups
        MsgBox("Failure to Add Target Unit", , "AddTargetUnit")
        Return False
        End If
        'check new unit against items already selected
        For Each ExistingUnit As ObjectClassLibrary.ASLXNA.PersUniti In TestList
        If ExistingUnit.BasePersUnit.Unit_ID = Selunit.BasePersUnit.Unit_ID And ExistingUnit.
        BasePersUnit.TypeType_ID = Selunit.BasePersUnit.TypeType_ID Then 'already added
        MsgBox("Failure to Add Target Unit as already added", , "AddTargetUnit")
        Return False
        Else
        Dim UnitDistance = MapGeo.CalcRange(ExistingUnit.BasePersUnit.Hexnum, NewTarget, True)
        If UnitDistance = 1
        And(System.Math.Abs(CInt(ExistingUnit.BasePersUnit.LevelinHex) - CInt(Selunit.BasePersUnit.LevelinHex)) = 0)
        Then 'is adjacent so ok
        GroupAddOk = True
        Exit For
        ElseIf UnitDistance = 0 Then
        'If Mainform.Scenario.Map.IsSameHexLOSClear(FiringUnit.ID, Unititem.OBUnit_ID) Then
        If System.Math.Abs(CInt(ExistingUnit.BasePersUnit.LevelinHex) - CInt(Selunit.BasePersUnit.LevelinHex)) <= 1 Then
        'is adjacent so ok
        GroupAddOk = True
        Exit For
        End If
        End If
        End If
        Next
        'if eligible then add
        If GroupAddOk Then
        'add new firer to TempFireGroup array
        Return If (AddtoTempTarget(Selunit), True, False)
        Else
        MessageBox.Show(Trim(Selunit.BasePersUnit.UnitName) & " cannot be part of multiple target locations. Not Adjacent (A7.5)", "Fire Routine")
        Return False
        End If
        End If


    }

    // overloaded
    protected boolean AddFirerUnit(PersUniti Unititem) {
        'called by IFT.Clickonnewparticipants
        'determines what unit and/or MG should be added and sends to ProcessAddFirer (some via context popup click)
        Dim Selectionmade As Integer = ConstantClassLibrary.ASLXNA.CombatSel.InhOnly :Dim Result As Boolean
        'do status checks - SHOULD THIS BE HERE OR ELSEWHERE?
        If Not Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken And Not
        Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken_DM And Not
        Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Disrupted And
        Not Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.DisruptedDM Then
        If Unititem.BasePersUnit.SW<> 0 Then 'need to add SW if part of fire and determine impact on inherent FP
        Dim MGIFPSel As New MGandInherentFPSelection
                Selectionmade = MGIFPSel.ShowChoices(Unititem)
        'determine which combo of Inherent and MG are being added - via popup and send result to ProcessAddFirer
        If Selectionmade = ConstantClassLibrary.ASLXNA.CombatSel.None
        Or Selectionmade = ConstantClassLibrary.ASLXNA.CombatSel.ShowMenu Then Return False
        End If
        Result = ProcessAddFirer(Unititem, Selectionmade)
        Else
                Result = False
        End If
        If Result = False Then
        MessageBox.Show("Failure to Add Firer Unit: " & Trim(Unititem.BasePersUnit.UnitName), "ClickedOnNewParticipants: Add New Firer")
        Return False
        Else
        Return True
        End If
    }

    protected boolean AddFirerUnit(VisibleOccupiedhexes OH) {
        'called by ClickLeftAlt mouse events
        'determines what unit to include and sends to ProcessAddFirer
        Dim Selectionmade As Integer = ConstantClassLibrary.ASLXNA.CombatSel.InhOnly :Dim Result As Boolean = False
        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
        'do status checks - SHOULD THIS BE HERE OR ELSEWHERE?
        For Each displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, displaysprite.TypeID)
        AndAlso displaysprite.Selected Then
        Dim Unititem As ObjectClassLibrary.ASLXNA.PersUniti = (From getunit As ObjectClassLibrary.ASLXNA.PersUniti In
        Scencolls.Unitcol Where getunit.BasePersUnit.Unit_ID = displaysprite.ObjID Select getunit).First
        If Not Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken And Not
        Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken_DM And Not
        Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Disrupted And
        Not Unititem.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.DisruptedDM Then
                Result = ProcessAddFirer(Unititem, Selectionmade)
        Else
        MessageBox.Show("Failure to Add Firer Unit: " & Trim(Unititem.BasePersUnit.UnitName), "ClickLeftAlt: Add New Firer")
        End If
        End If
        Next
        If Result = False Then
        Return False
        Else
        Return True
        End If
    }

    protected boolean ProcessAddFirer(PersUniti Unititem, Optional ByVal Selectionmade As Integer=ConstantClassLibrary.ASLXNA.CombatSel.InhOnly) {
        'called by AddFirerUnit or Contextpopup selection
        'confirms that unit is eligible to be part of fire group and then adds to TempFireGroup
        Dim MapGeo as mapgeoclasslibrary.
        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        'can use null values if sure instance already exists
        Dim GroupAddOk As Boolean = False 'toggle to signal if unit can be part of FG
        If FirerHex = Nothing Or FirerHex = 0 Then
        'first firer in group, set fire hex and add to TempFG
        FirerHex = CInt(Unititem.BasePersUnit.Hexnum)
        Firerloc = CInt(Unititem.BasePersUnit.hexlocation)
        ProcessAddFirer = If(AddtoTempFG(Unititem, Selectionmade), True, False)
        Else
        'determine if eligible to join existing selection
        'to add additional units, must be adjacent
        'check range
        Dim NewFir As Integer = CInt(Unititem.BasePersUnit.Hexnum) :Dim TestList As List (Of
        ObjectClassLibrary.ASLXNA.PersUniti)
        'start with TempFG
        If TempFireGroup.Count > 0 Then 'have not yet clicked on defender or resolved first LOS
        TestList = TempFireGroup
        ElseIf FireGroup.Count > 0 Then 'defender clicked and one LOS confirmed already
        TestList = FireGroup
        Else
        'error because not first firer but no other units in firegroup
        MsgBox(Trim(Unititem.BasePersUnit.UnitName) & " cannot be part of Fire Group. Unknown error", , "ProcessAddFirer")
        Return False
        End If
        For Each TestFiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In TestList
        If TestFiringUnit.BasePersUnit.Unit_ID = Unititem.BasePersUnit.Unit_ID
        And(ConstantClassLibrary.ASLXNA.Typetype.Personnel = TestFiringUnit.BasePersUnit.TypeType_ID) Then Return False
        'already added
        Dim UnitDistance = MapGeo.CalcRange(TestFiringUnit.BasePersUnit.Hexnum, NewFir, True)
        If UnitDistance = 1
        And(System.Math.Abs(CInt(TestFiringUnit.BasePersUnit.LevelinHex) - CInt(Unititem.BasePersUnit.LevelinHex)) = 0)
        Then
                GroupAddOk = True
        Exit For
        ElseIf UnitDistance = 0 Then
        'If Mainform.Scenario.Map.IsSameHexLOSClear(FiringUnit.ID, Unititem.OBUnit_ID) Then
        If System.Math.Abs(CInt(TestFiringUnit.BasePersUnit.LevelinHex) - CInt(Unititem.BasePersUnit.LevelinHex)) <= 1
        Then
                GroupAddOk = True
        Exit For
        End If
        End If
        Next
        'if eligible then add
        If GroupAddOk Then
        'add new firer to TempFireGroup array
        ProcessAddFirer = If(AddtoTempFG(Unititem, Selectionmade), True, False)
        Else
        MsgBox(Trim(Unititem.BasePersUnit.UnitName) & _
                " cannot be part of Fire Group. Not Adjacent (A7.5)", , "Fire Routine")
        ProcessAddFirer = False
        End If
        End If

    }

    protected boolean IsThereASolutiontoTest(int WhichOne) {
        'called by IFT.ClickedOnNewParticipants and MGandInherentFPSelection.ProcessChoice
        'after code returns from IFT.AddFirerUnit or IFT.AddTargetUnit
        'creates or gets Tempsolution or Solution as required and adds new selected units to it

        'NEED TO BREAK THIS UP INTO SEPARATE ROUTINES
        IsThereASolutiontoTest = False
        Dim AddYes As Boolean :Dim PassFirerhexnum As Integer = 0 :Dim PassFirerlevelinhex As Single = 0 :
        Dim PassFirerPositionInHex As Integer = 0
        Dim PassFirerLosIndex As Single = 0 :Dim PassSolworks As Boolean = False :Dim SolIDforFirer As Integer = 0
        Dim Scendet As DataClassLibrary.scen = Game.Linqdata.GetScenarioData(Game.Scenario.ScenID)
        'retrieves scenario data
        Dim scenmap As Integer = CInt(Scendet.Map)
        'select if Firer or Target being added
        If WhichOne = ConstantClassLibrary.ASLXNA.CombatStatus.Firing Then
        'retrieve most recently created TempFiringUnit to get data for solution
        Dim TempFiringUnit As ObjectClassLibrary.
        ASLXNA.PersUniti = CType(TempFireGroup.Item(TempFireGroup.Count - 1), ObjectClassLibrary.ASLXNA.PersUniti)
        With TempFiringUnit
        AddYes = False
        'put LOS-related info into variables to use in creating TempSolution
        PassFirerhexnum = TempFiringUnit.BasePersUnit.Hexnum
        PassFirerlevelinhex = TempFiringUnit.BasePersUnit.LevelinHex
        PassFirerLosIndex = TempFiringUnit.BasePersUnit.LOCIndex
        PassFirerPositionInHex = TempFiringUnit.BasePersUnit.hexPosition
        End With
        'check if a target has been selected, if only firers selected, do nothing, already added to Group
        If TempTarget.Count > 0 Then
        'if TempTarget exists then working on TempSolution
        'need to make sure using current values if Target Moving
        If Not IsNothing(Game.Scenario.DoMove) AndAlso Not IsNothing(Scencolls.SelMoveUnits) AndAlso Scencolls.
        SelMoveUnits.Count > 0 Then
        For Each MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.SelMoveUnits
        For Each TempTarg As ObjectClassLibrary.ASLXNA.PersUniti In TempTarget
        If TempTarg.BasePersUnit.Unit_ID = MovingUnit.BasePersUnit.Unit_ID Then
        TempTarg.BasePersUnit.Hexnum = MovingUnit.BasePersUnit.Hexnum
        TempTarg.BasePersUnit.hexlocation = MovingUnit.BasePersUnit.hexlocation
        TempTarg.BasePersUnit.hexPosition = MovingUnit.BasePersUnit.hexPosition
        Dim getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
        'Dim LocToUse As MapDataClassLibrary.GameLocation = getlocs.RetrieveLocationfromMaptable(TempTarg.BasePersUnit.Hexnum, TempTarg.BasePersUnit.hexlocation)
        TempTarg.BasePersUnit.LOCIndex = MovingUnit.BasePersUnit.LOCIndex
        'TempTarg.LevelinHex = LocToUse.LevelInHex
        'Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
        TempTarg.BasePersUnit.LevelinHex = MovingUnit.BasePersUnit.LevelinHex
        'LevelChk.GetLocationPositionLevel(MovingUnit.basepersunit.hexnum, MovingUnit.basepersunit.hexlocation, MovingUnit.basepersunit.hexposition)
        End If
        Next
                Next
        End If
        For Each TempTargU As ObjectClassLibrary.ASLXNA.PersUniti In TempTarget
        'put LOS-related info into variables for use in creating TempSolution
        Dim PassTargethexnum = TempTargU.BasePersUnit.Hexnum
        Dim PassTargetlevelinhex = TempTargU.BasePersUnit.LevelinHex
        Dim PassTargetLOSIndex As Single = TempTargU.BasePersUnit.LOCIndex
        Dim PassTargetPositionInHex As Integer = TempTargU.BasePersUnit.hexPosition
        'If PassTargetUsingCrestStatus Then PassTargetLOSIndex = CSng(PassTargetLOSIndex + 0.1)
        'check and see if same TEmpSol already exists
        AddYes = True
        If TempSolutions.Count > 0 Then
        For Each TempSolitem As LOSClassLibrary.ASLXNA.TempSolution In TempSolutions
        If PassTargetLOSIndex = TempSolitem.SeenLOSIndex Then
        'same TempSolution already exists no need to create new one
        For Each TempFirUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        TempFirUnit.BasePersUnit.SolID = TempSolitem.ID
        Next
                IsThereASolutiontoTest = True
        TempTargU.BasePersUnit.SolID = TempSolitem.ID
        AddYes = False :Exit For
        End If
        Next
        If AddYes Then
        'add new TempSol to the TempSolutions collection
        If AddtoTempSol (PassFirerhexnum, PassFirerlevelinhex,
                PassFirerLosIndex, PassFirerPositionInHex, PassTargethexnum, PassTargetlevelinhex,
                PassTargetLOSIndex, PassTargetPositionInHex, PassSolworks, TempSolutions.Count, scenmap)Then
        Dim TempSol As LOSClassLibrary.
        ASLXNA.TempSolution = CType(TempSolutions.Item(TempSolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        'update TempFirer and TempTarget to include new TempSolution id
        For Each TempFirUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        TempFirUnit.BasePersUnit.SolID = TempSol.ID
        Next
        TempTargU.BasePersUnit.SolID = TempSol.ID
        IsThereASolutiontoTest = True
        AddYes = False
        Else
        'error handling - will return to calling routine without adding to TempSol
        'should not cause crash; report failure in AddtoTempSol
        End If
        End If
        End If
        If AddYes Then
        'add new TempSol to the TempSolutions collection
        If AddtoTempSol (PassFirerhexnum, PassFirerlevelinhex,
                PassFirerLosIndex, PassFirerPositionInHex, PassTargethexnum, PassTargetlevelinhex,
                PassTargetLOSIndex, PassTargetPositionInHex, PassSolworks, ValidSolutions.Count, scenmap)Then
        Dim TempSol As LOSClassLibrary.
        ASLXNA.TempSolution = CType(TempSolutions.Item(TempSolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        'update TempFirer and TempTarget to include new TempSolution id
        For Each TempFirUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        TempFirUnit.BasePersUnit.SolID = TempSol.ID
        Next
        TempTargU.BasePersUnit.SolID = TempSol.ID
        IsThereASolutiontoTest = True
        Else
        'error handling - will return to calling routine without adding to TempSol
        'should not cause crash; report failure in AddtoTempSol
        End If
        End If
        Next
        ElseIf ValidSolutions.Count > 0 Then
        'if no target selected but Solution exists then add to FireGroup; if no solution exists then create temp solution
        AddYes = True
        For Each ValidSol As LOSClassLibrary.ASLXNA.LOSSolution In ValidSolutions
        If PassFirerLosIndex = ValidSol.SeeLOSIndex Then
        'if Firer location already part of valid solution, no need to check LOS again
        AddYes = False
        MsgBox("No need to add this to temp sol since already validated BUT must add units")
        'will return to calling routine, having added to FireGroup but not new Sol
        'should not cause crash
        For Each TempFirUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        If TempFirUnit.BasePersUnit.LOCIndex = ValidSol.SeeLOSIndex Then TempFirUnit.BasePersUnit.SolID = ValidSol.ID
        Next
        AddtoFireGroupandTargetGroup(ValidSol.ID)
        IsThereASolutiontoTest = True
        End If
        Next
        'if new Firer location then need to create temp solution
        If AddYes Then
        Dim ValidSol As LOSClassLibrary.ASLXNA.LOSSolution = ValidSolutions.Item(0)
        Dim PassTargethexnum = ValidSol.SeenHexNum
        Dim PassTargetlevelinhex = ValidSol.SeenLevelInHex
        Dim PassTargetLOSIndex = ValidSol.SeenLOSIndex
        Dim PassTargetPositionInHex = ValidSol.SeenPositionInHex
        If AddtoTempSol (PassFirerhexnum, PassFirerlevelinhex,
                PassFirerLosIndex, PassFirerPositionInHex, PassTargethexnum, PassTargetlevelinhex,
                PassTargetLOSIndex, PassTargetPositionInHex, PassSolworks, ValidSolutions.Count, scenmap)Then
        Dim TempSol As LOSClassLibrary.
        ASLXNA.TempSolution = CType(TempSolutions.Item(TempSolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        For Each TempFirUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        TempFirUnit.BasePersUnit.SolID = TempSol.ID
        Next
                IsThereASolutiontoTest = True
        Else
        'error handling - will return to calling routine, having added nothing
        'should not cause crash; report failure in AddtoTempSol
        End If
        End If
        Else
        'just adding new firers; nothing else to do
        End If
        Else
        'if only targets selected, do nothing, else create Temp solution
        Try
        Dim TempTargUnit As ObjectClassLibrary.
        ASLXNA.PersUniti = CType(TempTarget.Item(TempTarget.Count - 1), ObjectClassLibrary.ASLXNA.PersUniti)
        'deals with one Target Unit at a time; get data
        With TempTargUnit
        AddYes = False
        'put data in variabales to pass to other methods
        Dim PassTargethexnum = TempTargUnit.BasePersUnit.Hexnum
        Dim PassTargetlevelinhex = TempTargUnit.BasePersUnit.LevelinHex
        Dim PassTargetLOSIndex As Single = TempTargUnit.BasePersUnit.LOCIndex
        Dim PassTargetPositioninhex = TempTargUnit.BasePersUnit.hexPosition
        PassSolworks = False
        'if TempFirers then create a TempSol
        If TempFireGroup.Count > 0 Then
        For Each TempFiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        'put firer data into variables for passing
        PassFirerhexnum = TempFiringUnit.BasePersUnit.Hexnum
        PassFirerlevelinhex = TempFiringUnit.BasePersUnit.LevelinHex
        PassFirerLosIndex = TempFiringUnit.BasePersUnit.LOCIndex
        PassFirerPositionInHex = TempFiringUnit.BasePersUnit.hexPosition
        If TempSolutions.Count = 0 Then
        'if no TempSol then create a new one
        AddYes = True
        Else
        For Each TempSolitem As LOSClassLibrary.ASLXNA.TempSolution In TempSolutions
        'check against existing tempsol; if match then don' t create new one
        If PassFirerLosIndex = TempSolitem.SeeLOSIndex And PassTargetLOSIndex = TempSolitem.SeenLOSIndex Then
                IsThereASolutiontoTest = True
        AddYes = False :Exit For
        Else
                AddYes = True
        End If
        Next
        End If
        If AddYes Then
        'if no match then create new TempSol
        If AddtoTempSol (PassFirerhexnum, PassFirerlevelinhex,
                PassFirerLosIndex, PassFirerPositionInHex, PassTargethexnum, PassTargetlevelinhex,
                PassTargetLOSIndex, PassTargetPositioninhex, PassSolworks, TempSolutions.Count - 1, scenmap)Then
        Dim TempSol As LOSClassLibrary.
        ASLXNA.TempSolution = CType(TempSolutions.Item(TempSolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        TempFiringUnit.BasePersUnit.SolID = TempSol.ID
        TempTargUnit.BasePersUnit.SolID = TempSol.ID
        IsThereASolutiontoTest = True
        Else
        'error handling will return to calling routine, having added nothing
        'should not cause crash; report failure in AddtoTempSol
        End If
        End If
        Next
        ElseIf ValidSolutions.Count > 0 Then
        'check if LOS already validated
        AddYes = True
        For Each ValidSol As LOSClassLibrary.ASLXNA.LOSSolution In ValidSolutions
        If PassTargetLOSIndex = ValidSol.SeenLOSIndex Then
        'if Target location already part of valid solution, no need to check LOS again
        AddYes = False
        MsgBox("No need to add this to temp sol since already validated BUT must add units")
        'will return to calling routine, having added to TargetGroup but not new Sol
        'should not cause crash
        TempTargUnit.BasePersUnit.SolID = ValidSol.ID
        AddtoFireGroupandTargetGroup(ValidSol.ID)
        IsThereASolutiontoTest = True
        End If
        Next
        If AddYes Then
        'if not match with existing sol then create TempSol
        For Each ValidSol As LOSClassLibrary.ASLXNA.LOSSolution In ValidSolutions
                PassFirerhexnum = ValidSol.SeeHexNum
        PassFirerlevelinhex = ValidSol.SeeLevelInHex
        PassFirerLosIndex = ValidSol.SeeLOSIndex
        PassFirerPositionInHex = ValidSol.SeePositionInHex
        If AddtoTempSol (PassFirerhexnum, PassFirerlevelinhex,
                PassFirerLosIndex, PassFirerPositionInHex, CInt(PassTargethexnum), CSng(PassTargetlevelinhex),
                PassTargetLOSIndex, PassTargetPositioninhex, PassSolworks, TempSolutions.Count - 1, scenmap)Then
        Dim TempSol As LOSClassLibrary.
        ASLXNA.TempSolution = CType(TempSolutions.Item(TempSolutions.Count - 1), LOSClassLibrary.ASLXNA.TempSolution)
        TempTargUnit.BasePersUnit.SolID = TempSol.ID
        IsThereASolutiontoTest = True
        Else
        'error handling - will return to calling routine, having added nothing
        'should not cause crash; report failure in AddtoTempSol
        End If
        Next ValidSol
        End If
        Else
        'just adding new targets; nothing else to do
        End If
        End With
        Catch ex As Exception
        'Needed for same hex combat when OH.VisibleUnitsinHex contains both firer and target with selected=true
        If TargGroup.Count > 0 Then
                IsThereASolutiontoTest = True
        Else
                IsThereASolutiontoTest = False
        End If
        End Try
        End If
        'now that all TempSolutions/Solutions are set, return to ClickOnNewParticipants and
        'call DetermineFireSolution

    }

    protected boolean AddtoTempFG(PersUniti Unititem, Optional ByVal selectionmade As Integer=0) {

            'called by IFT.AddFirerUnit
                    'creates a new instance of firepersUnit property in the persuniti instance and then adds that to TempFireGroup
                    'if LOS exists, persuniti instances are later added to FireGroup
    Dim UsingMG
    As ObjectClassLibrary.ASLXNA.SuppWeapi
            'create Firingpersunitproperty
    Dim ObjCreate
    As ObjectFactoryClassLibrary.aslxna.PersCreation =
    New ObjectFactoryClassLibrary.aslxna.PersCreation
    Dim SWCreate
    As ObjectFactoryClassLibrary.aslxna.SWCreation =
    New ObjectFactoryClassLibrary.
    aslxna.SWCreation
            Unititem = ObjCreate.CreatefiringUnitandProperty(Unititem)
    Select Case
    selectionmade
    Case 0
    Case ConstantClassLibrary.
    ASLXNA.CombatSel.InhandFirstMG
            UsingMG = (From
    selsw As
    ObjectClassLibrary.ASLXNA.SuppWeapi In
    Scencolls.SWCol Where
    selsw.BaseSW.Unit_ID =
    Unititem.BasePersUnit.FirstSWLink Select
    selsw).First
    UsingMG.FiringSW =SWCreate.createfiringswproperty(UsingMG)
            Unititem.FiringPersUnit.FiringMGs.Add(UsingMG)
    Unititem.FiringPersUnit.UsingInHerentFP =True
    Unititem.FiringPersUnit.UsingfirstMG =True
    Case ConstantClassLibrary.
    ASLXNA.CombatSel.InhandSecondMG
            UsingMG = (From
    selsw As
    ObjectClassLibrary.ASLXNA.SuppWeapi In
    Scencolls.SWCol Where
    selsw.BaseSW.Unit_ID =
    Unititem.BasePersUnit.SecondSWlink Select
    selsw).First
    UsingMG.FiringSW =SWCreate.createfiringswproperty(UsingMG)
            Unititem.FiringPersUnit.FiringMGs.Add(UsingMG)
    Unititem.FiringPersUnit.UsingInHerentFP =True
    Unititem.FiringPersUnit.UsingsecondMG =True
    Case ConstantClassLibrary.
    ASLXNA.CombatSel.BothMG
            UsingMG = (From
    selsw As
    ObjectClassLibrary.ASLXNA.SuppWeapi In
    Scencolls.SWCol Where
    selsw.BaseSW.Unit_ID =
    Unititem.BasePersUnit.FirstSWLink Select
    selsw).First
    UsingMG.FiringSW =SWCreate.createfiringswproperty(UsingMG)
            Unititem.FiringPersUnit.FiringMGs.Add(UsingMG)
    Unititem.FiringPersUnit.UsingInHerentFP =False
    Unititem.FiringPersUnit.UsingfirstMG =
    True
            UsingMG = (From
    selsw As
    ObjectClassLibrary.ASLXNA.SuppWeapi In
    Scencolls.SWCol Where
    selsw.BaseSW.Unit_ID =
    Unititem.BasePersUnit.SecondSWlink Select
    selsw).First
                    Unititem.FiringPersUnit.FiringMGs.Add(UsingMG)
    Unititem.FiringPersUnit.UsingsecondMG =True
    Case ConstantClassLibrary.
    ASLXNA.CombatSel.FirstMG
            UsingMG = (From
    selsw As
    ObjectClassLibrary.ASLXNA.SuppWeapi In
    Scencolls.SWCol Where
    selsw.BaseSW.Unit_ID =
    Unititem.BasePersUnit.FirstSWLink Select
    selsw).First
    UsingMG.FiringSW =SWCreate.createfiringswproperty(UsingMG)
            Unititem.FiringPersUnit.FiringMGs.Add(UsingMG)
    Unititem.FiringPersUnit.UsingInHerentFP =False
    Unititem.FiringPersUnit.UsingfirstMG =True
    Case ConstantClassLibrary.
    ASLXNA.CombatSel.SecondMG
            UsingMG = (From
    selsw As
    ObjectClassLibrary.ASLXNA.SuppWeapi In
    Scencolls.SWCol Where
    selsw.BaseSW.Unit_ID =
    Unititem.BasePersUnit.SecondSWlink Select
    selsw).First
    UsingMG.FiringSW =SWCreate.createfiringswproperty(UsingMG)
            Unititem.FiringPersUnit.FiringMGs.Add(UsingMG)
    Unititem.FiringPersUnit.UsingInHerentFP =False
    Unititem.FiringPersUnit.UsingsecondMG =True
    Case ConstantClassLibrary.ASLXNA.CombatSel.InhOnly
    Unititem.FiringPersUnit.UsingInHerentFP =True
    Unititem.FiringPersUnit.UsingfirstMG =False
    Unititem.FiringPersUnit.UsingsecondMG =False
    Case Else
    End Select
            'add to TempFireGroup
    Try
                TempFireGroup.Add(Unititem)
    AddtoTempFG =
    True
            Catch
    AddtoTempFG =

    False
    MsgBox("Error adding unit to TempFireGroup",,"IFT.AddtoTempFG")

    End Try
    }
    protected boolean AddtoFireGroupandTargetGroup(int SolUsedID) {
        'called by IFT.DetermineFireSolution
        'adds units from valid Temp Solution to FireGroup and TargetGroup

        AddtoFireGroupandTargetGroup = False
        'add firer(s) to FireGroup
        Dim Firerlink As Integer = 0 :Dim FirerAdded As Boolean = False
        If TempFireGroup.Count > 0 Then
        For Each TempFiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempFireGroup
        If TempFiringUnit.BasePersUnit.SolID = SolUsedID Then
                Firerlink = TempFiringUnit.BasePersUnit.Unit_ID
        If ConstantClassLibrary.ASLXNA.Typetype.Personnel = TempFiringUnit.BasePersUnit.TypeType_ID Then 'unit
        'check if already added to Fire Group
        For Each AlreadyAdded As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        If AlreadyAdded.BasePersUnit.Unit_ID = Firerlink
        And(ConstantClassLibrary.ASLXNA.Typetype.Personnel = AlreadyAdded.BasePersUnit.TypeType_ID) Then
        'alreadyadded so do nothing
        FirerAdded = True :Exit For
        End If
        Next
        If Not FirerAdded Then
        If TempFiringUnit.FiringPersUnit.CombatStatus = 0 Or TempFiringUnit.
        BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None Then TempFiringUnit.
        FiringPersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.Firing
        Dim myLdrdrm As Integer = 0 :Dim myHeroDRM As Integer = 0 :Dim WhichtoUse As Integer = 0
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()
        'use empty variables when know that instance already exists
        Dim myType
        As Integer = CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType, CInt(TempFiringUnit.BasePersUnit.LOBLink)))
        If(myType = ConstantClassLibrary.ASLXNA.Utype.Leader) Or myType = ConstantClassLibrary.ASLXNA.Utype.LdrHero Then
                myLdrdrm = CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.ldrm, CInt(TempFiringUnit.BasePersUnit.LOBLink)))
        Else
                myLdrdrm = 0
        End If
        If myType = ConstantClassLibrary.ASLXNA.Utype.Hero Then
                myHeroDRM = -1
        Else
                myHeroDRM = 0
        End If
        If myLdrdrm = -1 And myType = ConstantClassLibrary.ASLXNA.Utype.LdrHero Then
        'ldrhero
        'need user to choose which drm to use - DONT TO THIS HERE; DO IT IN CALCldrDRM
        End If
        FireGroup.Add(TempFiringUnit) 'New ObjectClassLibrary.ASLXNA.PersUniti(unititem, AddInf, SolUsedID, WhichtoUse))
        End If
        End If
        AddtoFireGroupandTargetGroup = True
        End If
        Next
        End If
        'add target(s)to Target Group
        Dim Targetlink As Integer = 0 :Dim TargetAdded As Boolean = False
        If TempTarget.Count > 0 Then
        For Each TempTargUnit As ObjectClassLibrary.ASLXNA.PersUniti In TempTarget
        If TempTargUnit.BasePersUnit.SolID = SolUsedID Then 'part of this fire solution
        Targetlink = TempTargUnit.BasePersUnit.Unit_ID
        '' If TypeCheck.
        IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, TempTargUnit.BasePersUnit.Type_ID) Then
        'check if already added to Target group
        TargetAdded = False
        For Each Alreadyadded As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup
        If Alreadyadded.BasePersUnit.Unit_ID = Targetlink And TempTargUnit.
        BasePersUnit.TypeType_ID = Alreadyadded.BasePersUnit.TypeType_ID Then
        'already added so do nothing
        TargetAdded = True :Exit For
        End If
        Next
        If Not TargetAdded Then
        'unititem = Game.Linqdata.GetUnitfromCol(Targetlink)
        TargGroup.Add(TempTargUnit)
        End If
        AddtoFireGroupandTargetGroup = True
        End If
        Next
        End If
    }

    protected boolean AddtoTempTarget(PersUniti Unititem) {
        'called by IFT.AddTargetUnit
        'eligible to be added as target to create Targetpersunit property and add to TempTarget
        'if LOS exists, added to TargetGroup later

        'create Targetpersunitproperty
        Dim ObjCreate As ObjectFactoryClassLibrary.aslxna.PersCreation = New
        ObjectFactoryClassLibrary.aslxna.PersCreation
                Unititem = ObjCreate.CreateTargetUnitandProperty(Unititem, FirerSan)
        'add to TempTarget
        Try
        TempTarget.Add(Unititem)
        AddtoTempTarget = True
        Catch
                AddtoTempTarget = False
        MsgBox("Error adding unit to TempTarget", , "IFT.AddtoTempTarget")
        End Try
    }
    protected boolean AddtoTempSol(ByVal PassFirerhexnum As Integer, ByVal PassFirerlevelinhex As Single,
                                 ByVal PassFirerLOSindex As Single, ByVal PassFirerPositioninHex As Integer, ByVal PassTargethexnum As Integer,
                                 ByVal PassTargetlevelinhex As Single, ByVal PassTargetLOSIndex As Single, ByVal PassTargetPositionInHex As Integer,
                                 ByVal PassSolWorks As Boolean, ByVal PassTempSolID As Integer, ByVal PassScenMap As Integer) {
        'called by IFT.IsThereASolutionToTest
        'adds a new temporary LOS to be validated
        Try
        TempSolutions.Add(New LOSClassLibrary.ASLXNA.TempSolution(PassFirerhexnum, PassFirerlevelinhex,
                PassFirerLOSindex, PassFirerPositioninHex, PassTargethexnum, PassTargetlevelinhex,
                PassTargetLOSIndex, PassTargetPositionInHex, PassSolWorks, PassTempSolID, PassScenMap))
        AddtoTempSol = True
        Catch ex As Exception
        AddtoTempSol = False
        MsgBox("Error adding Fire Solution to TempSolutions", , "IFT.AddtoTempSol")
        End Try
        End Function
        'protected Function AddtothreadTempSol(ByVal PassFirerhexnum As Integer, ByVal PassFirerlevelinhex As Single,
        'ByVal PassFirerLOSindex As Single, ByVal PassFirerPositioninHex As Integer, ByVal PassTargethexnum As Integer,
        'ByVal PassTargetlevelinhex As Single, ByVal PassTargetLOSIndex As Single, ByVal PassTargetPositionInHex As Integer,
        'ByVal PassSolWorks As Boolean, ByVal PassTempSolID As Integer, ByVal PassScenMap As Integer) As Boolean
        '    ' called by IFT.IsThereASolutionToTest
        '    ' adds a new temporary LOS to be validated
        '    Try
        '        TempSolutions.Add(New LOSClassLibrary.ASLXNA.TempSolution(PassFirerhexnum, PassFirerlevelinhex,
        '        PassFirerLOSindex, PassFirerPositioninHex, PassTargethexnum, PassTargetlevelinhex,
        '        PassTargetLOSIndex, PassTargetPositionInHex, PassSolWorks, TempSolutions.Count - 1, PassScenMap))
        '        AddtothreadTempSol = True
        '    Catch ex As Exception
        '        AddtothreadTempSol = False
        '        MsgBox("Error adding Fire Solution to TempSolutions", , "IFT.AddtoTempSol")
        '    End Try
        'End Function
        protected Function AddtoValidSolutions (ByVal TempSolitem As LOSClassLibrary.ASLXNA.TempSolution)As Boolean
        'called by IFT.DetermineFireSolution
        'adds a validated fire solution to the ValidSolutions group

        Dim PassID As Integer = 0
        With TempSolitem
        If TempSolitem.ID = 0 And ValidSolutions.Count > 0 Then PassID = ValidSolutions.Count Else PassID = .ID
                Try
        ValidSolutions.Add(New LOSClassLibrary.ASLXNA.LOSSolution(.SeeHexNum,
                .SeeLevelInHex, .TotalSeeLevel, CInt(.SeeLOSIndex), .SeePositionInHex, .SeenHexNum,
            .SeenLevelInHex, .TotalSeenLevel, CInt(.SeenLOSIndex), .SeenPositionInHex, .Solworks, .LOSFollows, PassID, .
        ScenMap))
        AddtoValidSolutions = True
        Catch ex As Exception
        AddtoValidSolutions = False
        MsgBox("Error adding Fire Solution to ValidSolutions", , "IFT.AddtoValidSolutions")
        End Try
        End With
    }
*/
    public void ClickedOnNewParticipants(Hex ClickedHex, LinkedList<GamePiece> SelectedUnits) {
        // called by Game.DetermineClickPossibilities  in lots of classes
        // this routine manages Firer/Target selections and when valid triggers the combat process up to clicking on Fire or cancel
        // which checks LOS, determines FP and drm
        // once completed returns to Game.DetermineClickPossibilities with no further action there
        // it can be called multiple times to add additional target/firer units before resolving combat

        PersUniti Selunit;
        boolean GoCombatSolutionTest = false;
        boolean GoCombatSolution = false;
        int WhichOne = 0;
        int ObjIDlink = 0;
        // Get list of PersUniti objects for the selected units
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

        // temporary while debugging
        //if (ValidSolutions.size() > 0) { // selecting one of possible solutions already checked
            for (GamePiece SelUnit : SelectedUnits) {
                ObjIDlink = Integer.parseInt(SelUnit.getProperty("TextLabel").toString());
                for(PersUniti findunit: Scencolls.Unitcol){
                    if(findunit.getbaseunit().getUnit_ID() == ObjIDlink) {
                        GameModule.getGameModule().getChatter().send("Have found selected unit in PersUniti collection: " + findunit.getbaseunit().getUnitName());
                    }
                }

                /*If Selunit.BasePersUnit.Nationality = Game.Scenario.IFT.TargetSide Then WhichOne = 0
                Else WhichOne = ConstantClassLibrary.ASLXNA.CombatStatus.Firing
                LoSIndextoUse = CInt(Selunit.BasePersUnit.LOCIndex)
                Catch
                Continue For 'neither unit nor ? so skip
                End Try
                End If*/
            }
        //}

        /*'no solution yet in place, so add units and test for solution
        'loop through counters and find match with sprite
        For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If DisplaySprite.Selected Then
        'And DisplaySprite.Ontop Then  DOES NOT WORK WHEN MOVING IN CELLARS WITH LOCATION COUNTER ON TOP
        'if selected determine if unit or ? and use nationality to determine if Target or Firer
        Try
                Selunit = (From GetUnit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where
        GetUnit.BasePersUnit.Unit_ID = DisplaySprite.ObjID AndAlso GetUnit.BasePersUnit.LOBLink = DisplaySprite.TypeID
        Select GetUnit).First
        If Selunit.BasePersUnit.Nationality = Game.Scenario.IFT.TargetSide Then WhichOne = 0
        Else WhichOne = ConstantClassLibrary.ASLXNA.CombatStatus.Firing
        Catch
        Continue For 'neither unit nor ? so skip
        End Try
        Else
        Continue For 'sprite not selected so skip
        End If
        'add unit or ? to Target or Firer (? not added to firer)
        If WhichOne = 0 Then 'TargetUniut
        If Game.Scenario.IFT.AddTargetUnit(Selunit) Then GoCombatSolutionTest = True
        Else 'FiringUnit
        If Selunit.BasePersUnit.VisibilityStatus<> ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible Then
        'clicked on concealed unit; don' t add
        MessageBox.Show("Failure to Add Concealed Firer Unit: " & DisplaySprite.Objname, "ClickedOnNewParticipants")
        Else
        If Game.Scenario.IFT.AddFirerUnit(Selunit) Then GoCombatSolutionTest = True
        End If
        End If
        'See if a possible solution exists
        If GoCombatSolutionTest Then
        'if possible solution then decide to test it
        If Game.Scenario.IFT.IsThereASolutiontoTest(WhichOne) Then GoCombatSolution = True
        End If
        Next
        'if test required then
        If GoCombatSolution Then
        DetermineFireSolution() 'does LOSCheck and sets up valid solutinos
        'call overarching method to manage LOS, FP and DRM calculations based on Firer/Target selections and display Fire button
        If ValidSolutions.Count > 0 Then ManageCombatSolutionDetermination ()
        End If*/
    }
/*
    protected void ResetParticipants(PersUniti FiringUnit) {
        'called by ASLXNA.Dispform.FireCheck_CheckedChanged
        'this routine manages reset of Firer selections due to Displayform selection
        'if valid Firer/Target selection found, triggers the combat process up to clicking on Fire or cancel
        ' which checks LOS, determines FP and drm
        'once completed returns to XX with no further action there

        'Remove deselected unit from FireGroup and deselect the sprite
        Dim hexnumber As Integer = FiringUnit.BasePersUnit.Hexnum
        'Get list of counters for the hex
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(hexnumber), VisibleOccupiedhexes)

        'remove from FireGroup
        FireGroup.Remove(FiringUnit)
        'confirm that all remaining FG members are ADJACENT (A7.5) - this is only test that needs to be done
        'LOS must still be valid as was prior to deselection; leader only hexes will be checked in ManageCombatSolutionDetermination
        Dim Firinghex As Integer = FirerHex
        Dim Firingloc As Integer = Firerloc
        Dim FiringPos As Integer = Firerpos
        Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
        Dim BaseHexloc As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(Firinghex, Firingloc)
        Dim OKHexloc As MapDataClassLibrary.GameLocation:
        Dim AddTrue As Boolean = False
        Dim StillIn = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
        Do
                AddTrue = False
        For Each StillFiring As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        If StillIn.Count = 0 Then
        If BaseHexloc.Hexnum = StillFiring.BasePersUnit.Hexnum And BaseHexloc.
        Location = StillFiring.BasePersUnit.hexlocation Then
        'still qualifies for FireGroup
        StillIn.Add(StillFiring)
        AddTrue = True
        Else
        Dim TestHexloc As MapDataClassLibrary.
        GameLocation = Getlocs.RetrieveLocationfromMaptable(StillFiring.BasePersUnit.Hexnum, StillFiring.BasePersUnit.hexlocation)
        Dim ADJTest As New CombatTerrainClassLibrary.ASLXNA.HexBesideC(BaseHexloc, TestHexloc, FiringPos)
        If ADJTest.AreLocationsADJACENT Then
        StillIn.Add(StillFiring)
        AddTrue = True
        End If
        End If
        Else
        For Each StillOK As ObjectClassLibrary.ASLXNA.PersUniti In StillIn
                OKHexloc = Getlocs.RetrieveLocationfromMaptable(StillOK.BasePersUnit.Hexnum, StillOK.BasePersUnit.hexlocation)
        Dim TestHexloc As MapDataClassLibrary.
        GameLocation = Getlocs.RetrieveLocationfromMaptable(StillFiring.BasePersUnit.Hexnum, StillFiring.BasePersUnit.hexlocation)
        Dim ADJTest As New
        CombatTerrainClassLibrary.ASLXNA.HexBesideC(OKHexloc, TestHexloc, StillOK.BasePersUnit.hexPosition)
        If ADJTest.AreLocationsADJACENT Then
        StillIn.Add(StillFiring) :AddTrue = True
        Exit For
        End If
        Next
        End If
        Next
        If AddTrue = False Then Exit Do
                Loop
        'Update Firegroup to be those still in
        FireGroup = StillIn
        'update display sprites
        'deselect all and reselect those still in
        For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        Dim Lambdasprite As ObjectClassLibrary.ASLXNA.SpriteOrder = DisplaySprite
        DisplaySprite.Selected = False
        Try
        Dim FindUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From DoMatch In FireGroup Where
        DoMatch.BasePersUnit.Unit_ID = Lambdasprite.ObjID).First
        DisplaySprite.Selected = True
        Catch ex As Exception
        DisplaySprite.Selected = False
        End Try
        Next
        DetermineFireSolution()
        'call overarching method to manage LOS, FP and DRM calculations based on Firer/Target selections and display Fire button
        ManageCombatSolutionDetermination()
    }

    protected void ManageCombatSolutionDetermination() {
        'called by ClickOnNewParticipants and ResetParticipants and MGandInherentFPSelection.ProcessChoice
        'takes valid Firer/Target selections and manages LOS and FP, DRM calculations
        'calls routines and functions which handle parts and then return here
        'ResetParticipants can start with new FireGroup here as LOS tests are still valid and leader only checks still to come

        If ValidSolutions.Count > 0 Then
        'last check - determine if Fire Group is valid
        '(ie no hexes with just leaders - there may be other tests)
        Dim Firepower As Integer
        If Not (IsNothing(FireGroup)) Then
        If Not ConfirmValidFG(FireGroup) Then
        'no fire possible
        MsgBox("No FP possible", , "IFT.CalcFP")
        Game.Scenario.IFT.ClearCurrentIFT()
        Return
        End If
        FinalDRMLIst.Clear() 'this is new code; does it behave properly May 13?
        'confirm terrain variables are in place
        For Each Validsol As LOSClassLibrary.ASLXNA.LOSSolution In ValidSolutions
        If Validsol.HexesInLOS.Count = 0 Then
        For Each ComTer As ObjectClassLibrary.ASLXNA.CombatTerrain In LOSTest.TempCombatTerrCol 'CombatTerrCol
        If ComTer.SolID = Validsol.ID Then Validsol.AddtoLOSList(ComTer)
        Next
                Else
        MessageBox.Show("No Need to add Hexes to HexesInLOS; already there", "IFTC.CombatDRM")
        End If
        If Validsol.AltHexesInLOS.Count = 0 Then
        For Each Althex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In ThreadManager.AltHexLOSGroup
        If Althex.TempSolID = Validsol.ID Then Validsol.AddtoAltHexList(Althex)
        Next
        End If
        Next
        LOSTest.TempCombatTerrCol.Clear() 'can' t do in ClearTempCombat (called in determinefiresolution)as need to use
        in above loop
        'now see if FireGroup can bring FP to bear
        Dim Scendet As DataClassLibrary.scen = Game.Linqdata.GetScenarioData(Game.Scenario.ScenID)
        Dim CombatCalc As CombatClassLibrary.aslxna.iCombatCalc = New
        CombatClassLibrary.aslxna.CombatCalcC(ValidSolutions)
        Firepower = CombatCalc.CalcCombatFPandDRM(FireGroup, TargGroup, Scendet, -1)
        'pass UsingSol as -1 to indicate need to use all ValidSolutions (possible multi-location FG)
        FinalDRMLIst = CombatCalc.FinalDRMList
        If Firepower = -99 Then 'LOS Blocked
        ClearCurrentIFT()
        Return
        End If
        Dim VisClass As New SetIFTEnv
        VisClass.ShowFireButton(True)
        End If
        End If
    }

    protected void ClearCurrentIFT() {
        'called by Gameform.buClear_click, IFT.ManageCombatsolutionDetermination, EnemyValuesConcreteC.SetLOSHFPdrmValues
        'clears all temporary variables associated with IFT combat
        Dim MapGeo as mapgeoclasslibrary.
        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Dim Hextoclear As Integer = 0
        If TargGroup.Count > 0 Then
        For Each FirstItem As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup
        If FirstItem.BasePersUnit.Hexnum<> Hextoclear Then
                Hextoclear = FirstItem.BasePersUnit.Hexnum
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(Hextoclear), VisibleOccupiedhexes)
        For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        DisplaySprite.Selected = False
        Next
        End If
        FirstItem.TargetPersUnit = Nothing
        Next
        TargGroup.Clear()
        End If
        Hextoclear = 0
        If FireGroup.Count > 0 Then
        For Each FirstItem As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        If FirstItem.BasePersUnit.Hexnum<> Hextoclear Then
                Hextoclear = FirstItem.BasePersUnit.Hexnum
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(Hextoclear), VisibleOccupiedhexes)
        For Each DisplaySprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        DisplaySprite.Selected = False
        Next
        End If
        FirstItem.FiringPersUnit = Nothing
        Next
        FireGroup.Clear()
        End If
        ValidSolutions.Clear()
        FinalDRMLIst.Clear()
        ClearTempCombat()
        FirerHex = 0 :Firerloc = 0 :Firerpos = 0
        TargetHex = 0 :Targetloc = 0
        ThreadManager.AltHexLOSGroup.Clear()
        ThreadManager.TempAlthexcol.Clear()
        LOSTest.TempCombatTerrCol.Clear()
        Mapgeo.RangeC.ResetValues()
        CombatReport.ShowClearCombat()
        Dim VisClass As New SetIFTEnv
        VisClass.ShowFireButton(False)
        'same as for clear movement
        Game.MoveStringsToDraw.Clear()
        Game.StringsToDraw.Clear()
        Game.HexesToShade.Clear()
        Game.Scenario.ShaderToShow = 0
        'saves any terrain changes or LOS check updates/additions to the database
        If Not Game.Scenario.Phase = ConstantClassLibrary.ASLXNA.Phase.Movement Then
        ' CODE TO AVOID DUPLICATION - NEEDS TO BE MOVED ELSEWHERE OCT 13
        Dim ASLMapLink As String = "Scen" & CStr(Game.Scenario.ScenID) :Dim ASLLOSLink
        As String = "LOS" & CStr(Game.Scenario.ScenID)
        'need to pass string value to create terrain collection
        Dim Maptables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(Trim(ASLMapLink), Game.Scenario.ScenID)
        'Dim TerrCheck As TerrainClassLibrary.ASLXNA.TerrainChecks = New TerrainClassLibrary.ASLXNA.TerrainChecks(Game.Scenario.LocationCol)
        Maptables.UpdateMaptables(ASLMapLink, ASLLOSLink)
        'Then MessageBox.Show("Updated Game and Scenario Location and LOS tables")
        End If
    }

    protected void ClearTempCombat() {
        TempTarget.Clear()
        TempFireGroup.Clear()
        TempSolutions.Clear()
        LOSTest.TempAltHexLOSGroup.Clear()
    }
    protected void ProcessIFTCombat() {

        TargetHexes = New List(Of Integer) :FirerHexes = New List(Of Integer) :Dim AlreadyAdded As Boolean = False
        Dim RemoveList = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
        DR = New UtilClassLibrary.ASLXNA.DiceC
        Dim ODR As Integer = DR.Diceroll()
        'need to handle cowering and SW breakdown before obtaining IFT result - have to redo FP and drm calc - handled in IFTResultC
        'SW Breakdown DR, ROF result, HitLocation Result all set by IFTResultC as accessible properties

        'store target and firer hexes for sprite redraw at end of method
        For Each TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti In Game.Scenario.IFT.TargGroup
                AlreadyAdded = False
        For Each HexAdded As Integer In TargetHexes
        If HexAdded = TargetUnit.BasePersUnit.Hexnum Then AlreadyAdded = True
        Next
        If Not AlreadyAdded Then TargetHexes.Add(TargetUnit.BasePersUnit.Hexnum)
        Next
        For Each FirerUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
                AlreadyAdded = False
        For Each HexAdded As Integer In FirerHexes
        If HexAdded = FirerUnit.BasePersUnit.Hexnum Then AlreadyAdded = True
        Next
        If Not AlreadyAdded Then FirerHexes.Add(FirerUnit.BasePersUnit.Hexnum)
        Next
        'check for SAN
        If ODR = TargetSan Then
        'create sniper hover
        Dim SnipTexture As String = Trim(Game.Linqdata.GetNatInfo(TargetSide, 2)) & "Sniper"
        TargSanString = SnipTexture
        Dim Passposition As Integer = 1 :Dim PassTexture As Microsoft.
        Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D) (Trim(SnipTexture))
        Dim HoverToAdd = New
        ObjectClassLibrary.ASLXNA.HoverItem(PassTexture, 1, "Sniper Check", Passposition, TargetSide, ConstantClassLibrary.ASLXNA.HoverAction.SniperAvail, 0)
        Game.SniperToDraw.Add(HoverToAdd)
        End If
        'Returns same stack but with IFT result added for each thing in the stack - concealed units are revealed and OBUnit replaces them in TargGroup
        IFTRes = New CombatClassLibrary.aslxna.IFTResultC
        ' check cowering and breakdown - final FP adjustements
        FireGroup = IFTRes.SWBrkDwnCowerAdj(Game.Scenario.IFT.TargGroup, DR, FireGroup)
        If FireGroup.Count = 0 Then 'no FP left so result is NR
        For Each TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti In Game.Scenario.IFT.TargGroup
        TargetUnit.TargetPersUnit.IFTResult = ConstantClassLibrary.ASLXNA.IFTResult.NR
        Next
                Else
        If IFTRes.SW12 Then
        Dim Scendet As DataClassLibrary.scen = Game.Linqdata.GetScenarioData(Game.Scenario.ScenID)
        Dim CombatCalc As CombatClassLibrary.aslxna.iCombatCalc = New
        CombatClassLibrary.aslxna.CombatCalcC(ValidSolutions)
        CombatCalc.CalcCombatFPandDRM(FireGroup, Game.Scenario.IFT.TargGroup, Scendet, -1)
        End If
        End If
        'now determine IFT results
        Game.Scenario.IFT.TargGroup = IFTRes.GetIFTResult(Game.Scenario.IFT.TargGroup, DR, FireGroup)

        'move to combat resolution
        CombatRes = New CombatClassLibrary.aslxna.CombatResC
        CombatRes.ResolveCombat(TargGroup, IFTRes.FPdrmCombos, FirerSan, Game.Scenario.ScenID)
        If CombatRes.NeedAPopup Then
        ShowPopup(CombatRes.PopupItems, TargGroup.Item(0).BasePersUnit.Hexnum)
        myNeedToResumeResolution = True
        Exit Sub
        End If

        'Update Target Group
        For Each TargUnit As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup
        If TargUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay Then
        RemoveList.Add(TargUnit)
        End If
        Next
        For Each RemoveUnit As ObjectClassLibrary.ASLXNA.PersUniti In RemoveList
        TargGroup.Remove(RemoveUnit)
        Next
        For Each AddNewUnit As ObjectClassLibrary.ASLXNA.PersUniti In CombatRes.NewTargets
        If AddNewUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Prisoner Then
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(AddNewUnit.BasePersUnit.Hexnum), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        End If
        TargGroup.Add(AddNewUnit)
        Next
        RemoveList.Clear()
        'Update Fire Group
        For Each FireUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        If FireUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay Then
        RemoveList.Add(FireUnit)
        End If
        Next
        For Each RemoveUnit As ObjectClassLibrary.ASLXNA.PersUniti In RemoveList
        FireGroup.Remove(RemoveUnit)
        Next
        For Each AddNewUnit As ObjectClassLibrary.ASLXNA.PersUniti In CombatRes.NewFirings
        FireGroup.Add(AddNewUnit)
        Next
        'need to manage firing and target sprites here: changes due to revealing, breaking, reducing, prep fire, etc
        Dim NewCombatStatus As Integer = GetCombatStatus()
        For Each firer As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        firer.FiringPersUnit.UpdateCombatStatus(NewCombatStatus, IFTRes.ROFdr)
        Next
        'best way is to recreate all sprites in the hex based on final status at this point
        For Each Firehex As Integer In FirerHexes
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(Firehex), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
        For Each TargetHex As Integer In TargetHexes
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(TargetHex), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
        If CombatRes.NeedToResume Then
        myNeedToResumeResolution = True
        '' create sniper hover
        'Dim SnipTexture As String = Trim(Game.Linqdata.GetNatInfo(FirerSide, 2)) & "Sniper"
        'Dim FirSanString As String = SnipTexture
        'Dim Passposition As Integer = 1 : Dim PassTexture As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)(Trim(SnipTexture))
        'Dim HoverToAdd = New ObjectClassLibrary.ASLXNA.HoverItem(PassTexture, 1, "Sniper Check", Passposition, FirerSide, ConstantClassLibrary.ASLXNA.HoverAction.SniperAvail, 0)
        'Game.SniperToDraw.Add(HoverToAdd)
        Exit Sub
        Else
                CombatRes = Nothing
        End If
    }
    protected void ResumeCombatResolution() {
        If IsNothing (CombatRes) Then Exit Sub
        CombatRes.ResumeResolution()
        myNeedToResumeResolution = False
        'need to manage firing and target sprites here: changes due to revealing, breaking, reducing, prep fire, etc
        Dim NewCombatStatus As Integer = GetCombatStatus()
        For Each firer As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        firer.FiringPersUnit.UpdateCombatStatus(NewCombatStatus, IFTRes.ROFdr)
        Next
        'best way is to recreate all sprites in the hex based on final status at this point
        For Each Firehex As Integer In FirerHexes
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(Firehex), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
        For Each TargetHex As Integer In TargetHexes
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(TargetHex), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
                CombatRes = Nothing
        If Not IsNothing(Game.Unittab) Then
        Game.Unittab.ShowCombat()
        Game.Unittab.ShowResults()
        Game.Unittab.Visible = True
        End If
    }
    protected void ResumeSurrenderResolution(int AssignGuard, int PassTarg) {

        Dim RemoveList = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
        If IsNothing (CombatRes) Then Exit Sub
        CombatRes.ResumeSurrenderResolution(AssignGuard, PassTarg)
        myNeedToResumeResolution = False
        'Update Target Group
        For Each TargUnit As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup
        If TargUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay Then
        RemoveList.Add(TargUnit)
        End If
        Next
        For Each RemoveUnit As ObjectClassLibrary.ASLXNA.PersUniti In RemoveList
        TargGroup.Remove(RemoveUnit)
        Next
        For Each AddNewUnit As ObjectClassLibrary.ASLXNA.PersUniti In CombatRes.NewTargets
        If AddNewUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Prisoner Then
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(AddNewUnit.BasePersUnit.Hexnum), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        End If
        TargGroup.Add(AddNewUnit)
        Next
        RemoveList.Clear()
        'Update Fire Group
        For Each FireUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        If FireUnit.BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay Then
        RemoveList.Add(FireUnit)
        End If
        Next
        For Each RemoveUnit As ObjectClassLibrary.ASLXNA.PersUniti In RemoveList
        FireGroup.Remove(RemoveUnit)
        Next
        For Each AddNewUnit As ObjectClassLibrary.ASLXNA.PersUniti In CombatRes.NewFirings
        FireGroup.Add(AddNewUnit)
        Next
        'need to manage firing and target sprites here: changes due to revealing, breaking, reducing, prep fire, etc
        Dim NewCombatStatus As Integer = GetCombatStatus()
        For Each firer As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup
        firer.FiringPersUnit.UpdateCombatStatus(NewCombatStatus, IFTRes.ROFdr)
        Next
        'best way is to recreate all sprites in the hex based on final status at this point
        For Each Firehex As Integer In FirerHexes
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(Firehex), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
        For Each TargetHex As Integer In TargetHexes
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(TargetHex), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
        'Need to redo Guard hex as well to show new prisoner unit
        For Each TargUnit As ObjectClassLibrary.ASLXNA.PersUniti In CombatRes.NewTargets
        Dim OH As VisibleOccupiedhexes
        OH = CType(Game.Scenario.HexesWithCounter(TargUnit.BasePersUnit.Hexnum), VisibleOccupiedhexes)
        OH.GetAllSpritesInHex()
        OH.RedoDisplayOrder()
        Next
                CombatRes = Nothing
        If Not IsNothing(Game.Unittab) Then
        Game.Unittab.ShowCombat()
        Game.Unittab.ShowResults()
        Game.Unittab.Visible = True
        End If
    }
    private Constantvalues.Phase GetCombatStatus() {
        Select Case Game.Scenario.Phase
        Case ConstantClassLibrary.ASLXNA.Phase.PrepFire
        Return ConstantClassLibrary.ASLXNA.CombatStatus.PrepFirer
        Case ConstantClassLibrary.ASLXNA.Phase.Movement
        Return ConstantClassLibrary.ASLXNA.CombatStatus.FirstFirer
        Case ConstantClassLibrary.ASLXNA.Phase.DefensiveFire
        Return ConstantClassLibrary.ASLXNA.CombatStatus.FinalFirer
        Case ConstantClassLibrary.ASLXNA.Phase.AdvancingFire
        Return ConstantClassLibrary.ASLXNA.CombatStatus.AdvFirer
        Case Else
        Return ConstantClassLibrary.ASLXNA.CombatStatus.None
        End Select
    }

    private void DR_OnDRChanged() {
        Handles DR.OnDRChanged
        Dim ODRstring = (Game.Scenario.IFT.DR.White + Game.Scenario.IFT.DR.Colored).ToString
        DRstring = ": White " & Game.Scenario.IFT.DR.White.ToString & "; Colored " & Game.Scenario.IFT.DR.Colored.ToString & " = " & ODRstring
        End Sub
        private Sub ShowPopup (ByVal menuitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface), ByVal
        hexclicked As Integer)
        Dim ListofMenuThings = New ContextBuilder(menuitems)
        'create Context control
        ListofMenuThings.CreateMenu()
        Game.Scenario.ContextMenu.Tag = CStr(hexclicked)
        Game.Scenario.ContextMenu.Text = "Guard"
        Dim popuppoint As New System.Drawing.Point
        Dim MapGeo as mapgeoclasslibrary.
        aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        popuppoint = MapGeo.SetPoint(hexclicked)
        popuppoint.X = CInt(popuppoint.X + Game.Window.ClientBounds.Left + Game.translation.X)
        popuppoint.Y = CInt(popuppoint.Y + Game.Window.ClientBounds.Top + Game.translation.Y)
        Game.Scenario.ContextMenu.Show(popuppoint)
        Game.Scenario.ContextMenu.BringToFront()
        Game.contextshowing = True
    }
*/
}
