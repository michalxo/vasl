package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.EnemyHexLOSHFPdrm;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.LOSClasses.LOSSolution;
import VASL.build.module.fullrules.LOSClasses.LOSThreadManagerC;
import VASL.build.module.fullrules.LOSClasses.TempSolution;
import VASL.build.module.fullrules.ObjectClasses.AltHexGTerrain;
import VASL.build.module.fullrules.ObjectClasses.CombatTerrain;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.LevelChecks;

import java.util.*;

/**
 * Created by dougr_000 on 7/17/2017.
 */
public class DFFMVCPattern {
         /*'called by MovementC.MoveUpdate
        'this class creates and uses the Defensive First Fire MVC pattern which updates and displays the
        'LOSH, FP and drm of all enemy units on the mapwindow.
        Private DFF As DefensiveFirstFireInterface
        Public Sub New()
        'creates the model, then the controller, which creates the view
        'then trigger the model by calling SetLOSHFPdrm on the controller
        DFF = New DefensiveFirstFireConcreteC
        Dim DFFCont As DFFControllerInterface = New DFFControllerConcreteC(DFF)
        'Tells the controller to recalcuate EnemyValues
        Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
        DFFCont.SetLOSHFPdrm(Scencolls.SelMoveUnits)
        End Sub

        'these are functions visible to classes that pass the pattern results back to the main program
        Public Function GetShaderToShow() As Integer
        Return DFF.ShadertoShow
        End Function
        Public Function StoreHexestoShade() As List(Of storeShadeHex)
        Return DFF.StoreHexesToShade
        End Function
        Public Function StoreStringstoDraw() As List(Of storeShadeHex)
        Return DFF.storeStringstoDraw
        End Function*/
}
        /*'MODEL
        'interface
        Friend Interface DefensiveFirstFireInterface
        Property ShadertoShow As Integer
        Property StoreHexesToShade As List(Of storeShadeHex)
        Property storeStringstoDraw As List(Of storeShadeHex)
        Sub Initialize()
        Sub SetLOSFPdrmValues(ByVal MovingUnits As List(Of ObjectClassLibrary.ASLXNA.PersUniti))
        Function GetLOSFPdrmValues() As List(Of DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm)
        Sub RegisterObserver(ByVal DFFObserverC As DFFObserverInterface)
        Sub RemoveObserver()

        End Interface
        'concrete class
        Friend Class DefensiveFirstFireConcreteC
        Implements DefensiveFirstFireInterface

        Private DFFObserver As DFFObserverInterface
        Private SetEnemyValues As List(Of DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm)
        Friend Property ShadertoShow As Integer Implements DefensiveFirstFireInterface.ShadertoShow
        Friend Property storeHexesToShade As New List(Of storeShadeHex) Implements DefensiveFirstFireInterface.StoreHexesToShade
        Friend Property storeStringstoDraw As New List(Of storeShadeHex) Implements DefensiveFirstFireInterface.storeStringstoDraw
        Public Function GetLOSFPdrmValues() As List(Of DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm) Implements DefensiveFirstFireInterface.GetLOSFPdrmValues
        'called by DFFViewConcreteC.UpdateView
        'retrives new EnemyValues to be displayed by the view
        'does not triggers recalculation of EnemyValues - already done
        Return SetEnemyValues
        End Function

        Public Sub Initialize() Implements DefensiveFirstFireInterface.Initialize
        'no implementation required so far
        End Sub

        Public Sub RegisterObserver(ByVal DFFObserverC As DFFObserverInterface) Implements DefensiveFirstFireInterface.RegisterObserver
        'called by DFFViewConcreteC.SetUpView
        'the Observer is the view as a whole NOT each EnemyUnit - as per the example
        DFFObserver = DFFObserverC
        End Sub

        Public Sub RemoveObserver() Implements DefensiveFirstFireInterface.RemoveObserver
        'no implementation required so far
        End Sub

        Public Sub SetLOSFPdrmValues(ByVal MovingUnits As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) Implements DefensiveFirstFireInterface.SetLOSFPdrmValues
        'called by DFFControllerConcreteC.SetLOSHFPdrmValues
        'triggers calculation of new set of EnemyUnit values; not an update of existing
        'classes out the calculation process
        Dim EnemyValues As New DFFEnemyValuesConcreteC
        'set shader used in Game.draw
        ShadertoShow = ConstantClassLibrary.ASLXNA.ShadeShow.DFFShade
        'returns list of new EnemyValues
        SetEnemyValues = EnemyValues.SetLOSFPdrmValues(MovingUnits)
        'tells the observers(the view) that new EnemyValues are available
        NotifyObservers()
        End Sub
        Private Sub NotifyObservers()
        'called by SetLOSFPdrmValues
        'calls UpdateView on all observers
        DFFObserver.UpdateView()
        End Sub
        End Class
        'CONTROLLER
        'interface
        Friend Interface DFFControllerInterface
        Sub SetLOSHFPdrm(ByVal MovingUnits As List(Of ObjectClassLibrary.ASLXNA.PersUniti))
        End Interface
        'concrete class
        Friend Class DFFControllerConcreteC
        Implements DFFControllerInterface
        Private DFFModel As DefensiveFirstFireInterface
        Friend Sub New(ByVal Newmodel As DefensiveFirstFireInterface)
        DFFModel = Newmodel
        'creates the view
        Dim DFFView As DFFViewConcreteC = New DFFViewConcreteC
        DFFView.SetupView(DFFModel, Me)
        End Sub
        Public Sub SetLOSHFPdrm(ByVal MovingUnits As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) Implements DFFControllerInterface.SetLOSHFPdrm
        'called by DFFVVCPattern.New
        'this is where external call comes into the pattern and triggers the process
        'asks the model to recalcuate new EnemyValues
        DFFModel.SetLOSFPdrmValues(MovingUnits)
        End Sub
        End Class
        'VIEW
        'interface
        Friend Interface DFFObserverInterface
        Sub UpdateView()
        End Interface
        'concrete class
        Friend Class DFFViewConcreteC
        Implements DFFObserverInterface
        Private ViewModel As DefensiveFirstFireInterface
        Private ViewController As DFFControllerInterface
        Friend Sub SetupView(ByVal NewModel As DefensiveFirstFireInterface, ByVal NewController As DFFControllerInterface)
        'called by DFFControllerConcreteC.New
        'passes in references to the model and controller and registers as an observer
        ViewModel = NewModel
        ViewController = NewController
        'add observers
        ViewModel.RegisterObserver(Me)
        End Sub
        Friend Sub UpdateView() Implements DFFObserverInterface.UpdateView
        'called by DefensiveFirstFireConcreteC.NotifyObservers
        'tells observers that new set of EnemyValues is availalble, gets them and processes changes to the display
        Dim AlreadyAdded As Boolean = False
        'get the new EnemyValues
        Dim EnemyHexValues As List(Of DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm) = ViewModel.GetLOSFPdrmValues()
        Dim FPdrmstring As String : Dim drmSign As String
        'create list of ShadeHexes which are drawn by Game.Draw
        Dim EnemyShade As storeShadeHex
        For Each EnemyUnit As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexValues
        AlreadyAdded = False
        If EnemyUnit.drm > 0 Then
        drmSign = "+"
        ElseIf EnemyUnit.drm = 0 Then
        drmSign = "-"
        Else
        drmSign = ""
        End If
        If EnemyUnit.LOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.None Then
        FPdrmstring = "No LOS"
        ElseIf EnemyUnit.LOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.BeyondLR Then
        FPdrmstring = "BR"
        ElseIf EnemyUnit.LOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.Target Then
        FPdrmstring = ""
        Else
        FPdrmstring = Trim(EnemyUnit.FP.ToString) & Trim(drmSign) & Trim(EnemyUnit.drm.ToString)
        End If
        'check for multiple entries in same hex
        For Each ExistingShade As storeShadeHex In ViewModel.storeStringstoDraw
        If ExistingShade.Hexnum = EnemyUnit.Hexnum Then
        If (Trim(FPdrmstring) = "No LOS" And Trim(ExistingShade.LevelClear) = "No LOS") Or (Trim(FPdrmstring) = "BR" And Trim(ExistingShade.LevelClear) = "BR") Then
        Else
        FPdrmstring = FPdrmstring & ", " & ExistingShade.LevelClear
        ExistingShade.SetFPdrmstring(FPdrmstring, CInt(ExistingShade.Hexnum))
        End If
        AlreadyAdded = True
        End If
        Next
        If Not AlreadyAdded Then
        EnemyShade = New storeShadeHex(EnemyUnit.Hexname, CInt(EnemyUnit.Hexnum), EnemyUnit.LOSStatus)
        EnemyShade.SetFPdrmstring(FPdrmstring, CInt(EnemyUnit.Hexnum))
        'Game.Draw will iterate through HexesToShade and StringsToDraw if shader is set (done in DefensiveFirstFireConcreteC.SetLOSHFPdrmValues)
        If EnemyUnit.LOSStatus <> ConstantClassLibrary.ASLXNA.LosStatus.NormalRange Then
        ViewModel.StoreHexesToShade.Add(EnemyShade)
        End If
        ViewModel.storeStringstoDraw.Add(EnemyShade)
        End If
        Next
        'sets shader again - could drop one
        ViewModel.ShadertoShow = ConstantClassLibrary.ASLXNA.ShadeShow.DFFShade
        End Sub
        End Class*/

