package VASL.build.module.fullrules.TerrainClasses;

import VASL.LOS.Map.Terrain;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;

import java.util.LinkedList;

public class IsTerrain {
    // This class handles various methods to return value of boolean test accesssing one or more columns from the Map Table for specified location(s)
    private Terrain pTerrain;

    public IsTerrain(Terrain PassTerrain) {
        pTerrain = PassTerrain;
    }

    public boolean IsTerrainInherent(int LocationToTest) {

    //        'called by Mapactions.HexByHexMoveAlongOK
    //                'determines if terrain in a location is inherent terrain
    boolean IsTerInherent = false;
    /*Try
            IsTerInherent = CBool((From QU As MapDataClassLibrary.GameLocation In MapData Where QU.LocIndex = LocationToTest Select QU.IsInherent).First)
    Catch
            IsTerInherent = False
    End Try*/
        return IsTerInherent;
    }

    //'need to create overload that uses LocIndex
    public boolean IsLocationTerrainA(int hexnumber, int LocationToUse, int TypetoSearch) {
        /*'called by Map.IsSameHexLOSClear, Map.DoesObstacleBlockLOS, IFT.Combatdrm and  Movement MVC
        'CombatTerrain.GetScenFeatTEMLosh
        'determines if a parameter location is of a parameter type or contains scenario feature of parameter type

        Dim TypeFound As MapDataClassLibrary.GameLocation
        'Get scenario map specific data
        Dim Getlocs = New GetALocationFromMapTable(MapData)
        Dim Terrhex As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(hexnumber, LocationToUse)
        'Retrieve terrain type field for a specific hex
        Dim hexTerraintype As Integer = Terrhex.Location
        'check for broader condition - there will likely be several such tests
        If TypetoSearch = ConstantClassLibrary.ASLXNA.Location.NonStairBldg Then
                Try
        TypeFound = (From QU In MapData Where QU.IsBuilding = True And QU.HasStair = False And QU.Hexnum = hexnumber
        And QU.Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HasStairs Then
                Try
        TypeFound = (From QU In MapData Where QU.HasStair = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HindranceHex Then
        'do I have all of these terrain types ? Need to verify GullyOrchard applies
        Try
                TypeFound = (From QU In MapData Where QU.Hexnum = hexnumber
        And(QU.Location = LocationToUse Or QU.OtherTerraininLocation = LocationToUse)).First
        If TypeFound.LOSHdrm > 0 Then return True Else return False
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Roadtype Then
                Try
        TypeFound = (From QU In MapData Where QU.HasRoad = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HindranceFeature Then
        'NEED TO REDO THIS JUNE 12
        '' do I have all of these terrain types ?
                'For Each ScenFeat In ScenFeatcol
        '    ' need to check each ScenFeat as more than one can
        '    ' exist per hex(ie smoke and wire)
        '    ' check for hex match
        '    If ScenFeat.Hexnumber = hexnumber Then
        '        ' get type of terrain found
        '        If (IsFeatureSmoke(CInt(ScenFeat.FeatureType))) Or
        '        (ScenFeat.FeatureType >= Feature.RFE And ScenFeat.FeatureType <= Feature.BSR) Then
        '            ' firer can see through same level fire of orchards, etc:LOS is clear
        '            ' LOS Hindrance DRM applies
        '            Terrhex = Nothing : return True
        '        End If

        '    End If
        'Next
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Shellholetype Then
        'shellhole
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Shellhole
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyShell Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.CitySquareShellhole
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.CitySquareManShell Or
        Terrhex.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.Shellhole Or Terrhex.
        OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.GullyShell Or
        Terrhex.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.CitySquareShellhole Or Terrhex.
        OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.CitySquareManShell Then return True
        'check other terrain in hex
        If Terrhex.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.Shellhole Then return True
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Creststatustype Then
        'crest status in Gully/Stream
        Try
                TypeFound = (From QU In MapData Where QU.CrestStatusOK = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try

        'If hexTerraintype = Location.Gully Or hexTerraintype = Location.GullyBrush Or hexTerraintype = Location.GullyOrchard Or
        'hexTerraintype = Location.GullyPO Or hexTerraintype = Location.GullyShell Or hexTerraintype = Location.GullyWoods Or
        'hexTerraintype = Location.Streamshallow Or hexTerraintype = Location.StreamWoodsdry Or hexTerraintype = Location.streamwoodsdeep Or
        'hexTerraintype = Location.streamwoodsdeep Or hexTerraintype = Location.StreamWoodsdry Or hexTerraintype = Location.StreamWoodsshallow Or
        'hexTerraintype = Location.streamPinewoodsdeep Or hexTerraintype = Location.StreamPineWoodsdry Or hexTerraintype = Location.StreamWoodsshallow Or
        'hexTerraintype = Location.streambrushdeep Or hexTerraintype = Location.StreamBrushdry Or hexTerraintype = Location.StreamBrushshallow Or
        'hexTerraintype = Location.OrchardStreamdry Or hexTerraintype = Location.OrchardStreamdeep Or hexTerraintype = Location.OrchardStreamshallow Then
        '    Terrhex = Nothing : return True
        'End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Bypassable Then 'building or woods only
        Try
                TypeFound = (From QU In MapData Where QU.BypassOK = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Smoketype Then 'looking for smoke
        Try
                TypeFound = (From QU In MapData Where QU.Smoke > 0 And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.RoadOGtype Then
        'looking for road hexes with no other terrain
        Try
                TypeFound = (From QU In MapData Where QU.HasRoad = True And QU.
        OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.OpenGround And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        'If hexTerraintype = Location.PavedRoad Or hexTerraintype = Location.UnpavedRoad Or
        'hexTerraintype = Location.CitySquareManhole Or hexTerraintype = Location.CitySquare Or
        'hexTerraintype = Location.UnpavedRoadManhole Or hexTerraintype = Location.PavedRoadManhole Or
        'hexTerraintype = Location.SunkenPavedRoad Or hexTerraintype = Location.SunkenUnpvRoad Or hexTerraintype = Location.ElevRoad Or
        'hexTerraintype = Location.Fountain Then
        '    Terrhex = Nothing : return True
        'End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Manholetype Then 'looking for sewer entrances
        Try
                TypeFound = (From QU In MapData Where QU.IsManhole = True And QU.Hexnum = hexnumber
        And(QU.Location = LocationToUse Or QU.OtherTerraininLocation = LocationToUse)).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Factorytype Then 'looking for factory hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsFactory = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Rooflesstype Then
        'looking for roofless building hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsRoofless = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Cellartype Then 'looking for building with cellars
        Try
                TypeFound = (From QU In MapData Where QU.IsCellar = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.bridgetype Then 'looking for bridge hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsBridge = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Pillboxtype Then 'looking for pillbox hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsPillbox = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Buildingtype Then 'looking for buildings
        Try
                TypeFound = (From QU In MapData Where QU.IsBuilding = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.IntBuildtype Then
        'looking for interior building hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsInterior = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Rubbletype Then 'looking for Rubble
        Try
                TypeFound = (From QU In MapData Where QU.IsRubble = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.FortBuildtype Then 'looking for fortified building
        Try
                TypeFound = (From QU In MapData Where QU.IsFortified = True And QU.Hexnum = hexnumber And QU.
        Location = LocationToUse).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Marshtype Then 'looking of marsh hex
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Marsh Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.ShallowStreamtype Then 'looking for shallow stream
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Streamshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamWoodsshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamPineWoodsshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushshallow Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.DeepStreamtype Then 'looking for deep stream
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamDeep
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streamwoodsdeep
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streamPinewoodsdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streambrushdeep Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.WaterObstacletype Then 'looking for water obstacle
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Ocean
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OceanShallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.River
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.RiverBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Reef
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.SubmergedReef Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Pond Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Blazetype Then 'looking for blaze hex
        Try
                TypeFound = (From QU In MapData Where (QU.Smoke = ConstantClassLibrary.ASLXNA.VisHind.BlazeWood Or QU.
        Smoke = ConstantClassLibrary.ASLXNA.VisHind.BlazeStone)And QU.Hexnum = hexnumber And QU.Location = LocationToUse).
        First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HardSurftype Then 'looking for blaze hex
        Try
                TypeFound = (From QU In MapData Where (QU.IsHardSurface = True And QU.Location = LocationToUse)
        Or(QU.OTisHardSurface = True And QU.OtherTerraininLocation = LocationToUse) And QU.Hexnum = hexnumber).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Burnabletype Then 'looking for burnable terrain
        Try
                TypeFound = (From QU In MapData Where (QU.IsBuilding = True Or QU.IsRubble = True
        Or(QU.IsBridge = True And QU.IsStone = False))And QU.Hexnum = hexnumber And QU.Location = LocationToUse).First
        return True
        Catch ex As Exception
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineForest
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWoods Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWoodsPavedRoad
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWoodsUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWTB
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyWoods Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.SteppeWoods
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streamPinewoodsdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamPineWoodsdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamPineWoodsshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamWoodsdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamWoodsshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailPineWoods
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailWoods Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Woods
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodsPavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodsTB
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodsUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Grainfield
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Bamboo Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.BambooPath
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailBamboo Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Brush
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.BrushIrrD Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.BrushUnpavedRd
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.RiverBrush
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.SteppeBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushdry Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.CactusPatch
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJungle Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJunglePath
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJungleTB Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJungleUnpaved
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Jungle Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JungleDebris
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JunglePath Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JungleTB
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JungleUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamShallowJungleDebris
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailDenseJungle Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailJungle
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Huts Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Kunai
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OliveGrove Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyOrchard
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.IrDPOrch Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Orchard
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardPavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardPvRoadMan
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamflooded Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PartOrch
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.RicePaddiesInSeason Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PalmDebrisPalm
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PalmTrees Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PalmTreesUnpavedRoad
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Pier Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Vineyard Then
                Terrhex = Nothing :return True
        End If
        return False
        End Try
        End If*/
        /*'Now check for a very specific terrain match - if searching for a type, result will always be false
        IsLocationTerrainA = If(hexTerraintype = TypetoSearch, True, False)*/
        return false;
    }

