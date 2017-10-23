package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;
import VASL.build.module.fullrules.MapDataClasses.MapDataC;
import VASL.build.module.fullrules.TerrainClasses.GetALocationFromMap;
import VASL.build.module.fullrules.TerrainClasses.TerrainChecks;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

import java.util.LinkedList;

public class Russian447Firec implements FiringPersUniti {
    // Holds units and sw - selected fields for combat only
    // object held in FireGroup ArrayList
    private boolean myIsEncirc = false;
    private boolean myCX = false;
    private Constantvalues.CombatStatus myCombatStatus;
    private boolean myIsPinned;
    private double myCombatFP;
    private int mySolID;
    private boolean myHasMG;
    private boolean myUsingInherentFP;
    private boolean myUsingfirstMG;
    private boolean myUsingsecondMG;
    private boolean myIsInCrestStatus;
    private Constantvalues.AltPos myhexposition;
    private Constantvalues.Utype myUseHeroOrLeader;
    private int myOBLink;
    private Location myLoc;
    private PersUniti myUnit;
//    private LinkedList<GameLocation> Mapcol = new LinkedList<GameLocation>();
//    private GetALocationFromMap Getlocs;
//    MapDataC MapData = MapDataC.GetInstance("", 0);  // use empty values when already created FIX

    public Russian447Firec (boolean PassIsCX, boolean PassIsPinned, boolean PassHasMG, boolean PassUsingInherentFP, boolean PassUsingfirstMG, boolean PassUsingsecondMG, PersUniti PassUnit) {

        if (PassUnit.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Encircled ||
                PassUnit.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Enc_Wnd ||
                PassUnit.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Fan_Enc ||
                PassUnit.getbaseunit().getFortitudeStatus() == VASL.build.module.fullrules.Constantvalues.FortitudeStatus.Fan_Wnd_Enc) {
            this.myIsEncirc = true;
        }
        this.myCombatStatus = PassUnit.getbaseunit().getCombatStatus();
        this.myIsPinned = PassIsPinned;
        this.mySolID = PassUnit.getbaseunit().getSolID();
        this.myCombatFP = 0;
        this.myCX = PassIsCX;
        this.myHasMG = PassHasMG;
        this.myUsingInherentFP = PassUsingInherentFP;
        this.myUsingfirstMG = PassUsingfirstMG;
        this.myUsingsecondMG = PassUsingsecondMG;
        this.myLoc = PassUnit.getbaseunit().gethexlocation();
        /*MapDataC MapData = MapDataC.GetInstance("", 0);  // use empty values when already created FIX
        Mapcol = MapData.getLocationCol();
        Getlocs = new GetALocationFromMap(Mapcol);
        MyLoc = Getlocs.RetrieveLocationfromMaptable(PassUnit.getbaseunit().getLOCIndex());*/
        myIsInCrestStatus = PassUnit.getbaseunit().IsInCrestStatus();
        myhexposition = PassUnit.getbaseunit().gethexPosition();
        myOBLink = PassUnit.getbaseunit().getUnit_ID();
        myUnit = PassUnit;
    }

    public int getSolID() {
        return mySolID;
    }

    public Constantvalues.Utype getType() {
        return VASL.build.module.fullrules.Constantvalues.Utype.Squad;
    }

    public int getLOBLink() {
        return 27;
    }

    public Constantvalues.CombatStatus getCombatStatus() {
        return myCombatStatus;
    }

    public void setCombatStatus(Constantvalues.CombatStatus value) {
        myCombatStatus = value;
    }

    public boolean getIsCX() {
        return myCX;
    }

    public boolean getIsEncirc() {
        return myIsEncirc;
    }

    public int getBaseFP() {
        return 4;
    }

    public int getBaseRange() {
        return 4;
    }

    public boolean getAssF() {
        return false;
    }

    public double getCombatFP() {
        return myCombatFP;
    }

    public int getLdrDRM() {
        return 0;
    }

    public Constantvalues.Utype getUseHeroOrLeader() {
        return myUseHeroOrLeader;
    }

