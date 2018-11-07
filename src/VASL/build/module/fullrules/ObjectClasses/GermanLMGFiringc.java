package VASL.build.module.fullrules.ObjectClasses;

import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.OrderofBattleSW;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASSAL.build.GameModule;

import java.util.LinkedList;

public class GermanLMGFiringc implements FiringSuppWeapi {


    private Constantvalues.CombatStatus myCombatStatus;
    private double myCombatFP;
    private Location myLoc;
    private SuppWeapi mySW;

    public GermanLMGFiringc(SuppWeapi PassSW) {

        this.myLoc = PassSW.getbaseSW().gethexlocation();
        this.myCombatStatus = PassSW.getbaseSW().getCombatStatus();
        this.mySW = PassSW;
    }

    public void AdvancingFireModification(Constantvalues.Phase phase) {
        // called by IFT.CalcFP
        // changes CombatFP property based on Phase

        if (phase == Constantvalues.Phase.AdvancingFire && myCombatStatus != Constantvalues.CombatStatus.OppFirer) {
            // area due to advancing fire
            // ARE THERE OTHER AREA CONDITIONS NOT REFLECTED IN THIS?
            myCombatFP = myCombatFP / 2;
        }
    }

    public void AreaFireModification(int FGSize, Location targloc) {
        // called by EnemyValuesConcreteC.CalcFPandDRM_thread and IFTC.CalcFPandDRM
        // reduces FP for each Area fire case

        // cellar
        if (FGSize == 3) {  //Constantvalues.Utype.Squad) {
            boolean ReducedFP = false;
            ConversionC DoConversion = new ConversionC();
            Constantvalues.Location myLoctype = DoConversion.ConverttoLocationtypefromVASLLocation(myLoc);
            if (myLoctype == Constantvalues.Location.StoneCellar || myLoctype == Constantvalues.Location.WoodCellar) {
                ReducedFP = true;
                if (targloc.getTerrain().isCellar()) {
                    ReducedFP = false;   // must be part of building or LOS would not exist and would not be here; no need to check again
                }
                if (targloc.getHex().getName() == myLoc.getHex().getName() && targloc.getBaseHeight() == 0) {
                    ReducedFP = false;  // must be grounhd level or LOS would not exist and would not be here; no need to check again
                }
            }
            if (ReducedFP) {
                myCombatFP = myCombatFP / 2;
            }
        }
    }


    public void AssaultFireModification(Constantvalues.Phase phase) {
        // called by IFT.CalcFP
        // changes CombatFP property based on use of Spraying Fire

        //if (phase == Constantvalues.Phase.AdvancingFire) {myCombatFP = myCombatFP + 1;}
    }

    public boolean getAssF() {return false;}

    public int getBaseFP() {return 3;}

    public int getBaseRange() {return 8;}

    public int getBreakdown () {return 12;}

    public double getCombatFP () {return myCombatFP;}

    public Constantvalues.CombatStatus getCombatStatus () {return myCombatStatus;}
    public void setCombatStatus(Constantvalues.CombatStatus value) {myCombatStatus =value;}

    public void setCombatFP(double value) {myCombatFP = value;}

