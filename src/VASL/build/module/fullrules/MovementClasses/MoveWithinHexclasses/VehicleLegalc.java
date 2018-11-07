package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

public class VehicleLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private Constantvalues.AltPos positionchangevalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    // constructor
    public VehicleLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption, Constantvalues.AltPos passpositionchange)  {
        hexclickedvalue = passnewhexclicked;
        MovementOptionvalue = passmovementoption;
        returnresultsvalue = "";
        positionchangevalue = passpositionchange;
    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // Test if room available and set final location: passenger or rider

        // NOT CODING YET AS VEHICLES NOT DONE

        /*Dim OH As VisibleOccupiedhexes :Dim Vehloading As DataClassLibrary.AFV
        Dim TypeCheck = New UtilClassLibrary.ASLXNA.TypeUtil
                OH = CType(Game.Scenario.HexesWithCounter(hexclickedvalue), VisibleOccupiedhexes)
        For Each Displaysprite As ObjectClassLibrary.ASLXNA.SpriteOrder In OH.VisibleCountersInHex
        If TypeCheck.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Vehicle, Displaysprite.TypeID) Then
                Vehloading = Game.Linqdata.GetVehiclefromCol(Displaysprite.ObjID)
        Select Case MovementOption
        Case ConstantClassLibrary.ASLXNA.UMove.EnterVehicle
        'Test for sufficient PP
        Dim PPCost As New PortagePointsC()
        Dim TotalPPCost = PPCost.CalcTotalPPUsed
        If Vehloading.GetVehtype = ConstantClassLibrary.ASLXNA.Vtype.Halftracked Or Vehloading.
        GetVehtype = ConstantClassLibrary.ASLXNA.Vtype.Truck Then
        Dim VehPPCapacity
        As Integer = CInt(Game.Linqdata.GetLOBVehData(ConstantClassLibrary.ASLXNA.LOBVeh.PP, CInt(Vehloading.AFVDefaultsID)))
        If TotalPPCost <=VehPPCapacity - Vehloading.PPUsing Then
                positionchangedvalue = ConstantClassLibrary.ASLXNA.AltPos.Passenger
        Return True
        End If
        Else
        If TotalPPCost <=14 - Vehloading.PPUsing Then
                positionchangedvalue = ConstantClassLibrary.ASLXNA.AltPos.Rider
        Return True
        End If
        End If
        Case ConstantClassLibrary.ASLXNA.UMove.ExitVehicle
                positionchangedvalue = 0
        Return True
        End Select
        End If
        Next*/
        return false;
    }
}
