package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public class Leader {
    private String pLeaderName;
    private int pOBLink;
    private int pLDRM;
    private int pRanktype;
    private int pMoraleLevel;
    private boolean pIsAHero;
    private Constantvalues.Utype pUnitType;

    public Leader(){}

    public String getLeaderName(){return pLeaderName;}
    public void setLeaderName(String value){pLeaderName = value;}
    public int getOBLink() {return pOBLink;}
    public void setOBLink(int value) {pOBLink = value;}
    public int getLDRM() {return pLDRM;}
    public void setLDRM( int value) {pLDRM   = value;}
    public int getRanktype() {return pRanktype;}
    public void setRanktype(int value) {pRanktype = value;}
    public int getMoraleLevel(){return pMoraleLevel;}
    public void setMoraleLevel(int value) {pMoraleLevel = value;}
    public boolean getIsAHero() {return pIsAHero;}
    public void setIsAHero(boolean value) {pIsAHero = value;}
    public void setUnitType(Constantvalues.Utype value) {pUnitType = value;}
    public Constantvalues.Utype getUnitType() {return pUnitType;}

}