    public void  CrestStatusModification(int Targethexnum) {
        // called by EnemyValuesConcreteC.CalcFPandDRM_thread
        // amends inherent FP for unit in CrestStatus - handles blocked by wall and  firing outside crest CA
        if (!mySW.getbaseSW().IsInCrestStatus()) {return;}  // if not in crest status should not be here
        int[] hexsidescrossed =new int[5];  // NEED TO CODE As List (Of Integer) =MapGeo.LOSSideEntry(Targethexnum, myLoc.getHexnum()); // use reversed order for hexes to get exit hexsides
            boolean IsCrossingWHR = false;
            for (int nexthexside: hexsidescrossed) {
                switch (nexthexside) {
                    /*case 1:
                        IsCrossingWHR = myLoc.getSide1IsWHR();
                    case 2:
                        IsCrossingWHR = myLoc.getSide2IsWHR();
                    case 3:
                        IsCrossingWHR = myLoc.getSide3IsWHR();
                    case 4:
                        IsCrossingWHR = myLoc.getSide4IsWHR();
                    case 5:
                        IsCrossingWHR = myLoc.getSide5IsWHR();
                    case 6:
                        IsCrossingWHR = myLoc.getSide6IsWHR();*/
                    default:
                        //GameModule.getGameModule().getChatter().send("Hexside failure");
                        IsCrossingWHR = false;
                }
                if (IsCrossingWHR) {
                    if (mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus0 ||
                            mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus1 ||
                            mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus2 ||
                            mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus3 ||
                            mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus4 ||
                            mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus5) {
                        myCombatFP = 0; // wall/hedge blocks LOS by entrenched unit
                        return;
                    }
                }
            }
            // Dim CrestTest = New Utilvalues.ConversionC temporary while debugging UNDO
            int CrestCA  = 0;
            boolean UsingCrestCA = false;
            if (mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus0 ||
                    mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus1 ||
                    mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus2 ||
                    mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus3 ||
                    mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus4 ||
                    mySW.getbaseSW().gethexPosition() == Constantvalues.AltPos.CrestStatus5) {
                // CrestCA = CrestTest.CrestSideToSide(mySW.getBaseSW().gethexPosition());   temporary while debugging UNDO
            } else {
                // CrestCA = CrestTest.WACrestSideToSide(mySW.getBaseSW().gethexPosition());  temporary while debugging UNDO
            }
            int MinusCrestCA = 0;
            int PlusCrestCA = 0;
            // DONT THINK THIS CODE IS RIGHT - USES WRONG CA DEFINITION - CHECK AUG 13
            for (int nexthexside: hexsidescrossed) {
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
        if (!UsingCrestCA) {myCombatFP = myCombatFP / 2;}
    }

    public void FireVsConcealedModification(PersUniti TargetUnit) {
        // called by IFT.CalcFP
        // changes CombatFP property based on Concealed Target
        if (TargetUnit.getbaseunit().getVisibilityStatus() == Constantvalues.VisibilityStatus.Concealed) {
            myCombatFP = myCombatFP / 2;
        }
    }

    public void FirstFireModification() {
        // called by IFT.CalcFP
        // changes CombatFP property based on Def First Fire
        if (myCombatStatus == Constantvalues.CombatStatus.FirstFirer) {
            myCombatFP = myCombatFP / 2;
        }
    }

    public int getHeroDRM() {return 0;}  // NEED TO CODE

    public boolean getIsCX() {return mySW.getbaseSW().getCX();}


    public boolean getIsEncirc() {return mySW.getbaseSW().IsUnitEncircled();}

    public boolean getIsPinned() {return mySW.getbaseSW().getPinned();}
    public void setIsPinned(boolean value) {} // NEED TO CODE

    public int getLdrDRM () {return 0;} // NEED TO CODE
    public int getMalfunction() {return 12;}
    public void setMalfunction(int value) {}
    public void setBreakdown(int value){}
    public void PinnedModification() {
        // called by IFT.CalcFP
        // changes CombatFP property based on pinned Status

        if (getIsPinned()) {myCombatFP = myCombatFP / 2;}
    }

    public void RangeModification(double range, double LevelDifference, PersUniti TargetU) {
        // called by IFT.CalcFP
        // changes CombatFP property based on BaseFP, BaseRange and Combat range

        double TempFP = getBaseFP();
        double RangeFactor = 1;  // holds value of combat range/base range
        int comboFPitem = 0; // hold value of cbxFP index to be passed to Gameform routine
        RangeFactor = range / getBaseRange();

        if (RangeFactor <= 1) {
            if (range == 1 && (LevelDifference >= -1) ||
                            (range == 0 && (LevelDifference == -1 || LevelDifference > 0))) {
                        // level difference is positive when firer above target
                        // PBF
                        TempFP = TempFP * 2;
            } else if (range == 0 && LevelDifference == 0) {
                        /*// do pillbox check - LOS needs be within CA
                        Dim TerrGet = New Terrainvalues.GetALocationFromMapTable(MapCol)
                        Dim HexLocIndex
                        As Integer = TerrGet.GetPillboxLocation(MyLoc.Hexnum)
                        Dim UsingHex
                        As MapDataClassLibrary.GameLocation
                        if HexLocIndex > 0 Then
                        Dim PillboxLOS
                        As Boolean = false
                        'get Pillbox location
                        UsingHex = TerrGet.RetrieveLocationfromHex(HexLocIndex)
                        'Now determine Pillbox covered arc
                        Dim TerrChk = New Terrainvalues.TerrainChecks(MapCol)
                        Dim Maptables
                        As MapDatavalues.MapDataC = MapDatavalues.MapDataC.GetInstance("", 0)
                        'use null values for parameters when sure instance exists
                        Dim Imagename
                        As String = TerrChk.GetLocationData(Constantvalues.TerrFactor.Image, CInt(UsingHex.Location), Maptables)
                        Dim Linqdata = Datavalues.DataC.GetInstance() 'use null values when sure instance exists
                        Dim TestCA
                        As DataClassLibrary.LookupCA = (
                                From Qu
                        In Linqdata.
                        db.LookupCAs Where
                        Qu.Terraindesc = Imagename).First
                        'compare entry hexside with Pillbox CA
                        if TargetU.MovingPersUnit.HexEnteredSideCrossed =
                                TestCA.FirstCA Or
                        TargetU.MovingPersUnit.HexEnteredSideCrossed =
                                TestCA.SecondCA Then
                                PillboxLOS = true
                        End if
                        if Not
                        PillboxLOS Then
                        TempFP = 0
                        else TempFP = TempFP * 2
                        else
                        'if here then no pillbox issues so continue
                        'TPBF
                        TempFP = TempFP * 3
                        End if*/
            }
            else if (RangeFactor > 1 && RangeFactor <= 2) {
                    if (myCombatStatus == Constantvalues.CombatStatus.FirstFirer) {
                        // MsgBox(String.Format("First Firer {0} is beyond maximum range", Trim(Name)), , "No FP Added")
                        TempFP = 0;
                    } else {
                        // Long range
                        TempFP = TempFP / 2;
                    }

            } else if (RangeFactor > 2) {
                    GameModule.getGameModule().getChatter().send( " is beyond maximum range: No FP Added");
                    TempFP = 0;

            }
        }
        myCombatFP = TempFP;
    }

    public void ResetCombatFP() {myCombatFP=0;}

    public int getROF() {return 1;}


    public boolean getSprayFire() {return true;}
    public void setSprayFire(boolean value) {}


    public void SprayFireModification(boolean UsingSprayFire) {
        // called by IFT.CalcFP
        // changes CombatFP property based on use of Spraying Fire
        if (UsingSprayFire) {
            myCombatFP = myCombatFP / 2;
            //'MsgBox(String.Format("Spraying Fire reduces {0} FP to {1}", Trim(Me.Name), CStr(myCombatFP)))
        }
    }

    public void UpdateCombatStatus(Constantvalues.CombatStatus NewCombatStatus, int ROFdr) {
        // MOVE THIS OUT TO A COMMON FUNCTION AS IT WILL BE IDENTICAL ACROSS ALL FIRING CLASSES

        // update SuppWeapi
        myCombatStatus = NewCombatStatus;
        mySW.getbaseSW().setCombatStatus(NewCombatStatus);
        ScenarioC scen = ScenarioC.getInstance();
        // now update OBSW - IS THIS NEEDED HERE?
        LinkedList<OrderofBattleSW> TempSWCol = scen.getOBSWcol();
        for (OrderofBattleSW testOBSW : TempSWCol) {
            if (testOBSW.getOBSW_ID() == mySW.getbaseSW().getSW_ID()) {
                testOBSW.setCombatStatus(myCombatStatus);
                mySW.getbaseSW().setCombatStatus(myCombatStatus);
                break;
            }
        }
        // handle counter action here - no, handle in command
        //CounterActions counteractions = new CounterActions();
        //counteractions.placefirecounter(mySW);
    }

    public Constantvalues.Utype getUseHeroOrLeader() {return null;} // NEED TO CODE
    public void setUseHeroOrLeader(int value) {}

    public boolean getIsMG() {return true;}

}
