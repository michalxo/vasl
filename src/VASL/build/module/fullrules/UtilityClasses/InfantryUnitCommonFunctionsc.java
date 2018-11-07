package VASL.build.module.fullrules.UtilityClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.command.Command;

import java.util.LinkedList;

public class InfantryUnitCommonFunctionsc {
    /* this class hold methods that are common and identical for all of the infantry unit classes
       that use them
     */
    ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    public InfantryUnitCommonFunctionsc () {

    }

    // each method is separate and distinct and performs one function
    public boolean UpdateTargetStatus(PersUniti PassTarget) {
        // MOVE THIS OUT TO A COMMON FUNCTION AS IT WILL BE IDENTICAL ACROSS ALL TARGET CLASSES
        // get Order of Battle unit that matches the PersUniti

        ManageUpdateUnitCommand manageupdateunitcommand = new ManageUpdateUnitCommand();
        Command newcommand = manageupdateunitcommand.CreateCommand(PassTarget, Constantvalues.UnitCommandtype.targunit);
        manageupdateunitcommand.ProcessCommand(newcommand);

        // this may no longer be needed as above may handle for both local and remote
        CommonFunctionsC comfun = new CommonFunctionsC(PassTarget.getbaseunit().getScenario());
        OrderofBattle UpdateUnit = comfun.getUnderlyingOBunitforPersUniti(PassTarget.getbaseunit().getUnit_ID(),  PassTarget.getbaseunit().getUnitName());

        if (UpdateUnit != null) {
            UpdateUnit.setOrderStatus(PassTarget.getTargetunit().getOrderStatus());
            //PassTarget.getbaseunit().setOrderStatus(getOrderStatus());
            UpdateUnit.setCX(PassTarget.getbaseunit().getCX());
            UpdateUnit.setPinned(PassTarget.getbaseunit().getPinned());
            UpdateUnit.setCombatStatus(PassTarget.getbaseunit().getCombatStatus());
            UpdateUnit.setMovementStatus(PassTarget.getbaseunit().getMovementStatus());
            UpdateUnit.setFirstSWLink(PassTarget.getbaseunit().getFirstSWLink());
            UpdateUnit.setSecondSWlink(PassTarget.getbaseunit().getSecondSWLink());
            UpdateUnit.setSW(PassTarget.getbaseunit().getnumSW());
            return true;
        }
        return false;


    }

    public boolean IsUnitACrew(Constantvalues.UClass passclass){
        return (passclass == Constantvalues.UClass.CREWCLASS ||
                passclass == Constantvalues.UClass.ACREWCLASS ||
                passclass == Constantvalues.UClass.ASCREWCLASS ||
                passclass == Constantvalues.UClass.SCREWCLASS);
    }

    public LinkedList<PersUniti> FindUnitsInHex(Hex hextest){
        LinkedList<PersUniti> founditems = new LinkedList<PersUniti>();
        for (PersUniti founditem: Scencolls.Unitcol){
            if (founditem.getbaseunit().getHex().equals(hextest)) {
                founditems.add(founditem);
            }
        }
        return founditems;
    }
    // overload limits units found to those of specified nationality
    public LinkedList<PersUniti> FindUnitsInHex(Hex hextest, Constantvalues.Nationality Nattest){
        LinkedList<PersUniti> founditems = new LinkedList<PersUniti>();
        for (PersUniti founditem: Scencolls.Unitcol){
            if (founditem.getbaseunit().getHex().equals(hextest) && founditem.getbaseunit().getNationality().equals(Nattest)) {
                founditems.add(founditem);
            }
        }
        return founditems;
    }

    public LinkedList<PersUniti> FindUnitsInLocation(Hex hextest, Location loctest, Constantvalues.Nationality Nattest){
        LinkedList<PersUniti> unitsinhex = FindUnitsInHex(hextest);
        LinkedList<PersUniti> unitsinloc = new LinkedList<PersUniti>();
        for (PersUniti hexunit: unitsinhex){
            if (hexunit.getbaseunit().gethexlocation().equals(loctest) && hexunit.getbaseunit().getNationality().equals(Nattest)) {
                unitsinloc.add(hexunit);
            }
        }
        return unitsinloc;
    }
    public boolean IsBrokenorUnarmed(PersUniti infcheck){
        return (infcheck.getbaseunit().getOrderStatus().equals(Constantvalues.OrderStatus.Broken) ||
                infcheck.getbaseunit().getOrderStatus().equals(Constantvalues.OrderStatus.Broken_DM) ||
                infcheck.getbaseunit().getOrderStatus().equals(Constantvalues.OrderStatus.Disrupted) ||
                infcheck.getbaseunit().getOrderStatus().equals(Constantvalues.OrderStatus.DisruptedDM) ||
                infcheck.getbaseunit().getOrderStatus().equals(Constantvalues.OrderStatus.Unarmed) ||
                infcheck.getbaseunit().getOrderStatus().equals(Constantvalues.OrderStatus.Prisoner));
    }
}
