package VASL.build.module.fullrules;

import VASL.LOS.Map.Hex;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.DataClasses.OrderofBattleSW;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASSAL.build.GameModule;
import VASSAL.preferences.Prefs;

import java.io.*;
import java.util.LinkedList;

public class OpenSaveGame {
    private LinkedList<OrderofBattle> punitsinplay = new LinkedList<OrderofBattle>();
    private LinkedList<OrderofBattleSW> pswinplay = new LinkedList<OrderofBattleSW>();

    public OpenSaveGame (){

    }

    public void OpenGame(String dataFilename) throws IOException {
        String readFile = "C:\\Users\\dougr_000\\IdeaProjects\\vasldougreg\\src\\VASL\\build\\module\\fullrules\\Game\\read.txt";
        String readString = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(readFile));
            while ((readString = br.readLine()) != null) {
                createunitdatafromunitstring(readString);
            }
            br.close();

        }
        catch (IOException e){
            System.out.println("Catch error!");
        }
    }

    public void SaveGame (String dataFilename){
        String unitstring = null;
        //String testprefs =GameModule.getGameModule().getPrefs().getOption("savedGameDir").toString();
        //GameModule.getGameModule().saveAs();
        //String outfilename = testprefs + "\\Write.txt";
        String outfilename = ("C:\\Users\\dougr_000\\IdeaProjects\\vasldougreg\\src\\VASL\\build\\module\\fullrules\\Game\\read.txt");
        try {
            FileWriter fw = new FileWriter(outfilename);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for (OrderofBattle saveunit: punitsinplay) {
                unitstring = createunitstringfromunitdata(saveunit);
                out.println(unitstring);
            }
            out.close();
            bw.close();
            fw.close();
        } catch (IOException e) {

        }
    }
    private void createunitdatafromunitstring(String readString){

        String[] tokens = readString.split("\\s+");
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();
        OrderofBattle Newunit = new OrderofBattle();

        Newunit.setOBUnit_ID(Integer.parseInt(tokens[0]));                                    //(5678);
        Newunit.setOBName(tokens[1]);                                                         //("467B");
        Newunit.setLOBLink(Integer.parseInt(tokens[2]));                                      //(5);
        Newunit.setHexname(tokens[3]);                                                        //("N2");
        Newunit.sethex(scen.getGameMap().getHex(Newunit.getHexname()));
        Newunit.sethexlocation(Newunit.gethex().getCenterLocation());            // this needs to be captured as a token - how? what values?  for now set as 0 placeholder
        Newunit.setLevelinHex(Integer.parseInt(tokens[5]));                                   //(0);
        Newunit.setHexEnteredSideCrossedLastMove(Integer.parseInt(tokens[6]));                //(0);
        Newunit.setPosition(DoConversion.ConverttoAltPosType(Integer.parseInt(tokens[7])));   //(0);
        Newunit.setCon_ID(Integer.parseInt(tokens[8]));                                       //(0);
        Newunit.setCX(DoConversion.ConverttoBoolean(tokens[9]));                              //(false);
        Newunit.setPinned(DoConversion.ConverttoBoolean(tokens[10]));                         //(false);
        Newunit.setELR(Integer.parseInt(tokens[11]));                                         //(3);
        Newunit.setSW(Integer.parseInt(tokens[12]));                                          //(0);
        Newunit.setFirstSWLink(Integer.parseInt(tokens[13]));                                 //(0);
        Newunit.setSecondSWlink(Integer.parseInt(tokens[14]));                                //(0);
        Newunit.setVisibilityStatus(DoConversion.ConverttoVisibilityStatus(Integer.parseInt(tokens[15])));  // (.VisibilityStatus.Visible);
        Newunit.setCharacterStatus(DoConversion.ConverttoCharacterStatus(Integer.parseInt(tokens[16])));    // Constantvalues.CharacterStatus.NONE);
        Newunit.setCombatStatus(DoConversion.ConverttoCombatStatus(Integer.parseInt(tokens[17])));          // Constantvalues.CombatStatus.None);
        Newunit.setFortitudeStatus(DoConversion.ConverttoFortitudeStatus(Integer.parseInt(tokens[18])));    // Constantvalues.FortitudeStatus.Normal);
        Newunit.setMovementStatus(DoConversion.ConverttoMovementStatus(Integer.parseInt(tokens[19])));      // Constantvalues.MovementStatus.NotMoving);
        Newunit.setNationality(DoConversion.ConverttoNationality(Integer.parseInt(tokens[20])));     //  Constantvalues.Nationality.Germans);
        Newunit.setOrderStatus(DoConversion.ConverttoOrderStatus(Integer.parseInt(tokens[21])));            // Constantvalues.OrderStatus.GoodOrder);
        Newunit.setRoleStatus(DoConversion.ConverttoRoleStatus(Integer.parseInt(tokens[22])));              // Constantvalues.RoleStatus.None);
        //Newunit.setScenario(1);
        Newunit.setTurnArrives(Integer.parseInt(tokens[23]));

        punitsinplay.add(Newunit);
    }
    private String createunitstringfromunitdata(OrderofBattle saveunit){
        String saveunitstring = "";
        String[] tokens = new String[24];
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();

        tokens[0] = Integer.toString(saveunit.getOBUnit_ID());                                    //(5678);
        tokens[1] = saveunit.getOBName();                                                         //("467B");
        tokens[2] = Integer.toString(saveunit.getLOBLink());                                      //(5);
        tokens[3] = saveunit.getHexname();                                                        //("N2");
        //saveunit.sethex(scen.getGameMap().getHex(saveunit.getHexname()));
        //saveunit.sethexlocation(saveunit.gethex().getCenterLocation());            // this needs to be captured as a token - how? what values?  for now set as 0 placeholder
        tokens[4] = "0";
        tokens[5] = Integer.toString(saveunit.getLevelinHex());                                   //(0);
        tokens[6] = Integer.toString(saveunit.getHexEnteredSideCrossedLastMove());                //(0);
        tokens[7] = Integer.toString(DoConversion.ConvertAltPosTypetoInt(saveunit.getPosition()));   //(0);
        tokens[8] = Integer.toString(saveunit.getCon_ID());                                       //(0);
        tokens[9] =  DoConversion.ConvertBooleantoString(saveunit.getCX());                              //(false);
        tokens[10] = DoConversion.ConvertBooleantoString(saveunit.getPinned());                         //(false);
        tokens[11] = Integer.toString(saveunit.getELR());                                         //(3);
        tokens[12] = Integer.toString(saveunit.getSW());                                          //(0);
        tokens[13] = Integer.toString(saveunit.getFirstSWLink());                                 //(0);
        tokens[14] = Integer.toString(saveunit.getSecondSWlink());                                //(0);
        tokens[15] = Integer.toString(DoConversion.ConvertVisibilityStatustoInt(saveunit.getVisibilityStatus()));  // (.VisibilityStatus.Visible);
        tokens[16] = Integer.toString(DoConversion.ConvertCharacterStatustoInt(saveunit.getCharacterStatus()));    // Constantvalues.CharacterStatus.NONE);
        tokens[17] = Integer.toString(DoConversion.ConvertCombatStatustoInt(saveunit.getCombatStatus()));          // Constantvalues.CombatStatus.None);
        tokens[18] = Integer.toString(DoConversion.ConvertFortitudeStatustoInt(saveunit.getFortitudeStatus()));    // Constantvalues.FortitudeStatus.Normal);
        tokens[19] = Integer.toString(DoConversion.ConvertMovementStatustoInt(saveunit.getMovementStatus()));      // Constantvalues.MovementStatus.NotMoving);
        tokens[20] = Integer.toString(DoConversion.ConvertNationalitytoInt(saveunit.getNationality()));     //  Constantvalues.Nationality.Germans);
        tokens[21] = Integer.toString(DoConversion.ConvertOrderStatustoInt(saveunit.getOrderStatus()));            // Constantvalues.OrderStatus.GoodOrder);
        tokens[22] = Integer.toString(DoConversion.ConvertRoleStatustoInt(saveunit.getRoleStatus()));              // Constantvalues.RoleStatus.None);
        //saveunit.setScenario(1);
        tokens[23] = Integer.toString(saveunit.getTurnArrives());

        for (int x=0; x < 24; ++x) {
            saveunitstring += tokens[x] + " ";
        }
        return saveunitstring;
    }
    public LinkedList<OrderofBattle> unitsinplay() {
        return punitsinplay;
    }
    public LinkedList<OrderofBattleSW> swinplay() {return pswinplay;}
}
