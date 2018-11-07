package VASL.build.module.fullrules.DataClasses;

import VASL.LOS.Map.Hex;
import VASL.LOS.Map.Location;
import VASL.build.module.fullrules.Constantvalues;

public class ThingsToDo {
    private int pScenario;
    private Constantvalues.Phase pPhase;
    private Constantvalues.ToDo pToDo;
    private Hex pHex;
    private Location pHexlocation;
    private Constantvalues.WhoCanDo pPlayerTurn;
    private int pItemID;

    public ThingsToDo( ){

    }

    public int getScenario() {return pScenario;}
    public void setScenario(int value) {pScenario = value;}
    public Constantvalues.Phase getPhase() {return pPhase;}
    public void setPhase(Constantvalues.Phase value ) {pPhase = value;}
    public Constantvalues.ToDo getToDo() {return pToDo;}
    public void setToDo(Constantvalues.ToDo value) {pToDo = value;}
    public Hex getHex() {return pHex;}
    public void setHex(Hex value){pHex = value;}
    public Location getLocation(){return pHexlocation;}
    public void setLocation(Location value){ pHexlocation = value;}
    public Constantvalues.WhoCanDo getPlayerTurn(){ return pPlayerTurn;}
    public void setPlayerTurn(Constantvalues.WhoCanDo value){pPlayerTurn = value;}
    public int getItemID() {return pItemID;}
    public void setItemID(int value){pItemID = value;}

    public int GetNextId() {
        // called by Linqdata.CreateNewThingsToDo
        // pulls all records into a temporary collection of type ThingsToDo and identifies highest value of Itemid
        // adds 1 to get new ID number
        return 0;
        /*Dim Linqdata = ASLXNA.DataC.GetInstance()
        Dim ThingsToDocol As IQueryable (Of ThingsToDo)
        Try
        'gets records
        ThingsToDocol = From QU In Linqdata.db.ThingsToDos
        Catch ex As Exception
        MsgBox("Some kind of error", , "Retieve ThingsToDo Failure")
        Return Nothing
        End Try
        Dim Maxhexnum As Integer = 0
        For Each ToDoTest As ThingsToDo In ThingsToDocol
        'compares value with largest and selects new largest
        If ToDoTest.ItemID > Maxhexnum Then Maxhexnum = ToDoTest.ItemID
        Next ToDoTest
        Return(Maxhexnum + 1) 'creates new largest for next scen value
        End Function
        Public Function DoPhase(ByVal PassToDo As Integer) As Integer
        Select Case PassToDo
        Case ConstantClassLibrary.
        ASLXNA.UMove.ClearRubble, ConstantClassLibrary.ASLXNA.UMove.ClearRdBlk, ConstantClassLibrary.ASLXNA.UMove.ClearSetDC, ConstantClassLibrary.ASLXNA.UMove.ClearWire, ConstantClassLibrary.ASLXNA.UMove.ClearMine,
                ConstantClassLibrary.ASLXNA.UMove.ClearEnterRubble1 To ConstantClassLibrary.
        ASLXNA.UMove.ClearEnterMines6
        Return ConstantClassLibrary.ASLXNA.Phase.CloseCombat
        Case ConstantClassLibrary.ASLXNA.UMove.DoPlaceDC
        Return ConstantClassLibrary.ASLXNA.Phase.AdvancingFire
        Case Else
        Return Nothing
        End Select*/
    }
}
