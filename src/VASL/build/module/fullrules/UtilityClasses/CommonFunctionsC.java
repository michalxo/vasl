package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Stack;

public class CommonFunctionsC {
    private int myscenid;
    private DataC Linqdata = DataC.GetInstance();
    public final static String DB_COUNTER_TYPE_MARKER_KEY = "DBCounterType";
    public final static String DB_UNIT_TYPE = "unit";
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
    public GamePiece GetGamePieceFromID (int IDtoMatch){
        GamePiece ToUse = null;
        try {

            final PieceIterator pi = new PieceIterator(GameModule.getGameModule().getGameState().getAllPieces().iterator());

            while (pi.hasMoreElements()) {
                final GamePiece piece = pi.nextPiece();
                if (piece instanceof Stack) {
                    for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                        GamePiece p2 = pi2.nextPiece();
                        if (isDBUnitCounter(p2) && p2.getProperty("TextLabel").toString() != null) {
                            int ObjIDlink = Integer.parseInt(p2.getProperty("TextLabel").toString());
                            if (ObjIDlink == IDtoMatch) {
                                //GameModule.getGameModule().getChatter().send("Have found Gamepiece to DM: " + TargParent.getbaseunit().getUnitName());
                                ToUse = p2;
                                //p2.keyEvent(KeyStroke.getKeyStroke('F', java.awt.event.InputEvent.CTRL_MASK));
                            }
                        }
                    }
                } else {
                    /*if (isDBUnitCounter(piece) && piece.getProperty("TextLabel").toString() != null) {
                        int ObjIDlink = Integer.parseInt(piece.getProperty("TextLabel").toString());
                        if (ObjIDlink == TargParent.getbaseunit().getUnit_ID()) {
                            GameModule.getGameModule().getChatter().send("Have found Gamepiece to DM: " + TargParent.getbaseunit().getUnitName());
                        }
                    }*/
                }

            }
            return ToUse;
        } catch (Exception e) {
            GameModule.getGameModule().getChatter().send("Error finding Gamepiece " );
            return null;
        }
    }
    public boolean isDBUnitCounter(GamePiece piece) {

        return isPropertySet(piece, DB_COUNTER_TYPE_MARKER_KEY, DB_UNIT_TYPE);
    }
    public boolean isPropertySet(GamePiece piece, String key, String value) {

        return piece.getProperty(key) != null && piece.getProperty(key).equals(value);
    }
}
