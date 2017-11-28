package VASL.build.module.fullrules.ObjectClasses;


import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASL.build.module.map.boardArchive.AbstractMetadata;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SMCTable extends AbstractMetadata {
    private static final String  SMCTableElement = "SMCTable";
    private static final String  SMCtypeElement = "SMCtype";
    private static final String  SMCtypeLeaderNameAttr = "SMCname";
    private static final String  SMCtypeOBLinkAttr = "OBLink";
    private static final String  SMCtypeLDRMAttr = "LDRM";
    private static final String  SMCtypeHDRMAttr = "HDRM";
    private static final String  SMCtypeMoraleAttr = "Morale";
    private static final String  SMCtypeRanktypeAttr = "Ranktype";
    private static final String  SMCtypeIsAHeroAttr = "IsAHero";
    private static final String  SMCtypeUnittypeAttr = "Unittype";

    // maps terrain names to terrain objects
    private LinkedHashMap<String, SMC> SMCTableTypes = new LinkedHashMap<String, SMC>(50);


    /**
     * Parses a  SMCTable metadata file
     * @param metadata an <code>InputStream</code> for the SMCTable XML file
     * @throws JDOMException
     */
    public void parseSMCTableFile(InputStream metadata) throws JDOMException {


        SAXBuilder parser = new SAXBuilder();

        try {
            ConversionC DoConversion = new ConversionC();
            // the root element will be the  SMCTable element
            Document doc = parser.build(metadata);
            Element root = doc.getRootElement();

            // read the shared metadata
            if(root.getName().equals( SMCTableElement)) {

                for(Element e: root.getChildren()) {

                    // ignore any child elements that are not  Leadertype
                    if(e.getName().equals( SMCtypeElement)){

                        // read the  Leadertype attributes
                        SMC SMC = new SMC();
                        SMC.setLeaderName(e.getAttribute( SMCtypeLeaderNameAttr).getValue());
                        SMC.setOBLink(e.getAttribute( SMCtypeOBLinkAttr).getIntValue());
                        SMC.setLDRM((e.getAttribute( SMCtypeLDRMAttr).getIntValue()));
                        SMC.setHDRM((e.getAttribute( SMCtypeHDRMAttr).getIntValue()));
                        SMC.setRanktype((e.getAttribute( SMCtypeRanktypeAttr).getIntValue()));
                        SMC.setMoraleLevel((e.getAttribute( SMCtypeMoraleAttr).getIntValue()));
                        SMC.setIsAHero((e.getAttribute( SMCtypeIsAHeroAttr).getBooleanValue()));
                        SMC.setUnitType(DoConversion.ConverttoUnitType(e.getAttribute( SMCtypeUnittypeAttr).getIntValue()));

                        // add the terrain type to the terrain list
                        SMCTableTypes.put( Integer.toString(SMC.getOBLink()), SMC);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new JDOMException("Error reading the  SMC Table metadata", e);
        }
    }



    /**
     * @return the list of terrain types
     */
    public HashMap<String, SMC> getSMCTableTypes() {

        return  SMCTableTypes;
    }
}

