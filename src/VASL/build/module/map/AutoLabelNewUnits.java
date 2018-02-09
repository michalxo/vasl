package VASL.build.module.map;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.GameModule;
import VASSAL.build.module.GameComponent;
import VASSAL.build.module.Map;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.build.module.map.Drawable;
import VASSAL.command.Command;
import VASSAL.command.CommandEncoder;
import VASSAL.command.NullCommand;
import VASSAL.configure.ColorConfigurer;
import VASSAL.configure.NamedHotKeyConfigurer;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceIterator;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;
import VASSAL.tools.NamedKeyStroke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Copyright (c) 2018 by Doug Rimmer
 * <p>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License (LGPL) as published by the Free Software Foundation.
 * <p>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, copies are available
 * at http://www.opensource.org.
 *
 * This class forces the creation of a unique identifier for each unit created
 * which is shown as a label on the unit counter
 */
public class AutoLabelNewUnits extends AbstractConfigurable implements KeyListener, CommandEncoder, GameComponent, Drawable {

        private static final String LINK_COMMAND_PREFIX = "LINK_PIECE:";
        private static final String UNLINK_COMMAND_PREFIX = "UNLINK_PIECE:";
        private Map map;

        private static final String NAME = "Name";
        private static final String THREAD_COLOR = "Color";
        private static final String THREAD_WIDTH = "ThreadWidth";
        private static final String LINK_KEY = "LinkKey";
        private static final String UNLINK_KEY = "UnlinkKey";

        private Color threadColor = Color.RED;
        private int threadWidth = 2;
        private NamedKeyStroke labelKey = new NamedKeyStroke("71dd9846");


        @Override
        public Class<?>[] getAttributeTypes() {
        return new Class<?>[]{
        String.class,
        Color.class,
        Integer.class,
        NamedKeyStroke.class,
        NamedKeyStroke.class
        };
        }

        @Override
        public String[] getAttributeNames() {
        return new String[]{
        NAME,
        THREAD_COLOR,
        THREAD_WIDTH,
        LINK_KEY,
        UNLINK_KEY};
        }

        @Override
        public String[] getAttributeDescriptions() {
        return new String[]{
        "Piece Linker",
        "Thread Color",
        "Thread width",
        "Link Key",
        "Unlink Key",
        };
        }

        @Override
        public String getAttributeValueString(String key) {

        /*if (NAME.equals(key)) {
        return getConfigureName();
        } else if (THREAD_COLOR.equals(key)) {
        return ColorConfigurer.colorToString(threadColor);
        } else if (THREAD_WIDTH.equals(key)) {
        return String.valueOf(threadWidth);
        } else if (LINK_KEY.equals(key)) {
        return NamedHotKeyConfigurer.encode(linkKey);
        } else if (UNLINK_KEY.equals(key)) {
        return NamedHotKeyConfigurer.encode(unlinkKey);
        } else {
        return null;
        }*/
        return null;
        }

        @Override
        public void setAttribute(String key, Object value) {
        if (NAME.equals(key)) {
        setConfigureName((String) value);
        } else if (THREAD_COLOR.equals(key)) {
        if (value instanceof String) {
        value = ColorConfigurer.stringToColor((String) value);
        }
        threadColor = (Color) value;
        } else if (THREAD_WIDTH.equals(key)) {
        if (value instanceof String) {
        value = Integer.valueOf((String) value);
        }
        threadWidth = (Integer) value;
        } else if (LINK_KEY.equals(key)) {
        if (value instanceof String) {
        value = NamedHotKeyConfigurer.decode((String) value);
        }
        /*linkKey = (NamedKeyStroke) value;
        } else if (UNLINK_KEY.equals(key)) {
        if (value instanceof String) {
        value = NamedHotKeyConfigurer.decode((String) value);
        }
        unlinkKey = (NamedKeyStroke) value;*/
        }
        }

        @Override
        public void addTo(Buildable parent) {

        // add this component to the game and register a mouse listener
        if (parent instanceof Map) {
        this.map = (Map) parent;
        GameModule mod = GameModule.getGameModule();
        mod.addCommandEncoder(this);
        mod.getGameState().addGameComponent(this);
        map.addDrawComponent(this);
        map.getView().addKeyListener(this);
        }
        }

        @Override
        /**
         * Draw a label on the selected counter
         */
        public void draw(Graphics g, Map map) {

        }
        @Override
        public boolean drawAboveCounters() {
        return false;
        }

        /**
         * Encodes a link command
         * @param c the link command
         * @return command string of the form LINK_PIECE:<piece ID>:<piece ID>
         */
        public String encode(Command c) {
        if (c instanceof LinkPiecesCommand) {
        LinkPiecesCommand lpc = (LinkPiecesCommand) c;
        return LINK_COMMAND_PREFIX + lpc.getFromPieceID() + "," + lpc.getToPieceID();
        } else if (c instanceof UnlinkPiecesCommand) {
        UnlinkPiecesCommand upc = (UnlinkPiecesCommand) c;
        return UNLINK_COMMAND_PREFIX + upc.getFromPieceID() + "," + upc.getToPieceID();
        } else {
        return null;
        }
        }

