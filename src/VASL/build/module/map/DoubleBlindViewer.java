/*
 * $Id: DoubleBlindViewer 3/30/14 davidsullivan1 $
 *
 * Copyright (c) 2014 by David Sullivan
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License (LGPL) as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, copies are available
 * at http://www.opensource.org.
 */
package VASL.build.module.map;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.LOSResult;
import VASL.LOS.Map.Location;
import VASL.LOS.VASLGameInterface;
import VASL.build.module.ASLMap;
import VASL.counters.ASLProperties;
import VASSAL.build.AbstractConfigurable;
import VASSAL.build.AutoConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.GameModule;
import VASSAL.build.module.GameComponent;
import VASSAL.build.module.Map;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.build.module.map.Drawable;
import VASSAL.chat.SynchCommand;
import VASSAL.command.Command;
import VASSAL.command.CommandEncoder;
import VASSAL.command.NullCommand;
import VASSAL.configure.*;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;
import VASSAL.i18n.TranslatableConfigurerFactory;
import VASSAL.tools.FormattedString;
import VASSAL.tools.LaunchButton;
import VASSAL.tools.NamedKeyStroke;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import static VASL.build.module.map.boardPicker.ASLBoard.DEFAULT_HEX_HEIGHT;
import static VASSAL.build.GameModule.getGameModule;

/**
 * This component filters the players view of the board so only units in the player's LOS are shown.
 */
public class DoubleBlindViewer extends AbstractConfigurable implements CommandEncoder, Drawable, ActionListener, GameComponent {

    private static final String COMMAND_SEPARATOR = ":";
    private static final String COMMAND_PREFIX = "DOUBLE_BLIND" + COMMAND_SEPARATOR;
    private static final String ENABLE_COMMAND_PREFIX = "ENABLE_DOUBLE_BLIND" + COMMAND_SEPARATOR;

    // VASSAL attribute codes
    private static final String PROPERTY_TAB = "propertiesTab"; // properties tab name
    private static final String REPORT_FORMAT = "reportFormat"; // report DB updates?
    private static final String REPORT = "report";              // chatter string when DB update reported
    private static final String TEXT_ICON = "DB Synch";
    private static final String PLAYER_NAME_PROPERTY = "RealName";

    // piece dynamic property constants
    private static final String OWNER_PROPERTY = ASLProperties.OWNER; // contains the player name of the piece owner
    private static final String SPOTTED_PROPERTY = ASLProperties.SPOTTED; // set to spotted state

    // button attribute codes
    private static final String LABEL = "label";
    private static final String TOOLTIP = "tooltip";
    private static final String HOT_KEY = "hotKey";
    private static final String ICON_NAME = "iconName";

    // attributes
    private String propertyTab = "LOS";
    protected boolean report = true;
    private FormattedString reportFormat = new FormattedString(TEXT_ICON);

    // save the old preference setting - these are disabled during DB play
    private Boolean oldCenterOnMove = Boolean.TRUE;
    private Boolean oldAutoReport = Boolean.TRUE;

    // class properties
    private static boolean enabled = false;
    private static ASLMap map;
    private LaunchButton launchButton;
    private String myPlayerName;
    private VASLGameInterface VASLGameInterface;
    private HashSet<String> players = new HashSet<String>(); // set of all players in the game
    private HashSet<Hex> spottedCounters = new HashSet<Hex>(); // set of counters that pop into view on synch

    // animation control - for drawing the red circles around spotted units
    final private static int CIRCLE_DURATION = 2000;
    private Boolean active = false;
    private Timer timer;

