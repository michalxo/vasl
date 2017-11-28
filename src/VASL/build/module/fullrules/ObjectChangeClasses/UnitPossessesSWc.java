package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Unpossessed;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class UnitPossessesSWc implements StatusChangei {

    private int mySWtoPossess;
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    //private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)

    public UnitPossessesSWc(int SWToPossess) {
        mySWtoPossess = SWToPossess;
    }
    public boolean Takeaction(PersUniti GettingUnit) {
        // called by SWChangePoss.MoveOK and others
        // possesses SW and update OB, OBSW, and Unpossessed
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        SuppWeapi SWtoCheck = null;
        ChangeSWOwnerc SWChange;
        if (mySWtoPossess == 0) {
            return false;
        }  // no SW found
        for (SuppWeapi TestSW: Scencolls.SWCol){
            if (TestSW.getbaseSW().getUnit_ID() == mySWtoPossess) {
                SWtoCheck = TestSW;
                break;
            }
        }
        if (SWtoCheck == null) {
            return false;
        }
        if (GettingUnit.getbaseunit().getnumSW() == 2) {
            return false;
        } // cannot possess an additional sw
        String OBSWname = SWtoCheck.getbaseSW().getUnitName();
        GettingUnit.getbaseunit().setnumSW(GettingUnit.getbaseunit().getnumSW() + 1);
        if (GettingUnit.getbaseunit().getFirstSWLink() == 0) {
            GettingUnit.getbaseunit().setFirstSWLink(mySWtoPossess);
            SWChange = new ChangeSWOwnerc(GettingUnit.getbaseunit().getFirstSWLink(), GettingUnit.getbaseunit().getUnit_ID());
        } else {
            GettingUnit.getbaseunit().setSecondSWLink(mySWtoPossess);
            SWChange = new ChangeSWOwnerc(GettingUnit.getbaseunit().getSecondSWLink(), GettingUnit.getbaseunit().getUnit_ID());
        }
        GameModule.getGameModule().getChatter().send(GettingUnit.getbaseunit().getUnitName() + " gains " + SWtoCheck.getbaseSW().getUnitName());
        //Dim SWHexloc = New CombatTerrainClassLibrary.ASLXNA.HexAndLocHolder(CInt(SWtoCheck.BaseSW.Hexnum), CInt(SWtoCheck.BaseSW.hexlocation))

        // Delete Unpossessed
        try {
            Unpossessed DelUnpossessed = null;
            for (Unpossessed TestUnpossessed: Scencolls.Unpossesseds) {
                if (TestUnpossessed.getEquipmentID() == mySWtoPossess) {
                    DelUnpossessed = TestUnpossessed;
                    break;
                }
            }
            Scencolls.Unpossesseds.remove(DelUnpossessed);
        } catch (Exception e) {
            // do nothing, no unpossessed exists
        }
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs () {
            return myNewTargs;
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
