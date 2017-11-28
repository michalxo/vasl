/*
package VASL.build.module.map;

*/
/**
 * Created by dougr_000 on 5/12/2017.
 * Uses interfaces and concrete classes to implement MVC pattern for determining IFT
 * combat opportunities based on either Target or Firer
 */
package VASL.build.module.fullrules.IFTCombatClasses;


import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.EnemyHexLOSHFPdrm;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.awt.*;
import java.util.*;

import java.awt.image.BufferedImage;

public class IFTMVCPattern {
    // this class creates and uses the IFT MVC pattern which updates and displays the
    // LOSH, FP and drm of all enemy units on the mapwindow for a fire-capable (prep, def, advf phases) unit.
    private IFTFireInterface IFTFire;

    // constructor
    public IFTMVCPattern(PersUniti[] TempFireGroup) {
        // creates the model, then the controller, which creates the view
        // then trigger the model by calling SetLOSHFPdrm on the controller
        IFTFire = new IFTFireConcreteC();
        IFTControllerInterface IFTCont = new IFTControllerConcreteC(IFTFire);
        // Tells the controller to recalcuate EnemyValues
        IFTCont.SetLOSHFPdrm(TempFireGroup);
    }

    // these are functions visible to ASLXNA that pass the pattern results back to the main program
    public Constantvalues.ShadeShow GetShaderToShow() {
        return IFTFire.getShadertoShow();
    }

    public LinkedList<storeShadeHex> getStoreHexestoShade()  {
        return IFTFire.getstoreHexestoShade();
    }


    public LinkedList<storeShadeHex> getStoreStringstoDraw() {
        return IFTFire.getstoreStringstoDraw();
    }
}

// MODEL
// interface
interface IFTFireInterface {

        public Constantvalues.ShadeShow getShadertoShow();
        public void setShadertoShow(Constantvalues.ShadeShow addShadertoShow);
        public LinkedList<storeShadeHex> getstoreHexestoShade();
        public LinkedList<storeShadeHex> getstoreStringstoDraw();
        public void addstoreHexestoShade(storeShadeHex addShadeHex);
        public void addstoreStringstoDraw(storeShadeHex addShadeHex);

        public void Initialize();

        public void SetLOSFPdrmValues(PersUniti[] TempFireGroup);

        public LinkedList<EnemyHexLOSHFPdrm> GetLOSFPdrmValues();

        public void RegisterObserver(IFTObserverInterface IFTObserverC);

        public void RemoveObserver();
}

// concrete class
class IFTFireConcreteC implements IFTFireInterface {

        private IFTObserverInterface IFTObserver;
        private LinkedList<EnemyHexLOSHFPdrm> SetEnemyValues = new LinkedList<EnemyHexLOSHFPdrm>();
        public Constantvalues.ShadeShow pShadertoShow;
        LinkedList<storeShadeHex> pstoreHexestoShade = new LinkedList<storeShadeHex>();
        LinkedList<storeShadeHex> pstoreStringstoDraw = new LinkedList<storeShadeHex>();

        public LinkedList<EnemyHexLOSHFPdrm> GetLOSFPdrmValues() {
            // called by DFFViewConcreteC.UpdateView
            // retrives new EnemyValues to be displayed by the view
            // does not trigger recalculation of EnemyValues - already done
            return SetEnemyValues;
        }

        public void Initialize() {
            // no implementation required so far
        }

        public void RegisterObserver(IFTObserverInterface IFTObserverC) {
            // called by DFFViewConcreteC.SetUpView
            // the Observer is the view as a whole NOT each EnemyUnit - as per the example
            IFTObserver = IFTObserverC;
        }

        public void RemoveObserver() {
            // no implementation required so far
        }

        public void SetLOSFPdrmValues(PersUniti[] TempFireGroup) {
            // called by IFTControllerConcreteC.SetLOSHFPdrmValues
            // triggers calculation of new set of EnemyUnit values; not an update of existing
            // classes out the calculation process
            DFFEnemyValuesConcreteC EnemyValues = new DFFEnemyValuesConcreteC();

            // set shader used in Game.draw THIS NEEDS TO BE MOVED TO ASLXNA
            setShadertoShow(Constantvalues.ShadeShow.IFTShade);
            // returns list of new EnemyValues
            SetEnemyValues = EnemyValues.SetLOSFPdrmValues(TempFireGroup);
            // tells the observers(the view) that new EnemyValues are available
            NotifyObservers();
        }

        private void NotifyObservers() {
            // called by SetLOSFPdrmValues
            // calls UpdateView on all observers
            IFTObserver.UpdateView();
        }
        public Constantvalues.ShadeShow getShadertoShow() {return pShadertoShow;}
        public void setShadertoShow(Constantvalues.ShadeShow value){pShadertoShow=value;}

        public LinkedList<storeShadeHex> getstoreHexestoShade() {return pstoreHexestoShade;}
        public LinkedList<storeShadeHex> getstoreStringstoDraw() {return pstoreStringstoDraw;}

        public void addstoreHexestoShade(storeShadeHex addShadeHex) {
            pstoreHexestoShade.add(addShadeHex);
        }
        public void addstoreStringstoDraw(storeShadeHex addShadeHex) {
            pstoreStringstoDraw.add(addShadeHex);
        }
}

// CONTROLLER
// interface
interface IFTControllerInterface {
    public void SetLOSHFPdrm(PersUniti[] TempFireGroup);
}

// concrete class
class IFTControllerConcreteC implements IFTControllerInterface {
    private IFTFireInterface IFTModel;

    public IFTControllerConcreteC(IFTFireInterface Newmodel) {
        IFTModel = Newmodel;
        // creates the view
        IFTViewConcreteC IFTView = new IFTViewConcreteC(IFTModel, this);
    }

