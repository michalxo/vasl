package VASL.build.module.fullrules.DataClasses;

import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.MapDataClasses.GameLocation;

public class Unpossessed {
    
    private int pEquipmentID;
    private int phexnum;
    private Constantvalues.Typetype pEquipmenttype;
    private GameLocation phexlocation;
    private Constantvalues.AltPos pPosition;

	public Unpossessed (){

    }

	public int getEquipmentID() {return pEquipmentID;}
	public void setEquipmentID(int value) {pEquipmentID = value;}
	public int gethexnum() {return phexnum;}
	public void sethexnum(int value) {phexnum = value;}
	public Constantvalues.Typetype getEquipmenttype() {return pEquipmenttype;}
	public void setEquipmenttype(Constantvalues.Typetype value) {pEquipmenttype = value;}
	public GameLocation gethexlocation() {return phexlocation;}
	public void sethexlocation (GameLocation value) {phexlocation = value;}
	public Constantvalues.AltPos getPosition() {return pPosition;}
	public void setPosition (Constantvalues.AltPos value) {pPosition = value;}

}
