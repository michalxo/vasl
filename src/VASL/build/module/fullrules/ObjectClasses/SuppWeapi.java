package VASL.build.module.fullrules.ObjectClasses;

public interface SuppWeapi {
    // all support weapons must follow this interface


    // boolean IsMG();
    // boolean IsFT();
    // boolean IsDC();

    // baseSWi requires interface even though only one concrete class implements in order to allow decoration

    BaseSuppWeapc getbaseSW();
    FiringSuppWeapi getFiringSW();
    // public MovingPersuniti getMovingunit();
    void setFiringSW(FiringSuppWeapi value);
    // public void setMovingunit(MovingPersuniti value);

}
