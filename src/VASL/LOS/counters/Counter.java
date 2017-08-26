/*
 * $Id: Counter 2/16/14 davidsullivan1 $
 *
 * Copyright (c) 2013 by David Sullivan
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
package VASL.LOS.counters;

import VASL.LOS.Map.Location;

/**
 * A simple class that represents a VASL counter
 */
public class Counter {
    String name;
    Location location;

    public Counter(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Counter(String name) {
        this.name = name;
    }

    public String getName() { return name;}

    public Location getLocation() {
        return location;
    }
}
