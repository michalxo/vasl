package VASL.build.module.fullrules.Game;

import VASL.build.module.ASLMap;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.*;
import VASL.build.module.fullrules.IFTCombatClasses.IFTC;
import VASL.build.module.fullrules.IFTCombatClasses.IFTTableResult;
import VASL.build.module.fullrules.IFTCombatClasses.IIFTC;
import VASL.build.module.fullrules.MovementClasses.MakeMoveC;
import VASL.build.module.fullrules.ObjectClasses.Leader;
import VASL.build.module.fullrules.ObjectClasses.Scenlisttype;
import VASL.build.module.fullrules.OpenSaveGame;
import VASL.build.module.fullrules.PhaseClasses.PhaseMVCPattern;
import VASSAL.build.GameModule;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ScenarioC extends CampaignC {
    // Opened whenever game is being played; holds routines needed for individual game play
    // private ReportEvent As New LogReporting
    private int ScenIDValue;
    private String ScenName;
    private Constantvalues.Phase PhaseValue;
    private static ScenarioC Sceninstance;
    private Constantvalues.WhoCanDo PlayerTurnvalue;
    private int CurrentTurnvalue;
    public MakeMoveC DoMove;  // temporary while debugging undo
    private PhaseMVCPattern PhasePattern;
    private List<Scenlisttype> ListofScenarios;
    private VASL.LOS.Map.Map pgamemap;
    private ASLMap pmap;
    private Scenario pScenario;
    private LinkedList<OrderofBattle> pOBUnitcol;
    private LinkedList<OrderofBattleSW> pOBSWcol;
    private OpenSaveGame GameinPlay;
    private HashMap<String, IFTTableResult> pIFTTableLookUp;
    private HashMap<String, LineofBattle> pLOBTableLookUp;
    private HashMap<String, Leader> pLeaderTableLookUp;
    private HashMap<String, SupportWeapon> pSupportWeaponTableLookUp;
    // constructors
    private ScenarioC(String test) {
        // called by ScenarioC.Getinstance as part of singleton pattern
        super();
    }

    public static synchronized ScenarioC getInstance() {
        // instantiates object as singleton class - using singleton design pattern
        if (Sceninstance == null) {
            String test = "test";
            Sceninstance = new ScenarioC(test);
        }
        return Sceninstance;
    }

    public int getScenID() {
        return ScenIDValue;
    }
    public String getScenname() {return ScenName;}
    public VASL.LOS.Map.Map getGameMap() {return pgamemap;}
    public ASLMap getASLMap() {return pmap;}
    public Constantvalues.Phase getPhase() {
        return PhaseValue;
    }
    public Constantvalues.WhoCanDo getPlayerTurn() {
        return PlayerTurnvalue;
    }
    public Scenario getScendet() {return pScenario;}
    public int getCurrentTurn() {
        return CurrentTurnvalue;
    }
    public IIFTC getIFT() {return IFT;}
    public LinkedList<OrderofBattle> getOBUnitcol() {return pOBUnitcol;}
    public LinkedList<OrderofBattleSW> getOBSWcol() {return pOBSWcol;}
    public HashMap<String, IFTTableResult> getIFTTableLookUp(){return pIFTTableLookUp;}
    public void setIFTTableLookUp(HashMap<String, IFTTableResult> value){pIFTTableLookUp = value;}
    public void setLOBTableLookUp(HashMap<String, LineofBattle> value){pLOBTableLookUp = value;}
    public HashMap<String, LineofBattle> getLOBTableLookUp() {return pLOBTableLookUp;}
    public HashMap<String, Leader> getLeaderTableLookUp(){return pLeaderTableLookUp;}
    public void setLeaderTableLookUp(HashMap<String, Leader> value){pLeaderTableLookUp = value;}
    public HashMap<String, SupportWeapon> getSupportWeaponTableLookUp(){return pSupportWeaponTableLookUp;}
    public void setSupportWeaponTableLookUp(HashMap<String, SupportWeapon> value){pSupportWeaponTableLookUp = value;}

    public boolean StartASLScenario(int PassASLScenID) {
        // start new ASL scenario from preset ASLScenario

        /*Dim StartScenario
        As Boolean = True :
        Dim Fulltitle
        As String = ""
        'retrieve scenario data
        Dim ASLScendet
        As DataClassLibrary.ASLScenario = Game.Linqdata.GetASLScenarioData(PassASLScenID)
        Dim ASLScenUnits

        As List (Of DataClassLibrary.ASLScenUnit) =(Game.Linqdata.GetASLScenarioUnits(PassASLScenID)).tolist
        'Dim ASLScenMaps As List(Of MapDataClassLibrary.ASLMap) = (Game.Linqdata.GetASLScenarioMaps(PassASLScenID)).tolist
        Dim ASLScenarioSSRs

        As List (Of DataClassLibrary.ASLscenSSR) =(Game.Linqdata.GetASLscenarioSSR(PassASLScenID)).ToList
        'create new scenario record in scen table
        Dim NewScenNum
        As Integer = Game.Linqdata.GetNewScennum
        ScenIDValue = NewScenNum
        Game.Linqdata.CreateNewDBrecord(NewScenNum, ASLScendet)
        'retrieve new Scen
        Dim NewScen
        As DataClassLibrary.scen = Game.Linqdata.GetScenarioData(NewScenNum)
        'create map and graphics classes, Map table collection
        Dim ASLMapLink
        As String = "Scen" & CStr(NewScenNum)
        'create terrain collection for scenario
        Maptables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance(

                Trim(ASLMapLink), NewScenNum)
        If Maptables.

        CreateNewMapDbTables(PassASLScenID) Then
        'StartScenario = If(Maptables.CreateDataTableMap(ASLMapLink), True, False)
        LocationCol = Maptables.CreateMapCollection
        If LocationCol.Count > 0
        Then
                StartScenario = True
        Else
                StartScenario = False
        MessageBox.Show("Major League Error")
        End If
        'Map setup
        Dim ASLScenMaps

        As List (Of MapDataClassLibrary.ASLMap) =(Maptables.GetASLscenarioMaps(PassASLScenID)).ToList
        For Each
        ScenMap As
        MapDataClassLibrary.ASLMap In

        ASLScenMaps
        BoardNum(ScenMap.RowPosition, ScenMap.ColPosition) =

                CStr(ScenMap.MapNumber)

        Next
        End If

        ContinueScenarioStart(NewScen)

        End Function  */
        return false;
    }

    public boolean OpenScenario(String PassScenID, ASLMap theMap) {
        // open existing scenario
        // set scenario ID property
        ScenName = PassScenID;
        boolean StartScenario = true;
        String Fulltitle = "";
        pmap = (ASLMap) theMap;
        if (pmap == null) {

        } else {
            pgamemap = pmap.getVASLMap();
        }

        GameinPlay = new OpenSaveGame();
        try {
            GameinPlay.OpenGame("Fullrules");
            // retrieve scenario data
            pScenario = GameinPlay.getScenario();
            pOBUnitcol = GameinPlay.unitsinplay();
            pOBSWcol = GameinPlay.swinplay();
            //testgame.SaveGame("Fullrules");
        } catch (IOException e) {

        }
        // use scenario data to set property values
        ScenIDValue=pScenario.getScenNum();
        PhaseValue = pScenario.getPhase();
        PlayerTurnvalue = pScenario.getPTURN();

        ContinueScenarioStart(pScenario);



        return StartScenario;

    }

    private void ContinueScenarioStart(Scenario PassScenario) {
        boolean Startscenario = true;

        // join scenario
        if (!CreatePhaseMVC(Constantvalues.ScenarioAction.JoinPhase)) {
            Startscenario = false;
        }

        // populate collections of game objects
        if (CreateObjectCollections()) {
        } else {
            // get out of the scenario
            Startscenario = false;
        }
        // set title
        if (Startscenario) {
            UpdateScenarioValues();  //temporary while debugging UNDO
        }

    }


    private boolean CreatePhaseMVC(Constantvalues.ScenarioAction scenarioaction) {
        // called by Me.OpenScenario
        // creates MVC for current phase in the scenario
        PhasePattern = new PhaseMVCPattern(PhaseValue, getScenID(), scenarioaction);
        // THE FOLLOWING SHOULD BE PART OF JOIN PHASE IN Phase classes BUT IFTC AND MOVEMENT MUST BE OWN LIBRARIES FIRST

        // temporary while debugging undo
        switch (PhasePattern.GetCurrentPhase()) {
            case PrepFire: case DefensiveFire: case AdvancingFire:
                //IFT = new IFTC(getScenID());
                 //IFT.FirePhasePreparation();
                break;
            case Movement:
                DoMove = new MakeMoveC();
                IFT = new IFTC(getScenID());
                IFT.FirePhasePreparation();
                break;
            default:
                break;
        }

        if (PhasePattern == null) {
            return false;
        } else {
            return true;
        }
    }


    public void SaveScenario() { //boolean NewScen) {
        // NEEDS TO BE REDONE DUE TO CHANGES TO NEW SCENARIO METHODS JULY 15 - SEE StartASLScenario
        // called by Me.PhaseChangeNext, Me.PhaseChangePrevious
        // save current scenario
        GameinPlay.SaveGame("Fullrules");

        /*if (NewScen) {
            // 'sets new scenario number
            String NewScenNum = "";
            //    'CStr(Linqdata.GetNewScennum)
            //'' 'gets filename for new scenario
            //'' If(SaveFileDialog.ShowDialog(GameForm) = DialogResult.OK) Then
//            '' Dim FileName As String = SaveFileDialog.FileName
//            '' 'saves data added to panel to a new record in scenario table
//            '    Game.Linqdata.CreateNewDBrecord(CInt(NewScenNum), CInt(Game.Scenario.BoardNum(0, 0)))
//            '' Dim Maptype As String = "0"
//            '' If CInt (BoardNum(0, 0)) > 1100 Then
//            '' 'historical map
//            '' Maptype = BoardNum(0, 0)
//            '' Else
//            '' 'NOT SURE THIS BLOCK IS NEEDED ANYMORE July 10
//            '' 'NEED TO REVISE TO CREATE Board array for XNAGraph Jan 11
//            '' '    ' geomorphic map
//            '' '    ' desktop config
//            '' '    ' Dim MapFileName As String = ""
//            '' '    ' laptop config
//            '' '    Dim MapFileName As String = "c:\asl\scenarios\" & filetext & ".scm"
//            '' '    Dim Boarduse As String
//            '' '    For i As Integer = 1 To 5
//            '' '        For j As Integer = 1 To 5
//            '' '            Boarduse = BoardNum(i, j)
//            '' '            If Boarduse = Nothing Or Boarduse = "" Then Boarduse = "0"
//            '' '            My.Computer.FileSystem.WriteAllText(MapFileName, Boarduse & ",", True)
//            '' '    Next j, i
//            '' End If
//            '' ' saves key scenario data to text file in scenario directory
//            '' File.WriteAllText(FileName, String.Format("{0},{1}", NewScenNum, Maptype))
//            '' End If
//            '' 'Store scenario number in Scenario property
//            '' _ScenID = CInt(NewScenNum)
//            '' 'GameForm.TabCCamp.Visible = False
//            '' Dim BitmapName As String = GameForm.Scenario.FilePath & "Images\Maps\scen" & CStr(NewScenNum) & ".gif"
//            '' 'saves scenario Map as image file
//            '' Dim BitmaptoSave As New Bitmap(GameForm.pbGameMap.Image)
//            '' BitmaptoSave.Save(BitmapName)
//            '' BitmaptoSave.Dispose()
//            '' 'create scenario Map table in ASLMaps database
//            '' Linqdata.CreateNewMapDbTables("scen" & CStr(NewScenNum))
//            '' End Using
//            ' ' 'update visual display: map, heading, controls,
//            ' ' 'GameForm.pbGameMap.Visible = True
//            '' GameForm.TerrainActions = New TerrainActionsC
//            '' GameForm.SetMainEnvironment(Gamecontext.OpenNewScenario)
//            '' GameForm.SetBaseControls()
        } else {
            // saving existing scenario
            // update changed values -ANY OTHER VALUES NEED CHANGING - Sniper, etc?
            pScenario.setPhase(PhaseValue);
            pScenario.setPTURN(PlayerTurnvalue);
            pScenario.setCURRENTTURN(CurrentTurnvalue);
            pScenario.setFinished(PhasePattern.IsScenarioFinished());

            // write new values back to database
            Linqdata.WriteScenarioData(pScenario);
        }*/
    }

    public void PhaseChangeNext() {
        // called by ActionsToolbar action button

        // check move legal and if so exit current phase then create new MVC
        if (PhaseValue == Constantvalues.Phase.CloseCombat) {
            // moving to new player turn; need to do check for end of scenario
            CasUpdate();
            if (EndofScenarioCheck()) {
                UpdateScenarioValues();
                SaveScenario(); // save existing scenario
                return; // check for campaign and go to refit
            }
        }
        // if ok to change then
        // exit current phase forward
        PhasePattern.TakeAction(Constantvalues.ScenarioAction.ExitfromPhaseForward);
        UpdateScenarioValues();
        SaveScenario();
        // 'go to new phase forward
        NextPhase();
        CreatePhaseMVC(Constantvalues.ScenarioAction.EnterIntoNewPhase);
        UpdateScenarioValues();
        SaveScenario();  // save existing scenario
    }

    public void PhaseChangePrevious() {
        // called by Panelform "<< Ph" action button
        // check move legal and if so exit current phase
        if (PhaseValue == Constantvalues.Phase.Setup) {
            // illegal, there can be no previous phase to Setup
            GameModule.getGameModule().getChatter().send("Cannot move to previous Phase: Scenario Not Started");
            return;
        } else if (PhaseValue == Constantvalues.Phase.Rally) {
            // illegal in certain situations
            if (CurrentTurnvalue == 1 && PlayerTurnvalue == Constantvalues.WhoCanDo.Attacker) {
                GameModule.getGameModule().getChatter().send("Cannot move to previous Phase: Initial Rally Phase in Scenario");
                return;
            }
        }
        // exit current phase backward
        PhasePattern.TakeAction(Constantvalues.ScenarioAction.ExitfromPhaseBack);
        UpdateScenarioValues();
        SaveScenario();
        // go to new phase Backward
        PreviousPhase();
        CreatePhaseMVC(Constantvalues.ScenarioAction.JoinPhase);
        UpdateScenarioValues();
        SaveScenario();  // save existing scenario
    }

    private void NextPhase() {
        // called by Me.PhaseChangeNext
        // changes phase value to next phase
        switch (PhaseValue) {
            case Setup:
                PhaseValue = Constantvalues.Phase.Rally;
                break;
            case Rally:
                PhaseValue = Constantvalues.Phase.PrepFire;
                break;
            case PrepFire:
                PhaseValue = Constantvalues.Phase.Movement;
                break;
            case Movement:
                PhaseValue = Constantvalues.Phase.DefensiveFire;
                break;
            case DefensiveFire:
                PhaseValue = Constantvalues.Phase.AdvancingFire;
                break;
            case AdvancingFire:
                PhaseValue = Constantvalues.Phase.Rout;
                break;
            case Rout:
                PhaseValue = Constantvalues.Phase.Advance;
                break;
            case Advance:
                PhaseValue = Constantvalues.Phase.CloseCombat;
                break;
            case CloseCombat:
                PhaseValue = Constantvalues.Phase.Rally;
                break;
            case Refitphase:
                PhaseValue = Constantvalues.Phase.Setup;
                break;
            default:
                PhaseValue = null;
                break;
        }
    }

    private void PreviousPhase() {
        // called by Me.PhaseChangePrevious
        // changes phase value to previous phase
        switch (PhaseValue) {
            case Setup:
                PhaseValue = Constantvalues.Phase.Setup;
                break;
            case Rally:
                PhaseValue = Constantvalues.Phase.CloseCombat;
                break;
            case PrepFire:
                PhaseValue = Constantvalues.Phase.Rally;
                break;
            case Movement:
                PhaseValue = Constantvalues.Phase.PrepFire;
                break;
            case DefensiveFire:
                PhaseValue = Constantvalues.Phase.Movement;
                break;
            case AdvancingFire:
                PhaseValue = Constantvalues.Phase.DefensiveFire;
                break;
            case Rout:
                PhaseValue = Constantvalues.Phase.AdvancingFire;
                break;
            case Advance:
                PhaseValue = Constantvalues.Phase.Rout;
                break;
            case CloseCombat:
                PhaseValue = Constantvalues.Phase.Advance;
                break;
            case Refitphase:
                PhaseValue = Constantvalues.Phase.CloseCombat;
                break;
            default:
                PhaseValue = null;
                break;
        }
    }

    public Constantvalues.Nationality WhoseTurnIsIt() {

        if (PlayerTurnvalue == Constantvalues.WhoCanDo.Attacker) {
            return pScenario.getATT1();
        } else {
            return pScenario.getDFN1();
        }
    }

    private boolean CreateObjectCollections() {
        // called by OpenScenario
        // done as function to force completion before calling OrderCountersforDisplay

        // temporary while debugging undo
//        TerrainActions = new TerrainActionsC();
//        TerrainActions.ShowTerrainCounters();
        UnitActions = new UnitActionsC(getOBUnitcol());
        //VehicleActions = new VehicleActionsC(Linqdata, this);
        //SWActions = new SWActionsC(Linqdata, this);
//        ConcealActions = new ConcealActionsC();
        return true;
    }

    /*private List<Scenlisttype> GetScenList() {
        return ListofScenarios;
    }

    private List<Scenlisttype> SetScenList() {
        // called by ASLXNA.Game1.LoadContent
        // returns list of current scenarios to be displayed in a list box

        List<Scenario> Scendatalist;
        Scendatalist = Linqdata.GetScenList();
        if (Scendatalist == null) {
            return null;
        }
        for (Scenario Testscen : Scendatalist) {
            if (!Testscen.getFinished()) {
                Scenlisttype ScenToAdd = new Scenlisttype();
                ScenToAdd.setScenName(Testscen.getFULLNAME());
                ScenToAdd.setScenNum(Testscen.getASLName());
                ScenToAdd.setScenID(Testscen.getScenNum());
                ListofScenarios.add(ScenToAdd);
            }
        }
        return ListofScenarios;
    }*/

    public int GetBoardtype() {
        // called by lots of Mapactions routines
        // use scenario data to set MapBtype
        Constantvalues.Map Maptype = pScenario.getMap();
        pScenario = null;

        switch (Maptype) {
            case bdBloodReef:
                return 1;
            case bdPegasusBridge:
                return 2;
            case bdStoumont:
                return 2;
            case bdRedB:
                return 3;
            case bdLaGleize:
                return 3;
            case bdCheneux:
                return 4;
            case bdVotG:
                return 5;
            default:
                return 0; // geomorphic

        }
    }

    public void CasUpdate() {
    }

    private void UpdateScenarioValues() {
        // called by me.OpenScenario, me.savescenario
        // retrieves updated scenario values from the PhaseMVCPattern and updates screen display
        PhaseValue = PhasePattern.GetCurrentPhase();
        CurrentTurnvalue = PhasePattern.GetCurrentTurn();
        PlayerTurnvalue = PhasePattern.GetCurrentPlayerTurn();
        boolean Finishedvalue = PhasePattern.IsScenarioFinished();
        // update display

        // Game.Window.Title = "ASL Scenario: " + Scendet.getFULLNAME();
        Constantvalues.Nationality PhaseSide = WhoseTurnIsIt();
        //String PhaseSideString = Linqdata.GetNatInfo(PhaseSide, 1);
        // Game.Toolbox.Label1.Text = CurrentTurn.ToString + ": " + PhaseSideString + Space(2) + Linqdata.GetPhasename(PhaseValue);
        switch (PhaseValue) {
            case PrepFire:
            case DefensiveFire:
            case AdvancingFire:
                // Game.Toolbox.Button5.Text = "Clear Combat";
                break;
            case Movement:
                // Game.Toolbox.Button5.Text = "End Move";
                break;
        }
    }

    private boolean EndofScenarioCheck() {
        // called by Me.PhaseChangeNext
        // End Scenario check
        // DiceC dice = new UtilClassLibrary.ASLXNA.DiceC;
        int Endcheck = 0;
        String msg;
        if (pScenario.getCURRENTTURN() >= 5) {
            // Endcheck = dice.Dieroll();
            switch (pScenario.getRules()) {
                case PlatoonLCampaign:
                    if (PlayerTurnvalue == Constantvalues.WhoCanDo.Attacker) {
                        if (pScenario.getDAYNIGHT() == Constantvalues.DayNight.NIGHT) {
                            Endcheck = Endcheck + 1;
                        }
                        switch (CurrentTurnvalue) {
                            // process situations that end the game
                            case 6:
                                if (Endcheck <= 2) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 7:
                                if (Endcheck <= 3) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 8:
                                if (Endcheck <= 6) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 9:
                                if (Endcheck <= 6) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 10: {
                                CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                break;
                            }
                        }
                    } else {
                        // add end on half turn test
                    }
                case RedBCampaign:
                    if (PlayerTurnvalue == Constantvalues.WhoCanDo.Defender) {
                        switch (CurrentTurnvalue) {
                            case 5:
                                if (Endcheck == 1) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 6:
                                if (Endcheck <= 3) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 7:
                                if (Endcheck <= 5) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                            case 8:
                                if (Endcheck <= 6) {
                                    CurrentTurnvalue = (int) pScenario.getGT() + 1;
                                    break;
                                }
                        }
                    } else {
                    }
                case PegasusCampaign:
                    switch (CurrentTurnvalue) {
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                    }
            }
            msg = "You rolled a " + Integer.toString(Endcheck);
            if (CurrentTurnvalue > pScenario.getGT()) {
                pScenario.setFinished(true);
                msg = msg + ". IT'S ALL OVER BOYS!";
                GameModule.getGameModule().getChatter().send(msg);
                return true;
            } else {
                // CurrentTurnvalue += 1 - do this in the PhaseMVCPattern
                if (CurrentTurnvalue + 1 == pScenario.getGT()) {
                    msg = msg + ". NOW STARTING LAST TURN";
                    GameModule.getGameModule().getChatter().send(msg);
                } else {
                    msg += ". PLAY ON!";
                    GameModule.getGameModule().getChatter().send(msg);
                }
            }
        }
        return false; // if here then still playing

    }

}
