package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.MapDataClasses.MapDataC;

import java.util.LinkedList;

public class BaseHex {
    private String prhexnamevalue; // readonly
    private int prHexIDvalue;  // readonly
    private int prHextertypevalue;
    private String prControlvalue;
    private String prStaircasevalue;
    private int prhexside1value;  // readonly
    private int prhexside2value;// // readonly
    private int prhexside3value;  // readonly
    private int prhexside4value;  // readonly
    private int prhexside5value;   // readonly
    private int prhexside6value;   // readonly
    private double prHexBaseLevelValue;  // readonly
    private int prlocindex;   // readonly
    private LinkedList<SmokeHolder> prSmokeList;   // readonly
    private int prOBA;   // readonly

    public BaseHex(String PassHexname, int PassHexID, int PasshexTerrtype, int PassHexside1, int PassHexside2, int PassHexside3,
                   int PassHexside4, int PassHexside5, int PassHexside6, String Passstaircase, double PassBaselevel, String Passcontrol,
                   LinkedList<SmokeHolder> PassSmokeList, int PassOBA) {
        prhexnamevalue = PassHexname;
        prHexIDvalue = PassHexID;
        prHextertypevalue = PasshexTerrtype;
        prhexside1value = PassHexside1;
        prhexside2value = PassHexside2;
        prhexside3value = PassHexside3;
        prhexside4value = PassHexside4;
        prhexside5value = PassHexside5;
        prhexside6value = PassHexside6;
        prStaircasevalue = Passstaircase;
        prControlvalue = Passcontrol;
        prHexBaseLevelValue = PassBaselevel;
        MapDataC Maptables = MapDataC.GetInstance("", 0);  // use null values for parameters when sure instance exists
        //Dim LocationCol As IQueryable (Of MapDataClassLibrary.GameLocation) =Maptables.LocationCol
        //GetALocationFromMap Getlocs = new GetALocationFromMap(LocationCol);
        //GameLocation LocToUse = Getlocs.RetrieveLocationfromMaptable(PassHexID, PasshexTerrtype);
        //prlocindex = LocToUse.LocIndex;
        prSmokeList = PassSmokeList;
        prOBA = PassOBA;
    }
    // thread version
    public BaseHex(String PassHexname, int PassHexID, int PasshexTerrtype, int PassHexside1, int PassHexside2, int PassHexside3,
                   int PassHexside4, int PassHexside5, int PassHexside6, String Passstaircase, double PassBaselevel, String Passcontrol,
                   int PassThreadLOCIndex, LinkedList<SmokeHolder> PassSmokeList, int PassOBA) {
        prhexnamevalue = PassHexname;
        prHexIDvalue = PassHexID;
        prHextertypevalue = PasshexTerrtype;
        prhexside1value = PassHexside1;
        prhexside2value = PassHexside2;
        prhexside3value = PassHexside3;
        prhexside4value = PassHexside4;
        prhexside5value = PassHexside5;
        prhexside6value = PassHexside6;
        prStaircasevalue = Passstaircase;
        prControlvalue = Passcontrol;
        prHexBaseLevelValue = PassBaselevel;
        prlocindex = PassThreadLOCIndex;
        prSmokeList = PassSmokeList;
        prOBA = PassOBA;
    }

    public  String getHexName() {return prhexnamevalue;}
    public int getHexID() {return prHexIDvalue;}
    public int getHextertype() {return prHextertypevalue;}
    public void setHextertype(int value) {prHextertypevalue=value;}
    public int getHexside1() {return prhexside1value;}
    public int getHexside2() {return prhexside2value;}
    public int getHexside3() {return prhexside3value;}
    public int getHexside4() {return prhexside4value;}
    public int getHexside5() {return prhexside5value;}
    public int getHexside6() {return prhexside6value;}
    public double getHexBaseLevel() {return prHexBaseLevelValue;}
    public String getControl() {return prControlvalue;}
    public void setControl(String value) {prControlvalue=value;}
    public String getStaircase() {return prStaircasevalue;}
    public void setStaircase(String value) {prStaircasevalue = value;}
    public int getLocIndex() {return prlocindex;}
    public LinkedList<SmokeHolder> getSmokeList() {return prSmokeList;}
    public int getOBA() {return prOBA;}
}
