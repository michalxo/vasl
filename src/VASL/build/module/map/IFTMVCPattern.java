/*
package VASL.build.module.map;

*/
/**
 * Created by dougr_000 on 5/12/2017.
 * Uses interfaces and concrete classes to implement MVC pattern for determining IFT
 * combat opportunities based on either Target or Firer
 *//*


import VASL.build.module.ASLChatter;
import VASL.build.module.ASLDiceBot;
import VASL.build.module.ASLMap;
import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.GameModule;
import VASSAL.build.module.GameComponent;
import VASSAL.build.module.Map;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.build.module.map.Drawable;
import VASSAL.command.Command;
import VASSAL.configure.HotKeyConfigurer;
import VASSAL.tools.imageop.Op;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import VASL.build.module.ASLChatter.ChatterListener;
import static VASSAL.build.module.Chatter.getAnonymousUserName;
import VASSAL.build.module.GlobalOptions;
import VASSAL.configure.ColorConfigurer;
import VASSAL.configure.FontConfigurer;
import VASSAL.configure.LongConfigurer;
import VASSAL.configure.StringConfigurer;
import VASSAL.preferences.Prefs;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
public class IFTMVCPattern {
    // this class creates and uses the IFT MVC pattern which updates and displays the
    // LOSH, FP and drm of all enemy units on the mapwindow for a fire-capable (prep, def, advf phases) unit.
    private IFTFireInterface IFTFire;

    // constructor
    public IFTMVCPattern() {  //(ByVal TempFireGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti))
        // creates the model, then the controller, which creates the view
        // then trigger the model by calling SetLOSHFPdrm on the controller
        IFTFire = new IFTFireConcreteC();
        IFTControllerInterface IFTCont = new IFTControllerConcreteC(IFTFire);
        // Tells the controller to recalcuate EnemyValues
        IFTCont.SetLOSHFPdrm(TempFireGroup);
    }

    // these are functions visible to ASLXNA that pass the pattern results back to the main program
    public int GetShaderToShow() {
        return IFTFire.ShadertoShow;
    }

    public Function StoreHexestoShade() As List (of Storeshadehex)  {
        return IFTFire.StoreHexestoShade;
    }

    public Function StoreStringstoDraw() As List (Of StoreShadeHex) {
        return IFTFire.storeStringstoDraw;
    }
}

// MODEL
// interface
public interface IFTFireInterface {

        public int ShadertoShow;
        Property StoreHexesToShade As List(Of Storeshadehex)
        Property storeStringstoDraw As List(Of StoreShadehex)

        public void Initialize() {  };

        public void SetLOSFPdrmValues(ByVal TempFireGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti))

        public GetLOSFPdrmValues() As List(Of EnemyHexLOSHFPdrm)

        public void RegisterObserver(IFTObserverInterface IFTObserverC);

        public void RemoveObserver();
}

// concrete class
public class IFTFireConcreteC implements IFTFireInterface {

        private IFTObserverInterface IFTObserver;
        private SetEnemyValues As List(Of EnemyHexLOSHFPdrm)
        public int ShadertoShow;
        Property storeHexesToShade As New List(Of storeShadeHex) Implements IFTFireInterface.StoreHexesToShade
        Property storeStringstoDraw As New List(Of storeShadeHex) Implements IFTFireInterface.storeStringstoDraw

        public GetLOSFPdrmValues() {  // As List(Of EnemyHexLOSHFPdrm) Implements IFTFireInterface.GetLOSFPdrmValues
            // called by DFFViewConcreteC.UpdateView
            // retrives new EnemyValues to be displayed by the view
            // does not triggers recalculation of EnemyValues - already done
            return SetEnemyValues;
        }

        public void Initialize() {
            // no implementation required so far
        }

        public void RegisterObserver(IFTObserverInterface IFTObserverC) {   // Implements IFTFireInterface.RegisterObserver
            // called by DFFViewConcreteC.SetUpView
            // the Observer is the view as a whole NOT each EnemyUnit - as per the example
            IFTObserver = IFTObserverC;
        }

        public void RemoveObserver() {  // Implements IFTFireInterface.RemoveObserver
            // no implementation required so far
        }

        public void SetLOSFPdrmValues(ByVal TempFireGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) {
            // called by IFTControllerConcreteC.SetLOSHFPdrmValues
            // triggers calculation of new set of EnemyUnit values; not an update of existing
            // classes out the calculation process
            DFFEnemyValuesConcreteC EnemyValues = new DFFEnemyValuesConcreteC;
            // set shader used in Game.draw THIS NEEDS TO BE MOVED TO ASLXNA
            ShadertoShow = ConstantClassLibrary.ASLXNA.ShadeShow.IFTShade
            // returns list of new EnemyValues
            SetEnemyValues = EnemyValues.SetLOSFPdrmValues(TempFireGroup)
            // tells the observers(the view) that new EnemyValues are available
            NotifyObservers();
        }

        private void NotifyObservers() {
            // called by SetLOSFPdrmValues
            // calls UpdateView on all observers
            IFTObserver.UpdateView();
        }
}

// CONTROLLER
// interface
public interface IFTControllerInterface {
    public void SetLOSHFPdrm(ByVal TempFireGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti))
}

// concrete class
public class IFTControllerConcreteC implements IFTControllerInterface {
    private IFTFireInterface IFTModel;

    public void IFTControllerConcreteC(IFTFireInterface Newmodel) {
        IFTModel = Newmodel;
        // creates the view
        IFTViewConcreteC IFTView = new IFTViewConcreteC;
        IFTView.SetupView(IFTModel, this);
    }

    public void SetLOSHFPdrm(ByVal TempFireGroup As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) {
    Implements IFTControllerInterface.SetLOSHFPdrm
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
public class IFTViewConcreteC implements IFTObserverInterface {
    private IFTFireInterface ViewModel;
    private IFTControllerInterface ViewController;

    public void SetupView(IFTFireInterface NewModel, IFTControllerInterface NewController) {
        // called by IFTControllerConcreteC.New
        // passes in references to the model and controller and registers as an observer
        ViewModel = NewModel;
        ViewController = NewController;
        // add observers
        ViewModel.RegisterObserver(this);
    }

    public void UpdateView() { // Implements IFTObserverInterface.UpdateView
        // called by IFTFireConcreteC.NotifyObservers
        // tells observers that new set of EnemyValues is availalble, gets them and processes changes to the display
        boolean AlreadyAdded = False;
        // get the new EnemyValues
        Dim EnemyHexValues As List (Of EnemyHexLOSHFPdrm) =ViewModel.GetLOSFPdrmValues()
        String FPdrmstring;
        String drmSign;
        // create list of ShadeHexes which are drawn by Game.Draw
        StoreShadeHex EnemyShade;

        For Each EnemyUnit As EnemyHexLOSHFPdrm In EnemyHexValues
            AlreadyAdded = False;
            if (EnemyUnit.drm > 0) {
                drmSign = "+";
            } else if (EnemyUnit.drm = 0) {
                drmSign = "-";
            } else
                drmSign = "";
            }

            if(EnemyUnit.LOSStatus ==ConstantClassLibrary.ASLXNA.LosStatus.None) {
                FPdrmstring = "No LOS";
            } else if(EnemyUnit.LOSStatus ==ConstantClassLibrary.ASLXNA.LosStatus.BeyondLR) {
                FPdrmstring = "BR";
            } else if(EnemyUnit.LOSStatus ==ConstantClassLibrary.ASLXNA.LosStatus.Target) {
                FPdrmstring = "";
            } else
                FPdrmstring = Trim(EnemyUnit.FP.ToString) & Trim(drmSign) & Trim(EnemyUnit.drm.ToString)
            }
            // check for multiple entries in same hex
            if (ViewModel.storeStringstoDraw.Count > 0){
                For Each ExistingShade As storeShadeHex In ViewModel.storeStringstoDraw
                    if(ExistingShade.Hexnum==EnemyUnit.Hexnum){
                        if(Trim(FPdrmstring)="No LOS"And Trim(ExistingShade.LevelClear)="No LOS")Or(Trim(FPdrmstring)="BR"And Trim(ExistingShade.LevelClear)="BR"){
                        }else
                            FPdrmstring=FPdrmstring&", "&ExistingShade.LevelClear
                            ExistingShade.SetFPdrmstring(FPdrmstring,CInt(ExistingShade.Hexnum))
                        }
                        AlreadyAdded=True;
                    }
                }
            }

            if (!AlreadyAdded){
                EnemyShade=new storeShadeHex(EnemyUnit.Hexname,CInt(EnemyUnit.Hexnum),EnemyUnit.LOSStatus);
                EnemyShade.SetFPdrmstring(FPdrmstring,CInt(EnemyUnit.Hexnum));
                // Game.Draw will iterate through HexesToShade and StringsToDraw if shader is set (done in DefensiveFirstFireConcreteC.SetLOSHFPdrmValues)
                if(EnemyUnit.LOSStatus<> ConstantClassLibrary.ASLXNA.LosStatus.NormalRange){
                    ViewModel.StoreHexesToShade.Add(EnemyShade)
                }
                ViewModel.storeStringstoDraw.Add(EnemyShade)
            }
        }
        // sets shader again - could drop one
        ViewModel.ShadertoShow = ConstantClassLibrary.ASLXNA.ShadeShow.IFTShade

    }
}

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
            if (FiringUnit.BasePersUnit.Unittype = ConstantClassLibrary.ASLXNA.Utype.Leader Or(FiringUnit.BasePersUnit.Unittype =ConstantClassLibrary.ASLXNA.Utype.LdrHero And
            FiringUnit.FiringPersUnit.UseHeroOrLeader=ConstantClassLibrary.ASLXNA.Utype.Leader){
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

 ' ''Friend Class EnemyHexLOSHFPdrm
        ' ''    '    'type ussed by EnemyValuesConcreteC to store values to pass to Shadehexes in IFTviewConcreteC.UpdateView
        ' ''    '    'held in list EnemyHexList and treturned by EnemyValuesConcreteC.SetupLOSHFPdrmValues
        ' ''    Friend LOSStatus As Integer
        ' ''    Friend FP As Single
        ' ''    Friend drm As Integer
        ' ''    Friend Hexname As String
        ' ''    Friend Hexnum As Integer
        ' ''    Sub New(ByVal PassLOSStatus As Integer, ByVal PassFP As Single, ByVal Passdrm As Integer,
        ' ''            ByVal PassHexname As String, ByVal PassHexnum As Integer)
        ' ''        LOSStatus = PassLOSStatus
        ' ''        FP = PassFP
        ' ''        drm = Passdrm
        ' ''        Hexname = PassHexname
        ' ''        Hexnum = PassHexnum
        ' ''    End Sub
        ' ''End Class

public Class storeShadeHex{
// this class holds information about hexes to be shaded as part of various processes: LOS and Search
private String pHexname;
private int pHexnum;
private int pLOSStatus;
private int pSearchStatus;
private String pLevelClear;
private pShowColor As Microsoft.Xna.Framework.Color;
private pTextPos As Microsoft.Xna.Framework.Vector2;
private pShaderTex As Microsoft.Xna.Framework.Graphics.Texture2D;
private pRect As Microsoft.Xna.Framework.Rectangle;
// various constructor overloads depending on process
    public void storeShadeHex(ByVal Passhexname As String,ByVal Passhexnum As Integer,ByVal PassLOSResult As Integer){
        pHexname=Passhexname
        pHexnum=Passhexnum
        pLOSStatus=PassLOSResult
        'pShaderTex = GetShader(pLOSStatus)
        'pRect = GetRectangle(pHexnum)
    }
        'Sub New(ByVal Passhexname As String, ByVal Passhexnum As Integer, ByVal PassLOSResult As Integer, ByVal LOSResultbyLevel As List(Of LOSLevel))
        '    pHexname = Passhexname
        '    pHexnum = Passhexnum
        '    pLOSStatus = PassLOSResult
        '    pShaderTex = GetShader(pLOSStatus)
        '    pRect = GetRectangle(pHexnum)
        '    SetLevelClear(LOSResultbyLevel, Passhexnum)
        'End Sub

        'Friend Sub New(ByVal Passhexnum As Integer)
        '    'this overload comes from MapShade.SearchShadeShow and Movementc.addtostackattemp
        '    pHexnum = Passhexnum
        '    pHexname = ""
        '    pRect = GetRectangle(pHexnum)
        'End Sub
        'Friend Sub New(ByVal Passhexnum As Integer, ByVal ShadeTex As Microsoft.Xna.Framework.Graphics.Texture2D, ByVal Otherhexnum As Integer)
        '    'this overload comes from Enternewhex.moveupdate
        '    ' create movetrail shadehex
        '    pHexnum = Passhexnum
        '    pHexname = ""
        '    pShaderTex = ShadeTex
        '    pRect = GetRectangle(pHexnum, Otherhexnum)
        'End Sub
        'Friend Sub New(ByVal Passhexnum As Integer, ByVal ShadeTex As Microsoft.Xna.Framework.Graphics.Texture2D, ByVal Otherhexnum As Integer, ByVal PasssideCrossed As Integer)
        '    'this overload comes from drawmoveinfo.new
        '    ' create movetrail shadehex
        '    pHexnum = Passhexnum
        '    pHexname = ""
        '    pShaderTex = ShadeTex
        '    pRect = GetRectangle(pHexnum, Otherhexnum, PasssideCrossed)
        'End Sub
    public ReadOnly Property Hexname As String{
        Get
        Return pHexname
        End Get
    }
    public ReadOnly Property Hexnum As Integer{
        Get
        Return pHexnum
        End Get
    }
    public ReadOnly Property LOSStatus As Integer{
        Get
        Return pLOSStatus
        End Get
    }
    public ReadOnly Property SearchStatus As Integer{
        Get
        Return pSearchStatus
        End Get
    }
    public ReadOnly Property LevelClear As String{
        'holds value of string to display on map
        'in DFF holds value of potential FP and drm
        Get
        Return pLevelClear
        End Get
    }
    public ReadOnly Property ShowColor As Microsoft.Xna.Framework.Color{
        Get
        Return pShowColor
        End Get
    }
    public ReadOnly Property ScreenRect As Microsoft.Xna.Framework.Rectangle{
        Get
        Return pRect
        End Get
    }
    public ReadOnly Property TextPos As Microsoft.Xna.Framework.Vector2{
        Get
        Return pTextPos
        End Get
    }
    public ReadOnly Property ShaderTex As Microsoft.Xna.Framework.Graphics.Texture2D{
        Get
        Return pShaderTex
        End Get
    }
    // methods
        'Friend Function GetShader(ByVal PassLOSStatus As Integer) As Microsoft.Xna.Framework.Graphics.Texture2D
        '    Select Case PassLOSStatus
        '        Case EnumLosStatus.None
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderNoLOS1")
        '            Return Shadetex
        '        Case EnumLosStatus.LongRange
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderLongRange")
        '            Return Shadetex
        '        Case EnumLosStatus.BeyondLR
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderLongRange4")  ' Beyo
        '            Return Shadetex
        '        Case EnumLosStatus.PointBlank
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderPBF")  ' Beyo
        '            Return Shadetex
        '        Case EnumLosStatus.Target
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("HexOutline")  ' shader for selected hex
        '            Return Shadetex
        '        Case EnumLosStatus.NormalRange

        '        Case Else
        '            Return Nothing
        '    End Select
        'End Function
        'Friend Function SetSearchShader(ByVal PassSearchStatus As Integer) As Boolean
        '    Select Case PassSearchStatus
        '        Case EnumShadeShow.CanNotSearch
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderNoSearch")
        '            pShaderTex = Shadetex
        '            Return True
        '        Case EnumShadeShow.CanSearch
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderToSearch")
        '            pShaderTex = Shadetex
        '            Return True
        '        Case EnumShadeShow.Searched
        '            Dim Shadetex As Microsoft.Xna.Framework.Graphics.Texture2D = Game.Content.Load(Of Texture2D)("ShaderSearched")
        '            pShaderTex = Shadetex
        '            Return True
        '        Case Else
        '            Return False
        '    End Select
        'End Function
        'Friend Function GetRectangle(ByVal PassHexnum As Integer) As Microsoft.Xna.Framework.Rectangle

        '    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        '    Dim NewRect As New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(PassHexnum) - 33), CInt(MapGeo.GetCY(PassHexnum) - 37), 65, 75)
        '    Return NewRect
        'End Function
        'Friend Function GetRectangle(ByVal PassHexnum As Integer, ByVal Passotherhexnum As Integer) As Microsoft.Xna.Framework.Rectangle

        '    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        '    Dim NewRect As New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum)), CInt(MapGeo.GetCY(Passotherhexnum) - 5), 65, 10)
        '    Return NewRect
        'End Function
        'Friend Function GetRectangle(ByVal PassHexnum As Integer, ByVal Passotherhexnum As Integer, ByVal Passsidecrossed As Integer) As Microsoft.Xna.Framework.Rectangle

        '    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        '    Dim NewRect As Microsoft.Xna.Framework.Rectangle
        '    Select Case Passsidecrossed
        '        Case 1
        '            NewRect = New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum)), CInt(MapGeo.GetCY(Passotherhexnum) - 3), 65, 5)
        '        Case 2
        '            NewRect = New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum)), CInt(MapGeo.GetCY(Passotherhexnum)), 32, 56)
        '        Case 3
        '            NewRect = New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum) - 32), CInt(MapGeo.GetCY(Passotherhexnum)), 32, 56)
        '        Case 4
        '            NewRect = New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum) - 65), CInt(MapGeo.GetCY(Passotherhexnum) - 3), 65, 5)
        '        Case 5
        '            NewRect = New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum) - 32), CInt(MapGeo.GetCY(Passotherhexnum) - 56), 32, 56)
        '        Case 6
        '            NewRect = New Microsoft.Xna.Framework.Rectangle(CInt(MapGeo.GetCX(Passotherhexnum)), CInt(MapGeo.GetCY(Passotherhexnum) - 56), 32, 56)
        '        Case Else
        '            Return Nothing
        '    End Select

        '    ' NewRect=New Microsoft.Xna.Framework.Rectangle(CInt(Mapgeo.MapCoord.GetCX(Passotherhexnum)),CInt(Mapgeo.MapCoord.GetCY(Passotherhexnum)-5),65,10)
        '    Return NewRect
        'End Function
        'Friend Function GetTextPosLeftMiddle(ByVal PassHexnum As Integer) As Microsoft.Xna.Framework.Vector2

        '    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        '    GetTextPosLeftMiddle.X = CInt(MapGeo.GetCX(PassHexnum)) - 29
        '    GetTextPosLeftMiddle.Y = CInt(MapGeo.GetCY(PassHexnum)) - 7
        'End Function
    public Function GetTextPosCenterTop(ByVal PassHexnum As Integer)As Microsoft.Xna.Framework.Vector2{
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc=MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0,0,0,0,0,0,0,0,0,0,0);
        'Dim StringStart As Vector2 = Game.Usefont.MeasureString(Trim(pLevelClear)) / 2
        GetTextPosCenterTop.X=CInt(MapGeo.GetCX(PassHexnum))-5;'StringStart.X
        GetTextPosCenterTop.Y=CInt(MapGeo.GetCY(PassHexnum))-34;
    }
        'Friend Function GetTextPosCenterbottom(ByVal PassHexnum As Integer) As Microsoft.Xna.Framework.Vector2

        '    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        '    Dim StringStart As Vector2 = Game.Usefont.MeasureString(Trim(pLevelClear)) / 2
        '    GetTextPosCenterbottom.X = CInt(MapGeo.GetCX(PassHexnum)) - StringStart.X
        '    GetTextPosCenterbottom.Y = CInt(MapGeo.GetCY(PassHexnum)) + 34
        'End Function
        'Friend Function GetTextPosCenter(ByVal PassHexnum As Integer) As Microsoft.Xna.Framework.Vector2

        '    Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        '    ' Dim StringStart As Vector2=Game.Usefont.MeasureString(Trim(pLevelClear))/2
        '    GetTextPosCenter.X = CInt(MapGeo.GetCX(PassHexnum)) - 10
        '    GetTextPosCenter.Y = CInt(MapGeo.GetCY(PassHexnum)) - 25
        'End Function
        'Private Sub SetLevelClear(ByVal LOSResultbyLevel As List(Of LOSLevel), ByVal Passhexnum As Integer)
        '    ' called by Shadehex.New-when doing mapboard LOSShade

        '    If pLOSStatus < EnumLosStatus.None Then   ' LOS exists to this level
        '        ' check for LOS blocked at lower leves
        '        For Each Losleveltested As LOSLevel In LOSResultbyLevel
        '            If Losleveltested.LOSResult = EnumCon.LosStatus.None Then
        '                ' add level at which LoS blocked to display string
        '                If Trim(pLevelClear) = "" Then
        '                    pLevelClear = Losleveltested.Level.ToString
        '                Else
        '                    pLevelClear = Losleveltested.Level.ToString & ", " & pLevelClear
        '                End If
        '            End If
        '            ' colour blocked LOS as red
        '            pShowColor = Microsoft.Xna.Framework.Color.Red

        '        Next Losleveltested
        '    Else   ' LOS blocked at this level
        '        ' check for LOS clear at lower levels
        '        For Each Losleveltested As LOSLevel In LOSResultbyLevel
        '            If Losleveltested.LOSResult < EnumCon.LosStatus.None Then
        '                ' add level at which LOS clear to display string
        '                If Trim(pLevelClear) = "" Then
        '                    pLevelClear = Losleveltested.Level.ToString
        '                Else
        '                    pLevelClear = Losleveltested.ToString & ", " & pLevelClear
        '                End If
        '            End If
        '            ' color clear LOS as blue
        '            pShowColor = Microsoft.Xna.Framework.Color.Blue
        '        Next Losleveltested
        '    End If
        '    ' set where in hex display string will show
        '    pTextPos = GetTextPosLeftMiddle(Passhexnum)
        'End Sub
    public void SetFPdrmstring(ByVal FPdrm As String,ByVal Enemyhexnum As Integer){
        // called by DFFViewConcreteC.UpdateView
        // sets up display of FP and drm string
        pLevelClear=FPdrm;
        pShowColor=Microsoft.Xna.Framework.Color.Blue;
        pTextPos=GetTextPosCenterTop(Enemyhexnum);
    }
        'Friend Sub SetMovestring(ByVal MF As String, ByVal hexnum As Integer)
        '    ' called by Movementc.addtostackattempt,DrawMoveInfo.new
        '    ' sets up display of FP and drm string
        '    pLevelClear = ""
        '    pLevelClear = MF
        '    pShowColor = Microsoft.Xna.Framework.Color.White
        '    pTextPos = GetTextPosCenterbottom(hexnum)
        'End Sub
        'Friend Sub SetMoveFstring(ByVal MFcost As String, ByVal hexnum As Integer, ByVal AddMF As Boolean)
        '    ' called by DrawMoveInfo.new
        '    ' sets up display of FP and drm string
        '    If AddMF Then
        '        pLevelClear = CStr(CSng(pLevelClear) + CSng(MFcost))
        '    Else
        '        pLevelClear = MFcost
        '    End If
        '    pShowColor = Microsoft.Xna.Framework.Color.White
        '    pTextPos = GetTextPosCenter(hexnum)
        'End Sub
}
        ''keep this class as friend so not available outside the classlibrary - duplicated in movement
        'Friend Class EnemyChecks
        '    'This class meets the SRP - it does one thing: provides information about the enemy
        '    ' "enemy" is defined as not the specified friendly side
        '    ' it includes four separate methods that check for
        '    ' a) enemy units in a hex
        '    ' b) enemy units in a location
        '    ' c) enemy unit compared to friendly side
        '    ' d) return nationality values of enemy
        '    'these methods can be called externally and internally
        '    Private LocationContentsToCheck As DataClassLibrary.ContentsofLocation
        '    Private HexToCheck As Integer = 0
        '    Private LocationtoCheck As Integer = 0
        '    Private FriendlySidevalue As Integer = 0
        '    Private OtherFriendlysidevalue As Integer = 0
        '    Private Locindexvalue As Integer = 0
        '    'constructor
        '    Sub New(ByVal LocIndexclicked As Integer, ByVal FriendlyNat As Integer)
        '        If LocIndexclicked > 0 Then  'every location have an index value so PassLocationIndex must be greater than zero
        '            Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)   'use null values when sure that instance already exists
        '            Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
        '            Dim GetLocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
        '            Dim UsingLoc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(LocIndexclicked)
        '            HexToCheck = UsingLoc.Hexnum
        '            Locindexvalue = LocIndexclicked
        '            LocationtoCheck = UsingLoc.LocIndex
        '        Else
        '            MessageBox.Show("Error: locindex value not found in Map table", "ContentsOfLocation")
        '            Exit Sub
        '        End If
        '        FriendlySidevalue = FriendlyNat
        '        Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
        '        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(Linqdata.ScenarioID) 'retrieves scenario data
        '        If FriendlySidevalue = Scendet.ATT1 Then
        '            OtherFriendlysidevalue = CInt(Scendet.ATT2)
        '        ElseIf FriendlySidevalue = Scendet.ATT2 Then
        '            OtherFriendlysidevalue = CInt(Scendet.ATT1)
        '        ElseIf FriendlySidevalue = Scendet.DFN1 Then
        '            OtherFriendlysidevalue = CInt(Scendet.DFN2)
        '        ElseIf FriendlySidevalue = Scendet.DFN2 Then
        '            OtherFriendlysidevalue = CInt(Scendet.DFN1)
        '        End If
        '    End Sub
        '    'properties
        '    Friend ReadOnly Property FriendlySide As Integer
        '        Get
        '            Return FriendlySidevalue
        '        End Get
        '    End Property
        '    'methods
        '    Friend Function EnemyinLocationTest() As Boolean
        '        'called by MovementValidation.New
        '        'returns true if enemy present, false if not
        '        If IsNothing(LocationContentsToCheck) Then GetLocationContents(LocationtoCheck)
        '        For Each ItemInHex As DataClassLibrary.LocationContent In LocationContentsToCheck.ContentsInLocation
        '            If IsUnitEnemy(ItemInHex.ObjID, ItemInHex.TypeID) Then Return True
        '        Next
        '        Return False 'if gets here no enemy present
        '    End Function
        '    Friend Function IsUnitEnemy(ByVal UnitID As Integer, ByVal TypeID As Integer) As Boolean
        '        Dim Prisoner As Boolean = True : Dim hexunit As DataClassLibrary.OrderofBattle
        '        Dim hexVehicle As DataClassLibrary.AFV : Dim hexConceal As DataClassLibrary.Concealment
        '        Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
        '        Dim typeIs As Integer = Linqdata.GetTypeOfThing(TypeID)
        '        Select Case typeIs
        '            Case DataClassLibrary.ASLXNA.DataC.Typetype.Personnel
        '                'infantry
        '                hexunit = Linqdata.GetUnitfromCol(UnitID)
        '                If Prisoner Then
        '                    If hexunit.OrderStatus <> DataClassLibrary.ASLXNA.DataC.OrderStatus.Prisoner Then Prisoner = False 'once false, stays false - means enemy present
        '                End If
        '                If CInt(hexunit.Nationality) <> FriendlySide And CInt(hexunit.Nationality) <> OtherFriendlysidevalue And Not Prisoner Then
        '                    'enemy present
        '                    Return True
        '                End If
        '            Case DataClassLibrary.ASLXNA.DataC.Typetype.Vehicle
        '                'vehicle
        '                hexVehicle = Linqdata.VehicleCol.Where(Function(FindVeh) FindVeh.AFVID = UnitID).First
        '                'VehTypeClicked = ItemInHex.TypeID
        '                If (CInt(hexVehicle.OwnerNationality) <> FriendlySide And CInt(hexVehicle.OwnerNationality) <> OtherFriendlysidevalue And Not hexVehicle.Captured) Or
        '                   ((CInt(hexVehicle.OwnerNationality) = FriendlySide Or CInt(hexVehicle.OwnerNationality) = OtherFriendlysidevalue) And hexVehicle.Captured) Then
        '                    'enemy present
        '                    Return True
        '                End If
        '                Prisoner = False
        '            Case DataClassLibrary.ASLXNA.DataC.Typetype.Gun
        '                'Guns - still to code
        '            Case DataClassLibrary.ASLXNA.DataC.Typetype.SW, DataClassLibrary.ASLXNA.DataC.Typetype.Location
        '                'SW or Terrain - do nothing
        '            Case DataClassLibrary.ASLXNA.DataC.Typetype.Concealment
        '                'Concealment
        '                hexConceal = Linqdata.GetConcealmentfromCol(UnitID)
        '                Prisoner = False
        '                If CInt(hexConceal.Nationality) <> FriendlySide And CInt(hexConceal.Nationality) <> OtherFriendlysidevalue And Not Prisoner Then
        '                    'enemy present
        '                    Return True
        '                End If
        '            Case DataClassLibrary.ASLXNA.DataC.Typetype.WhiteC
        '                'anything needed?
        '        End Select
        '        Return False  'if get to here then nothing found that is Enemy
        '    End Function
        '    Friend Function EnemyInHexTest() As Boolean
        '        '
        '        Dim Maptables As MapDataClassLibrary.ASLXNA.MapDataC = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)   ' use null values when sure that instance already exists
        '        Dim LocationCol As IQueryable(Of MapDataClassLibrary.GameLocation) = Maptables.LocationCol
        '        Dim Getlocs = New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(LocationCol)
        '        For Each HexLocs As MapDataClassLibrary.GameLocation In Getlocs.RetrieveLocationsfromMapTable(HexToCheck, "Hexnum") ' Game.Scenario.TerrainActions.GetLocationsInHex(HexToCheck)
        '            GetLocationContents(HexLocs.LocIndex)
        '            If EnemyinLocationTest() Then Return True
        '        Next
        '        LocationContentsToCheck = Nothing
        '        Return False
        '    End Function
        '    Private Sub GetLocationContents(ByVal locationtoget As Integer)
        '        LocationContentsToCheck = New DataClassLibrary.ContentsofLocation(locationtoget)
        '        LocationContentsToCheck.GetContents()
        '    End Sub
        '    Friend Function GetEnemy(ByRef FirstEnemy As Integer, ByRef SecondEnemy As Integer) As Boolean
        '        'called by
        '        'returns the nationality values of the "enemy" side
        '        Dim Linqdata = DataClassLibrary.ASLXNA.DataC.getInstance()  'use null values when sure instance exists
        '        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(Linqdata.ScenarioID) 'retrieves scenario data
        '        If FriendlySidevalue = Scendet.ATT1 Or FriendlySidevalue = Scendet.ATT2 Then
        '            FirstEnemy = CInt(Scendet.DFN1) : SecondEnemy = CInt(Scendet.DFN2)
        '        ElseIf FriendlySidevalue = Scendet.DFN1 Or FriendlySidevalue = Scendet.DFN2 Then
        '            FirstEnemy = CInt(Scendet.ATT1) : SecondEnemy = CInt(Scendet.ATT2)
        '        Else
        '            Return False 'no nationality values set
        '        End If
        '        Return True
        '    End Function
        'End Class
        ' ''Enum EnumLosStatus
        ' ''    PointBlank = 8541
        ' ''    NormalRange = 8542
        ' ''    LongRange = 8543
        ' ''    BeyondLR = 8544
        ' ''    None = 8545
        ' ''    Target = 8546
        ' ''End Enum
        ' ''Enum EnumShadeShow
        ' ''    LOSShade = 8311
        ' ''    SearchShade = 8312
        ' ''    PlaceDCShade = 8313
        ' ''    DFFShade = 8314
        ' ''    IFTShade = 8315
        ' ''    CanSearch = 8321
        ' ''    CanNotSearch = 8322
        ' ''    Searched = 8323
        ' ''End Enum
    //    End Namespace

*/
