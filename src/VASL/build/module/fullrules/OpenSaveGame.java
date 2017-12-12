package VASL.build.module.fullrules;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.DataClasses.OrderofBattle;
import VASL.build.module.fullrules.DataClasses.OrderofBattleSW;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.UtilityClasses.ConversionC;
import VASSAL.build.GameModule;
import VASSAL.tools.filechooser.FileChooser;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class OpenSaveGame {
    private LinkedList<OrderofBattle> punitsinplay = new LinkedList<OrderofBattle>();
    private LinkedList<OrderofBattleSW> pswinplay = new LinkedList<OrderofBattleSW>();
    private FileChooser pfilechooser;
    private Scenario pscenario;

    public OpenSaveGame (){

    }

    public void OpenGame(String dataFilename) throws IOException {

        String readString = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFilename));
            while ((readString = br.readLine()) != null) {
                if (!parseeachline(readString)) {System.out.println("Catch error!");}
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Catch error!");
        }
    }

    public void SaveGame (String dataFilename){
        String savestring = null;
        String outfilename = "";
        JOptionPane choosefileaction = new JOptionPane();
        FileChooser chooser = getFileChooser();
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            if ((chooser.getSelectedFile().getName().contains(".txt"))) {
                outfilename = chooser.getSelectedFile().getAbsolutePath();
            } else {
                outfilename = chooser.getSelectedFile().getAbsolutePath() + ".txt";
            }

            File destinationFile = new File(chooser.getSelectedFile().getAbsolutePath());
            if (destinationFile.exists()) {
                int overrideExistingFile = (choosefileaction.showConfirmDialog(null, "Replace existing file?", "File with same name found", JOptionPane.YES_NO_OPTION));
                if (overrideExistingFile != choosefileaction.YES_OPTION) {
                    GameModule.getGameModule().getChatter().send("Game NOT saved; click save button again and select new filename");
                    return;
                }
            }
            destinationFile = null;
            try {
                FileWriter fw = new FileWriter(outfilename);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                for (OrderofBattle saveunit: punitsinplay) {
                    savestring = createunitstringfromunitdata(saveunit);
                    out.println(savestring);
                }
                for (OrderofBattleSW savesw: pswinplay) {
                    savestring = createSWstringfromSWdata(savesw);
                    out.println(savestring);
                }
                savestring = createScenariostringfromscenariodata();
                out.println(savestring);
                out.close();
                bw.close();
                fw.close();
                GameModule.getGameModule().getChatter().send("Game saved!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
    // this will create an OrderofBattle unit from a line of a savedGame file and add it to a collection
    private void createunitdatafromunitstring(String readString){

        String[] tokens = readString.split("\\s+");
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();
        OrderofBattle Newunit = new OrderofBattle();

        Newunit.setOBUnit_ID(Integer.parseInt(tokens[1]));                                    //(5678);
        Newunit.setOBName(tokens[2]);                                                         //("467B");
        Newunit.setLOBLink(Integer.parseInt(tokens[3]));                                      //(5);
        Newunit.setHexname(tokens[4]);                                                        //("N2");
        Newunit.sethex(scen.getGameMap().getHex(Newunit.getHexname()));
        Newunit.sethexlocation(Newunit.gethex().getCenterLocation());            // this needs to be captured as a token - how? what values?  for now set as 0 placeholder
        Newunit.setLevelinHex(Integer.parseInt(tokens[6]));                                   //(0);
        Newunit.setHexEnteredSideCrossedLastMove(Integer.parseInt(tokens[7]));                //(0);
        Newunit.setPosition(DoConversion.ConverttoAltPosType(Integer.parseInt(tokens[8])));   //(0);
        Newunit.setCon_ID(Integer.parseInt(tokens[9]));                                       //(0);
        Newunit.setCX(DoConversion.ConverttoBoolean(tokens[10]));                              //(false);
        Newunit.setPinned(DoConversion.ConverttoBoolean(tokens[11]));                         //(false);
        Newunit.setELR(Integer.parseInt(tokens[12]));                                         //(3);
        Newunit.setSW(Integer.parseInt(tokens[13]));                                          //(0);
        Newunit.setFirstSWLink(Integer.parseInt(tokens[14]));                                 //(0);
        Newunit.setSecondSWlink(Integer.parseInt(tokens[15]));                                //(0);
        Newunit.setVisibilityStatus(DoConversion.ConverttoVisibilityStatus(Integer.parseInt(tokens[16])));  // (.VisibilityStatus.Visible);
        Newunit.setCharacterStatus(DoConversion.ConverttoCharacterStatus(Integer.parseInt(tokens[17])));    // Constantvalues.CharacterStatus.NONE);
        Newunit.setCombatStatus(DoConversion.ConverttoCombatStatus(Integer.parseInt(tokens[18])));          // Constantvalues.CombatStatus.None);
        Newunit.setFortitudeStatus(DoConversion.ConverttoFortitudeStatus(Integer.parseInt(tokens[19])));    // Constantvalues.FortitudeStatus.Normal);
        Newunit.setMovementStatus(DoConversion.ConverttoMovementStatus(Integer.parseInt(tokens[20])));      // Constantvalues.MovementStatus.NotMoving);
        Newunit.setNationality(DoConversion.ConverttoNationality(Integer.parseInt(tokens[21])));     //  Constantvalues.Nationality.Germans);
        Newunit.setOrderStatus(DoConversion.ConverttoOrderStatus(Integer.parseInt(tokens[22])));            // Constantvalues.OrderStatus.GoodOrder);
        Newunit.setRoleStatus(DoConversion.ConverttoRoleStatus(Integer.parseInt(tokens[23])));              // Constantvalues.RoleStatus.None);
        Newunit.setScenario(scen.getScenID());
        Newunit.setTurnArrives(Integer.parseInt(tokens[24]));

        punitsinplay.add(Newunit);
    }
    private String createunitstringfromunitdata(OrderofBattle saveunit){
        String saveunitstring = "";
        String[] tokens = new String[25];
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();
        tokens[0] = "unit";
        tokens[1] = Integer.toString(saveunit.getOBUnit_ID());                                    //(5678);
        tokens[2] = saveunit.getOBName();                                                         //("467B");
        tokens[3] = Integer.toString(saveunit.getLOBLink());                                      //(5);
        tokens[4] = saveunit.getHexname();                                                        //("N2");
        //saveunit.sethex(scen.getGameMap().getHex(saveunit.getHexname()));
        //saveunit.sethexlocation(saveunit.gethex().getCenterLocation());            // this needs to be captured as a token - how? what values?  for now set as 0 placeholder
        tokens[5] = "0";
        tokens[6] = Integer.toString(saveunit.getLevelinHex());                                   //(0);
        tokens[7] = Integer.toString(saveunit.getHexEnteredSideCrossedLastMove());                //(0);
        tokens[8] = Integer.toString(DoConversion.ConvertAltPosTypetoInt(saveunit.getPosition()));   //(0);
        tokens[9] = Integer.toString(saveunit.getCon_ID());                                       //(0);
        tokens[10] =  DoConversion.ConvertBooleantoString(saveunit.getCX());                              //(false);
        tokens[11] = DoConversion.ConvertBooleantoString(saveunit.getPinned());                         //(false);
        tokens[12] = Integer.toString(saveunit.getELR());                                         //(3);
        tokens[13] = Integer.toString(saveunit.getSW());                                          //(0);
        tokens[14] = Integer.toString(saveunit.getFirstSWLink());                                 //(0);
        tokens[15] = Integer.toString(saveunit.getSecondSWlink());                                //(0);
        tokens[16] = Integer.toString(DoConversion.ConvertVisibilityStatustoInt(saveunit.getVisibilityStatus()));  // (.VisibilityStatus.Visible);
        tokens[17] = Integer.toString(DoConversion.ConvertCharacterStatustoInt(saveunit.getCharacterStatus()));    // Constantvalues.CharacterStatus.NONE);
        tokens[18] = Integer.toString(DoConversion.ConvertCombatStatustoInt(saveunit.getCombatStatus()));          // Constantvalues.CombatStatus.None);
        tokens[19] = Integer.toString(DoConversion.ConvertFortitudeStatustoInt(saveunit.getFortitudeStatus()));    // Constantvalues.FortitudeStatus.Normal);
        tokens[20] = Integer.toString(DoConversion.ConvertMovementStatustoInt(saveunit.getMovementStatus()));      // Constantvalues.MovementStatus.NotMoving);
        tokens[21] = Integer.toString(DoConversion.ConvertNationalitytoInt(saveunit.getNationality()));     //  Constantvalues.Nationality.Germans);
        tokens[22] = Integer.toString(DoConversion.ConvertOrderStatustoInt(saveunit.getOrderStatus()));            // Constantvalues.OrderStatus.GoodOrder);
        tokens[23] = Integer.toString(DoConversion.ConvertRoleStatustoInt(saveunit.getRoleStatus()));              // Constantvalues.RoleStatus.None);
        //saveunit.setScenario(1);
        tokens[24] = Integer.toString(saveunit.getTurnArrives());

        for (int x=0; x < 25; ++x) {
            saveunitstring += tokens[x] + " ";
        }
        return saveunitstring;
    }

    private boolean parseeachline(String readString){
        String[] tokens = readString.split("\\s+");
        if (tokens[0].equals("unit")) {
            createunitdatafromunitstring(readString);
            return true;
        } else if(tokens[0].equals("scen")) {
            createscenariodatafromstring(readString);
            return true;
        } else if (tokens[0].equals("supw")) {
            createsupportweaponfromstring(readString);
            return true;
        }
        return false;
    }
    private void createsupportweaponfromstring(String readString){
        String[] tokens = readString.split("\\s+");
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();
        OrderofBattleSW NewSW = new OrderofBattleSW();

        NewSW.setOBWeapon(tokens[1]);
        NewSW.setOBSW_ID(Integer.parseInt(tokens[2]));
        NewSW.setWeaponType(Integer.parseInt(tokens[3]));
        NewSW.setCaptured(DoConversion.ConverttoBoolean(tokens[4]));
        NewSW.setNationality(DoConversion.ConverttoNationality(Integer.parseInt(tokens[5])));
        NewSW.setOwner(Integer.parseInt(tokens[6]));
        NewSW.setScenario(scen.getScenID());
        NewSW.setStatus(DoConversion.ConverttoSWStatus(Integer.parseInt(tokens[7])));
        NewSW.setHexname(tokens[8]);
        NewSW.setHex(scen.getGameMap().getHex(NewSW.getHexname()));
        NewSW.setHexlocation(NewSW.getHex().getCenterLocation());            // this needs to be captured as a token - how? what values?  for now set as 0 placeholder in token[9]
        NewSW.setPosition (DoConversion.ConverttoAltPosType(Integer.parseInt(tokens[10])));
        NewSW.setCombatStatus(DoConversion.ConverttoCombatStatus(Integer.parseInt(tokens[11])));
        NewSW.setVisibilityStatus(DoConversion.ConverttoVisibilityStatus(Integer.parseInt(tokens[12])));

        pswinplay.add(NewSW);
    }
    private String createSWstringfromSWdata(OrderofBattleSW savesw){
        String saveswstring = "";
        String[] tokens = new String[13];
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();
        tokens[0] = "supw";
        tokens[1] = (savesw.getOBWeapon());
        tokens[2] = Integer.toString(savesw.getOBSW_ID());
        tokens[3] = Integer.toString(savesw.getWeaponType());
        tokens[4] = DoConversion.ConvertBooleantoString(savesw.getCaptured());
        tokens[5] = Integer.toString(DoConversion.ConvertNationalitytoInt(savesw.getNationality()));
        tokens[6] = Integer.toString(savesw.getOwner());
        tokens[7] = Integer.toString(DoConversion.ConvertSWStatustoInt(savesw.getStatus()));
        tokens[8] = savesw.getHexname();
        tokens[9] = "0";     // this needs to be captured as a token - how? what values?  for now set as 0 placeholder
        tokens[10] = Integer.toString(DoConversion.ConvertAltPosTypetoInt(savesw.getPosition()));
        tokens[11] = Integer.toString(DoConversion.ConvertCombatStatustoInt(savesw.getCombatStatus()));
        tokens[12] = Integer.toString(DoConversion.ConvertVisibilityStatustoInt(savesw.getVisibilityStatus()));

        for (int x=0; x < 13; ++x) {
            saveswstring += tokens[x] + " ";
        }
        return saveswstring;
    }
    private void createscenariodatafromstring(String readString){
        pscenario = new Scenario(readString);
    }

    private String createScenariostringfromscenariodata(){
        String savescenstring = "";
        String[] tokens = new String[39];
        ConversionC DoConversion = new ConversionC();
        ScenarioC scen = ScenarioC.getInstance();

        tokens[0]="scen";
        tokens[1] = Double.toString(scen.getScendet().getGT());
        tokens[2] = Integer.toString(scen.getScendet().getCURRENTTURN());
        tokens[3] = Integer.toString(scen.getScendet().getYEAR());
        tokens[4] = scen.getScendet().getMONTH();
        //pDAYNIGHT = ConverttoDayNight(rs.getInt(6));  // Constantvalues.DayNight
        //pDATETIME = rs.getDate(7);
        tokens[5] =Integer.toString(scen.getScendet().getWINDSPEED());
        tokens[6] = scen.getScendet().getWINDDIR();
        tokens[7] = Integer.toString(scen.getScendet().getECDRM());
        tokens[8] = Integer.toString(scen.getScendet().getATTSAN());
        tokens[9] = Integer.toString(scen.getScendet().getDFNSAN());
        tokens[10] = Integer.toString(scen.getScendet().getATTIB());
        tokens[11] = Integer.toString(scen.getScendet().getDFNIB());
        tokens[12] = Integer.toString(scen.getScendet().getATTCP());
        tokens[13] = Integer.toString(scen.getScendet().getDFNCP());
        tokens[14] = Integer.toString(scen.getScendet().getATTIBL());
        tokens[15] = Integer.toString(scen.getScendet().getDFNIBL());
        tokens[16] = scen.getScendet().getAREINFORCE();
        tokens[17] = scen.getScendet().getDREINFORCE();
        tokens[18] = scen.getScendet().getFULLNAME();
        tokens[19] = Integer.toString(scen.getScendet().getCVPATT());
        tokens[20] = Integer.toString(scen.getScendet().getCVPDEF());
        tokens[21] = Integer.toString(scen.getScendet().getLVPATT());
        tokens[22] = Integer.toString(scen.getScendet().getLVPDEF());
        tokens[23] = scen.getScendet().getSanattaloc();
        tokens[24] = scen.getScendet().getSanattbloc();
        tokens[25] = scen.getScendet().getSandfnaloc();
        tokens[26] = scen.getScendet().getSandfnbloc();
        tokens[27] = Integer.toString(DoConversion.ConvertNationalitytoInt(scen.getScendet().getATT1()));  //Constantvalues.Nationality.Germans;
        tokens[28] = Integer.toString(DoConversion.ConvertNationalitytoInt(scen.getScendet().getDFN1()));
        tokens[29] = Integer.toString(DoConversion.ConvertPhasetoInt(scen.getScendet().getPhase()));
        tokens[30] = Integer.toString(scen.getScendet().getScenNum());
        tokens[31] = Integer.toString(scen.getScendet().getMistDust());
        tokens[32] = Integer.toString(DoConversion.ConvertNationalitytoInt(scen.getScendet().getATT2()));
        tokens[33] = Integer.toString(DoConversion.ConvertNationalitytoInt(scen.getScendet().getDFN2()));
        tokens[34] = Integer.toString(scen.getScendet().getWeather());
        tokens[35] = Integer.toString(DoConversion.ConvertRulestoInt(scen.getScendet().getRules()));
        tokens[36] = Integer.toString(DoConversion.ConvertMaptoInt(scen.getScendet().getMap()));
        tokens[37] = Integer.toString(scen.getScendet().getWindHexGrain());
        tokens[38] = Integer.toString(DoConversion.ConvertWhoCanDotoInt(scen.getScendet().getPTURN()));
        for (int x=0; x < 39; ++x) {
            savescenstring += tokens[x] + " ";
        }
        return savescenstring;
    }
    public LinkedList<OrderofBattle> unitsinplay() {
        return punitsinplay;
    }
    public LinkedList<OrderofBattleSW> swinplay() {return pswinplay;}
    public Scenario getScenario() {return pscenario;}
    private FileChooser getFileChooser(){
        if (pfilechooser != null){
            return pfilechooser;
        } else {
            pfilechooser = getfilechooser();
            VASSAL.tools.filechooser.FileFilter newfilter = new VASSAL.tools.filechooser.FileFilter() {

                public String getDescription() {return "Text Documents (*.txt)";}
                public boolean accept(File f) {return f.getName().toLowerCase().endsWith(".txt");}
            };
            pfilechooser.setFileFilter(newfilter);
            pfilechooser.setCurrentDirectory(GameModule.getGameModule().getGameState().getSavedGameDirectoryPreference().getFileValue());
            return pfilechooser;
        }
    }
    public FileChooser getfilechooser() {
        FileChooser newfilechooser = null;
        if (this.pfilechooser == null) {
            newfilechooser = FileChooser.createFileChooser(GameModule.getGameModule().getFrame(), GameModule.getGameModule().getGameState().getSavedGameDirectoryPreference());
        } else {
            //this.pfilechooser.resetChoosableFileFilters();
            //this.pfilechooser.rescanCurrentDirectory();
        }

        return newfilechooser;
    }
}
