package VASL.build.module.fullrules.IFTCombatClasses;

import VASL.LOS.Map.Terrain;
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

public class IFTTable extends AbstractMetadata {
    private static final String IFTTableElement = "IFTTable";
    private static final String IFTtypeElement = "IFTtype";
    private static final String IFTtypeNameAttr = "name";
    private static final String IFTtypeDRAttr = "DR";
    private static final String IFTtypeFPColAttr = "FPCol";
    private static final String IFTtypeIFTResultAttr = "IFTResult";

    // maps terrain names to terrain objects
    private LinkedHashMap<String, IFTTableResult> IFTTableTypes = new LinkedHashMap<String, IFTTableResult>(176);


        /**
         * Parses a IFTTable metadata file
         * @param metadata an <code>InputStream</code> for the shared board metadata XML file
         * @throws JDOMException
         */
        public void parseIFTTableFile(InputStream metadata) throws JDOMException {


            SAXBuilder parser = new SAXBuilder();

            try {
                ConversionC DoConversion = new ConversionC();
                // the root element will be the IFTTable element
                Document doc = parser.build(metadata);
                Element root = doc.getRootElement();

                // read the shared metadata
                if(root.getName().equals(IFTTableElement)) {

                    for(Element e: root.getChildren()) {

                        // ignore any child elements that are not IFTtype
                        if(e.getName().equals(IFTtypeElement)){

                            // read the IFTtype attributes
                            IFTTableResult ifttableresult = new IFTTableResult();
                            ifttableresult.setName(e.getAttribute(IFTtypeNameAttr).getValue());
                            ifttableresult.setDR(e.getAttribute(IFTtypeDRAttr).getIntValue());
                            ifttableresult.setFPCol(e.getAttribute(IFTtypeFPColAttr).getIntValue());
                            ifttableresult.setIFTResult(DoConversion.ConverttoIFTResult(e.getAttribute(IFTtypeIFTResultAttr).getIntValue()));

                            // add the terrain type to the terrain list
                            IFTTableTypes.put(ifttableresult.getName(), ifttableresult);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace(System.err);
                throw new JDOMException("Error reading the IFT Table metadata", e);
            }
        }



        /**
         * @return the list of terrain types
         */
        public HashMap<String, IFTTableResult> getIFTTableTypes() {

            return IFTTableTypes;
        }
}
