package VASL.build.module.map;

/**
 * Created by dougr_000 on 5/9/2017.
  * Copyright (c) 2017 by Doug Rimmer
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License (LGPL) as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, copies are available
 * at http://www.opensource.org.
 */

import VASL.build.module.ASLMap;
import VASL.build.module.fullrules.Constantvalues;
import VASL.build.module.fullrules.DataClasses.Scenario;
import VASL.build.module.fullrules.Game.ScenarioC;
import VASL.build.module.fullrules.IFTCombatClasses.IIFTC;
import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.GameModule;
import VASSAL.build.module.GameComponent;
import VASSAL.build.module.Map;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.build.module.map.Drawable;
import VASSAL.command.Command;
import VASSAL.command.CommandEncoder;
import VASSAL.configure.HotKeyConfigurer;
import VASSAL.tools.SequenceEncoder;
import VASSAL.tools.imageop.Op;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VASSAL.configure.StringConfigurer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

interface ActionsNeedRepaintEvent {
        public void NeedRepaint();
}


enum ActionsToolBarPosition {TP_EAST, TP_WEST};


public class ActionsToolbar extends AbstractConfigurable implements GameComponent, CommandEncoder, Drawable, ActionsNeedRepaintEvent {

    private ASLMap m_objASLMap;
    private JToolBar m_Toolbar = null;
    //private ActionsQueueHandler m_objAQH = null;
    private ActionsToolBarPosition m_enToolbarPositionActions = ActionsToolBarPosition.TP_WEST;
    private boolean m_bToolbarActive = false;
    private final String ACTIONSTOOLBARPOS = "ActionsToolboxPos";
    private final String ACTIONSTOOLBARACTIVE = "ActionsToolboxActive";
    private JToggleButton m_Startbutton = null;
    // this component is not configurable
    @Override
    public Class<?>[] getAttributeTypes() {return new Class<?>[] {};}

    @Override
    public String[] getAttributeNames() {return new String[0];}

    @Override
    public String[] getAttributeDescriptions() {return new String[0];}

    @Override
    public String getAttributeValueString(String key) {return null;}

    @Override
    public void setAttribute(String key, Object value) {}

