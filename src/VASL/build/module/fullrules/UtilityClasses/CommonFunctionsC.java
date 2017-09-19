package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;

public class CommonFunctionsC {
    private int myscenid;
    private DataC Linqdata = DataC.GetInstance();
    public CommonFunctionsC (int PassScenid) {
        myscenid = PassScenid;
    }

    // Methods - each of this is distinct and does a particular thing

    public GameLocation GetSniperLocation(Constantvalues.Nationality SniperNationality, String AorB) {
        /*
        'use empty variables when know that instance already exists
        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(myscenid)
        Dim NewMap As UtilWObj.ASLXNA.NewMapDB = New UtilWObj.ASLXNA.NewMapDB
        Dim Mapcol As IQueryable (Of MapDataClassLibrary.GameLocation) =NewMap.GetMapCol
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(Mapcol)
        Dim loctouse As MapDataClassLibrary.GameLocation
        If SniperNationality = Scendet.ATT1 Or SniperNationality = Scendet.ATT2 Then
        If AorB = "A" Then
                loctouse = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sandfnaloc))
        Else
                loctouse = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sandfnbloc))
        End If
        Else
        If AorB = "A" Then
                loctouse = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sanattaloc))
        Else
                loctouse = GetLocs.RetrieveLocationfromMaptable(CInt(Scendet.Sanattbloc))
        End If
        End If
        Return loctouse*/
        return null;
    }
    public int GetEnemySan(Constantvalues.Nationality SideNationality) {
        // called by ObjectChange classes
        // retrieves SAN for other side of parameter SideNationality

        ScenarioC scen  = ScenarioC.getInstance();
        Scenario Scendet = scen.getScendet();
        return  (SideNationality == Scendet.getATT1() || SideNationality == Scendet.getATT2()) ? Scendet.getDFNSAN(): Scendet.getATTSAN();
    }
    public Constantvalues.Nationality GetPhaseSide() {
        /*'called by various initialize routines
        'determines which is the player side in any phase

        'use empty variables when know that instance already exists
        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(myscenid)
        With Scendet
        Select Case .Phase
        Case ConstantClassLibrary.ASLXNA.Phase.DefensiveFire
        If Not (.PlayerTurn = ConstantClassLibrary.ASLXNA.WhoCanDo.Attacker)Then 'ATTACKER
        Return CInt (.ATT1)
        Else 'Defender
        Return CInt (.DFN1)
        End If
        Case Else
        If.PlayerTurn = ConstantClassLibrary.ASLXNA.WhoCanDo.Attacker Then 'ATTACKER
        Return CInt (.ATT1)
        Else 'Defender
        Return CInt (.DFN1)
        End If
        End Select
        End With*/
        return null;
    }

}