    public void setUseHeroOrLeader(Constantvalues.Utype value) {
        myUseHeroOrLeader = value;
    }

    public boolean getIsPinned() {
        return myIsPinned;
    }

    public void setIsPinned(boolean value) {
        myIsPinned = value;
    }

    public int getHeroDRM() {
        return 0;
    }

    public boolean getHasMG() {
        return myHasMG;
    }

    public LinkedList<SuppWeapi> FiringMGs = new LinkedList<SuppWeapi>();

    public boolean getUsingInherentFP() {
        return myUsingInherentFP;
    }

    public void setUsingInherentFP(boolean value) {
        myUsingInherentFP = value;
    }

    public boolean getUsingfirstMG() {
        return myUsingfirstMG;
    }

    public void setUsingfirstMG(boolean value) {
        myUsingfirstMG = value;
    }

    public boolean getUsingsecondMG() {
        return myUsingsecondMG;
    }

    public void setUsingsecondMG(boolean value) {
        myUsingsecondMG = value;
    }

    public void RangeModification(double range, double LevelDifference, PersUniti TargetU) {
        // called by ifT.CalcFP
        // changes CombatFP property based on BaseFP, BaseRange and Combat range
        double TempFP = getBaseFP();
        double RangeFactor = 1; // holds value of combat range/base range
        //Dim comboFPitem As Integer = 0 'hold value of cbxFP index to be passed to Gameform routine
        RangeFactor = (range / getBaseRange());
        if (RangeFactor <= 1) {
            if ((range == 1 && LevelDifference >= -1) || (range == 0 && LevelDifference == -1) || LevelDifference > 0) {
                // level difference is positive when firer above target
                // PBF
                TempFP = TempFP * 2;
            } else if (range == 0 && LevelDifference == 0) {
                // do pillbox check - LOS needs be within CA
                int HexLocIndex = 0; //Getlocs.GetPillboxLocation(MyLoc.getHexnum());   temporary while debugging
                GameLocation UsingHex;
                if (HexLocIndex > 0) {
                    /*boolean PillboxLOS = false;
                    // get Pillbox location
                    UsingHex = Getlocs.RetrieveLocationfromMaptable(HexLocIndex);
                    // Now determine Pillbox covered arc
                    TerrainChecks TerrChk = new TerrainChecks(Mapcol);
                    String Imagename = TerrChk.GetLocationData(Constantvalues.TerrFactor.Image, (UsingHex.getLocation()));
                    // temporary while debugging UNDO
                    *//*Dim TestCA As DataClassLibrary.LookupCA = (From Qu In Linqdata.db.LookupCAs Where
                    Qu.Terraindesc = Imagename).First*//*
                    // compare entry hexside with Pillbox CA
                    if (TargetU.getMovingunit().getHexEnteredSideCrossed() == 0 || TargetU.getMovingunit().getHexEnteredSideCrossed() == 0) { // replace TestCA.firstca and TestCA.secondca with 0
                        PillboxLOS = true;
                    }
                    if (!PillboxLOS) {
                        TempFP = 0;
                    } else {
                        TempFP = TempFP * 2;
                    }
                } else {*/
                    // if here then no pillbox issues so continue
                    // TPBF
                    TempFP = TempFP * 3;
                }
            }
        } else if (RangeFactor >= 1.0001 && RangeFactor <= 2) {
            if (this.myCombatStatus == Constantvalues.CombatStatus.FirstFirer) {
                //'MsgBox(String.Format("First Firer {0} is beyond maximum range", Trim(Name)), , "No FP Added")
                TempFP = 0;
            } else {
                // long range
                TempFP = TempFP / 2;
            }
        } else if (RangeFactor > 2) {
            //'MsgBox(Trim(Me.Name) & " is beyond maximum range", , "No FP Added")
            TempFP = 0;
        }
        this.myCombatFP = TempFP;
    }

    public void PinnedModification() {
        // called by ifT.CalcFP
        // changes CombatFP property based on pinned Status

        if (getIsPinned()) {
            myCombatFP = myCombatFP / 2;
        }
    }

