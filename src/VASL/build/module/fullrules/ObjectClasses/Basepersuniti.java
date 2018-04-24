package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
//import VASL.build.module.fullrules.MapDataClasses.LocationType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

// Second level interface implemented by personnel unit instances
public interface Basepersuniti {
    String getUnitName();
    void setUnitName(String value);
    String getHexName();
    void setHexname(String value);
    int getScenario();
    Hex getHex();
    void setHex(Hex value);
    Location gethexlocation();
    void sethexlocation(Location value);
    Constantvalues.AltPos gethexPosition();
    void sethexPosition(Constantvalues.AltPos value);
    double getLevelinHex();
    void setLevelinHex(double value);
    //int getLOCIndex();
    //void setLOCIndex(int value);
    boolean getCX();
    void setCX(boolean value);
    int getELR();
    void setELR(int value);
    int getTurnArrives();
    Constantvalues.Nationality getNationality();
    int getCon_ID();
    void setCon_ID(int value);
    int getUnit_ID();
    Constantvalues.Typetype getTypeType_ID();
    void setTypeType_ID(Constantvalues.Typetype value);
    Constantvalues.Utype getUnittype();
    int getSecondSWLink();
    void setSecondSWLink(int value);
    int getFirstSWLink();
    void setFirstSWLink(int value);
    int getHexEntSideCrossed();
    void setHexEntSideCrossed(int value);
    int getSolID();
    void setSolID(int value);
    int getLOBLink();
    Constantvalues.FortitudeStatus getFortitudeStatus();
    void setFortitudeStatus(Constantvalues.FortitudeStatus value);
    Constantvalues.MovementStatus getMovementStatus();
    void setMovementStatus(Constantvalues.MovementStatus value);
    Constantvalues.OrderStatus getOrderStatus();
    void setOrderStatus(Constantvalues.OrderStatus value);
    Constantvalues.CombatStatus getCombatStatus();
    void setCombatStatus(Constantvalues.CombatStatus value);
    Constantvalues.VisibilityStatus getVisibilityStatus();
    void setVisibilityStatus(Constantvalues.VisibilityStatus value);
    Constantvalues.RoleStatus getRoleStatus();
    void setRoleStatus(Constantvalues.RoleStatus value);
    boolean getPinned();
    void setPinned(boolean value);
    LinkedList<PersUniti> getGuarding();
    int getnumSW();
    void setnumSW(int value);
    Constantvalues.UClass getUnitClass();
    void setUnitClass(Constantvalues.UClass value);
    Constantvalues.CharacterStatus getCharacterStatus();
    void setCharacterStatus(Constantvalues.CharacterStatus value);
    boolean IsUnitALeader();
    boolean IsUnitASMC();
    boolean IsInCrestStatus();
    boolean IsUnitInexperienced();
    boolean IsUnitGreen();
    boolean IsUnitEncircled();
    int GetSW(int SW1or2);
    boolean HasWallAdvantage();
    boolean SetID(int NewID);
    boolean  UpdateBaseStatus(PersUniti PassUnit);  // Implements TargetPersUniti.UpdateTargetStatus
    boolean CanGuard(Constantvalues.Utype Prisonertype);
    boolean AddPrisoner(PersUniti PassUnit);
    boolean DeletePrisoner(PersUniti PassUnit);
    boolean IsLocationAMatch(Location testLOCformatch);
    //public LocationType getLocationType(Constantvalues.Location phexlocation);

}
