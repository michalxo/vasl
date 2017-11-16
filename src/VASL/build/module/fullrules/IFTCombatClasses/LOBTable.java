package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.build.module.fullrules.DataClasses.LineofBattle;
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

public class LOBTable extends AbstractMetadata {
    private static final String  LOBTableElement = "LOBTable";
    private static final String  LOBtypeElement = "LOBtype";
    private static final String  LOBtypeLOBNameAttr = "LOBname";
    private static final String  LOBtypeNationalityAttr = "Nationality";
    private static final String  LOBtypeOBLinkAttr = "OBLink";
    private static final String  LOBtypeFirepowerAttr = "Firepower";
    private static final String  LOBtypeMoraleAttr = "Morale";
    private static final String  LOBtypeRangeAttr = "Range";
    private static final String  LOBtypeAssaultFireAttr = "AssaultFire";
    private static final String  LOBtypeSprayingFireAttr = "SprayingFire";
    private static final String  LOBtypeELR5Attr = "ELR5";
    private static final String  LOBtypeClassAttr = "Class";
    private static final String  LOBtypeSmokeAttr = "Smoke";
    private static final String  LOBtypeBrokenMLAttr = "BrokenML";
    private static final String  LOBtypeBPVAttr = "BPV";
    private static final String  LOBtypeRedToAttr = "RedTo";
    private static final String  LOBtypeSubToAttr = "SubTo";
    private static final String  LOBtypeHardToAttr = "HardTo";
    private static final String  LOBtypeSelfRallyAttr = "SelfRally";
    private static final String  LOBtypeReducesToAttr = "ReducesTo";
    private static final String  LOBtypeSubstitutesToAttr = "SubstitutesTo";
    private static final String  LOBtypeHardensToAttr = "HardensTo";
    private static final String  LOBtypeUnittypeAttr = "Unittype";

    // maps terrain names to terrain objects
    private LinkedHashMap<String, LineofBattle> LOBTableTypes = new LinkedHashMap<String, LineofBattle>(250);


    /**
     * Parses a  LOBTable metadata file
     * @param metadata an <code>InputStream</code> for the shared board metadata XML file
     * @throws JDOMException
     */
    public void parseLOBTableFile(InputStream metadata) throws JDOMException {


        SAXBuilder parser = new SAXBuilder();

        try {
            ConversionC DoConversion = new ConversionC();
            // the root element will be the  LOBTable element
            Document doc = parser.build(metadata);
            Element root = doc.getRootElement();

            // read the shared metadata
            if(root.getName().equals( LOBTableElement)) {

                for(Element e: root.getChildren()) {

                    // ignore any child elements that are not  LOBtype
                    if(e.getName().equals( LOBtypeElement)){

                        // read the  LOBtype attributes
                        LineofBattle  lineofbattle = new LineofBattle();
                        lineofbattle.setLOBName(e.getAttribute( LOBtypeLOBNameAttr).getValue());
                        lineofbattle.setNationality(DoConversion.ConverttoNationality(e.getAttribute( LOBtypeNationalityAttr).getIntValue()));
                        lineofbattle.setOBLink(e.getAttribute( LOBtypeOBLinkAttr).getIntValue());
                        lineofbattle.setFirePower((e.getAttribute( LOBtypeFirepowerAttr).getIntValue()));
                        lineofbattle.setRange((e.getAttribute( LOBtypeRangeAttr).getIntValue()));
                        lineofbattle.setMoraleLevel((e.getAttribute( LOBtypeMoraleAttr).getIntValue()));
                        lineofbattle.setAssaultFire((e.getAttribute( LOBtypeAssaultFireAttr).getBooleanValue()));
                        lineofbattle.setSprayFire((e.getAttribute( LOBtypeSprayingFireAttr).getBooleanValue()));
                        lineofbattle.setELR5((e.getAttribute( LOBtypeELR5Attr).getBooleanValue()));
                        lineofbattle.setUClass((e.getAttribute( LOBtypeClassAttr).getIntValue()));
                        lineofbattle.setSmoke((e.getAttribute( LOBtypeSmokeAttr).getIntValue()));
                        lineofbattle.setBrokenML((e.getAttribute( LOBtypeBrokenMLAttr).getIntValue()));
                        lineofbattle.setBPV((e.getAttribute( LOBtypeBPVAttr).getIntValue()));
                        lineofbattle.setRedTo(e.getAttribute( LOBtypeRedToAttr).getValue());
                        lineofbattle.setSubTo(e.getAttribute( LOBtypeSubToAttr).getValue());
                        lineofbattle.setHardTo(e.getAttribute( LOBtypeHardToAttr).getValue());
                        lineofbattle.setSelfRally((e.getAttribute( LOBtypeSelfRallyAttr).getBooleanValue()));
                        lineofbattle.setReducesTo((e.getAttribute( LOBtypeReducesToAttr).getIntValue()));
                        lineofbattle.setSubstitutesTo((e.getAttribute( LOBtypeSubstitutesToAttr).getIntValue()));
                        lineofbattle.setHardensTo((e.getAttribute( LOBtypeHardensToAttr).getIntValue()));
                        lineofbattle.setUnitType(DoConversion.ConverttoUnitType(e.getAttribute( LOBtypeUnittypeAttr).getIntValue()));

                        // add the terrain type to the terrain list
                         LOBTableTypes.put( Integer.toString(lineofbattle.getOBLink()),  lineofbattle);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new JDOMException("Error reading the  LOB Table metadata", e);
        }
    }



    /**
     * @return the list of terrain types
     */
    public HashMap<String,  LineofBattle> getLOBTableTypes() {

        return  LOBTableTypes;
    }
}
