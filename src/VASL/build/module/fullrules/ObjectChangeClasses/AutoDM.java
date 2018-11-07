package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;
import VASL.build.module.fullrules.UtilityClasses.MapCommonFunctionsc;

import java.util.LinkedList;

public class AutoDM {
    // handles Adjacent DM check and change
    private Hex starthex;
    private Location starthexlocation;
    private Constantvalues.AltPos starthexposition;
    private Constantvalues.Nationality Stacknat;
    private int ScenID;
    private ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();
    private LinkedList<Hex> myHexesToDm = new LinkedList<Hex>();

    public AutoDM(LinkedList<PersUniti> PassUnitList) {
        // called by various methods where action is sufficient to DM enemy units, mainly in Movement and ObjectChange
        // automatically DMs ADJACENT broken enemy
        // PassUnit is the stack of units that cause DM, need to find enemy brokies ADJACENT to it
        int AllConc = 2;
        // Determine stack visibility status - need to be known
        VisibilityC VisibilityStatus = new VisibilityC();
        int EnteringVisStatus = VisibilityStatus.GetStackStatus(PassUnitList);
        if (EnteringVisStatus != AllConc) {
            for (PersUniti StackUnit : PassUnitList) {
                if (StackUnit.getbaseunit().getVisibilityStatus() != Constantvalues.VisibilityStatus.Visible) {
                    continue; // concealed units do not cause DM on adjacent units
                }
                if (StackUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Unarmed) {
                    // DM any ADJACENT broken enemy
                    starthex = StackUnit.getbaseunit().getHex();
                    starthexlocation = StackUnit.getbaseunit().gethexlocation();
                    starthexposition = StackUnit.getbaseunit().gethexPosition();
                    Stacknat = StackUnit.getbaseunit().getNationality();
                    ScenID = StackUnit.getbaseunit().getScenario();
                    MakeAdjacentDM();
                    break; // only need to do once
                }
            }
        }
    }

    public AutoDM(PersUniti actionunit) {
        // called by various methods where action is sufficient to DM enemy units, mainly in Movement and ObjectChange
        // automatically DMs ADJACENT broken enemy
        // PassUnitID is the unit that can cause DM, need to find enemy brokies ADJACENT to it
        int AllConc = 2;
        LinkedList<PersUniti> PassUnitList = new LinkedList<PersUniti>();
        PassUnitList.add(actionunit);
        // Determine stack visibility status - need to be known
        VisibilityC VisibilityStatus = new VisibilityC();
        int StackVisStatus = VisibilityStatus.GetStackStatus(PassUnitList);
        if (StackVisStatus != AllConc) {
            for (PersUniti StackUnit : PassUnitList) {
                if (StackUnit.getbaseunit().getVisibilityStatus() != Constantvalues.VisibilityStatus.Visible) {
                    continue; // concealed units do not cause DM on adjacent units
                }
                if (StackUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Unarmed) {
                    // DM any ADJACENT broken enemy
                    starthex = StackUnit.getbaseunit().getHex();
                    starthexlocation = StackUnit.getbaseunit().gethexlocation();
                    starthexposition = StackUnit.getbaseunit().gethexPosition();
                    Stacknat = StackUnit.getbaseunit().getNationality();
                    ScenID = StackUnit.getbaseunit().getScenario();
                    MakeAdjacentDM();
                    break; // only need to do once
                }
            }
        }
    }

    public LinkedList<Hex> getHexesToDM() {
        return myHexesToDm;
    }

    private void MakeAdjacentDM() {
        boolean EnemyPresent = false;
        LinkedList<Locationi> ADJACENTLocs = new LinkedList<Locationi>();
        // first get list of adjacent Hexes
        MapCommonFunctionsc mapcommfunc = new MapCommonFunctionsc();
        Hex[] adjacentHexArray = mapcommfunc.getAdjacentHexArray(starthex);
        // get ADJACENT Locationc's
        /*ADJACENTLocs =ADJTest.AllADJACENTLocations();
        // check for enemy present in each ADJACENT location
        for (Locationi LocToCheck: ADJACENTLocs) {
            // Determine if present units (both real and dummy) are enemy to the moving side
            EnemyChecksC CheckforEnemy = new EnemyChecksC(LocToCheck.getvasllocation(),Stacknat,ScenID);
            EnemyPresent = CheckforEnemy.EnemyInHexTest();
            if (EnemyPresent) {
                // if found then get hex contents
                ContentsofLocation ContentsofLocationToBeSearched = new UtilWObj.ASLXNA.ContentsofLocation(LocToCheck);
                ContentsofLocationToBeSearched.GetContents();
                if (!(ContentsofLocationToBeSearched.ContentsInLocation() == null)) {
                    // look for broken infantry of other side
                    for (LocationContent ItemInHex: ContentsofLocationToBeSearched.ContentsInLocation()){
                        if (TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ItemInHex.TypeID)) {
                            // infantry
                            PersUniti hexUnit =(From selunit As ObjectClassLibrary.ASLXNA.PersUniti In scencolls.Unitcol Where
                            selunit.BasePersUnit.Unit_ID = ItemInHex.ObjID Select selunit).First
                            // side and visibility check
                            if (!(hexUnit.BasePersUnit.Nationality =Stacknat) AndAlso hexUnit.BasePersUnit.VisibilityStatus =
                                ConstantClassLibrary.ASLXNA.VisibilityStatus.Visible)){
                                // order check - must be broken or disrupted
                                if (hexUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken) {
                                    StatusChangei SetDM = new UnitDMsc();
                                    SetDM.Takeaction(hexUnit);
                                    myHexesToDm.add(hexUnit.getbaseunit().getHex());
                                } else if (hexUnit.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Disrupted) {
                                    StatusChangei SetDM = new UnitDisruptDMsc();
                                    SetDM.Takeaction(hexUnit);
                                    myHexesToDm.add(hexUnit.getbaseunit().getHex());
                                }
                            }
                        }
                    }
                }
            }
        }*/
    }

}
