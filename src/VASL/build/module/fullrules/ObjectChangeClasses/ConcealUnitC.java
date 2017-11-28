package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;

public class ConcealUnitC implements VisibilityChangei {
    //conceals a unit, whether from known status or hidden
    private PersUniti ConcealUnit = null;
    private String myConcealResults ="";
    private ScenarioCollectionsc Scencolls  = ScenarioCollectionsc.getInstance();

    public ConcealUnitC(int ConcealUnitID) {
        for (PersUniti UnittoConceal: Scencolls.Unitcol) {
            if (UnittoConceal.getbaseunit().getUnit_ID() == ConcealUnitID) {
                ConcealUnit = UnittoConceal;
                break;
            }
        }
    }

    public boolean TakeAction () {

        if (ConcealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed) {return true;}
         //no action required, unit is already concealed
        if (ConcealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible ||
                    ConcealUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
                ConcealUnit.getbaseunit().setVisibilityStatus(Constantvalues.VisibilityStatus.Concealed);
               /* 'see if ? exists in hex; ask to join NO LONGER NEED TO DO THIS AS MANAGED BY VASL
                Dim Constring As String = ""
                Try
                Dim ConList As List(Of ObjectClassLibrary.ASLXNA.PersUniti) = (From selcon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selcon.BasePersUnit.LOCIndex = ConcealUnit.BasePersUnit.LOCIndex AndAlso
                selcon.BasePersUnit.TypeType_ID = ConstantClassLibrary.ASLXNA.Typetype.Concealment Select selcon).ToList
                If ConList.Count > 0 Then
                'concealment exists in hex
                For Each Concounter As ObjectClassLibrary.ASLXNA.PersUniti In ConList
                'create string of ? names
                Constring &= Trim(Concounter.BasePersUnit.UnitName) & " "
                Next
                'check what to use
                Dim Currentcon As String = InputBox(Constring & "found in Location. Enter Name of Existing '?' to use OR Enter nothing to create new '?': ", "Concealing Unit")
                If Not Trim(Currentcon) = "" Then
                '? exists, add
                UsingCon = (From selcon As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where Trim(selcon.BasePersUnit.UnitName) = Trim(Currentcon) Select selcon).First
                Else
                '? exists but want to create new one
                UsingCon = CreateCon(ConcealUnit)
                End If
                Else
                'no ? exists, need to create new ? counter and add
                UsingCon = CreateCon(ConcealUnit)
                End If
                Catch
                'no ? exists, need to create new ? counter and add
                UsingCon = CreateCon(ConcealUnit)
                End Try
                'add unit to ?
                ConcealUnit.BasePersUnit.Con_ID = UsingCon.BasePersUnit.Unit_ID
                ConcealUnit.SetTexture()*/
                myConcealResults = ConcealUnit.getbaseunit().getUnitName();
                // Need to add any SW associated with this unit
                if (ConcealUnit.getbaseunit().getFirstSWLink() != 0) {  // 0 value means no SW
                    // retrieve SW and change visibility status
                    for (SuppWeapi OBSWitem : Scencolls.SWCol) {
                        if (OBSWitem.getbaseSW().getUnit_ID() == ConcealUnit.getbaseunit().getFirstSWLink()) {
                            if (OBSWitem.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible ||
                                    OBSWitem.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
                                OBSWitem.getbaseSW().setVisibilityStatus(Constantvalues.VisibilityStatus.Concealed);
                                myConcealResults += " & " + OBSWitem.getbaseSW().getUnitName();
                            }
                        }
                    }
                }
                if (ConcealUnit.getbaseunit().getSecondSWLink() != 0) {  // 0 value means no SW
                    // retrieve SW and change visibility status
                    for (SuppWeapi OBSWitem : Scencolls.SWCol) {
                        if (OBSWitem.getbaseSW().getUnit_ID() == ConcealUnit.getbaseunit().getSecondSWLink()) {
                            if (OBSWitem.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Visible ||
                                OBSWitem.getbaseSW().getVisibilityStatus() == Constantvalues.VisibilityStatus.Hidden) {
                                OBSWitem.getbaseSW().setVisibilityStatus(Constantvalues.VisibilityStatus.Concealed);
                                myConcealResults += " & " + OBSWitem.getbaseSW().getUnitName();
                            }
                        }
                    }
                }
                myConcealResults += " conceals in " + ConcealUnit.getbaseunit().getHexName();
                ConcealUnit.getbaseunit().UpdateBaseStatus(ConcealUnit);
                return true;  // unit is concealed
        }
        // if here then something wrong
        GameModule.getGameModule().getChatter().send(ConcealUnit.getbaseunit().getUnitName() + " could not conceal. Action fails");
        return false;
    }
    public String getActionResult() {return myConcealResults;}

        /*Private Function CreateCon(ByVal UnittoConceal As ObjectClassLibrary.ASLXNA.PersUniti) As ObjectClassLibrary.ASLXNA.PersUniti
        Dim NewName As String = InputBox("Enter Name of New ?: ", "Adding New Concealment Counter", )
        Dim UseObjectFactory = New ObjectFactoryClassLibrary.aslxna.PersCreation
        Dim LoBLink As Integer = UnittoConceal.BasePersUnit.Nationality + 2000
        Dim NewUnit As ObjectClassLibrary.ASLXNA.PersUniti = UseObjectFactory.CreateNewInstance(LoBLink, NewName, UnittoConceal)
        'update new HS with values of previous unit - Do we need all of this
        Dim UnitUpdateNewWithOld As New UnitUpdateNewOldc(NewUnit, UnittoConceal)
        If Not IsNothing(UnittoConceal.TargetPersUnit) Then
        'NewUnit = UseObjectFactory.CreateTargetUnitandProperty(NewUnit)
        With NewUnit.TargetPersUnit 'TargetPersUnit already created by UnitUpdateNewWithOldc
        .CombatResultString = myConcealResults & "added to " & Trim(NewUnit.BasePersUnit.UnitName) & vbCrLf
        If .OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken Then .OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken_DM
        End With
        End If
        Return NewUnit
        End Function{*/
}
