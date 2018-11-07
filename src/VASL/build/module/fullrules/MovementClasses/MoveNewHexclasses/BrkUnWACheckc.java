package VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.TerrainClasses.IsSide;
import VASL.build.module.fullrules.TerrainClasses.IsTerrain;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.build.module.fullrules.UtilityClasses.EnemyChecksC;
import VASL.build.module.fullrules.UtilityClasses.InfantryUnitCommonFunctionsc;

import java.util.LinkedList;

public class BrkUnWACheckc {

    private Hex hexclickedvalue;
    private Location locationinhexvalue;
    private Constantvalues.Nationality movnat;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private ScenarioC scen = ScenarioC.getInstance();

    public BrkUnWACheckc(Hex hexclicked, Constantvalues.Nationality MovingNationality, Location LocationInhex) {

        hexclickedvalue = hexclicked;
        locationinhexvalue = LocationInhex;
        movnat = MovingNationality;

    }
    public boolean BrokenUnarmedWACheck() {
        // called by MoveUpdate methods
        // looks for broken or unarmed friendlies in hexes where a unit has gained WA to see if they do so as well
        boolean BrokenUnarmedWACheck = false;
        InfantryUnitCommonFunctionsc infcomfunc = new InfantryUnitCommonFunctionsc();
        LinkedList<PersUniti> unitsinloc = infcomfunc.FindUnitsInLocation(hexclickedvalue, locationinhexvalue, movnat);
        if(unitsinloc == null || unitsinloc.isEmpty() ) {return true;}  // no friendlies in new location

        // something in the hex; loop thorugh, testing for enemy or brk/unarmed
        EnemyChecksC CheckforEnemy = new EnemyChecksC(unitsinloc.getFirst().getbaseunit().gethexlocation(), unitsinloc.getFirst().getbaseunit().getNationality(), scen.getScenID());
        if (CheckforEnemy.EnemyinLocationTest()) {
            return true; // enemy in same location - can't change WA status  -IS THIS CORRECT?
        }
        // look for broken or unarmed friendlies

        IsSide SideChk = new IsSide();
        Constantvalues.AltPos PassPosition = Constantvalues.AltPos.None;
        for (PersUniti infcheck: unitsinloc ) {
            if (infcomfunc.IsBrokenorUnarmed(infcheck)) {
                // determine if WA gain is mandatory or voluntary
                if (SideChk.IsWAMandatory(infcheck.getbaseunit().getHex(), infcheck.getbaseunit().gethexlocation(), infcheck.getbaseunit().gethexPosition())) {
                    DoChange(infcheck);
                } else { // voluntary
                    //Dim response  As Integer = MsgBox("Do you wish to claim Wall Advantage for " & Trim(infcheck.BasePersUnit.UnitName) & " (Y/N)?", MsgBoxStyle.YesNo, "Good Order Unit has claimed WA")
                    //If response = 6
                    //Then 'Yes
                    DoChange(infcheck);
                }
            }

        }
        return true;

    }
    public void DoChange (PersUniti infcheck){
        IsTerrain IsTerchk = new IsTerrain();
        if (IsTerchk.IsPositionCrest(infcheck.getbaseunit().gethexPosition())) {
            ConversionC confrom = new ConversionC();
            infcheck.getbaseunit().sethexPosition(confrom.ConvertCresttoWACrest(infcheck.getbaseunit().gethexPosition()));
        }  else {
            infcheck.getbaseunit().sethexPosition(Constantvalues.AltPos.WallAdv);
        }
    }

}
