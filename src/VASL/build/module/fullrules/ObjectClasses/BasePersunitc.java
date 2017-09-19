package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.MapDataClasses.LocationType;
import VASL.build.module.fullrules.MapDataClasses.MapDataC;
import VASL.build.module.fullrules.TerrainClasses.TerrainChecks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

// concrete classes that implement PersUniti
public class BasePersunitc implements Basepersuniti {
    // all concrete class of persuniti will create this class; can be decorated
    private String pHexname;
    private int pScenario;
    private int pHexnum;
    private Constantvalues.Location phexlocation;
    private Constantvalues.AltPos phexPosition;
    private double pLevelinHex;
    private int pLOCIndex;
    private boolean pCX;
    private int pELR;
    private int pTurnArrives;
    private Constantvalues.Nationality pNationality;
    private int pCon_ID;      // OB id of associated concealment counter; for concealment counters themsleves is always 0  Orderofbattle.con_id
    private int pUnit_ID;     // OB id of the personnel/SW/concealment counter  Orderofbattle.OBUnit_ID/Concealment.con_id/OrderofBattleSW/OBSW_ID
    private Constantvalues.Typetype pTypeType_ID;      //  value of Enum.TypeType for all counters
    private Constantvalues.Utype pUnitType; // value of Unittype; stored in LOB and Leader tables;  equals Enum.Utype
    private int pFirstSWLink;
    private int pSecondSWlink;
    private int pSW;
    private int pHexEntSideCrossed;
    private int pSolID;
    private String pUnitName;
    private int pLOBLink;     // ID of LineOfBattle or SupportWeapon of which this object is an instance  OrderofBattle.LOBLink/OrderofBattleSW.Weapontype  for concealment is always 0
    private Constantvalues.MovementStatus pMovementStatus;
    private Constantvalues.FortitudeStatus pFortitudeStatus;
    private Constantvalues.OrderStatus pOrderStatus;
    private Constantvalues.VisibilityStatus pVisibilityStatus;
    private Constantvalues.CombatStatus pCombatStatus;
    private Constantvalues.CharacterStatus pCharacterStatus;
    private Constantvalues.RoleStatus pRoleStatus;
    private boolean pPinned;
    private Constantvalues.UClass pUnitClass;   // value of Enum.UClass for personnel units; always 0 for concealment and SW counters   LineOfBattle.Class
    private BufferedImage pOBTexture;  // holds value of image
    private LinkedList<PersUniti> pGuarding;

    // constructor
    public BasePersunitc(String PassHexname, int PassScenario, int PassHexnum, Constantvalues.Location Passhexlocation, Constantvalues.AltPos PasshexPosition, double PassLevelinHex, int PassLOCIndex, boolean PassCX,
                         int PassELR, int PassTurnArrives, Constantvalues.Nationality PassNationality, int PassCon_ID, int PassUnit_ID, Constantvalues.Typetype PassTypeType_ID, int PassFirstSWLink, int PassSecondSWlink,
                         int PassHexEntSideCrossed, int PassSolID, String PassUnitName, int PassLOBLink, Constantvalues.CombatStatus PassCombatStatus, Constantvalues.VisibilityStatus PassVisibilityStatus,
                         Constantvalues.FortitudeStatus PassFortitudeStatus, Constantvalues.OrderStatus PassOrderStatus, Constantvalues.MovementStatus PassMovementStatus, boolean PassPinned, int PassSW,
                         Constantvalues.UClass PassUnitClass, Constantvalues.CharacterStatus PassCharacterStatus, Constantvalues.Utype PassUtype, Constantvalues.RoleStatus PassRoleStatus) {

        pHexname = PassHexname;
        pScenario = PassScenario;
        pHexnum = PassHexnum;
        phexlocation = Passhexlocation;
        phexPosition = PasshexPosition;
        pLevelinHex = PassLevelinHex;
        pLOCIndex = PassLOCIndex;
        pCX = PassCX;
        pELR = PassELR;
        pTurnArrives = PassTurnArrives;
        pNationality = PassNationality;
        pCon_ID = PassCon_ID;
        pUnit_ID = PassUnit_ID;
        pTypeType_ID = PassTypeType_ID;
        pSW = PassSW;
        pFirstSWLink = PassFirstSWLink;
        pSecondSWlink = PassSecondSWlink;
        pHexEntSideCrossed = PassHexEntSideCrossed;
        pSolID = PassSolID;
        pUnitName = PassUnitName;
        pLOBLink = PassLOBLink;
        pMovementStatus = PassMovementStatus;
        pFortitudeStatus = PassFortitudeStatus;
        pOrderStatus = PassOrderStatus;
        pVisibilityStatus = PassVisibilityStatus;
        pCombatStatus = PassCombatStatus;
        pPinned = PassPinned;
        pUnitClass = PassUnitClass;
        pCharacterStatus = PassCharacterStatus;
        pUnitType = PassUtype;
        pRoleStatus = PassRoleStatus;
    }

