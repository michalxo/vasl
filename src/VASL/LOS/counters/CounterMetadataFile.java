/*
 * $Id: CounterMetadataFile 3/30/14 davidsullivan1 $
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
package VASL.LOS.counters;

import VASSAL.build.GameModule;
import VASSAL.tools.DataArchive;
import VASSAL.tools.ErrorDialog;
import VASSAL.tools.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * This class provides access to the counter metadata file in the module archive
 */
public class CounterMetadataFile {

    // name of the counter metadata file in the module archive
    private final static String counterMetadataFileName = "CounterMetadata.xml";

    // XML element and attribute names
    private static final String counterMetadataElement = "counterMetadata";
    private static final String smokeCounterElement = "smoke";
    private static final String OBACounterElement = "OBA";
    private static final String terrainCounterElement = "terrain";
    private static final String wreckCounterElement = "wreck";
    private static final String ignoreCounterElement = "ignore";

    private static final String buildingLevelCounterElement = "buildingLevel";
    private static final String roofCounterElement = "roof";
    private static final String cellarCounterElement = "cellar";
    private static final String entrenchmentCounterElement = "entrenchment";
    private static final String crestCounterElement = "crest";
    private static final String climbCounterElement = "climb";
    private static final String pillboxCounterElement = "pillbox";

    private static final String counterNameAttribute = "name";
    private static final String counterHindranceAttribute = "hindrance";
    private static final String counterHeightAttribute = "height";
    private static final String counterTerrainAttribute = "terrain";
    private static final String counterLevelAttribute = "level";
    private static final String counterPositionAttribute = "position";
    private static final String counterCAAttribute = "ca";

    // constant values for the counter position attribute
    static final String counterPositionAbove = "above";
    static final String getCounterPositionBelow = "below";

    // List of the counter elements
    private LinkedHashMap<String, CounterMetadata> metadataElements = new LinkedHashMap<String, CounterMetadata>(30);

    public CounterMetadataFile() {

        InputStream inputStream = null;
        DataArchive archive = GameModule.getGameModule().getDataArchive();
        try {

            // counter metadata
            inputStream = archive.getInputStream(counterMetadataFileName);
            parseCounterMetadataFile(inputStream);

            // give up on any errors
        } catch (IOException e) {
            metadataElements = null;
            ErrorDialog.bug(e);
        } catch (JDOMException e) {
            metadataElements = null;
            ErrorDialog.bug(e);
        } catch (NullPointerException e) {
            metadataElements = null;
            ErrorDialog.bug(e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * Parses the counter metadata file
     * @param metadata an <code>InputStream</code> for the counter metadata XML file
     * @throws JDOMException if there's a parsing error
     */
    private void parseCounterMetadataFile(InputStream metadata) throws JDOMException {

        SAXBuilder parser = new SAXBuilder();

        try {

            // the root element will be the counter metadata element
            Document doc = parser.build(metadata);
            Element root = doc.getRootElement();

            // read the counters
            if(root.getName().equals(counterMetadataElement)) {

                parseCounters(root);
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new JDOMException("Error reading the counter metadata", e);
        }
    }

    /**
     * Parses the counter metadata element
     * @param element the counter metadata element
     * @throws JDOMException if there's a parsing error
     */
    private void parseCounters(Element element) throws JDOMException {

        // make sure we have the right element
        assertElementName(element, counterMetadataElement);

        for(Element e: element.getChildren()) {

            CounterMetadata counterMetadata = null;
            String name = e.getAttributeValue(counterNameAttribute);

            // ignore any child elements that are not counter rules
            if(e.getName().equals(smokeCounterElement)) {

                // read the height and hindrance
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.SMOKE);
                counterMetadata.setHeight(e.getAttribute(counterHeightAttribute).getIntValue());
                counterMetadata.setHindrance(e.getAttribute(counterHindranceAttribute).getIntValue());

            }
            else if(e.getName().equals(terrainCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.TERRAIN);
                counterMetadata.setTerrain(e.getAttributeValue(counterTerrainAttribute));

            }
            else if(e.getName().equals(OBACounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.OBA);

            }
            else if(e.getName().equals(ignoreCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.IGNORE);
            }
            else if(e.getName().equals(buildingLevelCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.BUILDING_LEVEL);
                counterMetadata.setLevel(e.getAttribute(counterLevelAttribute).getIntValue());
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }
            else if(e.getName().equals(roofCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.ROOF);
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }
            else if(e.getName().equals(cellarCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.CELLAR);
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }
            else if(e.getName().equals(entrenchmentCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.ENTRENCHMENT);
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }
            else if(e.getName().equals(crestCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.CREST);
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }
            else if(e.getName().equals(climbCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.CLIMB);
                counterMetadata.setLevel(e.getAttribute(counterLevelAttribute).getIntValue());
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }
            else if(e.getName().equals(pillboxCounterElement)) {
                counterMetadata = new CounterMetadata(name, CounterMetadata.CounterType.PILLBOX);
                counterMetadata.setLevel(e.getAttribute(counterLevelAttribute).getIntValue());
                counterMetadata.setCoveredArc(e.getAttribute(counterCAAttribute).getIntValue());
                counterMetadata.setPosition(e.getAttributeValue(counterPositionAttribute));
            }

            metadataElements.put(name, counterMetadata);
        }
    }

    /**
     * @return the list of LOS counter rules
     */
    public LinkedHashMap<String, CounterMetadata> getMetadataElements(){
        return metadataElements;
    }

    /**
     * Assert the element has the given name otherwise throw an exception
     * @param element the element
     * @param elementName the element name
     * @throws JDOMException if element name is invalid
     */
    private void assertElementName(Element element, String elementName) throws JDOMException {

        // make sure we have the right element
        if(!element.getName().equals(elementName)) {
            throw new JDOMException("Invalid element passed to an element parser: " + elementName);
        }
    }
}
