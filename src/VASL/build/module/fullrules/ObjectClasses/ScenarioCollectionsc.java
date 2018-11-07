package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.ThingsToDo;
import VASL.build.module.fullrules.DataClasses.Unpossessed;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.ObjectFactoryClasses.SWCreation;
import VASL.build.module.fullrules.UtilityClasses.*;
import VASSAL.command.Command;
import VASSAL.counters.GamePiece;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.util.LinkedList;

/**
 * Created by dougr_000 on 7/16/2017.
 */

    /* The Object class library is used to define objects used by the program. This definition includes all functionality
       of the objects
       Object creation is managed by the Object Factory class library.

       In many instances an interface and concrete implementation classes are used to handle creation of different objects with the same structure (ie different personnel unit types such as German 838 and Russian 426)
       This library also includes decorator classes for objects that require them

       AS OF JULY 2014 IMPLEMENTATION IS INCOMPLETE FOR ALL OF THE OBJECTS IMPLEMENTING THE PERSUNITI INTERFACE AND RELATED PROPERTY/DECORATOR CLASSES

    */

/*
        Public Interface MenuItemObjectholderinteface
        'used with Linqdata.GetContextItems and event driven menus (ie choose guard)
        'holds info about menu items to be displayed

        Property Activities As Integer
        Property ActivityNames As String
        Property ActivityChoices As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)

        End Interface
        'concrete classes
        Public Class MenuItemsMopUp
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjtypeID As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsKindle
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjTypeID As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim NoBurnable As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation)
        Dim NewDb As MapDataClassLibrary.MapDataClassesDataContext = New MapDataClassLibrary.MapDataClassesDataContext
        Try
        Dim LOCCol As IQueryable(Of MapDataClassLibrary.GameLocation) = From QU As MapDataClassLibrary.GameLocation In NewDb.GameLocations Select QU
        MapCol = LOCCol
        Catch ex As Exception
        ' MsgBox("Some kind of error" & ASLMapLink, , "CreateMapCollection Failure")
        End Try
        Dim ISTerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        NoBurnable = Not (ISTerrChk.IsLocationTerrainA(CInt(Selunit.LocIndex), ConstantClassLibrary.ASLXNA.Location.Burnabletype))
        If NoBurnable Then Exit Sub 'leave all properties empty
        'test for status allows
        If Selunit.CombatStatus <> ConstantClassLibrary.ASLXNA.CombatStatus.None Then Exit Sub 'Unit has already done Prep Fire activity
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsAssemble
        Implements MenuItemObjectholderinteface

        Sub New(ByVal ObjTypeID As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim NoSW As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Selunit.SW <> 0 Then NoSW = False
        Else
        Exit Sub
        End If
        If NoSW Then Exit Sub 'leave all properties empty
        'test for status allows
        If Selunit.CombatStatus <> ConstantClassLibrary.ASLXNA.CombatStatus.None Then Exit Sub 'Unit has already done Prep Fire activity
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next

        End Sub

        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDismantle
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjTypeID As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim NoSW As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Selunit.SW <> 0 Then NoSW = False
        Else
        Exit Sub
        End If
        If NoSW Then Exit Sub 'leave all properties empty
        'test for status allows
        If Selunit.CombatStatus <> ConstantClassLibrary.ASLXNA.CombatStatus.None Then Exit Sub 'Unit has already done Prep Fire activity
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsLimber
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsUnlimber
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsOppFire
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsEntrench
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRadioContact
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsStarshell
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsFireSW
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsLOS
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsForfeitWA
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim Selunit As DataClassLibrary.OrderofBattle : Dim selcon As DataClassLibrary.Concealment
        Dim Hexnum As Integer = 0 : Dim hexloc As Integer = 0 : Dim hexposition As Integer = 0 : Dim UsingWA As Boolean = False
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : Hexnum = CInt(Selunit.hexnum) : hexloc = CInt(Selunit.hexlocation) : hexposition = CInt(Selunit.Position)
        UsingWA = Selunit.HasWallAdvantage
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, Objtypeid) Then
        selcon = Linqdata.GetConcealmentfromCol(ObjID) : Hexnum = selcon.hexnum : hexloc = selcon.Hexlocation : hexposition = selcon.Position
        UsingWA = selcon.HasWallAdvantage
        Else
        Exit Sub
        End If
        Dim Otherhexnum As List(Of Integer) = MapGeo.FillDirExtentArray(Hexnum)
        If Not UsingWA Then Exit Sub 'can forfeit if don't have; leave property values empty

        'Has WA, check if can drop need a tweak here to reflect crest status
        If SideChk.IsWAMandatory(Hexnum, Otherhexnum, hexloc, hexposition) Then
        If Not (hexposition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And hexposition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Then Exit Sub 'can't quit WA; leave property values empty
        End If
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDropSw
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        End Sub
        Public Sub DropCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim NoSW As Boolean = False
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Dim SWCheck As Integer = 0 : Dim SWitem As String
        For x = 1 To 2
        If x = 1 Then
        SWCheck = CInt(Selunit.FirstSWLink)
        Else
        SWCheck = CInt(Selunit.SecondSWlink)
        End If
        If SWCheck > 0 Then
        'add SW to SW list to display as popup
        MenuItem.Activities = 0
        SWitem = Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon, SWCheck)
        Dim menuItemadd = New MenuItemsDropSw(objtypeid, ObjID, MenuItem)
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.ContextM.DropSW
        menuItemadd.ActivityNames = Trim(SWitem)
        ActivityChoices.Add(menuItemadd)
        End If
        Next
        If ActivityChoices.Count = 0 Then NoSW = True 'no SW found
        Else
        NoSW = True
        End If
        If NoSW Then Exit Sub 'no SW so leave property values empty
        'test for status allows
        'If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moved Or Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moving Or Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.AssaultMoving Then Exit Sub 'Unit has already done Prep Fire activity

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRecoverSW
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim NoUnpossessed As Boolean = True
        Dim SelSW As DataClassLibrary.OrderofBattleSW
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.SW, objtypeid) Then  'infantry
        SelSW = Linqdata.GetOBSWRecord(ObjID)
        If SelSW.Owner = 0 Then NoUnpossessed = False 'unpossessed exists
        End If
        If NoUnpossessed Then Exit Sub 'no unpossessed so leave properties empty
        'NEED TO ADD status check for multiple phases: rally, movement, defF

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDoubleTime
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim DTOK As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        'If Not (Selcon.IsDummy) Then
        Dim Conunittest As IQueryable(Of DataClassLibrary.OrderofBattle) = Linqdata.RetrieveConcealedUnits(CInt(Selcon.Con_ID))
        For Each ConUnit As DataClassLibrary.OrderofBattle In Conunittest
        If ConUnit.OrderStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded Or ConUnit.OrderStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Enc_Wnd Or
        ConUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd Or ConUnit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd_Enc Or
        ConUnit.CX Then
        DTOK = False : Exit For
        End If
        Next
        'ElseIf Selcon.CX Then
        '    DTOK = False
        'End If
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Selunit.OrderStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded Or Selunit.OrderStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Enc_Wnd Or
        Selunit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd Or Selunit.FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd_Enc Or Selunit.CX Then DTOK = False
        Else
        Exit Sub
        End If

        If Not (DTOK) Then Exit Sub 'NEED TO ADD STATUS CHECK Or Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsAssaultMove
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim AMOK As Boolean = True
        'check if already moved; if so can't AM
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim selcon As DataClassLibrary.Concealment
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving Then AMOK = False
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        selcon = Linqdata.GetConcealmentfromCol(ObjID)
        If selcon.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving Then AMOK = False
        Else
        Exit Sub
        End If
        If Not AMOK Then Exit Sub 'cant assault move; leave property values empty
        'NEED TO ADD STATUS CHECK
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDash
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        End Sub
        Public Sub DashCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim NoRoad As Boolean = True : Dim hexnum As Integer = 0 : Dim SelUnit As DataClassLibrary.OrderofBattle
        Dim MapTest As MapDataClassLibrary.GameLocation : Dim TerrTest As Integer
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)   'class for various data-based terrain checks
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        SelUnit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        For i = 1 To 6
        hexnum = MapGeo.DirExtent(CInt(SelUnit.hexnum), i, 1)
        If Not hexnum = 0 Then
        MapTest = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        TerrTest = CInt(MapTest.Location)
        If TerrChk.IsLocationTerrainA(hexnum, TerrTest, ConstantClassLibrary.ASLXNA.Location.Roadtype) Then
        NoRoad = False
        Dim menuItemadd = New MenuItemsDropSw(objtypeid, ObjID, MenuItem)
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.ContextM.Dash
        menuItemadd.ActivityNames = Trim(MapTest.Hexname)
        ActivityChoices.Add(menuItemadd)
        End If
        End If
        Next
        If ActivityChoices.Count = 0 Then Exit Sub 'no road found
        'checklevel, already moving and status
        If (LevelChk.IsLocationUpperLevel(CInt(SelUnit.hexnum), CInt(SelUnit.hexlocation))) Or (SelUnit.hexlocation = ConstantClassLibrary.ASLXNA.Location.Cellar) Then NoRoad = True
        If SelUnit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving Then NoRoad = True
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then NoRoad = True

        If NoRoad Then
        ActivityChoices.Clear()
        Exit Sub 'no road found or unit already moving or at upper level; leave properties empty
        End If

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSewerentry
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'test for manhole
        If Not (TerrChk.IsLocationTerrainA(CInt(Selunit.LocIndex), ConstantClassLibrary.ASLXNA.Location.Manholetype)) Then Exit Sub 'no manhole present so leave properties empty
        'test for status allows
        If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moved Then Exit Sub 'Unit has already done MOvement activity
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsClimbing
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim NoCliff As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'get unit info
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'test for cliff
        For i = 1 To 6
        If Linqdata.Gethexsidetype(i, CInt(Selunit.hexnum)) = ConstantClassLibrary.ASLXNA.Hexside.Cliff Then
        NoCliff = False : Exit For
        End If
        Next
        If NoCliff Then Exit Sub ' no cliff found so leave properties empty
        'status test
        If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moved Then Exit Sub 'Unit has already done MOvement activity
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsInfOverrun
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim SelUnit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'get unit info
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'NEED TO ADD MORE HERE
        'status test
        If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moved Then Exit Sub 'Unit has already done MOvement activity
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsPlaceDC
        Implements MenuItemObjectholderinteface
        Sub New(ByVal oBjtypeid As Integer, ByVal ObjID As Integer, ByVal Menuitem As DataClassLibrary.ASLXNA.Objectholder)

        Dim NoDC As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        If Selunit.FirstSWLink > 0 Then
        Dim SWLink As Integer = CInt(Selunit.FirstSWLink)
        Dim SelSW As DataClassLibrary.OrderofBattleSW = Linqdata.OBSWcol.Where(Function(FindSW) FindSW.OBSW_ID = SWLink).First
        If SelSW.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.DemoC) Then 'has a DC
        NoDC = False
        End If
        End If
        If Selunit.SecondSWlink > 0 AndAlso NoDC Then
        Dim SWLink As Integer = CInt(Selunit.SecondSWlink)
        Dim SelSW As DataClassLibrary.OrderofBattleSW = Linqdata.OBSWcol.Where(Function(FindSW) FindSW.OBSW_ID = SWLink).First
        If SelSW.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.DemoC) Then 'has a DC
        NoDC = False
        End If
        End If
        If NoDC Then Exit Sub 'no DC so don't show; leaves properties empy
        'test for status allows
        If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moved Then Exit Sub 'Unit has already done MOvement activity
        'If Game.Scenario.Moveobsi.StatusPrevents(Menuitem.Activities) Then RemoveList.Add(Menuitem)

        'all good so set property values
        Activities = Menuitem.Activities
        ActivityNames = Menuitem.ActivityNames
        'For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In Menuitem.ActivityChoices
        '    ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        'Next

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSetDC
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim NoDC As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        If Selunit.FirstSWLink > 0 Then
        Dim SWLink As Integer = CInt(Selunit.FirstSWLink)
        Dim SelSW As DataClassLibrary.OrderofBattleSW = Linqdata.OBSWcol.Where(Function(FindSW) FindSW.OBSW_ID = SWLink).First
        If SelSW.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.DemoC) Then 'has a DC
        NoDC = False
        End If
        End If
        If Selunit.SecondSWlink > 0 AndAlso NoDC Then
        Dim SWLink As Integer = CInt(Selunit.SecondSWlink)
        Dim SelSW As DataClassLibrary.OrderofBattleSW = Linqdata.OBSWcol.Where(Function(FindSW) FindSW.OBSW_ID = SWLink).First
        If SelSW.ISATypeOf(ConstantClassLibrary.ASLXNA.SWtype.DemoC) Then 'has a DC
        NoDC = False
        End If
        End If
        If NoDC Then Exit Sub 'no DC so don't show; leaves properties empy
        'test for status allows
        If Selunit.MovementStatus <> ConstantClassLibrary.ASLXNA.MovementStatus.Moved Then Exit Sub 'Unit has already done MOvement activity
        'If Game.Scenario.Moveobsi.StatusPrevents(Menuitem.Activities) Then RemoveList.Add(Menuitem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsThrowDC
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRecoverSWBrk
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        End Sub
        Public Sub RecoveryCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        'check selected SMC and for broken unit with SW
        'look for SMC
        Dim NoSMCRecovery As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Not Selunit.IsUnitASMC Then Exit Sub
        Else
        Exit Sub
        End If
        'have selected an SMC; now look for broken unit with SW

        'NEED TO ADD STATUS CHECK
        ''test for status allows
        ''If Selunit.CombatStatus <> ConstantClassLibrary.ASLXNA.CombatStatus.None Then Exit Sub 'Unit has already done Prep Fire activity

        Dim BrokiesList As IQueryable(Of DataClassLibrary.OrderofBattle) = Linqdata.GetUnitsInLocation(CInt(Selunit.LocIndex))
        For Each LookforBroken As DataClassLibrary.OrderofBattle In BrokiesList
        If LookforBroken.OrderStatus >= ConstantClassLibrary.ASLXNA.OrderStatus.Broken And LookforBroken.OrderStatus <= ConstantClassLibrary.ASLXNA.OrderStatus.DisruptedDM And LookforBroken.SW > 0 Then
        NoSMCRecovery = False : Exit For
        End If
        Next
        If NoSMCRecovery Then Exit Sub 'no broken unit with SW in location
        'this is where need to add next menu level - to hold SW to be recovered
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        'find SW
        For Each LookforBroken As DataClassLibrary.OrderofBattle In BrokiesList
        If LookforBroken.OrderStatus >= ConstantClassLibrary.ASLXNA.OrderStatus.Broken And LookforBroken.OrderStatus <= ConstantClassLibrary.ASLXNA.OrderStatus.DisruptedDM And LookforBroken.SW > 0 Then
        Dim SWCheck As Integer = 0 : Dim SWitem As String
        For x = 1 To 2
        If x = 1 Then
        SWCheck = CInt(LookforBroken.FirstSWLink)
        Else
        SWCheck = CInt(LookforBroken.SecondSWlink)
        End If
        If SWCheck > 0 Then
        'add SW to SW list to display as popup
        SWitem = Linqdata.GetOBSWData(ConstantClassLibrary.ASLXNA.OBSWitem.OBweapon, SWCheck)
        Dim menuItemadd = New MenuItemsRecoverSWBrk(objtypeid, ObjID, MenuItem)
        menuItemadd.Activities = ConstantClassLibrary.ASLXNA.ContextM.RecoverSWBrk
        menuItemadd.ActivityNames = Trim(SWitem)
        ActivityChoices.Add(menuItemadd)
        End If
        Next
        If ActivityChoices.Count = 0 Then Exit Sub 'no SW found
        End If
        Next
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        'For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        '    ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        'Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsExitObst
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        'must be in feature for this to show
        Dim FeaturePresent As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Not Selunit.IsOccupyingFeature Then FeaturePresent = False
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        If Not Selcon.IsOccupyingFeature Then FeaturePresent = False
        Else
        Exit Sub
        End If
        If Not FeaturePresent Then Exit Sub 'can't exit feature if not present; leave property values empty
        'NEED TO CHECK STATUS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsMount
        Implements MenuItemObjectholderinteface
        Sub New(ByVal OBJTYPEID As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim HorseBikePresent As Boolean = False
        If OBJTYPEID = ConstantClassLibrary.ASLXNA.WhiteC.Horses Or OBJTYPEID = ConstantClassLibrary.ASLXNA.WhiteC.Motorcyles Then HorseBikePresent = True 'mountable item present
        'more tests required. Need to test for personnel present and not already loaded (both personnel and horse/bike)

        If Not HorseBikePresent Then Exit Sub 'no mountable item; leave property values empty
        'NEED TO ADD STATUS CHECK
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDismount
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)


        Dim HorseBikePresent As Boolean = False
        If OBJTYPEID = ConstantClassLibrary.ASLXNA.WhiteC.Horses Or OBJTYPEID = ConstantClassLibrary.ASLXNA.WhiteC.Motorcyles Then HorseBikePresent = True 'DISmountable item present
        'more tests required. Need to test for personnel present and not already loaded (both personnel and horse/bike)

        If Not HorseBikePresent Then Exit Sub 'no dismountable item; leave property values empty
        'NEED TO ADD STATUS CHECK
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsClearance
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        End Sub
        Public Sub ClearanceCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'need to determine submenu items for amongst Rubble, Wire, mines, Set DC, roadblocks, and Flame
        Dim Clearablepresent As Boolean = False : Dim ClearanceAllowed As MenuItemsClearance : Dim Hexname As String = ""
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim Selunit As DataClassLibrary.OrderofBattle
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Selunit.IsUnitASMC Then Exit Sub
        Else
        Exit Sub
        End If
        Hexname = GetLocs.GetnamefromdatatableMap(CInt(Selunit.hexnum))
        'In hex
        Dim GetLocations As IQueryable(Of MapDataClassLibrary.GameLocation) = GetLocs.RetrieveLocationsinHex(CInt(Selunit.hexnum), "Hexnum")
        For Each GetLocation As MapDataClassLibrary.GameLocation In GetLocations
        Dim Hexterraintype As Integer = GetLocation.Location
        If Hexterraintype = ConstantClassLibrary.ASLXNA.Location.WoodRubbleFallen Or Hexterraintype = ConstantClassLibrary.ASLXNA.Location.StoneRubbleFallen Or
        Hexterraintype = ConstantClassLibrary.ASLXNA.Location.WoodRubbleFallTB Or Hexterraintype = ConstantClassLibrary.ASLXNA.Location.StoneRubbleFallTB Then  'clearable rubble
        'NEED TO ADD STATUS CHECK
        'If Not (Game.Scenario.Moveobsi.StatusPrevents(ConstantClassLibrary.ASLXNA.UMove.ClearRubble)) Then
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearRubble
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear Rubble"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True
        'End If
        Else
        If GetLocation.APMines > 0 And GetLocation.APMinesVisible Then  ' AP Mines
        'NEED TO ADD STATUS CHECKS
        'If Not (Game.Scenario.Moveobsi.StatusPrevents(ConstantClassLibrary.ASLXNA.UMove.ClearMine)) Then
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearMine
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear Mines"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True
        'End If
        ElseIf GetLocation.ATMines > 0 And GetLocation.ATMinesVisible Then
        If TerrChk.IsLocationTerrainA(CInt(Selunit.hexnum), CInt(Selunit.hexlocation), ConstantClassLibrary.ASLXNA.Location.HardSurftype) Then
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearRoadATMine
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear AT Mines on Hard Surface"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True
        Else
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearMine
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear AT Mines"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True
        End If
        ElseIf GetLocation.IsWire And GetLocation.WireVisible Then
        'If Not (Game.Scenario.Moveobsi.StatusPrevents(ConstantClassLibrary.ASLXNA.UMove.ClearWire)) Then
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearWire
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear Wire"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True
        'End If
        ElseIf GetLocation.Smoke = ConstantClassLibrary.ASLXNA.VisHind.Flame Or GetLocation.Smoke = ConstantClassLibrary.ASLXNA.VisHind.HamperedFlame Then
        'If Not (Game.Scenario.Moveobsi.StatusPrevents(ConstantClassLibrary.ASLXNA.UMove.ClearFlame)) Then
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearFlame
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear Flame"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True
        'End If
        'NEED TO FIX SET DC
        'ElseIf selitem.Selected And Game.Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.SW, selitem.TypeID) And selitem.LocIndex = GetLocation.LocIndex Then 'setDC
        '    If selitem.TypeID = ConstantClassLibrary.ASLXNA.SWtype.SetDC Then
        '        If Not (Game.Scenario.Moveobsi.StatusPrevents(ConstantClassLibrary.ASLXNA.UMove.ClearSetDC)) Then
        '            MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        '            ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        '            ClearanceAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ClearSetDC
        '            ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear Set DC"
        '            ActivityChoices.Add(ClearanceAllowed)
        '            Clearablepresent = True
        '        End If
        End If
        End If
        Next
        'sides - roadblock
        For i = 1 To 6
        Dim Sidetest As Integer = Linqdata.Gethexsidetype(i, CInt(Selunit.hexnum))
        If Sidetest >= ConstantClassLibrary.ASLXNA.Hexside.Roadblock0 And Sidetest <= ConstantClassLibrary.ASLXNA.Hexside.Roadblock6 Then 'roadblock
        'If Not (Game.Scenario.Moveobsi.StatusPrevents(ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk)) Then
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        ClearanceAllowed = New MenuItemsClearance(objtypeid, ObjID, MenuItem)
        ClearanceAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk * 10) + i
        ClearanceAllowed.ActivityNames = Trim(Hexname) & ": Clear RdBlk"
        ActivityChoices.Add(ClearanceAllowed)
        Clearablepresent = True : Exit For
        'End If
        End If
        Next

        If Not Clearablepresent Then Exit Sub 'no clearable terrain; leave property values empty


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsHookGun
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'must be proper Gun present for this to show
        Dim GunPresent As Boolean = False
        Dim SelGun As DataClassLibrary.Gun
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Gun, objtypeid) Then  'Gun
        'SelGun = Linqdata.(ObjID)
        GunPresent = True
        End If

        'more tests required. Need to test for personnel present and not already loaded (both personnel and gun)
        If Not GunPresent Then Exit Sub 'no gun; leave property values empty
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsUnhookGun
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'must be proper Gun present for this to show
        Dim GunPresent As Boolean = False
        Dim SelGun As DataClassLibrary.Gun
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Gun, objtypeid) Then  'Gun
        'SelGun = Linqdata.(ObjID)
        GunPresent = True
        End If

        'more tests required. Need to test for personnel present and not already loaded (both personnel and gun)
        If Not GunPresent Then Exit Sub 'no gun; leave property values empty
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsThrowSmoke
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        End Sub
        Public Sub ThrowSmokeCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim NoSmoke As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Hexnum As Integer = 0 : Dim ScenID As Integer = 0
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
        Dim TerrCheck = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : Hexnum = CInt(Selunit.hexnum) : ScenID = CInt(Selunit.Scenario)
        Else
        Exit Sub 'only infantry can throw smoke (Veh crews?); leave property values empty
        End If
        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(ScenID)
        'check for heavy wind or gusts
        If Scendet.WINDSPEED <> ConstantClassLibrary.ASLXNA.Weather.HeavyWinds And Scendet.WINDSPEED <> ConstantClassLibrary.ASLXNA.Weather.Gusts Then
        If CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.Smoke, CInt(Selunit.LOBLink))) > 0 Then
        NoSmoke = False
        'this is where need to add next menu level - to hold possible smoke targets
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        Dim SmokeAllowed As MenuItemsThrowSmoke : Dim AdjHex As Integer = 0 : Dim Hexside As Integer = 0 : Dim LevelString As String = ""
        'test for current hex
        Dim Startlevel As Single = LevelChk.GetLocationPositionLevel(CInt(Selunit.hexnum), CInt(Selunit.hexlocation), CInt(Selunit.Position))
        Select Case Startlevel
        Case -1
        LevelString = " Cellar"
        Case 0
        LevelString = " Ground"
        Case Is > 0
        LevelString = "Level" & Startlevel.ToString
        End Select
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)
        If TerrCheck.IsSmokeAllowed(Hexnum) Then
        SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        SmokeAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeSame
        SmokeAllowed.ActivityNames = GetLocs.GetnamefromdatatableMap(Hexnum) & LevelString
        ActivityChoices.Add(SmokeAllowed)
        End If
        Dim ADJLocs = New List(Of MapDataClassLibrary.GameLocation) : Dim DirTest As Integer = 0
        Dim Passhexandloc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromHex(Hexnum, CInt(Selunit.hexlocation))
        Dim ADJTest As New CombatTerrainClassLibrary.ASLXNA.HexBesideC(Passhexandloc, Nothing, CInt(Selunit.Position))
        ADJLocs = ADJTest.AllADJACENTLocations()
        If Not IsNothing(ADJLocs) Then
        For Each ADJLocation As MapDataClassLibrary.GameLocation In ADJLocs
        SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        If Hexnum = ADJLocation.Hexnum Then 'same hex, do up/down checks
        'set position to zero as not important for this check
        Dim LocChecklevel As Single = LevelChk.GetLevelofLocation(ADJLocation.LocIndex)
        If Startlevel > LocChecklevel Then
        SmokeAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeDown)
        SmokeAllowed.ActivityNames = "Below"
        Else
        SmokeAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeUp)
        SmokeAllowed.ActivityNames = "Above"
        End If
        ActivityChoices.Add(SmokeAllowed)
        If Startlevel >= 2 Then 'can throw to ground level if not interior buildinghex
        If Not TerrChk.IsLocationTerrainA(Hexnum, ADJLocation.Location, ConstantClassLibrary.ASLXNA.Location.IntBuildtype) Then
        'Dim GetBaseHex = Game.Scenario.Maptables.RetrieveHexfromDatatable(OH.Hexnum)
        'With GetBaseHex
        SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        SmokeAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeGround
        SmokeAllowed.ActivityNames = "To Ground Level"
        ActivityChoices.Add(SmokeAllowed)
        'End With
        End If
        End If
        Else 'adjacent hex
        'get direction
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)  'Mapgeo.MapBtype, Mapgeo.Xoffset, Mapgeo.Yoffset, Mapgeo.MaxCols, Mapgeo.MaxRows)
        DirTest = MapGeo.HexSideEntry(ADJLocation.Hexnum, Hexnum)
        Dim FinishHexNumber = MapGeo.DirExtent(Hexnum, DirTest, 1)

        If TerrCheck.AdjSmokeOK(Hexnum, DirTest, FinishHexNumber, CInt(Scendet.WINDSPEED), CInt(Scendet.WindHexGrain)) Then
        SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        SmokeAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ThrowSmoke * 10) + DirTest
        Dim AdjHexnum As Integer = MapGeo.DirExtent(Hexnum, DirTest, 1)
        SmokeAllowed.ActivityNames = GetLocs.GetnamefromdatatableMap(AdjHexnum)
        ActivityChoices.Add(SmokeAllowed)
        End If
        End If
        Next
        End If
        'NEED TO TEST FOR ADJACENT LOCATIONS IN SAME HEX SUCH AS UP/DOWN STAIRS
        'if found one possible thrower; don't need to look for more
        End If
        End If
        If NoSmoke Then Exit Sub 'no possibility to throw smoke; leave property values empty


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsChooseGuard
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Prisonersize As Integer, ByVal PossibleGuard As ObjectClassLibrary.ASLXNA.PersUniti, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        'Dim NoSmoke As Boolean = True
        'Dim Selunit As DataClassLibrary.OrderofBattle
        'Dim Hexnum As Integer = 0 : Dim ScenID As Integer = 0
        'Dim NewMap = New NewMapDB
        'Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        'Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        'Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
        'Dim TerrCheck = New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
        'Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        '    Selunit = Linqdata.GetUnitfromCol(ObjID) : Hexnum = CInt(Selunit.hexnum) : ScenID = CInt(Selunit.Scenario)
        'Else
        '    Exit Sub 'only infantry can throw smoke (Veh crews?); leave property values empty
        'End If
        'Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(ScenID)
        ''check for heavy wind or gusts
        'If Scendet.WINDSPEED <> ConstantClassLibrary.ASLXNA.Weather.HeavyWinds And Scendet.WINDSPEED <> ConstantClassLibrary.ASLXNA.Weather.Gusts Then
        '    If CInt(Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.Smoke, CInt(Selunit.LOBLink))) > 0 Then
        '        NoSmoke = False
        '        'this is where need to add next menu level - to hold possible smoke targets
        '        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        '        Dim SmokeAllowed As MenuItemsThrowSmoke : Dim AdjHex As Integer = 0 : Dim Hexside As Integer = 0 : Dim LevelString As String = ""
        '        'test for current hex
        '        Dim Startlevel As Single = LevelChk.GetLocationPositionLevel(CInt(Selunit.hexnum), CInt(Selunit.hexlocation), CInt(Selunit.Position))
        '        Select Case Startlevel
        '            Case -1
        '                LevelString = " Cellar"
        '            Case 0
        '                LevelString = " Ground"
        '            Case Is > 0
        '                LevelString = "Level" & Startlevel.ToString
        '        End Select
        '        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)
        '        If TerrCheck.IsSmokeAllowed(Hexnum) Then
        '            SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        '            SmokeAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeSame
        '            SmokeAllowed.ActivityNames = GetLocs.GetnamefromdatatableMap(Hexnum) & LevelString
        '            ActivityChoices.Add(SmokeAllowed)
        '        End If
        '        Dim ADJLocs = New List(Of MapDataClassLibrary.GameLocation) : Dim DirTest As Integer = 0
        '        Dim Passhexandloc As MapDataClassLibrary.GameLocation = GetLocs.RetrieveLocationfromHex(Hexnum, CInt(Selunit.hexlocation))
        '        Dim ADJTest As New CombatTerrainClassLibrary.ASLXNA.HexBesideC(Passhexandloc, Nothing, CInt(Selunit.Position))
        '        ADJLocs = ADJTest.AllADJACENTLocations()
        '        If Not IsNothing(ADJLocs) Then
        '            For Each ADJLocation As MapDataClassLibrary.GameLocation In ADJLocs
        '                SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        '                If Hexnum = ADJLocation.Hexnum Then 'same hex, do up/down checks
        '                    'set position to zero as not important for this check
        '                    Dim LocChecklevel As Single = LevelChk.GetLevelofLocation(ADJLocation.LocIndex)
        '                    If Startlevel > LocChecklevel Then
        '                        SmokeAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeDown)
        '                        SmokeAllowed.ActivityNames = "Below"
        '                    Else
        '                        SmokeAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeUp)
        '                        SmokeAllowed.ActivityNames = "Above"
        '                    End If
        '                    ActivityChoices.Add(SmokeAllowed)
        '                    If Startlevel >= 2 Then 'can throw to ground level if not interior buildinghex
        '                        If Not TerrChk.IsLocationTerrainA(Hexnum, ADJLocation.Location, ConstantClassLibrary.ASLXNA.Location.IntBuildtype) Then
        '                            'Dim GetBaseHex = Game.Scenario.Maptables.RetrieveHexfromDatatable(OH.Hexnum)
        '                            'With GetBaseHex
        '                            SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        '                            SmokeAllowed.Activities = ConstantClassLibrary.ASLXNA.UMove.ThrowSmokeGround
        '                            SmokeAllowed.ActivityNames = "To Ground Level"
        '                            ActivityChoices.Add(SmokeAllowed)
        '                            'End With
        '                        End If
        '                    End If
        '                Else 'adjacent hex
        '                    'get direction
        '                    Dim MapGeo = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0)  'Mapgeo.MapBtype, Mapgeo.Xoffset, Mapgeo.Yoffset, Mapgeo.MaxCols, Mapgeo.MaxRows)
        '                    DirTest = MapGeo.HexSideEntry(ADJLocation.Hexnum, Hexnum)
        '                    Dim FinishHexNumber = MapGeo.DirExtent(Hexnum, DirTest, 1)

        '                    If TerrCheck.AdjSmokeOK(Hexnum, DirTest, FinishHexNumber, CInt(Scendet.WINDSPEED), CInt(Scendet.WindHexGrain)) Then
        '                        SmokeAllowed = New MenuItemsThrowSmoke(objtypeid, ObjID, MenuItem)
        '                        SmokeAllowed.Activities = (ConstantClassLibrary.ASLXNA.UMove.ThrowSmoke * 10) + DirTest
        '                        Dim AdjHexnum As Integer = MapGeo.DirExtent(Hexnum, DirTest, 1)
        '                        SmokeAllowed.ActivityNames = GetLocs.GetnamefromdatatableMap(AdjHexnum)
        '                        ActivityChoices.Add(SmokeAllowed)
        '                    End If
        '                End If
        '            Next
        '        End If
        '        'NEED TO TEST FOR ADJACENT LOCATIONS IN SAME HEX SUCH AS UP/DOWN STAIRS
        '        'if found one possible thrower; don't need to look for more
        '    End If
        'End If
        'If NoSmoke Then Exit Sub 'no possibility to throw smoke; leave property values empty
        If PossibleGuard.BasePersUnit.CanGuard(PrisonerSize) Then
        MenuItem.Activities = PossibleGuard.BasePersUnit.Unit_ID  'ConstantClassLibrary.ASLXNA.PersUnitResult.Surrenders
        End If

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Sub New(ByVal Prisoner As ObjectClassLibrary.ASLXNA.PersUniti, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'first item - the prisoner
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsClaimWallAdv
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim NoWallA As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Hexnum As Integer = 0
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : Hexnum = CInt(Selunit.hexnum)
        Else
        Exit Sub
        End If
        For i = 1 To 6
        Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then
        NoWallA = False : Exit For
        End If
        Next
        If NoWallA Then Exit Sub 'no eligible hexside; leave property values empty
        'NEED TO ADD STATUS CHECKS - this is just one; needs more
        If Selunit.Position = ConstantClassLibrary.ASLXNA.AltPos.WallAdv Or (Selunit.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selunit.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Then Exit Sub
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsCreateTHHero
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsFireLane
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsReactionFire
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSnapshot
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSearch
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsScrounge
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        'NEEDS MORE April 2012
        Dim SelVehicle As DataClassLibrary.AFV
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Vehicle, objtypeid) Then  'vehicle
        SelVehicle = Linqdata.GetVehiclefromCol(ObjID)
        Else
        Exit Sub  'no vehicle found; leave property values empty
        End If
        'ADD CHECK FOR RIDERS/PASSENGERS and status
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsBerserkToGo
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsEnterVehicle
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'must be proper vehicle present for this to show
        Dim VehicleOK As Boolean = True
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim selcon As DataClassLibrary.Concealment
        Dim Hexnum As Integer = 0 : Dim VehLIst As IQueryable(Of DataClassLibrary.AFV) : Dim ScenID As Integer = 0
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : Hexnum = CInt(Selunit.hexnum) : ScenID = CInt(Selunit.Scenario)
        If Selunit.Position = ConstantClassLibrary.ASLXNA.AltPos.Passenger Or Selunit.Position = ConstantClassLibrary.ASLXNA.AltPos.Rider Then Exit Sub 'unit is already in a vehicle
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        selcon = Linqdata.GetConcealmentfromCol(ObjID) : Hexnum = selcon.hexnum : ScenID = selcon.Scenario
        If selcon.Position = ConstantClassLibrary.ASLXNA.AltPos.Passenger Or selcon.Position = ConstantClassLibrary.ASLXNA.AltPos.Rider Then Exit Sub 'con is already in a vehicle
        Else
        Exit Sub
        End If
        'Get Vehicle if exists
        Try
        VehLIst = Linqdata.GetVehiclesInHex(Hexnum)
        Catch
        Exit Sub 'no vehicles found in hex; leave property values empty
        End Try
        If VehLIst.Count = 0 Then Exit Sub ' no vehicles found; leave property values empty
        'check veh type
        For Each Selvehicle As DataClassLibrary.AFV In VehLIst
        Dim VehPP As Integer = CInt(Linqdata.GetLOBVehData(ConstantClassLibrary.ASLXNA.LOBVeh.PP, CInt(Selvehicle.AFVDefaultsID)))
        If VehPP = 0 Then  'can't have passengers, check for riders
        'date - must be after 41 for Russian, after 42 for all others
        Dim Scendet As DataClassLibrary.scen = Linqdata.GetScenarioData(ScenID)
        Dim Scendate As Date = CDate(Scendet.DATETIME)
        If Selvehicle.OwnerNationality = ConstantClassLibrary.ASLXNA.Nationality.Russians And Scendate.Year < 1942 Then
        VehicleOK = False
        ElseIf Selvehicle.OwnerNationality <> ConstantClassLibrary.ASLXNA.Nationality.Russians And Scendate.Year < 1943 Then
        VehicleOK = False
        End If
        If VehicleOK Then  'still eligible to show menu item
        Dim Typestring As String = Linqdata.GetLOBVehData(ConstantClassLibrary.ASLXNA.LOBVeh.VehType, CInt(Selvehicle.AFVDefaultsID))
        Select Case Typestring
        Case "LT", "MT", "HT", "TD", "SPA", "AG", "CA", "tr"
        ' do nothing
        Case Else
        VehicleOK = False 'no riders possible
        End Select
        End If
        If Selvehicle.PPUsing >= 14 Then VehicleOK = False 'vehicle is loaded with riders
        Else
        If Selvehicle.PPUsing >= VehPP Then VehicleOK = False 'vehicle is loaded passengers
        End If
        'veh status
        If Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.Vmove.Motion Or Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.Vmove.Moving Then VehicleOK = False
        If Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.Burning Or Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.BurntOutWreck Or
        Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.KIAVeh Or Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.VDestroyed Or
        Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.Wreck Or Selvehicle.VehicleStatus = ConstantClassLibrary.ASLXNA.VStatus.Recall Then VehicleOK = False
        If VehicleOK Then Exit For 'if find one valid vehicle then can show
        Next
        If Not VehicleOK Then Exit Sub 'no valid vehicle present; leave property values empty

        'NEED TO ADD CHECK FOR unit status
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then unitsel = False


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsManhandle
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'must be proper Gun present for this to show
        Dim GunPresent As Boolean = False
        Dim SelGun As DataClassLibrary.Gun
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Gun, objtypeid) Then  'Gun
        'SelGun = Linqdata.(ObjID)
        GunPresent = True
        End If

        'more tests required. Need to test for personnel present and not already loaded (both personnel and gun)
        If Not GunPresent Then Exit Sub 'no gun; leave property values empty
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsFreedomOfMovement
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)



        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsExitVehicle
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        If Selunit.Position <> ConstantClassLibrary.ASLXNA.AltPos.Passenger And Selunit.Position <> ConstantClassLibrary.ASLXNA.AltPos.Rider Then Exit Sub 'unit is not in a vehicle so can't exit
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        If Selcon.Position <> ConstantClassLibrary.ASLXNA.AltPos.Passenger And Selcon.Position <> ConstantClassLibrary.ASLXNA.AltPos.Rider Then Exit Sub 'con is not in a vehicle so can't exit
        Else
        Exit Sub
        End If

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRoadBonus
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
        Dim Selunit As ObjectClassLibrary.ASLXNA.PersUniti  'DataClassLibrary.OrderofBattle
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry

        Selunit = (From selectunit As ObjectClassLibrary.ASLXNA.PersUniti In Scencolls.Unitcol Where selectunit.BasePersUnit.Unit_ID = ObjID Select selectunit).First   'linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'test for manhole
        If Not (TerrChk.IsLocationTerrainA(CInt(Selunit.BasePersUnit.LOCIndex), ConstantClassLibrary.ASLXNA.Location.Roadtype)) Then Exit Sub 'no manhole present so leave properties empty
        'test for status allows
        If Not (Selunit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Moving Or Selunit.BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.NotMoving) Then Exit Sub 'Unit has already done MOvement activity
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsHumanWave
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsBanzai
        Implements MenuItemObjectholderinteface
        Sub New(ByVal Objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim Selunit As DataClassLibrary.OrderofBattle
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, ObjTypeID) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Else
        Exit Sub
        End If
        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRecombine
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDeploy
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRepairSW
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRepairGun
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsTransferSW
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsTransferGun
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRallyAttempt
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsVolBreak
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDM
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsRoutElim
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSurrender
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsAssFire
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSewerEmerge
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsEscape
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsCaptureVehicle
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsWithdrawFromMelee
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsConceal
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsInterrogate
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsSmokeDisp
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        MsgBox("Got to Here")
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsAbandon
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsMechRel
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsBogRemove
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsWrkRemove
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsESB
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsHDMan
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsShockUK
        Implements MenuItemObjectholderinteface
        Sub New(ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsUpstairs
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        End Sub
        Public Sub UpstairsCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Stairs As Boolean = False : Dim Hexnum As Integer = 0 : Dim LocToCheck As Integer = 0 : Dim hexlocation As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Hexnum = CInt(Selunit.hexnum) : LocToCheck = CInt(Selunit.LocIndex) : hexlocation = CInt(Selunit.hexlocation)
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        Hexnum = Selcon.hexnum : LocToCheck = Selcon.LocIndex : hexlocation = Selcon.Hexlocation
        Else
        Exit Sub
        End If
        Stairs = If(TerrChk.IsLocationTerrainA(LocToCheck, ConstantClassLibrary.ASLXNA.Location.HasStairs), True, False)
        If Not Stairs Then Exit Sub 'if no stairs then leave properties empty
        'nothing in STatusPrevents re downstairs, upstairs yet, can add later
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'check for at top
        Dim UpOK As Boolean = True
        If LevelChk.Attop(Hexnum, LocToCheck) Then UpOK = False

        If UpOK Then
        'check for WA possibility if moving to ground floor
        Dim NoWallA As Boolean = True : Dim HoldActivity As Integer = 0
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Select Case (hexlocation)
        'Case ConstantClassLibrary.ASLXNA.Location.Building1stLevel
        '    Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        '    For i = 1 To 6
        '        Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        '        If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then NoWallA = False : Exit For
        '    Next
        '    HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.Downstairs
        Case ConstantClassLibrary.ASLXNA.Location.Cellar
        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        For i = 1 To 6
        Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then NoWallA = False : Exit For
        Next
        HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.UpStairs
        'Case Else
        '    If hexlocation = ConstantClassLibrary.ASLXNA.Location.Roof And TerrChk.IsLocationTerrainA(LocToCheck, ConstantClassLibrary.ASLXNA.Location.Factorytype) Then
        '        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        '        For i = 1 To 6
        '            Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        '            If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then NoWallA = False : Exit For
        '        Next
        '        HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.Downstairs
        '    End If
        End Select
        If Not NoWallA Then
        If HoldActivity = MenuItem.Activities Then
        'this is where need to add next menu level - to hold WA or building options
        'Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        Dim menuoptions = New MenuItemsUpstairs(objtypeid, ObjID, MenuItem)
        menuoptions.Activities = HoldActivity
        menuoptions.ActivityNames = "Ground Level"
        ActivityChoices.Add(menuoptions)
        menuoptions = New MenuItemsUpstairs(objtypeid, ObjID, MenuItem)
        If HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.UpStairs Then menuoptions.Activities = ConstantClassLibrary.ASLXNA.UMove.StairsUpWA
        menuoptions.ActivityNames = "WA at Ground Level"
        ActivityChoices.Add(menuoptions)
        End If
        End If
        End If

        'all good so set property values
        If ActivityChoices.Count > 0 Then Activities = 0 Else Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsDownstairs
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        End Sub
        Public Sub DownstairsCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim Stairs As Boolean = False : Dim Hexnum As Integer = 0 : Dim LocToCheck As Integer = 0 : Dim hexlocation As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        Hexnum = CInt(Selunit.hexnum) : LocToCheck = CInt(Selunit.LocIndex) : hexlocation = CInt(Selunit.hexlocation)
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        Hexnum = Selcon.hexnum : LocToCheck = Selcon.LocIndex : hexlocation = Selcon.Hexlocation
        Else
        Exit Sub
        End If
        Stairs = If(TerrChk.IsLocationTerrainA(LocToCheck, ConstantClassLibrary.ASLXNA.Location.HasStairs), True, False)
        If Not Stairs Then Exit Sub 'if no stairs then leave properties empty
        'nothing in STatusPrevents re downstairs, upstairs yet, can add later
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'check for at bottom
        Dim DownOK As Boolean = True
        If LevelChk.Atbottom(Hexnum, LocToCheck) Then DownOK = False

        If DownOK Then
        'check for WA possibility if moving to ground floor
        Dim NoWallA As Boolean = True : Dim HoldActivity As Integer = 0
        Dim MapGeo as mapgeoclasslibrary.aslxna.mapgeoc = MapGeoClassLibrary.ASLXNA.MapGeoC.GetInstance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Select Case (hexlocation)
        Case ConstantClassLibrary.ASLXNA.Location.Building1stLevel
        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        For i = 1 To 6
        Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then NoWallA = False : Exit For
        Next
        HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.Downstairs
        'Case ConstantClassLibrary.ASLXNA.Location.Cellar
        '    Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        '    For i = 1 To 6
        '        Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        '        If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then NoWallA = False : Exit For
        '    Next
        '    HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.UpStairs
        Case Else
        If hexlocation = ConstantClassLibrary.ASLXNA.Location.Roof And TerrChk.IsLocationTerrainA(LocToCheck, ConstantClassLibrary.ASLXNA.Location.Factorytype) Then
        Dim SideChk As New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        For i = 1 To 6
        Dim Otherhexnum As Integer = MapGeo.DirExtent(Hexnum, i, 1)
        If SideChk.IsWAAllowed(Hexnum, i, Otherhexnum) Then NoWallA = False : Exit For
        Next
        HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.Downstairs
        End If
        End Select
        If Not NoWallA Then
        If HoldActivity = MenuItem.Activities Then
        'this is where need to add next menu level - to hold WA or building options
        'Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        Dim menuoptions = New MenuItemsDownstairs(objtypeid, ObjID, MenuItem)
        menuoptions.Activities = HoldActivity
        menuoptions.ActivityNames = "Ground Level"
        ActivityChoices.Add(menuoptions)
        menuoptions = New MenuItemsDownstairs(objtypeid, ObjID, MenuItem)
        If HoldActivity = ConstantClassLibrary.ASLXNA.ContextM.Downstairs Then menuoptions.Activities = ConstantClassLibrary.ASLXNA.UMove.StairsDownWA
        'Else
        '    MenuOptions.Activities = ConstantClassLibrary.ASLXNA.UMove.StairsUpWA
        'End If
        menuoptions.ActivityNames = "WA at Ground Level"
        ActivityChoices.Add(menuoptions)
        End If
        End If
        End If

        'all good so set property values
        If ActivityChoices.Count > 0 Then Activities = 0 Else Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsMoveIn
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        'within hex move, must be in crest status or exitedcrest status
        Dim Crest As Boolean = True
        Dim Selitem As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantryv
        Selitem = Linqdata.GetUnitfromCol(ObjID)
        If (Selitem.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Selitem.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5) Or
        (Selitem.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selitem.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Or
        (Selitem.Position >= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest0 And Selitem.Position <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest5) Then
        'unit is in crest status or exitedcrest status so ok to show
        Else
        Crest = False
        End If
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        If (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5) Or
        (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Or
        (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest5) Then
        'unit is in crest status or exitedcrest status so ok to show
        Else
        Crest = False
        End If
        Else
        Exit Sub
        End If

        If Not (Crest) Then Exit Sub 'no crest found so can't move IN; leave property values empty
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsCrestStatus
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        End Sub
        Public Sub CrestStatusCheck(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim hexnum As Integer = 0 : Dim LoCtoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk = New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)   'class for various data-based terrain checks
        Dim LevelChk = New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID)
        hexnum = CInt(Selunit.hexnum) : LoCtoCheck = CInt(Selunit.LocIndex)
        If (Selunit.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Selunit.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5) Or
        (Selunit.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selunit.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Or
        (Selunit.Position >= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest0 And Selunit.Position <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest5) Then
        'already in crest status  or exited crest at level so don't show
        Exit Sub
        End If
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        hexnum = Selcon.hexnum : LoCtoCheck = Selcon.LocIndex
        If (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5) Or
        (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Or
        (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.ExitedCrest5) Then
        'already in crest status  or exited crest at level so don't show
        Exit Sub
        End If
        Else
        Exit Sub
        End If
        If Not TerrChk.IsLocationTerrainA(LoCtoCheck, ConstantClassLibrary.ASLXNA.Location.Creststatustype) Then Exit Sub 'can't go into crest status in this location; leave property values empty

        'NEED TO ADD STATUS CHECKS
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then
        '    RemoveList.Add(MenuItem) : Exit For
        'Else
        'this is where need to add next menu level - to hold possible crest sides
        MenuItem.Activities = 0 ' triggers conversion to popupcomponentc rather than popupleafc
        Dim SideTest = New TerrainClassLibrary.ASLXNA.IsSide(MapCol)
        For i As Integer = 1 To 6
        Dim Hexside As Integer = Linqdata.Gethexsidetype(i, hexnum)
        If SideTest.IsADepression(Hexside) Then
        Dim Creststatuspossible = New MenuItemsCrestStatus(objtypeid, ObjID, MenuItem) : Dim SideConvert As New UtilClassLibrary.ASLXNA.ConversionC
        Creststatuspossible.Activities = SideConvert.SideToCrest(i)
        Creststatuspossible.ActivityNames = i.ToString & ": " & SideTest.GetSideData(ConstantClassLibrary.ASLXNA.TerrFactor.Hexsidedesc, Hexside, MapTables)
        ActivityChoices.Add(Creststatuspossible)
        If SideTest.IsAWallHedgeRdBlk(i, LoCtoCheck) Then   'IsWAAllowed(OH.Hexnum, i) Then
        Dim CrestWApossible = New MenuItemsCrestStatus(objtypeid, ObjID, MenuItem)
        CrestWApossible.Activities = SideConvert.SideToWACrestposition(i)
        CrestWApossible.ActivityNames = i.ToString & ": " & "Enter Crest Status and Claim WA"
        ActivityChoices.Add(CrestWApossible) '("Enter Crest Status and WA")
        End If
        End If
        Next
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsExitCrestStatus
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        'within hex move, must be in crest status
        Dim Crest As Boolean = False
        Dim Selitem As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then  'infantryv
        Selitem = Linqdata.GetUnitfromCol(ObjID)
        If (Selitem.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Selitem.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5) Or
        (Selitem.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selitem.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Then
        Crest = True
        End If
        ElseIf Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid) Then  'conceal
        Selcon = Linqdata.GetConcealmentfromCol(ObjID)
        If (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.CrestStatus5) Or
        (Selcon.Position >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus0 And Selcon.Position <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus5) Then
        Crest = True
        End If

        Else
        Exit Sub
        End If
        If Not (Crest) Then Exit Sub 'not in crest so can't exit it; leave property values empty
        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsEnterFoxhole
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim hexnum As Integer = 0 : Dim LoCtoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(Mapcol)

        Dim FeaturePresent As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'can't enter feature if already in one (except wire)
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objTypeID) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : hexnum = CInt(Selunit.hexnum)
        If Selunit.IsOccupyingFeature Then FeaturePresent = False
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : hexnum = CInt(Selcon.hexnum)
        If Selcon.IsOccupyingFeature Then FeaturePresent = False
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        If Not FeaturePresent Then Exit Sub 'meaning unit is in an obstacle; must exit first; leave property values empty
        'now check if there is a feature to enter
        Dim MapHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        If MapHex.Entrenchment = ConstantClassLibrary.ASLXNA.Feature.Foxhole Then 'feature present
        'now check if unit is eligible to enter (not pinned, TI, etc)
        'NEED TO ADD STATUS CHECK
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)
        Else
        Exit Sub 'no foxhole present; leave properties empty
        End If

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsEnterTrench
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim hexnum As Integer = 0 ': Dim LoCtoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)

        Dim FeaturePresent As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'can't enter feature if already in one (except wire)
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objTypeID) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : hexnum = CInt(Selunit.hexnum)
        If Selunit.IsOccupyingFeature Then FeaturePresent = False
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : hexnum = CInt(Selcon.hexnum)
        If Selcon.IsOccupyingFeature Then FeaturePresent = False
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        If Not FeaturePresent Then Exit Sub 'meaning unit is in an obstacle; must exit first; leave property values empty
        'now check if there is a feature to enter
        Dim MapHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        If MapHex.Entrenchment = ConstantClassLibrary.ASLXNA.Feature.Trench Then 'feature present
        'now check if unit is eligible to enter (not pinned, TI, etc)
        'NEED TO ADD STATUS CHECK
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)
        Else
        Exit Sub 'no foxhole present; leave properties empty
        End If

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsEnterPillbox
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        Dim hexnum As Integer = 0 ': Dim LoCtoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)
        Dim FeaturePresent As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'can't enter feature if already in one (except wire)
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : hexnum = CInt(Selunit.hexnum)
        If Selunit.hexlocation >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And Selunit.hexlocation <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
        'unit location is a pillbox or a so can't enter another one
        FeaturePresent = False
        ElseIf Selunit.IsOccupyingFeature Then
        FeaturePresent = False
        End If
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : hexnum = CInt(Selcon.hexnum)
        If Selcon.Hexlocation >= ConstantClassLibrary.ASLXNA.Location.Pill1571 And Selcon.Hexlocation <= ConstantClassLibrary.ASLXNA.Location.Bombprf Then
        'unit location is a pillbox or a so can't enter another one
        FeaturePresent = False
        ElseIf Selcon.IsOccupyingFeature Then
        FeaturePresent = False
        End If
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        If Not FeaturePresent Then Exit Sub 'meaning unit is in an obstacle; leave property values empty
        'now check if there is a feature to enter
        Dim MapHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        Dim LocHolder As Integer = 0
        Dim TerrTest As New TerrainClassLibrary.ASLXNA.TerrainChecks(MapCol)
        FeaturePresent = TerrTest.IsLocationPresent(hexnum, ConstantClassLibrary.ASLXNA.Location.Pillboxtype, LocHolder)
        If Not FeaturePresent Then Exit Sub 'no pillbox in hex; leave property values empty
        'NEED TO ADD STATUS CHECK
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsBelowWire
        Implements MenuItemObjectholderinteface
        Sub New(ByVal OBJTYPEID As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim hexnum As Integer = 0 ': Dim LoCtoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim LevelChk As New TerrainClassLibrary.ASLXNA.LevelChecks(MapCol)

        Dim FeaturePresent As Boolean = True
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        'can't enter feature if already in one (except wire)
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objTypeID) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : hexnum = CInt(Selunit.hexnum)
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, OBJTYPEID)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : hexnum = Selcon.hexnum
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        Dim MapHex As MapDataClassLibrary.GameLocation = LevelChk.GetLocationatLevelInHex(hexnum, 0)
        'check if there is a wire feature to enter
        If Not (MapHex.IsWire And MapHex.WireVisible) Then Exit Sub 'no wire exists or can be seen; leave property values empty
        'NEED TO ADD STATUS CHECK
        'check unit status does not prevent
        'If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsExitPillbox
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim LoCtoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        'must be in location for this to show
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk As New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : LoCtoCheck = CInt(Selunit.LocIndex)
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : LoCtoCheck = CInt(Selcon.LocIndex)
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        If Not TerrChk.IsLocationTerrainA(LoCtoCheck, ConstantClassLibrary.ASLXNA.Location.Pillboxtype) Then Exit Sub 'not in a pillbox so can't leave it; leave property values empty

        'NEED TO ADD STATUS CHECK
        '   If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)

        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsChangeTerrainInLocation
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)

        'handles within location moves such as in and out of shellholes
        'WHAT OTHER MOVES NEED TO BE HANDLED?
        Dim REmoveYes As Boolean = True : Dim BaseLocIsShellhole As Boolean = True
        Dim hexnum As Integer = 0 : Dim Hexloc As Integer = 0 : Dim LOCTocheck As Integer = 0 : Dim LOCToUse As MapDataClassLibrary.GameLocation
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk As New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
        Dim GetLocs As New TerrainClassLibrary.ASLXNA.GetALocationFromMapTable(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : hexnum = CInt(Selunit.hexnum) : Hexloc = CInt(Selunit.hexlocation) : LOCTocheck = CInt(Selunit.LocIndex)
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : hexnum = Selcon.hexnum : Hexloc = Selcon.Hexlocation : LOCTocheck = CInt(Selcon.LocIndex)
        Else
        Exit Sub
        End If
        LOCToUse = GetLocs.RetrieveLocationfromHex(LOCTocheck)
        'shellholes
        If TerrChk.IsLocationTerrainA(hexnum, Hexloc, ConstantClassLibrary.ASLXNA.Location.Shellholetype) Then
        'menuitem should display
        REmoveYes = False
        If (LOCToUse.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.Shellhole) Or (LOCToUse.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.CitySquareShellhole) Or (LOCToUse.OtherTerraininLocation = ConstantClassLibrary.ASLXNA.Location.CitySquareManShell) Then BaseLocIsShellhole = False
        End If
        If REmoveYes Then Exit Sub
        'NEED TO ADD STATUS CHECK
        'ElseIf Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then

        'change menu item to reflect situation

        If Hexloc = LOCToUse.Location Then  'in location
        MenuItem.Activities = ConstantClassLibrary.ASLXNA.UMove.ExitTerrain
        If BaseLocIsShellhole Then  'exiting shellholes
        MenuItem.ActivityNames = "Exit Shellholes"
        Else  'entering shellholes
        MenuItem.ActivityNames = "Enter Shellholes"
        End If
        ElseIf Hexloc = LOCToUse.OtherTerraininLocation Then 'in other terrain
        MenuItem.Activities = ConstantClassLibrary.ASLXNA.UMove.EnterTerrain
        If BaseLocIsShellhole Then 'exiting
        MenuItem.ActivityNames = "Enter Shellholes"
        Else 'entering
        MenuItem.ActivityNames = "ExitShellholes"
        End If
        End If


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        For Each ActChoice As DataClassLibrary.ASLXNA.Objectholder In MenuItem.ActivityChoices
        ActivityChoices.Add(CType(ActChoice, ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface))
        Next
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsExitFoxhole
        Implements MenuItemObjectholderinteface
        Sub New(ByVal OBjtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim POstoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        'must be in location for this to show
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk As New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : POstoCheck = CInt(Selunit.LocIndex)
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : POstoCheck = CInt(Selcon.LocIndex)
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        If Not POstoCheck = ConstantClassLibrary.ASLXNA.AltPos.InFoxhole Then Exit Sub 'not in a foxhole so can't leave it; leave property values empty

        'NEED TO ADD STATUS CHECK
        '   If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames

        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class
        Public Class MenuItemsExitTrench
        Implements MenuItemObjectholderinteface
        Sub New(ByVal objtypeid As Integer, ByVal ObjID As Integer, ByVal MenuItem As DataClassLibrary.ASLXNA.Objectholder)
        Dim POstoCheck As Integer = 0
        Dim Selunit As DataClassLibrary.OrderofBattle : Dim Selcon As DataClassLibrary.Concealment
        'must be in location for this to show
        Dim MapTables = MapDataClassLibrary.ASLXNA.MapDataC.GetInstance("", 0)
        Dim NewMap = New NewMapDB
        Dim MapCol As IQueryable(Of MapDataClassLibrary.GameLocation) = NewMap.GetMapCol
        Dim TerrChk As New TerrainClassLibrary.ASLXNA.IsTerrain(MapCol)
        Dim Linqdata As DataClassLibrary.ASLXNA.DataC = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use empty variables when know that instance already exists
        If Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Personnel, objtypeid) Then    'infantry
        Selunit = Linqdata.GetUnitfromCol(ObjID) : POstoCheck = CInt(Selunit.LocIndex)
        ElseIf (Linqdata.IsThingATypeOf(ConstantClassLibrary.ASLXNA.Typetype.Concealment, objtypeid)) Then  'concealed
        Selcon = Linqdata.GetConcealmentfromCol(ObjID) : POstoCheck = CInt(Selcon.LocIndex)
        Else
        Exit Sub 'not checking a unit or con - only they can enter foxhole; leave properties empty
        End If
        If Not POstoCheck = ConstantClassLibrary.ASLXNA.AltPos.InTrench Then Exit Sub 'not in a foxhole so can't leave it; leave property values empty

        'NEED TO ADD STATUS CHECK
        '   If Game.Scenario.Moveobsi.StatusPrevents(MenuItem.Activities) Then RemoveList.Add(MenuItem)


        'all good so set property values
        Activities = MenuItem.Activities
        ActivityNames = MenuItem.ActivityNames
        End Sub
        Public Property Activities As Integer Implements MenuItemObjectholderinteface.Activities
        Public Property ActivityNames As String Implements MenuItemObjectholderinteface.ActivityNames
        Public Property ActivityChoices As New List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface) Implements MenuItemObjectholderinteface.ActivityChoices
        End Class

        Public Class Objectholder
        Public Property Activities As Integer
        Public Property ActivityNames As String
        Public ActivityChoices As List(Of Objectholder)
        End Class
        Public Class SelectedThing
        'holds information about items (units, vehicles, guns, SW, ? and terrain) right-selected for popup display
        'one object per item, held in collection SelectedThings
        'constructor
        Private typevalue As Integer
        Private itemvalue As Integer
        Private phasevalue As Integer
        Private attdefvalue As Integer
        Public Sub New(ByVal Phase As Integer, ByVal WhatClicked As Integer, ByVal AttorDef As Integer, ByVal TypeClicked As Integer)
        phasevalue = Phase
        itemvalue = WhatClicked
        attdefvalue = AttorDef
        typevalue = TypeClicked
        End Sub
        Public ReadOnly Property TypeClicked As Integer
        Get
        Return typevalue
        End Get
        End Property
        Public ReadOnly Property WhatClicked As Integer
        Get
        Return itemvalue
        End Get
        End Property
        Public ReadOnly Property AttorDef As Integer
        Get
        Return attdefvalue
        End Get
        End Property
        Public ReadOnly Property Phase As Integer
        Get
        Return phasevalue
        End Get
        End Property
        End Class

        // Personel Unit and SW object creation
        /* This region includes Interfaces and classes to implement a structure for all personnel units in the game, using Option 1 from my December 1, 2013 email

        In this model, a single unit interface is used, which contains properties which are classes
        This interface is implemented by a single concrete class for each possible unit
        Different variations (circumstances or capabilities) of those units are implemented via decorator classes that wrap the concrete classes
        Also includes a single instance class to hold all of the collections of these units.
        */

    // collections class
