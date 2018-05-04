import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/**
 * TO MOVE CHARACTER:
 * 
 * character . moveCharacter( Character.Directions.NORTH );
 * character . moveCharacter( Character.Directions.EAST );
 * character . moveCharacter( Character.Directions.SOUTH );
 * character . moveCharacter( Character.Directions.WEST );
 * 
 * IT WORKS DON'T JUDGE
 * 
 * @author (Keith Allatt)
 */
public class Characters {
    private int x, y, size;
    public JDungeonMap map;
    private String characterType;
    private static Random ran = new Random();
    private ArrayList<Treasure> treasures;
    private int gold;
    private int moves;
    private int playerId;

    public Characters(JDungeonMap map, int x, int y, String characterType, int playerId) 
    {
        this.map = map;
        this.x = x;
        this.y = y;
        size = 50;
        treasures = new ArrayList<Treasure>();
        //gold = 0;
        moves = 0;
        this.characterType = characterType;
        //acui to fix a bug
        this.playerId = playerId;    
    }

    // Get x
    public int getX()
    {
        return x;
    }

    // Get y
    public int getY()
    {
        return y;
    }

    // Get the number of moves
    public int getMoves()
    {
        return moves;
    }

    // Reset moves
    public void setMoves(int moves)
    {
        this.moves = moves;
    }

    // Drop a treasure
    public void dropOne()
    {
        if(treasures.size() > 0)
        {
            int index = ran.nextInt(treasures.size());
            System.out.println("You have dropped: " + treasures.get(index));
            treasures.remove(index);
        }
    }

    // Drop half of the treasures
    public void dropHalf()
    {
        if(treasures.size() > 1)
        {
            int num = treasures.size() / 2;
            for(int i = 0; i < num; i++)
            {
                dropOne();
            }
        }
        else if(treasures.size() > 0)
        {
            dropOne();
        }
    }

    // Drop all of the treasures
    public void dropAll()
    {
        if(treasures.size() > 0)
        {
            for(int i = 0; i < treasures.size(); i++)
            {
                dropOne();
            }
        }
    }

    // Gain a treasure
    public void gainTreasure(Treasure t)
    {
        treasures.add(t);
    }

    // Sets x and y
    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Print the the treasures
    public void printTreasure()
    {
        //System.out.println(characterType);
        if(treasures.size() > 0)
        {
            int temp = 0;
            int total = 0;
            for(Treasure t: treasures)
            {
                //System.out.println(t);
                temp += t.getWorth();
            }
            //gold = temp;
            total = map.getPlayerGold()[playerId] + temp;
            System.out.println("Total gold: " + total);
        }
        else
        {
            System.out.println("You have nothing");
        }
    }
    
    // Attack the monster. Rolls a 2d6
    public int getAttack()
    {
        int damage = -1;
        int d1, d2;
        
        d1 = ran.nextInt(6);
        d2 = ran.nextInt(6);
        damage = d1 + d2 +2;
        
        DicePanel.animateDiceRoll(d1, d2);

        return damage;
    }
    /* acui: use the above method to animate dice roll
    public int getAttack()
    {
        int damage = -1;
        for(int i = 0; i < 2; i++)
        {
            damage += ran.nextInt(6) + 1;
        }
        return damage;
    }*/

    // Return the type of the character
    public String getType()
    {
        return characterType;
    }

    /**
     * Draws character at specific location at specific size
     * 
     */
    public void draw(Graphics2D g) { 
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(characterType+".png"));
        } catch (IOException e) {}

        g.drawImage(img,x,y,null);
    }

    /**
     * Methods to move character left right up and down
     */
    public void moveCharacter(Directions d) {
        int xC = (int)d.getDirection().getX();
        int yC = (int)d.getDirection().getY();

        ArrayList<Tile> tiles = map.map;

        for (Tile t : tiles) 
        {
            if(t.getX() == (x+xC) && t.getY() == (y+yC))
            {
                if(t.getType() == '#')
                {
                    return;
                }
                else if(t.isOccupied()) // Player-player Collision 
                {
                    if(moves == 4)
                    {
                        return;
                    }
                }
                else if(t.getType() == '0') // Once the players move out of the spawn they can never go back
                {
                    return;
                }
                else if(t.getType() == '+') // Open doors
                {
                    int number = ran.nextInt(6) + 1; // Rolls 1d6 to open door
                    if(characterType.equals("thief"))
                    {
                        if(number >= 2)
                        {
                            t.openDoor();
                        }
                        else
                        {
                            System.out.println("You failed to open the door. Turn ends.");
                            moves = 5; // Ends the turn if fails to open a door
                            return;
                        }
                    }
                    else if(characterType.equals("cleric"))
                    {
                        if(number >= 3)
                        {
                            t.openDoor();
                        }
                        else
                        {
                            System.out.println("You failed to open the door. Turn ends.");
                            moves = 5; // Ends the turn if fails to open a door
                            return;
                        }
                    }
                    else
                    {
                        if(number >= 4)
                        {
                            t.openDoor();
                        }
                        else
                        {
                            System.out.println("You failed to open the door. Turn ends.");
                            moves = 5; // Ends the turn if fails to open a door
                            return;
                        }
                    }
                }
                else if(t.getType() == '1' || t.getType() == '2' || t.getType() == '3') // Fighting monster
                {
                    // Only fight if the monster is not dead
                    Monster m = (Monster) t;
                    System.out.println(m);
                    System.out.println();
                    Battle.fight(m,this);
                    return;
                }
            }
        }

        x += xC;
        y += yC;
        moves++;
    }

    // Get the index of the tile
    public int getIndex(int x, int y)
    {
        int index = -1;
        for(int i = 0; i < map.map.size(); i++)
        {
            if(map.map.get(i).getX() == x && map.map.get(i).getY() == y)
            {
                index = i;
                break;
            }
        }
        return index;
    }

    // Get the amount of gold
    public int getGold()
    {
        int temp = 0;
        int total = 0;
        for(Treasure t: treasures)
        {
            //System.out.println(t);
            temp += t.getWorth();
        }
        total = map.getPlayerGold()[playerId] + temp;
        return total;
    }

    /**
     * Represents the four cardinal point direction things
     */
    static enum Directions {
        NORTH(0,-50),

        EAST(50,0),

        SOUTH(0,50),

        WEST(-50,0);

        int xC, yC;

        Directions(int xC,int yC) {
            this.xC = xC;
            this.yC = yC;
        }

        public Point getDirection() {
            return new Point(xC,yC);
        }
    }
}