package VASL.build.module.fullrules.MapDataClasses;

import VASL.build.module.fullrules.Constantvalues;

public class HexsideType {
    private Constantvalues.Hexside pHexsidevalue;
    private int pTEM;
    private boolean pSideHalfObstacle;
    private boolean pSideBypassOK;
    private String pSidedesc;
    private double pmfcot;
    private double pmpcot;
    private boolean pIsRoad;
    private boolean pIsWallHdRdbk;

    public HexsideType(Constantvalues.Hexside PassHexsidevalue, int PassTEM, boolean PassSideHalfObstacle, boolean PassBypassOK, String PassSideDesc, double Passmfcot, double Passmpcot,
                        boolean PassIsRoad, boolean PassIsWallHdRdbk) {
        pHexsidevalue= PassHexsidevalue;
        pTEM = PassTEM;
        pSideHalfObstacle= PassSideHalfObstacle;
        pmfcot = Passmfcot;
        pmpcot = Passmpcot;
        pSideBypassOK = PassBypassOK;
        pSidedesc = PassSideDesc;
        pIsRoad = PassIsRoad;
        pIsWallHdRdbk = PassIsWallHdRdbk;
    }

    public Constantvalues.Hexside getpHexsidevalue() {return pHexsidevalue;}
    public int getTEM() {return pTEM;}
    public boolean getSideHalfObstacle() {return pSideHalfObstacle;}
    public double getmfcot() {return pmfcot;}
    public double getmpcot() {return pmpcot;}
    public boolean isSideBypassOK() {return pSideBypassOK;}
    public String getHexdesc() {return pSidedesc;}
    public boolean isRoad(){return pIsRoad;}
    public boolean isWallHdRdbk() {return pIsWallHdRdbk;}

}
