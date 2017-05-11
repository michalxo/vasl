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
package VASL.build.module.map;

import VASL.counters.ASLProperties;
import VASSAL.build.module.map.StackMetrics;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceFilter;
import VASSAL.counters.Properties;
import VASSAL.counters.Stack;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.BitSet;
import java.util.Iterator;

public class ASLStackMetrics extends StackMetrics {

    public ASLStackMetrics() {
        this(false, DEFAULT_EXSEP_X, DEFAULT_EXSEP_Y, DEFAULT_UNEXSEP_X, DEFAULT_UNEXSEP_Y);
    }

    public ASLStackMetrics(boolean dis,
                           int exSx, int exSy,
                           int unexSx, int unexSy) {
        super(dis, exSx, exSy, unexSx, unexSy);

        // include DB logic in stack fillters
        unselectedVisible = new PieceFilter() {
            public boolean accept(GamePiece piece) {
                return !Boolean.TRUE.equals(piece.getProperty(Properties.INVISIBLE_TO_ME))
                        && !Boolean.TRUE.equals(piece.getProperty(Properties.SELECTED))
                        && DoubleBlindViewer.isSpotted(piece);
            }
        };
        selectedVisible = new PieceFilter() {
            public boolean accept(GamePiece piece) {
                return !Boolean.TRUE.equals(piece.getProperty(Properties.INVISIBLE_TO_ME))
                        && Boolean.TRUE.equals(piece.getProperty(Properties.SELECTED))
                        && DoubleBlindViewer.isSpotted(piece);
            }
        };
    }

    protected void drawUnexpanded(GamePiece p, Graphics g,
                                  int x, int y, Component obs, double zoom) {
        if (p.getProperty(ASLProperties.LOCATION) != null) {
            p.draw(g, x, y, obs, zoom);
        } else {
            super.drawUnexpanded(p, g, x, y, obs, zoom);
        }
    }

    public int getContents(Stack parent, Point[] positions, Shape[] shapes, Rectangle[] boundingBoxes, int x, int y) {

        int count = parent.getMaximumVisiblePieceCount();
        if (positions != null) {
            count = Math.min(count, positions.length);
        }
        if (boundingBoxes != null) {
            count = Math.min(count, boundingBoxes.length);
        }
        if (shapes != null) {
            count = Math.min(count,shapes.length);
        }
        int dx = parent.isExpanded() ? exSepX : unexSepX;
        int dy = parent.isExpanded() ? exSepY : unexSepY;
        Point currentPos = null, nextPos = null;
        Rectangle currentSelBounds = null, nextSelBounds = null;
        for (int index = 0; index < count; ++index) {
            GamePiece child = parent.getPieceAt(index);
            if (Boolean.TRUE.equals(child.getProperty(Properties.INVISIBLE_TO_ME)) || !DoubleBlindViewer.isSpotted(child)) {
                Rectangle blank = new Rectangle(x, y, 0, 0);
                if (positions != null) {
                    positions[index] = blank.getLocation();
                }
                if (boundingBoxes != null) {
                    boundingBoxes[index] = blank;
                }
                if (shapes != null) {
                    shapes[index] = blank;
                }
            }
            else {
                child.setProperty(Properties.USE_UNROTATED_SHAPE,Boolean.TRUE);
                nextSelBounds = child.getShape().getBounds();
                child.setProperty(Properties.USE_UNROTATED_SHAPE,Boolean.FALSE);
                nextPos = new Point(0,0);
                if (currentPos == null) {
                    currentSelBounds = nextSelBounds;
                    currentSelBounds.translate(x, y);
                    currentPos = new Point(x, y);
                    nextPos = currentPos;
                }
                else {
                    nextPosition(currentPos, currentSelBounds, nextPos, nextSelBounds, dx, dy);
                }
                if (positions != null) {
                    positions[index] = nextPos;
                }
                if (boundingBoxes != null) {
                    Rectangle bbox = child.boundingBox();
                    bbox.translate(nextPos.x, nextPos.y);
                    boundingBoxes[index] = bbox;
                }
                if (shapes != null) {
                    Shape s = child.getShape();
                    s = AffineTransform.getTranslateInstance(nextPos.x,nextPos.y).createTransformedShape(s);
                    shapes[index] = s;
                }
                currentPos = nextPos;
                currentSelBounds = nextSelBounds;
            }
        }

        if (!parent.isExpanded()) {
            int c = parent.getPieceCount();
            BitSet visibleLocations = new BitSet(c);
            BitSet visibleOther = new BitSet(c);

            for (int i = 0; i < c; ++i) {
                GamePiece p = parent.getPieceAt(i);
                boolean visibleToMe = !Boolean.TRUE.equals(p.getProperty(Properties.INVISIBLE_TO_ME)) && DoubleBlindViewer.isSpotted(p);
                boolean isLocation = p.getProperty((ASLProperties.LOCATION)) != null;
                visibleLocations.set(i, isLocation && visibleToMe);
                visibleOther.set(i, !isLocation && visibleToMe);
            }
            if (visibleLocations.cardinality() > 0 && visibleOther.cardinality() > 0) {
                for (int i = 0; i < c; ++i) {
                    if (visibleLocations.get(i)) {
                        if (positions != null) {
                            positions[i].translate(-15, 0);
                        }
                        if (boundingBoxes != null) {
                            boundingBoxes[i].translate(-15, 0);
                        }
                        if (shapes != null) {
                            shapes[i] = AffineTransform.getTranslateInstance(-15, 0).createTransformedShape(shapes[i]);
                        }
                    }
                }
            }
        }
        return count;
    }

