package VASL.build.module.fullrules.DataClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class  OrderofBattle {
    private String pOBName;
    private String pHexname;
    private Hex phex;
    private int pScenario;
    private Location phexlocation;
    private Constantvalues.AltPos pPosition;
    private int pLevelinHex;
    private int pLOBLink;
    private boolean pPinned;
    private boolean pCX;
    private int pELR;
    private int pSW;
    private int pTurnArrives;
    private Constantvalues.Nationality pNationality;
    private int pCon_ID;
    private int pOBUnit_ID;
    private int pFirstSWLink;
    private int pSecondSWlink;
    private Constantvalues.VisibilityStatus pVisibilityStatus;
    private Constantvalues.CombatStatus pCombatStatus;
    private Constantvalues.MovementStatus pMovementStatus;
    private Constantvalues.OrderStatus pOrderStatus;
    private Constantvalues.FortitudeStatus pFortitudeStatus;
    private Constantvalues.RoleStatus pRoleStatus;
    private Constantvalues.CharacterStatus pCharacterStatus;
    private int pHexEnteredSideCrossedLastMove;
    private int pGuard_ID;

    public OrderofBattle() {
    }


	public String getOBName() {return pOBName;}
    public void setOBName(String value) {pOBName = value;}
	public String  getHexname() {return pHexname;}
    public void setHexname(String value){pHexname = value;}
    public Hex  gethex() {return phex;}
    public void sethex(Hex value){phex = value;}
    public int  getScenario() {return pScenario;}
    public void setScenario(int value) {pScenario = value;}
	public Location   gethexlocation() {return  phexlocation;}
    public void sethexlocation(Location value) {phexlocation = value;}
	public Constantvalues.AltPos  getPosition() {return  pPosition;}
    public void setPosition(Constantvalues.AltPos value) {pPosition = value;}
	public int  getLevelinHex() {return  pLevelinHex;}
    public void setLevelinHex(int value) {pLevelinHex = value;}
	public int  getLOBLink() {return  pLOBLink;}
    public void setLOBLink(int value) {pLOBLink = value;}
	public boolean  getPinned() {return  pPinned;}
    public void setPinned(boolean value)  {pPinned = value;}
	public boolean getCX() {return pCX;}
    public void setCX(boolean value) {pCX = value;}
	public int getELR() {return  pELR;}
    public void setELR(int value) {pELR = value;}
	public int getSW() {return  pSW;}
    public void setSW(int value) {pSW = value;}
	public int getTurnArrives() {return  pTurnArrives;}
    public void setTurnArrives(int value){pTurnArrives = value;}
	public Constantvalues.Nationality getNationality() {return  pNationality;}
    public void setNationality(Constantvalues.Nationality value) {pNationality = value;}
	public int getCon_ID() {return  pCon_ID;}
    public void setCon_ID(int  value)  {pCon_ID = value;}
	public int getOBUnit_ID() {return  pOBUnit_ID;}
    public void setOBUnit_ID(int value){pOBUnit_ID = value;}
	public int getFirstSWLink() {return  pFirstSWLink;}
    public void setFirstSWLink(int value) {pFirstSWLink = value;}
	public int getSecondSWlink() {return  pSecondSWlink;}
    public void setSecondSWlink(int value){pSecondSWlink = value;}
	public Constantvalues.VisibilityStatus  getVisibilityStatus() {return  pVisibilityStatus;}
    public void setVisibilityStatus(Constantvalues.VisibilityStatus value) {pVisibilityStatus = value;}
	public Constantvalues.CombatStatus  getCombatStatus() {return  pCombatStatus;}
    public void setCombatStatus(Constantvalues.CombatStatus value){pCombatStatus = value;}
	public Constantvalues.MovementStatus  getMovementStatus() {return  pMovementStatus;}
    public void setMovementStatus(Constantvalues.MovementStatus value) {pMovementStatus = value;}
	public Constantvalues.OrderStatus  getOrderStatus() {return  pOrderStatus;}
    public void setOrderStatus(Constantvalues.OrderStatus value){pOrderStatus = value;}
	public Constantvalues.FortitudeStatus  getFortitudeStatus() {return  pFortitudeStatus;}
    public void setFortitudeStatus(Constantvalues.FortitudeStatus value) {pFortitudeStatus = value;}
	public Constantvalues.RoleStatus  getRoleStatus() {return  pRoleStatus;}
    public void setRoleStatus(Constantvalues.RoleStatus value){pRoleStatus = value;}
	public Constantvalues.CharacterStatus  getCharacterStatus() {return  pCharacterStatus;}
    public void setCharacterStatus(Constantvalues.CharacterStatus value){pCharacterStatus = value;}
	public int  getHexEnteredSideCrossedLastMove() {return  pHexEnteredSideCrossedLastMove;}
    public void setHexEnteredSideCrossedLastMove(int value){pHexEnteredSideCrossedLastMove = value;}
	public int  getGuard_ID() {return  pGuard_ID;}
    public void setGuard_ID(int value) {pGuard_ID = value;}

    // methods
    public boolean CanStillUseInherentFP(int UsingSW) {
        // called by IFT.AddtoFireGroupandTargetGroup
        // determines if unit can use their inherent FP due to SW use
        switch (UsingSW) {
            case 2:return false;
            // no unit can use 2 SW and still fire inherent FP
            case 1:
                /*Constantvalues.Utype Unittype; //CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType, CInt(Me.LOBLink)))
                if (Unittype == Constantvalues.Utype.Squad) {
                    return true;
                } else {
                    return false;
                }*/
            default:
                return true;
            // if no SW used then inherent FP ok
        }
    }

    public boolean IsUnitALeader() {
        /*Dim Linqdata = ASLXNA.DataC.GetInstance()
        return If(CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType, CInt(Me.LOBLink))) >=
                ConstantClassLibrary.ASLXNA.Utype.LdrHero And CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType,
                CInt(Me.LOBLink))) <= ConstantClassLibrary.ASLXNA.Utype.SMC, True, False)*/
        return false;
    }
    public boolean IsUnitASMC() {
        /*Dim Linqdata = ASLXNA.DataC.GetInstance()
        Dim Testcase
        As Boolean = If(CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType, CInt(Me.LOBLink))) >=
                ConstantClassLibrary.ASLXNA.Utype.LdrHero And CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitType,
                CInt(Me.LOBLink))) <= ConstantClassLibrary.ASLXNA.Utype.Commissar, True, False)*/
        return false;
    }
    public boolean UnitHasFT() {

        /*'For x As Integer = 1 To 2
        '    Dim OBSWPoss As Integer = Game.Scenario.UnitState.GetSW(Me, x)
        '    If OBSWPoss <> 0 Then  ' 0 value means no SW
        '        ' retrieve type of SW
        '        Dim OBSWtype As Integer = CInt(Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.Weapontype, OBSWPoss))
        '        ' check if Sw type is a FT
        '        If Game.Scenario.SWStatus.ISSWtype(OBSWtype, ConstantClassLibrary.ASLXNA.SWtype.FThr) Then   ' SW is
        a FT
        '            UnitHasFT = True
        '            Exit For
        '        End If
        '    End If
        'Next x
        If Me.FirstSWLink<> 0 Then '0 value means no SW
        'check if Sw type is a FT
        Dim SWToCheck As DataClassLibrary.OrderofBattleSW = Linqdata.GetOBSWRecord(CInt(Me.FirstSWLink))
        If SWToCheck.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.FThr) Then return True 'sw is a ft
        'If Game.Scenario.SWStatus.ISSWtype(OBSWtype, ConstantClassLibrary.ASLXNA.SWtype.FThr) Then return True ' SW is
        a FT
        End If
        If Me.SecondSWlink<> 0 Then '0 value means no SW
        'check if Sw type is a FT
        Dim SWToCheck As DataClassLibrary.OrderofBattleSW = Linqdata.GetOBSWRecord(CInt(Me.SecondSWlink))
        If SWToCheck.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.FThr) Then return True 'SW is a FT
        End If
        'if here then neither SW is a FT*/
        return false;
    }
    public boolean HasWallAdvantage() {
        /*If Me.Position = ConstantClassLibrary.ASLXNA.AltPos.WallAdv
        Or(Me.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And
                Me.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6) Then return True Else return False*/
                return false;
    }
    public boolean IsOccupyingFeature() {
        /*If Me.Position = ConstantClassLibrary.ASLXNA.AltPos.InFoxhole Or Me.
        Position = ConstantClassLibrary.ASLXNA.AltPos.InSanger Or
        Me.Position = ConstantClassLibrary.ASLXNA.AltPos.InTrench Then return True Else return False*/
                return false;
    }
    public boolean UnitHasMG() {
        /*Dim Linqdata = ASLXNA.DataC.GetInstance()
        If Me.FirstSWLink<> 0 Then '0 value means no SW
        'check if Sw type is a MG
        Dim SWToCheck As DataClassLibrary.OrderofBattleSW = Linqdata.GetOBSWRecord(CInt(Me.FirstSWLink))
        If SWToCheck.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.AnyMG) Then return True 'sw is a MG
        End If
        If Me.SecondSWlink<> 0 Then '0 value means no SW
        'check if Sw type is a MG
        Dim SWToCheck As DataClassLibrary.OrderofBattleSW = Linqdata.GetOBSWRecord(CInt(Me.SecondSWlink))
        If SWToCheck.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.AnyMG) Then return True 'SW is a MG
        End If
        'if here then no MG found*/
        return false;
    }
    public boolean IsInCrestStatus() {
        /*'determine if unit in crest
        If(Me.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus1 And Me.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus6)
        Or
                (Me.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And Me.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6)
        Then return True Else return False*/
                return false;
    }

}
