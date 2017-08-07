package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

// Second level interface implemented by personnel unit instances
public interface Basepersuniti {
    public String getUnitName();
    public String getHexName();
    public void setHexname(String value);
    public int getScenario();
    public int getHexnum();
    public void setHexnum(int value);
    public int gethexlocation();
    public void sethexlocation(int value);
    public Constantvalues.AltPos gethexPosition();
    public void sethexPosition(Constantvalues.AltPos value);
    public double getLevelinHex();
    public void setLevelinHex(double value);
    public int getLOCIndex();
    public void setLOCIndex(int value);
    public boolean getCX();
    public void setCX(boolean value);
    public int getELR();
    public void setELR(int value);
    public int getTurnArrives();
    public Constantvalues.Nationality getNationality();
    public int getCon_ID();
    public void setCon_ID(int value);
    public int getUnit_ID();
    public Constantvalues.Typetype getTypeType_ID();
    public void setTypeType_ID(Constantvalues.Typetype value);
    public Constantvalues.Utype getUnittype();
    public int getSecondSWLink();
    public void setSecondSWLink(int value);
    public int getFirstSWLink();
    public void setFirstSWLink(int value);
    public int getHexEntSideCrossed();
    public void setHexEntSideCrossed(int value);
    public int getSolID();
    public void setSolID(int value);
    public int getLOBLink();
    public Constantvalues.FortitudeStatus getFortitudeStatus();
    public void setFortitudeStatus(Constantvalues.FortitudeStatus value);
    public Constantvalues.MovementStatus getMovementStatus();
    public void setMovementStatus(Constantvalues.MovementStatus value);
    public Constantvalues.OrderStatus getOrderStatus();
    public void setOrderStatus(Constantvalues.OrderStatus value);
    public Constantvalues.CombatStatus getCombatStatus();
    public void setCombatStatus(Constantvalues.CombatStatus value);
    public Constantvalues.VisibilityStatus getVisibilityStatus();
    public void setVisibilityStatus(Constantvalues.VisibilityStatus value);
    public Constantvalues.RoleStatus getRoleStatus();
    public void setRoleStatus(Constantvalues.RoleStatus value);
    public boolean getPinned();
    public void setPinned(boolean value);
    public LinkedList<PersUniti> getGuarding();

    public BufferedImage getOBTexture();
    public void setOBTexture(BufferedImage value);  // holds value of current image for unit - DELETE AS WILL BE HELD IN VASL GAME PIECE

    public int getLeftPos(int MapBtype, double MapXOffset, double MapYOffset, int MapMaxcols, int MapMaxrows);   // holds value of x position in current hex
    public int getTopPos(int MapBtype, double MapXOffset, double MapYOffset, int MapMaxcols, int MapMaxrows);    // holds value of y position in current hex
    public Point getDrawPos(int MapBtype, double MapXOffset, double MapYOffset, int MapMaxcols, int MapMaxrows);

    public int getnumSW();
    public void setnumSW(int value);
    public Constantvalues.UClass getUnitClass();
    public void setUnitClass(Constantvalues.UClass value);
    public Constantvalues.CharacterStatus getCharacterStatus();
    public void setCharacterStatus(Constantvalues.CharacterStatus value);

    public boolean IsUnitALeader();
    public boolean IsUnitASMC();
    public boolean IsInCrestStatus();
    public boolean IsUnitInexperienced();
    public boolean IsUnitGreen();
    public boolean IsUnitEncircled();
    public int GetSW(int SW1or2);
    public boolean HasWallAdvantage();
    public boolean SetID(int NewID);
    public boolean  UpdateBaseStatus(PersUniti PassUnit);  // Implements TargetPersUniti.UpdateTargetStatus
    public boolean CanGuard(Constantvalues.Utype Prisonertype);
    public boolean AddPrisoner(PersUniti PassUnit);
    public boolean DeletePrisoner(PersUniti PassUnit);

}
