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
        HeroicFan_Wnd, HeroicFan_Wnd_Enc, HeroicEnc_Wnd}
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
    public enum Nationality {Yanks, Russians, Germans, British, French, Japanese, Italians, Finns, Partisans, Gurkhas, Commonwealth, Axisminor, Alliedminor, Chinese, Hungarians, None}
}

