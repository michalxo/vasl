package VASL.build.module.fullrules.Game;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.OpenSaveGame;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class UnitActionsC {
    // called by Me.CreateCounters
    // creates the Scencoll collection of units in the scenario

    // constructor
    public UnitActionsC(LinkedList<OrderofBattle> PassOBUnitcol) {
        // get all units involved in a scenario

        if (PassOBUnitcol.size() == 0) {
            GameModule.getGameModule().getChatter().send("No scenario units found. Exiting");
            return;
        }

            // use Object Factory to create the units
        PersCreation UseObjectFactory = new PersCreation();
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        PersUniti AddNewUnit;
        Constantvalues.UClass PassClass = Constantvalues.UClass.NONE;
        for (OrderofBattle unititem : PassOBUnitcol) {
            PassClass = Constantvalues.UClass.NONE; //  CInt(Game.Linqdata.GetLOBData(Constantvalues.LOBItem.UnitClass, CInt(unititem.LOBLink)))
            if (unititem.getOrderStatus() == Constantvalues.OrderStatus.KIAInf ||
                unititem.getOrderStatus() == Constantvalues.OrderStatus.NotInPlay) {
                continue;
            }
            AddNewUnit = UseObjectFactory.CreateExistingInstance(unititem);
            if (AddNewUnit == null) {
                continue;
            }
                // temporary while debugging UNDO
            /*if (UseObjectFactory.IsHeroic(AddNewUnit.getbaseunit().getFortitudeStatus())) {
                // need to decorate leader
                Dim DecNewLeader As ObjectClassLibrary.ASLXNA.PersunitDecoratori = New
                ObjectClassLibrary.ASLXNA.PersunitHeroicldrc(AddNewUnit)
                if (AddNewUnit.getbaseunit() != null) {
                    DecNewLeader.BasePersUnit = New ObjectClassLibrary.ASLXNA.BaseHeroicLdrc(AddNewUnit.BasePersUnit);
                }
                AddNewUnit = DecNewLeader;
            }*/
                // add new unit to Unitcol collection
            Scencolls.Unitcol.add(AddNewUnit);

        }

        // temporary while debugging undo
        /*'create guard lists - IS THIS BEST PLACE ? AUG 14
        Dim GetGuards

        As List (Of ObjectClassLibrary.ASLXNA.PersUniti) =(
                From selunit
        As ObjectClassLibrary.
        ASLXNA.PersUniti In
        Scencolls.Unitcol Where
        selunit.BasePersUnit.RoleStatus =
                Constantvalues.RoleStatus.GuardUnit Select
        selunit).ToList
        For Each
        TestGuard As
        ObjectClassLibrary.ASLXNA.PersUniti In
        GetGuards
        'find units it is guarding and add to list
        Dim PrisonerUnits

        As IQueryable (Of Integer) =
        From PrisonerID
        As DataClassLibrary.
        OrderofBattle In
        Game.Linqdata.Unitcol Where
        PrisonerID.Guard_ID =
                TestGuard.BasePersUnit.Unit_ID Select
        PrisonerID.OBUnit_ID
        For Each
        GetPrisoner As
        Integer In
        PrisonerUnits
        Dim Prisoner
        As ObjectClassLibrary.ASLXNA.PersUniti = (
                From selunit
        As ObjectClassLibrary.
        ASLXNA.PersUniti In
        Scencolls.Unitcol Where
        selunit.BasePersUnit.Unit_ID =
                GetPrisoner Select
        selunit).First
        If Not

        IsNothing(Prisoner) Then TestGuard.BasePersUnit.Guarding.Add(Prisoner)
        Next
                Next*/
    }
}