    public String getUnitName() {
        return pUnitName;
    }

    public String getHexName() {
        return pHexname;
    }

    public void setHexname(String value) {
        pHexname = value;
    }

    public int getScenario() {
        return pScenario;
    }

    public int getHexnum() {
        return pHexnum;
    }

    public void setHexnum(int value) {
        pHexnum = value;
    }

    public Constantvalues.Location gethexlocation() {
        return phexlocation;
    }

    public void sethexlocation(Constantvalues.Location value) {
        phexlocation = value;
    }

    public Constantvalues.AltPos gethexPosition() {
        return phexPosition;
    }

    public void sethexPosition(Constantvalues.AltPos value) {
        phexPosition = value;
    }

    public double getLevelinHex() {
        return pLevelinHex;
    }

    public void setLevelinHex(double value) {
        pLevelinHex = value;
    }

    public int getLOCIndex() {
        return pLOCIndex;
    }

    public void setLOCIndex(int value) {
        pLOCIndex = value;
    }

    public boolean getCX() {
        return pCX;
    }

    public void setCX(boolean value) {
        pCX = value;
    }

    public int getELR() {
        return pELR;
    }

    public void setELR(int value) {
        pELR = value;
    }

    public int getTurnArrives() {
        return pTurnArrives;
    }

    public Constantvalues.Nationality getNationality() {
        return pNationality;
    }

    public int getCon_ID() {
        return pCon_ID;
    }

    public void setCon_ID(int value) {
        pCon_ID = value;
    }

    public int getUnit_ID() {
        return pUnit_ID;
    }

    public Constantvalues.Typetype getTypeType_ID() {
        return pTypeType_ID;
    }

    public void setTypeType_ID(Constantvalues.Typetype value) {
        pTypeType_ID = value;
    }

    public Constantvalues.Utype getUnittype() {
        return pUnitType;
    }

    public int getFirstSWLink() {
        return pFirstSWLink;
    }

    public void setFirstSWLink(int value) {
        pFirstSWLink = value;
    }

    public int getSecondSWLink() {
        return pSecondSWlink;
    }

    public void setSecondSWLink(int value) {
        pSecondSWlink = value;
    }

    public int getHexEntSideCrossed() {
        return pHexEntSideCrossed;
    }

    public void setHexEntSideCrossed(int value) {
        pHexEntSideCrossed = value;
    }

    public int getSolID() {
        return pSolID;
    }

    public void setSolID(int value) {
        pSolID = value;
    }

    public int getLOBLink() {
        return pLOBLink;
    }

    public Constantvalues.FortitudeStatus getFortitudeStatus() {
        return pFortitudeStatus;
    }

    public void setFortitudeStatus(Constantvalues.FortitudeStatus value) {
        pFortitudeStatus = value;
    }

    public Constantvalues.MovementStatus getMovementStatus() {
        return pMovementStatus;
    }

    public void setMovementStatus(Constantvalues.MovementStatus value) {
        pMovementStatus = value;
    }

    public Constantvalues.OrderStatus getOrderStatus() {
        return pOrderStatus;
    }

    public void setOrderStatus(Constantvalues.OrderStatus value) {
        pOrderStatus = value;
    }

    public Constantvalues.CombatStatus getCombatStatus() {
        return pCombatStatus;
    }

