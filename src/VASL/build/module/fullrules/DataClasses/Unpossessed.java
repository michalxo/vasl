package VASL.build.module.fullrules.DataClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class Unpossessed {
    
    private int pEquipmentID;
    private Hex phex;
    private Constantvalues.Typetype pEquipmenttype;
    private Location phexlocation;
    private Constantvalues.AltPos pPosition;

	public Unpossessed (){

    }

	public int getEquipmentID() {return pEquipmentID;}
	public void setEquipmentID(int value) {pEquipmentID = value;}
	public Hex gethex() {return phex;}
	public void sethex(Hex value) {phex = value;}
	public Constantvalues.Typetype getEquipmenttype() {return pEquipmenttype;}
	public void setEquipmenttype(Constantvalues.Typetype value) {pEquipmenttype = value;}
	public Location gethexlocation() {return phexlocation;}
	public void sethexlocation (Location value) {phexlocation = value;}
	public Constantvalues.AltPos getPosition() {return pPosition;}
	public void setPosition (Constantvalues.AltPos value) {pPosition = value;}

}