    public Stack createStack(GamePiece p, boolean force) {
        return isStackingEnabled() || force ? new Stack(p) : null;
    }

//  rolled-back due to unintended information leakage
//  @Override
//  public void draw(Stack stack, Graphics g, int x, int y, Component obs, double zoom) {
//
//    Highlighter highlighter = stack.getMap() == null ? BasicPiece.getHighlighter() : stack.getMap().getHighlighter();
//    Point[] positions = new Point[stack.getPieceCount()];
//    getContents(stack, positions, null, null, x, y);
//
//    for (PieceIterator e = new PieceIterator(stack.getPiecesIterator(),
//                                             unselectedVisible);
//         e.hasMoreElements();) {
//      GamePiece next = e.nextPiece();
//      int index = stack.indexOf(next);
//      int nextX = x + (int) (zoom * (positions[index].x - x));
//      int nextY = y + (int) (zoom * (positions[index].y - y));
//      //if (stack.isExpanded() || !e.hasMoreElements()) {
//        next.draw(g,
//                  nextX,
//                  nextY,
//                  obs, zoom);
//      //}
//      //else {
////        drawUnexpanded(next, g, nextX, nextY, obs, zoom);
//  //    }
//    }
//
//    for (PieceIterator e = new PieceIterator(stack.getPiecesIterator(),
//                                             selectedVisible);
//         e.hasMoreElements();) {
//      GamePiece next = e.nextPiece();
//      int index = stack.indexOf(next);
//      int nextX = x + (int) (zoom * (positions[index].x - x));
//      int nextY = y + (int) (zoom * (positions[index].y - y));
//      next.draw(g,
//                nextX,
//                nextY,
//                obs, zoom);
//      highlighter.draw
//          (next, g,
//           nextX,
//           nextY,
//           obs, zoom);
//    }
//  }
//
//  private boolean isVisible(Rectangle region, Rectangle bounds) {
//    boolean visible = true;
//    if (region != null) {
//      visible = region.intersects(bounds);
//    }
//    return visible;
//  }
//
//  public void draw(Stack stack, Point location, Graphics g, Map map, double zoom, Rectangle visibleRect) {
//    Highlighter highlighter = map.getHighlighter();
//    Point mapLocation = map.mapCoordinates(location);
//    Rectangle region = visibleRect == null ? null : map.mapRectangle(visibleRect);
//    Point[] positions = new Point[stack.getPieceCount()];
//    Rectangle[] bounds = region == null ? null : new Rectangle[stack.getPieceCount()];
//    getContents(stack, positions, null, bounds, mapLocation.x, mapLocation.y);
//
//    for (PieceIterator e = new PieceIterator(stack.getPiecesIterator(),
//                                             unselectedVisible);
//         e.hasMoreElements();) {
//      GamePiece next = e.nextPiece();
//      int index = stack.indexOf(next);
//      Point pt = map.componentCoordinates(positions[index]);
//      if (bounds == null || isVisible(region, bounds[index])) {
////        if (stack.isExpanded() || !e.hasMoreElements()) {
//          next.draw(g,
//                    pt.x,
//                    pt.y,
//                    map.getView(), zoom);
////        }
////       else {
////          drawUnexpanded(next, g, pt.x, pt.y, map.getView(), zoom);
////        }
//      }
//    }
//
//    for (PieceIterator e = new PieceIterator(stack.getPiecesIterator(),
//                                             selectedVisible);
//         e.hasMoreElements();) {
//      GamePiece next = e.nextPiece();
//      int index = stack.indexOf(next);
//      if (bounds == null || isVisible(region, bounds[index])) {
//        Point pt = map.componentCoordinates(positions[index]);
//        next.draw(g,
//                  pt.x,
//                  pt.y,
//                  map.getView(), zoom);
//        highlighter.draw
//            (next, g,
//             pt.x,
//             pt.y,
//             map.getView(), zoom);
//      }
//    }
//  }
}
