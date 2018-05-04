
/**
 *Treasure Class for the Normal gold. Overrides all abstract methods from the parent class
 * which is the Treasure.
 * 
 * @author (Matekoa) 
 * @version (22 Nov 2015)
 */
import java.util.Random;

public class NormalGold extends Treasure
{
    private int RewardPicker;
    private static Random gen = new Random();

    public NormalGold(){
        name = "Gold";
        RewardPicker = gen.nextInt(5);
        if(RewardPicker == 0)
            reward = 100;
        if(RewardPicker == 1)
            reward = 250;
        if(RewardPicker == 2)
            reward = 500;
        if(RewardPicker == 3)
            reward = 750;
        if(RewardPicker == 4)
            reward = 1000;  
    }

}
