package VASL.build.module.fullrules.DataClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.LOS.Map.Location;

public class OrderofBattleSW {
    private String pOBWeapon;
    private int pOBSW_ID;
    private int pWeaponType;
    private boolean pCaptured;
    private Constantvalues.Nationality pNationality;
    private int pOwner;
    private int pScenario;
    private Constantvalues.SWStatus pStatus;
    private Location pHexlocation;
    private Hex pHex;
    private String pHexname;
    private Constantvalues.AltPos pPosition;
    private Constantvalues.CombatStatus pCombatStatus;
    private Constantvalues.VisibilityStatus pVisibilityStatus;

    public OrderofBattleSW() {
    }

	public String getOBWeapon() {return pOBWeapon;}
	public void setOBWeapon(String value ) {pOBWeapon = value;}
	public int getOBSW_ID() {return pOBSW_ID;}
	public void setOBSW_ID(int value){pOBSW_ID = value;}
	public int getWeaponType() {return pWeaponType;}
	public void setWeaponType(int value) {pWeaponType= value;}
	public boolean getCaptured() {return pCaptured;}
	public void setCaptured(boolean value) {pCaptured = value;}
	public Constantvalues.Nationality getNationality() {return pNationality;}
	public void setNationality(Constantvalues.Nationality value) {pNationality = value;}
	public int getOwner() {return pOwner;}
	public void setOwner(int value) {pOwner = value;}
	public int getScenario() {return pScenario;}
	public void setScenario(int value) {pScenario = value;}
	public Constantvalues.SWStatus getStatus() {return pStatus;}
	public void setStatus(Constantvalues.SWStatus value) {pStatus = value;}
	public Location getHexlocation() {return pHexlocation;}
	public void setHexlocation(Location value) {pHexlocation= value;}
	public Hex getHex() {return pHex;}
	public void setHex(Hex value) {pHex = value;}
	public String getHexname (){return pHexname;}
	public void setHexname(String value){pHexname = value;}
	public Constantvalues.AltPos getPosition() {return pPosition;}
	public void setPosition (Constantvalues.AltPos value ) {pPosition= value;}
	public Constantvalues.CombatStatus getCombatStatus() {return pCombatStatus;}
	public void setCombatStatus(Constantvalues.CombatStatus value) {pCombatStatus = value;}
	public Constantvalues.VisibilityStatus getVisibilityStatus() {return pVisibilityStatus;}
	public void setVisibilityStatus(Constantvalues.VisibilityStatus value) {pVisibilityStatus = value;}
    /*public int getOwnerhex() {
        DataC Linqdata = DataC.GetInstance();
        if (this.getOwner() != 0) {
            OrderofBattle Tempowner = Linqdata.GetUnitfromCol(this.getOwner());
            return (int) Tempowner.gethexnum();
        } else {
            if (this.getStatus() == Constantvalues.SWStatus.DCPlaced) {
                return this.getHexnumber();
            }
            return Linqdata.GetUnpossessedHex(this.getOBSW_ID(), this.getWeaponType());
        }
    }*/

    /*public boolean ISATypeOf(Constantvalues.SWtype TypeTocheck) {
        switch (TypeTocheck) {
            case AnyMG:
                switch (this.getWeaponType()) {
                    // Case 5001 To 5006, 5017 To 5019, 5028 To 5030, 5043 To 5046, 5055 To 5060, 5062 To 5064
                    // 'the weapon is a MG
                    // return true;
                    //case else:
                    //return false;
                }
            case FThr:
                switch (this.getWeaponType()) {
//                    case 5007, 5020, 5031, 5049
//                    'the weapon is a FT
//                    return trrue
//                    case else:
//                    return false;
                }
            case DemoC:
                switch (this.getWeaponType()) {
                    *//*case 5008,5021, 5032, 5048
                        'the weapon is a DCH
                        return true;
                    case else:
                    return false;*//*
                }
            *//*case else:
                return false;*//*
        }
        return false;
    }*/
    public boolean IsInCrestStatus() {
        // determine if SW in crest
        /*if(this.getPosition() == Constantvalues.AltPos.CrestStatus1 And Me.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6)
        Or
                (Me.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And Me.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        Then Return True Else Return False
        End Function
        publicFunction Breaksdown () As Boolean
        Me.Status = CShort(ConstantClassLibrary.ASLXNA.SWStatus.Brokendown)*/
        return false;
    }
}
