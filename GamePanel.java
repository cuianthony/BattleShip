/******************************************************************
 * Description:
 * 
 * The GamePanel class extends JPanel. It partitions the UI into
 * three areas. The main area is the Dungeon map, the bottom area displays
 * player info, and the right area shows the dice roll animation, an action
 * log displaying game history, and two control buttons.
 * 
 * 
 * @author (Anthony Cui & Matekoa) 
 * @version (v1.0)
 * @version (Dec 13, 2015)
 ******************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel
{
    //instance fields
    private JPanel dungeonPanel, controlPanel, leftPanel;
    private LogPanel logPanel;
    private DicePanel dicePanel;
    private CharPanel charPanel;
    
    //define constants
    private final int GAME_WIDTH = 1100;
    private final int GAME_HEIGHT = 700;
    
    private final int DP_WIDTH = 800;
    private final int DP_HEIGHT = 600;
    
    private final int CP_WIDTH = 300;
    private final int CP_HEIGHT = 700;
    
    private final int IP_WIDTH = 1100;
    private final int IP_HEIGHT = 200;
    
    /******************************************************************
     * Description:
     * The GamePanel constructor.
     * 
     * Limitations: none
     *****************************************************************/
    public GamePanel()
    {
        setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        //Set up dungeon panel
        dungeonPanel = new JPanel();
        charPanel = new CharPanel();
        JDungeonMap map = new JDungeonMap(charPanel);
        dungeonPanel.add(map);
        dungeonPanel.setPreferredSize(new Dimension(DP_WIDTH,DP_HEIGHT));
        
        //Control Panel UI
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(CP_WIDTH,CP_HEIGHT));
        
        dicePanel = new DicePanel();
        logPanel = new LogPanel(map);
        controlPanel.add(Box.createRigidArea(new Dimension(0,50)));
        controlPanel.add(dicePanel);
        controlPanel.add(Box.createRigidArea(new Dimension(0,25)));
        controlPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        controlPanel.add(Box.createRigidArea(new Dimension(0,25)));
        controlPanel.add(logPanel);
        controlPanel.add(Box.createRigidArea(new Dimension(0,50)));
        
        //Dungeon map and player's info
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(dungeonPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        leftPanel.add(charPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        
        //pack things together
        add(Box.createRigidArea(new Dimension(25,0)));
        add(leftPanel);
        add(Box.createRigidArea(new Dimension(25,0)));
        add(new JSeparator(SwingConstants.VERTICAL));
        add(Box.createRigidArea(new Dimension(25,0)));
        add(controlPanel);
        add(Box.createRigidArea(new Dimension(25,0)));
    }
}
