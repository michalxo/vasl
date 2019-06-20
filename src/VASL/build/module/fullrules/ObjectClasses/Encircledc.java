package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;

public class Encircledc implements MoveUnitDecoratori {
    //Unit is encircled
    private MovingPersuniti BaseUnit;
    private PersUniti BaseParent;
    private Double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private Double MFUsedvalue;
    private boolean UsingEncircvalue;

    public Encircledc(PersUniti PassParentUnit) {
        BaseParent = PassParentUnit;
        BaseUnit = BaseParent.getMovingunit();
        UsingDTvalue = BaseUnit.getUsingDT(); UsingRBvalue = BaseUnit.getUsingRoadBonus();
        MFUsedvalue = BaseUnit.getMFUsed();
        UsingEncircvalue = true;
    }
    public double CalcMF() {
        return - 1 + BaseUnit.CalcMF();
    }

    public double getMFAvailable() {return MFleft;}
    public void setMFAvailable(double value) {MFleft = value;}
    public String getItemName() {return BaseUnit.getItemName();}
    public int getItemID() {return BaseUnit.getItemID();}
    public int getIPC() {return BaseUnit.getIPC();}
    public boolean IsDummy() {return BaseUnit.IsDummy();}
    public boolean getIsConcealed() {return BaseUnit.getIsConcealed();}
    public void setIsConcealed(boolean value) {BaseUnit.setIsConcealed(value);}
    public boolean getUsingDT() {return UsingDTvalue;}
    public void setUsingDT(boolean value){UsingDTvalue = value;}
    public boolean getUsingRoadBonus() {return UsingRBvalue;}
    public void setUsingRoadBonus(boolean value){UsingRBvalue = value;}
    public boolean getHasLdrBonus() {return BaseUnit.getHasLdrBonus();}
    public void setHasLdrBonus(boolean value){;}
    public double getMFUsed() {return MFUsedvalue;}
    public void setMFUsed(double value) {MFUsedvalue = value;}
    public boolean getPPImpact() {return false;}
    public Constantvalues.MovementStatus getAssaultMove() {return BaseUnit.getAssaultMove();}
    public void setAssaultMove(Constantvalues.MovementStatus value) {BaseUnit.setAssaultMove(value);}
    public Constantvalues.MovementStatus getDash() {return BaseUnit.getDash();}
    public void setDash(Constantvalues.MovementStatus value) {BaseUnit.setAssaultMove(value);}
    public int getHexEnteredSideCrossed() { return BaseUnit.getHexEnteredSideCrossed();}
    public void setHexEnteredSideCrossed(int value) {BaseUnit.setHexEnteredSideCrossed(value);}
    public int getSmokeE() {return BaseUnit.getSmokeE();}
    public boolean getusingEncirc() {return true;}
    public void setUsingEncirc(boolean value){UsingEncircvalue = value;}

    public boolean UpdateMovementStatus(PersUniti PassMover, Constantvalues.MovementStatus PassMoveStatus) {
        // this update triggers the Command to update OrderofBattle values plus remote computer
        // the Command also triggers counter actions
        InfantryUnitCommonFunctionsc UpdateMoveCF = new InfantryUnitCommonFunctionsc();
        return UpdateMoveCF.UpdateTargetStatus(PassMover);
    }
}