    public void setCombatStatus(Constantvalues.CombatStatus value) {
        pCombatStatus = value;
    }

    public Constantvalues.VisibilityStatus getVisibilityStatus() {
        return pVisibilityStatus;
    }

    public void setVisibilityStatus(Constantvalues.VisibilityStatus value) {
        pVisibilityStatus = value;
    }

    public Constantvalues.RoleStatus getRoleStatus() {
        return pRoleStatus;
    }

    public void setRoleStatus(Constantvalues.RoleStatus value) {
        pRoleStatus = value;
    }

    public boolean getPinned() {
        return pPinned;
    }

    public void setPinned(boolean value) {
        pPinned = value;
    }

    public BufferedImage getOBTexture() {
        return pOBTexture;
    }

    public void setOBTexture(BufferedImage value) {
        pOBTexture = value;
    }

    public int getLeftPos(int MapBtype, double MapXOffset, double MapYOffset, int MapMaxcols, int MapMaxrows) {
        // temporary while debugging undo
        /*Dim MapGeoTemp = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return (int) (MapGeoTemp.GetCX(CInt(this.pHexnum)) - 23);*/
        return 0;
    }

    public int getTopPos(int MapBtype, double MapXOffset, double MapYOffset, int MapMaxcols, int MapMaxrows) {
        // temporary while debugging undo
        /*Dim MapGeoTemp = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return (int) (MapGeoTemp.GetCY(CInt(CInt(this.pHexnum))) - 23);*/
        return 0;
    }

    public Point getDrawPos(int MapBtype, double MapXOffset, double MapYOffset, int MapMaxcols, int MapMaxrows) {
        // temporary while debugging undo
        /*Dim MapGeoTemp = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point DrawPos = new Point(0, 0);
        DrawPos.x = (int) (MapGeoTemp.GetCX(CInt(this.pexnum)) - 23);
        DrawPos.y = (int) (MapGeoTemp.GetCY(CInt(this.pHexnum)) - 23);
        return DrawPos;*/
        return null;
    }

    public int getnumSW() {
        return pSW;
    }

    public void setnumSW(int value) {
        pSW = value;
    }

    public Constantvalues.UClass getUnitClass() {
        return pUnitClass;
    }

    public void setUnitClass(Constantvalues.UClass value) {
        pUnitClass = value;
    }

    public Constantvalues.CharacterStatus getCharacterStatus() {
        return pCharacterStatus;
    }

    public void setCharacterStatus(Constantvalues.CharacterStatus value) {
        pCharacterStatus = value;
    }

    public boolean IsUnitALeader() {
        boolean IsLdr = (this.pUnitType == Constantvalues.Utype.LdrHero || this.pUnitType == Constantvalues.Utype.Leader) ? true : false;
        return IsLdr;
    }

    public boolean IsUnitASMC() {
        boolean IsSMC = (this.pUnitType == Constantvalues.Utype.LdrHero ||
                this.pUnitType == Constantvalues.Utype.Commissar ||
                this.pUnitType == Constantvalues.Utype.Hero ||
                this.pUnitType == Constantvalues.Utype.THHero ||
                this.pUnitType == Constantvalues.Utype.PathFind ||
                this.pUnitType == Constantvalues.Utype.Leader) ? true : false;
        return IsSMC;
    }

    public boolean IsInCrestStatus() {
        return false;
    }

    public boolean IsUnitInexperienced() {
        boolean IsInex = (pUnitClass == Constantvalues.UClass.GREEN ||
                pUnitClass == Constantvalues.UClass.AGREEN ||
                pUnitClass == Constantvalues.UClass.SGREEN ||
                pUnitClass == Constantvalues.UClass.ASGREEN ||
                pUnitClass == Constantvalues.UClass.CONSCRIPT ||
                pUnitClass == Constantvalues.UClass.ACONSCRIPT ||
                pUnitClass == Constantvalues.UClass.ASCONSCRIPT ||
                pUnitClass == Constantvalues.UClass.SCONSCRIPT) ? true : false;
        return IsInex;
    }

