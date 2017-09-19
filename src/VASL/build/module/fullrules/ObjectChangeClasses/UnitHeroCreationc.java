package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASSAL.build.GameModule;

import java.util.LinkedList;
import java.util.Scanner;

public class UnitHeroCreationc implements StatusChangei {

    private ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();

    public UnitHeroCreationc() {}

    public boolean Takeaction(PersUniti TargParent) {
        /*Name:       TargetHardens()

        Identifier UC 211

                    Preconditions()
        1.	MMC Target is alive and has hardened on the HOB dr

                    Basic Course
        1.	Use case begins when a Harden result is produced [UC109-TargetHOBResult]
        2.	Check if Unit goes Fanatic; if so, exit to that UC [UC-??]
        3:          .Add New Unit
        4.	Target transfers settings to new unit
        5.	Change visibility status of Target
        6.	Use case ends when the Target changes status to Hardened

        Alternate Course A:
        Condition:

        Inheritance:
        Condition:

                    Post conditions
        1.	*/

        // create the hero
        int Hero = 1102;
        PersCreation UseObjectFactory = new PersCreation();
        PersUniti NewUnit = null;
        GameModule.getGameModule().getChatter().send("Enter Name of New Hero: " + TargParent.getbaseunit().getUnitName() + " HOB: Hero Creation");
        Scanner response = new Scanner(System.in);
        if (response.toString() != null) {
            String NewName = response.toString();
            NewUnit = UseObjectFactory.CreateNewInstance(Hero, NewName, TargParent);
            // update new Hero with values of previous unit - Do we need all of this
            UnitUpdateNewOldc UnitUpdateNewWithOld = new UnitUpdateNewOldc(NewUnit, TargParent);
            if (TargParent.getTargetunit() != null) {
                TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " HOB: creates Hero - " + NewUnit.getbaseunit().getUnitName());
            }
            // add new unit to Unitcol collection
            Scencolls.Unitcol.add(NewUnit);
            // Store values to update FireGroup and TargetGroup (maybe add movement?)
            if (NewUnit.getTargetunit() != null) {
                myNewTargs.add(NewUnit);
            }
            if (NewUnit.getFiringunit() != null) {
                myNewFiring.add(NewUnit);
            }
            // NO HoB as this comes from HOBMC
        }
        return true;
    }

    public LinkedList<PersUniti> GetNewTargs() {
        return myNewTargs;
    }
    public LinkedList<PersUniti> GetNewFirings () {
        return myNewFiring;
    }
    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/

}
