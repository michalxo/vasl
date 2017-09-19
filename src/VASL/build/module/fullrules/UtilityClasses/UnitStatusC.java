package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;

public class UnitStatusC {
    // handles Unit status checks that happen too infrequently to be built into the Unit objects in ObjectClassLibrary

    public UnitStatusC() {
    }

    public int GetSW(OrderofBattle Unititem, int SW1or2) {
        // called by IFT.AddFirerUnit
        // returns OBSW_ID of SW possessed by firing unit, 0 if no SW possessed

        if (Unititem.getSW() == 0) {
            return 0;
        } else {
            switch (SW1or2) {
                case 1:
                    return (int) Unititem.getFirstSWLink();
                case 2:
                    return (int) Unititem.getSecondSWlink();
                default:
                    return 0;
            }
        }
    }

    // THE FOLLOWING FUNCTIONS ARE REQUIRED BY THE SURRENDER PROCESS - ESPECIALLY OBJECTCHANGE.SELECTSTATUSCHANGE.HOBSTATUSCHANGE
    // AND NEED TO BE FULLY PROGRAMMED AS OBJECTS ARE CREATED - AUG 14
    public boolean IsGurkha(PersUniti CheckUnit) {

        //'called by
        //        '
        //        'If CheckUnit.BasePersUnit.LOBLink = ??? Then
        return false;
    }

    public boolean IsJapanese(PersUniti CheckUnit) {
        //'called by
        //'
        //'If CheckUnit.BasePersUnit.LOBLink = ??? Then
        return false;
    }

    public boolean IsPartisan(PersUniti CheckUnit) {
        //'called by
        //'
        //'If CheckUnit.BasePersUnit.LOBLink = ??? Then
        return false;
    }

    public boolean IsCommissar(PersUniti CheckUnit) {

            //'called by
            //        '
            //        'If CheckUnit.BasePersUnit.LOBLink = ??? Then
                    return false;
    }
    public boolean IsFanatic(PersUniti CheckUnit) {
        //'called by
        //'
        //'If CheckUnit.BasePersUnit.LOBLink = ??? Then
        return false;
    }
    public boolean IsSS(PersUniti CheckUnit) {
        //'called by
        //'
        //'If CheckUnit.BasePersUnit.LOBLink = ??? Then
        return false;
    }
//        'public Function ISUnitStatus(ByVal OBLink As Integer, ByVal Statustocheck As Integer) As Boolean
//                '    'called by IFT.CalcFP
//        '    'returns Yes/No value of queried status
//        '    'Unititem is the inf unit being queried, StatusToCheck is the status being queried
//        '    Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
//        '    Dim Unititem As DataClassLibrary.OrderofBattle = Linqdata.GetUnitfromCol(OBLink)
//                '    Select Case Statustocheck
//                '        Case ConstantClassLibrary.ASLXNA.CheckSpecificStatus.CX
//                '            Return Unititem.CX
//                '        Case Else
//                '            return false;
//                '    End Select
//                'End Function


}