    public void AdvancingFireModification(Constantvalues.Phase Phase) {
        // called by ifT.CalcFP
        // changes CombatFP property based on Phase

        if (Phase == Constantvalues.Phase.AdvancingFire && myCombatStatus != Constantvalues.CombatStatus.OppFirer) {
            // area due to advancing fire
            // ARE THERE OTHER AREA CONDITIONS NOT REFLECTED IN THIS?
            myCombatFP = myCombatFP / 2;
        }
    }

    public void FireVsConcealedModification(PersUniti TargetUnit) {
        // called by ifT.CalcFP
        // changes CombatFP property based on Concealed Target
        if (TargetUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed) {
            myCombatFP = myCombatFP / 2;
        }
    }

    public void FirstFireModification() {
        // called by ifT.CalcFP
        // changes CombatFP property based on Def First Fire

        if (myCombatStatus == Constantvalues.CombatStatus.FirstFirer) {
            myCombatFP = myCombatFP / 2;
        }
    }

    public void SprayFireModification(boolean UsingSprayFire) {
        // called by ifT.CalcFP
        // changes CombatFP property based on use of Spraying Fire
        if (UsingSprayFire == true) {
            myCombatFP = myCombatFP / 2;
            //'MsgBox(String.Format("Spraying Fire reduces {0} FP to {1}", Trim(Me.Name), CStr(myCombatFP)))
        }
    }

    public void AssaultFireModification(Constantvalues.Phase Phase) {
        //called by ifT.CalcFP
        //changes CombatFP property based on use of Assault Fire


    }
    public void AreaFireModification(int FGSize, Location targloc) {
        // called by EnemyValuesConcreteC.CalcFPandDRM_thread and ifTC.CalcFPandDRM
        // reduces FP for each Area fire case

        // cellar
        if (FGSize >= 3) {
            boolean ReducedFP = false;
            ConversionC DoConversion = new ConversionC();
            Constantvalues.Location myLoctype = DoConversion.getLocationtypefromVASLLocation(myLoc);
            if (myLoctype == Constantvalues.Location.StoneCellar || myLoctype == Constantvalues.Location.WoodCellar) {
                ReducedFP = true;
                if (targloc.getTerrain().isCellar()) {
                    ReducedFP = false;
                } // must be part of building or LOS would not exist and would not be here; no need to check again
                if (targloc.getHex().getName() == myUnit.getbaseunit().getHexName() && targloc.getBaseHeight() ==0) {
                    ReducedFP = false;
                } // must be ground level or LOS would not exist and would not be here; no need to check again
            }
            if (ReducedFP) {
                myCombatFP = myCombatFP / 2;
            }
        }
    }

    public void MGModification() {
        // called by EnemyValuesConcreteC.CalcFPandDRM_thread
        // amends inherent FP for crew/HS firing a MG or squad firing two MG

        // no tests built - do before calling
        myCombatFP = 0;
    }

