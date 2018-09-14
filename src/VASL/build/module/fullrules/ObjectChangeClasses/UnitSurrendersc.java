package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;
import VASL.build.module.fullrules.UtilityClasses.CounterActions;
import VASSAL.counters.GamePiece;

import javax.swing.*;
import java.util.LinkedList;

public class UnitSurrendersc implements StatusChangei {
    private ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myAdjacentEnemy = new LinkedList<PersUniti>();
    private PersUniti myGuardUnit;
    private Constantvalues.OrderStatus myPrisionerStatus;

    public UnitSurrendersc(PersUniti GuardUnit) {  //'ByVal AdjacentEnemy As List(Of Objectvalues.PersUniti))
        myGuardUnit = GuardUnit;
        //myPrisionerStatus = PrisonerStatus;
        //'myAdjacentEnemy = AdjacentEnemy
    }
    public boolean Takeaction(PersUniti TargParent) {
        /*'Name:       TargetSurrenders()
        'Identifier UC 213
        '            Preconditions()
        '1.	MMC Target is alive and surrenders on the HOB dr OR for failure to rout or due to CC.
        '            Basic Course
        '1.	Use case begins when a Surrender result is produced [UC109-TargetHOBResult]
        '2.	Target drops SW [UC209-TargetDropsSW]
        '3.	Target changes GO and other settings
        '4  Target moves to guard hex
        '5.	Use case ends when the Target changes status to Prisoner
        'Alternate Course A:
        'Condition:
        'Inheritance:
        'Condition:
        '            Post conditions
        '1.*/

        String GuardString = "";
        // surrendering unit abandons SW
        // update SW values
        StatusChangei SWChange;
        if(TargParent.getbaseunit().getFirstSWLink() > 0) {
        //    SWChange = new UnitDropsFirstSWc();
        //    SWChange.Takeaction(TargParent);
        }
        if(TargParent.getbaseunit().getSecondSWLink() > 0) {
        //    SWChange = new UnitDropsSecondSWc();
        //    SWChange.Takeaction(TargParent);
        }
        
        // create the new unarmed unit
        // determine if prisoner is Squad or HS
        int NewPrisoner  = 0;
        if (TargParent.getbaseunit().getUnittype() == Constantvalues.Utype.Squad) {
            NewPrisoner = 136; // values from LOBTable
        } else if (TargParent.getbaseunit().getUnittype() == Constantvalues.Utype.HalfSquad) {
            NewPrisoner = 137;
        } else {  // Is some kind of SMC
            NewPrisoner = 138;
        }
        String NewName =" ";
        while (NewName =="") {
            NewName = askforNewUnit(TargParent.getbaseunit().getUnitName());
        }
        PersCreation UseObjectFactory = new PersCreation();
        PersUniti NewUnit = UseObjectFactory.CreateNewInstance(NewPrisoner, NewName, TargParent);
        // add new unit to Unitcol collection
        Scencolls.Unitcol.add(NewUnit);
        // update ID value of counter to match new unit
        setcounterID(NewUnit.getbaseunit().getUnit_ID(), TargParent);
        // update new HS with values of previous unit - Do we need all of this
        UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
        NewUnit.getTargetunit().setOrderStatus(myPrisionerStatus);  //Prisoner or unarmed as per parameter
        // set Guard
        if (myPrisionerStatus == Constantvalues.OrderStatus.Prisoner) {
            myGuardUnit.getbaseunit().AddPrisoner(NewUnit);
            myGuardUnit.getbaseunit().setRoleStatus(Constantvalues.RoleStatus.GuardUnit);
            GuardString = " guarded by " + myGuardUnit.getbaseunit().getUnitName();
        } else {
            GuardString = " free in " + myGuardUnit.getbaseunit().getHexName();
            // if enemy can' t guard, unarmed unit still moves to the hex and is then 'freed'
        }
        // now move prisoner - this is quick and dirty - DEVELOP GLIDE APPROACH AUG 14 - see MoveItemsGraphics.MoveGraphics.Moveitem
        NewUnit.getbaseunit().setHex(myGuardUnit.getbaseunit().getHex());
        NewUnit.getbaseunit().setHexname(myGuardUnit.getbaseunit().getHexName());
        NewUnit.getbaseunit().sethexlocation(myGuardUnit.getbaseunit().gethexlocation());
        NewUnit.getbaseunit().sethexPosition(myGuardUnit.getbaseunit().gethexPosition());
        NewUnit.getbaseunit().setLevelinHex(myGuardUnit.getbaseunit().getLevelinHex());
        // .hexPosition = OldUnit.BasePersUnit.hexPosition
        NewUnit.getbaseunit().setHexEntSideCrossed(0);
        NewUnit.getTargetunit().UpdateTargetStatus(NewUnit);
        if (TargParent.getTargetunit() != null) {
            // NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
            NewUnit.getTargetunit().setCombatResultsString(TargParent.getbaseunit().getUnitName() + ": " + TargParent.getTargetunit().getCombatResultsString() +
                    "  HOB: Surrenders and is now " + NewUnit.getbaseunit().getUnitName() + GuardString);
            // TargetPersUnit already created by UnitUpdateNewWithOldc
        }
        // change values for former unit
        if (TargParent.getTargetunit() != null) {
            // temporary while debugging UNDO
            /*CommonFunctions ComFunc = new CommonFunctions(TargParent.getbaseunit().getScenario());
            int FirerSan  = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);*/
        }

        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.NotInPlay);
        TargParent.getbaseunit().setCX(false);
        TargParent.getbaseunit().setPinned(false);
        TargParent.getbaseunit().setCombatStatus(Constantvalues.CombatStatus.None);
        TargParent.getbaseunit().setMovementStatus(Constantvalues.MovementStatus.NotMoving);
        //TargParent.SetTexture()
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);

        // remove old unit from moving list TOO EARLY - DO THIS LATER
        //If Not IsNothing(TargParent.MovingPersUnit) Then Scencolls.SelMoveUnits.Remove(TargParent)

        // Store values to update FireGroup and TargetGroup (maybe add movement?)
        if (NewUnit.getTargetunit() != null) {myNewTargs.add(NewUnit);}
        if (NewUnit.getFiringunit() != null) {myNewFiring.add(NewUnit);}
        // No HoB - surrender is caused by HOB or takes place in non-fire situation (Rout, CC, Mopping Up)
        return true;
    }
       /* 'Private Sub ShowChooseGuardMenu(ByVal Prisonersize As Integer)
                '    Dim TempMenuList As List(Of Objectvalues.MenuItemObjectholderinteface)
                '    Dim Objectid As Integer = 0 : Dim objid As Integer = 0
                '    Dim Showstring As String = "Guards can be: "
                '    Dim GuardFactory = New ObjectFactoryvalues.MenuEventObjectCreation
                '    If GuardFactory.CreateMenuPrep(Prisonersize, myAdjacentEnemy, Constantvalues.PersUnitResult.Surrenders, TempMenuList) Then
                '        For Each ShowGuard As Objectvalues.MenuItemObjectholderinteface In TempMenuList
                '            Showstring &= ShowGuard.ActivityNames & ", "
                '        Next
                '        MessageBox.Show(Showstring)
                '        'Game.Scenario.Moveobsi.ShowContextPopup(menuitems, HexClicked)
            '        'Game.contextshowing = True
        '    End If
                'End Sub*/
    public LinkedList<PersUniti> getNewTargs () {return myNewTargs;}
    public LinkedList<PersUniti> getNewFirings () {return myNewFiring;}
    /**
     * Displays the input dialog and returns user input
     */
    public String askforNewUnit(String Oldname) {
        JOptionPane pane = new JOptionPane();
        String newname =  pane.showInputDialog(null,
                "Enter Name of New Prisoner Unit: ",
                Oldname + " surrenders",
                JOptionPane.QUESTION_MESSAGE
        );
        return newname;
    }
    // move this out to a common function as it will be the same in all classes
    private void setcounterID(int newunitID, PersUniti FormerUnit){
        CommonFunctionsC ToDO = new CommonFunctionsC(FormerUnit.getbaseunit().getScenario());
        GamePiece CounterToUse = ToDO.GetGamePieceFromID(FormerUnit.getbaseunit().getUnit_ID());

        if (CounterToUse != null) {
            // trigger counter action
            CounterActions counteractions = new CounterActions();
            counteractions.updatecounterID(newunitID, FormerUnit);
        }
    }

}