class DFFEnemyValuesConcreteC {
     PersUniti[] MovingList;
     private Constantvalues.Nationality EnemySide1 = Constantvalues.Nationality.None;
     private Constantvalues.Nationality EnemySide2 = Constantvalues.Nationality.None;
     private LinkedList<TempSolution> TempSolList;
     private LinkedList<CombatTerrain> TempTerrCol = new LinkedList<CombatTerrain>();
     private LOSThreadManagerC ThreadManager = new LOSThreadManagerC();
     private LinkedList<LOSSolution> ValidSolutions_Thread = new LinkedList<LOSSolution>();  // Holds LOSCheck with LOS in thread situations
     private ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();
     private LinkedList<PersUniti> FireGroup_Thread = new LinkedList<PersUniti>();
     private LinkedList<PersUniti> TargGroup = new LinkedList<PersUniti>();
     private LinkedList<PersUniti> TempFireGroup = new LinkedList<PersUniti>();
     private ScenarioC scen = ScenarioC.getInstance();

    public LinkedList<EnemyHexLOSHFPdrm> SetLOSFPdrmValues(PersUniti[] MovingUnits ) {
        // called by DefensiveFirstFireConcreteC.SetLOSFPdrmValues
        // calculates LOS status, FP and drm on a per hex basis and returns as list(of EnemyHexLOSHFPdrm)

        EnemyHexLOSHFPdrm EnemyHexNewValues;
        LinkedList<EnemyHexLOSHFPdrm> EnemyHexList=new LinkedList<EnemyHexLOSHFPdrm>();  // object type that holds results data in a list
        Constantvalues.LosStatus PassLOSStatus = Constantvalues.LosStatus.None;
        double PassFP = 0;
        int Passdrm = 0; // variables that hold data during calculation
        boolean PassSolWorks = false;
        int DFFHexnum = 0;
        String PassHexname = "";
        int DFFLOSIndex = 0;
        Constantvalues.AltPos DFFPositioninHex = Constantvalues.AltPos.None;
        boolean SeeUsingCrestStatus;
        double DFFlevel = 0;
        int scennum = 0;

        // this is the moving stack
        MovingList = MovingUnits;
        // now get the list of enemy units
        Hex Movinghexclicked = MovingList[0].getbaseunit().getHex();
        Hex MovingHex = scen.getGameMap().getHex(MovingList[0].getbaseunit().getHexName());
        Location Movinglocation = MovingList[0].getbaseunit().gethexlocation();
        Constantvalues.AltPos MovingPosition = MovingList[0].getbaseunit().gethexPosition();
        boolean SeenUsingCrestStatus = false;

        if (IsInCrestStatus(MovingPosition)) {
            SeenUsingCrestStatus = true;
        }

        // instantiate various classes used during calculations
       LevelChecks LevelChk = new LevelChecks(Movinglocation);
        // set some data variables
        double Movinglevel = LevelChk.GetLocationPositionLevel(Movinglocation, MovingPosition);
        PersUniti MovingItem = MovingList[0];
        scennum = MovingItem.getbaseunit().getScenario();
        ScenarioC scen  = ScenarioC.getInstance();
        Scenario Scendet = scen.getScendet(); // retrieves scenario data
        Constantvalues.Map scenmap = Scendet.getMap();
        VASL.LOS.Map.Map MapInUse = scen.getGameMap();
        int ScenDustMist = Scendet.getMistDust();
        Constantvalues.Nationality MovingNationality = MovingItem.getbaseunit().getNationality();
        SetEnemy(MovingNationality, scennum);

        // store the values for moving hex
        EnemyHexNewValues = new EnemyHexLOSHFPdrm(Constantvalues.LosStatus.Target, 0, 0, MovingItem.getbaseunit().getHexName());
        EnemyHexList.add(EnemyHexNewValues);
        // get all enemy units
        PersUniti[] EnemyToCheck = new PersUniti[1];   /* temporary while debugging undo =(From getunit As
        ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where (getunit.BasePersUnit.Nationality = EnemySide1
        Or getunit.BasePersUnit.Nationality = EnemySide2 AndAlso
        getunit.BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible AndAlso getunit.
        BasePersUnit.OrderStatus<> ConstantClassLibrary.ASLXNA.OrderStatus.KIAInf AndAlso
        getunit.BasePersUnit.OrderStatus<> ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay)Select getunit).ToList*/


        // Going to delete all of this so don't fix LinkedList<GameLO> NewLOSResults = new LinkedList<GameLO>;
        // holds list of LOS results between pairs of see and seen locations
        // loop through and calculate values; first use For Each Next to create tempsolulions and assign values - can' t do in parallel
        for (int x = 0; x < EnemyToCheck.length; x++) {
            // Get new values for this unit
            // Check if Hex already added
            PersUniti EnemyUnit=EnemyToCheck[x];
            EnemyHexLOSHFPdrm AlreadyAddedTest;
            boolean AlreadyAdded = false;
            //DFFLOSIndex = EnemyUnit.getbaseunit().getLOCIndex();
            try {
                AlreadyAddedTest = null; /* temporary while debugging undo (From Qu As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList Where
                Qu.LOCIndex = DFFLOSIndex Select Qu).First;*/
                AlreadyAdded = true;
            } catch (Exception ex) {
                AlreadyAddedTest = null;
            }
            if (AlreadyAdded) {
                AlreadyAdded = false;
                AlreadyAddedTest = null;
            } else {
                // get new values - need to redo los each time because could be level/location difference
                // get LOS value
                TempSolution TempSol;
                // get the required input variables for Tempsolution
                DFFlevel = EnemyUnit.getbaseunit().getLevelinHex();
                Hex DFFHex = scen.getGameMap().getHex(EnemyUnit.getbaseunit().getHexName());
                PassHexname = EnemyUnit.getbaseunit().getHexName();
                DFFPositioninHex = EnemyUnit.getbaseunit().gethexPosition();
                SeeUsingCrestStatus = EnemyUnit.getbaseunit().IsInCrestStatus();
                // create TempSolution
                TempSol = new TempSolution(DFFHex, DFFlevel, DFFPositioninHex, MovingHex, Movinglevel, MovingPosition, PassSolWorks, TempSolList.size(), MapInUse);

                if (TempSol != null) {
                    // add to list of temp
                    TempSolList.add(TempSol);
                    // create new EnemyHexLOSHPdrm instance and add it to list
                    if (EnemyUnit.getbaseunit().getVisibilityStatus() != Constantvalues.VisibilityStatus.Concealed &&
                    EnemyUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Broken &&
                    EnemyUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Broken_DM &&
                    EnemyUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Disrupted &&
                    EnemyUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.DisruptedDM &&
                    EnemyUnit.getbaseunit().getCombatStatus() != Constantvalues.CombatStatus.FinalFirer &&
                    EnemyUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Prisoner &&
                    EnemyUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Unarmed) {
                        // PassLOStatus, PassFP, Passdrm passed as None, 0, 0 at this point
                        EnemyHexNewValues = new EnemyHexLOSHFPdrm(PassLOSStatus, PassFP, Passdrm, PassHexname);
                        EnemyHexList.add(EnemyHexNewValues);
                        EnemyHexNewValues.setSolID(TempSol.getID());
                        AlreadyAddedTest = null;
                    }
                }
            }
        }
        // temporary while debugging undo ThreadManager.Runthreads(TempSolList, TempTerrCol, EnemyHexList, NewLOSResults);

        // Set up valid solutions
        for (TempSolution Tempsol: TempSolList) {
            if (Tempsol.getSolworks()) {
                ThreadManager.CreateNewThreadSolution(Tempsol, ValidSolutions_Thread);
                // add firer(s) to FireGroup, target(s) to Target Group
                // TempTarget already exists but need to add unit to  a new TempFireGroup
                CreateTempFG(Tempsol);
                AddtoFireGroupandTargetGroupMVC(Tempsol.getID());
            }
            for (EnemyHexLOSHFPdrm EnemyHex: EnemyHexList){
                if (EnemyHex.getHexname() == Tempsol.getSeeHex().getName() && EnemyHex.getSolID() == Tempsol.getID()) {
                    // if LOS exists use range test to refine LOS value
                    if (EnemyHex.getLOSStatus() == Constantvalues.LosStatus.None){
                        LinkedList<PersUniti> TempLOSTestFireGroup = new LinkedList<PersUniti>();
                        for (PersUniti TestFireUnit: FireGroup_Thread) {
                            if (TestFireUnit.getFiringunit().getSolID() == Tempsol.getID()) {
                                TempLOSTestFireGroup.add(TestFireUnit);
                            }
                        }
                        EnemyHex.setLOSStatus(ThreadManager.LOSRangeTest(Tempsol.getSeeHex(), Tempsol.getSeenHex(), TempLOSTestFireGroup));
                    } else {
                        EnemyHex.setdrm(0); EnemyHex.setFP(0);
                    }
                }
            }
        }
        for (PersUniti MovingTarget: MovingList) {
            TargGroup.add(MovingTarget);
        }
        for (LOSSolution Validsol: ValidSolutions_Thread) {
            for (CombatTerrain ComTer: TempTerrCol) {
                if (ComTer.getSolID() == Validsol.getID()) {Validsol.AddtoLOSList(ComTer);}
            }
            for (AltHexGTerrain Althex: ThreadManager.AltHexLOSGroup) {
                if (Althex.getTempSolID() == Validsol.getID()) {Validsol.AddtoAltHexList(Althex);}
            }
        }
        // Update database table with new LOS results
        // going to delete this so don't fix
        /*for (GameLO LOSCheck: NewLOSResults) {
            // done outside of thread as LINQ to SQL not thread safe
            Maptables.AddNewLOSEntry(LOSCheck);
        }*/
        // clear Temp values
        ClearTempCombat(ThreadManager.AltHexLOSGroup);
        // Now do FP, drm and range calculations in a thread
        for (LOSSolution Validsol: ValidSolutions_Thread) {
            // last check - determine if Fire Group is valid
            // (ie no hexes with just leaders - there may be other tests; devise special test here because
            // not checking multi-hex firegroup; just checking if a hex contains more than a leader)
            LinkedList<PersUniti> ThreadFireGroup = new LinkedList<PersUniti>();
            for (PersUniti TestFireUnit: FireGroup_Thread) {
                if (TestFireUnit.getbaseunit().getSolID() == Validsol.getID()) {
                    ThreadFireGroup.add(TestFireUnit);
                }
            }
            if(ThreadFireGroup !=null) {
                if (!CheckHexFGValid(ThreadFireGroup)) {  // no fire possible and no shading will be applied
                    for (EnemyHexLOSHFPdrm EnemyFPdrmNewValues: EnemyHexList) {
                        if (EnemyFPdrmNewValues.getSolID() == Validsol.getID()) {
                            EnemyFPdrmNewValues.setLOSStatus(Constantvalues.LosStatus.None);
                            continue;
                        }
                    }
                }
            }else {
                // no fire possible and no shading will be applied
                // Exit Function
                // SHOULD BE ABLE TO DELETE THIS ELSE AS IT REPEATS CODE ABOVE
                /*If Not CheckHexFGValid(ThreadFireGroup) Then 'Exit Function ' no fire possible and no shading will be
                applied
                For Each EnemyFPdrmNewValues As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList
                If EnemyFPdrmNewValues.SolID = Validsol.ID Then
                EnemyFPdrmNewValues.LOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.None
                Continue For
                End If
                Next
                End If*/
            }
            // if viable solution exists, see if FireGroup can bring FP to bear
            boolean DoFPdrmCalc = false;
            for (EnemyHexLOSHFPdrm EnemyFPdrmNewValues: EnemyHexList) {
                if (EnemyFPdrmNewValues.getSolID() == Validsol.getID() ) {
                    if (EnemyFPdrmNewValues.getLOSStatus() == Constantvalues.LosStatus.None ||
                            EnemyFPdrmNewValues.getLOSStatus() == Constantvalues.LosStatus.BeyondLR) {
                        EnemyFPdrmNewValues.setFP(0);
                        EnemyFPdrmNewValues.setdrm(0);
                    }else {
                        DoFPdrmCalc = true;
                    }
                }
            }

            if (DoFPdrmCalc) {
                LinkedList<LOSSolution> NewSols = new LinkedList<LOSSolution>();
                // only want to pass one solution to CombatCalc
                NewSols.add(Validsol);
                // temporary while debugging undo iCombatCalc CombatCalc = new CombatCalcC(NewSols);
                // Need to reset Targetgroup SolId as must participate in all Solutions
                for (PersUniti TargUnit: TargGroup) {
                    TargUnit.getbaseunit().setSolID(Validsol.getID());
                }
                PassFP =0; // tempoary whiel debugging undo CombatCalc.CalcCombatFPandDRM(ThreadFireGroup, TargGroup, Scendet, Validsol.getID());
                if (PassFP == -99) {  // LOSBlocked by LOSH or other reason no longer valid (spray fire test failure)
                    Validsol.setSolworks(false);
                    PassFP = 0;
                    Passdrm = 0;
                }else {
                    Passdrm = -99;
                    // will display highest drm possible (can be different due to location, etc)
                    for (PersUniti TargetUnit: TargGroup) {
                        if (TargetUnit.getTargetunit().getAttackedbydrm() > Passdrm) {
                            Passdrm = TargetUnit.getTargetunit().getAttackedbydrm();
                        }
                    }
                }
            }
            for (EnemyHexLOSHFPdrm EnemyFPdrmNewValues: EnemyHexList) {
                if (EnemyFPdrmNewValues.getSolID() == Validsol.getID()) {
                    EnemyFPdrmNewValues.setdrm(Passdrm);
                    EnemyFPdrmNewValues.setFP(PassFP);
                    if (Validsol.getSolworks() == false) {EnemyFPdrmNewValues.setLOSStatus(Constantvalues.LosStatus.None);}
                    break;
                }
            }
        }
        return EnemyHexList;
    }


