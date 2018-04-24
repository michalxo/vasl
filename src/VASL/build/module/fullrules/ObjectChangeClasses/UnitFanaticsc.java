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
        TargParent.getTargetunit().setOrderStatus(Constantvalues.OrderStatus.GoodOrder);
        if(TargParent.getTargetunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Normal) {TargParent.getTargetunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fanatic);}
        if (TargParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Encircled) {TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fan_Enc);}
        if (TargParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Wounded) {TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fan_Wnd);}
        if (TargParent.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Enc_Wnd) {TargParent.getbaseunit().setFortitudeStatus(Constantvalues.FortitudeStatus.Fan_Wnd_Enc);}
//                '.BasePersUnit.CX = False
//                        '.BasePersUnit.Pinned = False
//                        '.BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
//                        '.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
        TargParent.getTargetunit().UpdateTargetStatus(TargParent);
        TargParent.getTargetunit().setCombatResultsString(TargParent.getTargetunit().getCombatResultsString() + " becomes Fanatic");

        //'HoB
        if (TargParent.getTargetunit().getHoBFlag()) { // rolled a 2
            Constantvalues.PersUnitResult HobChange = TargParent.getTargetunit().HOBMC();
            StatusChangei RunStatusChange;
            SelectStatusChangec GetStatusChange = new SelectStatusChangec();
            RunStatusChange = GetStatusChange.HoBStatusChange(HobChange, TargParent);
            if (RunStatusChange != null ) {
                RunStatusChange.Takeaction(TargParent);
            } else {
                //myPopUpList = GetStatusChange.PopUpItems; temporary while debugging UNDO
                return false;
            }
            TargParent.getbaseunit().setOrderStatus(TargParent.getTargetunit().getOrderStatus());
            // update Target and Firing lists with new units
            if (RunStatusChange.getNewTargs() != null) {myNewTargs = RunStatusChange.getNewTargs();}
        }
        return true;
    }

    public LinkedList<PersUniti> getNewTargs () {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        // no code required; no new unit
        return null;
    }

    /*Public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get
    Return myPopUpList
    End Get
    End Property*/
}