public class ScenarioCollectionsc {

    // properties
    private static ScenarioCollectionsc Colinstance = null;
    public LinkedList<PersUniti> Unitcol = new LinkedList<PersUniti>();
    public LinkedList<PersUniti> SelMoveUnits = new LinkedList<PersUniti>();
    public LinkedList<SuppWeapi> SWCol = new LinkedList<SuppWeapi>();
    public LinkedList<Unpossessed> Unpossesseds = new LinkedList<Unpossessed>();
    public LinkedList<Vehiclesi> Vehcol = new LinkedList<Vehiclesi>();
    public LinkedList<ThingsToDo> ToDocol = new LinkedList<ThingsToDo>();

    // constructor; class uses singleton pattern to ensure only one instance ever exists
    private ScenarioCollectionsc() {
        // Exists only to defeat instantiation.
    }

    public static ScenarioCollectionsc getInstance() {
        if(Colinstance == null) {
            Colinstance = new ScenarioCollectionsc();
        }
        return Colinstance;
    }

    public void ProcessBaseUnitUpdate(UpdateBaseunitiCommand PassCommand ){

    }

    public void ProcessFireUnitUpdate(UpdateFireunitiCommand PassCommand){

        ConversionC confrom = new ConversionC();
        int UpdateUnitID = PassCommand.myOBLink;
        PersUniti BaseUnit = FindInCollection (UpdateUnitID);
        if (BaseUnit != null){
            FiringPersUniti UpdateFirer = BaseUnit.getFiringunit();
            if (UpdateFirer != null) {
                // firer unit already exists
            } else {
                // create Firerpersuniti property
                PersCreation ObjCreate = new PersCreation();
                BaseUnit = ObjCreate.CreatefiringUnitandProperty(BaseUnit);
                UpdateFirer = BaseUnit.getFiringunit();
                UpdateFirer.setUseHeroOrLeader(confrom.ConverttoUnitType(PassCommand.myUseHeroOrLeader));
                UpdateFirer.setIsPinned(PassCommand.myIsPinned);
                UpdateFirer.setUsingInherentFP(PassCommand.myUsingInherentFP);
                UpdateFirer.setUsingfirstMG(PassCommand.myUsingfirstMG);
                UpdateFirer.setUsingsecondMG(PassCommand.myUsingsecondMG);
                UpdateFirer.setIsCX(PassCommand.myCX);
                UpdateFirer.setIsEncirc(PassCommand.myIsEncirc);
                UpdateFirer.setCombatFP(PassCommand.myCombatFP);
                UpdateFirer.setHasMG(PassCommand.myHasMG);
                //UpdateFirer.FiringMGs = PassCommand.myF
            }
            UpdateFirer.setCombatStatus(confrom.ConverttoCombatStatus(PassCommand.myCombatStatus));
            BaseUnit.getbaseunit().setCombatStatus(confrom.ConverttoCombatStatus(PassCommand.myCombatStatus));

            CounterActions counteractions = new CounterActions();
            counteractions.placefirecounter(BaseUnit);

        }
    }

