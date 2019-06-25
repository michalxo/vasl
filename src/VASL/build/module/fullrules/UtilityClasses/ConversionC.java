package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;

public class ConversionC {

    // this class handles a variety of object type conversions; usually from int/string/etc stored in database to enums used in objects

    public ConversionC() {
    }

    public Constantvalues.WhoCanDo ConverttoWhoCanDo(int databasevalue) {
        if (databasevalue == 7996) {
            return Constantvalues.WhoCanDo.Attacker;
        } else if (databasevalue == 7997) {
            return Constantvalues.WhoCanDo.Defender;
        } else if (databasevalue == 7998) {
            return Constantvalues.WhoCanDo.Both;
        } else if (databasevalue == 7999) {
            return Constantvalues.WhoCanDo.None;
        } else {
            return Constantvalues.WhoCanDo.None;
        }
    }

    public int ConvertWhoCanDotoInt(Constantvalues.WhoCanDo WhoCanDovalue) {
        if (WhoCanDovalue == Constantvalues.WhoCanDo.Attacker) {
            return 7996;
        } else if (WhoCanDovalue == Constantvalues.WhoCanDo.Defender) {
            return 7997;
        } else if (WhoCanDovalue == Constantvalues.WhoCanDo.Both) {
            return 7998;
        } else if (WhoCanDovalue == Constantvalues.WhoCanDo.None) {
            return 7999;
        } else {
            return 7999;
        }
    }

    public Constantvalues.DayNight ConverttoDayNight(int databasevalue) {
        switch (databasevalue) {
            case 1301:
                return Constantvalues.DayNight.Day;
            case 1302:
                return Constantvalues.DayNight.AM;
            case 1303:
                return Constantvalues.DayNight.PM;
            case 1304:
                return Constantvalues.DayNight.NIGHT;
            case 1305:
                return Constantvalues.DayNight.Dawn;
            case 1306:
                return Constantvalues.DayNight.Dusk;
            default:
                return null;
        }
    }

    public Constantvalues.Nationality ConverttoNationality(int databasevalue) {
        switch (databasevalue) {
            case 2001:
                return Constantvalues.Nationality.Yanks;
            case 2002:
                return Constantvalues.Nationality.Russians;
            case 2003:
                return Constantvalues.Nationality.Germans;
            case 2004:
                return Constantvalues.Nationality.British;
            case 2005:
                return Constantvalues.Nationality.French;
            case 2006:
                return Constantvalues.Nationality.Japanese;
            case 2007:
                return Constantvalues.Nationality.Italians;
            case 2008:
                return Constantvalues.Nationality.Finns;
            case 2009:
                return Constantvalues.Nationality.Partisans;
            case 2010:
                return Constantvalues.Nationality.Gurkhas;
            case 2011:
                return Constantvalues.Nationality.Commonwealth;
            case 2012:
                return Constantvalues.Nationality.Axisminor;
            case 2013:
                return Constantvalues.Nationality.Alliedminor;
            case 2014:
                return Constantvalues.Nationality.Chinese;
            case 2015:
                return Constantvalues.Nationality.Hungarians;
            case 2016:
                return Constantvalues.Nationality.Poles;
            case 2017:
                return Constantvalues.Nationality.None;
            default:
                return Constantvalues.Nationality.None;
        }
    }

    public int ConvertNationalitytoInt(Constantvalues.Nationality nationalityvalue) {
        switch (nationalityvalue) {
            case Yanks:
                return 2001;
            case Russians:
                return 2002;
            case Germans:
                return 2003;
            case British:
                return 2004;
            case French:
                return 2005;
            case Japanese:
                return 2006;
            case Italians:
                return 2007;
            case Finns:
                return 2008;
            case Partisans:
                return 2009;
            case Gurkhas:
                return 2010;
            case Commonwealth:
                return 2011;
            case Axisminor:
                return 2012;
            case Alliedminor:
                return 2013;
            case Chinese:
                return 2014;
            case Hungarians:
                return 2015;
            case Poles:
                return 2016;
            case None:
                return 2017;
            default:
                return 2017;
        }
    }

    public Constantvalues.Phase ConverttoPhase(int databasevalue) {
        switch (databasevalue) {
            case 1200:
                return Constantvalues.Phase.Setup;
            case 1201:
                return Constantvalues.Phase.Rally;
            case 1202:
                return Constantvalues.Phase.PrepFire;
            case 1203:
                return Constantvalues.Phase.Movement;
            case 1204:
                return Constantvalues.Phase.DefensiveFire;
            case 1205:
                return Constantvalues.Phase.AdvancingFire;
            case 1206:
                return Constantvalues.Phase.Rout;
            case 1207:
                return Constantvalues.Phase.Advance;
            case 1208:
                return Constantvalues.Phase.CloseCombat;
            case 1209:
                return Constantvalues.Phase.Refitphase;
            default:
                return null;
        }
    }

    public int ConvertPhasetoInt(Constantvalues.Phase phasevalue) {
        switch (phasevalue) {
            case Setup:
                return 1200;
            case Rally:
                return 1201;
            case PrepFire:
                return 1202;
            case Movement:
                return 1203;
            case DefensiveFire:
                return 1204;
            case AdvancingFire:
                return 1205;
            case Rout:
                return 1206;
            case Advance:
                return 1207;
            case CloseCombat:
                return 1208;
            case Refitphase:
                return 1209;
            default:
                return 0;
        }
    }

    public Constantvalues.Rules ConverttoRules(int databasevalue) {
        switch (databasevalue) {
            case 1001:
                return Constantvalues.Rules.SingleScenario;
            case 1002:
                return Constantvalues.Rules.KGPCampaign;
            case 1003:
                return Constantvalues.Rules.RedBCampaign;
            case 1004:
                return Constantvalues.Rules.PlatoonLCampaign;
            case 1005:
                return Constantvalues.Rules.PegasusCampaign;
            case 1006:
                return Constantvalues.Rules.BloodReefcampaign;
            case 1007:
                return Constantvalues.Rules.PrimosoleCampaign;
            case 1008:
                return Constantvalues.Rules.KGPScenario;
            case 1009:
                return Constantvalues.Rules.RedBScenario;
            case 1010:
                return Constantvalues.Rules.PlatoonLScenario;
            case 1011:
                return Constantvalues.Rules.PegasusScenario;
            case 1012:
                return Constantvalues.Rules.BloodReefScenario;
            case 1013:
                return Constantvalues.Rules.PrimosoleScenario;
            case 1014:
                return Constantvalues.Rules.VotGCampaign;
            case 1015:
                return Constantvalues.Rules.VotGScenario;
            default:
                return null;
        }
    }

    public int ConvertRulestoInt(Constantvalues.Rules rulesvalue) {
        switch (rulesvalue) {
            case SingleScenario:
                return 1001;
            case KGPCampaign:
                return 1002;
            case RedBCampaign:
                return 1003;
            case PlatoonLCampaign:
                return 1004;
            case PegasusCampaign:
                return 1005;
            case BloodReefcampaign:
                return 1006;
            case PrimosoleCampaign:
                return 1007;
            case KGPScenario:
                return 1008;
            case RedBScenario:
                return 1009;
            case PlatoonLScenario:
                return 2010;
            case PegasusScenario:
                return 1011;
            case BloodReefScenario:
                return 1012;
            case PrimosoleScenario:
                return 1013;
            case VotGCampaign:
                return 1014;
            case VotGScenario:
                return 1015;
            default:
                return 0;
        }
    }

    public Constantvalues.Map ConverttoMap(int databasevalue) {
        switch (databasevalue) {
            case 1001:
                return Constantvalues.Map.bd1a;
            case 1011:
                return Constantvalues.Map.bd1b;
            case 1002:
                return Constantvalues.Map.bd2a;
            case 1012:
                return Constantvalues.Map.bd2b;
            case 1003:
                return Constantvalues.Map.bd3a;
            case 1013:
                return Constantvalues.Map.bd3b;
            case 1004:
                return Constantvalues.Map.bd4a;
            case 1014:
                return Constantvalues.Map.bd4b;
            case 1005:
                return Constantvalues.Map.bd5a;
            case 1015:
                return Constantvalues.Map.bd5b;
            case 1006:
                return Constantvalues.Map.bd6a;
            case 1016:
                return Constantvalues.Map.bd6b;
            case 1007:
                return Constantvalues.Map.bd7a;
            case 1017:
                return Constantvalues.Map.bd7b;
            case 1008:
                return Constantvalues.Map.bd8a;
            case 1018:
                return Constantvalues.Map.bd8b;
            case 10109:
                return Constantvalues.Map.bd9a;
            case 1019:
                return Constantvalues.Map.bd9b;
            case 1100:
                return Constantvalues.Map.bdCreateMap;
            case 1101:
                return Constantvalues.Map.bdStoumont;
            case 1102:
                return Constantvalues.Map.bdLaGleize;
            case 1103:
                return Constantvalues.Map.bdCheneux;
            case 1104:
                return Constantvalues.Map.bdRedB;
            case 1105:
                return Constantvalues.Map.bdCrete;
            case 1107:
                return Constantvalues.Map.bdBalta;
            case 1111:
                return Constantvalues.Map.bdBurma;
            case 1112:
                return Constantvalues.Map.bdPhillipines;
            case 1113:
                return Constantvalues.Map.bdPegasusBridge;
            case 1114:
                return Constantvalues.Map.bdBloodReef;
            case 1115:
                return Constantvalues.Map.bdPrimosole;
            case 1116:
                return Constantvalues.Map.bdVotG;
            default:
                return null;
        }
    }

    public int ConvertMaptoInt(Constantvalues.Map mapvalue) {
        switch (mapvalue) {
            case bd1a:
                return 1001;
            case bd1b:
                return 1101;
            case bd2a:
                return 1002;
            case bd2b:
                return 1012;
            case bd3a:
                return 1003;
            case bd3b:
                return 1013;
            case bd4a:
                return 1004;
            case bd4b:
                return 1014;
            case bd5a:
                return 1005;
            case bd5b:
                return 1015;
            case bd6a:
                return 1006;
            case bd6b:
                return 1016;
            case bd7a:
                return 1007;
            case bd7b:
                return 1017;
            case bd8a:
                return 1008;
            case bd8b:
                return 1018;
            case bd9a:
                return 1009;
            case bd9b:
                return 1019;
            case bdCreateMap:
                return 1100;
            case bdStoumont:
                return 1101;
            case bdLaGleize:
                return 1102;
            case bdCheneux:
                return 1103;
            case bdRedB:
                return 1104;
            case bdCrete:
                return 1105;
            case bdBalta:
                return 1107;
            case bdBurma:
                return 1111;
            case bdPhillipines:
                return 1112;
            case bdPegasusBridge:
                return 1113;
            case bdBloodReef:
                return 1114;
            case bdPrimosole:
                return 1115;
            case bdVotG:
                return 1116;
            default:
                return 0;
        }
    }

    public Constantvalues.Utype ConverttoUnitType(int databasevalue) {
        switch (databasevalue) {
            case 2101:
                return Constantvalues.Utype.Squad;
            case 2102:
                return Constantvalues.Utype.HalfSquad;
            case 2103:
                return Constantvalues.Utype.Crew;
            case 2104:
                return Constantvalues.Utype.Hero;
            case 2105:
                return Constantvalues.Utype.LdrHero;
            case 2106:
                return Constantvalues.Utype.Leader;
            case 2107:
                return Constantvalues.Utype.THHero;
            case 2108:
                return Constantvalues.Utype.PathFind;
            case 2109:
                return Constantvalues.Utype.Commissar;
            case 2121:
                return Constantvalues.Utype.MMC;
            case 2122:
                return Constantvalues.Utype.SMC;
            case 2123:
                return Constantvalues.Utype.Dummy;
            case 2124:
                return Constantvalues.Utype.Units;
            default:
                return Constantvalues.Utype.None;

        }
    }

    public int ConvertUnitTypetoint(Constantvalues.Utype typevalue) {
        switch (typevalue) {
            case Squad:
                return 2101;
            case HalfSquad:
                return 2102;
            case Crew:
                return 2103;
            case Hero:
                return 2104;
            case LdrHero:
                return 2105;
            case Leader:
                return 2106;
            case THHero:
                return 2107;
            case PathFind:
                return 2108;
            case Commissar:
                return 2109;
            case MMC:
                return 2121;
            case SMC:
                return 2122;
            case Dummy:
                return 2123;
            case Units:
                return 2124;
            default:
                return 0;

        }
    }

    public Constantvalues.SWtype ConverttoSWType(int databasevalue) {
        switch (databasevalue) {
            case 5001:
                return Constantvalues.SWtype.LMGun;
            case 5002:
                return Constantvalues.SWtype.MMGun;
            case 5003:
                return Constantvalues.SWtype.HMGun;
            case 5004:
                return Constantvalues.SWtype.H50cal;
            case 5005:
                return Constantvalues.SWtype.AnyMG;
            case 5006:
                return Constantvalues.SWtype.OBRadio;
            case 5007:
                return Constantvalues.SWtype.OBPhn;
            case 5008:
                return Constantvalues.SWtype.FThr;
            case 5009:
                return Constantvalues.SWtype.DemoC;
            case 5010:
                return Constantvalues.SWtype.BazK;
            case 5011:
                return Constantvalues.SWtype.Mortar;
            case 5012:
                return Constantvalues.SWtype.ATR;
            case 5013:
                return Constantvalues.SWtype.PIAT;
            case 5014:
                return Constantvalues.SWtype.PSK;
            case 5015:
                return Constantvalues.SWtype.Lahti;
            case 5016:
                return Constantvalues.SWtype.Rclr;
            case 5017:
                return Constantvalues.SWtype.InfG;
            case 5018:
                return Constantvalues.SWtype.MolP;
            case 5019:
                return Constantvalues.SWtype.SetDC;
            default:
                return Constantvalues.SWtype.None;
        }
    }

    public Constantvalues.Typetype ConverttoTypetype(int typevalue) {
        switch (typevalue) {
            case 5001:
                return Constantvalues.Typetype.Personnel;
            case 5002:
                return Constantvalues.Typetype.Vehicle;
            case 5003:
                return Constantvalues.Typetype.Concealment;
            case 5004:
                return Constantvalues.Typetype.SW;
            case 5005:
                return Constantvalues.Typetype.Gun;
            case 5006:
                return Constantvalues.Typetype.WhiteC;
            case 5007:
                return Constantvalues.Typetype.Location;
            case 5008:
                return Constantvalues.Typetype.Feature;
            case 5009:
                return Constantvalues.Typetype.AltPos;
            case 5010:
                return Constantvalues.Typetype.VisHind;
            default:
                return Constantvalues.Typetype.None;
        }
    }

