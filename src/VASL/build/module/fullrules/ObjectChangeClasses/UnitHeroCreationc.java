package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CounterCreationC;
import VASSAL.build.GameModule;

import javax.swing.*;
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
        String NewName ="";
        while (NewName =="") {
            NewName = askforNewUnit(TargParent.getbaseunit().getUnitName());
        }
        PersCreation UseObjectFactory = new PersCreation();
        PersUniti NewUnit = null;
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
        // create a VASL counter
        createcounter();
        // NO HoB as this comes from HOBMC
        return true;
    }

    public LinkedList<PersUniti> getNewTargs() {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        return myNewFiring;
    }
    /**
     * Displays the input dialog and returns user input
     */
    public String askforNewUnit(String Oldname) {

        // show confirmation dialog
        JOptionPane pane = new JOptionPane();
        String newname =  pane.showInputDialog(null,
                "Enter Name of New Hero: ",
                Oldname + " creates a Hero",
                JOptionPane.QUESTION_MESSAGE
        );
        return newname;
    }
    public void createcounter() {
        CounterCreationC createcounter = new CounterCreationC();
        String countername = "VASSAL.build.module.PieceWindow:SMC/VASSAL.build.widget.ListWidget:ge/VASSAL.build.widget.PieceSlot:geHero";
        createcounter.createCounter(countername);
    }

}
