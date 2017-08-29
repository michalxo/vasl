package VASL.build.module.fullrules.ObjectChangeClasses;

import VASL.build.module.fullrules.ObjectClasses.PersUniti;
import VASL.build.module.fullrules.ObjectFactoryClasses.PersCreation;

import java.util.LinkedList;

public interface StatusChangei {

    LinkedList<PersUniti> GetNewTargs = new LinkedList<PersUniti>();   //As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    LinkedList<PersUniti> GetNewFirings = new LinkedList<PersUniti>(); //As List(Of ObjectClassLibrary.ASLXNA.PersUniti)
    //ReadOnly Property NewPopupitems As List(Of ObjectClassLibrary.ASLXNA.MenuItemObjectholderinteface)
    boolean Takeaction(PersUniti value);

}
