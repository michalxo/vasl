package VASL.build.module.fullrules.ObjectFactoryClasses;

/*The Object Factory class library holds code to implement a number of object factories which handle the creation of objects used by the program
These objects include personnel, SW and Vehicular objectst that appear on the GUI but also Menu Item objects and other objects used by the code
All objects used by the code should be created by these factories
The objects themselves should be defined in the Object class library

AT PRESENT, THE POPUP MENU FACTORY IS A BETTER IMPLEMENTATION THAN THE PERSONNEL FACTORY - WHICH COULD PROBABLY BENEFIT FROM AN ADDITIONAL FACTORY LAYER DIVIDED BY NATIONALITY JUST AS
THE MENU FACTORY HAS A LAYER OF FACTORIES DIVIDED BY PHASE*/

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.ObjectClasses.German467c;
import VASL.build.module.fullrules.ObjectClasses.German838c;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class PersCreation {
    // holds code that creates persuniti objects and related property classes within Objectclasslibrary.aslxna.Persuniti
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private DataC Linqdata  = DataC.GetInstance();  // use empty variables when know that instance already exists
    // Personnel unit instances
    public PersUniti CreateExistingInstance(OrderofBattle unititem) {  //, Concealment conitem) {  // creates instance of existing unit from database
        // called by ASLXNA.Actions.UnitActionsC and PersCreation.CreateNewInstance which create new persuniti objects at startup or when new unit created during play
        // plus called by PersCreation.createtargetunitproperty when decorating leader (CAN WE AVOID THIS? July 2014)

        // takes information from database objects OrderofBattle/Concealment plus additional values and creates object of type persuniti which is returned to calling method
        // additional values are: PassClass which holds value from Enum UClass stored in LineOfBattle database table as Class; and PassUtype which hold value from Enum Utype (Squad, HS, SMC, etc) stored in LineofBattle database table as UnitType

        Constantvalues.Utype PassUtype = Constantvalues.Utype.None;
        Constantvalues.UClass PassClass = Constantvalues.UClass.NONE;
        PersUniti NewLeader;   // used in SMC creation process
        int UseLOBLink;
        // set variables
        if (unititem != null) {   // item is infantry
            UseLOBLink = (int) (unititem.getLOBLink());
            // test values
            PassUtype = Constantvalues.Utype.Squad;  // (java.lang.Integer.parseInt(Linqdata.GetLOBData(Constantvalues.LOBItem.UNITTYPE, UseLOBLink)));
            PassClass = Constantvalues.UClass.FIRSTLINE; //(java.lang.Integer.parseInt(Linqdata.GetLOBData(Constantvalues.LOBItem.UNITCLASS, UseLOBLink)));
        } else { // item is concealment
            UseLOBLink = 0; // conitem.getNationality() + 2000;
        }
        switch (UseLOBLink) {
            case 1:
            case 2:
                return new German838c(unititem.getHexname(), unititem.getScenario(), (int) unititem.gethexnum(), unititem.gethexlocation(), unititem.getPosition(), unititem.getLevelinHex(), unititem.getLocIndex(), unititem.getCX(), (int)unititem.getELR(),
                (int)unititem.getTurnArrives(), unititem.getNationality(), unititem.getCon_ID(), unititem.getOBUnit_ID(), Constantvalues.Typetype.Personnel, (int)unititem.getFirstSWLink(), (int)unititem.getSecondSWlink(), unititem.getHexEnteredSideCrossedLastMove(), 0, unititem.getOBName(), 2, unititem.getCombatStatus(),
                unititem.getVisibilityStatus(), unititem.getFortitudeStatus(), unititem.getOrderStatus(), unititem.getMovementStatus(), unititem.getPinned(), (int)unititem.getSW(), PassClass, unititem.getCharacterStatus(), PassUtype, unititem.getRoleStatus());

            /*case 4:
            return New
            ObjectClassLibrary.ASLXNA.German548c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 4, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))*/
            case 5:
                return new German467c(unititem.getHexname(), unititem.getScenario(), (int) unititem.gethexnum(), unititem.gethexlocation(), unititem.getPosition(), unititem.getLevelinHex(), unititem.getLocIndex(), unititem.getCX(), (int)unititem.getELR(),
                (int)unititem.getTurnArrives(), unititem.getNationality(), unititem.getCon_ID(), unititem.getOBUnit_ID(), Constantvalues.Typetype.Personnel, (int)unititem.getFirstSWLink(), (int)unititem.getSecondSWlink(), unititem.getHexEnteredSideCrossedLastMove(), 0, unititem.getOBName(), 2, unititem.getCombatStatus(),
                unititem.getVisibilityStatus(), unititem.getFortitudeStatus(), unititem.getOrderStatus(), unititem.getMovementStatus(), unititem.getPinned(), (int)unititem.getSW(), PassClass, unititem.getCharacterStatus(), PassUtype, unititem.getRoleStatus());

            /*case 9
            return New
            ObjectClassLibrary.ASLXNA.German338c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 9, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 11:
            return New
            ObjectClassLibrary.ASLXNA.German238c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 11, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 12:
            return New
            ObjectClassLibrary.ASLXNA.German247c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 12, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 13:
            return New
            ObjectClassLibrary.ASLXNA.German237c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 13, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 25:
            return New
            ObjectClassLibrary.ASLXNA.Russian628c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 25, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 26:
            return New
            ObjectClassLibrary.ASLXNA.Russian458c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 26, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 27
            return New
            ObjectClassLibrary.ASLXNA.Russian447c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 27, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 28
            return New
            ObjectClassLibrary.ASLXNA.Russian527c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 28, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 29
            return New
            ObjectClassLibrary.ASLXNA.Russian426c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 29, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 31:
            return New
            ObjectClassLibrary.ASLXNA.Russian248c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 31, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 32:
            return New
            ObjectClassLibrary.ASLXNA.Russian237c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 32, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 33
            return New
            ObjectClassLibrary.ASLXNA.Russian227c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 33, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 34:
            return New
            ObjectClassLibrary.ASLXNA.Russian226c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 34, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 136:
            return New
            ObjectClassLibrary.ASLXNA.PrisonerSquadc(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 136, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 137:
            return New
            ObjectClassLibrary.ASLXNA.PrisonerHSquadc(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 137, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 138:
            return New
            ObjectClassLibrary.ASLXNA.PrisonerSMCc(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 138, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 202:
            return New
            ObjectClassLibrary.ASLXNA.RussianDummyc(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 202, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case 203:
            return New
            ObjectClassLibrary.ASLXNA.GermanDummyc(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 203, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            'where case is above 1000, creating SMC and must differentiate again by Nationality - better structure?
            case 1004:
            switch (unititem.Nationality) {
                case ConstantClassLibrary.ASLXNA.Nationality.Germans
                        NewLeader = New
                ObjectClassLibrary.ASLXNA.German8_1c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                        CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 1004, CInt(unititem.CombatStatus),
                        CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
                case ConstantClassLibrary.ASLXNA.Nationality.Russians
                        NewLeader = New
                ObjectClassLibrary.ASLXNA.Russian8_1c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                        CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 1004, CInt(unititem.CombatStatus),
                        CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            }
            If Not IsNothing(NewLeader) Then return NewLeader Else return Nothing
            case 1101:
            Select case unititem.Nationality
            case ConstantClassLibrary.ASLXNA.Nationality.Russians:
            return New
            ObjectClassLibrary.ASLXNA.Russian138c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 1101, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            End Select
            case 1102:
            Select case unititem.Nationality
            case ConstantClassLibrary.ASLXNA.Nationality.Russians:
            return New
            ObjectClassLibrary.ASLXNA.Russian149c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 1102, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            End Select
            case 1124:
            Select case unititem.Nationality
            case ConstantClassLibrary.ASLXNA.Nationality.Germans:
                    NewLeader = New
            ObjectClassLibrary.ASLXNA.German8_1c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 1004, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            case ConstantClassLibrary.ASLXNA.Nationality.Russians:
                    NewLeader = New
            ObjectClassLibrary.ASLXNA.Russian8_1c(unititem.Hexname, CInt(unititem.Scenario), CInt(unititem.hexnum), CInt(unititem.hexlocation), CInt(unititem.Position), CSng(unititem.LevelinHex), CInt(unititem.LocIndex), unititem.CX, CInt(unititem.ELR),
                    CInt(unititem.TurnArrives), CInt(unititem.Nationality), unititem.Con_ID, unititem.OBUnit_ID, ConstantClassLibrary.ASLXNA.Typetype.Personnel, CInt(unititem.FirstSWLink), CInt(unititem.SecondSWlink), CInt(unititem.HexEnteredSideCrossedLastMove), 0, unititem.OBName, 1004, CInt(unititem.CombatStatus),
                    CInt(unititem.VisibilityStatus), CInt(unititem.FortitudeStatus), CInt(unititem.OrderStatus), CInt(unititem.MovementStatus), unititem.Pinned, CInt(unititem.SW), PassClass, CInt(unititem.CharacterStatus), PassUtype, CInt(unititem.RoleStatus))
            End Select
            If IsNothing (NewLeader) Then return Nothing
            'now must decorate it
            Dim DecNewLeader As ObjectClassLibrary.ASLXNA.PersunitDecoratori
                    DecNewLeader = New ObjectClassLibrary.ASLXNA.PersunitHeroicldrc(NewLeader)
            If Not IsNothing(NewLeader.BasePersUnit) Then DecNewLeader.BasePersUnit = New
            ObjectClassLibrary.ASLXNA.BaseHeroicLdrc(NewLeader.BasePersUnit)
            return DecNewLeader
            case 4002:
            return New
            ObjectClassLibrary.ASLXNA.RussianConc(conitem.Hexname, conitem.Scenario, conitem.hexnum, conitem.Hexlocation, conitem.Position, CSng(conitem.LevelinHex), conitem.LocIndex, CBool(conitem.CX), 0, 0, ConstantClassLibrary.ASLXNA.Nationality.Russians,
                    0, CInt(conitem.Con_ID), ConstantClassLibrary.ASLXNA.Typetype.Concealment, 0, 0, CInt(conitem.HexEnteredSideCrossedLastMove), 0, conitem.Concounter, ConstantClassLibrary.ASLXNA.Nationality.Russians + 2000, 0, ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed, conitem.FortitudeStatus, 0, CInt(conitem.MovementStatus), False, 0, PassClass, 0, 0, 0)
            case 4003:
            return New
            ObjectClassLibrary.ASLXNA.GermanConc(conitem.Hexname, conitem.Scenario, conitem.hexnum, conitem.Hexlocation, conitem.Position, CSng(conitem.LevelinHex), conitem.LocIndex, CBool(conitem.CX), 0, 0, ConstantClassLibrary.ASLXNA.Nationality.Germans,
                    0, CInt(conitem.Con_ID), ConstantClassLibrary.ASLXNA.Typetype.Concealment, 0, 0, CInt(conitem.HexEnteredSideCrossedLastMove), 0, conitem.Concounter, ConstantClassLibrary.ASLXNA.Nationality.Germans + 2000, 0, ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed, conitem.FortitudeStatus, 0, CInt(conitem.MovementStatus), False, 0, PassClass, 0, 0, 0)*/
        }
        return null;
    }
    /*public Function CreateNewInstance(ByVal TypeToCreate As Integer, ByVal NewName As String, ByVal BirthingUnit As ObjectClassLibrary.ASLXNA.PersUniti) As ObjectClassLibrary.ASLXNA.PersUniti
            'called in ObjectChange.Objectchange classes when creating a new unit during game play (due to combat results such as Casualty Reduction and ELR Replacment or HOB results such as Battle Hardening)

                    'creates new instance of a unit type, adds it to the database and then calls CreateExistingInstance to create persuniti object which is returned to calling method
    Dim DecNewLeader As ObjectClassLibrary.ASLXNA.PersunitDecoratori    'used if need to decorate the new persuniti
            'Need to create database object for the new unit
    Dim PassCharacterStatus As Integer = BirthingUnit.BasePersUnit.CharacterStatus
    Dim PassCombatStatus As Integer = BirthingUnit.BasePersUnit.CombatStatus
    Dim PassConID As Integer = BirthingUnit.BasePersUnit.Con_ID
    Dim PassCX As Boolean = False
    Dim PassELR As Integer = 0
    Dim PassFirstSWLink As Integer = 0
    Dim PassFortitudeStatus As Integer = 0
    Dim PassHexEnteredSideCrossedLastMove As Integer = 0
    Dim PassHexlocation As Integer = 0
    Dim Passhexname As String = ""
    Dim Passhexnum As Integer = 0
    Dim PassLevelInHex As Integer = 0
    Dim PassLobLink As Integer = TypeToCreate
    Dim PassLocIndex As Integer = 0
    Dim PassMovementStatus As Integer = 0
    Dim Passnationality As Integer = BirthingUnit.BasePersUnit.Nationality
    Dim PassOBname As String = Trim(NewName)
    Dim PassOrderStatus As Integer = BirthingUnit.BasePersUnit.OrderStatus
    Dim Passpinned As Boolean = False
    Dim Passhexposition As Integer = 0
    Dim PassRoleStatus As Integer = 0
    Dim PassScenario As Integer = BirthingUnit.BasePersUnit.Scenario
    Dim passsecondswlink As Integer = 0
    Dim PassSW As Integer = 0
    Dim PassTurnArrives As Integer = BirthingUnit.BasePersUnit.TurnArrives
    Dim PassVisibilityStatus As Integer = 0
    Dim NewUnit As ObjectClassLibrary.ASLXNA.PersUniti
    If TypeToCreate < 4000 Then 'is not new concealment
            'pass values for new OrderofBattle object to Linqdata to create object and add to database table and update Linqdata.Unitcol
    Dim NewDBobj As DataClassLibrary.OrderofBattle = Linqdata.CreateNewUnitinDB(PassCharacterStatus, PassCombatStatus, PassConID, PassCX, PassELR, PassFirstSWLink, PassFortitudeStatus,
    PassHexEnteredSideCrossedLastMove, PassHexlocation, Passhexname, Passhexnum, PassLevelInHex, PassLobLink,
    PassLocIndex, PassMovementStatus, Passnationality, PassOBname, PassOrderStatus, Passpinned, Passhexposition,
    PassRoleStatus, PassScenario, passsecondswlink, PassSW, PassTurnArrives, PassVisibilityStatus)
            'now create new persuniti object
    NewUnit = CreateExistingInstance(NewDBobj)
    Else
                'pass values for new OrderofBattle object to Linqdata to create object and add to database table and update Linqdata.Unitcol
    Dim NewDBobj As DataClassLibrary.Concealment = Linqdata.CreateNewConinDB(PassCX, PassFortitudeStatus,
    PassHexEnteredSideCrossedLastMove, PassHexlocation, Passhexname, Passhexnum, PassLevelInHex,
    PassLocIndex, PassMovementStatus, Passnationality, PassOBname, Passhexposition,
    PassScenario)
            'now create new persuniti object
    NewUnit = CreateExistingInstance(, NewDBobj)
    End If

            'check if need to decorate
    If IsHeroic(NewUnit.BasePersUnit.FortitudeStatus) Then
                'need to decorate leader
    DecNewLeader = New ObjectClassLibrary.ASLXNA.PersunitHeroicldrc(NewUnit)
    If Not IsNothing(NewUnit.BasePersUnit) Then DecNewLeader.BasePersUnit = New ObjectClassLibrary.ASLXNA.BaseHeroicLdrc(NewUnit.BasePersUnit)
    NewUnit = DecNewLeader
    End If
    return NewUnit

    End Function
    public Function IsHeroic(ByVal FortitudeStatus As Integer) As Boolean
            'called by CreateExistingInstance
                    'determines if leader is heroic and therefore persuniti needs to be decorated
    If FortitudeStatus >= ConstantClassLibrary.ASLXNA.FortitudeStatus.Heroic AndAlso FortitudeStatus <= ConstantClassLibrary.ASLXNA.FortitudeStatus.HeroicEnc_Wnd Then return True Else return False
    End Function*/

    /*    'Moving Unit property
    public Function CreateMovingUnit(ByVal ObjectTypeId As Integer, ByVal ObjectID As Integer, ByRef TempMovementStack As List(Of ObjectClassLibrary.ASLXNA.PersUniti)) As Boolean
            'called by movementc.AddToStackAttempt and movementc.RedoMovementStack
    Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
            'creates a new persuniti movement property class in a persuniti object and updates revised stack ready for decoration which is available to calling method through ByRef parameter
    Dim MoveUnit As ObjectClassLibrary.ASLXNA.PersUniti
            'locate existing persuniti and add a movement class property to it
    If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjectTypeId) Then  'item is infantry
    Dim PassUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(ObjectID)
    MoveUnit = (From SelTest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where SelTest.BasePersUnit.Unit_ID = ObjectID AndAlso SelTest.BasePersUnit.LOBLink = ObjectTypeId Select SelTest).First
                'set Moving Unit property of persuniti
    MoveUnit.MovingPersUnit = createmovingunitproperty(MoveUnit, PassUnit, Nothing)
    Else
    Dim PassUnit As DataClassLibrary.Concealment = Linqdata.GetConcealmentfromCol(ObjectID)
    MoveUnit = (From SelTest As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where SelTest.BasePersUnit.Unit_ID = ObjectID AndAlso SelTest.BasePersUnit.LOBLink = ObjectTypeId Select SelTest).First
                'set Moving Unit property of persuniti
    MoveUnit.MovingPersUnit = createmovingunitproperty(MoveUnit, Nothing, PassUnit)
    End If
            'updated list available to calling method as passed ByRef
                    TempMovementStack.Add(MoveUnit)
    return True
    End Function
    public Function createmovingunitproperty(ByVal MovingUnit As ObjectClassLibrary.ASLXNA.PersUniti, Optional ByVal PassPers As DataClassLibrary.OrderofBattle = Nothing, Optional ByVal PassCon As DataClassLibrary.Concealment = Nothing) As ObjectClassLibrary.ASLXNA.MovingPersuniti
            'called by PersCreation.CreateMovingUnit
                    'also called by ASLXNA.Movement.EnterNewHex.MoveUpdate - add to all movement classes

                    'creates and returns a moving unit class to be added as property of a peruniti object
                    'set variables to pass
    Dim PassOBname As String : Dim PassOBUnitID As Integer : Dim PassConID As Integer : Dim Passhexnum As Integer : Dim Passhexlocation As Integer
    Dim PassPosition As Integer : Dim PassLocIndex As Integer : Dim PassVisibilityStatus As Integer : Dim PasshexEnteredSideCrossedLastMove As Integer
    Dim PassNationality As Integer
    If Not IsNothing(PassPers) Then 'passing OrderofBattle unit
    Passhexnum = CInt(PassPers.hexnum)
    Passhexlocation = CInt(PassPers.hexlocation)
    PassPosition = CInt(PassPers.Position)
    PassLocIndex = CInt(PassPers.LocIndex)
    PassVisibilityStatus = CInt(PassPers.VisibilityStatus)
    PasshexEnteredSideCrossedLastMove = CInt(PassPers.HexEnteredSideCrossedLastMove)
    PassOBname = PassPers.OBName
            PassOBUnitID = PassPers.OBUnit_ID
    PassConID = PassPers.Con_ID
            PassNationality = CInt(PassPers.Nationality)
    Else  'passing Concealment unit
    Passhexnum = CInt(PassCon.hexnum)
    Passhexlocation = CInt(PassCon.Hexlocation)
    PassPosition = CInt(PassCon.Position)
    PassLocIndex = CInt(PassCon.LocIndex)
    PassVisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed
            PasshexEnteredSideCrossedLastMove = CInt(PassCon.HexEnteredSideCrossedLastMove)
    PassOBname = PassCon.Concounter
            PassOBUnitID = CInt(PassCon.Con_ID)
    PassConID = 0
    PassNationality = PassCon.Nationality
    End If
            'select item to create
    Select case MovingUnit.BasePersUnit.LOBLink
    case 1
    case 2
    return New ObjectClassLibrary.ASLXNA.German838Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 4
    return New ObjectClassLibrary.ASLXNA.German548Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 5
    return New ObjectClassLibrary.ASLXNA.German467Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 9
    return New ObjectClassLibrary.ASLXNA.German338Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 11
    return New ObjectClassLibrary.ASLXNA.German238Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 12
    return New ObjectClassLibrary.ASLXNA.German247Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 13
    return New ObjectClassLibrary.ASLXNA.German237Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 25
    return New ObjectClassLibrary.ASLXNA.Russian628Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 26
    return New ObjectClassLibrary.ASLXNA.Russian458Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 27
    return New ObjectClassLibrary.ASLXNA.Russian447Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 28
    return New ObjectClassLibrary.ASLXNA.Russian527Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 31
    return New ObjectClassLibrary.ASLXNA.Russian248Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 32
    return New ObjectClassLibrary.ASLXNA.Russian237Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 33
    return New ObjectClassLibrary.ASLXNA.Russian227Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 34
    return New ObjectClassLibrary.ASLXNA.Russian226Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 136
    return New ObjectClassLibrary.ASLXNA.PrisonerSquadMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 137
    return New ObjectClassLibrary.ASLXNA.PrisonerHalfSquadMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 138
    return New ObjectClassLibrary.ASLXNA.PrisonerSMCMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 202
    return New ObjectClassLibrary.ASLXNA.RussianDummyMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 203
    return New ObjectClassLibrary.ASLXNA.GermanDummyMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
            'where case is above 1000, creating SMC and must differentiate again by Nationality - better structure?
    case 1004
    Select case PassNationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
    return New ObjectClassLibrary.ASLXNA.German8_1Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian8_1Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    End Select
    case 1101
    Select case PassNationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
                            'return New ObjectClassLibrary.ASLXNA.German138Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian138Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    End Select
    case 1102
    Select case PassNationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
                            'return New ObjectClassLibrary.ASLXNA.German149Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian149Movec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    End Select
    case 4002
    return New ObjectClassLibrary.ASLXNA.RussianConMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    case 4003
    return New ObjectClassLibrary.ASLXNA.GermanConMovec(PassOBname, Passhexnum, Passhexlocation, PassPosition, PassLocIndex, PassOBUnitID, PassVisibilityStatus, PassConID, PasshexEnteredSideCrossedLastMove)
    End Select
    End Function


                'Firing Unit property
    public Function CreatefiringUnitandProperty(ByVal Unititem As ObjectClassLibrary.ASLXNA.PersUniti) As ObjectClassLibrary.ASLXNA.PersUniti
            'called by IFTC.AddtoTempFireGroup, DFFMVCP.EnemyValuesConcreteC.AddToTempFG, ObjectChange.UnitUpdateNewOldc.New

                    'creates a new persuniti firing property class in a persuniti object and returns updated persuniti to calling method
    If ConstantClassLibrary.ASLXNA.Typetype.Personnel = Unititem.BasePersUnit.TypeType_ID Then  'item is infantry
    Dim PassUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(Unititem.BasePersUnit.Unit_ID)
            'set Firing Unit property of persuniti object
    Unititem.FiringPersUnit = createfiringunitproperty(PassUnit, Unititem)
    End If
    return Unititem
    End Function
    private Function createfiringunitproperty(ByVal passunit As DataClassLibrary.OrderofBattle, ByVal FiringUnit As ObjectClassLibrary.ASLXNA.PersUniti) As ObjectClassLibrary.ASLXNA.FiringPersUniti
            'called by PersCreation.CreateFiringUnitandProperty
    Dim PassIsCX As Boolean = passunit.CX
    Dim PassIsPinned As Boolean = passunit.Pinned
    Dim PassHasMG As Boolean = passunit.UnitHasMG
    Dim PassUsingInherentFP As Boolean = False
    Dim PassUsingfirstMG As Boolean = False
    Dim PassUsingsecondMG As Boolean = False
    Select case FiringUnit.BasePersUnit.LOBLink
    case 1
    case 2
    return New ObjectClassLibrary.ASLXNA.German838Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 4
    return New ObjectClassLibrary.ASLXNA.German548Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 5
    return New ObjectClassLibrary.ASLXNA.German467Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 9
    return New ObjectClassLibrary.ASLXNA.German338Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 11
    return New ObjectClassLibrary.ASLXNA.German238Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 12
    return New ObjectClassLibrary.ASLXNA.German247Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 13
    return New ObjectClassLibrary.ASLXNA.German237Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 25
    return New ObjectClassLibrary.ASLXNA.Russian628Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 26
    return New ObjectClassLibrary.ASLXNA.Russian458Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 27
    return New ObjectClassLibrary.ASLXNA.Russian447Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 28
    return New ObjectClassLibrary.ASLXNA.Russian527Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 29
    return New ObjectClassLibrary.ASLXNA.Russian426Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 31
    return New ObjectClassLibrary.ASLXNA.Russian248Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 32
    return New ObjectClassLibrary.ASLXNA.Russian237Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 33
    return New ObjectClassLibrary.ASLXNA.Russian227Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case 34
    return New ObjectClassLibrary.ASLXNA.Russian226Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
            'where case is above 1000, creating SMC and must differentiate again by Nationality - better structure?
    case 1004
    Select case FiringUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
    return New ObjectClassLibrary.ASLXNA.German8_1Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian8_1Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    End Select
    case 1101
    Select case FiringUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
                            'return New ObjectClassLibrary.ASLXNA.German8_1Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian138Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    End Select
    case 1102
    Select case FiringUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
                            'return New ObjectClassLibrary.ASLXNA.German8_1Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian149Firec(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, FiringUnit)
    End Select
    case 1124
    Select case FiringUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans

    case ConstantClassLibrary.ASLXNA.Nationality.Russians
                            'create FiringUnit property for base unit then decorate it
                                    'get base unit
    Dim BaseOBUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(FiringUnit.BasePersUnit.Unit_ID)
    Dim PassClass As Integer = CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitClass, CInt(BaseOBUnit.LOBLink)))
    Dim BaseUnit As ObjectClassLibrary.ASLXNA.PersUniti = CreateExistingInstance(BaseOBUnit)
    BaseUnit.BasePersUnit.SolID = FiringUnit.BasePersUnit.SolID
    BaseUnit.FiringPersUnit = createfiringunitproperty(BaseOBUnit, BaseUnit)
    return New ObjectClassLibrary.ASLXNA.FiringHeroicLdrc(PassIsCX, PassIsPinned, PassHasMG, PassUsingInherentFP, PassUsingfirstMG, PassUsingsecondMG, BaseUnit)
    End Select
    End Select
    End Function

        'Target Unit property
    public Function CreateTargetUnitandProperty(ByVal Unititem As ObjectClassLibrary.ASLXNA.PersUniti, ByVal firersan As Integer) As ObjectClassLibrary.ASLXNA.PersUniti
            'called by IFTC.AddToTempTarget, IFTMVC.EnemyValuesConcreteC.AddToTempTargGroup, ObjectChange.UnitRevealConUnitbySniperc.RevealUnit, ObjectChange.UnitUpdateNewOldc.new, Sniper.SniperC.AttackResolution

                    'adds target unit property to selected persuniti and returns updated object
                    'If ConstantClassLibrary.ASLXNA.Typetype.Personnel = Unititem.BasePersUnit.TypeType_ID Then  'item is infantry
            'set Target Unit property
    Unititem.TargetPersUnit = createtargetunitproperty(Unititem, firersan)
            'Else
                    ''set Target Unit property
            'Unititem.TargetPersUnit = createtargetconproperty(Unititem, firersan)
                    'End If
    return Unititem
    End Function
    public Function createtargetunitproperty(ByVal TargetUnit As ObjectClassLibrary.ASLXNA.PersUniti, ByVal PassFirerSan As Integer) As ObjectClassLibrary.ASLXNA.TargetPersUniti
            'called by PersCreation.CreateTargetUnitandProperty

                    'creates a Target Unit class and returns it to be added as a property to an existing Persuniti instance
                    'set variables with default values
    Dim PassIFTResult As Integer = 0
    Dim TargStackLdrdrm As Integer = 0
    Dim PassAttackedbydrm As Integer = 0
    Dim PassAttackedbyFP As Integer = 0
    Dim PassELR5 As Boolean = False
    If TargetUnit.BasePersUnit.LOBLink < 100 Then
    Dim LOBRecord As DataClassLibrary.LineofBattle = Linqdata.GetLOBRecord(TargetUnit.BasePersUnit.LOBLink)
    PassELR5 = LOBRecord.ELR5
    End If
    Dim PassIsConceal As Boolean = False
    If TargetUnit.BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed Then PassIsConceal = True
    Dim PassIsDummy As Boolean = False
    Dim PassPinned As Boolean = TargetUnit.BasePersUnit.Pinned
    Dim PassQualityStatus As Integer = 0  'WHAT IS THIS MEANT TO BE?
    Dim PassRandomSelected As Integer = 0
    Dim PassSmoke As Integer = 0
    Dim PassConUnits = New List(Of Integer)
    Dim ConUnits As IEnumerable(Of ObjectClassLibrary.ASLXNA.PersUniti) = From selunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selunit.BasePersUnit.Con_ID = TargetUnit.BasePersUnit.Unit_ID Select selunit
    If Not IsNothing(ConUnits) Then
    For Each ConUnit As ObjectClassLibrary.ASLXNA.PersUniti In ConUnits
                    PassConUnits.Add(ConUnit.BasePersUnit.Unit_ID)
    Next
    End If
    If TargetUnit.BasePersUnit.LOBLink < 100 Then PassSmoke = CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.Smoke, TargetUnit.BasePersUnit.LOBLink))
            'now select which unit to create
    Select case TargetUnit.BasePersUnit.LOBLink
    case 1
    case 2
    return New ObjectClassLibrary.ASLXNA.German838Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 4
    return New ObjectClassLibrary.ASLXNA.German548Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 5
    return New ObjectClassLibrary.ASLXNA.German467Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 9
    return New ObjectClassLibrary.ASLXNA.German338Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 11
    return New ObjectClassLibrary.ASLXNA.German238Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 12
    return New ObjectClassLibrary.ASLXNA.German247Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 13
    return New ObjectClassLibrary.ASLXNA.German237Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 25
    return New ObjectClassLibrary.ASLXNA.Russian628Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 26
    return New ObjectClassLibrary.ASLXNA.Russian458Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 27
    return New ObjectClassLibrary.ASLXNA.Russian447Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 28
    return New ObjectClassLibrary.ASLXNA.Russian527Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 29
    return New ObjectClassLibrary.ASLXNA.Russian426Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 31
    return New ObjectClassLibrary.ASLXNA.Russian248Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 32
    return New ObjectClassLibrary.ASLXNA.Russian237Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 33
    return New ObjectClassLibrary.ASLXNA.Russian227Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 34
    return New ObjectClassLibrary.ASLXNA.Russian226Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 136
    return New ObjectClassLibrary.ASLXNA.PrisonerSquadTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 137
    return New ObjectClassLibrary.ASLXNA.PrisonerHalfSquadTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 138
    return New ObjectClassLibrary.ASLXNA.PrisonerSMCTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 202
    return New ObjectClassLibrary.ASLXNA.RussianDummyTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, True, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case 203
    return New ObjectClassLibrary.ASLXNA.GermanDummyTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, True, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
            'where case is above 1000, creating SMC and must differentiate again by Nationality - better structure?
    case 1004
    Select case TargetUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
    return New ObjectClassLibrary.ASLXNA.German8_1Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian8_1Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    End Select
    case 1101
    Select case TargetUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
                            'return New ObjectClassLibrary.ASLXNA.German138Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
                                    'PassSmoke, TargetUnit)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian138Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    End Select
    case 1102
    Select case TargetUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans
                            'return New ObjectClassLibrary.ASLXNA.German149Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
                                    'PassSmoke, TargetUnit)
    case ConstantClassLibrary.ASLXNA.Nationality.Russians
    return New ObjectClassLibrary.ASLXNA.Russian149Targc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit)
    End Select
    case 1124
    Select case TargetUnit.BasePersUnit.Nationality
    case ConstantClassLibrary.ASLXNA.Nationality.Germans

    case ConstantClassLibrary.ASLXNA.Nationality.Russians
                            'create TargetUnit property for base unit then decorate it
                                    'get base unit
    Dim BaseOBUnit As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(TargetUnit.BasePersUnit.Unit_ID)
    Dim PassClass As Integer = CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.UnitClass, CInt(BaseOBUnit.LOBLink)))
    Dim BaseUnit As ObjectClassLibrary.ASLXNA.PersUniti = CreateExistingInstance(BaseOBUnit)
    BaseUnit.TargetPersUnit = createtargetunitproperty(BaseUnit, PassFirerSan)
    return New ObjectClassLibrary.ASLXNA.TargetHeroicLdrc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, PassIsConceal, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, BaseUnit)
    End Select
    case 4002
    return New ObjectClassLibrary.ASLXNA.RussianConTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, True, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit, PassConUnits)
    case 4003
    return New ObjectClassLibrary.ASLXNA.GermanConTargc(PassIFTResult, TargStackLdrdrm, PassFirerSan, PassAttackedbydrm, PassAttackedbyFP, PassELR5, True, PassIsDummy, PassPinned, PassQualityStatus, PassRandomSelected,
    PassSmoke, TargetUnit, PassConUnits)
    End Select
    End Function*/



}
