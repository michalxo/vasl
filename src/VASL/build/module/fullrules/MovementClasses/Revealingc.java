package VASL.build.module.fullrules.MovementClasses;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectChangeClasses.*;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.TerrainClasses.IsTerrain;
import VASL.build.module.fullrules.UtilityClasses.RandomSelection;

import java.util.ArrayList;
import java.util.LinkedList;

public class Revealingc {

    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    public Revealingc() {}
        /*'Public Function RevealConcealUsingRanSel(ByVal RanSelEligList As List(Of ObjectClassLibrary.ASLXNA.LocationContent)) As Boolean
                '    'called by MovementValidation.New
        '    'reveals a single concealed unit by random selection
        '    Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
                '    Dim RndSel As New UtilClassLibrary.ASLXNA.RandomSelection
                '    Dim SelItems As Boolean()
                '    SelItems = RndSel.RandomSel(1, RanSelEligList.Count)
                '    Dim y As Integer = 0
                '    For Each IteminHex As ObjectClassLibrary.ASLXNA.LocationContent In RanSelEligList
                '        If SelItems(y) = True Then  'Random Selected; if more than one, reveal first - only one reveal required
        '            'unconceal unit
        '            Dim hexunit As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = IteminHex.ObjID Select selunit).First
                '            Dim conidtotest As Integer = CInt(hexunit.BasePersUnit.Con_ID)
                '            Dim UnittoChange As ObjectChange.ASLXNA.iVisibilityChange = New ObjectChange.ASLXNA.RevealUnitC(IteminHex.ObjID)
                '            UnittoChange.TakeAction()
                '            Return True
                '            Exit For
                '        Else
                '        End If
                '        y += 1
                '    Next
                '    'if here then no unit selected
        '    Return False
                'End Function*/
    public PersUniti RevealConcealUsingRanSel(LinkedList<LocationContentc> RanSelEligList) {
        // called by
        // reveals a single concealed unit by random selection

        RandomSelection RndSel = new RandomSelection();
        boolean[] SelItems;
        SelItems = RndSel.RandomSel(1, RanSelEligList.size());
        int y  = 0;
        for (LocationContentc IteminHex: RanSelEligList) {
            if (SelItems[y] == true) { // Random Selected; if more than one, reveal first - only one reveal required
                // unconceal unit
                for (PersUniti hexunit : Scencolls.Unitcol) {
                    if (hexunit.getbaseunit().getUnit_ID() == IteminHex.getObjID()) {
                        int conidtotest = hexunit.getbaseunit().getCon_ID();
                        VisibilityChangei UnittoChange = new RevealUnitC(hexunit);
                        UnittoChange.TakeAction();
                        return hexunit;
                    }
                }
            }
            y += 1;
        }
        // if here then no unit selected
        return null;
    }
    public PersUniti RevealSingleItem(int ItemtoGet) {
        // called by MovementValidation.new
        // reveal single concealed item
        // unconceal unit

        for (PersUniti hexunit : Scencolls.Unitcol) {
            if (hexunit.getbaseunit().getUnit_ID() == ItemtoGet) {
                int conidtoget = hexunit.getbaseunit().getCon_ID();
                VisibilityChangei UnittoChange = new RevealUnitC(hexunit);
                UnittoChange.TakeAction();
                // delete unit from Concealment - since only concealed unit is now revealed
                UnittoChange = new ElimConcealC(conidtoget);
                UnittoChange.TakeAction();
                return hexunit;
            }
        }
        // if here then no unit selected
        return null;
    }

    /*public boolean RevealSingleItem(int ItemtoGet, int RevealedUnit) {
        // called by MovementValidation.new
        // reveal single concealed item
        RevealedUnit = 0;

        PersUniti hexunit = (From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = ItemtoGet Select selunit).First
        int conidtoget = hexunit.getbaseunit().getCon_ID();
        VisibilityChangei UnittoChange = new RevealUnitC(ItemtoGet);
        UnittoChange.TakeAction();
        // delete unit from Concealment - since only concealed unit is now revealed
        UnittoChange = new ElimConcealC(conidtoget);
        UnittoChange.TakeAction();
        return true;
    }*/
    public boolean RevealEntering(Hex hextouse, Constantvalues.UMove MovementOptionClicked) {
        // called by MovementValidation.New
        // removes concealment from entering unit and deletes dummies

        LinkedList<PersUniti> Removelist = new LinkedList<PersUniti>();
        ArrayList<Integer> EliminatedCon = new ArrayList<Integer>();
        int ConID =0;
        IsTerrain IsTerrChk = new IsTerrain();
        VisibilityChangei UnittoChange;
        // loop through moving units
        for (PersUniti MovItem : Scencolls.SelMoveUnits) {
            if (MovementOptionClicked == Constantvalues.UMove.Assault  && !(IsTerrChk.IsLocationTerrainA(hextouse, MovItem.getbaseunit().gethexlocation(),
                    Constantvalues.Location.OpenGround)) && !(IsTerrChk.IsLocationTerrainA(MovItem.getbaseunit().getHex(), MovItem.getbaseunit().gethexlocation(),
                    Constantvalues.Location.OpenGround))) {
                // not revealed - FIND THE RULES REFERENCE FOR THIS?
            } else {
                // reveal all units/dummies/SW
                if (MovItem.getMovingunit().IsDummy()) {
                    // this deletes dummy
                    RevealDummyc UnittoReveal = new RevealDummyc(MovItem);
                    UnittoReveal.TakeAction();
                    Removelist.add(MovItem); // put in list to remove from stack
                    ConID = MovItem.getbaseunit().getCon_ID(); // identify concealment counter to be removed
                } else {
                    // show revealed units and update collection
                    if (MovItem.getMovingunit().getIsConcealed()) { // some moving units may be already known; don' t reveal
                        MovItem.getMovingunit().setIsConcealed(false);
                        RevealUnitC UnittoReveal = new RevealUnitC(MovItem);
                        // Revealed unit DMs adjacent enemy
                        AutoDM DMCHeck = new AutoDM(MovItem);
                        UnittoReveal.TakeAction();
                        ConID = MovItem.getbaseunit().getCon_ID(); // identify concealment counter to be removed
                    }
                }
                // eliminate the concealment counter
                // check from previously eliminated con counters
                if (EliminatedCon.size() > 0) { // some already eliminated
                    for (int ECon : EliminatedCon) {
                        if (ConID == ECon) {
                            // already eliminated so do nothing
                        } else {
                            // need to eliminate
                            EliminatedCon.add(ConID);
                            break;
                        }
                    }
                } else if (ConID > 0) { // need to eliminate
                    EliminatedCon.add(ConID);
                }
            }
        }
        // remove concealment counter from collection and from Scencolls.ConCol
        for (int ECon : EliminatedCon) {
            ElimConcealC UnittoElim = new ElimConcealC(ECon);
            UnittoElim.TakeAction();
        }
        // remove revealed dummies from Scencolls.selmoveunits
        for (PersUniti RemoveItem : Removelist) {
            Scencolls.SelMoveUnits.remove(RemoveItem);
        }
        return true;
    }
}
