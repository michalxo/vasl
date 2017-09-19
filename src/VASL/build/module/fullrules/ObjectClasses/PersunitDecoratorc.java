package VASL.build.module.fullrules.ObjectClasses;

public abstract class PersunitDecoratorc implements PersUniti {
    protected PersUniti baseunit;

    public PersunitDecoratorc(PersUniti unittodecorate) {
        baseunit=unittodecorate;
    }

    public Basepersuniti getbaseunit() {
        return baseunit.getbaseunit();
    }
}