    public int ConvertTypetypetoint(Constantvalues.Typetype typevalue) {
        switch (typevalue) {
            case Personnel:
                return 5001;
            case Vehicle:
                return 5002;
            case Concealment:
                return 5003;
            case SW:
                return 5004;
            case Gun:
                return 5005;
            case WhiteC:
                return 5006;
            case Location:
                return 5007;
            case Feature:
                return 5008;
            case AltPos:
                return 5009;
            case VisHind:
                return 5010;
            default:
                return 0;
        }
    }

    public Constantvalues.UClass ConverttoUClass(int typevalue) {
        switch (typevalue) {
            case 4001:
                return Constantvalues.UClass.FIRSTLINE;
            case 4002:
                return Constantvalues.UClass.SECONDLINE;
            case 4003:
                return Constantvalues.UClass.GREEN;
            case 4004:
                return Constantvalues.UClass.CONSCRIPT;
            case 4005:
                return Constantvalues.UClass.ELITE;
            case 4006:
                return Constantvalues.UClass.SS;
            case 4007:
                return Constantvalues.UClass.ENGINEER;
            case 4008:
                return Constantvalues.UClass.AIRBORNE;
            case 4009:
                return Constantvalues.UClass.MARINE;
            case 4010:
                return Constantvalues.UClass.CREWCLASS;
            case 4011:
                return Constantvalues.UClass.PARTISAN;
            case 4012:
                return Constantvalues.UClass.MARINEENG;
            case 4013:
                return Constantvalues.UClass.UNARMED;
            case 4014:
                return Constantvalues.UClass.SFIRSTLINE;
            case 4015:
                return Constantvalues.UClass.SSECONDLINE;
            case 4016:
                return Constantvalues.UClass.SGREEN;
            case 4017:
                return Constantvalues.UClass.SCONSCRIPT;
            case 4018:
                return Constantvalues.UClass.SELITE;
            case 4019:
                return Constantvalues.UClass.SSS;
            case 4020:
                return Constantvalues.UClass.SENGINEER;
            case 4021:
                return Constantvalues.UClass.SAIRBORNE;
            case 4022:
                return Constantvalues.UClass.SMARINE;
            case 4023:
                return Constantvalues.UClass.SCREWCLASS;
            case 4024:
                return Constantvalues.UClass.SPARTISAN;
            case 4025:
                return Constantvalues.UClass.SMARINEENG;
            case 4026:
                return Constantvalues.UClass.SUNARMED;
            case 4027:
                return Constantvalues.UClass.ASFIRSTLINE;
            case 4028:
                return Constantvalues.UClass.ASSECONDLINE;
            case 4029:
                return Constantvalues.UClass.ASGREEN;
            case 4030:
                return Constantvalues.UClass.ASCONSCRIPT;
            case 4031:
                return Constantvalues.UClass.ASELITE;
            case 4032:
                return Constantvalues.UClass.ASSS;
            case 4033:
                return Constantvalues.UClass.ASENGINEER;
            case 4034:
                return Constantvalues.UClass.ASAIRBORNE;
            case 4035:
                return Constantvalues.UClass.ASMARINE;
            case 4036:
                return Constantvalues.UClass.ASCREWCLASS;
            case 4037:
                return Constantvalues.UClass.ASPARTISAN;
            case 4038:
                return Constantvalues.UClass.ASMARINEENG;
            case 4039:
                return Constantvalues.UClass.ASUNARMED;
            case 4040:
                return Constantvalues.UClass.AFIRSTLINE;
            case 4041:
                return Constantvalues.UClass.ASECONDLINE;
            case 4042:
                return Constantvalues.UClass.AGREEN;
            case 4043:
                return Constantvalues.UClass.ACONSCRIPT;
            case 4044:
                return Constantvalues.UClass.AELITE;
            case 4045:
                return Constantvalues.UClass.ASS;
            case 4046:
                return Constantvalues.UClass.AENGINEER;
            case 4047:
                return Constantvalues.UClass.AAIRBORNE;
            case 4048:
                return Constantvalues.UClass.AMARINE;
            case 4049:
                return Constantvalues.UClass.ACREWCLASS;
            case 4050:
                return Constantvalues.UClass.APARTISAN;
            case 4051:
                return Constantvalues.UClass.AMARINEENG;
            case 4052:
                return Constantvalues.UClass.AUNARMED;
            case 4053:
                return Constantvalues.UClass.NONE;
            default:
                return Constantvalues.UClass.NONE;
        }
    }

    public int ConvertUClasstoint(Constantvalues.UClass typevalue) {
        switch (typevalue) {
            case FIRSTLINE:
                return 4001;
            case SECONDLINE:
                return 4002;
            case GREEN:
                return 4003;
            case CONSCRIPT:
                return 4004;
            case ELITE:
                return 4005;
            case SS:
                return 4006;
            case ENGINEER:
                return 4007;
            case AIRBORNE:
                return 4008;
            case MARINE:
                return 4009;
            case CREWCLASS:
                return 4010;
            case PARTISAN:
                return 4011;
            case MARINEENG:
                return 4012;
            case UNARMED:
                return 4013;
            case SFIRSTLINE:
                return 4014;
            case SSECONDLINE:
                return 4015;
            case SGREEN:
                return 4016;
            case SCONSCRIPT:
                return 4017;
            case SELITE:
                return 4018;
            case SSS:
                return 4019;
            case SENGINEER:
                return 4020;
            case SAIRBORNE:
                return 4021;
            case SMARINE:
                return 4022;
            case SCREWCLASS:
                return 4023;
            case SPARTISAN:
                return 4024;
            case SMARINEENG:
                return 4025;
            case SUNARMED:
                return 4026;
            case ASFIRSTLINE:
                return 4027;
            case ASSECONDLINE:
                return 4028;
            case ASGREEN:
                return 4029;
            case ASCONSCRIPT:
                return 4030;
            case ASELITE:
                return 4031;
            case ASSS:
                return 4032;
            case ASENGINEER:
                return 4033;
            case ASAIRBORNE:
                return 4034;
            case ASMARINE:
                return 4035;
            case ASCREWCLASS:
                return 4036;
            case ASPARTISAN:
                return 4037;
            case ASMARINEENG:
                return 4038;
            case ASUNARMED:
                return 4039;
            case AFIRSTLINE:
                return 4040;
            case ASECONDLINE:
                return 4041;
            case AGREEN:
                return 4042;
            case ACONSCRIPT:
                return 4043;
            case AELITE:
                return 4044;
            case ASS:
                return 4045;
            case AENGINEER:
                return 4046;
            case AAIRBORNE:
                return 4047;
            case AMARINE:
                return 4048;
            case ACREWCLASS:
                return 4049;
            case APARTISAN:
                return 4050;
            case AMARINEENG:
                return 4051;
            case AUNARMED:
                return 4052;
            case NONE:
                return 4053;
            default:
                return 4053;
        }
    }

