package VASL.build.module.fullrules.ObjectChangeClasses;

public class TransferSW {
    /*Implements UnitChange
    Private Gettingunit As ObjectClassLibrary.ASLXNA.PersUniti
    Private TransSW As ObjectClassLibrary.ASLXNA.SuppWeapi
    Private GivingUnit As ObjectClassLibrary.ASLXNA.PersUniti
    Private linqdata As DataClassLibrary.ASLXNA.DataC
    Private Scencolls As ObjectClassLibrary.ASLXNA.ScenarioCollectionsc = ObjectClassLibrary.ASLXNA.ScenarioCollectionsc.GetInstance
    Public Sub New(ByVal Addingunit As ObjectClassLibrary.ASLXNA.PersUniti, ByVal LosingUnit As ObjectClassLibrary.ASLXNA.PersUniti)
    linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()
    GivingUnit = LosingUnit
            Gettingunit = Addingunit
    End Sub
    Public Function TransferSW() As Boolean Implements UnitChange.TakeAction
            'called by
                    'transfers SW from one unit to another
                    'GivingUnit drops SW
    Dim FirstTransfer As Integer = GivingUnit.BasePersUnit.FirstSWLink : Dim SecondTransfer As Integer = GivingUnit.BasePersUnit.SecondSWlink
    If GivingUnit.BasePersUnit.FirstSWLink > 0 Then GivingUnit.BasePersUnit.FirstSWLink = 0
    If GivingUnit.BasePersUnit.SecondSWlink > 0 Then GivingUnit.BasePersUnit.SecondSWlink = 0
    GivingUnit.BasePersUnit.SW = 0
            'GettingUnit picks it up
    If Gettingunit.BasePersUnit.FirstSWLink = 0 And FirstTransfer <> 0 Then
    Gettingunit.BasePersUnit.FirstSWLink = FirstTransfer
            FirstTransfer = -1
    Gettingunit.BasePersUnit.SW += 1
    ElseIf Gettingunit.BasePersUnit.FirstSWLink = 0 And SecondTransfer <> 0 Then
    Gettingunit.BasePersUnit.FirstSWLink = SecondTransfer
            SecondTransfer = -1
    Gettingunit.BasePersUnit.SW += 1
    End If
    If Gettingunit.BasePersUnit.SecondSWlink = 0 And FirstTransfer <> 0 Then
    Gettingunit.BasePersUnit.SecondSWlink = FirstTransfer
            FirstTransfer = -1
    Gettingunit.BasePersUnit.SW += 1
    ElseIf Gettingunit.BasePersUnit.SecondSWlink = 0 And SecondTransfer <> 0 Then
    Gettingunit.BasePersUnit.SecondSWlink = SecondTransfer
            SecondTransfer = -1
    Gettingunit.BasePersUnit.SW += 1
    End If
            'update SW ownership and info
    If FirstTransfer = -1 Then
            TransSW = (From selsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where selsw.BaseSW.Unit_ID = Gettingunit.BasePersUnit.FirstSWLink).First
    TransSW.BaseSW.Owner = Gettingunit.BasePersUnit.Unit_ID
    TransSW.BaseSW.hexlocation = CShort(Gettingunit.BasePersUnit.hexlocation)
    TransSW.BaseSW.hexPosition = Gettingunit.BasePersUnit.hexPosition
    If TransSW.BaseSW.Nationality = Gettingunit.BasePersUnit.Nationality Then TransSW.BaseSW.Captured = False Else TransSW.BaseSW.Captured = True
                MessageBox.Show(Trim(GivingUnit.BasePersUnit.UnitName) & " transfers " & Trim(TransSW.BaseSW.UnitName) & " to " & Trim(Gettingunit.BasePersUnit.UnitName))
    End If
    If SecondTransfer = -1 Then
            TransSW = (From selsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where selsw.BaseSW.Unit_ID = Gettingunit.BasePersUnit.SecondSWlink).First
    TransSW.BaseSW.Owner = Gettingunit.BasePersUnit.Unit_ID
    TransSW.BaseSW.hexlocation = CShort(Gettingunit.BasePersUnit.hexlocation)
    TransSW.BaseSW.hexPosition = Gettingunit.BasePersUnit.hexPosition
    If TransSW.BaseSW.Nationality = Gettingunit.BasePersUnit.Nationality Then TransSW.BaseSW.Captured = False Else TransSW.BaseSW.Captured = True
                MessageBox.Show(Trim(GivingUnit.BasePersUnit.UnitName) & " transfers " & Trim(TransSW.BaseSW.UnitName) & " to " & Trim(Gettingunit.BasePersUnit.UnitName))
    End If
            'unpossess
    Dim UnpossSWcreate = New CreateUnpossessedSW
    If FirstTransfer > 0 Then
                'need to create unpossessed
    TransSW = (From selsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where selsw.BaseSW.Unit_ID = FirstTransfer).First
                UnpossSWcreate.CreateNewUnpossessed(TransSW, CInt(GivingUnit.BasePersUnit.Hexnum))
    TransSW.BaseSW.Owner = 0
    End If
    If SecondTransfer > 0 Then
                'need to create unpossessed
    TransSW = (From selsw As ObjectClassLibrary.ASLXNA.SuppWeapi In Scencolls.SWCol Where selsw.BaseSW.Unit_ID = SecondTransfer).First
                UnpossSWcreate.CreateNewUnpossessed(TransSW, CInt(GivingUnit.BasePersUnit.Hexnum))
    TransSW.BaseSW.Owner = 0
    End If
    Return True
    End Function*/
}