    public void SetLOSHFPdrm(PersUniti[] TempFireGroup) {
    //Implements IFTControllerInterface.SetLOSHFPdrm
    // called by IFTMVCPattern.New
    // this is where external call comes into the pattern and triggers the process
    // asks the model to recalcuate new EnemyValues
        IFTModel.SetLOSFPdrmValues(TempFireGroup);
    }
}

// VIEW
// interface
interface IFTObserverInterface {
    void UpdateView();
}

// concrete class
class IFTViewConcreteC implements IFTObserverInterface {
    private IFTFireInterface ViewModel;
    private IFTControllerInterface ViewController;

    public IFTViewConcreteC(IFTFireInterface NewModel, IFTControllerInterface NewController) {
        // called by IFTControllerConcreteC.New
        // passes in references to the model and controller and registers as an observer
        ViewModel = NewModel;
        ViewController = NewController;
        // add observers
        ViewModel.RegisterObserver(this);
    }

    public void UpdateView() {
        // called by IFTFireConcreteC.NotifyObservers
        // tells observers that new set of EnemyValues is available, gets them and processes changes to the display
        boolean AlreadyAdded = false;
        // get the new EnemyValues
        /*LinkedList<EnemyHexLOSHFPdrm> EnemyHexValues = ViewModel.GetLOSFPdrmValues();
        String FPdrmstring;
        String drmSign;
        // create list of ShadeHexes which are drawn by Game.Draw
        storeShadeHex EnemyShade;

        for (EnemyHexLOSHFPdrm EnemyUnit: EnemyHexValues) {
            AlreadyAdded = false;
            if (EnemyUnit.getdrm() > 0) {
                drmSign = "+";
            } else if (EnemyUnit.getdrm() == 0) {
                drmSign = "-";
            } else {
                drmSign = "";
            }

            if (EnemyUnit.getLOSStatus() == Constantvalues.LosStatus.None) {
                FPdrmstring = "No LOS";
            } else if(EnemyUnit.getLOSStatus() ==Constantvalues.LosStatus.BeyondLR) {
                FPdrmstring = "BR";
            } else if(EnemyUnit.getLOSStatus() ==Constantvalues.LosStatus.Target) {
                FPdrmstring = "";
            } else {
                FPdrmstring = (Integer.toString((int)EnemyUnit.getFP()).trim()) + (drmSign.trim()) + (Integer.toString(EnemyUnit.getdrm()).trim());
            }
            // check for multiple entries in same hex
            if (!ViewModel.getstoreStringstoDraw().isEmpty()){
                for (storeShadeHex ExistingShade: ViewModel.getstoreStringstoDraw()) {
                    if(ExistingShade.getHexnum()==EnemyUnit.getHexnum()){
                        if((FPdrmstring=="No LOS" && ExistingShade.getLevelClear()=="No LOS") || (FPdrmstring=="BR" && ExistingShade.getLevelClear()=="BR")){
                        }else {
                            FPdrmstring = FPdrmstring + ", " + ExistingShade.getLevelClear();
                            ExistingShade.SetFPdrmstring(FPdrmstring, ExistingShade.getHexnum());
                        }
                        AlreadyAdded=true;
                    }
                }
            }

            if (!AlreadyAdded){
                EnemyShade = new storeShadeHex(EnemyUnit.getHexname(), EnemyUnit.getHexnum(), EnemyUnit.getLOSStatus());
                EnemyShade.SetFPdrmstring(FPdrmstring, EnemyUnit.getHexnum());
                // Game.Draw will iterate through HexesToShade and StringsToDraw if shader is set (done in DefensiveFirstFireConcreteC.SetLOSHFPdrmValues)
                if(EnemyUnit.getLOSStatus() != Constantvalues.LosStatus.NormalRange){
                    ViewModel.addstoreHexestoShade(EnemyShade);
                }
                ViewModel.addstoreStringstoDraw(EnemyShade);
            }
        }
        // sets shader again - could drop one
        // DROP THIS AS THIS SET SHOULD BE DONE ONLY IN VIEWMODEL CLASS (IFTFireConcretec)
        ViewModel.setShadertoShow(Constantvalues.ShadeShow.IFTShade);
*/
    }
}