    // 'overload uses LOCIndex
    public boolean IsLocationTerrainA(Constantvalues.Location Loctype, Constantvalues.Location TypetoSearch) {
        /*'called by Map.IsSameHexLOSClear, Map.DoesObstacleBlockLOS, IFT.Combatdrm and  Movement MVC
        'CombatTerrain.GetScenFeatTEMLosh
        'determines if a parameter location is of a parameter type or contains scenario feature of parameter type

        Dim TypeFound As MapDataClassLibrary.GameLocation
        'Get scenario map specific data
        Dim Getlocs = New GetALocationFromMapTable(MapData)
        Dim Terrhex As MapDataClassLibrary.GameLocation = Getlocs.RetrieveLocationfromMaptable(LocIndex)
        'Retrieve terrain type field for a specific hex
        Dim hexTerraintype As Integer = Terrhex.Location
        'check for broader condition - there will likely be several such tests
        If TypetoSearch = ConstantClassLibrary.ASLXNA.Location.NonStairBldg Then
                Try
        TypeFound = (From QU In MapData Where QU.IsBuilding = True And QU.HasStair = False And QU.LocIndex = LocIndex).
        First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HasStairs Then
                Try
        TypeFound = (From QU In MapData Where QU.IsBuilding = True And QU.HasStair = True And QU.LocIndex = LocIndex).
        First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HindranceHex Then
        'do I have all of these terrain types ? Need to verify GullyOrchard applies
        Try
                TypeFound = (From QU In MapData Where QU.LocIndex = LocIndex).First
        If TypeFound.LOSHdrm > 0 Then return True Else return False
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Roadtype Then
                Try
        TypeFound = (From QU In MapData Where QU.HasRoad = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HindranceFeature Then
        'NEED TO REDO THIS JUNE 12
        '' do I have all of these terrain types ?
                'For Each ScenFeat In ScenFeatcol
        '    ' need to check each ScenFeat as more than one can
        '    ' exist per hex(ie smoke and wire)
        '    ' check for hex match
        '    If ScenFeat.Hexnumber = hexnumber Then
        '        ' get type of terrain found
        '        If (IsFeatureSmoke(CInt(ScenFeat.FeatureType))) Or
        '        (ScenFeat.FeatureType >= Feature.RFE And ScenFeat.FeatureType <= Feature.BSR) Then
        '            ' firer can see through same level fire of orchards, etc:LOS is clear
        '            ' LOS Hindrance DRM applies
        '            Terrhex = Nothing : return True
        '        End If

        '    End If
        'Next
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Shellholetype Then
        'shellhole
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Shellhole
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyShell Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.CitySquareShellhole
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.CitySquareManShell Or
        Terrhex.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.Shellhole Or Terrhex.
        OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.GullyShell Or
        Terrhex.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.CitySquareShellhole Or Terrhex.
        OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.CitySquareManShell Then return True
        'check other terrain in hex
        If Terrhex.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.Shellhole Then return True
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Creststatustype Then
        'crest status in Gully/Stream
        Try
                TypeFound = (From QU In MapData Where QU.CrestStatusOK = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try

        'If hexTerraintype = Location.Gully Or hexTerraintype = Location.GullyBrush Or hexTerraintype = Location.GullyOrchard Or
        'hexTerraintype = Location.GullyPO Or hexTerraintype = Location.GullyShell Or hexTerraintype = Location.GullyWoods Or
        'hexTerraintype = Location.Streamshallow Or hexTerraintype = Location.StreamWoodsdry Or hexTerraintype = Location.streamwoodsdeep Or
        'hexTerraintype = Location.streamwoodsdeep Or hexTerraintype = Location.StreamWoodsdry Or hexTerraintype = Location.StreamWoodsshallow Or
        'hexTerraintype = Location.streamPinewoodsdeep Or hexTerraintype = Location.StreamPineWoodsdry Or hexTerraintype = Location.StreamWoodsshallow Or
        'hexTerraintype = Location.streambrushdeep Or hexTerraintype = Location.StreamBrushdry Or hexTerraintype = Location.StreamBrushshallow Or
        'hexTerraintype = Location.OrchardStreamdry Or hexTerraintype = Location.OrchardStreamdeep Or hexTerraintype = Location.OrchardStreamshallow Then
        '    Terrhex = Nothing : return True
        'End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Bypassable Then 'building or woods only
        Try
                TypeFound = (From QU In MapData Where QU.BypassOK = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Smoketype Then 'looking for smoke
        Try
                TypeFound = (From QU In MapData Where QU.Smoke > 0 And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.RoadOGtype Then
        'looking for road hexes with no other terrain
        Try
                TypeFound = (From QU In MapData Where QU.HasRoad = True And QU.
        OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.OpenGround And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        'If hexTerraintype = Location.PavedRoad Or hexTerraintype = Location.UnpavedRoad Or
        'hexTerraintype = Location.CitySquareManhole Or hexTerraintype = Location.CitySquare Or
        'hexTerraintype = Location.UnpavedRoadManhole Or hexTerraintype = Location.PavedRoadManhole Or
        'hexTerraintype = Location.SunkenPavedRoad Or hexTerraintype = Location.SunkenUnpvRoad Or hexTerraintype = Location.ElevRoad Or
        'hexTerraintype = Location.Fountain Then
        '    Terrhex = Nothing : return True
        'End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Manholetype Then 'looking for sewer entrances
        Try
                TypeFound = (From QU In MapData Where QU.IsManhole = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Factorytype Then 'looking for factory hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsFactory = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Rooflesstype Then
        'looking for roofless building hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsRoofless = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Cellartype Then 'looking for building with cellars
        Try
                TypeFound = (From QU In MapData Where QU.IsCellar = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.bridgetype Then 'looking for bridge hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsBridge = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Pillboxtype Then 'looking for pillbox hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsPillbox = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Buildingtype Then 'looking for buildings
        Try
                TypeFound = (From QU In MapData Where QU.IsBuilding = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.IntBuildtype Then
        'looking for interior building hexes
        Try
                TypeFound = (From QU In MapData Where QU.IsInterior = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Rubbletype Then 'looking for Rubble
        Try
                TypeFound = (From QU In MapData Where QU.IsRubble = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.FortBuildtype Then 'looking for fortified building
        Try
                TypeFound = (From QU In MapData Where QU.IsFortified = True And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Marshtype Then 'looking of marsh hex
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Marsh Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.ShallowStreamtype Then 'looking for shallow stream
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Streamshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamWoodsshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamPineWoodsshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushshallow Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.DeepStreamtype Then 'looking for deep stream
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamDeep
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streamwoodsdeep
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streamPinewoodsdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streambrushdeep Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.WaterObstacletype Then 'looking for water obstacle
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Ocean
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OceanShallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.River
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.RiverBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Reef
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.SubmergedReef Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Pond Then
                Terrhex = Nothing :return True
        End If
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Blazetype Then 'looking for blaze hex
        Try
                TypeFound = (From QU In MapData Where (QU.Smoke = ConstantClassLibrary.ASLXNA.VisHind.BlazeWood Or QU.
        Smoke = ConstantClassLibrary.ASLXNA.VisHind.BlazeStone)And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.HardSurftype Then
        'looking for runway, citysquare, hard surface hex
        Try
                TypeFound = (From QU In MapData Where (QU.IsHardSurface = True Or QU.OTisHardSurface = True)And QU.
        LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        return False
        End Try
        ElseIf TypetoSearch = ConstantClassLibrary.ASLXNA.Location.Burnabletype Then 'looking for burnable terrain
        Try
                TypeFound = (From QU In MapData Where (QU.IsBuilding = True Or QU.IsRubble = True
        Or(QU.IsBridge = True And QU.IsStone = False))And QU.LocIndex = LocIndex).First
        return True
        Catch ex As Exception
        If hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineForest
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWoods Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWoodsPavedRoad
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWoodsUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PineWTB
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyWoods Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.SteppeWoods
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.streamPinewoodsdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamPineWoodsdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamPineWoodsshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamWoodsdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamWoodsshallow Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailPineWoods
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailWoods Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Woods
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodsPavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodsTB
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.WoodsUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Grainfield
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Bamboo Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.BambooPath
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailBamboo Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Brush
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.BrushIrrD Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.BrushUnpavedRd
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.RiverBrush
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.SteppeBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushdry Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamBrushshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailBrush Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.CactusPatch
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJungle Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJunglePath
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJungleTB Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.DenseJungleUnpaved
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Jungle Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JungleDebris
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JunglePath Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JungleTB
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.JungleUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.StreamShallowJungleDebris
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailDenseJungle Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.TrailJungle
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Huts Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Kunai
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OliveGrove Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.GullyOrchard
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.IrDPOrch Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Orchard
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardPavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardPvRoadMan
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamdeep Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamdry
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamflooded Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardStreamshallow
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.OrchardUnpavedRoad Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PartOrch
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.RicePaddiesInSeason Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PalmDebrisPalm
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PalmTrees Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.PalmTreesUnpavedRoad
        Or hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Pier Or
                hexTerraintype = ConstantClassLibrary.ASLXNA.Location.Vineyard Then
                Terrhex = Nothing :return True
        End If
        return False
        End Try
        End If
        'Now check for a very specific terrain match - if searching for a type, result will always be false
        IsLocationTerrainA = If(hexTerraintype = TypetoSearch, True, False)*/
        return false;
    }

    public boolean IsLocationConcealmentTerrain(Constantvalues.Location hexlocation) {
        return false;
    }
}