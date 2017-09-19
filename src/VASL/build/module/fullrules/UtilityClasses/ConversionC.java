package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;

public class ConversionC {

    // this class handles a variety of object type conversions; usually from int/string/etc stored in database to enums used in objects

    public ConversionC(){}

    public Constantvalues.WhoCanDo ConverttoWhoCanDo(String databasevalue){
        if (databasevalue == "A") {
            return Constantvalues.WhoCanDo.Attacker;
        } else if (databasevalue == "B") {
            return Constantvalues.WhoCanDo.Defender;
        } else {
            return null;
        }
    }
    public Constantvalues.DayNight ConverttoDayNight(int databasevalue) {
        switch(databasevalue) {
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
        switch(databasevalue) {
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

            default:
                return Constantvalues.Nationality.None;
        }
    }
    public Constantvalues.Phase ConverttoPhase(int databasevalue) {
        switch(databasevalue) {
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
    public Constantvalues.Rules ConverttoRules(int databasevalue) {
        switch(databasevalue) {
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
    public Constantvalues.Map ConverttoMap(int databasevalue) {
        switch(databasevalue) {
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
    public Constantvalues.Utype ConverttoUnitType(int databasevalue) {
        switch(databasevalue) {
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

    public Constantvalues.SWtype ConverttoSWType(int databasevalue) {
        switch(databasevalue) {
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
    public Constantvalues.Location ConverttoLocationType(int Locvalue){
        switch (Locvalue){
            case 6001:
                return Constantvalues.Location.OpenGround;
            case 6008:
                return Constantvalues.Location.Woods;
            default:
                return Constantvalues.Location.NA;
        }
    }

    public Constantvalues.Hexside ConverttoHexsideType( int databasevalue){
        switch (databasevalue){
            case 7500:
                return Constantvalues.Hexside.NoTerrain;

            default:
                return Constantvalues.Hexside.NoTerrain;
        }
    }
    public Constantvalues.IFTResult ConverttoIFTResult( int databasevalue){
        switch (databasevalue){
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
                return Constantvalues.IFTResult.MC4 ;
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
}
