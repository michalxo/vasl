package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.util.LinkedList;


public class UnitsVisibilityc implements VisibilityStatusi {
    // called by ASLXNA.ASLXNA.movementvalidationc
    // handles determination of visibility status of a stack of Units
    private LinkedList<PersUniti> StackToCheck;
    public UnitsVisibilityc(LinkedList<PersUniti> PassStackToCheck) {
        StackToCheck = PassStackToCheck;
    }
    public int GetStatus() {
        boolean SomeConcealed = false; boolean SomeVisible = false;
        int Mixed  = 1;
        int AllConc  = 2;
        int AllVis  = 3;
        for (PersUniti CheckUnit: StackToCheck) {
            if (CheckUnit.getMovingunit().getIsConcealed()) {
                SomeConcealed = true;
            } else {
                SomeVisible = true;
            }
        }
        if (SomeVisible && SomeConcealed) {
            return Mixed;
        } else if (SomeVisible && !SomeConcealed) {
            return AllVis;
        } else if (!SomeVisible && SomeConcealed) {
            return AllConc;
        }
        // error if here
        return 0;
    }
}
