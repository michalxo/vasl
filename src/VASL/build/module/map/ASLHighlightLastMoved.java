package VASL.build.module.map;

import VASSAL.build.module.Map;
import VASSAL.build.module.map.HighlightLastMoved;

import java.awt.*;

/**
 * Copyright (c) 2017 by David Sullivan
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
 * Override the VASSAL last moved highligher so pieces are not highlighted during double blind play
 */
public class ASLHighlightLastMoved  extends HighlightLastMoved {

    public ASLHighlightLastMoved() {
        super();
    }

    @Override
    public void draw(Graphics g, Map map) {

        // disable the highlighter dynamically if DB is enabled
        enabled = !DoubleBlindViewer.isEnabled();

        super.draw(g, map);
    }
}