    @Override
    public void addTo(Buildable parent) {

        if (GameModule.getGameModule().getPrefs().getOption(ACTIONSTOOLBARPOS) == null) GameModule.getGameModule().getPrefs().addOption(null, new StringConfigurer(ACTIONSTOOLBARPOS, null));

        if (GameModule.getGameModule().getPrefs().getOption(ACTIONSTOOLBARACTIVE) == null) GameModule.getGameModule().getPrefs().addOption(null, new StringConfigurer(ACTIONSTOOLBARACTIVE, null));

        readToolbarPos();
        readToolbarActive();

        // add this component to the game and register a mouse listener
        if (parent instanceof ASLMap) {
            m_objASLMap = (ASLMap) parent;
            m_objASLMap.addDrawComponent(this);
            // is this necessary to fire Command?
            GameModule mod = GameModule.getGameModule();
            mod.addCommandEncoder(this);
            mod.getGameState().addGameComponent(this);
            m_objASLMap.getPopupMenu().addSeparator();
            JMenuItem l_ToggleActiontoolbar = new JMenuItem("Action Toolbox");
            l_ToggleActiontoolbar.setBackground(new Color(255,255,255));
            m_objASLMap.getPopupMenu().add(l_ToggleActiontoolbar);
            m_objASLMap.getPopupMenu().addSeparator();

            // button toolbar activation
            JCheckBoxMenuItem l_objActionToolbarVisibleChange = new JCheckBoxMenuItem("Action Toolbox activation (on/off)");

            l_objActionToolbarVisibleChange.setSelected(m_bToolbarActive);

            l_objActionToolbarVisibleChange.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    m_bToolbarActive = !m_bToolbarActive;

                    CreateToolbar();

                    saveToolbarActive();
                }
            });

            m_objASLMap.getPopupMenu().add(l_objActionToolbarVisibleChange);


        }

        GameModule.getGameModule().getGameState().addGameComponent(this);
    }

    public void readToolbarPos() {
        String l_strPref = (String)GameModule.getGameModule().getPrefs().getValue(ACTIONSTOOLBARPOS);

        if (l_strPref == null) l_strPref = "WEST";
        //hack to separate toolbars
        l_strPref="WEST";
        if (l_strPref.compareToIgnoreCase("EAST") == 0) {
            m_enToolbarPositionActions = ActionsToolBarPosition.TP_EAST;
        }
        else if (l_strPref.compareToIgnoreCase("WEST") == 0) {
            m_enToolbarPositionActions = ActionsToolBarPosition.TP_WEST;
        }
    }

    public void readToolbarActive() {
        String l_strPref = (String)GameModule.getGameModule().getPrefs().getValue(ACTIONSTOOLBARACTIVE);

        if (l_strPref == null) l_strPref = "NO";

        if (l_strPref.compareToIgnoreCase("YES") == 0) {
            m_bToolbarActive = true;
        }
        else if (l_strPref.compareToIgnoreCase("NO") == 0) {
            m_bToolbarActive = false;
        }
    }

    public void saveToolbarPos() {
        if (m_enToolbarPositionActions == ActionsToolBarPosition.TP_EAST)
            GameModule.getGameModule().getPrefs().setValue(ACTIONSTOOLBARPOS, "EAST");
        else if (m_enToolbarPositionActions == ActionsToolBarPosition.TP_WEST)
            GameModule.getGameModule().getPrefs().setValue(ACTIONSTOOLBARPOS, "WEST");

        try {
            GameModule.getGameModule().getPrefs().save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveToolbarActive() {
        if (m_bToolbarActive)
            GameModule.getGameModule().getPrefs().setValue(ACTIONSTOOLBARACTIVE, "YES");
        else
            GameModule.getGameModule().getPrefs().setValue(ACTIONSTOOLBARACTIVE, "NO");

        try {
            GameModule.getGameModule().getPrefs().save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g, Map map) {
        if ((m_Toolbar != null) && (m_Toolbar.isVisible()));
            //m_objAQH.draw(g, map, m_enToolbarPositionActions);
    }

    @Override
    public boolean drawAboveCounters() {return true;}

    @Override
    public void removeFrom(Buildable parent) {}

    @Override
    public HelpFile getHelpFile() {return null;}

    @Override
    public Class[] getAllowableConfigureComponents() {return new Class[0];}

    @Override
    public void setup(boolean gameStarting) {
        if (gameStarting) {
            SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        CreateToolbar();
        }
        });
            //m_objAQH.RegisterForDiceEvents(true);
        }
    }

    @Override
    public Command getRestoreCommand() {return null;}

    public void CreateToolbar() {
        if (m_Toolbar == null) {
            final int iW = 48;
            int l_iRow = 0;
            JButton l_objBtn;

            m_Toolbar = new JToolBar(SwingConstants.VERTICAL);
            m_Toolbar.setVisible(false);
            m_Toolbar.setFloatable(false);
            m_Toolbar.setMargin(new Insets(0,0,0,0));

            JPanel l_objPanel = new JPanel();
            m_Toolbar.add(l_objPanel);
            GridBagLayout l_objGBL = new GridBagLayout();
            l_objGBL.columnWidths = new int[] {iW};
            l_objGBL.rowHeights = new int[] {0, iW};
            l_objGBL.columnWeights = new double[]{0.0};
            l_objGBL.rowWeights = new double[]{1.0, 0.0, 0.0};
            l_objPanel.setLayout(l_objGBL);

            Component l_objVertGlue = Box.createVerticalGlue();
            AddButton(l_objPanel, l_objVertGlue, l_iRow++, 2);

            JToggleButton l_objTBtn = CreateChangePhaseActionsButton("Start", "Move to Next Phase");
            AddButton(l_objPanel, l_objTBtn, l_iRow++, 20);
            m_Startbutton = l_objTBtn;

            l_objBtn = CreateActionButton("", "Prep", "Prep Fire", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Prep");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "Opp", "Opportunity Fire", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Opp");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "To Hit", "Assemble/Disassemble SW", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("ToH");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "OBA", "Entrench", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("OBA");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "Smk", "OBA Actions", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Smk");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "Dig", "Smoke Actions", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Dig");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "Mop Up", "Mopping Up", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Mop");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "D/A", "Process Action", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("D/A");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "L WA", "Process Action", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("LWA");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "Action", "Process Action", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Action");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            l_objBtn = CreateActionButton("", "Action", "Process Action", new ActionListener() {public void actionPerformed(ActionEvent e) {ProcessAction("Action");}});
            AddButton(l_objPanel, l_objBtn, l_iRow++, 2);

            try {
                if (m_enToolbarPositionActions == ActionsToolBarPosition.TP_EAST)
                    SwingUtilities.getWindowAncestor(m_objASLMap.getLayeredPane()).add(m_Toolbar, BorderLayout.EAST);
                else if (m_enToolbarPositionActions == ActionsToolBarPosition.TP_WEST)
                    SwingUtilities.getWindowAncestor(m_objASLMap.getLayeredPane()).add(m_Toolbar, BorderLayout.WEST);
                } catch (Exception ex)
            {
            ex.printStackTrace();
            }
        }

        m_Toolbar.setVisible(m_bToolbarActive);

        m_objASLMap.getView().revalidate();
        m_Toolbar.revalidate();
    }

    private void ProcessAction(String actiontype) {

        if (actiontype=="Prep") {
            ScenarioC scen = ScenarioC.getInstance();
            scen.IFT.ProcessIFTCombat();
            //IFTMVCPattern Actiontest= new IFTMVCPattern();
        } else if (actiontype=="Opp"){
            OppFire markOppFire = new OppFire();
                markOppFire.markasOppFirer();
        } else if (actiontype=="Dis") {

        }

        NeedRepaint();
    }

    private void SetPhaseActions(String newphase) {
        String AddActionsarray[]= new String[2];

        if (newphase == "Prep") {
            String testarray[]={"Prep", "Opp", "To Hit", "OBA", "Smk", "Dig", "Mop Up", "D/A", "L WA"};
            AddActionsarray = testarray;
        } else if (newphase == "Move") {
            String testarray[]={"Smk", "AssM", "DT", "Dsh", "P DC", "FL", "D SW", "R SW"};
            AddActionsarray = testarray;
        } else if (newphase == "DefF") {
            String testarray[]={"DefF", "Final", "ToHit"};
            AddActionsarray = testarray;
        } else if (newphase == "AdvF") {
            String testarray[]={"AdvF", "Opp"};
            AddActionsarray = testarray;
        } else if (newphase == "Rout") {
            String testarray[]={"Rout", "Surr"};
            AddActionsarray = testarray;
        } else if (newphase == "Adv") {
            String testarray[]={"Swap", "CE/BU"};
            AddActionsarray = testarray;
        } else if (newphase == "CC") {
            String testarray[]={"Amb", "CC", "?"};
            AddActionsarray = testarray;
        } else if (newphase == "Rally") {
            String testarray[]={"S-R", "Rally", "Rec", "WA", "Dep", "Recom", "Repair"};
            AddActionsarray = testarray;
        } else {
            String testarray[]={"--", "--"};
            AddActionsarray = testarray;
        }

        for (int i=0; i <AddActionsarray.length; i++) {
            JPanel panelcomponent= (JPanel) m_Toolbar.getComponent(0);
            Component allcomponents[]= panelcomponent.getComponents();
            Component l_toolcomponent = allcomponents[2 + i];
            if (l_toolcomponent instanceof JButton){
                JButton l_toolbutton = (JButton) l_toolcomponent;
                l_toolbutton.setText(AddActionsarray[i]);
            } else {

            }
        }
        NeedRepaint();
    }


    private void AddButton(JPanel objPanel, Component objComp, int iRow, int iGap) {
        GridBagConstraints l_objGBL_Btn;

        l_objGBL_Btn = new GridBagConstraints();
        l_objGBL_Btn.fill = GridBagConstraints.BOTH;
        l_objGBL_Btn.insets = new Insets(0, 0, iGap, 0);
        l_objGBL_Btn.gridx = 0;
        l_objGBL_Btn.gridy = iRow;

        objPanel.add(objComp, l_objGBL_Btn);
    }

    private JToggleButton CreateChangePhaseActionsButton(String strCurrent, String strTooltip) {
        final JToggleButton l_btn = new JToggleButton(strCurrent);
        //final String currentphase = strCurrent;
        l_btn.setMargin(new Insets(0, 0, 0, 0));
        l_btn.setMaximumSize(new Dimension(32, 32));
        l_btn.setMinimumSize(new Dimension(10, 10));
        l_btn.setPreferredSize(new Dimension(32, 32));
        l_btn.setFocusable(false);
        l_btn.setRolloverEnabled(false);
        final ActionsToolbar passparent = this;
        ItemListener l_objIL = new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    if (l_btn.getText()=="Start") {
                        DoStartActions(l_btn);
                        String startgamename = "test";
                        StartbuttonCommand  startbtnc = new StartbuttonCommand(startgamename, passparent);
                        GameModule.getGameModule().sendAndLog(startbtnc);
                    } else if (l_btn.getText()=="Prep") {
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                        l_btn.setText("Move");
                    } else if (l_btn.getText()=="Move"){
                        l_btn.setText("DefF");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    } else if (l_btn.getText()=="DefF"){
                        l_btn.setText("AdvF");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    } else if (l_btn.getText()=="AdvF"){
                        l_btn.setText("Rout");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    } else if (l_btn.getText()=="Rout"){
                        l_btn.setText("Adv");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    } else if (l_btn.getText()=="Adv"){
                        l_btn.setText("CC");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    } else if (l_btn.getText()=="CC"){
                        l_btn.setText("Rally");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    } else if (l_btn.getText()=="Rally"){
                        l_btn.setText("Prep");
                        ScenarioC scen = ScenarioC.getInstance();
                        scen.PhaseChangeNext();
                    }
                    SetPhaseActions(l_btn.getText());
                    l_btn.setSelected(false);
                    //NeedRepaint();
                }
                else
                {
                    //m_objDRQH.setKeepAlive(false);
                }
            }
        };

        l_btn.addItemListener(l_objIL);
        AddHotKeyToTooltip(l_btn, null, strTooltip);
        l_btn.setFocusable(false);

        return l_btn;
    }

    private JButton CreateActionButton(String strImage, String strCaption, String strTooltip, ActionListener objList) {
        JButton l_btn = new JButton(strCaption);

        l_btn.setMargin(new Insets(0, 0, 0, 0));
        l_btn.setMaximumSize(new Dimension(32, 32));
        l_btn.setMinimumSize(new Dimension(10, 10));
        l_btn.setPreferredSize(new Dimension(32, 32));
        l_btn.setFocusable(false);
        l_btn.setRolloverEnabled(false);

        try {
            if (!strImage.isEmpty())
                l_btn.setIcon(new ImageIcon(Op.load(strImage).getImage(null)));
        } catch (Exception ex) {
        }

        l_btn.addActionListener(objList);
        AddHotKeyToTooltip(l_btn, null, strTooltip);
        l_btn.setFocusable(false);

        return l_btn;
    }

    private void AddHotKeyToTooltip(JComponent objButton, KeyStroke keyStroke, String strTooltipText) {
        if (keyStroke != null)
            objButton.setToolTipText(strTooltipText + " [" + HotKeyConfigurer.getString(keyStroke) + "]");
        else
            objButton.setToolTipText(strTooltipText);
    }

    public void NeedRepaint()
        {
        if (m_objASLMap != null)
        m_objASLMap.repaint();
    }
    /*public void setPhaseatStartofPlay(String currentphase) {
        for (Component findbutton: m_Toolbar.getComponents()) {
            if (findbutton instanceof JButton) {
                JButton newbutton = (JButton) findbutton;
                if (newbutton.getText() == "Next") {
                    newbutton.setText(currentphase);
                }
            }
        }
    }*/
    private String PhasetoString(Constantvalues.Phase currentphase) {
        switch (currentphase) {
            case PrepFire:
                return "Prep";
            case Movement:
                return "Move";
            case DefensiveFire:
                return "DefF";
            case AdvancingFire:
                return "AdvF";
            case Rout:
                return "Rout";
            case Advance:
                return "Adv";
            case CloseCombat:
                return "CC";
            case Rally:
                return "Rally";
            default:
                return "";
        }
    }

    private void DoStartActions(JToggleButton l_btn){
        ScenarioC scen = ScenarioC.getInstance();
        Constantvalues.Phase currentphase = scen.getPhase();
        String currentphasename = PhasetoString(currentphase);
        getStartbutton().setText(currentphasename);
        //scen.PhaseChangeNext();
    }

    public Command decode (String command){
        SequenceEncoder.Decoder sdcr = null;
        if (command.startsWith("StartbuttonC")){
            sdcr = new SequenceEncoder.Decoder(command, '\t');
            sdcr.nextToken();  // passover first token which is identifier string (ie "UPDATE_BASE_UNIT:")
            String opengamename = sdcr.nextToken();
            return new StartbuttonCommand(opengamename, this);
        } else  {
            return null;
        }

    }

    public String encode (Command c){
        if (c instanceof StartbuttonCommand) {
            StartbuttonCommand sc = (StartbuttonCommand) c;
            SequenceEncoder scencoder = new SequenceEncoder(sc.passOpengame, '\t');
            return "StartbuttonC" + "\t" + scencoder.getValue();
        } else {
            return null;
        }
    }

    private JToggleButton getStartbutton(){
        return m_Startbutton;
    }


    // this command used by ItemListener in this.CreateChangePhaseActionsButton to set fullrules toolbar on remote computers
    public class StartbuttonCommand extends Command {
        protected String passOpengame;
        protected ActionsToolbar atb;
        protected JToggleButton l_btn;

        public StartbuttonCommand(String opengame, ActionsToolbar passatb) {
            this.passOpengame = opengame;
            this.atb = passatb;
            this.l_btn = passatb.getStartbutton();
        }

        protected void executeCommand() {

            // can this all be replaced by l_btn.setSelected(true); ?
            if (l_btn.getText()=="Start") {
                DoStartActions(l_btn);
            } else if (l_btn.getText()=="Prep") {
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
                l_btn.setText("Move");
            } else if (l_btn.getText()=="Move"){
                l_btn.setText("DefF");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            } else if (l_btn.getText()=="DefF"){
                l_btn.setText("AdvF");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            } else if (l_btn.getText()=="AdvF"){
                l_btn.setText("Rout");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            } else if (l_btn.getText()=="Rout"){
                l_btn.setText("Adv");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            } else if (l_btn.getText()=="Adv"){
                l_btn.setText("CC");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            } else if (l_btn.getText()=="CC"){
                l_btn.setText("Rally");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            } else if (l_btn.getText()=="Rally"){
                l_btn.setText("Prep");
                ScenarioC scen = ScenarioC.getInstance();
                scen.PhaseChangeNext();
            }
            SetPhaseActions(l_btn.getText());
            l_btn.setSelected(false);
        }

        protected Command myUndoCommand() {
            return null;
        }
    }
}