    public void CreateTempFG(TempSolution Tempsol) {
        // called by EnemyValuesConcreteC.CreateNewSolution
        // sets up the TempFireGroup based on FirerHex in Tempsol
        boolean Result;
        String AddingThing;

        // get units in the Tempsol Targ hex- stored as Targethex due to DFF loop
        LinkedList<PersUniti> UnitList = new LinkedList<PersUniti>();
        //temporary while debugging undo
        /*Dim UnitList As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =(From selunit As
        ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.LOCIndex = FirerLoc).
        ToList*/

        for (PersUniti Firerfound : UnitList) {
            if (Firerfound.getbaseunit().getNationality() == EnemySide1 || Firerfound.getbaseunit().getNationality() == EnemySide2) {
                // is enemy
                // don' t include concealed, broken or Units marked as FinalFired - OTHER CONDITIONS ?
                if (Firerfound.getbaseunit().getVisibilityStatus() != Constantvalues.VisibilityStatus.Concealed &&
                        Firerfound.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Broken &&
                        Firerfound.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Broken_DM &&
                        Firerfound.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Disrupted &&
                        Firerfound.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.DisruptedDM &&
                        Firerfound.getbaseunit().getCombatStatus() != Constantvalues.CombatStatus.FinalFirer &&
                        Firerfound.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Prisoner &&
                        Firerfound.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Unarmed) {
                    Result = AddtoTempFG(Firerfound, Tempsol.getID());
                    AddingThing = Firerfound.getbaseunit().getUnitName();
                    if (Result == false) {
                        //System.Windows.Forms.MessageBox.Show("Failure to Add Target Unit: " & Trim(AddingThing), "DFF pattern: CreatetempFG");
                    }
                }
            }
        }
    }
    private boolean CheckHexFGValid(LinkedList<PersUniti> firegrouptouse) {
            // called by EnemyValuesConcreteC.SetLOSFPdrmValues
            // used check that there is more in a hex than a leader
            for (PersUniti FiringUnit: firegrouptouse) {
                if ((FiringUnit.getbaseunit().getUnittype() == Constantvalues.Utype.Leader) ||   //FEELS LIKE AN ERROR IN THE VS CODE TYPETYPE SHOULD BE UTYPE
                (FiringUnit.getbaseunit().getUnittype() == Constantvalues.Utype.LdrHero &&
                 FiringUnit.getFiringunit().getUseHeroOrLeader() == Constantvalues.Utype.Leader)) {
                    // do nothing
                }else {
                    return true;
                }
            }
            // if here then only leader present and no valid FG exists in the hex
            return false;
    }

