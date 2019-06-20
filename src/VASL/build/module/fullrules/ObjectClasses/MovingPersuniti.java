package VASL.build.module.fullrules.ObjectClasses;

import VASL.build.module.fullrules.Constantvalues;

// Second level interface implemented by personnel unit instances
public interface MovingPersuniti{
        double getMFAvailable();
        void setMFAvailable(double value);
        String getItemName();
        double CalcMF();
        int getItemID();
        int getIPC();
        boolean IsDummy();
        boolean getIsConcealed();
        void setIsConcealed(boolean value);
        boolean getUsingDT();
        void setUsingDT(boolean value);
        boolean getUsingRoadBonus();
        void setUsingRoadBonus(boolean value);
        boolean getHasLdrBonus();
        void setHasLdrBonus(boolean value);
        double getMFUsed();
        void setMFUsed(double value);
        boolean getPPImpact();
        Constantvalues.MovementStatus getAssaultMove();
        void setAssaultMove(Constantvalues.MovementStatus value);
        Constantvalues.MovementStatus getDash();
        void setDash(Constantvalues.MovementStatus value);
        int getHexEnteredSideCrossed();
        void setHexEnteredSideCrossed(int value);
        int getSmokeE();
        boolean UpdateMovementStatus(PersUniti mover, Constantvalues.MovementStatus NewMovementStatus);

}
