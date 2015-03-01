/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VASL.counters;

import VASSAL.build.module.documentation.HelpFile;
import VASSAL.command.ChangeTracker;
import VASSAL.command.Command;
import VASSAL.counters.Decorator;
import VASSAL.counters.EditablePiece;
import VASSAL.counters.GamePiece;
import VASSAL.counters.KeyCommand;
import VASSAL.counters.PieceEditor;
import VASSAL.counters.Properties;
import VASSAL.counters.SimplePieceEditor;
import VASSAL.tools.SequenceEncoder;
import VASSAL.tools.imageop.ImageOp;
import VASSAL.tools.imageop.Op;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Federico
        <VASSAL.build.module.PrototypeDefinition name="Unit58v">+/null/report;71\,130;Prep Fire created in $location$ *;;;	report;74\,130;First Fire created in $location$ *;;;\	placemark;Prep Fire;71,130;VASSAL.build.module.PieceWindow:VASL Counters\/VASSAL.build.widget.TabWidget\/VASSAL.build.widget.TabWidget:Unit\/VASSAL.build.widget.PanelWidget:Basic\/VASSAL.build.widget.PieceSlot:Prep Fire;null;0;0;false;;Prep Fire;;0;false\\	placemark;First Fire;74,130;VASSAL.build.module.PieceWindow:VASL Counters\/VASSAL.build.widget.TabWidget\/VASSAL.build.widget.TabWidget:Unit\/VASSAL.build.widget.PanelWidget:Basic\/VASSAL.build.widget.PieceSlot:First Fire;null;0;0;false;;First Fire;;0;false\\\	hide;72,130;HIP;;player:;0.30000001192092896\\\\	submenu;Move;Moved,Motion,dir1,dir2,dir3,dir4,dir5,dir6\\\\\	translate;dir6;103,130;-56;32;true;0;0;0;0;\\\\\\	translate;dir5;97,130;-56;-32;true;0;0;0;0;\\\\\\\	translate;dir4;98,130;0;-64;true;0;0;0;0;\\\\\\\\	translate;dir3;99,130;56;-32;true;0;0;0;0;\\\\\\\\\	translate;dir2;105,130;56;32;true;0;0;0;0;\\\\\\\\\\	translate;dir1;104,130;0;64;true;0;0;0;0;\\\\\\\\\\\	moved;moved58\\\\\\\\\\\\	report;70\,130,90\,130,88\,130;$location$: $newPieceName$ *;;;\\\\\\\\\\\\\	piece;;;;/-1	-1\	\\	\\\	null\\\\	\\\\\	\\\\\\	\\\\\\\	\\\\\\\\	\\\\\\\\\	\\\\\\\\\\	\\\\\\\\\\\	false\\\\\\\\\\\\	-1\\\\\\\\\\\\\	null;258;0;</VASSAL.build.module.PrototypeDefinition>
 */
public class MarkAmmo extends Decorator implements EditablePiece {

  public static final String ID = "MarkAmmo;";
  
  // HEAmmo, APAmmo, APCRAmmo, APDSAmmo, HEATAmmo, SMOKEAmmo, WPAMMO, CANNISTERAmmo    
  
