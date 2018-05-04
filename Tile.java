
/**
 * Tile - This class contains the walls, floor, doors and spawn point of the dungeon as individual tiles, which the
 * board class will be able to use with the map file to create the dungeon.
 * 
 * @author (Lukas Skardinskas)
 * @version (Nov 24, 2015)
 */

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Tile
{
    // instance fields
    private int xLoc, yLoc, size;
    protected char tileType;

    // See for symbols that represent wall, floor, door and spawn.
    protected final char WALL = '#';
    protected final char FLOOR = '*';
    protected final char DOOR = '+';
    protected final char SPAWN = '0';
    protected final char M1 = '1';
    protected final char M2 = '2';
    protected final char M3 = '3';
    private boolean occupied;

    private final int SIZE = 50; // pxls 
    protected BufferedImage pic;

    // constructor
    public Tile(char type)
    {
        xLoc = 0;
        yLoc = 0;
        tileType = type;
        occupied = false;
        
        try{
            if(tileType == DOOR)
                pic = ImageIO.read(new File("door.png"));
            else if(tileType == WALL)
                pic = ImageIO.read(new File("wall.png"));
            else if(tileType == SPAWN)
                pic = ImageIO.read(new File("spawn.png"));
            else if(tileType == FLOOR)
                pic = ImageIO.read(new File("floor.png"));
            else if(tileType == M1)
                pic = ImageIO.read(new File("bat1.png"));
            else if(tileType == M2)
                pic = ImageIO.read(new File("bat2.png"));
            else if(tileType == M3)
                pic = ImageIO.read(new File("bat3.png"));
        }catch(IOException e){
            System.err.println("Image for Tile not Found...");
        }
    }
    
    // Return occupied
    public boolean isOccupied()
    {
        return occupied;
    }
    
    // Set the tile to be occupied
    public void occupied()
    {
        occupied = true;
    }
    
    // Set the tile to be unoccupied
    public void unoccupied()
    {
        occupied = false;
    }
    
    // Opens the door
    public void openDoor()
    {
        try
        {
            pic = ImageIO.read(new File("floor.png"));
        }
        catch(Exception e)
        {
            System.err.println("Image for Tile not Found...");
        }
        
        tileType = FLOOR;
    }
    
    // Sets x and y
    public void setXY(int x, int y)
    {
        xLoc = x;
        yLoc = y;
    }

    // Getters
    public int getX()
    {
        return xLoc;
    }

    public int getY()
    {
        return yLoc;
    }

    // Get the type
    public int getType()
    {
        return tileType;
    }

    // drawing itself
    public void draw(Graphics g)
    {      
        // drawing the image of the tile
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pic,xLoc,yLoc, null);
    }

    public String toString()
    {
        return "" + tileType;
    }
}
