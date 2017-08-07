package VASL.build.module.fullrules.ObjectClasses;

// Second level interface implemented by personnel unit instances
public interface MovingPersuniti{
        double getMFAvailable();
        void setMFAvailable(double value);
        String getItemName();
        double CalcMF();
        int getItemID();
        int getIPC();
        boolean IsDummy();
        boolean IsConcealed();
        boolean getUsingDT();
        void setUsingDT(boolean value);
        boolean getUsingRoadBonus();
        void setUsingRoadBonus(boolean value);
        boolean getHasLdrBonus();
        void setHasLdrBonus(boolean value);
        double getMFUsed();
        void setMFUsed(double value);
        boolean getPPImpact();
        int getAssaultMove();
        void setAssaultMove(int value);
        int getDash();
        void setDash(int value);
        int getHexEnteredSideCrossed();
        void setHexEnteredSideCrossed(int value);
        int getSmokeE();

}