/*
public class EnemyValuesConcreteC {
    private FiringList As List(Of ObjectClassLibrary.ASLXNA.PersUniti);
    private int EnemySide1 = 0;
    private int EnemySide2 = 0;
    private TempSolList As New List(Of LOSClassLibrary.ASLXNA.TempSolution);
    private TempTerrCol As New List(Of ObjectClassLibrary.ASLXNA.CombatTerrain);
    private CombatTerrCol As New List(Of ObjectClassLibrary.ASLXNA.CombatTerrain);
    private TemptargetGroup As New List(Of ObjectClassLibrary.ASLXNA.PersUniti);
    private FireGroup_Thread As New List(Of ObjectClassLibrary.ASLXNA.PersUniti);
    private TargGroup_Thread As New List(Of ObjectClassLibrary.ASLXNA.PersUniti);  // holds selected items with LOS
    private ThreadManager As New LOSClassLibrary.ASLXNA.ThreadManagerC;
    private ValidSolutions_Thread As New List(Of LOSClassLibrary.ASLXNA.LOSSolution); // Holds LOSCheck with LOS in thread situations
    public FinalDRMLIst As New List(Of DataClassLibrary.ASLXNA.IFTMods)  // holds info about drm for current combat
    private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc =ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance;

    public SetLOSFPdrmValues(ByVal TempFireGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) As List(Of EnemyHexLOSHFPdrm) {
        // called by IFTConcreteC.SetLOSFPdrmValues
        // calculates LOS status, FP and drm on a per hex basis and returns as list(of EnemyHexLOSHFPdrm)
        EnemyHexLOSHFPdrm EnemyHexNewValues;
        Dim EnemyHexList = New List(Of EnemyHexLOSHFPdrm);
        int PassLOSStatus;
        double PassFP;
        int Passdrm;
        String PassHexname;
        int TargHexnum = 0;
        boolean PassSolWorks = False;
        int TargLOSIndex = 0;
        int TargPositioninHex = 0;
        // boolean SeeUsingCrestStatus;
        double Targlevel = 0;
        int Scennum = 0;
        // clear existing values - THIS IS A PROBLEM - KEEP TESTING
        TempSolList.Clear();
        'Game.Scenario.IFT.ValidSolutions_Thread.Clear()
        'Game.Scenario.IFT.CombatTerrCol.Clear()
        'Game.Scenario.IFT.TempFireGroup.Clear()
        'Game.Scenario.IFT.FireGroup_Thread.Clear()
        ThreadManager.AltHexLOSGroup.Clear();
        // this is the firing stack
        FiringList = TempFireGroup;
        // now get the list of enemy units
        Dim FiringUnit As ObjectClassLibrary.ASLXNA.PersUniti = FiringList.Item(0);
        Dim Firinghexclicked As Integer = FiringUnit.BasePersUnit.Hexnum;
        Dim Firinglocation As Integer = FiringUnit.BasePersUnit.hexlocation;
        int FiringPosition = FiringUnit.BasePersUnit.hexPosition;
        boolean SeenUsingCrestStatus = False;

        If(FiringPosition >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And FiringPosition <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6)
        Or(FiringPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And FiringPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        {
            SeenUsingCrestStatus = True;
        }
        // instantiate various classes used during calculations
        Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance() 'use null values when sure instance already exists
        Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        // use null values when sure that instance already exists
        Dim LocationCol As IQueryable (Of MapDataClassLibrary.GameLocation) =Maptables.LocationCol
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
        Dim LoCtouse As MapDataClassLibrary.
        GameLocation = GetLocs.RetrieveLocationfromMaptable(Firinghexclicked, Firinglocation)
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(LocationCol)
        // set some data variables
        double Firinglevel = LevelChk.GetLocationPositionLevel(Firinghexclicked, Firinglocation, FiringPosition);
        double FiringLOSIndex = LoCtouse.LocIndex
        Scennum = CInt(FiringUnit.BasePersUnit.Scenario);
        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(Scennum) 'retrieves scenario data
        int scenmap = CInt(Scendet.Map);
        int ScenDustMist = CInt(Scendet.MistDust);
        int ScenPhase = CInt(Scendet.Phase);
        int Friendly2 = 0;
        int FiringNationality = FiringUnit.BasePersUnit.Nationality;
        if (FiringNationality = Scendet.ATT1) {
            Friendly2 = CInt(Scendet.ATT2);
        } else {
            Friendly2 = CInt(Scendet.DFN2);
        }
        SetEnemy(FiringNationality, Scennum) // sets value of EnemySide1 and EnemySide2
        // store the values for Firing hex
        EnemyHexNewValues = New
        EnemyHexLOSHFPdrm(ConstantClassLibrary.ASLXNA.LosStatus.Target, 0, 0, GetLocs.GetnamefromdatatableMap(Firinghexclicked), Firinghexclicked, FiringLOSIndex);
        EnemyHexList.Add(EnemyHexNewValues);
        // get all enemy units
        Dim EnemyToCheck As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =(From getunit As
        ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where (getunit.BasePersUnit.Nationality = EnemySide1
        Or getunit.
        BasePersUnit.Nationality = EnemySide2 And getunit.
        BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible)Select getunit).ToList;

        Dim NewLOSResults = New List(Of MapDataClassLibrary.GameLO)
        ' holds list of LOS results between pairs of see and seen locations
        'loop through and calculate values
        'first use For Each Next to create tempsolulions and assign values - can' t do in parallel
        for Each EnemyUnit As ObjectClassLibrary.ASLXNA.PersUniti In EnemyToCheck
            // Get new values for this unit
            // Check if Hex already added
            Dim AlreadyAddedTest As EnemyHexLOSHFPdrm;
            boolean AlreadyAdded = False;
            TargLOSIndex = CInt(EnemyUnit.BasePersUnit.LOCIndex);

            try {
                AlreadyAddedTest = (From Qu As EnemyHexLOSHFPdrm In EnemyHexList Where Qu.LOCIndex = TargLOSIndex Select Qu).
                First;
                AlreadyAdded = True;
            } catch (Exception ex) {
                AlreadyAddedTest = Nothing;
            }
            if (AlreadyAdded) {
                AlreadyAdded = False;
                AlreadyAddedTest = Nothing;
            } else {
                'If EnemyUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible Then
                // get new values - need to redo los each time because could be level/location difference
                Dim TempSol As LOSClassLibrary.ASLXNA.TempSolution
                        // get the required input variables for Tempsolution
                        Targlevel = CSng(EnemyUnit.BasePersUnit.LevelinHex)
                ': Targlocation = CInt(EnemyUnit.hexlocation)
                TargHexnum = CInt(EnemyUnit.BasePersUnit.Hexnum);
                PassHexname = Trim(EnemyUnit.BasePersUnit.Hexname);
                TargPositioninHex = CInt(EnemyUnit.BasePersUnit.hexPosition);
                SeeUsingCrestStatus = EnemyUnit.BasePersUnit.IsInCrestStatus;
                // create TempSolution
                TempSol = New
                LOSClassLibrary.ASLXNA.TempSolution(Firinghexclicked, Firinglevel, FiringLOSIndex, FiringPosition, TargHexnum, Targlevel, TargLOSIndex, TargPositioninHex, PassSolWorks, TempSolList.Count, scenmap)
                if (IsNothing(TempSol)) {
                    Continue For;
                }
                // add to list of temp
                TempSolList.Add(TempSol);
                // create new EnemyHexLOSHPdrm instance and add it to list
                // PassLOStatus, PassFP, Passdrm passed as 0 at this point
                EnemyHexNewValues = New
                EnemyHexLOSHFPdrm(PassLOSStatus, PassFP, Passdrm, PassHexname, TargHexnum, TargLOSIndex);
                EnemyHexList.Add(EnemyHexNewValues);
                EnemyHexNewValues.SolID = TempSol.ID;
                'End If
                AlreadyAddedTest = Nothing;
            }
        }
        // get concealed enemy units
        Dim EnemyConToCheck As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =(From getunit As ObjectClassLibrary.ASLXNA.PersUniti In
        Scencolls.Unitcol Where (getunit.BasePersUnit.Nationality = EnemySide1 Or getunit.BasePersUnit.Nationality = EnemySide2 And
        getunit.BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed)Select getunit).ToList;
        'EnemyConToCheck = Linqdata.GetAllConForOneSide(EnemySide1, EnemySide2)
        for Each EnemyConUnit As ObjectClassLibrary.ASLXNA.PersUniti In EnemyConToCheck {
            // Get new values for this unit
            // Check if Hex already added
            Dim AlreadyAddedTest As EnemyHexLOSHFPdrm;
            boolean AlreadyAdded = False;
            TargLOSIndex = CInt(EnemyConUnit.BasePersUnit.LOCIndex);
            try {
                AlreadyAddedTest = (From Qu As EnemyHexLOSHFPdrm In EnemyHexList Where Qu.LOCIndex = TargLOSIndex
                Select Qu).First;
                AlreadyAdded = True;
            } catch (Exception ex) {
                AlreadyAddedTest = Nothing;
            }
            if (AlreadyAdded) {
                AlreadyAdded = False;
                AlreadyAddedTest = Nothing;
            } else {
                // get new values - need to redo los each time because could be level/location difference
                Dim TempSol As LOSClassLibrary.ASLXNA.TempSolution;
                // get the required input variables for Tempsolution
                Targlevel = CSng(EnemyConUnit.BasePersUnit.LevelinHex) ': Targlocation = CInt(EnemyUnit.hexlocation)
                TargHexnum = CInt(EnemyConUnit.BasePersUnit.Hexnum);
                PassHexname = Trim(EnemyConUnit.BasePersUnit.Hexname);
                TargPositioninHex = CInt(EnemyConUnit.BasePersUnit.hexPosition);
                SeeUsingCrestStatus = EnemyConUnit.BasePersUnit.IsInCrestStatus;
                // create TempSolution
                TempSol = new LOSClassLibrary.ASLXNA.TempSolution(Firinghexclicked, Firinglevel, FiringLOSIndex, FiringPosition, TargHexnum, Targlevel, TargLOSIndex, TargPositioninHex, PassSolWorks, TempSolList.Count, scenmap)
                if (IsNothing(TempSol)) {
                    Continue For;
                }
                // add to list of temp
                TempSolList.Add(TempSol);
                // create new EnemyHexLOSHPdrm instance and add it to list
                // PassLOStatus, PassFP, Passdrm passed as 0 at this point
                EnemyHexNewValues = new EnemyHexLOSHFPdrm(PassLOSStatus, PassFP, Passdrm, PassHexname, TargHexnum, TargLOSIndex);
                EnemyHexList.Add(EnemyHexNewValues);
                EnemyHexNewValues.SolID = TempSol.ID;
                AlreadyAddedTest = Nothing;
            }
        }

        // create type and assemble data for parallel.foreach to avoid LINQ queries in threads
        // loop through and calculate values
        ThreadManager.RunParallelthreads(TempSolList, TempTerrCol, EnemyHexList, NewLOSResults);
        // Set up valid solutions
        for Each Tempsol As LOSClassLibrary.ASLXNA.TempSolution In TempSolList {
            if (Tempsol.Solworks) {
                ThreadManager.CreateNewThreadSolution(Tempsol, ValidSolutions_Thread);
                // add firer(s) to FireGroup, target(s) to Target Group
                // Tempfirer already exists but need to add unit to  a new TemptargGroup
                CreateTempTargGroup(Tempsol);
                AddtoFireGroupandTargetGroupMVC(Tempsol.ID);
                ' + 1)
            }
            for Each enemyhex As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList {
                if (enemyhex.LOCIndex = Tempsol.SeenLOSIndex And enemyhex.SolID = Tempsol.ID){
                    // if LOS exists use range test to refine LOS value
                    if (enemyhex.LOSStatus = 0) {
                        Dim TempLOSTestFireGroup = New List(Of ObjectClassLibrary.ASLXNA.PersUniti);
                        for Each TestFireUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup_Thread {
                            if (TestFireUnit.BasePersUnit.SolID = Tempsol.ID) {
                                TempLOSTestFireGroup.Add(TestFireUnit);
                            }
                        }
                        enemyhex.LOSStatus = ThreadManager.LOSRangeTest(Tempsol.SeeHexNum, Tempsol.SeenHexNum, TempLOSTestFireGroup);
                        'FiringNationality, Friendly2)
                    } else {
                        enemyhex.drm = 0;
                        enemyhex.FP = 0;
                    }
                }
            }
        }
        ThreadManager.AddtoCombatTerrain(TempTerrCol, CombatTerrCol);
        for Each Validsol As LOSClassLibrary.ASLXNA.LOSSolution In ValidSolutions_Thread {
            for Each ComTer As ObjectClassLibrary.ASLXNA.CombatTerrain In CombatTerrCol {
                if (ComTer.SolID = Validsol.ID) { Validsol.AddtoLOSList(ComTer);}
            }
            for Each Althex As ObjectClassLibrary.ASLXNA.AltHexGTerrain In ThreadManager.AltHexLOSGroup {
                if (Althex.TempSolID = Validsol.ID) {Validsol.AddtoAltHexList(Althex); }
            }
        }
        // Update database table with new LOS results
        for Each LOSCheck As MapDataClassLibrary.GameLO In NewLOSResults {
            // done outside of thread as LINQ to SQL not thread safe
            Maptables.AddNewLOSEntry(LOSCheck);
        }
        // clear Temp values
        ClearTempCombat(ThreadManager.AltHexLOSGroup);
        //  Now do FP, drm and range calculations in a thread
        for Each Validsol As LOSClassLibrary.ASLXNA.LOSSolution In ValidSolutions_Thread {
            Dim ThreadTargGroup As New List(Of ObjectClassLibrary.ASLXNA.PersUniti);
            for Each TestTargUnit As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup_Thread;
                if (TestTargUnit.BasePersUnit.SolID = Validsol.ID) {
                    ThreadTargGroup.Add(TestTargUnit);
                }
            }
            if (!(IsNothing(FireGroup_Thread)) && !(IsNothing(ThreadTargGroup))) {
                if (!CheckHexFGValid(FireGroup_Thread)) {  // Exit Function, no fire possible and no shading will be applied
                    for Each EnemyFPdrmNewValues As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList {
                        if (EnemyFPdrmNewValues.SolID = Validsol.ID) {
                            EnemyFPdrmNewValues.LOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.None;
                            Continue For;
                        }
                    }
                }
            }
            else {
                // no fire possible and no shading will be applied
                // Exit Function
                if (!CheckHexFGValid(FireGroup_Thread)) { // Exit Function, no fire possible and no shading will be applied
                    for Each EnemyFPdrmNewValues As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList {
                        if (EnemyFPdrmNewValues.SolID = Validsol.ID) {
                            EnemyFPdrmNewValues.LOSStatus = ConstantClassLibrary.ASLXNA.LosStatus.None;
                            Continue For;
                        }
                    }
                }
            }

        }
        // if viable solution exists, see if FireGroup can bring FP to bear
        boolean DoFPdrmCalc = False;
        for Each EnemyFPdrmNewValues As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList {
            if(EnemyFPdrmNewValues.SolID=Validsol.ID){
                if(EnemyFPdrmNewValues.LOSStatus=ConstantClassLibrary.ASLXNA.LosStatus.None Or EnemyFPdrmNewValues.LOSStatus=ConstantClassLibrary.ASLXNA.LosStatus.BeyondLR){
                    EnemyFPdrmNewValues.FP=0:EnemyFPdrmNewValues.drm=0;
                }else{
                    DoFPdrmCalc=True;
                }
            }
        }
        'Dim Passlossstatus As Integer = 0
        if (DoFPdrmCalc){
            Dim NewSols=New List(Of LOSClassLibrary.ASLXNA.LOSSolution); // only want to pass one solution to CombatCalc
            NewSols.Add(Validsol);
            Dim CombatCalc As CombatClassLibrary.aslxna.iCombatCalc=New CombatClassLibrary.aslxna.CombatCalcC(NewSols);
            // Need to reset Firegroup SolId as must participate in all Solutions
            for Each FireUnit As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup_Thread{
                FireUnit.BasePersUnit.SolID=Validsol.ID;
                'FiringUnit.FiringPersUnit.SolID = Validsol.ID
            }
            PassFP=CombatCalc.CalcCombatFPandDRM(FireGroup_Thread,ThreadTargGroup,Scendet,Validsol.ID)
            if(PassFP=-99){ // LOSBlocked by LOSH or other reason no longer valid (spray fire test failure)
                Validsol.Solworks=False;
                PassFP=0;Passdrm=0;
            }else
                Passdrm=-99
                // will display highest drm possible (can be different due to location, etc)
                For Each TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti In ThreadTargGroup{
                    if(TargetUnit.TargetPersUnit.Attackedbydrm>Passdrm){
                        Passdrm=TargetUnit.TargetPersUnit.Attackedbydrm;
                    }
                }
                if(Passdrm=-99){
                    Validsol.Solworks=False'blocked by LOSH
                }
            }
        }
        for Each EnemyFPdrmNewValues As DataClassLibrary.ASLXNA.EnemyHexLOSHFPdrm In EnemyHexList{
            if(EnemyFPdrmNewValues.SolID=Validsol.ID){
                EnemyFPdrmNewValues.drm=Passdrm;
                EnemyFPdrmNewValues.FP=PassFP;
                if(Validsol.Solworks=False){
                    EnemyFPdrmNewValues.LOSStatus=ConstantClassLibrary.ASLXNA.LosStatus.None;
                    Exit For;
                }
            }
        }
        return EnemyHexList;
    }

    private void  CreateTempTargGroup(ByVal Tempsol As LOSClassLibrary.ASLXNA.TempSolution){
        // called by EnemyValuesConcreteC.SetLOSFPdrmValues
        // sets up the TempTargGroup based on TargHex in Tempsol
        boolean Result;
        String AddingThing;
        // get units in the Tempsol Targ hex- stored as Targethex due to DFF loop
        int Targloc=CInt(Tempsol.SeenLOSIndex);
        Dim UnitList As List(Of ObjectClassLibrary.ASLXNA.PersUniti)=(From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.LOCIndex=Targloc).ToList;
        'dim Conceallist As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Concol Where selunit.BasePersUnit.LOCIndex = Targloc).ToList
        for Each Targfound As ObjectClassLibrary.ASLXNA.PersUniti In UnitList{
            Result=AddtoTempTargGroup(Targfound,Tempsol.ID);
            AddingThing=Trim(Targfound.BasePersUnit.UnitName);
            if(Result=False){System.Windows.Forms.MessageBox.Show("Failure to Add Target Unit: "&Trim(AddingThing),"IFt pattern: Create TempTargGroup");}
        }
    }
    End Sub

    // overloaded
    ''Friend Function AddtoTempTargGroup(ByVal Conitem As ObjectClassLibrary.ASLXNA.PersUniti, ByVal TempSolutionID As Integer) As Boolean
        '' 'called by IFT.
                '' 'creates a new instance of TempTargUnit and adds it TempTargetGroup
                '' 'if LOS exists, TempTargUnits are later added to TargetGroup
                '' Try
        '' TemptargetGroup.Add(Conitem)'New ObjectClassLibrary.ASLXNA.PersUniti(Conitem, TempSolutionID))
            '' AddtoTempTargGroup =True
        '' Catch
        '' AddtoTempTargGroup =False
        ''
        ''MsgBox("Error adding unit to TempTargetGroup",,"IFTMVC.AddtoTempTargGroup")
        ''End Try
    ''End Function

    public boolean AddtoTempTargGroup(ByVal Unititem As ObjectClassLibrary.ASLXNA.PersUniti, ByVal TempSolutionID As Integer){
        // called by
        // creates a new instance of TempTargUnit and adds it to TempTargetGroup
        // if LOS exists, TempTargUnits are later added to TargetGroup
        try{
            Unititem.BasePersUnit.SolID=TempSolutionID;
            Dim ObjCreate As ObjectFactoryClassLibrary.aslxna.PersCreation=New ObjectFactoryClassLibrary.aslxna.PersCreation;
            Dim Linqdata=DataClassLibrary.ASLXNA.DataC.GetInstance();   // use null values when sure instance already exists
            Dim Scendet As DataClassLibrary.scen=Linqdata.GetScenarioData(Unititem.BasePersUnit.Scenario); // retrieves scenario data
            integer Firersan=0;
        if(EnemySide1=CInt(Scendet.ATT1)Or EnemySide1=CInt(Scendet.ATT2)){}
            FirerSan=CInt(Scendet.ATTSAN);
        }
        else{
            firersan=CInt(Scendet.DFNSAN);
            Unititem=ObjCreate.CreateTargetUnitandProperty(Unititem,Firersan);
            TemptargetGroup.Add(Unititem);'New ObjectClassLibrary.ASLXNA.PersUniti(Unititem, TempSolutionID))
            AddtoTempTargGroup=True;
        }
        catch{
            AddtoTempTargGroup=False;
            MsgBox("Error adding unit to TempTargetGroup",,"IFTMVC.AddtoTempTargGroup");
        }

        }

    private boolean CheckHexFGValid(ByVal firegrouptouse As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) {
        // called by EnemyValuesConcreteC.SetLOSFPdrmValues
        // used check that there is more in a hex than a leader
        for Each FiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In firegrouptouse {
            if (FiringUnit.BasePersUnit.Unittype = ConstantClassLibrary.ASLXNA.Utype.SMC Or(FiringUnit.BasePersUnit.Unittype =ConstantClassLibrary.ASLXNA.Utype.LdrHero And
            FiringUnit.FiringPersUnit.UseHeroOrLeader=ConstantClassLibrary.ASLXNA.Utype.SMC){
            // do nothing
            }
            else {
                Return True;
            }
        }
        // if here then only leader present and no valid FG exists in the hex
        Return False;
    }

    private boolean SetEnemy(ByVal FirstFriendly As Integer, ByVal Scennum As Integer) {
        // called by SetLOSHFPdrmValues
        // set the nationality values of the "enemy" side
        Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance() ;   // use null values when sure instance already exists
        Dim Scendet As DataClassLibrary.scen =Linqdata.GetScenarioData(Scennum); // retrieves scenario data
        if (FirstFriendly = Scendet.ATT1 Or FirstFriendly = Scendet.ATT2){
            EnemySide1=CInt(Scendet.DFN1);EnemySide2=CInt(Scendet.DFN2);
        }
        else if (FirstFriendly = Scendet.DFN1 Or FirstFriendly = Scendet.DFN2){
            EnemySide1=CInt(Scendet.ATT1);EnemySide2=CInt(Scendet.ATT){
        }
        else{
            Return False; // no nationality values set
        }
        Return True;
    }

    public boolean AddtoFireGroupandTargetGroupMVC(int SolUsedID) {
        // called by EnemyValuesConcreteC.SetLOSFPdrm
        // adds units from valid Temp Solution to FireGroup and TargetGroup
    private static final int ADDINF  = 1;
    private static final int ADDCON = 2;
    private static final int AddMG = 3 ;  // variables used to determine which type
    Dim unititem As DataClassLibrary.OrderofBattle;
    Dim Conitem  As DataClassLibrary.Concealment;
    Dim SWitem As DataClassLibrary.OrderofBattleSW;
    Dim ThreadLinq As DataClassLibrary.ASLXNA.DataC =DataClassLibrary.ASLXNA.DataC.GetInstance(); // use null values if sure instance already exists
    AddtoFireGroupandTargetGroupMVC =False;
    int Targlink = 0;
    boolean TargAdded = False;
    int FirerLink = 0;
    boolean FirerAdded = False;
    Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC =MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("",0) ;  // use null values when sure that instance already exists
    Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) =Maptables.LocationCol;
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol);
    // add Firer(s) to FireGroup
    for Each TempFiringUnit As ObjectClassLibrary.ASLXNA.PersUniti In FiringList{
        FirerAdded=False;
        'If TempFiringUnit.TempSolID = SolUsedID Then
        FirerLink=TempFiringUnit.BasePersUnit.Unit_ID;
        if(ConstantClassLibrary.ASLXNA.Typetype.Personnel=TempFiringUnit.BasePersUnit.TypeType_ID){  // unit
        // check if already added to Fire Group - THIS MAY NOT BE NECESSARY AS NO POSSIBILITY OF DUPLICATE FIRE UNITS
        for Each AlreadyAdded As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup_Thread{
        if(AlreadyAdded.BasePersUnit.Unit_ID=FirerLink&&ConstantClassLibrary.ASLXNA.Typetype.Personnel=AlreadyAdded.BasePersUnit.TypeType_ID){ // And AlreadyAdded.FiringPersUnit.SolID = SolUsedID Then
        // alreadyadded so do nothing
        FirerAdded=True;
        Exit For;
        }
        }
        if(!FirerAdded){
        'unititem = ThreadLinq.GetUnitfromCol(TempFiringUnit.BasePersUnit.Unit_ID)
        'TempFiringUnit.BasePersUnit.SolID = SolUsedID
        TempFiringUnit.FiringPersUnit.CombatStatus=ConstantClassLibrary.ASLXNA.CombatStatus.Firing;
        SetMGInherent(TempFiringUnit);
        FireGroup_Thread.Add(TempFiringUnit);'New ObjectClassLibrary.ASLXNA.PersUniti(unititem, AddInf, SolUsedID, 0))
        }
        }
        else{'MG
            // check if already added to Fire Group
            '' For Each AlreadyAdded As ObjectClassLibrary.ASLXNA.PersUniti In FireGroup_Thread
            '' If AlreadyAdded.BasePersUnit.Unit_ID=FirerLink And ThreadLinq.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.SW,AlreadyAdded.BasePersUnit.Type_ID)
            '' And AlreadyAdded.FiringPersUnit.SolID=SolUsedID Then
            '' 'alreadyadded so do nothing
            '' FirerAdded=True:Exit For
            '' End If
            '' Next
            '' If Not FirerAdded Then
            '' SWitem=ThreadLinq.GetOBSWRecord(TempFiringUnit.OBLink)
            '' Dim SWHexname As String=GetLocs.GetnamefromdatatableMap(CInt(SWitem.Hexnumber))
            '' Dim SWOwner As Integer=CInt(ThreadLinq.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.Owner,SWitem.OBSW_ID))
            '' Dim SWUnit As DataClassLibrary.OrderofBattle=ThreadLinq.GetUnitfromCol(SWOwner)
            '' Dim SWLevelinHex As Single=CSng(SWUnit.LevelinHex)
            '' FireGroup_Thread.Add(New ObjectClassLibrary.ASLXNA.PersUniti(SWitem,AddMG,SWHexname,SWLevelinHex,SolUsedID))
            '' End If
        }
        AddtoFireGroupandTargetGroupMVC=True
        'End If
    }
    // add target(s)to Target Group
    if (TemptargetGroup.Count >0){
        for Each TempTargUnit As ObjectClassLibrary.ASLXNA.PersUniti In TemptargetGroup{
        TargAdded=False;
        if(TempTargUnit.BasePersUnit.SolID=SolUsedID){
        Targlink=TempTargUnit.BasePersUnit.Unit_ID;
        if(ConstantClassLibrary.ASLXNA.Typetype.Personnel=TempTargUnit.BasePersUnit.TypeType_ID){  // unit
        // check if already added to Target Group
        for Each AlreadyAdded As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup_Thread{
        if(AlreadyAdded.BasePersUnit.Unit_ID=Targlink And(ConstantClassLibrary.ASLXNA.Typetype.Personnel=AlreadyAdded.BasePersUnit.TypeType_ID)){
        // alreadyadded so do nothing
        TargAdded=True;
        Exit For;
        }
        }
        if(Not TargAdded){
        unititem=ThreadLinq.GetUnitfromCol(TempTargUnit.BasePersUnit.Unit_ID);
        TargGroup_Thread.Add(TempTargUnit);'New ObjectClassLibrary.ASLXNA.PersUniti(unititem, SolUsedID))
        }
        else{  // Concealment
        // check if already added to Target Group
        for Each AlreadyAdded As ObjectClassLibrary.ASLXNA.PersUniti In TargGroup_Thread{
        if(AlreadyAdded.BasePersUnit.Unit_ID=Targlink And(ConstantClassLibrary.ASLXNA.Typetype.Concealment=AlreadyAdded.BasePersUnit.TypeType_ID)){
        // alreadyadded so do nothing
        TargAdded=True;
        Exit For;
        }
        }
        if(!TargAdded){
        Conitem=ThreadLinq.GetConcealmentfromCol(TempTargUnit.BasePersUnit.Unit_ID);
        TargGroup_Thread.Add(TempTargUnit);'New ObjectClassLibrary.ASLXNA.PersUniti(Conitem, SolUsedID))
        }
        }
        AddtoFireGroupandTargetGroupMVC=True
        }
        }
        }

        public void ClearTempCombat(ByVal TempAltHexLosGroup As List(Of ObjectClassLibrary.ASLXNA.AltHexGTerrain)){
            TemptargetGroup.Clear();
            'TempFireGroup.Clear()
            TempSolList.Clear();
            TempTerrCol.Clear();
            TempAltHexLosGroup.Clear();
        }

    private void SetMGInherent(ByVal firingunit As ObjectClassLibrary.ASLXNA.PersUniti)
        Dim SWCreate As ObjectFactoryClassLibrary.aslxna.SWCreation=New ObjectFactoryClassLibrary.aslxna.SWCreation;
        if(firingunit.BasePersUnit.FirstSWLink>0){
            Dim TestForMG=(From MaybeMG As ObjectClassLibrary.ASLXNA.SuppWeapi In scencolls.SWCol Where MaybeMG.BaseSW.Unit_ID=firingunit.BasePersUnit.FirstSWLink).First;
            TestForMG.FiringSW=SWCreate.createfiringswproperty(TestForMG);
            firingunit.FiringPersUnit.FiringMGs.Add(TestForMG);
            if(TestForMG.FiringSW.IsMG){
                firingunit.FiringPersUnit.UsingfirstMG=True;
            }
        }
        if(firingunit.BasePersUnit.SecondSWlink>0){
            Dim TestForMG=(From MaybeMG As ObjectClassLibrary.ASLXNA.SuppWeapi In scencolls.SWCol Where MaybeMG.BaseSW.Unit_ID=firingunit.BasePersUnit.SecondSWlink).First;
            TestForMG.FiringSW=SWCreate.createfiringswproperty(TestForMG);
            firingunit.FiringPersUnit.FiringMGs.Add(TestForMG);
            if(TestForMG.FiringSW.IsMG){
                firingunit.FiringPersUnit.UsingsecondMG=True;
            }
        }
        if (firingunit.FiringPersUnit.UsingfirstMG=True AndAlso firingunit.FiringPersUnit.UsingsecondMG=True) {
            firingunit.FiringPersUnit.UsingInHerentFP=False;
        }else{
            firingunit.FiringPersUnit.UsingInHerentFP=True;
        }
        if(firingunit.FiringPersUnit.UsingfirstMG=True Or firingunit.FiringPersUnit.UsingsecondMG=True)AndAlso if(firingunit.BasePersUnit.Unittype=ConstantClassLibrary.ASLXNA.Utype.Crew Or
            firingunit.BasePersUnit.Unittype=ConstantClassLibrary.ASLXNA.Utype.HalfSquad){
            firingunit.FiringPersUnit.UsingInHerentFP=False;
        }else{
            firingunit.FiringPersUnit.UsingInHerentFP=True;
        }

    }

    public void EnemyValuesConcreteC() {

    }

}
*/


