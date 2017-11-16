package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.DataClasses.SupportWeapon;
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

public class SupportWeaponTable extends AbstractMetadata {
    private static final String  SuppWTableElement = "SuppWTable";
    private static final String  SuppWtypeElement = "SuppWtype";
    private static final String  SuppWtypeSWNameAttr = "SWname";
    private static final String  SuppWtypeOBLinkAttr = "OBLink";
    private static final String  SuppWtypeNationalityAttr = "Nationality";
    private static final String  SuppWtypeFirepowerAttr = "Firepower";
    private static final String  SuppWtypeRangeAttr = "Range";
    private static final String  SuppWtypeAssaultFireAttr = "AssaultFire";
    private static final String  SuppWtypeSprayingFireAttr = "SprayingFire";
    private static final String  SuppWtypePortageCostAttr = "PortageCost";
    private static final String  SuppWtypeWeapontypeAttr = "Weapontype";
    private static final String  SuppWtypeMalfunctionAttr = "Malfunction";
    private static final String  SuppWtypeRepairAttr = "Repair";
    private static final String  SuppWtypeBreakdownAttr = "Breakdown";
    private static final String  SuppWtypeROFAttr = "ROF";
    private static final String  SuppWtypeDismantledPPAttr = "DismantledPP";

    // maps terrain names to terrain objects
    private LinkedHashMap<String, SupportWeapon> SuppWTableTypes = new LinkedHashMap<String, SupportWeapon>(50);


    /**
     * Parses a  SuppWTable metadata file
     * @param metadata an <code>InputStream</code> for the SuppWTable XML file
     * @throws JDOMException
     */
    public void parseSuppWTableFile(InputStream metadata) throws JDOMException {


        SAXBuilder parser = new SAXBuilder();

        try {
            ConversionC DoConversion = new ConversionC();
            // the root element will be the  SuppWTable element
            Document doc = parser.build(metadata);
            Element root = doc.getRootElement();

            // read the shared metadata
            if(root.getName().equals( SuppWTableElement)) {

                for(Element e: root.getChildren()) {

                    // ignore any child elements that are not  SuppWtype
                    if(e.getName().equals( SuppWtypeElement)){

                        // read the  SuppWtype attributes
                        SupportWeapon  SuppW = new SupportWeapon();
                        SuppW.setWeaponName(e.getAttribute( SuppWtypeSWNameAttr).getValue());
                        SuppW.setOBLink(e.getAttribute( SuppWtypeOBLinkAttr).getIntValue());
                        SuppW.setNationality(DoConversion.ConverttoNationality(e.getAttribute( SuppWtypeNationalityAttr).getIntValue()));
                        SuppW.setFIREPOWER((e.getAttribute( SuppWtypeFirepowerAttr).getIntValue()));
                        SuppW.setRANGE((e.getAttribute( SuppWtypeRangeAttr).getIntValue()));
                        SuppW.setASSAULTFIRE((e.getAttribute( SuppWtypeAssaultFireAttr).getBooleanValue()));
                        SuppW.setSPRAYINGFIRE((e.getAttribute( SuppWtypeSprayingFireAttr).getBooleanValue()));
                        SuppW.setPORTAGECOST((e.getAttribute( SuppWtypePortageCostAttr).getIntValue()));
                        SuppW.setWeaponType(DoConversion.ConverttoSWType(e.getAttribute( SuppWtypeWeapontypeAttr).getIntValue()));
                        SuppW.setMALFUNCTION((e.getAttribute( SuppWtypeMalfunctionAttr).getIntValue()));
                        SuppW.setREPAIR((e.getAttribute( SuppWtypeRepairAttr).getIntValue()));
                        SuppW.setBREAKDOWN((e.getAttribute( SuppWtypeBreakdownAttr).getIntValue()));
                        SuppW.setROF((e.getAttribute( SuppWtypeROFAttr).getIntValue()));
                        SuppW.setDismantledPP((e.getAttribute( SuppWtypeDismantledPPAttr).getIntValue()));
                        // add the terrain type to the terrain list
                        SuppWTableTypes.put( Integer.toString(SuppW.getOBLink()),  SuppW);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new JDOMException("Error reading the  SuppW Table metadata", e);
        }
    }



    /**
     * @return the list of terrain types
     */
    public HashMap<String,  SupportWeapon> getSuppWTableTypes() {

        return  SuppWTableTypes;
    }}
