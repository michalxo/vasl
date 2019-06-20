package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;

import javax.swing.*;
import java.util.LinkedList;

public class LdrBonusc implements MoveUnitDecoratori {
    // Unit is moving with ldr
    private MovingPersuniti BaseUnit;
    private PersUniti BaseParent;
    private Double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private Double MFUsedvalue;
    private LinkedList<PersUniti> TempMovingList;
    private boolean hasldrbvalue = false;

    public LdrBonusc(PersUniti PassParentUnit, LinkedList<PersUniti> Templist) {
        BaseParent = PassParentUnit;
        BaseUnit = BaseParent.getMovingunit();
        UsingDTvalue = BaseUnit.getUsingDT(); UsingRBvalue = BaseUnit.getUsingRoadBonus();
        MFUsedvalue = BaseUnit.getMFAvailable();
        TempMovingList = Templist;
    }
    public double CalcMF() {
        if (!BaseUnit.IsDummy()) {
            if (BaseParent.getbaseunit().IsUnitGreen()) { // Green with leader have 6 MF
                return 3 + IPCAdjustment() + BaseUnit.CalcMF();
            } else if ((BaseParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Wounded || BaseParent.getbaseunit().getFortitudeStatus() ==
                    Constantvalues.FortitudeStatus.Enc_Wnd || BaseParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd
                    || BaseParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd_Enc) ||
                    BaseParent.getbaseunit().IsUnitASMC()) { // no ldrbonus applies
                return BaseUnit.CalcMF();
            } else { // MMC
                return 2 + IPCAdjustment() + BaseUnit.CalcMF();
            }
        } else {
            return 2 + BaseUnit.CalcMF();
        }
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
    //public boolean getusingEncirc() {return BaseUnit.UsingRBvalue;}
    //public void setUsingEncirc(boolean value){BaseUnit.setusingEncirc = value;}

    // Methods
    private int IPCAdjustment() {
        int UnitswithExcessPP = 0; int SMCPresent = 0;
        for (PersUniti MovingUnittoCheck: TempMovingList) {
            if (MovingUnittoCheck.getMovingunit().getPPImpact()) {
                UnitswithExcessPP += 1;
            }
            if (BaseParent.getbaseunit().IsUnitASMC() && BaseParent.getbaseunit().getnumSW() == 0 &&
                    BaseParent.getbaseunit().getFortitudeStatus() != Constantvalues.FortitudeStatus.Wounded &&
                    BaseParent.getbaseunit().getFortitudeStatus() != Constantvalues.FortitudeStatus.Enc_Wnd &&
                    BaseParent.getbaseunit().getFortitudeStatus() != Constantvalues.FortitudeStatus.Fan_Wnd &&
                    BaseParent.getbaseunit().getFortitudeStatus() != Constantvalues.FortitudeStatus.Fan_Wnd_Enc) {
                SMCPresent += 1;
            }
        }
        if (SMCPresent == 0) {return 0;}  // no SMC eligible to add IPC to units carrying excess PP
        if (SMCPresent >= UnitswithExcessPP) { // SMC can add their IPC to all units carrying excess PP
            return 1;
        } else { // need to choose which unit to add IPC to
            boolean addipctothisunit = askforldripc(BaseParent.getbaseunit().getUnitName());
            if (addipctothisunit) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    /**
     * Displays the input dialog and returns user input
     */
    // move this out to a common function as it will be the same in all classes
    private boolean askforldripc(String Oldname) {
        JOptionPane pane = new JOptionPane();
        int reply = pane.showConfirmDialog(null, "Do you wish to have SMC add its IPC to " + Oldname + "?", "Checking IPC", JOptionPane.YES_NO_OPTION);
        return (reply == JOptionPane.YES_OPTION);
    }
    public boolean UpdateMovementStatus(PersUniti PassMover, Constantvalues.MovementStatus PassMoveStatus) {
        // this update triggers the Command to update OrderofBattle values plus remote computer
        // the Command also triggers counter actions
        InfantryUnitCommonFunctionsc UpdateMoveCF = new InfantryUnitCommonFunctionsc();
        return UpdateMoveCF.UpdateTargetStatus(PassMover);
    }
}
