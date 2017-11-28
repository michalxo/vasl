package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.*;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.SMC;
import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Stack;

import java.util.HashMap;

public class CommonFunctionsC {
    private int myscenid;
    public final static String DB_COUNTER_TYPE_MARKER_KEY = "DBCounterType";
    public final static String DB_UNIT_TYPE = "unit";
    public final static String DB_CON_TYPE = "concealment";
    ScenarioC scen  = ScenarioC.getInstance();

    public CommonFunctionsC (int PassScenid) {
        myscenid = PassScenid;
    }

    // Methods - each of this is distinct and does a particular thing

    public Location GetSniperLocation(Constantvalues.Nationality SniperNationality, String AorB) {
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
                        try {
                            if (isDBUnitCounter(p2) && p2.getProperty("TextLabel").toString() != null) {
                                int ObjIDlink = Integer.parseInt(p2.getProperty("TextLabel").toString());
                                if (ObjIDlink == IDtoMatch) {
                                    ToUse = p2;
                                    break;
                                }
                            }
                        } catch (Exception e){
                            continue;
                        }
                    }
                } else {

                }
                if (ToUse != null) {return ToUse;}
            }

        } catch (Exception e) {
            GameModule.getGameModule().getChatter().send("Error finding Gamepiece " );
            return null;
        }
        return null;
    }

    public GamePiece GetGamePieceConCounterFromID (int IDtoMatch){
        GamePiece ToUse = null;
        GamePiece concounter = null;
        try {

            final PieceIterator pi = new PieceIterator(GameModule.getGameModule().getGameState().getAllPieces().iterator());
            // find GamePiece that matches PersUniti or SuppWeapi
            while (pi.hasMoreElements()) {
                final GamePiece piece = pi.nextPiece();
                if (piece instanceof Stack) {
                    for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                        GamePiece p2 = pi2.nextPiece();
                        try {
                            if (isDBUnitCounter(p2) && p2.getProperty("TextLabel").toString() != null) {
                                int ObjIDlink = Integer.parseInt(p2.getProperty("TextLabel").toString());
                                if (ObjIDlink == IDtoMatch) {
                                    ToUse = piece;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                } else {

                }

            }
            // Now find the ? counter Game Piece
            if (ToUse instanceof Stack) {
                for (PieceIterator pi2 = new PieceIterator(((Stack) ToUse).getPiecesIterator()); pi2.hasMoreElements(); ) {
                    GamePiece p2 = pi2.nextPiece();
                    if (p2.getName().equals("?")) {
                            concounter = p2;
                            break;

                    }
                }
            }
            return concounter;
        } catch (Exception e) {
            GameModule.getGameModule().getChatter().send("Error finding Gamepiece " );
            return null;
        }
    }

    public boolean CheckIfInfoCounterExistsFromID (int IDtoMatch, String InfoName){
        GamePiece ToUse = null;

        try {
            final PieceIterator pi = new PieceIterator(GameModule.getGameModule().getGameState().getAllPieces().iterator());
            // find Stack that includes GamePiece that matches PersUniti or SuppWeapi
            while (pi.hasMoreElements()) {
                final GamePiece piece = pi.nextPiece();
                if (piece instanceof Stack) {
                    for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                        GamePiece p2 = pi2.nextPiece();
                        if (isDBUnitCounter(p2)) {
                            try {
                                if (p2.getProperty("TextLabel").toString() != null) {
                                    int ObjIDlink = Integer.parseInt(p2.getProperty("TextLabel").toString());
                                    if (ObjIDlink == IDtoMatch) {
                                        ToUse = piece;
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                }
            }
            if (ToUse == null) {return false;}
            // Now find the info counter Game Piece
            if (ToUse instanceof Stack) {
                for (PieceIterator pi2 = new PieceIterator(((Stack) ToUse).getPiecesIterator()); pi2.hasMoreElements(); ) {
                    GamePiece p2 = pi2.nextPiece();
                    if (p2.getName().equals(InfoName)) {  return true;}
                }
            }
            return false;
        } catch (Exception e) {
            GameModule.getGameModule().getChatter().send("Error finding Gamepiece " );
            return false;
        }
    }
    public boolean isDBUnitCounter(GamePiece piece) {

        return isPropertySet(piece, DB_COUNTER_TYPE_MARKER_KEY, DB_UNIT_TYPE);
    }

    public boolean isPropertySet(GamePiece piece, String key, String value) {

        return piece.getProperty(key) != null && piece.getProperty(key).equals(value);
    }

    public LineofBattle Getlob(String OBLink){
        HashMap<String, LineofBattle> LOBLookUp = scen.getLOBTableLookUp();
        LineofBattle lineofBattle = LOBLookUp.get(OBLink);
        return lineofBattle;
    }

    public SMC GetSMC(String OBLink){
        HashMap<String, SMC> SMCLookUp = scen.getSMCTableLookUp();
        SMC SMC = SMCLookUp.get(OBLink);
        return SMC;
    }

    public SupportWeapon GetSupportWeapon(String OBLink){
        HashMap<String, SupportWeapon> SupportWeaponLookUp = scen.getSupportWeaponTableLookUp();
        SupportWeapon supportWeapon = SupportWeaponLookUp.get(OBLink);
        return supportWeapon;
    }

    public OrderofBattle getUnderlyingOBunitforPersUniti(int OBUnit_ID) {
        // returns the matching OrderofBattle unit for a PersUniti
        ScenarioC scen = ScenarioC.getInstance();
        for(OrderofBattle testOBunit: scen.getOBUnitcol() ) {
            if (testOBunit.getOBUnit_ID() == OBUnit_ID) {
                return testOBunit;
            }
        }
        return null;
    }

    /*public GamePiece GetNewGamePiece(int Counterlink) {
        GamePiece newpiece = new GamePiece() {
            public void setMap(Map map) {

            }

            public Map getMap() {
                return null;
            }

            public void draw(Graphics graphics, int i, int i1, Component component, double v) {

            }

            public Point getPosition() {
                return null;
            }

            public void setPosition(Point point) {

            }

            public Rectangle boundingBox() {
                return null;
            }

            public Shape getShape() {
                return null;
            }

            public Stack getParent() {
                return null;
            }

            public void setParent(Stack stack) {

            }

            public Command keyEvent(KeyStroke keyStroke) {
                return null;
            }

            public String getName() {
                return null;
            }

            public String getLocalizedName() {
                return null;
            }

            public String getId() {
                return null;
            }

            public void setId(String s) {

            }

            public String getType() {
                return null;
            }

            public String getState() {
                return null;
            }

            public void setState(String s) {

            }

            public void setProperty(Object o, Object o1) {

            }

            public Object getProperty(Object o) {
                return null;
            }

            public Object getLocalizedProperty(Object o) {
                return null;
            }
        }
    }*/
}