    private boolean SetEnemy(Constantvalues.Nationality FirstFriendly, int scennum){
        // called by SetLOSHFPdrmValues
        // set the nationality values of the "enemy" side
        ScenarioC scen  = ScenarioC.getInstance();
        Scenario Scendet = scen.getScendet();  // retrieves scenario data
        if (FirstFriendly == Scendet.getATT1() || FirstFriendly == Scendet.getATT2()) {
            EnemySide1 = Scendet.getDFN1();
            EnemySide2 = Scendet.getDFN2();
        } else if (FirstFriendly == Scendet.getDFN1() || FirstFriendly == Scendet.getDFN2()) {
            EnemySide1 = Scendet.getATT1();
            EnemySide2 = Scendet.getATT2();
        } else {
            return false; // no nationality values set
        }
        return true;
    }
    public void ClearTempCombat(LinkedList<AltHexGTerrain> TempAltHexLosGroup) {
        TempFireGroup.clear();
        TempSolList.clear();
        TempTerrCol.clear();
        TempAltHexLosGroup.clear();
    }

    public boolean AddtoFireGroupandTargetGroupMVC(int SolUsedID) {
        // called by EnemyvaluesConcreteC.SetLOSFPdrmValues
       // adds units from valid Temp Solution to FireGroup and TargetGroup

       int AddInf  = 1; int AddMG = 2;  // variables used to determine which type
       // temparay while debugging undo  dim unititem As DataClassLibrary.OrderofBattle
       DataC ThreadLinq = DataC.GetInstance();  // use null values if sure instance already exists
       // add firer(s) to FireGroup
       int Firerlink; boolean FirerAdded= false;
       if (TempFireGroup.size() > 0) {
           for (PersUniti TempFiringUnit : TempFireGroup) {
               if (TempFiringUnit.getbaseunit().getSolID() == SolUsedID) {
                   Firerlink = TempFiringUnit.getbaseunit().getUnit_ID();
                   if(Constantvalues.Typetype.Personnel == TempFiringUnit.getbaseunit().getTypeType_ID()) {
                      // unit - check if already added to Fire Group
                      for (PersUniti AlreadyAdded : FireGroup_Thread) {
                          if (AlreadyAdded.getbaseunit().getUnit_ID() == Firerlink && Constantvalues.Typetype.Personnel == AlreadyAdded.getbaseunit().getTypeType_ID()) {
                               // alreadyadded so do nothing
                               FirerAdded = true;
                               break;
                          }
                      }
                      if (!FirerAdded) {
                          TempFiringUnit.getFiringunit().setCombatStatus(Constantvalues.CombatStatus.Firing);
                          SetMGInherent(TempFiringUnit);
                          FireGroup_Thread.add(TempFiringUnit);
                      }
                   }else {  // MG
                          // check if already added to Fire Group
                   }
                   return true;
               }
           }
       }
       return false;
    }

