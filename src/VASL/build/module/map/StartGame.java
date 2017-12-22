package VASL.build.module.map;


/*
This class is called by ActionsToolbar.addTo and initiates the full rules code for a scenario
*/

import VASL.LOS.Map.Hex;
import VASL.build.module.ASLMap;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.Game.*;
import VASL.build.module.fullrules.IFTCombatClasses.IFTTable;
import VASL.build.module.fullrules.IFTCombatClasses.LOBTable;
import VASL.build.module.fullrules.ObjectClasses.SMCTable;
import VASL.build.module.fullrules.ObjectClasses.SupportWeaponTable;
import VASL.build.module.fullrules.UtilityClasses.UpdateBaseunitiCommand;
import VASL.build.module.fullrules.UtilityClasses.UpdateFireunitiCommand;
import VASL.build.module.fullrules.UtilityClasses.UpdateMoveunitiCommand;
import VASL.build.module.fullrules.UtilityClasses.UpdateTargunitiCommand;
import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.GameModule;
import VASSAL.build.module.GameComponent;
import VASSAL.build.module.Map;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.build.module.map.Drawable;
import VASSAL.command.Command;
import VASSAL.command.CommandEncoder;
import VASSAL.counters.*;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;
import VASSAL.tools.DataArchive;
import VASSAL.tools.SequenceEncoder;
import VASSAL.tools.filechooser.FileChooser;
import VASSAL.tools.filechooser.FileFilter;
import VASSAL.tools.io.IOUtils;
import org.jdom2.JDOMException;

