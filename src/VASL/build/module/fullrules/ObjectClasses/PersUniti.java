package VASL.build.module.fullrules.ObjectClasses;

// First level interface; all personnel unit instances will implement
public interface PersUniti {
    // all personnel units, including concealed and dummies must follow this interface

    // basepersuniti requires interface even though only one concrete class implements in order to allow decoration

    public Basepersuniti getbaseunit();
    public FiringPersUniti getFiringunit();
    public MovingPersuniti getMovingunit();
    public TargetPersUniti getTargetunit();
    public void setFiringunit(FiringPersUniti value);
    public void setMovingunit(MovingPersuniti value);
    public void setTargetunit(TargetPersUniti value);
}
