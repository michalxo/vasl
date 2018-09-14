package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import javax.swing.*;
import java.util.LinkedList;

public class UnitReducesDMsc {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private String myResultstring;

    public UnitReducesDMsc(String Resultstring) {
        myResultstring = Resultstring;
    }
    public boolean Takeaction(PersUniti TargParent) {
               /*'Name:       TargetReducesBreaks()

                    'Identifier UC 203.5

                    '            Preconditions()
                    '2.	MMC Target is alive and has suffered a CR result due to rolling 12 on a MC

                    '            Basic Course
                    '6.	Use case begins when a CR result is produced [several possible causes: UC102-TargetCRMCResult; UC103-TargetMCResult]
                    '7.	Add new HS [UC217-AddNewUnit]
                    '8.	Target transfers settings to new unit & change its Order status to Broken-DM
                    '9.	Change visibility status of Target
                    '10.	Use case ends when the Target status changes to Reduced

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '2.
                    'create the new HS*/

        boolean PassHoBCHeck = false; // Hob test done by last unitchange
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Reduces);
        StatusChangei RunFirstChange = new UnitReducesc(myResultstring, PassHoBCHeck);
        RunFirstChange.Takeaction(TargParent);
        TargParent = (RunFirstChange.getNewTargs()).get(0);
        myResultstring = TargParent.getTargetunit().getCombatResultsString();
        TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.DMs);
        StatusChangei RunnextChange = new UnitDMsc();
        RunnextChange.Takeaction(TargParent);
        myNewFiring = RunFirstChange.getNewFirings();
        myNewTargs = RunFirstChange.getNewTargs();
        // No HoB - done in UnitReducesBreaksC
        return true;

    }
    public LinkedList<PersUniti> getNewTargs() {return myNewTargs;}
    public LinkedList<PersUniti> getNewFirings () {return myNewFiring;}

    /**
     * Displays the input dialog and returns user input
     */
    public String askforNewUnit(String Oldname) {

        //JFrame frame = new JFrame("Unit Reduces");
        JOptionPane pane = new JOptionPane();
        String newname =  pane.showInputDialog(null,
                "Enter Name of New Half-Squad: ",
                Oldname + " is reduced to a Half-Squad",
                JOptionPane.QUESTION_MESSAGE
        );
        return newname;
    }
}
