package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;

public class IPCc  implements MoveUnitDecoratori{
    // Unit is carrying
    private MovingPersuniti BaseUnit;
    private PersUniti BaseParent;
    private int ppexcess;
    private int ldraddstoIPC;
    //Private MovementStacktoCheck As List(Of MovingObjecttypeinterface)
    private Double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private Double mfusedvalue;
    private boolean ppimpactvalue;

    public IPCc(PersUniti PassParentUnit, MovementModifiersi DetermineModifiers) {
        BaseParent = PassParentUnit;
        BaseUnit = BaseParent.getMovingunit();
        ldraddstoIPC = 0;
        // If DetermineModifiers.GetsLdrBonus Then ' does ldr add IPC to this unit ?
        ppexcess = DetermineModifiers.getPPAdjust();
        if (ppexcess != 0) {ppimpactvalue = true;}
        UsingDTvalue = BaseUnit.getUsingDT();UsingRBvalue = BaseUnit.getUsingRoadBonus();
        mfusedvalue = BaseUnit.getMFUsed();
    }
    public double CalcMF() {
        // If LdrAdds() Then ldraddstoIPC = 1
        return ppexcess + ldraddstoIPC + BaseUnit.CalcMF();
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
