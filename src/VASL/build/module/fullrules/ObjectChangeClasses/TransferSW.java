package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.ObjectClasses.SuppWeapi;
import VASSAL.build.GameModule;

public class TransferSW implements UnitChangei {
    private PersUniti Gettingunit;
    private SuppWeapi TransSW;
    private PersUniti GivingUnit;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();

    public TransferSW(PersUniti Addingunit, PersUniti LosingUnit) {
        GivingUnit = LosingUnit;
        Gettingunit = Addingunit;
    }

    public boolean TakeAction(){
        //transfers SW from one unit to another
        // set transfer variables
        int FirstTransfer = GivingUnit.getbaseunit().getFirstSWLink();
        int SecondTransfer = GivingUnit.getbaseunit().getSecondSWLink();
        int FirstSW = 0; int SecondSW = 0;
        //GivingUnit drops SW
        if (FirstTransfer > 0) {
            GivingUnit.getbaseunit().setFirstSWLink(0);
            GivingUnit.getbaseunit().setnumSW(GivingUnit.getbaseunit().getnumSW() - 1);
        }
        if (SecondTransfer > 0) {
            GivingUnit.getbaseunit().setSecondSWLink(0);
            GivingUnit.getbaseunit().setnumSW(GivingUnit.getbaseunit().getnumSW() - 1);
        }
        // GettingUnit picks it up
        if (Gettingunit.getbaseunit().getFirstSWLink() == 0 && FirstTransfer > 0) {
            Gettingunit.getbaseunit().setFirstSWLink(FirstTransfer);
            FirstSW = FirstTransfer;
            FirstTransfer = -1;
            Gettingunit.getbaseunit().setnumSW(Gettingunit.getbaseunit().getnumSW() + 1);
        } else if (Gettingunit.getbaseunit().getFirstSWLink() == 0 && SecondTransfer > 0) {
            Gettingunit.getbaseunit().setFirstSWLink(SecondTransfer);
            SecondSW = SecondTransfer;
            SecondTransfer = -1;
            Gettingunit.getbaseunit().setnumSW(Gettingunit.getbaseunit().getnumSW() + 1);
        }
        if (Gettingunit.getbaseunit().getSecondSWLink() == 0 && FirstTransfer > 0) {
            Gettingunit.getbaseunit().setSecondSWLink(FirstTransfer);
            FirstSW = FirstTransfer;
            FirstTransfer = -1;
            Gettingunit.getbaseunit().setnumSW(Gettingunit.getbaseunit().getnumSW() + 1);
        } else if (Gettingunit.getbaseunit().getSecondSWLink() == 0 && SecondTransfer > 0) {
            Gettingunit.getbaseunit().setSecondSWLink(SecondTransfer);
            SecondSW = SecondTransfer;
            SecondTransfer = -1;
            Gettingunit.getbaseunit().setnumSW(Gettingunit.getbaseunit().getnumSW() + 1);
        }
        // update SW ownership and info
        if (FirstSW > 0) {
            for (SuppWeapi findSW : Scencolls.SWCol) {
                if (findSW.getbaseSW().getSW_ID() == FirstSW) {
                    TransSW = findSW;
                    TransSW.getbaseSW().setOwner(Gettingunit.getbaseunit().getUnit_ID());
                    TransSW.getbaseSW().sethexlocation(Gettingunit.getbaseunit().gethexlocation());
                    TransSW.getbaseSW().sethexposition(Gettingunit.getbaseunit().gethexPosition());
                    if (TransSW.getbaseSW().getNationality() == Gettingunit.getbaseunit().getNationality()) {
                        TransSW.getbaseSW().setCaptured(false);
                    } else {
                        TransSW.getbaseSW().setCaptured(true);
                    }
                    TransSW.getbaseSW().UpdateSWStatus(TransSW);
                    GameModule.getGameModule().getChatter().send (GivingUnit.getbaseunit().getUnitName() + " transfers " +
                            TransSW.getbaseSW().getUnitName() + " to " + Gettingunit.getbaseunit().getUnitName());
                }
            }
        }
        if (SecondSW > 0) {
            for (SuppWeapi findSW : Scencolls.SWCol) {
                if (findSW.getbaseSW().getSW_ID() == SecondSW) {
                    TransSW = findSW;
                    TransSW.getbaseSW().setOwner(Gettingunit.getbaseunit().getUnit_ID());
                    TransSW.getbaseSW().sethexlocation(Gettingunit.getbaseunit().gethexlocation());
                    TransSW.getbaseSW().sethexposition(Gettingunit.getbaseunit().gethexPosition());
                    if (TransSW.getbaseSW().getNationality() == Gettingunit.getbaseunit().getNationality()) {
                        TransSW.getbaseSW().setCaptured(false);
                    } else {
                        TransSW.getbaseSW().setCaptured(true);
                    }
                    TransSW.getbaseSW().UpdateSWStatus(TransSW);
                    GameModule.getGameModule().getChatter().send (GivingUnit.getbaseunit().getUnitName() + " transfers " +
                            TransSW.getbaseSW().getUnitName() + " to " + Gettingunit.getbaseunit().getUnitName());
                }
            }
        }

        // check transfers worked
        if (FirstTransfer <= 0 && SecondTransfer <= 0) {return true;}
        // if here, transfer didn't work
        // unpossess  - is this necessary or should just return false?
        CreateUnpossessedSW UnpossSWcreate = new CreateUnpossessedSW();
        if (FirstTransfer > 0 ) {
            for (SuppWeapi findSW : Scencolls.SWCol) {
                if (findSW.getbaseSW().getSW_ID() == FirstTransfer) {
                    TransSW = findSW;
                    UnpossSWcreate.CreateNewUnpossessed(TransSW, GivingUnit.getbaseunit().getHex());
                    TransSW.getbaseSW().setOwner(0);
                }
            }
        }
        if (SecondTransfer > 0 ) {
            for (SuppWeapi findSW : Scencolls.SWCol) {
                if (findSW.getbaseSW().getSW_ID() == SecondTransfer) {
                    TransSW = findSW;
                    UnpossSWcreate.CreateNewUnpossessed(TransSW, GivingUnit.getbaseunit().getHex());
                    TransSW.getbaseSW().setOwner(0);
                }
            }
        }
        return false;  // should it be true or false?
    }
}
