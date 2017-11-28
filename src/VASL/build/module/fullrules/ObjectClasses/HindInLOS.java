package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

public class HindInLOS {

    private String hexname;
    private Constantvalues.Feature hindtype;
    private String hinddesc;
    private int hindvalue;

    public HindInLOS (String Passhexname, Constantvalues.Feature Passhindtype, String Passhinddesc, int Passhindvalue){
        hexname=Passhexname;
        hindtype = Passhindtype;
        hinddesc = Passhinddesc;
        hindvalue = Passhindvalue;
    }

    public String getHindhexname (){return hexname;}
    public Constantvalues.Feature getHindtype (){return hindtype;}
    public String getHinddesc(){return hinddesc;}
    public int getHindvalue(){return hindvalue;}
}
