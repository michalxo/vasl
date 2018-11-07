package VASL.build.module.fullrules.MovementClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.HexandLocation.Locationi;
import VASL.build.module.fullrules.ObjectClasses.LocationContentc;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

public class PresentTestc {
    private Locationi locationtouse;
    public PresentTestc (Locationi UsingLoc){
        locationtouse = UsingLoc;
    }
    public boolean PresentTest(boolean MovingIsMMC) {
        // called by
        // checks if visible (unconcealed, non-hidden) units block entry
        int InfUnitsize = 0;
        int VehUnitsize = 0;
        boolean OKValue = false;
        boolean AllDisruptedorUnarmed = true;
        boolean SingleSMC = true;
        boolean PrevUnit = false;
        ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
        //Constantvalues.Typetype TypeIs = ItemInHex.getTypeID)
        //Select Case TypeIs
        //Case Constantvalues.Typetype.Personnel
        // infantry
        // determine max unit size of personnel present
        ConversionC confrom = new ConversionC();
        for (PersUniti hexunit : Scencolls.Unitcol) {
            if (hexunit.getbaseunit().getHex() == locationtouse.getvaslhex() && hexunit.getbaseunit().gethexlocation() == locationtouse.getvasllocation()) {
                // skip concealed units at this point
                if (hexunit.getbaseunit().getCon_ID() > 0) {
                    continue;
                }
                int TempInfUnitsize = confrom.ConvertUnitTypetoint(hexunit.getbaseunit().getUnittype());
                if (TempInfUnitsize != InfUnitsize) {  // if = is true then no need to change InfUnitsize value
                    if (((TempInfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Squad) && TempInfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Crew)) ||
                            (TempInfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.MMC)) && ((InfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) &&
                                    InfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Commissar)) ||
                                    (InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.SMC)) ||
                                    (((InfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Squad) && InfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Crew) ||
                                            (InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.MMC) && ((TempInfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) &&
                                                    TempInfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Commissar) || TempInfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.SMC)))))))))) {
                        // MMC and SMC present so = Units
                        InfUnitsize = confrom.ConvertUnitTypetoint(Constantvalues.Utype.Units);
                    } else if ((TempInfUnitsize < confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) || TempInfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.MMC) &&
                            ((InfUnitsize < confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) || InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.MMC))))) {
                        // two types of mmc present and no SMC so = MMC
                        InfUnitsize = confrom.ConvertUnitTypetoint(Constantvalues.Utype.MMC);
                    } else if ((TempInfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) && TempInfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Commissar) ||
                            TempInfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.SMC) && ((InfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) &&
                                    InfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Commissar) || InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.SMC) ||
                                    InfUnitsize == 0)))) {
                        {
                            // two types of SMC and no MMC so = SMC
                            InfUnitsize = confrom.ConvertUnitTypetoint(Constantvalues.Utype.SMC);
                        }
                    }
                    // determine if ALL present are disrupted or unarmed(which can't block entry)
                    if (hexunit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.Disrupted || hexunit.getbaseunit().getOrderStatus() != Constantvalues.OrderStatus.DisruptedDM) {
                        AllDisruptedorUnarmed = false;
                    }
                    if (TempInfUnitsize <= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Hero) && TempInfUnitsize >= confrom.ConvertUnitTypetoint(Constantvalues.Utype.Commissar)) {
                        // check if MMC is unarmed
                        if (hexunit.getbaseunit().getUnitClass() != Constantvalues.UClass.UNARMED && hexunit.getbaseunit().getUnitClass() != Constantvalues.UClass.AUNARMED &&
                                hexunit.getbaseunit().getUnitClass() != Constantvalues.UClass.ASUNARMED && hexunit.getbaseunit().getUnitClass() != Constantvalues.UClass.SUNARMED) {
                            AllDisruptedorUnarmed = false;
                        }
                    } else { // if SMC, check if unarmed
                        // don't need to check for SMC unarmed as it is always armed (only unarmed if prisoner which does not prevent movement)
                        AllDisruptedorUnarmed = false;
                    }
                    // determine if only single smc present (does not block entry)
                    if (InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.Crew) || InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.HalfSquad) ||
                            InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.MMC) || InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.Squad) ||
                            InfUnitsize == confrom.ConvertUnitTypetoint(Constantvalues.Utype.Units) || PrevUnit == true) {
                        SingleSMC = false;
                    }
                }
            }
        /*Case Constantvalues.Typetype.Vehicle, Constantvalues.Typetype.Gun
                        'this will cause entry to be blocked - by presence of known item such as Gun or Vehicle, which cannot be disrupted nor be a SingleSMC
        AllDisruptedorUnarmed = False : SingleSMC = False
        Case Else
                        'No action required - won't get here as SW, Terrain are not present enemy and concealed are not known
        End Select*/
            PrevUnit = true;
        }
        if (AllDisruptedorUnarmed || SingleSMC) {
            // All Present are disrupted or unarmed OR only single SMC is present (and entering is MMC in this case) then entry allowed
            if (SingleSMC && !MovingIsMMC) {
                OKValue = false;  // entry blocked because MovingUnits are not MMC
            } else {
                OKValue = true;
            }
        } else {
            // otherwise blocked
            OKValue = false;
        }
        return OKValue;
    }
}
