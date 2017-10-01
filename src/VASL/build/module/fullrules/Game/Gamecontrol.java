package VASL.build.module.fullrules.Game;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.IFTCombatClasses.IFTC;
import VASL.build.module.fullrules.IFTCombatClasses.IIFTC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SelectedThing;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASSAL.build.GameModule;


import java.util.LinkedList;

public class Gamecontrol {
    // This class holds basic game functionality and is inherited by scenario via campaign. It should
    // hold values and routines that could be accessed outside of a scenario
    private int pGameAction; // not implemented yet; will hold Constantvalues.UserInput (2800) and
    public DataC Linqdata = DataC.GetInstance();
    // temporary while debugging undo
    /*public SWStatusC SWStatus; // = new SWStatusC();
    public UnitStatusC UnitState; // = new UnitStatusC();
    public TerrainActionsC TerrainActions; // = new TerrainActionsC();

    public ConcealActionsC ConcealActions; // = new ConcealActionsC();

    public observeri Moveobs; //i = new observeri();*/
    public IIFTC IFT;
    public UnitActionsC UnitActions; // = new UnitActionsC();
    public VehicleActionsC VehicleActions; // = new VehicleActionsC();
    public SWActionsC SWActions; // = new SWActionsC();
    public LinkedList<SelectedThing> SelectedThings = new LinkedList<SelectedThing>();  // part of context menu process
    //    public ContextMenu As ContextMenuStrip   'part of context menu process
    //    public ContextLocation As ContextMenuStrip   'part of context menu process
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();


