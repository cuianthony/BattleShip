import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.File;
import javax.imageio.ImageIO;
/**
 * The Monster class.
 * 
 * @author (Michael Fan) 
 * @version (Nov 24, 2015)
 */
public class Monster extends Tile
{
    // Instance field 
    private final int SIX_SIDED = 6;
    private int x,y;
    private static Random ran = new Random();
    private Treasure treasure = null;
    private String name;
    private int level;
    private int rogue, cleric, fighter, wizard;

    // Constructor
    public Monster(char type)
    {
        super(type);
        
        //Set the attributes depending the level of the monster
        if(tileType == M1)
        {
            levelOneMonster();
        }
        else if(tileType == M2)
        {
            levelTwoMonster();
        }
        if(tileType == M3)
        {
            levelThreeMonster();
        }
    }

    // Method

    // Set the attributes depending the level of the monster
    public void levelOneMonster()
    {
        name = "Level 1 Bat";
        level = 1;
        rogue = 3;
        cleric = 1;
        fighter = 5;
        wizard = 1;
        treasure = new NormalGold();
    }

    public void levelTwoMonster()
    {
        name = "Level 2 Bat";
        level = 2;
        rogue = 5;
        cleric = 3;
        fighter = 7;
        wizard = 3;

        // Randomly assign a treasure
        int chance1 = ran.nextInt(5);
        int chance2 = ran.nextInt(5);
        int chance3 = ran.nextInt(5);
        int chance4 = ran.nextInt(5);

        int chance = chance1 + chance2 + chance3 + chance4;//makes it harder to give out special treasure
        
        if(chance == 9)
        {
            treasure = new MagicSword();
        }
        else
        {
            treasure = new NormalGold();
        }
    }

    public void levelThreeMonster()
    {
        name = "Level 3 Bat";
        level = 3;
        rogue = 7;
        cleric = 5;
        fighter = 9;
        wizard = 5;

        // Randomly assign a treasure
        int chance1 = ran.nextInt(5);
        int chance2 = ran.nextInt(5);
        int chance3 = ran.nextInt(5);
        int chance4 = ran.nextInt(5);

        int chance = chance1 + chance2 + chance3 + chance4;//makes it harder to give out special treasure

        if(chance == 7)
        {
            treasure = new MagicSword();
        }
        else if(chance == 9)
        {
            treasure = new CrystalBall();
        }
        else
        {
            treasure = new NormalGold();
        }
    }

    // Attack the player
    public int getAttack()
    {
        int damage = 0;

        for(int i = 0; i < 2; i++)
        {
            damage += ran.nextInt(SIX_SIDED) + 1;
        }

        return damage;
    }

    // Drops a treasure
    public Treasure getTreasure()
    {
        return treasure;
    }

    // Getters 
    public int getLevel()
    {
        return level;
    }

    public int getRogue()
    {
        return rogue;
    }

    public int getCleric()
    {
        return cleric;
    }

    public int getFighter()
    {
        return fighter;
    }

    public int getWizard()
    {
        return wizard;
    }

    // Dies. Set the monster tile becomes a floor.
    public void die()
    {
        try
        {
            pic = ImageIO.read(new File("floor.png"));
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        tileType = FLOOR;
    }

    // Display the attributes of the monster
    public String toString()
    {
        return "Encountered a monster!" + "\n"+"Name: "+name +"\n"+"\n" + "Battle Requirements:"+
                "\n"+"Rogue: "+rogue + "\n"+"Cleric: "+cleric + "\n"+"Fighter: "+fighter + "\n" +
                "Wizard: "+wizard;
    }
}
