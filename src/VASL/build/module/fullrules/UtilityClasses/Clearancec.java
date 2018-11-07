package VASL.build.module.fullrules.UtilityClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;
import VASL.build.module.fullrules.ObjectFactoryClasses.SWCreation;

public class Clearancec {

    private ScenarioCollectionsc scencolls = ScenarioCollectionsc.getInstance();

    public int ClearanceRollIs2(Constantvalues.UMove Clearancetype) {
        // called by ClearLegalC.IsMovementLegal and elsewhere
        int LDRM  = 0; int BestLDRM = 0; Constantvalues.Utype Unittype = null; int TotalUnits = 0;
        int UnitsDRM = 0; int LabourDRM = 0; int HeroDRM = 0; Constantvalues.UClass UnitClass = null; int FinalDRM = 0;
        int SapperDRM = 0; int BullDRM = 0; int ScenID = 0;
        DiceC ClearDice = new DiceC();
        int ClearDR = ClearDice.Diceroll();
        if (scencolls.SelMoveUnits.size() > 0) {ScenID = scencolls.SelMoveUnits.getFirst().getbaseunit().getScenario();}
        for (PersUniti ClearingUnit : scencolls.SelMoveUnits) {
            if (ClearingUnit.getbaseunit().IsUnitASMC()) {
                if (ClearingUnit.getFiringunit() == null) {
                    PersCreation ObjCreate = new PersCreation();
                    ClearingUnit = ObjCreate.CreatefiringUnitandProperty(ClearingUnit);
                }
                LDRM = ClearingUnit.getFiringunit().getLdrDRM();
                if (LDRM < BestLDRM) {
                    BestLDRM = LDRM;
                }
            }
            Unittype = ClearingUnit.getbaseunit().getUnittype();
            if (Unittype == Constantvalues.Utype.Hero) {
                HeroDRM += -1;
            } else if (Unittype == Constantvalues.Utype.Squad) {
                TotalUnits += 2;
            } else {
                TotalUnits += 1;
            }
            UnitClass = ClearingUnit.getbaseunit().getUnitClass();
            if (IsaSapper(UnitClass)) {
                if (Unittype == Constantvalues.Utype.Squad) {
                    SapperDRM += -2;
                } else {
                    SapperDRM += -1;
                }
            }
            if (ClearingUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Labour1) {
                LabourDRM = -1;
            } else if (ClearingUnit.getbaseunit().getMovementStatus() == Constantvalues.MovementStatus.Labour2) {
                LabourDRM = -2;
            }
        }
            double unitstest = 0.0;
        if (TotalUnits > 2) {                 // Check THIS WHOLE CALCULATION
            double A = (TotalUnits - 2) / 2;
            int B = (int)(TotalUnits - 2) /2;

            if (A > B) {
                unitstest = (-((B * 2) + 1));
            } else {
                unitstest = (-B * 2);
            }
        } else if (TotalUnits == 2) {
            UnitsDRM = -1;
        }
        UnitsDRM = (int) unitstest;  // will round down IS THIS WHAT WE WANT?
        // look for bulldozer

        FinalDRM = LabourDRM + BestLDRM + UnitsDRM;
        switch (Clearancetype) {
            case ClearRubble: case ClearRdBlk:
                FinalDRM += BullDRM;
            case ClearMine: case ClearSetDC: case ClearWire:
                FinalDRM += HeroDRM + SapperDRM;
            case ClearFlame:
                FinalDRM += BullDRM;
                ScenarioC scen = ScenarioC.getInstance();
                //FinalDRM = FinalDRM + scen.getECDRM();
        }
        //MessageBox.Show("Original Clearance DR is " & ClearDR.ToString & " with a total DRM of " & FinalDRM.ToString)
        ClearDR += FinalDRM;
        int FinalDR = ClearDR; // used for hampered flame - anything else?
        return ClearDR;
    }
    private boolean IsaSapper(Constantvalues.UClass unitclass) {
                return (unitclass == Constantvalues.UClass.SUNARMED ||
                        unitclass == Constantvalues.UClass.ASUNARMED ||
                        unitclass == Constantvalues.UClass.ASAIRBORNE ||
                        unitclass == Constantvalues.UClass.SAIRBORNE ||
                        unitclass == Constantvalues.UClass.SCONSCRIPT ||
                        unitclass == Constantvalues.UClass.SCREWCLASS ||
                        unitclass == Constantvalues.UClass.SELITE ||
                        unitclass == Constantvalues.UClass.SENGINEER ||
                        unitclass == Constantvalues.UClass.SFIRSTLINE||
                        unitclass == Constantvalues.UClass.SGREEN ||
                        unitclass == Constantvalues.UClass.SMARINE ||
                        unitclass == Constantvalues.UClass.ASCONSCRIPT ||
                        unitclass == Constantvalues.UClass.ASCREWCLASS ||
                        unitclass == Constantvalues.UClass.ASSS ||
                        unitclass == Constantvalues.UClass.ASELITE ||
                        unitclass == Constantvalues.UClass.ASENGINEER ||
                        unitclass == Constantvalues.UClass.ASFIRSTLINE ||
                        unitclass == Constantvalues.UClass.ASGREEN ||
                        unitclass == Constantvalues.UClass.ASMARINE ||
                        unitclass == Constantvalues.UClass.ASMARINEENG ||
                        unitclass == Constantvalues.UClass.ASPARTISAN ||
                        unitclass == Constantvalues.UClass.ASSECONDLINE ||
                        unitclass == Constantvalues.UClass.SMARINEENG ||
                        unitclass == Constantvalues.UClass.SSECONDLINE ||
                        unitclass == Constantvalues.UClass.SSS ||
                        unitclass == Constantvalues.UClass.SPARTISAN
                        );
            }
}