    public boolean IsUnitGreen() {
        boolean IsGreen = (pUnitClass == Constantvalues.UClass.GREEN ||
                pUnitClass == Constantvalues.UClass.AGREEN ||
                pUnitClass == Constantvalues.UClass.SGREEN ||
                pUnitClass == Constantvalues.UClass.ASGREEN) ? true : false;
        return IsGreen;
    }

    public boolean IsUnitEncircled() {
        boolean IsEncirc = (pFortitudeStatus == Constantvalues.FortitudeStatus.Enc_Wnd ||
                pFortitudeStatus == Constantvalues.FortitudeStatus.Encircled ||
                pFortitudeStatus == Constantvalues.FortitudeStatus.Fan_Wnd_Enc) ? true : false;
        return IsEncirc;
    }

    public int GetSW(int SW1or2) {
        // called by IFT.AddFirerUnit
        // returns OBSW_ID of SW possessed by firing unit, 0 if no SW possessed
        int SW = 0;
        switch (SW1or2) {
            case 1:
                SW = (int) (pFirstSWLink);
                break;
            case 2:
                SW = (int) (pSecondSWlink);
                break;
            default:
                SW = 0;
                break;
        }
        return SW;
    }

    public boolean HasWallAdvantage() {
        boolean HasWA =(phexPosition == Constantvalues.AltPos.WallAdv ||
                (phexPosition == Constantvalues.AltPos.WACrestStatus1 ||
                 phexPosition == Constantvalues.AltPos.WACrestStatus2 ||
                 phexPosition == Constantvalues.AltPos.WACrestStatus3 ||
                 phexPosition == Constantvalues.AltPos.WACrestStatus4 ||
                 phexPosition == Constantvalues.AltPos.WACrestStatus5 ||
                 phexPosition == Constantvalues.AltPos.WACrestStatus6) ? true: false);
        return HasWA;
    }
    public boolean SetID(int NewID) {
        pUnit_ID = NewID;
        return true;
    }
    public boolean UpdateBaseStatus(PersUniti PassUnit) {
        DataC Linqdata = DataC.GetInstance();
        /*if (PassUnit.getbaseunit().getTypeType_ID() == Constantvalues.Typetype.Concealment) {
            Dim UpdateUnit As DataClassLibrary.
            Concealment = Linqdata.GetConcealmentfromCol(PassUnit.BasePersUnit.Unit_ID)
            UpdateUnit.CX = CX
            'UpdateUnit.ELR = CType(ELR, Short?)
            'UpdateUnit.Pinned = Pinned
            'UpdateUnit.CombatStatus = CombatStatus
            UpdateUnit.MovementStatus = MovementStatus
            'UpdateUnit.FirstSWLink = CType(FirstSWLink, Short?)
            'UpdateUnit.SecondSWlink = CType(SecondSWlink, Short?)
            'UpdateUnit.SW = CType(SW, Short?)
            'UpdateUnit.TurnArrives = CType(TurnArrives, Short?)
            UpdateUnit.hexnum = Hexnum
            UpdateUnit.Hexname = Hexname
            UpdateUnit.hexlocation = hexlocation
            UpdateUnit.Position = hexPosition
            UpdateUnit.LocIndex = LOCIndex
            UpdateUnit.LevelinHex = LevelinHex
            UpdateUnit.HexEnteredSideCrossedLastMove = HexEntSideCrossed
            UpdateUnit.FortitudeStatus = FortitudeStatus
            'UpdateUnit.RoleStatus = RoleStatus
            'UpdateUnit.VisibilityStatus = VisibilityStatus
            Else
            Dim UpdateUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(PassUnit.BasePersUnit.Unit_ID)
            UpdateUnit.CX = CX
            UpdateUnit.ELR = CType(ELR, Short ?)
            UpdateUnit.Pinned = Pinned
            UpdateUnit.CombatStatus = CombatStatus
            UpdateUnit.MovementStatus = MovementStatus
            UpdateUnit.FirstSWLink = CType(FirstSWLink, Short ?)
            UpdateUnit.SecondSWlink = CType(SecondSWlink, Short ?)
            UpdateUnit.SW = CType(SW, Short ?)
            UpdateUnit.TurnArrives = CType(TurnArrives, Short ?)
            UpdateUnit.hexnum = CType(Hexnum, Short ?)
            UpdateUnit.Hexname = Hexname
            UpdateUnit.hexlocation = hexlocation
            UpdateUnit.Position = hexPosition
            UpdateUnit.LocIndex = LOCIndex
            UpdateUnit.LevelinHex = LevelinHex
            UpdateUnit.HexEnteredSideCrossedLastMove = HexEntSideCrossed
            UpdateUnit.FortitudeStatus = FortitudeStatus
            UpdateUnit.RoleStatus = RoleStatus
            UpdateUnit.VisibilityStatus = VisibilityStatus
            UpdateUnit.Con_ID = Con_ID
        }
        Linqdata.QuickUpdate();*/
        return true;
    }

