package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
//import VASL.build.module.fullrules.ObjectClasses.PersunitDecoratori;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;

import java.util.LinkedList;

public class UnitHeroicLdrCreationc implements StatusChangei {

    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private LinkedList<PersUniti> myNewTargs = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewFiring = new LinkedList<PersUniti>();

    public UnitHeroicLdrCreationc() {}

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

        // create the heroic leader as decorator class
        /*PersunitDecoratori NewTargParent =  new PersunitHeroicldrc(TargParent);
        if (TargParent.getTargetunit() != null) {NewTargParent.TargetPersUnit =
    New ObjectClassLibrary.ASLXNA.TargetHeroicLdrc(TargParent.TargetPersUnit)
    If Not

    IsNothing(TargParent.FiringPersUnit) Then NewTargParent.FiringPersUnit =
    New ObjectClassLibrary.ASLXNA.FiringHeroicLdrc(TargParent.FiringPersUnit)
    If Not

    IsNothing(TargParent.BasePersUnit) Then NewTargParent.BasePersUnit =
    New ObjectClassLibrary.ASLXNA.BaseHeroicLdrc(TargParent.BasePersUnit)
            'replace leader with heroic leader in Target group and in Unitcol
    Dim GetUnit
    As ObjectClassLibrary.ASLXNA.PersUniti =(
    From SearchUnit
    As ObjectClassLibrary.
    ASLXNA.PersUniti In
    Scencolls.Unitcol Where

    Trim(SearchUnit.BasePersUnit.UnitName) =

    Trim(NewTargParent.BasePersUnit.UnitName) Select SearchUnit).First
    If Not

    IsNothing(GetUnit) Then
                Scencolls.Unitcol.Remove(GetUnit)
            Scencolls.Unitcol.Add(NewTargParent)
    End If
    GetUnit =
    NewTargParent
            TargParent = NewTargParent

            ''
    Dim HeroicLdr
    As Integer = TargParent.BasePersUnit.LOBLink + 20
            ''
    Dim NewName
    As String = Trim(TargParent.BasePersUnit.UnitName)
            ''
    Dim UseObjectFactory = New
    ObjectFactoryClassLibrary.aslxna.PersCreation
            ''
    Dim NewUnit
    As ObjectClassLibrary.ASLXNA.PersUniti =UseObjectFactory.CreateNewInstance(HeroicLdr,NewName,TargParent)
            ' ' 'update new unit with values of previous unit - Do we need all of this
            ''
    Dim UnitUpdateNewWithOld
    As New

    UnitUpdateNewOldc(NewUnit, TargParent)

    If IsNothing(TargParent.TargetPersUnit) Then

    Dim ComFunc = New UtilWObj.ASLXNA.CommonFunctions(TargParent.BasePersUnit.Scenario)
    Dim FirerSan
    As Integer = ComFunc.GetEnemySan(TargParent.BasePersUnit.Nationality)
    Dim UseObjectFactory = New
    ObjectFactoryClassLibrary.aslxna.PersCreation
            TargParent = UseObjectFactory.CreateTargetUnitandProperty(TargParent, FirerSan)
    End If
    If Not

    IsNothing(TargParent.TargetPersUnit) Then
                'With NewUnit.TargetPersUnit
    TargParent.TargetPersUnit.CombatResultString =

    Trim(TargParent.BasePersUnit.UnitName) &": "&TargParent.TargetPersUnit.CombatResultString &" becomes heroic due to HOB "&vbCrLf ' & myResultstring & vbCrLf
            TargParent.SetTexture()
            TargParent.TargetPersUnit.UpdateTargetStatus(TargParent)
            'End With
    End If
            ''
    change values for
    former unit
            'With TargParent
                    '    If Not IsNothing(.TargetPersUnit) Then .TargetPersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay
                    '    .BasePersUnit.CX = False
                    '    .BasePersUnit.Pinned = False
                    '    .BasePersUnit.CombatStatus = ConstantClassLibrary.ASLXNA.CombatStatus.None
                    '    .BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving
                    '    .BasePersUnit.FirstSWLink = 0
                    '    .BasePersUnit.SecondSWlink = 0
                    '    .BasePersUnit.SW = 0
                    '    .SetTexture()
                    '    .TargetPersUnit.UpdateTargetStatus(TargParent)
                    'End With
                    ''
    remove old
    unit from
    moving list
    TOO EARLY -
    DO THIS
    LATER
            'If Not IsNothing(TargParent.MovingPersUnit) Then Scencolls.SelMoveUnits.Remove(TargParent)
                    '' add new
    unit to
    Unitcol collection
            'Scencolls.Unitcol.Add(NewUnit)
                    ''
    Store values
    to update
    FireGroup and

    TargetGroup(maybe add movement?)
            'If Not IsNothing(NewUnit.TargetPersUnit) Then myNewTargs.Add(NewUnit)
                    'If Not IsNothing(NewUnit.FiringPersUnit) Then myNewFiring.Add(NewUnit)

                    ''
    update SW
    values
            'With NewUnit
                    '    Dim SWChange As ObjectChange.ASLXNA.ChangeSWOwnerc
                    '    If .BasePersUnit.FirstSWLink > 0 Then SWChange = New ObjectChange.ASLXNA.ChangeSWOwnerc(.BasePersUnit.FirstSWLink, .BasePersUnit.Unit_ID)
                    '    If .BasePersUnit.SecondSWlink > 0 Then SWChange = New ObjectChange.ASLXNA.ChangeSWOwnerc(.BasePersUnit.SecondSWlink, .BasePersUnit.Unit_ID)
                    'End With

                    'No HoB as this comes from HOBMC*/
        return true;
    }

    public LinkedList<PersUniti> getNewTargs() {
        return myNewTargs;
    }
    public LinkedList<PersUniti> getNewFirings () {
        return myNewFiring;
    }
    /*public ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements StatusChangei.NewPopupitems
            Get

    End Get
    End Property*/
}