    public Constantvalues.Location ConverttoLocationType(int Locvalue) {
        switch (Locvalue) {
            case 6000:
                return Constantvalues.Location.Ocean;
            case 6001:
                return Constantvalues.Location.OpenGround;
            case 6002:
                return Constantvalues.Location.River;
            case 6003:
                return Constantvalues.Location.Marsh;
            case 6004:
                return Constantvalues.Location.Brush;
            case 6005:
                return Constantvalues.Location.Streamshallow;
            case 6006:
                return Constantvalues.Location.Ford;
            case 6007:
                return Constantvalues.Location.PineWoods;
            case 6008:
                return Constantvalues.Location.Woods;
            case 6009:
                return Constantvalues.Location.Crag;
            case 6010:
                return Constantvalues.Location.Airfield;
            case 6011:
                return Constantvalues.Location.Mudflats;
            case 6012:
                return Constantvalues.Location.Orchard;
            case 6013:
                return Constantvalues.Location.OrchardPavedRoad;
            case 6014:
                return Constantvalues.Location.OrchardUnpavedRoad;
            case 6015:
                return Constantvalues.Location.PavedRoad;
            case 6016:
                return Constantvalues.Location.UnpavedRoad;
            case 6017:
                return Constantvalues.Location.TrailWoods;
            case 6018:
                return Constantvalues.Location.TrailBrush;
            case 6019:
                return Constantvalues.Location.TrailPineWoods;
            case 6020:
                return Constantvalues.Location.WoodsPavedRoad;
            case 6021:
                return Constantvalues.Location.WoodsUnpavedRoad;
            case 6022:
                return Constantvalues.Location.PineWoodsPavedRoad;
            case 6023:
                return Constantvalues.Location.PineWoodsUnpavedRoad;
            case 6024:
                return Constantvalues.Location.GullyBrush;
            case 6025:
                return Constantvalues.Location.GullyWoods;
            case 6026:
                return Constantvalues.Location.Graveyard;
            case 6027:
                return Constantvalues.Location.SunkenPavedRoad;
            case 6028:
                return Constantvalues.Location.OrchardStreamshallow;
            case 6029:
                return Constantvalues.Location.StreamWoodsshallow;
            case 6030:
                return Constantvalues.Location.StreamPineWoodsshallow;
            case 6031:
                return Constantvalues.Location.StreamBrushshallow;
            case 6032:
                return Constantvalues.Location.SunkenUnpvRoad;
            case 6033:
                return Constantvalues.Location.StratLoc;
            case 6034:
                return Constantvalues.Location.Grainfield;
            case 6035:
                return Constantvalues.Location.Gully;
            case 6036:
                return Constantvalues.Location.WoodRubble;
            case 6037:
                return Constantvalues.Location.StoneRubble;
            case 6038:
                return Constantvalues.Location.WoodDebris;
            case 6039:
                return Constantvalues.Location.StoneDebris;
            case 6040:
                return Constantvalues.Location.Shellhole;
            case 6041:
                return Constantvalues.Location.Jungle;
            case 6042:
                return Constantvalues.Location.DenseJungle;
            case 6043:
                return Constantvalues.Location.Bamboo;
            case 6044:
                return Constantvalues.Location.PalmTrees;
            case 6045:
                //return Constantvalues.Location.Gully;
            case 6046:
                return Constantvalues.Location.Kunai;
            case 6047:
                return Constantvalues.Location.Swamp;
            case 6048:
                return Constantvalues.Location.RicePaddiesdrained;
            case 6049:
                return Constantvalues.Location.RicePaddiesIrrigated;
            case 6050:
                return Constantvalues.Location.RicePaddiesInSeason;
            case 6051:
                return Constantvalues.Location.Panjis;
            case 6052:
                return Constantvalues.Location.CaveComplex;
            case 6053:
                return Constantvalues.Location.Beaches;
            case 6054:
                return Constantvalues.Location.PalmTreesUnpavedRoad;
            case 6055:
                return Constantvalues.Location.TrailJungle;
            case 6056:
                return Constantvalues.Location.TrailDenseJungle;
            case 6057:
                return Constantvalues.Location.JungleUnpavedRoad;
            case 6058:
                return Constantvalues.Location.DenseJungleUnpaved;
            case 6059:
                return Constantvalues.Location.TrailBamboo;
            case 6060:
                return Constantvalues.Location.Trail;
            case 6061:
                return Constantvalues.Location.Trailpalmtrees;
            case 6062:
                return Constantvalues.Location.GullyShell;
            case 6063:
                return Constantvalues.Location.PalmDebris;
            case 6064:
                return Constantvalues.Location.PalmDebrisPalm;
            case 6065:
                return Constantvalues.Location.GullyUnpavedBridge;
            case 6066:
                return Constantvalues.Location.PierNoLoc;
            case 6067:
                return Constantvalues.Location.SunkenLane;
            case 6068:
                return Constantvalues.Location.Passage;
            case 6069:
                return Constantvalues.Location.PavedIntersectionManhole;
            case 6070:
                return Constantvalues.Location.ElevRoad;
            case 6071:
                //return Constantvalues.Location.StreamBrushshallow;
            case 6072:
                //return Constantvalues.Location.SunkenUnpvRoad;
            case 6073:
                return Constantvalues.Location.Pier;
            case 6074:
                //return Constantvalues.Location.Grainfield;
            case 6075:
                return Constantvalues.Location.Reef;
            case 6076:
                return Constantvalues.Location.Tetrahedron;
            case 6077:
                return Constantvalues.Location.Tetrawire;
            case 6078:
                //return Constantvalues.Location.WoodDebris;
            case 6079:
                //return Constantvalues.Location.StoneDebris;
            case 6080:
                //return Constantvalues.Location.Shellhole;
            case 6081:
                return Constantvalues.Location.Vineyard;
            case 6082:
                //return Constantvalues.Location.SunkenUnpvRoad;
            case 6083:
                return Constantvalues.Location.IrrigDitch;
            case 6084:
                return Constantvalues.Location.PartOrch;
            case 6085:
                return Constantvalues.Location.IrDPOrch;
            case 6086:
                return Constantvalues.Location.UnPavedIrDPO;
            case 6087:
                return Constantvalues.Location.UnPavedIrD;
            case 6088:
                return Constantvalues.Location.UnPavedPO;
            case 6089:
                return Constantvalues.Location.PavedPO;
            case 6090:
                return Constantvalues.Location.GullyPO;
            case 6091:
                return Constantvalues.Location.PavedIrD;
            case 6092:
                return Constantvalues.Location.Forest;
            case 6093:
                return Constantvalues.Location.CactusPatch;
            case 6094:
                return Constantvalues.Location.OliveGrove;
            case 6095:
                return Constantvalues.Location.Hillock;
            case 6096:
                return Constantvalues.Location.SandDuneH;
            case 6097:
                return Constantvalues.Location.SandDuneL;
            case 6098:
                return Constantvalues.Location.Lumberyard;
            case 6099:
                return Constantvalues.Location.GullyOrchard;
            case 6103:
                return Constantvalues.Location.HillockSummit;
            case 6101:
                return Constantvalues.Location.WoodRubbleFallen;
            case 6102:
                return Constantvalues.Location.StoneRubbleFallen;
            case 6111:
                return Constantvalues.Location.GrdLRR;
            case 6112:
                return Constantvalues.Location.RailCar;
            case 6113:
                return Constantvalues.Location.WreckedRC;
            case 6114:
                return Constantvalues.Location.GuttedRC;
            case 6115:
                return Constantvalues.Location.CitySquare;
            case 6116:
                return Constantvalues.Location.CitySquareShellhole;
            case 6119:
                return Constantvalues.Location.CitySquareManShell;
            case 6124:
                return Constantvalues.Location.Fountain;
            case 6125:
                return Constantvalues.Location.GrdLRRMan;
            case 6126:
                return Constantvalues.Location.OrchardPvRoadMan;
            case 6127:
                return Constantvalues.Location.StorageTanks;
            case 6128:
                return Constantvalues.Location.Canal;
            case 6129:
                return Constantvalues.Location.Pond;
            case 6131:
                return Constantvalues.Location.StreamDry;
            case 6132:
                return Constantvalues.Location.StreamDeep;
            case 6133:
                return Constantvalues.Location.StreamFlooded;
            case 6134:
                return Constantvalues.Location.OrchardStreamdry;
            case 6135:
                return Constantvalues.Location.OrchardStreamdeep;
            case 6136:
                return Constantvalues.Location.OrchardStreamflooded;
            case 6137:
                return Constantvalues.Location.StreamWoodsdry;
            case 6138:
                return Constantvalues.Location.streamwoodsdeep;
            case 6139:
                return Constantvalues.Location.Streamwoodsflooded;
            case 6140:
                return Constantvalues.Location.StreamPineWoodsdry;
            case 6141:
                return Constantvalues.Location.streamPinewoodsdeep;
            case 6142:
                return Constantvalues.Location.StreamPinewoodsflooded;
            case 6143:
                return Constantvalues.Location.StreamBrushdry;
            case 6144:
                return Constantvalues.Location.streambrushdeep;
            case 6145:
                return Constantvalues.Location.Streambrushflooded;
            case 6146:
                return Constantvalues.Location.Wadi;
            case 6147:
                return Constantvalues.Location.Hammada;
            case 6148:
                return Constantvalues.Location.SandTrack;
            case 6149:
                return Constantvalues.Location.HammadaTrack;
            case 6150:
                return Constantvalues.Location.Deir;
            case 6151:
                return Constantvalues.Location.Track;
            case 6152:
                return Constantvalues.Location.HillockTrack;
            case 6153:
                return Constantvalues.Location.DeirTrack;
            case 6154:
                return Constantvalues.Location.Scrub;
            case 6155:
                return Constantvalues.Location.ScrubTrack;
            case 6156:
                return Constantvalues.Location.HillockSummitTrack;
            case 6157:
                return Constantvalues.Location.SandScrub;
            case 6158:
                return Constantvalues.Location.SandScrubTrack;
            case 6159:
                return Constantvalues.Location.SandDuneLTrack;
            case 6160:
                return Constantvalues.Location.SandDuneHTrack;
            case 6161:
                return Constantvalues.Location.Mausoleum;
            case 6162:
                return Constantvalues.Location.Camp;
            case 6163:
                return Constantvalues.Location.DesertCluster;  //D12.43
            case 6164:
                return Constantvalues.Location.BrkCrag;
            case 6165:
                return Constantvalues.Location.BrokenGround;
            case 6166:
                return Constantvalues.Location.SteppeBrush;
            case 6167:
                return Constantvalues.Location.SteppeWoods;
            case 6168:
                return Constantvalues.Location.SteppeGrain;
            case 6169:
                return Constantvalues.Location.ExcavDitch;
            case 6170:
                return Constantvalues.Location.BambooPath;
            case 6171:
                return Constantvalues.Location.JunglePath;
            case 6172:
                return Constantvalues.Location.DenseJunglePath;
            case 6173:
                //return Constantvalues.Location.WoodDebris;
            case 6174:
                return Constantvalues.Location.BeachSlightSoft;
            case 6175:
                return Constantvalues.Location.BeachModerateSoft;
            case 6176:
                return Constantvalues.Location.BeachSteepSoft;
            case 6177:
                return Constantvalues.Location.Sandbar;
            case 6178:
                return Constantvalues.Location.BeachSlightHard;
            case 6179:
                return Constantvalues.Location.BeachModerateHard;
            case 6180:
                return Constantvalues.Location.BeachSteepHard;
            case 6181:
                return Constantvalues.Location.OceanShallow;
            case 6182:
                return Constantvalues.Location.ExposedReef;
            case 6183:
                return Constantvalues.Location.SubmergedReef;
            case 6184:
                return Constantvalues.Location.JungleDebris; //SC2
            case 6185:
                return Constantvalues.Location.CordoroyRoads;  //SC4
            case 6186:
                return Constantvalues.Location.StreamShallowJungleDebris;
            case 6187:
                return Constantvalues.Location.RiverBrush;
            case 6188:
                return Constantvalues.Location.BrushUnpavedRd;
            case 6189:
                return Constantvalues.Location.BrushIrrD;
            case 6190:
                return Constantvalues.Location.IrrDVineyard;
            case 6191:
                //return Constantvalues.Location.WoodRubble;
            case 6192:
                return Constantvalues.Location.PineForest;
            case 6193:
                return Constantvalues.Location.CulvertOG;
            case 6194:
                return Constantvalues.Location.CulvertPvRD;
            case 6195:
                return Constantvalues.Location.CulStrPvRd;
            case 6234:
                return Constantvalues.Location.CommandBunker;
            case 6235:
                return Constantvalues.Location.IslComBunker;
            case 6236:
                return Constantvalues.Location.GunEmplacement;
            case 6237:
                return Constantvalues.Location.BRTTower;
            case 6247:
                return Constantvalues.Location.OneMarketStone;
            case 6248:
                return Constantvalues.Location.OneMarketWood;
            case 6254:
                return Constantvalues.Location.PBTower;
            case 6255:
                return Constantvalues.Location.SingleSteeple;
            case 6256:
                return Constantvalues.Location.OneSteeple;
            case 6257:
                return Constantvalues.Location.TwoSteeple;
            case 6261:
                return Constantvalues.Location.PartCol1;
            case 6262:
                return Constantvalues.Location.PartCol15;
            case 6273:
                return Constantvalues.Location.Manhole;
            case 5999:
                return Constantvalues.Location.All;
            case 9999:
                return Constantvalues.Location.NA;
            case 6301:
                return Constantvalues.Location.NonStairBldg;
            case 6302:
                return Constantvalues.Location.HindranceHex;
            case 6303:
                return Constantvalues.Location.HindranceFeature;
            case 6304:
                return Constantvalues.Location.Shellholetype;
            case 6305:
                return Constantvalues.Location.Creststatustype;
            case 6306:
                return Constantvalues.Location.Bypassable;
            case 6307:
                return Constantvalues.Location.Smoketype;
            case 6308:
                return Constantvalues.Location.RoadOGtype;
            case 6309:
                return Constantvalues.Location.Manholetype;
            case 6310:
                return Constantvalues.Location.Factorytype;
            case 6311:
                return Constantvalues.Location.Rooflesstype;
            case 6312:
                return Constantvalues.Location.Cellartype;
            case 6313:
                return Constantvalues.Location.SplitLeveltype;
            case 6314:
                return Constantvalues.Location.Buildingtype;
            case 6315:
                return Constantvalues.Location.Pillboxtype;
            case 6316:
                return Constantvalues.Location.IntBuildtype;
            case 6317:
                return Constantvalues.Location.Rubbletype;
            case 6318:
                return Constantvalues.Location.FortBuildtype;
            case 6319:
                return Constantvalues.Location.bridgetype;
            case 6320:
                return Constantvalues.Location.Marshtype;
            case 6321:
                return Constantvalues.Location.ShallowStreamtype;
            case 6322:
                return Constantvalues.Location.DeepStreamtype;
            case 6323:
                return Constantvalues.Location.WaterObstacletype;
            case 6324:
                return Constantvalues.Location.Blazetype;
            case 6325:
                return Constantvalues.Location.towertype;
            case 6326:
                return Constantvalues.Location.Roadtype;
            case 6327:
                return Constantvalues.Location.OBAtype;
            case 6328:
                return Constantvalues.Location.HardSurftype;
            case 6329:
                return Constantvalues.Location.HasStairs;
            case 6330:
                return Constantvalues.Location.Burnabletype;
            case 6701:
                return Constantvalues.Location.Roof;
            case 6702:
                return Constantvalues.Location.StoneBuilding1stLevel;
            case 6703:
                return Constantvalues.Location.StoneBuilding2ndLevel;
            case 6704:
                return Constantvalues.Location.StoneBuilding3rdLevel;
            case 6705:
                return Constantvalues.Location.StoneCellar;
            case 6706:
                return Constantvalues.Location.InCave;
            case 6707:
                return Constantvalues.Location.BeneathBridge;
            case 6708:
                return Constantvalues.Location.Sewer;
            case 6711:
                return Constantvalues.Location.BeneathPier;
            case 6712:
                return Constantvalues.Location.StoneBuildingGroundlevel;
            case 6713:
                return Constantvalues.Location.BunkUnder;
            case 6714:
                return Constantvalues.Location.Tunnel;
            case 6715:
                return Constantvalues.Location.Huts;
            case 6730:
                return Constantvalues.Location.FortStoneGrd;
            case 6731:
                return Constantvalues.Location.FortSTone1st;
            case 6732:
                return Constantvalues.Location.FortStone2nd;
            case 6733:
                return Constantvalues.Location.FortSTone3rd;
            case 6734:
                return Constantvalues.Location.FortWoodGrd;
            case 6735:
                return Constantvalues.Location.FortWood1st;
            case 6736:
                return Constantvalues.Location.FortWood2nd;
            case 6737:
                return Constantvalues.Location.FortWood3rd;
            case 15001:
                return Constantvalues.Location.Pill1571;
            case 15002:
                return Constantvalues.Location.Pill1572;
            case 15003:
                return Constantvalues.Location.Pill1573;
            case 15004:
                return Constantvalues.Location.Pill1574;
            case 15005:
                return Constantvalues.Location.Pill1575;
            case 15006:
                return Constantvalues.Location.Pill1570;
            case 15007:
                return Constantvalues.Location.Pill1351;
            case 15008:
                return Constantvalues.Location.Pill1352;
            case 15009:
                return Constantvalues.Location.Pill1353;
            case 15010:
                return Constantvalues.Location.Pill1354;
            case 15011:
                return Constantvalues.Location.Pill1355;
            case 15012:
                return Constantvalues.Location.Pill1350;
            case 15013:
                return Constantvalues.Location.Pill2351;
            case 15014:
                return Constantvalues.Location.Pill2352;
            case 15015:
                return Constantvalues.Location.Pill2353;
            case 15016:
                return Constantvalues.Location.Pill2354;
            case 15017:
                return Constantvalues.Location.Pill2355;
            case 15018:
                return Constantvalues.Location.Pill2350;
            case 15019:
                return Constantvalues.Location.Pill2571;
            case 15020:
                return Constantvalues.Location.Pill2572;
            case 15021:
                return Constantvalues.Location.Pill2573;
            case 15022:
                return Constantvalues.Location.Pill2574;
            case 15023:
                return Constantvalues.Location.Pill2575;
            case 15024:
                return Constantvalues.Location.Pill2570;
            case 15025:
                return Constantvalues.Location.Pill3351;
            case 15026:
                return Constantvalues.Location.Pill3352;
            case 15027:
                return Constantvalues.Location.Pill3353;
            case 15028:
                return Constantvalues.Location.Pill3354;
            case 15029:
                return Constantvalues.Location.Pill3355;
            case 15030:
                return Constantvalues.Location.Pill3350;
            case 15031:
                return Constantvalues.Location.Pill3571;
            case 15032:
                return Constantvalues.Location.Pill3572;
            case 15033:
                return Constantvalues.Location.Pill3573;
            case 15034:
                return Constantvalues.Location.Pill3574;
            case 15035:
                return Constantvalues.Location.Pill3575;
            case 15036:
                return Constantvalues.Location.Pill3570;
            case 15037:
                return Constantvalues.Location.Pill2461;
            case 15038:
                return Constantvalues.Location.Pill2462;
            case 15039:
                return Constantvalues.Location.Pill2463;
            case 15040:
                return Constantvalues.Location.Pill2464;
            case 15041:
                return Constantvalues.Location.Pill2465;
            case 15042:
                return Constantvalues.Location.Pill2460;
            case 15043:
                return Constantvalues.Location.Pill1461;
            case 15044:
                return Constantvalues.Location.Pill1462;
            case 15045:
                return Constantvalues.Location.Pill1463;
            case 15046:
                return Constantvalues.Location.Pill1464;
            case 15047:
                return Constantvalues.Location.Pill1465;
            case 15048:
                return Constantvalues.Location.Pill1460;
            case 15897:
                return Constantvalues.Location.Bombprf;
            case 10001:
                return Constantvalues.Location.Cave146;
            case 10002:
                return Constantvalues.Location.CaveH46;
            case 10003:
                return Constantvalues.Location.Cave146LM1;
            case 10004:
                return Constantvalues.Location.CaveH46LM1;
            case 10005:
                return Constantvalues.Location.Cave146L0;
            case 10006:
                return Constantvalues.Location.CaveH46L0;
            case 10007:
                return Constantvalues.Location.Cave146L1;
            case 10008:
                return Constantvalues.Location.CaveH46L1;
            case 10009:
                return Constantvalues.Location.Cave146L2;
            case 10010:
                return Constantvalues.Location.CaveH46L2;
            case 10011:
                return Constantvalues.Location.Cave146L3;
            case 10012:
                return Constantvalues.Location.CaveH46L3;
            case 10013:
                return Constantvalues.Location.Cave146L4;
            case 10014:
                return Constantvalues.Location.CaveH46L4;
            case 10030:
                return Constantvalues.Location.WoodsTB;
            case 10031:
                return Constantvalues.Location.MineTB;
            case 10032:
                return Constantvalues.Location.StoneRubbleTB;
            case 10033:
                return Constantvalues.Location.PineWTB;
            case 10034:
                return Constantvalues.Location.JungleTB;
            case 10035:
                return Constantvalues.Location.DenseJungleTB;
            case 10036:
                return Constantvalues.Location.DebrisTB;
            case 10037:
                return Constantvalues.Location.WoodRubbleTB;
            case 10038:
                return Constantvalues.Location.WoodRubbleFallTB;
            case 10039:
                return Constantvalues.Location.StoneRubbleFallTB;
            case 10040:
                return Constantvalues.Location.OneFactRooflessMan;
            case 30001:
                return Constantvalues.Location.StBr14;
            case 30002:
                return Constantvalues.Location.StBr25;
            case 30003:
                return Constantvalues.Location.StBr30;
            case 30004:
                return Constantvalues.Location.WdBr14;
            case 30005:
                return Constantvalues.Location.WdBr25;
            case 30006:
                return Constantvalues.Location.WdBr30;
            case 30007:
                return Constantvalues.Location.SStBr14;
            case 30008:
                return Constantvalues.Location.SStBr25;
            case 30009:
                return Constantvalues.Location.SStBr30;
            case 30010:
                return Constantvalues.Location.SWdBr14;
            case 30011:
                return Constantvalues.Location.SWdBr25;
            case 30012:
                return Constantvalues.Location.SWdBr30;
            case 30013:
                return Constantvalues.Location.PoBr14;
            case 30014:
                return Constantvalues.Location.PoBr25;
            case 30015:
                return Constantvalues.Location.PoBr30;
            case 30016:
                return Constantvalues.Location.FoBr;
            default:
                return Constantvalues.Location.NA;
        }
    }