    public void CrestStatusModification(int Targethexnum) {
        // called by EnemyValuesConcreteC.CalcFPandDRM_thread
        // amends inherent FP for unit in CrestStatus - handles blocked by wall and  firing outside crest CA
        if (!myIsInCrestStatus) {
            return;
        }  // if not in creststatus should not be here
        int[] hexsidescrossed = new int[6]; // As List (Of Integer) =MapGeo.LOSSideEntry(Targethexnum, CInt(MyLoc.Hexnum))
        // use reversed order for hexes to get exit hexsides
        boolean IsCrossingWHR = false;
        for (int nexthexside : hexsidescrossed) {
            switch (nexthexside) {
                /*case 1:
                    IsCrossingWHR = MyLoc.getSide1IsWHR();
                    break;
                case 2:
                    IsCrossingWHR = MyLoc.getSide2IsWHR();
                    break;
                case 3:
                    IsCrossingWHR = MyLoc.getSide3IsWHR();
                    break;
                case 4:
                    IsCrossingWHR = MyLoc.getSide4IsWHR();
                    break;
                case 5:
                    IsCrossingWHR = MyLoc.getSide5IsWHR();
                    break;
                case 6:
                    IsCrossingWHR = MyLoc.getSide6IsWHR();
                    break;*/
                default:
                    //MsgBox("Hexside failure")
                    IsCrossingWHR = false;
            }
            if (IsCrossingWHR) {
                if (myhexposition == Constantvalues.AltPos.CrestStatus1 ||
                        myhexposition == Constantvalues.AltPos.CrestStatus2 ||
                        myhexposition == Constantvalues.AltPos.CrestStatus3 ||
                        myhexposition == Constantvalues.AltPos.CrestStatus4 ||
                        myhexposition == Constantvalues.AltPos.CrestStatus5 ||
                        myhexposition == Constantvalues.AltPos.CrestStatus6) {
                    myCombatFP = 0; // wall/hedge blocks LOS by entrenched unit
                    return;
                }
            }
        }

        //ConversionC CrestTest = new ConversionC();
        int CrestCA = 0;
        boolean UsingCrestCA = false;

        if (myhexposition == Constantvalues.AltPos.CrestStatus1 ||
                myhexposition == Constantvalues.AltPos.CrestStatus2 ||
                myhexposition == Constantvalues.AltPos.CrestStatus3 ||
                myhexposition == Constantvalues.AltPos.CrestStatus4 ||
                myhexposition == Constantvalues.AltPos.CrestStatus5 ||
                myhexposition == Constantvalues.AltPos.CrestStatus6) {
            CrestCA = 0;  //CrestTest.CrestSideToSide(myhexposition);
        } else {
            CrestCA = 0;  //CrestTest.WACrestSideToSide(myhexposition);
        }
        int MinusCrestCA = 0;
        int PlusCrestCA = 0;
        //'DONT THINK THIS CODE IS RIGHT - USES WRONG CA DEFINITION - CHECK AUG 13
        for (int nexthexside : hexsidescrossed) {
            if (CrestCA == 6) {
                PlusCrestCA = 1;
            } else {
                PlusCrestCA = CrestCA + 1;
            }
            if (CrestCA == 1) {
                MinusCrestCA = 6;
            } else {
                MinusCrestCA = CrestCA - 1;
            }
            if (CrestCA == nexthexside || MinusCrestCA == nexthexside || PlusCrestCA == nexthexside) {
                UsingCrestCA = true;
                break;
            }
        }
        if (!UsingCrestCA) {
            myCombatFP = myCombatFP / 2;
        }
    }

    public void ResetCombatFP() {
        myCombatFP = 0;
    }

    public void UpdateCombatStatus(Constantvalues.CombatStatus NewCombatStatus, int ROFdr){

        myCombatStatus =NewCombatStatus;
        SuppWeapi FiringMG;
    /*Dim Linqdata
    As Datavalues.DataC =Datavalues.DataC.GetInstance()
    Dim UpdateUnit
    As DataClassLibrary.OrderofBattle =Linqdata.GetUnitfromCol(myOBLink)
    UpdateUnit.CombatStatus =Me.CombatStatus
    myBaseUnit.BasePersUnit.CombatStatus =Me.CombatStatus
    if
    Me.UsingfirstMG Then
    FiringMG =(
    From getMG
    As Objectvalues.
    SuppWeapi In
    Me.FiringMGs Where
    getMG.BaseSW.Unit_ID =myBaseUnit.BasePersUnit.FirstSWLink).First
                FiringMG.FiringSW.UpdateCombatStatus(NewCombatStatus,ROFdr)
    End if
            if
    Me.UsingsecondMG Then
    FiringMG =(
    From getMG
    As Objectvalues.
    SuppWeapi In
    Me.FiringMGs Where
    getMG.BaseSW.Unit_ID =myBaseUnit.BasePersUnit.SecondSWlink).First
                FiringMG.FiringSW.UpdateCombatStatus(NewCombatStatus,ROFdr)
    End if
            Linqdata.QuickUpdate()*/
    }

        /*'public Function CanStillUseInherentFP(ByVal MGCount As Integer) As Boolean Implements FiringPersUniti.CanStillUseInherentFP
                '    if MGCount = 2 Then return false Else return true
                'End Function*/
}
