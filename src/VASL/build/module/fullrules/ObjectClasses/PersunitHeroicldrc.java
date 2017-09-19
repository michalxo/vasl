package VASL.build.module.fullrules.ObjectClasses;

public class PersunitHeroicldrc { //extends PersunitDecoratorc {

    //private PersUniti BaseUnit As ObjectClassLibrary.ASLXNA.PersUniti Implements PersunitDecoratori.BaseUnit

    public PersunitHeroicldrc(PersUniti PassParentUnit) {
        //super(PassParentUnit);
    }

    //public Basepersuniti getbaseunit() {return super.getbaseunit();}


    /*Public Property FiringPersUnit As FiringPersUniti Implements PersUniti.FiringPersUnit

    Public Property MovingPersUnit As MovingPersuniti Implements PersUniti.MovingPersUnit
    Public Property TargetPersUnit As TargetPersUniti Implements PersUniti.TargetPersUnit
 xture
            'called by
                    'sets texture property for persuniti
                    'can be nothing; if so then Game.Draw will not draw
                    'content directory set in Game.New

                    'Const NatPre As Integer = 2
    Dim Linqdata = DataClassLibrary.ASLXNA.DataC.GetInstance()  'use null values when sure instance exists
    Dim UnitTexture As String = Nothing
    If BasePersUnit.Hexnum = 0 Then  'unit is not yet onboard so don't set texture
            UnitTexture = Nothing
    ElseIf BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.KIAInf Or BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.NotInPlay Or BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Hidden Or
    BasePersUnit.VisibilityStatus = ConstantClassLibrary.ASLXNA.VisibilityStatus.Concealed Then
    UnitTexture = "Rucon"
            'ElseIf Me.myBasePU.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken Or Me.myBasePU.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken_DM Then
            '    UnitTexture = Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.ImageBrkUnit, CInt(Me.myBasePU.LOBLink))
            '    UnitTexture = Trim(Linqdata.GetNatInfo(CInt(Me.myBasePU.Nationality), NatPre)) & Trim(UnitTexture)

    ElseIf BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.GoodOrder Then
    UnitTexture = Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.ImageUnit, CInt(BasePersUnit.LOBLink))
    UnitTexture = "ru" & Trim(UnitTexture)
    Else
            UnitTexture = Linqdata.GetLOBData(ConstantClassLibrary.ASLXNA.LOBItem.ImageBrkUnit, CInt(BasePersUnit.LOBLink))
    UnitTexture = "ru" & Trim(UnitTexture)
    End If

    If IsNothing(UnitTexture) Then
    Return Nothing
    Else
                'UnitTexture needs to be just the rootname with no previous status addition, ie 667s not 667scx
    UnitTexture = Trim(UnitTexture)
    UnitTexture = StatusCheck(UnitTexture)
    Return UnitTexture
                'Return Game.Content.Load(Of Texture2D)(Trim(UnitTexture))
    End If
    End Function
    Private Function StatusCheck(ByVal Unittexture As String) As String
            'called by ManageTexture.GetTexture
                    'creates texture string by checking the various status properties
    Select Case BasePersUnit.CombatStatus
    Case ConstantClassLibrary.ASLXNA.CombatStatus.PrepFirer
    Unittexture &= "prep"
    Case ConstantClassLibrary.ASLXNA.CombatStatus.AdvFirer
    Case ConstantClassLibrary.ASLXNA.CombatStatus.FinalFirer
    Unittexture &= "final"
    Case ConstantClassLibrary.ASLXNA.CombatStatus.Firing
    Case ConstantClassLibrary.ASLXNA.CombatStatus.FirstFirer
    Unittexture &= "first"
    Case ConstantClassLibrary.ASLXNA.CombatStatus.Melee
    Case ConstantClassLibrary.ASLXNA.CombatStatus.OppFirer
    Unittexture &= "opp"
    Case ConstantClassLibrary.ASLXNA.CombatStatus.SubsequentFirstFiring
    End Select
    If BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.Broken_DM Or BasePersUnit.OrderStatus = ConstantClassLibrary.ASLXNA.OrderStatus.DisruptedDM Then Unittexture &= "dm"
    If IsWounded(BasePersUnit.FortitudeStatus) Then Unittexture &= "wnd" ' = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded Then Unittexture &= "wnd"
    If BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.TI Then Unittexture &= "ti"
    If BasePersUnit.CX = True Then Unittexture &= "cx"
    If BasePersUnit.Pinned Then Unittexture &= "pin"
    If BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Labour1 Then Unittexture &= "tilab1"
    If BasePersUnit.MovementStatus = ConstantClassLibrary.ASLXNA.MovementStatus.Labour2 Then Unittexture &= "tilab2"
    If BasePersUnit.hexPosition = ConstantClassLibrary.ASLXNA.AltPos.WallAdv Or (BasePersUnit.hexPosition >= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus1 And BasePersUnit.hexPosition <= ConstantClassLibrary.ASLXNA.AltPos.WACrestStatus6) Then Unittexture &= "wa"
    Return Unittexture
    End Function
    Private Function IsWounded(ByVal FortitudeStatus As Integer) As Boolean
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Wounded Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.HeroicWounded Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Fan_Wnd_Enc Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.HeroicEnc_Wnd Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.Enc_Wnd Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.HeroicFan_Wnd Then Return True
    If FortitudeStatus = ConstantClassLibrary.ASLXNA.FortitudeStatus.HeroicFan_Wnd_Enc Then Return True
    Return False

    End Function*/
}