import javax.swing.*;
//import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class  StartGame extends AbstractConfigurable implements MouseListener, GameComponent, CommandEncoder, Drawable {
    private ScenarioC scen;
    private VASL.LOS.Map.Map GameMap;
    protected Map map;
    private Clicki ProcessClick;
    private ASLMap pmap;
    private boolean startsavetoggle = false;

    protected PieceFinder dragTargetSelector;
    protected PieceVisitorDispatcher selectionProcessor;
    protected Comparator<GamePiece> pieceSorter = new PieceSorter();
    private FileChooser pfilechooser;

    private static final String UPDATEBASEUNIT_COMMAND_PREFIX = "UPDATE_BASE_UNIT:";
    private static final String UPDATEFIREUNIT_COMMAND_PREFIX = "UPDATE_FIRE_UNIT:";
    private static final String UPDATEMOVEUNIT_COMMAND_PREFIX = "UPDATE_MOVE_UNIT:";
    private static final String UPDATETARGUNIT_COMMAND_PREFIX = "UPDATE_TARG_UNIT:";

    public void Initialize(boolean getgoing) {

        startsavetoggle = !startsavetoggle;

        if (startsavetoggle) {

            this.map.addLocalMouseListener(this);      //pushMouseListener(this);
            this.dragTargetSelector = this.createDragTargetSelector();
            this.selectionProcessor = this.createSelectionProcessor();
            initializeMap();
            // create classes required at start
            scen = ScenarioC.getInstance();
            CreateLookUpTables();
            // get fullrules savedgame file associated with VASL scenario
            String openfilename = "";
            FileChooser chooser = getFileChooser();
            int retrival = chooser.showOpenDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                openfilename = chooser.getSelectedFile().getAbsolutePath();
            }

            if (scen.OpenScenario(openfilename, pmap)) {

            }
        } else {
            scen.SaveScenario();
        }

    }
    private void initializeMap() {

        // make sure we have a map
        String VASLVersion = GameModule.getGameModule().getGameVersion();
        final ASLMap theMap = (ASLMap) map;
        pmap=theMap;
        if (theMap == null) {

        } else {
            GameMap = theMap.getVASLMap();
        }
    }
    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        // checks context of user click and passes info to DetermineClickPossibilities
        // initialize variables used
        boolean ItemFound = false;
        
        // First, see if anything clicked
        // get point of click and map hex
        Hex ClickedHex = setHexFromMousePressedEvent(new Point(e.getPoint()));
        if (ClickedHex == null) {return;} // within map space but not within a hex - on certain boards due to edge features
        // determine which counters, if any, have been clicked
        if (selectMovablePieces(e)) {
            Component firingcomponent = e.getComponent();
            LinkedList<GamePiece> SelectedCounters = getSelectedPieces();
            if (SelectedCounters.size() == 0) {
                ItemFound = false;
            } else {
                ItemFound = true;
            }
            // now do click events
            ProcessingClick(ItemFound, e, ClickedHex, SelectedCounters);
        } else {
            // have clicked outside a piece to trigger deselection
            //LinkedList<GamePiece> SelectedCounters = getSelectedPieces();
            //for (GamePiece clearpiece: SelectedCounters) {
            //    clearpiece.setProperty(Properties.SELECTED,false);
                // clear ITFC and Movement
                ScenarioC scen = ScenarioC.getInstance();
                if (scen.getIFT() != null) {
                    scen.getIFT().ClearCurrentIFT();
                }
            //}
        }
    }
    /**
     * Sets the Hex using a mouse-pressed event point
     * @param eventPoint the point in mouse pressed coordinates
     */
    private Hex setHexFromMousePressedEvent(Point eventPoint) {

        final Point p = mapMouseToMapCoordinates(eventPoint);
        if (p == null || !GameMap.onMap(p.x, p.y)) {return null;}
        try {
            return GameMap.gridToHex(p.x, p.y);
        } catch (Exception e) {
            return null;
        }
    }
    private Point mapMouseToMapCoordinates(Point p) {

        // just remove edge buffer
        final Point temp = new Point(p);
        temp.translate(-map.getEdgeBuffer().width, -map.getEdgeBuffer().height);
        return temp;
    }
    /**
     * Get all currently selected pieces
     *
     * @return LinkedList of selected pieces
     */
    public LinkedList<GamePiece> getSelectedPieces() {

        LinkedList<GamePiece> temp = new LinkedList<GamePiece>();


        final PieceIterator pi = new PieceIterator(GameModule.getGameModule().getGameState().getAllPieces().iterator());

        while (pi.hasMoreElements()) {
            final GamePiece piece = pi.nextPiece();

            if(piece instanceof Stack) {
                for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                    GamePiece piece2 = pi2.nextPiece();
                    if (isSelected(piece2)) {
                        if (!temp.contains(piece2)) {
                            temp.add(piece2);
                        }
                    }
                }
            }
            else  {
                if (isSelected(piece)) {
                    if (!temp.contains(piece)) {
                        temp.add(piece);
                    }
                }
            }
            /*if (isSelected(piece)) {
                temp.add(piece);
            }*/
        }
        return temp;
    }

    /**
     * @param p the piece
     * @return true if the piece is selected
     */
    private boolean isSelected(GamePiece p) {
        return Boolean.TRUE.equals(p.getProperty(Properties.SELECTED)) &&
                p.getId() != null &&
                !"".equals(p.getId());
    }
    protected boolean  selectMovablePieces(MouseEvent var1) {
        GamePiece var2 = this.map.findPiece(var1.getPoint(), this.dragTargetSelector);
        if (var2 == null) {return false;}
        if(var2 instanceof Stack) {
            for (PieceIterator pi2 = new PieceIterator(((Stack) var2).getPiecesIterator()); pi2.hasMoreElements(); ) {
                GamePiece piece2 = pi2.nextPiece();
                piece2.setProperty(Properties.SELECTED,true);
            }
        }
        else  {
            var2.setProperty(Properties.SELECTED,true);
        }
        //var2.setProperty(Properties.SELECTED,true);
        //this.dragBegin = var1.getPoint();
        /*if (var2 != null) {
            EventFilter var3 = (EventFilter)var2.getProperty("moveEventFilter");
            if (var3 != null && var3.rejectEvent(var1)) {
                DragBuffer.getBuffer().clear();
            } else {
                this.selectionProcessor.accept(var2);
            }
        } else {
            DragBuffer.getBuffer().clear();
        }*/
        this.map.repaint();
        return true;
    }
    protected PieceFinder createDragTargetSelector() {
        return new PieceFinder.Movable() {
            public Object visitDeck(Deck var1) {
                Point var2 = var1.getPosition();
                Point var3 = new Point(this.pt.x - var2.x, this.pt.y - var2.y);
                return var1.boundingBox().contains(var3) && var1.getPieceCount() > 0 ? var1 : null;
            }
        };
    }
    protected PieceVisitorDispatcher createSelectionProcessor() {
        return new DeckVisitorDispatcher(new DeckVisitor() {
            public Object visitDeck(Deck var1) {
                DragBuffer.getBuffer().clear();
                PieceIterator var2 = var1.drawCards();

                while(var2.hasMoreElements()) {
                    DragBuffer.getBuffer().add(var2.nextPiece());
                }

                return null;
            }

            public Object visitStack(Stack var1) {
                DragBuffer.getBuffer().clear();
                int var2 = 0;

                int var3;
                for(var3 = 0; var3 < var1.getPieceCount(); ++var3) {
                    if (Boolean.TRUE.equals(var1.getPieceAt(var3).getProperty("Selected"))) {
                        ++var2;
                    }
                }

                GamePiece var4;
                if (!((Boolean)GameModule.getGameModule().getPrefs().getValue("movingStacksPickupUnits")).booleanValue() && var1.getPieceCount() != 1 && var1.getPieceCount() != var2) {
                    for(var3 = 0; var3 < var1.getPieceCount(); ++var3) {
                        var4 = var1.getPieceAt(var3);
                        if (Boolean.TRUE.equals(var4.getProperty("Selected"))) {
                            DragBuffer.getBuffer().add(var4);
                        }
                    }
                } else {
                    DragBuffer.getBuffer().add(var1);
                }

                if (KeyBuffer.getBuffer().containsChild(var1)) {
                    KeyBuffer.getBuffer().sort(pieceSorter);
                    Iterator var5 = KeyBuffer.getBuffer().getPiecesIterator();

                    while(var5.hasNext()) {
                        var4 = (GamePiece)var5.next();
                        if (var4.getParent() != var1) {
                            DragBuffer.getBuffer().add(var4);
                        }
                    }
                }

                return null;
            }

            public Object visitDefault(GamePiece var1) {
                DragBuffer.getBuffer().clear();
                if (KeyBuffer.getBuffer().contains(var1)) {
                    KeyBuffer.getBuffer().sort(pieceSorter);
                    Iterator var2 = KeyBuffer.getBuffer().getPiecesIterator();

                    while(var2.hasNext()) {
                        DragBuffer.getBuffer().add((GamePiece)var2.next());
                    }
                } else {
                    DragBuffer.getBuffer().clear();
                    DragBuffer.getBuffer().add(var1);
                }

                return null;
            }
        });
    }
    private void ProcessingClick(boolean ItemFound, MouseEvent e, Hex ClickedHex, LinkedList<GamePiece> SelectedCounters) {
        boolean leftbutton = false; boolean rightbutton = false; 
        switch(e.getModifiers()) {
            case InputEvent.BUTTON1_MASK: {
                leftbutton = true;
                break;
            }
            case InputEvent.BUTTON3_MASK: {
                rightbutton = true;
                break;
            }
        }
        if (rightbutton &&  e.isAltDown() && ItemFound) {
            // Right - Alt on counter: changing ontop (visible) counter to next one in collection
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickRightAltSetupC;
                case Rally:
                    ProcessClick = new ClickRightAltRallyC;
                case PrepFire:
                    ProcessClick = new ClickRightAltPrepC;
                case Movement:
                    ProcessClick = new ClickRightAltMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickRightAltDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickRightAltAdvancingC;
                case Rout:
                    ProcessClick = new ClickRightAltRoutC;
                case Advance:
                    ProcessClick = new ClickRightAltAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickRightAltCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickRightAltRefitC;
            }*/
        }else if(rightbutton &&  e.isAltDown() && !ItemFound) {
            // Right - Alt, no counter:
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickRightAltNothingSetupC;
                case Rally:
                    ProcessClick = new ClickRightAltnothingRallyC;
                case PrepFire:
                    ProcessClick = new ClickRightAltnothingPrepC;
                case Movement:
                    ProcessClick = new ClickRightAltNothingMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickRightAltNothingDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickRightAltNothingAdvancingC;
                case Rout:
                    ProcessClick = new ClickRightAltNothingRoutC;
                case Advance:
                    ProcessClick = new ClickRightAltNothingAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickRightAltNothingCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickRightAltNothingRefitC;
            }*/
        } else if (rightbutton &&  e.isControlDown() && ItemFound) {
            // 'Right - Control on unit: Context Popup for all counters in hex
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickRightCtlSetupC;
                case Rally:
                    ProcessClick = new ClickRightCtlRallyC;
                case PrepFire:
                    ProcessClick = new ClickRightCtlPrepC;
                case Movement:
                    ProcessClick = new ClickRightCtlMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickRightCtlDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickRightCtlAdvancingC;
                case Rout:
                    ProcessClick = new ClickRightCtlRoutC;
                case Advance:
                    ProcessClick = new ClickRightCtlAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickRightCtlCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickRightCtlRefitC;
            }*/
        } else if (rightbutton &&  e.isShiftDown() && ItemFound) {
            // Right - Shift on unit: context popup for all items in hex location
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickRightShiftSetupC;
                case Rally:
                    ProcessClick = new ClickRightShiftRallyC;
                case PrepFire:
                    ProcessClick = new ClickRightShiftPrepC;
                case Movement:
                    ProcessClick = new ClickRightShiftMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickRightShiftDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickRightShiftAdvancingC;
                case Rout:
                    ProcessClick = new ClickRightShiftRoutC;
                case Advance:
                    ProcessClick = new ClickRightShiftAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickRightShiftCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickRightShiftRefitC;
            }*/
        } else if (rightbutton &&  ItemFound) {
            // Right on unit - show pop up menu for ontop item - includes LOSShade
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickRightSetupC;
                case Rally:
                    ProcessClick = new ClickRightRallyC;
                case PrepFire:
                    ProcessClick = new ClickRightPrepC;
                case Movement:
                    ProcessClick = new ClickRightMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickRightDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickRightAdvancingC;
                case Rout:
                    ProcessClick = new ClickRightRoutC;
                case Advance:
                    ProcessClick = new ClickRightAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickRightCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickRightRefitC;
            }*/
        } else if (rightbutton &&  !ItemFound) {
            // Right - Nothing on map: Show menu for terrain
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickRightNothingSetupC;
                case Rally:
                    ProcessClick = new ClickRightNothingRallyC;
                case PrepFire:
                    ProcessClick = new ClickRightNothingPrepC;
                case Movement:
                    ProcessClick = new ClickRightNothingMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickRightNothingDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickRightNothingAdvancingC;
                case Rout:
                    ProcessClick = new ClickRightNothingRoutC;
                case Advance:
                    ProcessClick = new ClickRightNothingAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickRightNothingCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickRightNothingRefitC;
            }*/
        } else if (leftbutton &&  e.isShiftDown() && ItemFound) {
            // Left - Shift on units: switchs all units in hex location
            switch (scen.getPhase()) {
                /*case Setup:
                    ProcessClick = new ClickLeftShiftSetupC;
                case Rally:
                    ProcessClick = new ClickLeftShiftRallyC;*/
                case PrepFire:
                    ProcessClick = new ClickLeftShiftPrepC();
                    break;
                /*case Movement:
                    ProcessClick = new ClickLeftShiftMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickLeftShiftDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickLeftShiftAdvancingC;
                case Rout:
                    ProcessClick = new ClickLeftShiftRoutC;
                case Advance:
                    ProcessClick = new ClickLeftShiftAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickLeftShiftCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickLeftShiftRefitC;*/
            }
        } else if (leftbutton &&  e.isControlDown() && ItemFound) {
            // Left - Control on units: switchs all units in hex
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickLeftCtlSetUpC;
                case Rally:
                    ProcessClick = new ClickLeftCtlRallyC;
                case PrepFire:
                    ProcessClick = new ClickLeftCtlPrepC;
                case Movement:
                    ProcessClick = new ClickLeftCtlMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickLeftCtlDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickLeftCtlAdvancingC;
                case Rout:
                    ProcessClick = new ClickLeftCtlRoutC;
                case Advance:
                    ProcessClick = new ClickLeftCtlAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickLeftCtlCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickLeftCtlRefitC;
            }*/
        } else if(leftbutton &&  e.isShiftDown() && !ItemFound) {
            // Left - Shift on Map: opens terrain help
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickLeftShiftNothingSetupC;
                case Rally:
                    ProcessClick = new ClickLeftShiftNothingRallyC;
                case PrepFire:
                    ProcessClick = new ClickLeftShiftNothingPrepC;
                case Movement:
                    ProcessClick = new ClickLeftShiftNothingMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickLeftShiftNothingDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickLeftShiftNothingAdvancingC;
                case Rout:
                    ProcessClick = new ClickLeftShiftNothingRoutC;
                case Advance:
                    ProcessClick = new ClickLeftShiftNothingAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickLeftShiftNothingCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickLeftShiftNothingRefitC;
            }*/
        }else if (leftbutton &&  e.isAltDown() && !ItemFound) {
            // Left - Alt on Map:

        } else if (leftbutton &&  e.isAltDown() && ItemFound) {
            // Left - Alt on counter:
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickLeftAltSetupC;
                case Rally:
                    ProcessClick = new ClickLeftAltRallyC;
                case PrepFire:
                    ProcessClick = new ClickLeftAltPrepC;
                case Movement:
                    ProcessClick = new ClickLeftAltMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickLeftAltDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickLeftAltAdvancingC;
                case Rout:
                    ProcessClick = new ClickLeftAltRoutC;
                case Advance:
                    ProcessClick = new ClickLeftAltAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickLeftAltCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickLeftAltRefitC;
            }*/
        } else if (leftbutton &&  !ItemFound) {
            // Left on Map: triggers move
            /*switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickLeftNothingSetupC;
                case Rally:
                    ProcessClick = new ClickLeftNothingRallyC;
                case PrepFire:
                    ProcessClick = new ClickLeftNothingPrepC;
                case Movement:
                    ProcessClick = new ClickLeftNothingMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickLeftNothingDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickLeftNothingAdvancingC;
                case Rout:
                    ProcessClick = new ClickLeftNothingRoutC;
                case Advance:
                    ProcessClick = new ClickLeftNothingAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickLeftNothingCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickLeftNothingRefitC;
            }*/
        } else if (leftbutton &&   ItemFound) {
            // Left on Unit: selecting ontop unit
            switch (scen.getPhase()) {
                case Setup:
                    ProcessClick = new ClickLeftSetupC();
                /*case Rally:
                    ProcessClick = new ClickLeftRallyC;*/
                case PrepFire:
                    ProcessClick = new ClickLeftPrepC();
                    break;
               /* case Movement:
                    ProcessClick = new ClickLeftMovementC;
                case DefensiveFire:
                    ProcessClick = new ClickLeftDefensiveC;
                case AdvancingFire:
                    ProcessClick = new ClickLeftAdvancingC;
                case Rout:
                    ProcessClick = new ClickLeftRoutC;
                case Advance:
                    ProcessClick = new ClickLeftAdvanceC;
                case CloseCombat:
                    ProcessClick = new ClickLeftCloseCombatC;
                case Refitphase:
                    ProcessClick = new ClickLeftRefitC;*/
            }
        } else {
            return; // nothing happens
        }

        // test code
        if (ProcessClick==null) {return;}
        //
        ProcessClick.DetermineClickPossibilities(ClickedHex, SelectedCounters);
    }
    public Command decode(String command) {

        SequenceEncoder.Decoder sdcr = null;
        if(command.startsWith("UPDATE_BASE_UNIT:")) {
            sdcr = new SequenceEncoder.Decoder(command, '\t');
            sdcr.nextToken();  // passover first token which is identifier string (ie "UPDATE_BASE_UNIT:")
            String pHexname = sdcr.nextToken();
            int pScenario = Integer.parseInt(sdcr.nextToken());
            int phexlocation = Integer.parseInt(sdcr.nextToken());
            int phexPosition = Integer.parseInt(sdcr.nextToken());
            int pLevelinHex = Integer.parseInt(sdcr.nextToken());
            String pCX = sdcr.nextToken();
            int pELR = Integer.parseInt(sdcr.nextToken());
            int pTurnArrives = Integer.parseInt(sdcr.nextToken());
            int pNationality = Integer.parseInt(sdcr.nextToken());
            int pCon_ID = Integer.parseInt(sdcr.nextToken());
            int pUnit_ID = Integer.parseInt(sdcr.nextToken());
            int pTypeType_ID = Integer.parseInt(sdcr.nextToken());
            int pSW = Integer.parseInt(sdcr.nextToken());
            int pFirstSWLink = Integer.parseInt(sdcr.nextToken());
            int pSecondSWlink = Integer.parseInt(sdcr.nextToken());
            int pHexEntSideCrossed = Integer.parseInt(sdcr.nextToken());
            int pSolID = Integer.parseInt(sdcr.nextToken());
            String pUnitName = sdcr.nextToken();
            int pLOBLink = Integer.parseInt(sdcr.nextToken());
            int pMovementStatus = Integer.parseInt(sdcr.nextToken());
            int pFortitudeStatus = Integer.parseInt(sdcr.nextToken());
            int pOrderStatus = Integer.parseInt(sdcr.nextToken());
            int pVisibilityStatus = Integer.parseInt(sdcr.nextToken());
            int pCombatStatus = Integer.parseInt(sdcr.nextToken());
            String pPinned = sdcr.nextToken();
            int pUnitClass = Integer.parseInt(sdcr.nextToken());
            int pCharacterStatus = Integer.parseInt(sdcr.nextToken());
            int pUnitType = Integer.parseInt(sdcr.nextToken());
            int pRoleStatus = Integer.parseInt(sdcr.nextToken());

            return new UpdateBaseunitiCommand(pHexname, pScenario, phexlocation,
                    phexPosition, pLevelinHex, pCX, pELR, pTurnArrives, pNationality,
                    pCon_ID, pUnit_ID, pTypeType_ID, pSW, pFirstSWLink, pSecondSWlink,
                    pHexEntSideCrossed, pSolID, pUnitName, pLOBLink, pMovementStatus,
                    pFortitudeStatus, pOrderStatus, pVisibilityStatus, pCombatStatus,
                    pPinned, pUnitClass, pCharacterStatus, pUnitType, pRoleStatus);

        } else if(command.startsWith("UPDATE_FIRE_UNIT:")){
            sdcr = new SequenceEncoder.Decoder(command, '\t');
            sdcr.nextToken();  // passover first token which is identifier string (ie "UPDATE_BASE_UNIT:")
            int myCombatStatus = Integer.parseInt(sdcr.nextToken());
            String myIsEncirc = sdcr.nextToken();
            String myCX = sdcr.nextToken();
            String myIsPinned = sdcr.nextToken();
            int myCombatFP = Integer.parseInt(sdcr.nextToken());
            int mySolID = Integer.parseInt(sdcr.nextToken());
            String myHasMG = sdcr.nextToken();
            String myUsingInherentFP = sdcr.nextToken();
            String myUsingfirstMG = sdcr.nextToken();
            String myUsingsecondMG = sdcr.nextToken();
            String myIsInCrestStatus = sdcr.nextToken();
            int myhexposition = Integer.parseInt(sdcr.nextToken());
            int myUseHeroOrLeader = Integer.parseInt(sdcr.nextToken());
            int myOBLink = Integer.parseInt(sdcr.nextToken());
            int MyLoc = Integer.parseInt(sdcr.nextToken());

            return new UpdateFireunitiCommand(myCombatStatus, myIsEncirc, myCX, myIsPinned,
                myCombatFP, mySolID, myHasMG, myUsingInherentFP, myUsingfirstMG, myUsingsecondMG,
                myIsInCrestStatus, myhexposition, myUseHeroOrLeader, myOBLink, MyLoc);

        } else if(command.startsWith("UPDATE_MOVE_UNIT:")){
            sdcr = new SequenceEncoder.Decoder(command, '\t');
            sdcr.nextToken();  // passover first token which is identifier string (ie "UPDATE_BASE_UNIT:")
            // need to fully implement

        } else if(command.startsWith("UPDATE_TARG_UNIT:")){
            sdcr = new SequenceEncoder.Decoder(command, '\t');
            sdcr.nextToken();  // passover first token which is identifier string (ie "UPDATE_BASE_UNIT:")
        } else  {
            return null;
        }

    }

    public String encode(Command c) {

        if (c instanceof UpdateBaseunitiCommand) {
            UpdateBaseunitiCommand ubuc = (UpdateBaseunitiCommand) c;
            SequenceEncoder ubucencoder = new SequenceEncoder(ubuc.pHexname, '\t');
            ubucencoder.append(ubuc.pScenario).append(ubuc.phexlocation).append(
                ubuc.phexPosition).append(ubuc.pLevelinHex).append(ubuc.pCX).append(
                ubuc.pELR).append(ubuc.pTurnArrives).append(ubuc.pNationality).append(
                ubuc.pCon_ID).append(ubuc.pUnit_ID).append(ubuc.pTypeType_ID).append(
                ubuc.pSW).append(ubuc.pFirstSWLink).append(ubuc.pSecondSWlink).append(
                ubuc.pHexEntSideCrossed).append(ubuc.pSolID).append(ubuc.pUnitName).append(
                ubuc.pLOBLink).append(ubuc.pMovementStatus).append(ubuc.pFortitudeStatus).append(
                ubuc.pOrderStatus).append(ubuc.pVisibilityStatus).append(
                ubuc.pCombatStatus).append(ubuc.pPinned).append(ubuc.pUnitClass).append(
                ubuc.pCharacterStatus).append(ubuc.pUnitType).append(ubuc.pRoleStatus);
            return UPDATEBASEUNIT_COMMAND_PREFIX +  "\t" + ubucencoder.getValue();
        } else if (c instanceof UpdateFireunitiCommand) {
            UpdateFireunitiCommand ufuc = (UpdateFireunitiCommand) c;
            SequenceEncoder ufucencoder = new SequenceEncoder(Integer.toString(ufuc.myCombatStatus), '\t');
            ufucencoder.append(ufuc.myIsEncirc).append(ufuc.myCX).append(ufuc.myIsPinned).append(
                ufuc.myCombatFP).append(ufuc.mySolID).append(ufuc.myHasMG).append(ufuc.myUsingInherentFP).append(
                ufuc.myUsingfirstMG).append(ufuc.myUsingsecondMG).append(ufuc.myIsInCrestStatus).append(
                ufuc.myhexposition).append(ufuc.myUseHeroOrLeader).append(ufuc.myOBLink).append(
                ufuc.MyLoc);
            return UPDATEFIREUNIT_COMMAND_PREFIX + "\t" + ufucencoder.getValue();
        } else if (c instanceof UpdateMoveunitiCommand) {
            UpdateMoveunitiCommand umuc = (UpdateMoveunitiCommand) c;
            // need to fully implement umuc
            SequenceEncoder umucencoder = new SequenceEncoder("umuc.myCombatStatus", '\t');

            return UPDATEMOVEUNIT_COMMAND_PREFIX + "\t" + umucencoder.getValue();
        } else if (c instanceof UpdateTargunitiCommand) {
            UpdateTargunitiCommand utuc = (UpdateTargunitiCommand) c;
            SequenceEncoder utucencoder = new SequenceEncoder(utuc.myName, '\t');
            utucencoder.append(utuc.myFirerSAN).append(utuc.myAttackedbydrm).append(utuc.myAttackedbyFP).append(
                utuc.myELR5).append(utuc.myFortitudeStatus).append(utuc.myIFTResult).append(utuc.myIsConceal).append(
                utuc.myMovementStatus).append(utuc.myOrderStatus).append(utuc.myPinned).append(utuc.myQualityStatus).append(
                utuc.myRandomSelected).append(utuc.mySmoke).append(utuc.myVisibilityStatus).append(
                utuc.myPersUnitImpact).append(utuc.mySanActivated).append(utuc.myIFTResolved).append(
                utuc.myELR).append(utuc.myMCNum).append(utuc.myTargSTackLdrdrm).append(utuc.myHOBFlag).append(
                utuc.myCombatResultsString)      ;

            return UPDATETARGUNIT_COMMAND_PREFIX + "\t" + utucencoder.getValue();
        } else {
            return null;
        }

    }
    public Class[] getAllowableConfigureComponents() {
        return new Class[0];
    }
    @Override
    public Class<?>[] getAttributeTypes() {return new Class<?>[] {};}

    @Override
    public String[] getAttributeNames() {return new String[0];}

    @Override
    public String[] getAttributeDescriptions() {return new String[0];}

    @Override
    public String getAttributeValueString(String key) {return null;}

    @Override
    public void setAttribute(String key, Object value) {}
    public void addTo(Buildable parent) {
        // add this component to the game
        if (parent instanceof Map) {
            this.map = (Map) parent;
            map.addDrawComponent(this);
        }
    }
    public void removeFrom(Buildable parent) {}

    //@Override
    public HelpFile getHelpFile() {return null;}
    public void draw(Graphics g, Map map) {
       // if ((m_Toolbar != null) && (m_Toolbar.isVisible()));
        //m_objAQH.draw(g, map, m_enToolbarPositionActions);
    }
    //@Override
    public boolean drawAboveCounters() {return true;}
    public void setup(boolean gameStarting) {
    }
    public Command getRestoreCommand() {
        return null;
    }

    private void CreateLookUpTables(){
        //IFT Table
        InputStream inputStream = null;
        IFTTable ifttable = new IFTTable();
        final String IFTTableFileName = "fullrulesdata/IFTTable.xml"; // name of the shared board metadata file
        try {
            DataArchive archive = GameModule.getGameModule().getDataArchive();
            // IFT Table metadata
            inputStream = archive.getInputStream(IFTTableFileName);

            ifttable.parseIFTTableFile(inputStream);
        } catch (IOException e){

        } catch (JDOMException e) {
            //sharedBoardMetadata = null;
            //throw new JDOMException("Cannot read the shared metadata file", e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        scen.setIFTTableLookUp(ifttable.getIFTTableTypes());

        // LOB Table
        inputStream = null;
        LOBTable lobtable = new LOBTable();
        final String LOBTableFileName = "fullrulesdata/LOBTable.xml"; // name of the LOB metadata file
        try {
            DataArchive archive = GameModule.getGameModule().getDataArchive();
            // LOB Table metadata
            inputStream = archive.getInputStream(LOBTableFileName);

            lobtable.parseLOBTableFile(inputStream);
        } catch (IOException e){

        } catch (JDOMException e) {
            //sharedBoardMetadata = null;
            //throw new JDOMException("Cannot read the line of battle metadata file", e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        scen.setLOBTableLookUp(lobtable.getLOBTableTypes());
        // SMC Table
        inputStream = null;
        SMCTable smctable = new SMCTable();
        final String SMCTableFileName = "fullrulesdata/SMCTable.xml"; // name of the leader metadata file
        try {
            DataArchive archive = GameModule.getGameModule().getDataArchive();
            // SMC Table metadata
            inputStream = archive.getInputStream(SMCTableFileName);

            smctable.parseSMCTableFile(inputStream);
        } catch (IOException e){

        } catch (JDOMException e) {
            //sharedBoardMetadata = null;
            //throw new JDOMException("Cannot read the leader metadata file", e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        scen.setSMCTableLookUp(smctable.getSMCTableTypes());
        // LOB SupportWeapon Table
        inputStream = null;
        SupportWeaponTable supportweapontable = new SupportWeaponTable();
        final String SupportWeaponTableFileName = "fullrulesdata/SuppWTable.xml"; // name of the SupportWeapon metadata file
        try {
            DataArchive archive = GameModule.getGameModule().getDataArchive();
            // SW Table metadata
            inputStream = archive.getInputStream(SupportWeaponTableFileName);

            supportweapontable.parseSuppWTableFile(inputStream);
        } catch (IOException e){

        } catch (JDOMException e) {
            //sharedBoardMetadata = null;
            //throw new JDOMException("Cannot read the shared metadata file", e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        scen.setSupportWeaponTableLookUp(supportweapontable.getSuppWTableTypes());
    }
    private FileChooser getFileChooser(){
        if (pfilechooser != null){
            return pfilechooser;
        } else {
            pfilechooser = getfilechooser();
            FileFilter newfilter = new FileFilter() {

                public String getDescription() {return "Text Documents (*.txt)";}
                public boolean accept(File f) {return f.getName().toLowerCase().endsWith(".txt");}
            };
            pfilechooser.setFileFilter(newfilter);
            pfilechooser.setCurrentDirectory(GameModule.getGameModule().getGameState().getSavedGameDirectoryPreference().getFileValue());
            return pfilechooser;
        }
    }
    public FileChooser getfilechooser() {
        FileChooser newfilechooser = null;
        if (this.pfilechooser == null) {
             newfilechooser = FileChooser.createFileChooser(GameModule.getGameModule().getFrame(), GameModule.getGameModule().getGameState().getSavedGameDirectoryPreference());
        } else {
            //this.pfilechooser.resetChoosableFileFilters();
            //this.pfilechooser.rescanCurrentDirectory();
        }

        return newfilechooser;
    }

}
