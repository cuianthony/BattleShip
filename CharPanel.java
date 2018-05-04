/******************************************************************
 * Description:
 * 
 * The Character Panel class extends JPanel. It is used 
 * to display player information. The information is updated in real 
 * time as the game is played.
 *  
 * @author (Anthony Cui) 
 * @version (v1.0)
 * @version (Dec 13, 2015)
 */
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class CharPanel extends JPanel
{
    private final int IP_WIDTH = 1060;
    private final int IP_HEIGHT = 200;
    
    //these widgets are used to build the CharPanel
    private JPanel playerP1, playerP2, playerP3, playerP4, playerInfo;
    
    private JLabel goldLabel1, goldLabel2, goldLabel3, goldLabel4;
    private JLabel playerL1, playerL2, playerL3, playerL4;
    private JLabel currentPlayer, stepLeft;
    
    private BufferedImage goldImage, playerImage1, playerImage2, playerImage3, playerImage4;
    
    private JLabel[] playerGoldNum = new JLabel[4];
    private String[] playerName = {"Fighter", "Wizard  ", "Rogue    ", "Cleric   "};
    
    /******************************************************************
     * Description:
     * The CharPanel constructor.
     * 
     * Limitations: none
     *****************************************************************/
    public CharPanel()
    {       
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        //read images from png files
        try{
            goldImage = ImageIO.read(new File("gold2.png"));
            playerImage1 = ImageIO.read(new File("Fighter.png"));
            playerImage2 = ImageIO.read(new File("Wizard.png"));
            playerImage3 = ImageIO.read(new File("Rogue.png"));
            playerImage4 = ImageIO.read(new File("Cleric.png"));
        }catch(IOException e){
            System.err.println("Image for Tile not Found...");
        }
        
        //add gold image to labels
        goldLabel1 = new JLabel(new ImageIcon(goldImage));
        goldLabel2 = new JLabel(new ImageIcon(goldImage));
        goldLabel3 = new JLabel(new ImageIcon(goldImage));
        goldLabel4 = new JLabel(new ImageIcon(goldImage));
        
        //add player images to labels
        playerL1 = new JLabel(new ImageIcon(playerImage1));
        playerL2 = new JLabel(new ImageIcon(playerImage2));
        playerL3 = new JLabel(new ImageIcon(playerImage3));
        playerL4 = new JLabel(new ImageIcon(playerImage4));
        
        //gold amount earned
        for(int i=0; i<4; i++)
        {
            playerGoldNum[i] = new JLabel("0   ");
        }
        
        
        playerP1 = new JPanel();
        playerP2 = new JPanel();
        playerP3 = new JPanel();
        playerP4 = new JPanel();
        playerP1.setLayout(new BoxLayout(playerP1, BoxLayout.X_AXIS));
        playerP2.setLayout(new BoxLayout(playerP2, BoxLayout.X_AXIS));
        playerP3.setLayout(new BoxLayout(playerP3, BoxLayout.X_AXIS));
        playerP4.setLayout(new BoxLayout(playerP4, BoxLayout.X_AXIS));
        
        //pack player info
        playerP1.add(playerL1);
        playerP1.add(goldLabel1);
        playerP1.add(Box.createRigidArea(new Dimension(10,0)));
        playerP1.add(playerGoldNum[0]);
        playerP2.add(playerL2);
        playerP2.add(goldLabel2);
        playerP2.add(Box.createRigidArea(new Dimension(10,0)));
        playerP2.add(playerGoldNum[1]);
        playerP3.add(playerL3);
        playerP3.add(goldLabel3);
        playerP3.add(Box.createRigidArea(new Dimension(10,0)));
        playerP3.add(playerGoldNum[2]);
        playerP4.add(playerL4);
        playerP4.add(goldLabel4);
        playerP4.add(Box.createRigidArea(new Dimension(10,0)));
        playerP4.add(playerGoldNum[3]);

        
        currentPlayer = new JLabel("Current Player: Fighter");
        currentPlayer.setForeground(Color.RED);
        currentPlayer.setFont(new Font("Arial", Font.BOLD, 14));
        stepLeft = new JLabel("Step Left: 5");
        stepLeft.setForeground(Color.RED);
        stepLeft.setFont(new Font("Arial", Font.BOLD, 14));
        playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
        playerInfo.add(currentPlayer);
        playerInfo.add(Box.createRigidArea(new Dimension(0,8)));
        playerInfo.add(stepLeft);
        
        //pack everything together
        add(playerP1);
        add(Box.createRigidArea(new Dimension(30,0)));
        add(playerP2);
        add(Box.createRigidArea(new Dimension(30,0)));
        add(playerP3);
        add(Box.createRigidArea(new Dimension(30,0)));
        add(playerP4);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(playerInfo);  
    }

    /******************************************************************
     * Description:
     * This method is called in the JDungeonMap when the keys "S", "W",
     * "A","D" are pressed. This method updates the current player info
     * 
     * Limitations: none
     *****************************************************************/
    void update(Characters player, int playerNumber)
    {
        playerGoldNum[playerNumber].setText(Integer.toString(player.getGold()));

        currentPlayer.setText("Current Player: " + playerName[playerNumber]);
        stepLeft.setText("Step Left: "+ (5-player.getMoves()));
    }
    
    /******************************************************************
     * Description:
     * This method cleans up the player info and sets it to default values
     * 
     * Limitations: none
     *****************************************************************/
    void newGame()
    {
        for(int i=0; i<4; i++)
        {
            playerGoldNum[i].setText("0   ");
        }
        currentPlayer.setText("Current Player: Fighter");
        stepLeft.setText("Step Left: "+ 5);
    }
}
