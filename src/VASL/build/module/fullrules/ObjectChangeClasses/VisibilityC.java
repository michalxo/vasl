package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;

import java.util.LinkedList;

public class VisibilityC {

    public VisibilityC(){}
    public int GetStackStatus(LinkedList<PersUniti> StackCheck) {
        // called by ObjectChange.ASLXNA.AutoDM
        // determines visibility status of a stack of persuniti and returns value
        boolean SomeConcealed = false; boolean SomeVisible = false;
        int Mixed = 1; // returned value is one of these Const
        int AllConc = 2;
        int AllVis = 3;

        for (PersUniti StackUnit: StackCheck) {
            if (StackUnit.getbaseunit().getCon_ID() > 0) {
                SomeConcealed = true;
            } else {
                SomeVisible = true;
            }
        }
        if(SomeVisible && SomeConcealed) {
            return Mixed;
        } else if(SomeVisible && !(SomeConcealed)) {
            return AllVis;
        } else if (!(SomeVisible) && SomeConcealed) {
            return AllConc;
        }
        return AllVis;
    }

}