class storeShadeHex{
// this class holds information about hexes to be shaded as part of various processes: LOS and Search
private String pHexname;
private int pHexnum;
private Constantvalues.LosStatus pLOSStatus;
private int pSearchStatus;
private String pLevelClear;
private Color pShowColor;
private Point pTextPos;
private BufferedImage pShaderTex;   // pShaderTex As Microsoft.Xna.Framework.Graphics.Texture2D
private Rectangle pRect;
 // various constructor overloads depending on process
    public storeShadeHex(String Passhexname, int Passhexnum, Constantvalues.LosStatus PassLOSResult){
        pHexname=Passhexname;
        pHexnum=Passhexnum;
        pLOSStatus=PassLOSResult;
        // pShaderTex = GetShader(pLOSStatus)
        // pRect = GetRectangle(pHexnum)
    }

    // properties
    public String getHexname(){return pHexname;}
    public void setHexname(String value) {pHexname=value;}

    public int getHexnum(){return pHexnum;}
    public void setHexnum(int value) {pHexnum=value;}

    public Constantvalues.LosStatus getLOSStatus(){return pLOSStatus;}
    public void setLOSStatus(Constantvalues.LosStatus value) {pLOSStatus=value;}

    public int getSearchStatus(){return pSearchStatus;}
    public void setSearchStatus(int value) {pSearchStatus=value;}

