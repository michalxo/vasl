package VASL.build.module.fullrules.MapDataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class LocationType {
    private Constantvalues.Location pLocationvalue;
    private int pTEM;
    private int pLOSHindDRM;
    private double pmfcot;
    private double pmpcot;
    private boolean pBypassOK;
    private boolean pbogcheck;
    private String pTerraindesc;
    private int pObstHeight;
    private boolean pBuilding;
    private boolean pRoad;
    private boolean pBridge;
    private boolean pPillbox;
    private boolean pRemoveWhenUnoccupied;

    public LocationType(Constantvalues.Location PassLocationvalue, int PassTEM, int PassLOSHindDRM, double Passmfcot, double Passmpcot, boolean PassBypassOK, boolean Passbogcheck, String PassTerrainDesc,
                        int PassObstHeight, boolean PassBuilding, boolean PassRoad, boolean PassBridge, boolean PassPillbox, boolean PassRemoveWhenUnoccupied) {
        pLocationvalue= PassLocationvalue;
        pTEM = PassTEM;
        pLOSHindDRM= PassLOSHindDRM;
        pmfcot = Passmfcot;
        pmpcot = Passmpcot;
        pBypassOK = PassBypassOK;
        pbogcheck = Passbogcheck;
        pTerraindesc = PassTerrainDesc;
        pObstHeight = PassObstHeight;
        pBuilding = PassBuilding;
        pRoad = PassRoad;
        pBridge = PassBridge;
        pPillbox = PassPillbox;
        pRemoveWhenUnoccupied = PassRemoveWhenUnoccupied;
    }
    /*public LocationType(ResultSet rs){
        try {
            PassLocationvalue = ConverttoLocationType(rs.getInt(1));
            PassTEM = rs.getInt(2);
            PassLOSHindDRM = rs.getInt(3);
            Passmfcot = rs.getDouble(4);
            Passmpcot = rs.getDouble(5);
            PassBypassOK = rs.getBoolean(6);
            Passbogcheck = rs.getBoolean(7);
            PassTerrainDesc = rs.getString(8);
            PassObstHeight = rs.getInt(10);
            assBuilding = rs.getBoolean(11);
            PassRoad = rs.getBoolean(12);
            PassBridge = rs.getBoolean(13);
            PassPillbox = rs.getBoolean(14);
            PassRemoveWhenUnoccupied = rs.getBoolean(15);
        } catch (Exception e) {

        }
    }*/

    public Constantvalues.Location getLocationvalue() {return pLocationvalue;}
    public int getTEM() {return pTEM;}
    public int getLOSHindDRM() {return pLOSHindDRM;}
    public double getmfcot() {return pmfcot;}
    public double getmpcot() {return pmpcot;}
    public boolean isBypassOK() {return pBypassOK;}
    public boolean isbogcheck() {return pbogcheck;}
    public String getTerraindesc() {return pTerraindesc;}
    public int getObstHeight() {return pObstHeight;}
    public boolean isBuilding() {return pBuilding;}
    public boolean isRoad(){return pRoad;}
    public boolean isBridge() {return pBridge;}
    public boolean isPillbox() {return pPillbox;}
    public boolean isRemoveWhenUnoccupied() {return pRemoveWhenUnoccupied;}


}