    public boolean CanGuard(Constantvalues.Utype Prisonertype) {

        int TotalPrisonerSize = 0; int NewPrisonerSize = 0;
        int TotalPrisonerCapacity = 0; int psize = 0; boolean CanSoGuard=false;
        if (this.pUnitType == Constantvalues.Utype.Squad) {
            psize = 3;
        } else if (this.pUnitType == Constantvalues.Utype.HalfSquad || this.pUnitType == Constantvalues.Utype.Crew) {
            psize = 2;
        } else {
            psize = 1;
        }
        TotalPrisonerCapacity = 5 * psize;
        if (Prisonertype == Constantvalues.Utype.Squad) {
            NewPrisonerSize += 3;
        } else if (Prisonertype == Constantvalues.Utype.HalfSquad || Prisonertype == Constantvalues.Utype.Crew) {
            NewPrisonerSize += 2;
        } else {
            NewPrisonerSize += 1;
        }
        if (pGuarding.size() == 0) {return true;}
        for (PersUniti Prisoner: pGuarding) {
            if (Prisoner.getbaseunit().getUnittype() == Constantvalues.Utype.Squad) {
                TotalPrisonerSize += 3;
            } else if(Prisoner.getbaseunit().getUnittype() == Constantvalues.Utype.HalfSquad || Prisoner.getbaseunit().getUnittype() == Constantvalues.Utype.Crew) {
                TotalPrisonerSize += 2;
            } else {
                TotalPrisonerSize += 1;
            }
            CanSoGuard= (TotalPrisonerSize +NewPrisonerSize <= TotalPrisonerCapacity) ? true: false;
        }
        return CanSoGuard;
    }

    public LinkedList<PersUniti> getGuarding() {return pGuarding;}

    public boolean AddPrisoner(PersUniti PassUnit) {

        DataC Linqdata = DataC.GetInstance(); // use empty variables when know that instance already exists
        pGuarding.add(PassUnit);
        // update OBUnit -    MOVE ELSEWEHRE THHIS IS TEMP FIX AUG 14
        //Dim UpdateUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(PassUnit.BasePersUnit.Unit_ID);
        //UpdateUnit.Guard_ID = pUnit_ID;
        //Linqdata.QuickUpdate();
        return true;
    }

    public boolean DeletePrisoner(PersUniti PassUnit) {
        DataC Linqdata = DataC.GetInstance();  // use empty variables when know that instance already exists
        pGuarding.remove(PassUnit);
        // update OBUnit -    MOVE ELSEWEHRE THHIS IS TEMP FIX AUG 14
        //Dim UpdateUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(PassUnit.BasePersUnit.Unit_ID);
        //UpdateUnit.Guard_ID = 0;
        //Linqdata.QuickUpdate();
        return true;
    }
    public boolean IsLocationAMatch(Location testLOCformatch){
        TerrainChecks Terrchk = new TerrainChecks();
        if ((this.gethexlocation()).equals(Terrchk.getLocationtypefromVASLLocation(testLOCformatch)) &&
                this.getLevelinHex() == testLOCformatch.getBaseHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public LocationType getLocationType(Constantvalues.Location phexlocation) {
        TerrainChecks Terrchk = new TerrainChecks();
        return Terrchk.getLocationType(phexlocation);
    }
}