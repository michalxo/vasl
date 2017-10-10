package VASL.build.module.fullrules.ObjectClasses;
// Decorator for persunits
// interfaces
public abstract class PersunitDecoratorac implements PersUniti {
    protected PersUniti decoratedPersUnit;

    public PersunitDecoratorac(PersUniti decoratedUnit){
        this.decoratedPersUnit = decoratedUnit;
    }
    public Basepersuniti getbaseunit() {return decoratedPersUnit.getbaseunit();}
    public FiringPersUniti getFiringunit(){return decoratedPersUnit.getFiringunit();}
    public MovingPersuniti getMovingunit(){return decoratedPersUnit.getMovingunit();}
    public TargetPersUniti getTargetunit(){return decoratedPersUnit.getTargetunit();}
    public void setFiringunit(FiringPersUniti value) {decoratedPersUnit.setFiringunit(value);}
    public void setMovingunit(MovingPersuniti value){decoratedPersUnit.setMovingunit(value);}
    public void setTargetunit(TargetPersUniti value){decoratedPersUnit.setTargetunit(value);}
}
