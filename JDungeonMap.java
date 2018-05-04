
/**
 * JDungeonMap - a 2D map of a dungeon 
 * 
 * @author (??????) 
 * @version (??????)
 */

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class JDungeonMap extends JPanel
{

    // instance fields 
    public static final int PANEL_WIDTH = 1000;
    public static final int MAP_WIDTH = 800;
    public static final int MAP_HEIGHT = 600;
    public static final int SQUARE = 50;
    private final int MAX_PLAYER = 4;
    public int spawnX;
    public int spawnY;
    public ArrayList<Tile> map;
    private Characters[] players = null;
    public int numMonster = 0;
    private int playerNumber;
    public String mapName = "map.txt";
    private CharPanel charPanel;
    //to store player's gold
    private int[] playerGold;
    private boolean newMap;
    private String[] playerName = {"Fighter", "Wizard", "Rogue", "Cleric"};
    
    // constructor 
    public JDungeonMap(CharPanel cp)
    {
        setPreferredSize(new Dimension(MAP_WIDTH,MAP_HEIGHT));
        
        charPanel = cp;
        playerGold = new int[4];
        newGame();
        
        getMap();
        addKeyListener(new Listener());
        setFocusable(true);
        //playerNumber = 0;
    }

    // Get map
    public void getMap()
    {
        MapReader mr = new MapReader();
        mr.setFileName(mapName);
        map = mr.getTiles();
        int index = 0;
        numMonster = mr.monster;

        // Sets the coordinates
        for(int y = 0; y < MAP_HEIGHT; y += SQUARE)
        {
            for(int x = 0; x < MAP_WIDTH; x += SQUARE)
            {
                map.get(index).setXY(x,y);
                if(map.get(index).getType() == '0')
                {
                    spawnX = map.get(index).getX();
                    spawnY = map.get(index).getY();
                }
                index++;
            }
        }
        
        players = new Characters[MAX_PLAYER];
        //do not change the sequence 
        players[0] = new Characters(this,spawnX,spawnY, "Fighter",0);
        players[1] = new Characters(this,spawnX,spawnY, "Wizard",1);
        players[2] = new Characters(this,spawnX,spawnY, "Rogue",2);
        players[3] = new Characters(this,spawnX,spawnY, "Cleric",3);
        
        newMap = true;
        repaint();
        //charPanel.newGame();
    }
    
    //acui:
    //return the array of the player's gold amount
    public int[] getPlayerGold()
    {
        return playerGold;
    }
    
    //acui:
    //update the playGold so that the gold amount of each player earned
    //will be kept when a new map is loaded.
    public void saveGold()
    {
        for(int i=0; i<4; i++)
        {
            playerGold[i] = players[i].getGold();
        }
    }
    
    //acui:
    //clean up when the game is started or if user choose to play a new game
    public void newGame()
    {
        for(int i=0; i<4; i++)
        {
            playerGold[i] = 0;
        }
        playerNumber = 0;
        charPanel.newGame();
    }

    // drawing the map piece by piece
    public void paintComponent(Graphics g)
    {
        paintMap(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < MAX_PLAYER; i++)
        {
            players[i].draw(g2d);
        }
    }

    private void paintMap(Graphics g)
    {
        for(int i = 0; i < map.size(); i++)
            map.get(i).draw(g);
    }
    
    public void setNextPlayer()
    {
        System.out.println(playerName[playerNumber] +" turn ended.");
        
        playerNumber++;
        if(playerNumber > 3)
        {
            playerNumber = 0;
        }
        System.out.println();
        System.out.println();
        System.out.println("~~~ " +playerName[playerNumber] +
                           " turn begins ~~~");
    }
    
    // Controls the turn
    public void turnControl()
    {
        if(players[playerNumber].getMoves() == 5)
        {
            players[playerNumber].setMoves(0);
            System.out.println(playerName[playerNumber] +" turn ended.");
            playerNumber++;
            if(playerNumber > 3)
            {
                playerNumber = 0;
            }
            
            System.out.println();
            System.out.println();
            System.out.println("~~~ " +playerName[playerNumber] +
                               " turn begins ~~~");
        }
    }
    
    //acui: 
    //end turn of current player, move to next player
    //user may choose the skip or stop his turn from the "End Turn"
    //button on the UI
    public void endTurn()
    {
        players[playerNumber].setMoves(0);
        System.out.println(playerName[playerNumber] +" turn ended.");
        playerNumber++;
        if(playerNumber > 3)
        {
            playerNumber = 0;
        }
        charPanel.update(players[playerNumber], playerNumber);
        System.out.println();
        System.out.println();
        System.out.println("~~~ " +playerName[playerNumber] +
                           " turn begins ~~~");
    }

    // Check for wins
    public void checkWins()
    {
        for(int i = 0; i < players.length; i++)
        {
            if(players[i].getType().equals("Wizard"))
            {
                if(players[i].getGold() >= 10000)
                {
                    System.out.println(players[i].getType() + " wins the game!");
                    newGame();
                    mapName = "map.txt";
                    getMap();
                    break;
                }
            }
            else
            {
                if(players[i].getGold() >= 5000)
                {
                    System.out.println(players[i].getType() + " wins the game!");
                    newGame();
                    mapName = "map.txt";
                    getMap();
                    break;
                }
            }
        }
    }

    // Check for position
    public void checkPosition()
    {
        for(int i = 0; i < map.size(); i++)
        {
            for(int j = 0; j < players.length; j++)
            {
                if(players[j].getX() == map.get(i).getX() && players[j].getY() == map.get(i).getY())
                {
                    map.get(i).occupied();
                    break;
                }
                else
                {
                    map.get(i).unoccupied();
                }
            }
        }
    }

    // The keylistener class
    private class Listener implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();

            newMap = false;
            //System.out.println("Key pressed");
            if(key == KeyEvent.VK_W)
            {
                players[playerNumber].moveCharacter(Characters.Directions.NORTH);
            }
            else if(key == KeyEvent.VK_S)
            {
                players[playerNumber].moveCharacter(Characters.Directions.SOUTH);
            }
            else if(key == KeyEvent.VK_A)
            {
                players[playerNumber].moveCharacter(Characters.Directions.WEST);
            }
            else if(key == KeyEvent.VK_D)
            {
                players[playerNumber].moveCharacter(Characters.Directions.EAST);
            }
            
            charPanel.update(players[playerNumber], playerNumber);
            turnControl();
            charPanel.update(players[playerNumber], playerNumber);
            checkWins();
            checkPosition();
            
            repaint();
        }

        public void keyReleased(KeyEvent e)
        {}

        public void keyTyped(KeyEvent e)
        {}
    }
}
