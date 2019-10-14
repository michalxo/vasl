package VASL.build.module.fullrules.ObjectChangeClasses;

// differs from StatusChangei in that it does not require getNewTargs, getNewFirings to be implemented
public interface UnitChangei {
    boolean TakeAction();
}
