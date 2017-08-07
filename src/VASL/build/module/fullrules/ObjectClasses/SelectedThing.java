package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public class SelectedThing {
    // holds information about items (units, vehicles, guns, SW, ? and terrain) right-selected for popup display
    // one object per item, held in collection SelectedThings
    private int typevalue;
    private int itemvalue;
    private Constantvalues.Phase phasevalue;
    private Constantvalues.WhoCanDo attdefvalue;

    public SelectedThing(Constantvalues.Phase PassPhase, int PassWhatClicked, Constantvalues.WhoCanDo PassAttorDef, int PassTypeClicked) {
        phasevalue = PassPhase;
        itemvalue = PassWhatClicked;
        attdefvalue = PassAttorDef;
        typevalue = PassTypeClicked;
    }
    public int getTypeClicked() {return typevalue;}
    public int getWhatClicked() {return itemvalue;}
    public Constantvalues.WhoCanDo getAttorDef() {return attdefvalue;}
    public Constantvalues.Phase getPhase() {return phasevalue;}

}
