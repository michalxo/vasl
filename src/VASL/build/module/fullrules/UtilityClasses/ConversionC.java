package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Location;
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
                return Constantvalues.Location.Building1stLevel;
            case 6703:
                return Constantvalues.Location.Building2ndLevel;
            case 6704:
                return Constantvalues.Location.Building3rdLevel;
            case 6705:
                return Constantvalues.Location.Cellar;
            case 6706:
                return Constantvalues.Location.InCave;
            case 6707:
                return Constantvalues.Location.BeneathBridge;
            case 6708:
                return Constantvalues.Location.Sewer    ;
            case 6711:
                return Constantvalues.Location.BeneathPier;
            case 6712:
                return Constantvalues.Location.BuildingGroundlevel;
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
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
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
                return Constantvalues.Location.Building1stLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.Building2ndLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.Building3rdLevel;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.Cellar;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.InCave;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.BeneathBridge;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.Sewer    ;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.BeneathPier;
            } else if ((SeeLOSLoc.getTerrain().getName()).equals("OpenGround")) {
                return Constantvalues.Location.BuildingGroundlevel;
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
