package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
//import VASL.build.module.fullrules.MapDataClasses.LocationType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Basepersunitdecoratorac implements Basepersuniti {
    protected Basepersuniti decoratedbasePersUnit;

    public Basepersunitdecoratorac(Basepersuniti decoratedbasePersUnit){
        this.decoratedbasePersUnit = decoratedbasePersUnit;
    }
    public String getUnitName() {return decoratedbasePersUnit.getUnitName();}
    public String getHexName() {return decoratedbasePersUnit.getHexName();}
    public void setHexname(String value) {decoratedbasePersUnit.setHexname(value);}
    public int getScenario(){return decoratedbasePersUnit.getScenario();}
    public Hex getHex(){return decoratedbasePersUnit.getHex();}
    public void setHex(Hex value){decoratedbasePersUnit.setHex(value);}
    public Location gethexlocation(){return decoratedbasePersUnit.gethexlocation();}
    public void sethexlocation(Location value){decoratedbasePersUnit.sethexlocation(value);}
    public Constantvalues.AltPos gethexPosition(){return decoratedbasePersUnit.gethexPosition();}
    public void sethexPosition(Constantvalues.AltPos value){decoratedbasePersUnit.sethexPosition(value);}
    public double getLevelinHex(){return decoratedbasePersUnit.getLevelinHex();}
    public void setLevelinHex(double value){decoratedbasePersUnit.setLevelinHex(value);}
    public boolean getCX(){return decoratedbasePersUnit.getCX();}
    public void setCX(boolean value){decoratedbasePersUnit.setCX(value);}
    public int getELR(){return decoratedbasePersUnit.getELR();}
    public void setELR(int value){decoratedbasePersUnit.setELR(value);}
    public int getTurnArrives(){return decoratedbasePersUnit.getTurnArrives();}
    public Constantvalues.Nationality getNationality(){return decoratedbasePersUnit.getNationality();}
    public int getCon_ID(){return decoratedbasePersUnit.getCon_ID();}
    public void setCon_ID(int value){decoratedbasePersUnit.setCon_ID(value);}
    public int getUnit_ID(){return decoratedbasePersUnit.getUnit_ID();}
    public Constantvalues.Typetype getTypeType_ID(){return decoratedbasePersUnit.getTypeType_ID();}
    public  void setTypeType_ID(Constantvalues.Typetype value){decoratedbasePersUnit.setTypeType_ID(value);}
    public Constantvalues.Utype getUnittype(){return decoratedbasePersUnit.getUnittype();}
    public int getSecondSWLink(){return decoratedbasePersUnit.getSecondSWLink();}
    public void setSecondSWLink(int value){decoratedbasePersUnit.setSecondSWLink(value);}
    public int getFirstSWLink(){return decoratedbasePersUnit.getFirstSWLink();}
    public void setFirstSWLink(int value){decoratedbasePersUnit.setFirstSWLink(value);}
    public int getHexEntSideCrossed(){return decoratedbasePersUnit.getHexEntSideCrossed();}
    public void setHexEntSideCrossed(int value){decoratedbasePersUnit.setHexEntSideCrossed(value);}
    public int getSolID(){return decoratedbasePersUnit.getSolID();}
    public void setSolID(int value){decoratedbasePersUnit.setSolID(value);}
    public int getLOBLink(){return decoratedbasePersUnit.getLOBLink();}
    public Constantvalues.FortitudeStatus getFortitudeStatus(){return decoratedbasePersUnit.getFortitudeStatus();}
    public void setFortitudeStatus(Constantvalues.FortitudeStatus value){decoratedbasePersUnit.setFortitudeStatus(value);}
    public Constantvalues.MovementStatus getMovementStatus(){return decoratedbasePersUnit.getMovementStatus();}
    public void setMovementStatus(Constantvalues.MovementStatus value){decoratedbasePersUnit.setMovementStatus(value);}
    public Constantvalues.OrderStatus getOrderStatus(){return decoratedbasePersUnit.getOrderStatus();}
    public void setOrderStatus(Constantvalues.OrderStatus value){decoratedbasePersUnit.setOrderStatus(value);}
    public Constantvalues.CombatStatus getCombatStatus(){return decoratedbasePersUnit.getCombatStatus();}
    public void setCombatStatus(Constantvalues.CombatStatus value){decoratedbasePersUnit.setCombatStatus(value);}
    public Constantvalues.VisibilityStatus getVisibilityStatus(){return decoratedbasePersUnit.getVisibilityStatus();}
    public void setVisibilityStatus(Constantvalues.VisibilityStatus value){decoratedbasePersUnit.setVisibilityStatus(value);}
    public Constantvalues.RoleStatus getRoleStatus(){return decoratedbasePersUnit.getRoleStatus();}
    public void setRoleStatus(Constantvalues.RoleStatus value){decoratedbasePersUnit.setRoleStatus(value);}
    public boolean getPinned(){return decoratedbasePersUnit.getPinned();}
    public  void setPinned(boolean value){decoratedbasePersUnit.setPinned(value);}
    public LinkedList<PersUniti> getGuarding(){return decoratedbasePersUnit.getGuarding();}
    public int getnumSW(){return decoratedbasePersUnit.getnumSW();}
    public void setnumSW(int value){decoratedbasePersUnit.setnumSW(value);}
    public Constantvalues.UClass getUnitClass(){return decoratedbasePersUnit.getUnitClass();}
    public void setUnitClass(Constantvalues.UClass value){decoratedbasePersUnit.setUnitClass(value);}
    public Constantvalues.CharacterStatus getCharacterStatus(){return decoratedbasePersUnit.getCharacterStatus();}
    public void setCharacterStatus(Constantvalues.CharacterStatus value){decoratedbasePersUnit.setCharacterStatus(value);}
    public boolean IsUnitALeader(){return decoratedbasePersUnit.IsUnitALeader();}
    public boolean IsUnitASMC(){return decoratedbasePersUnit.IsUnitASMC();}
    public boolean IsInCrestStatus(){return decoratedbasePersUnit.IsInCrestStatus();}
    public boolean IsUnitInexperienced(){return decoratedbasePersUnit.IsUnitInexperienced();}
    public  boolean IsUnitGreen(){return decoratedbasePersUnit.IsUnitGreen();}
    public boolean IsUnitEncircled(){return decoratedbasePersUnit.IsUnitEncircled();}
    public int GetSW(int SW1or2){return decoratedbasePersUnit.GetSW(SW1or2);}
    public boolean HasWallAdvantage(){return decoratedbasePersUnit.HasWallAdvantage();}
    public boolean SetID(int NewID){return decoratedbasePersUnit.SetID(NewID);}
    public boolean  UpdateBaseStatus(PersUniti PassUnit){return decoratedbasePersUnit.UpdateBaseStatus(PassUnit);}  // Implements TargetPersUniti.UpdateTargetStatus
    public boolean CanGuard(Constantvalues.Utype Prisonertype){return decoratedbasePersUnit.CanGuard(Prisonertype);}
    public boolean AddPrisoner(PersUniti PassUnit){return decoratedbasePersUnit.AddPrisoner(PassUnit);}
    public boolean DeletePrisoner(PersUniti PassUnit){return decoratedbasePersUnit.DeletePrisoner(PassUnit);}
    public boolean IsLocationAMatch(Location testLOCformatch){return decoratedbasePersUnit.IsLocationAMatch(testLOCformatch);}

}