    public void ProcessTargUnitUpdate(UpdateTargunitiCommand PassCommand){
        ConversionC confrom = new ConversionC();
        String UpdateUnitName = PassCommand.myName;
        PersUniti BaseUnit = FindNameInCollection (UpdateUnitName);
        if (BaseUnit != null){  // NOT WORKING FOR WOUNDED SMC AS KEEP SAME NAME
            if (BaseUnit.getTargetunit() != null) {
                // target unit already exists
            } else {
                // create Targetpersuniti property
                PersCreation ObjCreate = new PersCreation();
                BaseUnit = ObjCreate.CreateTargetUnitandProperty(BaseUnit, 0);
            }
            TargetPersUniti UpdateTarget = BaseUnit.getTargetunit();
            UpdateTarget.setCombatResultsString(PassCommand.myCombatResultsString);
            UpdateTarget.setFirerSan(PassCommand.myFirerSAN);
            UpdateTarget.setAttackedbydrm(PassCommand.myAttackedbydrm);
            UpdateTarget.setAttackedbyFP(PassCommand.myAttackedbyFP);
            UpdateTarget.setELR5(PassCommand.myELR5);
            UpdateTarget.setFortitudeStatus(confrom.ConverttoFortitudeStatus(PassCommand.myFortitudeStatus));
            UpdateTarget.setIFTResult(confrom.ConverttoIFTResult(PassCommand.myIFTResult));
            UpdateTarget.setIsConcealed(PassCommand.myIsConceal);
            UpdateTarget.setMovementStatus(confrom.ConverttoMovementStatus(PassCommand.myMovementStatus));
            UpdateTarget.setOrderStatus(confrom.ConverttoOrderStatus(PassCommand.myOrderStatus));
            UpdateTarget.setPinned(PassCommand.myPinned);
            UpdateTarget.setQualityStatus(PassCommand.myQualityStatus);
            UpdateTarget.setRandomSelected(PassCommand.myRandomSelected);
            UpdateTarget.setSmoke(PassCommand.mySmoke);
            UpdateTarget.setVisibilityStatus(confrom.ConverttoVisibilityStatus(PassCommand.myVisibilityStatus));
            UpdateTarget.setPersUnitImpact(confrom.ConverttoPersUnitResult(PassCommand.myPersUnitImpact));
            UpdateTarget.setSANActivated(PassCommand.mySanActivated);
            UpdateTarget.setIFTResolved(PassCommand.myIFTResolved);
            //UpdateTarget.setELR(PassCommand.myELR);
            //UpdateTarget.setName(PassCommand.myName);
            UpdateTarget.setHoBFlag(PassCommand.myHOBFlag);
            UpdateTarget.setMCNumber(PassCommand.myMCNum);
            UpdateTarget.setTargStackLeaderDRM(PassCommand.myTargSTackLdrdrm);

            CommonFunctionsC ToDO = new CommonFunctionsC(BaseUnit.getbaseunit().getScenario());
            GamePiece CounterToUse = ToDO.GetGamePieceFromID(BaseUnit.getbaseunit().getUnit_ID());

            if (CounterToUse != null) {
                // trigger counter action
                CounterActions counteractions = new CounterActions();
                counteractions.processcounter(UpdateTarget.getPersUnitImpact(), BaseUnit);
            }
        }
    }

