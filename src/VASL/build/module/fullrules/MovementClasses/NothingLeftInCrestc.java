package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class NothingLeftInCrestc {
    private LinkedList<LocationContentc> ContentsToCheck = new LinkedList<LocationContentc>();
    public NothingLeftInCrestc(LinkedList<LocationContentc> ContentsInLocation) {
        ContentsToCheck = ContentsInLocation;
    }
    public boolean NothingLeft(Constantvalues.AltPos CrestPositionTocheck) {
        ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();
        //Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
        for (LocationContentc LocationThing : ContentsToCheck) {
            //If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, LocationThing.TypeID) Then
            for (PersUniti Checkcrest : scencolls.Unitcol) {
                if (Checkcrest.getbaseunit().getUnit_ID() == LocationThing.getObjID()) {
                    if (Checkcrest.getbaseunit().gethexPosition() == CrestPositionTocheck) {
                        return false;
                    }
                }
            }
            /*ElseIf TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, LocationThing.TypeID) Then
            Dim checkcrest As ObjectClassLibrary.ASLXNA.PersUniti = (From selunit As
            ObjectClassLibrary.ASLXNA.PersUniti In
            scencolls.Unitcol Where selunit.BasePersUnit.Unit_ID = LocationThing.ObjID AndAlso selunit.
            BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment Select selunit).First
            If checkcrest.BasePersUnit.hexPosition = CrestPositionTocheck Then Return False
            ElseIf TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.SW, LocationThing.TypeID) Then
            Dim checkcrest As DataClassLibrary.OrderofBattleSW = Game.Linqdata.GetOBSWRecord(LocationThing.ObjID)
            If checkcrest.Position = CrestPositionTocheck Then Return False*/
            //End If
        }
        return true;
    }
}
