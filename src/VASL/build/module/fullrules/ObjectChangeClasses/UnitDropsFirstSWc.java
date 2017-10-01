package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class UnitDropsFirstSWc implements StatusChangei {
    private DataC Linqdata = DataC.GetInstance();
    public UnitDropsFirstSWc (){}


    public boolean Takeaction (PersUniti ActiveUnit) {
        // called by SWChangePoss.MoveOK
        // drops SW and update OB, OBSW, and Unpossessed (does Lost check)
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        SuppWeapi SWtoCheck= null;
        int FindSW = 0;
        if (ActiveUnit.getbaseunit().getFirstSWLink() != 0) {
            FindSW = ActiveUnit.getbaseunit().getFirstSWLink();
        }
        if (FindSW == 0) {return false;}  // no SW found
        for (SuppWeapi findSW: Scencolls.SWCol) {
            if (findSW.getbaseSW().getUnit_ID() == FindSW) {
                SWtoCheck = findSW;
                break;
            }
        }
        if (SWtoCheck == null) {return false;}   // no SW found
        String OBSWname = SWtoCheck.getbaseSW().getUnitName();
        ActiveUnit.getbaseunit().setnumSW(ActiveUnit.getbaseunit().getnumSW()- 1);
        if (ActiveUnit.getbaseunit().getnumSW() == SWtoCheck.getbaseSW().getUnit_ID()) {ActiveUnit.getbaseunit().setFirstSWLink(0);}
        GameModule.getGameModule().getChatter().send(ActiveUnit.getbaseunit().getUnitName() + " drops " + SWtoCheck.getbaseSW().getUnitName());
//        Dim SWHexloc = New
//        CombatTerrainClassLibrary.ASLXNA.HexAndLocHolder(CInt(SWtoCheck.BaseSW.Hexnum), CInt(SWtoCheck.BaseSW.hexlocation))
        if (!SWtoCheck.getbaseSW().WeaponIsLost()) {
            CreateUnpossessedSW UnpossSWcreate = new CreateUnpossessedSW();
            UnpossSWcreate.CreateNewUnpossessed(SWtoCheck, (ActiveUnit.getbaseunit().getHexnum()));
            SWtoCheck.getbaseSW().setOwner(0);
        } else {
            SWtoCheck.getbaseSW().setOwner(0);
            SWtoCheck.getbaseSW().setSWStatus(Constantvalues.SWStatus.Malfunctioned);
        }
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs () {
        // no code required; no new unit
        return null;
    }
    public LinkedList<PersUniti> GetNewFirings () {
        // no code required; no new unit
        return null;
    }
    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}