    // Constructors
    public Gamecontrol() {
        // called by Campaign.New (which is always called by scenario.new)
//        ContextMenu = New ContextMenuStrip
//        ContextLocation = New ContextMenuStrip

    }
    /*
    Friend Sub ShowContextPopups(ByVal OH As VisibleOccupiedhexes, ByVal mousepoint As Point)
            'called by classes that implmement Clicki interface
                    'handles various right click events (of multiple/single units or hex/location)
                    'Get common data
    Dim ScenCurrentPhase
    As Integer = Game.Scenario.Phase
    Dim WhoseTurn
    As Integer = Game.Scenario.WhoseTurnIsIt
            'Set variables
    Dim InfUnitsize
    As Integer = 0 :
    Dim VehUnitsize
    As Integer = 0 ': Dim ConUnitsize As Integer = 0
    Dim InfAttorDef
    As Integer = 0 :
    Dim VehAttorDef
    As Integer = 0 :
    Dim SWAttorDef
    As Integer = 0 ': Dim ConAttorDef As Integer = 0
    Dim InfTypeClicked
    As Integer = 0 :
    Dim VehTypeClicked
    As Integer = 0 ': Dim ConTypeclicked As Integer = 0
    Dim hexUnit
    As ObjectClassLibrary.ASLXNA.PersUniti
    Dim hexVehicle
    As DataClassLibrary.AFV ': Dim hexConceal As DataClassLibrary.Concealment
    Dim hexSW
    As DataClassLibrary.OrderofBattleSW
            'Clear previous selections
                    SelectedThings.Clear()
                    'Put new selections into SelectedThings
    For Each
    DisplaySprite As
    ObjectClassLibrary.ASLXNA.SpriteOrder In
    OH.VisibleCountersInHex
    If DisplaySprite.
    Selected Then
    Dim TypeIs
    As Integer = Game.Linqdata.GetTypeOfThing(DisplaySprite.TypeID)
    Select Case
    TypeIs
    caseConstantvalues.Typetype.Personnel
                            'infantry
    hexUnit =(
    From selunit
    As ObjectClassLibrary.
    ASLXNA.PersUniti In
    Scencolls.Unitcol Where
    selunit.BasePersUnit.Unit_ID =
    DisplaySprite.ObjID Select
    selunit).
    First
            InfTypeClicked = DisplaySprite.TypeID
    InfAttorDef =

    If(hexUnit.BasePersUnit.Nationality =WhoseTurn, Constantvalues.WhatClicked.Attacker, Constantvalues.WhatClicked.Defender)

    Dim TempInfUnitsize
    As Integer = hexUnit.BasePersUnit.Unittype  'holds LineOfBattle.Unittype value
    If InfUnitsize = 0
    Then
                                'first unit
    InfUnitsize =TempInfUnitsize
    ElseIf InfUnitsize = Constantvalues.Utype.Units
    Then
                                'nothing to do here as additional units don'
    t change
    mix
            Else
    If TempInfUnitsize
    <> InfUnitsize Then  'if = is true then no need to change InfUnitsize value

    If(((TempInfUnitsize >=Constantvalues.Utype.Squad And TempInfUnitsize<=Constantvalues.Utype.Crew) Or TempInfUnitsize =
    Constantvalues.Utype.MMC Or
    TempInfUnitsize =Constantvalues.Utype.Dummy)

    And
            ((InfUnitsize >=Constantvalues.Utype.Hero And InfUnitsize<=Constantvalues.Utype.Commissar) Or InfUnitsize =Constantvalues.Utype.SMC))

    Or
            (((InfUnitsize >=Constantvalues.Utype.Squad And InfUnitsize<=Constantvalues.Utype.Crew) Or InfUnitsize =
    Constantvalues.Utype.MMC Or
    TempInfUnitsize =Constantvalues.Utype.Dummy)

    And
            ((TempInfUnitsize >=Constantvalues.Utype.Hero And TempInfUnitsize<=Constantvalues.Utype.Commissar) Or TempInfUnitsize =Constantvalues.Utype.SMC))Then
                                        'MMC and SMC present so = Units
    InfUnitsize =

    Constantvalues.Utype.Units
    ElseIf((TempInfUnitsize<Constantvalues.Utype.Hero) Or TempInfUnitsize =
    Constantvalues.Utype.MMC Or
    TempInfUnitsize =Constantvalues.Utype.Dummy)

    And
            ((InfUnitsize<Constantvalues.Utype.Hero Or InfUnitsize=Constantvalues.Utype.MMC Or TempInfUnitsize=Constantvalues.Utype.Dummy))Then
                                        'two types of mmc present and no SMC so = MMC
    InfUnitsize =

    Constantvalues.Utype.MMC
    ElseIf((TempInfUnitsize >=Constantvalues.Utype.Hero And TempInfUnitsize<=Constantvalues.Utype.Commissar) Or TempInfUnitsize =Constantvalues.Utype.SMC)

    And
            ((InfUnitsize >=Constantvalues.Utype.Hero And InfUnitsize<=Constantvalues.Utype.Commissar) Or InfUnitsize =Constantvalues.Utype.SMC)Then
                                        'two types of SMC and no MMC so = SMC
    InfUnitsize =Constantvalues.Utype.SMC
    End If
    End If
    End If
    caseConstantvalues.Typetype.Vehicle
                            'vehicle
    hexVehicle =Game.Linqdata.VehicleCol.Where(

    Function(FindVeh) FindVeh.AFVID =DisplaySprite.ObjID).
    First
            VehTypeClicked = DisplaySprite.TypeID
    VehAttorDef =

    If(hexVehicle.OwnerNationality =WhoseTurn, Constantvalues.WhatClicked.Attacker, Constantvalues.WhatClicked.Defender)

    Dim TempVehUnitsize = hexVehicle.GetVehtype()
    If VehUnitsize = 0
    Then
            VehUnitsize = TempVehUnitsize
    ElseIf VehUnitsize = TempVehUnitsize
    Then
                                'nothing needs to be done
    ElseIf VehUnitsize >
    TempVehUnitsize Then
    VehUnitsize =TempVehUnitsize
    End If
    caseConstantvalues.Typetype.Gun
                            'Guns
    caseConstantvalues.Typetype.Concealment
                            'Concealment - do nothing as items in stack provide required info
                                    'hexConceal = Game.Linqdata.GetConcealmentfromCol(DisplaySprite.ObjID)
                                    'ConTypeclicked = DisplaySprite.TypeID
                                    'ConAttorDef = If(hexConceal.Nationality = WhoseTurn, Constantvalues.WhatClicked.Attacker, Constantvalues.WhatClicked.Defender)
                                    'ConUnitsize = Constantvalues.Utype.Units  'this
    will cover
    all unit
    possibilities
    caseConstantvalues.Typetype.SW
                            'SW
    hexSW =Game.Linqdata.OBSWcol.Where(

    Function(FindSW) FindSW.OBSW_ID =DisplaySprite.ObjID).
    First
            SWAttorDef = If(hexSW.Nationality = WhoseTurn, Constantvalues.WhatClicked.Attacker, Constantvalues.WhatClicked.Defender)
    caseConstantvalues.Typetype.Location
                            'terrain
                                    'hexTerr = ScenFeatcol.Where(Function(FindTerr) FindTerr.Scenter_id = WhatClicked).First
                                    'AttorDef = 0
    caseConstantvalues.Typetype.WhiteC
                            'white counter
    caseElse

    End Select
    End If
    Next
    If InfUnitsize >0Then
    Dim Selthing
    As New ObjectClassLibrary.ASLXNA.SelectedThing(ScenCurrentPhase,InfUnitsize,InfAttorDef,InfTypeClicked)
            SelectedThings.Add(Selthing)
    End If
            'If ConUnitsize > 0 Then
                    '    Dim Selthing = New SelectedThing(ScenCurrentPhase, ConUnitsize, ConAttorDef, ConTypeclicked)
                    '    SelectedThings.Add(Selthing)
                    'End If
    If VehUnitsize >0Then
    Dim Selthing
    As New ObjectClassLibrary.ASLXNA.SelectedThing(ScenCurrentPhase,VehUnitsize,VehAttorDef,VehTypeClicked)
            SelectedThings.Add(Selthing)
    End If
            'Send list of selected things to observer in MVC which then passes to controller for processing
    If Game.Scenario.ScenID =
    Constantvalues.Phase.Movement Then
                Game.Scenario.Moveobsi.PasstoObserver(SelectedThings,OH)
    Else
    Dim ListofMenuThings = New

    ContextBuilder(SelectedThings, OH)
                'Now need to filter the list by context of whatever has been clicked - done in ContextBuilder
                        'create Context control and filter menu
                        ListofMenuThings.CreateMenu()
    End If

            'Show the popup context menu - events handled by Gameform.dropdownEventHandler
    mousepoint.X =

    CInt(mousepoint.X +Game.Window.ClientBounds.Left+Game.translation.X)

    mousepoint.Y =

    CInt(mousepoint.Y +Game.Window.ClientBounds.Top+Game.translation.Y)
            Game.Scenario.ContextMenu.Show(mousepoint)
    Game.contextshowing =True
    End Sub

    Friend Sub

    dropdownEventHandler(ByVal sender As Object, ByVal e As System.EventArgs)
            'called by click on popup menu item
                    'event handler for all popup menus - set in ASLXNA.ContextBuilder.CreateMenu

                    'reset Contextshowing flag to hide shade
    Game.contextshowing =False
            'determine what clicked
    Dim PassSelection
    As String = ""
    Dim Getitem
    As ToolStripMenuItem
    Getitem =

    CType(sender, ToolStripMenuItem)
            'set variables to pass to MVC model and others
    Dim ControlClick
    As Integer = CInt(Getitem.Tag)  'value of action selected

    If Trim(CStr(Game.Scenario.ContextMenu.Text))="Firer"Then
                'doing Inh and MG selection - menu comes from MGandInherentFPSelection.ShowChoices
    Dim PassUnitUsed
    As Integer = CInt(Game.Scenario.ContextMenu.Tag)  'value of unit being selected
    Dim MGIFPSel
    As New
    MGandInherentFPSelection
                MGIFPSel.ProcessChoice(ControlClick,PassUnitUsed)

    ElseIf Trim(CStr(Game.Scenario.ContextMenu.Text))="Guard"Then
    Dim TargId
    As Integer = CInt(Game.Scenario.ContextMenu.Items(0).Tag)
                Game.Scenario.IFT.ResumeSurrenderResolution(ControlClick,TargId);
    Else
                'handle special case- SHOULD THERE BE OTHERS? Aug 14
    If ControlClick = Constantvalues.ContextM.RecoverSWBrk
    Then PassSelection = Getitem.Text
    If ControlClick = Constantvalues.ContextM.EnterVehicle
    Then PassSelection = Getitem.Text
    Dim PassHexClicked
    As Integer = CInt(Game.Scenario.ContextMenu.Tag)  'value of hex involved
    Dim PhaseinUse = Game.Scenario.Phase
    Select Case
    PhaseinUse
    caseConstantvalues.Phase.Movement
                        Game.Scenario.Moveobsi.PasstoObserver(ControlClick,PassHexClicked,PassSelection)
            'need to add other phases
    End Select
    End If
            'clean up
    Game.Scenario.ContextMenu.Tag =""
    Game.Scenario.ContextMenu.Text =""

    End Sub
    Friend Sub

    LocdropdownEventHandler(ByVal sender As Object, ByVal e As System.EventArgs)
            'handles click events on context popup menu for locations - where need to select one of multiple locations in a hex - driven by ClickShift mouse events
                    'assigned as handler in ContextBuilder.createlocmenu

                    'reset Contextshowing flag to hide shade
    Game.contextshowing =False
            'determine what clicked
    Dim Getitem
    As ToolStripMenuItem
    Getitem =

    CType(sender, ToolStripMenuItem)

    Dim OH
    As VisibleOccupiedhexes
            'determine what and where clicked
    Dim ControlClick
    As Integer = CInt(Getitem.Tag)  'value of location selected
    Dim PassHexClicked
    As Integer = CInt(Game.Scenario.ContextLocation.Tag)  'value of hex involved
    Dim PhaseinUse = Game.Scenario.Phase
    Select Case
    PhaseinUse
    caseConstantvalues.Phase.Movement
                    'handle special case- SHOULD THERE BE OTHERS?

    If CInt(Game.Scenario.ContextLocation.Text) =
    Constantvalues.UMove.DoPlaceDC Then
                        'set last parameter (hexclicktype) to 0 to trigger correct selection
                                Game.Scenario.Moveobsi.PasstoObserver(Constantvalues.UMove.DoPlaceDC,PassHexClicked,

    CStr(ControlClick))

    ElseIf(ControlClick >=6000And ControlClick<=6999) Then  'some kind of terrrain or feature selected
            'now determine what kind of action required
    Select Case

    CInt(Getitem.Name) 'stores value of mouse click
    caseConstantvalues.MouseAction.LeftShift
                                'select units in the location
    OH =

    CType(Game.Scenario.HexesWithCounter(PassHexClicked),VisibleOccupiedhexes)
    For Each
    DisplaySprite As
    ObjectClassLibrary.ASLXNA.SpriteOrder In
    OH.VisibleCountersInHex
    If DisplaySprite.hexloc =
    ControlClick And
    DisplaySprite.TypeID< 5000Then 'is infantry, vehicle, gun or concealment in selected location, not SW or Terrain
    DisplaySprite.Selected =True
    End If
    Next
                                'set as moving units
                                        Game.Scenario.Moveobsi.PasstoObserver(OH)
    caseConstantvalues.MouseAction.RightShift
                                'show context popup for units in the location
    OH =

    CType(Game.Scenario.HexesWithCounter(PassHexClicked),VisibleOccupiedhexes)
    For Each
    DisplaySprite As
    ObjectClassLibrary.ASLXNA.SpriteOrder In
    OH.VisibleCountersInHex
    If DisplaySprite.hexloc =
    ControlClick And
    DisplaySprite.TypeID< 5000Then 'is infantry, vehicle, gun or concealment in selected location, not SW or Terrain
    DisplaySprite.Selected =True
    End If
    Next
                                'set as moving units
                                        Game.Scenario.Moveobsi.PasstoObserver(OH)
            'show the context popup
    Dim MapGeo
    As MapGeoClassLibrary.ASLXNA.MapGeoC =MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0,0,0,0,0,0,0,0,0,0,0)' use zeros when know instance exists Mapgeo.MapBtype, Mapgeo.Xoffset, Mapgeo.Yoffset, Mapgeo.MaxCols, Mapgeo.MaxRows)
    Dim Mousepoint
    As System.
    Drawing.Point
            Mousepoint = MapGeo.SetPoint(PassHexClicked)
                                Game.Scenario.ShowContextPopups(OH,Mousepoint)
            'set Contextshowing flag to display shade
    Game.contextshowing =True
    End Select
    Else
                        'NOT SURE WHY WOULD GET HERE? Control click would have to be something other than terrain/feature - what?
                                Game.Scenario.Moveobsi.PasstoObserver(ControlClick,PassHexClicked,"")
    End If
                    'ADD OTHER PHASES - PROBABLY SHOULD CLASS OUT WITH AN INTERFACE
    End Select
            'clean up
    Game.Scenario.ContextLocation.Tag =""
    End Sub
#
    End Region*/
}



    /*
    Friend Class GunActionsC
    Friend Sub New()

    End Sub
    End Class
    Friend Class TerrainActionsC
        '
                'constructor
    Friend Sub New()
    End Sub
    Friend Sub ShowTerrainCounters()
            'called by Scenario.createcounters
                    'creates special terrain counter sprites as required by scenario situation
    Dim ManageScenT = New ManageScenarioTerrain
            'retrive list of scenario terrain objects
    Dim TerrainCounterShow As IQueryable(Of MapDataClassLibrary.GameLocation) = ManageScenT.ShowScenarioTerrain(Game.Scenario.LocationCol)
            'create sprites and add to hex collection
    For Each ShowCounter As MapDataClassLibrary.GameLocation In TerrainCounterShow
    ShowTerrainInLocation(ShowCounter)
    Next
            'sniper - create sprites
    Dim Sniperhex As MapDataClassLibrary.GameLocation : Dim sidetoget As Integer = 0
    Dim Scendet As DataClassLibrary.scen = Game.Linqdata.GetScenarioData(Game.Scenario.ScenID) 'retrieves scenario data
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim util = New UtilClassLibrary.ASLXNA.ConversionC : Dim Sniperid As Integer = 0
    If Scendet.Sanattaloc > 0 Then
            Sniperhex = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sanattaloc))
    sidetoget = CInt(Scendet.ATT1)
    Sniperid = util.GetSnipervalue(sidetoget, "A")
    SetSniperTextureAndCreateSprite(sidetoget, Sniperhex, Sniperid)
    End If
    If Scendet.Sanattbloc > 0 Then
            Sniperhex = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sanattbloc))
    sidetoget = CInt(Scendet.ATT1)
    Sniperid = util.GetSnipervalue(sidetoget, "B")
    SetSniperTextureAndCreateSprite(sidetoget, Sniperhex, Sniperid)
    End If
    If Scendet.Sandfnaloc > 0 Then
            Sniperhex = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sandfnaloc))
    sidetoget = CInt(Scendet.DFN1)
    Sniperid = util.GetSnipervalue(sidetoget, "A")
    SetSniperTextureAndCreateSprite(sidetoget, Sniperhex, Sniperid)
    End If
    If Scendet.Sandfnbloc > 0 Then
            Sniperhex = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sandfnbloc))
    sidetoget = CInt(Scendet.DFN1)
    Sniperid = util.GetSnipervalue(sidetoget, "B")
    SetSniperTextureAndCreateSprite(sidetoget, Sniperhex, Sniperid)
    End If
    End Sub
    Friend Sub ShowTerrainInLocation(ByVal ShowCounter As MapDataClassLibrary.GameLocation)
            'called by Me.ShowTerrainCounters
                    'determines if a counter(s) should be visible and if so, creates a sprite and sets the texture

    Dim TerrTexture As Texture2D = Nothing : Dim Getname As String = "" : Dim Drawsprite As ObjectClassLibrary.ASLXNA.SpriteOrder
            'go through types - can have multiple counters in a hex and location
                    'APMines
    If ShowCounter.APMines > 0 And ShowCounter.APMinesVisible Then
                'if visible, set texture
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Feature.APMines, Getname)
                'if not no texture then create sprite
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, ShowCounter.APMines, Getname)
                'add to hex collection
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'ATMines
    If ShowCounter.ATMines > 0 And ShowCounter.ATMinesVisible Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Feature.ATMines, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, ShowCounter.ATMines, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'wire
    If ShowCounter.IsWire And ShowCounter.WireVisible Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Feature.Wire, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Feature.Wire, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'smoke
    If ShowCounter.Smoke > 0 Then
            TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Location.Smoketype, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, ShowCounter.Smoke, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'Rubble
    If ShowCounter.IsRubble And ShowCounter.ShowCounter Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Location.Rubbletype, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, ShowCounter.Location, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'Pillbox
    If ShowCounter.IsPillbox Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Location.Pillboxtype, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, ShowCounter.Location, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'Fortified
    If ShowCounter.IsFortified Then
    Dim Feattype As Integer = 0
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Location.FortBuildtype, Getname)
    Select caseShowCounter.LevelInHex
    case-1
    Feattype = Constantvalues.Location.Cellar
    case0
    If ShowCounter.IsStone Then Feattype = Constantvalues.Location.FortStoneGrd Else Feattype = Constantvalues.Location.FortWoodGrd
    case1
    If ShowCounter.IsStone Then Feattype = Constantvalues.Location.FortSTone1st Else Feattype = Constantvalues.Location.FortWood1st
    case2
    If ShowCounter.IsStone Then Feattype = Constantvalues.Location.FortStone2nd Else Feattype = Constantvalues.Location.FortWood2nd
    case3
    If ShowCounter.IsStone Then Feattype = Constantvalues.Location.FortSTone3rd Else Feattype = Constantvalues.Location.FortWoodGrd
    caseElse
    Feattype = 0
    End Select
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Feattype, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'Roadblock  - need to check each hexside  -COME BACK AND USE DYNAMIC LINQ QUERY TO DO THIS AS A LOOP
    If ShowCounter.Hexside1 = Constantvalues.Hexside.Roadblock1 Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Hexside.Roadblock1, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Hexside.Roadblock1, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
    If ShowCounter.Hexside2 = Constantvalues.Hexside.Roadblock2 Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Hexside.Roadblock2, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Hexside.Roadblock2, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
    If ShowCounter.Hexside3 = Constantvalues.Hexside.Roadblock3 Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Hexside.Roadblock3, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Hexside.Roadblock3, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
    If ShowCounter.Hexside4 = Constantvalues.Hexside.Roadblock4 Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Hexside.Roadblock4, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Hexside.Roadblock4, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
    If ShowCounter.Hexside5 = Constantvalues.Hexside.Roadblock5 Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Hexside.Roadblock5, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Hexside.Roadblock5, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
    If ShowCounter.Hexside6 = Constantvalues.Hexside.Roadblock6 Then
    TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Hexside.Roadblock6, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, Constantvalues.Hexside.Roadblock6, Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'OBA
    If ShowCounter.OBA > 0 Then
            TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Location.OBAtype, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, CInt(ShowCounter.OBA), Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'Entrenchment
    If ShowCounter.Entrenchment > 0 Then
            TerrTexture = SetTextureandName(ShowCounter, Constantvalues.Feature.entrenchment, Getname)
    If Not IsNothing(TerrTexture) Then Drawsprite = CreateSprite(ShowCounter, TerrTexture, CInt(ShowCounter.Entrenchment), Getname)
    AddToDisplay(ShowCounter, Drawsprite)
    End If
            'ANY OTHER TERRAIN TYPES? wreck, strat loc, control

    End Sub
    Friend Function SetSniperTextureAndCreateSprite(ByVal sidetoget As Integer, ByVal sniperhex As MapDataClassLibrary.GameLocation, ByVal snipervalue As Integer) As Boolean
            'called by Me.ShowTerrainCounters
                    'determines if a counter(s) should be visible and if so, creates a sprite and sets the texture
    Dim snipTexture As Microsoft.Xna.Framework.Graphics.Texture2D : Dim Drawsprite As ObjectClassLibrary.ASLXNA.SpriteOrder
            'set texture name
    Dim TextureName As String = Trim(Game.Linqdata.GetNatInfo(sidetoget, 2)) & "Sniper"
            'load the sniper texture
    Try
            snipTexture = Game.Content.Load(Of Texture2D)(Trim(TextureName))
    Catch
                MessageBox.Show("The god damn image file isn't loading", "GetTexture(Scenterrain)")
    End Try
            'if not no texture then create sprite
    If Not IsNothing(snipTexture) Then
    Dim DrawPos As Vector2 = GetDrawpos(sniperhex.Hexnum)
    Drawsprite = New ObjectClassLibrary.ASLXNA.SpriteOrder(snipervalue, CInt(sniperhex.Location), Constantvalues.Feature.Sniper,
    snipTexture, DrawPos, "Sniper", Constantvalues.Feature.Sniper, sniperhex.Hexnum, sniperhex.LocIndex)
            'add to hex collection
    AddToDisplay(sniperhex, Drawsprite)
    End If
    End Function
    private Function SetTextureandName(ByVal Showcounter As MapDataClassLibrary.GameLocation, ByVal Typetoget As Integer, ByRef GetName As String) As Texture2D
            'called by Me.ShowScenarioTerrain
                    'modelled on ScenFeat.SetTexture()
                    'gets texture and name for non-unit counters
    Dim TerrTexture As String = ""
    Dim Passtype As Integer = 0
    Dim TerrCheck As TerrainClassLibrary.ASLXNA.TerrainChecks = New TerrainClassLibrary.ASLXNA.TerrainChecks(Game.Scenario.LocationCol)
    Dim SideCheck As TerrainClassLibrary.ASLXNA.IsSide = New TerrainClassLibrary.ASLXNA.IsSide(Game.Scenario.LocationCol)
    Select caseTypetoget
    caseConstantvalues.Feature.APMines
            TerrTexture = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Image, Showcounter.APMines, Game.Scenario.Maptables)
    GetName = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Desc, Showcounter.APMines, Game.Scenario.Maptables)
    caseConstantvalues.Feature.ATMines
            TerrTexture = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Image, Showcounter.ATMines, Game.Scenario.Maptables)
    GetName = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Desc, Showcounter.ATMines, Game.Scenario.Maptables)
    caseConstantvalues.Feature.Wire
            TerrTexture = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Image, Constantvalues.Feature.Wire, Game.Scenario.Maptables)
    GetName = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Desc, Constantvalues.Feature.Wire, Game.Scenario.Maptables)
    caseConstantvalues.Location.Rubbletype
            TerrTexture = TerrCheck.GetLocationData(Constantvalues.TerrFactor.Image, Showcounter.Location, Game.Scenario.Maptables)
    GetName = TerrCheck.GetLocationData(Constantvalues.TerrFactor.Desc, Showcounter.Location, Game.Scenario.Maptables)
    caseConstantvalues.Location.Pillboxtype
            TerrTexture = TerrCheck.GetLocationData(Constantvalues.TerrFactor.Image, Showcounter.Location, Game.Scenario.Maptables)
    GetName = TerrCheck.GetLocationData(Constantvalues.TerrFactor.Desc, Showcounter.Location, Game.Scenario.Maptables)
    caseConstantvalues.Location.FortBuildtype
    Select caseShowcounter.LevelInHex
    case-1
    Passtype = Constantvalues.Location.Cellar
    case0
    If Showcounter.IsStone Then Passtype = Constantvalues.Location.FortStoneGrd Else Passtype = Constantvalues.Location.FortWoodGrd
    case1
    If Showcounter.IsStone Then Passtype = Constantvalues.Location.FortSTone1st Else Passtype = Constantvalues.Location.FortWood1st
    case2
    If Showcounter.IsStone Then Passtype = Constantvalues.Location.FortStone2nd Else Passtype = Constantvalues.Location.FortWood2nd
    case3
    If Showcounter.IsStone Then Passtype = Constantvalues.Location.FortSTone3rd Else Passtype = Constantvalues.Location.FortWoodGrd
    caseElse
    Passtype = 0
    End Select
    TerrTexture = TerrCheck.GetLocationData(Constantvalues.TerrFactor.Image, Passtype, Game.Scenario.Maptables)
    GetName = TerrCheck.GetLocationData(Constantvalues.TerrFactor.Desc, Passtype, Game.Scenario.Maptables)
    caseConstantvalues.Hexside.Roadblock1
            TerrTexture = SideCheck.GetSideData(Constantvalues.TerrFactor.Image, Constantvalues.Hexside.Roadblock1, Game.Scenario.Maptables)
    GetName = SideCheck.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Constantvalues.Hexside.Roadblock1, Game.Scenario.Maptables)
    caseConstantvalues.Hexside.Roadblock2
            TerrTexture = SideCheck.GetSideData(Constantvalues.TerrFactor.Image, Constantvalues.Hexside.Roadblock2, Game.Scenario.Maptables)
    GetName = SideCheck.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Constantvalues.Hexside.Roadblock2, Game.Scenario.Maptables)
    caseConstantvalues.Hexside.Roadblock3
            TerrTexture = SideCheck.GetSideData(Constantvalues.TerrFactor.Image, Constantvalues.Hexside.Roadblock3, Game.Scenario.Maptables)
    GetName = SideCheck.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Constantvalues.Hexside.Roadblock3, Game.Scenario.Maptables)
    caseConstantvalues.Hexside.Roadblock4
            TerrTexture = SideCheck.GetSideData(Constantvalues.TerrFactor.Image, Constantvalues.Hexside.Roadblock4, Game.Scenario.Maptables)
    GetName = SideCheck.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Constantvalues.Hexside.Roadblock4, Game.Scenario.Maptables)
    caseConstantvalues.Hexside.Roadblock5
            TerrTexture = SideCheck.GetSideData(Constantvalues.TerrFactor.Image, Constantvalues.Hexside.Roadblock5, Game.Scenario.Maptables)
    GetName = SideCheck.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Constantvalues.Hexside.Roadblock5, Game.Scenario.Maptables)
    caseConstantvalues.Hexside.Roadblock6
            TerrTexture = SideCheck.GetSideData(Constantvalues.TerrFactor.Image, Constantvalues.Hexside.Roadblock6, Game.Scenario.Maptables)
    GetName = SideCheck.GetSideData(Constantvalues.TerrFactor.Hexsidedesc, Constantvalues.Hexside.Roadblock6, Game.Scenario.Maptables)
            'caseOBA

    caseConstantvalues.Feature.entrenchment
            TerrTexture = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Image, CInt(Showcounter.Entrenchment), Game.Scenario.Maptables)
    GetName = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Desc, CInt(Showcounter.Entrenchment), Game.Scenario.Maptables)
    caseConstantvalues.Location.Smoketype
    If Showcounter.Smoke >= Constantvalues.VisHind.VehDust And Showcounter.Smoke <= Constantvalues.VisHind.GreyWPDisp Then
    TerrTexture = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Image, CInt(Showcounter.Smoke), Game.Scenario.Maptables)
    GetName = TerrCheck.GetPositionData(Constantvalues.TerrFactor.Desc, CInt(Showcounter.Smoke), Game.Scenario.Maptables)
    End If
    End Select

    If IsNothing(TerrTexture) Then
    Return Nothing
    Else
            Try
    Return Game.Content.Load(Of Texture2D)(Trim(TerrTexture))
    Catch
                    MessageBox.Show("The god damn image file isn't loading", "TerrainActionsC.SetTextureandName")
    Return Nothing
    End Try
    End If
    End Function
    private Function CreateSprite(ByVal Showcounter As MapDataClassLibrary.GameLocation, ByVal TerrTexture As Texture2D, ByVal Featuretype As Integer, ByVal Featurename As String) As ObjectClassLibrary.ASLXNA.SpriteOrder
            'called by Me.ShowTerrainInLocation
                    'creates sprite for terrain feature to be shown on map
    Dim DrawPos As Vector2 = GetDrawpos(Showcounter.Hexnum)
    Dim DisplaySprite = New ObjectClassLibrary.ASLXNA.SpriteOrder(Showcounter.LocIndex, CInt(Showcounter.Location), Featuretype,
    TerrTexture, DrawPos, Featurename, Featuretype, Showcounter.Hexnum, Showcounter.LocIndex)
    Return DisplaySprite
    End Function
    private Function GetDrawpos(ByVal Hexnum As Integer) As Vector2
            'called by Me.Createsprite
                    'returns topleft position of feature counter to draw
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Dim Drawpos As Vector2
    Drawpos.X = CInt(MapGeo.GetCX(CInt(Hexnum)) - 23)
    Drawpos.Y = CInt(MapGeo.GetCY(CInt(Hexnum)) - 23)
    Return Drawpos
    End Function
    private Sub AddToDisplay(ByVal Showcounter As MapDataClassLibrary.GameLocation, ByVal Displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder)
            'called by Me. varous methods
                    'adds to list of hexes with a counter texture to show

                    'adds to collection of hexes where counters are to be displayed plus adds to list of sprites in a specified hex
    Dim hexnum As Integer = CInt(Showcounter.Hexnum)
            'adds to list of hexes with sprite
    If Not (Game.Scenario.HexesWithCounter.ContainsKey(hexnum)) Then
                'if hex not already added, then add
                        Game.Scenario.HexesWithCounter.Add(hexnum, New VisibleOccupiedhexes(hexnum))
    End If
            'adds specific counter infor to collection for each occupied hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
            OH.AddObjecttoDisplay(Displaysprite)
    OH = Nothing
    End Sub
    End Class


    Friend Class ConcealActionsC
        'called by Me.CreateCounters
                'creates the Scencoll collection of Concealment counters in the scenario and creates sprites for them

                'constructor
    Friend Sub New()
    Game.Linqdata.Concealcol = Game.Linqdata.RetrieveConcealment(Game.Scenario.ScenID)
    If Game.Linqdata.Concealcol.Count = 0 Then
    MsgBox("No scenario concealment found. Exiting")
    Exit Sub
    End If

            'use Object Factory to create the Concealment
    Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
    Dim MapGeo As mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Dim Scencolls = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance : Dim AddNewUnit As ObjectClassLibrary.ASLXNA.PersUniti
    For Each concealitem As DataClassLibrary.Concealment In Game.Linqdata.Concealcol
    If concealitem.ValidateConceal Then
    AddNewUnit = UseObjectFactory.CreateExistingInstance(, concealitem)
    If IsNothing(AddNewUnit) Then Continue For
                    Scencolls.Unitcol.Add(AddNewUnit)
            'determines if a counter should be visible and if so sets the texture
    Dim TextureName As String = AddNewUnit.SetTexture()
    If Not IsNothing(TextureName) Then
    AddNewUnit.BasePersUnit.OBTexture = Game.Content.Load(Of Texture2D)(Trim(TextureName))
            'adds to list of hexes with a counter texture to show
    Game.Scenario.DisplaySprite = New ObjectClassLibrary.ASLXNA.SpriteOrder(AddNewUnit.BasePersUnit.Unit_ID, CInt(AddNewUnit.BasePersUnit.hexlocation), CInt(AddNewUnit.BasePersUnit.Nationality) + 2000, AddNewUnit.BasePersUnit.OBTexture, AddNewUnit.BasePersUnit.DrawPos(MapGeo.MapBtype, MapGeo.Xoffset, MapGeo.Yoffset, MapGeo.MaxCols, MapGeo.MaxRows), AddNewUnit.BasePersUnit.UnitName, CInt(AddNewUnit.BasePersUnit.hexPosition), CInt(AddNewUnit.BasePersUnit.Hexnum), CInt(AddNewUnit.BasePersUnit.LOCIndex))
            'creates collection of hexes where counters are to be displayed
    Dim hexnum As Integer = CInt(AddNewUnit.BasePersUnit.Hexnum)
    If Not (Game.Scenario.HexesWithCounter.ContainsKey(hexnum)) Then
                            'if hex not already added, then add
                                    Game.Scenario.HexesWithCounter.Add(hexnum, New VisibleOccupiedhexes(hexnum))
    End If
                        'adds specific counter infor to collection for each occupied hex
    Dim OH As VisibleOccupiedhexes
    OH = CType(Game.Scenario.HexesWithCounter(hexnum), VisibleOccupiedhexes)
            OH.AddObjecttoDisplay(Game.Scenario.DisplaySprite)
            'check to see if level, sewer, or other location counter needs to be displayed - inherent locations only
            OH.CheckforAssociatedLocationCounter(Game.Scenario.DisplaySprite)
    OH = Nothing

    End If
    End If
    Next
    End Sub
    End Class
    /*
    Friend Class BoatPlaneActionsC
    Friend Sub New()

    End Sub
    Public Sub BoatDriftCheck()

    End Sub
    End Class


    'NOT SURE IF THIS IS BEST LOCATION FOR THE FOLLOWING CLASSES? Aug 14 Where else?
    Public Interface locationclickactionsi
        'Inferface which provides structure to certain mouse clicks on map locations: search, sniper, place dc
    Property CasualtiesFlag As Boolean
    Property CasualtiesDRM As Integer
    Function HandleAction(ByVal hexnum As Integer, ByVal controlclick As Integer) As Boolean
    End Interface
    Public Class SearchAHex
    Implements locationclickactionsi
    private MovingNationality As Integer
    private mycasflag As Boolean = False
    private myCasDRM As Integer = 0
    private scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    private myDMHexes As New List(Of Integer)
    Public Property CasualtiesFlag As Boolean Implements locationclickactionsi.CasualtiesFlag
            Get
    Return myCasFlag
    End Get
    Set(value As Boolean)
    myCasFlag = value
    End Set
    End Property

    Public Property CasualtiesDRM As Integer Implements locationclickactionsi.CasualtiesDRM
            Get
    Return myCasDRM
    End Get
    Set(value As Integer)
    myCasDRM = value
    End Set
    End Property

    Public Function Search(ByVal Hexnum As Integer, ByVal controlclick As Integer) As Boolean Implements locationclickactionsi.HandleAction
            'called by MovementC.Process popup
                    'driven by left click on shaded hex - tested in MovementItem.MoveItem
                    'searches for and reveals contents of specified hex
    Dim XNAGph = XNAGraphicsC.GetInstance
            'get hexname for reporting
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim Hexname As String = GetLocs.GetnamefromdatatableMap(Hexnum)
            ' reduce count of remaining searchable hexes by one
    XNAGph.DisplayShade.HexesToSearch -= 1
            'info report
    If XNAGph.DisplayShade.HexesToSearch = 0 Then
                MessageBox.Show("Final search underway in " & Trim(Hexname) & " . . . . ", "Trying to Search " & Trim(Hexname))
    Else
                MessageBox.Show("Searching " & Trim(Hexname) & " . . . . ", "SearchAhex.New")
    End If
            'now do search
    Dim Searchdone As ShadeHex = (From QU In Game.HexesToShade Where QU.Hexnum = Hexnum Select QU).First
            Searchdone.SetSearchShader(Constantvalues.ShadeShow.Searched)
            'Get nationality of searching side
    Dim CheckNat As ObjectClassLibrary.ASLXNA.PersUniti = CType(scencolls.SelMoveUnits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
    MovingNationality = CheckNat.BasePersUnit.Nationality
            'now loop through each location in the hex and each object in the location
    Dim Loclist As List(Of MapDataClassLibrary.GameLocation) = (GetLocs.RetrieveLocationsfromMapTable(Hexnum, "Hexnum")).ToList
    Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    For Each CheckLoc As MapDataClassLibrary.GameLocation In Loclist
                'can't search below ground locations
    If CheckLoc.Location = Constantvalues.Location.Sewer Or CheckLoc.Location = Constantvalues.Location.InCave Or
    CheckLoc.Location = Constantvalues.Feature.Tunnel Then Continue For
                'can't check cellar from non-ground level
    Dim Locinuse As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromMaptable(CheckNat.BasePersUnit.Hexnum, CheckNat.BasePersUnit.hexlocation)
    If Locinuse.LevelInHex > 0 And (CheckLoc.Location = Constantvalues.Location.Cellar Or CheckLoc.Location = Constantvalues.Location.BunkUnder) Then Continue For
                'reveal hidden fortifications (FortBuidling and Mines only)
    FortifiedBuildingCheck(CheckLoc)
    MineCheck(CheckLoc)
                'get contents of location
    Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(CheckLoc.Hexnum), VisibleOccupiedhexes)
    If IsNothing(OH) Then
                    Game.Scenario.HexesWithCounter.Add(CheckLoc.Hexnum, New VisibleOccupiedhexes(CheckLoc.Hexnum))
    OH = CType(Game.Scenario.HexesWithCounter(CheckLoc.Hexnum), VisibleOccupiedhexes)
    End If
                'reveal concealed units
    RevealConcealed(OH)
                'put hidden things on board
    Dim ContentsofLocationToBeSearched = New UtilWObj.ASLXNA.ContentsofLocation(CheckLoc.LocIndex)
            ContentsofLocationToBeSearched.GetContents()
    Dim Boolcheck As Boolean = PutHiddenOnboard(ContentsofLocationToBeSearched.ContentsInLocation)
                'display the sprites
                        OH.GetAllSpritesInHex()
                        OH.RedoDisplayOrder()
    If OH.Hexnum = scencolls.SelMoveUnits.Item(0).BasePersUnit.Hexnum Then 'searching in units own hex
    Dim DisplayCheck As Boolean = False
                    'need to reselect moving units - selection cleared by RedoDisplayOrder
    For Each MovingUnit In scencolls.SelMoveUnits
    For Each Displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    Dim CompareCheck As New CompareUnitandSpriteC(MovingUnit, Displaysprite)
    Displaycheck = CompareCheck.Compare
    If DisplayCheck Then
    Displaysprite.Selected = True
    If MovingUnit.BasePersUnit.FirstSWLink > 0 Then
    Dim MovingSW As ObjectClassLibrary.ASLXNA.SpriteOrder = (From selsw As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where selsw.ObjID = MovingUnit.BasePersUnit.FirstSWLink Select selsw).First
    MovingSW.Selected = True
    End If
    If MovingUnit.BasePersUnit.SecondSWlink > 0 Then
    Dim MovingSW As ObjectClassLibrary.ASLXNA.SpriteOrder = (From selsw As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex Where selsw.ObjID = MovingUnit.BasePersUnit.SecondSWlink Select selsw).First
    MovingSW.Selected = True
    End If
    End If
    Next
            Next

    End If
    Next
    If myDMHexes.Count > 0 Then
    For Each DMHex As Integer In myDMHexes
    Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(DMHex), VisibleOccupiedhexes)
    If IsNothing(OH) Then
                        Game.Scenario.HexesWithCounter.Add(DMHex, New VisibleOccupiedhexes(DMHex))
    OH = CType(Game.Scenario.HexesWithCounter(DMHex), VisibleOccupiedhexes)
    End If
                    'display the sprites
                            OH.GetAllSpritesInHex()
                            OH.RedoDisplayOrder()
    Next
    End If
    End Function
    private Function PutHiddenOnboard(ByVal LocationtoCheck As List(Of ObjectClassLibrary.ASLXNA.LocationContent)) As Boolean
            'also found in MovementValidation - this version unique to search
                    'called by SearchAhex.New
                    'puts hidden units on board under a new concealment counter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
    Dim ShowHidden As New List(Of Integer)
    If Not IsNothing(LocationtoCheck) Then
    For Each ItemInHex As ObjectClassLibrary.ASLXNA.LocationContent In LocationtoCheck
                    'AddSidecheck
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Personnel, ItemInHex.TypeID) Then
                        'infantry
    Dim hexUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = ItemInHex.ObjID Select selunit).First
                        'side check
    If Not (hexUnit.BasePersUnit.Nationality = MovingNationality) And hexUnit.BasePersUnit.VisibilityStatus = Constantvalues.VisibilityStatus.Hidden Then
                            ShowHidden.Add(hexUnit.BasePersUnit.Unit_ID)
    ElseIf hexUnit.BasePersUnit.VisibilityStatus = Constantvalues.VisibilityStatus.Visible Then
    CasualtiesFlag = True
    End If
    Else
                        'terrain already done - mines & fort building revealed
                                'need to add guns, vehicles,
    End If
    Next
    For Each UnitShow As Integer In ShowHidden
    Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange = New ObjectChange.ASLXNA.ConcealUnitC(UnitShow)
            UnittoChange.TakeAction()
    Next
    End If
    Return True
    End Function
    private Function FortifiedBuildingCheck(ByVal Checkloc As MapDataClassLibrary.GameLocation) As Boolean
            'called by Me.search
                    'reveals hidden fortified building location due to search
    If Checkloc.IsFortified Then
    Dim ShowFort As New ShowLocationCounter(Checkloc)
                ShowFort.ShowFortified()
    Return True
    End If
    Return False
    End Function
    private Function MineCheck(ByVal Checkloc As MapDataClassLibrary.GameLocation) As Boolean
            'called by SearchAhexNew
                    'reveals hidden mines

                    'check if location is basehex location
    Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(Game.Scenario.LocationCol)
    Dim UsingHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(Checkloc.Hexnum, 0)
    Dim Basehexloc As Integer = UsingHex.Location
    If Checkloc.Location = Basehexloc Then 'at ground level where mines can exist
    Dim ShowMines As New ShowMinefield(Checkloc)
    End If
    Return True
    End Function
    private Function RevealConcealed(ByVal OH As VisibleOccupiedhexes) As Boolean
            'called by SearchAhex.New
                    'removes concealment from enemy units and deletes dummies in location parameter
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
    Dim EliminatedCon As New List(Of Integer) : Dim elimsprite As New List(Of ObjectClassLibrary.ASLXNA.SpriteOrder)
    Dim Reveallist As New List(Of Integer) : Dim ElimDummylist As New List(Of Integer) : Dim PassStack As New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange
    If Not IsNothing(OH) Then
    For Each ItemInHex As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
    If TypeCheck.IsThingATypeOf(Constantvalues.Typetype.Concealment, ItemInHex.TypeID) Then
                        'concealment
    Dim ConElim As Integer = 0 : Dim spriteelim As ObjectClassLibrary.ASLXNA.SpriteOrder
    Dim Conitem As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = ItemInHex.ObjID AndAlso selunit.BasePersUnit.TypeType_ID = Constantvalues.Typetype.Concealment Select selunit).First
                        'side check
    If Not Conitem.BasePersUnit.Nationality = MovingNationality Then
                            'show revealed units, eliminate dummies and update collection and screen display
                                    'get concealed units/dummies
    Dim Conlist As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = Conitem.BasePersUnit.Unit_ID Select selunit).ToList
                            'check from previously eliminated con counters
    For Each Conunit In Conlist
    Dim StateCheck = New UtilClassLibrary.ASLXNA.StateChecksc
    If StateCheck.IsUnitDummy(Conunit.BasePersUnit.LOBLink) Then
                                    'dummy, eliminate
                                            ElimDummylist.Add(Conunit.BasePersUnit.Unit_ID)
    Try
            spriteelim = (From Domatch In OH.VisibleCountersInHex Where Domatch.TypeID >= 201 And Domatch.TypeID < 215 And Domatch.ObjID = Conunit.BasePersUnit.Unit_ID).First
                                        elimsprite.Add(spriteelim)
    Catch ex As Exception
    End Try
    Else
                                    'real unit, reveal
                                            Reveallist.Add(Conunit.BasePersUnit.Unit_ID)
            PassStack.Add(Conunit)
    End If

    Next
                            'now eliminate the concealment counter from conceal collection and screen display
    ConElim = ItemInHex.ObjID
                            EliminatedCon.Add(ConElim) 'use statuschange
    Try
            spriteelim = (From Domatch In OH.VisibleCountersInHex Where Domatch.TypeID >= 4000 And Domatch.TypeID < 5000 And Domatch.ObjID = ConElim).First
                                elimsprite.Add(spriteelim)
    Catch ex As Exception
    End Try
    End If
    End If
    Next
                'process the changes - can't do within For . . . Next
                'do the reveal
    For Each ConunitID In Reveallist
            UnittoChange = New ObjectChange.ASLXNA.RevealUnitC(ConunitID)
            UnittoChange.TakeAction()
    Dim DMCHeck = New ObjectChange.ASLXNA.AutoDM(PassStack)
    myDMHexes = DMCHeck.HexestoDM
            Next
    For Each Conunitid In ElimDummylist
            UnittoChange = New ObjectChange.ASLXNA.RevealDummyC(Conunitid)
            UnittoChange.TakeAction()
    Next
    CalcCasualtiesDRM(Reveallist)
                'perform deletions
    If Not IsNothing(EliminatedCon) Then
    For Each conelim As Integer In EliminatedCon
            UnittoChange = New ObjectChange.ASLXNA.ElimConcealC(conelim)
            UnittoChange.TakeAction()
    Next
    End If
    If Not IsNothing(elimsprite) Then
    For Each spriteelim As ObjectClassLibrary.ASLXNA.SpriteOrder In elimsprite
                        OH.VisibleCountersInHex.Remove(spriteelim)
    Next
    End If
    End If
    Return True
    End Function
    private Function CalcCasualtiesDRM(ByVal reveallist As List(Of Integer)) As Boolean
            'called by Me.new
                    'calculates drm for Casualties DR when search reveals real enemy units

    Dim FoundUnitsize As Integer = 0 : Dim CasSize As Integer = 0 : Dim StealthyDRM As Integer = 0
    Dim ldrDRM As Integer = 0 : Dim FinalLdrDrm As Integer = 5 : Dim LdrPres As Boolean = False
    For Each revealunit As Integer In reveallist
    Dim FoundUnit As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = revealunit Select selunit).First
            FoundUnitsize = FoundUnit.BasePersUnit.Unittype
    Select caseFoundUnitsize
    caseConstantvalues.Utype.Squad
                        '+1 for every half squad >1 - this overcounts, correct later
    CasSize += 2
    If FoundUnit.BasePersUnit.CharacterStatus = Constantvalues.CharacterStatus.Stealthy Then StealthyDRM -= 1
    If FoundUnit.BasePersUnit.CharacterStatus = Constantvalues.CharacterStatus.Lax Then StealthyDRM += 1
    caseConstantvalues.Utype.Crew, Constantvalues.Utype.HalfSquad
    CasSize += 1
    If FoundUnit.BasePersUnit.CharacterStatus = Constantvalues.CharacterStatus.Stealthy Then StealthyDRM -= 1
    If FoundUnit.BasePersUnit.CharacterStatus = Constantvalues.CharacterStatus.Lax Then StealthyDRM += 1
    caseConstantvalues.Utype.LdrHero, Constantvalues.Utype.Leader
    ldrDRM += CInt(Game.Linqdata.GetLOBData(Constantvalues.LOBItem.ldrm, CInt(FoundUnit.BasePersUnit.LOBLink)))
    If FinalLdrDrm > ldrDRM Then FinalLdrDrm = ldrDRM
    If reveallist.Count > 1 Then LdrPres = True
    caseElse
    CasSize += 0
    End Select
    Next
    If Not LdrPres Then FinalLdrDrm = 0
            'subtract for first half-squad
    CasualtiesDRM -= (CasSize - 1) + StealthyDRM - FinalLdrDrm
    Return True

    End Function
    End Class
    Public Class PlacingDC
    Implements locationclickactionsi
    private HextoPlace As Integer = 0
    private LoctoPlace As Integer = 0
    private scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Sub New()

    End Sub
    Public Function PlacementOK(ByVal hexnumber As Integer, ByVal controlclick As Integer) As Boolean Implements locationclickactionsi.HandleAction
            'called by MovementC.Processpopup
                    'handles selction of hex/location in which to place DC
                    'follows structure of MoveAllOk method in movement classes
    Dim XNAGph = XNAGraphicsC.GetInstance
    HextoPlace = hexnumber
            LoctoPlace = controlclick
    Dim MFCost As Single = 0 : Dim LocationChange As Integer = 0 : Dim CurrentPosisExitedCrest As Boolean = False
    Dim Recalculate As Boolean : Dim Movehex As Locationi
            'get placing unit
    Dim PlacingUnit As ObjectClassLibrary.ASLXNA.PersUniti = CType(scencolls.SelMoveUnits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
            'calculate moving costs and determine if placement legal
    If HextoPlace = PlacingUnit.BasePersUnit.Hexnum Then
    If LoctoPlace = PlacingUnit.BasePersUnit.hexlocation Then 'only Japs can place in own location
    If Not PlacingUnit.BasePersUnit.Nationality = Constantvalues.Nationality.Japanese Then Return False
    MFCost = 1
    Else
            Movehex = New Locationc(HextoPlace, Constantvalues.UMove.DoPlaceDC)
                    'wrap with decorators
                            'Movehex = New HexsideImpactc(Movehex, movementoptionclickedvalue)
    Movehex = New ScenTerImpactc(Movehex, Constantvalues.UMove.DoPlaceDC)
    Movehex = New WeatherImpactc(Movehex)
                    'this will cascade down and back up the wrappers
    MFCost = Movehex.EntryCost(HextoPlace)
    End If
    Else
            Movehex = New Locationc(HextoPlace, Constantvalues.UMove.DoPlaceDC)
                ' check move legal - there are likely to be a number of possible checks, class out each one to respect SRP, access via LegalMovei interface
    Dim LegalCheckunit As ObjectClassLibrary.ASLXNA.PersUniti = CType(scencolls.SelMoveUnits.Item(0), ObjectClassLibrary.ASLXNA.PersUniti)
    If LegalCheckunit.BasePersUnit.hexlocation >= Constantvalues.Location.Roof And LegalCheckunit.BasePersUnit.hexlocation <= Constantvalues.Location.Building3rdLevel Then
    Dim UpperLevelCheck As LegalMovei
    UpperLevelCheck = New UpperlevelLegalC(HextoPlace, Constantvalues.UMove.DoPlaceDC)
    If Not UpperLevelCheck.IsMovementLegal Then
                        MessageBox.Show("Not a legal move at upper level")
    Return False
    Else
                        'no position change
    LocationChange = UpperLevelCheck.LocationChange
    End If
    ElseIf LegalCheckunit.BasePersUnit.hexlocation = Constantvalues.Location.Cellar Or LegalCheckunit.BasePersUnit.hexlocation = Constantvalues.Location.BunkUnder Then
    Dim LowerLevelCheck As LegalMovei
    LowerLevelCheck = New LowerLevelLegalC(HextoPlace, Constantvalues.UMove.DoPlaceDC)
    If Not LowerLevelCheck.IsMovementLegal Then
                        MessageBox.Show("Not a legal move at lower level")
    Return False
    Else
                        'no position change
    LocationChange = LowerLevelCheck.LocationChange
    End If
                    'may need to add more elseif to handle other location situations and movementoptions that impact what location clicked in new hex
    Else
    Dim MoveNewCheck As LegalMovei
    MoveNewCheck = New MovementNewLegalC(HextoPlace, Constantvalues.UMove.DoPlaceDC)
    If Not MoveNewCheck.IsMovementLegal Then
                        MessageBox.Show(MoveNewCheck.Returnstring)
    Return False
    Else
                        'if currently at ground level then have clicked ground level of new hex - even if moving uphill or INTO depression
    If MoveNewCheck.Returnstring <> "" Then MessageBox.Show(MoveNewCheck.Returnstring)
    LocationChange = Movehex.Location
    If (LegalCheckunit.BasePersUnit.hexPosition >= Constantvalues.AltPos.ExitedCrest1 And LegalCheckunit.BasePersUnit.hexPosition <= Constantvalues.AltPos.ExitedCrest6) Then
    CurrentPosisExitedCrest = True 'if ismovementlegal is true then must be exited crest
    End If
    End If
    End If
                'handle special cases: where bridge is inherent and moving beneath bridge or bridge is ScenFeature and entering bridge
                        'via UsingOTImpactc
                        'wrap with decorators
    Movehex = New HexsideImpactc(Movehex, Constantvalues.UMove.DoPlaceDC)
    Movehex = New ScenTerImpactc(Movehex, Constantvalues.UMove.DoPlaceDC)
    Movehex = New WeatherImpactc(Movehex)
                'if CurrentPosIsExitedCrest then unit is not at the Depression level and so is not moving uphill when leaving the hex
    Movehex = New Uphillc(Movehex, CurrentPosisExitedCrest)
                'this will cascade down and back up the wrappers
    MFCost = Movehex.EntryCost(PlacingUnit.BasePersUnit.Hexnum) + Movehex.HexsideEntryCost
    End If
            'Determine if move is affordable
    Dim HoldMFUsed As Single = 0
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
            MessageBox.Show("Trying to place DC in " & GetLocs.GetnamefromdatatableMap(HextoPlace) & " . . . which will cost " & MFCost.ToString & " MF")
    Do
            Recalculate = False
                'Now do road bonus check
    If PlacingUnit.MovingPersUnit.UsingRoadBonus Then
                    MessageBox.Show("No longer moving on road. -1 MF ", "Forfeiting Road Bonus")
    HoldMFUsed = PlacingUnit.MovingPersUnit.MFUsed
            Recalculate = True
    End If
    If MFCost > PlacingUnit.MovingPersUnit.MFAvailable Then
                    'move is not affordable, exit move
                            MessageBox.Show(Trim(PlacingUnit.BasePersUnit.UnitName) & " does not have enough MF to enter " & GetLocs.GetnamefromdatatableMap(HextoPlace))
    Movehex = Nothing
    Return False
    ElseIf MFCost = PlacingUnit.MovingPersUnit.MFAvailable And PlacingUnit.MovingPersUnit.AssaultMove = Constantvalues.MovementStatus.AssaultMoving Then
                    'unit cannot use all its MF when assault moving
                            MessageBox.Show(Trim(PlacingUnit.BasePersUnit.UnitName) & " is assault moving and place a DC in " & GetLocs.GetnamefromdatatableMap(HextoPlace) & " as it requires all its remaining MF")
    Movehex = Nothing
    Return False
    End If
    Loop Until Recalculate = False
    If Recalculate Then
                'this resets the decorator process to create a new Stack with revised MF and adjusts for movement so far
                        'Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
                        Game.Scenario.DoMove.ConcreteMove.RedoMovementStack(Constantvalues.UMove.DoPlaceDC)
    End If
            'if this far, then action is affordable
                    'check for illegal terrain - beyond non-ADJACENT - none identified yet

    PlacingUnit.MovingPersUnit.MFAvailable -= MFCost
    PlacingUnit.MovingPersUnit.MFUsed += MFCost
            'store location of placed DC for use in PlaceDC.moveupdate
    XNAGph.DisplayShade.SelectedHexloc = New CombatTerrainClassLibrary.ASLXNA.HexAndLocHolder(HextoPlace, LoctoPlace)
            'set this value to enable draw to stop and PlaceDC.MoveUpdate to be called by MoveGraphics.MoveItem
    XNAGph.DisplayShade.HexesToSearch = 0
    Return True
    End Function

    Public Property CasualtiesFlag As Boolean Implements locationclickactionsi.CasualtiesFlag

    Public Property CasualtiesDRM As Integer Implements locationclickactionsi.CasualtiesDRM
    End Class
    Public Class SniperLocationC
    Implements locationclickactionsi
    Public Function finishsniper(ByVal hexnum As Integer, ByVal Passcontroller As Integer) As Boolean Implements locationclickactionsi.HandleAction
            'called by ASLGame.aslxna.ClickLeftShadeC
                    'handles selection of sniper hex from multiple possible hexes (which are shaded)
                    'follows structure of SniperActionsC - starts with click on target hex on map

    Dim Sniperstring As String = "" : Dim Sellist As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
            'clear locationshades
    Game.Scenario.ShaderToShow = 0
            Game.HexesToShade.Clear()
            'reset sniper class
    Dim Convert = New UtilClassLibrary.ASLXNA.ConversionC
    Dim SniperNat = Convert.SniperCountertoNationality(Passcontroller)
    Dim SniperUsing = Convert.SniperAorB(Passcontroller)
            'new sniper class
    Dim Sniper As SniperClassLibrary.aslxna.Sniperi = New SniperClassLibrary.aslxna.SniperC(Game.Scenario.ScenID)
            'set sniper values
    Sniper.sniperlocation = 0
    Sniper.Sniperside = SniperNat
    Dim AttackDR As Integer = 0
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
            Sniper.ResetSide(SniperUsing, hexnum, AttackDR)
            'resume processing, using location choice
            Sniper.ChooseLocationInHex(Game)
            'store results
    Sniperstring = Sniper.Sniperstatusstring
    If Not (Trim(Sniper.Sniperstatusstring) = "") Then Game.ResultsToDraw.Add(Sniperstring)
            'if multiple locations in hex, need to display options via hover and have user select
    If Sniper.SelectHoverToDraw.Count > 0 Then
    Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
    Return True ' processing completed; restart sniper class after user choice made
    End If
            'only one location so continue to unit selection
    If Sniper.sniperlocation > 0 Then
                'select target
    Sellist = Sniper.DoRandomSelection(Game, False)
            'store result
    If Not (Trim(Sniper.Sniperstatusstring) = "") Then Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
    End If
            'if one target selected, proceed to resolution
    If Not IsNothing(Sellist) AndAlso Sellist.Count = 1 Then
                'resovle attack
                        Sniper.AttackResolution()
                        'store results
    Dim SniperAttackstring As String
    If Sellist.Item(0).BasePersUnit.Unit_ID = Sniper.SniperTarget.BasePersUnit.Unit_ID Then
    SniperAttackstring = Trim(Sniper.SniperTarget.BasePersUnit.UnitName) & Sniper.SniperTarget.TargetPersUnit.CombatResultString
            Else
    SniperAttackstring = Sniper.SniperTarget.TargetPersUnit.CombatResultString
    End If
                Game.ResultsToDraw.Add("Target selected: " & SniperAttackstring)
            Game.ResultsToDraw.Add("")
    ElseIf Not IsNothing(Sellist) AndAlso Sellist.Count > 1 Then
                'if multiple targets, show as hover for user selection
                        Sniper.ShowSelectedPossibleTargets(Sellist, Game)
    If Sniper.SelectHoverToDraw.Count > 0 Then Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
                Game.ResultsToDraw.Add("Sniper chooses Target")
    Return True
    ElseIf IsNothing(Sellist) AndAlso Sniper.SelectHoverToDraw.Count > 0 Then
    Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
    Return True
    End If
            'Move sniper counter to new hex
    Dim SniperToMove As Integer = 0
    For Each SniperInUse As SniperClassLibrary.aslxna.SniperInfoC In Sniper.SniperInfoList
    If SniperInUse.SniperIsAttacking Then
    SniperToMove = SniperInUse.SniperID
    Exit For
    End If
    Next
            'update databse
                    Sniper.UpdateSniperInDatabase()
                    'redraw sprites on map in original sniper counter hex
    Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(Sniper.OriginalSniperhex), VisibleOccupiedhexes)
            OH.GetAllSpritesInHex()
                    OH.RedoDisplayOrder()
                    'show results
                    Game.SniperToDraw.Remove(Game.SniperToDraw.Item(0))
                    Game.ResultsToDraw.Add("")
                    'redraw target hex to show attack impact
    OH = CType(Game.Scenario.HexesWithCounter(Sniper.Sniperhex), VisibleOccupiedhexes)
            OH.GetAllSpritesInHex()
            OH.RedoDisplayOrder()
    Return True
    End Function

    Public Property CasualtiesFlag As Boolean Implements locationclickactionsi.CasualtiesFlag

    Public Property CasualtiesDRM As Integer Implements locationclickactionsi.CasualtiesDRM
    End Class
    Public Interface HoverActionsi
        'This interface defines the structure for all classes that deal with "hover" items
                'these are sprites that do not scroll with the map and are used to convey information (hex contents)
                'or to enable user selection (sniper choice of targets)
    Sub HandleAction(ByVal itemid As Integer)
    End Interface
    Public Class SniperActionsc
    Implements HoverActionsi
    Public Sub HandleAction(ByVal ItemID As Integer) Implements HoverActionsi.HandleAction
            'called by ASLGame.aslxna.ClickLeftSelectHoverC
                    'processes click on sniper hover counter to trigger sniper resolution
                    'follows same structure as SniperLocationC  - starts with click on Sniper hover counter
    Dim XNAGph = XNAGraphicsC.GetInstance
    Dim Sellist As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
    Dim Sniperside As Integer = 0 : Dim Locationstring As String = "" : Dim Sniperstring As String = ""
            'ensure snipers resolved in proper order
    If ItemID > 1 Then
                MessageBox.Show("You MUST resolve sniper attacks in order: click on first counter", "A14.1 Sniper Activation")
    Exit Sub
    End If
            'retrieve sniper info
    Sniperside = Game.SniperToDraw.Item(0).ItemID
            'process sniper attack
    Dim Sniper As SniperClassLibrary.aslxna.Sniperi = New SniperClassLibrary.aslxna.SniperC(Game.Scenario.ScenID)
            'determine if a sniper attack takes place
            Sniper.SniperCheck()
    If Sniper.dr <= 2 Then
    If Sniper.IsPinned(Sniperside) Then
                    Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
            Game.ResultsToDraw.Add("")
            'clear the sniper hover
            Game.SniperToDraw.Remove(Game.SniperToDraw.Item(0))
    If Game.Scenario.IFT.NeedToResumeResolution Then Game.Scenario.IFT.ResumeCombatResolution()
    Exit Sub 'no sniper
    End If
                'attack takes place so select and move a sniper counter
                        Sniper.SelectandMoveSniperCounter(Sniperside)
    Dim SniperHex As String = GetLocs.GetnamefromdatatableMap(Sniper.OriginalSniperhex)
                'store results
    Sniperstring = "DR=" & Sniper.dr.ToString & " Sniper activated in " & SniperHex
                Game.ResultsToDraw.Add(Sniperstring)
            Game.SniperToDraw.Item(0).ItemName = "Sniper" & Sniper.dr.ToString
            Else
                Game.ResultsToDraw.Add("You rolled a " & Sniper.dr.ToString & " so no sniper")
            Game.ResultsToDraw.Add("")
            'clear the sniper hover
            Game.SniperToDraw.Remove(Game.SniperToDraw.Item(0))
            'sniper can interrupt fire resolution versus a stack. Resume if required
    If Game.Scenario.IFT.NeedToResumeResolution Then Game.Scenario.IFT.ResumeCombatResolution()
    Exit Sub 'no sniper
    End If
    Sniperstring = Sniper.Sniperstatusstring
            Game.ResultsToDraw.Add(Sniperstring)
            'select final sniper hex where attack will take place
            Sniper.SelectFinalSniperhex()
    If Not (Trim(Sniper.Sniperstatusstring) = "") Then
            Sniperstring = Sniper.Sniperstatusstring
                Game.ResultsToDraw.Add(Sniperstring)
    End If
    If Sniper.MultipleTargetHexes.Count > 1 Then  'must chose between multiple hexes using snipershade
            'draw the hex shades
    Game.Scenario.ShaderToShow = Constantvalues.ShadeShow.SniperShade
    Game.ShaderDrawStart = True
    For Each SniperInUse As SniperClassLibrary.aslxna.SniperInfoC In Sniper.SniperInfoList
    If SniperInUse.SniperIsAttacking Then
                        XNAGph.DisplayShade.SniperShadeShow(Sniper.MultipleTargetHexes, SniperInUse.SniperID)
    Exit For
    End If
    Next
    Game.ShaderDrawStart = False
    Exit Sub ' processing completed; restart sniper class after user choice made
    Else
    If Not Sniper.UseLocation Then
                    'if multiple locations possible; user gets to choose user hover shade
                            Sniper.ChooseLocationInHex(Game)
    Sniperstring = Sniper.Sniperstatusstring
    If Not (Trim(Sniper.Sniperstatusstring) = "") Then Game.ResultsToDraw.Add(Sniperstring)
    If Sniper.SelectHoverToDraw.Count > 0 Then
    Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
    Exit Sub ' processing completed; restart sniper class after user choice made
    End If
    End If
    If Sniper.sniperlocation > 0 Then
                    'one location so now select target by random selection
    Sellist = Sniper.DoRandomSelection(Game, False)
    If Not (Trim(Sniper.Sniperstatusstring) = "") Then Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
    End If
    If Not IsNothing(Sellist) AndAlso Sellist.Count = 1 Then
                    'if one unit selected then resolve attack
                            Sniper.AttackResolution()
    Dim SniperAttackstring As String
    If Sellist.Item(0).BasePersUnit.Unit_ID = Sniper.SniperTarget.BasePersUnit.Unit_ID Then
    SniperAttackstring = Trim(Sniper.SniperTarget.BasePersUnit.UnitName) & Sniper.SniperTarget.TargetPersUnit.CombatResultString
            Else
    SniperAttackstring = Sniper.SniperTarget.TargetPersUnit.CombatResultString
    End If
                    Game.ResultsToDraw.Add("Target selected: " & SniperAttackstring)  '& Sellist.Item(0).BasePersUnit.UnitName & Trim(Sellist.Item(0).TargetPersUnit.CombatResultString))
    ElseIf Not IsNothing(Sellist) AndAlso Sellist.Count > 1 Then
                    'if multiple targets selected, user chooses via hover
                            Sniper.ShowSelectedPossibleTargets(Sellist, Game)
    If Sniper.SelectHoverToDraw.Count > 0 Then Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
                    Game.ResultsToDraw.Add("Sniper chooses Target")
    Exit Sub
    ElseIf IsNothing(Sellist) AndAlso Sniper.SelectHoverToDraw.Count > 0 Then
                    '??
    Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
    Exit Sub
    End If
                'Move sniper counter to new hex
    Dim SniperUsing As Integer = 0
    For Each SniperInUse As SniperClassLibrary.aslxna.SniperInfoC In Sniper.SniperInfoList
    If SniperInUse.SniperIsAttacking Then
    SniperUsing = SniperInUse.SniperID
    Exit For
    End If
    Next
                'update database
                        Sniper.UpdateSniperInDatabase()
                        'redraw hex with initial sniper counter
    Dim OH As VisibleOccupiedhexes = CType(Game.Scenario.HexesWithCounter(Sniper.OriginalSniperhex), VisibleOccupiedhexes)
                OH.GetAllSpritesInHex()
                        OH.RedoDisplayOrder()
                        Game.SniperToDraw.Remove(Game.SniperToDraw.Item(0))
                        Game.ResultsToDraw.Add("")
                        'redraw target hex to show attack impact
    OH = CType(Game.Scenario.HexesWithCounter(Sniper.Sniperhex), VisibleOccupiedhexes)
    If IsNothing(OH) Then
                    Game.Scenario.HexesWithCounter.Add(Sniper.Sniperhex, New VisibleOccupiedhexes(Sniper.Sniperhex))
    OH = CType(Game.Scenario.HexesWithCounter(Sniper.Sniperhex), VisibleOccupiedhexes)
    End If
                OH.GetAllSpritesInHex()
                        OH.RedoDisplayOrder()
    End If
    If Game.Scenario.IFT.NeedToResumeResolution Then Game.Scenario.IFT.ResumeCombatResolution()
    End Sub


    End Class
    Public Class selectchoicec
    Implements HoverActionsi

    Public Sub HandleAction(itemid As Integer) Implements HoverActionsi.HandleAction
            'called by ASLGame.aslxna.ClickLeftSelectHoverC
                    'processes click on sniper hover counter to trigger sniper resolution
                    'follows same structure as SniperActionsC - starts with click on location/unit counter in hover

    Dim Sellist = New List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    Dim Selitem As ObjectClassLibrary.ASLXNA.PersUniti
    Dim OH As VisibleOccupiedhexes
    Dim Convert = New UtilClassLibrary.ASLXNA.ConversionC
    Dim Hovertouse As ObjectClassLibrary.ASLXNA.HoverItem = (From Qu As ObjectClassLibrary.ASLXNA.HoverItem In Game.SelectHoverToDraw Where Qu.ItemID = itemid Select Qu).First
    Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Game.Scenario.LocationCol)
            'restart SniperC
    Dim Sniper As SniperClassLibrary.aslxna.Sniperi = New SniperClassLibrary.aslxna.SniperC(Game.Scenario.ScenID)
    Dim AttackDR As Integer = 0
    If Hovertouse.Controller >= Constantvalues.Sniper.GermanA And Hovertouse.Controller <= Constantvalues.Sniper.PartisanB Then  'doing sniper activity
            'have clicked on location hover
    Dim SniperNat = Convert.SniperCountertoNationality(Hovertouse.Controller)
    Dim SniperUsing = Convert.SniperAorB(Hovertouse.Controller)
                'set sniper location
    Sniper.sniperlocation = itemid
    Sniper.Sniperside = SniperNat
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
    If Hovertouse.ItemPosition = 0 Then 'clicked on location
    Dim LocToUse = GetLocs.RetrieveLocationfromMaptable(Hovertouse.ItemID)
    Sniper.sniperlocation = itemid
                    Sniper.ResetSide(SniperUsing, LocToUse.Hexnum, AttackDR)
    Dim SniperChoiceAlreadyShown As Boolean = False
    Try
    Dim LayerTest As ObjectClassLibrary.ASLXNA.HoverItem = (From Qu As ObjectClassLibrary.ASLXNA.HoverItem In Game.SelectHoverToDraw Where Qu.ItemLayer > 0 Select Qu).First
            Catch
    SniperChoiceAlreadyShown = True
    End Try
                    'select target unit in location
    Sellist = Sniper.DoRandomSelection(Game, SniperChoiceAlreadyShown)
    If Not (Trim(Sniper.Sniperstatusstring) = "") Then Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
    If IsNothing(Sellist) AndAlso Not SniperChoiceAlreadyShown Then
                        'need to show multiple possible targets for user choice via hover
    If Sniper.SelectHoverToDraw.Count > 0 Then Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
    Exit Sub
    ElseIf Not IsNothing(Sellist) AndAlso Sellist.Count = 1 Then
                        'one target selected, proceed to resolution
    Selitem = Sniper.AddActualTarget(Sellist.Item(0).BasePersUnit.TypeType_ID, Sellist.Item(0).BasePersUnit.Unit_ID)
            Sniper.AttackResolution()
            Game.ResultsToDraw.Add("Target selected: " & Trim(Sniper.SniperTarget.BasePersUnit.UnitName) & Sniper.SniperTarget.TargetPersUnit.CombatResultString) 'Sellist.Item(0).BasePersUnit.UnitName & Trim(Sellist.Item(0).TargetPersUnit.CombatResultString))
            Game.ResultsToDraw.Add("")
    ElseIf Not IsNothing(Sellist) AndAlso Sellist.Count > 1 Then
                        '
                                Sniper.ShowSelectedPossibleTargets(Sellist, Game)
    If Sniper.SelectHoverToDraw.Count > 0 Then Game.SelectHoverToDraw = Sniper.SelectHoverToDraw
                        Game.ResultsToDraw.Add("Sniper chooses Target")
    Exit Sub
    End If

    Else  'clicked on counter
            'set as target if sniper, ce veh crew or unarmoured veh -NEED TO PROGRAM VEHICLE CHOICES AUG 14
    If Hovertouse.Controller >= Constantvalues.Sniper.GermanA AndAlso Hovertouse.Controller <= Constantvalues.Sniper.PartisanB Then
                        'sniper counter selected as target
    Dim TargetSnipercounter As Integer = itemid
    Dim TargetAorB As String = Convert.SniperAorB(TargetSnipercounter)
                        'set sniper location
    Dim ComFunc = New UtilWObj.ASLXNA.CommonFunctions(Game.Scenario.ScenID)
    Dim loctouse As MapDataClassLibrary.GameLocation = ComFunc.GetSniperLocation(SniperNat, TargetAorB)
    Sniper.sniperlocation = loctouse.Hexnum
                        Sniper.ResetSide(SniperUsing, loctouse.Hexnum, AttackDR)
            Game.ResultsToDraw.Add("Target selected: " & "Enemy Sniper")
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
                        'resolve attack
                                Sniper.ResolveAttackversusEnemySniper(AttackDR, TargetSnipercounter)
            Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
    End If
    End If
    ElseIf Hovertouse.Controller = Constantvalues.Typetype.Personnel Or Hovertouse.Controller = Constantvalues.Typetype.Concealment Then
                'have clicked on unit hover
    Dim Dice = New UtilClassLibrary.ASLXNA.DiceC
                'reset sniper values
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
    Dim SniperNat = Game.SniperToDraw.Item(0).ItemID
    Dim Sniperusing = Convert.SniperAorB(Game.SniperToDraw.Item(0).Controller)
    Selitem = Sniper.AddActualTarget(Hovertouse.Controller, Hovertouse.ItemID)
    Sniper.sniperlocation = Sniper.SniperTarget.BasePersUnit.LOCIndex
    Sniper.Sniperside = SniperNat
                Sniper.ResetSide(Sniperusing, Sniper.SniperTarget.BasePersUnit.Hexnum, AttackDR)
            Sellist.Add(Selitem)
            'attacked selected item
            Sniper.AttackResolution()
            'store results
    Dim SniperAttackstring As String
    If Sellist.Item(0).BasePersUnit.Unit_ID = Sniper.SniperTarget.BasePersUnit.Unit_ID Then
    SniperAttackstring = Sniper.SniperTarget.BasePersUnit.UnitName & Sniper.SniperTarget.TargetPersUnit.CombatResultString
            Else
    SniperAttackstring = Sniper.SniperTarget.TargetPersUnit.CombatResultString
    End If
                Game.ResultsToDraw.Add("Target selected: " & SniperAttackstring)
            'hover items reflect multiple selections in random selection routine; so need to see if any of these are attacked
    For Each Hovertocheck As ObjectClassLibrary.ASLXNA.HoverItem In Game.SelectHoverToDraw
    If Hovertocheck.ItemID = itemid Then Continue For
    Dim ATDR As Integer = Dice.Dieroll
                    Game.ResultsToDraw.Add("Additional Target dr is a " & ATDR.ToString)
    If ATDR < 3 Then
                        'hover is attacked by sniper
    If Hovertocheck.Controller = Constantvalues.Typetype.Personnel Or Hovertocheck.Controller = Constantvalues.Typetype.Concealment Then
                            'hover is unit
    Selitem = Sniper.AddActualTarget(Hovertocheck.Controller, Hovertocheck.ItemID)
            Game.ResultsToDraw.Add(Selitem.BasePersUnit.UnitName & " is attacked by Sniper")
            'resolve attack
            Sniper.AttackResolution()
            Game.ResultsToDraw.Add(Sniper.SniperTarget.TargetPersUnit.CombatResultString)  '"Target selected: " & SniperAttackstring)
    Else
                            'hover is sniper counter
                                    Game.ResultsToDraw.Add("Target selected: " & "Enemy Sniper")
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
                            'resolve attack
                                    Sniper.ResolveAttackversusEnemySniper(AttackDR, Hovertocheck.ItemID)
            Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
    End If
    Else
                        Game.ResultsToDraw.Add(Hovertocheck.ItemName & " is NOT attacked by Sniper")
    End If
    Next
    ElseIf Hovertouse.Controller = Constantvalues.Feature.Sniper Then
                'have clicked on eneny sniper as target
    Dim Dice = New UtilClassLibrary.ASLXNA.DiceC
    Dim SniperAttackstring As String
                Game.ResultsToDraw.Add("Target selected: " & "Enemy Sniper")
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
                'resolve attack
                        Sniper.ResolveAttackversusEnemySniper(AttackDR, Hovertouse.ItemID)
            Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
            'hover items reflect multiple selections in random selection routine; so need to see if any of these are attacked
    For Each Hovertocheck As ObjectClassLibrary.ASLXNA.HoverItem In Game.SelectHoverToDraw
    If Hovertocheck.ItemID = itemid Then Continue For
    If Dice.Dieroll < 3 Then
                        'hover is attacked by sniper
    If Hovertocheck.Controller = Constantvalues.Typetype.Personnel Or Hovertocheck.Controller = Constantvalues.Typetype.Concealment Then
                            'hover is unit
    Selitem = Sniper.AddActualTarget(Hovertocheck.Controller, Hovertocheck.ItemID)
            Game.ResultsToDraw.Add("Additional Target selected: " & Selitem.BasePersUnit.UnitName)
            'resolve attack
    Selitem = Sniper.AddActualTarget(Hovertouse.Controller, Hovertouse.ItemID)
            Sniper.AttackResolution()
    If Sellist.Item(0).BasePersUnit.Unit_ID = Sniper.SniperTarget.BasePersUnit.Unit_ID Then
    SniperAttackstring = Sniper.SniperTarget.BasePersUnit.UnitName & Sniper.SniperTarget.TargetPersUnit.CombatResultString
            Else
    SniperAttackstring = Sniper.SniperTarget.TargetPersUnit.CombatResultString
    End If
                            Game.ResultsToDraw.Add("Target selected: " & SniperAttackstring)
    Else
                            'hover is sniper counter
                                    Game.ResultsToDraw.Add("Target selected: " & "Enemy Sniper")
    If Trim(Game.SniperToDraw.Item(0).ItemName) = "Sniper1" Then AttackDR = 1 Else AttackDR = 2
                            'resolve attack
                                    Sniper.ResolveAttackversusEnemySniper(AttackDR, Hovertocheck.ItemID)
            Game.ResultsToDraw.Add(Sniper.Sniperstatusstring)
    End If
    Else
                        Game.ResultsToDraw.Add(Hovertocheck.ItemName & " is NOT attacked by Sniper")
    End If
    Next
    End If

            '?? NEEDED
    If Sniper.OriginalSniperhex = 0 Then

    End If

            'update database
                    Sniper.UpdateSniperInDatabase()
                    'redraw hex initially containing sniper counter
    OH = CType(Game.Scenario.HexesWithCounter(Sniper.OriginalSniperhex), VisibleOccupiedhexes)
            OH.GetAllSpritesInHex()
            OH.RedoDisplayOrder()
            Game.SniperToDraw.Remove(Game.SniperToDraw.Item(0))
            Game.ResultsToDraw.Add("")
            'redraw target hex to show attack impact
    OH = CType(Game.Scenario.HexesWithCounter(Sniper.Sniperhex), VisibleOccupiedhexes)
            OH.GetAllSpritesInHex()
            OH.RedoDisplayOrder()
            'clear the hover to stop drawing it
            Game.SelectHoverToDraw.Clear()
    End Sub
    End Class*/

