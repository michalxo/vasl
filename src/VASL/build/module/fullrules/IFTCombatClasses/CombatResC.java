package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.DataC;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectChangeClasses.*;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class CombatResC implements CombatResi {

    private DataC Linqdata = DataC.GetInstance();
    private boolean myNeedToResume;
    private boolean myNeedAPopup;
    // private myPopupItems As New List(Of Objectvalues.MenuItemObjectholderinteface)
    private LinkedList<PersUniti> OrderedTargets = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewTargets = new LinkedList<PersUniti>();
    private LinkedList<PersUniti> myNewfirings = new LinkedList<PersUniti>();
    private boolean myFirerSANActivated;

    public CombatResC() {
        myNeedToResume = false;
        myNeedAPopup = false;
    }

    public boolean getFirerSANActivated() {
        return myFirerSANActivated;
    }
    public void setFirerSANActivated(boolean value) {
        myFirerSANActivated=value;
    }

    public LinkedList<PersUniti> getNewTargets() {
        return myNewTargets;
    }

    public LinkedList<PersUniti> getNewFirings() {
        return myNewfirings;
    }

    public boolean NeedToResume() {
        return myNeedToResume;
    }

    public boolean NeedAPopup() {
        return myNeedAPopup;
    }

    /*public Property PopupItems

    As List(Of Objectvalues.MenuItemObjectholderinteface) Implements CombatResi.
    PopupItems
            Get
    return myPopupItems
    End Get

    Set(value As List(Of Objectvalues.MenuItemObjectholderinteface))
    myPopupItems =value
    End Set
}*/
    public void ResolveCombat(LinkedList<PersUniti> TargGroup, LinkedList<TargetType> FPdrmCombos, int FirerSAN, int ScenID) {
        // at this point each member of TargGroup can be handled individually to resolve their specific IFT result - random selection for KIA && K is done
        // however, need to manage leader results for possible LLTC/LLMC
        ScenarioC scen  = ScenarioC.getInstance();
        Scenario Scendet = scen.getScendet();
        int LdrDRMforTargets = 0; String Resultstring = "";
        // first order units
        // take all units undergoing the same attack && order them
        LinkedList<PersUniti>  SameTarget = new LinkedList<PersUniti>();
        LinkedList<LinkedList<PersUniti>> OrderedTargets = new LinkedList<LinkedList<PersUniti>>();
        LinkedList<PersUniti> NewTargs = new LinkedList<PersUniti>();
        LinkedList<PersUniti> NewFirers = new LinkedList<PersUniti>();
        boolean PassHoBCheck;
        for (TargetType  UseTargettype: FPdrmCombos) {
            for (PersUniti TestforTargets : TargGroup) {
                if (TestforTargets.getTargetunit().getAttackedbyFP() == UseTargettype.getAttackedbyFP() &&
                        TestforTargets.getTargetunit().getAttackedbydrm() == UseTargettype.getattackedbydrm()) {
                    SameTarget.add(TestforTargets);
                }
            }
            OrderedTargets.add(OrderSelectedUnits(SameTarget));
        }
        //take each ordered list of targets && process them
        for (LinkedList<PersUniti> TargetList: OrderedTargets) {
            for (PersUniti TargetUnit : TargetList) {
                if (TargetUnit.getTargetunit().getIFTResolved() == true) {
                    continue;
                }  // don' t process units twice -will stop replacement units from being processed again
                LdrDRMforTargets = DetermineTargetLdrDRM(TargetList);
                // need to do each time through as can change if leader killed, wounded, broken or pinned
                GameModule.getGameModule().getChatter().send("Leader DRM for " + TargetUnit.getbaseunit().getUnitName() + " is " + Integer.toString(LdrDRMforTargets));
                // process impact on target
                if (ProcessImpact(TargetUnit, LdrDRMforTargets, Resultstring)) {
                    Constantvalues.PersUnitResult CombatImpact = TargetUnit.getTargetunit().getPersUnitImpact();
                    StatusChangei RunStatusChange = null;
                    switch (CombatImpact) {
                        case Dies:
                            RunStatusChange = new UnitDiesC();
                            break;
                        case Breaks:
                            RunStatusChange = new UnitBreaksc();
                            break;
                        case NoEffects:
                            //RunStatusChange = new UnitNoEffectsc();
                            break;
                        case Pins:
                            //RunStatusChange = new UnitPinsc();
                            break;
                        case Reduces:
                            PassHoBCheck = true; // Hob test done by last unitchange
                            //RunStatusChange = new UnitReducesc(Resultstring, PassHoBCHeck);
                            break;
                        case ReducesBreaks:
                            //RunStatusChange = new UnitReducesBreaksc(Resultstring);
                            break;
                        case DMs:
                            //RunStatusChange = new UnitDMsc();
                            break;
                        case Fanatics:
                            //RunStatusChange = new UnitFanaticsc();
                            break;
                        case Hardens:
                            RunStatusChange = new UnitHardensc();
                            break;
                        case Berserks:
                            RunStatusChange = new UnitBerserksc();
                            break;
                        case Surrenders:
                            //RunStatusChange = new UnitSurrendersc(AdjacentEnemy);
                            break;
                        case Replaces:
                            PassHoBCheck = true; // Hob test done by last unitchange
                            //RunStatusChange = new UnitReplacesc(PassHoBCHeck);
                            break;
                        case ReplacesReducesBreaks:
                            //RunStatusChange = new UnitReplacesReducesBreaksc();
                            break;
                        case ReplacesDMs:
                            //RunStatusChange = new UnitReplacesDMsc();
                            break;
                        case Wounds:
                            //RunStatusChange = new UnitWoundsc();
                            break;
                        case Substitutues:
                            //RunStatusChange = new UnitSubstitutesc();
                            break;
                        case StepReduces:
                            //RunStatusChange = new UnitStepReducesc();
                            break;
                        case Disrupts:
                            //RunStatusChange = new UnitDisruptsc();
                            break;
                        case HeroCreation:
                            //RunStatusChange = new UnitHeroCreationc();
                            break;
                        case HeroHardens:
                            RunStatusChange = new UnitHeroHardensc();
                            break;
                        //'case ReducesHOB
                        //'    RunStatusChange = new UnitReducesHOBc
                        case ReducesDies:
                            //RunStatusChange = new UnitReducesDiesc();
                            break;
                        case ReducesPins:
                            //RunStatusChange = new UnitReducesPinsc();
                            break;
                        case ReducesReplaces:
                            //RunStatusChange = new UnitReducesReplacesc();
                            break;
                        case StepReducesHS:
                            //RunStatusChange = new UnitStepReducesHSc();
                            break;
                        case ReplacesStepReduces:
                            //RunStatusChange = new UnitReplacesStepReducesc();
                            break;
                        case ReplacesStepReducesHS:
                            //RunStatusChange = new UnitReplacesStepReducesHSc();
                            break;
                        case DisruptDMs:
                            //RunStatusChange = new UnitDisruptDMsc();
                            break;
                            /*'case ReducesHeroHardens
                            '    RunStatusChange = new UnitReducesHeroHardensc(Resultstring)
                            'case ReducesHeroCreation
                            'RunStatusChange = new UnitReducesHeroCreationc(Resultstring)
                            'case ReducesHardens
                            '    RunStatusChange = new UnitReducesHardensc(Resultstring)
                            'case ReducesBerserks
                            'RunStatusChange = new UnitReducesberserksc(Resultstring)
                            'case ReducesSurrenders
                            'RunStatusChange = new UnitReducessurrendersc(Resultstring)*/
                        case HeroicLdrCreation:
                            RunStatusChange = new UnitHeroicLdrCreationc();
                            break;
                        case HeroicLdrHardens:
                            //'RunStatusChange = new UnitHeroicLdrHardensc
                        default:
                    }
                    if (RunStatusChange != null) {
                        RunStatusChange.Takeaction(TargetUnit);
                    }
                    TargetUnit.getTargetunit().setIFTResolved(true);
                    TargetUnit.getbaseunit().setOrderStatus(TargetUnit.getTargetunit().getOrderStatus());
                    if (RunStatusChange.GetNewTargs != null) {
                        NewTargs = RunStatusChange.GetNewTargs;
                    }
                    if (RunStatusChange.GetNewFirings != null) {
                        NewFirers = RunStatusChange.GetNewFirings;
                    }
                    //if (RunStatusChange.NewPopupitems) Then Me.PopupItems = RunStatusChange.NewPopupitems
                    //If Me.PopupItems.Count > 0 Then myNeedAPopup = true
                }
                // update Target && Firing lists with new units
                if (NewTargs.size() > 0) {
                    for (PersUniti AddUnit : NewTargs) {
                        myNewTargets.add(AddUnit);
                    }
                    NewTargs.clear();
                }
                if (NewFirers.size() > 0) {
                    for (PersUniti AddFireUnit : NewFirers) {
                        myNewfirings.add(AddFireUnit);
                    }
                    NewFirers.clear();
                }
                // Need to process SAN - can' t leave it to user click; rules says resolve before continuing
               myFirerSANActivated = TargetUnit.getTargetunit().getSANActivated();
                //'TEST CODe
                myFirerSANActivated = true;
                //
                if (myFirerSANActivated) {
                    if (TargetUnit.getbaseunit().getNationality() == Scendet.getATT1() ||
                            TargetUnit.getbaseunit().getNationality() == Scendet.getATT2()) {
                        Scendet.setdfnsanactivated(true);
                    } else {
                        Scendet.setattsanactivated(true);
                    }
                    PauseForSniper();
                    return;
                }
                // TargetUnit.TargetPersUnit = Nothing  ' can delete target info once resolved
            }
        }
    }
    private LinkedList<PersUniti> OrderSelectedUnits(LinkedList<PersUniti> SameTargets) {
        int j;
        int i; boolean test1 = false; boolean Keepgoing = true;
        int MoraleLevelTest1;
        int LdrmTest1=0;
        PersUniti Unittocheck;
        PersUniti TestUnittoCheck;
        // Dim tempML As Integer, MoraleFlag As Integer
        boolean test2 = false;
        int MoraleLevelTest2;
        int LdrmTest2=0;
        boolean Test3 = false; // : Dim TestUnittoget As Integer
        boolean swap = false;
        // Dim TempArray As Integer, TempType As Integer,
        int NumTargs;
        String msg;
        String msgtitle;
        PersUniti TestToSwap1; PersUniti TestToSwap2;

        NumTargs = SameTargets.size();
        do {
            swap = false; Keepgoing=true;
            for (i = 0; i < NumTargs; i++) {
                for (j = (i + 1); j < NumTargs; j++) {
                    Unittocheck = SameTargets.get(i);
                    test1 = Unittocheck.getbaseunit().IsUnitALeader();
                    if (test1) {
                        LdrmTest1 = Integer.parseInt(Linqdata.GetLOBData(Constantvalues.LOBItem.LDRM, Unittocheck.getbaseunit().getLOBLink()));
                        if ((Unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Wounded ||
                                Unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd ||
                                Unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Enc_Wnd ||
                                Unittocheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd_Enc) && LdrmTest1 < 1) {
                            LdrmTest1 += 1;
                        }
                    }
                    MoraleLevelTest1 = Integer.parseInt(Linqdata.GetLOBData(Constantvalues.LOBItem.MORALELEVEL, Unittocheck.getbaseunit().getLOBLink()));
                    TestUnittoCheck = SameTargets.get(j);
                    test2 = TestUnittoCheck.getbaseunit().IsUnitALeader();
                    if (test2) {
                        LdrmTest2 = Integer.parseInt(Linqdata.GetLOBData(Constantvalues.LOBItem.LDRM, (TestUnittoCheck.getbaseunit().getLOBLink())));
                        if ((TestUnittoCheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Wounded ||
                                TestUnittoCheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd ||
                                TestUnittoCheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Enc_Wnd ||
                                TestUnittoCheck.getbaseunit().getFortitudeStatus() == Constantvalues.FortitudeStatus.Fan_Wnd_Enc) && LdrmTest2 < 1) {
                            LdrmTest2 += 1;
                        }
                    }
                    MoraleLevelTest2 = Integer.parseInt(Linqdata.GetLOBData(Constantvalues.LOBItem.MORALELEVEL, TestUnittoCheck.getbaseunit().getLOBLink()));
                    Test3 = (MoraleLevelTest2 >= MoraleLevelTest1);
                    if (test1 && test2 && Test3) {
                        if (LdrmTest2 < LdrmTest1) {
                            swap = true;
                        }
                    } else if (!test1 && test2) {
                        swap = true;
                    }
                    if (swap == true) {
                        TestToSwap1 = SameTargets.get(i);
                        TestToSwap2 = SameTargets.get(j);
                        SameTargets.set(i, TestToSwap2);
                        SameTargets.set(j, TestToSwap1);
                        swap = false;
                    }
                }
            }
            Keepgoing = false;
        } while (Keepgoing=true);

        msgtitle = "Checking Order of Units";
        /*For Each checkitem As Objectvalues.PersUniti In SameTargets
        msg &= Trim(checkitem.BasePersUnit.UnitName) & vbCrLf
        Next
        MsgBox(msg, , msgtitle)*/
        return SameTargets;
    }
    private boolean PauseForSniper() {
        myNeedToResume = true;
        GameModule.getGameModule().getChatter().send("Pausing Combat Resolution to do Sniper Check");
                return true;
    }
        public void ResumeResolution () {
            GameModule.getGameModule().getChatter().send("Resuming Combat Resolution after Sniper Check");

    }
    public void ResumeSurrenderResolution (int GuardSelected, int TargId){
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        LinkedList<PersUniti> NewTargs = new LinkedList<PersUniti>() ;
        //': Dim AddNewTargs = New List(Of Objectvalues.PersUniti)
        LinkedList<PersUniti> NewFirers = new LinkedList<PersUniti>() ;
        myNeedAPopup = false; //myPopupItems = null;
        PersUniti PassGuard=null; PersUniti PassTarget = null;
        for (PersUniti selunit: Scencolls.Unitcol) {
            if(selunit.getbaseunit().getUnit_ID() == GuardSelected) {
                PassGuard = selunit;
                break;
            }
        }
        for (PersUniti seltarget: Scencolls.Unitcol) {
            if (seltarget.getbaseunit().getUnit_ID() == TargId) {
               PassTarget=seltarget;
               break;
            }
        }
        StatusChangei RunStatusChange = new UnitSurrendersc(PassGuard);
        RunStatusChange.Takeaction(PassTarget);
        PassTarget.getTargetunit().setIFTResolved(true);
        PassTarget.getbaseunit().setOrderStatus(PassTarget.getTargetunit().getOrderStatus());
        if (RunStatusChange.GetNewTargs != null) {NewTargs = RunStatusChange.GetNewTargs;}
        if (RunStatusChange.GetNewFirings != null) { NewFirers = RunStatusChange.GetNewFirings;}
        // update Target && Firing lists with new units
        if (NewTargs.size() > 0) {
            for (PersUniti AddUnit : NewTargs) {
                myNewTargets.add(AddUnit);
            }
            NewTargs.clear();
        }
        if (NewFirers.size() > 0) {
            for (PersUniti AddFireUnit : NewFirers) {
                myNewfirings.add(AddFireUnit);
            }
            NewFirers.clear();
        }
    }
    private boolean ProcessImpact(PersUniti TargetUnit, int LdrDRMforTargets, String Resultstring) {
        boolean GoodResult = false;  Resultstring = "";
                
        switch (TargetUnit.getTargetunit().getIFTResult()) {
            case KIA:
                GoodResult = TargetUnit.getTargetunit().KIA();
                break;
            case K4:
                GoodResult = TargetUnit.getTargetunit().CRMC(4, LdrDRMforTargets, Resultstring);
                break;
            case K3:
                GoodResult = TargetUnit.getTargetunit().CRMC(3, LdrDRMforTargets, Resultstring);
                break;
            case K2:
                GoodResult = TargetUnit.getTargetunit().CRMC(2, LdrDRMforTargets, Resultstring);
                break;
            case K1:
                GoodResult = TargetUnit.getTargetunit().CRMC(1, LdrDRMforTargets, Resultstring);
                break;
            case MC4:
                GoodResult = TargetUnit.getTargetunit().MC(4, LdrDRMforTargets);
                break;
            case MC3:
                GoodResult = TargetUnit.getTargetunit().MC(3, LdrDRMforTargets);
                break;
            case MC2:
                GoodResult = TargetUnit.getTargetunit().MC(2, LdrDRMforTargets);
                break;
            case MC1:
                GoodResult = TargetUnit.getTargetunit().MC(1, LdrDRMforTargets);
                break;
            case NMC:
                GoodResult = TargetUnit.getTargetunit().MC(0, LdrDRMforTargets);
                break;
            case Broken:
                GoodResult = TargetUnit.getTargetunit().Break();
                break;
            case PTC:
                GoodResult = TargetUnit.getTargetunit().PTC(LdrDRMforTargets);
                break;
            case NR:
                GoodResult = TargetUnit.getTargetunit().NR();
                break;
            default:
        }
        return GoodResult;
    }
    private int DetermineTargetLdrDRM(LinkedList<PersUniti> OrderedList) {
        if (OrderedList.get(0).getbaseunit().IsUnitALeader()) {
            return OrderedList.get(0).getTargetunit().getLdrDRM();
        } else {
            return 0;
        }
    }
}
