
/**
 * The driver class
 * 
 * @author (Michael Fan) 
 * @version (Dec 2, 2015)
 */

import javax.swing.JFrame;

public class Game
{
  public static void main (String[] args)
  {
      JFrame frame = new JFrame("Dungeon Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      frame.getContentPane().add(new GamePanel());
      
      frame.setResizable(false);
      frame.pack();
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
      
      System.out.println("***** Dungeon Game *****");
      System.out.println();
      System.out.println("~~~ Fighter turn begins ~~~");
  }
}