    public boolean AddtoTempFG(PersUniti Unititem, int Tempsolid) {
            // creates a new instance of TempFireUnit and adds it
            // to TempFireGroup
            // if LOS exists, TempFireUnits are later added to FireGroup
            PersUniti TempUnititem = Unititem;
            try {
                TempUnititem.getbaseunit().setSolID(Tempsolid);
                // temporary while debugging undo
                /*Dim ObjCreate As ObjectFactoryClassLibrary.aslxna.PersCreation = New ObjectFactoryClassLibrary.aslxna.PersCreation
                        Unititem = ObjCreate.CreatefiringUnitandProperty(Unititem)*/
                TempFireGroup.add(Unititem);
                return true;
            }catch (Exception e) {
                return false;
                // MsgBox("Error adding unit to TempFireGroup", , "IFT.AddtoTempFG")
            }

    }

    private void SetMGInherent(PersUniti firingunit) {
        // temporary while debugging undo
        /*Dim SWCreate As ObjectFactoryClassLibrary.aslxna.SWCreation = New ObjectFactoryClassLibrary.aslxna.SWCreation
        if (firingunit.getbaseunit().getFirstSWLink() > 0) {
            Dim TestForMG = (From MaybeMG As ObjectClassLibrary.ASLXNA.SuppWeapi In scencolls.SWCol Where
            MaybeMG.BaseSW.Unit_ID = firingunit.BasePersUnit.FirstSWLink).First
            TestForMG.FiringSW = SWCreate.createfiringswproperty(TestForMG)
            firingunit.FiringPersUnit.FiringMGs.Add(TestForMG)
            If TestForMG.FiringSW.IsMG Then firingunit.FiringPersUnit.UsingfirstMG = True
        }
        if (firingunit.getbaseunit().getSecondSWLink() > 0) {
            Dim TestForMG = (From MaybeMG As ObjectClassLibrary.ASLXNA.SuppWeapi In scencolls.SWCol Where
            MaybeMG.BaseSW.Unit_ID = firingunit.BasePersUnit.SecondSWlink).First
            TestForMG.FiringSW = SWCreate.createfiringswproperty(TestForMG)
            firingunit.FiringPersUnit.FiringMGs.Add(TestForMG)
            If TestForMG.FiringSW.IsMG Then firingunit.FiringPersUnit.UsingsecondMG = True
        }
        if (firingunit.FiringPersUnit.getUsingfirstMG() == true && firingunit.FiringPersUnit.getUsingsecondMG() == true) {
            firingunit.FiringPersUnit.setUsingInherentFP(false);
        } else {
            firingunit.FiringPersUnit.setUsingInherentFP(true);
        }*/
    }
    private boolean IsInCrestStatus(Constantvalues.AltPos MovingPosition) {
        return MovingPosition == Constantvalues.AltPos.CrestStatus1 ||
                MovingPosition == Constantvalues.AltPos.CrestStatus2 ||
                MovingPosition == Constantvalues.AltPos.CrestStatus3 ||
                MovingPosition == Constantvalues.AltPos.CrestStatus4 ||
                MovingPosition == Constantvalues.AltPos.CrestStatus5 ||
                MovingPosition == Constantvalues.AltPos.CrestStatus6 ||
                MovingPosition == Constantvalues.AltPos.WACrestStatus1 ||
                MovingPosition == Constantvalues.AltPos.WACrestStatus2 ||
                MovingPosition == Constantvalues.AltPos.WACrestStatus3 ||
                MovingPosition == Constantvalues.AltPos.WACrestStatus4 ||
                MovingPosition == Constantvalues.AltPos.WACrestStatus5 ||
                MovingPosition == Constantvalues.AltPos.WACrestStatus6;
    }
}