
/**
 * This class represents a battle sequence.
 * 
 * @author (Michael Fan) 
 * @version (Dec 2, 2015)
 */
public class Battle
{
    /**
     * The player has to roll a certain number accrding to the monster in order to kill the monster. And gain a treasure.
     * If the player fails to kill the monster than the monster will roll against the player. 
     * According to the rule book:
     * 5 or lower is a miss
     * 6-7 is stuuned. Lose 1 random treasure
     * 8-10 is wounded. Lose 1 random treasure
     * 11 is seriously wounded. Drop half of the treasure
     * 12 is Killed. Drop all of the treasure card and goes back to the spawn tile
     */
    public static void fight(Monster monster, Characters player)
    {
        // First the player rolls
        int playerDamage = player.getAttack();
        int damageRequired = -1;
        if(player.getType().equals("wizard"))
        {
            damageRequired = monster.getWizard();
        }
        else if(player.getType().equals("fighter"))
        {
            damageRequired = monster.getFighter();
        }
        else if(player.getType().equals("cleric"))
        {
            damageRequired = monster.getCleric();
        }
        else if(player.getType().equals("rogue"))
        {
            damageRequired = monster.getRogue();
        }
        
        // Kills the monster
        if(playerDamage >= damageRequired)
        {
            // You win
            System.out.println("You rolled a "+playerDamage +" and killed the monster!");
            System.out.println("The monster has dropped: " + monster.getTreasure());
            System.out.println();
            monster.die();
            player.gainTreasure(monster.getTreasure());
            player.map.numMonster--;
            player.setMoves(5); // Ends the turn
        }
        else
        {
            // The monster gets to attack you
            System.out.println("You rolled a "+playerDamage +" and failed to kill the monster!");
            System.out.println("The monster attacks back!");
            int monsterDamage = monster.getAttack();
            
            if(monsterDamage == 12)
            {
                // The monster kills the player
                System.out.println("The monster has killed you.");
                player.setXY(player.map.spawnX,player.map.spawnY);
                player.dropAll();
            }
            else if(monsterDamage >= 11)
            {
                // The monster seriously wounds the player
                System.out.println("The monster has seriously wounded you. You drop a lot of gold.");
                player.dropHalf();
            }
            else if(monsterDamage >= 8)
            {
                // The monster wounds the player
                System.out.println("The monster has wounded you. You drop some gold.");
                System.out.println();
                player.dropOne();
            }
            else if(monsterDamage >= 6)
            {
                // Stuns the player
                System.out.println("The monster stunned you. You drop some gold.");
                System.out.println();
                player.dropOne();
            }
            else if(monsterDamage <= 5)
            {
                // The monster misses
                System.out.println("The monster missed.");
            }
            player.setMoves(5); // Ends the turn
        }
        
        System.out.println();
        player.printTreasure();
        System.out.println();
        
        // Refresh the map if all the monsters is killed
        if(player.map.numMonster == 0)
        {
            player.map.saveGold();
            player.map.setNextPlayer();
            player.map.mapName = "map2.txt";
            player.map.getMap();
        }
    }
}
