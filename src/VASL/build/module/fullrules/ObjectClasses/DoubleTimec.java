package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;

public class DoubleTimec implements MoveUnitDecoratori {
    // Unit is DoubleTiming
    private MovingPersuniti BaseUnit;
    private PersUniti BaseParent;
    private double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private boolean hasldrbvalue;
    private double mfusedvalue;
    private int DTBonus;

    public DoubleTimec (PersUniti PassParentUnit) {
        BaseParent = PassParentUnit;
        BaseUnit = BaseParent.getMovingunit();
        UsingDTvalue = true; UsingRBvalue = BaseUnit.getUsingRoadBonus();
        mfusedvalue = BaseUnit.getMFUsed();
        if(mfusedvalue > 0) {
            DTBonus = 1;
        } else {
            DTBonus = 2;
        }
    }
    public double CalcMF() {
        return DTBonus + BaseUnit.CalcMF();
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
    public boolean getHasLdrBonus() {return hasldrbvalue;}
    public void setHasLdrBonus(boolean value){hasldrbvalue = value;}
    public double getMFUsed() {return mfusedvalue;}
    public void setMFUsed(double value) {mfusedvalue = value;}
    public boolean getPPImpact() {return false;}
    public Constantvalues.MovementStatus getAssaultMove() {return BaseUnit.getAssaultMove();}
    public void setAssaultMove(Constantvalues.MovementStatus value) {BaseUnit.setAssaultMove(value);}
    public Constantvalues.MovementStatus getDash() {return BaseUnit.getDash();}
    public void setDash(Constantvalues.MovementStatus value) {BaseUnit.setAssaultMove(value);}
    public int getHexEnteredSideCrossed() { return BaseUnit.getHexEnteredSideCrossed();}
    public void setHexEnteredSideCrossed(int value) {BaseUnit.setHexEnteredSideCrossed(value);}
    public int getSmokeE() {return BaseUnit.getSmokeE();}
    //public boolean getusingEncirc() {return BaseUnit.getusingEncirc;}
    //public void setUsingEncirc(boolean value){BaseUnit.setusingEncirc = value;}

    public boolean UpdateMovementStatus(PersUniti PassMover, Constantvalues.MovementStatus PassMoveStatus) {
        // this update triggers the Command to update OrderofBattle values plus remote computer
        // the Command also triggers counter actions
        InfantryUnitCommonFunctionsc UpdateMoveCF = new InfantryUnitCommonFunctionsc();
        return UpdateMoveCF.UpdateTargetStatus(PassMover);
    }
}
