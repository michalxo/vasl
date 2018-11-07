package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.UtilityClasses.CommonFunctionsC;

import java.util.LinkedList;

public class UnitFanaticsc implements StatusChangei {
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();

    //Private myPopUpList As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    public UnitFanaticsc() {

    }
    public boolean Takeaction(PersUniti TargParent) {
            /*'Name:       TargetFanatics()

                    'Identifier UC 221

                    '            Preconditions()
                    '2.	Unbroken MMC Target is alive and gets a Fanatic result on a HOB dice roll

                    '            Basic Course
                    '3.	Use case begins when a Target makes a HOB dice roll [UC109-TargetHOBResult]
                    '4.	Use case ends when the Target changes status to Fanatic

                    'Alternate Course A:
                    'Condition:

                    'Inheritance:
                    'Condition:

                    '            Post conditions
                    '2.
*/
        if (TargParent.getTargetunit() == null) {
            CommonFunctionsC ComFunc = new CommonFunctionsC(TargParent.getbaseunit().getScenario());
            int FirerSan = ComFunc.GetEnemySan(TargParent.getbaseunit().getNationality());
            PersCreation UseObjectFactory = new PersCreation();
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan);
        }
        if (TargParent.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken || TargParent.getbaseunit().getOrderStatus() == Constantvalues.OrderStatus.Broken_DM) {
            StatusChangei RunStatusChange = new UnitGoodOrdersc();
            if (RunStatusChange != null) {
                RunStatusChange.Takeaction(TargParent);
            } else {
                //myPopUpList = GetStatusChange.PopUpItems; temporary while debugging UNDO
                return false;
            }
           // update Target and Firing lists with new units
            if (RunStatusChange.getNewTargs() != null) {
                myNewTargs = RunStatusChange.getNewTargs();
            }
            TargParent.getTargetunit().setPersUnitImpact(Constantvalues.PersUnitResult.Fanatics);
        }
        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        if(TargParent.getTargetunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Normal) {TargParent.getTargetunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fanatic);}
        if (TargParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Encircled) {TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fan_Enc);}
        if (TargParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Wounded) {TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fan_Wnd);}
        if (TargParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Enc_Wnd) {TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fan_Wnd_Enc);}
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " HOB: becomes Fanatic");

        // No HoB - Fanaticism is caused by HOB

        return true;
    }

    public LinkedList<PersUniti> getNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        // no code required; no new unit
        return null;
    }

}