        /**
         * Decodes a link command
         * @param s command string of the form LINK_PIECE:<piece ID>:<piece ID>
         * @return
         */
        public Command decode(String s) {
        if (s.startsWith(LINK_COMMAND_PREFIX)) {

        // decode the linked piece IDs
        String fromPieceID = s.substring(s.indexOf(":") + 1, s.indexOf(","));
        String toPieceID = s.substring(s.indexOf(",") + 1);

        //return new LinkPiecesCommand(this, fromPieceID, toPieceID);
        } else if (s.startsWith(UNLINK_COMMAND_PREFIX)) {

        // decode the linked piece IDs
        String fromPieceID = s.substring(s.indexOf(":") + 1, s.indexOf(","));
        String toPieceID = s.substring(s.indexOf(",") + 1);

        //return new UnlinkPiecesCommand(this, fromPieceID, toPieceID);
        } else {
        return null;
        }
        return null;
        }

        @Override
        public HelpFile getHelpFile() {
        return null;
        }

        @Override
        public Class[] getAllowableConfigureComponents() {
        return new Class[0];
        }

        @Override
        /**
         * Called when a game is starting
         */
        public void setup(boolean gameStarting) {


        }

        @Override
        /**
         * This command is created when a saved game is opened
         */
        public Command getRestoreCommand() {

        Command c = new NullCommand();
        /*if (!links.isEmpty()) {
        for (String fromPieceID : links.keySet()) {
        for (String toPieceID : links.get(fromPieceID)) {
        Command l = new LinkPiecesCommand(this, fromPieceID, toPieceID);
        c.append(l);
        }
        }
        }*/
        return c;
        }

        @Override
        public void removeFrom(Buildable parent) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
        public void keyPressed(KeyEvent e) {

        }
    public Command keyEvent(javax.swing.KeyStroke stroke) {
            return myKeyEvent(stroke);
    }
        public Command myKeyEvent(KeyStroke stroke) {
            if (stroke.equals(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_MASK, true))) {

                LinkedList<GamePiece> selectedPieces = getSelectedPieces();
                final PieceIterator pi = new PieceIterator(selectedPieces.iterator());
                while (pi.hasMoreElements()) {
                    final GamePiece piece = pi.nextPiece();
                    if (piece instanceof Stack) {
                        for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                            GamePiece piece2 = pi2.nextPiece();
                            if (isSelected(piece2)) {
                                piece2.keyEvent(KeyStroke.getKeyStroke('L', java.awt.event.InputEvent.CTRL_MASK));
                            }
                        }
                    } else{
                        if (isSelected(piece)) {
                            //piece.setProperty(Properties."TextLabel", "447D");
                            //map.repaint();
                            piece.keyEvent(KeyStroke.getKeyStroke('L', java.awt.event.InputEvent.CTRL_MASK));
                        }
                    }
                }
            }
            //stroke.consume();
            return null;
        }

        @Override
        /**
         * Add a label text to the selected counters
         */
        public void keyReleased(KeyEvent e) {

            /*if (KeyStroke.getKeyStrokeForEvent(e).equals(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_MASK, true))) {

                LinkedList<GamePiece> selectedPieces = getSelectedPieces();
                final PieceIterator pi = new PieceIterator(selectedPieces.iterator());
                while (pi.hasMoreElements()) {
                    final GamePiece piece = pi.nextPiece();
                    if (piece instanceof Stack) {
                        for (PieceIterator pi2 = new PieceIterator(((Stack) piece).getPiecesIterator()); pi2.hasMoreElements(); ) {
                            GamePiece piece2 = pi2.nextPiece();
                            if (isSelected(piece2)) {
                                piece2.keyEvent(KeyStroke.getKeyStroke('L', java.awt.event.InputEvent.CTRL_MASK));
                            }
                        }
                    } else{
                        if (isSelected(piece)) {
                            piece.setProperty("TextLabel", "447D");
                            map.repaint();
                            //piece.keyEvent(KeyStroke.getKeyStroke('L', java.awt.event.InputEvent.CTRL_MASK));
                        }
                    }
                }
            }
            e.consume();*/
        }

        /**
         * Get all currently selected pieces
         *
         * @return LinkedList of selected pieces
         */
        private LinkedList<GamePiece> getSelectedPieces() {

        LinkedList<GamePiece> temp = new LinkedList<GamePiece>();
        for (GamePiece piece : GameModule.getGameModule().getGameState().getAllPieces()) {
        if (isSelected(piece)) {
        temp.add(piece);
        }
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

        /*@Override
        public void keyReleased(KeyEvent e) {

        }*/

        /**
         * Command to link two pieces
         */
        private class LinkPiecesCommand extends Command {

        PieceLinker linker;
        String fromPieceID;
        String toPieceID;

        LinkPiecesCommand(PieceLinker linker, String fromPieceID, String toPieceID) {
        this.linker = linker;
        this.fromPieceID = fromPieceID;
        this.toPieceID = toPieceID;
        }

        protected void executeCommand() {

        //linker.addLink(fromPieceID, toPieceID);
        }

        protected Command myUndoCommand() {
        return new UnlinkPiecesCommand(linker, fromPieceID, toPieceID);
        }

        String getFromPieceID() {
        return fromPieceID;
        }

        String getToPieceID() {
        return toPieceID;
        }
        }

        /**
         * Command to unlink two pieces
         */
        private class UnlinkPiecesCommand extends LinkPiecesCommand {

        UnlinkPiecesCommand(PieceLinker linker, String fromPieceID, String toPieceID) {
        super(linker, fromPieceID, toPieceID);
        }

        protected void executeCommand() {
        //linker.removeLink(fromPieceID, toPieceID);
        }

        protected Command myUndoCommand() {
        return new LinkPiecesCommand(linker, fromPieceID, toPieceID);
        }

        }
        }