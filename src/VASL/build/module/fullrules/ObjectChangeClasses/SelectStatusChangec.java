package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.UtilityClasses.UnitStatusC;
import VASSAL.build.GameModule;

import java.util.LinkedList;
import java.util.Scanner;

public class SelectStatusChangec {

    //Private TempMenuList As List(Of Objectvalues.MenuItemObjectholderinteface)
    public SelectStatusChangec(){
        
    }

    /*Public Function CombatStatusChange(ByVal PassValue As Integer) As ObjectChange.ASLXNA.StatusChangei

    End Function
    Public Function SniperStatusChange(ByVal PassValue As Integer) As ObjectChange.ASLXNA.StatusChangei

    End Function
    Public ReadOnly Property PopUpItems As List(Of Objectvalues.MenuItemObjectholderinteface)
    Get
    return TempMenuList
    End Get
    End Property*/
    public StatusChangei HoBStatusChange(Constantvalues.PersUnitResult HoBChange, PersUniti TargetUnit) {
        
        switch (HoBChange) {
            case Hardens:
                return new UnitHardensc();
            case HeroCreation:
                return new UnitHeroCreationc();
            case HeroicLdrCreation:
                return new UnitHeroicLdrCreationc();
            case HeroHardens:
                return  new UnitHeroHardensc();
            case HeroicLdrHardens:
                //'returnNew ObjectChange.ASLXNA.UnitHero
            case Berserks:
                return new UnitBerserksc();
            case Surrenders:
                /*UnitStatusC StatusCheck = new UnitStatusC();
                EnemyChecksC TestForEnemy = new EnemyChecksC(TargetUnit.getbaseunit().getLOCIndex(), TargetUnit.getbaseunit().getNationality(), TargetUnit.getbaseunit().getScenario());
                boolean VersusRussians = TestForEnemy.VersusRussians();
                LinkedList<PersUniti> AdjacentEnemy  = TestForEnemy.EnemyUnitsInAdjacentHexes(TargetUnit.getbaseunit().gethexPosition());
                DataC Linqdata = DataC.GetInstance();
                Scenario Scendet = Linqdata.GetScenarioData(TargetUnit.getbaseunit().getScenario());
                // No Quarter, Japanese, Gurkhas, Partisans, Fanatics, and Commissars, SS vs Russians never surrender due to HOB - go berserk instead
                if (Scendet.getNoQuarter() || StatusCheck.IsGurkha(TargetUnit) || StatusCheck.IsJapanese(TargetUnit) || StatusCheck.IsPartisan(TargetUnit) || StatusCheck.IsCommissar(TargetUnit) ||
                        (StatusCheck.IsSS(TargetUnit) && VersusRussians) || StatusCheck.IsFanatic(TargetUnit)) {
                    return new UnitBerserksc();
                } else {
                    if (AdjacentEnemy.size() == 0) {
                        // no adjacent enemy so disrupts
                        return new UnitDisruptDMsc();
                    } else {
                        // since adjacent enemy exists will surrender - must be Known Good Order to guard
                        boolean GuardPossible = false;
                        for (PersUniti AdjEnemy : AdjacentEnemy) {
                            if (AdjEnemy.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible && AdjEnemy.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.GoodOrder) {
                                GuardPossible = true;
                                break;
                            }
                        }
                        // disrupts because no known, good order enemy ajacent
                        if (!GuardPossible) {
                            return new UnitDisruptDMsc();
                        }
                        // check if captor wishes to invoke No Quarter
                        GameModule.getGameModule().getChatter().send("Do you wish to use eliminate surrendering Unit (Y/N)? A20.3 Invoke No Quarter");
                        Scanner response = new Scanner(System.in);
                        if (response.toString() == "Y") {
                            Scendet.setNoQuarter(true);
                            Linqdata.WriteScenarioData(Scendet);
                            TargetUnit.getTargetunit().setCombatResultsString(TargetUnit.getTargetunit().getCombatResultsString() + "Surrender rejected: ");
                            return new UnitDiesC();
                        } else {
                            if (AdjacentEnemy.size() == 1) {
                                if (AdjacentEnemy.get(0).getbaseunit().CanGuard(TargetUnit.getbaseunit().getUnittype())) {
                                    return new UnitSurrendersc(AdjacentEnemy.get(0), Constantvalues.OrderStatus.Prisoner);
                                } else {
                                    // pass PrisonerStatus as Unarmed to signify that unit surrenders but is freed
                                    return new UnitSurrendersc(AdjacentEnemy.get(0), Constantvalues.OrderStatus.Unarmed));
                                }
                            } else {
                                // ShowChooseGuardMenu(TargetUnit, AdjacentEnemy)
                                return null;
                            }
                        }
                    }
                }*/
            default:
                return null;
        }
    }
    /*Private Sub ShowChooseGuardMenu(ByVal Prisoner As Objectvalues.PersUniti, ByVal AdjacentEnemy As List(Of Objectvalues.PersUniti))

    Dim Objectid As Integer = 0 : Dim objid As Integer = 0
    Dim Showstring As String = "Guards can be: "
    Dim GuardFactory = New ObjectFactoryvalues.MenuEventObjectCreation
    If GuardFactory.CreateMenuPrep(Prisoner, AdjacentEnemy, Constantvalues.PersUnitResult.Surrenders, TempMenuList) Then
    For Each ShowGuard As Objectvalues.MenuItemObjectholderinteface In TempMenuList
    Showstring &= ShowGuard.ActivityNames & ", "
    Next
                MessageBox.Show(Showstring)

            'Game.Scenario.Moveobsi.ShowContextPopup(menuitems, HexClicked)
            'Game.contextshowing = True
    End If
    End Sub*/

}
