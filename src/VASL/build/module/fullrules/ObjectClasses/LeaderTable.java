package VASL.build.module.fullrules.ObjectClasses;


import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.build.module.map.boardArchive.AbstractMetadata;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LeaderTable extends AbstractMetadata {
    private static final String  LeaderTableElement = "LeaderTable";
    private static final String  LeadertypeElement = "Leadertype";
    private static final String  LeadertypeLeaderNameAttr = "Leadername";
    private static final String  LeadertypeOBLinkAttr = "OBLink";
    private static final String  LeadertypeLDRMAttr = "LDRM";
    private static final String  LeadertypeMoraleAttr = "Morale";
    private static final String  LeadertypeRanktypeAttr = "Ranktype";
    private static final String  LeadertypeIsAHeroAttr = "IsAHero";
    private static final String  LeadertypeUnittypeAttr = "Unittype";

    // maps terrain names to terrain objects
    private LinkedHashMap<String, Leader> LeaderTableTypes = new LinkedHashMap<String, Leader>(50);


    /**
     * Parses a  LeaderTable metadata file
     * @param metadata an <code>InputStream</code> for the LeaderTable XML file
     * @throws JDOMException
     */
    public void parseLeaderTableFile(InputStream metadata) throws JDOMException {


        SAXBuilder parser = new SAXBuilder();

        try {
            ConversionC DoConversion = new ConversionC();
            // the root element will be the  LeaderTable element
            Document doc = parser.build(metadata);
            Element root = doc.getRootElement();

            // read the shared metadata
            if(root.getName().equals( LeaderTableElement)) {

                for(Element e: root.getChildren()) {

                    // ignore any child elements that are not  Leadertype
                    if(e.getName().equals( LeadertypeElement)){

                        // read the  Leadertype attributes
                        Leader  leader = new Leader();
                        leader.setLeaderName(e.getAttribute( LeadertypeLeaderNameAttr).getValue());
                        leader.setOBLink(e.getAttribute( LeadertypeOBLinkAttr).getIntValue());
                        leader.setLDRM((e.getAttribute( LeadertypeLDRMAttr).getIntValue()));
                        leader.setRanktype((e.getAttribute( LeadertypeRanktypeAttr).getIntValue()));
                        leader.setMoraleLevel((e.getAttribute( LeadertypeMoraleAttr).getIntValue()));
                        leader.setIsAHero((e.getAttribute( LeadertypeIsAHeroAttr).getBooleanValue()));
                        leader.setUnitType(DoConversion.ConverttoUnitType(e.getAttribute( LeadertypeUnittypeAttr).getIntValue()));

                        // add the terrain type to the terrain list
                        LeaderTableTypes.put( Integer.toString(leader.getOBLink()),  leader);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new JDOMException("Error reading the  Leader Table metadata", e);
        }
    }



    /**
     * @return the list of terrain types
     */
    public HashMap<String,  Leader> getLeaderTableTypes() {

        return  LeaderTableTypes;
    }
}