    public String getLevelClear(){return pLevelClear;}  // holds value of string to display on map in DFF holds value of potential FP and drm
    public void setLevelClear(String value) {pLevelClear=value;}

    public Color getShowColor(){return pShowColor;}
    public void setShowColor(Color value) {pShowColor=value;}

    public Rectangle getScreenRect(){return pRect;}
    public void setScreenRect(Rectangle value) {pRect=value;}

    public Point getTextPos(){return pTextPos;}
    public void setTextPos(Point value) {pTextPos=value;}

    public BufferedImage getShaderTex(){return pShaderTex;}
    public void setShaderTex(BufferedImage value) {pShaderTex=value;}

    // methods
    public Point GetTextPosCenterTop(int PassHexnum){
        Point GetTextPosCenterTop = new Point(0,0);

        // NEED TO FIX

        //Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc=MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0,0,0,0,0,0,0,0,0,0,0);
        //GetTextPosCenterTop.x = CInt(MapGeo.GetCX(PassHexnum))-5;
        //GetTextPosCenterTop.y=CInt(MapGeo.GetCY(PassHexnum))-34;
        return GetTextPosCenterTop;
    }

    public void SetFPdrmstring(String FPdrm, int Enemyhexnum){
        // called by DFFViewConcreteC.UpdateView
        // sets up display of FP and drm string
        pLevelClear=FPdrm;
        pShowColor= Color.BLUE;
        pTextPos=GetTextPosCenterTop(Enemyhexnum);
    }
}
/*class EnemyHexLOSHFPdrm {

    // This class imported from Data.vb in Dataclasslibrary in VB Solution
    // move back to data level if create such in java solution


    // held in list EnemyHexList and returned by EnemyValuesConcreteC.SetupLOSHFPdrmValues
    public Constantvalues.LosStatus LOSStatus;
    public double FP;
    public int drm;
    public String EHexname; //Hexname
    public int Hexnum;
    public double ELOCIndex;  //LOCIndex
    private int Emysolid;   //mysolid


    public EnemyHexLOSHFPdrm(Constantvalues.LosStatus PassLOSStatus, double PassFP, int Passdrm,
                             String PassHexname, int PassHexnum, double PassLOCIndex) {
        LOSStatus = PassLOSStatus;
        FP = PassFP;
        drm = Passdrm;
        EHexname = PassHexname;
        Hexnum = PassHexnum;
        ELOCIndex = PassLOCIndex;
        Emysolid = 0;  //set to 0 on instance creation, changed later
}
        public int getSolID() {
            return Emysolid;
        }
        public void setSolID(int value) {
            Emysolid =value;
        }

    //
}*/