    public int ConvertLocationTypetoint(Constantvalues.Location Locvalue) {
        switch (Locvalue) {
            case Ocean:
                return 6000;
            case OpenGround:
                return 6001;
            case River:
                return 6002;
            case Marsh:
                return 6003;
            case Brush:
                return 6004;
            case Streamshallow:
                return 6005;
            case Ford:
                return 6006;
            case PineWoods:
                return 6007;
            case Woods:
                return 6008;
            case Crag:
                return 6009;
            case Airfield:
                return 6010;
            case Mudflats:
                return 6011;
            case Orchard:
                return 6012;
            case OrchardPavedRoad:
                return 6013;
            case OrchardUnpavedRoad:
                return 6014;
            case PavedRoad:
                return 6015;
            case UnpavedRoad:
                return 6016;
            case TrailWoods:
                return 6017;
            case TrailBrush:
                return 6018;
            case TrailPineWoods:
                return 6019;
            case WoodsPavedRoad:
                return 6020;
            case WoodsUnpavedRoad:
                return 6021;
            case PineWoodsPavedRoad:
                return 6022;
            case PineWoodsUnpavedRoad:
                return 6023;
            case GullyBrush:
                return 6024;
            case GullyWoods:
                return 6025;
            case Graveyard:
                return 6026;
            case SunkenPavedRoad:
                return 6027;
            case OrchardStreamshallow:
                return 6028;
            case StreamWoodsshallow:
                return 6029;
            case StreamPineWoodsshallow:
                return 6030;
            case StreamBrushshallow:
                return 6031;
            case SunkenUnpvRoad:
                return 6032;
            case StratLoc:
                return 6033;
            case Grainfield:
                return 6034;
            case Gully:
                return 6035;
            case WoodRubble:
                return 6036;
            case StoneRubble:
                return 6037;
            case WoodDebris:
                return 6038;
            case StoneDebris:
                return 6039;
            case Shellhole:
                return 6040;
            case Jungle:
                return 6041;
            case DenseJungle:
                return 6042;
            case Bamboo:
                return 6043;
            case PalmTrees:
                return 6044;
            //case 6045:
            //return Gully;
            case Kunai:
                return 6046;
            case Swamp:
                return 6047;
            case RicePaddiesdrained:
                return 6048;
            case RicePaddiesIrrigated:
                return 6049;
            case RicePaddiesInSeason:
                return 6050;
            case Panjis:
                return 6051;
            case CaveComplex:
                return 6052;
            case Beaches:
                return 6053;
            case PalmTreesUnpavedRoad:
                return 6054;
            case TrailJungle:
                return 6055;
            case TrailDenseJungle:
                return 6056;
            case JungleUnpavedRoad:
                return 6057;
            case DenseJungleUnpaved:
                return 6058;
            case TrailBamboo:
                return 6059;
            case Trail:
                return 6060;
            case Trailpalmtrees:
                return 6061;
            case GullyShell:
                return 6062;
            case PalmDebris:
                return 6063;
            case PalmDebrisPalm:
                return 6064;
            case GullyUnpavedBridge:
                return 6065;
            case PierNoLoc:
                return 6066;
            case SunkenLane:
                return 6067;
            case Passage:
                return 6068;
            case PavedIntersectionManhole:
                return 6069;
            case ElevRoad:
                return 6070;
            //case 6071:
            //return StreamBrushshallow;
            //case 6072:
            //return SunkenUnpvRoad;
            case Pier:
                return 6073;
            //case 6074:
            //return Grainfield;
            case Reef:
                return 6075;
            case Tetrahedron:
                return 6076;
            case Tetrawire:
                return 6077;
            //case 6078:
            //return WoodDebris;
            //case 6079:
            //return StoneDebris;
            //case 6080:
            //return Shellhole;
            case Vineyard:
                return 6081;
            //case 6082:
            //return SunkenUnpvRoad;
            case IrrigDitch:
                return 6083;
            case PartOrch:
                return 6084;
            case IrDPOrch:
                return 6085;
            case UnPavedIrDPO:
                return 6086;
            case UnPavedIrD:
                return 6087;
            case UnPavedPO:
                return 6088;
            case PavedPO:
                return 6089;
            case GullyPO:
                return 6090;
            case PavedIrD:
                return 6091;
            case Forest:
                return 6092;
            case CactusPatch:
                return 6093;
            case OliveGrove:
                return 6094;
            case Hillock:
                return 6095;
            case SandDuneH:
                return 6096;
            case SandDuneL:
                return 6097;
            case Lumberyard:
                return 6098;
            case GullyOrchard:
                return 6099;
            case HillockSummit:
                return 6103;
            case WoodRubbleFallen:
                return 6101;
            case StoneRubbleFallen:
                return 6102;
            case GrdLRR:
                return 6111;
            case RailCar:
                return 6112;
            case WreckedRC:
                return 6113;
            case GuttedRC:
                return 6114;
            case CitySquare:
                return 6115;
            case CitySquareShellhole:
                return 6116;
            case CitySquareManShell:
                return 6119;
            case Fountain:
                return 6124;
            case GrdLRRMan:
                return 6125;
            case OrchardPvRoadMan:
                return 6126;
            case StorageTanks:
                return 6127;
            case Canal:
                return 6128;
            case Pond:
                return 6129;
            case StreamDry:
                return 6131;
            case StreamDeep:
                return 6132;
            case StreamFlooded:
                return 6133;
            case OrchardStreamdry:
                return 6134;
            case OrchardStreamdeep:
                return 6135;
            case OrchardStreamflooded:
                return 6136;
            case StreamWoodsdry:
                return 6137;
            case streamwoodsdeep:
                return 6138;
            case Streamwoodsflooded:
                return 6139;
            case StreamPineWoodsdry:
                return 6140;
            case streamPinewoodsdeep:
                return 6141;
            case StreamPinewoodsflooded:
                return 6142;
            case StreamBrushdry:
                return 6143;
            case streambrushdeep:
                return 6144;
            case Streambrushflooded:
                return 6145;
            case Wadi:
                return 6146;
            case Hammada:
                return 6147;
            case SandTrack:
                return 6148;
            case HammadaTrack:
                return 6149;
            case Deir:
                return 6150;
            case Track:
                return 6151;
            case HillockTrack:
                return 6152;
            case DeirTrack:
                return 6153;
            case Scrub:
                return 6154;
            case ScrubTrack:
                return 6155;
            case HillockSummitTrack:
                return 6156;
            case SandScrub:
                return 6157;
            case SandScrubTrack:
                return 6158;
            case SandDuneLTrack:
                return 6159;
            case SandDuneHTrack:
                return 6160;
            case Mausoleum:
                return 6161;
            case Camp:
                return 6162;
            case DesertCluster:
                return 6163;  //F12.43
            case BrkCrag:
                return 6164;
            case BrokenGround:
                return 6165;
            case SteppeBrush:
                return 6166;
            case SteppeWoods:
                return 6167;
            case SteppeGrain:
                return 6168;
            case ExcavDitch:
                return 6169;
            case BambooPath:
                return 6170;
            case JunglePath:
                return 6171;
            case DenseJunglePath:
                return 6172;
            //case 6173:
            //return WoodDebris;
            case BeachSlightSoft:
                return 6174;
            case BeachModerateSoft:
                return 6175;
            case BeachSteepSoft:
                return 6176;
            case Sandbar:
                return 6177;
            case BeachSlightHard:
                return 6178;
            case BeachModerateHard:
                return 6179;
            case BeachSteepHard:
                return 6180;
            case OceanShallow:
                return 6181;
            case ExposedReef:
                return 6182;
            case SubmergedReef:
                return 6183;
            case JungleDebris:
                return 6184; //SC2
            case CordoroyRoads:
                return 6185;  //SC4
            case StreamShallowJungleDebris:
                return 6186;
            case RiverBrush:
                return 6187;
            case BrushUnpavedRd:
                return 6188;
            case BrushIrrD:
                return 6189;
            case IrrDVineyard:
                return 6190;
            //case 6191:
            //return WoodRubble;
            case PineForest:
                return 6192;
            case CulvertOG:
                return 6193;
            case CulvertPvRD:
                return 6194;
            case CulStrPvRd:
                return 6195;
            case CommandBunker:
                return 6234;
            case IslComBunker:
                return 6235;
            case GunEmplacement:
                return 6236;
            case BRTTower:
                return 6237;
            case OneMarketStone:
                return 6247;
            case OneMarketWood:
                return 6248;
            case PBTower:
                return 6254;
            case SingleSteeple:
                return 6255;
            case OneSteeple:
                return 6256;
            case TwoSteeple:
                return 6257;
            case PartCol1:
                return 6261;
            case PartCol15:
                return 6262;
            case Manhole:
                return 6273;
            case All:
                return 5999;
            case NA:
                return 9999;
            case NonStairBldg:
                return 6301;
            case HindranceHex:
                return 6302;
            case HindranceFeature:
                return 6303;
            case Shellholetype:
                return 6304;
            case Creststatustype:
                return 6305;
            case Bypassable:
                return 6306;
            case Smoketype:
                return 6307;
            case RoadOGtype:
                return 6308;
            case Manholetype:
                return 6309;
            case Factorytype:
                return 6310;
            case Rooflesstype:
                return 6311;
            case Cellartype:
                return 6312;
            case SplitLeveltype:
                return 6313;
            case Buildingtype:
                return 6314;
            case Pillboxtype:
                return 6315;
            case IntBuildtype:
                return 6316;
            case Rubbletype:
                return 6317;
            case FortBuildtype:
                return 6318;
            case bridgetype:
                return 6319;
            case Marshtype:
                return 6320;
            case ShallowStreamtype:
                return 6321;
            case DeepStreamtype:
                return 6322;
            case WaterObstacletype:
                return 6323;
            case Blazetype:
                return 6324;
            case towertype:
                return 6325;
            case Roadtype:
                return 6326;
            case OBAtype:
                return 6327;
            case HardSurftype:
                return 6328;
            case HasStairs:
                return 6329;
            case Burnabletype:
                return 6330;
            case Roof:
                return 6701;
            case StoneBuilding1stLevel:
                return 6702;
            case StoneBuilding2ndLevel:
                return 6703;
            case StoneBuilding3rdLevel:
                return 6704;
            case StoneCellar:
                return 6705;
            case InCave:
                return 6706;
            case BeneathBridge:
                return 6707;
            case Sewer:
                return 6708;
            case BeneathPier:
                return 6711;
            case StoneBuildingGroundlevel:
                return 6712;
            case BunkUnder:
                return 6713;
            case Tunnel:
                return 6714;
            case Huts:
                return 6715;
            case FortStoneGrd:
                return 6730;
            case FortSTone1st:
                return 6731;
            case FortStone2nd:
                return 6732;
            case FortSTone3rd:
                return 6733;
            case FortWoodGrd:
                return 6734;
            case FortWood1st:
                return 6735;
            case FortWood2nd:
                return 6736;
            case FortWood3rd:
                return 6737;
            case Pill1571:
                return 15001;
            case Pill1572:
                return 15002;
            case Pill1573:
                return 15003;
            case Pill1574:
                return 15004;
            case Pill1575:
                return 15005;
            case Pill1570:
                return 15006;
            case Pill1351:
                return 15007;
            case Pill1352:
                return 15008;
            case Pill1353:
                return 15009;
            case Pill1354:
                return 15010;
            case Pill1355:
                return 15011;
            case Pill1350:
                return 15012;
            case Pill2351:
                return 15013;
            case Pill2352:
                return 15014;
            case Pill2353:
                return 15015;
            case Pill2354:
                return 15016;
            case Pill2355:
                return 15017;
            case Pill2350:
                return 15018;
            case Pill2571:
                return 15019;
            case Pill2572:
                return 15020;
            case Pill2573:
                return 15021;
            case Pill2574:
                return 15022;
            case Pill2575:
                return 15023;
            case Pill2570:
                return 15024;
            case Pill3351:
                return 15025;
            case Pill3352:
                return 15026;
            case Pill3353:
                return 15027;
            case Pill3354:
                return 15028;
            case Pill3355:
                return 15029;
            case Pill3350:
                return 15030;
            case Pill3571:
                return 15031;
            case Pill3572:
                return 15032;
            case Pill3573:
                return 15033;
            case Pill3574:
                return 15034;
            case Pill3575:
                return 15035;
            case Pill3570:
                return 15036;
            case Pill2461:
                return 15037;
            case Pill2462:
                return 15038;
            case Pill2463:
                return 15039;
            case Pill2464:
                return 15040;
            case Pill2465:
                return 15041;
            case Pill2460:
                return 15042;
            case Pill1461:
                return 15043;
            case Pill1462:
                return 15044;
            case Pill1463:
                return 15045;
            case Pill1464:
                return 15046;
            case Pill1465:
                return 15047;
            case Pill1460:
                return 15048;
            case Bombprf:
                return 15897;
            case Cave146:
                return 10001;
            case CaveH46:
                return 10002;
            case Cave146LM1:
                return 10003;
            case CaveH46LM1:
                return 10004;
            case Cave146L0:
                return 10005;
            case CaveH46L0:
                return 10006;
            case Cave146L1:
                return 10007;
            case CaveH46L1:
                return 10008;
            case Cave146L2:
                return 10009;
            case CaveH46L2:
                return 10010;
            case Cave146L3:
                return 10011;
            case CaveH46L3:
                return 10012;
            case Cave146L4:
                return 10013;
            case CaveH46L4:
                return 10014;
            case WoodsTB:
                return 10030;
            case MineTB:
                return 10031;
            case StoneRubbleTB:
                return 10032;
            case PineWTB:
                return 10033;
            case JungleTB:
                return 10034;
            case DenseJungleTB:
                return 10035;
            case DebrisTB:
                return 10036;
            case WoodRubbleTB:
                return 10037;
            case WoodRubbleFallTB:
                return 10038;
            case StoneRubbleFallTB:
                return 10039;
            case OneFactRooflessMan:
                return 10040;
            case StBr14:
                return 30001;
            case StBr25:
                return 30002;
            case StBr30:
                return 30003;
            case WdBr14:
                return 30004;
            case WdBr25:
                return 30005;
            case WdBr30:
                return 30006;
            case SStBr14:
                return 30007;
            case SStBr25:
                return 30008;
            case SStBr30:
                return 30009;
            case SWdBr14:
                return 30010;
            case SWdBr25:
                return 30011;
            case SWdBr30:
                return 30012;
            case PoBr14:
                return 30013;
            case PoBr25:
                return 30014;
            case PoBr30:
                return 30015;
            case FoBr:
                return 30016;
            default:
                return 0;
        }
    }