  private static final int iNumAmmo = 8;
  //private boolean bAvailAmmo[] = new boolean[] {true, true, true, true, true, true, true, true};
  private boolean bAvailAmmo[] = new boolean[] {false, false, false, false, false, false, false, false};
  private boolean bShowAmmo[] = new boolean[] {false, false, false, false, false, false, false, false};
  private static final KeyStroke strokes[] = new KeyStroke[] 
                                                    {
                                                        KeyStroke.getKeyStroke('1', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('2', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('3', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('4', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('5', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('6', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('7', java.awt.event.InputEvent.CTRL_MASK),
                                                        KeyStroke.getKeyStroke('8', java.awt.event.InputEvent.CTRL_MASK)
                                                    };

    private static final String images[] = new String[] {"ammo/HE.png", "ammo/AP.png", "ammo/A.png", "ammo/D.png", "ammo/H.png", "ammo/S.png", "ammo/WP.png", "ammo/C.png"};
    private static final String names[] = new String[] {"HE", "AP", "A", "D", "H", "S", "WP", "C"};
    private static final String commandnames[] = new String[] {"HE Ammo", "AP Ammo", "APCR Ammo", "APDS Ammo", "HEAT Ammo", "SMOKE Ammo", "WP Ammo", "CANNISTER Ammo"};

    public MarkAmmo()
    {
        this(ID + "HE,AP,A,D,H,S,WP,C", null);
    }
    
    public MarkAmmo(String type, GamePiece p) 
    {
        mySetType(type);
        setInner(p);
    }
    
    @Override
    public void mySetState(String newState) 
    {
        for (int i=0; i < iNumAmmo; i++)
        {
            if (i < newState.length())
                bShowAmmo[i] = (newState.charAt(i) == 'Y') && bAvailAmmo[i];
            else
                bShowAmmo[i] = false;
        }
    }

    @Override
    public String myGetState() 
    {
        StringBuilder strBuild = new StringBuilder();
        
        for (int i=0; i < iNumAmmo; i++)
            strBuild.append(bShowAmmo[i] && bAvailAmmo[i] ? "Y" : "N");
        
        return strBuild.toString();
    }

    @Override
    protected KeyCommand[] myGetKeyCommands() 
    {
        int iCount = 0;
        
        for (int i=0; i < iNumAmmo; i++)
           if (bAvailAmmo[i])
               iCount++;
        
        if (iCount == 0)
            return new KeyCommand[0];
        else
        {
            KeyCommand commands[] = new KeyCommand[iCount];
            
            iCount = 0;
            
            for (int i=0; i < iNumAmmo; i++)
            {
                if (bAvailAmmo[i])
                {
                    commands[iCount] = new KeyCommand(commandnames[i], strokes[i], Decorator.getOutermost(this));
                    iCount++;            
                }
            }
            
            return commands;
        }        
    }
        
    @Override
    public Command myKeyEvent(KeyStroke stroke) 
    {
        for (int i=0; i < iNumAmmo; i++)
        {
            if (bAvailAmmo[i])
            {
                if (stroke.equals(strokes[i])) 
                {
                    ChangeTracker c = new ChangeTracker(this);
                    bShowAmmo[i] = !bShowAmmo[i];
                    return c.getChangeCommand();
                }
            }
        }
        
        return null;
    }

    public void draw(Graphics g, int x, int y, Component obs, double zoom) 
    {
        int iNum = 0;
        piece.draw(g, x, y, obs, zoom);

        boolean concealed = Boolean.TRUE.equals(getProperty(Properties.OBSCURED_TO_ME));
        
        if (!concealed)
        {
            for (int i=0; i < iNumAmmo; i++)
            {
                if (bShowAmmo[i]) 
                {
                    Rectangle r = piece.getShape().getBounds();

                    try 
                    {
                        ImageOp im = Op.load(images[i]);

                        if (zoom != 1.0) 
                          im = Op.scale(im,zoom);

                        if (iNum < 5)
                            g.drawImage(im.getImage(null),
                                        x + (int) (zoom * (r.x - 15)),
                                        y + (int) (zoom * (2 + r.y + iNum * 11)),obs);
                        else
                            g.drawImage(im.getImage(null),
                                        x + (int) (zoom * (r.x + 2 + 15 * (iNum - 5))),
                                        y + (int) (zoom * (r.y + r.height)),obs);
                    }
                    catch (Exception ex) 
                    {
                      ex.printStackTrace();
                    }

                    iNum++;
                }
            }
        }
    }

    public Rectangle boundingBox() {
        boolean concealed = Boolean.TRUE.equals(getProperty(Properties.OBSCURED_TO_ME));
        Rectangle r = piece.boundingBox();
        
        if (concealed)
            return r;
        
        int iCount = 0;
        
        for (int i=0; i < iNumAmmo; i++)
            if (bShowAmmo[i]) 
                iCount++;
        
        if (iCount == 0)
            return r;
                    
        if (iCount <= 5)
        {
            Rectangle r2 = piece.getShape().getBounds();

            Rectangle rLeft = new Rectangle
                (r2.x - 15, r2.y + 2,
                 15, iCount * 11);

            return r.union(rLeft);
        }
        else
        {
            Rectangle r2 = piece.getShape().getBounds();

            Rectangle rLeft = new Rectangle
                (r2.x - 15, r2.y + 2,
                 15, 5 * 11);

            Rectangle rBottom = new Rectangle
                (r2.x + 2, r2.y + r2.height,
                 (iCount - 5) * 15, 11);

            return r.union(rLeft).union(rBottom);
        }
    }

    public Shape getShape() {
        return piece.getShape();
    }

    public String getName() {
        return piece.getName();
    }

    public String getDescription() {
        return "Ammo";
    }

    @Override
    public String myGetType() 
    {
        StringBuilder strBuild = new StringBuilder();
        
        for (int i=0; i < iNumAmmo; i++)
            strBuild.append(bAvailAmmo[i] ? (strBuild.length() != 0 ? "," : "") + names[i] : "");
        
        return ID + strBuild.toString();
    }
    
    public void mySetType(String type) 
    {
        for (int i=0; i < iNumAmmo; i++)
        {
            bAvailAmmo[i] = false;
            bShowAmmo[i] = false; 
        }
        
        SequenceEncoder.Decoder st = new SequenceEncoder.Decoder(type, ';');
        st.nextToken();
        
        String strType = st.nextToken();
        
        if (strType != null) 
        {
            strType = strType.trim();
            
            if (!strType.isEmpty())
            {
                SequenceEncoder.Decoder st2 = new SequenceEncoder.Decoder(strType, ',');
                
                while (st2.hasMoreTokens())
                {
                    String strAmmo = st2.nextToken();
                    
                    if (strAmmo == null)
                        break;
                    
                    strAmmo = strAmmo.trim();
                    
                    if (strAmmo.isEmpty())
                        break;
                    
                    for (int i=0; i < iNumAmmo; i++)
                    {
                        if (names[i].equals(strAmmo))
                        {
                            bAvailAmmo[i] = true;
                            bShowAmmo[i] = true; 
                            break;
                        }
                    }                       
                }
            }
        }
    }

    public HelpFile getHelpFile() {
        return null;
    }
    
  public PieceEditor getEditor() {
    return new SimplePieceEditor(this);
  }
}
