package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Location;
import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

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
            case 2017:
                return Constantvalues.Nationality.None;
            default:
                return Constantvalues.Nationality.None;
        }
    }
    public int ConvertNationalitytoInt(Constantvalues.Nationality nationalityvalue) {
        switch(nationalityvalue) {
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
                return Constantvalues.Location.Wadi ;
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
                return Constantvalues.Location.Sewer    ;
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
                return Constantvalues.Location.Pill1576;
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
                return Constantvalues.Location.Pill1356;
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
                return Constantvalues.Location.Pill2356;
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
                return Constantvalues.Location.Pill2576;
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
                return Constantvalues.Location.Pill3356;
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
                return Constantvalues.Location.Pill3576;
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
                return Constantvalues.Location.Pill2466;
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
                return Constantvalues.Location.Pill1466;
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
                return Constantvalues.Location.CaveH46L2    ;
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
                return Constantvalues.Location.StBr36;
            case 30004:
                return Constantvalues.Location.WdBr14;
            case 30005:
                return Constantvalues.Location.WdBr25;
            case 30006:
                return Constantvalues.Location.WdBr36;
            case 30007:
                return Constantvalues.Location.SStBr14;
            case 30008:
                return Constantvalues.Location.SStBr25;
            case 30009:
                return Constantvalues.Location.SStBr36;
            case 30010:
                return Constantvalues.Location.SWdBr14    ;
            case 30011:
                return Constantvalues.Location.SWdBr25;
            case 30012:
                return Constantvalues.Location.SWdBr36;
            case 30013:
                return Constantvalues.Location.PoBr14;
            case 30014:
                return Constantvalues.Location.PoBr25;
            case 30015:
                return Constantvalues.Location.PoBr36;
            case 30016:
                return Constantvalues.Location.FoBr;
            default:
                return Constantvalues.Location.NA;
        }
    }

    public Constantvalues.Location getLocationtypefromVASLLocation(Location SeeLOSLoc){

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
                return Constantvalues.Location.Wadi ;
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
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.StoneBuildingGroundlevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.StoneBuilding1stLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("Stone Building, 2 Level")) {
                return Constantvalues.Location.StoneBuilding2ndLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.StoneBuilding3rdLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.StoneCellar;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.WoodBuildingGroundlevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.WoodBuilding1stLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
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
                return Constantvalues.Location.Sewer    ;
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
                return Constantvalues.Location.Pill1576;
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
                return Constantvalues.Location.Pill1356;
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
                return Constantvalues.Location.Pill2356;
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
                return Constantvalues.Location.Pill2576;
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
                return Constantvalues.Location.Pill3356;
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
                return Constantvalues.Location.Pill3576;
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
                return Constantvalues.Location.Pill2466;
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
                return Constantvalues.Location.Pill1466;
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
                return Constantvalues.Location.CaveH46L2    ;
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
                return Constantvalues.Location.StBr36;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.WdBr14;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.WdBr25;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.WdBr36;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.SStBr14;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.SStBr25;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.SStBr36;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.SWdBr14    ;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.SWdBr25;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.SWdBr36;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.PoBr14;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.PoBr25;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.PoBr36;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.FoBr;
            } else  {
                return Constantvalues.Location.NA;
            }
            return Constantvalues.Location.NA;
    }
    public Constantvalues.Hexside ConverttoHexsideType( int databasevalue) {
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
                return Constantvalues.Hexside. CrestUpGullyWire;
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
                return Constantvalues.Hexside.Roadblock1;
            case 75882:
                return Constantvalues.Hexside.Roadblock2;
            case 75883:
                return Constantvalues.Hexside.Roadblock3;
            case 75884:
                return Constantvalues.Hexside.Roadblock4;
            case 75885:
                return Constantvalues.Hexside.Roadblock5;
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
                return Constantvalues.Hexside. River;
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
    public Constantvalues.Hexside ConverttoHexside(Terrain Passterrain){
        if (Passterrain.getName().equals("Open Ground")){
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
                return Constantvalues.Hexside. CrestUpGullyWire;
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
                return Constantvalues.Hexside.Roadblock1;
        } else if (Passterrain.getName().equals("Wall")) {
                return Constantvalues.Hexside.Roadblock2;
        } else if (Passterrain.getName().equals("Wall")) {
                return Constantvalues.Hexside.Roadblock3;
        } else if (Passterrain.getName().equals("Wall")) {
                return Constantvalues.Hexside.Roadblock4;
        } else if (Passterrain.getName().equals("Wall")) {
                return Constantvalues.Hexside.Roadblock5;
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
        } else if (Passterrain.getName().equals("Wall")) {
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
                return Constantvalues.Hexside. River;
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

    public Constantvalues.AltPos ConverttoAltPosType( int databasevalue) {
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
                return Constantvalues.AltPos.CrestStatus1;
            case 6912:
                return Constantvalues.AltPos.CrestStatus2;
            case 6913:
                return Constantvalues.AltPos.CrestStatus3;
            case 6914:
                return Constantvalues.AltPos.CrestStatus4;
            case 6915:
                return Constantvalues.AltPos.CrestStatus5;
            case 6916:
                return Constantvalues.AltPos.CrestStatus6;
            case 6917:
                return Constantvalues.AltPos.ExitedEntrench;
            case 6921:
                return Constantvalues.AltPos.ExitedCrest1;
            case 6922:
                return Constantvalues.AltPos.ExitedCrest2;
            case 6923:
                return Constantvalues.AltPos.ExitedCrest3;
            case 6924:
                return Constantvalues.AltPos.ExitedCrest4;
            case 6925:
                return Constantvalues.AltPos.ExitedCrest5;
            case 6926:
                return Constantvalues.AltPos.ExitedCrest6;
            case 6927:
                return Constantvalues.AltPos.Rider;
            case 6928:
                return Constantvalues.AltPos.Passenger;
            case 6931:
                return Constantvalues.AltPos.WACrestStatus1;
            case 6932:
                return Constantvalues.AltPos.WACrestStatus2;
            case 6933:
                return Constantvalues.AltPos.WACrestStatus3;
            case 6934:
                return Constantvalues.AltPos.WACrestStatus4;
            case 6935:
                return Constantvalues.AltPos.WACrestStatus5;
            case 6936:
                return Constantvalues.AltPos.WACrestStatus6;
            case 6937:
                return Constantvalues.AltPos.None;
            default:
                return Constantvalues.AltPos.None;
        }
    }
    public int ConvertAltPosTypetoInt( Constantvalues.AltPos altposvalue) {
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
            case CrestStatus1:
                return 6911;
            case CrestStatus2:
                return 6912;
            case CrestStatus3:
                return 6913;
            case CrestStatus4:
                return 6914;
            case CrestStatus5:
                return 6915;
            case CrestStatus6:
                return 6916;
            case ExitedEntrench:
                return 6917;
            case ExitedCrest1:
                return 6921;
            case ExitedCrest2:
                return 6922;
            case ExitedCrest3:
                return 6923;
            case ExitedCrest4:
                return 6924;
            case ExitedCrest5:
                return 6925;
            case ExitedCrest6:
                return 6926;
            case Rider:
                return 6927;
            case Passenger:
                return 6928;
            case WACrestStatus1:
                return 6931;
            case WACrestStatus2:
                return 6932;
            case WACrestStatus3:
                return 6933;
            case WACrestStatus4:
                return 6934;
            case WACrestStatus5:
                return 6935;
            case WACrestStatus6:
                return 6936;
            case None:
                return 6937;
            default:
                return 6937;
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
    public int ConvertIFTResulttoInt( Constantvalues.IFTResult iftResultvalue){
        switch (iftResultvalue){
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
    public Constantvalues.VisibilityStatus ConverttoVisibilityStatus(int visibilityvalue){
        switch (visibilityvalue){
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
    public int ConvertVisibilityStatustoInt(Constantvalues.VisibilityStatus visibilitystatusvalue){
        switch (visibilitystatusvalue){
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
    public Constantvalues.CharacterStatus ConverttoCharacterStatus(int charactervalue){
        switch (charactervalue){
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
    public int ConvertCharacterStatustoInt(Constantvalues.CharacterStatus characterstatusvalue){
        switch (characterstatusvalue){
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
    public Constantvalues.CombatStatus ConverttoCombatStatus(int combatvalue){
        switch (combatvalue){
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
    public int ConvertCombatStatustoInt(Constantvalues.CombatStatus combatstatusvalue){
        switch (combatstatusvalue){
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
    public Constantvalues.FortitudeStatus ConverttoFortitudeStatus(int fortitudevalue){
        switch (fortitudevalue){
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

    public int ConvertFortitudeStatustoInt(Constantvalues.FortitudeStatus fortitudestatusvalue){
        switch (fortitudestatusvalue){
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
    public Constantvalues.MovementStatus ConverttoMovementStatus(int movementvalue){
        switch (movementvalue){
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
    public int ConvertMovementStatustoInt(Constantvalues.MovementStatus movementstatusvalue){
        switch (movementstatusvalue){
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
    public Constantvalues.OrderStatus ConverttoOrderStatus(int ordervalue){
        switch (ordervalue){
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
    public int ConvertOrderStatustoInt (Constantvalues.OrderStatus orderstatusvalue){
        switch (orderstatusvalue){
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
    public Constantvalues.RoleStatus ConverttoRoleStatus(int rolevalue){
        switch (rolevalue){
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

    public int ConvertRoleStatustoInt(Constantvalues.RoleStatus rolestatusvalue){
        switch (rolestatusvalue){
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
}
