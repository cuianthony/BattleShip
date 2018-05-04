
/**
 * The super class for all Treasure types
 * 
 * @author (Matekoa) 
 * @version (22 Nov 2015)
 */
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Treasure
{
    //instance variables
    protected int reward;
    protected String name;
    
    public Treasure(){
    }
    
    public int getWorth()
    {
        return reward;
    }
    //for testing
    public String toString()
    {
        return name;
    }
}
