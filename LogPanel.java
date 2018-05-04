/******************************************************************
 * Description:
 * 
 * The LogPanel class extends JPanel. It builds the UI components of the
 * JTextArea for logs and two control buttons "New Game" and "End Turn"
 * 
 * @author (Anthony Cui & Matekoa) 
 * @version (v1.0)
 * @version (Dec 13, 2015)
 ******************************************************************/
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

public class LogPanel extends JPanel
{
    private JButton newGame, endTurn;
    private JDungeonMap map;
    
    /******************************************************************
     * Description:
     * The LogPanel constructor.
     * 
     * Limitations: none
     *****************************************************************/
    public LogPanel(JDungeonMap map)
    {
        setPreferredSize(new Dimension(280,600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //create the text area
        JTextArea textArea = new JTextArea(40,5);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        
        //add scroll bars for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        //use the text area for System output
        PrintStream dungeonLogStream = new PrintStream(new DungeonLogStream(textArea));
        System.setOut(dungeonLogStream);
        this.map = map;
        
        //create two buttons
        newGame = new JButton("New Game");
        endTurn = new JButton("  End Turn  ");
        newGame.setFocusable(false);
        endTurn.setFocusable(false);
        
        //pack things together
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(0,25)));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createRigidArea(new Dimension(0,25)));
        
        add(newGame);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(endTurn);
        
        //add button lisenter
        ButtonListener listener = new ButtonListener();
        newGame.addActionListener(listener);
        endTurn.addActionListener(listener);
    }
    
    /******************************************************************
     * Description:
     * The ButtonListener class implements the actions to be taken 
     * when buttons are pressed.
     * 
     * When the "New Game" button is pressed, a new game map will be 
     * loaded and all players' info is reset.
     * 
     * User may choose to skip his turn or end his turn. When the 
     * "End Turn" button is pressed, the current player' turn is
     * ended, and the game moves to the next player.
     * 
     * Limitations: none
     *****************************************************************/
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == newGame)
            {
                map.newGame();
                map.mapName = "map.txt";
                map.getMap();
                System.out.println("Start a new game");
            }
            else if(event.getSource() == endTurn)
            {
                System.out.println("You choose to end your turn");
                map.endTurn();
            }
        }
    }
}
