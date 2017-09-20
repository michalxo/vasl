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

            /*Jungle = 6041
            DenseJungle = 6042
            Bamboo = 6043
            PalmTrees = 6044
            Kunai = 6046
            Swamp = 6047
            RicePaddiesdrained = 6048
            RicePaddiesIrrigated = 6049
            RicePaddiesInSeason = 6050
            Panjis = 6051
            CaveComplex = 6052
            Beaches = 6053
            PalmTreesUnpavedRoad = 6054
            TrailJungle = 6055
            TrailDenseJungle = 6056
            JungleUnpavedRoad = 6057
            DenseJungleUnpaved = 6058
            TrailBamboo = 6059
            Trail = 6060
            Trailpalmtrees = 6061
            GullyShell = 6062
            PalmDebris = 6063
            PalmDebrisPalm = 6064
            GullyUnpavedBridge = 6065
            PierNoLoc = 6066
            SunkenLane = 6067
            Passage = 6068
            PavedIntersectionManhole = 6069  'NEW - add to terrain
            ElevRoad = 6070
            'PavedRoadShellhole = 6071 '- don 't use - split as Road and otherterrain=shellhole
            'UnpavedRoadShellhole = 6072
            Pier = 6073
            'UnpavedIntersctionManhole = 6074  'NEW - add to Terrain table - no, only works for paved
                    Reef = 6075
            Tetrahedron = 6076
            Tetrawire = 6077
            'FortStone = 6078  'need for image reasons - in terrain table not Map table
            'FortWood = 6079
            Vineyard = 6081
            IrrigDitch = 6083
            PartOrch = 6084
            IrDPOrch = 6085
            UnPavedIrDPO = 6086
            UnPavedIrD = 6087
            UnPavedPO = 6088
            PavedPO = 6089
            GullyPO = 6090
            PavedIrD = 6091
            Forest = 6092
            CactusPatch = 6093
            OliveGrove = 6094
            Hillock = 6095
            HillockSummit = 6103
            SandDuneH = 6096
            SandDuneL = 6097
            Lumberyard = 6098
            GullyOrchard = 6099
            Canal = 6128
            Pond = 6129
            WoodRubbleFallen = 6101
            StoneRubbleFallen = 6102

            'RB and VotG
            GrdLRR = 6111
            RailCar = 6112
            WreckedRC = 6113
            GuttedRC = 6114
            CitySquare = 6115
            CitySquareShellhole = 6116
            'CitySquareManhole = 6118 'don't use
            CitySquareManShell = 6119
            'PavedRoadManhole = 6120  'don't use
            'UnpavedRoadManhole = 6121  'don't use
            'PvRdManholeShellhole = 6122  'don't use
            'UnpvRdManholeShellhole = 6123  'don't use
            Fountain = 6124
            GrdLRRMan = 6125
            OrchardPvRoadMan = 6126
            StorageTanks = 6127


            'waterdepth
            StreamDry = 6131
            StreamDeep = 6132
            StreamFlooded = 6133
            OrchardStreamdry = 6134
            OrchardStreamdeep = 6135
            OrchardStreamflooded = 6136
            StreamWoodsdry = 6137
            streamwoodsdeep = 6138
            Streamwoodsflooded = 6139
            StreamPineWoodsdry = 6140
            streamPinewoodsdeep = 6141
            StreamPinewoodsflooded = 6142
            StreamBrushdry = 6143
            streambrushdeep = 6144
            Streambrushflooded = 6145
            'Desert
            Wadi = 6146
            Hammada = 6147
            SandTrack = 6148
            HammadaTrack = 6149
            Deir = 6150
            Track = 6151
            HillockTrack = 6152
            DeirTrack = 6153
            Scrub = 6154
            ScrubTrack = 6155
            HillockSummitTrack = 6156
            SandScrub = 6157
            SandScrubTrack = 6158
            SandDuneLTrack = 6159
            SandDuneHTrack = 6160
            Mausoleum = 6161
            Camp = 6162
            DesertCluster = 6163  'D12.43
            'BrokenTerrain
            BrkCrag = 6164
            BrokenGround = 6165
            'SteppeTerrain
            SteppeBrush = 6166
            SteppeWoods = 6167
            SteppeGrain = 6168
            'BRT
            ExcavDitch = 6169
            'Pacific
            BambooPath = 6170
            JunglePath = 6171
            DenseJunglePath = 6172
            'RiceBank = 6173  this is a hexside
            BeachSlightSoft = 6174
            BeachModerateSoft = 6175
            BeachSteepSoft = 6176
            Sandbar = 6177
            BeachSlightHard = 6178
            BeachModerateHard = 6179
            BeachSteepHard = 6180
            OceanShallow = 6181
            ExposedReef = 6182
            SubmergedReef = 6183
            JungleDebris = 6184  'Z SC2
            CordoroyRoads = 6185 'Z SC4
            StreamShallowJungleDebris = 6186
            'Italy
            RiverBrush = 6187
            BrushUnpavedRd = 6188
            BrushIrrD = 6189
            IrrDVineyard = 6190

            PineForest = 6192
            CulvertOG = 6193  '6258
            CulvertPvRD = 6194  '6259
            CulStrPvRd = 6195  '6260

            'Building constants    6200
            CommandBunker = 6234
            IslComBunker = 6235
            GunEmplacement = 6236
            BRTTower = 6237
            OneMarketStone = 6247
            OneMarketWood = 6248
            PBTower = 6254
            SingleSteeple = 6255
            OneSteeple = 6256
            TwoSteeple = 6257
            PartCol1 = 6261
            PartCol15 = 6262
            Manhole = 6273

            'for searching

            All = 5999
            NA = 9999
            NonStairBldg = 6301 'used in TerrainActions.IsHexTerrainA function
            HindranceHex = 6302
            HindranceFeature = 6303  'used in TerrainActins.IsFeatureA function
            Shellholetype = 6304
            Creststatustype = 6305
            Bypassable = 6306
            Smoketype = 6307
            RoadOGtype = 6308
            Manholetype = 6309
            Factorytype = 6310
            Rooflesstype = 6311
            Cellartype = 6312
            SplitLeveltype = 6313
            Buildingtype = 6314
            Pillboxtype = 6315
            IntBuildtype = 6316
            Rubbletype = 6317
            FortBuildtype = 6318
            bridgetype = 6319
            Marshtype = 6320
            ShallowStreamtype = 6321
            DeepStreamtype = 6322
            WaterObstacletype = 6323
            Blazetype = 6324
            towertype = 6325
            Roadtype = 6326
            OBAtype = 6327
            HardSurftype = 6328
            HasStairs = 6329
            Burnabletype = 6330

            Roof = 6701
            'Roof25 = 6709
            'Roof35 = 6710
            BuildingGroundlevel = 6712
            Building1stLevel = 6702
            Building2ndLevel = 6703
            Building3rdLevel = 6704
            Cellar = 6705
            InCave = 6706
            BeneathBridge = 6707
            Sewer = 6708
            BeneathPier = 6711
            'PillCellar = 6713
            Tunnel = 6714
            Huts = 6715
            BunkUnder = 6713
            FortStoneGrd = 6730
            FortSTone1st = 6731
            FortStone2nd = 6732
            FortSTone3rd = 6733
            FortWoodGrd = 6734
            FortWood1st = 6735
            FortWood2nd = 6736
            FortWood3rd = 6737

            Pill1571 = 15001
            Pill1572 = 15002
            Pill1573 = 15003
            Pill1574 = 15004
            Pill1575 = 15005
            Pill1576 = 15006
            Pill1351 = 15007
            Pill1352 = 15008
            Pill1353 = 15009
            Pill1354 = 15010
            Pill1355 = 15011
            Pill1356 = 15012
            Pill2351 = 15013
            Pill2352 = 15014
            Pill2353 = 15015
            Pill2354 = 15016
            Pill2355 = 15017
            Pill2356 = 15018
            Pill2571 = 15019
            Pill2572 = 15020
            Pill2573 = 15021
            Pill2574 = 15022
            Pill2575 = 15023
            Pill2576 = 15024
            Pill3351 = 15025
            Pill3352 = 15026
            Pill3353 = 15027
            Pill3354 = 15028
            Pill3355 = 15029
            Pill3356 = 15030
            Pill3571 = 15031
            Pill3572 = 15032
            Pill3573 = 15033
            Pill3574 = 15034
            Pill3575 = 15035
            Pill3576 = 15036
            Pill2461 = 15037
            Pill2462 = 15038
            Pill2463 = 15039
            Pill2464 = 15040
            Pill2465 = 15041
            Pill2466 = 15042
            Pill1461 = 15043
            Pill1462 = 15044
            Pill1463 = 15045
            Pill1464 = 15046
            Pill1465 = 15047
            Pill1466 = 15048
            Bombprf = 15897
            Cave146 = 10001 'need to add caves to terrain table
            CaveH46 = 10002
            Cave146LM1 = 10003
            CaveH46LM1 = 10004
            Cave146L0 = 10005
            CaveH46L0 = 10006
            Cave146L1 = 10007
            CaveH46L1 = 10008
            Cave146L2 = 10009
            CaveH46L2 = 10010
            Cave146L3 = 10011
            CaveH46L3 = 10012
            Cave146L4 = 10013
            CaveH46L4 = 10014
            StBr14 = 30001
            StBr25 = 30002
            StBr36 = 30003
            WdBr14 = 30004
            WdBr25 = 30005
            WdBr36 = 30006
            SStBr14 = 30007
            SStBr25 = 30008
            SStBr36 = 30009
            SWdBr14 = 30010
            SWdBr25 = 30011
            SWdBr36 = 30012
            PoBr14 = 30013
            PoBr25 = 30014
            PoBr36 = 30015
            FoBr = 30016
            WoodsTB = 10030  'ADD TB to terrain table; all  but last two
            MineTB = 10031
            StoneRubbleTB = 10032
            PineWTB = 10033
            JungleTB = 10034
            DenseJungleTB = 10035
            DebrisTB = 10036
            WoodRubbleTB = 10037
            WoodRubbleFallTB = 10038
            StoneRubbleFallTB = 10039

            OneFactRooflessMan*/
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