    public Constantvalues.Location ConverttoLocationtypefromVASLLocation(Location SeeLOSLoc) {

        if ((SeeLOSLoc.getTerrain().getName()).equals("Ocean")) {
            return Constantvalues.Location.Ocean;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Open Ground")) {
            return Constantvalues.Location.OpenGround;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.River;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Marsh;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Brush;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Streamshallow;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Ford;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PineWoods;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Woods")) {
            return Constantvalues.Location.Woods;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Crag;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Airfield;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Mudflats;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Orchard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardPavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardUnpavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Dirt Road")) {
            return Constantvalues.Location.UnpavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TrailWoods;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TrailBrush;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TrailPineWoods;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodsPavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodsUnpavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PineWoodsPavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PineWoodsUnpavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GullyBrush;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GullyWoods;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Graveyard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SunkenPavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardStreamshallow;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamWoodsshallow;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamPineWoodsshallow;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamBrushshallow;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SunkenUnpvRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StratLoc;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Grain")) {
            return Constantvalues.Location.Grainfield;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Gully;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodRubble;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneRubble;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodDebris;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneDebris;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Shellhole;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Jungle;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DenseJungle;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Bamboo;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PalmTrees;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.Gully;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Kunai;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Swamp;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.RicePaddiesdrained;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.RicePaddiesIrrigated;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.RicePaddiesInSeason;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Panjis;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveComplex;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Beaches;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PalmTreesUnpavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TrailJungle;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TrailDenseJungle;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.JungleUnpavedRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DenseJungleUnpaved;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TrailBamboo;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Trail;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Trailpalmtrees;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GullyShell;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PalmDebris;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PalmDebrisPalm;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GullyUnpavedBridge;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PierNoLoc;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SunkenLane;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Passage;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PavedIntersectionManhole;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.ElevRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.StreamBrushshallow;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.SunkenUnpvRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pier;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.Grainfield;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Reef;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Tetrahedron;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Tetrawire;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.WoodDebris;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.StoneDebris;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.Shellhole;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Vineyard;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.SunkenUnpvRoad;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.IrrigDitch;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PartOrch;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.IrDPOrch;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.UnPavedIrDPO;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.UnPavedIrD;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.UnPavedPO;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PavedPO;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GullyPO;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PavedIrD;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Forest;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CactusPatch;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OliveGrove;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Hillock;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandDuneH;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandDuneL;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Lumberyard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GullyOrchard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HillockSummit;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodRubbleFallen;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneRubbleFallen;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GrdLRR;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.RailCar;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WreckedRC;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GuttedRC;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CitySquare;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CitySquareShellhole;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CitySquareManShell;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Fountain;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GrdLRRMan;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardPvRoadMan;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StorageTanks;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Canal;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pond;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamDry;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamDeep;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamFlooded;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardStreamdry;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardStreamdeep;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OrchardStreamflooded;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamWoodsdry;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.streamwoodsdeep;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Streamwoodsflooded;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamPineWoodsdry;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.streamPinewoodsdeep;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamPinewoodsflooded;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamBrushdry;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.streambrushdeep;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Streambrushflooded;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Wadi;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Hammada;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HammadaTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Deir;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Track;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HillockTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DeirTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Scrub;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.ScrubTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HillockSummitTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandScrub;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandScrubTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandDuneLTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SandDuneHTrack;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Mausoleum;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Camp;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DesertCluster;  //D12.43
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BrkCrag;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BrokenGround;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SteppeBrush;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SteppeWoods;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SteppeGrain;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.ExcavDitch;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BambooPath;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.JunglePath;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DenseJunglePath;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.WoodDebris;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeachSlightSoft;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeachModerateSoft;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeachSteepSoft;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Sandbar;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeachSlightHard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeachModerateHard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeachSteepHard;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OceanShallow;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.ExposedReef;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SubmergedReef;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.JungleDebris; //SC2
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CordoroyRoads;  //SC4
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StreamShallowJungleDebris;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.RiverBrush;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BrushUnpavedRd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BrushIrrD;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.IrrDVineyard;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //return Constantvalues.Location.WoodRubble;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PineForest;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CulvertOG;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CulvertPvRD;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CulStrPvRd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CommandBunker;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.IslComBunker;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.GunEmplacement;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BRTTower;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OneMarketStone;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OneMarketWood;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PBTower;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SingleSteeple;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OneSteeple;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.TwoSteeple;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PartCol1;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PartCol15;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Manhole;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.All;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.NA;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.NonStairBldg;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HindranceHex;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HindranceFeature;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Shellholetype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Creststatustype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Bypassable;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Smoketype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.RoadOGtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Manholetype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Factorytype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Rooflesstype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cellartype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SplitLeveltype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Buildingtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pillboxtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.IntBuildtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Rubbletype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortBuildtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.bridgetype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Marshtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.ShallowStreamtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DeepStreamtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WaterObstacletype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Blazetype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.towertype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Roadtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OBAtype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HardSurftype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.HasStairs;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Burnabletype;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Roof;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Stone Building")) {
            return Constantvalues.Location.StoneBuildingGroundlevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Stone Building, 1 Level")) {
            return Constantvalues.Location.StoneBuilding1stLevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Stone Building, 2 Level")) {
            return Constantvalues.Location.StoneBuilding2ndLevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneBuilding3rdLevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneCellar;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Wooden Building")) {
            return Constantvalues.Location.WoodBuildingGroundlevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Wooden Building, 1 Level")) {
            return Constantvalues.Location.WoodBuilding1stLevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("Wooden Building, 2 Level")) {
            return Constantvalues.Location.WoodBuilding2ndLevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodBuilding3rdLevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodCellar;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.InCave;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeneathBridge;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Sewer;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BeneathPier;
            //} else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            //    return Constantvalues.Location.BuildingGroundlevel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.BunkUnder;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Tunnel;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Huts;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortStoneGrd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortSTone1st;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortStone2nd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortSTone3rd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortWoodGrd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortWood1st;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortWood2nd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FortWood3rd;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1571;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1572;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1573;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1574;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1575;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1570;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1351;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1352;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1353;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1354;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1355;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1350;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2351;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2352;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2353;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2354;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2355;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2350;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2571;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2572;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2573;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2574;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2575;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2570;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3351;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3352;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3353;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3354;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3355;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3350;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3571;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3572;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3573;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3574;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3575;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill3570;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2461;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2462;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2463;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2464;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2465;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill2460;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1461;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1462;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1463;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1464;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1465;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Pill1460;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Bombprf;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146LM1;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46LM1;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146L0;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46L0;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146L1;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46L1;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146L2;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46L2;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146L3;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46L3;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.Cave146L4;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.CaveH46L4;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodsTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.MineTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneRubbleTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PineWTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.JungleTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DenseJungleTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.DebrisTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodRubbleTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WoodRubbleFallTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StoneRubbleFallTB;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.OneFactRooflessMan;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StBr14;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StBr25;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.StBr30;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WdBr14;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WdBr25;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.WdBr30;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SStBr14;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SStBr25;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SStBr30;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SWdBr14;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SWdBr25;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.SWdBr30;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PoBr14;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PoBr25;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.PoBr30;
        } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
            return Constantvalues.Location.FoBr;
        } else {
            return Constantvalues.Location.NA;
        }
        return Constantvalues.Location.NA;
    }

    public Constantvalues.Hexside ConverttoHexsideType(int databasevalue) {
        switch (databasevalue) {
            case 7401:
                return Constantvalues.Hexside.GullyUp;
            case 7402:
                return Constantvalues.Hexside.GullyDown;
            case 7403:
                return Constantvalues.Hexside.GullyUpSlope;
            case 7404:
                return Constantvalues.Hexside.GullyDownSlope;
            case 7405:
                return Constantvalues.Hexside.GullyUpWire;
            case 7406:
                return Constantvalues.Hexside.GullyDownWire;
            case 7407:
                return Constantvalues.Hexside.AttWoodsGullyUp;
            case 7408:
                return Constantvalues.Hexside.AttWoodsGullyDown;
            case 7409:
                return Constantvalues.Hexside.AttPWdsGullyUp;
            case 7410:
                return Constantvalues.Hexside.AttPWdsGullyDn;
            case 7411:
                return Constantvalues.Hexside.GullyUpHedge;
            case 7412:
                return Constantvalues.Hexside.GullyDownHedge;
            case 7413:
                return Constantvalues.Hexside.GullyUpWall;
            case 7414:
                return Constantvalues.Hexside.GullyDownWall;
            case 7415:
                return Constantvalues.Hexside.GullyUpTrail;
            case 7416:
                return Constantvalues.Hexside.GullyDownTrail;
            case 7417:
                return Constantvalues.Hexside.AttPwdsCrestUpGully;
            case 7418:
                return Constantvalues.Hexside.AttPWdsCrestDnGully;
            case 7419:
                return Constantvalues.Hexside.CrestUpGullyDown;
            case 7420:
                return Constantvalues.Hexside.CrestDownGullyUp;
            case 7421:
                return Constantvalues.Hexside.AttPWdsCrestUpGullyDn;
            case 7422:
                return Constantvalues.Hexside.AttPWdsCrestDnGullyUp;
            case 7423:
                return Constantvalues.Hexside.GullyUpSlopeWire;
            case 7424:
                return Constantvalues.Hexside.GullyDownSlopeWire;
            case 7425:
                return Constantvalues.Hexside.GullyUpUnPvRd;
            case 7426:
                return Constantvalues.Hexside.GullyDnUnPvRd;
            case 7427:
                return Constantvalues.Hexside.GullyUpSlopeHedge;
            case 7428:
                return Constantvalues.Hexside.GullyDnSlopeHedge;
            case 7429:
                return Constantvalues.Hexside.GullyUpGullyDown;
            case 7430:
                return Constantvalues.Hexside.GullyUpGullyDownHedge;
            case 7431:
                return Constantvalues.Hexside.CrestUpGullyHedge;
            case 7432:
                return Constantvalues.Hexside.CrestDnGullyHedge;
            case 7433:
                return Constantvalues.Hexside.GullyDnCLUpHedge;
            case 7434:
                return Constantvalues.Hexside.GullyUpCLDnHedge;
            case 7435:
                return Constantvalues.Hexside.CLUpGullySlopehedge;
            case 7436:
                return Constantvalues.Hexside.CLDnGullySlopeHedge;
            case 7437:
                return Constantvalues.Hexside.AttWdsCrestUpGullyDn;
            case 7438:
                return Constantvalues.Hexside.AttWdsCrestDnGullyUp;
            case 7439:
                return Constantvalues.Hexside.CrestUpGullyWire;
            case 7440:
                return Constantvalues.Hexside.CrestDnGullyWire;
            case 7441:
                return Constantvalues.Hexside.AttWoodsCrestUpGully;
            case 7442:
                return Constantvalues.Hexside.AttWoodsCrestDnGully;
            case 7443:
                return Constantvalues.Hexside.SlopeUpTrail;
            case 7444:
                return Constantvalues.Hexside.SlopeDnTrail;
            case 7445:
                return Constantvalues.Hexside.CulvertUp;
            case 7446:
                return Constantvalues.Hexside.CulvertDn;
            case 7447:
                return Constantvalues.Hexside.CLUpWire;
            case 7448:
                return Constantvalues.Hexside.CLDownWire;
            case 7449:
                return Constantvalues.Hexside.CLUpWireSlope;
            case 7450:
                return Constantvalues.Hexside.CLDnWireSlope;
            case 7451:
                return Constantvalues.Hexside.AttachedWoods;
            case 7452:
                return Constantvalues.Hexside.AttachedPineWoods;
            case 7453:
                return Constantvalues.Hexside.AttPineWCrestUp;
            case 7454:
                return Constantvalues.Hexside.AttPineWCrestDown;
            case 7455:
                return Constantvalues.Hexside.AttWoodsCrestUp;
            case 7456:
                return Constantvalues.Hexside.AttWoodsCrestDown;
            case 7457:
                return Constantvalues.Hexside.SlopeUpPWUnpavR;
            case 7458:
                return Constantvalues.Hexside.SlopeDownPWUnpavR;
            case 7459:
                return Constantvalues.Hexside.CrestUpStream;
            case 7460:
                return Constantvalues.Hexside.CrestDownStream;
            case 7461:
                return Constantvalues.Hexside.CrestUpTrailPWoods;
            case 7462:
                return Constantvalues.Hexside.CrestDownTrailPWoods;
            case 7463:
                return Constantvalues.Hexside.CrestUpSlopeWire;
            case 7464:
                return Constantvalues.Hexside.CrestDownSlopeWire;
            case 7465:
                return Constantvalues.Hexside.AttWoodsCrestUpDbl;
            case 7466:
                return Constantvalues.Hexside.AttWoodsCrestDownDbl;
            case 7467:
                return Constantvalues.Hexside.SlopeUpTrailPWoods;
            case 7468:
                return Constantvalues.Hexside.SlopeDownTrailPWoods;
            case 7469:
                return Constantvalues.Hexside.CLUpNarrowPaved;
            case 7470:
                return Constantvalues.Hexside.CLDownNarrowPaved;
            case 7471:
                return Constantvalues.Hexside.WoodsStream;
            case 7472:
                return Constantvalues.Hexside.CLupdblhedge;
            case 7473:
                return Constantvalues.Hexside.CLDowndblHedge;
            case 7474:
                return Constantvalues.Hexside.CliffDownHedge;
            case 7475:
                return Constantvalues.Hexside.CliffUpHedge;
            case 7476:
                return Constantvalues.Hexside.CliffDoubleUPHedge;
            case 7477:
                return Constantvalues.Hexside.CliffDoubleDownHedge;
            case 7478:
                return Constantvalues.Hexside.CrestDownSlopeUpPvRd;
            case 7479:
                return Constantvalues.Hexside.CrestUpSlopeDwnPvRd;
            case 7480:
                return Constantvalues.Hexside.CrestUpTrail;
            case 7481:
                return Constantvalues.Hexside.CrestDownTrail;
            case 7482:
                return Constantvalues.Hexside.Cave;
            case 7483:
                return Constantvalues.Hexside.TrailBreak;
            case 75881:
                return Constantvalues.Hexside.Roadblock0;
            case 75882:
                return Constantvalues.Hexside.Roadblock1;
            case 75883:
                return Constantvalues.Hexside.Roadblock2;
            case 75884:
                return Constantvalues.Hexside.Roadblock3;
            case 75885:
                return Constantvalues.Hexside.Roadblock4;
            case 75886:
                return Constantvalues.Hexside.Roadblock6;
            case 7485:
                return Constantvalues.Hexside.CrestNarrowPaved;
            case 7486:
                return Constantvalues.Hexside.CrestUpNarrowPaved;
            case 7487:
                return Constantvalues.Hexside.CrestDownNarrowPaved;
            case 7488:
                return Constantvalues.Hexside.AttPineWCrestUpDbl;
            case 7489:
                return Constantvalues.Hexside.AttPineWCrestDnDbl;
            case 7490:
                return Constantvalues.Hexside.crestdndblslopehedge;
            case 7491:
                return Constantvalues.Hexside.crestupdblslopehedge;
            case 7492:
                return Constantvalues.Hexside.CLDownDblslopehedge;
            case 7493:
                return Constantvalues.Hexside.CLUpDblSlopehedge;
            case 7494:
                return Constantvalues.Hexside.crestdowndblwire;
            case 7495:
                return Constantvalues.Hexside.crestupdblwire;
            case 7496:
                return Constantvalues.Hexside.AttWdsCrestDnStream;
            case 7497:
                return Constantvalues.Hexside.AttWdsCrestUpStream;
            case 7498:
                return Constantvalues.Hexside.PineWStream;
            case 7499:
                return Constantvalues.Hexside.RiceBank;
            case 7500:
                return Constantvalues.Hexside.NoTerrain;
            case 7501:
                return Constantvalues.Hexside.Bocage;
            case 7502:
                return Constantvalues.Hexside.Hedge;
            case 7503:
                return Constantvalues.Hexside.Wall;
            case 7504:
                return Constantvalues.Hexside.Wire;
            case 7505:
                return Constantvalues.Hexside.SlopeDown;
            case 7506:
                return Constantvalues.Hexside.SlopeUp;
            case 7507:
                return Constantvalues.Hexside.CrestUp;
            case 7508:
                return Constantvalues.Hexside.CrestDown;
            case 7509:
                return Constantvalues.Hexside.PineWoodsPavedRoad;
            case 7510:
                return Constantvalues.Hexside.PineWoodsUnpavedRoad;
            case 7511:
                return Constantvalues.Hexside.River;
            case 7512:
                return Constantvalues.Hexside.Stream;
            case 7513:
                return Constantvalues.Hexside.WoodsPavedRoad;
            case 7514:
                return Constantvalues.Hexside.WoodsUnpavedRoad;
            case 7515:
                return Constantvalues.Hexside.PavedRoad;
            case 7516:
                return Constantvalues.Hexside.UnpavedRoad;
            case 7517:
                return Constantvalues.Hexside.TrailWoods;
            case 7518:
                return Constantvalues.Hexside.TrailBrush;
            case 7519:
                return Constantvalues.Hexside.TrailPineWoods;
            case 7520:
                return Constantvalues.Hexside.CrestDownPavedRoad;
            case 7521:
                return Constantvalues.Hexside.CrestDownUnpavedRoad;
            case 7522:
                return Constantvalues.Hexside.CrestUpPavedRoad;
            case 7523:
                return Constantvalues.Hexside.CrestUpUnpavedRoad;
            case 7524:
                return Constantvalues.Hexside.CrestDownHedge;
            case 7525:
                return Constantvalues.Hexside.CrestUpHedge;
            case 7526:
                return Constantvalues.Hexside.CrestDownWall;
            case 7527:
                return Constantvalues.Hexside.CrestUpWall;
            case 7528:
                return Constantvalues.Hexside.CrestDownSlope;
            case 7529:
                return Constantvalues.Hexside.CrestUpSlope;
            case 7530:
                return Constantvalues.Hexside.CrestDownSlopeHedge;
            case 7531:
                return Constantvalues.Hexside.CrestUpSlopeHedge;
            case 7532:
                return Constantvalues.Hexside.CrestDownWire;
            case 7533:
                return Constantvalues.Hexside.CrestUpWire;
            case 7534:
                return Constantvalues.Hexside.SlopeDownHedge;
            case 7535:
                return Constantvalues.Hexside.SlopeUpHedge;
            case 7536:
                return Constantvalues.Hexside.SlopeDownWall;
            case 7537:
                return Constantvalues.Hexside.SlopeUpWall;
            case 7538:
                return Constantvalues.Hexside.SlopeDownWire;
            case 7539:
                return Constantvalues.Hexside.SlopeUpWire;
            case 7540:
                return Constantvalues.Hexside.SlopeDownPavedRoad;
            case 7541:
                return Constantvalues.Hexside.SlopeUpPavedRoad;
            case 7542:
                return Constantvalues.Hexside.SlopeDownUnpavedRoad;
            case 7543:
                return Constantvalues.Hexside.SlopeUpUnpavedRoad;
            case 7544:
                return Constantvalues.Hexside.SunkenNonroad;
            case 7545:
                return Constantvalues.Hexside.SunkenNonroadWire;
            case 7546:
                return Constantvalues.Hexside.SunkenNonroadHedge;
            case 7547:
                return Constantvalues.Hexside.NarrowPavedRoad;
            case 7548:
                return Constantvalues.Hexside.NarrowUnpavedRoad;
            case 7549:
                return Constantvalues.Hexside.Cliff;
            case 7550:
                return Constantvalues.Hexside.CactusHedge;
            case 7551:
                return Constantvalues.Hexside.RailwayEmb;
            case 7552:
                return Constantvalues.Hexside.Seawall;
            case 7553:
                return Constantvalues.Hexside.CrestDownSlopeWall;
            case 7554:
                return Constantvalues.Hexside.CrestUpSlopeWall;
            case 7555:
                return Constantvalues.Hexside.CrestDownWoodsPavedRoad;
            case 7556:
                return Constantvalues.Hexside.CrestUpWoodsPavedRoad;
            case 7557:
                return Constantvalues.Hexside.CrestDownWoodsUnpavedRoad;
            case 7558:
                return Constantvalues.Hexside.CrestUpWoodsUnpavedRoad;
            case 7559:
                return Constantvalues.Hexside.CrestUpTrailWoods;
            case 7560:
                return Constantvalues.Hexside.CrestDownTrailWoods;
            case 7562:
                return Constantvalues.Hexside.HedgeUnpavedRoad;
            case 7563:
                return Constantvalues.Hexside.WallUnpavedRoad;
            case 7564:
                return Constantvalues.Hexside.CLUpWall;
            case 7565:
                return Constantvalues.Hexside.CLDownWall;
            case 7566:
                return Constantvalues.Hexside.CLUpHedge;
            case 7567:
                return Constantvalues.Hexside.CLDownHedge;
            case 7568:
                return Constantvalues.Hexside.CLUpWPO;
            case 7569:
                return Constantvalues.Hexside.CLDnWPO;
            case 7570:
                return Constantvalues.Hexside.Gully;
            case 7571:
                return Constantvalues.Hexside.CLUpWallSlope;
            case 7572:
                return Constantvalues.Hexside.CLDnWallSlope;
            case 7573:
                return Constantvalues.Hexside.CLUpWallSlopePO;
            case 7574:
                return Constantvalues.Hexside.CLDnWallSlopePO;
            case 7575:
                return Constantvalues.Hexside.PartOrch;
            case 7576:
                return Constantvalues.Hexside.Jetty;
            case 7577:
                return Constantvalues.Hexside.CLUpHedgeSlope;
            case 7578:
                return Constantvalues.Hexside.CLDnHedgeSlope;
            case 7579:
                return Constantvalues.Hexside.CrestUpPO;
            case 7580:
                return Constantvalues.Hexside.CrestDownPO;
            case 7581:
                return Constantvalues.Hexside.WirePO;
            case 7582:
                return Constantvalues.Hexside.CrestUpDouble;
            case 7583:
                return Constantvalues.Hexside.CrestDnDouble;
            case 7584:
                return Constantvalues.Hexside.SlopeUpPO;
            case 7585:
                return Constantvalues.Hexside.SlopeDnPO;
            case 7586:
                return Constantvalues.Hexside.CrestUpGully;
            case 7587:
                return Constantvalues.Hexside.CrestDnGully;
            case 7588:
                return Constantvalues.Hexside.Roadblock;
            case 7589:
                return Constantvalues.Hexside.WallPO;
            case 7590:
                return Constantvalues.Hexside.HedgePO;
            case 7598:
                return Constantvalues.Hexside.Trail;
            case 7599:
                return Constantvalues.Hexside.ByPassNA;
            case 7591:
                return Constantvalues.Hexside.RailCar;
            case 7592:
                return Constantvalues.Hexside.AttachedTerrain;
            case 7593:
                return Constantvalues.Hexside.PierLanding;
            case 7594:
                return Constantvalues.Hexside.AttachedBldg;
            case 7595:
                return Constantvalues.Hexside.IntFactside;
            case 7596:
                return Constantvalues.Hexside.IntBldgWall;
            case 7597:
                return Constantvalues.Hexside.Rowhouseside;
            case 7601:
                return Constantvalues.Hexside.CliffDownGullyDown;
            case 7602:
                return Constantvalues.Hexside.CliffUpGullyUp;
            case 7603:
                return Constantvalues.Hexside.CliffUp;
            case 7604:
                return Constantvalues.Hexside.CliffDown;
            case 7605:
                return Constantvalues.Hexside.crestupGullydnHdge;
            case 7606:
                return Constantvalues.Hexside.CrestDnGullyUpHdge;
            case 7607:
                return Constantvalues.Hexside.GullyUpSlopeDown;
            case 7608:
                return Constantvalues.Hexside.GullyDnSlopeUp;
            case 7609:
                return Constantvalues.Hexside.CLupGullydnHdge;
            case 7610:
                return Constantvalues.Hexside.CLDnGullyUpHdge;
            case 7611:
                return Constantvalues.Hexside.CrestUpGullySide;
            case 7612:
                return Constantvalues.Hexside.CrestDownGullySide;
            case 7613:
                return Constantvalues.Hexside.AttWoodsGullySide;
            case 7614:
                return Constantvalues.Hexside.Trench;
            case 7615:
                return Constantvalues.Hexside.TrenchCrestUp;
            case 7616:
                return Constantvalues.Hexside.TrenchCrestDown;
            case 7617:
                return Constantvalues.Hexside.TrenchHedge;
            case 7618:
                return Constantvalues.Hexside.TrenchWall;
            default:
                return Constantvalues.Hexside.NoTerrain;
        }
    }

    public Constantvalues.Hexside ConverttoHexside(Terrain Passterrain) {
        if (Passterrain.getName().equals("Open Ground")) {
            return Constantvalues.Hexside.NoTerrain;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Wall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDownSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDownWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsGullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsGullyDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPWdsGullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPWdsGullyDn;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDownWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpTrail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDownTrail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPwdsCrestUpGully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPWdsCrestDnGully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpGullyDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownGullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPWdsCrestUpGullyDn;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPWdsCrestDnGullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpSlopeWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDownSlopeWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpUnPvRd;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDnUnPvRd;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpSlopeHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDnSlopeHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpGullyDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpGullyDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpGullyHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDnGullyHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDnCLUpHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpCLDnHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpGullySlopehedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnGullySlopeHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWdsCrestUpGullyDn;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWdsCrestDnGullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpGullyWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDnGullyWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsCrestUpGully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsCrestDnGully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpTrail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDnTrail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CulvertUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CulvertDn;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDownWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpWireSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnWireSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttachedWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttachedPineWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPineWCrestUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPineWCrestDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsCrestUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsCrestDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpPWUnpavR;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownPWUnpavR;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpStream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownStream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpTrailPWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownTrailPWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpSlopeWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownSlopeWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsCrestUpDbl;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsCrestDownDbl;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpTrailPWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownTrailPWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpNarrowPaved;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDownNarrowPaved;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.WoodsStream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLupdblhedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDowndblHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffUpHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffDoubleUPHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffDoubleDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownSlopeUpPvRd;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpSlopeDwnPvRd;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpTrail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownTrail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Cave;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrailBreak;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock0;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock1;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock2;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock3;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock4;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock6;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestNarrowPaved;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpNarrowPaved;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownNarrowPaved;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPineWCrestUpDbl;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttPineWCrestDnDbl;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.crestdndblslopehedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.crestupdblslopehedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDownDblslopehedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpDblSlopehedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.crestdowndblwire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.crestupdblwire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWdsCrestDnStream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWdsCrestUpStream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.PineWStream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.RiceBank;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.NoTerrain;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Bocage;
        } else if (Passterrain.getName().equals("Hedge")) {
            return Constantvalues.Hexside.Hedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Wall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Wire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.PineWoodsPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.PineWoodsUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.River;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Stream;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.WoodsPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.WoodsUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.PavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.UnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrailWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrailBrush;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrailPineWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownSlopeHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpSlopeHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDownUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SunkenNonroad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SunkenNonroadWire;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SunkenNonroadHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.NarrowPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.NarrowUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Cliff;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CactusHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.RailwayEmb;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Seawall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownSlopeWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpSlopeWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownWoodsPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpWoodsPavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownWoodsUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpWoodsUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpTrailWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownTrailWoods;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.HedgeUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.WallUnpavedRoad;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDownWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDownHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpWPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnWPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Gully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpWallSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnWallSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpWallSlopePO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnWallSlopePO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.PartOrch;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Jetty;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLUpHedgeSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnHedgeSlope;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.WirePO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpDouble;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDnDouble;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeUpPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.SlopeDnPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpGully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDnGully;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Roadblock;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.WallPO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.HedgePO;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Trail;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.ByPassNA;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.RailCar;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttachedTerrain;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.PierLanding;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttachedBldg;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.IntFactside;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.IntBldgWall;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Rowhouseside;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffDownGullyDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffUpGullyUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CliffDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.crestupGullydnHdge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDnGullyUpHdge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyUpSlopeDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.GullyDnSlopeUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLupGullydnHdge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CLDnGullyUpHdge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestUpGullySide;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.CrestDownGullySide;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.AttWoodsGullySide;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.Trench;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrenchCrestUp;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrenchCrestDown;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrenchHedge;
        } else if (Passterrain.getName().equals("Wall")) {
            return Constantvalues.Hexside.TrenchWall;
        } else {
            return Constantvalues.Hexside.NoTerrain;
        }

    }

    public Constantvalues.AltPos ConverttoAltPosType(int databasevalue) {
        switch (databasevalue) {
            case 6901:
                return Constantvalues.AltPos.AboveWire;
            case 6902:
                return Constantvalues.AltPos.WallAdv;
            case 6903:
                return Constantvalues.AltPos.InFoxhole;
            case 6904:
                return Constantvalues.AltPos.InTrench;
            case 6905:
                return Constantvalues.AltPos.OtherTerrainInHex;
            case 6906:
                return Constantvalues.AltPos.InSanger;
            case 6908:
                return Constantvalues.AltPos.AbovePanji;
            case 6909:
                return Constantvalues.AltPos.OnRoad;
            case 6911:
                return Constantvalues.AltPos.CrestStatus0;
            case 6912:
                return Constantvalues.AltPos.CrestStatus1;
            case 6913:
                return Constantvalues.AltPos.CrestStatus2;
            case 6914:
                return Constantvalues.AltPos.CrestStatus3;
            case 6915:
                return Constantvalues.AltPos.CrestStatus4;
            case 6916:
                return Constantvalues.AltPos.CrestStatus5;
            case 6917:
                return Constantvalues.AltPos.ExitedEntrench;
            case 6921:
                return Constantvalues.AltPos.ExitedCrest0;
            case 6922:
                return Constantvalues.AltPos.ExitedCrest1;
            case 6923:
                return Constantvalues.AltPos.ExitedCrest2;
            case 6924:
                return Constantvalues.AltPos.ExitedCrest3;
            case 6925:
                return Constantvalues.AltPos.ExitedCrest4;
            case 6926:
                return Constantvalues.AltPos.ExitedCrest5;
            case 6927:
                return Constantvalues.AltPos.Rider;
            case 6928:
                return Constantvalues.AltPos.Passenger;
            case 6931:
                return Constantvalues.AltPos.WACrestStatus0;
            case 6932:
                return Constantvalues.AltPos.WACrestStatus1;
            case 6933:
                return Constantvalues.AltPos.WACrestStatus2;
            case 6934:
                return Constantvalues.AltPos.WACrestStatus3;
            case 6935:
                return Constantvalues.AltPos.WACrestStatus4;
            case 6936:
                return Constantvalues.AltPos.WACrestStatus5;
            case 6937:
                return Constantvalues.AltPos.None;
            default:
                return Constantvalues.AltPos.None;
        }
    }

    public int ConvertAltPosTypetoInt(Constantvalues.AltPos altposvalue) {
        switch (altposvalue) {
            case AboveWire:
                return 6901;
            case WallAdv:
                return 6902;
            case InFoxhole:
                return 6903;
            case InTrench:
                return 6904;
            case OtherTerrainInHex:
                return 6905;
            case InSanger:
                return 6906;
            case AbovePanji:
                return 6908;
            case OnRoad:
                return 6909;
            case CrestStatus0:
                return 6911;
            case CrestStatus1:
                return 6912;
            case CrestStatus2:
                return 6913;
            case CrestStatus3:
                return 6914;
            case CrestStatus4:
                return 6915;
            case CrestStatus5:
                return 6916;
            case ExitedEntrench:
                return 6917;
            case ExitedCrest0:
                return 6921;
            case ExitedCrest1:
                return 6922;
            case ExitedCrest2:
                return 6923;
            case ExitedCrest3:
                return 6924;
            case ExitedCrest4:
                return 6925;
            case ExitedCrest5:
                return 6926;
            case Rider:
                return 6927;
            case Passenger:
                return 6928;
            case WACrestStatus0:
                return 6931;
            case WACrestStatus1:
                return 6932;
            case WACrestStatus2:
                return 6933;
            case WACrestStatus3:
                return 6934;
            case WACrestStatus4:
                return 6935;
            case WACrestStatus5:
                return 6936;
            case None:
                return 6937;
            default:
                return 6937;
        }
    }
    public int ConvertAltPosSidetohexsideInt(Constantvalues.AltPos altposvalue) {
        switch (altposvalue) {
            case CrestStatus0: case ExitedCrest0: case WACrestStatus0:
                return 0;
            case CrestStatus1: case ExitedCrest1: case WACrestStatus1:
                return 1;
            case CrestStatus2: case ExitedCrest2: case WACrestStatus2:
                return 2;
            case CrestStatus3: case ExitedCrest3: case WACrestStatus3:
                return 3;
            case CrestStatus4: case ExitedCrest4: case WACrestStatus4:
                return 4;
            case CrestStatus5: case ExitedCrest5: case WACrestStatus5:
                return 5;
            default:
                return -1;
        }
    }
    public Constantvalues.IFTResult ConverttoIFTResult(int databasevalue) {
        switch (databasevalue) {
            case 8601:
                return Constantvalues.IFTResult.KIA7;
            case 8602:
                return Constantvalues.IFTResult.KIA6;
            case 8603:
                return Constantvalues.IFTResult.KIA5;
            case 8604:
                return Constantvalues.IFTResult.KIA4;
            case 8605:
                return Constantvalues.IFTResult.KIA3;
            case 8606:
                return Constantvalues.IFTResult.KIA2;
            case 8607:
                return Constantvalues.IFTResult.KIA1;
            case 8608:
                return Constantvalues.IFTResult.K4;
            case 8609:
                return Constantvalues.IFTResult.K3;
            case 8610:
                return Constantvalues.IFTResult.K2;
            case 8611:
                return Constantvalues.IFTResult.K1;
            case 8612:
                return Constantvalues.IFTResult.MC4;
            case 8613:
                return Constantvalues.IFTResult.MC3;
            case 8614:
                return Constantvalues.IFTResult.MC2;
            case 8615:
                return Constantvalues.IFTResult.MC1;
            case 8616:
                return Constantvalues.IFTResult.NMC;
            case 8617:
                return Constantvalues.IFTResult.PTC;
            case 8618:
                return Constantvalues.IFTResult.NR;
            case 8620:
                return Constantvalues.IFTResult.Broken;
            case 8621:
                return Constantvalues.IFTResult.CR;
            case 8622:
                return Constantvalues.IFTResult.KIA;
            default:
                return Constantvalues.IFTResult.NR;
        }
    }

    public int ConvertIFTResulttoInt(Constantvalues.IFTResult iftResultvalue) {
        switch (iftResultvalue) {
            case KIA7:
                return 8601;
            case KIA6:
                return 8602;
            case KIA5:
                return 8603;
            case KIA4:
                return 8604;
            case KIA3:
                return 8605;
            case KIA2:
                return 8606;
            case KIA1:
                return 8607;
            case K4:
                return 8608;
            case K3:
                return 8609;
            case K2:
                return 8610;
            case K1:
                return 8611;
            case MC4:
                return 8612;
            case MC3:
                return 8613;
            case MC2:
                return 8614;
            case MC1:
                return 8615;
            case NMC:
                return 8616;
            case PTC:
                return 8617;
            case NR:
                return 8618;
            case Broken:
                return 8620;
            case CR:
                return 8621;
            case KIA:
                return 8622;
            default:
                return 8616;
        }
    }

    public Constantvalues.PersUnitResult ConverttoPersUnitResult(int passvalue) {
        switch (passvalue) {
            case 7001:
                return Constantvalues.PersUnitResult.Breaks;
            case 7002:
                return Constantvalues.PersUnitResult.Dies;
            case 7003:
                return Constantvalues.PersUnitResult.Pins;
            case 7004:
                return Constantvalues.PersUnitResult.Reduces;
            case 7005:
                return Constantvalues.PersUnitResult.ReducesBreaks;
            case 7006:
                return Constantvalues.PersUnitResult.DMs;
            case 7007:
                return Constantvalues.PersUnitResult.Fanatics;
            case 7008:
                return Constantvalues.PersUnitResult.Hardens;
            case 7009:
                return Constantvalues.PersUnitResult.Berserks;
            case 7010:
                return Constantvalues.PersUnitResult.Surrenders;
            case 7011:
                return Constantvalues.PersUnitResult.Replaces;
            case 7012:
                return Constantvalues.PersUnitResult.ReplacesReducesBreaks;
            case 7013:
                return Constantvalues.PersUnitResult.Wounds;
            case 7014:
                return Constantvalues.PersUnitResult.Substitutues;
            case 7015:
                return Constantvalues.PersUnitResult.ReplacesDMs;
            case 7016:
                return Constantvalues.PersUnitResult.Disrupts;
            case 7017:
                return Constantvalues.PersUnitResult.HeroCreation;
            case 7018:
                return Constantvalues.PersUnitResult.HeroHardens;
            case 7019:
                return Constantvalues.PersUnitResult.NoEffects;
            case 7021:
                return Constantvalues.PersUnitResult.ReducesHOB;
            case 7022:
                return Constantvalues.PersUnitResult.ReducesDies;
            case 7023:
                return Constantvalues.PersUnitResult.ReducesPins;
            case 7024:
                return Constantvalues.PersUnitResult.ReducesReplaces;
            case 7025:
                return Constantvalues.PersUnitResult.DisruptDMs;
            case 7026:
                return Constantvalues.PersUnitResult.StepReduces;
            case 7027:
                return Constantvalues.PersUnitResult.StepReducesHS;
            case 7028:
                return Constantvalues.PersUnitResult.ReplacesStepReduces;
            case 7029:
                return Constantvalues.PersUnitResult.ReplacesStepReducesHS;
            case 7030:
                return Constantvalues.PersUnitResult.RevealDummy;
            case 7031:
                return Constantvalues.PersUnitResult.RevealConUnitbySniper;
            case 7032:
                return Constantvalues.PersUnitResult.RevealAllDummy;
            case 7033:
                return Constantvalues.PersUnitResult.HeroicLdrCreation;
            case 7034:
                return Constantvalues.PersUnitResult.HeroicLdrHardens;
            case 7035:
                return Constantvalues.PersUnitResult.ReducesDMs;
            case 7036:
                return Constantvalues.PersUnitResult.GoodOrders;
            default:
                return null;
        }
    }

    public int ConvertPersUnitResulttoint(Constantvalues.PersUnitResult passvalue) {
        switch (passvalue) {
            case Breaks:
                return 7001;
            case Dies:
                return 7002;
            case Pins:
                return 7003;
            case Reduces:
                return 7004;
            case ReducesBreaks:
                return 7005;
            case DMs:
                return 7006;
            case Fanatics:
                return 7007;
            case Hardens:
                return 7008;
            case Berserks:
                return 7009;
            case Surrenders:
                return 7010;
            case Replaces:
                return 7011;
            case ReplacesReducesBreaks:
                return 7012;
            case Wounds:
                return 7013;
            case Substitutues:
                return 7014;
            case ReplacesDMs:
                return 7015;
            case Disrupts:
                return 7016;
            case HeroCreation:
                return 7017;
            case HeroHardens:
                return 7018;
            case NoEffects:
                return 7019;
            case ReducesHOB:
                return 7021;
            case ReducesDies:
                return 7022;
            case ReducesPins:
                return 7023;
            case ReducesReplaces:
                return 7024;
            case DisruptDMs:
                return 7025;
            case StepReduces:
                return 7026;
            case StepReducesHS:
                return 7027;
            case ReplacesStepReduces:
                return 7028;
            case ReplacesStepReducesHS:
                return 7029;
            case RevealDummy:
                return 7030;
            case RevealConUnitbySniper:
                return 7031;
            case RevealAllDummy:
                return 7032;
            case HeroicLdrCreation:
                return 7033;
            case HeroicLdrHardens:
                return 7034;
            case ReducesDMs:
                return 7035;
            case GoodOrders:
                return 7036;
            default:
                return 0;
        }
    }

    public boolean ConverttoBoolean(String Passstring) {
        if (Passstring.equals("true")) {
            return true;
        } else if (Passstring.equals("false")) {
            return false;
        } else {
            return false;
        }
    }

    public String ConvertBooleantoString(boolean Passboolean) {
        if (Passboolean) {
            return "true";
        } else {
            return "false";
        }
    }

    public Constantvalues.VisibilityStatus ConverttoVisibilityStatus(int visibilityvalue) {
        switch (visibilityvalue) {
            case 7071:
                return Constantvalues.VisibilityStatus.Hidden;
            case 7072:
                return Constantvalues.VisibilityStatus.Concealed;
            case 7073:
                return Constantvalues.VisibilityStatus.Revealed;
            case 7074:
                return Constantvalues.VisibilityStatus.Visible;
            case 7075:
                return Constantvalues.VisibilityStatus.None;
            default:
                return Constantvalues.VisibilityStatus.None;
        }
    }

    public int ConvertVisibilityStatustoInt(Constantvalues.VisibilityStatus visibilitystatusvalue) {
        switch (visibilitystatusvalue) {
            case Hidden:
                return 7071;
            case Concealed:
                return 7072;
            case Revealed:
                return 7073;
            case Visible:
                return 7074;
            case None:
                return 7075;
            default:
                return 7075;
        }
    }

    public Constantvalues.CharacterStatus ConverttoCharacterStatus(int charactervalue) {
        switch (charactervalue) {
            case 7171:
                return Constantvalues.CharacterStatus.STEALTHY;
            case 7172:
                return Constantvalues.CharacterStatus.LAX;
            case 7173:
                return Constantvalues.CharacterStatus.NONE;
            default:
                return Constantvalues.CharacterStatus.NONE;

        }
    }

    public int ConvertCharacterStatustoInt(Constantvalues.CharacterStatus characterstatusvalue) {
        switch (characterstatusvalue) {
            case STEALTHY:
                return 7171;
            case LAX:
                return 7172;
            case NONE:
                return 7173;
            default:
                return 7173;

        }
    }

    public Constantvalues.CombatStatus ConverttoCombatStatus(int combatvalue) {
        switch (combatvalue) {
            case 7001:
                return Constantvalues.CombatStatus.Firing;
            case 7002:
                return Constantvalues.CombatStatus.PrepFirer;
            case 7003:
                return Constantvalues.CombatStatus.OppFirer;
            case 7004:
                return Constantvalues.CombatStatus.FirstFirer;
            case 7005:
                return Constantvalues.CombatStatus.FinalFirer;
            case 7009:
                return Constantvalues.CombatStatus.AdvFirer;
            case 7006:
                return Constantvalues.CombatStatus.SubsequentFirstFiring;
            case 7007:
                return Constantvalues.CombatStatus.Melee;
            case 7010:
                return Constantvalues.CombatStatus.None;
            default:
                return Constantvalues.CombatStatus.None;
        }
    }

    public int ConvertCombatStatustoInt(Constantvalues.CombatStatus combatstatusvalue) {
        switch (combatstatusvalue) {
            case Firing:
                return 7001;
            case PrepFirer:
                return 7002;
            case OppFirer:
                return 7003;
            case FirstFirer:
                return 7004;
            case FinalFirer:
                return 7005;
            case AdvFirer:
                return 7009;
            case SubsequentFirstFiring:
                return 7006;
            case Melee:
                return 7007;
            case None:
                return 7010;
            default:
                return 7010;
        }
    }

    public Constantvalues.FortitudeStatus ConverttoFortitudeStatus(int fortitudevalue) {
        switch (fortitudevalue) {
            case 7091:
                return Constantvalues.FortitudeStatus.Normal;
            case 7092:
                return Constantvalues.FortitudeStatus.Fanatic;
            case 7093:
                return Constantvalues.FortitudeStatus.Encircled;
            case 7094:
                return Constantvalues.FortitudeStatus.Wounded;
            case 7095:
                return Constantvalues.FortitudeStatus.Fan_Enc;
            case 7096:
                return Constantvalues.FortitudeStatus.Fan_Wnd;
            case 7097:
                return Constantvalues.FortitudeStatus.Fan_Wnd_Enc;
            case 7098:
                return Constantvalues.FortitudeStatus.Enc_Wnd;
            case 7099:
                return Constantvalues.FortitudeStatus.Heroic;
            case 7100:
                return Constantvalues.FortitudeStatus.HeroicFanatic;
            case 7101:
                return Constantvalues.FortitudeStatus.HeroicEncircled;
            case 7102:
                return Constantvalues.FortitudeStatus.HeroicWounded;
            case 7103:
                return Constantvalues.FortitudeStatus.HeroicFan_Enc;
            case 7104:
                return Constantvalues.FortitudeStatus.HeroicFan_Wnd;
            case 7105:
                return Constantvalues.FortitudeStatus.HeroicFan_Wnd_Enc;
            case 7106:
                return Constantvalues.FortitudeStatus.HeroicEnc_Wnd;
            case 7107:
                return Constantvalues.FortitudeStatus.None;
            default:
                return Constantvalues.FortitudeStatus.None;
        }
    }

    public int ConvertFortitudeStatustoInt(Constantvalues.FortitudeStatus fortitudestatusvalue) {
        switch (fortitudestatusvalue) {
            case Normal:
                return 7091;
            case Fanatic:
                return 7092;
            case Encircled:
                return 7093;
            case Wounded:
                return 7094;
            case Fan_Enc:
                return 7095;
            case Fan_Wnd:
                return 7096;
            case Fan_Wnd_Enc:
                return 7097;
            case Enc_Wnd:
                return 7098;
            case Heroic:
                return 7099;
            case HeroicFanatic:
                return 7100;
            case HeroicEncircled:
                return 7101;
            case HeroicWounded:
                return 7102;
            case HeroicFan_Enc:
                return 7103;
            case HeroicFan_Wnd:
                return 7105;
            case HeroicFan_Wnd_Enc:
                return 7105;
            case HeroicEnc_Wnd:
                return 7106;
            case None:
                return 7107;
            default:
                return 7107;
        }
    }

    public Constantvalues.MovementStatus ConverttoMovementStatus(int movementvalue) {
        switch (movementvalue) {
            case 7021:
                return Constantvalues.MovementStatus.Moved;
            case 7022:
                return Constantvalues.MovementStatus.TI;
            case 7023:
                return Constantvalues.MovementStatus.Waded;
            case 7024:
                return Constantvalues.MovementStatus.HumanWave;
            case 7025:
                return Constantvalues.MovementStatus.BanzaiCharge;
            case 7026:
                return Constantvalues.MovementStatus.AssaultMoving;
            case 7027:
                return Constantvalues.MovementStatus.Moving;
            case 7028:
                return Constantvalues.MovementStatus.FirstDash;
            case 7029:
                return Constantvalues.MovementStatus.SecondDash;
            case 7030:
                return Constantvalues.MovementStatus.Dashed;
            case 7031:
                return Constantvalues.MovementStatus.Labour1;
            case 7032:
                return Constantvalues.MovementStatus.Labour2;
            case 7033:
                return Constantvalues.MovementStatus.Advanced;
            case 7034:
                return Constantvalues.MovementStatus.Connecting;
            case 7035:
                return Constantvalues.MovementStatus.Wading;
            case 7036:
                return Constantvalues.MovementStatus.AssaultMoved;
            case 7037:
                return Constantvalues.MovementStatus.NotMoving;
            default:
                return Constantvalues.MovementStatus.NotMoving;
        }
    }

    public int ConvertMovementStatustoInt(Constantvalues.MovementStatus movementstatusvalue) {
        switch (movementstatusvalue) {
            case Moved:
                return 7021;
            case TI:
                return 7022;
            case Waded:
                return 7023;
            case HumanWave:
                return 7024;
            case BanzaiCharge:
                return 7025;
            case AssaultMoving:
                return 7026;
            case Moving:
                return 7027;
            case FirstDash:
                return 7028;
            case SecondDash:
                return 7028;
            case Dashed:
                return 7030;
            case Labour1:
                return 7031;
            case Labour2:
                return 7032;
            case Advanced:
                return 7033;
            case Connecting:
                return 7034;
            case Wading:
                return 7035;
            case AssaultMoved:
                return 7036;
            case NotMoving:
                return 7037;
            default:
                return 7037;
        }
    }

    public Constantvalues.OrderStatus ConverttoOrderStatus(int ordervalue) {
        switch (ordervalue) {
            case 7051:
                return Constantvalues.OrderStatus.GoodOrder;
            case 7052:
                return Constantvalues.OrderStatus.Berserk;
            case 7053:
                return Constantvalues.OrderStatus.Prisoner;
            case 7054:
                return Constantvalues.OrderStatus.Unarmed;
            case 7055:
                return Constantvalues.OrderStatus.Broken;
            case 7056:
                return Constantvalues.OrderStatus.Broken_DM;
            case 7057:
                return Constantvalues.OrderStatus.Disrupted;
            case 7058:
                return Constantvalues.OrderStatus.DisruptedDM;
            case 7059:
                return Constantvalues.OrderStatus.KIAInf;
            case 7060:
                return Constantvalues.OrderStatus.NotInPlay;
            default:
                return Constantvalues.OrderStatus.NotInPlay;
        }
    }

    public int ConvertOrderStatustoInt(Constantvalues.OrderStatus orderstatusvalue) {
        switch (orderstatusvalue) {
            case GoodOrder:
                return 7051;
            case Berserk:
                return 7052;
            case Prisoner:
                return 7053;
            case Unarmed:
                return 7054;
            case Broken:
                return 7055;
            case Broken_DM:
                return 7056;
            case Disrupted:
                return 7057;
            case DisruptedDM:
                return 7058;
            case KIAInf:
                return 7059;
            case NotInPlay:
                return 7060;
            default:
                return 7060;
        }
    }

    public Constantvalues.RoleStatus ConverttoRoleStatus(int rolevalue) {
        switch (rolevalue) {
            case 7111:
                return Constantvalues.RoleStatus.GuardUnit;
            case 7112:
                return Constantvalues.RoleStatus.Passenger;
            case 7113:
                return Constantvalues.RoleStatus.Rider;
            case 7114:
                return Constantvalues.RoleStatus.None;
            default:
                return Constantvalues.RoleStatus.None;

        }
    }

    public int ConvertRoleStatustoInt(Constantvalues.RoleStatus rolestatusvalue) {
        switch (rolestatusvalue) {
            case GuardUnit:
                return 7111;
            case Passenger:
                return 7112;
            case Rider:
                return 7113;
            case None:
                return 7114;
            default:
                return 7114;

        }
    }

    public Constantvalues.SWStatus ConverttoSWStatus(int swstatusvalue) {
        switch (swstatusvalue) {
            case 5101:
                return Constantvalues.SWStatus.GoodOrderSW;
            case 5102:
                return Constantvalues.SWStatus.Brokendown;
            case 5103:
                return Constantvalues.SWStatus.Dismantled;
            case 5104:
                return Constantvalues.SWStatus.Dis_Broken;
            case 5105:
                return Constantvalues.SWStatus.Malfunctioned;
            case 5106:
                return Constantvalues.SWStatus.Eliminated;
            case 5110:
                return Constantvalues.SWStatus.DCPlaced;
            case 5115:
                return Constantvalues.SWStatus.Used;
            case 5120:
                return Constantvalues.SWStatus.None;
            default:
                return Constantvalues.SWStatus.None;

        }
    }

    public int ConvertSWStatustoInt(Constantvalues.SWStatus swstatusvalue) {
        switch (swstatusvalue) {
            case GoodOrderSW:
                return 5101;
            case Brokendown:
                return 5102;
            case Dismantled:
                return 5103;
            case Dis_Broken:
                return 5104;
            case Malfunctioned:
                return 5105;
            case Eliminated:
                return 5106;
            case DCPlaced:
                return 5110;
            case Used:
                return 5115;
            case None:
                return 5120;
            default:
                return 5120;
        }
    }

    public String ConvertCombatStatustoCounterNameString(Constantvalues.CombatStatus combatstatusvalue) {
        switch (combatstatusvalue) {
            case Firing:
                return null;
            case PrepFirer:
                return "Prep Fire";
            case OppFirer:
                return "Opp Fire";
            case FirstFirer:
                return "First Fire";
            case FinalFirer:
                return "Final Fire";
            case AdvFirer:
                return "Adv Fire";
            case SubsequentFirstFiring:
                return null;
            case Melee:
                return null;
            case None:
                return null;
            default:
                return null;
        }
    }

    public Constantvalues.AltPos ConvertCresttoWACrest(Constantvalues.AltPos FromPos) {
        switch (FromPos) {
            case CrestStatus0:
                return Constantvalues.AltPos.WACrestStatus0;
            case CrestStatus1:
                return Constantvalues.AltPos.WACrestStatus1;
            case CrestStatus2:
                return Constantvalues.AltPos.WACrestStatus2;
            case CrestStatus3:
                return Constantvalues.AltPos.WACrestStatus3;
            case CrestStatus4:
                return Constantvalues.AltPos.WACrestStatus4;
            case CrestStatus5:
                return Constantvalues.AltPos.WACrestStatus5;
            default:
                return null;
        }
    }

    
    public Constantvalues.AltPos WACresttoJustCrest(Constantvalues.AltPos CurrentPosition) {
        switch (CurrentPosition) {
            case WACrestStatus0:
                return Constantvalues.AltPos.CrestStatus0;
            case WACrestStatus1:
                return Constantvalues.AltPos.CrestStatus1;
            case WACrestStatus2:
                return Constantvalues.AltPos.CrestStatus2;
            case WACrestStatus3:
                return Constantvalues.AltPos.CrestStatus3;
            case WACrestStatus4:
                return Constantvalues.AltPos.CrestStatus4;
            case WACrestStatus5:
                return Constantvalues.AltPos.CrestStatus5;
            default:
                return null;
        }
    }
    public Constantvalues.AltPos SideToWACrestposition(int hexside) {
        switch (hexside) {
            case 0:
                return Constantvalues.AltPos.WACrestStatus0;
            case 1:
                return Constantvalues.AltPos.WACrestStatus1;
            case 2:
                return Constantvalues.AltPos.WACrestStatus2;
            case 3:
                return Constantvalues.AltPos.WACrestStatus3;
            case 4:
                return Constantvalues.AltPos.WACrestStatus4;
            case 5:
                return Constantvalues.AltPos.WACrestStatus5;
            default:
                return null;
        }
    }
    public Constantvalues.AltPos SideToCrest(int hexside) {
        switch (hexside) {
            case 0:
                return Constantvalues.AltPos.CrestStatus0;
            case 1:
                return Constantvalues.AltPos.CrestStatus1;
            case 2:
                return Constantvalues.AltPos.CrestStatus2;
            case 3:
                return Constantvalues.AltPos.CrestStatus3;
            case 4:
                return Constantvalues.AltPos.CrestStatus4;
            case 5:
                return Constantvalues.AltPos.CrestStatus5;
            default:
                return null;
        }
    }
    public int CrestSideToSide(Constantvalues.AltPos Crestside) {
        switch (Crestside) {
            case CrestStatus0:
                return 0;
            case CrestStatus1:
                return 1;
            case CrestStatus2:
                return 2;
            case CrestStatus3:
                return 3;
            case CrestStatus4:
                return 4;
            case CrestStatus5:
                return 5;
            default:
                return 0;
        }
    }
    public int WACrestSideToSide(Constantvalues.AltPos Crestside) {
        switch (Crestside) {
            case WACrestStatus0:
                return 0;
            case WACrestStatus1:
                return 1;
            case WACrestStatus2:
                return 2;
            case WACrestStatus3:
                return 3;
            case WACrestStatus4:
                return 4;
            case WACrestStatus5:
                return 5;
            default:
                return 0;
        }
    }
    public Constantvalues.AltPos CresttoExitedCrest(Constantvalues.AltPos CurrentPos) {
        switch (CurrentPos) {
            case CrestStatus0: WACrestStatus1:
                return Constantvalues.AltPos.ExitedCrest0;
            case CrestStatus1: WACrestStatus2:
                return Constantvalues.AltPos.ExitedCrest1;
            case CrestStatus2: WACrestStatus3:
                return Constantvalues.AltPos.ExitedCrest2;
            case CrestStatus3: WACrestStatus4:
                return Constantvalues.AltPos.ExitedCrest3;
            case CrestStatus4: WACrestStatus5:
                return Constantvalues.AltPos.ExitedCrest4;
            case CrestStatus5: WACrestStatus6:
                return Constantvalues.AltPos.ExitedCrest5;
            default:
                return null;
        }
    }

    public Constantvalues.ToDo ConvertUMovetoToDo (Constantvalues.UMove movementoption){
        switch (movementoption){
            default:
                return null;
        }
    }
    public Constantvalues.AltPos ConvertUMovetoAltPos(Constantvalues.UMove movementoption){
        switch (movementoption) {
            case CrestStatus0:
                return Constantvalues.AltPos.CrestStatus0;
            case CrestStatus1:
                return Constantvalues.AltPos.CrestStatus1;
            case CrestStatus2:
                return Constantvalues.AltPos.CrestStatus2;
            case CrestStatus3:
                return Constantvalues.AltPos.CrestStatus3;
            case CrestStatus4:
                return Constantvalues.AltPos.CrestStatus4;
            case CrestStatus5:
                return Constantvalues.AltPos.CrestStatus5;
            case WACrestStatus0:
                return Constantvalues.AltPos.WACrestStatus0;
            case WACrestStatus1:
                return Constantvalues.AltPos.WACrestStatus1;
            case WACrestStatus2:
                return Constantvalues.AltPos.WACrestStatus2;
            case WACrestStatus3:
                return Constantvalues.AltPos.WACrestStatus3;
            case WACrestStatus4:
                return Constantvalues.AltPos.WACrestStatus2;
            case WACrestStatus5:
                return Constantvalues.AltPos.WACrestStatus5;
            default:
                return null;
        }
    }
}
