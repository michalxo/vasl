/*
 * $Id$
 *
 * Copyright (c) 2000-2003 by Rodney Kinney
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
package VASL.counters;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import javax.swing.KeyStroke;

import VASL.build.module.map.StartGame;
import VASSAL.build.GameModule;
import VASSAL.command.ChangeTracker;
import VASSAL.command.Command;
import VASSAL.counters.*;
import VASSAL.tools.SequenceEncoder;
import VASSAL.tools.imageop.ImageOp;
import VASSAL.tools.imageop.Op;

/**
 * Allows a piece to be marked as having moved
 */
public class MarkMoved extends Decorator implements EditablePiece {
  public static final String ID = "moved;";

  private static final KeyStroke markStroke = KeyStroke.getKeyStroke('M', java.awt.event.InputEvent.CTRL_MASK);
  private String markImage;
  private boolean hasMoved = false;

  public MarkMoved() {
    this(ID + "moved", null);
  }

  public MarkMoved(String type, GamePiece p) {
    mySetType(type);
    setInner(p);
  }

  public boolean isMoved() {
    return hasMoved;
  }

  public void setMoved(boolean b) {
    hasMoved = b;
  }

  public Object getProperty(Object key) {
    if (Properties.MOVED.equals(key)) {

      return new Boolean(isMoved());
    }
    else {
      return super.getProperty(key);
    }
  }

  public void setProperty(Object key, Object val) {

    if (Properties.MOVED.equals(key)) {
      // test code for accessing movement routines
      boolean ItemFound = false;
      LinkedList<GamePiece> SelectedCounters = getSelectedPieces();
      if (SelectedCounters.size() == 0) {
        ItemFound = false;
      } else {
        ItemFound = true;
      }
      // now do click events
      //ProcessingClick(ItemFound, e, ClickedHex, SelectedCounters);
      // end test code

      setMoved(Boolean.TRUE.equals(val));
    }
    else {
      super.setProperty(key, val);
    }
  }

  public void mySetType(String type) {
    SequenceEncoder.Decoder st = new SequenceEncoder.Decoder(type, ';');
    st.nextToken();
    markImage = st.nextToken();
  }

  public void mySetState(String newState) {
    hasMoved = "true".equals(newState);
  }

  public String myGetState() {
    return "" + hasMoved;
  }

  public String myGetType() {
    return ID + markImage;
  }

  protected KeyCommand[] myGetKeyCommands() {
    return new KeyCommand[]{new KeyCommand("Moved", markStroke, Decorator.getOutermost(this))};
  }

  public Command myKeyEvent(javax.swing.KeyStroke stroke) {
    if (stroke.equals(markStroke)) {
      ChangeTracker c = new ChangeTracker(this);
      hasMoved = !hasMoved;
      return c.getChangeCommand();
    }
    else {
      return null;
    }
  }

  public Shape getShape() {
    return piece.getShape();
  }

  public Rectangle boundingBox() {
    Rectangle r = piece.boundingBox();
    Rectangle r2 = piece.getShape().getBounds();
    r2.width += 20;
    return r.union(r2);
  }

  public String getName() {
    return piece.getName();
  }

  public void draw(Graphics g, int x, int y, Component obs, double zoom) {
    piece.draw(g, x, y, obs, zoom);
    if (hasMoved) {
      Rectangle r = piece.getShape().getBounds();
      try {
        ImageOp im = Op.load(markImage + ".gif");
        if (zoom != 1.0) {
          im = Op.scale(im,zoom);
        }
        g.drawImage(im.getImage(null),
                    x + (int) (zoom * (r.x + r.width)),
                    y + (int) (zoom * r.y),obs);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  public String getDescription() {
    return "Can be marked moved";
  }

  public VASSAL.build.module.documentation.HelpFile getHelpFile() {
    return null;
  }

  public PieceEditor getEditor() {
    return new SimplePieceEditor(this);
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
}
