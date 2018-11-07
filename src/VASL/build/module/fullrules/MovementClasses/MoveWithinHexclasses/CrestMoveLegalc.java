package VASL.build.module.fullrules.MovementClasses.MoveWithinHexclasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MovementClasses.MoveNewHexclasses.LegalMovei;
import VASL.build.module.fullrules.ObjectChangeClasses.WARemovec;
import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectClasses.ScenarioCollectionsc;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;

public class CrestMoveLegalc implements LegalMovei {
    private Hex hexclickedvalue;
    private Constantvalues.UMove MovementOptionvalue;
    private Location locationchangevalue;
    private String returnresultsvalue;
    private ScenarioCollectionsc Scencolls = ScenarioCollectionsc.getInstance();
    private Constantvalues.AltPos PositionChangedvalue;
    private String WALossvalue  = "";

    // constructor
    public CrestMoveLegalc(Hex passnewhexclicked, Constantvalues.UMove passmovementoption)  {
        hexclickedvalue = passnewhexclicked;
        MovementOptionvalue = passmovementoption;
        returnresultsvalue = "";
        //locationchangevalue = passhexlocationclicked;
    }
    // properties
    public String getresultsstring() {return returnresultsvalue;}
    public Location getLocationchangevalue() {return locationchangevalue;}
    // methods
    public boolean IsMovementLegal() {
        // called by MoveIntoCrest.MoveAllOK
        // determines if moves are logically possible and legal

        ConversionC confrom = new ConversionC();
        boolean DoWALoss = false;
        PersUniti MovingUnit = Scencolls.SelMoveUnits.getFirst();
        // don' t need legal test for crest status change; just need to figure out where
        // no assault move test as not changing location

        // movement is now legal, need to determine final position
        switch (MovingUnit.getbaseunit().gethexPosition()) {
            case None: // ground level - ARE THERE OTHER POSITIONS FROM WHICH A UNIT COULD MOVE INTO CRESTSTATUS WITHIN A HEX?
                if (MovementOptionvalue == Constantvalues.UMove.CrestStatus1 ||
                        MovementOptionvalue == Constantvalues.UMove.CrestStatus2 ||
                        MovementOptionvalue == Constantvalues.UMove.CrestStatus3 ||
                        MovementOptionvalue == Constantvalues.UMove.CrestStatus4 ||
                        MovementOptionvalue == Constantvalues.UMove.CrestStatus5 ||
                        MovementOptionvalue == Constantvalues.UMove.CrestStatus0) {
                    PositionChangedvalue = confrom.ConvertUMovetoAltPos(MovementOptionvalue);
                    DoWALoss = false;

                } else if (MovementOptionvalue == Constantvalues.UMove.WACrestStatus1 ||
                        MovementOptionvalue == Constantvalues.UMove.WACrestStatus2 ||
                        MovementOptionvalue == Constantvalues.UMove.WACrestStatus3 ||
                        MovementOptionvalue == Constantvalues.UMove.WACrestStatus4 ||
                        MovementOptionvalue == Constantvalues.UMove.WACrestStatus5 ||
                        MovementOptionvalue == Constantvalues.UMove.WACrestStatus0) {
                    PositionChangedvalue = confrom.ConvertUMovetoAltPos(MovementOptionvalue);
                    DoWALoss = false;

                }
                break;
            case CrestStatus1: case CrestStatus2: case CrestStatus3: case CrestStatus4: case CrestStatus5: case CrestStatus0:
            case WACrestStatus1: case WACrestStatus2: case WACrestStatus3: case WACrestStatus4: case WACrestStatus5: case WACrestStatus0:
                if (MovementOptionvalue == Constantvalues.UMove.INrate) {
                    PositionChangedvalue = Constantvalues.AltPos.None;
                    DoWALoss = true;
                } else if (MovementOptionvalue == Constantvalues.UMove.ExitCrestStatus) {
                    DoWALoss = true;
                    PositionChangedvalue = confrom.CresttoExitedCrest(MovingUnit.getbaseunit().gethexPosition());
                }
                break;
            default:
                return false;
        }
        if (DoWALoss) {
            for (PersUniti MovingUnitInSTack : Scencolls.SelMoveUnits) {
                boolean test = WACheckLoss(MovingUnitInSTack);
            }
        }
        return true;
    }
    private boolean WACheckLoss(PersUniti MovingUnit) {
        if (MovingUnit.getbaseunit().gethexPosition() == Constantvalues.AltPos.WallAdv) {
            WARemovec UnittoChange = new WARemovec(MovingUnit);
                UnittoChange.TakeAction();
                WALossvalue = "WALoss";
                return true;
        }
        return false;
    }

}