    public void ProcessMoveUnitUpdate(UpdateMoveunitiCommand PassCommand){

    }

    private PersUniti FindInCollection (int UpdateUnitID){
        for (PersUniti FindBaseUnit: Unitcol) {
            if (FindBaseUnit.getbaseunit().getUnit_ID() == UpdateUnitID && FindBaseUnit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.NotInPlay) {
                return FindBaseUnit;
            }
        }
        return null;
    }
    private PersUniti FindNameInCollection (String UpdateUnitName){
        for (PersUniti FindBaseUnit: Unitcol) {
            if (FindBaseUnit.getbaseunit().getUnitName().equals(UpdateUnitName)) {
                return FindBaseUnit;
            }
        }
        return null;
    }
    public void ProcessFireSwUpdate(UpdateFireSWiCommand PassCommand){

        ConversionC confrom = new ConversionC();
        int UpdateSWID = PassCommand.mySWID;
        SuppWeapi swtoupdate = FindSWInCollection (UpdateSWID);
        if (swtoupdate != null){
            FiringSuppWeapi UpdateSWFirer = swtoupdate.getFiringSW();
            if (UpdateSWFirer != null) {
                // firer unit already exists
            } else {
                // create Firerpersuniti property
                SWCreation ObjCreate = new SWCreation();
                swtoupdate = ObjCreate.CreatefiringSwandProperty(swtoupdate);
                UpdateSWFirer = swtoupdate.getFiringSW();
                //UpdateSWFirer.set(PassCommand.myUseHeroOrLeader););

                // does this do everything necessary? May need to add more properties
            }
            UpdateSWFirer.setCombatStatus(confrom.ConverttoCombatStatus(PassCommand.myCombatStatus));
            swtoupdate.getbaseSW().setCombatStatus(confrom.ConverttoCombatStatus(PassCommand.myCombatStatus));

            CounterActions counteractions = new CounterActions();
            counteractions.placefirecounter(swtoupdate);

        }
    }
    public void ProcessBaseSWUpdate(UpdateBaseSWiCommand PassCommand){

        ConversionC confrom = new ConversionC();
        int UpdateSWID = PassCommand.myUnit_ID;
        SuppWeapi swtoupdate = FindSWInCollection (UpdateSWID);
        if (swtoupdate != null){
            BaseSuppWeapc UpdateBaseSW = swtoupdate.getbaseSW(); // BaseSuppWeapc MUST always exist

             // does this update all the necessary properties?

            //UpdateBaseSW.sethexlocation();
            //myScenario = PassObject.getbaseSW().getScenario();
            //myHexName = PassObject.getbaseSW().getHex().getName();
            //Constantvalues.Location locvalue = confrom.ConverttoLocationtypefromVASLLocation(PassObject.getbaseSW().gethexlocation());
            //myhexlocation = confrom.ConvertLocationTypetoint(locvalue);
            //myhexPosition = confrom.ConvertAltPosTypetoInt(PassObject.getbaseSW().gethexPosition());
            //myLevelinHex = PassObject.getbaseSW().getLevelinHex();
            //myCX = PassObject.getbaseSW().getCX();
            //myTurnArrives = PassObject.getbaseSW().getTurnArrives();
            //myNationality = confrom.ConvertNationalitytoInt(PassObject.getbaseSW().getNationality());
            //myCon_ID = PassObject.getbaseSW().getCon_ID();
            //myUnit_ID = PassObject.getbaseSW().getSW_ID();
            //myTypeType_ID = confrom.ConvertTypetypetoint(PassObject.getbaseSW().getType_ID());
            //myHexEntSideCrossed = PassObject.getbaseSW().getHexEntSideCrossed();
            //mySolID = PassObject.getbaseSW().getSolID();
            //myUnitName = PassObject.getbaseSW().getUnitName();
            //myLOBLink = PassObject.getbaseSW().getLOBLink();
            UpdateBaseSW.setMovementStatus((confrom.ConverttoMovementStatus(PassCommand.myMovementStatus)));
            UpdateBaseSW.setFortitudeStatus((confrom.ConverttoFortitudeStatus(PassCommand.myFortitudeStatus)));
            UpdateBaseSW.setSWStatus((confrom.ConverttoSWStatus(PassCommand.mySWStatus)));
            UpdateBaseSW.setVisibilityStatus((confrom.ConverttoVisibilityStatus(PassCommand.myVisibilityStatus)));
            UpdateBaseSW.setPinned(confrom.ConverttoBoolean(PassCommand.myPinned));
            UpdateBaseSW.setPP(PassCommand.myPP);
            UpdateBaseSW.setCharacterStatus((confrom.ConverttoCharacterStatus(PassCommand.myCharacterStatus)));
            //myRepair = PassObject.getbaseSW().getRepair();
            UpdateBaseSW.setDisPP(PassCommand.myDisPP);
            UpdateBaseSW.setCaptured(confrom.ConverttoBoolean(PassCommand.myCaptured));
            UpdateBaseSW.setOwner(PassCommand.myOwner);
            //myIsDC = PassObject.getbaseSW().IsDC();
            //myIsFT = PassObject.getbaseSW().IsFT();
            //myIsMG = PassObject.getbaseSW().IsMG();

            UpdateBaseSW.setCombatStatus(confrom.ConverttoCombatStatus(PassCommand.myCombatStatus));
            swtoupdate.getbaseSW().setCombatStatus(confrom.ConverttoCombatStatus(PassCommand.myCombatStatus));

            //CounterActions counteractions = new CounterActions();
            //counteractions.placefirecounter(swtoupdate);

        }
    }
    private SuppWeapi FindSWInCollection (int UpdateSWID){
        for (SuppWeapi FindSW: SWCol) {
            if (FindSW.getbaseSW().getSW_ID() == UpdateSWID && FindSW.getbaseSW().getSWStatus() != Constantvalues.SWStatus.Eliminated) {
                return FindSW;
            }
        }
        return null;
    }
}
