
/**
 * Special Treasure Class for the Crystall Ball. Overrides all abstract methods from the parent class
 * which is the Treasure. 
 * @author (Matekoa) 
 * @version (22 Nov 2015)
 */
public class CrystalBall extends Treasure
{
  public CrystalBall(){
      name = "CrystallBall";
      reward = 2000;
    }
    
  public int getWorth(){
      return reward;
    }  
      
  public String toString()
    {
        return name;
    }
     
}
