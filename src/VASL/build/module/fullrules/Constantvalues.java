package VASL.build.module.fullrules;

/**
 * Created by dougr_000 on 7/16/2017.
 */
public class Constantvalues {
    public static enum ShadeShow {LOSShade, SearchShade, PlaceDCShade, DFFShade, IFTShade, CanSearch, CanNotSearch, Searched, SniperShade}
    public static enum LosStatus {PointBlank, NormalRange, LongRange, BeyondLR, None, Target}
    public static enum AltPos {AboveWire, WallAdv, InFoxhole, InTrench, OtherTerrainInHex, InSanger, AbovePanji, OnRoad, CrestStatus1,
        CrestStatus2, CrestStatus3, CrestStatus4, CrestStatus5, CrestStatus6, ExitedEntrench, ExitedCrest1, ExitedCrest2, ExitedCrest3,
        ExitedCrest4, ExitedCrest5, ExitedCrest6, Rider, Passenger, WACrestStatus1, WACrestStatus2, WACrestStatus3, WACrestStatus4,
        WACrestStatus5, WACrestStatus6, None}
    public static enum VisibilityStatus {Hidden, Concealed, Revealed, Visible}
    public static enum CombatStatus {Firing, PrepFirer, OppFirer, FirstFirer, FinalFirer, AdvFirer, SubsequentFirstFiring, Melee, None}
    public static enum MovementStatus {Moved, TI, Waded, AssaultMoving, Moving, Wading, AssaultMoved, FirstDash, SecondDash, Dashed, Labour1, Labour2, Advanced, Connecting, NotMoving}
    public static enum OrderStatus {GoodOrder, Berserk, Prisoner, Unarmed, Broken, Broken_DM, Disrupted, DisruptedDM, KIAInf, NotInPlay}
    public enum FortitudeStatus {Normal, Fanatic, Encircled, Wounded, Fan_Enc, Fan_Wnd, Fan_Wnd_Enc, Enc_Wnd, Heroic, HeroicFanatic, HeroicEncircled, HeroicWounded, HeroicFan_Enc,
        HeroicFan_Wnd, HeroicFan_Wnd_Enc, HeroicEnc_Wnd, None}
    public enum RoleStatus {GuardUnit, Passenger, Rider, None}
    public enum Typetype{Personnel, Vehicle, Concealment, SW, Gun, WhiteC, Location, Feature, AltPos, VisHind}
    public enum Utype{Squad, HalfSquad, Crew, Hero, LdrHero, Leader, THHero, PathFind, Commissar, MMC, SMC, Dummy, Units, None}
    public enum LOBItem{MORALELEVEL, UNITCLASS, BPV, SMOKE, FIREPOWER, GETRANGE, UNITTYPE, ASSAULTFIRE, REDUCESTO, REDTO, LOBCLSID, SUBSTITUTESTOTO, SUBTO,
        HARDENSTO, HARDTO, ELR5, LDRGRADE, LDRM, IMAGEUNIT, IMAGEBRKUNIT, PP, DMPP, SWIMAGE, BROKENSWIMAGE, DMSIMAGE, BROKENDMSWIMAGE, ROF}
    public enum CharacterStatus {STEALTHY, NONE, LAX}
    public enum UClass {FIRSTLINE, SECONDLINE, GREEN, CONSCRIPT, ELITE, SS, ENGINEER, AIRBORNE, MARINE, CREWCLASS, PARTISAN, MARINEENG, UNARMED,
        SFIRSTLINE, SSECONDLINE, SGREEN, SCONSCRIPT, SELITE, SSS, SENGINEER, SAIRBORNE, SMARINE, SCREWCLASS, SPARTISAN, SMARINEENG, SUNARMED, ASFIRSTLINE,
        ASSECONDLINE, ASGREEN, ASCONSCRIPT, ASELITE, ASSS, ASENGINEER, ASAIRBORNE, ASMARINE, ASCREWCLASS, ASPARTISAN, ASMARINEENG, ASUNARMED,
        AFIRSTLINE, ASECONDLINE, AGREEN, ACONSCRIPT, AELITE, ASS, AENGINEER, AAIRBORNE, AMARINE, ACREWCLASS, APARTISAN, AMARINEENG, AUNARMED, NONE}
    public enum Hexrole {Firer, Intervening, Target, FirerInt, TargetInt, FirerTarget, FirerTargetInt}
    public enum Phase{Setup, Rally, PrepFire, Movement, DefensiveFire, AdvancingFire, Rout, Advance, CloseCombat, Refitphase}
    public enum ScenarioAction{EnterIntoNewPhase, JoinPhase, ExitfromPhaseForward, ExitfromPhaseBack, LeavePhase}
    public enum WhoCanDo {Attacker, Defender, Both, None}
    public enum Map{bd1a, bd1b, bd2a, bd2b, bd3a, bd3b, bd4a, bd4b, bd5a, bd5b, bd6a, bd6b, bd7a, bd7b, bd8a, bd8b, bd9a, bd9b, bdCreateMap, bdStoumont, bdLaGleize,
        bdCheneux, bdRedB, bdCrete, bdBalta, bdBurma, bdPhillipines, bdPegasusBridge, bdBloodReef, bdPrimosole, bdVotG}
    public enum Rules {SingleScenario, KGPCampaign, RedBCampaign, PlatoonLCampaign, PegasusCampaign, BloodReefcampaign, PrimosoleCampaign, KGPScenario,
        RedBScenario, PlatoonLScenario, PegasusScenario, BloodReefScenario, PrimosoleScenario, VotGCampaign, VotGScenario}
    public enum DayNight {Day, AM, PM, NIGHT, Dawn, Dusk}
    public enum Nationality {Yanks, Russians, Germans, British, French, Japanese, Italians, Finns, Partisans, Gurkhas, Commonwealth, Axisminor, Alliedminor, Chinese, Hungarians, Poles, None}
    public enum CombatSel{None, InhandFirstMG, InhandSecondMG, BothMG, FirstMG, SecondMG, InhOnly, ShowMenu}
    public enum SWStatus{None, GoodOrderSW, Brokendown, Dismantled, Dis_Broken, Malfunctioned, DCPlaced, Used, Eliminated}
    public enum SWtype{None, LMGun, MMGun, HMGun, H50cal, AnyMG, OBRadio, OBPhn, FThr, DemoC, BazK, Mortar, ATR, PIAT, PSK, Lahti, Rclr, InfG, MolP, SetDC}
    public enum IFTResult{KIA7, KIA6, KIA5, KIA4, KIA3, KIA2, KIA1, K4, K3, K2, K1, MC4, MC3, MC2, MC1, NMC, PTC, NR, Broken, KIA, CR}
    public enum IFTdrm{FFMO, FFNAM, HA, Terrain, LOSH, HexHind, TargHasFT, Hexside, Feature, Leader, Hero, FirerCX, FirerEnc, TargLOSH, VisLoSH, VehWrkTEM, VehWrkLOSH}
    public enum LOS{HexGrain, AltHexGrain, VertHexGrain, NoHexGrain, HorizontalHexGrain, Is60}
    public enum Location {Ocean, OpenGround, River, Marsh, Brush, Streamshallow, Ford, PineWoods, Woods, Crag, Airfield, Mudflats, Orchard, OrchardPavedRoad, OrchardUnpavedRoad,
        PavedRoad, UnpavedRoad, TrailWoods, TrailBrush, TrailPineWoods, WoodsPavedRoad, WoodsUnpavedRoad, PineWoodsPavedRoad, PineWoodsUnpavedRoad, GullyBrush, GullyWoods,
        Graveyard, SunkenPavedRoad, OrchardStreamshallow, StreamWoodsshallow, StreamPineWoodsshallow, StreamBrushshallow, SunkenUnpvRoad, StratLoc, Grainfield, Gully,
        WoodRubble, StoneRubble, WoodDebris, StoneDebris, Shellhole, Jungle, DenseJungle,  Bamboo, PalmTrees, Kunai, Swamp, RicePaddiesdrained, RicePaddiesIrrigated, RicePaddiesInSeason,
        Panjis, CaveComplex, Beaches, PalmTreesUnpavedRoad, TrailJungle, TrailDenseJungle, JungleUnpavedRoad, DenseJungleUnpaved, TrailBamboo, Trail, Trailpalmtrees, GullyShell, PalmDebris,
        PalmDebrisPalm, GullyUnpavedBridge, PierNoLoc, SunkenLane, Passage, PavedIntersectionManhole, ElevRoad, Pier, Reef, Tetrahedron, Tetrawire, Vineyard, IrrigDitch, PartOrch, IrDPOrch,
        UnPavedIrDPO, UnPavedIrD, UnPavedPO, PavedPO, GullyPO, PavedIrD, Forest, CactusPatch, OliveGrove, Hillock, HillockSummit, SandDuneH, SandDuneL, Lumberyard, GullyOrchard, Canal, Pond,
        WoodRubbleFallen, StoneRubbleFallen, GrdLRR, RailCar, WreckedRC, GuttedRC, CitySquare, CitySquareShellhole, CitySquareManShell, Fountain, GrdLRRMan, OrchardPvRoadMan, StorageTanks,
        StreamDry, StreamDeep, StreamFlooded, OrchardStreamdry, OrchardStreamdeep, OrchardStreamflooded, StreamWoodsdry, streamwoodsdeep, Streamwoodsflooded, StreamPineWoodsdry, streamPinewoodsdeep,
        StreamPinewoodsflooded, StreamBrushdry, streambrushdeep, Streambrushflooded, Wadi, Hammada, SandTrack,  HammadaTrack, Deir, Track, HillockTrack, DeirTrack, Scrub, ScrubTrack, HillockSummitTrack,
        SandScrub, SandScrubTrack, SandDuneLTrack, SandDuneHTrack, Mausoleum, Camp, DesertCluster, BrkCrag, BrokenGround, SteppeBrush, SteppeWoods, SteppeGrain,  ExcavDitch, BambooPath, JunglePath,
        DenseJunglePath, BeachSlightSoft, BeachModerateSoft, BeachSteepSoft, Sandbar, BeachSlightHard, BeachModerateHard, BeachSteepHard, OceanShallow, ExposedReef, SubmergedReef, JungleDebris,
        CordoroyRoads, StreamShallowJungleDebris,  RiverBrush, BrushUnpavedRd, BrushIrrD, IrrDVineyard, PineForest, CulvertOG, CulvertPvRD, CulStrPvRd, CommandBunker, IslComBunker, GunEmplacement,
        BRTTower, OneMarketStone, OneMarketWood, PBTower, SingleSteeple, OneSteeple, TwoSteeple, PartCol1, PartCol15, Manhole, All, NA, NonStairBldg, HindranceHex, HindranceFeature, Shellholetype,
        Creststatustype, Bypassable, Smoketype, RoadOGtype, Manholetype, Factorytype, Rooflesstype, Cellartype, SplitLeveltype, Buildingtype, Pillboxtype, IntBuildtype, Rubbletype, FortBuildtype,
        bridgetype,  Marshtype,  ShallowStreamtype, DeepStreamtype, WaterObstacletype, Blazetype, towertype, Roadtype, OBAtype, HardSurftype, HasStairs, Burnabletype,  Roof, StoneBuildingGroundlevel,
        StoneBuilding1stLevel, StoneBuilding2ndLevel, StoneBuilding3rdLevel, StoneCellar, WoodBuildingGroundlevel, WoodBuilding1stLevel, WoodBuilding2ndLevel, WoodBuilding3rdLevel, WoodCellar, InCave,  BeneathBridge, Sewer, BeneathPier, Tunnel, Huts, BunkUnder, FortStoneGrd, FortSTone1st, FortStone2nd, FortSTone3rd,
        FortWoodGrd, FortWood1st, FortWood2nd, FortWood3rd,  Pill1571, Pill1572, Pill1573, Pill1574, Pill1575, Pill1576 , Pill1351, Pill1352, Pill1353, Pill1354, Pill1355, Pill1356, Pill2351,
        Pill2352, Pill2353, Pill2354, Pill2355, Pill2356, Pill2571, Pill2572, Pill2573, Pill2574, Pill2575, Pill2576, Pill3351, Pill3352, Pill3353, Pill3354,  Pill3355, Pill3356, Pill3571, Pill3572,
        Pill3573,  Pill3574, Pill3575, Pill3576, Pill2461, Pill2462, Pill2463 , Pill2464, Pill2465, Pill2466, Pill1461, Pill1462, Pill1463, Pill1464, Pill1465, Pill1466, Bombprf,  Cave146, CaveH46,
        Cave146LM1,  CaveH46LM1, Cave146L0, CaveH46L0, Cave146L1, CaveH46L1, Cave146L2, CaveH46L2,  Cave146L3, CaveH46L3, Cave146L4, CaveH46L4, StBr14,  StBr25,  StBr36, WdBr14, WdBr25, WdBr36,
        SStBr14, SStBr25, SStBr36, SWdBr14, SWdBr25, SWdBr36, PoBr14, PoBr25, PoBr36,  FoBr,  WoodsTB, MineTB, StoneRubbleTB, PineWTB, JungleTB , DenseJungleTB, DebrisTB, WoodRubbleTB,
        WoodRubbleFallTB, StoneRubbleFallTB, OneFactRooflessMan}
    public enum Mist {None, VLight, Light, Moderate, Heavy, VHeavy, EHeavy}
    public enum Dust{None, Light, Moderate, Heavy, VHeavy, EHeavy}
    public enum VisHind{VehDust, GreyWP, Flame, HamperedFlame, BlazeWood, BlazeStone, InfWP, InfSmoke, OBASmoke, OBASmokeDisp, GunSmoke, GunSmokeDisp, GreyDisp, GunWP, GunWPDisp, GreyWPDisp, None}
    public enum Feature{None, Foxhole, Wire, entrenchment, FFE1, FFE2, FFEC, RSR, ArtR, AttObjective, DfnObjective, DCHPlaced, BOWreck, Wreck, Trench, Cave, Tunnel, Sanger, Panji, APMines,
        ATMines, AP6, AP8, AP12, APDummy, AT1, AT2, AT3, AT4, AT5, Sniper}
    public enum TerrFactor {None, TEM, LOSHind, Desc, MF, Image, HexsideTEM, Hexsidedesc, HexsideMFcost, HexsideImage, ScenFeature, ObstHeight, RemoveCounter}
    public enum Hexside{GullyUp, GullyDown, GullyUpSlope, GullyDownSlope, GullyUpWire, GullyDownWire, AttWoodsGullyUp, AttWoodsGullyDown, AttPWdsGullyUp, AttPWdsGullyDn, GullyUpHedge, GullyDownHedge,
        GullyUpWall, GullyDownWall, GullyUpTrail, GullyDownTrail, AttPwdsCrestUpGully, AttPWdsCrestDnGully, CrestUpGullyDown, CrestDownGullyUp,AttPWdsCrestUpGullyDn, AttPWdsCrestDnGullyUp, GullyUpSlopeWire,
        GullyDownSlopeWire, GullyUpUnPvRd, GullyDnUnPvRd, GullyUpSlopeHedge, GullyDnSlopeHedge, GullyUpGullyDown, GullyUpGullyDownHedge, CrestUpGullyHedge, CrestDnGullyHedge, GullyDnCLUpHedge, GullyUpCLDnHedge,
        CLUpGullySlopehedge, CLDnGullySlopeHedge, AttWdsCrestUpGullyDn, AttWdsCrestDnGullyUp, CrestUpGullyWire, CrestDnGullyWire, AttWoodsCrestUpGully, AttWoodsCrestDnGully, SlopeUpTrail, SlopeDnTrail,
        CulvertUp,  CulvertDn, CLUpWire, CLDownWire, CLUpWireSlope, CLDnWireSlope, AttachedWoods, AttachedPineWoods, AttPineWCrestUp, AttPineWCrestDown, AttWoodsCrestUp, AttWoodsCrestDown, SlopeUpPWUnpavR,
        SlopeDownPWUnpavR, CrestUpStream, CrestDownStream, CrestUpTrailPWoods, CrestDownTrailPWoods, CrestUpSlopeWire, CrestDownSlopeWire, AttWoodsCrestUpDbl, AttWoodsCrestDownDbl, SlopeUpTrailPWoods,
        SlopeDownTrailPWoods, CLUpNarrowPaved, CLDownNarrowPaved , WoodsStream, CLupdblhedge, CLDowndblHedge, CliffDownHedge, CliffUpHedge, CliffDoubleUPHedge, CliffDoubleDownHedge, CrestDownSlopeUpPvRd,
        CrestUpSlopeDwnPvRd , CrestUpTrail, CrestDownTrail, Cave, TrailBreak, Roadblock1, Roadblock2, Roadblock3, Roadblock4, Roadblock5, Roadblock6, CrestNarrowPaved, CrestUpNarrowPaved, CrestDownNarrowPaved,
        AttPineWCrestUpDbl, AttPineWCrestDnDbl, crestdndblslopehedge, crestupdblslopehedge, CLDownDblslopehedge, CLUpDblSlopehedge, crestdowndblwire, crestupdblwire,  AttWdsCrestDnStream, AttWdsCrestUpStream,
        PineWStream, RiceBank, NoTerrain, Bocage, Hedge, Wall, Wire,  SlopeDown, SlopeUp , CrestUp, CrestDown, PineWoodsPavedRoad, PineWoodsUnpavedRoad, River, Stream, WoodsPavedRoad, WoodsUnpavedRoad,
        PavedRoad,  UnpavedRoad, TrailWoods, TrailBrush, TrailPineWoods, CrestDownPavedRoad, CrestDownUnpavedRoad, CrestUpPavedRoad, CrestUpUnpavedRoad, CrestDownHedge, CrestUpHedge, CrestDownWall,
        CrestUpWall, CrestDownSlope, CrestUpSlope,  CrestDownSlopeHedge, CrestUpSlopeHedge, CrestDownWire, CrestUpWire, SlopeDownHedge, SlopeUpHedge, SlopeDownWall, SlopeUpWall, SlopeDownWire, SlopeUpWire,
        SlopeDownPavedRoad, SlopeUpPavedRoad, SlopeDownUnpavedRoad, SlopeUpUnpavedRoad, SunkenNonroad, SunkenNonroadWire, SunkenNonroadHedge, NarrowPavedRoad, NarrowUnpavedRoad, Cliff, CactusHedge,
        RailwayEmb, Seawall, CrestDownSlopeWall, CrestUpSlopeWall , CrestDownWoodsPavedRoad, CrestUpWoodsPavedRoad, CrestDownWoodsUnpavedRoad, CrestUpWoodsUnpavedRoad, CrestUpTrailWoods, CrestDownTrailWoods,
        HedgeUnpavedRoad , WallUnpavedRoad, CLUpWall, CLDownWall, CLUpHedge, CLDownHedge, CLUpWPO, CLDnWPO , Gully , CLUpWallSlope, CLDnWallSlope, CLUpWallSlopePO, CLDnWallSlopePO, PartOrch, Jetty , CLUpHedgeSlope,
        CLDnHedgeSlope, CrestUpPO, CrestDownPO, WirePO, CrestUpDouble, CrestDnDouble , SlopeUpPO, SlopeDnPO, CrestUpGully, CrestDnGully , Roadblock, WallPO, HedgePO, Trail, ByPassNA , RailCar, AttachedTerrain,
        PierLanding, AttachedBldg, IntFactside,  IntBldgWall, Rowhouseside, CliffDownGullyDown, CliffUpGullyUp, CliffUp,  CliffDown, crestupGullydnHdge, CrestDnGullyUpHdge, GullyUpSlopeDown, GullyDnSlopeUp,
        CLupGullydnHdge, CLDnGullyUpHdge, CrestUpGullySide, CrestDownGullySide, AttWoodsGullySide, Trench, TrenchCrestUp, TrenchCrestDown, TrenchHedge,TrenchWall}
    public enum HitLocation{Turret, Hull}
    public enum PersUnitResult{Breaks, Dies, Pins, Reduces, ReducesBreaks, DMs, Fanatics, Hardens, Berserks, Surrenders, Replaces, ReplacesReducesBreaks, Wounds, Substitutues, ReplacesDMs, Disrupts, HeroCreation,
        HeroHardens, NoEffects, ReducesHOB, ReducesDies, ReducesPins, ReducesReplaces, DisruptDMs, StepReduces, StepReducesHS, ReplacesStepReduces, ReplacesStepReducesHS, RevealDummy, RevealConUnitbySniper,
        RevealAllDummy, HeroicLdrCreation, HeroicLdrHardens}
    public enum HOBResult{Hardens, HeroCreation, HardensAndHero, Berserk, Surrenders}
    public enum VClass {None}

}