    public DoubleBlindViewer() {

        // initialize the button action listener
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonAction();
            }
        };
        launchButton = new LaunchButton("", TOOLTIP, LABEL, HOT_KEY, ICON_NAME, al);
        launchButton.setAttribute(TOOLTIP, "Update DB View");
        launchButton.setEnabled(false); // button inactive unless DB explicitly enabled.
        launchButton.setMargin(new Insets(0,0,0,0));
    }

    /**
     * Enable/disable DB play
     * @param e enabled?
     */
    void enableDB(boolean e) {

        if(enabled && !e) {
            getGameModule().getChatter().send("Double blind play has been disabled for this game");
        }
        else if(!enabled && e) {
            getGameModule().getChatter().send("Double blind play has been enabled for this game");
        }

        enabled = e;
        launchButton.setEnabled(e);
    }

    /**
     * @return true if DB play has been enabled
     */
    public static boolean isEnabled() {
        return enabled;
    }

    /**
     * Update the map
      * @param m the map
     */
    public void setMap(ASLMap m) {
        map = m;
    }

    /**
     * Sends the command and updates the view when the button or button hot key is pressed
     */
    private void buttonAction() {

        // warn only if the DB preference is turned off
        if(!enabled) {
            getGameModule().getChatter().send("Double blind is not enabled on this game");
        }
        else {
            GameModule.getGameModule().sendAndLog(new DoubleBlindUpdateCommand(myPlayerName));
            if(report) {
                GameModule.getGameModule().getChatter().send(TEXT_ICON);
            }
            updateView();
        }
    }

    /**
     * Updates the player's view of the map, hiding pieces that are out of LOS
     */
    private void updateView() {


        spottedCounters.clear();

        // gotta have a map to update the view
        if(map == null ||  map.getVASLMap() == null) {
            return;
        }

        VASLGameInterface = new VASLGameInterface(map, map.getVASLMap());
        VASLGameInterface.updatePieces();

        // hide all pieces out of LOS
        for (GamePiece piece : map.getAllPieces()) {
            if (piece instanceof Stack) {

                for (PieceIterator pi = new PieceIterator(((Stack) piece).getPiecesIterator()); pi.hasMoreElements(); ) {
                    setPieceVisibility(pi.nextPiece());
                }
            } else {
                setPieceVisibility(piece);
            }
        }

        // show spotted counters
        if(spottedCounters.size() > 0) {
            active = true;
            timer.restart();
        }
        map.repaint();
    }

    /**
     * Is this piece spotted?
     * @param piece the piece
     * @return true if DB disabled, piece is spotted or piece doesn't support DB
     */
    public static boolean isSpotted(GamePiece piece) {
       return !enabled || piece.getProperty(ASLProperties.SPOTTED) == null || Boolean.TRUE.equals(Boolean.parseBoolean((String) piece.getProperty(ASLProperties.SPOTTED)));
    }

    /**
     * Hides a piece if it's out of LOS
     * @param piece the piece
     */
    private void setPieceVisibility(GamePiece piece) {

        // global pieces and unowned pieces are always spotted
        if(VASLGameInterface.isDBGlobalCounter(piece) || (piece.getProperty(OWNER_PROPERTY) == null)){
            setPieceSpotted(piece);
            debug("Global piece " + piece.getName() + " was spotted ");
            return;
        }

        // my pieces are spotted
        if(isMyPiece(piece)) {
            setPieceSpotted(piece);
            debug("My piece " + piece.getName() + " was spotted ");
        }
        // check my opponents' pieces
        else {

            // step through all my pieces and check LOS to piece
            for (GamePiece p : map.getAllPieces()) {
                if (p instanceof Stack) {
                    for (PieceIterator pi = new PieceIterator(((Stack) p).getPiecesIterator()); pi.hasMoreElements(); ) {
                        GamePiece p2 = pi.nextPiece();
                        if (isMyPiece(p2) && VASLGameInterface.isDBUnitCounter(p2) && isInLOS(p2, piece)) {

                            // counter is newly spotted?
                            if(!isSpotted(piece)) {
                                spottedCounters.add(VASLGameInterface.getLocation(piece).getHex());
                            }
                            setPieceSpotted(piece);
                            debug("Piece " + piece.getName() + " was Spotted by " + p2.getName());
                            return;
                        }
                    }
                } else {
                    if (isMyPiece(p) && VASLGameInterface.isDBUnitCounter(p) && isInLOS(p, piece)) {

                        // counter is newly spotted?
                        if(!isSpotted(piece)) {
                            spottedCounters.add(VASLGameInterface.getLocation(piece).getHex());
                        }
                        setPieceSpotted(piece);
                        debug("Piece " + piece.getName() + " was Spotted by " + p.getName());
                        return;
                    }
                }
            }
            setPieceUnspotted(piece);
            debug("Piece " + piece.getName() + " was NOT Spotted");
        }
    }

    /**
     * @param piece the piece
     * @return true if I own the piece
     */
    private boolean isMyPiece(GamePiece piece) {

        return  piece.getProperty(OWNER_PROPERTY) == null || piece.getProperty(OWNER_PROPERTY).equals(myPlayerName);
    }

    /**
     * Marks a piece as spotted so it will be drawn on the map
     * @param piece the piece
     */
    private void setPieceSpotted(GamePiece piece) {

        piece.setProperty(SPOTTED_PROPERTY, "true");
    }

    /**
     * Marks a piece as NOT spotted so it will NOT be drawn on the map
     * @param piece the piece
     */
    private void setPieceUnspotted(GamePiece piece) {

        piece.setProperty(SPOTTED_PROPERTY, "false");
    }

    /**
     * Can piece1 see piece2?
     * Sub-classes could override this method to implement custom sighting rules
     * @param piece1 the viewing game piece
     * @param piece2 the piece being viewed
     * @return true if piece1 can see piece2
     */
    private boolean isInLOS(GamePiece piece1, GamePiece piece2) {

        // can the unit spot?
        if (!VASLGameInterface.isDBUnitCounter(piece1)) {
            return false;
        }

        // get the piece location
        Location source = getLocation(piece1);
        Location target = getLocation(piece2);

        // off board?
        if(source == null || target == null){
            return false;
        }

        // check the LOS
        LOSResult result = new LOSResult();
        map.getVASLMap().LOS(source, false, target, false, result, VASLGameInterface);
        if(!result.isBlocked()) {
            return true;
        }

        // if the source or target is a hexside location we also need to check the alternate aiming points
        if(source.isCenterLocation() && !target.isCenterLocation()) {
            result.reset();
            map.getVASLMap().LOS(source, false, target, true, result, VASLGameInterface);
            if(!result.isBlocked()) {
                return true;
            }
        }
        if(!source.isCenterLocation() && target.isCenterLocation()) {
            result.reset();
            map.getVASLMap().LOS(source, true, target, false, result, VASLGameInterface);
            if(!result.isBlocked()) {
                return true;
            }
        }
        if(!source.isCenterLocation() && !target.isCenterLocation()) {
            result.reset();
            map.getVASLMap().LOS(source, true, target, true, result, VASLGameInterface);
            if(!result.isBlocked()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Finds the location for the given piece
     * @param piece the piece
     * @return the location - null if error or none
     */
    protected Location getLocation(GamePiece piece) {

        // get the VASL interface if necessary
        if(VASLGameInterface == null){
            return null;
        }

        return VASLGameInterface.getLocation(piece);
    }

    @Override
    public Class<?>[] getAttributeTypes() {
        return new Class<?>[]{
                String.class,
                Boolean.class,
                ReportFormatConfig.class,
                String.class,
                String.class,
                IconConfig.class,
                NamedKeyStroke.class
        };
    }

    @Override
    public String[] getAttributeNames() {
        return new String[]{
                PROPERTY_TAB,
                REPORT,
                REPORT_FORMAT,
                LABEL,
                TOOLTIP,
                ICON_NAME,
                HOT_KEY
        };
    }

    @Override
    public String[] getAttributeDescriptions() {
        return new String[]{
                "Properties tab: ",
                "Report DB updates?  ",
                "Report format: ",
                "Button text: ",
                "Tool tip: ",
                "Button icon: ",
                "Hotkey: "
        };
    }

    @Override
    public String getAttributeValueString(String key) {

        if (PROPERTY_TAB.equals(key)) {
            return propertyTab;
        }
        else if (REPORT.equals(key)) {
            return String.valueOf(report);
        }
        else  if(REPORT_FORMAT.equals(key)) {
            return reportFormat.getText();
        }
        else {
            return launchButton.getAttributeValueString(key);
        }
    }

    @Override
    public void setAttribute(String key, Object value) {
        if (PROPERTY_TAB.equals(key)) {
            if (value instanceof String) {
                propertyTab = (String) value;
            }
        }
        else if (REPORT.equals(key)) {
            if (value instanceof String) {
                report = Boolean.valueOf((String) value);
            }
        }
        else if (REPORT_FORMAT.equals(key)) {
            if (value instanceof String) {
                reportFormat.setFormat((String) value);
            }
        }
        else {
            launchButton.setAttribute(key, value);
        }
    }

    public void addTo(Buildable parent) {

        // add this component to the map toolbar
        if (parent instanceof Map) {
            setMap((ASLMap) parent);
            map.getToolBar().add(launchButton);
            map.addDrawComponent(this);
            GameModule.getGameModule().addCommandEncoder(this);
            timer = new Timer (CIRCLE_DURATION, this);
        }

        // record the player information
        myPlayerName = (String) getGameModule().getPrefs().getValue(PLAYER_NAME_PROPERTY);
        players.add(myPlayerName);
        GameModule.getGameModule().getGameState().addGameComponent(this);
    }

    public void removeFrom(Buildable parent) {

        if (parent instanceof Map) {
            GameModule.getGameModule().removeCommandEncoder(this);
            map.getToolBar().remove(launchButton);
        }
    }

    /**
     * The DOUBLE_BLIND command is used to exchange player information and trigger an update of the DB view
     * The ENABLE_DOUBLE_BLIND command marks the game as a DB game
     * @param c the command
     * @return the command string
     */
    public String encode(Command c) {

        if (c instanceof SynchCommand) {

            // push player information when I synch with my opponent
            debug("Encoding synch command");
            GameModule.getGameModule().sendAndLog(new DoubleBlindUpdateCommand(myPlayerName));
        }

        // Command string is ENABLE_DOUBLE_BLIND:<enable>
        if(c instanceof  EnableDoubleBlindCommand) {

            debug("Encoded enable DB command string");
            return  ENABLE_COMMAND_PREFIX + Boolean.toString(enabled);
        }

        // Command string is DOUBLE_BLIND:<player name>
        if (c instanceof DoubleBlindUpdateCommand && myPlayerName != null) {
            String commandString = COMMAND_PREFIX + myPlayerName;
            debug("Encoded command string");
            return commandString;
        }
        else {
            return null;
        }
    }

    /**
     * @param s the command string
     * @return the DoubleBlindUpdateCommand
     */
    public Command decode(String s) {

        // Command string is ENABLE_DOUBLE_BLIND:<enable>
        if (s.startsWith(ENABLE_COMMAND_PREFIX)) {
            debug("Decoded enable DB command string: " + s);
            String strings[] = s.split(COMMAND_SEPARATOR);
            return new EnableDoubleBlindCommand(this, Boolean.valueOf(strings[1]));
        }

        // Command string is DOUBLE_BLIND:<player name>
        else if (s.startsWith(COMMAND_PREFIX)) {

            // build the player object
            debug("Decoded DB command string: " + s);
            String strings[] = s.split(COMMAND_SEPARATOR);

            // add to the players list if necessary
            if(!players.contains(strings[1])) {
                players.add(strings[1]);
            }
            return new DoubleBlindUpdateCommand(strings[1]);
        }

        // push our player information when opponent synchronizes with me
        else if (s.startsWith("SYNC")) {
            debug("Sending player info on synch");
            GameModule.getGameModule().sendAndLog(new DoubleBlindUpdateCommand(myPlayerName));
        }

        return null;
    }

    public HelpFile getHelpFile() {
        return null;
    }

    public Class[] getAllowableConfigureComponents() {
        return new Class[0];
    }

    public void setup(boolean gameStarting) {

        debug("DB set up called - starting " + gameStarting);

        // make sure map supports LOS
        if (map == null || map.isLegacyMode()) {
            enabled = false;
        }

        if(enabled){

            final String CENTER_ON_MOVE = "centerOnMove";
            final String AUTO_REPORT = "autoReport";

            if(gameStarting) {

                // push player information
                GameModule.getGameModule().sendAndLog(new DoubleBlindUpdateCommand(myPlayerName));

                // save preferences 'cause we disable them during DB play
                oldCenterOnMove   = (Boolean) getGameModule().getPrefs().getOption(CENTER_ON_MOVE).getValue();
                oldAutoReport = (Boolean) getGameModule().getPrefs().getOption(AUTO_REPORT).getValue();

                getGameModule().getPrefs().getOption(CENTER_ON_MOVE).setValue(Boolean.FALSE);
                getGameModule().getPrefs().getOption(AUTO_REPORT).setValue(Boolean.FALSE);

                // update the view
                updateView();

            }
            else {

                // restore preference settings
                getGameModule().getPrefs().getOption(CENTER_ON_MOVE).setValue(oldCenterOnMove);
                getGameModule().getPrefs().getOption(AUTO_REPORT).setValue(oldAutoReport);

                players.clear();

                enabled = false;
            }
        }
    }

    /**
     * Dumps a message to the local chatter and console for debugging
     * @param message the message
     */
    private void debug(String message) {
        // getGameModule().warn(message);
        System.out.println(message);
    }

    /**
     * Saves the player list and DB state
     */
    public Command getRestoreCommand() {

        debug("Creating restore command for DB - " + enabled + ". Player count = " + players.size());
        Command c = new NullCommand();
        if(enabled) {

            for (String p : players) {

                c = c.append(new DoubleBlindUpdateCommand(p));
            }
            c = c.append(new EnableDoubleBlindCommand(this, enabled));
        }
        return c;
    }

    @Override
    public void draw(Graphics g, Map map) {

        if(active){
            for(Hex h: spottedCounters) {

                int circleSize = (int)(map.getZoom() * DEFAULT_HEX_HEIGHT * 2);

                // translate the piece center for current zoom
                Point p = new Point(h.getCenterLocation().getLOSPoint());
                p.translate(map.getEdgeBuffer().width, map.getEdgeBuffer().height);
                p.x *= map.getZoom();
                p.y *= map.getZoom();

                // draw a circle around the selected point
                Graphics2D gg = (Graphics2D) g;
                g.setColor(Color.RED);
                gg.setStroke(new BasicStroke(3));
                gg.drawOval(p.x - circleSize/2, p.y - circleSize/2, circleSize, circleSize);
            }
        }
    }

    @Override
    public boolean drawAboveCounters() {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        active = false;
        timer.stop();
        map.repaint();
    }

    /**
     * Configurer for the icon image
     */
    public static class IconConfig implements ConfigurerFactory {
        public Configurer getConfigurer(AutoConfigurable c, String key, String name) {
            return new IconConfigurer(key, name, "");
        }
    }

    /**
     * Configurer for the chatter report formatter
     */
    public static class ReportFormatConfig implements TranslatableConfigurerFactory {
        public Configurer getConfigurer(AutoConfigurable c, String key, String name) {
            return new PlayerIdFormattedStringConfigurer(key, name, new String[] {
                    ASLProperties.LOCATION,
                    Properties.MOVED});
        }
    }

    /**
     * Use this command to update the players' view of the map for DB
     */
    class DoubleBlindUpdateCommand extends Command {

        private String playerName;

        DoubleBlindUpdateCommand(String playerName) {
            this.playerName = playerName;
        }

        protected void executeCommand() {

            debug("Executing DB update command ");
            if(!players.contains(playerName)){
                players.add(playerName);
            }
            updateView();
        }

        protected Command myUndoCommand() {
            return null;
        }

        public int getValue() {
            return 0;
        }
    }

    /**
     * Use this command to enable or disable double blind for the game
     */
    class EnableDoubleBlindCommand extends Command {
        private boolean enabledFlag;
        private DoubleBlindViewer doubleBlindViewer;

        EnableDoubleBlindCommand(DoubleBlindViewer doubleBlindViewer, boolean enabled) {
            this.enabledFlag = enabled;
            this.doubleBlindViewer = doubleBlindViewer;
        }

        protected void executeCommand() {

            doubleBlindViewer.enableDB(enabledFlag);
        }

        protected Command myUndoCommand() {
            return null;
        }

        public int getValue() {
            return 0;
        }
    }



}


