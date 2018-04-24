package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;

import java.util.LinkedList;

public interface StatusChangei {

    LinkedList<PersUniti> getNewTargs() ;   //As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    LinkedList<PersUniti> getNewFirings (); //As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    //ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    boolean Takeaction(PersUniti value);

}
