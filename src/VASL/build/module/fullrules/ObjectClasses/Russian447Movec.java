package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;
import VASL.build.module.fullrules.UtilityClasses.ManageUpdateUnitCommand;
import VASSAL.command.Command;

public class Russian447Movec implements MovingPersuniti {

    private boolean concealedvalue;
    private String myOBname;
    private Hex currhex;
    private Location currhexloc;
    private Constantvalues.AltPos currhexpos;
    private int OBID;
    private double MFleft;
    private boolean UsingDTvalue;
    private boolean UsingRBvalue;
    private boolean usingencircvalue;
    private boolean hasldrbvalue;
    private double mfusedvalue;
    private Constantvalues.MovementStatus AMvalue;
    private Constantvalues.MovementStatus Dashvalue;
    private int LOCIndexvalue;
    private int HexEntSideCross;
    // private mysmokee As Integer

    public Russian447Movec (String PassOBname, Hex Passhex, Location Passhexlocation, Constantvalues.AltPos PassPosition, int PassLocIndex,
                            int PassOBUnitID, Constantvalues.VisibilityStatus PassVisibilityStatus, int PassConID, int PasshexEnteredSideCrossedLastMove) {
        myOBname = PassOBname;
        currhex = Passhex;
        currhexloc = Passhexlocation;
        currhexpos = PassPosition;
        LOCIndexvalue = PassLocIndex;
        OBID = PassOBUnitID;
        if (PassVisibilityStatus == Constantvalues.VisibilityStatus.Concealed && PassConID > 0) {concealedvalue=true;}
        HexEntSideCross = PasshexEnteredSideCrossedLastMove;

        // setting values to avoid nulls - this will go away as build up xxOB.txt file structure
        UsingDTvalue=false;
        UsingRBvalue=false;
        MFleft = CalcMF();
        usingencircvalue = false;
        hasldrbvalue = false;
        mfusedvalue = 0;
        AMvalue= Constantvalues.MovementStatus.NotMoving;
        Dashvalue = Constantvalues.MovementStatus.NotMoving;

    }


    public double CalcMF() {return 4;}
    public double getMFAvailable() {return MFleft;}
    public void setMFAvailable(double value){MFleft=value;}
    public String getItemName () {return myOBname;}
    public int getItemID() {return OBID;}
    public int getIPC() {return 3;}
    public boolean IsDummy() {return false;}
    public boolean getIsConcealed () {return concealedvalue;}
    public void setIsConcealed(boolean value) {concealedvalue = value;}
    public boolean getUsingDT () {return UsingDTvalue;}
    public void setUsingDT(boolean value) {UsingDTvalue = value;}
    public boolean getUsingRoadBonus() {return UsingRBvalue;}
    public void setUsingRoadBonus(boolean value) {UsingRBvalue = value;}
    public boolean getHasLdrBonus () {return hasldrbvalue;}
    public void setHasLdrBonus(boolean value) {hasldrbvalue = value;}
    public double getMFUsed () {return mfusedvalue;}
    public void setMFUsed(double value) {mfusedvalue = value;}
    public boolean getPPImpact () {return false;}
    public Constantvalues.MovementStatus getAssaultMove () {return AMvalue;}
    public void setAssaultMove(Constantvalues.MovementStatus value) {AMvalue = value;}
    public Constantvalues.MovementStatus getDash () {return Dashvalue;}
    public void setDash(Constantvalues.MovementStatus value) {Dashvalue = value;}
    public int getHexEnteredSideCrossed () {return HexEntSideCross;}
    public void setHexEnteredSideCrossed(int value) {HexEntSideCross = value;}
    public int getSmokeE () {return 1;}
    public boolean getusingEncirc () {return usingencircvalue;}
    public void setusingEncirc(boolean value) {usingencircvalue = value;}

    public boolean UpdateMovementStatus(PersUniti mover, Constantvalues.MovementStatus NewMovementStatus) {

        mover.getbaseunit().setMovementStatus(NewMovementStatus);

        //ScenarioC scen = ScenarioC.getInstance();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        SuppWeapi MovingSW = null;
        // this may no longer be needed as below  may handle for both local and remote
        CommonFunctionsC comfun = new CommonFunctionsC(mover.getbaseunit().getScenario());
        OrderofBattle UpdateUnit = comfun.getUnderlyingOBunitforPersUniti(mover.getbaseunit().getUnit_ID(),  mover.getbaseunit().getUnitName());
        if (UpdateUnit != null) {
            UpdateUnit.setMovementStatus(NewMovementStatus);
            if (mover.getbaseunit().getFirstSWLink() != 0) {
                for(SuppWeapi testOBSWunit: Scencolls.SWCol ) {
                    if (testOBSWunit.getbaseSW().getSW_ID() == mover.getbaseunit().getFirstSWLink()) {
                        MovingSW = testOBSWunit;
                        break;
                    }
                }
                MovingSW.getbaseSW().setMovementStatus(NewMovementStatus);
            }
            if (mover.getbaseunit().getSecondSWLink() != 0) {
                for(SuppWeapi testOBSWunit: Scencolls.SWCol ) {
                    if (testOBSWunit.getbaseSW().getSW_ID() == mover.getbaseunit().getSecondSWLink()) {
                        MovingSW = testOBSWunit;
                        break;
                    }
                }
                MovingSW.getbaseSW().setMovementStatus(NewMovementStatus);
            }
        }
        InfantryUnitCommonFunctionsc UpdateTargCF = new InfantryUnitCommonFunctionsc();
        return UpdateTargCF.UpdateMovementStatus(mover);

//        if (UpdateUnit != null) {
//
//
//        }

        //CounterActions counteractions = new CounterActions();
        //counteractions.placefirecounter(myUnit);

    }
}
